package com.deebspace.pidimadocassistant.models;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class Session {
    private String id;
    private String metaData;
    private Instant createdAt;
    private Instant updatedAt;

    public Session(String metaData) {
        this.id = UUID.randomUUID().toString();
        this.metaData = metaData;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }
}
