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
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbGpZlWtsXxDto;
import com.crfeb.tbmpt.zsjk.xmb.mapper.ZsJkXmbGpZlWtsXxMapper;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbGpZlWtsXx;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbGpZlWtsXxService;
/**
 * <p>项目部角度  各质量分类（管片破损、管片错台、螺栓复紧、渗漏水等）下的管片质量问题数量信息Service实现类</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@Service
public class ZsJkXmbGpZlWtsXxServiceImpl extends CommonServiceImpl<ZsJkXmbGpZlWtsXxMapper, ZsJkXmbGpZlWtsXx> implements ZsJkXmbGpZlWtsXxService{

    @Autowired
    private ZsJkXmbGpZlWtsXxMapper zsJkXmbGpZlWtsXxMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<ZsJkXmbGpZlWtsXx> page = new Page<ZsJkXmbGpZlWtsXx>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<ZsJkXmbGpZlWtsXx> list = zsJkXmbGpZlWtsXxMapper.selectZsJkXmbGpZlWtsXxList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(ZsJkXmbGpZlWtsXxDto zsJkXmbGpZlWtsXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbGpZlWtsXx zsJkXmbGpZlWtsXx = new ZsJkXmbGpZlWtsXx();
        BeanUtils.copyProperties(zsJkXmbGpZlWtsXxDto, zsJkXmbGpZlWtsXx);
    	  zsJkXmbGpZlWtsXxMapper.insert(zsJkXmbGpZlWtsXx);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  zsJkXmbGpZlWtsXxMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(ZsJkXmbGpZlWtsXxDto zsJkXmbGpZlWtsXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbGpZlWtsXx zsJkXmbGpZlWtsXx = this.zsJkXmbGpZlWtsXxMapper.selectById(zsJkXmbGpZlWtsXxDto.getId());
		  zsJkXmbGpZlWtsXxMapper.updateById(zsJkXmbGpZlWtsXx);
    	  return errorMessage;
    }

	   @Override
	   public ZsJkXmbGpZlWtsXx findById(String id) throws Exception {
		   return zsJkXmbGpZlWtsXxMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(ZsJkXmbGpZlWtsXxDto zsJkXmbGpZlWtsXxDto, String[] clumNames) throws Exception {
		   String id = zsJkXmbGpZlWtsXxDto.getId();
		   Map map = BeanUtils.toMap(zsJkXmbGpZlWtsXxDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<ZsJkXmbGpZlWtsXx> lists = zsJkXmbGpZlWtsXxMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (ZsJkXmbGpZlWtsXx zsJkXmbGpZlWtsXx2 : lists) {
				   String newId = zsJkXmbGpZlWtsXx2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

	@Override
	public List<ZsJkXmbGpZlWtsXxDto> gpzl(String projectId, String startTime, String endTime) throws Exception {
		List<ZsJkXmbGpZlWtsXxDto> dtoList = new ArrayList<ZsJkXmbGpZlWtsXxDto>();
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("projectId", projectId);
		condition.put("startTime", startTime);
		condition.put("endTime", endTime);
		dtoList = this.zsJkXmbGpZlWtsXxMapper.selectZsJkXmbGpZlWtsXxDtoList(condition);
		return dtoList;
	}

}