package com.stgutah.playground.opencsv.examples;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Java Bean Read example.
 * <p/>
 * User: dqromney
 * Date: Oct 14, 2010
 * Time: 1:19:37 PM
 */
public class JavaBeansRead {

    private final java.util.logging.Logger LOGGER = Logger.getLogger(JavaBeansRead.class.toString());

    final String DEFAULT_LINE_SEPARATOR = System.getProperty("line.separator");
    final String DEFAULT_FILE_SEPARATOR = System.getProperty("file.separator");

    private String userDirectory;
    private String pathAndFile;
    private CSVReader csvReader;

    public JavaBeansRead() {

    }

    private void init() {
        userDirectory = System.getProperty("user.dir");
        pathAndFile = new String(userDirectory + "/src/com/stgutah/playground/opencsv/examples/JavaBeansRead.csv");
    }

    private void execute() {
        CSVReader csvReader = null;
        String[] columns = null;
        try {
            csvReader = new CSVReader(new FileReader(pathAndFile), ',', '\"');

            ColumnPositionMappingStrategy mappingStrategy = new ColumnPositionMappingStrategy();
            mappingStrategy.setType(UserBean.class);

            // Order the mapping in the raw column order
            columns = mapColumns(readHeader(csvReader));
            // The fields to bind in the JavaBean, i.e. UserBean object
            mappingStrategy.setColumnMapping(columns);

            CsvToBean csv = new CsvToBean();
            List<UserBean> list = csv.parse(mappingStrategy, csvReader);

            for (UserBean user : list) {
                System.out.println(user.toString());
            }

        } catch (FileNotFoundException e) {
            LOGGER.severe(MessageFormat.format("File was not found at [{0}] location", pathAndFile));
        } catch (IOException e) {
            LOGGER.severe(MessageFormat.format("File was not found at [{0}] location", pathAndFile));
        }
    }

    private List<String> readHeader(CSVReader csvReader) throws IOException {
        return Arrays.asList(csvReader.readNext());
    }

    private String[] mapColumns(List<String> rawHeaderList) {
        UserBeanEnum item;
        String[] columnsArray = new String[rawHeaderList.size()];

        int index = 0;
        for (String columnHeader : rawHeaderList) {
            item = UserBeanEnum.searchRawColumnName(columnHeader);
            if (item != null) {
                columnsArray[index++] = item.beanPropertyName;
            } else {
                LOGGER.warning("Undefined column ['" + columnHeader + "']");
            }
//
//
//            // Put them in the order the exist in the file
//            if (columnHeader.equalsIgnoreCase("user_id")) {
//                columnsArray[index++] = "userId";
//            } else if (columnHeader.equalsIgnoreCase("user_name")) {
//                columnsArray[index++] = "userName";
//            } else if (columnHeader.equalsIgnoreCase("password")) {
//                columnsArray[index++] = "password";
//            } else if (columnHeader.equalsIgnoreCase("zip_code")) {
//                columnsArray[index++] = "zipCode";
//            } else {
//                LOGGER.warning("Undefined column ['" + columnHeader + "']");
//            }
        }
        return columnsArray;
    }

    public static void main(String[] args) throws Exception {
        JavaBeansRead main = new JavaBeansRead();
        main.init();
        main.execute();
    }

}
