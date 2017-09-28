package com.crfeb.tbmpt.dgjj.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.crfeb.tbmpt.dgjj.model.DgjjRjjInfo;
import com.crfeb.tbmpt.dgjj.model.dto.DgjjRjjInfoDto;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcPointDto;
import com.crfeb.tbmpt.gpztcl.base.model.GpztclSjzxInfo;
import com.crfeb.tbmpt.project.model.vo.SectionLineVo;


/**
 * <p>日掘进信息管理Mapper</p>
 * <p>系统：盾构掘进参数管理系统</p>
 * <p>模块：基础模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author wpg
 */
public interface DgjjRjjInfoMapper extends CommonMapper<DgjjRjjInfo>{

    /**
	 * 日掘进  信息集合 
	 * @param page
	 * @param params
	 * @param sort
	 * @param order
	 * @return
	 */
	List<DgjjRjjInfoDto> selectDgjjRjjInfoList(Pagination page,@Param("params") Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);


	/**
	 * 导出 时 查询 日掘进  信息集合 
	 * @param page
	 * @param params
	 * @param sort
	 * @param order
	 * @return
	 */
    List<DgjjRjjInfoDto> selectDgjjRjjPage(Pagination page, Map<String, Object> params);

    /***
     * 作用：编辑日掘进信息，查询 单条班组、日掘进信息
     * @param id 日掘进Id
     * @return
     * @throws Exception
     */
    DgjjRjjInfoDto selectDgjjRjjById(String id) throws Exception;

}






