package com.stgutah.playground.BigDecimalExamples;

import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Big Decimal Rounding example.
 * <p/>
 * User: dqromney
 * Date: Jan 7, 2011
 * Time: 11:14:50 AM
 */
public class BigDecimalRounding {
    private static Logger log = Logger.getLogger(BigDecimalRounding.class);

    MathContext mc = null;

    private void execute() {
        mc = new MathContext(8); // Significant digits
        RoundingMode rm = RoundingMode.HALF_EVEN;

        BigDecimal obj_0 = new BigDecimal(.050).setScale(2, rm), obj_1 = new BigDecimal(40.022).setScale(2, rm);

        System.out.println("obj_0 value : " + obj_0);
        System.out.println("method generated " + "result : " + obj_0.round(mc));
        String cobolString = String.format("[%1$08.2f]", obj_0);
        System.out.println("COBOL 9(6)V99 formatted string " + "result : " + formatCobol8W2PBigDecimal(obj_0));

        System.out.println("obj_1 value : " + obj_1);
        System.out.println("method generated result" + " : " + obj_1.round(mc));
        cobolString = String.format("[%1$08.2f]", obj_1.round(mc));
        System.out.println("COBOL 9(6)V99 formatted string " + "result : " + formatCobol8W2PBigDecimal(obj_1));

        obj_0 = new BigDecimal(0.1220).setScale(2, rm);
        obj_1 = new BigDecimal(.636).setScale(2, rm);

        System.out.println("\nobj_0 value : " + obj_0);
        System.out.println("method generated result" + " : " + obj_0.round(mc));
        cobolString = String.format("[%1$08.2f]", obj_0.round(mc));
        System.out.println("COBOL 9(6)V99 formatted string " + "result : " + formatCobol8W2PBigDecimal(obj_0));

        System.out.println("obj_1 value : " + obj_1);
        System.out.println("method generated result" + " : " + obj_1.round(mc));
        cobolString = String.format("[%1$08.2f]", obj_1.round(mc));
        System.out.println("COBOL 9(6)V99 formatted string " + "result : " + formatCobol8W2PBigDecimal(obj_1));

        obj_0 = new BigDecimal(5.0).setScale(2, rm);
        obj_1 = new BigDecimal(-1.0).setScale(2, rm);

        System.out.println("\nobj_0 value : " + obj_0);
        System.out.println("method generated result" + " : " + obj_0.round(mc));
        cobolString = String.format("[%1$08.2f]", obj_0.round(mc));
        System.out.println("COBOL 9(6)V99 formatted string " + "result : " + formatCobol8W2PBigDecimal(obj_0));

        System.out.println("obj_1 value : " + obj_1);
        System.out.println("method generated result" + " : " + obj_1.round(mc));
        cobolString = String.format("[%1$08.2f]", obj_1.round(mc));
        System.out.println("COBOL 9(6)V99 formatted string " + "result : " + formatCobol8W2PBigDecimal(obj_1));

        obj_0 = new BigDecimal(99999.0).setScale(2, rm);
        obj_1 = new BigDecimal(0.00002).setScale(2, rm);

        System.out.println("\nobj_0 value : " + obj_0);
        System.out.println("method generated result" + " : " + obj_0.round(mc));
        System.out.println("COBOL 9(6)V99 formatted string " + "result : " + formatCobol8W2PBigDecimal(obj_0));

        System.out.println("obj_1 value : " + obj_1);
        System.out.println("method generated result" + " : " + obj_1.round(mc));
        System.out.println("COBOL 9(6)V99 formatted string " + "result : " + formatCobol8W2PBigDecimal(obj_1));

        String a9_6V99 = "";
        System.out.println(String.format("COBOL 9(6)V99 String [%1$s] to BigDecimal [%2$.2f]", a9_6V99, formatCobol9_6V99String(a9_6V99)));

        a9_6V99 = "-0001223";
        System.out.println(String.format("COBOL 9(6)V99 String [%1$s] to BigDecimal [%2$.2f]", a9_6V99, formatCobol9_6V99String(a9_6V99)));

        a9_6V99 = "000001223";
        System.out.println(String.format("COBOL 9(6)V99 String [%1$s] to BigDecimal [%2$.2f]", a9_6V99, formatCobol9_6V99String(a9_6V99)));

        a9_6V99 = "1223";
        System.out.println(String.format("COBOL 9(6)V99 String [%1$s] to BigDecimal [%2$.2f]", a9_6V99, formatCobol9_6V99String(a9_6V99)));

        a9_6V99 = "00001223";
        System.out.println(String.format("COBOL 9(6)V99 String [%1$s] to BigDecimal [%2$.2f]", a9_6V99, formatCobol9_6V99String(a9_6V99)));


        String a9_6 = "";
        System.out.println(String.format("COBOL 9(6) String [%1$s] to Integer [%2$d]", a9_6, formatCobol9_6String(a9_6)));

        a9_6 = "-0001223";
        System.out.println(String.format("COBOL 9(6) String [%1$s] to Integer [%2$d]", a9_6, formatCobol9_6String(a9_6)));

        a9_6 = "000001223";
        System.out.println(String.format("COBOL 9(6) String [%1$s] to Integer [%2$d]", a9_6, formatCobol9_6String(a9_6)));

        a9_6 = "1223";
        System.out.println(String.format("COBOL 9(6) String [%1$s] to Integer [%2$d]", a9_6, formatCobol9_6String(a9_6)));

        a9_6 = "00001223";
        System.out.println(String.format("COBOL 9(6) String [%1$s] to Integer [%2$d]", a9_6, formatCobol9_6String(a9_6)));

    }

    private String formatCobol8W2PBigDecimal(BigDecimal pDecimalNumber) {
        if(pDecimalNumber.compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("-- Error,  unexpected negative number, setting to zero: " +  pDecimalNumber);
            pDecimalNumber = BigDecimal.ZERO;

        }
        return String.format("%1$08.2f", pDecimalNumber).replace('.', 'V');
    }

    /**
     * Convert String with 9(6)V99 Cobol Format to BigDecimal object.
     * Can be null or empty, negatives set to zero, more than 8 characters are set to zero.
     *
     * @param p9_6_V99 the String with 9(6)V99 format, i.e. '00001223'
     * @return a BigDecimal object representing 9(6)V99 format
     */
    private BigDecimal formatCobol9_6V99String(String p9_6_V99) {
        BigDecimal number = BigDecimal.ZERO;

        if (p9_6_V99 == null || p9_6_V99.length() == 0) {
            log.warn("-- Error, unexpected null value, setting to zero: " + p9_6_V99);
            number = BigDecimal.ZERO;
        }
        else if (p9_6_V99.contains("-")) {
            log.warn("-- Error,  unexpected negative sign in string, setting to zero: " + p9_6_V99);
            number = BigDecimal.ZERO;
        } else if (p9_6_V99.length() > 8) {
            log.warn("-- Error, string too large for field, setting to zero: " + p9_6_V99);
            number = BigDecimal.ZERO;
        } else {
            number = new BigDecimal(p9_6_V99).divide(new BigDecimal(100));
        }

        return number;
    }

    /**
     * Convert String with 9_6 Cobol Format to Integer object.
     * Can be null or empty, negatives set to zero, more than 8 characters are set to zero.
     *
     * @param p9_6 theString with 9(6) format, i.e. '001223'
     * @return a Integer object representing the 9(6) format
     */
    private Integer formatCobol9_6String(String p9_6) {
        Integer number = 0;
        if (p9_6 == null || p9_6.length() == 0) {
            log.warn("-- Error, unexpected null value, setting to zero: " + p9_6);
        }
        else if (p9_6.contains("-")) {
            log.warn("-- Error,  unexpected negative sign in string, setting to zero: " + p9_6);
        } else if (p9_6.length() > 8) {
            log.warn("-- Error, string too large for field, setting to zero: " + p9_6);
        } else {
            number = Integer.parseInt(p9_6);
        }

        return number;
    }

    private void init() {
    }

    /**
     * Main driver.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BigDecimalRounding main = new BigDecimalRounding();
        main.init();
        main.execute();
    }

}
