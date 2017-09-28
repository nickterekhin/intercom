package db;

import domain.UserInfo;

import java.util.List;
import java.util.Set;

/**
 * Created by Nick on 06.11.2014.
 */
public interface UserInfoIntercomDAO {
    public List<UserInfo> getAll(String appName, Long fromRow, Long toRow );
    public Long getRowsNum(String appName);
    public Set<String> getAllUsersNames(String appName);
    public Set<String> getAllNotExistsUsers();
    public Set<String> getAllNotExistsUsersByEmail();
}
