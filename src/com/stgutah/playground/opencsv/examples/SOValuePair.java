package com.stgutah.playground.opencsv.examples;

/**
 * Service Value Pair Bean.
 *
 * IDENTIFIER(Originator|ClientId|SubClientId|ServiceTemplateName|isValid)~SERVICE_DETAIL_TYPE_DATA
 *
 * User: dqromney
 * Date: Dec 9, 2011
 * Time: 12:28:38 PM
 */
public class SOValuePair {

    String key;
    String value;

    public SOValuePair(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("SOValuePair");
        sb.append("{key='").append(key).append('\'');
        sb.append(", value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
