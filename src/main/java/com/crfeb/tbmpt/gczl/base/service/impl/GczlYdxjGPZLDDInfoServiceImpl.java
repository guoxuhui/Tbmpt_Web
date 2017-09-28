package com.crfeb.tbmpt.gczl.base.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.base.BaseService;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.gczl.base.mapper.GczlYdxjGPZLDDInfoMapper;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjGPZLDDInfo;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjGPZLDDInfoDto;
import com.crfeb.tbmpt.gczl.base.service.GczlYdxjGPZLDDInfoService;
import com.crfeb.tbmpt.sys.mapper.OrgzMapper;
import com.crfeb.tbmpt.sys.model.User;
/**
 * <p>盾构施工质量基础数据Service实现类</p>
 * <p>系统：</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-11-19</p>
 * @version 1.0
 * @author wangbinbin
 */
@Service
public class GczlYdxjGPZLDDInfoServiceImpl extends CommonServiceImpl<GczlYdxjGPZLDDInfoMapper, GczlYdxjGPZLDDInfo> implements GczlYdxjGPZLDDInfoService{

    @Autowired
    private GczlYdxjGPZLDDInfoMapper gczlYdxjGPZLDDInfoMapper;
    @Autowired
    private OrgzMapper orgzMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) {
       Page<GczlYdxjGPZLDDInfo> page = new Page<GczlYdxjGPZLDDInfo>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<GczlYdxjGPZLDDInfo> list = gczlYdxjGPZLDDInfoMapper.selectGczlYdxjGPZLDDInfoList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

	@Override
	public void save(GczlYdxjGPZLDDInfo gczlYdxjGPZLDDInfo,User user) {
		BaseService.operatorMessage(gczlYdxjGPZLDDInfo, null, user);
		gczlYdxjGPZLDDInfoMapper.insert(gczlYdxjGPZLDDInfo);
	}

	@Override
	public void del(String[] ids) {
		List<String> idList = Arrays.asList(ids);
		gczlYdxjGPZLDDInfoMapper.deleteBatchIds(idList);
	}

	@Override
	public String update(GczlYdxjGPZLDDInfoDto gczlYdxjGPZLDDInfoDto,User user) {
		String errorMessage ="";
		GczlYdxjGPZLDDInfo gczlYdxjGPZLDDInfo = this.gczlYdxjGPZLDDInfoMapper.selectById(gczlYdxjGPZLDDInfoDto.getId());
		errorMessage = BaseService.operatorMessage(gczlYdxjGPZLDDInfo, gczlYdxjGPZLDDInfoDto, user);
		if(StringUtils.isNotBlank(errorMessage))return errorMessage;
		BeanUtils.copyProperties(gczlYdxjGPZLDDInfoDto, gczlYdxjGPZLDDInfo);
		gczlYdxjGPZLDDInfoMapper.updateById(gczlYdxjGPZLDDInfo);
		return errorMessage;
	}

	@Override
	public GczlYdxjGPZLDDInfo findById(String id) {
		return gczlYdxjGPZLDDInfoMapper.selectById(id);
	}

	@Override
	public String qy(String[] ids, boolean ifQy) {
		String state = "启用";
		if(!ifQy)state = "禁用";
		List<String> idList = Arrays.asList(ids);
		List<GczlYdxjGPZLDDInfo> entityList = gczlYdxjGPZLDDInfoMapper.selectBatchIds(idList);
		for (GczlYdxjGPZLDDInfo gczlYdxjGPZLDDInfo : entityList) {
			gczlYdxjGPZLDDInfo.setIfQy(state);
		}
		int num = gczlYdxjGPZLDDInfoMapper.updateBatchById(entityList);
		return "更新"+num+"条数据!";
	}

	@Override
	public String checkClumIfexits(GczlYdxjGPZLDDInfoDto gczlYdxjGPZLDDInfoDto, String[] clumNames) {
		String id = gczlYdxjGPZLDDInfoDto.getId();
		Map map = BeanUtils.toMap(gczlYdxjGPZLDDInfoDto);
		Map<String,Object> columnMap = new HashMap<String,Object>();
		for (String clum : clumNames) {
			Object ss =  map.get(clum);
			columnMap.put(clum, ss);
		}
		List<GczlYdxjGPZLDDInfo> lists = gczlYdxjGPZLDDInfoMapper.selectByMap(columnMap);
		if(StringUtils.isBlank(id)){//新增
			if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		}else{//修改
			for (GczlYdxjGPZLDDInfo gczlYdxjGPZLDDInfo2 : lists) {
				String newId = gczlYdxjGPZLDDInfo2.getId();
				if(!newId.equals(id)){
					return "当前数据已存在!";
				}
			}
		}
		return null;
	}

	@Override
	public void insert(GczlYdxjGPZLDDInfoDto gczlYdxjGPZLDDInfoDto, User user) {
		GczlYdxjGPZLDDInfo gczlYdxjGPZLDDInfo = new GczlYdxjGPZLDDInfo();
		BeanUtils.copyProperties(gczlYdxjGPZLDDInfoDto, gczlYdxjGPZLDDInfo);
		BaseService.operatorMessage(gczlYdxjGPZLDDInfo, null, user);
		this.gczlYdxjGPZLDDInfoMapper.insert(gczlYdxjGPZLDDInfo);
		
	}
}