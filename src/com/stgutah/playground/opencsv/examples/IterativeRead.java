package com.stgutah.playground.opencsv.examples;

import au.com.bytecode.opencsv.CSVReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Iterative CSV Reader example.
 *
 * User: dqromney
 * Date: Nov 5, 2010
 * Time: 1:05:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class IterativeRead {

    final String DEFAULT_FILE_SEPARATOR = System.getProperty("file.separator");
    CSVReader csvReader;
    String exportDir = System.getProperty("user.dir");

    private void execute() throws IOException {
        List<String> headerList = Arrays.asList(csvReader.readNext());
        String [] nextLine;
        while ((nextLine = csvReader.readNext()) != null) {
            // nextLine[] is an array of values from the line
            UserBean userBean = new UserBean();
            userBean.setUserName(nextLine[headerList.indexOf("user_name")]);
            userBean.setPassword(nextLine[headerList.indexOf("password")]);
            userBean.setZipCode(nextLine[headerList.indexOf("zip_code")]);
            userBean.setUserId(new Long(nextLine[headerList.indexOf("user_Id")]));
            System.out.println(userBean.toString());
            userBean = null;
        }
    }

    private void init() throws FileNotFoundException {
        exportDir = System.getProperty("user.dir");
        csvReader = new CSVReader(new FileReader(exportDir + "/src/com/stgutah/playground/opencsv/examples/JavaBeansRead.csv"), ',','"',0);
    }

    public static void main(String[] args) throws IOException
    {
        IterativeRead main = new IterativeRead();
        main.init();
        main.execute();
    }
}
