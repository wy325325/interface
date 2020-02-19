package com.mooc.utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class parameterization {
    private static final String not_existed_passwd="#not_existed_passwd";
    private static final String not_existed_phone="#not_existed_tel";
    public static String getNot_existed_tel_parameter(String data){
        Pattern p=Pattern.compile(not_existed_phone);
        Matcher m=p.matcher(data);
        return m.replaceAll("15278909811");


    }
    public static String getNot_existed_passwd_parameter(String data){
        Pattern p=Pattern.compile(not_existed_passwd);
        Matcher m=p.matcher(data);
        return m.replaceAll("123456");
    }
    public static String login_parameter(String data){

        data=getNot_existed_passwd_parameter(data);
        return getNot_existed_tel_parameter(data);
    }

//    public static void main(String[] args) {
//        String data="{\"mobilephone\":\"#not_existed_tel\",\"pwd\":\"#not_existed_passwd\"}";
//
//        data=login_parameter(data);
//        System.out.println(data);
//        System.out.println(not_existed_passwd);
//    }
}
