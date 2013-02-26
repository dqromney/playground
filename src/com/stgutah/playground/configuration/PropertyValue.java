package com.stgutah.playground.configuration;

/**
 * Property Value, defined and default values.
 *
 * User: dqromney
 * Date: 10/16/12
 * Time: 4:35 PM
 */
public class PropertyValue {
    private String name;
    private String value;
    private String defaultValue;

    public PropertyValue(String name, String value, String defaultValue) {
        this.name = name;
        this.value = value;
        this.defaultValue = defaultValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
