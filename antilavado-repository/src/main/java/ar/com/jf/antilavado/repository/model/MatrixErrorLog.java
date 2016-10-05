package ar.com.jf.antilavado.repository.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * MatrixErrorLog.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by fvaldes on 19/01/2016.
 */
@Entity
@Table(name = "MATRIX_ERROR_LOGS", indexes = {
        @Index(name = "MATRIX_ERROR_LOGS_CLIENT_ID_INDEX", columnList = "CLIENT_ID")})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MatrixErrorLog extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "MATRIX_ERROR_LOGS_ID")
    private Long id;

    @NotNull
    @Size(min = 0, max = 4000)
    @Column(name = "MSG_ERROR", length = 4000, nullable = false)
    private String msgError;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "ERROR_DATE")
    private DateTime errorDate = DateTime.now();

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    private Client client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }

    public DateTime getErrorDate() {
        return errorDate;
    }

    public void setErrorDate(DateTime errorDate) {
        this.errorDate = errorDate;
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
        if (!(o instanceof MatrixErrorLog)) return false;
        MatrixErrorLog district = (MatrixErrorLog) o;
        return Objects.equal(getId(), district.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MatrixErrorLog{");
        sb.append("id=").append(id);
        sb.append(", msgError='").append(msgError).append('\'');
        sb.append(", errorDate=").append(errorDate);
        sb.append('}');
        return sb.toString();
    }
}
