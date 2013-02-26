package com.stgutah.playground.stringUtils;

import com.stgutah.playground.fixedLengthParsing.DetailRecord;
import com.stgutah.playground.fixedLengthParsing.InsRecord05;
import org.apache.commons.lang.StringUtils;

/**
 * Index a String, this was used in the O.C. Tanner project to create Customer Defined Field Names that
 * were indexed with a suffix. These field definitions were used many times by customer/clients.  Basically
 * the number of Customer Defined fields is based upon their largest user, the maximum number of defined fields
 * used by a Customer. For example, if the largest customer used 30 Customer Defined fields, there would only
 * be 30 defined fields in the database.  Other customers would use, incrementally, the existing Customer
 * Defined fields 1,2,3,4,.. to say 10, while the largest would be creating new ones like 31,32,33.  References
 * to these fields are made in the CustomerField table. Long explanation, but you get the idea.   
 *
 * User: dqromney
 * Date: Dec 8, 2009
 * Time: 11:36:23 AM
 *
 */
public class ConcatenateStrings {

    private static Character SPACE_CHAR = ' ';

    StringBuffer specialInstructionsBuffer = new StringBuffer(0);
    DetailRecord detailRecord = new DetailRecord();
    InsRecord05 ins05 = detailRecord.getInsRecord05();
    String constructionOtherType;

    private void execute() {
        System.out.println(String.format("getInstructions1()=[%1$s]",getInstructions1()));
        System.out.println(String.format("getInstructions2()=[%1$s]",getInstructions2()));
        System.out.println(String.format("getInstructions3()=[%1$s]",getInstructions3()));

        constructionOtherType = null;
        System.out.println(String.format("getConstructionOtherType(%1$s)=[%2$s]", constructionOtherType, getConstructionOtherType()));
        constructionOtherType = "";
        System.out.println(String.format("getConstructionOtherType(%1$s)=[%2$s]", constructionOtherType, getConstructionOtherType()));
        constructionOtherType = "no painting";
        System.out.println(String.format("getConstructionOtherType(%1$s)=[%2$s]", constructionOtherType, getConstructionOtherType()));
        constructionOtherType = "wood siding";
        System.out.println(String.format("getConstructionOtherType(%1$s)=[%2$s]", constructionOtherType, getConstructionOtherType()));
        constructionOtherType = "paint";
        System.out.println(String.format("getConstructionOtherType(%1$s)=[%2$s]", constructionOtherType, getConstructionOtherType()));
    }

    private String getInstructions3() {
        specialInstructionsBuffer.setLength(0);

        specialInstructionsBuffer.append(ins05.getSpecialInstructions1().isEmpty() ? "" :  ins05.getSpecialInstructions1());
        specialInstructionsBuffer.append(ins05.getSpecialInstructions2().isEmpty() ? "" :  ins05.getSpecialInstructions2());
        specialInstructionsBuffer.append(ins05.getSpecialInstructions3().isEmpty() ? "" :  ins05.getSpecialInstructions3());

        String specialInstructions = specialInstructionsBuffer.toString().trim();
        if(specialInstructions.toString().length() > 0) {
            specialInstructions = specialInstructions + "; ";
        }
        return specialInstructions;
    }

    private String getInstructions2() {
        specialInstructionsBuffer.setLength(0);
        String specialInstructions = "";
        if(StringUtils.isNotEmpty(ins05.getSpecialInstructions1())) {
            specialInstructions = ins05.getSpecialInstructions1();
            specialInstructionsBuffer.append(specialInstructions.isEmpty() ? "" :  specialInstructions);

        }
        if(StringUtils.isNotEmpty(ins05.getSpecialInstructions2())) {
            specialInstructions = ins05.getSpecialInstructions2();
            specialInstructionsBuffer.append(specialInstructions.isEmpty() ? "" :  specialInstructions);

        }
        if(StringUtils.isNotEmpty(ins05.getSpecialInstructions3())) {
            specialInstructions = ins05.getSpecialInstructions3();
            specialInstructionsBuffer.append(specialInstructions.isEmpty() ? "" :  specialInstructions);

        }
        specialInstructions = specialInstructionsBuffer.toString().trim();
        if(specialInstructions.toString().length() > 0) {
            specialInstructions = specialInstructions + "; ";
        }
        return specialInstructions;
    }

    private String getInstructions1() {
        specialInstructionsBuffer.setLength(0);
        String specialInstructions = "";
        if(StringUtils.isNotEmpty(ins05.getSpecialInstructions1())) {
            specialInstructions = ins05.getSpecialInstructions1().trim();
            specialInstructionsBuffer.append(specialInstructions.isEmpty() ? "" :  specialInstructions + "; ");

        }
        if(StringUtils.isNotEmpty(ins05.getSpecialInstructions2())) {
            specialInstructions = ins05.getSpecialInstructions2().trim();
            specialInstructionsBuffer.append(specialInstructions.isEmpty() ? "" :  specialInstructions + "; ");

        }
        if(StringUtils.isNotEmpty(ins05.getSpecialInstructions3())) {
            specialInstructions = ins05.getSpecialInstructions3().trim();
            specialInstructionsBuffer.append(specialInstructions.isEmpty() ? "" :  specialInstructions + "; ");

        }
        return specialInstructionsBuffer.toString();
    }

    public String getConstructionOtherType() {
        String otherType = StringUtils.rightPad(constructionOtherType == null ? "" : constructionOtherType, 8, SPACE_CHAR);
        return otherType.substring(0, 8); // Truncate it if longer than eight characters
    }

    private void init() {
        specialInstructionsBuffer = new StringBuffer(0);
        detailRecord = new DetailRecord();
        ins05 = detailRecord.getInsRecord05();

        ins05.setSpecialInstructions1("CHANGED TYPE P: TO AN A");
        ins05.setSpecialInstructions2(" NO CONTACT INSPECTION ");
        ins05.setSpecialInstructions3("                       ");

//        ins05.setSpecialInstructions1("                       ");
//        ins05.setSpecialInstructions2("                       ");
//        ins05.setSpecialInstructions3("                       ");

//        ins05.setSpecialInstructions1("NO COMMENT-LINE1       ");
//        ins05.setSpecialInstructions2("                       ");
//        ins05.setSpecialInstructions3("                       ");

//        ins05.setSpecialInstructions1("                       ");
//        ins05.setSpecialInstructions2("NO COMMENT-LINE2       ");
//        ins05.setSpecialInstructions3("                       ");

        ins05.setSpecialInstructions1("                       ");
        ins05.setSpecialInstructions2("                       ");
        ins05.setSpecialInstructions3("NO COMMENT-LINE3       ");

        constructionOtherType = "no painting";
    }

    /**
     * Main driver.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ConcatenateStrings main = new ConcatenateStrings();
        main.init();
        main.execute();
    }



/**
 // Handle Special Instructions: formatted as '<SpecialInstruction1 text>; <SpecialInstruction2 text>; <SpecialInstruction3 text>;'
 StringBuffer specialInstructionsBuffer = new StringBuffer(0);
 InspectionRecord05 ins05 = pDetailRecord.getInspectionRecord05();
 String specialInstructions = "";
 if(StringUtils.isNotEmpty(ins05.getSpecialInstructions1())) {
     specialInstructions = ins05.getSpecialInstructions1().trim();
     specialInstructionsBuffer.append(specialInstructions.isEmpty() ? "" :  specialInstructions + "; ");

 } else if(StringUtils.isNotEmpty(ins05.getSpecialInstructions2())) {
     specialInstructions = ins05.getSpecialInstructions2().trim();
     specialInstructionsBuffer.append(specialInstructions.isEmpty() ? "" :  specialInstructions + "; ");

 } else if(StringUtils.isNotEmpty(ins05.getSpecialInstructions3())) {
     specialInstructions = ins05.getSpecialInstructions3().trim();
     specialInstructionsBuffer.append(specialInstructions.isEmpty() ? "" :  specialInstructions + "; ");

 }
 serviceOrderLoadItem.setSpecialInstructions(specialInstructionsBuffer.toString());

  */

}
