package com.stgutah.playground.opencsv.examples;

/**
 * User Bean class, used in CSVReader.
 *
 * User: dqromney
 * Date: Oct 14, 2010
 * Time: 1:28:13 PM
 */
public class UserBean {

    Long userId;
    String userName;
    String password;
    String zipCode;

    public UserBean() {
        this.password = "";
        this.userId = 0L;
        this.userName = "";
        this.zipCode = "";
    }

    public UserBean(String password, Long userId, String userName, String zipCode) {
        this.password = password;
        this.userId = userId;
        this.userName = userName;
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }

    // ----------------------------------------------------------------
    // Accessors
    // ----------------------------------------------------------------
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }


}
