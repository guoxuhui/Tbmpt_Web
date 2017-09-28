package com.crfeb.tbmpt.aqsc.base.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.aqsc.base.model.PeiXunRenYuan;
import com.crfeb.tbmpt.aqsc.base.model.dto.PeiXunRenYuanDto;

/**
 * <p>培训人员管理Mapper</p>
 * <p>系统：安全生产管理</p>
 * <p>模块：基础信息</p>
 * <p>日期：2017-02-20</p>
 * @version 1.0
 * @author wangbinbin
 */
public interface PeiXunRenYuanMapper extends CommonMapper<PeiXunRenYuan>{

    List<PeiXunRenYuan> selectPeiXunRenYuanList(Page<PeiXunRenYuan> page,@Param("condition")Map<String, Object> condition) throws Exception;
    
    List<PeiXunRenYuanDto> selectPeiXunRenYuanDtoList(Page<PeiXunRenYuan> page,@Param("condition")Map<String, Object> condition, @Param("sort") String sort, @Param("order") String order) throws Exception;

}