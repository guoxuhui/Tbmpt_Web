package com.crfeb.tbmpt.zsjk.jt.service.impl;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.CommUtils;
import com.crfeb.tbmpt.commons.utils.ExcelUtils;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.dmcjjc.info.model.JcPoint;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.base.BaseService;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtAqZlMxXxDto;
import com.crfeb.tbmpt.zsjk.jt.mapper.ZsJkJtAqZlMxXxMapper;
import com.crfeb.tbmpt.zsjk.jt.model.ZsJkJtAqZlMxXx;
import com.crfeb.tbmpt.zsjk.jt.service.ZsJkJtAqZlMxXxService;
/**
 * <p>集团角度安全质量明细信息Service实现类</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：集团角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@Service
public class ZsJkJtAqZlMxXxServiceImpl extends CommonServiceImpl<ZsJkJtAqZlMxXxMapper, ZsJkJtAqZlMxXx> implements ZsJkJtAqZlMxXxService{

    @Autowired
    private ZsJkJtAqZlMxXxMapper zsJkJtAqZlMxXxMapper;
    
    private int su;
	private int ex;
	private int total;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<ZsJkJtAqZlMxXx> page = new Page<ZsJkJtAqZlMxXx>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<ZsJkJtAqZlMxXx> list = zsJkJtAqZlMxXxMapper.selectZsJkJtAqZlMxXxList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(ZsJkJtAqZlMxXxDto zsJkJtAqZlMxXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkJtAqZlMxXx zsJkJtAqZlMxXx = new ZsJkJtAqZlMxXx();
        BeanUtils.copyProperties(zsJkJtAqZlMxXxDto, zsJkJtAqZlMxXx);
    	  zsJkJtAqZlMxXxMapper.insert(zsJkJtAqZlMxXx);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  zsJkJtAqZlMxXxMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

//    @Override
//    public String update(ZsJkJtAqZlMxXxDto zsJkJtAqZlMxXxDto,User user) throws Exception {
//    	  String errorMessage = "";
//        ZsJkJtAqZlMxXx zsJkJtAqZlMxXx = this.zsJkJtAqZlMxXxMapper.selectById(zsJkJtAqZlMxXxDto.getId());
//		  zsJkJtAqZlMxXxMapper.updateById(zsJkJtAqZlMxXx);
//    	  return errorMessage;
//    }
	   @Override
	    public String update(ZsJkJtAqZlMxXxDto zsJkJtAqZlMxXxDto,User user) throws Exception {
	    	  String errorMessage = "";
	    	  ZsJkJtAqZlMxXx zsJkJtAqZlMxXx=new ZsJkJtAqZlMxXx();
	    	  BeanUtils.copyProperties(zsJkJtAqZlMxXxDto, zsJkJtAqZlMxXx);	         
			  int a=zsJkJtAqZlMxXxMapper.updateById(zsJkJtAqZlMxXx);
			  if(a==1){
				  return errorMessage;
			  }else{
	    	  return errorMessage+"失败";
			  }
	    }

	   @Override
	   public ZsJkJtAqZlMxXx findById(String id) throws Exception {
		   return zsJkJtAqZlMxXxMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(ZsJkJtAqZlMxXxDto zsJkJtAqZlMxXxDto, String[] clumNames) throws Exception {
		   String id = zsJkJtAqZlMxXxDto.getId();
		   Map map = BeanUtils.toMap(zsJkJtAqZlMxXxDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<ZsJkJtAqZlMxXx> lists = zsJkJtAqZlMxXxMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (ZsJkJtAqZlMxXx zsJkJtAqZlMxXx2 : lists) {
				   String newId = zsJkJtAqZlMxXx2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

	@Override
	public List<ZsJkJtAqZlMxXxDto> getAQZLMX(String strTime, String endTime,String xxfl) throws Exception {
		Map<String,Object>  columnMap = new HashMap<String,Object>();
		if(StringUtils.isNotBlank(strTime)){
			columnMap.put("strTime", strTime);
		}
		if(StringUtils.isNotBlank(endTime)){
			columnMap.put("endTime", endTime);
		}
		if(StringUtils.isNotBlank(xxfl)){
			columnMap.put("xxfl", xxfl);
		}
		return this.zsJkJtAqZlMxXxMapper.selectZsJkJtAqZlMxXxListByMap(columnMap);
	}
	
	//创建excel模板
		@Override
		public HSSFWorkbook generateExcelTemplate() {
			//列标题
			String[] header = { "*上报时间","*上报单位","*信息分类","详细信息","*上报人","图片链接","上报单位全称"};
			
			//sheet页名称
			String sheetName = "安全质量明细信息";
			//生成excel模版
			HSSFWorkbook wb = ExcelUtils.excelSheet(header, sheetName);
			HSSFSheet sheet = wb.getSheetAt(0);
	        HSSFRow row = sheet.getRow(0);
	        
	        ExcelUtils.resizeWidth(wb,row,sheet);
			return wb;
		}

	@Override
	public String transformationBingPreservation(List<List<Map<String, String>>> list) {
		String ring = "";
		su=0;
		ex=0;
		total=0;
		try {
				
			if(null!=list && !list.isEmpty()){
				
				for(List<Map<String, String>> ListP : list){
					//计算 总条数
					total +=  ListP.size();
					for(Map<String, String> rowMap : ListP){
						//监测点  对象
						ZsJkJtAqZlMxXx zsJkJtAqZlMxXx=new ZsJkJtAqZlMxXx();
						zsJkJtAqZlMxXx.setId(CommUtils.getUUID());
						zsJkJtAqZlMxXx.setSbdw(rowMap.get("上报单位"));
						zsJkJtAqZlMxXx.setSbdwqc(rowMap.get("上报单位全称"));
						zsJkJtAqZlMxXx.setSbr(rowMap.get("上报人"));
						zsJkJtAqZlMxXx.setSbrq(rowMap.get("上报时间"));
						zsJkJtAqZlMxXx.setTpurl(rowMap.get("图片链接"));
						zsJkJtAqZlMxXx.setXxfl(rowMap.get("信息分类"));
						zsJkJtAqZlMxXx.setXxxx(rowMap.get("详细信息"));
						this.insert(zsJkJtAqZlMxXx);
						su++;
						
					}
					
				}
			    
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
//		返回  拼接 提示 文 
		return calculationPrompt(ring);
	}

	private String calculationPrompt(String ring){
		
		if(su > 0){
			String SuccessRing =String.valueOf("总操作 "+total+" 条信息！");
			
			SuccessRing = String.valueOf(SuccessRing+"成功保存 "+su+" 条信息！ ");						
			return SuccessRing;
		}else{
			return null;
		}
	}
		
	

}