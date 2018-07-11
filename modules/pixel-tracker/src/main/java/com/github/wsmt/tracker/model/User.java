package com.github.wsmt.tracker.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class User {

    private String id;
    private Map<String, String> info = new HashMap<>();
    private Map<String, String> behavior = new HashMap<>();

    public User(String id) {
        this.id = id;
    }

    public User() {
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, String> getInfo() {
        return info;
    }

    public void setInfo(Map<String, String> info) {
        this.info = info;
    }

    public Map<String, String> getBehavior() {
        return behavior;
    }

    public void setBehavior(Map<String, String> behavior) {
        this.behavior = behavior;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                '}';
    }
}
