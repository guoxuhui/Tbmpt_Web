package com.crfeb.tbmpt.zsjk.xmb.model.dto;

import java.io.Serializable;
import java.util.List;

public class ZsJkXmbXmLjCzXxDto  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 项目id<br>
     */
    private String projectId;

    /**
     * 项目名称<br>
     */
    private String projectName;

    /**
     * 累计产值<br>
     */
    private String leijcz;
    
    /**
     * 明细信息
     */
    private List<ZsJkXmbXmLjCzMxXxDto> items;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    /**
     * 项目id
     */
    public String getProjectId() {
      return projectId;
    }

    /**
     * 项目id
     */
    public void setProjectId(String projectId) {
      this.projectId = projectId;
    }

    /**
     * 项目名称
     */
    public String getProjectName() {
      return projectName;
    }

    /**
     * 项目名称
     */
    public void setProjectName(String projectName) {
      this.projectName = projectName;
    }

    /**
     * 累计产值
     */
    public String getLeijcz() {
      return leijcz;
    }

    /**
     * 累计产值
     */
    public void setLeijcz(String leijcz) {
      this.leijcz = leijcz;
    }

	public List<ZsJkXmbXmLjCzMxXxDto> getItems() {
		return items;
	}

	public void setItems(List<ZsJkXmbXmLjCzMxXxDto> items) {
		this.items = items;
	}
    

}