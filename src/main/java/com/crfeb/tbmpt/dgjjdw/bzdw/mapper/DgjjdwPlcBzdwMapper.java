package com.crfeb.tbmpt.dgjjdw.bzdw.mapper;

import com.crfeb.tbmpt.dgjjdw.bzdw.model.DgjjdwPlcBzdw;
import com.crfeb.tbmpt.dgjjdw.bzdw.model.dto.DgjjdwPlcBzdwDto;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>盾构掘进点位管理：标准点位字典信息管理 Mapper接口</p>
 * <p>模块：盾构掘进点位管理</p>
 * <p>日期：2017-03-17</p>
 * @version 1.0
 * @author wl_wpg
 */
public interface DgjjdwPlcBzdwMapper extends CommonMapper<DgjjdwPlcBzdw> {

	List<DgjjdwPlcBzdw> selectDgjjdwPlcBzdwList(Page<DgjjdwPlcBzdw> page,@Param("condition")Map<String, Object> condition, @Param("sort") String sort, @Param("order") String order) throws Exception;

	//void insertBzdw(DgjjdwPlcBzdw DgjjdwPlcBzdw);
	
	/**
	 * 查询标准点位
	 * @author wl_zjh
	 * @return
	 */
	List<DgjjdwPlcBzdwDto> listAllBzdw();
}