package com.stgutah.playground.opencsv.examples;

/**
 * Class the represents a row column value.
 * 
 * User: dqromney
 * Date: Aug 2, 2010
 * Time: 3:13:53 PM
 */
public class ColumnItem
{
    Long fileRowDataRltnpId;
    Long fileRowDataId;
    Long fieldId;
    String fieldValue;

    public ColumnItem()
    {
        this.fieldId = 0L;
        this.fieldValue = "";
        this.fileRowDataId = 0L;
        this.fileRowDataRltnpId = 0L;
    }

    public ColumnItem(Long fieldId, String fieldValue, Long fileRowDataId, Long fileRowDataRltnpId)
    {
        this.fieldId = fieldId;
        this.fieldValue = fieldValue;
        this.fileRowDataId = fileRowDataId;
        this.fileRowDataRltnpId = fileRowDataRltnpId;
    }
    
    public Long getFieldId()
    {
        return fieldId;
    }

    public void setFieldId(Long fieldId)
    {
        this.fieldId = fieldId;
    }

    public Long getFileRowDataId()
    {
        return fileRowDataId;
    }

    public void setFileRowDataId(Long fileRowDataId)
    {
        this.fileRowDataId = fileRowDataId;
    }

    public String getFieldValue()
    {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue)
    {
        this.fieldValue = fieldValue;
    }

    public Long getFileRowDataRltnpId()
    {
        return fileRowDataRltnpId;
    }

    public void setFileRowDataRltnpId(Long fileRowDataRltnpId)
    {
        this.fileRowDataRltnpId = fileRowDataRltnpId;
    }
}
