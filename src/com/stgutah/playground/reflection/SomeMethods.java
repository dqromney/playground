package com.stgutah.playground.reflection;

import com.stgutah.playground.reflection.types.ConstructionType;

import java.util.Map;
import java.lang.annotation.*;

/**
 * Some methods to be called via Reflection.
 *
 * User: dqromney
 * Date: 6/7/12
 * Time: 4:16 PM
 */
public class SomeMethods {

    Map<DetailType, String> detailValueMap;

    public SomeMethods(Map<DetailType, String> pDetailValueMap) {
        detailValueMap = pDetailValueMap;
    }

//    public int simpleMethod() {
//        System.out.println("simpleMethod() called");
//        return 10;
//    }
//
//    public String oneArgumentMethod(String s1) {
//        System.out.println(String.format("oneArgument([%1$s]) called", s1));
//        return s1;
//    }
//
//    public List<String> twoArgumentMethod(String s1, String s2) {
//        List<String> listOfArgs = new ArrayList<String>(0);
//        listOfArgs.add(s1);
//        listOfArgs.add(s2);
//        System.out.println(String.format("twoArgument([%1$s], [%2$s]) called", s1, s2));
//        return listOfArgs;
//    }

    @DetailTypeDataMap(detailType=DetailType.CONSTRUCTION_TYPE)
    public void mapConstructionType(ResultPropertyInspection01 pPir01) {
        // Construction Type
        String value = detailValueMap.get(DetailType.CONSTRUCTION_TYPE);
        pPir01.setConstructionType(ConstructionType.searchValue(value));
        System.out.println(String.format("mapConstructionType([%1$s]) called", pPir01));
    }
}
