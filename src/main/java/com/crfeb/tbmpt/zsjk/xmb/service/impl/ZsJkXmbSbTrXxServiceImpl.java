package com.crfeb.tbmpt.zsjk.xmb.service.impl;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.base.BaseService;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbSbTrXxDto;
import com.crfeb.tbmpt.zsjk.xmb.mapper.ZsJkXmbSbTrXxMapper;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbSbTrXx;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbSbTrXxService;
/**
 * <p>项目部角度 盾构机、电瓶车、龙门吊等主要设备的运行与投入情况Service实现类</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@Service
public class ZsJkXmbSbTrXxServiceImpl extends CommonServiceImpl<ZsJkXmbSbTrXxMapper, ZsJkXmbSbTrXx> implements ZsJkXmbSbTrXxService{

    @Autowired
    private ZsJkXmbSbTrXxMapper zsJkXmbSbTrXxMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<ZsJkXmbSbTrXx> page = new Page<ZsJkXmbSbTrXx>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<ZsJkXmbSbTrXx> list = zsJkXmbSbTrXxMapper.selectZsJkXmbSbTrXxList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(ZsJkXmbSbTrXxDto zsJkXmbSbTrXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbSbTrXx zsJkXmbSbTrXx = new ZsJkXmbSbTrXx();
        BeanUtils.copyProperties(zsJkXmbSbTrXxDto, zsJkXmbSbTrXx);
    	  zsJkXmbSbTrXxMapper.insert(zsJkXmbSbTrXx);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  zsJkXmbSbTrXxMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(ZsJkXmbSbTrXxDto zsJkXmbSbTrXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbSbTrXx zsJkXmbSbTrXx = this.zsJkXmbSbTrXxMapper.selectById(zsJkXmbSbTrXxDto.getId());
		  zsJkXmbSbTrXxMapper.updateById(zsJkXmbSbTrXx);
    	  return errorMessage;
    }

	   @Override
	   public ZsJkXmbSbTrXx findById(String id) throws Exception {
		   return zsJkXmbSbTrXxMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(ZsJkXmbSbTrXxDto zsJkXmbSbTrXxDto, String[] clumNames) throws Exception {
		   String id = zsJkXmbSbTrXxDto.getId();
		   Map map = BeanUtils.toMap(zsJkXmbSbTrXxDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<ZsJkXmbSbTrXx> lists = zsJkXmbSbTrXxMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (ZsJkXmbSbTrXx zsJkXmbSbTrXx2 : lists) {
				   String newId = zsJkXmbSbTrXx2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

	@Override
	public List<ZsJkXmbSbTrXxDto> xmsbtr(String xmId) throws Exception {
		List<ZsJkXmbSbTrXxDto> dtoList = new ArrayList<ZsJkXmbSbTrXxDto>();
		if(StringUtils.isNotBlank(xmId)){
			List<ZsJkXmbSbTrXx> entityList = new ArrayList<ZsJkXmbSbTrXx>();
			Map<String,Object> columnMap = new HashMap<String,Object>();
			columnMap.put("xmId", xmId);
			entityList = this.selectByMap(columnMap);
			if(null != entityList && entityList.size()>0){
				ZsJkXmbSbTrXxDto dto = new ZsJkXmbSbTrXxDto();
				for(ZsJkXmbSbTrXx entity : entityList){
					dto = new ZsJkXmbSbTrXxDto();
					BeanUtils.copy(entity, dto);
					dtoList.add(dto);
				}
			}
		}
		return dtoList;
	}

}