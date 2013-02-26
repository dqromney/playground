package com.stgutah.playground.fixedLengthParsing;

/**
 * Inspection Request Record 01.
 *
 * User: dqromney
 * Date: Dec 23, 2010
 * Time: 7:21:20 PM
 */
public class InsRecord04 extends AbstractInsRecord {
    // ----------------------------------------------------------------
    // Unique Fields
    // ----------------------------------------------------------------
    private String principleBalance;
    private String lateChargeBalance;
    private String mortgagePayment;
    // 1 = Annual 2 = Semi-annual 4 = Quarterly 12 = Monthly 24 = Semi-monthly 26 = Bi-weekly
    private String paymentFrequency;
    private String totalAmountDue;
    // Alphanumeric data entered by the user in the LOAN PROCESSOR field on the PIRM screen and PIR1/REQ window in
    // the Property Inspection Facility.
    private String processorCode;
    // A = Bulk tape; mass, B = Bulk tape; individual, C = Bulk tape; auto-generated, D = Telephone E = Fax
    // F = Written request, G = Recurring
    private String UNDEFINED_DATA;
    private String inspectionSourceRequest;
    private String requestorCode;
    // A = Property condition, no contract, B = Property condition, no contract, C = FNMA Form 30, D = Delinquent interview,
    // E = Bankruptcy, F = Foreclosure, G = Sale Date, H = Loss Draft, I = Commercial, J = Annual, K = Other, L = None
    private String inspectionTypeForPropertyAddress;
    // A = Property condition, no contract, B = Property condition, no contract, C = FNMA Form 30, D = Delinquent interview,
    // E = Bankruptcy, F = Foreclosure, G = Sale Date, H = Loss Draft, I = Commercial, J = Annual, K = Other, L = None
    private String inspectionTypeForMailingAddress;
    // 0 = No photo, 1 = Photo, 2 = Photo, only if bad condition, 3 = Photo, only if vacant,
    // 4 = Photo, only if first time vacant, 5 = Photo, property, and neighborhood, 6-9 = User-defined
    private String photoRequested;
    // format MMDDYY
    private String foreclosureSaleDate;
    // format MMDDYY
    private String redemptionExpireDate;
    // A = Servicer, B = Inspection company, C = Other
    private String mortgageBilledBy;

    // Starts departure from first InsRecord04 now with additional details.
    // 1 = First Mortgage, 2  = Second Mortgage
    private String mortgageType;
    // The investor code the servicer assigns to the owner of the loan. This field can be three alphanumeric
    // characters, with the exception of I, O, and ALL.
    private String mortgageBankCode;
    // A code indicating the government-sponsored enterprise (GSE) that owns the loan.
    // F = Fannie Mae, G = Ginnie Mae, H = Freddie Mac, O = Other
    private String gseCode;
    // Indicates whether the loan is an asset loan. This field is populated only when the GSE-CODE field is O (other).
    // This comes from the ASSET LN CD field on the Investor Header Setup/Maintenance screen (IN01) in the Investor
    // Setup Workstation.
    // 0 = Non-asset, 1 = Asset
    private String assetLoanCode;
    // Free-form investor name from line 1 of the INVESTOR NAME AND ADDRESS field on the IN01 screen. This field is
    // populated only when the GSE-CODE field is O (other).
    private String investorName;

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

    public String getForeclosureSaleDate() {
        return foreclosureSaleDate;
    }

    public void setForeclosureSaleDate(String foreclosureSaleDate) {
        this.foreclosureSaleDate = foreclosureSaleDate;
    }

    public String getInspectionSourceRequest() {
        return inspectionSourceRequest;
    }

    public void setInspectionSourceRequest(String inspectionSourceRequest) {
        this.inspectionSourceRequest = inspectionSourceRequest;
    }

    public String getInspectionTypeForMailingAddress() {
        return inspectionTypeForMailingAddress;
    }

    public void setInspectionTypeForMailingAddress(String inspectionTypeForMailingAddress) {
        this.inspectionTypeForMailingAddress = inspectionTypeForMailingAddress;
    }

    public String getInspectionTypeForPropertyAddress() {
        return inspectionTypeForPropertyAddress;
    }

    public void setInspectionTypeForPropertyAddress(String inspectionTypeForPropertyAddress) {
        this.inspectionTypeForPropertyAddress = inspectionTypeForPropertyAddress;
    }

    public String getLateChargeBalance() {
        return lateChargeBalance;
    }

    public void setLateChargeBalance(String lateChargeBalance) {
        this.lateChargeBalance = lateChargeBalance;
    }

    public String getMortgageBilledBy() {
        return mortgageBilledBy;
    }

    public void setMortgageBilledBy(String mortgageBilledBy) {
        this.mortgageBilledBy = mortgageBilledBy;
    }

    public String getMortgagePayment() {
        return mortgagePayment;
    }

    public void setMortgagePayment(String mortgagePayment) {
        this.mortgagePayment = mortgagePayment;
    }

    public String getPaymentFrequency() {
        return paymentFrequency;
    }

    public void setPaymentFrequency(String paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    public String getPhotoRequested() {
        return photoRequested;
    }

    public void setPhotoRequested(String photoRequested) {
        this.photoRequested = photoRequested;
    }

    public String getPrincipleBalance() {
        return principleBalance;
    }

    public void setPrincipleBalance(String principleBalance) {
        this.principleBalance = principleBalance;
    }

    public String getProcessorCode() {
        return processorCode;
    }

    public void setProcessorCode(String processorCode) {
        this.processorCode = processorCode;
    }

    public String getRedemptionExpireDate() {
        return redemptionExpireDate;
    }

    public void setRedemptionExpireDate(String redemptionExpireDate) {
        this.redemptionExpireDate = redemptionExpireDate;
    }

    public String getRequestorCode() {
        return requestorCode;
    }

    public void setRequestorCode(String requestorCode) {
        this.requestorCode = requestorCode;
    }

    public String getTotalAmountDue() {
        return totalAmountDue;
    }

    public void setTotalAmountDue(String totalAmountDue) {
        this.totalAmountDue = totalAmountDue;
    }

    public String getAssetLoanCode() {
        return assetLoanCode;
    }

    public void setAssetLoanCode(String assetLoanCode) {
        this.assetLoanCode = assetLoanCode;
    }

    public String getGseCode() {
        return gseCode;
    }

    public void setGseCode(String gseCode) {
        this.gseCode = gseCode;
    }

    public String getInvestorName() {
        return investorName;
    }

    public void setInvestorName(String investorName) {
        this.investorName = investorName;
    }

    public String getMortgageBankCode() {
        return mortgageBankCode;
    }

    public void setMortgageBankCode(String mortgageBankCode) {
        this.mortgageBankCode = mortgageBankCode;
    }

    public String getMortgageType() {
        return mortgageType;
    }

    public void setMortgageType(String mortgageType) {
        this.mortgageType = mortgageType;
    }

    public String getUNDEFINED_DATA() {
        return UNDEFINED_DATA;
    }

    public void setUNDEFINED_DATA(String UNDEFINED_DATA) {
        this.UNDEFINED_DATA = UNDEFINED_DATA;
    }

    //    @Override
//    public String toString() {
//        return super.toString() + " InsRecord04{" +
//                "principleBalance='" + principleBalance + '\'' +
//                ", lateChargeBalance='" + lateChargeBalance + '\'' +
//                ", mortgagePayment='" + mortgagePayment + '\'' +
//                ", paymentFrequency='" + paymentFrequency + '\'' +
//                ", totalAmountDue='" + totalAmountDue + '\'' +
//                ", processorCode='" + processorCode + '\'' +
//                ", inspectionSourceRequest='" + inspectionSourceRequest + '\'' +
//                ", requestorCode='" + requestorCode + '\'' +
//                ", inspectionTypeForPropertyAddress='" + inspectionTypeForPropertyAddress + '\'' +
//                ", inspectionTypeForMailingAddress='" + inspectionTypeForMailingAddress + '\'' +
//                ", photoRequested='" + photoRequested + '\'' +
//                ", foreclosureSaleDate='" + foreclosureSaleDate + '\'' +
//                ", redemptionExpireDate='" + redemptionExpireDate + '\'' +
//                ", mortgageBilledBy='" + mortgageBilledBy + '\'' +
//                ", mortgageType='" + mortgageType + '\'' +
//                ", mortgageBankCode='" + mortgageBankCode + '\'' +
//                ", gseCode='" + gseCode + '\'' +
//                ", assetLoanCode='" + assetLoanCode + '\'' +
//                ", investorName='" + investorName + '\'' +
//                ", filler='" + filler + '\'' +
//                '}';
//    }
    @Override
    public String toString() {
        return super.toString() +
                "principleBalance=[" + principleBalance + ']' +  DEFAULT_LINE_SEPARATOR +
                "lateChargeBalance=[" + lateChargeBalance + ']' +  DEFAULT_LINE_SEPARATOR +
                "mortgagePayment=[" + mortgagePayment + ']' +  DEFAULT_LINE_SEPARATOR +
                "paymentFrequency=[" + paymentFrequency + ']' +  DEFAULT_LINE_SEPARATOR +
                "totalAmountDue=[" + totalAmountDue + ']' +  DEFAULT_LINE_SEPARATOR +
                "processorCode=[" + processorCode + ']' +  DEFAULT_LINE_SEPARATOR +
                "UNDEFINED_DATA=[" + UNDEFINED_DATA + ']' + DEFAULT_LINE_SEPARATOR +
                "inspectionSourceRequest=[" + inspectionSourceRequest + ']' +  DEFAULT_LINE_SEPARATOR +
                "requestorCode=[" + requestorCode + ']' +  DEFAULT_LINE_SEPARATOR +
                "inspectionTypeForPropertyAddress=[" + inspectionTypeForPropertyAddress + ']' +  DEFAULT_LINE_SEPARATOR +
                "inspectionTypeForMailingAddress=[" + inspectionTypeForMailingAddress + ']' +  DEFAULT_LINE_SEPARATOR +
                "photoRequested=[" + photoRequested + ']' +  DEFAULT_LINE_SEPARATOR +
                "foreclosureSaleDate=[" + foreclosureSaleDate + ']' +  DEFAULT_LINE_SEPARATOR +
                "redemptionExpireDate=[" + redemptionExpireDate + ']' +  DEFAULT_LINE_SEPARATOR +
                "mortgageBilledBy=[" + mortgageBilledBy + ']' +  DEFAULT_LINE_SEPARATOR +
                "mortgageType=[" + mortgageType + ']' +  DEFAULT_LINE_SEPARATOR +
                "mortgageBankCode=[" + mortgageBankCode + ']' +  DEFAULT_LINE_SEPARATOR +
                "gseCode=[" + gseCode + ']' +  DEFAULT_LINE_SEPARATOR +
                "assetLoanCode=[" + assetLoanCode + ']' +  DEFAULT_LINE_SEPARATOR +
                "investorName=[" + investorName + ']' +  DEFAULT_LINE_SEPARATOR +
                "filler=[" + filler + ']' +  DEFAULT_LINE_SEPARATOR;
    }
}
