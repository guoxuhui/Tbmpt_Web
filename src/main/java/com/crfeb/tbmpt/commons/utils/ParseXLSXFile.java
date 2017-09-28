package com.crfeb.tbmpt.commons.utils;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;


public class ParseXLSXFile {
	
	public static Map<String, Map<String, String>> readXlsx(String filePath){
		Map<String,Map<String,String>> rmap=new HashMap<String,Map<String,String>>();
		try {
			InputStream is=new FileInputStream(filePath);
			HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
			List<List<String>> result=new ArrayList<List<String>>();
			Sheet hssfSheet = hssfWorkbook.getSheetAt(0);
				for(int rowNum=1;rowNum<=hssfSheet.getLastRowNum();rowNum++){
					Map<String,String> map=new HashMap<String,String>();
					String key=null;
					HSSFRow hssfRow =(HSSFRow) hssfSheet.getRow(rowNum);
					int minColIx=hssfRow.getFirstCellNum();
					int maxColIx=hssfRow.getLastCellNum();
					List<String>rowList=new ArrayList<String>();
					for(int colIx=minColIx;colIx < maxColIx; colIx++){
						
						HSSFCell cell=hssfRow.getCell(colIx);
						if(cell.getCellType()==HSSFCell.CELL_TYPE_BLANK){
							map.put("bcgc", "0");
							continue;
						}
						String s = cell.toString();
						String str=s.trim();
						if (colIx == minColIx) {
							map.put("jcd", str);
							continue;
						}

						map.put("bcgc", str);
						
//						System.out.println(JSON.toJSONString(resutMap));
					}
					rmap.put(map.get("jcd"), map);
					}
	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rmap;
	}

}
