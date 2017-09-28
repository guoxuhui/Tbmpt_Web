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
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtAqZlXxDto;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtdgSgRyTrXxDto;
import com.crfeb.tbmpt.zsjk.jt.mapper.ZsJkJtdgSgRyTrXxMapper;
import com.crfeb.tbmpt.zsjk.jt.model.ZsJkJtAqZlXx;
import com.crfeb.tbmpt.zsjk.jt.model.ZsJkJtdgSgRyTrXx;
import com.crfeb.tbmpt.zsjk.jt.service.ZsJkJtdgSgRyTrXxService;
/**
 * <p>集团角度项目的盾构施工人员投入信息Service实现类</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：集团角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@Service
public class ZsJkJtdgSgRyTrXxServiceImpl extends CommonServiceImpl<ZsJkJtdgSgRyTrXxMapper, ZsJkJtdgSgRyTrXx> implements ZsJkJtdgSgRyTrXxService{

    @Autowired
    private ZsJkJtdgSgRyTrXxMapper zsJkJtdgSgRyTrXxMapper;
    
    private int su;
	private int ex;
	private int total;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<ZsJkJtdgSgRyTrXx> page = new Page<ZsJkJtdgSgRyTrXx>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<ZsJkJtdgSgRyTrXx> list = zsJkJtdgSgRyTrXxMapper.selectZsJkJtdgSgRyTrXxList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(ZsJkJtdgSgRyTrXxDto zsJkJtdgSgRyTrXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkJtdgSgRyTrXx zsJkJtdgSgRyTrXx = new ZsJkJtdgSgRyTrXx();
        BeanUtils.copyProperties(zsJkJtdgSgRyTrXxDto, zsJkJtdgSgRyTrXx);
    	  zsJkJtdgSgRyTrXxMapper.insert(zsJkJtdgSgRyTrXx);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  zsJkJtdgSgRyTrXxMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

//    @Override
//    public String update(ZsJkJtdgSgRyTrXxDto zsJkJtdgSgRyTrXxDto,User user) throws Exception {
//    	  String errorMessage = "";
//        ZsJkJtdgSgRyTrXx zsJkJtdgSgRyTrXx = this.zsJkJtdgSgRyTrXxMapper.selectById(zsJkJtdgSgRyTrXxDto.getId());
//		  zsJkJtdgSgRyTrXxMapper.updateById(zsJkJtdgSgRyTrXx);
//    	  return errorMessage;
//    }
	   
	   @Override
	    public String update(ZsJkJtdgSgRyTrXxDto zsJkJtdgSgRyTrXxDto,User user) throws Exception {
	    	  String errorMessage = "";
	    	  ZsJkJtdgSgRyTrXx zsJkJtdgSgRyTrXx=new ZsJkJtdgSgRyTrXx();
	    	  BeanUtils.copyProperties(zsJkJtdgSgRyTrXxDto, zsJkJtdgSgRyTrXx);	         
			  int a=zsJkJtdgSgRyTrXxMapper.updateById(zsJkJtdgSgRyTrXx);
			  if(a==1){
				  return errorMessage;
			  }else{
	    	  return errorMessage+"失败";
			  }
	    }

	   @Override
	   public ZsJkJtdgSgRyTrXx findById(String id) throws Exception {
		   return zsJkJtdgSgRyTrXxMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(ZsJkJtdgSgRyTrXxDto zsJkJtdgSgRyTrXxDto, String[] clumNames) throws Exception {
		   String id = zsJkJtdgSgRyTrXxDto.getId();
		   Map map = BeanUtils.toMap(zsJkJtdgSgRyTrXxDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<ZsJkJtdgSgRyTrXx> lists = zsJkJtdgSgRyTrXxMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (ZsJkJtdgSgRyTrXx zsJkJtdgSgRyTrXx2 : lists) {
				   String newId = zsJkJtdgSgRyTrXx2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

	@Override
	public ZsJkJtdgSgRyTrXxDto getAllxmrytr() throws Exception {
		List<ZsJkJtdgSgRyTrXx> entityList = this.zsJkJtdgSgRyTrXxMapper.selectList(null);
		ZsJkJtdgSgRyTrXxDto dto = new ZsJkJtdgSgRyTrXxDto();
		if(null != entityList && entityList.size()>0){
			BeanUtils.copy(entityList.get(0), dto);
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
	public void selectdgSgRyTrXxDataGrid(PageInfo pageInfo) throws Exception {
		// TODO Auto-generated method stub
		Page<ZsJkJtdgSgRyTrXx> page = new Page<ZsJkJtdgSgRyTrXx>(pageInfo.getNowpage(), pageInfo.getSize());
	       Map<String, Object> condition = pageInfo.getCondition();
	       List<ZsJkJtdgSgRyTrXx> list = zsJkJtdgSgRyTrXxMapper.selectZsJkJtdgSgRyTrXxDataGrid(page, condition);
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
		String[] header = { "*当前项目总数","*当前盾构队总数","*当前盾构队人员总数","*平均每个盾构队人数"};
		
		//sheet页名称
		String sheetName = "盾构施工人员投入信息";
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
						ZsJkJtdgSgRyTrXx zsJkJtdgSgRyTrXx=new ZsJkJtdgSgRyTrXx();
						zsJkJtdgSgRyTrXx.setId(CommUtils.getUUID());
						zsJkJtdgSgRyTrXx.setDgdPjrs(String.valueOf((int)Double.parseDouble(rowMap.get("平均每个盾构队人数"))));
						zsJkJtdgSgRyTrXx.setDgdZrs(String.valueOf((int)Double.parseDouble(rowMap.get("当前盾构队人员总数"))));
						zsJkJtdgSgRyTrXx.setDgdZs(String.valueOf((int)Double.parseDouble(rowMap.get("当前盾构队总数"))));
						zsJkJtdgSgRyTrXx.setXmZrs(String.valueOf((int)Double.parseDouble(rowMap.get("当前项目总数"))));
						this.insert(zsJkJtdgSgRyTrXx);
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