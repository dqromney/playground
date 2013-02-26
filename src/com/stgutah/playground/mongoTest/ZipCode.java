package com.stgutah.playground.mongoTest;

/**
 * Domain class that holds ZipCode object
 * User: dqromney
 * Date: Jun 22, 2010
 * Time: 5:14:44 PM
 */
public class ZipCode
{
    String zipCode;
    String county;
    String areaCode;

    public ZipCode()
    {
    }

    public ZipCode(String areaCode, String county, String zipCode)
    {
        this.areaCode = areaCode;
        this.county = county;
        this.zipCode = zipCode;
    }

    public String getAreaCode()
    {
        return areaCode;
    }

    public void setAreaCode(String areaCode)
    {
        this.areaCode = areaCode;
    }

    public String getCounty()
    {
        return county;
    }

    public void setCounty(String county)
    {
        this.county = county;
    }

    public String getZipCode()
    {
        return zipCode;
    }

    public void setZipCode(String zipCode)
    {
        this.zipCode = zipCode;
    }

    @Override
    public String toString()
    {
        return "ZipCode{" +
                "areaCode='" + areaCode + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", county='" + county + '\'' +
                '}';
    }
}
