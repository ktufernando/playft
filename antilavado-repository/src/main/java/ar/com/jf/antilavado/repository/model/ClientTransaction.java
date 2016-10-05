package ar.com.jf.antilavado.repository.model;

import ar.com.jf.antilavado.repository.model.util.CustomDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * ClientTransaction.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by fvaldes on 16/03/2016.
 */
@Entity
@Table(name = "CLIENT_TRANSACTIONS")
@IdClass(ClientTransaction.ClientTransactionPK.class)
public class ClientTransaction implements Serializable{

    public static class ClientTransactionPK implements Serializable {

        protected String invoiceNumber;
        protected DateTime invoiceDate;
        protected Client client;

        public ClientTransactionPK() {
        }

        public ClientTransactionPK(String invoiceNumber, DateTime invoiceDate, Client client) {
            this.invoiceNumber = invoiceNumber;
            this.invoiceDate = invoiceDate;
            this.client = client;
        }

        public String getInvoiceNumber() {
            return invoiceNumber;
        }

        public void setInvoiceNumber(String invoiceNumber) {
            this.invoiceNumber = invoiceNumber;
        }

        public DateTime getInvoiceDate() {
            return invoiceDate;
        }

        public void setInvoiceDate(DateTime invoiceDate) {
            this.invoiceDate = invoiceDate;
        }

        public Client getClient() {
            return client;
        }

        public void setClient(Client client) {
            this.client = client;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ClientTransactionPK)) return false;
            ClientTransactionPK that = (ClientTransactionPK) o;
            return
                    Objects.equals(invoiceNumber, that.invoiceNumber) &&
                    Objects.equals(invoiceDate, that.invoiceDate) &&
                    Objects.equals(client, that.client);
        }

        @Override
        public int hashCode() {
            return Objects.hash(invoiceNumber, invoiceDate, client);
        }
    }


    @Id
    @NotNull
    @Column(name = "INVOICE_NUMBER", nullable = false)
    private String invoiceNumber;

    @NotNull
    @Column(name = "INVOICE_AMOUNT", nullable = false)
    private BigDecimal invoiceAmount;

    @Id
    @NotNull
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "INVOICE_DATE", nullable = false)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private DateTime invoiceDate;

    @NotNull
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "IMPORT_DATE", nullable = false)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private DateTime importDate;

    @Size(min = 0, max = 50)
    @Column(name = "FILE_NAME", length = 50)
    private String fileName;

    @Id
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    private Client client;

    public BigDecimal getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(BigDecimal invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public DateTime getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(DateTime invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public DateTime getImportDate() {
        return importDate;
    }

    public void setImportDate(DateTime importDate) {
        this.importDate = importDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ClientTransaction{");
        sb.append(", invoiceNumber='").append(invoiceNumber).append('\'');
        sb.append(", invoiceAmount=").append(invoiceAmount);
        sb.append(", invoiceDate=").append(invoiceDate);
        sb.append(", importDate=").append(importDate);
        sb.append(", fileName='").append(fileName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
