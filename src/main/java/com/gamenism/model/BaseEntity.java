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
    private Date createDate;
    private Date modifyDate;
    private long version;

    public BaseEntity() {
        this.createDate = new Date();
        this.modifyDate = new Date();
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
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date creationDate) {
        this.createDate = creationDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modificationDate) {
        this.modifyDate = modificationDate;
    }

}
