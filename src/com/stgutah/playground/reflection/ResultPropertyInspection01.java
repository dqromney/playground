package com.stgutah.playground.reflection;

import com.stgutah.playground.reflection.types.ConstructionType;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * Property Inspection Results 01 Record value class.
 *
 * User: dqromney
 * Date: Dec 23, 2010
 * Time: 6:15:50 PM
 */
public class ResultPropertyInspection01 implements ResultRecord {

    final Integer FILLER_SIZE = 2;

    // ----------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------
    private String recordId = PROPERTY_INSPECTION_RESULTS_RECORD_ID;
    private String recordCode = RECORD_CODE_01;
    private String loanNumber;
    private String propertyMapReference;
    private String mailingMapReference;
    private ConstructionType constructionType;
    private String constructionOtherType;
//    private PropertyType propertyType;
//    private YesNoUnknownType poolOnSite = YesNoUnknownType.UNKNOWN;
//    private YesNoUnknownType poolSecure = YesNoUnknownType.UNKNOWN;
//    private Date inspectionRequestDate;
//    private InspectionRequestSource inspectionRequestSource;
//    private String requestorCode;
//    private Date inspectionCompleteDate;
//    private YesNoUnknownType cardLeftAtProperty = YesNoUnknownType.UNKNOWN;
//    private Date firstKnownVacancyDate;
//    private OccupancyCode occupancyCode;
//    private YesNoUnknownType firstTimeVacant = YesNoUnknownType.UNKNOWN;
//    private OccupancyVerified occupancyVerified;
//    private String occupancyVerifiedOther;
//    private String inspectorNameCode;
//    private ExternalPropertyCondition externalPropertyCondition;
//    private PropertyConditionProblemCode propertyConditionProblemCode;
//    private String otherPropertyConditionProblem;
//    private NeighborhoodConditionCode neighborhoodConditionCode;
//    private YesNoUnknownType personalPropertyOnSite = YesNoUnknownType.UNKNOWN;
//    private PropertyForSale propertyForSale;
//    private String brokerName;
//    private String brokerTelephone;
////    private InspectionAddress inspectionAddress;
//    private BadMailingAddressCause badMailingAddressCause;
//    private BadPropertyAddressCause badPropertyAddressCause;
//    private InspectionServiceCode inspectionServiceCode;
    private String filler;

    // ----------------------------------------------------------------
    // Accessors
    // ----------------------------------------------------------------
    public String getRecordId() {
        return recordId;
    }

    public String getRecordCode() {
        return recordCode;
    }

//    public String getBrokerName() {
//        return StringUtils.rightPad(brokerName == null ? "" : brokerName, 30, SPACE_CHAR).substring(0, 30);
//    }
//
//    public void setBrokerName(String brokerName) {
//        this.brokerName = brokerName;
//    }
//
//    public String getBrokerTelephone() {
//        return NumberFormatUtils.formatCobol9_10PhoneNumber(brokerTelephone);
//    }
//
//    public void setBrokerTelephone(String brokerTelephone) {
//        this.brokerTelephone = brokerTelephone;
//    }
//
//    public Character getCardLeftAtProperty() {
//        return cardLeftAtProperty.getType();
//    }
//
//    public void setCardLeftAtProperty(YesNoUnknownType cardLeftAtProperty) {
//        this.cardLeftAtProperty = cardLeftAtProperty;
//    }

    public String getConstructionOtherType() {
        // CAP-1505 Mapping issues to MSP; Truncate it if longer than eight characters
        return StringUtils.rightPad(constructionOtherType == null ? "" : constructionOtherType, 8, SPACE_CHAR).substring(0, 8);
    }

    public void setConstructionOtherType(String constructionOtherType) {
        this.constructionOtherType = constructionOtherType;
    }

    public Character getConstructionType() {
        return constructionType.getType();
    }

    public void setConstructionType(ConstructionType constructionType) {
        this.constructionType = constructionType;
    }

//    public Character getExternalPropertyCondition() {
//        return externalPropertyCondition.getType();
//    }
//
//    public void setExternalPropertyCondition(ExternalPropertyCondition externalPropertyCondition) {
//        this.externalPropertyCondition = externalPropertyCondition;
//    }

    public String getFiller() {
        return StringUtils.rightPad(filler == null ? "" : filler, FILLER_SIZE, SPACE_CHAR);
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

//    public String getFirstKnownVacancyDate() {
//        String dateDDMMYY = StringUtils.rightPad("", 6, '0');
//        if(firstKnownVacancyDate != null) {
//            dateDDMMYY = String.format("%1$tm%1$td%1$ty", firstKnownVacancyDate); // MMDDYY
//        }
//        return dateDDMMYY;
//    }
//
//    public void setFirstKnownVacancyDate(Date firstKnownVacancyDate) {
//        this.firstKnownVacancyDate = firstKnownVacancyDate;
//    }
//
//    public Character getFirstTimeVacant() {
//        return firstTimeVacant.getType();
//    }
//
//    public void setFirstTimeVacant(YesNoUnknownType firstTimeVacant) {
//        this.firstTimeVacant = firstTimeVacant;
//    }

//    // Returns Bad Address code based upon with it is for MAILING or PROPERTY: BadPropertyAddressCause, and BadMailingAddressCause
//    public Character getInspectionAddress() {
//        Character inspectionCode = BadMailingAddressCause.UNDEFINED.getType();
//        if(getBadMailingAddress() != null) {
//            inspectionCode = getBadMailingAddress().getType();
//        } else if(getBadPropertyAddress() != null) {
//            inspectionCode = getBadPropertyAddress().getType();
//        }
//        return inspectionCode;
//    }
//
//    public BadMailingAddressCause getBadMailingAddress() {
//        return badMailingAddressCause;
//    }
//
//    public void setBadMailingAddress(BadMailingAddressCause badMailingAddressCause) {
//        this.badMailingAddressCause = badMailingAddressCause;
//    }
//
//    public BadPropertyAddressCause getBadPropertyAddress() {
//        return badPropertyAddressCause;
//    }
//
//    public void setBadPropertyAddress(BadPropertyAddressCause badPropertyAddressCause) {
//        this.badPropertyAddressCause = badPropertyAddressCause;
//    }
//
//    public String getInspectionCompleteDate() {
//        String dateDDMMYY = StringUtils.rightPad("", 6, '0');
//        if(inspectionCompleteDate != null) {
//            dateDDMMYY = String.format("%1$tm%1$td%1$ty", inspectionCompleteDate); // MMDDYY
//        }
//        return dateDDMMYY;
//    }
//
//    public void setInspectionCompleteDate(Date inspectionCompleteDate) {
//        this.inspectionCompleteDate = inspectionCompleteDate;
//    }
//
//    public String getInspectionRequestDate() {
//        String dateDDMMYY = StringUtils.rightPad("", 6, '0');
//        if(inspectionRequestDate != null) {
//            dateDDMMYY = String.format("%1$tm%1$td%1$ty", inspectionRequestDate); // MMDDYY
//        }
//        return dateDDMMYY;
//    }
//
//    public void setInspectionRequestDate(Date inspectionRequestDate) {
//        this.inspectionRequestDate = inspectionRequestDate;
//    }
//
//    public Character getInspectionRequestSource() {
//        return inspectionRequestSource.getType();
//    }
//
//    public void setInspectionRequestSource(InspectionRequestSource inspectionRequestSource) {
//        this.inspectionRequestSource = inspectionRequestSource;
//    }
//
//    public Character getInspectionServiceCode() {
//        return inspectionServiceCode.getCode().charAt(0);
//    }
//
//    public void setInspectionServiceCode(InspectionServiceCode inspectionServiceCode) {
//        this.inspectionServiceCode = inspectionServiceCode;
//    }
//
//    public String getInspectorNameCode() {
//        return StringUtils.rightPad(inspectorNameCode == null ? "" : inspectorNameCode, 9, SPACE_CHAR).substring(0, 9);
//    }
//
//    public void setInspectorNameCode(String inspectorNameCode) {
//        this.inspectorNameCode = inspectorNameCode;
//    }

    public String getLoanNumber() {
        return StringUtils.rightPad(loanNumber == null ? "" : loanNumber.trim(), 15, SPACE_CHAR).substring(0, 15);
    }

    public void setLoanNumber(String loanNumber) {
        this.loanNumber = loanNumber;
    }

    public String getMailingMapReference() {
        return StringUtils.rightPad(mailingMapReference == null ? "" : mailingMapReference, 6, SPACE_CHAR).substring(0, 6);
    }

    public void setMailingMapReference(String mailingMapReference) {
        this.mailingMapReference = mailingMapReference;
    }

//    public Character getNeighborhoodConditionCode() {
//        return neighborhoodConditionCode.getType();
//    }
//
//    public void setNeighborhoodConditionCode(NeighborhoodConditionCode neighborhoodConditionCode) {
//        this.neighborhoodConditionCode = neighborhoodConditionCode;
//    }
//
//    public OccupancyCode getOccupancyCode() {
//    	if(occupancyCode != null){
//    		return occupancyCode;
//    	}else{
//    		return OccupancyCode.UNDEFINED;
//    	}
//    }
//
//    public void setOccupancyCode(OccupancyCode occupancyCode) {
//        this.occupancyCode = occupancyCode;
//    }
//
//    public Character getOccupancyVerified() {
//        return occupancyVerified.getType();
//    }
//
//    public void setOccupancyVerified(OccupancyVerified occupancyVerified) {
//        this.occupancyVerified = occupancyVerified;
//    }
//
//    public String getOccupancyVerifiedOther() {
//        return StringUtils.rightPad(occupancyVerifiedOther == null ? "" : occupancyVerifiedOther, 8, SPACE_CHAR).substring(0, 8);
//    }
//
//    public void setOccupancyVerifiedOther(String occupancyVerifiedOther) {
//        this.occupancyVerifiedOther = occupancyVerifiedOther;
//    }
//
//    public String getOtherPropertyConditionProblem() {
//        String item = StringUtils.rightPad(otherPropertyConditionProblem == null ? "" :
//                otherPropertyConditionProblem, 8, SPACE_CHAR).substring(0, 8);
//        return item;
//    }

//    public void setOtherPropertyConditionProblem(String otherPropertyConditionProblem) {
//        this.otherPropertyConditionProblem = otherPropertyConditionProblem;
//    }
//
//    public Character getPersonalPropertyOnSite() {
//        return personalPropertyOnSite.getType();
//    }
//
//    public void setPersonalPropertyOnSite(YesNoUnknownType personalPropertyOnSite) {
//        this.personalPropertyOnSite = personalPropertyOnSite;
//    }
//
//    public Character getPoolOnSite() {
//        return poolOnSite.getType();
//    }
//
//    public void setPoolOnSite(YesNoUnknownType poolOnSite) {
//        this.poolOnSite = poolOnSite;
//    }
//
//    public Character getPoolSecure() {
//        return poolSecure.getType();
//    }
//
//    public void setPoolSecure(YesNoUnknownType poolSecure) {
//        this.poolSecure = poolSecure;
//    }
//
//    public Character getPropertyConditionProblemCode() {
//        return propertyConditionProblemCode.getType();
//    }

//    public void setPropertyConditionProblemCode(PropertyConditionProblemCode propertyConditionProblemCode) {
//        this.propertyConditionProblemCode = propertyConditionProblemCode;
//    }
//
//    public Character getPropertyForSale() {
//        return propertyForSale.getType();
//    }
//
//    public void setPropertyForSale(PropertyForSale propertyForSale) {
//        this.propertyForSale = propertyForSale;
//    }

    public String getPropertyMapReference() {
        return StringUtils.rightPad(propertyMapReference == null ? "" : propertyMapReference, 6, SPACE_CHAR).substring(0, 6);
    }

    public void setPropertyMapReference(String propertyMapReference) {
        this.propertyMapReference = propertyMapReference;
    }

//    public Character getPropertyType() {
//        return propertyType.getType();
//    }
//
//    public void setPropertyType(PropertyType propertyType) {
//        this.propertyType = propertyType;
//    }
//
//    public String getRequestorCode() {
//        return StringUtils.rightPad(requestorCode == null ? "" : requestorCode, 9, SPACE_CHAR).substring(0, 9);
//    }
//
//    public void setRequestorCode(String requestorCode) {
//        this.requestorCode = requestorCode;
//    }

    /**
     * Get the fixed length field string for record.
     *
     * @return the fixed-length field record
     */
    @Override
    public String getFixedLengthFieldString() {
        return getRecordId() +
                getRecordCode() +
                getLoanNumber() +
                getPropertyMapReference() +
                getMailingMapReference() +
                getConstructionType() +
                getConstructionOtherType() +
//                getPropertyType() +
//                getPoolOnSite() +
//                getPoolSecure() +
//                getInspectionRequestDate() +
//                getInspectionRequestSource() +
//                getRequestorCode() +
//                getInspectionCompleteDate() +
//                getCardLeftAtProperty() +
//                getFirstKnownVacancyDate() +
//                getOccupancyCode().getType() +
//                getFirstTimeVacant() +
//                getOccupancyVerified() +
//                getOccupancyVerifiedOther() +
//                getInspectorNameCode() +
//                getExternalPropertyCondition() +
//                getPropertyConditionProblemCode() +
//                getOtherPropertyConditionProblem() +
//                getNeighborhoodConditionCode() +
//                getPersonalPropertyOnSite() +
//                getPropertyForSale() +
//                getBrokerName() +
//                getBrokerTelephone() +
//                getInspectionAddress() +
//                getInspectionServiceCode() +
                getFiller() +
                DEFAULT_LINE_SEPARATOR;
    }

    @Override
    public String toString() {
        return "ResultPropertyInspection01{" +
                "recordId='" + recordId + '\'' +
                ", recordCode='" + recordCode + '\'' +
//                ", brokerName='" + brokerName + '\'' +
                ", loanNumber='" + loanNumber + '\'' +
                ", propertyMapReference='" + propertyMapReference + '\'' +
                ", mailingMapReference='" + mailingMapReference + '\'' +
                ", constructionType=" + constructionType +
                ", constructionOtherType='" + constructionOtherType + '\'' +
//                ", propertyType=" + propertyType +
//                ", poolOnSite=" + poolOnSite +
//                ", poolSecure=" + poolSecure +
//                ", inspectionRequestDate=" + inspectionRequestDate +
//                ", inspectionRequestSource=" + inspectionRequestSource +
//                ", requestorCode='" + requestorCode + '\'' +
//                ", inspectionCompleteDate=" + inspectionCompleteDate +
//                ", cardLeftAtProperty=" + cardLeftAtProperty +
//                ", firstKnownVacancyDate=" + firstKnownVacancyDate +
//                ", occupancyCode=" + occupancyCode +
//                ", firstTimeVacant=" + firstTimeVacant +
//                ", occupancyVerified=" + occupancyVerified +
//                ", occupancyVerifiedOther='" + occupancyVerifiedOther + '\'' +
//                ", inspectorNameCode='" + inspectorNameCode + '\'' +
//                ", externalPropertyCondition=" + externalPropertyCondition +
//                ", propertyConditionProblemCode=" + propertyConditionProblemCode +
//                ", otherPropertyConditionProblem='" + otherPropertyConditionProblem + '\'' +
//                ", neighborhoodConditionCode=" + neighborhoodConditionCode +
//                ", personalPropertyOnSite=" + personalPropertyOnSite +
//                ", propertyForSale=" + propertyForSale +
//                ", brokerTelephone='" + brokerTelephone + '\'' +
//                ", badMailingAddressCause=" + badMailingAddressCause +
//                ", badPropertyAddressCause=" + badPropertyAddressCause +
//                ", inspectionType=" + inspectionServiceCode +
                ", filler='" + filler + '\'' +
                '}';
    }
}
