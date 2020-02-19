package com.mooc.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class confighadle {

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		confighadle c=new confighadle("loginconfig");
//		
//		String t=c.getPropers("user");
//		String type=t.split(">")[0];
//		String value=t.split(">")[1];
//		System.out.println(type);
//		System.out.println(value);
//	
//		
//
//	}
	private Properties pos;
	private String filename;
	private InputStream in;
	private BufferedInputStream bs; 
	private OutputStream out;
	private BufferedOutputStream bo;
	
		
	
	public confighadle(String filename) {
		this.filename=filename;
		this.pos=readProperties();
	}
	public Properties readProperties() {
		Properties properties=new Properties();
		try {
			in=new FileInputStream(filename);
			bs=new BufferedInputStream(in);
			
			try {
				properties.load(bs);
				return properties;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			
		
		}finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				bs.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return properties;
	}
	public String getPropers(String key) {
		return pos.getProperty(key);
		
	}
	public void writePropers(String key,String value) {
		try {
			out=new FileOutputStream(filename);
			bo=new BufferedOutputStream(out);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		pos.put(key, value);
		try {

			pos.store(out,"configman");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
//	public static void main(String[] args) {
//		confighadle c=new confighadle("src/main/resources/config.properties");
//		c.writePropers("apsidhh", "iiiiiiiiiiiiiiiiii");
//		String string=c.getPropers("user");
//		System.out.println(string);
//	}

}


