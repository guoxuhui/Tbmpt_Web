package com.crfeb.tbmpt.commons.utils;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.beans.PropertyDescriptor;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

/**
 * 表格数据导出工具类，使用Pdf文件导出
 * @author：smxg
 * @date：2016-10-06 11:12
 */
public class ExportPdfUtil {
	private static Logger log = LoggerFactory.getLogger(ExportPdfUtil.class);
	private static final String CONTYPE_PDF = "application/pdf";
	private static final String FONT_STSONG_LIGHT = "STSong-Light";
	private static final String ENCOD_GB_UCS2_H = "UniGB-UCS2-H";

	/**
	 * 创建pdf并输出
	 * @param response
	 * @param exportDto
	 * @param resultList
	 * @param clazz
	 * @throws Exception
	 */
	public static void writePdfToClient(HttpServletResponse response, ExportDto exportDto,
			List<? extends Object> resultList, Class<?> clazz) throws Exception {
		OutputStream os = getWriteOutputStream(response, CONTYPE_PDF, exportDto);
		Document doc = createPageA4Doc(30.0F, 30.0F, 50.0F, 50.0F);
		try {
			PdfWriter.getInstance(doc, os);
			doc.open();
			doc.add(createTable(exportDto, resultList, clazz));
			os.flush();
		} catch (Exception e) {
			log.error("导出Pdf出错，" + e.getMessage());

			throw e;
		} finally {
			doc.close();
			os.close();
		}
	}

	/**
	 * 创建pdf并输出
	 * @param response
	 * @param exportDto
	 * @param resultList
	 * @throws Exception
	 */
	public static void writePdfToClient(HttpServletResponse response, ExportDto exportDto,
			List<Map<String, Object>> resultList) throws Exception {
		OutputStream os = getWriteOutputStream(response, CONTYPE_PDF, exportDto);
		Document doc = createPageA4Doc(30.0F, 30.0F, 50.0F, 50.0F);
		try {
			PdfWriter.getInstance(doc, os);
			doc.open();
			doc.add(createTable(exportDto, resultList));
			os.flush();
		} catch (Exception e) {
			log.error("导出Pdf出错，" + e.getMessage());

			throw e;
		} finally {
			doc.close();
			os.close();
		}
	}

	/**
	 * 创建Document
	 * @param marginLeft
	 * @param marginRight
	 * @param marginTop
	 * @param marginBottom
	 * @return
	 */
	private static Document createPageA4Doc(float marginLeft, float marginRight, float marginTop, float marginBottom) {
		Document doc = new Document(PageSize.A4, marginLeft, marginRight, marginTop, marginBottom);
		return doc;
	}

	/**
	 * 创建表格
	 * @param exportDto
	 * @param resultList
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	private static PdfPTable createTable(ExportDto exportDto, List<? extends Object> resultList, Class<?> clazz)
			throws Exception {
		if (exportDto == null) {
			throw new IllegalArgumentException("参数不能为空");
		}

		List<String> titleList = exportDto.getTitleList();
		int columns = titleList.size();

		PdfPTable t = new PdfPTable(columns);

		for (String title : titleList) {
			t.addCell(getHeaderCell(title, 12));
		}

		t.setHeaderRows(1);

		List<String> fieldList = exportDto.getFieldList();

		List<Method> methodList = new ArrayList<Method>(columns);
		PropertyDescriptor pd = null;
		Object cellValue = null;

		for (int i = 0; i < resultList.size(); i++) {
			Object obj = resultList.get(i);

			for (int j = 0; j < fieldList.size(); j++) {
				if (i == 0) {
					pd = BeanUtils.getPropertyDescriptor(clazz, (String) fieldList.get(j));
					methodList.add(pd.getReadMethod());
				}

				cellValue = ((Method) methodList.get(j)).invoke(obj, new Object[0]);
				if (cellValue != null)
					t.addCell(getNormalCell(cellValue.toString(), 10));
				else {
					t.addCell(getNormalCell("", 10));
				}
			}
		}

		return t;
	}

	/**
	 * 创建表格
	 * @param exportDto
	 * @param resultList
	 * @return
	 * @throws Exception
	 */
	private static PdfPTable createTable(ExportDto exportDto, List<Map<String, Object>> resultList) throws Exception {
		if (exportDto == null) {
			throw new IllegalArgumentException("参数不能为空");
		}

		List<String> titleList = exportDto.getTitleList();

		int columns = titleList.size();
		PdfPTable t = new PdfPTable(columns);

		for (String title : titleList) {
			t.addCell(getHeaderCell(title, 12));
		}

		t.setHeaderRows(1);

		List<String> fieldList = exportDto.getFieldList();
		Object cellValue = null;
		for (int i = 0; i < resultList.size(); i++) {
			Map<String, Object> dmap = resultList.get(i);

			for (String field : fieldList) {
				cellValue = dmap.get(field);

				if (cellValue != null)
					t.addCell(getNormalCell(cellValue.toString(), 10));
				else
					t.addCell(getNormalCell("", 10));
			}
		}
		return t;
	}

	/**
	 * 获取输出
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

		String fileName = new String(exportDto.getFileName().getBytes("GBK"), "ISO-8859-1");
		response.addHeader("Content-Disposition", "attachment; filename=" + fileName + extName);

		return response.getOutputStream();
	}

	/**
	 * 创建单元格
	 * @param value
	 * @param valign
	 * @param halign
	 * @param font
	 * @return
	 */
	private static PdfPCell createCell(String value, int valign, int halign, Font font) {
		PdfPCell c = new PdfPCell();

		c.setVerticalAlignment(valign);
		c.setHorizontalAlignment(halign);

		Paragraph p = new Paragraph(value, font);
		p.setAlignment(halign);

		c.addElement(p);

		return c;
	}

	/**
	 * 获取表头单元格
	 * @param value
	 * @param fontSize
	 * @return
	 */
	private static PdfPCell getHeaderCell(String value, int fontSize) {
		Font font = getHeaderFont(fontSize);
		PdfPCell c = createCell(value, 4, 1, font);
		c.setPaddingBottom(10.0F);
		c.setBackgroundColor(BaseColor.LIGHT_GRAY);

		return c;
	}

	/**
	 * 获取普通单元格
	 * @param value
	 * @param fontSize
	 * @return
	 */
	private static PdfPCell getNormalCell(String value, int fontSize) {
		Font font = getNormalFont(fontSize);
		PdfPCell c = createCell(value, 4, 1, font);
		c.setPaddingBottom(5.0F);
		return c;
	}

	/**
	 * 创建普通单元格
	 * @param value
	 * @param fontSize
	 * @param halign
	 * @return
	 */
	public static PdfPCell getNormalCell(String value, int fontSize, int halign) {
		Font font = getNormalFont(fontSize);
		PdfPCell c = createCell(value, 4, halign, font);

		return c;
	}

	/**
	 * 创建字体
	 * @param fontName
	 * @param encoding
	 * @param fontSize
	 * @param style
	 * @param color
	 * @return
	 */
	private static Font createFont(String fontName, String encoding, float fontSize, int style, BaseColor color) {
		Font f = FontFactory.getFont(fontName, encoding, false, fontSize, style, color);
		return f;
	}

	/**
	 * 创建字体
	 * @param fontSize
	 * @param style
	 * @param color
	 * @return
	 */
	private static Font createChineseFont(float fontSize, int style, BaseColor color) {
		return createFont(FONT_STSONG_LIGHT, ENCOD_GB_UCS2_H, fontSize, style, color);
	}

	/**
	 * 创建字体
	 * @param fontSize
	 * @return
	 */
	private static Font getHeaderFont(int fontSize) {
		return createChineseFont(fontSize, 1, BaseColor.BLACK);
	}

	/**
	 * 创建字体
	 * @param fontSize
	 * @return
	 */
	private static Font getNormalFont(int fontSize) {
		return createChineseFont(fontSize, 0, BaseColor.BLACK);
	}
}