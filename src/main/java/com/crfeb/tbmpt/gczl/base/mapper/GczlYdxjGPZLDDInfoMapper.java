package com.crfeb.tbmpt.gczl.base.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjGPZLDDInfo;

/**
 * <p>盾构施工质量基础数据Mapper</p>
 * <p>系统：</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-11-19</p>
 * @version 1.0
 * @author wangbinbin
 */
public interface GczlYdxjGPZLDDInfoMapper extends CommonMapper<GczlYdxjGPZLDDInfo>{

    List<GczlYdxjGPZLDDInfo> selectGczlYdxjGPZLDDInfoList(Page<GczlYdxjGPZLDDInfo> page,@Param("condition")Map<String, Object> condition);

}