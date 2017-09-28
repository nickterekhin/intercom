package http;

import io.intercom.api.Intercom;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;


/**
 * Created by Nick on 06.11.2014.
 */
public class HttpMyAdapter {
    private static final String USER_AGENT = Intercom.USER_AGENT;
    private  URI uri;
    private CloseableHttpClient client;
    private HttpPost post;
    private String appName="PO";
    //need to be fill by API's secret keys
    private String PO_API_KEY="";
    private String CO_API_KEY="";
    private String CO_API_ID="";
    private String FA_API_KEY="";
    private String FA_API_ID="";

    public HttpMyAdapter(String appName) {
        this.appName=appName;
        client = HttpClients.createDefault();
        try {
            this.uri = new URI("https://api.intercom.io/bulk/users");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
            post = new HttpPost(this.uri);
        setHeaders();
    }

    public void setHeaders()
    {
       post.addHeader("Accept","application/json");
       post.addHeader("User-Agent",USER_AGENT);
       authenticationInit();
       post.addHeader("Content-type","application/json");
       post.addHeader("Accept-Encoding","gzip,deflate");
       post.addHeader("Accept-Language","ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4");


    }
    public void authenticationInit()
    {
        String authStr="";
        switch (appName)
        {
            case "PO":
                    authStr = PO_API_KEY;
                break;
            case "CO":
                    String tmpAuthCO = CO_API_ID+":"+CO_API_KEY;
                    authStr = Base64.encodeBase64String(tmpAuthCO.getBytes());
                break;
            case "FA":
                String tmpAuthFA = FA_API_ID+":"+FA_API_KEY;
                    authStr = Base64.encodeBase64String(tmpAuthFA.getBytes());
                break;
        }
        post.addHeader("Authorization","Basic "+authStr);
    }
    public HttpResponse post(String jobj)
    {
        try {
            StringEntity stringEntity = new StringEntity(jobj,"utf-8");
            stringEntity.setContentType("application/json");
            post.setEntity(stringEntity);
            return client.execute(post);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
