package com.stgutah.playground.configuration;

/**
 * Connection Configuration.
 *
 * User: dqromney
 * Date: 10/16/12
 * Time: 4:05 PM
 */
public class ConnectionConfiguration {
    private PropertyValue host;
    private PropertyValue hostPort;
    private PropertyValue user;
    private PropertyValue password;
    private PropertyValue privateKeyFile;
    private PropertyValue privateKeyUsed;
    private PropertyValue timeoutInSeconds;

    public ConnectionConfiguration(PropertyValue host, PropertyValue hostPort, PropertyValue user, PropertyValue password, PropertyValue privateKeyFile, PropertyValue privateKeyUsed, PropertyValue timeoutInSeconds) {
        this.host = host;
        this.hostPort = hostPort;
        this.user = user;
        this.password = password;
        this.privateKeyFile = privateKeyFile;
        this.privateKeyUsed = privateKeyUsed;
        this.timeoutInSeconds = timeoutInSeconds;
    }

    public PropertyValue getHost() {
        return host;
    }

    public void setHost(PropertyValue host) {
        this.host = host;
    }

    public PropertyValue getHostPort() {
        return hostPort;
    }

    public void setHostPort(PropertyValue hostPort) {
        this.hostPort = hostPort;
    }

    public PropertyValue getUser() {
        return user;
    }

    public void setUser(PropertyValue user) {
        this.user = user;
    }

    public PropertyValue getPassword() {
        return password;
    }

    public void setPassword(PropertyValue password) {
        this.password = password;
    }

    public PropertyValue getPrivateKeyFile() {
        return privateKeyFile;
    }

    public void setPrivateKeyFile(PropertyValue privateKeyFile) {
        this.privateKeyFile = privateKeyFile;
    }

    public PropertyValue getPrivateKeyUsed() {
        return privateKeyUsed;
    }

    public void setPrivateKeyUsed(PropertyValue privateKeyUsed) {
        this.privateKeyUsed = privateKeyUsed;
    }

    public PropertyValue getTimeoutInSeconds() {
        return timeoutInSeconds;
    }

    public void setTimeoutInSeconds(PropertyValue timeoutInSeconds) {
        this.timeoutInSeconds = timeoutInSeconds;
    }
}
