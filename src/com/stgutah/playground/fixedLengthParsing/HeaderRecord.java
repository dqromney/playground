package com.stgutah.playground.fixedLengthParsing;

/**
 * Header Record value class.
 *
 * User: dqromney
 * Date: Dec 23, 2010
 * Time: 6:15:50 PM
 */
public class HeaderRecord {

    final String DEFAULT_LINE_SEPARATOR = System.getProperty("line.separator");
    
    // ----------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------
    String recordId;
    String version;
    String fieldServiceCompanyId; // TIN#
    String fieldServiceCompanyOfficeId;
    String mortgageCompanyId; // TIN#
    String mortgageCompanyOfficeId;
    String serviceBureauId; // TIN#
    String serviceBureauOfficeId;
    String workOrderDate;
    String filler;

    // ----------------------------------------------------------------
    // Accessors
    // ----------------------------------------------------------------
    public String getFieldServiceCompanyId() {
        return fieldServiceCompanyId;
    }

    public void setFieldServiceCompanyId(String fieldServiceCompanyId) {
        this.fieldServiceCompanyId = fieldServiceCompanyId;
    }

    public String getFieldServiceCompanyOfficeId() {
        return fieldServiceCompanyOfficeId;
    }

    public void setFieldServiceCompanyOfficeId(String fieldServiceCompanyOfficeId) {
        this.fieldServiceCompanyOfficeId = fieldServiceCompanyOfficeId;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

    public String getMortgageCompanyId() {
        return mortgageCompanyId;
    }

    public void setMortgageCompanyId(String mortgageCompanyId) {
        this.mortgageCompanyId = mortgageCompanyId;
    }

    public String getMortgageCompanyOfficeId() {
        return mortgageCompanyOfficeId;
    }

    public void setMortgageCompanyOfficeId(String mortgageCompanyOfficeId) {
        this.mortgageCompanyOfficeId = mortgageCompanyOfficeId;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getServiceBureauId() {
        return serviceBureauId;
    }

    public void setServiceBureauId(String serviceBureauId) {
        this.serviceBureauId = serviceBureauId;
    }

    public String getServiceBureauOfficeId() {
        return serviceBureauOfficeId;
    }

    public void setServiceBureauOfficeId(String serviceBureauOfficeId) {
        this.serviceBureauOfficeId = serviceBureauOfficeId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getWorkOrderDate() {
        return workOrderDate;
    }

    public void setWorkOrderDate(String workOrderDate) {
        this.workOrderDate = workOrderDate;
    }

//    @Override
//    public String toString() {
//        return "HeaderRecord{" +
//                "recordId='" + recordId + '\'' +
//                ", version='" + version + '\'' +
//                ", fieldServiceCompanyId='" + fieldServiceCompanyId + '\'' +
//                ", fieldServiceCompanyOfficeId='" + fieldServiceCompanyOfficeId + '\'' +
//                ", mortgageCompanyId='" + mortgageCompanyId + '\'' +
//                ", mortgageCompanyOfficeId='" + mortgageCompanyOfficeId + '\'' +
//                ", serviceBureauId='" + serviceBureauId + '\'' +
//                ", serviceBureauOfficeId='" + serviceBureauOfficeId + '\'' +
//                ", workOrderDate(DDMMYY)='" + workOrderDate + '\'' +
//                ", filler='" + filler + '\'' +
//                '}';
//    }
    @Override
    public String toString() {
        return "------------------ [Begin Header Record] -----------------------------" + DEFAULT_LINE_SEPARATOR +
                "recordId=[" + recordId + ']' + DEFAULT_LINE_SEPARATOR +
                "version=[" + version + ']' +  DEFAULT_LINE_SEPARATOR +
                "fieldServiceCompanyId=[" + fieldServiceCompanyId + ']' +  DEFAULT_LINE_SEPARATOR +
                "fieldServiceCompanyOfficeId=[" + fieldServiceCompanyOfficeId + ']' +  DEFAULT_LINE_SEPARATOR +
                "mortgageCompanyId=[" + mortgageCompanyId + ']' +  DEFAULT_LINE_SEPARATOR +
                "mortgageCompanyOfficeId=[" + mortgageCompanyOfficeId + ']' +  DEFAULT_LINE_SEPARATOR +
                "serviceBureauId=[" + serviceBureauId + ']' +  DEFAULT_LINE_SEPARATOR +
                "serviceBureauOfficeId=[" + serviceBureauOfficeId + ']' +  DEFAULT_LINE_SEPARATOR +
                "workOrderDate(DDMMYY)=[" + workOrderDate + ']' +  DEFAULT_LINE_SEPARATOR +
                "filler=[" + filler + ']' +  DEFAULT_LINE_SEPARATOR +
                "------------------ [End Header Record] -----------------------------" + DEFAULT_LINE_SEPARATOR;
    }
}
