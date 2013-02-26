package com.stgutah.playground.supercsv;

import java.util.Date;

/**
 * User Bean.
 * <p/>
 * User: dqromney
 * Date: Oct 13, 2010
 * Time: 4:59:50 PM
 */
public class UserBean {
    String username, password, town;
    Date date;
    int zip;

    public Date getDate() {
        return date;
    }

    public String getPassword() {
        return password;
    }

    public String getTown() {
        return town;
    }

    public String getUsername() {
        return username;
    }

    public int getZip() {
        return zip;
    }

    public void setDate(final Date date) {
        this.date = date;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setTown(final String town) {
        this.town = town;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public void setZip(final int zip) {
        this.zip = zip;
    }

}
