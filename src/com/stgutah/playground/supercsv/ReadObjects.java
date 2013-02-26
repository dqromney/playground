package com.stgutah.playground.supercsv;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.StrMinMax;
import org.supercsv.cellprocessor.constraint.Unique;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Read an UserBean object from foo.csv file.
 *
 * User: dqromney
 * Date: Oct 13, 2010
 * Time: 4:59:08 PM
 */
public class ReadObjects {
    String defaultDirectory;
    String pathAndFile;
    ICsvBeanReader inFile;

    public ReadObjects() {
        defaultDirectory = System.getProperty("user.dir");
        pathAndFile = defaultDirectory + "/src/com/stgutah/playground/supercsv/foo.csv";
    }

    static final CellProcessor[] userProcessors = new CellProcessor[]{
            new Unique(new StrMinMax(5, 20)),
            new StrMinMax(8, 35),
            new ParseDate("dd/MM/yyyy"),
            new Optional(new ParseInt()),
            null
    };

    private void execute() throws IOException {
        try {
            final String[] header = inFile.getCSVHeader(true);
            UserBean user;
            while ((user = inFile.read(UserBean.class, header, userProcessors)) != null) {
                System.out.println(user.getZip());
            }
        } finally {
            inFile.close();
        }
    }

    private void init() throws FileNotFoundException {
        inFile = new CsvBeanReader(new FileReader(this.pathAndFile), CsvPreference.EXCEL_PREFERENCE);
    }

    public static void main(String[] args) throws IOException
    {
        ReadObjects main = new ReadObjects();
        main.init();
        main.execute();
    }
}
