package com.crfeb.tbmpt.gpztcl.base.model.dto;

import java.io.Serializable;

import com.crfeb.tbmpt.project.model.vo.SectionLineVo;

public class GpztclSjzxInfoParentDto extends SectionLineVo implements Serializable {


    /**
     * 已导入环数<br>
     */
    private String impHuan;

	public String getImpHuan() {
		return impHuan;
	}

	public void setImpHuan(String impHuan) {
		this.impHuan = impHuan;
	}


   

}