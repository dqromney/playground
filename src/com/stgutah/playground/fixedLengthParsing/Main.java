package com.stgutah.playground.fixedLengthParsing;

import com.stgutah.playground.FixedLengthParsing2.ConfigFileSegment;
import com.stgutah.playground.FixedLengthParsing2.SegmentFrequency;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Development harness to parse fixed Length field fields.
 * <p/>
 * User: dqromney
 * Date: Dec 23, 2010
 * Time: 5:09:24 PM
 */
public class Main {

    private static Logger log = Logger.getLogger(Main.class);

    final String DEFAULT_LINE_SEPARATOR = System.getProperty("line.separator");

    private File flfFile;
    private File outFile;
    private FileWriter fw;
    private BufferedWriter bw;
    private List<ConfigFileSegment> configFileSegmentList;

    private void execute() {
        BufferedReader br = openBufferedReader(flfFile);
        bw = openBufferedWriter(outFile);
        String line;
        Integer detailRecordCode = 0;

        System.out.println("Filename: " + flfFile.getName());

        try {
            while ((line = br.readLine()) != null) {
                if(getRecordType(line).compareTo(RecordType.HEADER) == 0) {
                    bw.write(String.format("%1$s\n", parseHeaderRecord(line).toString()));
                    System.out.println(parseHeaderRecord(line).toString());
                } else if(getRecordType(line).compareTo(RecordType.DETAIL) == 0) {
                    detailRecordCode = getDetailRecordCode(line);
                    if(detailRecordCode == 1) {
                        bw.write(String.format("%1$s\n", parseDetailRecord(line).getInsRecord01().toString()));
                        System.out.println(parseDetailRecord(line).getInsRecord01().toString());
                    } else if(detailRecordCode == 2) {
                        bw.write(String.format("%1$s\n", parseDetailRecord(line).getInsRecord02().toString()));
                        System.out.println(parseDetailRecord(line).getInsRecord02().toString());
                    } else if(detailRecordCode == 3) {
                        bw.write(String.format("%1$s\n", parseDetailRecord(line).getInsRecord03().toString()));
                        System.out.println(parseDetailRecord(line).getInsRecord03().toString());
                    } else if(detailRecordCode == 4) {
                        bw.write(String.format("%1$s\n", parseDetailRecord(line).getInsRecord04().toString()));
                        System.out.println(parseDetailRecord(line).getInsRecord04().toString());
                    } else if(detailRecordCode == 5) {
                        bw.write(String.format("%1$s\n", parseDetailRecord(line).getInsRecord05().toString()));
                        System.out.println(parseDetailRecord(line).getInsRecord05().toString());
                    }
                } else if(getRecordType(line).compareTo(RecordType.TRAILER) == 0) {
                    bw.write(String.format("%1$s\n", parseTrailerRecord(line).toString()));
                    System.out.println(parseTrailerRecord(line).toString());
                }
                // System.out.printf("[%s] %s" + DEFAULT_LINE_SEPARATOR, getRecordType(line), line);
            }
        } catch (IOException e) {
            log.error(String.format("Error reading [%1$s] file", flfFile.getPath()), e);
        }
        closeBufferedWriter(bw);
        closeBufferedReader(br);
    }

    private void init(String[] args) throws IOException {
        if (args != null && args.length > 0) {
            flfFile = new File(args[0]);
        }
        outFile = new File("./outFile.txt");

        bw = new BufferedWriter(new FileWriter(outFile));
        // Can be read in by file or database
        configFileSegmentList = new ArrayList<ConfigFileSegment>(0);
        int i = 1;
        configFileSegmentList.add(i++, new ConfigFileSegment("Header", "HDR", "01", "MSP Header (only one per file)", 150L, Boolean.FALSE, SegmentFrequency.ONCE, SegmentFrequency.ZERO, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.TRUE));
        configFileSegmentList.add(i++, new ConfigFileSegment("Detail", "INS", "01", "Inspection Record 01 (once per record)", 150L, Boolean.FALSE, SegmentFrequency.UNLIMITED, SegmentFrequency.ONCE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE));
        configFileSegmentList.add(i++, new ConfigFileSegment("Detail", "INS", "02", "Inspection Record 02 (once per record)", 150L, Boolean.FALSE, SegmentFrequency.UNLIMITED, SegmentFrequency.ONCE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE));
        configFileSegmentList.add(i++, new ConfigFileSegment("Detail", "INS", "03", "Inspection Record 03 (once per record)", 150L, Boolean.FALSE, SegmentFrequency.UNLIMITED, SegmentFrequency.ONCE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE));
        configFileSegmentList.add(i++, new ConfigFileSegment("Detail", "INS", "04", "Inspection Record 04 (once per record)", 150L, Boolean.FALSE, SegmentFrequency.UNLIMITED, SegmentFrequency.ONCE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE));
        configFileSegmentList.add(i++, new ConfigFileSegment("Detail", "INS", "05", "Inspection Record 05 (once per record)", 150L, Boolean.FALSE, SegmentFrequency.UNLIMITED, SegmentFrequency.ONCE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE));
        configFileSegmentList.add(i++, new ConfigFileSegment("Trailer", "TRL", "01", "MSP Trailer (once per record)", 150L, Boolean.FALSE, SegmentFrequency.UNLIMITED, SegmentFrequency.ONCE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE));

//        while(-- i > 0) {
//            configFileSegmentList.get(i).setNextSegment(configFileSegmentList.get(i+1));
//            configFileSegmentList.get(i).setPrevSegment(configFileSegmentList.get(i-1));
//            System.out.println(String.format("cfs=[%1$s]", configFileSegmentList.get(i)));
//        }

    }

    private DetailRecord parseDetailRecord(String pLine) {
        DetailRecord dr = new DetailRecord();
        String recordCode = pLine.substring(3, 5);
        int fIndex = 0;
        if(recordCode.compareTo("01") == 0) {
            dr.getInsRecord01().setRecordId(pLine.substring(fIndex, fIndex += 3));
            dr.getInsRecord01().setRecordCode(pLine.substring(fIndex, fIndex += 2));
            dr.getInsRecord01().setVersion(pLine.substring(fIndex, fIndex += 3));
            dr.getInsRecord01().setLoanNumber(pLine.substring(fIndex, fIndex += 15));
            // fIndex += 2; // Filler
            dr.getInsRecord01().setMortgageName(pLine.substring(fIndex, fIndex += 30));
            dr.getInsRecord01().setPropertyAddressLine1(pLine.substring(fIndex, fIndex += 30));
            dr.getInsRecord01().setPropertyAddressLine2(pLine.substring(fIndex, fIndex += 30));
            dr.getInsRecord01().setPropertyCity(pLine.substring(fIndex, fIndex += 21));
            dr.getInsRecord01().setPropertyState(pLine.substring(fIndex, fIndex += 2));
            dr.getInsRecord01().setPropertyZip(pLine.substring(fIndex, fIndex += 9));
            dr.getInsRecord01().setBadPropertyAddressFlag(pLine.substring(fIndex, fIndex += 1));
            dr.getInsRecord01().setFiller(pLine.substring(fIndex, fIndex += 4));

        } if(recordCode.compareTo("02") == 0) {
            dr.getInsRecord02().setRecordId(pLine.substring(fIndex, fIndex += 3));
            dr.getInsRecord02().setRecordCode(pLine.substring(fIndex, fIndex += 2));
            dr.getInsRecord02().setLoanNumber(pLine.substring(fIndex, fIndex += 15));
            // fIndex += 2; // Filler
            dr.getInsRecord02().setMailAddressLine1(pLine.substring(fIndex, fIndex += 30));
            dr.getInsRecord02().setMailAddressLine2(pLine.substring(fIndex, fIndex += 30));
            dr.getInsRecord02().setMailAddressLine3(pLine.substring(fIndex, fIndex += 30));
            dr.getInsRecord02().setMailAddressLine4(pLine.substring(fIndex, fIndex += 30));
            dr.getInsRecord02().setFiller(pLine.substring(fIndex, fIndex += 10));

        } if(recordCode.compareTo("03") == 0) {
            dr.getInsRecord03().setRecordId(pLine.substring(fIndex, fIndex += 3));
            dr.getInsRecord03().setRecordCode(pLine.substring(fIndex, fIndex += 2));
            dr.getInsRecord03().setLoanNumber(pLine.substring(fIndex, fIndex += 15));
            // fIndex += 2; // Filler
            dr.getInsRecord03().setMailCity(pLine.substring(fIndex, fIndex += 21));
            dr.getInsRecord03().setMailState(pLine.substring(fIndex, fIndex += 2));
            dr.getInsRecord03().setMailZip(pLine.substring(fIndex, fIndex += 9));
            dr.getInsRecord03().setPhone1(pLine.substring(fIndex, fIndex += 10));
            dr.getInsRecord03().setPhone1Location(pLine.substring(fIndex, fIndex += 1)); // H=Home, B=Business, O=Other
            dr.getInsRecord03().setPhone2(pLine.substring(fIndex, fIndex += 10));
            dr.getInsRecord03().setPhone2Location(pLine.substring(fIndex, fIndex += 1));  // H=Home, B=Business, O=Other
            dr.getInsRecord03().setPhone3(pLine.substring(fIndex, fIndex += 10));
            dr.getInsRecord03().setPhone3Location(pLine.substring(fIndex, fIndex += 1));  // H=Home, B=Business, O=Other
            dr.getInsRecord03().setMortgageDueDate(pLine.substring(fIndex, fIndex += 6)); // Format DDMMYY
            dr.getInsRecord03().setInsurerCode(pLine.substring(fIndex, fIndex += 1)); // 1 = FHA 2 = VA 3 = CONV 4 = Uninsured
            dr.getInsRecord03().setInsurerCaseNumber(pLine.substring(fIndex, fIndex += 14));
            dr.getInsRecord03().setInvestorId(pLine.substring(fIndex, fIndex += 11));
            dr.getInsRecord03().setInvestorLoanNumber(pLine.substring(fIndex, fIndex += 15));
            dr.getInsRecord03().setFiller1(pLine.substring(fIndex, fIndex += 1));
            dr.getInsRecord03().setMortgagePhdOrderId(pLine.substring(fIndex, fIndex += 10));
            dr.getInsRecord03().setFiller2(pLine.substring(fIndex, fIndex += 7));

        } if(recordCode.compareTo("04") == 0) {
            dr.getInsRecord04().setRecordId(pLine.substring(fIndex, fIndex += 3));
            dr.getInsRecord04().setRecordCode(pLine.substring(fIndex, fIndex += 2));
            dr.getInsRecord04().setLoanNumber(pLine.substring(fIndex, fIndex += 15));
            // fIndex += 2; // Filler
            dr.getInsRecord04().setPrincipleBalance(pLine.substring(fIndex, fIndex += (11+2))); // 11 + 2
            dr.getInsRecord04().setLateChargeBalance(pLine.substring(fIndex, fIndex += (9+2))); // 9 + 2
            dr.getInsRecord04().setMortgagePayment(pLine.substring(fIndex, fIndex += (9+2))); // 9 + 2
            dr.getInsRecord04().setPaymentFrequency(pLine.substring(fIndex, fIndex += 2));
            dr.getInsRecord04().setTotalAmountDue(pLine.substring(fIndex, fIndex += (11+2))); // 11 + 2
            dr.getInsRecord04().setProcessorCode(pLine.substring(fIndex, fIndex += 4));

            // TODO Question; Don't know what this is? It is not in the specification
//            dr.getInsRecord04().setUNDEFINED_DATA(pLine.substring(fIndex, fIndex += 8));

            dr.getInsRecord04().setInspectionSourceRequest(pLine.substring(fIndex, fIndex += 1));
            dr.getInsRecord04().setRequestorCode(pLine.substring(fIndex, fIndex += 9));
            dr.getInsRecord04().setInspectionTypeForPropertyAddress(pLine.substring(fIndex, fIndex += 1));
            dr.getInsRecord04().setInspectionTypeForMailingAddress(pLine.substring(fIndex, fIndex += 1));
            dr.getInsRecord04().setPhotoRequested(pLine.substring(fIndex, fIndex += 1));
            dr.getInsRecord04().setForeclosureSaleDate(pLine.substring(fIndex, fIndex += 6));
            dr.getInsRecord04().setRedemptionExpireDate(pLine.substring(fIndex, fIndex += 6));
            dr.getInsRecord04().setMortgageBilledBy(pLine.substring(fIndex, fIndex += 1));
            // INS04 Additional Details
            if (fIndex < pLine.length()) {
                dr.getInsRecord04().setMortgageType(pLine.substring(fIndex, fIndex += 1));
                dr.getInsRecord04().setMortgageBankCode(pLine.substring(fIndex, fIndex += 3));
                dr.getInsRecord04().setGseCode(pLine.substring(fIndex, fIndex += 1));
                dr.getInsRecord04().setAssetLoanCode(pLine.substring(fIndex, fIndex += 1));
                dr.getInsRecord04().setInvestorName(pLine.substring(fIndex, fIndex += 25));
                dr.getInsRecord04().setFiller(pLine.substring(fIndex, fIndex += 19));
            }
            else {
                dr.getInsRecord04().setMortgageType(StringUtils.repeat(" ", 1));
                dr.getInsRecord04().setMortgageBankCode(StringUtils.repeat(" ", 3));
                dr.getInsRecord04().setGseCode(StringUtils.repeat(" ", 1));
                dr.getInsRecord04().setAssetLoanCode(StringUtils.repeat(" ", 1));
                dr.getInsRecord04().setInvestorName(StringUtils.repeat(" ", 25));
                dr.getInsRecord04().setFiller(StringUtils.repeat(" ", 19));
            }

        } if(recordCode.compareTo("05") == 0) {
            dr.getInsRecord05().setRecordId(pLine.substring(fIndex, fIndex += 3));
            dr.getInsRecord05().setRecordCode(pLine.substring(fIndex, fIndex += 2));
            dr.getInsRecord05().setLoanNumber(pLine.substring(fIndex, fIndex += 15));
            //fIndex += 2; // Filler
            dr.getInsRecord05().setSpecialInstructions1(pLine.substring(fIndex, fIndex += 23));
            dr.getInsRecord05().setSpecialInstructions2(pLine.substring(fIndex, fIndex += 23));
            dr.getInsRecord05().setSpecialInstructions3(pLine.substring(fIndex, fIndex += 23));
            dr.getInsRecord05().setLastOccupancyStatus(pLine.substring(fIndex, fIndex += 1));
            dr.getInsRecord05().setLastExternalPropertyCondition(pLine.substring(fIndex, fIndex += 1));
            dr.getInsRecord05().setMapRefPropertyAddress(pLine.substring(fIndex, fIndex += 6));
            dr.getInsRecord05().setMapRefMailingAddress(pLine.substring(fIndex, fIndex += 6));
            // TODO Question; There is some data in here; '029' no in the specification
            dr.getInsRecord05().setFiller(pLine.substring(fIndex, fIndex += 47));
        }
        return dr;
    }

    private Integer getDetailRecordCode(String pLine) {
        int fIndex = 3;
        return new Integer(pLine.substring(fIndex, fIndex += 2));
    }

    /**
     * Parse Fixed Length Header Record line into HeaderRecord object.
     *
     * @param pLine the Header line
     * @return a populated HeaderRecord object
     */
    private HeaderRecord parseHeaderRecord(String pLine) {
        HeaderRecord hr = new HeaderRecord();
        int fIndex = 0;
        hr.setRecordId(pLine.substring(fIndex, fIndex += 3));
        hr.setVersion(pLine.substring(fIndex, fIndex += 3));
        hr.setFieldServiceCompanyId(pLine.substring(fIndex, fIndex += 11));
        hr.setFieldServiceCompanyOfficeId(pLine.substring(fIndex, fIndex += 2));
        hr.setMortgageCompanyId(pLine.substring(fIndex, fIndex += 11));
        hr.setMortgageCompanyOfficeId(pLine.substring(fIndex, fIndex += 2));
        hr.setServiceBureauId(pLine.substring(fIndex, fIndex += 11));
        hr.setServiceBureauOfficeId(pLine.substring(fIndex, fIndex += 2));
        hr.setWorkOrderDate(pLine.substring(fIndex, fIndex += 6)); // format DDMMYY
        hr.setFiller(pLine.substring(fIndex, fIndex += 99));
        return hr;
    }

    /**
     * Parse Fixed Length Trailer Record line into TrailerRecord object.
     * @param pLine the Trailer line
     * @return a populated TrailerRecord object 
     */
    private TrailerRecord parseTrailerRecord(String pLine) {
        TrailerRecord tr = new TrailerRecord();
        // startIndex is inclusive while endIndex is exclusive.
        int fIndex = 0;
        tr.setRecordId(pLine.substring(fIndex, fIndex += 3));
        tr.setVersion(pLine.substring(fIndex, fIndex += 3));
        tr.setMortgageCompanyId(pLine.substring(fIndex, fIndex += 11));
        tr.setMortgageCompanyOfficeId(pLine.substring(fIndex, fIndex += 2));
        tr.setTypeAInspectionCount(pLine.substring(fIndex, fIndex += 6));
        tr.setTypeBInspectionCount(pLine.substring(fIndex, fIndex += 6));
        tr.setTypeCInspectionCount(pLine.substring(fIndex, fIndex += 6));
        tr.setTypeDInspectionCount(pLine.substring(fIndex, fIndex += 6));
        tr.setTypeEInspectionCount(pLine.substring(fIndex, fIndex += 6));
        tr.setTypeFInspectionCount(pLine.substring(fIndex, fIndex += 6));
        tr.setTypeGInspectionCount(pLine.substring(fIndex, fIndex += 6));
        tr.setTypeHInspectionCount(pLine.substring(fIndex, fIndex += 6));
        tr.setTypeIInspectionCount(pLine.substring(fIndex, fIndex += 6));
        tr.setTypeJInspectionCount(pLine.substring(fIndex, fIndex += 6));
        tr.setTypeKInspectionCount(pLine.substring(fIndex, fIndex += 6));
        tr.setTotalInspectionCount(pLine.substring(fIndex, fIndex += 9));
        tr.setTotalLoanCount(pLine.substring(fIndex, fIndex += 9));
        tr.setFiller(pLine.substring(fIndex, fIndex += 47));
        return tr;
    }

    /**
     * Get the RecordType enum for a line.
     *
     * @param pLine the line read from the fixed length format file
     * @return the RecordType of the line, UNDEFINED if unrecognizable.
     */
    private RecordType getRecordType(String pLine) {
        RecordType rt = RecordType.UNDEFINED;
        if(pLine.startsWith(RecordType.HEADER.getValue())) {
            rt = RecordType.HEADER;
        } else if (pLine.startsWith(RecordType.DETAIL.getValue())) {
            rt = RecordType.DETAIL;
        } else if (pLine.startsWith(RecordType.TRAILER.getValue())) {
            rt = RecordType.TRAILER;
        }
        return rt;
    }

    /**
     * Open file for buffered reading.
     *
     * @param pFile the File object to open
     * @return null if not able to open, otherwise the BufferedReader object
     */
    private BufferedReader openBufferedReader(File pFile) {
        BufferedReader br = null;
        if (pFile.exists()) {
            try {
                br = new BufferedReader(new FileReader(pFile));
            } catch (FileNotFoundException e) {
                log.error(String.format("Error, file [%1$s] does not exist", pFile.getPath()));
            }
        } else {
            log.error(String.format("Error, file [%1$s] does not exist", pFile.getPath()));
        }
        return br;
    }

    /**
     * Close opened BufferedReader object.
     *
     * @param pBufferedReader the BufferedReader object to close
     */
    private void closeBufferedReader(BufferedReader pBufferedReader) {
        if (pBufferedReader != null) {
            try {
                pBufferedReader.close();
            } catch (IOException e) {
                log.error(String.format("Error closing opened for buffered reading"), e);
            }
        }
    }

    /**
     * Open file for buffered writing.
     *
     * @param pFile the File object to open
     * @return null if not able to open, otherwise the BufferedWriter object
     */
    private BufferedWriter openBufferedWriter(File pFile) {
        BufferedWriter bw = null;
        if (pFile.exists()) {
            try {
                bw = new BufferedWriter(new FileWriter(pFile));
            } catch (FileNotFoundException e) {
                log.error(String.format("Error, file [%1$s] does not exist", pFile.getPath()));
            } catch (IOException e) {
                log.error(String.format("Error, unable to write file [%1$s]", pFile.getPath()));
            }
        }
        return bw;
    }

    /**
     * Close opened BufferedWriter object.
     *
     * @param pBufferedWriter the BufferedReader object to close
     */
    private void closeBufferedWriter(BufferedWriter pBufferedWriter) {
        if (pBufferedWriter != null) {
            try {
                pBufferedWriter.close();
            } catch (IOException e) {
                log.error(String.format("Error closing opened for buffered writing"), e);
            }
        }
    }


    /**
     * Main driver.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.init(args);
        main.execute();
    }
}
