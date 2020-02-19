package com.mooc.utils;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class exelhandle {
    public static void main(String[] args) {
        Object[][] objects=exelhandle.dataMethod("src/data/testcase.xlsx","register");


//		Object[][] o=dataMethod("C:\\Users\\78762\\Desktop\\case\\dddgg.xls");
//		writeExel("C:\\Users\\78762\\Desktop\\case\\abc.xls");

//		writeExel("C:\\Users\\78762\\Desktop\\tool\\dddg.xls");



    }

    public static List<Map<String, String>> readXlsx(String fileName,String sheetname) {


        XSSFWorkbook xssfWorkbook=null;
        try {

            InputStream in=new FileInputStream(fileName);
            xssfWorkbook = new XSSFWorkbook(in);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 循环工作表Sheet
        XSSFSheet xssfSheet = xssfWorkbook.getSheet(sheetname);
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        // 循环行Row
        XSSFRow rowTitleRow =xssfSheet.getRow(0);
        for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
            XSSFRow xssfRow = xssfSheet.getRow(rowNum);

            if (xssfRow == null) {
                continue;
            }
            Map<String, String> map = new HashMap<String, String>();
            // 循环列Cell
            for (int cellNum = 0; cellNum <rowTitleRow.getLastCellNum(); cellNum++) {
                XSSFCell xssfCell = xssfRow.getCell(cellNum);
                XSSFCell xssfCellTitleCell = rowTitleRow.getCell(cellNum);
                map.put(getValue(xssfCellTitleCell), getValue(xssfCell));

            }
            list.add(map);

        }
        return list;
    }
    //    @SuppressWarnings("static-access")
    private static String getValue(XSSFCell xssfCell) {
        if (xssfCell ==null){
            return ""; }
        if (xssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfCell.getBooleanCellValue());
        } else if (xssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            String s= String.valueOf(xssfCell.getNumericCellValue());

            return s.substring(0,s.length()-2);
        }
        else {
            return String.valueOf(xssfCell.getStringCellValue());
        }
    }

    //列表转换数组
    public static Object[][] dataMethod(String fileName,String sheetname){
        List<Map<String, String>> result =readXlsx(fileName,sheetname);

        Object[][] files = new Object[result.size()][];
        for(int i=0; i<result.size(); i++){
            files[i] = new Object[]{result.get(i)};
        }
        return files;
    }

    public static void writeExel(String filename,String sheetname,int row,String Actual,String result) throws FileNotFoundException {
        XSSFWorkbook xssfWorkbook=null;


        OutputStream out;
        out=null;
        InputStream in=null;
        try {
            in=new FileInputStream(filename);



            xssfWorkbook=new XSSFWorkbook(in);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


        XSSFSheet xssfSheet = xssfWorkbook.getSheet(sheetname);
        List l=readXlsx(filename,sheetname);

        XSSFRow rownum=xssfSheet.getRow(row);



        XSSFCell cell7=rownum.createCell(7);
        cell7.setCellValue(Actual);
        XSSFCell cell8=rownum.createCell(8);
        cell8.setCellValue(result);

        try {
            out=new FileOutputStream(filename);


            xssfWorkbook.write(out);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        try {
//           	out.close();
            xssfWorkbook.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }





    }

}
