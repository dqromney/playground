package com.stgutah.playground.opencsv.examples;

/**
 * Service Order Identifier Bean.
 *
 * IDENTIFIER(Originator|ClientId|SubClientId|ServiceTemplateName|isValid)~SERVICE_DETAIL_TYPE_DATA
 *
 * User: dqromney
 * Date: Dec 9, 2011
 * Time: 12:28:38 PM
 */
public class SOIdentifier {

    String originator;
    Long clientId;
    Long subClientId;
    String serviceTemplateName;
    Boolean isValid;
    String testName;

    public SOIdentifier() {
        this.clientId = -1L;
        isValid = true;
        this.originator = "UNDEFINED";
        this.serviceTemplateName = "UNDEFINED";
        this.subClientId = -1L;
        this.testName = "UNDEFINED";
    }

    public SOIdentifier(Long clientId, String originator, Long subClientId, String serviceTemplateName, Boolean valid, String testName) {
        this.clientId = clientId;
        isValid = valid;
        this.originator = originator;
        this.serviceTemplateName = serviceTemplateName;
        this.subClientId = subClientId;
        this.testName = testName;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    public String getOriginator() {
        return originator;
    }

    public void setOriginator(String originator) {
        this.originator = originator;
    }

    public String getServiceTemplateName() {
        return serviceTemplateName;
    }

    public void setServiceTemplateName(String serviceTemplateName) {
        this.serviceTemplateName = serviceTemplateName;
    }

    public Long getSubClientId() {
        return subClientId;
    }

    public void setSubClientId(Long subClientId) {
        this.subClientId = subClientId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SOIdentifier that = (SOIdentifier) o;

        if (!clientId.equals(that.clientId)) return false;
        if (!isValid.equals(that.isValid)) return false;
        if (!originator.equals(that.originator)) return false;
        if (!serviceTemplateName.equals(that.serviceTemplateName)) return false;
        if (!subClientId.equals(that.subClientId)) return false;
        if (!testName.equals(that.testName)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = originator.hashCode();
        result = 31 * result + clientId.hashCode();
        result = 31 * result + subClientId.hashCode();
        result = 31 * result + serviceTemplateName.hashCode();
        result = 31 * result + isValid.hashCode();
        result = 31 * result + testName.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("SOIdentifier");
        sb.append("{clientId=").append(clientId);
        sb.append(", originator='").append(originator).append('\'');
        sb.append(", subClientId=").append(subClientId);
        sb.append(", serviceTemplateName='").append(serviceTemplateName).append('\'');
        sb.append(", isValid=").append(isValid);
        sb.append(", testName='").append(testName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
