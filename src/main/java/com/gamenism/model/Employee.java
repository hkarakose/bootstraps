package com.gamenism.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.io.Serializable;

@Entity
public class Employee extends BaseEntity {
    private String title;
    private String name;
    private String hiddenValue;

    public Employee() {
    }

    public String getHiddenValue() {
        return hiddenValue;
    }

    public void setHiddenValue(String hiddenValue) {
        this.hiddenValue = hiddenValue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append("hiddenValue='").append(hiddenValue).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
