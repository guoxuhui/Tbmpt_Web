package com.crfeb.tbmpt.gczl.base.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * 盾构施工质量数据分类<br>
 * 维护盾构施工质量相关信息内容的分类。
 * 此表数据无需维护，主要信息包括：
 * 1、管片破损
 * 2、管片错台
 * 3、螺栓复紧
 * 4、渗漏水....
 * @author wangbin
 *
 */
@TableName("gczlydxj_gpzl_dd_Type")
public class GczlYdxjGPZLDDType implements Serializable {
	@TableField(exist = false)
	private static final long serialVersionUID = 1L;
	/** UUID */
	@TableId(type = IdType.UUID)
	private String id;
	
	/**分类名称 */
	@TableField(value = "TYPENAME")
	private String typeName;
	
	/**排序号 */
	@TableField(value = "SORTNUM")
	private int sortNum;
	
	/**使用状态 */
	@TableField(value = "IFQY")
	private String ifQy;
	
	/**备注 */
	private String remarks;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}

	public String getIfQy() {
		return ifQy;
	}

	public void setIfQy(String ifQy) {
		this.ifQy = ifQy;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	

	
	
}
