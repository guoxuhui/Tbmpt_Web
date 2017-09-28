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
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbGpZtClXxDto;
import com.crfeb.tbmpt.zsjk.xmb.mapper.ZsJkXmbGpZtClXxMapper;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbGpZtClXx;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbGpZtClXxService;
/**
 * <p>项目部角度  管片姿态测量信息Service实现类</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@Service
public class ZsJkXmbGpZtClXxServiceImpl extends CommonServiceImpl<ZsJkXmbGpZtClXxMapper, ZsJkXmbGpZtClXx> implements ZsJkXmbGpZtClXxService{

    @Autowired
    private ZsJkXmbGpZtClXxMapper zsJkXmbGpZtClXxMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<ZsJkXmbGpZtClXx> page = new Page<ZsJkXmbGpZtClXx>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<ZsJkXmbGpZtClXx> list = zsJkXmbGpZtClXxMapper.selectZsJkXmbGpZtClXxList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(ZsJkXmbGpZtClXxDto zsJkXmbGpZtClXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbGpZtClXx zsJkXmbGpZtClXx = new ZsJkXmbGpZtClXx();
        BeanUtils.copyProperties(zsJkXmbGpZtClXxDto, zsJkXmbGpZtClXx);
    	  zsJkXmbGpZtClXxMapper.insert(zsJkXmbGpZtClXx);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  zsJkXmbGpZtClXxMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(ZsJkXmbGpZtClXxDto zsJkXmbGpZtClXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbGpZtClXx zsJkXmbGpZtClXx = this.zsJkXmbGpZtClXxMapper.selectById(zsJkXmbGpZtClXxDto.getId());
		  zsJkXmbGpZtClXxMapper.updateById(zsJkXmbGpZtClXx);
    	  return errorMessage;
    }

	   @Override
	   public ZsJkXmbGpZtClXx findById(String id) throws Exception {
		   return zsJkXmbGpZtClXxMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(ZsJkXmbGpZtClXxDto zsJkXmbGpZtClXxDto, String[] clumNames) throws Exception {
		   String id = zsJkXmbGpZtClXxDto.getId();
		   Map map = BeanUtils.toMap(zsJkXmbGpZtClXxDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<ZsJkXmbGpZtClXx> lists = zsJkXmbGpZtClXxMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (ZsJkXmbGpZtClXx zsJkXmbGpZtClXx2 : lists) {
				   String newId = zsJkXmbGpZtClXx2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

	@Override
	public List<ZsJkXmbGpZtClXxDto> gpztxx(String ID, String qshh, String jzhh) throws Exception {
		List<ZsJkXmbGpZtClXxDto> dtoList = new ArrayList<ZsJkXmbGpZtClXxDto>();
		if(StringUtils.isNotBlank(ID)){
			Map<String,Object> columnMap = new HashMap<String,Object>();
			columnMap.put("ID", ID);
			columnMap.put("qshh", qshh);
			columnMap.put("jzhh", jzhh);
			dtoList = this.zsJkXmbGpZtClXxMapper.selectZsJkXmbGpZtClXxDtoList(columnMap);
		}
		return dtoList;
	}

}