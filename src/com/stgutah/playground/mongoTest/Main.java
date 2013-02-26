package com.stgutah.playground.mongoTest;

import com.mongodb.*;
import com.stgutah.playground.formatIdentification.FormatDescriptionReader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 * MongoDB test code.
 * <p/>
 * Note(s):
 * To start MongoDB type the following at the command line:
 * sudo /Users/dqromney/tools//mongodb-osx-i386-1.4.3/bin/mongod
 * <p/>
 * User: dqromney
 * Date: Jun 8, 2010
 * Time: 2:45:49 PM
 */
public class Main
{
    Logger log = Logger.getLogger(Main.class.getName());

    private Mongo mongo;
    private DB db;
    private DBCollection collection;

    public static void main(String[] args) throws IOException
    {
        Main main = new Main();
        main.init();
        main.execute();
    }

    private void init() throws IOException
    {
        // m = new Mongo();
        // m = new Mongo("localhost", 27017);
        setMongo(new Mongo("localhost"));
        getMongo().dropDatabase("test");
        setDb(getMongo().getDB("test"));
        setCollection(createCollection("testCollection"));
    }

    private void execute() throws IOException
    {
        showCollectionNames(getDb());
        BasicDBObject bdbObject = createDBObject();
        getCollection().insert(bdbObject);
        DBObject myDoc = getCollection().findOne(bdbObject);
        System.out.println(myDoc);
        System.out.println("Name=[" + myDoc.get("name") + "] Info[" + myDoc.get("info") + "]");
        System.out.println("Count=[" + getCollection().getCount(myDoc) + "]");

        log.info("Populate zipCodeList...");
        List<ZipCode> zipCodeList = getZipCodeData(
                "/Users/dqromney/stgdev/playground/src/com/stgutah/playground/mongoTest/zipCodeData.csv");
        populateZipCodeDoc(1L, zipCodeList);

        // Do query
        log.info("Do queries...");
        DBCursor cursor;
        
        cursor = getCollection().find(new BasicDBObject("id", new BasicDBObject("$gt", 790)));
        printResults(cursor, "Find id > 790");

        cursor = getCollection().find(new BasicDBObject()
                .append("id", new BasicDBObject("$lte", 3)));
        printResults(cursor, "Find id <= 3");

        cursor = getCollection().find(new BasicDBObject()
                .append("id", new BasicDBObject("$lte", 3))
                .append("areaCode", "435"));
        printResults(cursor, "Find id <= 3 and areaCode is equal to 435");

        cursor = getCollection().find(new BasicDBObject()
                .append("county", Pattern.compile("^Woo.*?s$")))
                .sort(new BasicDBObject("zipCode", -1));
        printResults(cursor, "Find county like Woo%s and sort reverse by zipCode");

        cursor = getCollection().find(new BasicDBObject()
                .append("areaCode", "435"))
                .sort(new BasicDBObject("id", 1))
                .limit(3);
        printResults(cursor, "Get top 3 (by id) where areaCode = 435");
    }

    private DBCollection createCollection(String pCollectionName)
    {
        return getDb().getCollection(pCollectionName);
    }

    private static void printResults(DBCursor cur, String message)
    {
        System.out.println("<<<<<<<<<< " + message + " >>>>>>>>>>>>");
        while (cur.hasNext())
        {
            System.out.println(cur.next().get("id") + "," + cur.curr().get("zipCode") + "," + cur.curr().get("county") + "," + cur.curr().get("areaCode"));
        }
    }

    private List<ZipCode> getZipCodeData(String pZipCodeFile) throws IOException
    {
        List<ZipCode> zipCodeList = new ArrayList<ZipCode>(0);
        File zipCodeInputFile = new File(pZipCodeFile);
        InputStream input = new FileInputStream(zipCodeInputFile.getCanonicalFile());
        if (input == null)
        {
            return zipCodeList;
        }
        ZipCodeReader in = new ZipCodeReader(new InputStreamReader(input));

        ZipCode zipCode;
        while ((zipCode = in.read()) != null)
        {
            zipCodeList.add(zipCode);
        }

        return zipCodeList;
    }

    private BasicDBObject createDBObject()
    {
        BasicDBObject doc = new BasicDBObject();
        doc.put("name", "MongoDB");
        doc.put("type", "database");
        doc.put("count", 1);

        BasicDBObject info = new BasicDBObject();
        info.put("x", 203);
        info.put("y", 102);

        doc.put("info", info);

        return doc;
    }

    private void populateZipCodeDoc(long pStartKey, List<ZipCode> pZipCodeListList)
    {
        long key = pStartKey;

        for(ZipCode zipCode : pZipCodeListList)
        {
            createZipCodeObject(key++, zipCode.getZipCode(), zipCode.getCounty(), zipCode.getAreaCode());
        }
    }

    private BasicDBObject createZipCodeObject(Long pKey, String pZipCode, String pCounty, String pAreaCode)
    {
        BasicDBObject doc = new BasicDBObject("id", pKey);
        doc.put("zipCode", pZipCode);
        doc.put("county", pCounty);
        doc.put("areaCode", pAreaCode);
        getCollection().insert(doc);
        return doc;
    }

    private void showCollectionNames(DB db)
    {
        Set<String> collections = db.getCollectionNames();

        for(String s : collections)
        {
            System.out.println(s);
        }
    }

    public Mongo getMongo()
    {
        return mongo;
    }

    public void setMongo(Mongo m)
    {
        this.mongo = m;
    }

    public DB getDb()
    {
        return db;
    }

    public void setDb(DB db)
    {
        this.db = db;
    }

    public DBCollection getCollection()
    {
        return collection;
    }

    public void setCollection(DBCollection collection)
    {
        this.collection = collection;
    }
}
