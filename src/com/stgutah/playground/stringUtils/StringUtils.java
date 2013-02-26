package com.stgutah.playground.stringUtils;

import javax.xml.stream.events.Characters;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Demonstration of using the String.format().
 * <p/>
 * User: dqromney
 * Date: Jul 30, 2010
 * Time: 11:36:58 AM
 */
public class StringUtils {

    Logger log = Logger.getLogger(StringUtils.class.getName());
        
    private static final String DEFAULT_FILE_SEPARATOR = System.getProperty("file.separator");
    private String pathFile;

    public void execute() {
        System.out.println(String.format("pathFile=[%1$s] --> path=[%2$s]",
                pathFile,
                getPathFromPathFile(pathFile)));

        System.out.println(String.format("pathFile=[%1$s] --> filename=[%2$s]",
                pathFile,
                getFilenameFromPathFile(pathFile)));

        System.out.println(String.format("pathFile=[%1$s] --> path=[%2$s]",
                getFilenameFromPathFile(pathFile),
                getPathFromPathFile(getFilenameFromPathFile(pathFile))));

        System.out.println(String.format("pathFile=[%1$s] --> filename=[%2$s]",
                getPathFromPathFile(pathFile),
                getFilenameFromPathFile(getPathFromPathFile(pathFile))));

        System.out.println(String.format("pathFile=[%1$s] --> filename=[%2$s]",
                null,
                getFilenameFromPathFile(null)));

        System.out.println(String.format("pathFile=[%1$s] --> path=[%2$s]",
                null,
                getPathFromPathFile(null)));

        String messyString = "\n\tHere is a string with \tcontrol characters, \rand will be striped of them.\n";
        String cleanString = StringUtils.stripControlCharacters(messyString);
        System.out.println(String.format("MessyString -->[%1$s] --> CleanString --> [%2$s]", messyString, cleanString));

        messyString = "\n\t\t\r\n";
        cleanString = StringUtils.stripControlCharacters(messyString);
        System.out.println(String.format("MessyString -->[%1$s] --> CleanString --> [%2$s]", messyString, cleanString));

        messyString = "";
        cleanString = StringUtils.stripControlCharacters(messyString);
        System.out.println(String.format("MessyString -->[%1$s] --> CleanString --> [%2$s]", messyString, cleanString));

        messyString = null;
        cleanString = StringUtils.stripControlCharacters(messyString);
        System.out.println(String.format("MessyString -->[%1$s] --> CleanString --> [%2$s]", messyString, cleanString));

        System.out.println("");
        System.out.println("\"CDEU\".contains(\"D\")" + "= " + "CDEU".contains("D"));
        System.out.println("\"CDEU\".contains(\"F\")" + "= " + "CDEU".contains("F"));


        List<String> bannerList = new ArrayList<String>(0);
        bannerList.add("StringUtils() :: Enter");
        bannerList.add("Entering method that show off the StringUtils utility class");
        logBanner(bannerList, log, Level.INFO);

        logBanner(new String[] {"StringUtils() :: Enter", "Entering method that show off the StringUtils utility class"}, log, Level.INFO);
    }

    /**
     * Strip all control characters (char < 32) from String.
     *
     * @param pText the text string to strip
     * @return a string clean of control characters
     */
    public static String stripControlCharacters(String pText) {
        StringBuffer buffer = new StringBuffer(0);
        if (pText != null) {
            for (Character c : pText.toCharArray()) {
                if (Character.isISOControl(c)) {
                    System.out.println(String.format("Found Control Character [0x%1$02X] = [%1$02d]", new Integer(c)));
                } else {
                    buffer.append(c);
                }
            }
        }
        return buffer.toString();
    }

    /**
     * TODO Strip all control characters (char < 32) from String. ~ using regular expressions
     *
     * @param pText the text string to strip
     * @return a string clean of control characters
     */
    public static String stripControlCharactersRegExpression(String pText) {
        return null;
    }

    /**
     * Gets the path from a Path and File string.
     *
     * @param pPathFile the path and file, i.e. /home/foo/bar.txt
     * @return the path without the file name, i.e. /home/foo, if no path a './' is returned.
     */
    public String getPathFromPathFile(String pPathFile) {
        return pPathFile == null ? "." + DEFAULT_FILE_SEPARATOR :
                pPathFile.lastIndexOf(DEFAULT_FILE_SEPARATOR) == -1 ? "." + DEFAULT_FILE_SEPARATOR :
                        pPathFile.substring(0, pPathFile.lastIndexOf(DEFAULT_FILE_SEPARATOR));
    }

    /**
     * Gets the file name from a Path and File string; files must have extension.
     *
     * @param pPathFile the path and file, i.e. /home/foo/bar.txt
     * @return the file name without the path, i.e. bar.txt
     */
    public String getFilenameFromPathFile(String pPathFile) {
        return pPathFile == null ? "" :
                pPathFile.lastIndexOf(DEFAULT_FILE_SEPARATOR) == -1 ? "" :
                        pPathFile.lastIndexOf(".") == -1 ? "" :
                                pPathFile.substring(pPathFile.lastIndexOf(DEFAULT_FILE_SEPARATOR) + 1);
    }


    private static Character[] defaultHeaderCharacters = {'.', '-', '.'};
    private static Character[] defaultSpanCharacters =   {':',      ':'};
    private static Character[] defaultFooterCharacters = {'`', '-', '\''};

    /**
     * Log a banner; used at the beginning of a job or method useful for logging.
     *
     * @param pBannerList the String list items to include in the banner
     */
    public void logBanner(List<String> pBannerList, Logger pLog, Level pLogLevel) {
        int extraFormattingSpace = 4;
        int maxBannerLength = maxStringLength(pBannerList);
        String header = createBannerLine(maxBannerLength + extraFormattingSpace, defaultHeaderCharacters[0], defaultHeaderCharacters[1], defaultHeaderCharacters[2]);
        String footer = createBannerLine(maxBannerLength + extraFormattingSpace, defaultFooterCharacters[0], defaultFooterCharacters[1], defaultFooterCharacters[2]);
        logLine(pLog, pLogLevel, header);
        for(String item : pBannerList) {
            logLine(pLog, pLogLevel, String.format("%1$s %2$s %3$s", defaultSpanCharacters[0], padRight(item, maxBannerLength), defaultSpanCharacters[1]));
        }
        logLine(pLog, pLogLevel, footer);
    }

    /**
     * Log a banner; used at the beginning of a job or method useful for logging.
     *
     * @param pBannerArray the String array items to include in the banner
     */
    public void logBanner(String[] pBannerArray, Logger pLog, Level pLogLevel) {
        this.logBanner(Arrays.asList(pBannerArray), pLog, pLogLevel);
    }

    /**
     * Create a Banner line String of n length using a defined characters.
     *
     * @param pLength the length of the line
     * @param pLeftCornerChar the left-top corner character
     * @param pLineChar the line character
     * @param pRightCornerChar the right-top corner character
     * @return a header String
     */
    public String createBannerLine(int pLength, char pLeftCornerChar, char pLineChar, char pRightCornerChar) {
        StringBuffer headerBuffer = new StringBuffer();
        for(int i = 0; i < pLength; i++) {
            if(i == 0) {
                headerBuffer.append(pLeftCornerChar);
            }
            else if(i == (pLength-1)) {
                headerBuffer.append(pRightCornerChar);
            }
            else {
                headerBuffer.append(pLineChar);
            }
        }
        return headerBuffer.toString();
    }

    /**
     * Determine the maximum length of string s in list.
     *
     * @param pStringList a list of String objects
     * @return the maximum length of any one string in the list.
     */
    public int maxStringLength(List<String> pStringList) {
        int maxLength = 0;
        if(pStringList != null) {
            for(String item : pStringList) {
                maxLength = Math.max(maxLength, item.length());
            }
        }
        return maxLength;
    }

    /**
     * Pad String to its right with spaces. , i.e. "Left" padded 10 spaces would be [      Left]
     *
     * @param s the String object to pad
     * @param n the number of spaces to pad
     * @return the padded String object
     */
    public static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }

    /**
     * Pad String to its left with spaces, i.e. "Right" padded 10 spaces would be [Right     ]
     *
     * @param s the String object to pad
     * @param n the number of spaces to pad
     * @return the padded String object
     */
    public static String padLeft(String s, int n) {
        return String.format("%1$#" + n + "s", s);
    }

    /**
     * Log's a line to whatever log-level is set.
     *
     * @param pLog the Logger
     * @param pLogLevel the Logger's log Level object
     * @param pLine the String object to log
     */
    private void logLine(Logger pLog, Level pLogLevel, String pLine) {
        if(pLog.isLoggable(pLogLevel)) { pLog.log(pLogLevel, pLine); }
    }

    public void init() {
        //log.info("init() :: Enter");
        pathFile = "/home/dqromney/stgdev/playground/export.csv";
        //log.info("init() :: Exit");
    }

    /**
     * Main driver.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StringUtils main = new StringUtils();
        main.init();
        main.execute();
    }
}
