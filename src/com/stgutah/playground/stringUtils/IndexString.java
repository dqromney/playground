package com.stgutah.playground.stringUtils;

import org.apache.commons.lang.StringUtils;

/**
 * Index a String, this was used in the O.C. Tanner project to create Customer Defined Field Names that
 * were indexed with a suffix. These field definitions were used many times by customer/clients.  Basically
 * the number of Customer Defined fields is based upon their largest user, the maximum number of defined fields
 * used by a Customer. For example, if the largest customer used 30 Customer Defined fields, there would only
 * be 30 defined fields in the database.  Other customers would use, incrementally, the existing Customer
 * Defined fields 1,2,3,4,.. to say 10, while the largest would be creating new ones like 31,32,33.  References
 * to these fields are made in the CustomerField table. Long explanation, but you get the idea.   
 *
 * User: dqromney
 * Date: Dec 8, 2009
 * Time: 11:36:23 AM
 *
 */
public class IndexString {

    public static final String CUSTOMER_DEFINED_FIELD = "CUSTOMER_DEFINED_FIELD_";

    /**
     * Construct a field name for a Customer Defined Field
     *
     * @param pCustomerID the customer identifier
     * @return a unique field name used in Field table
     */
    public String getNextPlaceHolderFieldName(Long pCustomerID) throws Exception {
        StringBuffer buffer = new StringBuffer();
        String fieldName = findMaxCustomerFieldName(pCustomerID);

        // Get max(fieldName) index for customer identifier i.e. CUSTOMER_DEFINED_FIELD_{00000001}
        Long lastIndex = getIndex(fieldName);
        // Set next place-holder (increment max by 1)
        String newIndex = new Long(lastIndex+1).toString();
        newIndex = StringUtils.leftPad(newIndex, 8, '0');
        // Append fieldName by place-holder
        buffer.append(CUSTOMER_DEFINED_FIELD).append(newIndex);

        return buffer.toString();
    }

    private Long getIndex(String fieldName) throws NumberFormatException {
        int beginIndex = fieldName.lastIndexOf("_")+1;
        return new Long(fieldName.substring(beginIndex));
    }

    private String findMaxCustomerFieldName(Long customerID) {
        String customerDefinedField = null;
        if (customerID.compareTo(1L) == 0) {
            customerDefinedField = CUSTOMER_DEFINED_FIELD + "00000124";
        } else if (customerID.compareTo(2L) == 0) {
            customerDefinedField = CUSTOMER_DEFINED_FIELD + "00000000";
        } else if (customerID.compareTo(3L) == 0) {
            customerDefinedField = CUSTOMER_DEFINED_FIELD + "4";
        } else if (customerID.compareTo(4L) == 0) {
            customerDefinedField = CUSTOMER_DEFINED_FIELD;
        }
        return customerDefinedField;
    }

    public static void main(String[] args) {

        IndexString is = new IndexString();
        String placeHolderFieldName = null;
        try {
            System.out.println("Customer("+1L+") MAXimum Field Name=[" +is.findMaxCustomerFieldName(1L)+ "]");
            placeHolderFieldName = is.getNextPlaceHolderFieldName(1L);
            System.out.println("Customer's nextPlaceHolderFieldName=[" + placeHolderFieldName + "]");

            System.out.println("Customer("+2L+") MAXimum Field Name=[" +is.findMaxCustomerFieldName(2L)+ "]");
            placeHolderFieldName = is.getNextPlaceHolderFieldName(2L);
            System.out.println("Customer's nextPlaceHolderFieldName=[" + placeHolderFieldName + "]");

            System.out.println("Customer("+3L+") MAXimum Field Name=[" +is.findMaxCustomerFieldName(3L)+ "]");
            placeHolderFieldName = is.getNextPlaceHolderFieldName(3L);
            System.out.println("Customer's nextPlaceHolderFieldName=[" + placeHolderFieldName + "]");

            System.out.println("Customer("+4L+") MAXimum Field Name=[" +is.findMaxCustomerFieldName(4L)+ "]");
            placeHolderFieldName = is.getNextPlaceHolderFieldName(4L);
            System.out.println("Customer's nextPlaceHolderFieldName=[" + placeHolderFieldName + "]");
        } catch (NumberFormatException e) {
            System.out.println("Exception: Invalid index number!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
