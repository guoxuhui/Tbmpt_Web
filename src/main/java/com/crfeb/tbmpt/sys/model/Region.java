/**
 * 
 */
package com.crfeb.tbmpt.sys.model;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;


/**
 * 地区实体类    Region
 * 
 * 实现  import java.io.Serializable;  jdk.7.0 接口；
 * 
 * @author Administrator  wpg
 * 
 * 2016-11-18
 * 
 */

@TableName("sys_region")
public class Region implements Serializable {

	/**
	 * 
	 */
	@TableField(exist = false)
	private static final long serialVersionUID = -4661415070826042913L;
    
	/** 1 主键id */
	@TableId(type = IdType.UUID)
	private String id;
	
	/**2 代码编号 */
	private String code;

	/** 3 地区名   */   
	private String name;
	
	/** 4 父级主键 */
	private String pid;
	
	/** 5 地区类型：国家， 地区 ，省级，省辖市，直辖市，自治区， */
	private String type ;
	
	/** 6 排序 */      
	private Integer seq;
	
	


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	

	public String getPid() {
		return this.pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public Integer getSeq() {
		return this.seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
}
