package com.stgutah.playground.reflection;

/**
 * Result Record interface.
 *
 * User: dqromney
 * Date: Jan 7, 2011
 * Time: 3:51:06 PM
 */
public interface ResultRecord {

    final String DEFAULT_LINE_SEPARATOR = System.getProperty("line.separator");
    final String HEADER = "HDR";
    final String TRAILER = "TRL";
    final String VERSION = "001";

    final String PROPERTY_INSPECTION_RESULTS_RECORD_ID = "PIR";
    final String CUSTOMER_FINANCIAL_STATEMENT_RECORD_ID = "CFS";
    final String FEES_DUE_RECORD_ID = "FEE";

    final String RECORD_CODE_01 = "01";
    final String RECORD_CODE_02 = "02";
    final String RECORD_CODE_03 = "03";
    final String RECORD_CODE_04 = "04";
    final String RECORD_CODE_05 = "05";
    final String RECORD_CODE_06 = "06";
    final String RECORD_CODE_07 = "07";

    final Character ZERO_CHAR = '0';
    final Character SPACE_CHAR = ' ';

    // Defined Service Templates in use
    final String ST_DISASTER_INSPECTION = "DISASTER INSPECTION";
    final String ST_FANNIE_MAE_INSPECTION = "FANNIE MAE INSPECTION";
    final String ST_LOSS_DRAFT_INSPECTION = "LOSS DRAFT INSPECTION";
    final String ST_NO_CONTACT_INSPECTION = "NO CONTACT INSPECTION";
    final String ST_OCCUPANCY_VERIFICATION = "OCCUPANCY VERIFICATION";
    final String ST_PROPERTY_CONDITION = "PROPERTY CONDITION";
    final String ST_SALE_DATE_INSPECTION = "SALE DATE INSPECTION";

    /**
     * Get the fixed length field string for record.
     *
     * @return the fixed-length field record
     */
    public String getFixedLengthFieldString();

}
