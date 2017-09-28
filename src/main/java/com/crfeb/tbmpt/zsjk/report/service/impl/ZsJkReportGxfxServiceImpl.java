package com.crfeb.tbmpt.zsjk.report.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.sys.model.User;

import com.crfeb.tbmpt.zsjk.report.mapper.ZsJkReportGxfxMapper;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportGxfx;
import com.crfeb.tbmpt.zsjk.report.model.dto.ZsJkReportGxfxDto;
import com.crfeb.tbmpt.zsjk.report.service.ZsJkReportGxfxService;


/**
 * @author wl_zjh
 *	工效分析
 */
@Service
public class ZsJkReportGxfxServiceImpl extends CommonServiceImpl<ZsJkReportGxfxMapper, ZsJkReportGxfx> implements ZsJkReportGxfxService{

    @Autowired
    private ZsJkReportGxfxMapper zsJkReportGxfxMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<ZsJkReportGxfx> page = new Page<ZsJkReportGxfx>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<ZsJkReportGxfx> list = zsJkReportGxfxMapper.selectZsJkReportGxfxList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(ZsJkReportGxfxDto zsJkReportGxfxDto,User user) throws Exception {
    	  String errorMessage = "";
    	  ZsJkReportGxfx zsJkReportGxfx = new ZsJkReportGxfx();
        BeanUtils.copyProperties(zsJkReportGxfxDto, zsJkReportGxfx);
        zsJkReportGxfxMapper.insert(zsJkReportGxfx);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  zsJkReportGxfxMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(ZsJkReportGxfxDto zsJkReportGxfxDto,User user) throws Exception {
    	  String errorMessage = "";
    	  ZsJkReportGxfx zsJkReportGxfx = this.zsJkReportGxfxMapper.selectById(zsJkReportGxfxDto.getId());
    	  zsJkReportGxfxMapper.updateById(zsJkReportGxfx);
    	  return errorMessage;
    }

	   @Override
	   public ZsJkReportGxfx findById(String id) throws Exception {
		   return zsJkReportGxfxMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(ZsJkReportGxfxDto zsJkReportGxfxDto, String[] clumNames) throws Exception {
		   String id = zsJkReportGxfxDto.getId();
		   Map map = BeanUtils.toMap(zsJkReportGxfxDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<ZsJkReportGxfx> lists = zsJkReportGxfxMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (ZsJkReportGxfx ZsJkReportGxfx2 : lists) {
				   String newId = ZsJkReportGxfx2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }
    
    /**
     * @param kssj 开始时间
     * @param jssj 结束时间
     * @param kshh 开始环号
     * @param jshh 结束环号
     * @param proId 项目ID
     * @param xlId 线路ID
     */
	@Override
	public List<ZsJkReportGxfxDto> gxfx(String kssj, String jssj,String kshh,String jshh,String proId,String lineId) throws Exception {
		Map<String,Object>  columnMap = new HashMap<String,Object>();
		if(StringUtils.isNotBlank(kssj)){
			columnMap.put("kssj", kssj);
		}
		if(StringUtils.isNotBlank(jssj)){
			columnMap.put("jssj", jssj);
		}
		if(StringUtils.isNotBlank(kshh)){
			int k=Integer.valueOf(kshh);
			columnMap.put("kshh", k);
		}
		if(StringUtils.isNotBlank(jshh)){
			int j=Integer.valueOf(jshh);
			columnMap.put("jshh", j);
		}
		if(StringUtils.isNotBlank(proId)){
			columnMap.put("proId", proId);
		}
		if(StringUtils.isNotBlank(lineId)){
			columnMap.put("lineId", lineId);
		}
		List<ZsJkReportGxfxDto> list= zsJkReportGxfxMapper.selectZsJkReportGxfxListByMap(columnMap);
		return list;
	}

	

}
