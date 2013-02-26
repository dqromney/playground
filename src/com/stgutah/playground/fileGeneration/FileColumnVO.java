package com.stgutah.playground.fileGeneration;

/**
 * Class that represents a column in the file.
 *
 * User: dqromney
 * Date: Aug 13, 2010
 * Time: 3:42:51 PM
 */
public class FileColumnVO
{
    private String name;

    private String dataType;

    private Object value;

    public FileColumnVO()
    {
        this.dataType = Constants.DATA_TYPE_UNDEFINED;
        this.name = "UNDEFINED";
        this.value = "UNDEFINED";
    }

    public FileColumnVO(String dataType, String name, Object value)
    {
        this.dataType = dataType;
        this.name = name;
        this.value = value;
    }

    /**
     * Getter method for name.
     *
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Setter method for name.
     *
     * @param pName
     *            the name to set
     */
    public void setName(String pName)
    {
        this.name = pName;
    }

    /**
     * Getter method for dataType.
     *
     * @return the dataType
     */
    public String getDataType()
    {
        return dataType;
    }

    /**
     * Setter method for dataType.
     *
     * @param pDataType
     *            the dataType to set
     */
    public void setDataType(String pDataType)
    {
        this.dataType = pDataType;
    }

    /**
     * Getter method for value.
     *
     * @return the value
     */
    public Object getValue()
    {
        return value;
    }

    /**
     * Setter method for value.
     *
     * @param pValue
     *            the value to set
     */
    public void setValue(Object pValue)
    {
        this.value = pValue;
    }
}
