package com.stgutah.playground.FixedLengthParsing2;

/**
 * Detail Record value class.
 *
 * User: dqromney
 * Date: Dec 23, 2010
 * Time: 7:19:02 PM
 */
public class DetailRecord {

    // ----------------------------------------------------------------
    // Fields
    // ----------------------------------------------------------------
    private InsRecord01 insRecord01 = new InsRecord01();
    private InsRecord02 insRecord02 = new InsRecord02();
    private InsRecord03 insRecord03 = new InsRecord03();
    private InsRecord04 insRecord04 = new InsRecord04();
    private InsRecord05 insRecord05 = new InsRecord05();

    // ----------------------------------------------------------------
    // Accessors
    // ----------------------------------------------------------------
    public InsRecord01 getInsRecord01() {
        return insRecord01;
    }

    public void setInsRecord01(InsRecord01 insRecord01) {
        this.insRecord01 = insRecord01;
    }

    public InsRecord02 getInsRecord02() {
        return insRecord02;
    }

    public void setInsRecord02(InsRecord02 insRecord02) {
        this.insRecord02 = insRecord02;
    }

    public InsRecord03 getInsRecord03() {
        return insRecord03;
    }

    public void setInsRecord03(InsRecord03 insRecord03) {
        this.insRecord03 = insRecord03;
    }

    public InsRecord04 getInsRecord04() {
        return insRecord04;
    }

    public void setInsRecord04(InsRecord04 insRecord04) {
        this.insRecord04 = insRecord04;
    }

    public InsRecord05 getInsRecord05() {
        return insRecord05;
    }

    public void setInsRecord05(InsRecord05 insRecord05) {
        this.insRecord05 = insRecord05;
    }

    @Override
    public String toString() {
        return "DetailRecord{" +
                "insRecord01=" + insRecord01 +
                ", insRecord02=" + insRecord02 +
                ", insRecord03=" + insRecord03 +
                ", insRecord04=" + insRecord04 +
                ", insRecord05=" + insRecord05 +
                '}';
    }
}
