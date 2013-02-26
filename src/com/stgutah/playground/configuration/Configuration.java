package com.stgutah.playground.configuration;

import java.util.Properties;

/**
 * Configuration Interface.
 *
 * User: dqromney
 * Date: 10/16/12
 * Time: 3:10 PM
 */
public interface Configuration {

    public Properties getAll();
    public Properties findByGroup(String pGroup);
    public Properties findByGroupAndName(String pGroup, String pName);

}
