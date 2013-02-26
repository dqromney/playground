package com.stgutah.playground.regx;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Using Regular Expressions Example.
 * <p/>
 * Note(s):
 * The Java standard regular expression library supports the following metacharacters: ([{\^$|)?*+.
 * User: dqromney
 * Date: May 21, 2010
 * Time: 3:22:55 PM
 */
public class Main
{
    Logger log = Logger.getLogger(Main.class.getName());

    public void execute()
    {
        //log.info("execute() :: Enter");

        List<String> matchStringList = null;

        // Find a word 'dog' Pattern: dog
        matchStringList = test("dog",
                "The big dog was off the leash and bit the little dog.");
        for(String item : matchStringList) {
            System.out.println(MessageFormat.format("matchString=[{0}]", item));
        }

        // Email address Pattern: [a-zA-Z]*@[a-zA-Z]*\.[a-zA-Z]*
        matchStringList = test("[a-zA-Z0-9]*@[a-zA-Z0-9]*\\.[a-zA-Z]*",
                "My email is dqromney@gmail.com, and Karla's is suqidge@yahoo.com and has been for years.");
        for(String item : matchStringList) {
            System.out.println(MessageFormat.format("matchString=[{0}]", item));
        }

        // URL Pattern: http://[a-zA-Z]*\.[a-zA-Z]*\.[a-zA-Z]*
        matchStringList = test("http://[a-zA-Z.]*[a-zA-Z0-9]*\\.[a-zA-Z]*",
                "Here is the URL http://www.iag1.com I want to parse, oh and here is another one: http://candibowl.com/index.html.");
        for(String item : matchStringList) {
            System.out.println(MessageFormat.format("matchString=[{0}]", item));
        }
        // URL Pattern: (\d*)_(\d{8})_(\d{6}).csv
        matchStringList = test("(\\d*)_(\\d{8})_(\\d{6}).csv",
                "-rw-r--r--    1 lps      lps          1093 Oct 28 14:07 47_20101028_140300.csv");
        for(String item : matchStringList) {
            System.out.println(MessageFormat.format("matchString=[{0}]", item));
        }

        // URL Pattern: StatusUpdate_(\d{8})(\d{6}).CSV
        matchStringList = test("StatusUpdate_(\\d{8})(\\d{6}).CSV", "StatusUpdate_20101215202256.CSV");
        for(String item : matchStringList) {
            System.out.println(MessageFormat.format("matchString=[{0}]", item));
        }

        // URL Pattern: P042(\d{1})(\d{3})_(\d{6}).txt (#0421-0425)
        matchStringList = test("P042[1-5](\\d{3})_(\\d{6}).txt", "-rw-r--r--    1 lps      lps          1093 Oct 28 14:07 P0421668_164145.txt");
        for(String item : matchStringList) {
            System.out.println(MessageFormat.format("matchString=[{0}]", item));
        }

        //log.info("execute() :: Exit");
    }

    private List<String> test(String regularExpressionPattern, String inputText)
    {
        List<String> matchedStringList = new ArrayList<String>(0);

        System.out.println("\n regular expression: " + regularExpressionPattern +
                "\n input text: " + inputText + "\n");
        Pattern pattern = Pattern.compile(regularExpressionPattern);
        Matcher matcher = pattern.matcher(inputText);
        while (matcher.find())
        {
            String matchedString = matcher.group();
            matchedStringList.add(matchedString);
            System.out.println("matching text: '" + matchedString +
                    "' starting index=" + matcher.start() + ",ending index=" + matcher.end());
        }
        return matchedStringList;
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
        Main main = new Main();
        main.init();
        main.execute();
    }

}