package com.crfeb.tbmpt.sys.base.service.impl;

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
import com.crfeb.tbmpt.commons.utils.UploadUtil;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.base.BaseService;
import com.crfeb.tbmpt.sys.base.model.dto.SysFujianDto;
import com.crfeb.tbmpt.sys.base.mapper.SysFujianMapper;
import com.crfeb.tbmpt.sys.base.model.SysFujian;
import com.crfeb.tbmpt.sys.base.service.SysFujianService;
/**
 * <p>公共统一附件Service实现类</p>
 * <p>系统：系统管理</p>
 * <p>模块：公共模块</p>
 * <p>日期：2017-02-17</p>
 * @version 1.0
 * @author wangbinbin
 */
@Service
public class SysFujianServiceImpl extends CommonServiceImpl<SysFujianMapper, SysFujian> implements SysFujianService{

    @Autowired
    private SysFujianMapper sysFujianMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<SysFujian> page = new Page<SysFujian>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<SysFujian> list = sysFujianMapper.selectSysFujianList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(SysFujianDto sysFujianDto,User user) throws Exception {
    	  String errorMessage = "";
        SysFujian sysFujian = new SysFujian();
        BeanUtils.copyProperties(sysFujianDto, sysFujian);
    	  BaseService.operatorMessage(sysFujian, null, user);
    	  sysFujianMapper.insert(sysFujian);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  sysFujianMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(SysFujianDto sysFujianDto,User user) throws Exception {
    	  String errorMessage = "";
        SysFujian sysFujian = this.sysFujianMapper.selectById(sysFujianDto.getId());
        errorMessage = BaseService.operatorMessage(sysFujian, sysFujianDto, user);
		  sysFujianMapper.updateById(sysFujian);
    	  return errorMessage;
    }

	   @Override
	   public SysFujian findById(String id) throws Exception {
		   return sysFujianMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(SysFujianDto sysFujianDto, String[] clumNames) throws Exception {
		   String id = sysFujianDto.getId();
		   Map map = BeanUtils.toMap(sysFujianDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<SysFujian> lists = sysFujianMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (SysFujian sysFujian2 : lists) {
				   String newId = sysFujian2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

	@Override
	public List<SysFujianDto> findFuJianListByForeignId(String foreignId,String backupOne,String backupTwo) throws Exception {
		Map<String, Object> columnMap = new HashMap<>();
		if(StringUtils.isNotBlank(foreignId)){
			columnMap.put("foreignId", foreignId);
		}else return null;
		if(StringUtils.isNotBlank(backupOne)) columnMap.put("backupOne", backupOne);
		if(StringUtils.isNotBlank(backupTwo)) columnMap.put("backupTwo", backupTwo);
		List<SysFujianDto> listdto = new ArrayList<>(); 
		List<SysFujian> list = this.sysFujianMapper.selectByMap(columnMap);
		if(list.size()>0)
		for (SysFujian sysFujian : list) {
			SysFujianDto dto = new SysFujianDto();
			BeanUtils.copyProperties(sysFujian, dto);
			listdto.add(dto);
		}
		return listdto;
	}

	@Override
	public String save(List<SysFujianDto> sysfjList, User user) throws Exception {
		for (SysFujianDto sysFujianDto : sysfjList) {
			this.save(sysFujianDto, user);
		}
		return "";
	}

	@Override
	public void deleteFujianByForeignId(String rootPath, String foreignId) throws Exception {
		List<SysFujianDto> list = this.findFuJianListByForeignId(foreignId, null, null);
		if(null != list && list.size()>0){
			for(SysFujianDto fujian:list){
				//删除附件表中信息
				this.deleteById(fujian.getId());
				//删除附件文件
				UploadUtil uploadutil = new UploadUtil();
				uploadutil.deleteFile(rootPath+fujian.getFilePath());
				if(StringUtils.isNotBlank(fujian.getMinImgPath())){
					uploadutil.deleteFile(rootPath+fujian.getMinImgPath());
				}
			}
		}
	}
}