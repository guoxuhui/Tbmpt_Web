package com.crfeb.tbmpt.dgjjdw.bzdw.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.ExcelUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.dgjjdw.bzdw.mapper.DgjjdwPlcBzdwMapper;
import com.crfeb.tbmpt.dgjjdw.bzdw.model.DgjjdwPlcBzdw;
import com.crfeb.tbmpt.dgjjdw.bzdw.model.dto.DgjjdwPlcBzdwDto;
import com.crfeb.tbmpt.dgjjdw.bzdw.service.IDgjjdwPlcBzdwService;
import com.crfeb.tbmpt.dgjjdw.real.model.DgjjdwPlcReal;
import com.crfeb.tbmpt.sys.model.User;
import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>盾构掘进点位管理：标准点位字典信息管理 ServiceImpl</p>
 * <p>模块：盾构掘进点位管理</p>
 * <p>日期：2017-03-17</p>
 * @version 1.0
 * @author wl_wpg
 */
@Service
public class DgjjdwPlcBzdwServiceImpl extends CommonServiceImpl<DgjjdwPlcBzdwMapper, DgjjdwPlcBzdw> implements IDgjjdwPlcBzdwService {


    @Autowired 
    private DgjjdwPlcBzdwMapper dgjjdwPlcBzdwMapper;
    
    /**
     * 查询 标准点位字典信息
     * @param pageInfo 分页公用类
     */
    @Override
	public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<DgjjdwPlcBzdw> page = new Page<DgjjdwPlcBzdw>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<DgjjdwPlcBzdw> list = dgjjdwPlcBzdwMapper.selectDgjjdwPlcBzdwList(page,condition,pageInfo.getSort(), pageInfo.getOrder());
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    /**
     * 新增 标准点位字典信息
     * @param dgjjdwPlcBzdwDto 要保存的实体
     */
	@Override
    public String save(DgjjdwPlcBzdwDto dgjjdwPlcBzdwDto,User user) throws Exception {
    	 String errorMessage = "";
    	 DgjjdwPlcBzdw bzdw = null;
    	 if(dgjjdwPlcBzdwDto ==null){
    		 return  "保存失败！";
    	 }
    	 bzdw = this.dgjjdwPlcBzdwMapper.selectById(dgjjdwPlcBzdwDto.getId());
		 if(bzdw !=null && !bzdw.getId().isEmpty()){
			  errorMessage = "Id为："+dgjjdwPlcBzdwDto.getId()+"的数据已存在，新增失败！";
		 }else{
			 DgjjdwPlcBzdw dgjjdwPlcBzdw = new DgjjdwPlcBzdw();
	          BeanUtils.copyProperties(dgjjdwPlcBzdwDto, dgjjdwPlcBzdw);
	          if(StringUtils.isNotBlank(dgjjdwPlcBzdw.getId())
				   && StringUtils.isNotBlank(dgjjdwPlcBzdw.getName())){
        	       dgjjdwPlcBzdwMapper.insert(dgjjdwPlcBzdw);
						
			  }
		 }
         return  errorMessage;
    }

    /**
     * 集合删除 标准点位字典信息
     * @param ids String字符串，中间用“,”间隔开
     */
	@Override
    public String del(List<String> idList) throws Exception {
    	  String errorMessage = "";
		  if(idList.size()>0){
			  DgjjdwPlcBzdw entity = new DgjjdwPlcBzdw();
			  for(String id :idList){
				  entity.setId(id);
				  dgjjdwPlcBzdwMapper.deleteSelective(entity);
			  }
		  }else{
			  errorMessage="删除失败！";
		  }
    	  return errorMessage;
    }

	/**     
     * 修改 标准点位字典信息
     * @param dgjjdwPlcBzdw 要修改的实体
     */
	@Override
	public String update(DgjjdwPlcBzdw dgjjdwPlcBzdw) throws Exception {
		 String errorMessage = "";
		 DgjjdwPlcBzdw bzdw = null;
		 if(dgjjdwPlcBzdw ==null){
    		 return  "保存失败！";
    	 }
		 bzdw = this.dgjjdwPlcBzdwMapper.selectById(dgjjdwPlcBzdw.getId());
		 if(bzdw!=null && !bzdw.getId().isEmpty()){
			 if(StringUtils.isNotBlank(dgjjdwPlcBzdw.getId())
				   && StringUtils.isNotBlank(dgjjdwPlcBzdw.getName())){
				   dgjjdwPlcBzdwMapper.updateById(dgjjdwPlcBzdw);
						
			  }
		 }else{
			 errorMessage = "数据已过期，编辑失败！";
		 }
  	     return errorMessage;
    }

    /**     
     * 生成 Excel导入 模版
     * @param dgjjdwPlcBzdw 要修改的实体
     */
	@Override
	public HSSFWorkbook generateExcelTemplate() {
		//列标题
		String[] header = { "*点位编号","*点位名称","点位类型","点位单位","所属系统","盾构机类型","描述","备注"};
		//sheet页名称
		String sheetName = "标准点位字典信息";
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
	private int failed;
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
		failed=0;
		total=0;
		try {
				
			if(null!=list && !list.isEmpty()){
				
				for(List<Map<String, String>> ListP : list){
					//计算 总条数
					total +=  ListP.size();
					for(Map<String, String> rowMap : ListP){
						
							
							if(StringUtils.isNotBlank(rowMap.get("点位编号"))){
								DgjjdwPlcBzdw real =null;
								real = dgjjdwPlcBzdwMapper.selectById(rowMap.get("点位编号"));
								if(real !=null){
									failed++;
									continue;
								}
							}else{
								failed++;
								continue;
							}
						//  对象
						DgjjdwPlcBzdw dgjjdwPlcBzdw = new DgjjdwPlcBzdw();
						dgjjdwPlcBzdw.setId(rowMap.get("点位编号"));
						dgjjdwPlcBzdw.setName(rowMap.get("点位名称"));
						dgjjdwPlcBzdw.setDwlx(rowMap.get("点位类型"));
						dgjjdwPlcBzdw.setDwdw(rowMap.get("点位单位"));
						dgjjdwPlcBzdw.setSsxt(rowMap.get("所属系统"));
						dgjjdwPlcBzdw.setDgjlx(rowMap.get("盾构机类型"));
						dgjjdwPlcBzdw.setMs(rowMap.get("描述"));
						dgjjdwPlcBzdw.setBz(rowMap.get("备注"));
						if(StringUtils.isNotBlank(dgjjdwPlcBzdw.getId())
						   && StringUtils.isNotBlank(dgjjdwPlcBzdw.getName())){
							this.insert(dgjjdwPlcBzdw);
							su++;
						}
						
						
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
			if(failed>0){
				SuccessRing = String.valueOf(SuccessRing+"保存失败"+failed+"条");	
			}
			return SuccessRing;
		}else{
			return null;
		}
	}

	
	

}