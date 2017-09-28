package com.crfeb.tbmpt.dgjjdw.real.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.dgjjdw.real.model.DgjjdwPlcReal;
import com.crfeb.tbmpt.dgjjdw.real.model.dto.DgjjdwPlcRealDto;

/**
 * <p>盾构掘进点位管理：盾构机机器数据实时信息管理 Mapper</p>
 * <p>模块：盾构掘进点位管理</p>
 * <p>日期：2017-03-17</p>
 * @version 1.0
 * @author wl_wpg
 */
public interface DgjjdwPlcRealMapper extends CommonMapper<DgjjdwPlcReal> {

	List<DgjjdwPlcReal> selectDgjjdwPlcRealList(Page<DgjjdwPlcReal> page,@Param("condition")Map<String, Object> condition, @Param("sort") String sort, @Param("order") String order) throws Exception;
	
	/**
	 * 根据盾构code查询点位
	 * @author wl_zjh
	 * @param tbmcode
	 * @return
	 */
	List<DgjjdwPlcRealDto> selectTbmCode(@Param("tbmcode")String tbmcode);
	
	List<DgjjdwPlcRealDto> selectTbmName(@Param("tbmName")String tbmName,@Param("tbmcode")String tbmcode);

}