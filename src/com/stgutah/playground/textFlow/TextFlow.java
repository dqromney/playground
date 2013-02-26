package com.stgutah.playground.textFlow;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Text flow from a long string to limited string arrays example.
 * <p/>
 * User: dqromney
 * Date: Jan 25, 2011
 * Time: 2:57:01 PM
 */
public class TextFlow {

    Logger log = Logger.getLogger(TextFlow.class.getName());

    static Integer MAX_COMMENT_LENGTH = 60;

    private Pir040506 pir04 = new Pir040506(4);
    private Pir040506 pir05 = new Pir040506(5);
    private Pir040506 pir06 = new Pir040506(6);
    private String initialDamageString = "Property shows damage from ";
    private List<String> codeList = new ArrayList<String>(0);

    private void init() {
        pir04 = new Pir040506(4);
        pir05 = new Pir040506(5);
        pir06 = new Pir040506(6);

        pir04.setComments1("Home is currently being maintained by FAS.");
        // Damage Code list
        codeList.add("Mud/Landslide");
        codeList.add("Hailing");
        codeList.add("Wind");
        codeList.add("Tornado in the alley");
        codeList.add("Vandalism of the brick walls");
        codeList.add("Flooding of the basement");
        codeList.add("Freezing of the cats");
        codeList.add("Garbage and other stuff");
        codeList.add("Some more stuff");
        codeList.add("Additional stuff added");
        codeList.add("This should be the end if it");
        codeList.add("But it is not the end");
        codeList.add("However this should be the end now");
        codeList.add("Come on now this must be the end now");
        codeList.add("Nope it was not it but this will be");
    }

    private void execute() {
        flowText2(initialDamageString, pir04, pir05, pir06);

        StringBuffer codeBuffer = new StringBuffer(0);

        int listSize = codeList.size();
        int itemCount = 0;
        for (String code : codeList) {
            itemCount++;
            codeBuffer.append(code).append(itemCount < listSize ? ',' : '.');
            codeBuffer.append(" "); // Padding
            flowText2(codeBuffer.toString(), pir04, pir05, pir06);
            codeBuffer.setLength(0);
        }
        showRecords("END Before conversion...", false, pir04, pir05, pir06);
        showRecords("END After conversion...", true, pir04, pir05, pir06);
    }

    /**
     * Flow Text over number of comment field in multiple PIR040506, i.e. comment fields.
     *
     * @param pText   the text string to be flowed; should be less than 60 characters
     * @param pirList the list of PIR040506 objects
     */
    private void flowText2(String pText, Pir040506... pirList) {
        int consumed = 0;
        int start = 0;
        int end = 0;
        StringBuffer sb = new StringBuffer();

        // Gather up all existing strings in a StringBuffer object
        for (Pir040506 pir : pirList) {
            sb.append(pir.getComments1().trim());
            sb.append(pir.getComments2().trim());
        }
        // To prevent this 'Home is currently being maintained by FAS.GARAGE: Attached.' for example.
        if(sb.length() > 0 && sb.charAt(sb.length()-1) == '.') {
            sb.append(' ');
        }
        // Add new string
        sb.append(pText);

        for(Pir040506 pir : pirList) {
            start = end == 0 ? 0 : end;
            end = (sb.length() - consumed) < MAX_COMMENT_LENGTH ? sb.length() : start + MAX_COMMENT_LENGTH;
            if (consumed >= sb.length())
                break;
            pir.setComments1(sb.substring(start, end).replace(' ', '~'));
            consumed = end;

            if (consumed >= sb.length())
                break;
            start = end == 0 ? 0 : end;
            end = (sb.length() - consumed) < MAX_COMMENT_LENGTH ? sb.length() : start + MAX_COMMENT_LENGTH;
            pir.setComments2(sb.substring(start, end).replace(' ', '~'));
            consumed =  end;
        }

        showRecords("Before conversion...", false, pir04, pir05, pir06);
    }


    /**
     * Flow Text over number of comment field in multiple PIR040506, i.e. comment fields.
     *
     * @param pText   the text string to be flowed; should be less than 60 characters
     * @param pirList the list of PIR040506 objects
     */
    private void flowText(String pText, Pir040506... pirList) {
        int pTextLength = pText.length();
        for (Pir040506 pir : pirList) {
            if ((MAX_COMMENT_LENGTH - pir.getComments1().length()) > pTextLength) {
                pir.setComments1(pir.getComments1() + pText);
                break;
            } else if ((MAX_COMMENT_LENGTH - pir.getComments2().length()) > pTextLength) {
                pir.setComments2(pir.getComments2() + pText);
                break;
            }
        }
    }

    private void showRecords(String pText, Boolean pConvertTildaToSpace, Pir040506... pirList) {
        System.out.println(pText);
        if(pConvertTildaToSpace) {
            System.out.println(String.format("pir04=[%1$s]", pir04.toString().replace('~', ' ')));
            System.out.println(String.format("pir05=[%1$s]", pir05.toString().replace('~', ' ')));
            System.out.println(String.format("pir06=[%1$s]", pir06.toString().replace('~', ' ')));
        } else {
            System.out.println(String.format("pir04=[%1$s]", pir04.toString()));
            System.out.println(String.format("pir05=[%1$s]", pir05.toString()));
            System.out.println(String.format("pir06=[%1$s]", pir06.toString()));
        }

    }

    /**
     * Main driver.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TextFlow main = new TextFlow();
        main.init();
        main.execute();
    }
}

class Pir040506 {
    final Character SPACE_CHAR = ' ';

    Integer recordTypeNumber;
    String comments1 = new String();
    String comments2 = new String();

    Pir040506(Integer recordTypeNumber) {
        this.recordTypeNumber = recordTypeNumber;
    }

    public String getComments1() {
        return StringUtils.rightPad(comments1 == null ? "Home is currently being maintained by FAS." : comments1, 60, SPACE_CHAR);
    }

    public void setComments1(String comments1) {
        this.comments1 = comments1;
    }

    public String getComments2() {
        return StringUtils.rightPad(comments2 == null ? "" : comments2, 60, SPACE_CHAR);
    }

    public void setComments2(String comments2) {
        this.comments2 = comments2;
    }

    @Override
    public String toString() {
        return "Pir040506{" +
                "recordTypeNumber=" + recordTypeNumber +
                ", comments1='" + comments1 + '\'' +
                ", comments2='" + comments2 + '\'' +
                '}';
    }
}