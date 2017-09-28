package com.crfeb.tbmpt.sddzgl.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.crfeb.tbmpt.sddzgl.model.SddzglDzxx;
import com.crfeb.tbmpt.sddzgl.model.dto.SddzglDzxxDto;

/**
 * <p>地质信息 数据访问层接口 Mapper</p>
 * <p>模块：隧道地质管理</p>
 * <p>日期：2017-03-28</p>
 * @version 1.0
 * @author wl_wpg
 */
public interface SddzglDzxxMapper  extends CommonMapper<SddzglDzxx> {

	List<SddzglDzxxDto> selectDtoList(Pagination page,@Param("params") Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);
   
	/**
	 * @author wl_zjh
	 * 查找地层信息
	 */
	List<SddzglDzxxDto> listDzxx();

}
