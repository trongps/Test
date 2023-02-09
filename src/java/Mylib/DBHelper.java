package Mylib;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author TrongPS
 */
public class DBHelper {
    public static Connection makeConnection() throws ClassNotFoundException, SQLException{
        Connection con = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost\\TRONGPS:1433;databaseName=PRJ301_EX00";
        con = DriverManager.getConnection(url, "sa", "sa20042002");
        return con;
    }
}
