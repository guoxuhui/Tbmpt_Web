package com.crfeb.tbmpt.dgjjdw.real.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crfeb.tbmpt.commons.utils.ExcelUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.dgjjdw.real.mapper.DgjjdwPlcRealMapper;
import com.crfeb.tbmpt.dgjjdw.real.model.DgjjdwPlcReal;
import com.crfeb.tbmpt.dgjjdw.real.service.IDgjjdwPlcRealService;
import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>盾构掘进点位管理：盾构机机器数据实时信息管理 ServiceImpl</p>
 * <p>模块：盾构掘进点位管理</p>
 * <p>日期：2017-03-17</p>
 * @version 1.0
 * @author wl_wpg
 */
@Service
public class DgjjdwPlcRealServiceImpl extends CommonServiceImpl<DgjjdwPlcRealMapper, DgjjdwPlcReal> implements IDgjjdwPlcRealService {



    @Autowired 
    private DgjjdwPlcRealMapper dgjjdwPlcRealMapper;
    
    /**
     * 查询 盾构机机器数据实时信息
     * @param pageInfo 分页公用类
     */
    @Override
	public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<DgjjdwPlcReal> page = new Page<DgjjdwPlcReal>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<DgjjdwPlcReal> list = dgjjdwPlcRealMapper.selectDgjjdwPlcRealList(page,condition,pageInfo.getSort(), pageInfo.getOrder());
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

   

    /**     
     * 生成 Excel导入 模版
     * @param dgjjdwPlcReal 要修改的实体
     */
	@Override
	public HSSFWorkbook generateExcelTemplate() {
		//列标题
		String[] header = { "*点位编号","*盾构机ID","盾构机CODE","工业库点位名称","工业库点位数据类型","工业库点位值","时间","描述","备注"};
		
		//sheet页名称
		String sheetName = "盾构机机器数据实时信息";
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
						DgjjdwPlcReal real =null;
						if(StringUtils.isNotBlank(rowMap.get("点位编号"))){
							real = dgjjdwPlcRealMapper.selectById(rowMap.get("点位编号"));
							if(real !=null){
								failed++;
								continue;
							}
						}else{
							failed++;
							continue;
						}
						
						//  对象
						DgjjdwPlcReal dgjjdwPlcReal = new DgjjdwPlcReal();
						dgjjdwPlcReal.setId(rowMap.get("点位编号"));
						dgjjdwPlcReal.setTbmid(rowMap.get("盾构机ID"));
						dgjjdwPlcReal.setTbmcode(rowMap.get("盾构机CODE"));
						dgjjdwPlcReal.setTagname(rowMap.get("工业库点位名称"));
						dgjjdwPlcReal.setTagtype(rowMap.get("工业库点位数据类型"));
						dgjjdwPlcReal.setTagvalue(rowMap.get("工业库点位值"));
						dgjjdwPlcReal.setTagtime(rowMap.get("时间"));
						dgjjdwPlcReal.setMs(rowMap.get("描述"));
						dgjjdwPlcReal.setBz(rowMap.get("备注"));
						if(StringUtils.isNotBlank(dgjjdwPlcReal.getId())
						   && StringUtils.isNotBlank(dgjjdwPlcReal.getTbmid())){
					        this.insert(dgjjdwPlcReal);
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