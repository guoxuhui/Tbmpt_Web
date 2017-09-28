package com.crfeb.tbmpt.zsjk.xmb.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbSgZlXx;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbSgZlXxDto;

/**
 * <p>项目部角度  结构施工质量问题信息Mapper</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
public interface ZsJkXmbSgZlXxMapper extends CommonMapper<ZsJkXmbSgZlXx>{

    List<ZsJkXmbSgZlXx> selectZsJkXmbSgZlXxList(Page<ZsJkXmbSgZlXx> page,@Param("condition")Map<String, Object> condition) throws Exception;
    
    /**
     * 方法说明：根据参数条件查询数据
     * @param condition 参数条件
     * @return 返回查询结果集合
     * @throws Exception
     * @author:YangYj
     * @Time: 2017年1月10日 下午3:33:34
     */
    List<ZsJkXmbSgZlXxDto> selectZsJkXmbSgZlXxDtoList(@Param("condition")Map<String, Object> condition) throws Exception;


}