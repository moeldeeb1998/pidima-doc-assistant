package com.deebspace.pidimadocassistant.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    /**
     * Number of days to retain chat history
     */
    private int retentionDays;

}
