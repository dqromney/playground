package com.stgutah.playground.opencsv.examples;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Try out OpenCSV to write an array.
 * <p/>
 * User: dqromney
 * Date: Aug 2, 2010
 * Time: 1:47:43 PM
 */
public class JavaBeansWrite
{
    private CsvUtil csvUtil;
    private List<List<ColumnItem>> listOfColumnItemLists = new ArrayList<List<ColumnItem>>(0);
    private List<String> headerList = new ArrayList<String>(0);
    String exportDir = ".";
    private Character fieldSeparator = CSVWriter.DEFAULT_SEPARATOR;
    public JavaBeansWrite()
    {
    }

    void init()
    {
        headerList.add("*ONE*");
        headerList.add("*TWO*");
        headerList.add("*THREE*");

        // 1st Row
        ColumnItem columnItem1 = new ColumnItem(32L, "one", 10L, 1L);
        ColumnItem columnItem2 = new ColumnItem(21L, "two", 10L, 2L);
        ColumnItem columnItem3 = new ColumnItem(33L, "three", 10L, 3L);

        List<ColumnItem> columnItemList = new ArrayList<ColumnItem>(0);
        columnItemList.add(columnItem1);
        columnItemList.add(columnItem2);
        columnItemList.add(columnItem3);

        listOfColumnItemLists.add(columnItemList);

        // 2nd Row
        columnItem1 = new ColumnItem(32L, "ene", 11L, 4L);
        columnItem2 = new ColumnItem(21L, "owt", 11L, 5L);
        columnItem3 = new ColumnItem(33L, "eerht", 11L, 6L);

        columnItemList = new ArrayList<ColumnItem>(0);
        columnItemList.add(columnItem1);
        columnItemList.add(columnItem2);
        columnItemList.add(columnItem3);

        listOfColumnItemLists.add(columnItemList);

        // 3rd Row
        columnItem1 = new ColumnItem(32L, "OnE", 12L, 7L);
        columnItem2 = new ColumnItem(21L, "TwO", 12L, 8L);
        columnItem3 = new ColumnItem(33L, "ThreE,Three", 12L, 9L);

        columnItemList = new ArrayList<ColumnItem>(0);
        columnItemList.add(columnItem1);
        columnItemList.add(columnItem2);
        columnItemList.add(columnItem3);

        listOfColumnItemLists.add(columnItemList);
    }

    private void execute() throws IOException
    {
        final String DEFAULT_LINE_SEPARATOR = System.getProperty("line.separator");
        final String DEFAULT_FILE_SEPARATOR = System.getProperty("file.separator");

        exportDir = System.getProperty("user.dir");
        // Any of these will work...
        // csvUtil = new CsvUtil();
        csvUtil = new CsvUtil(exportDir + DEFAULT_FILE_SEPARATOR + "export.csv");
        // csvUtil = new CsvUtil(".", "export", "csv");
        // csvUtil = new CsvUtil(exportDir, "export", "csv");

        csvUtil.setSuppressEscape(true);
        File theFile = csvUtil.writeCsvFile(headerList, listOfColumnItemLists);

        BufferedReader br = new BufferedReader(new FileReader(theFile));
        String line;

        while((line = br.readLine()) != null)
        {
            System.out.printf("%s" + DEFAULT_LINE_SEPARATOR, line);
        }
//        FileInputStream input = new FileInputStream(theFile);
//
//        int data = input.read();
//        OutputStream output = new FileOutputStream(theFile);
//        while(data != -1)
//        {
//            output.write(data);
//            data = input.read();
//        }
//        output.close();
    }


    public static void main(String[] args) throws IOException
    {
        JavaBeansWrite main = new JavaBeansWrite();
        main.init();
        main.execute();
    }
}
