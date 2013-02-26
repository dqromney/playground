package com.stgutah.playground.opencsv.examples;

import au.com.bytecode.opencsv.CSVReader;
import org.apache.commons.lang.StringUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Integration Test Data Reader.
 *
 * User: dqromney
 * Date: Dec 9, 2011
 * Time: 11:50:39 AM
 */
public class SOIntegrationTestData {

    private final java.util.logging.Logger LOGGER = Logger.getLogger(SOIntegrationTestData.class.toString());

    final String DEFAULT_FILE_SEPARATOR = System.getProperty("file.separator");

    private CSVReader csvReader;
    private String exportDir = System.getProperty("user.dir");
    private List<SOIntegrationDataBean> soIntegrationDataBeanList = new ArrayList<SOIntegrationDataBean>(0);

    private void execute() throws IOException {
        // Read in test data
        soIntegrationDataBeanList = getSOIntegrationDataBeanList();

        // Set search by example for Service Order Identifer
        SOIdentifier soIdentifier = new SOIdentifier(32L, "FAS", 1945L, "INTERIOR SALE DATE", true, "HP_BAD_ADDRESS");

        // Find the Integration Data for test
        SOIntegrationDataBean soIntegrationDataBean = findSOTestData(soIdentifier);
        if(soIntegrationDataBean != null) {
            // Report found
            LOGGER.info(String.format("-- Found Data [%1$s]", soIntegrationDataBean.toString()));
        }

    }

    /**
     * Get Service Order Integration Data Bean list.
     *
     * @return a list of SOIntegrationDataBean objects
     * @throws IOException thrown if IO exception occurs
     */
    private List<SOIntegrationDataBean> getSOIntegrationDataBeanList() throws IOException {
        List<SOIntegrationDataBean> theList = new ArrayList<SOIntegrationDataBean>(0);
        List<String> headerList = Arrays.asList(getHeader());
        String [] nextLine;
        while ((nextLine = csvReader.readNext()) != null) {
            if(StringUtils.startsWith(nextLine[0], "#")) {
                LOGGER.info(String.format("-- Ignoring commented line [%1$s...]", nextLine[0]));
            } else {
                // nextLine[] is an array of values from the line
                SOIntegrationDataBean soIntegrationDataBean = new SOIntegrationDataBean();
                soIntegrationDataBean.setSoIndentifier(parseSoIdentifier(nextLine[headerList.indexOf(SOIntegrationDataEnum.IDENTIFIER.getRawColumnName())]));
                soIntegrationDataBean.setSoValuePairList(parseSoValuePairList(nextLine[headerList.indexOf(SOIntegrationDataEnum.DETAIL_TYPE.getRawColumnName())]));
                theList.add(soIntegrationDataBean);
                System.out.println(soIntegrationDataBean.toString());
                soIntegrationDataBean = null;
            }
        }
        return theList;
    }

    private String[] getHeader()  throws IOException {
        String [] nextLine;
        while ((nextLine = csvReader.readNext()) != null) {
            if(StringUtils.startsWith(nextLine[0], "#")) {
                LOGGER.info(String.format("-- Ignoring commented line [%1$s...]", nextLine[0]));
            } else {
                break;
            }
        }
        return nextLine;
    }

    private SOIntegrationDataBean findSOTestData(SOIdentifier pSoIdentifier) {
        SOIntegrationDataBean soIntegrationDataBean = null;
        for(SOIntegrationDataBean item : soIntegrationDataBeanList) {
            if(item.getSoIndentifier().equals(pSoIdentifier)) {
                soIntegrationDataBean = item;
                break;
            }
        }
        return soIntegrationDataBean;
    }

    /**
     * Parse Service Order Value Pair.
     *
     * @param pDetailTypePairs a Pipe delimited list of key/value pairs
     * @return a populated list of SOValuePair objects
     */
    private List<SOValuePair> parseSoValuePairList(String pDetailTypePairs) {
        String[] keyValuePair;
        List<SOValuePair> soValuePairList = new ArrayList<SOValuePair>(0);
        // Get key/value pair string for detail types
        String[] detailTypeKeyPairArray = StringUtils.split(pDetailTypePairs, '|');
        for(String keyPair : detailTypeKeyPairArray) {
            // Parse key/value string to key and value; add to array
            keyValuePair = StringUtils.split(keyPair, '=');
            if(keyValuePair != null) {
                soValuePairList.add(new SOValuePair(keyValuePair[0], keyValuePair.length == 2 ? keyValuePair[1] : ""));
            }
        }
        return soValuePairList;
    }

    /**
     * Parse Service Order Identifier record to object.
     *
     * @param pRecordIdentifier a Pipe delimited list record header information
     * @return a populated SOIdentifier object
     */
    private SOIdentifier parseSoIdentifier(String pRecordIdentifier) {
        SOIdentifier soIdentifier = new SOIdentifier();
        String[] recordHeaderArray = StringUtils.split(pRecordIdentifier, '|');
        if(recordHeaderArray == null || recordHeaderArray.length < 5) {
            LOGGER.warning(String.format("-- Undefined recordHeader items [%1$s]", pRecordIdentifier));
        } else {
            soIdentifier.setOriginator(recordHeaderArray[0]);
            soIdentifier.setClientId(new Long(recordHeaderArray[1]));
            soIdentifier.setSubClientId(new Long(recordHeaderArray[2]));
            soIdentifier.setServiceTemplateName(recordHeaderArray[3]);
            soIdentifier.setValid(new Boolean(recordHeaderArray[4]));
            soIdentifier.setTestName(recordHeaderArray[5]);
        }
        return soIdentifier;        
    }

    private void init() throws FileNotFoundException {
        exportDir = System.getProperty("user.dir");
        csvReader = new CSVReader(new FileReader(exportDir + "/src/com/stgutah/playground/opencsv/examples/SERVICE_ORDER_INTEGRATION_TEST.csv"), '~','"',0);
    }

    public static void main(String[] args) throws IOException
    {
        SOIntegrationTestData main = new SOIntegrationTestData();
        main.init();
        main.execute();
    }

}
