package com.stgutah.playground.FixedLengthParsing2;

/**
 * Data Segment.
 *
 * User: dqromney
 * Date: 6/21/12
 * Time: 10:16 AM
 */
public class DataSegment {
    private ConfigRecordSegment configRecordSegment;
    private Integer recordIndex;
    private String value;

    // ----------------------------------------------------------------
    // Constructor
    // ----------------------------------------------------------------

    public DataSegment() {
        this.configRecordSegment = new ConfigRecordSegment();
        this.recordIndex = 1;
        this.value = "UNDEFINED";
    }

    public DataSegment(ConfigRecordSegment configRecordSegment, Integer recordIndex, String value) {
        this.configRecordSegment = configRecordSegment;
        this.recordIndex = recordIndex;
        this.value = value;
    }

    public DataSegment(DataSegment dataSegment) {
        this.configRecordSegment = dataSegment.getConfigRecordSegment();
        this.recordIndex = dataSegment.getRecordIndex();
        this.value = dataSegment.getValue();
    }

    // ----------------------------------------------------------------
    // Utility Methods
    // ----------------------------------------------------------------

    // ----------------------------------------------------------------
    // Accessors
    // ----------------------------------------------------------------

    public ConfigRecordSegment getConfigRecordSegment() {
        return configRecordSegment;
    }

    public void setConfigRecordSegment(ConfigRecordSegment configRecordSegment) {
        this.configRecordSegment = configRecordSegment;
    }

    public Integer getRecordIndex() {
        return recordIndex;
    }

    public void setRecordIndex(Integer recordIndex) {
        this.recordIndex = recordIndex;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("DataSegment");
        sb.append("{configRecordSegment=").append(configRecordSegment);
        sb.append(", recordIndex=").append(recordIndex);
        sb.append(", value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
