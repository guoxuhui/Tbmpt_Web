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
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.ExcelUtils;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.base.BaseService;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtDjXhZTrXxDto;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtdgSgRyTrXxDto;
import com.crfeb.tbmpt.zsjk.jt.mapper.ZsJkJtDjXhZTrXxMapper;
import com.crfeb.tbmpt.zsjk.jt.model.ZsJkJtDjXhZTrXx;
import com.crfeb.tbmpt.zsjk.jt.model.ZsJkJtdgSgRyTrXx;
import com.crfeb.tbmpt.zsjk.jt.service.ZsJkJtDjXhZTrXxService;
/**
 * <p>集团角度刀具消耗总投入信息Service实现类</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：集团角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@Service
public class ZsJkJtDjXhZTrXxServiceImpl extends CommonServiceImpl<ZsJkJtDjXhZTrXxMapper, ZsJkJtDjXhZTrXx> implements ZsJkJtDjXhZTrXxService{

    @Autowired
    private ZsJkJtDjXhZTrXxMapper zsJkJtDjXhZTrXxMapper;
    
    private int su;
	private int ex;
	private int total;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<ZsJkJtDjXhZTrXx> page = new Page<ZsJkJtDjXhZTrXx>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<ZsJkJtDjXhZTrXx> list = zsJkJtDjXhZTrXxMapper.selectZsJkJtDjXhZTrXxList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(ZsJkJtDjXhZTrXxDto zsJkJtDjXhZTrXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkJtDjXhZTrXx zsJkJtDjXhZTrXx = new ZsJkJtDjXhZTrXx();
        BeanUtils.copyProperties(zsJkJtDjXhZTrXxDto, zsJkJtDjXhZTrXx);
    	  zsJkJtDjXhZTrXxMapper.insert(zsJkJtDjXhZTrXx);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  zsJkJtDjXhZTrXxMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

//    @Override
//    public String update(ZsJkJtDjXhZTrXxDto zsJkJtDjXhZTrXxDto,User user) throws Exception {
//    	  String errorMessage = "";
//        ZsJkJtDjXhZTrXx zsJkJtDjXhZTrXx = this.zsJkJtDjXhZTrXxMapper.selectById(zsJkJtDjXhZTrXxDto.getId());
//		  zsJkJtDjXhZTrXxMapper.updateById(zsJkJtDjXhZTrXx);
//    	  return errorMessage;
//    }
	   @Override
	    public String update(ZsJkJtDjXhZTrXxDto zsJkJtDjXhZTrXxDto,User user) throws Exception {
	    	  String errorMessage = "";
	    	  ZsJkJtDjXhZTrXx zsJkJtDjXhZTrXx=new ZsJkJtDjXhZTrXx();
	    	  BeanUtils.copyProperties(zsJkJtDjXhZTrXxDto, zsJkJtDjXhZTrXx);	         
			  int a=zsJkJtDjXhZTrXxMapper.updateById(zsJkJtDjXhZTrXx);
			  if(a==1){
				  return errorMessage;
			  }else{
	    	  return errorMessage+"失败";
			  }
	    }
	   
	   @Override
	   public ZsJkJtDjXhZTrXx findById(String id) throws Exception {
		   return zsJkJtDjXhZTrXxMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(ZsJkJtDjXhZTrXxDto zsJkJtDjXhZTrXxDto, String[] clumNames) throws Exception {
		   String id = zsJkJtDjXhZTrXxDto.getId();
		   Map map = BeanUtils.toMap(zsJkJtDjXhZTrXxDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<ZsJkJtDjXhZTrXx> lists = zsJkJtDjXhZTrXxMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (ZsJkJtDjXhZTrXx zsJkJtDjXhZTrXx2 : lists) {
				   String newId = zsJkJtDjXhZTrXx2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

	@Override
	public ZsJkJtDjXhZTrXxDto djxhztr(String nd) throws Exception {
		if(StringUtils.isBlank(nd)){
			nd = DateUtils.getCurrentYear();
		}
		ZsJkJtDjXhZTrXx param = new ZsJkJtDjXhZTrXx();
		ZsJkJtDjXhZTrXx entity = new ZsJkJtDjXhZTrXx();
		ZsJkJtDjXhZTrXxDto dto = new ZsJkJtDjXhZTrXxDto();
		param.setNd(nd);
		entity = this.zsJkJtDjXhZTrXxMapper.selectOne(param);
		if(null != entity){
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
	public void selectdjXhZTrXxDataGrid(PageInfo pageInfo) throws Exception {
	   Page<ZsJkJtDjXhZTrXx> page = new Page<ZsJkJtDjXhZTrXx>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<ZsJkJtDjXhZTrXx> list = zsJkJtDjXhZTrXxMapper.selectZsJkJtDjXhZTrXxDataGrid(page, condition);
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
		//列标题
		String[] header = { "*年度","*刀具消耗计划投入值","*刀具消耗实际投入值"};
		
		//sheet页名称
		String sheetName = "刀具消耗总投入信息";
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
						ZsJkJtDjXhZTrXx zsJkJtDjXhZTrXx=new ZsJkJtDjXhZTrXx();
						zsJkJtDjXhZTrXx.setId(CommUtils.getUUID());
						String str =rowMap.get("年度");
						str=str.substring(0, 4);
						zsJkJtDjXhZTrXx.setNd(str);
						zsJkJtDjXhZTrXx.setJhTrz(rowMap.get("刀具消耗计划投入值"));
						zsJkJtDjXhZTrXx.setSjTrz(rowMap.get("刀具消耗实际投入值"));
						this.insert(zsJkJtDjXhZTrXx);
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