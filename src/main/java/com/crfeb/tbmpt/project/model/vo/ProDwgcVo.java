package com.crfeb.tbmpt.project.model.vo;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

public class ProDwgcVo implements Serializable{
	
	@TableId(type = IdType.UUID)
	private String id;
	
	@TableField(value = "PRO_ID")
	private String proId;
	
	@TableField(value="DWGCNAME")
	private String dwgcname;
	
	private String proName;
	
	private String remarks;
	
	/** wl_wpg: 单位工程分类 */
	private String dwgcfl;
	
	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public String getDwgcname() {
		return dwgcname;
	}

	public void setDwgcname(String dwgcname) {
		this.dwgcname = dwgcname;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String getDwgcfl() {
		return dwgcfl;
	}

	public void setDwgcfl(String dwgcfl) {
		this.dwgcfl = dwgcfl;
	}


}
