package com.crfeb.tbmpt.gpztcl.base.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.gpztcl.base.model.DaoXiang;

public interface DaoXiangMapper extends CommonMapper<DaoXiang>{
	 /**
	 * 方法说明：分页查询
	 * @param page 分页信息
	 * @param condition 参数键值对
	 * @return 返回查询集合信息
	 * @throws Exception
	 * @author:YangYj
	 * @Time: 2016年12月27日 上午10:33:09
	 */
	List<DaoXiang> selectDaoXiangList(Page<DaoXiang> page,@Param("condition")Map<String, Object> condition) throws Exception;
	
	/**
	 * 方法说明：删除所有数据
	 * @throws Exception
	 * @author:YangYj
	 * @Time: 2016年12月27日 下午4:29:52
	 */
	public void deleteAll() throws Exception;
}
