package com.gamenism.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * User: halil
 * Date: 9/19/13
 * Time: 7:25 PM
 */
@MappedSuperclass
public class BaseEntity implements Serializable {
    private Long id;
    private Date creationDate;
    private Date modificationDate;
    private long version;

    public BaseEntity() {
        this.creationDate = new Date();
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Version
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @Temporal(value = TemporalType.TIMESTAMP)
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

}
