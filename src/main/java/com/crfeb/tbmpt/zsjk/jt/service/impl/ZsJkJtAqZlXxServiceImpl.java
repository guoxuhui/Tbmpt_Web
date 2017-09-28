package com.crfeb.tbmpt.zsjk.jt.service.impl;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.CommUtils;
import com.crfeb.tbmpt.commons.utils.ExcelUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.zsjk.jt.mapper.ZsJkJtAqZlXxMapper;
import com.crfeb.tbmpt.zsjk.jt.model.ZsJkJtAqZlMxXx;
import com.crfeb.tbmpt.zsjk.jt.model.ZsJkJtAqZlXx;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtAqZlMxXxDto;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtAqZlXxDto;
import com.crfeb.tbmpt.zsjk.jt.service.ZsJkJtAqZlXxService;
/**
 * <p>安全质量信息Service实现类</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：集团角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@Service
public class ZsJkJtAqZlXxServiceImpl extends CommonServiceImpl<ZsJkJtAqZlXxMapper, ZsJkJtAqZlXx> implements ZsJkJtAqZlXxService{

    @Autowired
    private ZsJkJtAqZlXxMapper zsJkJtAqZlXxMapper;
    
    private int su;
	private int ex;
	private int total;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<ZsJkJtAqZlXx> page = new Page<ZsJkJtAqZlXx>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<ZsJkJtAqZlXx> list = zsJkJtAqZlXxMapper.selectZsJkJtAqZlXxList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(ZsJkJtAqZlXxDto zsJkJtAqZlXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkJtAqZlXx zsJkJtAqZlXx = new ZsJkJtAqZlXx();
        BeanUtils.copyProperties(zsJkJtAqZlXxDto, zsJkJtAqZlXx);
    	  zsJkJtAqZlXxMapper.insert(zsJkJtAqZlXx);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  zsJkJtAqZlXxMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

//    @Override
//    public String update(ZsJkJtAqZlXxDto zsJkJtAqZlXxDto,User user) throws Exception {
//    	  String errorMessage = "";
//        ZsJkJtAqZlXx zsJkJtAqZlXx = this.zsJkJtAqZlXxMapper.selectById(zsJkJtAqZlXxDto.getId());
//		  zsJkJtAqZlXxMapper.updateById(zsJkJtAqZlXx);
//    	  return errorMessage;
//    }
	   @Override
	    public String update(ZsJkJtAqZlXxDto zsJkJtAqZlXxDto,User user) throws Exception {
	    	  String errorMessage = "";
	    	  ZsJkJtAqZlXx zsJkJtAqZlXx=new ZsJkJtAqZlXx();
	    	  BeanUtils.copyProperties(zsJkJtAqZlXxDto, zsJkJtAqZlXx);	         
			  int a=zsJkJtAqZlXxMapper.updateById(zsJkJtAqZlXx);
			  if(a==1){
				  return errorMessage;
			  }else{
	    	  return errorMessage+"失败";
			  }
	    }

	   @Override
	   public ZsJkJtAqZlXx findById(String id) throws Exception {
		   return zsJkJtAqZlXxMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(ZsJkJtAqZlXxDto zsJkJtAqZlXxDto, String[] clumNames) throws Exception {
		   String id = zsJkJtAqZlXxDto.getId();
		   Map map = BeanUtils.toMap(zsJkJtAqZlXxDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<ZsJkJtAqZlXx> lists = zsJkJtAqZlXxMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (ZsJkJtAqZlXx zsJkJtAqZlXx2 : lists) {
				   String newId = zsJkJtAqZlXx2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }
    
	@Override
	public List<ZsJkJtAqZlXxDto> getAQZL(String strTime, String endTime) throws Exception {
		Map<String,Object>  columnMap = new HashMap<String,Object>();
		if(StringUtils.isNotBlank(strTime)){
			columnMap.put("strTime", strTime);
		}
		if(StringUtils.isNotBlank(endTime)){
			columnMap.put("endTime", endTime);
		}
		return this.zsJkJtAqZlXxMapper.selectZsJkJtAqZlXxListByMap(columnMap);
	}
	
	/**
	 * 生成excel模板
	 * @author wl_zjh
	 * @return
	 */
	
	@Override
	public HSSFWorkbook generateExcelTemplate() {
		//列标题
		String[] header = { "*日期","*质量问题数","*安全问题数","*隐患数"};
		
		//sheet页名称
		String sheetName = "安全质量信息";
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
						ZsJkJtAqZlXx zsJkJtAqZlXx=new ZsJkJtAqZlXx();
						zsJkJtAqZlXx.setId(CommUtils.getUUID());
						zsJkJtAqZlXx.setRq(rowMap.get("日期"));
						zsJkJtAqZlXx.setAqwts(String.valueOf((int)Double.parseDouble(rowMap.get("安全问题数"))));
						zsJkJtAqZlXx.setZlwts(String.valueOf((int)Double.parseDouble(rowMap.get("质量问题数"))));
						zsJkJtAqZlXx.setYhs(String.valueOf((int)Double.parseDouble(rowMap.get("隐患数"))));
						this.insert(zsJkJtAqZlXx);
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
	
	/**
	 * 查询数据表格
	 * @author wl_zjh
	 * @param pageInfo
	 * @throws Exception
	 */
	@Override
	public void selectAqZlXxDataGrid(PageInfo pageInfo) throws Exception {
		Page<ZsJkJtAqZlXx> page = new Page<ZsJkJtAqZlXx>(pageInfo.getNowpage(), pageInfo.getSize());
	       Map<String, Object> condition = pageInfo.getCondition();
	       List<ZsJkJtAqZlXx> list = zsJkJtAqZlXxMapper.selectZsJkJtAqZlXxDataGrid(page, condition);
	       pageInfo.setRows(list);
	       pageInfo.setTotal(page.getTotal());
	}
}