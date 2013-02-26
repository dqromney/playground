package com.stgutah.playground.configuration;

/**
 * Configuration Type.
 *
 * User: dqromney
 * Date: 10/16/12
 * Time: 3:54 PM
 */
public enum ConfigurationType {

    UNDEFINED("Undefined"),
    SERVICE_AUTOMATION("Service Automation"),
    NOTIFICATION_SERVICE("Notification Services"),
    SERVICE_LOGS("Service Logs"),
    DOCUMENT_MANAGEMENT_SERVICE("Document Management Service"),
    ACCOUNT_EXPORT_SERVICES("Account Export Services");

    private String description;

    private ConfigurationType(String description) {
        this.description = description;
    }
}
