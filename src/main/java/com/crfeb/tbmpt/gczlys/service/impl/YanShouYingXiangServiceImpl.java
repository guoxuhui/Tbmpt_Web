package com.crfeb.tbmpt.gczlys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.crfeb.tbmpt.open.model.YanShouYingXiangInfo;
import com.crfeb.tbmpt.sys.base.model.SysFujian;
import com.crfeb.tbmpt.sys.base.model.dto.SysFujianDto;
import com.crfeb.tbmpt.sys.base.service.SysFujianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.aqxj.model.AqxjXjdfl;
import com.crfeb.tbmpt.aqxj.model.dto.AqxjXjdflDto;
import com.crfeb.tbmpt.commons.base.BaseService;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.gczl.base.mapper.GczlYdxjSGZLJtwzMapper;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLJtwz;
import com.crfeb.tbmpt.gczl.base.service.GczlYdxjSGZLJtwzService;
import com.crfeb.tbmpt.gczlys.mapper.YanShouYingXiangMapper;
import com.crfeb.tbmpt.gczlys.model.YanShouYingXiang;
import com.crfeb.tbmpt.gczlys.model.dto.YanShouYingXiangDto;
import com.crfeb.tbmpt.gczlys.service.YanShouYingXiangService;
import com.crfeb.tbmpt.sys.model.User;


@Service
public class YanShouYingXiangServiceImpl extends CommonServiceImpl<YanShouYingXiangMapper, YanShouYingXiang> implements YanShouYingXiangService{

	@Autowired
	private YanShouYingXiangMapper yanShouYingXiangMapper;

	@Autowired
	private SysFujianService sysFujianService;
	
	
	@Override
	public void selectDataGrid(PageInfo pageInfo) {
		 Page<YanShouYingXiang> page = new Page<YanShouYingXiang>(pageInfo.getNowpage(), pageInfo.getSize());
	       Map<String, Object> condition = pageInfo.getCondition();
	       List<YanShouYingXiang> list = yanShouYingXiangMapper.selectYanShouYingXiangList(page,condition);
	       pageInfo.setRows(list);
	       pageInfo.setTotal(page.getTotal());
		
	}


	@Override
	public String update(YanShouYingXiang yanShouYingXiang, User user) {
		YanShouYingXiang oldEntity = this.yanShouYingXiangMapper.selectById(yanShouYingXiang.getId());
    	String errorMessage = BaseService.operatorMessage(oldEntity, yanShouYingXiang, user);
    	if(StringUtils.isNotBlank(errorMessage)) return errorMessage;
    	BeanUtils.copyProperties(yanShouYingXiang, oldEntity);
		int i = yanShouYingXiangMapper.updateById(oldEntity);
		if(i<1){
			return "数据更新失败,请重试!";
		}
		return "";
	}


	@Override
	public int insert(YanShouYingXiang yanShouYingXiang, User user) {
    	BaseService.operatorMessage(yanShouYingXiang, null,user );  //存储
    	return this.yanShouYingXiangMapper.insert(yanShouYingXiang);
	}


	@Override
	public String checkClumIfexits(YanShouYingXiang yanShouYingXiang, String[] clumNames) {
		   String id = yanShouYingXiang.getId();
		   Map map = BeanUtils.toMap(yanShouYingXiang);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames){
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<YanShouYingXiang> lists = yanShouYingXiangMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前工序下已存在相同的检查情况!";
		   }else{//修改
			    for (YanShouYingXiang YanShouYingXiang1 : lists) {
				   String newId = YanShouYingXiang1.getId();
				   if(!newId.equals(id)){
					   return "当前工序下已存在相同的检查情况!";
				   }
			    }
		   }
		   return null;
	}

	/**
	 * 根据条件获取巡检日历信息
	 *
	 * @param condition
	 * @return
	 */
	@Override
	public List<Map<String, Object>> selectReport(Map<String, Object> condition) {
		return yanShouYingXiangMapper.selectReport(condition);
	}

	@Override
	public void selectDataGridList(PageInfo pageInfo) {
		Page<YanShouYingXiang> page = new Page<YanShouYingXiang>(pageInfo.getNowpage(), pageInfo.getSize());
		Map<String, Object> condition = pageInfo.getCondition();
		List<YanShouYingXiang> list = yanShouYingXiangMapper.selectYanShouYingXiangList(page, condition);
		List<YanShouYingXiangInfo> yanShouYingXiangInfos = new ArrayList<>();
		for(int i = 0;i<list.size();i++){
			YanShouYingXiangInfo yanShouYingXiangInfo = new YanShouYingXiangInfo();
			BeanUtils.copyProperties(list.get(i),yanShouYingXiangInfo);
			try{
				List<SysFujianDto> sysFujianList =sysFujianService.findFuJianListByForeignId(list.get(i).getId(),null,null);
				for(int j = 0; j<sysFujianList.size();j++){
					yanShouYingXiangInfo.getFileNames().add(sysFujianList.get(j).getFileName());
					yanShouYingXiangInfo.getMinifileNames().add(sysFujianList.get(j).getMinImgPath());
					yanShouYingXiangInfo.getOriginalfileNames().add(sysFujianList.get(j).getFilePath());
				}
			}catch (Exception e){
				e.printStackTrace();
			}

			yanShouYingXiangInfos.add(yanShouYingXiangInfo);

		}
		pageInfo.setRows(yanShouYingXiangInfos);
		pageInfo.setTotal(page.getTotal());

	}
}
