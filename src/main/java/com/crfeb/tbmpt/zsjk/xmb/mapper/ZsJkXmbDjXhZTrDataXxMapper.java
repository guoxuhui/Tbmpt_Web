package com.crfeb.tbmpt.zsjk.xmb.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbDjXhZTrDataXx;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbDjXhZTrDataXxDto;

/**
 * <p>项目部角度 刀具消耗总投入明细信息Mapper</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
public interface ZsJkXmbDjXhZTrDataXxMapper extends CommonMapper<ZsJkXmbDjXhZTrDataXx>{

    List<ZsJkXmbDjXhZTrDataXx> selectZsJkXmbDjXhZTrDataXxList(Page<ZsJkXmbDjXhZTrDataXx> page,@Param("condition")Map<String, Object> condition) throws Exception;
    
    /**
     * 方法说明：根据参数条件查询数据
     * @param condition 参数条件
     * @return 返回查询数据集合
     * @throws Exception
     * @author:YangYj
     * @Time: 2017年1月10日 下午3:15:49
     */
    public List<ZsJkXmbDjXhZTrDataXxDto> selectZsJkXmbDjXhZTrDataXxDtoList(@Param("condition")Map<String, Object> condition) throws Exception;

}