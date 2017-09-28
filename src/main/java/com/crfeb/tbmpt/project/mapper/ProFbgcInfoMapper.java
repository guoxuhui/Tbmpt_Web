package com.crfeb.tbmpt.project.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.crfeb.tbmpt.dgjj.model.dto.DgjjBzglEmpDto;
import com.crfeb.tbmpt.project.model.ProFbgcInfo;
import com.crfeb.tbmpt.project.model.vo.ProFbgcVo;
import com.crfeb.tbmpt.project.model.vo.SectionLineVo;

/**
 * 
 * ProFbgcInfo 表数据库控制层接口
 */
public interface ProFbgcInfoMapper extends CommonMapper<ProFbgcInfo>{
	
	//List<DgjjBzglEmpDto> selectVoList(@Param("bzId")String bzId);
	/**
	 * 查询线路信息列表
	 * @param page
	 * @param params
	 * @param sort
	 * @param order
	 * @return
	 */
	List<ProFbgcVo> selectVoList(Pagination page,@Param("params") Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);
	
	/**
	 * 调用接口查询用户拥有的项目信息
	 * @param page
	 * @param params
	 * @param sort
	 * @param order
	 * @return
	 */
	List<ProFbgcVo> selectVoList2(Pagination page,@Param("params") Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);
	
	List<ProFbgcVo> selectVoListByDwgcId(@Param("dwgcId")String dwgcId);
	
	/**
	 * 保存
	 * @param fbgcInfo
	 * @return
	 */
	int save (ProFbgcInfo fbgcInfo);
	
	int delete (@Param("id") String id);
}
