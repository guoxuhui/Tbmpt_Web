package com.crfeb.tbmpt.commons.utils;

import java.math.BigDecimal;

/**
 * 数学公式计算公共类
 * @author YangYJ
 *
 */
public  class UtilMath {
	
	/**
	  * 描述：两个double类型相加
	  * @param a  加数
	  * @param b  加数
	  * @param scale 小数点后精确位数
	  * @return 返回double型两数相加之和
	  */
	public static double add(double a,double b,int scale){
		 double result = 0.0d;
		 BigDecimal a1 = new BigDecimal(a);
		 BigDecimal a2 = new BigDecimal(b);
		 result = a1.add(a2).setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue();
		 return result;
	}
	/**
	  * 描述：两个double类型相减
	  * @param a 减数
	  * @param b 被减数
	  * @param scale 小数点后精确位数
	  * @return 返回两数相减之和double类型
	  */
	public static double subtract(double a,double b,int scale){
		 double result = 0.0d;
		 BigDecimal a1 = new BigDecimal(a);
		 BigDecimal a2 = new BigDecimal(b);
		 result = a1.subtract(a2).setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue();
		 return result;
	}
	/**
	  * 描述：两个double类型相乘
	  * @param a 乘数
	  * @param b 被乘数
	  * @param scale 小数点后精确位数
	  * @return 返回两数相乘double结果
	  */
	public static double multiply(double a,double b,int scale){
		 double result = 0.0d;
		 BigDecimal a1 = new BigDecimal(a);
		 BigDecimal a2 = new BigDecimal(b);
		 result = a1.multiply(a2).setScale(scale,BigDecimal.ROUND_HALF_UP).doubleValue();
		 return result;
	}
	/**
	  * 描述：两double类型相除
	  * @param a 除数
	  * @param b 被除数
	  * @param scale 小数点后精确位数
	  * @return 返回两数相除结果double型
	  */
	public static double divide(double a,double b,int scale){
		 double result = 0.0d;
		 if(b==0){
			 return result;//若被除数为0则结果返回0
		 }
		 BigDecimal a1 = new BigDecimal(a);
		 BigDecimal a2 = new BigDecimal(b);
		 result = a1.divide(a2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
		 return result;
	}
	
	/**
	  * 描述：两个float类型相加
	  * @param a  加数
	  * @param b  加数
	  * @param scale 小数点后精确位数
	  * @return 返回float型两数相加之和
	  */
	public static float add(float a,float b,int scale){
		float result = 0.0f;
		 BigDecimal a1 = new BigDecimal(a);
		 BigDecimal a2 = new BigDecimal(b);
		 result = a1.add(a2).setScale(scale,BigDecimal.ROUND_HALF_UP).floatValue();
		 return result;
	}
	/**
	  * 描述：两个float类型相减
	  * @param a 减数
	  * @param b 被减数
	  * @param scale 小数点后精确位数
	  * @return 返回两数相减之和float类型
	  */
	public static float subtract(float a,float b,int scale){
		float result = 0.0f;
		 BigDecimal a1 = new BigDecimal(a);
		 BigDecimal a2 = new BigDecimal(b);
		 result = a1.subtract(a2).setScale(scale,BigDecimal.ROUND_HALF_UP).floatValue();
		 return result;
	}
	/**
	  * 描述：两个float类型相乘
	  * @param a 乘数
	  * @param b 被乘数
	  * @param scale 小数点后精确位数
	  * @return 返回两数相乘float结果
	  */
	public static float multiply(float a,float b,int scale){
		float result = 0.0f;
		 BigDecimal a1 = new BigDecimal(a);
		 BigDecimal a2 = new BigDecimal(b);
		 result = a1.multiply(a2).setScale(scale,BigDecimal.ROUND_HALF_UP).floatValue();
		 return result;
	}
	/**
	  * 描述：两个float类型相乘
	  * @param a 乘数
	  * @param b 被乘数
	  * @param scale 小数点后精确位数
	  * @return 返回两数相乘float结果
	  */
	public static float multiply(int a,int b,int scale){
		 float result = 0.0f;
		 BigDecimal a1 = new BigDecimal(a);
		 BigDecimal a2 = new BigDecimal(b);
		 result = a1.multiply(a2).setScale(scale,BigDecimal.ROUND_HALF_UP).floatValue();
		 return result;
	}
	/**
	  * 描述：两float类型相除
	  * @param a 除数
	  * @param b 被除数
	  * @param scale 小数点后精确位数
	  * @return 返回两数相除结果float型
	  */
	public static float divide(float a,float b,int scale){
		float result = 0.0f;
		 if(b==0){
			 return result;//若被除数为0则结果返回0
		 }
		 BigDecimal a1 = new BigDecimal(a);
		 BigDecimal a2 = new BigDecimal(b);
		 result = a1.divide(a2).setScale(scale,BigDecimal.ROUND_HALF_UP).floatValue();
		 return result;
	}
	
	/**
	  * 描述：两float类型相除
	  * @param a 除数
	  * @param b 被除数
	  * @param scale 小数点后精确位数
	  * @return 返回两数相除结果float型
	  */
	public static float divide(int a,int b,int scale){
		float result = 0;
		 if(b==0){
			 return result;//若被除数为0则结果返回0
		 }
		 BigDecimal a1 = new BigDecimal(a);
		 BigDecimal a2 = new BigDecimal(b);
		 result = a1.divide(a2).setScale(scale,BigDecimal.ROUND_HALF_UP).floatValue();
		 return result;
	}

}
