package com.github.wsmt.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "urls")
public class PageStatistics {
    @Id
    @GeneratedValue
    @JsonIgnore
    private final Integer id = null;

    private final String url = null;

    private final Integer count = null;

    public Integer getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public Integer getCount() {
        return count;
    }
}
