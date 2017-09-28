package com.crfeb.tbmpt.gczl.base.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLSgnr;

/**
 * <p>施工内容管理Mapper</p>
 * <p>系统：</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-11-23</p>
 * @version 1.0
 * @author wangbinbin
 */
public interface GczlYdxjSGZLSgnrMapper extends CommonMapper<GczlYdxjSGZLSgnr>{

    List<GczlYdxjSGZLSgnr> selectGczlYdxjSGZLSgnrList(Page<GczlYdxjSGZLSgnr> page,@Param("condition")Map<String, Object> condition);
    
    /**
     * 根据状态查询施工内容信息
     * @param sta 施工状态 1:启用；0：禁用 为空时则查询所有的数据
     * @return 返回施工内容数据
     */
    List<GczlYdxjSGZLSgnr> selectBySta(@Param("sta") String sta);
    
    String test(@Param("param") GczlYdxjSGZLSgnr entity);

}