package com.crfeb.tbmpt.commons.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.project.model.ProRSectionLine;

/**
 * Xml解析工具
 * @author smxg
 *
 */
public class XmlUtils {

	public static Document readConfigXml(){
		InputStream in = XmlUtils.class.getResourceAsStream("/config/config.xml");
		//创建SAXReader对象  
        SAXReader reader = new SAXReader();  
        //读取文件 转换成Document  
        Document document = null;
        try {
			document = reader.read(in);
		} catch (DocumentException e) {
			e.printStackTrace();
		}  
        return document;
	}
	
	/**
	 * 获取项目Element
	 * @param pro
	 * @return
	 */
	public static Element getBaseConfigXml(String proName){
		Element conf = DocumentHelper.createElement("configuration");
		//创建标题
		Element title = conf.addElement("title");
 		title.setText(proName);
 		
 		Element subtitle = conf.addElement("subtitle");
 		subtitle.setText("");
 		
 		Element logo = conf.addElement("logo");
 		logo.setText("assets/images/logo.png");
 		
 		Element style = conf.addElement("style");
 		Element colors = style.addElement("colors");//0x000000,0xFFFFFF,0x99CCFF,0x99CCFF,0xEEEEEE
 		colors.setText("0xFFFFFF,0x333333,0x101010,0x000000,0xFFFFFF");
 		Element alpha = style.addElement("alpha");
 		alpha.setText("0.8");
 		
 		Element geometryservice = conf.addElement("geometryservice");
 		geometryservice.addAttribute("url", SysPropertieUtil.getInstince().getMapPath()+"/rest/services/Utilities/Geometry/GeometryServer");
		
		return conf;
	}
}
