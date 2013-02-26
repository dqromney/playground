package com.stgutah.playground.opencsv.examples;

/**
 * Service Order Data enumeration.
 *
 * User: dqromney
 * Date: Dec 9, 2011
 * Time: 1:18:41 PM
 */
public enum SOIntegrationDataEnum {
    // All defined CSV column names, paired with a ServiceOrderLoadItem object bean property names;
    // add or remove as needed.
    IDENTIFIER("IDENTIFIER(Originator|ClientId|SubClientId|ServiceTemplateName|isValid|testName)", "soIndentifier"),
    DETAIL_TYPE("SERVICE_DETAIL_TYPE_DATA", "soValuePairList");

    public final String rawColumnName;
    public final String beanPropertyName;

    SOIntegrationDataEnum(String rawColumnName, String beanPropertyName) {
        this.beanPropertyName = beanPropertyName;
        this.rawColumnName = rawColumnName;
    }

    /**
     * Search for a raw column name, as defined in the CSV file, and return null if not found, or the defined enum.
     *
     * @param pRawColumnName the raw column name search string.
     * @return a null if not found, otherwise the enum for search string.
     */
    public static SOIntegrationDataEnum searchRawColumnName(String pRawColumnName) {
        SOIntegrationDataEnum found = null;
        SOIntegrationDataEnum[] searchList = SOIntegrationDataEnum.values();
        for (SOIntegrationDataEnum item : searchList) {
            if (item.getRawColumnName().equalsIgnoreCase(pRawColumnName)) {
                found = item;
                break;
            }
        }
        return found;
    }

    /**
     * Search for a bean property name, as defined in the UserBean object, and return null if not found,
     * or the defined enum.
     *
     * @param pBeanPropertyName the bean property name search string.
     * @return a null if not found, otherwise the enum for search string.
     */
    public static SOIntegrationDataEnum searchBeanPropertyName(String pBeanPropertyName) {
        SOIntegrationDataEnum found = null;
        SOIntegrationDataEnum[] searchList = SOIntegrationDataEnum.values();
        for (SOIntegrationDataEnum item : searchList) {
            if (item.getBeanPropertyName().equalsIgnoreCase(pBeanPropertyName)) {
                found = item;
                break;
            }
        }
        return found;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("SOIntegrationDataEnum");
        sb.append("{beanPropertyName='").append(beanPropertyName).append('\'');
        sb.append(", rawColumnName='").append(rawColumnName).append('\'');
        sb.append('}');
        return sb.toString();
    }

// ----------------------------------------------------------------
    // Accessors
    // ----------------------------------------------------------------

    public String getBeanPropertyName() {
        return beanPropertyName;
    }

    public String getRawColumnName() {
        return rawColumnName;
    }
}
