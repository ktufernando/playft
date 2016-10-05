package ar.com.jf.antilavado.repository.model;

import ar.com.jf.antilavado.repository.model.util.CustomDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Objects;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * FileMovement.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 14/09/2015.
 */
@Entity
@Table(name = "FILES_MOVEMENT", indexes={
        @Index(name="FILES_MOVEMENT_CLIENT_ID_INDEX", columnList="CLIENT_ID")})
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FileMovement extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FILES_MOVEMENT_ID")
    private Long id;

    @NotNull
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "ORDER_DATE", nullable = false)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private DateTime orderDate;

    @NotNull
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "SHIPPING_DATE", nullable = false)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private DateTime shippingDate;

    @NotNull
    @Size(min = 0, max = 50)
    @Column(name = "APPLICANT", length = 50, nullable = false)
    private String applicant;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "RETURN_DATE")
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private DateTime returnDate;

    @NotNull
    @Size(min = 0, max = 50)
    @Column(name = "LOCATION", length = 50, nullable = false)
    private String location;

    @Size(min = 0, max = 100)
    @Column(name = "COMMENTS", length = 100)
    private String comments;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    private Client client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(DateTime orderDate) {
        this.orderDate = orderDate;
    }

    public DateTime getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(DateTime shippingDate) {
        this.shippingDate = shippingDate;
    }

    public DateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(DateTime returnDate) {
        this.returnDate = returnDate;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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
        if (!(o instanceof FileMovement)) return false;
        FileMovement that = (FileMovement) o;
        return Objects.equal(getId(), that.getId()) &&
                Objects.equal(getClient(), that.getClient());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getClient());
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("FileMovement{");
        sb.append("id=").append(id);
        sb.append(", orderDate=").append(orderDate);
        sb.append(", shippingDate=").append(shippingDate);
        sb.append(", applicant='").append(applicant).append('\'');
        sb.append(", returnDate=").append(returnDate);
        sb.append(", location='").append(location).append('\'');
        sb.append(", comments='").append(comments).append('\'');
        sb.append(", client=").append(client);
        sb.append('}');
        return sb.toString();
    }
}