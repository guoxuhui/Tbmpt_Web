package com.crfeb.tbmpt.gczl.base.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjGPZLXJInfo;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjGPZLXJInfoDto;

/**
 * <p>盾构施工质量巡检信息Mapper</p>
 * <p>系统：</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-11-23</p>
 * @version 1.0
 * @author wangbinbin
 */
public interface GczlYdxjGPZLXJInfoMapper extends CommonMapper<GczlYdxjGPZLXJInfo>{

    List<GczlYdxjGPZLXJInfoDto> selectGczlYdxjGPZLXJInfoList2(Page<GczlYdxjGPZLXJInfo> page,@Param("condition")Map<String, Object> condition, @Param("sort") String sort, @Param("order") String order);

    /**
     * 通过id查找实体Dto信息
     * @param id 实体id
     * @return
     */
    GczlYdxjGPZLXJInfoDto selectGczlYdxjGPZLXJInfoById(@Param("id")String id);
    
    int findStateNum(@Param("pid")String proId,@Param("typeName") String typeName);
}