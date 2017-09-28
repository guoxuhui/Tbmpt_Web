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
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.base.BaseService;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtDjXhZTrXxDto;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtLjCzXxDto;
import com.crfeb.tbmpt.zsjk.jt.mapper.ZsJkJtLjCzXxMapper;
import com.crfeb.tbmpt.zsjk.jt.model.ZsJkJtDjXhZTrXx;
import com.crfeb.tbmpt.zsjk.jt.model.ZsJkJtLjCzXx;
import com.crfeb.tbmpt.zsjk.jt.service.ZsJkJtLjCzXxService;
/**
 * <p>当前所有项目总累计产值信息Service实现类</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：集团角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@Service
public class ZsJkJtLjCzXxServiceImpl extends CommonServiceImpl<ZsJkJtLjCzXxMapper, ZsJkJtLjCzXx> implements ZsJkJtLjCzXxService{

    @Autowired
    private ZsJkJtLjCzXxMapper zsJkJtLjCzXxMapper;
    
    private int su;
	private int ex;
	private int total;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<ZsJkJtLjCzXx> page = new Page<ZsJkJtLjCzXx>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<ZsJkJtLjCzXx> list = zsJkJtLjCzXxMapper.selectZsJkJtLjCzXxList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(ZsJkJtLjCzXxDto zsJkJtLjCzXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkJtLjCzXx zsJkJtLjCzXx = new ZsJkJtLjCzXx();
        BeanUtils.copyProperties(zsJkJtLjCzXxDto, zsJkJtLjCzXx);
    	  zsJkJtLjCzXxMapper.insert(zsJkJtLjCzXx);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  zsJkJtLjCzXxMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

//    @Override
//    public String update(ZsJkJtLjCzXxDto zsJkJtLjCzXxDto,User user) throws Exception {
//    	  String errorMessage = "";
//        ZsJkJtLjCzXx zsJkJtLjCzXx = this.zsJkJtLjCzXxMapper.selectById(zsJkJtLjCzXxDto.getId());
//		  zsJkJtLjCzXxMapper.updateById(zsJkJtLjCzXx);
//    	  return errorMessage;
//    }
	   
	   @Override
	    public String update(ZsJkJtLjCzXxDto zsJkJtLjCzXxDto,User user) throws Exception {
	    	  String errorMessage = "";
	    	  ZsJkJtLjCzXx zsJkJtLjCzXx=new ZsJkJtLjCzXx();
	    	  BeanUtils.copyProperties(zsJkJtLjCzXxDto, zsJkJtLjCzXx);	         
			  int a=zsJkJtLjCzXxMapper.updateById(zsJkJtLjCzXx);
			  if(a==1){
				  return errorMessage;
			  }else{
	    	  return errorMessage+"失败";
			  }
	    }

	   @Override
	   public ZsJkJtLjCzXx findById(String id) throws Exception {
		   return zsJkJtLjCzXxMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(ZsJkJtLjCzXxDto zsJkJtLjCzXxDto, String[] clumNames) throws Exception {
		   String id = zsJkJtLjCzXxDto.getId();
		   Map map = BeanUtils.toMap(zsJkJtLjCzXxDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<ZsJkJtLjCzXx> lists = zsJkJtLjCzXxMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (ZsJkJtLjCzXx zsJkJtLjCzXx2 : lists) {
				   String newId = zsJkJtLjCzXx2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

	@Override
	public ZsJkJtLjCzXxDto getAllXmljcz(String strTime, String endTime) throws Exception {
		ZsJkJtLjCzXxDto dto = new ZsJkJtLjCzXxDto();
		List<ZsJkJtLjCzXx> list = this.selectList(null);
		if(null != list && list.size()>0){
			ZsJkJtLjCzXx entity = new ZsJkJtLjCzXx();
			entity = list.get(0);
			BeanUtils.copy(entity, dto);
		}
		return dto;
	}
	/**
	 * 查询数据表格
	 * @author wl_zjh
	 * @param pageInfo
	 * @throws Exception
	 */
	@Override
	public void selectLjCzXxDataGrid(PageInfo pageInfo) throws Exception {
		// TODO Auto-generated method stub
		Page<ZsJkJtLjCzXx> page = new Page<ZsJkJtLjCzXx>(pageInfo.getNowpage(), pageInfo.getSize());
	       Map<String, Object> condition = pageInfo.getCondition();
	       List<ZsJkJtLjCzXx> list = zsJkJtLjCzXxMapper.selectZsJkJtLjCzXxDataGrid(page, condition);
	       pageInfo.setRows(list);
	       pageInfo.setTotal(page.getTotal());
	}
	/**
	 * 生成excel模板
	 * @author wl_zjh
	 * @return
	 */
	@Override
	public HSSFWorkbook generateExcelTemplate() {
		// TODO Auto-generated method stub
		//列标题
		String[] header = { "*计划总产值","*总累计产值"};
		
		//sheet页名称
		String sheetName = "项目总累计产值信息";
		//生成excel模版
		HSSFWorkbook wb = ExcelUtils.excelSheet(header, sheetName);
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow row = sheet.getRow(0);
		
		ExcelUtils.resizeWidth(wb,row,sheet);
		return wb;
	}
	
	/**
	 * 将集合转换成对象
	 * @author wl_zjh
	 * @param list
	 * @return
	 */
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
						ZsJkJtLjCzXx zsJkJtLjCzXx=new ZsJkJtLjCzXx();
						zsJkJtLjCzXx.setId(CommUtils.getUUID());						
						zsJkJtLjCzXx.setJhzcz(rowMap.get("计划总产值"));
						zsJkJtLjCzXx.setZljcz(rowMap.get("总累计产值"));
						this.insert(zsJkJtLjCzXx);
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