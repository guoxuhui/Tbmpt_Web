package com.crfeb.tbmpt.zsjk.xmb.model.dto;

import java.io.Serializable;
import java.util.List;

public class ZsJkXmbXlJdXxDto  implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 项目ID<br>
     */
    private String xmId;

    /**
     * 项目名称<br>
     */
    private String xmMc;
    
    /**
     * 明细信息
     */
    private List<ZsJkXmbXlJdMxXxDto> xlList;
    /**
     * 项目ID
     */
    public String getXmId() {
      return xmId;
    }

    /**
     * 项目ID
     */
    public void setXmId(String xmId) {
      this.xmId = xmId;
    }

    /**
     * 项目名称
     */
    public String getXmMc() {
      return xmMc;
    }

    /**
     * 项目名称
     */
    public void setXmMc(String xmMc) {
      this.xmMc = xmMc;
    }

	public List<ZsJkXmbXlJdMxXxDto> getXlList() {
		return xlList;
	}

	public void setXlList(List<ZsJkXmbXlJdMxXxDto> xlList) {
		this.xlList = xlList;
	}
    


}