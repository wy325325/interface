package com.mooc.utils;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class Mysqlhandle {
//    public static void main(String[] args) {
//        Map<String,Object> m=getMysql("select * from user");
//        int s=(Integer)m.get("id");
//        String t=String.valueOf(s);
//        s=Integer.valueOf(t);
//
//
//        System.out.println(s+"                          "+t);
//    }

    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/lucky?serverTimezone=UTC&characterEncoding=utf-8";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "123456";



    public static Map<String,Object> getMysql(String sql) {
        Map<String,Object> map=new HashMap<String, Object>();
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
//            while(rs.next()){
//                // 通过字段检索
//                int id  = rs.getInt("id");
//                String name = rs.getString("username");
//                String age = rs.getString("age");
//
//                // 输出数据
//                System.out.print("ID: " + id);
//                System.out.print(", 站点名称: " + name);
//                System.out.print(", 站点 URL: " + age);
//                System.out.print("\n");
//            }
            ResultSetMetaData rd=rs.getMetaData();
//
            if(rs.next()){
               for(int i=1;i<=rd.getColumnCount();i++){
                   map.put(rd.getColumnName(i),rs.getObject(i));


               }
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
        return map;
    }

}
