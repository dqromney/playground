package com.stgutah.playground.enums;

import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: dqromney
 * Date: May 3, 2010
 * Time: 6:14:58 PM
 * Note(s):
 * select distinct DB_TABLE_NAME, DB_COLUMN_NAME, FIELD_NAME, FIELD_GROUP_NAME || ':' || FIELD_NAME || ':' || DB_COLUMN_NAME from CLIENT_FIELD_PROPERTIES_VW where SYSTEM_DEFINED_FIELD_FLAG = 1;

 *
 */
public enum EnumSearch
{

    ACCOUNT_CLAIM_DATE("ACCOUNT_CLAIM_DATE", "ACCOUNT_CLAIM_DATE", 'D'), // TODO Where is this?
    ANNIVERSARY_DATE("ANNIVERSARY_DATE", ":ANNIVERSARY_DATE:ANNIVERSARY_DATE", 'D'),
    AWARD_LEVEL("AWARD_LEVEL", ":AWARD_LEVEL:AWARD_NAME", 'T'),
    BILL_ADD1("BILL_ADD1", "EMP BILLING ADDRESS:BILL_ADD1:ADDRESS_LINE_1", 'T'),
    BILL_ADD2("BILL_ADD2", "EMP BILLING ADDRESS:BILL_ADD1:ADDRESS_LINE_2", 'T'),
    BILL_ADD3("BILL_ADD3", "EMP BILLING ADDRESS:BILL_ADD1:ADDRESS_LINE_3", 'T'),
    BILL_CITY("BILL_CITY", "EMP BILLING ADDRESS:BILL_CITY:CITY", 'T'),
    BILL_COUNTRY("BILL_COUNTRY", "EMP BILLING ADDRESS:BILL_COUNTRY:ISO_COUNTRY_CODE", 'T'),
    BILL_EMAIL("BILL_EMAIL", "EMP BILLING ADDRESS:EMAIL_ADDRESS", 'T'),
    BILL_FAX("BILL_FAX", "REPLACE", 'T'),
    BILL_LOCATION_CONTACT_NAME("BILL_LOCATION_CONTACT_NAME", "EMP BILLING ADDRESS:CONTACT_NAME", 'T'),
    BILL_LOCATION_ID("BILL_LOCATION_ID", "EMP BILLING ADDRESS:LOCATION_CODE", 'T'),
    BILL_LOCATION_NAME("BILL_LOCATION_NAME", "EMP BILLING ADDRESS:LOCATION_NAME", 'T'),
    BILL_PHONE("BILL_PHONE", "EMP BILLING ADDRESS:TELEPHONE_NUM", 'T'),
    BILL_POSTAL("BILL_POSTAL", "EMP BILLING ADDRESS:POSTAL_CODE", 'T'),
    BILL_PO_NUMBER("BILL_PO_NUMBER", "EMP BILLING ADDRESS:LOCATION_PO_NUM", 'T'),
    BILL_STATE("BILL_STATE", "EMP BILLING ADDRESS:SAP_STATE_CODE", 'T'),
    BU_ID("BU_ID", "BU_ID_FIELD", 'T'),
    BU_NAME("BU_NAME", "BUSINESS_UNIT_CODE", 'T'),
    CAMPAIGN_ID("CAMPAIGN_ID", "REPLACE", 'T'),
    CLIENT_NUMBER("CLIENT_NUMBER", "EMPLOYEE RELATIONSHIP DATA:CAMPAIGN_NUM", 'T'),
    CORPORATE_VALUE("CORPORATE_VALUE", "REPLACE", 'T'),
    CREDIT_CARD_EXPIRATION("CREDIT_CARD_EXPIRATION", "EMP BILLING ADDRESS:EXPIRATION_DATE", 'D'),
    CREDIT_CARD_HOLDER_NAME("CREDIT_CARD_HOLDER_NAME", "EMP BILLING ADDRESS:NAME_ON_CARD", 'T'),
    CREDIT_CARD_NUMBER("CREDIT_CARD_NUMBER", "EMP BILLING ADDRESS:CARD_NUM", 'T'),
    CREDIT_CARD_TYPE("CREDIT_CARD_TYPE", "EMP BILLING ADDRESS:CARD_TYPE", 'T'),
    EMAIL_ADDRESS("EMAIL_ADDRESS", "EMPLOYEE DATA:EMAIL_ADDRESS", 'T'),
    EMPLOYEE_ID("EMPLOYEE_ID", "EMPLOYEE DATA:EMPLOYEE_ID", 'T'),
    EMPLOYEE_IMAGE_URL("EMPLOYEE_IMAGE_URL", "EMPLOYEE RELATIONSHIP DATA:USER_IMAGE_URL", 'T'),
    EMPLOYEE_LANGUAGE("EMPLOYEE_LANGUAGE", "EMPLOYEE DATA:LANGAUGE_ISO_CODE", 'T'),
    EMPLOYEE_SECURITY_ROLE("EMPLOYEE_SECURITY_ROLE", "EMPLOYEE RELATIONSHIP DATA:USER_ROLE_NAME", 'T'),
    EMPLOYMENT_STATUS("EMPLOYMENT_STATUS", "EMPLOYEE DATA:STATUS_TITLE_TYPE_DESC", 'T'),
    FIRST_NAME("FIRST_NAME", "EMPLOYEE DATA:FIRST_NAME", 'T'), // TODO What about :FIRST_MAME ?
    GENDER("GENDER", "EMPLOYEE DATA:GENDER_CODE", 'T'),
    HIRE_DATE("HIRE_DATE", "EMPLOYEE DATA:HIRE_DATE", 'D'),
    HOME_ADD1("HOME_ADD1", "EMP HOME ADDRESS:ADDRESS_LINE_1", 'T'),
    HOME_ADD2("HOME_ADD2", "EMP HOME ADDRESS:ADDRESS_LINE_2", 'T'),
    HOME_ADD3("HOME_ADD3", "EMP HOME ADDRESS:ADDRESS_LINE_3", 'T'),
    HOME_CITY("HOME_CITY", "EMP HOME ADDRESS:CITY", 'T'),
    HOME_COUNTRY("HOME_COUNTRY", "EMP HOME ADDRESS:ISO_COUNTRY_CODE", 'T'),
    HOME_PHONE("HOME_PHONE", "EMP HOME ADDRESS:TELEPHONE_NUM", 'T'),
    HOME_POSTAL("HOME_POSTAL", "EMP HOME ADDRESS:POSTAL_CODE", 'T'),
    HOME_STATE("HOME_STATE", "EMP HOME ADDRESS:SAP_STATE_CODE", 'T'),
    LAST_NAME("LAST_NAME", "EMPLOYEE DATA:LAST_NAME", 'T'),
    LOGIN_ID("LOGIN_ID", "EMPLOYEE DATA:USER_LOGIN_NAME", 'T'),
    MANAGER_EMPLOYEE_ID("MANAGER_EMPLOYEE_ID", "EMPLOYEE RELATIONSHIP DATA:EMPLOYEE_ID", 'T'),
    MIDDLE_NAME("MIDDLE_NAME", "EMPLOYEE DATA:MIDDLE_NAME", 'T'),
    PARENT_BU_ID("PARENT_BU_ID", "REPLACE", 'T'),
    PARENT_BU_NAME("PARENT_BU_NAME", "REPLACE", 'T'),
    PREFERRED_NAME("PREFERRED_NAME", "EMPLOYEE DATA:PREFERRED_NAME", 'T'),
    PRESENTER("PRESENTER", "REPLACE", 'T'),
    PROGRAM_ID("PROGRAM_ID", "EMPLOYEE RELATIONSHIP DATA:PROGRAM_NUM", 'T'),
    RETIREMENT_DATE("RETIREMENT_DATE", ":RETIREMENT_DATE", 'D'),
    THIRD_PARTY_BILL_ACCOUNT_NUM("THIRD_PARTY_BILL_ACCOUNT_NUM", "EMP BILLING ADDRESS:TRD_PARTY_SHIPPING_ACCOUNT_NUM", 'T'),
    THIRD_PARTY_SHIP_ACCOUNT_NUM("THIRD_PARTY_SHIP_ACCOUNT_NUM", "EMP WORK ADDRESS:TRD_PARTY_SHIPPING_ACCOUNT_NUM", 'T'),
    TITLE_GREETING("TITLE_GREETING", "REPLACE", 'T'),
    TROPHY_NAME("TROPHY_NAME", "REPLACE", 'T'),
    TROPHY_SELECTION_MM_NUMBER("TROPHY_SELECTION_MM_NUMBER", "REPLACE", 'T'),
    UNIQUE_ID("UNIQUE_ID", "EMPLOYEE DATA:UNIQUE_ID", 'T'),
    WORK_ADD1("WORK_ADD1", "EMP WORK ADDRESS:ADDRESS_LINE_1", 'T'),
    WORK_ADD2("WORK_ADD2", "EMP WORK ADDRESS:ADDRESS_LINE_2", 'T'),
    WORK_ADD3("WORK_ADD3", "EMP WORK ADDRESS:ADDRESS_LINE_3", 'T'),
    WORK_CITY("WORK_CITY", "EMP WORK ADDRESS:CITY", 'T'),
    WORK_COUNTRY("WORK_COUNTRY", "EMP WORK ADDRESS:ISO_COUNTRY_CODE", 'T'),
    WORK_EMAIL("WORK_EMAIL", "EMP WORK ADDRESS:EMAIL_ADDRESS", 'T'),
    WORK_FAX("WORK_FAX", "REPLACE", 'T'),
    WORK_LOCATION_CONTACT_NAME("WORK_LOCATION_CONTACT_NAME", "EMP WORK ADDRESS:CONTACT_NAME  ", 'T'),
    WORK_LOCATION_ID("WORK_LOCATION_ID", "EMP WORK ADDRESS:LOCATION_CODE", 'T'),
    WORK_LOCATION_NAME("WORK_LOCATION_NAME", "EMP WORK ADDRESS:LOCATION_NAME", 'T'),
    WORK_PHONE("WORK_PHONE", "EMP BILLING ADDRESS:TELEPHONE_NUM", 'T'),
    WORK_POSTAL("WORK_POSTAL", "EMP WORK ADDRESS:POSTAL_CODE", 'T'),
    WORK_STATE("WORK_STATE", "EMP WORK ADDRESS:SAP_STATE_CODE", 'T'),
    YEARS_OF_SERVICE("YEARS_OF_SERVICE", "REPLACE", 'D');

    // Field name as defined in the CUSTOMER_FIELD_PROPERTIES_VW
    public final String fieldName;
    // COLUMN name as defined in the SYSTEM_DEFINED_FIELD_VALUE_VW
    public final String systemColumnName;
    private final Character fieldTypeInd;

    private EnumSearch(final String pFieldName, final String pSystemColumnName, final Character pFieldTypeInd) {
        this.fieldName = pFieldName;
        this.systemColumnName = pSystemColumnName;
        this.fieldTypeInd = pFieldTypeInd;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Character getFieldTypeInd() {
        return fieldTypeInd;
    }

    public String getSystemColumnName() {
        return systemColumnName;
    }

    /**
     * Search for Field name as defined in CLIENT_FIELD_PROPERTIES_VW.
     *
     * @param pFieldName the FieldName string to search for
     * @return the enum for the FieldName
     */
    public static EnumSearch searchFieldName(String pFieldName) {
        EnumSearch found = null;
        EnumSearch[] enumSearchList = EnumSearch.values();
        for(EnumSearch item : enumSearchList) {
            if (item.getFieldName().compareTo(pFieldName) == 0) {
                found = item;
                break;
            }
        }
        return found;
    }

    /**
     * Search for a System Defined Field Value column name as defined in SYSTEM_DEFINED_FIELD_VALUE_VW.
     *
     * @param pSystemColumnName the System Define Field Value column name string to search for
     * @return the enum for the FieldName
     */
    public static EnumSearch searchSystemColumnName(String pSystemColumnName) {
        EnumSearch found = null;
        EnumSearch[] enumSearchList = EnumSearch.values();
        for(EnumSearch item : enumSearchList) {
            if (item.getSystemColumnName().compareTo(pSystemColumnName) == 0) {
                found = item;
                break;
            }
        }
        return found;
    }


    @Override
    public String toString()
    {
        return "SystemDefinedFieldIds{" +
                "fieldName='" + fieldName + '\'' +
                ", systemColumnName=" + systemColumnName +
                ", fieldTypeInd=" + fieldTypeInd +
                '}';
    }

    public static void main(String [] args) {
        Logger LOG = Logger.getLogger("EnumSearch");

        EnumSearch item = EnumSearch.searchSystemColumnName("ACCOUNT_CLAIM_DATE");
        LOG.info("ACCOUNT_CLAIM_DATE FOUND=[" +item.toString() + "]");

        item = EnumSearch.searchFieldName("HOME_COUNTRY");
        LOG.info("HOME_COUNTRY FOUND=[" +item.toString() + "]");
    }
}
