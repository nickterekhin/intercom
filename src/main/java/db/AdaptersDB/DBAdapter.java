package db.AdaptersDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Nick on 19.10.2014.
 */
public class DBAdapter {
    private String passwd=null;
    private String dbUrl = null;
    private String userName=null;

    public DBAdapter() {

        this.passwd="159357684258Nick";
        this.dbUrl="jdbc:mysql://localhost:3306/intercom";
        this.userName="root";
        initDriver();
    }
    public void initDriver()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    protected Connection getConnection()
    {
        try{
            return DriverManager.getConnection(dbUrl,userName,passwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    protected void closeConnection(Connection connection)
    {
        try{
            if(connection!=null)
            {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
