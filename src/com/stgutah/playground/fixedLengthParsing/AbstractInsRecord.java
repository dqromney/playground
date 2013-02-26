package com.stgutah.playground.fixedLengthParsing;

/**
 * Abstract Inspection Request class.
 *
 * User: dqromney
 * Date: Dec 23, 2010
 * Time: 7:33:26 PM
 */
public abstract class AbstractInsRecord implements InsRecord {

    final String DEFAULT_LINE_SEPARATOR = System.getProperty("line.separator");
    // ----------------------------------------------------------------
    // Common Fields
    // ----------------------------------------------------------------
    String recordId = null;
    String recordCode = null;
    String loanNumber = null;

    // ----------------------------------------------------------------
    // Accessors
    // ----------------------------------------------------------------
    public String getLoanNumber() {
        return loanNumber;
    }

    public void setLoanNumber(String loanNumber) {
        this.loanNumber = loanNumber;
    }

    public String getRecordCode() {
        return recordCode;
    }

    public void setRecordCode(String recordCode) {
        this.recordCode = recordCode;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

//    @Override
//    public String toString() {
//        return "AbstractInsRecord{" +
//                "recordId='" + recordId + '\'' +
//                ", recordCode='" + recordCode + '\'' +
//                ", loanNumber='" + loanNumber + '\'' +
//                '}';
//    }
    @Override
    public String toString() {
        return "recordId=[" + recordId + ']' + DEFAULT_LINE_SEPARATOR +
                "recordCode=[" + recordCode + ']' + DEFAULT_LINE_SEPARATOR +
                "loanNumber=[" + loanNumber + ']' + DEFAULT_LINE_SEPARATOR;
    }
}
