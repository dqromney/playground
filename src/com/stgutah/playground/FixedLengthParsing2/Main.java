package com.stgutah.playground.FixedLengthParsing2;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.*;

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
    private Map<String, ConfigFileSegment> configFileSegmentMap;
    private Map<String, ConfigRecordSegment> configRecordSegmentMap;
    private List<DataSegment> dataSegmentList;
    private List<ServiceOrderLoadItem> serviceOrderLoadItemList;

    private void execute() {
        BufferedReader br = openBufferedReader(flfFile);
        bw = openBufferedWriter(outFile);
        String line;
        Integer dataRecordIndex = 0;
        System.out.println("Filename: " + flfFile.getName());

        try {
            while ((line = br.readLine()) != null) {
                ConfigFileSegment configFileSegment = getFileSegment(line);

                if (configFileSegment.isStartOfRecord() && !configFileSegment.isControlRecord()) {
                    dataRecordIndex++;
                }
                dataSegmentList = parseRecord(configRecordSegmentMap, dataSegmentList, dataRecordIndex, line);
            }
        } catch (IOException e) {
            log.error(String.format("Error reading [%1$s] file", flfFile.getPath()), e);
        }
        closeBufferedWriter(bw);
        closeBufferedReader(br);

        String record;
        for (DataSegment segment : dataSegmentList) {
            record = String.format("%1$s", segment.toString());
            System.out.println(record);
        }

    }

    private void init(String[] args) throws IOException {
        String record;
        if (args != null && args.length > 0) {
            flfFile = new File(args[0]);
        }
        outFile = new File("./configFileSegment.csv");
        bw = new BufferedWriter(new FileWriter(outFile));
        configFileSegmentMap = getConfigFileSegment();
        record = String.format("%1$s%2$s", "NAME,TOKEN,ID,DESCRIPTION,OPTIONAL,MAX_LENGTH,VARIABLE_LENGTH,ALLOWED_FREQUENCY_PER_FILE,ALLOWED_FREQUENCY_PER_RECORD_SET,IS_HEADER,IS_FOOTER,START_OF_RECORD", DEFAULT_LINE_SEPARATOR);
        bw.write(record);
        for (ConfigFileSegment seg : configFileSegmentMap.values()) {
            record = String.format("'%1$s','%2$s','%3$s','%4$s','%5$s','%6$s','%7$s','%8$s','%9$s','%10$s','%11$s','%12$s'%13$s",
                    seg.getName(), seg.getToken(), seg.getId(), seg.getDescription(), seg.getOptional(),
                    seg.getMaxLength(), seg.getVariableLength(), seg.getAllowedFrequencyPerFile(),
                    seg.getAllowedFrequencyPerRecordSet(), seg.isHeader(), seg.isFooter(), seg.isStartOfRecord(),
                    DEFAULT_LINE_SEPARATOR);
            bw.write(record);
        }
        bw.flush();
        bw.close();
        outFile = null;

        outFile = new File("./configRecordSegment.csv");
        bw = new BufferedWriter(new FileWriter(outFile));
        configRecordSegmentMap = getConfigRecordSegmentMap();
        record = String.format("%1$s%2$s", "FIELD_NAME,PICTURE,FIELD_DESCRIPTION,POSITION_START,POSITION_END,VALID_VALUES", DEFAULT_LINE_SEPARATOR);
        bw.write(record);
        for (ConfigRecordSegment seg : configRecordSegmentMap.values()) {
            record = String.format("((select id from config_file where type = 'FLF'), '%1$s','%2$s','%3$s',%4$s,%5$s,'%6$s'),%7$s",
                    seg.getFieldName(), seg.getPicture(), seg.getFieldDescription(), seg.getPositionStart(),
                    seg.getPositionEnd(), seg.getValidValues() == null ? "{}" : seg.getValidValues(),
                    DEFAULT_LINE_SEPARATOR);
            bw.write(record);
        }
        bw.flush();
        bw.close();
        outFile = null;

        dataSegmentList = new ArrayList<DataSegment>(0);

        serviceOrderLoadItemList = new ArrayList<ServiceOrderLoadItem>(0);

        outFile = new File("./outFile.txt");
    }

    private Map<String, ConfigRecordSegment> getConfigRecordSegmentMap() {
        Map<String, ConfigRecordSegment> crsMap = new HashMap<String, ConfigRecordSegment>(0);
        Properties validValues = null;

        int i = 0;
        ConfigFileSegment cfs = configFileSegmentMap.get("HDR001");
        crsMap.put("HDR-REC-ID", new ConfigRecordSegment(cfs, "HDR-REC-ID", "X(3)", "Record ID", 1, 3, validValues));
        crsMap.put("HDRVERS-NO", new ConfigRecordSegment(cfs, "HDRVERS-NO", "X(3)", "Version number of the field servicing format.", 4, 6, validValues));
        crsMap.put("HDR-FIELD-SERV-CO", new ConfigRecordSegment(cfs, "HDR-FIELD-SERV-CO", "X(11)", "TIN#.", 7, 17, validValues));
        crsMap.put("HDR-OFFICE-1", new ConfigRecordSegment(cfs, "HDR-OFFICE-1", "X(2)", "Identifies business entities within the field servicing company.", 18, 19, validValues));
        crsMap.put("HDR-MTG-CO", new ConfigRecordSegment(cfs, "HDR-MTG-CO", "X(11)", "TIN#.", 20, 30, validValues));
        crsMap.put("HDR-OFFICE-2", new ConfigRecordSegment(cfs, "HDR-OFFICE-2", "X(2)", "Identifies business entities within the mortgage company.", 31, 32, validValues));
        crsMap.put("HDR-SERVICE-BUREAU", new ConfigRecordSegment(cfs, "HDR-SERVICE-BUREAU", "X(11)", "TIN#.", 33, 43, validValues));
        crsMap.put("HDR-OFFICE-3", new ConfigRecordSegment(cfs, "HDR-OFFICE-3", "X(2)", "Identifies business entities within the service bureau.", 44, 45, validValues));
        crsMap.put("HDR-WK-ORDER-DDMMYY", new ConfigRecordSegment(cfs, "HDR-WK-ORDER-DDMMYY", "X(6)", "Date the work order is prepared by requester. Format: DDMMYY.", 46, 51, validValues));
        crsMap.put("HDR-FILLER", new ConfigRecordSegment(cfs, "HDR-FILLER", "X(99)", "Unused space.", 52, 150, validValues));

        cfs = configFileSegmentMap.get("INS01");
        crsMap.put("IN1-REC-ID", new ConfigRecordSegment(cfs, "IN1-REC-ID", "X(3)", "Value = INS.", 1, 3, validValues));
        crsMap.put("IN1-REC-CODE", new ConfigRecordSegment(cfs, "IN1-REC-CODE", "X(2)", "Value = 01.", 4, 5, validValues));
        crsMap.put("IN1-VERS-NO", new ConfigRecordSegment(cfs, "IN1-VERS-NO", "X(3)", "Version number of the field servicing format.", 6, 8, validValues));
        // TODO look at this for it's variation
        crsMap.put("IN1-LOAN-NO", new ConfigRecordSegment(cfs, "IN1-LOAN-NO", "X(15)", "Loan number of the property to be inspected.", 9, 23, validValues));
        crsMap.put("IN1-MTG-NAME", new ConfigRecordSegment(cfs, "IN1-MTG-NAME", "X(30)", "Free-form; contains mortgagor's first name, middle initial, and last name.", 24, 53, validValues));
        crsMap.put("IN1-PROP-ADDR-1", new ConfigRecordSegment(cfs, "IN1-PROP-ADDR-1", "X(30)", "Street number and name.", 54, 83, validValues));
        crsMap.put("IN1-PROP-ADDR-2", new ConfigRecordSegment(cfs, "IN1-PROP-ADDR-2", "X(30)", "Street number and name (optional).", 84, 113, validValues));
        crsMap.put("IN1-PROP-CITY", new ConfigRecordSegment(cfs, "IN1-PROP-CITY", "X(21)", "City.", 114, 134, validValues));
        crsMap.put("IN1-PROP-STATE", new ConfigRecordSegment(cfs, "IN1-PROP-STATE", "X(2)", "State.", 135, 136, validValues));
        crsMap.put("IN1-PROP-ZIP", new ConfigRecordSegment(cfs, "IN1-PROP-ZIP", "X(9)", "Zip code.", 137, 145, validValues));
        validValues = new Properties();
        validValues.put("N", "No");
        validValues.put("Y", "Yes");
        crsMap.put("IN1-BAD-PROP-ADDR-FLAG", new ConfigRecordSegment(cfs, "IN1-BAD-PROP-ADDR-FLAG", "X", "Indicates whether the property address is incorrect.", 146, 146, validValues));

        validValues = null;
        crsMap.put("IN1-FILLER", new ConfigRecordSegment(cfs, "IN1-FILLER", "X(4)", "Unused space.", 147, 150, validValues));


        cfs = configFileSegmentMap.get("INS02");
        crsMap.put("IN2-REC-ID", new ConfigRecordSegment(cfs, "IN2-REC-ID", "X(3)", "Value = INS.", 1, 3, validValues));
        crsMap.put("IN2-REC-CODE", new ConfigRecordSegment(cfs, "IN2-REC-CODE", "X(2)", "Value = 01.", 4, 5, validValues));
        crsMap.put("IN2-LOAN-NO", new ConfigRecordSegment(cfs, "IN2-LOAN-NO", "X(15)", "Loan number of the property to be inspected.", 6, 20, validValues));
        crsMap.put("IN2-MAIL-ADDR-1", new ConfigRecordSegment(cfs, "IN2-MAIL-ADDR-1", "X(30)", "1-Street number and name.", 21, 50, validValues));
        crsMap.put("IN2-MAIL-ADDR-2", new ConfigRecordSegment(cfs, "IN2-MAIL-ADDR-2", "X(30)", "2-Street number and name.", 51, 80, validValues));
        crsMap.put("IN2-MAIL-ADDR-3", new ConfigRecordSegment(cfs, "IN2-MAIL-ADDR-3", "X(30)", "3-Street number and name.", 81, 110, validValues));
        crsMap.put("IN2-MAIL-ADDR-4", new ConfigRecordSegment(cfs, "IN2-MAIL-ADDR-4", "X(30)", "4-Street number and name.", 111, 140, validValues));
        crsMap.put("IN2-FILLER", new ConfigRecordSegment(cfs, "IN2-FILLER", "X(4)", "Unused space.", 141, 150, validValues));

        cfs = configFileSegmentMap.get("INS03");
        crsMap.put("IN3-REC-ID", new ConfigRecordSegment(cfs, "IN3-REC-ID", "X(3)", "Value = INS.", 1, 3, validValues));
        crsMap.put("IN3-REC-CODE", new ConfigRecordSegment(cfs, "IN3-REC-CODE", "X(2)", "Value = 01.", 4, 5, validValues));
        crsMap.put("IN3-LOAN-NO", new ConfigRecordSegment(cfs, "IN3-LOAN-NO", "X(15)", "Loan number of the property to be inspected.", 6, 20, validValues));
        crsMap.put("IN3-MAIL-CITY", new ConfigRecordSegment(cfs, "IN3-MAIL-CITY", "X(21)", "Mailing address city.", 21, 41, validValues));
        crsMap.put("IN3-MAIL-STATE", new ConfigRecordSegment(cfs, "IN3-MAIL-STATE", "X(2)", "Mailing address city.", 42, 43, validValues));
        crsMap.put("IN3-MAIL-ZIP", new ConfigRecordSegment(cfs, "IN3-MAIL-ZIP", "X(9)", "Mailing address zip code.", 44, 52, validValues));
        validValues = new Properties();
        validValues.put("H", "Home");
        validValues.put("B", "Business");
        validValues.put("O", "Other");
        crsMap.put("IN3-1ST-PHONE", new ConfigRecordSegment(cfs, "IN3-1ST-PHONE", "X(10)", "1st phone number.", 53, 62, validValues));
        crsMap.put("IN3-1ST-PHONE-LOC", new ConfigRecordSegment(cfs, "IN3-1ST-PHONE-LOC", "X", "1st phone number location.", 63, 63, validValues));
        crsMap.put("IN3-2ND-PHONE", new ConfigRecordSegment(cfs, "IN3-2ND-PHONE", "X(10)", "1st phone number.", 64, 73, validValues));
        crsMap.put("IN3-2ND-PHONE-LOC", new ConfigRecordSegment(cfs, "IN3-2ND-PHONE-LOC", "X", "1st phone number location.", 74, 74, validValues));
        crsMap.put("IN3-3RD-PHONE", new ConfigRecordSegment(cfs, "IN3-3RD-PHONE", "X(10)", "1st phone number.", 75, 84, validValues));
        crsMap.put("IN3-3RD-PHONE-LOC", new ConfigRecordSegment(cfs, "IN3-3RD-PHONE-LOC", "X", "1st phone number location.", 85, 85, validValues));
        validValues = null;
        crsMap.put("IN3-MTG-DUE-DDMMYY", new ConfigRecordSegment(cfs, "IN3-MTG-DUE-DDMMYY", "X(6)", "1st phone number location. Format MMDDYY.", 86, 91, validValues));
        validValues = new Properties();
        validValues.put("1", "FHA");
        validValues.put("2", "VA");
        validValues.put("3", "CONV");
        validValues.put("4", "Uninsured");
        crsMap.put("IN3-GUAR-CODE", new ConfigRecordSegment(cfs, "IN3-GUAR-CODE", "X", "Insurer/guarantor of loan.", 92, 92, validValues));
        validValues = null;
        crsMap.put("IN3-GUAR-CASE-NO", new ConfigRecordSegment(cfs, "IN3-GUAR-CASE-NO", "X(14)", "Insurer case number.", 93, 106, validValues));
        crsMap.put("IN3-INV-CODE", new ConfigRecordSegment(cfs, "IN3-INV-CODE", "X(11)", "Insurer case number.", 107, 117, validValues));
        crsMap.put("IN3-INV-LOAN-NO", new ConfigRecordSegment(cfs, "IN3-INV-LOAN-NO", "X(15)", "Investor loan number of the property to be inspected.", 118, 132, validValues));
        crsMap.put("IN3-FILLER_1", new ConfigRecordSegment(cfs, "IN3-FILLER_1", "X", "Unused space.", 133, 133, validValues));
        crsMap.put("IN3-MORTGAGE-PHD-ORDER-ID", new ConfigRecordSegment(cfs, "IN3-MORTGAGE-PHD-ORDER-ID", "X(10)", "The Mortgage order ID number if you use the Mortgage PhD system and are installed on the Property Inspection Reconciliation Facility in the Mortgage PhD system.", 134, 143, validValues));
        crsMap.put("IN3-FILLER_2", new ConfigRecordSegment(cfs, "IN3-FILLER_2", "X(7)", "Unused space.", 144, 150, validValues));

        cfs = configFileSegmentMap.get("INS04");
        crsMap.put("IN4-REC-ID", new ConfigRecordSegment(cfs, "IN4-REC-ID", "X(3)", "Value = INS.", 1, 3, validValues));
        crsMap.put("IN4-REC-CODE", new ConfigRecordSegment(cfs, "IN4-REC-CODE", "X(2)", "Value = 01.", 4, 5, validValues));
        crsMap.put("IN4-LOAN-NO", new ConfigRecordSegment(cfs, "IN4-LOAN-NO", "X(15)", "Loan number of the property to be inspected.", 6, 20, validValues));
        crsMap.put("IN4-PRIN-BAL", new ConfigRecordSegment(cfs, "IN4-PRIN-BAL", "9(11)V99", "Amount of principle still owed by mortgagor on the first mortgage or independent second mortgage.", 21, 33, validValues));
        crsMap.put("IN4-LATE-CHG-BAL", new ConfigRecordSegment(cfs, "IN4-LATE-CHG-BAL", "9(9)V99", "Balance of all late chares assessed but not paid to date.", 34, 44, validValues));
        crsMap.put("IN4-MTG-PAYMT", new ConfigRecordSegment(cfs, "IN4-MTG-PAYMT", "9(9)V99", "Total amount of mortgagor's payment.", 45, 55, validValues));
        validValues = new Properties();
        validValues.put("1", "Annual");
        validValues.put("2", "Semi-annual");
        validValues.put("4", "Quarterly");
        validValues.put("12", "Monthly");
        validValues.put("24", "Semi-monthly");
        validValues.put("26", "Bi-weekly");
        crsMap.put("IN4-PAYMNT-FREQ", new ConfigRecordSegment(cfs, "IN4-PAYMNT-FREQ", "9(2)", "Numer of payments made each year.", 56, 57, validValues));
        validValues = null;
        crsMap.put("IN4-TOTAL-AMOUNT-DUE", new ConfigRecordSegment(cfs, "IN4-TOTAL-AMOUNT-DUE", "9(11)V99", "Total amount currently due from mortgagor.", 58, 70, validValues));
        crsMap.put("IN4-PROCESSOR-CODE", new ConfigRecordSegment(cfs, "IN4-PROCESSOR-CODE", "X(4)", "Alphanumeric data entered by the user in the LOAN PROCESSOR field on the PIRM screen and PIR1/REQ window in the Property Inspection Facility.", 71, 74, validValues));
        validValues = new Properties();
        validValues.put("A", "Bulk tape; mass");
        validValues.put("B", "Bulk tape; individual");
        validValues.put("C", "Bulk tape; auto-generated");
        validValues.put("D", "Telephone");
        validValues.put("E", "Fax");
        validValues.put("F", "Written request");
        validValues.put("G", "Recurring");
        crsMap.put("IN4-INSP-REQ-SOURCE", new ConfigRecordSegment(cfs, "IN4-INSP-REQ-SOURCE", "X", "Source of request.", 75, 75, validValues));
        validValues = null;
        crsMap.put("IN4-REQUESTOR-CODE", new ConfigRecordSegment(cfs, "IN4-REQUESTOR-CODE", "X(9)", "The individual requesting the inspection.", 76, 84, validValues));
        validValues = new Properties();
        validValues.put("A", "Property condition, no contract");
        validValues.put("B", "Property condition, no contract (Occupancy Verification)");
        validValues.put("C", "FNMA Form 30");
        validValues.put("D", "Delinquent interview");
        validValues.put("E", "Bankruptcy");
        validValues.put("F", "Foreclosure");
        validValues.put("G", "Sale Date");
        validValues.put("H", "Loss Draft");
        validValues.put("I", "Commercial");
        validValues.put("J", "Annual");
        validValues.put("K", "Other");
        validValues.put("L", "None");
        crsMap.put("IN4-INSP-TYPE-PROP-ADDR", new ConfigRecordSegment(cfs, "IN4-INSP-TYPE-PROP-ADDR", "X", "Type of inspection requested for the property address.", 85, 85, validValues));
        crsMap.put("IN4-INSP-TYPE-MAIL-ADDR", new ConfigRecordSegment(cfs, "IN4-INSP-TYPE-MAIL-ADDR", "X", "Type of inspection requested for the mailing address.", 86, 86, validValues));
        validValues = null;
        validValues = new Properties();
        validValues.put("0", "No photo");
        validValues.put("1", "Photo");
        validValues.put("2", "Photo, only if bad condition");
        validValues.put("3", "Photo, only if vacant");
        validValues.put("4", "Photo, only if first time vacant");
        validValues.put("5", "Photo, property, and neighborhood");
        validValues.put("6-9", "User-defined");
        crsMap.put("IN4-PHOTO-REQ", new ConfigRecordSegment(cfs, "IN4-PHOTO-REQ", "X", "Specifies whether a photo is requested with the property inspection.", 87, 87, validValues));
        validValues = null;
        crsMap.put("IN4-FC-SALE-MMDDYY", new ConfigRecordSegment(cfs, "IN4-FC-SALE-MMDDYY", "X(6)", "Date of foreclosure sale. Format: MMDDYY.", 88, 93, validValues));
        crsMap.put("IN4-REDEMP-EXPIR-MMDDYY", new ConfigRecordSegment(cfs, "IN4-REDEMP-EXPIR-MMDDYY", "X(6)", "Date redemption expires. Format: MMDDYY.", 94, 99, validValues));
        validValues = new Properties();
        validValues.put("A", "Servicer");
        validValues.put("B", "Inspection company");
        validValues.put("C", "Other");
        crsMap.put("IN4-BILL-MTGR-CODE", new ConfigRecordSegment(cfs, "IN4-BILL-MTGR-CODE", "X", "Indicates if mortgagor is billed by one of the following.", 100, 100, validValues));
        validValues = null;
        // Non-extended
        // crsMap.put("", new ConfigRecordSegment(cfs, "FILLER", "X(50)", "Total loan count.", 101, 150, validValues));
        // Extended
        validValues = new Properties();
        validValues.put("1", "First mortgage");
        validValues.put("3", "Second mortgage carried under a separate loan number.");
        crsMap.put("IN4X-HI-TYPE", new ConfigRecordSegment(cfs, "IN4X-HI-TYPE", "X", "A code that indicates whether the loan is for a first or second mortgage.", 101, 101, validValues));
        validValues = null;
        crsMap.put("IN4X-BANK", new ConfigRecordSegment(cfs, "IN4X-BANK", "X(3)", "The investor code the servicer assigns to the owner of the loan. This field can be three alphanumeric characters, with the exception of I, O, and ALL.", 102, 104, validValues));
        validValues = new Properties();
        validValues.put("F", "Fannie Mae");
        validValues.put("G", "Ginnie Mae");
        validValues.put("H", "Freddie Mac");
        validValues.put("O", "Other");
        crsMap.put("IN4X-GSE-CODE", new ConfigRecordSegment(cfs, "IN4X-GSE-CODE", "X", "A code indicating the governument-sponsored enterprise (GSE) that owns the loan.", 105, 105, validValues));
        validValues = null;
        validValues = new Properties();
        validValues.put("0", "Non-asset");
        validValues.put("1", "Asset");
        crsMap.put("IN4X-ASSET LOAN CODE", new ConfigRecordSegment(cfs, "IN4X-ASSET LOAN CODE", "X", "Indicates whether the loan is an asset loan. This field is populated only when the GSE-COE field is O (other). This comes from the ASSET LN CD field on the Investor Header Setup/Maintenance screen (IN01) in he Investor Setup Workstation.", 106, 106, validValues));
        validValues = null;
        crsMap.put("IN4X-INVESTOR NAME", new ConfigRecordSegment(cfs, "IN4X-INVESTOR NAME", "X(25)", "Free-form investor name from line 1 of the INVESTOR NAME AND ADDRESS field on the IN01 screen. This field is populated only when the GSE-CODE field is O (other).", 107, 131, validValues));
        crsMap.put("IN4X-FILLER", new ConfigRecordSegment(cfs, "IN4X-FILLER", "X(19)", "Total loan count.", 132, 150, validValues));

        cfs = configFileSegmentMap.get("INS05");
        crsMap.put("IN5-REC-ID", new ConfigRecordSegment(cfs, "IN5-REC-ID", "X(3)", "Value = INS.", 1, 3, validValues));
        crsMap.put("IN5-REC-CODE", new ConfigRecordSegment(cfs, "IN5-REC-CODE", "X(2)", "Value = 01.", 4, 5, validValues));
        crsMap.put("IN5-LOAN-NO", new ConfigRecordSegment(cfs, "IN5-LOAN-NO", "X(15)", "Loan number of the property to be inspected.", 6, 20, validValues));
        crsMap.put("IN5-SPEC-INSTRUC-1", new ConfigRecordSegment(cfs, "IN5-SPEC-INSTRUC-1", "X(23)", "Free-form instruction line.", 21, 43, validValues));
        crsMap.put("IN5-SPEC-INSTRUC-2", new ConfigRecordSegment(cfs, "IN5-SPEC-INSTRUC-2", "X(23)", "Free-form instruction line.", 44, 66, validValues));
        crsMap.put("IN5-SPEC-INSTRUC-3", new ConfigRecordSegment(cfs, "IN5-SPEC-INSTRUC-3", "X(23)", "Free-form instruction line.", 67, 89, validValues));
        crsMap.put("IN5-LAST-OCCUPANCY-STATUS", new ConfigRecordSegment(cfs, "IN5-LAST-OCCUPANCY-STATUS", "X", "Space.", 90, 90, validValues));
        crsMap.put("IN5-LAST-EXT-PROP-COND", new ConfigRecordSegment(cfs, "IN5-LAST-EXT-PROP-COND", "X", "Space.", 91, 91, validValues));
        crsMap.put("IN5-MAP-REF-PROP-ADDR", new ConfigRecordSegment(cfs, "IN5-MAP-REF-PROP-ADDR", "X(6)", "Spaces.", 92, 97, validValues));
        crsMap.put("IN5-MAP-REF-MAIL-ADDR", new ConfigRecordSegment(cfs, "IN5-MAP-REF-MAIL-ADDR", "X(6)", "Spaces.", 98, 103, validValues));
        crsMap.put("IN5-FILLER", new ConfigRecordSegment(cfs, "IN5-FILLER", "X(47)", "Total loan count.", 104, 150, validValues));

        cfs = configFileSegmentMap.get("TRL001");
        crsMap.put("TRL-REC-ID", new ConfigRecordSegment(cfs, "TRL-REC-ID", "X(3)", "Value = TRL.", 1, 3, validValues));
        crsMap.put("TRL-VERS-NO", new ConfigRecordSegment(cfs, "TRL-VERS-NO", "X(3)", "Version number of the field servicing.", 4, 6, validValues));
        crsMap.put("TRL-MTG-COMPANY", new ConfigRecordSegment(cfs, "TRL-MTG-COMPANY", "X(11)", "TIN#. Identifies the mortgage servicing.", 7, 17, validValues));
        crsMap.put("TRL-OFFICE", new ConfigRecordSegment(cfs, "TRL-OFFICE", "X(2)", "Identifies business entities within the mortgage servicing company.", 18, 19, validValues));
        crsMap.put("TRL-REC-CNT-INSP-A", new ConfigRecordSegment(cfs, "TRL-REC-CNT-INSP-A", "X(6)", "Inspection type - Property Condition.", 20, 25, validValues));
        crsMap.put("TRL-REC-CNT-INSP-B", new ConfigRecordSegment(cfs, "TRL-REC-CNT-INSP-B", "X(6)", "Inspection type - Property Condition, Verify Occupancy.", 26, 31, validValues));
        crsMap.put("TRL-REC-CNT-INSP-C", new ConfigRecordSegment(cfs, "TRL-REC-CNT-INSP-C", "X(6)", "Inspection type - Property Condition, FNMA Form 30.", 32, 37, validValues));
        crsMap.put("TRL-REC-CNT-INSP-D", new ConfigRecordSegment(cfs, "TRL-REC-CNT-INSP-D", "X(6)", "Inspection type - Delinquent.", 38, 43, validValues));
        crsMap.put("TRL-REC-CNT-INSP-E", new ConfigRecordSegment(cfs, "TRL-REC-CNT-INSP-E", "X(6)", "Inspection type - Bankruptcy.", 44, 49, validValues));
        crsMap.put("TRL-REC-CNT-INSP-F", new ConfigRecordSegment(cfs, "TRL-REC-CNT-INSP-F", "X(6)", "Inspection type - Foreclosure.", 50, 55, validValues));
        crsMap.put("TRL-REC-CNT-INSP-G", new ConfigRecordSegment(cfs, "TRL-REC-CNT-INSP-G", "X(6)", "Inspection type - Sale Date.", 56, 61, validValues));
        crsMap.put("TRL-REC-CNT-INSP-H", new ConfigRecordSegment(cfs, "TRL-REC-CNT-INSP-H", "X(6)", "Inspection type - Loss Draft.", 62, 67, validValues));
        crsMap.put("TRL-REC-CNT-INSP-I", new ConfigRecordSegment(cfs, "TRL-REC-CNT-INSP-I", "X(6)", "Inspection type - Commercial.", 68, 73, validValues));
        crsMap.put("TRL-REC-CNT-INSP-J", new ConfigRecordSegment(cfs, "TRL-REC-CNT-INSP-J", "X(6)", "Inspection type - Annual.", 74, 79, validValues));
        crsMap.put("TRL-REC-CNT-INSP-K", new ConfigRecordSegment(cfs, "TRL-REC-CNT-INSP-K", "X(6)", "Inspection type - Other.", 80, 85, validValues));
        crsMap.put("TRL-GRAND-TOT-ALL-INSP", new ConfigRecordSegment(cfs, "TRL-GRAND-TOT-ALL-INSP", "X(9)", "Total for all inspections.", 86, 94, validValues));
        crsMap.put("TRL-TOT-LOAN-CNT", new ConfigRecordSegment(cfs, "TRL-TOT-LOAN-CNT", "X(9)", "Total loan count.", 96, 103, validValues));
        crsMap.put("TRL-FILLER", new ConfigRecordSegment(cfs, "TRL-FILLER", "X(47)", "Total loan count.", 104, 150, validValues));

        for (ConfigRecordSegment segment : crsMap.values()) {
            System.out.println(String.format("crs=[%1$s]", segment.toString()));
        }
        return crsMap;
    }

    /**
     * Get Configuration File Segments, could be DB or file read call.
     *
     * @return a Map, key=token+id, of ConfigFileSegment objects
     */
    private Map<String, ConfigFileSegment> getConfigFileSegment() {
        Map<String, ConfigFileSegment> cfgMap = new HashMap<String, ConfigFileSegment>(0);

        // Can be read in by file or database
        int i = 0;
        cfgMap.put("HDR001", new ConfigFileSegment("Header", "HDR", "001", "MSP Header (only one per file)", 150L, Boolean.FALSE, SegmentFrequency.ONCE, SegmentFrequency.ZERO, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE));

        cfgMap.put("INS01", new ConfigFileSegment("Detail", "INS", "01", "Inspection Detail Record 01 (once per record)", 150L, Boolean.FALSE, SegmentFrequency.UNLIMITED, SegmentFrequency.ONCE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE));
//        cfgMap.get("HDR001").setNextSegment(cfgMap.get("INS01"));
//        cfgMap.get("INS01").setPrevSegment(cfgMap.get("HDR001"));

        cfgMap.put("INS02", new ConfigFileSegment("Detail", "INS", "02", "Inspection Detail Record 02 (once per record)", 150L, Boolean.FALSE, SegmentFrequency.UNLIMITED, SegmentFrequency.ONCE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE));
//        cfgMap.get("INS01").setNextSegment(cfgMap.get("INS02"));
//        cfgMap.get("INS02").setPrevSegment(cfgMap.get("INS01"));

        cfgMap.put("INS03", new ConfigFileSegment("Detail", "INS", "03", "Inspection Detail Record 03 (once per record)", 150L, Boolean.FALSE, SegmentFrequency.UNLIMITED, SegmentFrequency.ONCE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE));
//        cfgMap.get("INS02").setNextSegment(cfgMap.get("INS03"));
//        cfgMap.get("INS03").setPrevSegment(cfgMap.get("INS02"));

        cfgMap.put("INS04", new ConfigFileSegment("Detail", "INS", "04", "Inspection Detail Record 04 (once per record)", 150L, Boolean.FALSE, SegmentFrequency.UNLIMITED, SegmentFrequency.ONCE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE));
//        cfgMap.get("INS03").setNextSegment(cfgMap.get("INS04"));
//        cfgMap.get("INS04").setPrevSegment(cfgMap.get("INS03"));

        cfgMap.put("INS05", new ConfigFileSegment("Detail", "INS", "05", "Inspection Detail Record 05 (once per record)", 150L, Boolean.FALSE, SegmentFrequency.UNLIMITED, SegmentFrequency.ONCE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE));
//        cfgMap.get("INS04").setNextSegment(cfgMap.get("INS05"));
//        cfgMap.get("INS05").setPrevSegment(cfgMap.get("INS04"));

        cfgMap.put("TRL001", new ConfigFileSegment("Trailer", "TRL", "001", "MSP Trailer (once per file)", 150L, Boolean.FALSE, SegmentFrequency.ONCE, SegmentFrequency.ZERO, Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE));
//        cfgMap.get("INS05").setNextSegment(cfgMap.get("TRL001"));
//        cfgMap.get("TRL001").setPrevSegment(cfgMap.get("INS05"));
//        int maxSize = i;
//        i = 0;
//        while(i < maxSize) {
//            // Set previous and next segments
//            cfgMap.get(i).setNextSegment(null);
//            cfgMap.get(i).setPrevSegment(null);
//            if((i + 1) < maxSize) {
//                cfgMap.get(i).setNextSegment(cfgMap.get(i+1));
//            }
//
//            if((i - 1) <= 0) {
//                cfgMap.get(i).setPrevSegment(null);
//            } else {
//                cfgMap.get(i).setPrevSegment(cfgMap.get(i-1));
//            }
//            System.out.println(String.format("cfs=[%1$s]", cfgMap.get(i).toString()));
//            i ++;
//        }
        return cfgMap;
    }

    /**
     * Parse FLF record based upon ConfigFileSegment.
     *
     * @param pRecordSegmentMap the RecordSegmentMap
     * @param pLine the line of the record
     * @return a list of DataSegment objects
     */
    private List<DataSegment> parseRecord(Map<String, ConfigRecordSegment> pRecordSegmentMap, List<DataSegment> pDataSegment, Integer pRecordIndex, String pLine) {
        Integer recordIndex = pRecordIndex;
        for (ConfigRecordSegment segment : pRecordSegmentMap.values()) {
            // Record index not used for Control (Header or Footer) records
            recordIndex = segment.getConfigFileSegment().isControlRecord() ? 0 : pRecordIndex;
            // Find the ConfigFileSegment token and id
            if (pLine.contains(segment.getConfigFileSegment().getToken() + segment.getConfigFileSegment().getId())) {
                pDataSegment.add(new DataSegment(segment, recordIndex, pLine.substring(segment.getPositionStart() - 1, segment.getPositionEnd())));
            }
        }
        return pDataSegment;
    }

    /**
     * Get the Configured File Segment for a line.
     *
     * @param pLine the line read from the fixed length format file
     * @return the ConfigFileSegment of the line, null if unrecognizable.
     */
    private ConfigFileSegment getFileSegment(String pLine) {
        ConfigFileSegment configFileSegment = null;
        for (ConfigFileSegment segment : configFileSegmentMap.values()) {
            if (pLine.startsWith(segment.getToken() + segment.getId())) {
                configFileSegment = segment;
                break;
            }
        }
        return configFileSegment;
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
                log.error(String.format("Error, uable to write file [%1$s]", pFile.getPath()));
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
