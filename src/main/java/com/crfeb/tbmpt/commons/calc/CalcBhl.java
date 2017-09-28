package com.crfeb.tbmpt.commons.calc;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import com.alibaba.fastjson.JSON;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDetailsDto;
/**
 * 计算变化量
 * @author noah
 *
 */
public class CalcBhl {
	public static void calc(DmcjJcInfoDetailsDto dto){
		Float csgc = dto.getCsgc();
		Float bcgc = dto.getBcgc();
		Float scgc = dto.getScgc();
		System.out.println("bcgc=="+bcgc);
		System.out.println("csgc=="+csgc);
		System.out.println("scgc=="+scgc);
		BigDecimal csgcBig = new BigDecimal(String.valueOf( (null == csgc)?0:csgc) );
		BigDecimal bcgcBig = new BigDecimal(String.valueOf( (null == bcgc)?0:bcgc) );
		BigDecimal scgcBig = new BigDecimal(String.valueOf( (null == scgc)?0:scgc ));
		BigDecimal scale = new BigDecimal("1000");
		DecimalFormat decimalFormat = new DecimalFormat(".#"); 
		DecimalFormat decimalFormat2 = new DecimalFormat("0.00000");
		BigDecimal bcbhl = new BigDecimal(0);
		BigDecimal ljbhl = new BigDecimal(0);
		if(bcgcBig.compareTo(BigDecimal.ZERO)==0){
			//若本次高程为0时
			ljbhl = scgcBig.subtract(csgcBig).multiply(scale);
		}else{
			bcbhl = bcgcBig.subtract(scgcBig).multiply(scale);
			ljbhl = bcgcBig.subtract(csgcBig).multiply(scale);
		}
		dto.setBcbhl(Float.parseFloat(decimalFormat.format(bcbhl)));
		dto.setLjbhl(Float.parseFloat(decimalFormat.format(ljbhl)));
		dto.setBhsl(Float.parseFloat(decimalFormat.format(bcbhl)));
		
//		csgcBig.setScale(5);
		
		dto.setBcgc(Float.parseFloat(decimalFormat2.format( (null == bcgc)?0:bcgc )));
		dto.setCsgc(Float.parseFloat(decimalFormat2.format( (null == csgc)?0:csgc )));
		dto.setScgc(Float.parseFloat(decimalFormat2.format( (null == scgc)?0:scgc )));
		System.out.println("-------------"+JSON.toJSONString(dto));
	}
}
