package ar.com.jf.antilavado.repository.model;

import com.google.common.base.Objects;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * GeneralFile.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 14/09/2015.
 */
@Entity
@Table(name = "GENERAL_FILES", indexes={
        @Index(name="GENERAL_FILES_CLIENT_ID_INDEX", columnList="CLIENT_ID")})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class GeneralFile extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GENERAL_FILE_ID")
    private Long id;

    @NotNull
    @Size(min = 0, max = 4)
    @Column(name = "ROW", length = 4, nullable = false)
    private String row;

    @NotNull
    @Size(min = 0, max = 4)
    @Column(name = "[COLUMN]", length = 4, nullable = false)
    private String column;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STATUS")
    private StatusType status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    private Client client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
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
        if (!(o instanceof GeneralFile)) return false;
        GeneralFile that = (GeneralFile) o;
        return Objects.equal(getId(), that.getId()) &&
                Objects.equal(getClient(), that.getClient());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getClient());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GeneralFile{");
        sb.append("id=").append(id);
        sb.append(", row='").append(row).append('\'');
        sb.append(", column='").append(column).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", client=").append(client);
        sb.append('}');
        return sb.toString();
    }
}
