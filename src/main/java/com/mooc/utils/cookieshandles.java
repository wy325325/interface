package com.mooc.utils;

import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;


import java.io.IOException;
import java.util.List;

public class cookieshandles {

    private static CookieStore cs;
    private static String filename;


    public static void getCookies(DefaultHttpClient client,String filename){
        cs=client.getCookieStore();
        List<Cookie> cookies=cs.getCookies();
        for(Cookie cook:cookies){
            if(cook.getName().equals("login")){
                confighadle c=new confighadle(filename);
                c.writePropers(cook.getName(),cook.getValue());

            }
        }


    }
    public static void setCookies(HttpRequestBase get, String filename){
        confighadle c=new confighadle(filename);
        String s=c.getPropers("login");
        get.setHeader("login",s);





    }
    public static void aaa() throws IOException {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost posturl = new HttpPost("http://127.0.1:8888/hdgetcookies");
        HttpResponse response = client.execute(posturl);
        String result = EntityUtils.toString(response.getEntity(), "utf-8");
        getCookies(client,"src/main/resources/config.properties");

    }
    public static void bbb() throws IOException {
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost getturl = new HttpPost("http://127.0.1:8888/hdget");

//        setCookies(getturl,"src/main/resources/config.properties");
        getturl.setHeader("cookies","login:true");
        HttpResponse response = client.execute(getturl);
        String result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);


    }

    public static void main(String[] args) throws IOException {
//        aaa();
        bbb();





    }


}
