package com.crfeb.tbmpt.zsjk.jt.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.zsjk.jt.model.ZsJkJtdgSgRyTrXx;

/**
 * <p>集团角度项目的盾构施工人员投入信息Mapper</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：集团角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
public interface ZsJkJtdgSgRyTrXxMapper extends CommonMapper<ZsJkJtdgSgRyTrXx>{

    List<ZsJkJtdgSgRyTrXx> selectZsJkJtdgSgRyTrXxList(Page<ZsJkJtdgSgRyTrXx> page,@Param("condition")Map<String, Object> condition) throws Exception;
    
    /**
     * 查询数据表格数据
     * @author wl_zjh
     * @param page
     * @param condition
     * @return
     * @throws Exception
     */
    List<ZsJkJtdgSgRyTrXx> selectZsJkJtdgSgRyTrXxDataGrid(Page<ZsJkJtdgSgRyTrXx> page,@Param("condition")Map<String, Object> condition) throws Exception;

}