package com.crfeb.tbmpt.zsjk.xmb.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbSbWbXx;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbSbWbXxDto;

/**
 * <p>项目部角度 当前设备的维保（维修、保养）明细Mapper</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
public interface ZsJkXmbSbWbXxMapper extends CommonMapper<ZsJkXmbSbWbXx>{

    List<ZsJkXmbSbWbXx> selectZsJkXmbSbWbXxList(Page<ZsJkXmbSbWbXx> page,@Param("condition")Map<String, Object> condition) throws Exception;
    
    /**
     * 方法说明：根据参数条件查询数据
     * @param condition 参数条件
     * @return 返回数据集合
     * @throws Exception
     * @author:YangYj
     * @Time: 2017年1月10日 下午6:47:34
     */
    List<ZsJkXmbSbWbXxDto> selectZsJkXmbSbWbXxDtoList(@Param("condition")Map<String, Object> condition) throws Exception;
    
    /**
     * 方法说明：按周查询数据
     * @param condition 参数条件
     * @return 返回数据集合
     * @throws Exception
     * @author:YangYj
     * @Time: 2017年1月10日 下午7:18:49
     */
    List<ZsJkXmbSbWbXxDto> selectZsJkXmbSbWbXxDtoListByWeek(@Param("condition")Map<String, Object> condition) throws Exception;
    
    /**
     * 方法说明：按月查询数据
     * @param condition 参数条件
     * @return 返回数据集合
     * @throws Exception
     * @author:YangYj
     * @Time: 2017年1月10日 下午7:18:49
     */
    List<ZsJkXmbSbWbXxDto> selectZsJkXmbSbWbXxDtoListByMonth(@Param("condition")Map<String, Object> condition) throws Exception;


}