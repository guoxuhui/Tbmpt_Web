package com.crfeb.tbmpt.commons.utils;

import java.beans.PropertyDescriptor;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.Region;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;



/**
 * 表格数据导出工具类，使用Excel文件导出
 * @author：smxg
 * @date：2016-10-06 11:12
 */
public class ExportExcelUtil {
	private static Logger log = LoggerFactory.getLogger(ExportExcelUtil.class);
	private static final String CONTYPE_XLS = "application/ms-excel";
	private static final String XLSX = "xlsx";
	private static final String XLS = "xls";

	public static void writeExcelToClient(HttpServletResponse response, ExportDto exportDto,
			List<? extends Object> resultList, Class<?> clazz) throws Exception {
		OutputStream os = getWriteOutputStream(response, CONTYPE_XLS, exportDto);
		try {
			Workbook wb = createWorkbook(exportDto, resultList, clazz);

			wb.write(os);
			os.flush();
		} catch (Exception e) {
			log.error("导出Excel出错，" + e.getMessage());

			throw e;
		} finally {
			os.close();
		}
	}

	/**
	 * 根据列表map输出Excel文件
	 * @param response
	 * @param exportDto
	 * @param resultList
	 * @throws Exception
	 */
	public static void writeExcelToClient(HttpServletResponse response, ExportDto exportDto,
			List<Map<String, Object>> resultList) throws Exception {
		OutputStream os = getWriteOutputStream(response, CONTYPE_XLS, exportDto);
		try {
			Workbook wb = createWorkbook(exportDto, resultList);

			wb.write(os);
			os.flush();
		} catch (Exception e) {
			log.error("导出Excel出错，" + e.getMessage());

			throw e;
		} finally {
			os.close();
		}
	}

	/**
	 * 创建并写入Workbook XLSX XLS
	 * @param exportDto
	 * @param resultList
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	private static Workbook createWorkbook(ExportDto exportDto, List<? extends Object> resultList, Class<?> clazz)
			throws Exception {
		Workbook wb = null;

		if (XLSX.equalsIgnoreCase(exportDto.getFileType()))
			wb = new SXSSFWorkbook(100);
		else if (XLS.equalsIgnoreCase(exportDto.getFileType())) {
			wb = new HSSFWorkbook();
		}

		wb = writeExcel(wb, exportDto, resultList, clazz);

		return wb;
	}

	/**
	 * 创建并写入Workbook
	 * @param wb
	 * @param exportDto
	 * @param resultList
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	private static Workbook writeExcel(Workbook wb, ExportDto exportDto, List<? extends Object> resultList,
			Class<?> clazz) throws Exception {
		if (wb == null) {
			throw new IllegalArgumentException("参数Workbook不允许为NULL");
		}

		Sheet sheet = wb.createSheet();

		int rowno = 0;

		Row titleRow = sheet.createRow(rowno++);

		List<String> titleList = exportDto.getTitleList();
		int colSize = titleList.size();

		CellStyle headerCs = getHeaderCellStyle(wb);
		for (int i = 0; i < colSize; i++) {
			Cell c = titleRow.createCell(i);
			c.setCellStyle(headerCs);

			sheet.setColumnWidth(i, ((String) titleList.get(i)).length() * 256 * 2 * 2);

			c.setCellValue((String) titleList.get(i));
		}

		List<String> fieldList = exportDto.getFieldList();
		CellStyle conCs = getNormalCellStyle(wb);

		List<Method> methodList = new ArrayList<Method>(colSize);
		PropertyDescriptor pd = null;
		Object cellValue = null;

		for (int i = 0; i < resultList.size(); i++) {
			Row row = sheet.createRow(rowno++);

			Object obj = resultList.get(i);

			for (int j = 0; j < colSize; j++) {
				Cell c = row.createCell(j);
				c.setCellStyle(conCs);

				if (i == 0) {
					pd = BeanUtils.getPropertyDescriptor(clazz, (String) fieldList.get(j));
					methodList.add(pd.getReadMethod());
				}

				cellValue = ((Method) methodList.get(j)).invoke(obj, new Object[0]);
				if (cellValue != null) {
					c.setCellValue(cellValue.toString());
				}
			}

		}

		return wb;
	}
	/**
	 * 创建Workbook
	 * @param exportDto
	 * @param resultList
	 * @return
	 * @throws Exception
	 */
	private static Workbook createWorkbook(ExportDto exportDto, List<Map<String, Object>> resultList) throws Exception {
		Workbook wb = null;

		if (XLSX.equalsIgnoreCase(exportDto.getFileType()))
			wb = new SXSSFWorkbook(100);
		else if (XLS.equalsIgnoreCase(exportDto.getFileType())) {
			wb = new HSSFWorkbook();
		}

		Sheet sheet = wb.createSheet();
		int rowno = 0;

		Row titleRow = sheet.createRow(rowno++);

		List<String> titleList = exportDto.getTitleList();
		int colSize = titleList.size();

		CellStyle headerCs = getHeaderCellStyle(wb);
		for (int i = 0; i < colSize; i++) {
			Cell c = titleRow.createCell(i);
			c.setCellStyle(headerCs);

			sheet.setColumnWidth(i, ((String) titleList.get(i)).length() * 256 * 2 * 2);

			c.setCellValue((String) titleList.get(i));
		}

		List<String> fieldList = exportDto.getFieldList();
		Object cellValue = null;
		CellStyle conCs = getNormalCellStyle(wb);
		for (int i = 0; i < resultList.size(); i++) {
			Row row = sheet.createRow(rowno++);

			Map<String, Object> dmap = resultList.get(i);

			for (int j = 0; j < fieldList.size(); j++) {
				Cell c = row.createCell(j);
				c.setCellStyle(conCs);
				
				cellValue = dmap.get(fieldList.get(j));
				if (cellValue != null) {
					c.setCellValue(cellValue.toString());
				}
			}
		}

		return wb;
	}

	/**
	 * 获取输入流
	 * @param response
	 * @param contentType
	 * @param exportDto
	 * @return
	 * @throws Exception
	 */
	private static OutputStream getWriteOutputStream(HttpServletResponse response, String contentType,
			ExportDto exportDto) throws Exception {
		response.reset();
		response.setContentType(contentType);

		String extName = "." + exportDto.getFileType();

		response.addHeader("Content-Disposition",
				"attachment; filename=" + new String(exportDto.getFileName().getBytes("GBK"), "ISO-8859-1") + extName);

		return response.getOutputStream();
	}

	/**
	 * 表头单元格样式
	 * @param wb
	 * @return
	 */
	public static CellStyle getHeaderCellStyle(Workbook wb) {
		if (wb == null) {
			throw new IllegalArgumentException("参数Workbook不允许为NULL");
		}

		CellStyle cs = wb.createCellStyle();

		cs.setAlignment((short) 2);
		cs.setVerticalAlignment((short) 1);

		cs.setFont(getHeaderFont(wb, (short) 10));

		cs.setWrapText(true);

		cs.setFillBackgroundColor(IndexedColors.AQUA.getIndex());
		cs.setFillPattern((short) 9);

		cs.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		cs.setFillPattern((short) 1);

		cs.setBorderBottom((short) 1);
		cs.setBorderLeft((short) 1);
		cs.setBorderRight((short) 1);
		cs.setBorderTop((short) 1);

		return cs;
	}

	/**
	 *  普通单元格样式
	 * @param wb
	 * @return
	 */
	public static CellStyle getNormalCellStyle(Workbook wb) {
		if (wb == null) {
			throw new IllegalArgumentException("参数Workbook不允许为NULL");
		}

		CellStyle cs = wb.createCellStyle();

		cs.setAlignment((short) 2);
		cs.setVerticalAlignment((short) 1);

		cs.setFont(getNormalFont(wb, (short) 10));

		cs.setWrapText(true);

		cs.setBorderBottom((short) 1);
		cs.setBorderLeft((short) 1);
		cs.setBorderRight((short) 1);
		cs.setBorderTop((short) 1);

		return cs;
	}

	/**
	 * 创建表头字体样式
	 * @param wb
	 * @param fontSize
	 * @return
	 */
	private static Font getHeaderFont(Workbook wb, short fontSize) {
		return createFont(wb, "宋体", fontSize, true);
	}

	/**
	 * 创建普通字体样式
	 * @param wb
	 * @param fontSize
	 * @return
	 */
	private static Font getNormalFont(Workbook wb, short fontSize) {
		return createFont(wb, "宋体", fontSize, false);
	}

	/**
	 * 创建字体
	 * @param wb
	 * @param fontName
	 * @param fontSize
	 * @param isBold
	 * @return
	 */
	private static Font createFont(Workbook wb, String fontName, short fontSize, boolean isBold) {
		if (wb == null) {
			throw new IllegalArgumentException("参数Workbook不允许为NULL");
		}

		Font f = wb.createFont();
		f.setFontName(fontName);
		f.setFontHeightInPoints(fontSize);

		if (isBold)
			f.setBoldweight((short) 700);
		else {
			f.setBoldweight((short) 400);
		}

		return f;
	}
}