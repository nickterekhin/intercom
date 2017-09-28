package db;

import db.AdaptersDB.DBAdapter;
import domain.Options;
import domain.UserIntercomInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Nick on 06.11.2014.
 */
public class UserServiceImpl extends DBAdapter implements UsersService {
    Connection connection =null;
    @Override
    public void creatUserRow(UserIntercomInfo userIntercom) {

        if(userIntercom==null)
            return;
            try{
                connection = getConnection();
                PreparedStatement st = connection.prepareStatement("INSERT INTO usersIntercom (id,"+
                                                "userJSON,"+
                                                "inDate,"+
                                                "isUpdate,fromRow,toRow)VALUES(default,?,?,?,?,?)");
                st.setString(1,userIntercom.getJsonString());
                st.setLong(2,userIntercom.getInDate());
                st.setLong(3,userIntercom.getIsupdate());
                st.setLong(4,userIntercom.getFromRow());
                st.setLong(5,userIntercom.getToRow());
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        finally {
                closeConnection(connection);
            }

    }
    public void setOptions(Options opt)
    {
        if(opt==null)
            return;
        try{
            connection = getConnection();
            PreparedStatement st = connection.prepareStatement("INSERT INTO options (id,usersQty,pDate,isUpdate)VALUES(default,?,?,?)");
            st.setLong(1,opt.getUsersQty());
            st.setLong(2,opt.getPdate());
            st.setInt(3,opt.getIsUpdate());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeConnection(connection);
        }
    }
    public Options getOptions()
    {
        try{
            connection = getConnection();
            PreparedStatement st = connection.prepareStatement("SELECT * FROM options WHERE id=1");
            ResultSet res = st.executeQuery();
            if(res.next()) {
                Options opt = new Options(res.getLong("usersQty"), res.getLong("pDate"), res.getInt("isUpdate"));
                return opt;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeConnection(connection);
        }
        return null;
    }
    public void updateOptions(Options opt)
    {
        if(opt==null)
            return;
        try{
            connection = getConnection();
            PreparedStatement st = connection.prepareStatement("UPDATE options SET usersQty=?,pDate=?,isUpdate=? WHERE id=1");
            st.setLong(1,opt.getUsersQty());
            st.setLong(2,opt.getPdate());
            st.setInt(3,opt.getIsUpdate());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            closeConnection(connection);
        }
    }
}
