package domain;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class IntercomUserFormat {

    public static List<IntercomBulkData> CreateBulkData(List<UserInfo> list)
    {
        List<IntercomBulkData> bdata = new ArrayList<>();
        DateTimeFormatter sdf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");
        for(Iterator<UserInfo> i = list.iterator(); i.hasNext();)
        {

            UserInfo item = i.next();
            DateTime subscribe = null;
            subscribe = item.getSubscribeDate()!=null ? sdf.parseDateTime(item.getSubscribeDate()) : new DateTime("2000-01-01");
            IntercomBulkData bd = new IntercomBulkData();
            IntercomUser iUser = new IntercomUser();
            iUser.user_id=item.getUserName();
            iUser.name =String.format("%s %s",item.getFirstName(),item.getLastName());
            iUser.email = item.getEmail();
            iUser.remote_created_at =  GetSubscribeDateAsTicks(subscribe);
            iUser.custom_attributes = new IntercomCustomAttributes();
            iUser.custom_attributes.phone=item.getPhone();
            iUser.custom_attributes.invoices=item.getUserInvoiceCount();
            iUser.custom_attributes.contacts=item.getContactCount();
            iUser.custom_attributes.logins = item.getLoginCount();
            iUser.custom_attributes.status = item.getUserState();
            iUser.custom_attributes.title = item.getTitle();
            iUser.custom_attributes.lang = item.getLanguage();
            iUser.custom_attributes.AscendType = item.getAscendType();
            bd.data = iUser;
            bdata.add(bd);
        }
        return bdata;
    }

    public static List<IntercomUser> CreateIntercomUserList(List<UserInfo> list)
    {
        List <IntercomUser> iUsers = new ArrayList<>();
        DateTimeFormatter sdf = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");
        for(Iterator<UserInfo> i = list.iterator(); i.hasNext();)
        {

            UserInfo item = i.next();
            DateTime subscribe = null;
            subscribe = item.getSubscribeDate()!=null ? sdf.parseDateTime(item.getSubscribeDate()) : new DateTime("2000-01-01");
            IntercomUser iUser = new IntercomUser();
            iUser.user_id=item.getUserName();
            iUser.name =String.format("%s %s",item.getFirstName(),item.getLastName());
            iUser.email = item.getEmail();
            iUser.remote_created_at =  GetSubscribeDateAsTicks(subscribe);
            iUser.custom_attributes = new IntercomCustomAttributes();
            iUser.custom_attributes.phone=item.getPhone();
            iUser.custom_attributes.invoices=item.getUserInvoiceCount();
            iUser.custom_attributes.contacts=item.getContactCount();
            iUser.custom_attributes.logins = item.getLoginCount();
            iUser.custom_attributes.status = item.getUserState();
            iUser.custom_attributes.title = item.getTitle();
            iUser.custom_attributes.lang = item.getLanguage();
            iUser.custom_attributes.AscendType = item.getAscendType();
            iUsers.add(iUser);
        }
        return iUsers;
    }
    public static int GetSubscribeDateAsTicks(DateTime subscribe)
    {
        DateTime tmpDate = new DateTime("2000-01-01");
        DateTime stCalend=new DateTime("1970-01-01");


            int res = subscribe.compareTo(tmpDate);
            if (res<=0)
            {
                subscribe = tmpDate;
            }

            res= (int) (subscribe.getMillis()/1000 - stCalend.getMillis()/1000);
            return res;



    }
}
