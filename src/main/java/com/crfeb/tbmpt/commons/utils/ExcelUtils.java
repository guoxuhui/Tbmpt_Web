package com.crfeb.tbmpt.commons.utils;

import java.io.UnsupportedEncodingException;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;

import com.crfeb.tbmpt.commons.calc.CalcBhl;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDetailsDto;

/**
 * poi操作excel的工具类
 * @author Administrator
 *
 */
public class ExcelUtils {
	
	//返回一个只有列头的空的sheet页
    public static HSSFWorkbook excelSheet(String[] excelHeader,String sheetName) {    
        HSSFWorkbook wb = new HSSFWorkbook();    
        HSSFSheet sheet = wb.createSheet(sheetName);    
        HSSFRow row = sheet.createRow((int) 0);    
        CellStyle headerCs = ExportExcelUtil.getHeaderCellStyle(wb);
		for (int i = 0; i < excelHeader.length; i++) {
			Cell c = row.createCell(i);
			c.setCellStyle(headerCs);
			sheet.setColumnWidth(i, ((String) excelHeader[i]).length() * 256 * 2 * 2);
			c.setCellValue(excelHeader[i]);
		}
    
        return wb;    
    }    
    
  //返回主表和子表2个sheet页的wb
    public static HSSFWorkbook excelSheet(String[] excelHeader,String sheetName,
    		String[] subHeader,String subSheetName) {    
        HSSFWorkbook wb = new HSSFWorkbook();    
        
        HSSFSheet sheet = wb.createSheet(sheetName);    
        HSSFRow row = sheet.createRow((int) 0);    
        CellStyle headerCs = ExportExcelUtil.getHeaderCellStyle(wb);
    
        //设置主表列头
        for (int i = 0; i < excelHeader.length; i++) {
			Cell c = row.createCell(i);
			c.setCellStyle(headerCs);
			sheet.setColumnWidth(i, ((String) excelHeader[i]).length() * 256 * 2 * 2);
			c.setCellValue(excelHeader[i]);
		}
        //设置子表列头
        HSSFSheet subSheet = wb.createSheet(subSheetName);    
        HSSFRow subrow = subSheet.createRow((int) 0);    
    
        //设置列头
        for (int i = 0; i < subHeader.length; i++) {    
        	Cell c = subrow.createCell(i);
			c.setCellStyle(headerCs);
			subSheet.setColumnWidth(i, ((String) subHeader[i]).length() * 256 * 2 * 2);
			c.setCellValue(subHeader[i]);
        }    
    
        return wb;    
    }
    
    //下载文件时设置文件名为中文
    public static String parseGBK(String sIn) {
		if (sIn == null || sIn.equals(""))
			return sIn;
		try {
			return new String(sIn.getBytes("GBK"), "ISO-8859-1");
		} catch (UnsupportedEncodingException usex) {
			return sIn;
		}
	}

    //自动调整列宽
    public static void resizeWidth(HSSFWorkbook wb,HSSFRow row,HSSFSheet sheet){
    	CellStyle conCs = ExportExcelUtil.getNormalCellStyle(wb);
    	 int columnNum=row.getPhysicalNumberOfCells();
         int rowNum = row.getRowNum();
         for(int i=0;i<columnNum;i++){
         	for(int j=1;j<rowNum+1;j++){
         		HSSFRow r = sheet.getRow(j);
         		Cell cell = r.getCell(i);
         		if (null != cell)
         			cell.setCellStyle(conCs);
         	}
         }
    }
    
    
    //生成监测明细数据时的通用方法
    public static void makeDetailSheet(int j,HSSFRow subrow,HSSFSheet subsheet,DmcjJcInfoDetailsDto detail){
    	CalcBhl.calc(detail);//计算变化率等信息
    	
    	subrow.createCell(0).setCellValue(j + 1);// 行号
		String jcdno = detail.getJcdNo();
		if (null != jcdno)
			subrow.createCell(1).setCellValue(jcdno);
		Float bcgc = detail.getBcgc();
		if (null != bcgc)
			subrow.createCell(2).setCellValue(Double.parseDouble(String.valueOf(bcgc)));
		
		subrow.createCell(3).setCellValue(Double.parseDouble(String.valueOf(detail.getScgc())));
		subrow.createCell(4).setCellValue(Double.parseDouble(String.valueOf(detail.getCsgc())));
		subrow.createCell(5).setCellValue(Double.parseDouble(String.valueOf(detail.getBcbhl())));
		subrow.createCell(6).setCellValue(Double.parseDouble(String.valueOf(detail.getLjbhl())));
		subrow.createCell(7).setCellValue(Double.parseDouble(String.valueOf(detail.getBhsl())));
		
		String subgcbh = detail.getGcbh();
		if (null != subgcbh)
			subrow.createCell(8).setCellValue(subgcbh);
		String qujian = detail.getQujianName();
		if (null != qujian)
			subrow.createCell(9).setCellValue(qujian);
		String xianlu = detail.getXianluName();
		if (null != xianlu)
			subrow.createCell(10).setCellValue(xianlu);
		String weizhi = detail.getWeizhi();
		if (null != weizhi)
			subrow.createCell(11).setCellValue(weizhi);
		String licheng = detail.getLicheng();
		if (null != licheng)
			subrow.createCell(12).setCellValue(licheng);
		String subremarks = detail.getRemarks();
		if (null != subremarks)
			subrow.createCell(13).setCellValue(subremarks);
    }
}
