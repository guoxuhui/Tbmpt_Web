package com.crfeb.tbmpt.dmcjjc.dd.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.utils.ExcelUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.dmcjjc.dd.mapper.DmcjDDInfoMapper;
import com.crfeb.tbmpt.dmcjjc.dd.model.DdInfo;
import com.crfeb.tbmpt.dmcjjc.dd.service.IDmcjDDInfoService;

@Service
public class DmcjDDInfoServiceImpl 
	extends SuperServiceImpl<DmcjDDInfoMapper, DdInfo> implements IDmcjDDInfoService {

	@Autowired
	private DmcjDDInfoMapper dDInfoMapper;
	
    public void selectDataGrid(PageInfo pageInfo) {
        Page<DdInfo> page = new Page<DdInfo>(pageInfo.getNowpage(), pageInfo.getSize());
        List<DdInfo> list = dDInfoMapper.selectDdInfoPage(page, pageInfo.getCondition());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }

	public void del(Long ids) {
		dDInfoMapper.deleteById(ids);
	}

	public DdInfo findById(Long id) {
		return dDInfoMapper.selectById(id);
	}
	
	public void qy(String id) {
		dDInfoMapper.qy(id);
	}

	
	public void jy(String id) {
		dDInfoMapper.jy(id);
	}
	
	public void getDmcjDDType() {
		
	}

	//根据id批量查询出基础数据，再生成excel返回给controller
	public HSSFWorkbook expDDInfo(List<String> ids) {
		List<DdInfo> list = dDInfoMapper.selectDdInfos(ids);
		//列标题
		String[] header = { "序号","分类名称", "数据名称", "使用状态","备注","排序号"};
		//sheet页名称
		String sheetName = "基础数据";
		HSSFWorkbook wb = ExcelUtils.excelSheet(header, sheetName);
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow row = sheet.getRow(0);
        //生成单元格内容
        for (int i = 0; i < list.size(); i++) {    
          row = sheet.createRow(i + 1);    
          DdInfo info = list.get(i);    
          row.createCell(0).setCellValue(i+1);//行号
          String typeName = info.getTypeName();
          if(null != typeName)
        	  row.createCell(1).setCellValue(typeName);    
          String ddName = info.getDdName();
          if(null != ddName)
        	  row.createCell(2).setCellValue(ddName);
          String status = info.getIfqy();
          if(null != status)
        	  row.createCell(3).setCellValue(status);
          String remarks = info.getRemarks();
          if(null != remarks)
          	row.createCell(4).setCellValue(remarks);
          Integer sort = info.getSortNum();
          if(null != sort)
        	  row.createCell(5).setCellValue(sort);
      }    
        ExcelUtils.resizeWidth(wb,row,sheet);
		return wb;
	}

	
	public List<DdInfo> getDmcjDDInfo(String typeName) {
		Map<String, Object> columnMap = new HashMap<String,Object>();
		columnMap.put("typename",typeName);
		columnMap.put("ifqy","启用");//只取启用的基础数据
		return dDInfoMapper.selectByMap(columnMap);
	}

}
