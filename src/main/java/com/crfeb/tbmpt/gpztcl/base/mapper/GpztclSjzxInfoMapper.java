package com.crfeb.tbmpt.gpztcl.base.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.gpztcl.base.model.GpztclSjzxInfo;

/**
 * <p>线路中心线信息管理Mapper</p>
 * <p>系统：管片姿态测量系统</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-12-20</p>
 * @version 1.0
 * @author wangbinbin
 */
public interface GpztclSjzxInfoMapper extends CommonMapper<GpztclSjzxInfo>{

    List<GpztclSjzxInfo> selectGpztclSjzxInfoList(Page<GpztclSjzxInfo> page,@Param("condition")Map<String, Object> condition) throws Exception;
    
    List<GpztclSjzxInfo> selectGpztclSjzxInfoListByXlbh(@Param("xlbh")String xlbh) throws Exception;

}