package com.crfeb.tbmpt.commons.model;

import com.baomidou.mybatisplus.annotations.TableField;

/**
 * 项目实体类
 * @author smxg
 *
 */
public class ProjectModel extends BaseModel {

    /**
     * 工程（项目）编号<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "gc_Bh")
    private String gcBh;

    /**
     * 区间编号<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "ql_Bh")
    private String qlBh;

    /**
     * 线路编号<br>
     * <font color="ff0000">必填项</font><br>
     */
    @TableField(value = "xl_Bh")
    private String xlBh;

	public String getGcBh() {
		return gcBh;
	}

	public void setGcBh(String gcBh) {
		this.gcBh = gcBh;
	}

	public String getQlBh() {
		return qlBh;
	}

	public void setQlBh(String qlBh) {
		this.qlBh = qlBh;
	}

	public String getXlBh() {
		return xlBh;
	}

	public void setXlBh(String xlBh) {
		this.xlBh = xlBh;
	}
	
}
