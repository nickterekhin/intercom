package db;

import db.AdaptersDB.MSDBAdapter;
import domain.UserInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by Nick on 06.11.2014.
 */
public class UserInfoIntercomView extends MSDBAdapter implements UserInfoIntercomDAO {
    private Connection connection = null;
    @Override
    public List<UserInfo> getAll(String appName,Long fromRow,Long toRow) {
        List<UserInfo> usersInfo = new ArrayList<UserInfo>();
        //String sql = "SELECT TotalRows,rownum, * FROM (SELECT COUNT(*) OVER () [TotalRows], ROW_NUMBER() OVER (ORDER By dbo.UserInfoIntercomView.SubscribeDate) AS rownum, * FROM dbo.UserInfoIntercomView WHERE (dbo.UserInfoIntercomView.UserState='PaidUp' OR dbo.UserInfoIntercomView.UserState='Freemium' OR dbo.UserInfoIntercomView.UserState='Trial' OR dbo.UserInfoIntercomView.UserState='Collections' OR dbo.UserInfoIntercomView.UserState='PaymentDue' OR dbo.UserInfoIntercomView.UserState='AscendBilled') AND dbo.UserInfoIntercomView.Email IS NOT NULL AND dbo.UserInfoIntercomView.Email<>'' AND dbo.UserInfoIntercomView.ApplicationName='"+appName+"' AND dbo.UserInfoIntercomView.UserName='Africk11') AS a WHERE rownum BETWEEN "+fromRow+" AND "+toRow;
       //String sql = "SELECT TotalRows,rownum, * FROM (SELECT COUNT(*) OVER () [TotalRows], ROW_NUMBER() OVER (ORDER By dbo.UserInfoIntercomView.SubscribeDate) AS rownum, * FROM dbo.UserInfoIntercomView WHERE (dbo.UserInfoIntercomView.UserState='Cancelled') AND dbo.UserInfoIntercomView.Email IS NOT NULL AND dbo.UserInfoIntercomView.Email<>'' AND dbo.UserInfoIntercomView.ApplicationName='"+appName+"') AS a WHERE rownum BETWEEN "+fromRow+" AND "+toRow;
        String sql = "SELECT TotalRows,rownum, * FROM (SELECT COUNT(*) OVER () [TotalRows], ROW_NUMBER() OVER (ORDER By dbo.UserInfoIntercomView.SubscribeDate) AS rownum, * FROM dbo.UserInfoIntercomView WHERE (dbo.UserInfoIntercomView.UserState='Free') AND dbo.UserInfoIntercomView.Email IS NOT NULL AND dbo.UserInfoIntercomView.Email<>'' AND dbo.UserInfoIntercomView.ApplicationName='"+appName+"') AS a WHERE rownum BETWEEN "+fromRow+" AND "+toRow;
        try{
            connection = getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet resultSet = st.executeQuery();
            while(resultSet.next()){
                UserInfo userInfo = new UserInfo();
                initUserInfo(userInfo,resultSet);
                usersInfo.add(userInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            closeConnection(connection);
        }
        return usersInfo;
    }

    @Override
    public Long getRowsNum(String appName) {
        Long numRows = 0L;
        //String sql = "SELECT COUNT(*) as usersNum FROM dbo.UserInfoIntercomView WHERE (dbo.UserInfoIntercomView.UserState='PaidUp' OR dbo.UserInfoIntercomView.UserState='Freemium' OR dbo.UserInfoIntercomView.UserState='Trial' OR dbo.UserInfoIntercomView.UserState='Collections' OR dbo.UserInfoIntercomView.UserState='PaymentDue' OR dbo.UserInfoIntercomView.UserState='AscendBilled') AND dbo.UserInfoIntercomView.Email<>'' AND dbo.UserInfoIntercomView.ApplicationName='"+appName+"' AND dbo.UserInfoIntercomView.UserName='Africk11'";
        //String sql = "SELECT COUNT(*) as usersNum FROM dbo.UserInfoIntercomView WHERE (dbo.UserInfoIntercomView.UserState='Cancelled') AND dbo.UserInfoIntercomView.Email IS NOT NULL AND dbo.UserInfoIntercomView.Email<>'' AND dbo.UserInfoIntercomView.ApplicationName='"+appName+"'";
        String sql = "SELECT COUNT(*) as usersNum FROM dbo.UserInfoIntercomView WHERE (dbo.UserInfoIntercomView.UserState='Free') AND dbo.UserInfoIntercomView.Email IS NOT NULL AND dbo.UserInfoIntercomView.Email<>'' AND dbo.UserInfoIntercomView.ApplicationName='"+appName+"'";
        try{
            connection = getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet result = st.executeQuery();
            if(result.next())
            numRows=result.getLong("usersNum");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            closeConnection(connection);
        }

        return numRows;
    }

    public Set<String> getAllUsersNames(String appName)
    {
        Set<String> usersName = new HashSet<String>();
        String sql = "SELECT * FROM dbo.UserInfoIntercomView WHERE dbo.UserInfoIntercomView.Email IS NOT NULL AND dbo.UserInfoIntercomView.Email<>'' AND dbo.UserInfoIntercomView.ApplicationName='"+appName+"'";

        try{
            connection = getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet resultSet = st.executeQuery();
            while(resultSet.next()){

                usersName.add(resultSet.getString("UserName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            closeConnection(connection);
        }
        return usersName;
    }

    public Set<String> getAllNotExistsUsers()
    {
        Set<String> usersName = new HashSet<String>();
        String sql = "SELECT * FROM dbo.pink2 WHERE dbo.pink2.[exists] =0 AND dbo.pink2.UserID Is NOT NULL";

        try{
            connection = getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet resultSet = st.executeQuery();
            while(resultSet.next()){

                usersName.add(resultSet.getString("UserID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            closeConnection(connection);
        }
        return usersName;
    }
    public Set<String> getAllNotExistsUsersByEmail()
    {
        Set<String> usersName = new HashSet<String>();
        String sql = "SELECT * FROM dbo.pink3 WHERE dbo.pink3.Email Is NOT NULL";

        try{
            connection = getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet resultSet = st.executeQuery();
            while(resultSet.next()){

                usersName.add(resultSet.getString("Email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            closeConnection(connection);
        }
        return usersName;
    }
    private void initUserInfo(UserInfo u, ResultSet r ) throws SQLException {
        u.setUserName(r.getString("UserName"));
        u.setFirstName(r.getString("FirstName"));
        u.setLastName(r.getString("LastName"));
        u.setUserGUID(r.getString("UserGUID"));
        u.setSubscribeDate(r.getString("SubscribeDate"));
        u.setTrialEndDate(r.getString("TrialEndDate"));
        u.setUserInvoiceCount(r.getInt("UserInvoiceCount"));
        u.setLastLoginDate(r.getString("LastLoginDate"));
        u.setContactCount(r.getInt("ContactCount"));
        u.setLoginCount(r.getInt("LoginCount"));
        u.setPurchaseOrdercount(r.getInt("PurchaseOrderCount"));
        u.setEmail(r.getString("Email"));
        u.setUserState(r.getString("UserState"));
        u.setPhone(r.getString("Phone"));
        u.setApplicationName(r.getString("ApplicationName"));
        u.setTitle(r.getString("Title"));
        u.setLanguage(r.getString("Language"));
        u.setAscendType(r.getString("AscendType"));



    }
}
