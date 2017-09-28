package com.crfeb.tbmpt.commons.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/***
 * 解析 Excel 文件  通用类
 * @author wpg
 * 
 * 2016-12-14
 *
 */
public class ParseExcelFile {

	/***
	 * 解析 Excel 文件  通用方法
	 * 有标题 方式
	 * @param filepath 文件路径
	 * @return
	 * @throws Exception
	 * 2016-12-14
	 */
	public static List<List<Map<String,String>>> readExcelWithTitle(String filepath) throws Exception{
		//获取 文件  扩展名
	    String fileType = filepath.substring(filepath.lastIndexOf(".") + 1, filepath.length());
	    
	    InputStream is = null;
	    Workbook wb = null;
	    try {
	    	
	        is = new FileInputStream(filepath);
	         
	        if (fileType.equals("xls")) {
	        	//处理  .xls 文件
	            wb = new HSSFWorkbook(is);
	        } else if (fileType.equals("xlsx")) {
	        	//处理  .xlsx 文件 
	            wb = new XSSFWorkbook(is);
	        } else {
	            throw new Exception("读取的不是excel文件");
	        }
	         
	        List<List<Map<String,String>>> result = new ArrayList<List<Map<String,String>>>();//对应excel文件
	        // 获取  wb 长度 (多少行)
	        int sheetSize = wb.getNumberOfSheets();
	        for (int i = 0; i < sheetSize; i++) {//遍历sheet页
	        	//获取 一页  数据
	            Sheet sheet = wb.getSheetAt(i);
	            List<Map<String, String>> sheetList = new ArrayList<Map<String, String>>();//对应sheet页
	             
	            List<String> titles = new ArrayList<String>();//放置所有的标题
	            //sheet 长度（行）
	            int rowSize = sheet.getLastRowNum() + 1;
	            for (int j = 0; j < rowSize; j++) {//遍历行
	            	//获取  一行 数据
	                Row row = sheet.getRow(j);
	                if (row == null) {//略过空行
	                    continue;
	                }
	                // row 长度 （列）
	                int cellSize = row.getLastCellNum();//行中有多少个单元格，也就是有多少列
	                if (j == 0) {//第一行是标题行
	                    for (int k = 0; k < cellSize; k++) {
	                        Cell cell = row.getCell(k);
	                        titles.add(cell.toString());
	                    }
	                } else {//其他行是数据行
	                	//对应一个数据行
	                    Map<String, String> rowMap = new HashMap<String, String>();
	                    //根据  标题 的 长度  遍历  获取  数据（列）
	                    for (int k = 0; k < titles.size(); k++) {
	                        Cell cell = row.getCell(k);
	                        //获取  标题  做 key
	                        String key = titles.get(k);
	                        String value = null;
	                        if (cell != null) {
	                        	//获取某一行  对应某个标题那列 的 值
	                            value = cell.toString();
	                            value=value.trim();
	                        }
	                        //获取 ‘首字母’
	            			char initial = key.charAt(0);
	                        switch(initial)
	                        { 
	                          case '*' : 
	                        	  key = key.substring(1);
	    		                  break;
	    		            }
	                        rowMap.put(key, value);
	                    }
	                    sheetList.add(rowMap);
	                }
	            }
	            result.add(sheetList);
	        }
	         
	        return result;
	    } catch (FileNotFoundException e) {
	        throw e;
	    } finally {
	    	//关闭
	        if (wb != null) {
	            wb.close();
	        }
	        if (is != null) {
	            is.close();
	        }
	    }
	}
	
	/**
	 * 读取excel文件参考方法，大家根据自己的需要写自已的方法
	 * @param fileName
	 * @throws IOException
	 */
	public static void readXlsxIs(InputStream is) throws IOException {
		//String fileName = "D:\\excel\\xlsx_test.xlsx";
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		
		// 循环工作表Sheet
		for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
			if (xssfSheet == null) {
				continue;
			}
			
			// 循环行Row
			for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow == null) {
					continue;
				}
				
				// 循环列Cell
				for (int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++) {
					XSSFCell xssfCell = xssfRow.getCell(cellNum);
					if (xssfCell == null) {
						continue;
					}
					System.out.print("   " + getValue(xssfCell));
				}
				System.out.println();
			}
		}
	}
	/**
	 * 读取excel文件参考方法，大家根据自己的需要写自已的方法
	 * @param fileName
	 * @throws IOException
	 */
	public static void readXlsx(String fileName) throws IOException {
        //String fileName = "D:\\excel\\xlsx_test.xlsx";
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileName);
 
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
 
            // 循环行Row
            for (int rowNum = 0; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow == null) {
                    continue;
                }
 
                // 循环列Cell
                for (int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++) {
                    XSSFCell xssfCell = xssfRow.getCell(cellNum);
                    if (xssfCell == null) {
                        continue;
                    }
                    System.out.print("   " + getValue(xssfCell));
                }
                System.out.println();
            }
        }
    }
	
	 public static String getValue(XSSFCell xssfCell) {
	        if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {
	            return String.valueOf(xssfCell.getBooleanCellValue());
	        } else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC) {
	            return String.valueOf(xssfCell.getNumericCellValue());
	        } else {
	        	xssfCell.setCellType(Cell.CELL_TYPE_STRING);
	            return String.valueOf(xssfCell.getStringCellValue());
	        }
	    }


	
	public static void main(String[] args) {
		try {
			readXlsx("f:1.xlsx");
//			List<List<Map<String,String>>> test = readExcelWithTitle("f:1.xlsx");
//			int i = 0;
//			for (List<Map<String, String>> list : test) {
//				System.out.println("第"+i+"行");
//				for (Map<String, String> map : list) {
//					for (String str: map.keySet()) {
//						System.out.println(str+"====="+map.get(str));
//					}
//				}
//				i++;
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
