package com.crfeb.tbmpt.dgjj.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.crfeb.tbmpt.dgjj.model.DgjjBzgl;
import com.crfeb.tbmpt.dgjj.model.dto.DgjjBzglDto;


/**
 * <p>班组信息管理Mapper</p>
 * <p>系统：盾构掘进参数管理系统</p>
 * <p>模块：基础模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author wpg
 */
public interface DgjjBzglMapper extends CommonMapper<DgjjBzgl>{

	/***
	 * 作用：根据线路Id 查询 班组表信息   
	 * 返回：班组集合
	 * @author wpg
	 * 日期：2017-01-09
	 * @param xlbh 线路Id
	 * @return
	 * @throws Exception
	 */
    List<DgjjBzgl> selectDgjjBzglListByXlbh(@Param("xlbh")String xlbh) throws Exception;

	/**
	 * 班组数据表格
	 * @param page
	 * @param params
	 * @param sort
	 * @param order
	 * @return
	 */
	List<DgjjBzglDto> selectVoList(Pagination page,@Param("params") Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);
	
	/**
	 * 调用接口获取用户项目信息
	 * @param page
	 * @param params
	 * @param sort
	 * @param order
	 * @return
	 */
	List<DgjjBzglDto> selectVoList2(Pagination page,@Param("params") Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);
}