package com.stgutah.playground.fileCompare;

import difflib.Delta;
import difflib.DiffUtils;
import difflib.Patch;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

/**
 * FileCompare using JLibDiff.
 * <p/>
 * User: dqromney
 * Date: 2/25/13
 * Time: 7:53 AM
 */
public class FileDiff {

    Logger log = Logger.getLogger(FileDiff.class.getName());
    private static String lineSeparator = System.getProperty("line.separator");

    private List<String> original = null;
    private List<String> revised = null;
    private List<String> deltaList = new ArrayList<String>(0);

    private String TARGET_FILE_NAME = "ActivePropertiesDelta.csv";
    private static String FILE_HEADER = "\"LoanNumber\",\"PreservationPropertyID\",\"TMPropertyID\",\"PreservationStatus\",\"TenantManagedMStatus\",\"PreservationAddress1\",\"PreservationAddress2\",\"PreservationCity\",\"PreservationState\",\"PreservationZipCode\",\"TenantManagedAddress1\",\"TenantManagedAddress2\",\"TenantManagedCity\",\"TenantManagedState\",\"TenanatManagedZipCode\"";

    public void execute() throws IOException {
        log.info("execute() :: Enter");

        // Compute diff. Get the Patch object. Patch is the container for computed deltas.
        Patch patch = DiffUtils.diff(original, revised);

//        Iterator<Delta> it = patch.getDeltas().listIterator();
//        while(it.hasNext()) {deltaList.add((it.next().getRevised().getLines().get(0).toString()));}

        for (Delta delta : patch.getDeltas()) {
            if(!delta.getRevised().getLines().isEmpty()) {
                deltaList.add(delta.getRevised().getLines().get(0).toString());
            }
        }

        // Save to delta file.
        saveDelta(TARGET_FILE_NAME, deltaList);

        log.info("execute() :: Exit");
    }

    public void init(String[] args) {
        log.info("init() :: Enter");

        original = fileToLines(args[0]);
        revised = fileToLines(args[1]);

        log.info("init() :: Exit");
    }

    // Helper method for get the file content
    private static List<String> fileToLines(String filename) {
        List<String> lines = new LinkedList<String>();
        String line = "";
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            while ((line = in.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println(String.format("-- Error reading file [%1$s] with exception [%2$s].", filename, e.toString()));
        }
        return lines;
    }

    // Helper method to create and save delta file.
    private static void saveDelta(String pDeltaFileName, List<String> pDeltaList) throws IOException {
        FileWriter fw = new FileWriter(pDeltaFileName, false);

        System.out.println(String.format("Creating [%1$s] file...", pDeltaFileName));
        System.out.println(String.format("Writing out: %1$s", FILE_HEADER));
        fw.write(FILE_HEADER);
        fw.write(lineSeparator);
        for(String line : pDeltaList) {
            System.out.println(String.format("Writing out: %1$s", line));
            fw.write(line);
            fw.append(lineSeparator);
        }
        fw.close();

    }

    /**
     * Main driver.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        FileDiff main = new FileDiff();
        main.init(args);
        main.execute();
    }
}
