package dbmanager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
    public static void main(String[] args) throws SQLException{
        String q ="Select * from Products";
        String[][] result = FetchDataFromDB(q);
        System.out.println(result);
    }
    public static String[][] FetchDataFromDB(String query) throws SQLException{
        String url ="jdbc:mysql://db4free.net:3306/db_autofrat?user=akashdktyagi&password=akashdktyagi";
        DBConnectionManager dbInstance =DBConnectionManager.getInstance(url);
        Connection con = dbInstance.getConnection();
        Statement stat = con.createStatement();
        ResultSet res = stat.executeQuery(query);

        //To get total number of column returned
        int clmCount = res .getMetaData().getColumnCount();

        //To get total number of rows returned
        res.last(); // to go to last row
        int rowcount = res.getRow();
        res.beforeFirst();

        //create  a object
        String[][] result = new String[rowcount][clmCount];
        int i= 0;
        while (res.next()){
            for (int j=0; j<clmCount; j++){

            }
            i=i+1;
        }
        return result;
    }
}
