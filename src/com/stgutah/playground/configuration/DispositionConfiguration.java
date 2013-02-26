package com.stgutah.playground.configuration;

/**
 * Disposition (file) Configuration.
 *
 * User: dqromney
 * Date: 10/16/12
 * Time: 4:11 PM
 */
public class DispositionConfiguration {
    private ProtocolType protocolType = ProtocolType.UNDEFINED;
    private PropertyValue basePath; // i.e. /DEV, /TEST, /PROD
    private PropertyValue filePath; // i.e. /FromSFS/Results
    private PropertyValue filePattern; // i.e. T0001623_(\\d*)

    public DispositionConfiguration(ProtocolType protocolType, PropertyValue basePath, PropertyValue filePath, PropertyValue filePattern) {
        this.protocolType = protocolType;
        this.basePath = basePath;
        this.filePath = filePath;
        this.filePattern = filePattern;
    }

    public ProtocolType getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(ProtocolType protocolType) {
        this.protocolType = protocolType;
    }

    public PropertyValue getBasePath() {
        return basePath;
    }

    public void setBasePath(PropertyValue basePath) {
        this.basePath = basePath;
    }

    public PropertyValue getFilePath() {
        return filePath;
    }

    public void setFilePath(PropertyValue filePath) {
        this.filePath = filePath;
    }

    public PropertyValue getFilePattern() {
        return filePattern;
    }

    public void setFilePattern(PropertyValue filePattern) {
        this.filePattern = filePattern;
    }
}
