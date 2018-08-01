package com.github.wsmt.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "browser")
public class BrowserStatistics {
    @Id
    @GeneratedValue
    @JsonIgnore
    private final Integer id = null;
    private final String browser = null;
    private final Integer count = null;
    @JsonIgnore
    private final Date report = null;

    public Integer getId() {
        return id;
    }

    public String getBrowser() {
        return browser;
    }

    public Integer getCount() {
        return count;
    }

    public Date getReport() {
        return report;
    }
}