package com.crfeb.tbmpt.commons.utils;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通用工具类
 * @author：smxg
 * @date：2016-10-18 10:12
 */
public class CommUtils {

	/**
	 * 获得一个UUID
	 * 
	 * @return String UUID
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		// 去掉“-”符号
		return uuid.replaceAll("-", "");
	}
	
	/**
	 * 方法说明：判断给定的字符串中是否包含字母
	 * @param str 给定字符串
	 * @return 若包含则返回true 反之返回false
	 * @author:YangYj
	 * @Time: 2016年12月23日 下午5:25:46
	 */
	public static boolean judgeContainsStr(String str) {  
        String regex=".*[a-zA-Z]+.*";  
        Matcher m=Pattern.compile(regex).matcher(str);  
        return m.matches();  
    }
	/**
	 * 方法说明：判断给定的字符串中是否包含数值
	 * @param str
	 * @return
	 * @author:YangYj
	 * @Time: 2016年12月27日 上午11:15:26
	 */
	public static boolean judgeContainsNumber(String str) {  
        String regex=".*[0-9]+.*";  
        Matcher m=Pattern.compile(regex).matcher(str);  
        return m.matches();  
    }
	/**
	 * 方法说明：查询给定字符串中数值第一次出现的位置
	 * @param str 查询的字符串
	 * @return 若存在数值，则返回第一次出现的位置
	 * @author:YangYj
	 * @Time: 2016年12月27日 下午1:38:56
	 */
	public static int findNumberFirstLocationInstr(String str){
		int result = -1;
		if(StringUtils.isNotBlank(str)){
			String regEx="[0-9]";   
			Pattern p = Pattern.compile(regEx);   
			Matcher m = p.matcher(str);   
			if(m.find()){
				result = m.start();
			}
		}
		return result;
	}
	
	/**
	 * 方法说明：查询给定字符串中非数值第一次出现的位置
	 * @param str  查询的字符串
	 * @return 若存在数值，则返回第一次出现的位置
	 * @author:YangYj
	 * @Time: 2016年12月27日 下午3:04:23
	 */
	public static int findChartFirstLocationInstr(String str){
		int result = -1;
		if(StringUtils.isNotBlank(str)){
			String regEx=".*[^0-9]+.*";   
			Pattern p = Pattern.compile(regEx);   
			Matcher m = p.matcher(str);   
			if(m.find()){
				result = m.start();
			}
		}
		return result;
	}
	
	/**
	 * 方法说明：判断给定的字符串中是否包含除数值之外的其他的字符
	 * @param str 查询的字符串
	 * @return 若存在返回true,否则返回false
	 * @author:YangYj
	 * @Time: 2016年12月27日 下午1:50:16
	 */
	public static boolean judgeNotNumberInstr(String str){
		String regex=".*[^0-9]+.*";  
        Matcher m=Pattern.compile(regex).matcher(str);  
        return m.matches();
	}
	public static void main(String[] args){
		boolean ss = judgeNotNumberInstr("2'4");
		System.out.println(ss);
//		findNumberFirstLocationInstr("3'2");
	}
	
}
