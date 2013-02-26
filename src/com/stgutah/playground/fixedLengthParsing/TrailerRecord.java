package com.stgutah.playground.fixedLengthParsing;

/**
 * Trailer Record value class.
 *
 * User: dqromney
 * Date: Dec 23, 2010
 * Time: 6:16:26 PM
 */
public class TrailerRecord {

    final String DEFAULT_LINE_SEPARATOR = System.getProperty("line.separator");

    // ----------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------
    String recordId;
    String version;
    String mortgageCompanyId;
    String mortgageCompanyOfficeId;
    String typeAInspectionCount;
    String typeBInspectionCount;
    String typeCInspectionCount;
    String typeDInspectionCount;
    String typeEInspectionCount;
    String typeFInspectionCount;
    String typeGInspectionCount;
    String typeHInspectionCount;
    String typeIInspectionCount;
    String typeJInspectionCount;
    String typeKInspectionCount;
    String totalInspectionCount;
    String totalLoanCount;
    String filler;

    // ----------------------------------------------------------------
    // Accessors
    // ----------------------------------------------------------------
    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
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

    public String getTotalInspectionCount() {
        return totalInspectionCount;
    }

    public void setTotalInspectionCount(String totalInspectionCount) {
        this.totalInspectionCount = totalInspectionCount;
    }

    public String getTotalLoanCount() {
        return totalLoanCount;
    }

    public void setTotalLoanCount(String totalLoanCount) {
        this.totalLoanCount = totalLoanCount;
    }

    public String getTypeAInspectionCount() {
        return typeAInspectionCount;
    }

    public void setTypeAInspectionCount(String typeAInspectionCount) {
        this.typeAInspectionCount = typeAInspectionCount;
    }

    public String getTypeBInspectionCount() {
        return typeBInspectionCount;
    }

    public void setTypeBInspectionCount(String typeBInspectionCount) {
        this.typeBInspectionCount = typeBInspectionCount;
    }

    public String getTypeCInspectionCount() {
        return typeCInspectionCount;
    }

    public void setTypeCInspectionCount(String typeCInspectionCount) {
        this.typeCInspectionCount = typeCInspectionCount;
    }

    public String getTypeDInspectionCount() {
        return typeDInspectionCount;
    }

    public void setTypeDInspectionCount(String typeDInspectionCount) {
        this.typeDInspectionCount = typeDInspectionCount;
    }

    public String getTypeEInspectionCount() {
        return typeEInspectionCount;
    }

    public void setTypeEInspectionCount(String typeEInspectionCount) {
        this.typeEInspectionCount = typeEInspectionCount;
    }

    public String getTypeFInspectionCount() {
        return typeFInspectionCount;
    }

    public void setTypeFInspectionCount(String typeFInspectionCount) {
        this.typeFInspectionCount = typeFInspectionCount;
    }

    public String getTypeGInspectionCount() {
        return typeGInspectionCount;
    }

    public void setTypeGInspectionCount(String typeGInspectionCount) {
        this.typeGInspectionCount = typeGInspectionCount;
    }

    public String getTypeHInspectionCount() {
        return typeHInspectionCount;
    }

    public void setTypeHInspectionCount(String typeHInspectionCount) {
        this.typeHInspectionCount = typeHInspectionCount;
    }

    public String getTypeIInspectionCount() {
        return typeIInspectionCount;
    }

    public void setTypeIInspectionCount(String typeIInspectionCount) {
        this.typeIInspectionCount = typeIInspectionCount;
    }

    public String getTypeJInspectionCount() {
        return typeJInspectionCount;
    }

    public void setTypeJInspectionCount(String typeJInspectionCount) {
        this.typeJInspectionCount = typeJInspectionCount;
    }

    public String getTypeKInspectionCount() {
        return typeKInspectionCount;
    }

    public void setTypeKInspectionCount(String typeKInspectionCount) {
        this.typeKInspectionCount = typeKInspectionCount;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

//    @Override
//    public String toString() {
//        return "TrailerRecord{" +
//                "recordId='" + recordId + '\'' +
//                ", version='" + version + '\'' +
//                ", mortgageCompanyId='" + mortgageCompanyId + '\'' +
//                ", mortgageCompanyOfficeId='" + mortgageCompanyOfficeId + '\'' +
//                ", typeAInspectionCount='" + typeAInspectionCount + '\'' +
//                ", typeBInspectionCount='" + typeBInspectionCount + '\'' +
//                ", typeCInspectionCount='" + typeCInspectionCount + '\'' +
//                ", typeDInspectionCount='" + typeDInspectionCount + '\'' +
//                ", typeEInspectionCount='" + typeEInspectionCount + '\'' +
//                ", typeFInspectionCount='" + typeFInspectionCount + '\'' +
//                ", typeGInspectionCount='" + typeGInspectionCount + '\'' +
//                ", typeHInspectionCount='" + typeHInspectionCount + '\'' +
//                ", typeIInspectionCount='" + typeIInspectionCount + '\'' +
//                ", typeJInspectionCount='" + typeJInspectionCount + '\'' +
//                ", typeKInspectionCount='" + typeKInspectionCount + '\'' +
//                ", totalInspectionCount='" + totalInspectionCount + '\'' +
//                ", totalLoanCount='" + totalLoanCount + '\'' +
//                ", filler='" + filler + '\'' +
//                '}';
//    }

    @Override
    public String toString() {
        return "------------------ [Begin Trailer Record] -----------------------------" + DEFAULT_LINE_SEPARATOR +
                "recordId=[" + recordId + ']' +  DEFAULT_LINE_SEPARATOR +
                "version=[" + version + ']' +  DEFAULT_LINE_SEPARATOR +
                "mortgageCompanyId=[" + mortgageCompanyId + ']' +  DEFAULT_LINE_SEPARATOR +
                "mortgageCompanyOfficeId=[" + mortgageCompanyOfficeId + ']' +  DEFAULT_LINE_SEPARATOR +
                "totalInspectionCount=[" + totalInspectionCount + ']' +  DEFAULT_LINE_SEPARATOR +
                "totalLoanCount=[" + totalLoanCount + ']' +  DEFAULT_LINE_SEPARATOR +
                "typeAInspectionCount=[" + typeAInspectionCount + ']' +  DEFAULT_LINE_SEPARATOR +
                "typeBInspectionCount=[" + typeBInspectionCount + ']' +  DEFAULT_LINE_SEPARATOR +
                "typeCInspectionCount=[" + typeCInspectionCount + ']' +  DEFAULT_LINE_SEPARATOR +
                "typeDInspectionCount=[" + typeDInspectionCount + ']' +  DEFAULT_LINE_SEPARATOR +
                "typeEInspectionCount=[" + typeEInspectionCount + ']' +  DEFAULT_LINE_SEPARATOR +
                "typeFInspectionCount=[" + typeFInspectionCount + ']' +  DEFAULT_LINE_SEPARATOR +
                "typeGInspectionCount=[" + typeGInspectionCount + ']' +  DEFAULT_LINE_SEPARATOR +
                "typeHInspectionCount=[" + typeHInspectionCount + ']' +  DEFAULT_LINE_SEPARATOR +
                "typeIInspectionCount=[" + typeIInspectionCount + ']' +  DEFAULT_LINE_SEPARATOR +
                "typeJInspectionCount=[" + typeJInspectionCount + ']' +  DEFAULT_LINE_SEPARATOR +
                "typeKInspectionCount=[" + typeKInspectionCount + ']' +  DEFAULT_LINE_SEPARATOR +
                "filler=[" + filler + ']' +  DEFAULT_LINE_SEPARATOR +
                "------------------ [End Trailer Record] -----------------------------" + DEFAULT_LINE_SEPARATOR;
    }
}

