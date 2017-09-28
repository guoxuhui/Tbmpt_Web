package com.crfeb.tbmpt.dmcjjc.zdcjddb.service;

import java.text.ParseException;
import java.util.List;

/**
 * 沉降报表数据统计业务
 * @author smxg
 *
 */
public interface IZdcjddbService {

	/**
	 * 获取最大最小沉降量数据统计信息
	 * @param lineId 线路ID
	 * @param sdate  开始日期
	 * @param edate  截止日期
	 * @return 统计内容JSON
	 * @throws ParseException 
	 */
	Object getZdcjddbData(List<String> qujians,String sdate,String edate) throws ParseException;
}
