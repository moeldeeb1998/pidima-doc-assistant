package com.deebspace.pidimadocassistant.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Message {
    private String id;
    @NotBlank
    private String sessionId;
    @NotBlank
    private String role; // User | Assistant
    @NotBlank
    private String text;
    private Instant timestamp;

    public Message(String sessionId, String role, String text) {
        this.id = UUID.randomUUID().toString();
        this.sessionId = sessionId;
        this.role = role;
        this.text = text;
        this.timestamp = Instant.now();
    }
}
