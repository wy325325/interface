package com.mooc.utils;

//import com.jayway.jsonpath.JsonPath;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

//import java.io.IOException;
//import java.nio.charset.Charset;

public class Httpclient {
    private static Logger logger = Logger.getLogger(Httpclient.class);
    private DefaultHttpClient client;

    public Httpclient() {

        client = new DefaultHttpClient();


    }

    public String request(String url,String method,String parameter,String data) throws Exception {

        if (method.equals("post")) {

            if (parameter.equals("json")) {


                HttpPost posturl = new HttpPost(url);
//                System.out.println(url);
//                System.out.println(data);

                StringEntity entity = new StringEntity(data.toString(),"utf-8");


                entity.setContentType("application/json");


                posturl.setEntity(entity);
                HttpResponse response = client.execute(posturl);
                String result = EntityUtils.toString(response.getEntity(), "utf-8");

                return result;
            } else {

                HttpPost posturl = new HttpPost(url);
                StringEntity entity = new StringEntity(data,"utf-8");

                entity.setContentType("application/x-www-form-urlencoded");
                posturl.setEntity(entity);
                HttpResponse response = client.execute(posturl);
                String result = EntityUtils.toString(response.getEntity());
                return result;

            }

        } else {

            HttpGet get = new HttpGet(url + "?" + data);
            HttpResponse response = client.execute(get);
            String relut = EntityUtils.toString(response.getEntity());
            System.out.println(relut);
            return relut;

        }

    }

//    public static void main(String[] args) throws Exception {
//
//        String url = "http://test.lemonban.com:8080/futureloan/mvc/api/member/register";
//        String method = "post";
//        String parameter = "json";
//        String data = "{\"mobilephone\":\"15268890987\",\"pwd\":\"123456\"}";
//        String datar ="{\"mobilephone\":\"13562789091\",\"pwd\":\"123456\"}";
//        System.out.println(datar);
//
//
//
//        Httpclient httpclient = new Httpclient();
//         String result=httpclient.request(url, method, parameter, datar);
//
//        System.out.println(result);
//    }


}
