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
import com.crfeb.tbmpt.gczl.base.mapper.GczlYdxjSGZLJtwzMapper;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLJtwz;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLSgnr;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjSGZLJtwzDto;
import com.crfeb.tbmpt.gczl.base.service.GczlYdxjSGZLJtwzService;
import com.crfeb.tbmpt.sys.model.User;
/**
 * <p>结构施工质量基础数据-具体位置Service实现类</p>
 * <p>系统：</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-11-24</p>
 * @version 1.0
 * @author wangbinbin
 */
@Service
public class GczlYdxjSGZLJtwzServiceImpl extends CommonServiceImpl<GczlYdxjSGZLJtwzMapper, GczlYdxjSGZLJtwz> implements GczlYdxjSGZLJtwzService{

    @Autowired
    private GczlYdxjSGZLJtwzMapper gczlYdxjSGZLJtwzMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) {
       Page<GczlYdxjSGZLJtwz> page = new Page<GczlYdxjSGZLJtwz>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<GczlYdxjSGZLJtwz> list = gczlYdxjSGZLJtwzMapper.selectGczlYdxjSGZLJtwzList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public void save(GczlYdxjSGZLJtwz gczlYdxjSGZLJtwz) {
    	  gczlYdxjSGZLJtwzMapper.insert(gczlYdxjSGZLJtwz);
    }

	   @Override
    public void del(String[] ids) {
		  List<String> idList = Arrays.asList(ids);
		  gczlYdxjSGZLJtwzMapper.deleteBatchIds(idList);
    }

    @Override
    public String update(GczlYdxjSGZLJtwzDto dto,User user) {
    	GczlYdxjSGZLJtwz oldEntity = this.gczlYdxjSGZLJtwzMapper.selectById(dto.getId());
    	String errorMessage = BaseService.operatorMessage(oldEntity, dto, user);
    	if(StringUtils.isNotBlank(errorMessage)) return errorMessage;
    	BeanUtils.copyProperties(dto, oldEntity);
		int i = gczlYdxjSGZLJtwzMapper.updateById(oldEntity);
		 if(i<1){
			 return "数据更新失败，请重试!";
		 }
		return "";
    }

	   @Override
	   public GczlYdxjSGZLJtwz findById(String id) {
		   return gczlYdxjSGZLJtwzMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(GczlYdxjSGZLJtwzDto gczlYdxjSGZLJtwz, String[] clumNames) {
		   String id = gczlYdxjSGZLJtwz.getId();
		   Map map = BeanUtils.toMap(gczlYdxjSGZLJtwz);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<GczlYdxjSGZLJtwz> lists = gczlYdxjSGZLJtwzMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (GczlYdxjSGZLJtwz gczlYdxjSGZLJtwz2 : lists) {
				   String newId = gczlYdxjSGZLJtwz2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

	@Override
	public String qy(String[] ids, boolean ifQy) {
		String state = "启用";
		if(!ifQy)state = "禁用";
		List<String> idList = Arrays.asList(ids);
		List<GczlYdxjSGZLJtwz> entityList = gczlYdxjSGZLJtwzMapper.selectBatchIds(idList);
		for (GczlYdxjSGZLJtwz gczlYdxjSGZLJtwz : entityList) {
			gczlYdxjSGZLJtwz.setIfQy(state);
		}
		int num = gczlYdxjSGZLJtwzMapper.updateBatchById(entityList);
		return "成功更新"+num+"条数据!";
	}

	@Override
	public List<GczlYdxjSGZLJtwz> selectList(GczlYdxjSGZLJtwz entity) {
		return this.gczlYdxjSGZLJtwzMapper.selectList(entity);
	}

	@Override
	public String insert(GczlYdxjSGZLJtwzDto dto, User user) {
		GczlYdxjSGZLJtwz entity = new GczlYdxjSGZLJtwz();
    	BeanUtils.copyProperties(dto, entity);
    	BaseService.operatorMessage(entity, null,user );
    	int i = this.gczlYdxjSGZLJtwzMapper.insert(entity);
    	if(i<1){
    		return "数据添加失败，请重试!";
    	}
    	return "";
	}

}