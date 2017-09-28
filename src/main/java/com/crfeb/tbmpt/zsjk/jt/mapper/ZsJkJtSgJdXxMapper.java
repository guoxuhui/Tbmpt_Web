package com.crfeb.tbmpt.zsjk.jt.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.zsjk.jt.model.ZsJkJtSgJdXx;

/**
 * <p>所有项目总的施工进度信息Mapper</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：集团角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
public interface ZsJkJtSgJdXxMapper extends CommonMapper<ZsJkJtSgJdXx>{

    List<ZsJkJtSgJdXx> selectZsJkJtSgJdXxList(Page<ZsJkJtSgJdXx> page,@Param("condition")Map<String, Object> condition) throws Exception;
    
    /**
     * 查询数据表格数据
     * @author wl_zjh
     * @param page
     * @param condition
     * @return
     * @throws Exception
     */
    List<ZsJkJtSgJdXx> selectZsJkJtSgJdXxDataGrid(Page<ZsJkJtSgJdXx> page,@Param("condition")Map<String, Object> condition) throws Exception;

}