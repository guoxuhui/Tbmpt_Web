package com.crfeb.tbmpt.zsjk.xmb.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbGpZlWtsXx;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbGpZlWtsXxDto;

/**
 * <p>项目部角度  各质量分类（管片破损、管片错台、螺栓复紧、渗漏水等）下的管片质量问题数量信息Mapper</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
public interface ZsJkXmbGpZlWtsXxMapper extends CommonMapper<ZsJkXmbGpZlWtsXx>{

    List<ZsJkXmbGpZlWtsXx> selectZsJkXmbGpZlWtsXxList(Page<ZsJkXmbGpZlWtsXx> page,@Param("condition")Map<String, Object> condition) throws Exception;
    
    /**
     * 方法说明：根据参数条件查询数据
     * @param condition 参数条件
     * @return 返回查询结果集合
     * @throws Exception
     * @author:YangYj
     * @Time: 2017年1月10日 下午3:33:34
     */
    List<ZsJkXmbGpZlWtsXxDto> selectZsJkXmbGpZlWtsXxDtoList(@Param("condition")Map<String, Object> condition) throws Exception;

}