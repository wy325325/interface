package com.mooc.casetest;

import com.jayway.jsonpath.JsonPath;
import com.mooc.utils.Assertion;
import com.mooc.utils.Httpclient;
import com.mooc.utils.exelhandle;
import com.mooc.utils.parameterization;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

@Listeners(com.mooc.utils.AssertListener.class)
public class login {
    private  Httpclient client;
    @BeforeClass
    public void init(){
        client=new Httpclient();
    }
    @DataProvider
    public Object[][] ph(){
        return exelhandle.dataMethod("src/data/testcase.xlsx","register");

    }
    @Test(dataProvider = "ph")
    public void logintest(Map<String,String> m) throws Exception {
        Map<String,String> map=new HashMap<String, String>();
        String id=m.get("case_id");
        String title=m.get("title");
        int row=Integer.valueOf(id);
        String url=m.get("url");
        String data=m.get("data");
        String method=m.get("method");
        String expected=m.get("expected");
        String actual=m.get("actual");
        String reual=m.get("resual");
        String parameter=m.get("parameter");
        String Actual=m.get("actual");
        data=parameterization.login_parameter(data);
        String result=client.request(url,method,parameter,data);

        String code=JsonPath.read(result,"$.code");
        Assertion.verifyEquals(result,code,expected,"src/data/testcase.xlsx","register",row,"登入测试");






    }
    }


