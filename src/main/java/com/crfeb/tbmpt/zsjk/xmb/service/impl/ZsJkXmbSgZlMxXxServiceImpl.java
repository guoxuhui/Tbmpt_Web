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
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbSgZlMxXxDto;
import com.crfeb.tbmpt.zsjk.xmb.mapper.ZsJkXmbSgZlMxXxMapper;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbSgZlMxXx;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbSgZlMxXxService;
/**
 * <p>项目部角度  结构施工质量问题明细信息Service实现类</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@Service
public class ZsJkXmbSgZlMxXxServiceImpl extends CommonServiceImpl<ZsJkXmbSgZlMxXxMapper, ZsJkXmbSgZlMxXx> implements ZsJkXmbSgZlMxXxService{

    @Autowired
    private ZsJkXmbSgZlMxXxMapper zsJkXmbSgZlMxXxMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<ZsJkXmbSgZlMxXx> page = new Page<ZsJkXmbSgZlMxXx>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<ZsJkXmbSgZlMxXx> list = zsJkXmbSgZlMxXxMapper.selectZsJkXmbSgZlMxXxList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(ZsJkXmbSgZlMxXxDto zsJkXmbSgZlMxXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbSgZlMxXx zsJkXmbSgZlMxXx = new ZsJkXmbSgZlMxXx();
        BeanUtils.copyProperties(zsJkXmbSgZlMxXxDto, zsJkXmbSgZlMxXx);
    	  zsJkXmbSgZlMxXxMapper.insert(zsJkXmbSgZlMxXx);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  zsJkXmbSgZlMxXxMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(ZsJkXmbSgZlMxXxDto zsJkXmbSgZlMxXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbSgZlMxXx zsJkXmbSgZlMxXx = this.zsJkXmbSgZlMxXxMapper.selectById(zsJkXmbSgZlMxXxDto.getId());
		  zsJkXmbSgZlMxXxMapper.updateById(zsJkXmbSgZlMxXx);
    	  return errorMessage;
    }

	   @Override
	   public ZsJkXmbSgZlMxXx findById(String id) throws Exception {
		   return zsJkXmbSgZlMxXxMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(ZsJkXmbSgZlMxXxDto zsJkXmbSgZlMxXxDto, String[] clumNames) throws Exception {
		   String id = zsJkXmbSgZlMxXxDto.getId();
		   Map map = BeanUtils.toMap(zsJkXmbSgZlMxXxDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<ZsJkXmbSgZlMxXx> lists = zsJkXmbSgZlMxXxMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (ZsJkXmbSgZlMxXx zsJkXmbSgZlMxXx2 : lists) {
				   String newId = zsJkXmbSgZlMxXx2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

	@Override
	public List<ZsJkXmbSgZlMxXxDto> sgzlmx(String projectId, String shigzlfl, String date) throws Exception {
		List<ZsJkXmbSgZlMxXxDto> dtoList = new ArrayList<ZsJkXmbSgZlMxXxDto>();
		if(StringUtils.isNotBlank(projectId)){
			Map<String,Object> columnMap = new HashMap<String,Object>();
			columnMap.put("projectId", projectId);
			columnMap.put("type", shigzlfl);
			columnMap.put("sbDate", date);
			dtoList =  this.zsJkXmbSgZlMxXxMapper.selectZsJkXmbSgZlMxXxDtoList(columnMap);
		}
		return dtoList;
	}

}