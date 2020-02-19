package com.mooc.utils;

import javax.imageio.stream.ImageInputStream;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;

public class Getdatatime {
//    public static void main(String[] args) {
//       String s= getTime();
//       System.out.println(s);
//    }
    public static String getTime(){
        long l=System.currentTimeMillis();
        DateFormat date;
        date = new SimpleDateFormat("YYYY-MM-dd-HH-mm-ss");
        return date.format(new Date(l));

    }

}
