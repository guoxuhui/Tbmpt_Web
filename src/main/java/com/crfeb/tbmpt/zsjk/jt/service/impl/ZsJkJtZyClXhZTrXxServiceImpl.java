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
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtZyClXhZTrXxDto;
import com.crfeb.tbmpt.zsjk.jt.mapper.ZsJkJtZyClXhZTrXxMapper;
import com.crfeb.tbmpt.zsjk.jt.model.ZsJkJtZyClXhZTrXx;
import com.crfeb.tbmpt.zsjk.jt.service.ZsJkJtZyClXhZTrXxService;
/**
 * <p>集团角度主要材料消耗总投入信息Service实现类</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：集团角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@Service
public class ZsJkJtZyClXhZTrXxServiceImpl extends CommonServiceImpl<ZsJkJtZyClXhZTrXxMapper, ZsJkJtZyClXhZTrXx> implements ZsJkJtZyClXhZTrXxService{

    @Autowired
    private ZsJkJtZyClXhZTrXxMapper zsJkJtZyClXhZTrXxMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<ZsJkJtZyClXhZTrXx> page = new Page<ZsJkJtZyClXhZTrXx>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<ZsJkJtZyClXhZTrXx> list = zsJkJtZyClXhZTrXxMapper.selectZsJkJtZyClXhZTrXxList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(ZsJkJtZyClXhZTrXxDto zsJkJtZyClXhZTrXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkJtZyClXhZTrXx zsJkJtZyClXhZTrXx = new ZsJkJtZyClXhZTrXx();
        BeanUtils.copyProperties(zsJkJtZyClXhZTrXxDto, zsJkJtZyClXhZTrXx);
    	  zsJkJtZyClXhZTrXxMapper.insert(zsJkJtZyClXhZTrXx);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  zsJkJtZyClXhZTrXxMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(ZsJkJtZyClXhZTrXxDto zsJkJtZyClXhZTrXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkJtZyClXhZTrXx zsJkJtZyClXhZTrXx = this.zsJkJtZyClXhZTrXxMapper.selectById(zsJkJtZyClXhZTrXxDto.getId());
		  zsJkJtZyClXhZTrXxMapper.updateById(zsJkJtZyClXhZTrXx);
    	  return errorMessage;
    }

	   @Override
	   public ZsJkJtZyClXhZTrXx findById(String id) throws Exception {
		   return zsJkJtZyClXhZTrXxMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(ZsJkJtZyClXhZTrXxDto zsJkJtZyClXhZTrXxDto, String[] clumNames) throws Exception {
		   String id = zsJkJtZyClXhZTrXxDto.getId();
		   Map map = BeanUtils.toMap(zsJkJtZyClXhZTrXxDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<ZsJkJtZyClXhZTrXx> lists = zsJkJtZyClXhZTrXxMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (ZsJkJtZyClXhZTrXx zsJkJtZyClXhZTrXx2 : lists) {
				   String newId = zsJkJtZyClXhZTrXx2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

	@Override
	public ZsJkJtZyClXhZTrXxDto getclxhztr(String nd) throws Exception {
		if(StringUtils.isBlank(nd)){
			nd = DateUtils.getCurrentYear();
		}
		ZsJkJtZyClXhZTrXx param = new ZsJkJtZyClXhZTrXx();
		ZsJkJtZyClXhZTrXx entity = new ZsJkJtZyClXhZTrXx();
		ZsJkJtZyClXhZTrXxDto dto = new ZsJkJtZyClXhZTrXxDto();
		param.setNd(nd);
		entity = this.zsJkJtZyClXhZTrXxMapper.selectOne(param);
		if(null != entity){
			BeanUtils.copy(entity, dto);
		}
		return dto;
	}

	@Override
	public String updateZyClXhZTrXx(ZsJkJtZyClXhZTrXx zsJkJtZyClXhZTrXx) throws Exception {
		 String errorMessage = "";
		 ZsJkJtZyClXhZTrXx ZyClXhZTrXx = this.zsJkJtZyClXhZTrXxMapper.selectById(zsJkJtZyClXhZTrXx.getId());
		  if(ZyClXhZTrXx!=null && !ZyClXhZTrXx.getId().isEmpty()){
			  zsJkJtZyClXhZTrXxMapper.updateById(zsJkJtZyClXhZTrXx);
		  }else{
			  errorMessage = "数据已过期，编辑失败！";
		  }
	      
   	  return errorMessage;
		
	}

	/**     
     * 生成 Excel导入 模版
     * @param zsJkJtZyClXhZTrXx 要修改的实体
     */
	@Override
	public HSSFWorkbook generateExcelTemplate() {
		//列标题
		String[] header = { "*年度","材料消耗计划投入值","材料消耗实际投入值"};
		//sheet页名称
		String sheetName = "材料消耗总投入信息";
		//生成excel模版
		HSSFWorkbook wb = ExcelUtils.excelSheet(header, sheetName);
		HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row = sheet.getRow(0);
        
        ExcelUtils.resizeWidth(wb,row,sheet);
		return wb;
	}
	
	/***
	 * 全局变量
	 * 记录操作结果
	 */
	private int su;
	private int total;
	/**
     * 把 Excel表格数据集合 转成  对象  并  保存
     * @param list
     * @return 操作结果提示
     */
	@Override
	public String transformationBingPreservation(List<List<Map<String, String>>> list) {
		String ring = "";
		su=0;
		total=0;
		try {
				
			if(null!=list && !list.isEmpty()){
				
				for(List<Map<String, String>> ListP : list){
					//计算 总条数
					total +=  ListP.size();
					for(Map<String, String> rowMap : ListP){
						//  对象
						ZsJkJtZyClXhZTrXx zsJkJtZyClXhZTrXx = new ZsJkJtZyClXhZTrXx();
						zsJkJtZyClXhZTrXx.setId(CommUtils.getUUID());
						zsJkJtZyClXhZTrXx.setNd(rowMap.get("年度"));
						zsJkJtZyClXhZTrXx.setJhTrz(rowMap.get("材料消耗计划投入值"));
						zsJkJtZyClXhZTrXx.setSjTrz(rowMap.get("材料消耗实际投入值"));
						this.insert(zsJkJtZyClXhZTrXx);
						su++;
						
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        // 返回  拼接 提示 文 
		return calculationPrompt(ring);
	}
	
    /***
     * 计算操作结果提示文
     * @param ring
     * @return
     */
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