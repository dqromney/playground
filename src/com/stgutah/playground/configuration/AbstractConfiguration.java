package com.stgutah.playground.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Abstract Configuration class.
 * User: dqromney
 * Date: 10/16/12
 * Time: 3:12 PM
 */
public abstract class AbstractConfiguration implements Configuration {

    protected ConfigurationType configurationType = ConfigurationType.UNDEFINED;
    protected Client client;
    protected GroupConfiguration configurationGroup;
    protected ConnectionConfiguration connectionConfiguration;
    protected DispositionConfiguration dispositionConfiguration;
    protected List<PropertyValue> valueList = new ArrayList<PropertyValue>(0);

    public ConfigurationType getConfigurationType() {
        return configurationType;
    }

    public void setConfigurationType(ConfigurationType configurationType) {
        this.configurationType = configurationType;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public GroupConfiguration getConfigurationGroup() {
        return configurationGroup;
    }

    public void setConfigurationGroup(GroupConfiguration configurationGroup) {
        this.configurationGroup = configurationGroup;
    }

    public ConnectionConfiguration getConnectionConfiguration() {
        return connectionConfiguration;
    }

    public void setConnectionConfiguration(ConnectionConfiguration connectionConfiguration) {
        this.connectionConfiguration = connectionConfiguration;
    }

    public DispositionConfiguration getDispositionConfiguration() {
        return dispositionConfiguration;
    }

    public void setDispositionConfiguration(DispositionConfiguration dispositionConfiguration) {
        this.dispositionConfiguration = dispositionConfiguration;
    }

}
