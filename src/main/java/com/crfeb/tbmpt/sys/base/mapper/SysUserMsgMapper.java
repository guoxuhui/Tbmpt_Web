package com.crfeb.tbmpt.sys.base.mapper;

import com.crfeb.tbmpt.sys.base.model.SysUserMsg;
import com.crfeb.tbmpt.sys.base.model.dto.SysUserMsgDto;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;


/**
 * <p>用户消息信息 数据访问层 Mapper</p>
 * <p>模块：系统消息模块</p>
 * <p>日期：2017-06-03</p>
 * @version 1.0
 * @author wl_wpg
 */
public interface SysUserMsgMapper extends CommonMapper<SysUserMsg> {

	List<SysUserMsgDto> selectDtoList(Pagination page,@Param("params") Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);

	
	void updateMsgState(@Param("userid") String userid,@Param("msgtype") String msgtype);
	
	List<Map<String, String>> getMsgStats(@Param("userid") String userid);
	
}
