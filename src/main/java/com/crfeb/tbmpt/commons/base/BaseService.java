package com.crfeb.tbmpt.commons.base;

import com.crfeb.tbmpt.commons.model.BaseModel;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.sys.mapper.OrgzMapper;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.User;

public class BaseService {
	
	/**
	 * 公共操作者信息存储方法
	 * @param baseModel 需保存的实体信息
	 * @param baseModelDto 若为null，测为新增；若不为null，则为编辑（若为编辑将此方法置于获取数据库实体方法下一行）
	 * @param user 当前登陆用户
	 * @return 修改时，若当前数据与数据库中修改时间不一致，则返回过期信息
	 */
	public static String operatorMessage(BaseModel baseModel,BaseModel baseModelDto,User user){
		String errorMsg =""; 
		if(baseModelDto==null){
			baseModel.setCreateUser(user.getEmpId());
			baseModel.setCreateUsername(user.getName());
			baseModel.setCreateOrgz(user.getOrgzId());
			baseModel.setCreateOrgzname(user.getOrgzName());
			baseModel.setCreateTime(DateUtils.getToday());
		}else{
			baseModelDto.setCreateUser(baseModel.getCreateUser());
			baseModelDto.setCreateUsername(baseModel.getCreateUsername());
			baseModelDto.setCreateOrgz(baseModel.getCreateOrgz());
			baseModelDto.setCreateOrgzname(baseModel.getCreateOrgzname());
			baseModelDto.setCreateTime(baseModel.getCreateTime());
			String dbTime = baseModel.getUpdateTime();
			String pgTime = baseModelDto.getUpdateTime();
			if(StringUtils.isNotBlank(dbTime)){
				if(StringUtils.isBlank(pgTime)||(StringUtils.isNotBlank(pgTime)&& !dbTime.equals(pgTime))){
					errorMsg = "当前数据已过期,请刷新页面重试！"; 
					return errorMsg;
				}
			}
			baseModelDto.setUpdateUser(user.getEmpId());
			baseModelDto.setUpdateUsername(user.getName());
			baseModelDto.setUpdateOrgz(user.getOrgzId());
			baseModelDto.setUpdateOrgzname(user.getOrgzName());
			baseModelDto.setUpdateTime(DateUtils.getToday());
			
			baseModel.setUpdateUser(user.getEmpId());
			baseModel.setUpdateUsername(user.getName());
			baseModel.setUpdateOrgz(user.getOrgzId());
			baseModel.setUpdateOrgzname(user.getOrgzName());
			baseModel.setUpdateTime(DateUtils.getToday());
		}
		return errorMsg;
	}
}
