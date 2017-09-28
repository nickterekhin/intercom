package db.AdaptersDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Nick on 06.11.2014.
 */
public class MSDBAdapter {
    private String passwd=null;
    private String dbUrl = null;
    private String userName=null;

    public MSDBAdapter() {
        this.passwd="";//db password
        this.dbUrl="jdbc:sqlserver://[server IP:3333];databaseName=VerticalOffice.Enterprise.Database";
        this.userName="";//db username
        initDriver();

    }
    public void initDriver()
    {
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    protected Connection getConnection()
    {
        try{
            return DriverManager.getConnection(dbUrl, userName, passwd);
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
