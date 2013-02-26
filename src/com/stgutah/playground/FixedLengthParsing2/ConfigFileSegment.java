package com.stgutah.playground.FixedLengthParsing2;

/**
 * Configure the File Structure.
 *
 * User: dqromney
 * Date: 6/19/12
 * Time: 11:02 AM
 */
public class ConfigFileSegment {
    // Segment meta-data
    private String name; // Header, Trailer, Detail, Fees, etc...
    private String token; // HDR, TRL, INS, FEE, etc...
    private String id; // Identifier or Version 01,02,03,001,...
    private String description; // Segment is for file Header information.
    private Boolean optional = Boolean.FALSE;
//    private ConfigFileSegment prevSegment; // What is the previous Segment, null for top segment.
//    private ConfigFileSegment nextSegment; // What is the next Segment, null for bottom segment.

    // Control used for validation
    private Long maxLength; // MaxNumber characters in record
    private Boolean variableLength; // Record can be of variable length
    private SegmentFrequency allowedFrequencyPerFile;
    private SegmentFrequency allowedFrequencyPerRecordSet;
    private Boolean header; // Is this segment a Header?
    private Boolean footer; // Is this segment a Footer?
    private Boolean startOfRecord;

    // ----------------------------------------------------------------
    // Constructors
    // ----------------------------------------------------------------
    public ConfigFileSegment() {
        this("Segment", "SEG", "01", "Segment description", 150L, Boolean.FALSE,
                SegmentFrequency.ONCE, SegmentFrequency.ZERO,
                Boolean.FALSE,
//                null, null,
                Boolean.FALSE, Boolean.FALSE,
                Boolean.TRUE);
    }

    public ConfigFileSegment(String name, String token, String id, String description, Long maxLength, Boolean variableLength,
                             SegmentFrequency allowedFrequencyPerFile, SegmentFrequency allowedFrequencyPerRecordSet,
                             Boolean optional,
//                             ConfigFileSegment prevSegment, ConfigFileSegment nextSegment,
                             Boolean header, Boolean footer,
                             Boolean startOfRecord) {
        this.name = name;
        this.token = token;
        this.id = id;
        this.description = description;
        this.maxLength = maxLength;
        this.variableLength = variableLength;
        this.allowedFrequencyPerFile = allowedFrequencyPerFile;
        this.allowedFrequencyPerRecordSet = allowedFrequencyPerRecordSet;
        this.optional = optional;
//        this.prevSegment = prevSegment;
//        this.nextSegment = nextSegment;
        this.header = header;
        this.footer = footer;
        this.startOfRecord = startOfRecord;
    }

    public ConfigFileSegment(ConfigFileSegment pConfigFileSegment) {
        this(pConfigFileSegment.getName(),
                pConfigFileSegment.getToken(),
                pConfigFileSegment.getId(),
                pConfigFileSegment.getDescription(),
                pConfigFileSegment.getMaxLength(),
                pConfigFileSegment.getVariableLength(),
                pConfigFileSegment.getAllowedFrequencyPerFile(),
                pConfigFileSegment.getAllowedFrequencyPerRecordSet(),
                pConfigFileSegment.getOptional(),
//                pConfigFileSegment.getPrevSegment(),
//                pConfigFileSegment.getNextSegment(),
                pConfigFileSegment.isHeader(),
                pConfigFileSegment.isFooter(),
                pConfigFileSegment.isStartOfRecord());
    }


    // ----------------------------------------------------------------
    // Utility methods
    // ----------------------------------------------------------------
//    public Boolean isTop() {
//        return prevSegment == null;
//    }
//
//    public Boolean isBottom() {
//        return nextSegment == null;
//    }

    public Boolean isControlRecord() {
        return header || footer;
    }

    // ----------------------------------------------------------------
    // Accessors
    // ----------------------------------------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getOptional() {
        return optional;
    }

    public void setOptional(Boolean optional) {
        this.optional = optional;
    }

    public Long getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Long maxLength) {
        this.maxLength = maxLength;
    }

    public Boolean getVariableLength() {
        return variableLength;
    }

    public void setVariableLength(Boolean variableLength) {
        this.variableLength = variableLength;
    }

    public SegmentFrequency getAllowedFrequencyPerFile() {
        return allowedFrequencyPerFile;
    }

    public void setAllowedFrequencyPerFile(SegmentFrequency allowedFrequencyPerFile) {
        this.allowedFrequencyPerFile = allowedFrequencyPerFile;
    }

    public SegmentFrequency getAllowedFrequencyPerRecordSet() {
        return allowedFrequencyPerRecordSet;
    }

    public void setAllowedFrequencyPerRecordSet(SegmentFrequency allowedFrequencyPerRecordSet) {
        this.allowedFrequencyPerRecordSet = allowedFrequencyPerRecordSet;
    }

//    public ConfigFileSegment getNextSegment() {
//        return nextSegment;
//    }
//
//    public void setNextSegment(ConfigFileSegment nextSegment) {
//        this.nextSegment = nextSegment;
//    }
//
//    public ConfigFileSegment getPrevSegment() {
//        return prevSegment;
//    }
//
//    public void setPrevSegment(ConfigFileSegment prevSegment) {
//        this.prevSegment = prevSegment;
//    }

    public Boolean isHeader() {
        return header;
    }

    public void setHeader(Boolean header) {
        this.header = header;
    }

    public Boolean isFooter() {
        return footer;
    }

    public void setFooter(Boolean footer) {
        this.footer = footer;
    }

    public Boolean isStartOfRecord() {
        return startOfRecord;
    }

    public void setStartOfRecord(Boolean startOfRecord) {
        this.startOfRecord = startOfRecord;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ConfigFileSegment");
        sb.append("{name='").append(name).append('\'');
        sb.append(", token='").append(token).append('\'');
        sb.append(", id='").append(id).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", maxLength=").append(maxLength);
        sb.append(", variableLength=").append(variableLength);
        sb.append(", allowedFrequencyPerFile=").append(allowedFrequencyPerFile);
        sb.append(", allowedFrequencyPerRecordSet=").append(allowedFrequencyPerRecordSet);
        sb.append(", optional=").append(optional);
//        sb.append(", prevSegment=").append(prevSegment == null ? "TOP" : prevSegment.getToken() + prevSegment.getId());
//        sb.append(", nextSegment=").append(nextSegment == null ? "BOT" : nextSegment.getToken() + nextSegment.getId());
        sb.append(", startOfRecord=").append(startOfRecord);
        sb.append('}');
        return sb.toString();
    }
}
