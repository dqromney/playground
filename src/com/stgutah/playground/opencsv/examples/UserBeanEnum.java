package com.stgutah.playground.opencsv.examples;

/**
 * UserBean enumeration; convenience enum.
 *
 * User: dqromney
 * Date: Oct 14, 2010
 * Time: 3:50:13 PM
 */
public enum UserBeanEnum {

    // All defined CSV column names, paired with a ServiceOrderLoadItem object bean property names;
    // add or remove as needed.
    USER_ID("user_id", "userId"),
    USER_NAME("user_name", "userName"),
    PASSWORD("password", "password"),
    ZIP("zip_code", "zipCode");

    public final String rawColumnName;
    public final String beanPropertyName;

    UserBeanEnum(String rawColumnName, String beanPropertyName) {
        this.beanPropertyName = beanPropertyName;
        this.rawColumnName = rawColumnName;
    }

    /**
     * Search for a raw column name, as defined in the CSV file, and return null if not found, or the defined enum.
     *
     * @param pRawColumnName the raw column name search string.
     * @return a null if not found, otherwise the enum for search string.
     */
    public static UserBeanEnum searchRawColumnName(String pRawColumnName) {
        UserBeanEnum found = null;
        UserBeanEnum[] searchList = UserBeanEnum.values();
        for (UserBeanEnum item : searchList) {
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
    public static UserBeanEnum searchBeanPropertyName(String pBeanPropertyName) {
        UserBeanEnum found = null;
        UserBeanEnum[] searchList = UserBeanEnum.values();
        for (UserBeanEnum item : searchList) {
            if (item.getBeanPropertyName().equalsIgnoreCase(pBeanPropertyName)) {
                found = item;
                break;
            }
        }
        return found;
    }

    @Override
    public String toString() {
        return "UserBeanEnum{" +
                "beanPropertyName='" + beanPropertyName + '\'' +
                ", rawColumnName='" + rawColumnName + '\'' +
                '}';
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
