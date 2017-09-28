package com.crfeb.tbmpt.gczlys.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.aqxj.model.AqxjXjdfl;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLJtwz;
import com.crfeb.tbmpt.gczlys.model.YanShouYingXiang;

public interface YanShouYingXiangMapper extends CommonMapper<YanShouYingXiang>{
	
	 List<YanShouYingXiang> selectYanShouYingXiangList(Page<YanShouYingXiang> page,@Param("condition")Map<String, Object> condition);

	List<Map<String, Object>> selectReport(@Param("datecondition")Map<String, Object> condition);


}
