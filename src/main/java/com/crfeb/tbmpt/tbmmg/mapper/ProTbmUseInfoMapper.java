package com.crfeb.tbmpt.tbmmg.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.crfeb.tbmpt.tbmmg.model.ProTbmUseInfo;
import com.crfeb.tbmpt.tbmmg.model.ProTbminfo;
import com.crfeb.tbmpt.tbmmg.model.dto.TbmUseInfoDto;
import com.crfeb.tbmpt.tbmmg.model.vo.ProTbmUseInfoVo;

/**
 *
 * ProTbminfo 表数据库控制层接口
 *
 */
public interface ProTbmUseInfoMapper extends CommonMapper<ProTbmUseInfo> {

	/**
	 * 分页查询盾构机使用历史
	 * @param page
	 * @param params
	 * @param sort
	 * @param order
	 * @return
	 */
	List<TbmUseInfoDto> selectList(Pagination page,@Param("params") Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);
	
	/**
	 * 获取全部盾构机使用历史列表
	 * @return
	 */
	List<ProTbmUseInfo> selectAllList();

	TbmUseInfoDto selectByTbmid(@Param("tbmid")String tbmid);
	
	//根据接口查询用户拥有的项目权限
	List<TbmUseInfoDto> selectList2(Pagination page,@Param("params") Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);
}