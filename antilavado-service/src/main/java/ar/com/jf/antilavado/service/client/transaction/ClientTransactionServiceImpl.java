package ar.com.jf.antilavado.service.client.transaction;

import ar.com.jf.antilavado.repository.contant.AlertFrequency;
import ar.com.jf.antilavado.repository.dto.response.client.transaction.SumOfTransaccions;
import ar.com.jf.antilavado.repository.interfaces.AlertManagementRepository;
import ar.com.jf.antilavado.repository.interfaces.ClientRepository;
import ar.com.jf.antilavado.repository.interfaces.ClientTransactionsRepository;
import ar.com.jf.antilavado.repository.interfaces.StatusTypeRepository;
import ar.com.jf.antilavado.repository.model.AlertManagement;
import ar.com.jf.antilavado.repository.model.Client;
import ar.com.jf.antilavado.repository.model.ClientTransaction;
import ar.com.jf.antilavado.service.exceptions.ClientTransactionException;
import ar.com.jf.antilavado.service.ftp.FtpService;
import com.google.common.base.Strings;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.util.List;

/**
 * ClientTransactionServiceImpl.java
 * <p>
 * PLAYFT.
 * <p>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p>
 * Created by fvaldes on 16/03/2016.
 */
@Service
public class ClientTransactionServiceImpl implements ClientTransactionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientTransactionServiceImpl.class);

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientTransactionsRepository clientTransactionsRepository;

    @Autowired
    private FtpService ftpService;

    @Autowired
    private AlertManagementRepository alertManagementRepository;

    @Autowired
    private StatusTypeRepository statusTypeRepository;

    @Value("${ftp.transactions.registration.path}")
    private String registrationPath;

    @Value("${ftp.transactions.result.path}")
    private String resultPath;

    @Value("${alerts.process.frequency}")
    private AlertFrequency frequency;

    @Override
    @Transactional
    @Scheduled(cron = "0 00 23 ? * *")
    public void importTransactions() throws Exception {

        List<String> files = ftpService.listFiles(registrationPath);

        if(CollectionUtils.isEmpty(files)){
            return;
        }

        for (String file : files) {

            List<String> lines = ftpService.downloadFileAndReadLines(registrationPath + file);

            String result = "";
            for (String line : lines) {
                result = result + line;
                try{
                    ClientTransaction transaction = getTransactionData(line);
                    transaction.setImportDate(DateTime.now());
                    transaction.setFileName(file);
                    try {
                        clientTransactionsRepository.saveAndCommit(transaction);
                        result = result + " | OK\n";
                    }catch (Exception e){
                        LOGGER.error("No se pudo guardar la informacion. Ya existe un registro con estos datos.");
                        result = result + " | No se pudo guardar la informacion. Ya existe un registro con estos datos.\n";
                    }
                }catch (ClientTransactionException e){
                    LOGGER.error("Fail to read file: " + file + " in line: " + line);
                    result = result + " | " + e.getMessage() + "\n";
                }catch (Exception e){
                    LOGGER.error("Fail to read file: " + file + " in line: " + line);
                    result = result + " | Fallo en la lectura de la linea: " + line + "\n";
                }
            }

            ftpService.uploadFile(resultPath + file, IOUtils.toInputStream(result));
            ftpService.deleteFile(registrationPath + file);
        }


    }

    private ClientTransaction getTransactionData(String line) throws Exception{
        ClientTransaction t = new ClientTransaction();
        String errors = "";
        String[] values = line.split(";");
        if (values == null || values.length < 4){
            errors = errors + "Formato incorrecto de la linea o no posee los 4 campos minimos requeridos. ";
            throw new ClientTransactionException(errors);
        }
        try{
            t.setClient(getClient(values[0].trim()));
        }catch (ClientTransactionException e) {
            errors = errors + e.getMessage();
        }catch (Exception e){
            errors = errors + "No se encontro un cliente (o un solo cliente) con el cuit/cuil: " + values[0] + ". ";
        }
        try{
            t.setInvoiceNumber(getInvoiceNumber(values[1].trim()));
        }catch (ClientTransactionException e) {
            errors = errors + e.getMessage();
        }catch (Exception e){
            errors = errors + "El numero de factura deben ser solo numeros. ";
        }
        try{
            t.setInvoiceAmount(getInvoiceAmount(values[2].trim()));
        }catch (ClientTransactionException e) {
            errors = errors + e.getMessage();
        }catch (Exception e){
            errors = errors + "El formato del monto es incorrecto. Formato correcto: ###0.0#. ";
        }
        try{
            t.setInvoiceDate(getInvoiceDate(values[3].trim()));
        }catch (ClientTransactionException e) {
            errors = errors + e.getMessage();
        }catch (Exception e){
            errors = errors + "El formato de la fecha es incorrecto. Formato correcto: DD/MM/YYYY. ";
        }
        if(!Strings.isNullOrEmpty(errors)){
            throw new ClientTransactionException(errors);
        }
        return t;
    }

    private String getInvoiceNumber(String invoiceNumber) {
        if(Strings.isNullOrEmpty(invoiceNumber)){
            throw new ClientTransactionException("La columna de numero de factura esta vacia. ");
        }
        if(invoiceNumber.matches("[0-9]+")){
            return invoiceNumber;
        }
        throw new ClientTransactionException("El nro de factura debe ser un numero. ");
    }

    private BigDecimal getInvoiceAmount(String amount) throws ParseException {
        if(Strings.isNullOrEmpty(amount)){
            throw new ClientTransactionException("La columna de monto esta vacia. ");
        }
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        String pattern = "###0.0#";
        DecimalFormat decimalFormat = new DecimalFormat(pattern, symbols);
        decimalFormat.setParseBigDecimal(true);
        return (BigDecimal) decimalFormat.parse(amount);
    }

    private DateTime getInvoiceDate(String date) throws IllegalArgumentException{
        if(Strings.isNullOrEmpty(date)){
            throw new ClientTransactionException("La columna de la fecha esta vacia. ");
        }
        DateTimeFormatter dtfOut = DateTimeFormat.forPattern("dd/MM/yyyy");
        return dtfOut.parseDateTime(date);
    }

    private Client getClient (String cuitCuil) throws Exception{
        if(Strings.isNullOrEmpty(cuitCuil)){
            throw new ClientTransactionException("La columna de cuit/cuil esta vacia. ");
        }
        List<Client> clients = clientRepository.getByCuitCuil(cuitCuil);
        if(CollectionUtils.isNotEmpty(clients)){
            if(clients.size() < 2){
                return clients.get(0);
            }else{
                LOGGER.error("More than one client found by cuit/cuil: " + cuitCuil);
                throw new ClientTransactionException("Mas de un client encontrado con el cuit/cuil: " + cuitCuil + ". ");
            }
        }else{
            LOGGER.error("Client not found by cuit/cuil: " + cuitCuil);
            throw new ClientTransactionException("Cliente no encontrado con el cuit/cuil: " + cuitCuil + ". ");
        }
    }

    @Override
    @Transactional
    @Scheduled(cron = "${alerts.process.time}")
    public void jobTransactions(){
        List<SumOfTransaccions> txs = clientTransactionsRepository
                .findTransactionsBy(frequency.getDateTime());

        for (SumOfTransaccions sum : txs) {
            if(sum.getClient().getEconomicFinancialProfile().getMonthlyEstimate().compareTo(sum.getSumAmounts()) < 0 ){
                AlertManagement alert = new AlertManagement();
                alert.setClient(sum.getClient());
                alert.setPeriodAmount(sum.getSumAmounts());
                alert.setDifference(sum.getSumAmounts().subtract(sum.getClient().getEconomicFinancialProfile().getMonthlyEstimate()));
                alert.setUnusualOperation(statusTypeRepository.load(5L)); //FIXME: ID 5 = Estado "SIN TRATAMIENTO"

                DateTime periodAnalyzed = DateTime.now();
                alert.setPeriodAnalyzed(periodAnalyzed);
                alert.setPeriodDescription(frequency.getPeriodDesc(periodAnalyzed));

                alert.setCreatedDate(DateTime.now());
                alert.setCreatedBy("system");

                alertManagementRepository.save(alert);
            }
        }

    }


}
