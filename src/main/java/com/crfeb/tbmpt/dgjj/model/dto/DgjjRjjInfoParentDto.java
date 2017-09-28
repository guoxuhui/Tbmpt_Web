package com.crfeb.tbmpt.dgjj.model.dto;

import java.io.Serializable;

import com.crfeb.tbmpt.project.model.vo.SectionLineVo;

public class DgjjRjjInfoParentDto extends SectionLineVo implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 线路Id<br>
     */
    private String xlBh;

	public String getXlBh() {
		return xlBh;
	}

	public void setXlBh(String xlBh) {
		this.xlBh = xlBh;
	}


   

}