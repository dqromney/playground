package com.stgutah.playground.opencsv.examples;

import java.util.ArrayList;
import java.util.List;

/**
 * Service Order Integration Bean class, used in CSVReader.
 *
 * IDENTIFIER(Originator|ClientId|SubClientId|ServiceTemplateName|isValid)~SERVICE_DETAIL_TYPE_DATA
 *
 * User: dqromney
 * Date: Dec 9, 2011
 * Time: 1:28:13 PM
 */
public class SOIntegrationDataBean {

    SOIdentifier soIndentifier;
    List<SOValuePair> soValuePairList;

    public SOIntegrationDataBean() {
        soIndentifier = new SOIdentifier();
        soValuePairList = new ArrayList<SOValuePair>(0);
    }

    public SOIntegrationDataBean(SOIdentifier pSOIdentifier, List<SOValuePair> pSOValuePairList) {
        soIndentifier = pSOIdentifier == null ? new SOIdentifier() : pSOIdentifier;
        soValuePairList = pSOValuePairList == null ? new ArrayList<SOValuePair>(0) : pSOValuePairList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("SOIntegrationDataBean");
        sb.append("{soIndentifier=").append(soIndentifier.toString());
        sb.append(", soValuePairList=").append(soValuePairList.toString());
        sb.append('}');
        return sb.toString();
    }

    // ----------------------------------------------------------------
    // Accessors
    // ----------------------------------------------------------------

    public SOIdentifier getSoIndentifier() {
        return soIndentifier;
    }

    public void setSoIndentifier(SOIdentifier soIndentifier) {
        this.soIndentifier = soIndentifier;
    }

    public List<SOValuePair> getSoValuePairList() {
        return soValuePairList;
    }

    public void setSoValuePairList(List<SOValuePair> soValuePairList) {
        this.soValuePairList = soValuePairList;
    }
}
