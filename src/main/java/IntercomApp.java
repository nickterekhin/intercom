import com.google.gson.Gson;
import db.AdaptersDB.DBEntities;
import db.AdaptersDB.MySqlDBEntities;
import domain.*;
import http.HttpMyAdapter;
import io.intercom.api.*;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.HttpResponse;
import org.apache.log4j.Logger;

import java.util.*;


/**
 * Created by Nick on 06.11.2014.
 */
public class IntercomApp {
    public static DBEntities db = new DBEntities();
    public static MySqlDBEntities mydb=new MySqlDBEntities();
    public static String method = "bulk";
    public static int n = 50;
    public static void main(String[] args) throws InterruptedException {
        //Logger log = Logger.getLogger(args[0].toUpperCase() + "Logging");
        Logger logdel = Logger.getLogger("PODelLogging");
       //updated all users;
        //updateUsers(args);
        //removeDuplicates(logdel);
        //deleteUserByEmail("vvanderveen@pinkoffice.com",logdel);
        updateUserId(args[0],args[1],logdel);

    }
    public static void updateUsers(String[] args) throws InterruptedException {
        Logger log = Logger.getLogger(args[0].toUpperCase() + "Logging");
        //Logger logdel = Logger.getLogger("PODelLogging");
        if(args.length!=0) {

            if(args.length>1) method = !args[1].isEmpty() ? args[1] : method;
            List<UserInfo> userList;
            if (method.equals("bulk")) {

                if(args.length>3) n = args[3].isEmpty() ? n : Integer.valueOf(args[3]);

                Long numUsers = db.userInfoList.getRowsNum("/" + args[0].toUpperCase());
                Long loopCount = 0L, knum = 0L;
                if(args.length > 3) {
                    if (!args[2].isEmpty()) {
                        loopCount = Math.round(Math.ceil((numUsers - Long.valueOf(args[2])) / n)) + 1;
                        knum = Long.valueOf(args[2]);
                    } else {
                        loopCount = Math.round(Math.ceil(numUsers / n)) + 1;
                    }
                }
                else
                {
                    loopCount = Math.round(Math.ceil(numUsers / n)) + 1;
                }
                for (Long i = 1L, k = knum; i <= loopCount; i++, k += n) {
                    userList = db.userInfoList.getAll("/" + args[0].toUpperCase(), k, k + (n-1));
                    //deleteUsers(userList,logdel);
                    List<IntercomBulkData> users = IntercomUserFormat.CreateBulkData(userList);
                    Gson obj = new Gson();
                    String jsonString = "{\"items\":" + obj.toJson(users) + "}";
                    HttpMyAdapter httpAdapter = new HttpMyAdapter(args[0]);
                    HttpResponse resp = httpAdapter.post(jsonString);
                    log.info("[" + k + "L:" + (k + (n-1)) + "] {Qty: " + numUsers + " Loops Nums:[" + loopCount + " : " + i + " ] Update status:{" + resp.getStatusLine().toString() + "}");
                    Thread.sleep(2000);
                }
            } else if (method.equals("single")) {
                if (!args[2].isEmpty() && !args[3].isEmpty()) {
                    userList = db.userInfoList.getAll("/" + args[0].toUpperCase(), Long.valueOf(args[2]), Long.valueOf(args[3]));
                    // deleteUsers(userList,logdel);
                    List<IntercomBulkData> users = IntercomUserFormat.CreateBulkData(userList);
                    Gson obj = new Gson();
                    String jsonString = "{\"items\":" + obj.toJson(users) + "}";
                    HttpMyAdapter httpAdapter = new HttpMyAdapter(args[0]);
                    HttpResponse resp = httpAdapter.post(jsonString);
                    log.info("[ " + args[2] + " : " + args[3] + " ] {Qty: 50 Loops Nums:[ 1 ] Update status:{ " + resp.getStatusLine().toString() + " } JSON String: "+jsonString.replaceAll("(\\r\\n|\\n)", ""));
                } else {
                    log.info("Users limit should be init: {from  to}");
                }
            }
        }
        else
        {
            System.out.println("please input parameters: AppShortName [method:bulk/single default=bulk] [limit: from to (method=single)]");
        }
    }
    public static void removeDuplicates(Logger log)
    {
        //Set<String> usersNamesList = db.userInfoList.getAllUsersNames("/PO");
        Set<String> usersNamesList = db.userInfoList.getAllNotExistsUsersByEmail();
        Intercom.setApiKey("");
        Intercom.setAppID("");
        Map<String,String> params = new HashMap<>();
        System.out.println(usersNamesList.size());
        int i=0;
        for(String item : usersNamesList)
        {

            params.put("email",item);
            User us = null;
            try{
                us = User.find(params);
                if (us != null)
                {
                        log.info("Found in PO -> UserName:"+item+" and successfully deleted");
                        User.delete(us.getId());
                        System.out.printf("%d. User: %s Found and Deleted\n", i++, item);
                }
            }catch(NotFoundException e)
            {
                us = null;
                log.info("Not Found in PO -> UserName:"+item);
                System.out.printf("%d. User Not Found:%s\n", i++, item);
            }
        }


    }
    public static void deleteUserByEmail(String email, Logger log)
    {
        //vvanderveen@pinkoffice.com
        Intercom.setApiKey("");
        Intercom.setAppID("");
        Map<String,String> params = new HashMap<>();
        params.put("email", email);
        User us = null;
                try{
                    us =User.find(params);
                    if(us!=null)
                    {
                        log.info("Found in PO -> UserName:"+us.getUserId()+" and successfully deleted");
                        User.delete(us.getId());
                        System.out.printf("User: %s Found and Deleted\n", us.getUserId());
                    }
                }catch (NotFoundException e)
                {
                    us = null;
                    log.info("Not Found in PO -> "+email);
                    System.out.printf("User Not Found:%s\n", email);
                }
    }
    public static void deleteUsers(List<UserInfo> uList,Logger log)
    {
        Intercom.setApiKey("");
        Intercom.setAppID("");
        Map<String,String> params = new HashMap<>();
        for(UserInfo item : uList)
        {
            params.put("user_id",item.getUserName());
            User us = null;
            try {
                us = User.find(params);
            }catch(NotFoundException e)
            {
                us=null;
                log.info("Not Found in PO -> UserName:"+item.getUserName()+" FirstName:"+item.getFirstName()+" LastName:"+item.getLastName());
            }
            if (us != null)
            {
                //User.delete(us.getId());
                log.info("Found in PO -> UserName:"+item.getUserName()+" FirstName:"+item.getFirstName()+" LastName:"+item.getLastName());
            }
        }
    }

    public static void updateUserId(String oldUserId, String newUserId, Logger log)
    {
        Intercom.setApiKey("");
        Intercom.setAppID("");
        Map<String,String> params = new HashMap<>();
        params.put("user_id", oldUserId);

        User us = null;
        String oldUID="";
        User newUs = null;
        try{
            us = User.find(params);
            oldUID = us.getUserId();
            us.setUserId(newUserId);
            newUs = User.update(us);
        }catch(NotFoundException e)
        {
            us=null;
            log.info("Not Found in PO -> UserName:"+oldUserId);
        }
        if(us!=null)
        {
            log.info("Id:"+us.getId()+" UserId:"+oldUID+" changed to New userId:"+newUs.getUserId());
        }
    }
}
