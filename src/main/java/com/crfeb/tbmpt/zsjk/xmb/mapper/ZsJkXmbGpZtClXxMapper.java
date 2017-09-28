package com.crfeb.tbmpt.zsjk.xmb.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbGpZtClXx;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbGpZtClXxDto;

/**
 * <p>项目部角度  管片姿态测量信息Mapper</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
public interface ZsJkXmbGpZtClXxMapper extends CommonMapper<ZsJkXmbGpZtClXx>{

    List<ZsJkXmbGpZtClXx> selectZsJkXmbGpZtClXxList(Page<ZsJkXmbGpZtClXx> page,@Param("condition")Map<String, Object> condition) throws Exception;
    
    /**
     * 方法说明：根据参数条件查询数据
     * @param condition 参数条件
     * @return 返回数据集合
     * @throws Exception
     * @author:YangYj
     * @Time: 2017年1月10日 下午4:52:46
     */
    public List<ZsJkXmbGpZtClXxDto> selectZsJkXmbGpZtClXxDtoList(@Param("condition")Map<String, Object> condition) throws Exception;
    
    

}