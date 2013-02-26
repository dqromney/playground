package com.stgutah.playground.db.cachedRowSetExample;

import com.sun.rowset.CachedRowSetImpl;
import com.stgutah.playground.formatIdentification.FormatDescriptionReader;

import javax.sql.DataSource;
import javax.sql.rowset.CachedRowSet;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.sql.*;
import java.io.*;
import java.text.MessageFormat;

import org.apache.commons.lang.StringUtils;

/**
 * Using CachedResultSet Example.
 * <p/>
 * User: dqromney
 * Date: May 13, 2010
 * Time: 3:22:55 PM
 */
public class Main
{
    Logger log = Logger.getLogger(Main.class.getName());

    private Connection connection;
    static final String DB_URL_WITH_LOGIN = "jdbc:mysql://localhost:3306/playground?user=root&password="; // works
    static final String DB_URL_WITHOUT_LOGIN = "jdbc:mysql://localhost:3306/playground"; // works
    private BufferedReader in;

    public void execute()
    {
        log.info("execute() :: Enter");

        String query = "select ID, COUNTY, ZIPCODE, AREACODE from ZIPCODE";
        try
        {
            Connection conn = openConnection(DB_URL_WITHOUT_LOGIN, "root", null, false);
            Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            CachedRowSet cachedRowSet = new CachedRowSetImpl();

            ResultSet resultSet = statement.executeQuery(query);
            cachedRowSet.populate(resultSet);
            if (cachedRowSet.size() > 0)
            {
                // Delete contents if any.
                deleteAll(statement);
            }
            // Populate with initial data
            populateTable(statement);

            // Release set, and show it, close connection
            cachedRowSet.release();
            resultSet = statement.executeQuery(query);
            cachedRowSet.populate(resultSet);
            showResults(cachedRowSet);
            closeConnection(conn, statement, resultSet);

            // Opening it back up
            conn = openConnection(DB_URL_WITHOUT_LOGIN, "root", null, false);
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            // Update, batch commit the county field and show it
            cachedRowSet = updateRows(cachedRowSet);
            cachedRowSet = commitChanges(conn, cachedRowSet);
            showResults(cachedRowSet);

            // Clean up
            closeConnection(conn, statement, resultSet);

        } catch (SQLException sqle)
        {
            log.log(Level.SEVERE, "SQL Exception: ", sqle);
        } catch (Exception e)
        {
            log.log(Level.SEVERE, "Exception: ", e);
        }

        log.info("execute() :: Exit");
    }

    private void showResults(CachedRowSet pCachedRowSet)
            throws SQLException
    {
        log.info("showResults() :: Enter");

        pCachedRowSet.beforeFirst();
        System.out.println("  ID ZIPCODE COUNTY                         AREACODE");
        System.out.println("---- ------- ------------------------------ --------");
        while (pCachedRowSet.next())
        {
            String id = StringUtils.leftPad(new Integer(pCachedRowSet.getInt("ID")).toString(), 4);
            String zipCode = StringUtils.leftPad(pCachedRowSet.getString("ZIPCODE"), 7 + 1);
            String country = StringUtils.leftPad(pCachedRowSet.getString("COUNTY"), 30 + 1);
            String areaCode = StringUtils.leftPad(pCachedRowSet.getString("AREACODE"), 8 + 1);
            System.out.println(id + zipCode + country + areaCode);
        }
        log.info("showResults() :: Exit");
    }

    private CachedRowSet updateRows(CachedRowSet pCachedRowSet) throws SQLException
    {
        log.info("updateRows() :: Enter");
        pCachedRowSet.beforeFirst();
        while (pCachedRowSet.next())
        {
            System.out.println(
                    " " + pCachedRowSet.getInt("ID") + " " + pCachedRowSet.getString("ZIPCODE") + " " + pCachedRowSet.getString(
                            "COUNTY"));
            pCachedRowSet.updateString("COUNTY", "Salt Lake");
            pCachedRowSet.updateRow();
            System.out.println(
                    " " + pCachedRowSet.getInt("ID") + " " + pCachedRowSet.getString("ZIPCODE") + " " + pCachedRowSet.getString(
                            "COUNTY"));
            System.out.println("Record Number: " + pCachedRowSet.getInt("ID"));
        }
        log.info("updateRows() :: Exit");
        return pCachedRowSet;
    }

    private CachedRowSet commitChanges(Connection pConnection, CachedRowSet pCachedRowSet) throws SQLException
    {
        log.info("commitChanges() :: Enter");
        pCachedRowSet.beforeFirst();
        while (pCachedRowSet.next())
        {
            pCachedRowSet.acceptChanges(pConnection);
            System.out.println(
                    " " + pCachedRowSet.getInt("ID") + " " + pCachedRowSet.getString("ZIPCODE") + " " + pCachedRowSet.getString(
                            "COUNTY"));
            System.out.println("Record Number: " + pCachedRowSet.getInt("ID"));
        }
        log.info("commitChanges() :: Exit");
        return pCachedRowSet;
    }

    private Connection openConnection(String pDbUrl, String pLogin, String pPassword, boolean pAutoCommit) throws SQLException
    {
        log.info("openConnection() :: Enter");
        connection = DriverManager.getConnection(pDbUrl, pLogin, pPassword);
        connection.setAutoCommit(pAutoCommit);
        log.info("openConnection() :: Exit");
        return connection;
    }

    /**
     * This method should be used to close any connection objects using EnterpriseDAO.
     * Order of closing the object will be - ResultSet, Statement and Connection.
     *
     * @param pConnection Connection object that needs to be closed
     * @param pStatement  Statement object that needs to be closed.
     * @param pResultSet  ResultSet object that needs to be closed.
     * @exception SQLException
     */
    private void closeConnection(Connection pConnection, Statement pStatement, ResultSet pResultSet)
    {

        log.info("closeConnection() :: Enter");
        try
        {
            if (pResultSet != null)
            {
                pResultSet.close();
                pResultSet = null;
                log.info("Enterprise RS CLOSED ################################");
            }
            if (pStatement != null)
            {
                pStatement.close();
                pStatement = null;
                log.info("Enterprise STMT CLOSED ################################");
            }

            if (pConnection != null)
            {
                pConnection.close();
                pConnection = null;
                log.info("Enterprise CONNECTION CLOSED ################################");
            }
        } catch (SQLException ex)
        {
            log.warning("SQL exception occured unable to close Enterprise pConnectionection objects");
            log.warning(ex.getMessage());
        } finally
        {
            try
            {
                if (pResultSet != null)
                {
                    pResultSet.close();
                    pResultSet = null;
                    log.info("Enterprise RS CLOSED at finally section ################################");
                }
                if (pStatement != null)
                {
                    pStatement.close();
                    pStatement = null;
                    log.info("Enterprise STMT CLOSED at finally section ################################");
                }
                if (pConnection != null)
                {
                    pConnection.close();
                    pConnection = null;
                    log.info("Enterprise CONNECTION CLOSED at  FINALLY section ###################");
                }
            } catch (SQLException ex)
            {
                log.warning("Unable to close the Enterprise pConnectionection objects at final block");
            }
        }
        log.info("closeConnection() :: Exit");
    }


    private void deleteAll(Statement pStatement)
    {
        log.info("deleteAll() :: Enter");
        String deleteSql = "delete from zipcode";
        try
        {
            pStatement.execute(deleteSql);
        } catch (SQLException e)
        {
            log.log(Level.SEVERE, "SQLException while deleting contents of zipcode table: ", e);
        }
        log.info("deleteAll() :: Exit");
    }

    public void init()
    {
        log.info("init() :: Enter");
        // Register MySql driver
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException ex)
        {
            log.log(Level.SEVERE, "DB Driver Class not found: ", ex);
        } catch (IllegalAccessException e)
        {
            log.log(Level.SEVERE, "IllegalAccessException: ", e);
        } catch (InstantiationException e)
        {
            log.log(Level.SEVERE, "InstantiationException: ", e);
        }
        log.info("init() :: Exit");
    }

    /**
     * Populate ZipCode table.
     * <p/>
     * Note(s):
     * IntelliJ Regular Expressions used normalize Town name:
     * Find: ([a-zA-Z])','([a-zA-Z])
     * Replace: $1 $2
     *
     * @param pStatement the Query statement
     * @exception Exception if there are some SQL exceptions.
     */
    private void populateTable(Statement pStatement) throws Exception
    {
        log.info("populateTable() :: Enter");

        String insertSql = readSqlFile("/Users/dqromney/stgdev/playground/src/com/stgutah/playground/db/cachedRowSetExample/populate.sql");

        pStatement.execute(insertSql);

        log.info("populateTable() :: Exit");
    }

    /**
     * Read SQL from file and return the string. Ignores comment characters "-- ..."
     *
     * @param pPathAndFile the path and file of the SQL file
     * @return a String containing the contents of the SQL file
     * @exception IOException thrown if file not found, or something else related.
     */
    private String readSqlFile(String pPathAndFile)
            throws IOException
    {
        StringBuffer insertSqlBuffer = new StringBuffer();

        File file = new File("/Users/dqromney/stgdev/playground/src/com/stgutah/playground/db/cachedRowSetExample/populate.sql");
        System.out.println(MessageFormat.format("inputFile: {0}", file == null ? "null" : file.getCanonicalFile()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        String line;
        do
        {
            line = reader.readLine();
            // Ignore SQL comments in statement
            if (!line.startsWith("-- "))
            {
                insertSqlBuffer.append(line);
            }
            // System.out.println("[" + line == null ? "EMPTY" : line + "]");
            if (line == null)
            {
                break;
            }
        }
        while (line.length() > 0);

        return insertSqlBuffer.toString();
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
