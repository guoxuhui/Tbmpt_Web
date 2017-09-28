package com.crfeb.tbmpt.gczl.base.service.impl;

import java.util.ArrayList;
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
import com.crfeb.tbmpt.gczl.base.mapper.GczlYdxjSGZLSgnrMapper;
import com.crfeb.tbmpt.gczl.base.mapper.GczlYdxjSGZLZlqxMapper;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLJtwz;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLSgnr;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLZlqx;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjSGZLSgnrDto;
import com.crfeb.tbmpt.gczl.base.service.GczlYdxjSGZLSgnrService;
import com.crfeb.tbmpt.sys.model.User;
/**
 * <p>施工内容管理Service实现类</p>
 * <p>系统：</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-11-23</p>
 * @version 1.0
 * @author wangbinbin
 */
@Service
public class GczlYdxjSGZLSgnrServiceImpl extends CommonServiceImpl<GczlYdxjSGZLSgnrMapper, GczlYdxjSGZLSgnr> implements GczlYdxjSGZLSgnrService{

    @Autowired
    private GczlYdxjSGZLSgnrMapper gczlYdxjSGZLSgnrMapper;
    
    @Autowired
    private GczlYdxjSGZLJtwzMapper gczlYdxjSGZLJtwzMapper;
    
    @Autowired
    private GczlYdxjSGZLZlqxMapper gczlYdxjSGZLZlqxMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) {
       Page<GczlYdxjSGZLSgnr> page = new Page<GczlYdxjSGZLSgnr>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<GczlYdxjSGZLSgnr> list = gczlYdxjSGZLSgnrMapper.selectGczlYdxjSGZLSgnrList(page,condition);
       List<GczlYdxjSGZLSgnrDto> dtoList = new ArrayList<GczlYdxjSGZLSgnrDto>();
       GczlYdxjSGZLSgnrDto target = null;
       for(GczlYdxjSGZLSgnr entity :list){
    	   target = new GczlYdxjSGZLSgnrDto();
    	   this.BeanUtilsEntityToDto(entity, target);
    	   dtoList.add(target);
       }
       pageInfo.setRows(dtoList);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public void save(GczlYdxjSGZLSgnr gczlYdxjSGZLSgnr) {
    	  gczlYdxjSGZLSgnrMapper.insert(gczlYdxjSGZLSgnr);
    }

	   @Override
    public void del(String[] ids) {
		  List<String> idList = Arrays.asList(ids);
		  gczlYdxjSGZLSgnrMapper.deleteBatchIds(idList);
    }

    @Override
    public String update(GczlYdxjSGZLSgnrDto dto, User user) {
    	GczlYdxjSGZLSgnr oldEntity = this.gczlYdxjSGZLSgnrMapper.selectById(dto.getId());
    	String errorMessage = BaseService.operatorMessage(oldEntity, dto, user);
    	if(StringUtils.isNotBlank(errorMessage)) return errorMessage;
    	BeanUtils.copyProperties(dto, oldEntity);
		int i = gczlYdxjSGZLSgnrMapper.updateById(oldEntity);
		if(i<1){
			return "数据更新失败,请重试!";
		}
		return "";
    }

	   @Override
	   public GczlYdxjSGZLSgnr findById(String id) {
		   return gczlYdxjSGZLSgnrMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(GczlYdxjSGZLSgnrDto gczlYdxjSGZLSgnr, String[] clumNames) {
		   String id = gczlYdxjSGZLSgnr.getId();
		   Map map = BeanUtils.toMap(gczlYdxjSGZLSgnr);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<GczlYdxjSGZLSgnr> lists = gczlYdxjSGZLSgnrMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (GczlYdxjSGZLSgnr gczlYdxjSGZLSgnr2 : lists) {
				   String newId = gczlYdxjSGZLSgnr2.getId();
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
		List<GczlYdxjSGZLSgnr> entityList = gczlYdxjSGZLSgnrMapper.selectBatchIds(idList);
		for (GczlYdxjSGZLSgnr gczlYdxjSGZLSgnr : entityList) {
			gczlYdxjSGZLSgnr.setIfQy(state);
		}
		int num = gczlYdxjSGZLSgnrMapper.updateBatchById(entityList);
		return "成功更新"+num+"条数据!";
	}

	@Override
	public void BeanUtilsEntityToDto(GczlYdxjSGZLSgnr source, GczlYdxjSGZLSgnrDto target) {
		if(null != source){
			List<GczlYdxjSGZLJtwz> wzList = null;//施工内容对应具体位置信息
			List<GczlYdxjSGZLZlqx> zlqxList = null;//施工内容对应质量缺陷信息
			String wzNames = "";//具体位置名称信息
			String zlqxNames = "";//质量缺陷名称信息
			BeanUtils.copyProperties(source, target);
			//=============查询对应的具体位置信息================//
			wzList = gczlYdxjSGZLJtwzMapper.selectGczlYdxjSGZLJtwzListBySgNr(source.getId());
			if(null != wzList && wzList.size()>0)
			{
				for(GczlYdxjSGZLJtwz wzEntity : wzList){
					wzNames+=","+wzEntity.getJtWz();
				}
				wzNames = wzNames.substring(1);
				target.setJtWz(wzNames);
			}
			//===============查询对应的质量缺陷信息================//
			zlqxList = gczlYdxjSGZLZlqxMapper.selectGczlYdxjSGZLZlqxListBySgNr(source.getId());
			if(null != zlqxList && zlqxList.size()>0)
			{
				for(GczlYdxjSGZLZlqx zlqxEntity : zlqxList){
					zlqxNames+=","+zlqxEntity.getZlQx();
				}
				zlqxNames = zlqxNames.substring(1);
				target.setZlQx(zlqxNames);
			}
		}
	}

	@Override
	public List<GczlYdxjSGZLSgnrDto> selectDtoBatchIds(List<String> idList) {
		List<GczlYdxjSGZLSgnr> list = this.selectBatchIds(idList);
		List<GczlYdxjSGZLSgnrDto> dtoList = new ArrayList<GczlYdxjSGZLSgnrDto>();
		if(null != list && list.size()>0){
			GczlYdxjSGZLSgnrDto dto = null;
			for(GczlYdxjSGZLSgnr entity:list){
				dto = new GczlYdxjSGZLSgnrDto();
				 this.BeanUtilsEntityToDto(entity, dto);
				 dtoList.add(dto);
			}
		}
		return dtoList;
	}

	@Override
	public void deleteByIds(List<String> idlist) {
		if(null != idlist && idlist.size()>0){
			String sgNrIds ="";
			for(String id:idlist){
				sgNrIds +=",'"+id+"'";
			}
			sgNrIds = sgNrIds.substring(1);
			
			List<GczlYdxjSGZLJtwz> jtwzList = this.gczlYdxjSGZLJtwzMapper.selectBySgNrIds(sgNrIds);
			if(null != jtwzList && jtwzList.size()>0){
				List<String> jtwzlist = new ArrayList<String>();
				for(GczlYdxjSGZLJtwz jtwzEntity:jtwzList){
					jtwzlist.add(jtwzEntity.getId());
				}
				this.gczlYdxjSGZLJtwzMapper.deleteBatchIds(jtwzlist);//删除所关联的具体位置信息
			}
			
			List<GczlYdxjSGZLZlqx> zlqxList = this.gczlYdxjSGZLZlqxMapper.selectBySgNrIds(sgNrIds);
			if(null!=zlqxList && zlqxList.size()>0){
				List<String> zlqxlist = new ArrayList<String>();
				for(GczlYdxjSGZLZlqx zlqxEntity: zlqxList){
					zlqxlist.add(zlqxEntity.getId());
				}
				this.gczlYdxjSGZLZlqxMapper.deleteBatchIds(zlqxlist);//删除所关联的质量缺陷信息
			}
			this.gczlYdxjSGZLSgnrMapper.deleteBatchIds(idlist);//删除施工内容信息
		}
		
	}

	@Override
	public List<GczlYdxjSGZLSgnr> selectBySta(String sta) {
		return this.gczlYdxjSGZLSgnrMapper.selectBySta(sta);
	}

	@Override
	public void test(GczlYdxjSGZLSgnr entity) {
		String ss = this.gczlYdxjSGZLSgnrMapper.test(entity);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>");
		
	}
	@Override
	public int insert(GczlYdxjSGZLSgnrDto dto, User user) {
		GczlYdxjSGZLSgnr entity = new GczlYdxjSGZLSgnr();
    	BeanUtils.copyProperties(dto, entity);
    	BaseService.operatorMessage(entity, null,user );
    	return this.gczlYdxjSGZLSgnrMapper.insert(entity);
		
	}

}