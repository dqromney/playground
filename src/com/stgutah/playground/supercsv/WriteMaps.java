package com.stgutah.playground.supercsv;

import org.supercsv.io.CsvMapWriter;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Write a Map object to a bar.csv file.
 *
 * User: dqromney
 * Date: Oct 13, 2010
 * Time: 5:31:25 PM
 */
public class WriteMaps {

    private String[] header;
    private String defaultDirectory;
    private String pathAndFile;
    ICsvMapWriter writer;
    CsvPreference csvPreference;


    public WriteMaps() {
        defaultDirectory = System.getProperty("user.dir");
        pathAndFile = defaultDirectory + "/src/com/stgutah/playground/supercsv/bar.csv";
        csvPreference = CsvPreference.STANDARD_PREFERENCE;
    }

    public WriteMaps(String pathAndFile, CsvPreference csvPreference) {
        this.pathAndFile = pathAndFile;
        this.csvPreference = csvPreference;
    }

    private void init() throws IOException {
        setCsvPreference(CsvPreference.NO_COMMENT_PREFERENCE);
        writer = new CsvMapWriter(new FileWriter(this.getPathAndFile()), this.getCsvPreference());
    }

    private void execute() throws IOException {

        setHeader(new String[]{"name", "city", "zip"});
        
        try {
            // set up some data to write
            final HashMap<String, ? super Object> data1 = new HashMap<String, Object>();
            data1.put(header[0], "Karl");
            data1.put(header[1], "Tent city,IA");
            data1.put(header[2], 5565);
            final HashMap<String, ? super Object> data2 = new HashMap<String, Object>();
            data2.put(header[0], "Banjo");
            data2.put(header[1], "River side,CA");
            data2.put(header[2], 5551);
            // the actual writing
            writer.writeHeader(header);
            writer.write(data1, header); // <--- Handles commas.
            writer.write(data2, header);
        } finally {
            writer.close();
        }
    }

    public static void main(String[] args) throws Exception
    {
        WriteMaps main = new WriteMaps();
        main.init();
        main.execute();
    }

    // ----------------------------------------------------------------
    // ACCESSORS
    // ----------------------------------------------------------------
    public String getDefaultDirectory() {
        return defaultDirectory;
    }

    public void setDefaultDirectory(String defaultDirectory) {
        this.defaultDirectory = defaultDirectory;
    }

    public String[] getHeader() {
        return header;
    }

    public void setHeader(String[] header) {
        this.header = header;
    }

    public String getPathAndFile() {
        return pathAndFile;
    }

    public void setPathAndFile(String pathAndFile) {
        this.pathAndFile = pathAndFile;
    }

    public ICsvMapWriter getWriter() {
        return writer;
    }

    public void setWriter(ICsvMapWriter writer) {
        this.writer = writer;
    }

    public CsvPreference getCsvPreference() {
        return csvPreference;
    }

    public void setCsvPreference(CsvPreference csvPreference) {
        this.csvPreference = csvPreference;
    }
}
