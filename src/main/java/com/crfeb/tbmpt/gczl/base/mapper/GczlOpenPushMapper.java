package com.crfeb.tbmpt.gczl.base.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.gczl.base.model.GczlOpenPush;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjGPZLDDInfo;
/**
 * <p>工程质量手机端推送Mapper</p>
 * <p>日期：2017年1月17日</p>
 * @version 1.0
 * @author lzh
 */
public interface GczlOpenPushMapper extends CommonMapper<GczlOpenPush>{
	List<GczlOpenPush> selectGczlOpenPushList(Page<GczlOpenPush> page,@Param("condition")Map<String, Object> condition);
}
