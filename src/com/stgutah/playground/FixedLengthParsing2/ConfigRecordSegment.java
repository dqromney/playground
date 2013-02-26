package com.stgutah.playground.FixedLengthParsing2;

import org.apache.commons.lang.StringUtils;

import java.util.Properties;

/**
 * Configuration of a FLF Record segment.
 *
 * User: dqromney
 * Date: 6/20/12
 * Time: 2:38 PM
 */
public class ConfigRecordSegment {
    private ConfigFileSegment configFileSegment;
    private String fieldName; // HDR-REC-ID, FILLER, etc.
    private String picture; // X(3), X, 9(9)V99, 9(2).
    private String fieldDescription; // "Loan number of the property to be inspected"
    private Integer positionStart; // 56
    private Integer positionEnd; // 57
    private Properties validValues; // N=No,Y=Yes.

    // ----------------------------------------------------------------
    // Constructors
    // ----------------------------------------------------------------
    public ConfigRecordSegment() {
        this.configFileSegment = new ConfigFileSegment();
        this.fieldName = "SEG-NAME";
        this.picture = "X(10)";
        this.fieldDescription = "Segment description.";
        this.positionStart = 1;
        this.positionEnd = 10;
        this.validValues = new Properties();
    }

    public ConfigRecordSegment(ConfigFileSegment configFileSegment, String fieldName, String picture, String fieldDescription, Integer positionStart, Integer positionEnd, Properties validValues) {
        this.configFileSegment = configFileSegment;
        this.fieldName = fieldName;
        this.picture = picture;
        this.fieldDescription = fieldDescription;
        this.positionStart = positionStart;
        this.positionEnd = positionEnd;
        this.validValues = validValues;
    }

    public ConfigRecordSegment(ConfigRecordSegment pConfigRecordSegment) {
        this.configFileSegment = pConfigRecordSegment.getConfigFileSegment();
        this.fieldName = getFieldName();
        this.picture = getPicture();
        this.fieldDescription = getFieldDescription();
        this.positionStart = getPositionStart();
        this.positionEnd = getPositionEnd();
        this.validValues = getValidValues();
    }

    // ----------------------------------------------------------------
    // Utility methods
    // ----------------------------------------------------------------
    public Integer getOffset() {
        return positionEnd - (positionStart -1);
    }

    // ----------------------------------------------------------------
    // Accessors
    // ----------------------------------------------------------------
    public ConfigFileSegment getConfigFileSegment() {
        return configFileSegment;
    }

    public void setConfigFileSegment(ConfigFileSegment configFileSegment) {
        this.configFileSegment = configFileSegment;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getFieldDescription() {
        return fieldDescription;
    }

    public void setFieldDescription(String fieldDescription) {
        this.fieldDescription = fieldDescription;
    }

    public Integer getPositionStart() {
        return positionStart;
    }

    public void setPositionStart(Integer positionStart) {
        this.positionStart = positionStart;
    }

    public Integer getPositionEnd() {
        return positionEnd;
    }

    public void setPositionEnd(Integer positionEnd) {
        this.positionEnd = positionEnd;
    }

    public Properties getValidValues() {
        return validValues;
    }

    public void setValidValues(Properties validValues) {
        this.validValues = validValues;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ConfigRecordSegment");
        sb.append("{configFileSegment=").append(configFileSegment == null ?  "UNDEFINED" : configFileSegment.getToken() + configFileSegment.getId());
        sb.append(", fieldName='").append(fieldName).append('\'');
        sb.append(", picture='").append(picture).append('\'');
        sb.append(", fieldDescription='").append(fieldDescription).append('\'');
        sb.append(", positionStart=").append(positionStart);
        sb.append(", positionEnd=").append(positionEnd);
        sb.append(", validValues=").append(validValues);
        sb.append('}');
        return sb.toString();
    }
}
