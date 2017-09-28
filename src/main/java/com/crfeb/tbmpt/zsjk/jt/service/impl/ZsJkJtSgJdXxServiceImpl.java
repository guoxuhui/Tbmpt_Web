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
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtLjCzXxDto;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtSgJdXxDto;
import com.crfeb.tbmpt.zsjk.jt.mapper.ZsJkJtSgJdXxMapper;
import com.crfeb.tbmpt.zsjk.jt.model.ZsJkJtLjCzXx;
import com.crfeb.tbmpt.zsjk.jt.model.ZsJkJtSgJdXx;
import com.crfeb.tbmpt.zsjk.jt.service.ZsJkJtSgJdXxService;
/**
 * <p>所有项目总的施工进度信息Service实现类</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：集团角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@Service
public class ZsJkJtSgJdXxServiceImpl extends CommonServiceImpl<ZsJkJtSgJdXxMapper, ZsJkJtSgJdXx> implements ZsJkJtSgJdXxService{

    @Autowired
    private ZsJkJtSgJdXxMapper zsJkJtSgJdXxMapper;
    
    private int su;
	private int ex;
	private int total;
    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<ZsJkJtSgJdXx> page = new Page<ZsJkJtSgJdXx>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<ZsJkJtSgJdXx> list = zsJkJtSgJdXxMapper.selectZsJkJtSgJdXxList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(ZsJkJtSgJdXxDto zsJkJtSgJdXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkJtSgJdXx zsJkJtSgJdXx = new ZsJkJtSgJdXx();
        BeanUtils.copyProperties(zsJkJtSgJdXxDto, zsJkJtSgJdXx);
    	  zsJkJtSgJdXxMapper.insert(zsJkJtSgJdXx);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  zsJkJtSgJdXxMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

//    @Override
//    public String update(ZsJkJtSgJdXxDto zsJkJtSgJdXxDto,User user) throws Exception {
//    	  String errorMessage = "";
//        ZsJkJtSgJdXx zsJkJtSgJdXx = this.zsJkJtSgJdXxMapper.selectById(zsJkJtSgJdXxDto.getId());
//		  zsJkJtSgJdXxMapper.updateById(zsJkJtSgJdXx);
//    	  return errorMessage;
//    }
	   @Override
	    public String update(ZsJkJtSgJdXxDto zsJkJtSgJdXxDto,User user) throws Exception {
	    	  String errorMessage = "";
	    	  ZsJkJtSgJdXx zsJkJtSgJdXx=new ZsJkJtSgJdXx();
	    	  BeanUtils.copyProperties(zsJkJtSgJdXxDto, zsJkJtSgJdXx);	         
			  int a=zsJkJtSgJdXxMapper.updateById(zsJkJtSgJdXx);
			  if(a==1){
				  return errorMessage;
			  }else{
	    	  return errorMessage+"失败";
			  }
	    }

	   @Override
	   public ZsJkJtSgJdXx findById(String id) throws Exception {
		   return zsJkJtSgJdXxMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(ZsJkJtSgJdXxDto zsJkJtSgJdXxDto, String[] clumNames) throws Exception {
		   String id = zsJkJtSgJdXxDto.getId();
		   Map map = BeanUtils.toMap(zsJkJtSgJdXxDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<ZsJkJtSgJdXx> lists = zsJkJtSgJdXxMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (ZsJkJtSgJdXx zsJkJtSgJdXx2 : lists) {
				   String newId = zsJkJtSgJdXx2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

	@Override
	public ZsJkJtSgJdXxDto getAllXMjd(String year) throws Exception {
		ZsJkJtSgJdXxDto dto = new ZsJkJtSgJdXxDto();
		ZsJkJtSgJdXx paramEntity = new ZsJkJtSgJdXx();
		ZsJkJtSgJdXx resultEntity = new ZsJkJtSgJdXx();
		if(StringUtils.isBlank(year)){
			year = DateUtils.getCurrentYear();
		}
		paramEntity.setNd(year);
		resultEntity = zsJkJtSgJdXxMapper.selectOne(paramEntity);
		if(null != resultEntity){
			BeanUtils.copy(resultEntity, dto);
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
	public void selectSgJdXxDataGrid(PageInfo pageInfo) throws Exception {
		Page<ZsJkJtSgJdXx> page = new Page<ZsJkJtSgJdXx>(pageInfo.getNowpage(), pageInfo.getSize());
	       Map<String, Object> condition = pageInfo.getCondition();
	       List<ZsJkJtSgJdXx> list = zsJkJtSgJdXxMapper.selectZsJkJtSgJdXxDataGrid(page, condition);
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
		String[] header = { "*年度","*所有项目施工进度的百分比"};
		
		//sheet页名称
		String sheetName = "项目总的施工进度信息";
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
						ZsJkJtSgJdXx zsJkJtSgJdXx=new ZsJkJtSgJdXx();
						zsJkJtSgJdXx.setId(CommUtils.getUUID());
						String str=rowMap.get("年度");
						str=str.substring(0, 4);
						zsJkJtSgJdXx.setNd(str);
						String s=rowMap.get("所有项目施工进度的百分比");
						Double  d=Double.parseDouble(s);
					    java.text.NumberFormat percentFormat =java.text.NumberFormat.getPercentInstance(); 
					    percentFormat.setMaximumFractionDigits(2); //最大小数位数
					    percentFormat.setMaximumIntegerDigits(3);//最大整数位数
					    percentFormat.setMinimumFractionDigits(0); //最小小数位数
					    percentFormat.setMinimumIntegerDigits(1);//最小整数位数
					    s=percentFormat.format(d);//自动转换成百分比显示					 
						zsJkJtSgJdXx.setAllXMjdbfb(s);
						this.insert(zsJkJtSgJdXx);
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