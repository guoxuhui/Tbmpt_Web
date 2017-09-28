package com.crfeb.tbmpt.zsjk.xmb.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbSgZlMxXx;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbSgZlMxXxDto;

/**
 * <p>项目部角度  结构施工质量问题明细信息Mapper</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
public interface ZsJkXmbSgZlMxXxMapper extends CommonMapper<ZsJkXmbSgZlMxXx>{

    List<ZsJkXmbSgZlMxXx> selectZsJkXmbSgZlMxXxList(Page<ZsJkXmbSgZlMxXx> page,@Param("condition")Map<String, Object> condition) throws Exception;
    
    /**
     * 方法说明：根据参数条件查询数据
     * @param condition 参数条件
     * @return 返回dto集合信息
     * @throws Exception
     * @author:YangYj
     * @Time: 2017年1月17日 上午10:56:41
     */
    List<ZsJkXmbSgZlMxXxDto> selectZsJkXmbSgZlMxXxDtoList(@Param("condition")Map<String, Object> condition) throws Exception;
    
    
}