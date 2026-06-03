package config;

import enums.LogLevel;

import java.util.Set;

public class AppenderConfig {

    private String target;

    private Set<LogLevel> logLevels;

    private String endpoint;

    private Integer port;

    private String username;

    private String password;

    private String filePath;

    public String getTarget() {
        return target;
    }

    public Set<LogLevel> getLogLevels() {
        return logLevels;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public Integer getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFilePath() {
        return filePath;
    }
}