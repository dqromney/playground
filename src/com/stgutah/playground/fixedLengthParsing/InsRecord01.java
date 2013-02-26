package com.stgutah.playground.fixedLengthParsing;

/**
 * Inspection Request Record 01.
 *
 * User: dqromney
 * Date: Dec 23, 2010
 * Time: 7:21:20 PM
 */
public class InsRecord01 extends AbstractInsRecord {

    // ----------------------------------------------------------------
    // Unique Fields
    // ----------------------------------------------------------------
    private String version;
    private String mortgageName;
    private String propertyAddressLine1;
    private String propertyAddressLine2;
    private String propertyCity;
    private String propertyState;
    private String propertyZip;
    private String badPropertyAddressFlag; // Valid values 'N' = No, 'Y' = Yes
    private String filler;

    // ----------------------------------------------------------------
    // Accessors
    // ----------------------------------------------------------------
    public String getBadPropertyAddressFlag() {
        return badPropertyAddressFlag;
    }

    public void setBadPropertyAddressFlag(String badPropertyAddressFlag) {
        this.badPropertyAddressFlag = badPropertyAddressFlag;
    }

    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

    public String getMortgageName() {
        return mortgageName;
    }

    public void setMortgageName(String mortgageName) {
        this.mortgageName = mortgageName;
    }

    public String getPropertyAddressLine1() {
        return propertyAddressLine1;
    }

    public void setPropertyAddressLine1(String propertyAddressLine1) {
        this.propertyAddressLine1 = propertyAddressLine1;
    }

    public String getPropertyAddressLine2() {
        return propertyAddressLine2;
    }

    public void setPropertyAddressLine2(String propertyAddressLine2) {
        this.propertyAddressLine2 = propertyAddressLine2;
    }

    public String getPropertyCity() {
        return propertyCity;
    }

    public void setPropertyCity(String propertyCity) {
        this.propertyCity = propertyCity;
    }

    public String getPropertyState() {
        return propertyState;
    }

    public void setPropertyState(String propertyState) {
        this.propertyState = propertyState;
    }

    public String getPropertyZip() {
        return propertyZip;
    }

    public void setPropertyZip(String propertyZip) {
        this.propertyZip = propertyZip;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

//    @Override
//    public String toString() {
//        return super.toString() + " InsRecord01{" +
//                "version='" + version + '\'' +
//                ", mortgageName='" + mortgageName + '\'' +
//                ", propertyAddressLine1='" + propertyAddressLine1 + '\'' +
//                ", propertyAddressLine2='" + propertyAddressLine2 + '\'' +
//                ", propertyCity='" + propertyCity + '\'' +
//                ", propertyState='" + propertyState + '\'' +
//                ", propertyZip='" + propertyZip + '\'' +
//                ", badPropertyAddressFlag='" + badPropertyAddressFlag + '\'' +
//                ", filler='" + filler + '\'' +
//                '}';
//    }

    @Override
    public String toString() {
        return "------------------ [New Loan Number] -----------------------------" + DEFAULT_LINE_SEPARATOR +
                super.toString() +
                "version=[" + version + ']' + DEFAULT_LINE_SEPARATOR +
                "mortgageName=[" + mortgageName + ']' + DEFAULT_LINE_SEPARATOR +
                "propertyAddressLine1=[" + propertyAddressLine1 + ']' + DEFAULT_LINE_SEPARATOR +
                "propertyAddressLine2=[" + propertyAddressLine2 + ']' + DEFAULT_LINE_SEPARATOR +
                "propertyCity=[" + propertyCity + ']' + DEFAULT_LINE_SEPARATOR +
                "propertyState=[" + propertyState + ']' + DEFAULT_LINE_SEPARATOR +
                "propertyZip=[" + propertyZip + ']' + DEFAULT_LINE_SEPARATOR +
                "badPropertyAddressFlag=[" + badPropertyAddressFlag + ']' + DEFAULT_LINE_SEPARATOR +
                "filler=[" + filler + ']' + DEFAULT_LINE_SEPARATOR;
    }
}
