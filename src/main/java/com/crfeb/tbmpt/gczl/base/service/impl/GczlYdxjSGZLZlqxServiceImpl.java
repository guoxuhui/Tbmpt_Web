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
import com.crfeb.tbmpt.gczl.base.mapper.GczlYdxjSGZLZlqxMapper;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLSgnr;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLZlqx;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjSGZLZlqxDto;
import com.crfeb.tbmpt.gczl.base.service.GczlYdxjSGZLZlqxService;
import com.crfeb.tbmpt.sys.model.User;
/**
 * <p>结构施工质量基础数据-质量缺陷Service实现类</p>
 * <p>系统：</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-11-24</p>
 * @version 1.0
 * @author wangbinbin
 */
@Service
public class GczlYdxjSGZLZlqxServiceImpl extends CommonServiceImpl<GczlYdxjSGZLZlqxMapper, GczlYdxjSGZLZlqx> implements GczlYdxjSGZLZlqxService{

    @Autowired
    private GczlYdxjSGZLZlqxMapper gczlYdxjSGZLZlqxMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) {
       Page<GczlYdxjSGZLZlqx> page = new Page<GczlYdxjSGZLZlqx>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<GczlYdxjSGZLZlqx> list = gczlYdxjSGZLZlqxMapper.selectGczlYdxjSGZLZlqxList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public void save(GczlYdxjSGZLZlqx gczlYdxjSGZLZlqx) {
    	  gczlYdxjSGZLZlqxMapper.insert(gczlYdxjSGZLZlqx);
    }

	   @Override
    public void del(String[] ids) {
		  List<String> idList = Arrays.asList(ids);
		  gczlYdxjSGZLZlqxMapper.deleteBatchIds(idList);
    }

    @Override
    public String update(GczlYdxjSGZLZlqxDto dto,User user) {
    	GczlYdxjSGZLZlqx oldEntity = this.gczlYdxjSGZLZlqxMapper.selectById(dto.getId());
    	String errorMessage = BaseService.operatorMessage(oldEntity, dto, user);
    	if(StringUtils.isNotBlank(errorMessage)) return errorMessage;
    	BeanUtils.copyProperties(dto, oldEntity);
		int i = gczlYdxjSGZLZlqxMapper.updateById(oldEntity);
		if(i<1){
			return "数据更新失败，请重试!";
		}
		return "";
    }

	   @Override
	   public GczlYdxjSGZLZlqx findById(String id) {
		   return gczlYdxjSGZLZlqxMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(GczlYdxjSGZLZlqxDto gczlYdxjSGZLZlqx, String[] clumNames) {
		   String id = gczlYdxjSGZLZlqx.getId();
		   Map map = BeanUtils.toMap(gczlYdxjSGZLZlqx);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<GczlYdxjSGZLZlqx> lists = gczlYdxjSGZLZlqxMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (GczlYdxjSGZLZlqx gczlYdxjSGZLZlqx2 : lists) {
				   String newId = gczlYdxjSGZLZlqx2.getId();
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
		List<GczlYdxjSGZLZlqx> entityList = gczlYdxjSGZLZlqxMapper.selectBatchIds(idList);
		for (GczlYdxjSGZLZlqx gczlYdxjSGZLZlqx : entityList) {
			gczlYdxjSGZLZlqx.setIfQy(state);
		}
		int num = gczlYdxjSGZLZlqxMapper.updateBatchById(entityList);
		return "成功更新"+num+"条数据!";
	}

	@Override
	public List<GczlYdxjSGZLZlqx> selectList(GczlYdxjSGZLZlqx entity) {
		return this.gczlYdxjSGZLZlqxMapper.selectList(entity);
	}
	
	@Override
	public String insert(GczlYdxjSGZLZlqxDto dto, User user) {
		GczlYdxjSGZLZlqx entity = new GczlYdxjSGZLZlqx();
    	BeanUtils.copyProperties(dto, entity);
    	BaseService.operatorMessage(entity, null,user );
    	int i = this.gczlYdxjSGZLZlqxMapper.insert(entity);
    	if(i<1){
    		return "数据添加失败，请重试!";
    	}
    	return "";
	}

}