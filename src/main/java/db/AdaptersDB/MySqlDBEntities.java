package db.AdaptersDB;

import db.UserServiceImpl;
import db.UsersService;

/**
 * Created by Nick on 06.11.2014.
 */
public class MySqlDBEntities {
    public UsersService usersService = new UserServiceImpl();
}
