package ar.com.jf.antilavado.service.matrix;

import ar.com.jf.antilavado.repository.dto.response.matrix.FactorData;
import ar.com.jf.antilavado.repository.dto.response.matrix.RiskMatrix;
import ar.com.jf.antilavado.repository.dto.response.matrix.RiskMatrixCron;
import ar.com.jf.antilavado.repository.interfaces.*;
import ar.com.jf.antilavado.repository.model.*;
import ar.com.jf.antilavado.service.exceptions.FactorHandlerException;
import com.google.common.base.Strings;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

/**
 * MatrixServiceImpl.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by fvaldes on 01/12/2015.
 */
@Service
public class MatrixServiceImpl implements MatrixService {

    @Autowired
    private FactorLevelRepository factorLevelRepository;
    @Autowired
    private FactorsProcessor factorsProcessor;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private MatrixErrorLogRepository matrixErrorLogRepository;
    @Autowired
    private MatrixDetailRepository matrixDetailRepository;
    @Autowired
    private MatrixMasterRepository matrixMasterRepository;
    @Autowired
    private DetailRiskFactorRepository detailRiskFactorRepository;
    @Autowired
    private MessageSource messageSource;

    @Override
    @Transactional(readOnly = true)
    public RiskMatrix getMatrixBy(Long clientId) {
        return getMatrix(clientId);
    }

    @Override
    @Transactional(noRollbackFor = FactorHandlerException.class)
    @Scheduled(cron = "0 00 00 ? * *")
    public void matrixForAllClients(){
        generateMatrixForAllClient(null);
    }

    @Override
    @Transactional(noRollbackFor = FactorHandlerException.class)
    public void matrixForAllClients(String username){
        generateMatrixForAllClient(username);
    }

    private void generateMatrixForAllClient(String username){
        List<FactorLevel> levels = factorLevelRepository.findAll();
        List<Client> clients = clientRepository.findAll();
        matrixMasterRepository.deleteAll();
        for (Client c : clients){
            logicNewMatrix(c, levels, username);
        }
    }

    private void logicNewMatrix(Client c, List<FactorLevel> levels, String username){
        username = Strings.isNullOrEmpty(username) ? "CRON" : username;
        RiskMatrixCron cron = new RiskMatrixCron();
        factorsProcessor.handleOneClient(cron, c.getId());
        if(!Strings.isNullOrEmpty(cron.getErrors())){
            MatrixErrorLog errorLog = new MatrixErrorLog();
            errorLog.setCreatedBy(username);
            errorLog.setErrorDate(DateTime.now());
            errorLog.setMsgError(cron.getErrors());
            errorLog.setClient(c);
            matrixErrorLogRepository.save(errorLog);
        }else{
            MatrixMaster master = new MatrixMaster();
            master.setCreatedBy(username);
            master.setTotalPonderation(cron.getRiskMatrix().getTotalPonderation());
            master.setClient(c);
            FactorLevel level = getLevelForTotalPonderation(cron.getRiskMatrix().getTotalPonderation(), levels);
            master.setFactorLevel(level);
            level.getMatrixMasters().add(master);
            for(FactorData factorData : cron.getRiskMatrix().getFactors()){
                DetailRiskFactor detailRiskFactor = detailRiskFactorRepository.loadBy(factorData.getFactor(), factorData.getImpact());
                MatrixDetail detail = new MatrixDetail();
                detail.setCreatedBy(username);
                detail.setDetailRiskFactor(detailRiskFactor);
                detail.setMatrixMaster(master);
                //detailRiskFactor.getMatrixDetails().add(detail);
                master.getMatrixDetails().add(detail);
            }
            save(master);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void save(MatrixMaster master){
        matrixMasterRepository.saveFlushAndClear(master);
    }

    private FactorLevel getLevelForTotalPonderation(int totalPonderation, List<FactorLevel> levels){
        BigDecimal totalPond = new BigDecimal(totalPonderation);
        for (FactorLevel level : levels) {
            if (level.getLowerBound().compareTo(totalPond) <= 0 && level.getUpperBound().compareTo(totalPond) >= 0) {
                return level;
            }
        }
        return null;
    }

    private RiskMatrix getMatrix(Long clientId){
        RiskMatrix response = new RiskMatrix();
        MatrixMaster master = matrixMasterRepository.loadBy(clientId);
        if(master == null){
            MatrixErrorLog errorLog = matrixErrorLogRepository.loadBy(clientId);
            if(errorLog != null){
                throw new FactorHandlerException(errorLog.getMsgError());
            }else{
                throw new FactorHandlerException(messageSource.getMessage("riskmatrix.null", new Object[]{}, Locale.getDefault()));
            }
        }
        response.setLevel(master.getFactorLevel());
        response.setTotalPonderation(master.getTotalPonderation());
        for(MatrixDetail detail : master.getMatrixDetails()){
            FactorData data = new FactorData(detail.getDetailRiskFactor().getMasterRiskFactor().getDescription(),
                    detail.getDetailRiskFactor().getDescription(), detail.getDetailRiskFactor().getPonderation());
            response.getFactors().add(data);
        }
        return response;
    }

    @Override
    @Transactional
    public void matrixForClient(Long clientId, String username) {
        Client c = clientRepository.get(clientId);
        logicExistMatrix(c, username);
    }

    private void logicExistMatrix(Client c, String username){
        List<FactorLevel> levels = factorLevelRepository.findAll();
        MatrixMaster master = matrixMasterRepository.loadBy(c.getId());
        MatrixErrorLog errorLog = matrixErrorLogRepository.loadBy(c.getId());
        if(errorLog != null){
            matrixErrorLogRepository.delete(errorLog);
        }
        if(master != null){
            for (MatrixDetail detail : master.getMatrixDetails()){
                matrixDetailRepository.delete(detail);
            }
            matrixMasterRepository.delete(master);
        }
        logicNewMatrix(c, levels, username);
    }
}
