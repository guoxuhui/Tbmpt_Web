package com.crfeb.tbmpt.commons.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.project.model.ProRSectionLine;

public class ProNoUtils {

	/**
	 * 项目编号生成工具
	 * 生成格式 国家+地区+省份+城市+几号线+标段+开工年月
	 * CN-XB-SX-XA-03-10-16-01
	 * 
	 * @param list 内容列表
	 * @param fix 间隔符号
	 * @return
	 */
	public static String generateProjectNo(List<String> list,String fix){
		StringBuffer result = new StringBuffer();
		if(list != null){
			for(String name:list){
				result.append(Pinyin4jUtil.converterToFirstSpellNoM(name));
				result.append(fix);
			}
		}
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int month = Calendar.getInstance().get(Calendar.MONTH)+1;
		result.append(year);
		result.append(fix);
		result.append(month);
		return result.toString().toUpperCase();
	}
	
	/**
	 * 项目编号生成工具
	 * 生成格式 国家+地区+省份+城市+几号线+标段+开工年月
	 * CN-XB-SX-XA-03-10-16-01
	 * 
	 * @param ProProjectinfo 
	 * @param fix 间隔符号
	 * @return
	 */
	public static String generateProjectNo(ProProjectinfo pro,String fix){
		List<String> list = new ArrayList<String>();
		list.add(pro.getCountry());
		list.add(pro.getArea());
		list.add(pro.getProvince());
		list.add(pro.getCity());
		if(StringUtils.isNotBlank(pro.getLine())){
			list.add(pro.getLine());
		}
        if(StringUtils.isNotBlank(pro.getTender())){
        	list.add(pro.getTender());
		}
		/*list.add(pro.getLine());*/
		/*list.add(pro.getTender());*/
		return generateProjectNo(list, fix);
	}
	
	
//	public static String generateSectionNo(ProProjectinfo pro,ProRProjectSection section,String fix){
//		return pro.getProCode()+fix+Pinyin4jUtil.converterToFirstSpellNoM(section.getSectionName()).toUpperCase();
//	}
	
//	public static String generateLineNo(ProRProjectSection section,ProRSectionLine line,String fix){
//		return section.getSectionCode()+fix+Pinyin4jUtil.converterToFirstSpellNoM(line.getLineName()).toUpperCase();
//	}
	
	
}
