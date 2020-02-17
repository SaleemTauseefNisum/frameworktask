package dbmanager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
public class DataBaseCon {
    /*private static final Logger log = LoggerFactory.getLogger(DataBaseCon.class);
    public void connectSql() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://db4free.net:3306", "akashdktyagi", "akashdktyagi");
            System.out.println(con);
            //here is database name, username and password
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from Products;");
            System.out.println(rs);
            while (rs.next())
                log.info(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4));
            con.close();
        } catch (Exception e) {
            log.info("exception" + e);
        }
    }*/
    public static final Logger logger = LogManager.getLogger(DataBaseCon.class);
    private Connection conn = null;
    private ResultSet currentRS = null;
    private String DbURL = "db4free.net:3306/db_autofrat";
    private String DbUsername = "akashdktyagi";
    private String DbPassword = "akashdktyagi";
    public void initConnection(String DbURL, String DbUsername, String DbPassword) {
        // Create connection
        logger.info("Database Credentials: " + DbURL + DbPassword);
        try {
            conn = DriverManager.getConnection(DbURL, DbUsername, DbPassword);
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            logger.error("Coult not Initiate Connection to the Database. Please refer to the trace below: ");
            e.printStackTrace();
        }
    }
    public void MySQLJDBCUtility() {
        //PropertyReader properties = new PropertyReader();
        //QATestProperties qaTestProperties = script.getQATestProperties();
        this.initConnection(DbURL, DbUsername, DbPassword);
    }
    public Connection getConn() {
        return conn;
    }
    public ResultSet execute(String query) {
        ResultSet rs = null;
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            currentRS = rs;
        } catch (java.sql.SQLException e) {
            logger.error("ERROR WHILE EXECUTING QUERY - PLEASE CHECK CONNECTION AND QUERY");
            currentRS = null;
        }
        return rs;
    }
    public boolean executeUpdate(String sql) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
        } catch (java.sql.SQLException e) {
            return false;
        }
        return true;
    }
    public void closeConnection() throws SQLException {
        conn.close();
    }
}
