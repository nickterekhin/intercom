package db;

import domain.Options;
import domain.UserIntercomInfo;

/**
 * Created by Nick on 06.11.2014.
 */
public interface UsersService {
    public void creatUserRow(UserIntercomInfo userIntercom);
    public void setOptions(Options opt);
    public Options getOptions();
    public void updateOptions(Options opt);
}
