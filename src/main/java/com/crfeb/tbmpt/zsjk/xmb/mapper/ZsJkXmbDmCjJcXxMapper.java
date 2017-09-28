package com.crfeb.tbmpt.zsjk.xmb.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbDmCjJcXx;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbDmCjJcXxDto;

/**
 * <p>项目部角度  地面沉降情况（默认取当前里程的前后50米）Mapper</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
public interface ZsJkXmbDmCjJcXxMapper extends CommonMapper<ZsJkXmbDmCjJcXx>{

    List<ZsJkXmbDmCjJcXx> selectZsJkXmbDmCjJcXxList(Page<ZsJkXmbDmCjJcXx> page,@Param("condition")Map<String, Object> condition) throws Exception;
    
    /**
     * 方法说明：根据参数条件查询
     * @param condition 参数条件
     * @return 返回数据集合
     * @throws Exception
     * @author:YangYj
     * @Time: 2017年1月10日 下午4:34:25
     */
    List<ZsJkXmbDmCjJcXxDto> selectZsJkXmbDmCjJcXxDtoList(@Param("condition")Map<String, Object> condition) throws Exception;

}