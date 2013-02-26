package com.stgutah.playground.reflection.types;

/**
 * Construction Type.
 *
 * Frame,Brick/Block,Stucco,Vinyl/Aluminum Siding,Other* //OCCUPANCY VERIFICATION and PROPERTY CONDITION
 * and
 * Frame,Brick/Block,Stucco,Other* // DISASTER INSPECTION, NO CONTACT INSPECTION, SALE DATE INSPECTION
 *
 * User: dqromney
 * Date: Jan 7, 2011
 * Time: 6:06:33 PM
 */
public enum ConstructionType {
    FRAME('A', "Frame"),
    FRAME_SIDING('A', "Frame/Siding"), // TODO Most likely will be removed. dqr
    BRICK('B', "Brick/Block"),
    STUCCO('C', "Stucco"),
    VINYL_ALUMINUM_SIDING('Z', "Vinyl/Aluminum Siding"),
    OTHER('Z', "Other*"),
    OTHER_INSPECTION_J('Z', "Other"),
    UNDEFINED(' ', "UNDEFINED");

    private Character type;
    private String value;

    ConstructionType(Character type, String value) {
        this.type = type;
        this.value = value;
    }

    /**
     * Search for 'input option' value.
     *
     * @param pValue the 'input option' value string to search for
     * @return the enum or null if not found
     */
    public static ConstructionType searchValue(String pValue) {
        ConstructionType found = ConstructionType.UNDEFINED;
        if(pValue != null) {
            ConstructionType[] enumSearchList = ConstructionType.values();
            for(ConstructionType item : enumSearchList) {
                if (item.getValue().compareToIgnoreCase(pValue) == 0) {
                    found = item;
                    break;
                }
            }
        }
        return found;
    }

    // ----------------------------------------------------------------
    // Accessors
    // ----------------------------------------------------------------
    public Character getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

}
