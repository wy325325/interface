package com.mooc.utils;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
@Listeners(com.mooc.utils.AssertListener.class)
public class Assertion {
    public static Logger logger  =  Logger.getLogger(Assertion.class);

    public static boolean flag = true;

    public static List<Error> errors = new ArrayList<Error>();

    public static void verifyEquals(String Actual, String expected,String filename,String sheetname,int row) throws FileNotFoundException {
        try {
            Assert.assertEquals(Actual, expected);
            exelhandle.writeExel(filename,sheetname,row,Actual,"通过");
        } catch (Error e) {
            exelhandle.writeExel("src/data/testcase.xlsx","register",row,Actual,"不通过");

            errors.add(e);
            flag = false;
            logger.error(e);

        }
    }

    public static void verifyEquals(String results,String Actual, String expected,String filename,String sheetname,int row,String message) throws FileNotFoundException {
        try {
            Assert.assertEquals(Actual, expected, message);
            exelhandle.writeExel(filename,sheetname,row,results,"通过");
        } catch (Error e) {
            exelhandle.writeExel("src/data/testcase.xlsx","register",row,results,"不通过");
            errors.add(e);
            flag = false;
            logger.error(e);
        }
    }

    public static void verifyNulls(boolean actual, boolean expected) {

        try {

            Assert.assertEquals(actual, expected);

        } catch (Error e) {

            errors.add(e);
            flag = false;
        }
    }

    public static void verifyNulls(boolean actual, boolean expected, String msg) {

        try {

            Assert.assertEquals(actual, expected, msg);

        } catch (Error e) {

            errors.add(e);
            flag = false;
        }
    }
}
