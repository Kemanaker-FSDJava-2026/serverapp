package com.metrodata.serverapp.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public record AppPropertiesConfig(String name, String version, String description) {
}
