package com.crfeb.tbmpt.gpztcl.base.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.base.BaseService;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.CommUtils;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.gpztcl.base.mapper.GpztclSczxInfoMapper;
import com.crfeb.tbmpt.gpztcl.base.mapper.GpztclSczxpcMapper;
import com.crfeb.tbmpt.gpztcl.base.model.GpztclSczxInfo;
import com.crfeb.tbmpt.gpztcl.base.model.GpztclSczxpc;
import com.crfeb.tbmpt.gpztcl.base.model.dto.GpztclSczxInfoDto;
import com.crfeb.tbmpt.gpztcl.base.model.dto.GpztclSczxpcDto;
import com.crfeb.tbmpt.gpztcl.base.model.dto.TextFileDto;
import com.crfeb.tbmpt.gpztcl.base.service.GpztclSczxpcService;
import com.crfeb.tbmpt.sys.model.User;
/**
 * <p>实测中线信息管理Service实现类</p>
 * <p>系统：管片姿态测量系统</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-12-20</p>
 * @version 1.0
 * @author YangYj
 */
@Service
public class GpztclSczxpcServiceImpl extends CommonServiceImpl<GpztclSczxpcMapper, GpztclSczxpc> implements GpztclSczxpcService{

    @Autowired
    private GpztclSczxpcMapper gpztclSczxpcMapper;
    
    @Autowired
    private GpztclSczxInfoMapper gpztclSczxInfoMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<GpztclSczxpc> page = new Page<GpztclSczxpc>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<GpztclSczxpcDto> list = gpztclSczxpcMapper.selectGpztclSczxpcDtoList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(GpztclSczxpcDto gpztclSczxpcDto,User user) throws Exception {
    	  String errorMessage = "";
    	  String id = CommUtils.getUUID(); //主表主键
          GpztclSczxpc gpztclSczxpc = new GpztclSczxpc();
          BeanUtils.copyProperties(gpztclSczxpcDto, gpztclSczxpc);
    	  BaseService.operatorMessage(gpztclSczxpc, null, user);
    	  gpztclSczxpc.setImpMan(user.getEmpId());
    	  gpztclSczxpc.setImpTime(DateUtils.getToday());
    	  gpztclSczxpc.setSendState("未上报");
    	  gpztclSczxpc.setId(id);
    	  String json = gpztclSczxpcDto.getDataList();
    	  //子表信息
    	  if(!"[]".equals(json) && null != json){
  			json = json.replace("&quot;", "\"");
  			 List<GpztclSczxInfo> entityList = new ArrayList<GpztclSczxInfo>();  
  			entityList = JSONObject.parseArray(json, GpztclSczxInfo.class);
  			String startNo = "";//起始环号
  			String endNo = "";//截止环号
  		     for(GpztclSczxInfo detail:entityList){
  		    	detail.setId(CommUtils.getUUID());
  		    	detail.setFid(id);
  		    	//=================获取起始环号和截止环号==========================//
  		    	if(StringUtils.isBlank(endNo) || Integer.parseInt(detail.getHh()) >= Integer.parseInt(endNo)){
  		    		endNo = detail.getHh();
  		    	}
  		    	if(StringUtils.isBlank(startNo) ||  Integer.parseInt(detail.getHh()) <= Integer.parseInt(startNo)){
  		    		startNo = detail.getHh();
  		    	}
  		    	BaseService.operatorMessage(detail, null, user);
  		     }
  		   gpztclSczxpc.setStarNo(startNo);
  		   gpztclSczxpc.setEndNo(endNo);
  		     gpztclSczxpcMapper.insert(gpztclSczxpc);//保存主表
  		     this.gpztclSczxInfoMapper.insertBatch(entityList);//保存子表
    	  }
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  String fids = "";
		  for(String fid:idList){
			  fids +=",'"+fid+"'";
		  }
		  fids = fids.substring(1);
		  this.gpztclSczxInfoMapper.deleteByFids(fids);//删除明细表信息
		  gpztclSczxpcMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(GpztclSczxpcDto gpztclSczxpcDto,User user) throws Exception {
    	  String errorMessage = "";
        GpztclSczxpc gpztclSczxpc = this.gpztclSczxpcMapper.selectById(gpztclSczxpcDto.getId());
        errorMessage = BaseService.operatorMessage(gpztclSczxpc, gpztclSczxpcDto, user);
        if(StringUtils.isNotBlank(errorMessage)) return errorMessage;
        BeanUtils.copyProperties(gpztclSczxpcDto, gpztclSczxpc);
        String json = gpztclSczxpcDto.getDataList();
	  	  //子表信息
	  	  if(!"[]".equals(json) && null != json){
				json = json.replace("&quot;", "\"");
				 List<GpztclSczxInfo> entityList = new ArrayList<GpztclSczxInfo>();  
				entityList = JSONObject.parseArray(json, GpztclSczxInfo.class);
				String startNo = "";//起始环号
	  			String endNo = "";//截止环号
			     for(GpztclSczxInfo detail:entityList){
			    	detail.setId(CommUtils.getUUID());
			    	detail.setFid(gpztclSczxpc.getId());
			    	//=================获取起始环号和截止环号==========================//
	  		    	if(StringUtils.isBlank(endNo) || Integer.parseInt(detail.getHh()) >= Integer.parseInt(endNo)){
	  		    		endNo = detail.getHh();
	  		    	}
	  		    	if(StringUtils.isBlank(startNo) || Integer.parseInt(detail.getHh()) <= Integer.parseInt(startNo)){
	  		    		startNo = detail.getHh();
	  		    	}
			    	BaseService.operatorMessage(detail, null, user);
			     }
			     gpztclSczxpc.setStarNo(startNo);
		  		 gpztclSczxpc.setEndNo(endNo);
			     Map<String,Object> condition = new HashMap<String,Object>();
			     condition.put("fid", gpztclSczxpc.getId());
			     this.gpztclSczxInfoMapper.deleteByMap(condition);//删除主表之前的数据
			     gpztclSczxpcMapper.updateById(gpztclSczxpc);//更新主表
			     this.gpztclSczxInfoMapper.insertBatch(entityList);//保存子表
	  	  }
    	  return errorMessage;
    }

	   @Override
	   public GpztclSczxpc findById(String id) throws Exception {
		   return gpztclSczxpcMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(GpztclSczxpcDto gpztclSczxpcDto, String[] clumNames) throws Exception {
		   String id = gpztclSczxpcDto.getId();
		   Map map = BeanUtils.toMap(gpztclSczxpcDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        if(clum.equals("xlBh")){
		        	columnMap.put("xl_Bh", ss);
		        }else{
		        	columnMap.put(clum, ss);
		        }
		   }
		   List<GpztclSczxpc> lists = gpztclSczxpcMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前线路在"+lists.get(0).getClTime()+"的"+lists.get(0).getClType()+"数据已存在，不可重复添加。";
		   }else{//修改
			    for (GpztclSczxpc gpztclSczxpc2 : lists) {
				   String newId = gpztclSczxpc2.getId();
				   if(!newId.equals(id)){
					   return "当前线路在"+gpztclSczxpc2.getClTime()+"的"+gpztclSczxpc2.getClType()+"数据已存在，不可重复添加。";
				   }
			    }
		   }
		   return null;
    }

	@Override
	public String ifSb(String idss, String sbState) throws Exception {
		String[] idsArray = idss.split(",");
		String ids = "";
		for(String id:idsArray){
			ids +=",'"+id+"'";
		}
		ids = ids.substring(1);
		this.gpztclSczxpcMapper.updateSbStateByIds(ids, sbState);
		return "";
	}

	@Override
	public GpztclSczxpcDto selectDtoById(String id) throws Exception {
		return this.gpztclSczxpcMapper.selectDtoById(id);
	}

}