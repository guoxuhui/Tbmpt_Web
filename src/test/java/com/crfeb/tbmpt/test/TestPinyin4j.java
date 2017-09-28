package com.crfeb.tbmpt.test;

import java.util.ArrayList;
import java.util.List;

import com.crfeb.tbmpt.commons.utils.Pinyin4jUtil;
import com.crfeb.tbmpt.commons.utils.ProNoUtils;

public class TestPinyin4j {
	public static void main(String[] args) {
		
		String country = "中国";
		String area = "华南";
		String province = "海南省";
		String city = "海口市";
		String line = "03";
		String tender = "05";
		
		String result = Pinyin4jUtil.converterToFirstSpell(country)+"-"+
				Pinyin4jUtil.converterToFirstSpell(area)+"-"+
				Pinyin4jUtil.converterToFirstSpell(province)+"-"+
				Pinyin4jUtil.converterToFirstSpell(city)+"-"+
				Pinyin4jUtil.converterToFirstSpell(line)+"-"+
				Pinyin4jUtil.converterToFirstSpell(tender);
		System.out.println(result);
		
		List<String> list = new ArrayList<String>();
		list.add(country);
		list.add(area);
		list.add(province);
		list.add(city);
		list.add(line);
		list.add(tender);
		System.out.println(ProNoUtils.generateProjectNo(list,"-"));
	}
	
}
