package com.stgutah.playground.stringUtils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Date;
import java.util.logging.Logger;
import java.util.Calendar;
import java.text.MessageFormat;

/**
 * Demonstration of using the String.format().
 * <p/>
 * User: dqromney
 * Date: Jul 30, 2010
 * Time: 11:36:58 AM
 */
public class StringFormat
{

    Logger log = Logger.getLogger(StringFormat.class.getName());

    public void execute()
    {
        //log.info("execute() :: Enter");

//        String exportFileName = String.format("exportFileRecords-%tY%tm%td-%tT.%tL", Calendar.getInstance());
        String exportFileName = String.format("exportFileRecords-%1$tY%1$tm%1$td-%1$tH%1$tM%1$tS_%1$tL", new Date());
        log.info(MessageFormat.format("exportFileName=[{0}]", exportFileName));
        //log.info("execute() :: Exit");

        exportFileName = String.format("Results_%1$ty%1$tm%1$td%1$tH%1$tM%1$tS", Calendar.getInstance());
        log.info(MessageFormat.format("exportFileName=[{0}]", exportFileName));


/**
 * The format specifiers for general, character, and numeric types have the following syntax:
   %[argument_index$][flags][width][.precision]conversion

The optional argument_index is a decimal integer indicating the position of the argument in the argument list. The first argument is referenced by "1$", the second by "2$", etc.

The optional flags is a set of characters that modify the output format. The set of valid flags depends on the conversion.

The optional width is a non-negative decimal integer indicating the minimum number of characters to be written to the output.

The optional precision is a non-negative decimal integer usually used to restrict the number of characters. The specific behavior depends on the conversion.

The required conversion is a character indicating how the argument should be formatted. The set of valid conversions for a given argument depends on the argument's data type.

  */
        Long count = 1L;
        log.info(String.format("Pad [%1$d] to [%2$09d]", count, count));

        log.info(String.format("Pad [%1$d] to [%2$-9d]", count, count));

        BigDecimal feesDue = new BigDecimal(12.34561);
        String feesDueString = feesDue.toString();
        int decimalIndex = feesDueString.indexOf(".");
        log.info(String.format("Pad [%1$f] to [%2$06dV%3$s]", feesDue, feesDue.longValue(), feesDueString.substring(decimalIndex+1, decimalIndex+3)));

        Double amount = new Double(123423452.34561);
        System.out.println(String.format("amount=[%1$f] formatted amount=[%1$.2f]", amount));

        amount = new Double(0);
        System.out.println(String.format("amount=[%1$f] formatted amount=[%1$.2f]", amount));

        amount = null;
        System.out.println(String.format("amount=[%1$f] formatted amount=[%2$.2f]", amount, amount == null ? 0 : amount));

        Double maxLength = Math.pow(2,16);
        System.out.println(String.format("maxLength=[%1$d]", maxLength.longValue()));

    }

    public void init()
    {
        //log.info("init() :: Enter");

        //log.info("init() :: Exit");
    }

    /**
     * Main driver.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        StringFormat main = new StringFormat();
        main.init();
        main.execute();
    }
}
