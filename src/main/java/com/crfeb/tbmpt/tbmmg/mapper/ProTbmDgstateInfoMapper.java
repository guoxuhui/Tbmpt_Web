package com.crfeb.tbmpt.tbmmg.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.crfeb.tbmpt.tbmmg.model.ProTbmDgstateInfo;
import com.crfeb.tbmpt.tbmmg.model.ProTbminfo;

/**
 *
 * ProTbminfo 表数据库控制层接口
 *
 */
public interface ProTbmDgstateInfoMapper extends CommonMapper<ProTbminfo> {

	/**
	 * 获取盾构机位置坐标
	 * @param riqi 日期
	 * @return
	 */
	List<ProTbmDgstateInfo> getWzZb(@Param("riqi")String riqi);
	
	/**
	 * 获取盾构机基本信息
	 * @param riqi 日期
	 * @return
	 */
	List<ProTbmDgstateInfo> getTbmInfo(@Param("riqi")String riqi);
	
}