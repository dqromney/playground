package com.stgutah.playground.fixedLengthParsing;

/**
 * Inspection Request Record 01.
 *
 * User: dqromney
 * Date: Dec 23, 2010
 * Time: 7:21:20 PM
 */
public class InsRecord05 extends AbstractInsRecord {
    // ----------------------------------------------------------------
    // Unique Fields
    // ----------------------------------------------------------------
    private String specialInstructions1;
    private String specialInstructions2;
    private String specialInstructions3;
    private String lastOccupancyStatus;
    private String lastExternalPropertyCondition;
    private String mapRefPropertyAddress;
    private String mapRefMailingAddress;
    private String filler;

    // ----------------------------------------------------------------
    // Accessors
    // ----------------------------------------------------------------
    public String getFiller() {
        return filler;
    }

    public void setFiller(String filler) {
        this.filler = filler;
    }

    public String getLastOccupancyStatus() {
        return lastOccupancyStatus;
    }

    public void setLastOccupancyStatus(String lastOccupancyStatus) {
        this.lastOccupancyStatus = lastOccupancyStatus;
    }

    public String getLastExternalPropertyCondition() {
        return lastExternalPropertyCondition;
    }

    public void setLastExternalPropertyCondition(String lastExternalPropertyCondition) {
        this.lastExternalPropertyCondition = lastExternalPropertyCondition;
    }

    public String getMapRefMailingAddress() {
        return mapRefMailingAddress;
    }

    public void setMapRefMailingAddress(String mapRefMailingAddress) {
        this.mapRefMailingAddress = mapRefMailingAddress;
    }

    public String getMapRefPropertyAddress() {
        return mapRefPropertyAddress;
    }

    public void setMapRefPropertyAddress(String mapRefPropertyAddress) {
        this.mapRefPropertyAddress = mapRefPropertyAddress;
    }

    public String getSpecialInstructions1() {
        return specialInstructions1;
    }

    public void setSpecialInstructions1(String specialInstructions1) {
        this.specialInstructions1 = specialInstructions1;
    }

    public String getSpecialInstructions2() {
        return specialInstructions2;
    }

    public void setSpecialInstructions2(String specialInstructions2) {
        this.specialInstructions2 = specialInstructions2;
    }

    public String getSpecialInstructions3() {
        return specialInstructions3;
    }

    public void setSpecialInstructions3(String specialInstructions3) {
        this.specialInstructions3 = specialInstructions3;
    }


//    @Override
//    public String toString() {
//        return super.toString() +  " InsRecord05{" +
//                "specialInstructions1='" + specialInstructions1 + '\'' +
//                ", specialInstructions2='" + specialInstructions2 + '\'' +
//                ", specialInstructions3='" + specialInstructions3 + '\'' +
//                ", lastOccupancyStatus='" + lastOccupancyStatus + '\'' +
//                ", lastExternalPropertyCondition='" + lastExternalPropertyCondition + '\'' +
//                ", mapRefPropertyAddress='" + mapRefPropertyAddress + '\'' +
//                ", mapRefMailingAddress='" + mapRefMailingAddress + '\'' +
//                ", filler='" + filler + '\'' +
//                '}';
//    }
    @Override
    public String toString() {
        return super.toString() +
                "specialInstructions1=[" + specialInstructions1 + ']' + DEFAULT_LINE_SEPARATOR +
                "specialInstructions2=[" + specialInstructions2 + ']' + DEFAULT_LINE_SEPARATOR +
                "specialInstructions3=[" + specialInstructions3 + ']' + DEFAULT_LINE_SEPARATOR +
                "lastOccupancyStatus=[" + lastOccupancyStatus + ']' + DEFAULT_LINE_SEPARATOR +
                "lastExternalPropertyCondition=[" + lastExternalPropertyCondition + ']' +  DEFAULT_LINE_SEPARATOR +
                "mapRefPropertyAddress=[" + mapRefPropertyAddress + ']' +  DEFAULT_LINE_SEPARATOR +
                "mapRefMailingAddress=[" + mapRefMailingAddress + ']' +  DEFAULT_LINE_SEPARATOR +
                "filler=[" + filler + ']' + DEFAULT_LINE_SEPARATOR;
    }
}
