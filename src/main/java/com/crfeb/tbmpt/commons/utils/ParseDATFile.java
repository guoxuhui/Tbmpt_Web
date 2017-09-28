package com.crfeb.tbmpt.commons.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.IOUtils;
import com.crfeb.tbmpt.dmcjjc.info.model.JcDetails;

/**
 * 解析上传的DAT文件，获取文件中的点位编号和本次高程
 * @author Administrator
 *
 */
public class ParseDATFile {
	public static void main(String[] args) {
		ParseDATFile parse = new ParseDATFile();
		String filePath = "F:\\mu\\0804（监测仪）.DAT";
		Map<String,Map<String,String>> list = parse.getDetailFromFile(filePath );
		System.out.println(JSON.toJSONString(list));
	}

	//从文件中取出点位编号和本次高程
	private static void parseStr(String str,Map<String,Map<String,String>> resutMap) {
		String[] row = str.split("\\|");
		if (row.length >= 6) {
			Map<String, String> rec = new HashMap<String, String>();
			String col2 = row[2];
			String[] cols2 = col2.split("\\s+");// 再通过空格拆分，取出点位编号所在第二列的值
			if (cols2.length > 1) {
				rec.put("jcd", cols2[1]);
			}
			String col5 = row[5];
			String[] cols5 = col5.split("\\s+");// 再通过空格拆分，取出本次高程所在第二列的值
			if (cols5.length > 1) {
				rec.put("bcgc", cols5[1]);
			}
			if(rec.keySet().size() > 1)
				resutMap.put(rec.get("jcd"), rec);
//			System.out.println(JSON.toJSONString(resutMap));
		}
	}
	
	public static Map<String,Map<String,String>> getDetailFromFile(String filePath) {
		Map<String,Map<String,String>> parseResult = new HashMap<String,Map<String,String>>();
		FileReader reader;
		try {
			reader = new FileReader(filePath);
			BufferedReader br = new BufferedReader(reader);
			String str = null;
			//一行行读取文件
			while ((str = br.readLine()) != null) {
				parseStr(str,parseResult);
			}
			IOUtils.close(br);
			IOUtils.close(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parseResult;
	}

}
