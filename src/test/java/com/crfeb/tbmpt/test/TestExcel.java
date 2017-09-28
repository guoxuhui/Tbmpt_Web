package com.crfeb.tbmpt.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.crfeb.tbmpt.commons.utils.ExcelUtils;
import com.crfeb.tbmpt.dmcjjc.dd.model.DdInfo;

public class TestExcel {
	public static void main(String[] args) {
		String[] header = { "Sno中文", "Name", "Age"};
		String sheetName = "基础数据";
		
		List<DdInfo> list = new ArrayList<DdInfo>();
		DdInfo d1 = new DdInfo();
		DdInfo d2 = new DdInfo();
		DdInfo d3 = new DdInfo();
		d1.setDdName("中文");
		d1.setTypeName("测试1123");
		d2.setDdName("abcd的");
		d2.setTypeName("测试1123567");
		d3.setDdName("dd");
		d3.setSortNum(1);
        list.add(d1);    
        list.add(d2);    
        list.add(d3);
        
        HSSFWorkbook wb = ExcelUtils.excelSheet(header, sheetName);
        
        HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row = sheet.getRow(0);
        for (int i = 0; i < list.size(); i++) {    
          row = sheet.createRow(i + 1);    
          DdInfo info = list.get(i);    
          row.createCell(0).setCellValue(info.getDdName());    
          row.createCell(1).setCellValue(info.getTypeName());
          Integer sort = info.getSortNum();
          if(null != sort)
        	  row.createCell(2).setCellValue(sort);
          sheet.autoSizeColumn((short)0);
          sheet.autoSizeColumn((short)1);
          sheet.autoSizeColumn((short)2);
      }    
        
        File file = new File("c:/2.xls");
       
        OutputStream out = null ; // 准备好一个输出的对象
        try {
			out = new FileOutputStream(file)  ;
			wb.write(out);  
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
}
