package com.stgutah.playground.configuration;

/**
 * Protocol (Communication) Type.
 *
 * User: dqromney
 * Date: 10/16/12
 * Time: 4:12 PM
 */
public enum ProtocolType {

    UNDEFINED("Undefined"),
    SFTP("Secure File Transfer Protocol");

    String description;

    private ProtocolType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
