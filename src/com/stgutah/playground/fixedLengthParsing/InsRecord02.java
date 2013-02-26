package com.stgutah.playground.fixedLengthParsing;

/**
 * Inspection Request Record 01.
 *
 * User: dqromney
 * Date: Dec 23, 2010
 * Time: 7:21:20 PM
 */
public class InsRecord02 extends AbstractInsRecord {
    // ----------------------------------------------------------------
    // Unique Fields
    // ----------------------------------------------------------------
    private String mailAddressLine1;
    private String mailAddressLine2;
    private String mailAddressLine3;
    private String mailAddressLine4;
    private String filler;

    // ----------------------------------------------------------------
    // Accessors
    // ----------------------------------------------------------------
    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

    public String getMailAddressLine1() {
        return mailAddressLine1;
    }

    public void setMailAddressLine1(String mailAddressLine1) {
        this.mailAddressLine1 = mailAddressLine1;
    }

    public String getMailAddressLine2() {
        return mailAddressLine2;
    }

    public void setMailAddressLine2(String mailAddressLine2) {
        this.mailAddressLine2 = mailAddressLine2;
    }

    public String getMailAddressLine3() {
        return mailAddressLine3;
    }

    public void setMailAddressLine3(String mailAddressLine3) {
        this.mailAddressLine3 = mailAddressLine3;
    }

    public String getMailAddressLine4() {
        return mailAddressLine4;
    }

    public void setMailAddressLine4(String mailAddressLine4) {
        this.mailAddressLine4 = mailAddressLine4;
    }

//    @Override
//    public String toString() {
//        return super.toString() + " InsRecord02{" +
//                "mailAddressLine1='" + mailAddressLine1 + '\'' +
//                ", mailAddressLine2='" + mailAddressLine2 + '\'' +
//                ", mailAddressLine3='" + mailAddressLine3 + '\'' +
//                ", mailAddressLine4='" + mailAddressLine4 + '\'' +
//                ", filler='" + filler + '\'' +
//                '}';
//    }
    @Override
    public String toString() {
        return super.toString() +
                "mailAddressLine1=[" + mailAddressLine1 + ']' + DEFAULT_LINE_SEPARATOR +
                "mailAddressLine2=[" + mailAddressLine2 + ']' + DEFAULT_LINE_SEPARATOR +
                "mailAddressLine3=[" + mailAddressLine3 + ']' + DEFAULT_LINE_SEPARATOR +
                "mailAddressLine4=[" + mailAddressLine4 + ']' + DEFAULT_LINE_SEPARATOR +
                "filler=[" + filler + ']' + DEFAULT_LINE_SEPARATOR;
    }
}
