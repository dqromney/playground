package com.stgutah.playground.reflection;

import java.util.*;

public class Detail {

	//Indicates which detail value is to be displayed.
	public static enum ServiceDetailValueType{APPROVED_VALUE,PRE_APPROVED_VALUE};

	public static final String COPY_PREAPPROVED_DETAILS_FOR_SERVICE = "copyPreApprovedDetailsForService";

    private String name;

    private String value;

    private String detailTemplateId;

    private String preApprovedValue;

    private String groupName;

    private Integer sortOrder;

    private String inputLabel;

    private String inputInstructions;

    private String inputType;

    private String inputOptions;

    private String reportLabel;

    // private Service service;

    private DetailType detailType;

    /**
     * Constructor
     */
    public Detail(){}

    // ----------------------------------------------------------------

    // ----------------------------------------------------------------
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setValues(List<String> values){
		this.value = "";
		if(values == null)
			return;
		int counter =0;
		for(String v:values){
			if(counter == 0)
				value += v;
			else
				value += "," + v;

			counter++;
		}
	}

	public List<String> getValues(){
		if(this.value != null)
			return Arrays.asList(value.split(","));
		else
			return Collections.emptyList();
	}

	public String getDetailTemplateId() {
		return detailTemplateId;
	}

	public void setDetailTemplateId(String detailTemplateId) {
		this.detailTemplateId = detailTemplateId;
	}

	public void setPreApprovedValues(List<String> preApprovedValues){
		this.preApprovedValue = "";
		if(preApprovedValues == null)
			return;
		int counter =0;
		for(String v:preApprovedValues){
			if(counter == 0)
				preApprovedValue += v;
			else
				preApprovedValue += "," + v;

			counter++;
		}
	}

	public List<String> getPreApprovedValues(){
		if(this.preApprovedValue != null)
			return Arrays.asList(preApprovedValue.split(","));
		else
			return Collections.emptyList();
	}

	public void copyPreApprovedValue(){
		setValue(getPreApprovedValue());
	}



	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Integer getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}

	public String getInputLabel() {
		return inputLabel;
	}

	public void setInputLabel(String inputLabel) {
		this.inputLabel = inputLabel;
	}

	public String getInputInstructions() {
		return inputInstructions;
	}

	public void setInputInstructions(String inputInstructions) {
		this.inputInstructions = inputInstructions;
	}

	public String getInputType() {
		return inputType;
	}

	public void setInputType(String inputType) {
		this.inputType = inputType;
	}

	public String getInputOptions() {
		return inputOptions;
	}

	public void setInputOptions(String inputOptions) {
		this.inputOptions = inputOptions;
	}

	//This is a method specifically written for the reporting. (Service Request).
	public List<Map<String, Object>> getInputOptionsMap(){
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(inputType != null && !inputType.equals("input")){
			for(String option:inputOptions.split(",")){
				Map<String,Object> optionMap  = new HashMap<String, Object>();
				optionMap.put("value", option);
				list.add(optionMap);
			}
		}
		return list;
	}

	public Integer getMaxOptionLength(){
		if(inputOptions == null || inputType.equals("input"))
			return 0;

		Integer maxLength = 0;
		for(String option:inputOptions.split(",")){
			Integer length = option.length();
			if(length > maxLength)
				maxLength = length;
		}

		return maxLength;
	}

	public List<String> getInputOptionsList(){
		return Arrays.asList(inputOptions.split(","));
	}

	public String getInputOptionsNoCommas(){
		String options = null;
		if(inputOptions != null){
			options = inputOptions.replaceAll(",", "  ");
		}
		return options;
	}

	public String getReportLabel() {
		return reportLabel;
	}

	public void setReportLabel(String reportLabel) {
		this.reportLabel = reportLabel;
	}

//	public void setService(Service service) {
//		this.service = service;
//	}
//
//	public Service getService() {
//		return service;
//	}

	public void setPreApprovedValue(String preApprovedValue) {
		this.preApprovedValue = preApprovedValue;
	}

	public String getPreApprovedValue() {
		return preApprovedValue;
	}

	public void setDetailType(DetailType detailType) {
		this.detailType = detailType;
	}

	public DetailType getDetailType() {
		return detailType;
	}

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Detail");
        sb.append("{name='").append(name).append('\'');
        sb.append(", value='").append(value).append('\'');
        sb.append(", detailTemplateId='").append(detailTemplateId).append('\'');
        sb.append(", preApprovedValue='").append(preApprovedValue).append('\'');
        sb.append(", groupName='").append(groupName).append('\'');
        sb.append(", sortOrder=").append(sortOrder);
        sb.append(", inputLabel='").append(inputLabel).append('\'');
        sb.append(", inputInstructions='").append(inputInstructions).append('\'');
        sb.append(", inputType='").append(inputType).append('\'');
        sb.append(", inputOptions='").append(inputOptions).append('\'');
        sb.append(", reportLabel='").append(reportLabel).append('\'');
        sb.append(", detailType=").append(detailType);
        sb.append('}');
        return sb.toString();
    }



}