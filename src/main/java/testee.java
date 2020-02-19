import com.mooc.utils.Httpclient;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;

import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class testee {
    private DefaultHttpClient client;
    private CookieStore cookieStore;

    @BeforeClass

    public void init() throws IOException {
        client=new DefaultHttpClient();


    }
    @Test
    public void abc() throws IOException {
        HttpPost posturl = new HttpPost("http://127.0.1:8888/hdgetcookies");
        this.cookieStore= client.getCookieStore();
        HttpResponse response = client.execute(posturl);
        List<Cookie> cookies=cookieStore.getCookies();
        for(Cookie c : cookies) {
            String m = c.getName();
            String s = c.getValue();
            System.out.println(m + " : " + s +"+++++++++++++++++++++++++++++++++++++++++++++++++++++++==");
        }


            String result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
    }
    @Test
    public void bbb() throws IOException {


        HttpGet posturl = new HttpGet("http://127.0.1:8888/hdget");
        client.setCookieStore(cookieStore);

        HttpResponse response = client.execute(posturl);
        String result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
    }



}
