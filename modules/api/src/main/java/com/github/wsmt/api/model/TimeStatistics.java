package com.github.wsmt.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "time")
public class TimeStatistics {
    @Id
    @GeneratedValue
    @JsonIgnore
    private final Integer id = null;
    private final String time = null;
    private final Integer count = null;
    @JsonIgnore
    private final Date report = null;

    public Integer getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public Integer getCount() {
        return count;
    }

    public Date getReport() {
        return report;
    }
}
