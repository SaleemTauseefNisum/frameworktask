package dbmanager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnectionManager {
    private static DBConnectionManager instance;
    private Connection con;
    private DBConnectionManager(String url){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = DriverManager.getConnection(url);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return this.con;
    }

    public static DBConnectionManager getInstance(String url) throws SQLException {
        if(instance==null){
            instance = new DBConnectionManager(url);
        } else if(instance.getConnection().isClosed()){
            instance = new DBConnectionManager(url);
        }
        return instance;
    }

}
