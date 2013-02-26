package com.stgutah.playground.FixedLengthParsing2;

import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * Service Order Load Item.
 * User: dqromney
 * Date: 6/25/12
 * Time: 10:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class ServiceOrderLoadItem {
    public static final String REGULAR_EXPRESSION_PHONE = "\\D*(\\d\\D*){10}\\D*";
    public static final String REGULAR_EXPRESSION_ZIP = "\\d{5}";

    /**
     *
     */
    private static final long serialVersionUID = 5195047272174729552L;

    public static enum ServiceOrderLoadItemType {EXCEPTION}

    private Long clientId;
    private Date dateVendorDue;
    private Date dateClientDue;
    private Date dateReceived;
    private String specialInstructions;
    private ServiceOrderLoadItemType serviceOrderLoadItemType;
    private String errors;
    private String assetNumber;
    private String propertyName;
    private String clientReference;
    private String accountServicer;
    private String propertyType;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String zip;
    private Boolean porpertyAddressChangeOverride = false;
    //       @Transient
    private Boolean usePorpertyAddressFlag = false;
    private Boolean serviceAreaAddOverride = false;
    private String propertyContact;
    private String phone1;
    private String phone2;
    private String clientContactFirstName;
    private String clientContactLastName;
    //   	@RegExp(value=REGULAR_EXPRESSION_PHONE, errorCode = "error.phone.digit.count" , applyIf="clientContactPhone IS NOT BLANK", message="10 digits are required")
//   	@Column(name="client_contact_phone")
    private String clientContactPhone;
    //   	@Email(applyIf="clientContactEmail IS NOT BLANK")
//   	@Column(name="client_contact_email")
    private String clientContactEmail;
    private String brokerFirstName;
    private String brokerLastName;
    //   	@RegExp(value=REGULAR_EXPRESSION_PHONE, errorCode= "error.phone.digit.count" , applyIf="brokerPhoneMobile IS NOT BLANK", message="10 digits are required")
    private String brokerPhoneMobile;
    //   	@RegExp(value=REGULAR_EXPRESSION_PHONE,errorCode="error.phone.digit.count", applyIf="brokerPhoneOffice IS NOT BLANK", message="10 digits are required")
    private String brokerPhoneOffice;
    //   	@Email(applyIf="brokerEmail IS NOT BLANK")
    private String brokerEmail;
    private String serviceArea;
    private String serviceAreaStreet1;
    private String serviceAreaStreet2;
    private String serviceAreaCity;
    private String serviceAreaState;
    //       @RegExp(value = "(\\d{5})|(^\\s*)", errorCode = "error.zip")
    private String serviceAreaZip;
    private String lockBoxCode;
    private String accessCode;
    private String keyCode;
    private String services;
    // private ClientOrder clientOrder;
    // private ServiceWorkflowType serviceWorkflowType;
    private String borrower;
    private String borrowerPhone1;
    private String borrowerPhone2;
    private String owbSourceOfRequest;
    private String owbRequestorCode;
    private String owbLoanType;
    private String owbOrderType;
    private String safClientReferenceNumber;
    // private CreationType creationType;

    public String getErrors() {
        return errors;
    }

    public ServiceOrderLoadItemType getServiceOrderLoadItemType() {
        return serviceOrderLoadItemType;
    }

    public void setServiceOrderLoadItemType(ServiceOrderLoadItemType serviceOrderLoadItemType) {
        this.serviceOrderLoadItemType = serviceOrderLoadItemType;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    public String getAssetNumber() {
        return assetNumber;
    }

    public void setAssetNumber(String assetNumber) {
        this.assetNumber = assetNumber;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getClientReference() {
        return clientReference;
    }

    public void setClientReference(String clientReference) {
        this.clientReference = clientReference;
    }

    public String getAccountServicer() {
        return accountServicer;
    }

    public void setAccountServicer(String accountServicer) {
        this.accountServicer = accountServicer;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPropertyContact() {
        return propertyContact;
    }

    public void setPropertyContact(String propertyContact) {
        this.propertyContact = propertyContact;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getClientContactFirstName() {
        return clientContactFirstName;
    }

    public void setClientContactFirstName(String clientContactFirstName) {
        this.clientContactFirstName = clientContactFirstName;
    }

    public String getClientContactLastName() {
        return clientContactLastName;
    }

    public void setClientContactLastName(String clientContactLastName) {
        this.clientContactLastName = clientContactLastName;
    }

    public String getClientContactPhone() {
        return clientContactPhone;
    }

    public void setClientContactPhone(String clientContactPhone) {
        this.clientContactPhone = clientContactPhone;
    }

    public String getClientContactEmail() {
        return clientContactEmail;
    }

    public void setClientContactEmail(String clientContactEmail) {
        this.clientContactEmail = clientContactEmail;
    }

    public String getBrokerFirstName() {
        return brokerFirstName;
    }

    public void setBrokerFirstName(String brokerFirstName) {
        this.brokerFirstName = brokerFirstName;
    }

    public String getBrokerLastName() {
        return brokerLastName;
    }

    public void setBrokerLastName(String brokerLastName) {
        this.brokerLastName = brokerLastName;
    }

    public String getBrokerPhoneMobile() {
        return brokerPhoneMobile;
    }

    public void setBrokerPhoneMobile(String brokerPhoneMobile) {
        this.brokerPhoneMobile = brokerPhoneMobile;
    }

    public String getBrokerPhoneOffice() {
        return brokerPhoneOffice;
    }

    public void setBrokerPhoneOffice(String brokerPhoneOffice) {
        this.brokerPhoneOffice = brokerPhoneOffice;
    }

    public String getBrokerEmail() {
        return brokerEmail;
    }

    public void setBrokerEmail(String brokerEmail) {
        this.brokerEmail = brokerEmail;
    }

    public String getServiceArea() {
        return serviceArea;
    }

    public void setServiceArea(String serviceArea) {
        this.serviceArea = serviceArea;
    }

    public String getServiceAreaCity() {
        return serviceAreaCity;
    }

    public void setServiceAreaCity(String serviceAreaCity) {
        this.serviceAreaCity = serviceAreaCity;
    }

    public String getServiceAreaState() {
        return serviceAreaState;
    }

    public void setServiceAreaState(String serviceAreaState) {
        this.serviceAreaState = serviceAreaState;
    }

    public String getServiceAreaStreet1() {
        return serviceAreaStreet1;
    }

    public void setServiceAreaStreet1(String serviceAreaStreet1) {
        this.serviceAreaStreet1 = serviceAreaStreet1;
    }

    public String getServiceAreaStreet2() {
        return serviceAreaStreet2;
    }

    public void setServiceAreaStreet2(String serviceAreaStreet2) {
        this.serviceAreaStreet2 = serviceAreaStreet2;
    }

    public String getServiceAreaZip() {
        return serviceAreaZip;
    }

    public void setServiceAreaZip(String serviceAreaZip) {
        this.serviceAreaZip = serviceAreaZip;
    }

    public String getLockBoxCode() {
        return lockBoxCode;
    }

    public void setLockBoxCode(String lockBoxCode) {
        this.lockBoxCode = lockBoxCode;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

//    public ClientOrder getClientOrder() {
//        return clientOrder;
//    }
//
//    public void setClientOrder(ClientOrder clientOrder) {
//        this.clientOrder = clientOrder;
//    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

    public String getKeyCode() {
        return keyCode;
    }

    public Date getDateVendorDue() {
        return dateVendorDue;
    }

    public void setDateVendorDue(Date dateVendorDue) {
        this.dateVendorDue = dateVendorDue;
    }

    public Date getDateClientDue() {
        return dateClientDue;
    }

    public void setDateClientDue(Date dateClientDue) {
        this.dateClientDue = dateClientDue;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    public String getSpecialInstructions() {
        return specialInstructions;
    }

    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }

//    public void setServiceWorkflowType(ServiceWorkflowType serviceWorkflowType) {
//        this.serviceWorkflowType = serviceWorkflowType;
//    }
//
//    public ServiceWorkflowType getServiceWorkflowType() {
//        return serviceWorkflowType;
//    }

    public String getOwbLoanType() {
        return owbLoanType;
    }

    public void setOwbLoanType(String owbLoanType) {
        this.owbLoanType = owbLoanType;
    }

    public String getOwbOrderType() {
        return owbOrderType;
    }

    public void setOwbOrderType(String owbOrderType) {
        this.owbOrderType = owbOrderType;
    }

    public String getOwbRequestorCode() {
        return owbRequestorCode;
    }

    public void setOwbRequestorCode(String owbRequestorCode) {
        this.owbRequestorCode = owbRequestorCode;
    }

    public String getOwbSourceOfRequest() {
        return owbSourceOfRequest;
    }

    public void setOwbSourceOfRequest(String owbSourceOfRequest) {
        this.owbSourceOfRequest = owbSourceOfRequest;
    }

    public String formatPhoneNumber(String source) {
        if (StringUtils.isNotBlank(source)) {
            String phoneNumber = source.replaceAll("\\D", "");
            StringBuilder phoneNumberBuilder = new StringBuilder();
            phoneNumberBuilder.append("(");
            phoneNumberBuilder.append(phoneNumber.substring(0, 3));
            phoneNumberBuilder.append(")");
            phoneNumberBuilder.append(" ");
            phoneNumberBuilder.append(phoneNumber.substring(3, 6));
            phoneNumberBuilder.append("-");
            phoneNumberBuilder.append(phoneNumber.substring(6));
            return phoneNumberBuilder.toString();
        } else {
            return null;
        }
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrowerPhone1(String borrowerPhone1) {
        this.borrowerPhone1 = borrowerPhone1;
    }

    public String getBorrowerPhone1() {
        return borrowerPhone1;
    }

    public void setBorrowerPhone2(String borrowerPhone2) {
        this.borrowerPhone2 = borrowerPhone2;
    }

    public String getBorrowerPhone2() {
        return borrowerPhone2;
    }

    public void setSafClientReferenceNumber(String safClientReferenceNumber) {
        this.safClientReferenceNumber = safClientReferenceNumber;
    }

    public String getSafClientReferenceNumber() {
        return safClientReferenceNumber;
    }

//    public CreationType getCreationType() {
//        return creationType;
//    }
//
//    public void setCreationType(CreationType creationType) {
//        this.creationType = creationType;
//    }

    public void setPorpertyAddressChangeOverride(
            Boolean porpertyAddressChangeOverride) {
        this.porpertyAddressChangeOverride = porpertyAddressChangeOverride;
    }

    public Boolean getPorpertyAddressChangeOverride() {
        return porpertyAddressChangeOverride;
    }

    public void setServiceAreaAddOverride(Boolean serviceAreaAddOverride) {
        this.serviceAreaAddOverride = serviceAreaAddOverride;
    }

    public Boolean getServiceAreaAddOverride() {
        return serviceAreaAddOverride;
    }

    public void setUsePorpertyAddressFlag(Boolean usePorpertyAddressFlag) {
        this.usePorpertyAddressFlag = usePorpertyAddressFlag;
    }

    public Boolean getUsePorpertyAddressFlag() {
        return usePorpertyAddressFlag;
    }

}
