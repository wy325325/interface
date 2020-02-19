import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class gh {



    @Test
    public void abc() throws IOException {
        DefaultHttpClient client=new DefaultHttpClient();
        HttpPost posturl = new HttpPost("http://127.0.1:8888/hdgetcookies");

        HttpResponse response = client.execute(posturl);


        String result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
    }
    @Test
    public void bbb() throws IOException {
        DefaultHttpClient client=new DefaultHttpClient();

        HttpGet posturl = new HttpGet("http://127.0.1:8888/hdget");


        HttpResponse response = client.execute(posturl);
        String result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
    }



}
