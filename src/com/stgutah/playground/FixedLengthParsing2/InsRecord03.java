package com.stgutah.playground.FixedLengthParsing2;

/**
 * Inspection Request Record 03.
 *
 * User: dqromney
 * Date: Dec 23, 2010
 * Time: 7:21:20 PM
 */
public class InsRecord03 extends AbstractInsRecord {
    // ----------------------------------------------------------------
    // Unique Fields
    // ----------------------------------------------------------------
    private String mailCity;
    private String mailState;
    private String mailZip;
    private String phone1;
    private String phone1Location;
    private String phone2;
    private String phone2Location;
    private String phone3;
    private String phone3Location;
    private String mortgageDueDate;
    private String insurerCode;
    private String insurerCaseNumber;
    private String investorId; // TIN# i.e. Tax Identification number?
    private String investorLoanNumber;
    private String filler1;
    private String mortgagePhdOrderId;
    private String filler2;

    // ----------------------------------------------------------------
    // Accessors
    // ----------------------------------------------------------------
    public String getFiller1() {
        return filler1;
    }

    public void setFiller1(String filler1) {
        this.filler1 = filler1;
    }

    public String getFiller2() {
        return filler2;
    }

    public void setFiller2(String filler2) {
        this.filler2 = filler2;
    }

    public String getInsurerCaseNumber() {
        return insurerCaseNumber;
    }

    public void setInsurerCaseNumber(String insurerCaseNumber) {
        this.insurerCaseNumber = insurerCaseNumber;
    }

    public String getInsurerCode() {
        return insurerCode;
    }

    public void setInsurerCode(String insurerCode) {
        this.insurerCode = insurerCode;
    }

    public String getInvestorId() {
        return investorId;
    }

    public void setInvestorId(String investorId) {
        this.investorId = investorId;
    }

    public String getInvestorLoanNumber() {
        return investorLoanNumber;
    }

    public void setInvestorLoanNumber(String investorLoanNumber) {
        this.investorLoanNumber = investorLoanNumber;
    }

    public String getMailCity() {
        return mailCity;
    }

    public void setMailCity(String mailCity) {
        this.mailCity = mailCity;
    }

    public String getMailState() {
        return mailState;
    }

    public void setMailState(String mailState) {
        this.mailState = mailState;
    }

    public String getMailZip() {
        return mailZip;
    }

    public void setMailZip(String mailZip) {
        this.mailZip = mailZip;
    }

    public String getMortgageDueDate() {
        return mortgageDueDate;
    }

    public void setMortgageDueDate(String mortgageDueDate) {
        this.mortgageDueDate = mortgageDueDate;
    }

    public String getMortgagePhdOrderId() {
        return mortgagePhdOrderId;
    }

    public void setMortgagePhdOrderId(String mortgagePhdOrderId) {
        this.mortgagePhdOrderId = mortgagePhdOrderId;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone1Location() {
        return phone1Location;
    }

    public void setPhone1Location(String phone1Location) {
        this.phone1Location = phone1Location;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone2Location() {
        return phone2Location;
    }

    public void setPhone2Location(String phone2Location) {
        this.phone2Location = phone2Location;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public String getPhone3Location() {
        return phone3Location;
    }

    public void setPhone3Location(String phone3Location) {
        this.phone3Location = phone3Location;
    }

//    @Override
//    public String toString() {
//        return super.toString() + " InsRecord03{" +
//                "mailCity='" + mailCity + '\'' +
//                ", mailState='" + mailState + '\'' +
//                ", mailZip='" + mailZip + '\'' +
//                ", phone1='" + phone1 + '\'' +
//                ", phone1Location='" + phone1Location + '\'' +
//                ", phone2='" + phone2 + '\'' +
//                ", phone2Location='" + phone2Location + '\'' +
//                ", phone3='" + phone3 + '\'' +
//                ", phone3Location='" + phone3Location + '\'' +
//                ", mortgageDueDate='" + mortgageDueDate + '\'' +
//                ", insurerCode='" + insurerCode + '\'' +
//                ", insurerCaseNumber='" + insurerCaseNumber + '\'' +
//                ", investorId='" + investorId + '\'' +
//                ", investorLoanNumber='" + investorLoanNumber + '\'' +
//                ", filler1='" + filler1 + '\'' +
//                ", mortgagePhdOrderId='" + mortgagePhdOrderId + '\'' +
//                ", filler2='" + filler2 + '\'' +
//                '}';
//    }

    @Override
    public String toString() {
        return super.toString() +
                "mailCity=[" + mailCity + ']' + DEFAULT_LINE_SEPARATOR +
                "mailState=[" + mailState + ']' + DEFAULT_LINE_SEPARATOR +
                "mailZip=[" + mailZip + ']' + DEFAULT_LINE_SEPARATOR +
                "phone1=[" + phone1 + ']' + DEFAULT_LINE_SEPARATOR +
                "phone1Location=[" + phone1Location + ']' + DEFAULT_LINE_SEPARATOR +
                "phone2=[" + phone2 + ']' + DEFAULT_LINE_SEPARATOR +
                "phone2Location=[" + phone2Location + ']' + DEFAULT_LINE_SEPARATOR +
                "phone3=[" + phone3 + ']' + DEFAULT_LINE_SEPARATOR +
                "phone3Location=[" + phone3Location + ']' + DEFAULT_LINE_SEPARATOR +
                "mortgageDueDate=[" + mortgageDueDate + ']' + DEFAULT_LINE_SEPARATOR +
                "insurerCode=[" + insurerCode + ']' + DEFAULT_LINE_SEPARATOR +
                "insurerCaseNumber=[" + insurerCaseNumber + ']' + DEFAULT_LINE_SEPARATOR +
                "investorId=[" + investorId + ']' + DEFAULT_LINE_SEPARATOR +
                "investorLoanNumber=[" + investorLoanNumber + ']' + DEFAULT_LINE_SEPARATOR +
                "filler1=[" + filler1 + ']' + DEFAULT_LINE_SEPARATOR +
                "mortgagePhdOrderId=[" + mortgagePhdOrderId + ']' +  DEFAULT_LINE_SEPARATOR +
                "filler2=[" + filler2 + ']' +  DEFAULT_LINE_SEPARATOR;
    }
}
