package com.scraper.scraper.restApi.model;

import java.util.UUID;

public class CoffeeBean {
    private final String id;
    private String name;

    public CoffeeBean(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public CoffeeBean(String name) {
        this(UUID.randomUUID().toString(), name);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
