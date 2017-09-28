package com.crfeb.tbmpt.project.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.CommUtils;
import com.crfeb.tbmpt.commons.utils.ExcelUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.project.mapper.ProPqInfoMapper;
import com.crfeb.tbmpt.project.model.ProPqInfo;
import com.crfeb.tbmpt.project.model.vo.ProPqInfoVo;
import com.crfeb.tbmpt.project.model.vo.ProjectinfoVo;
import com.crfeb.tbmpt.project.service.IProPqInfoService;
import com.crfeb.tbmpt.sys.mapper.OrgzMapper;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.zsjk.jt.model.ZsJkJtAqZlXx;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtAqZlXxDto;
import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;

/**
 *
 * ProPqInfo 表数据服务层接口实现类
 *
 */
@Service
public class ProPqInfoServiceImpl extends CommonServiceImpl<ProPqInfoMapper, ProPqInfo> implements IProPqInfoService {
	@Autowired
    private OrgzMapper orgzMapper;
	@Autowired
	private ProPqInfoMapper proPqInfoMapper;
	
	private int su;
	private int ex;
	private int total;
	
	@Override
	public void selectDataGrid(PageInfo pageInfo, User currentUser) {
		Orgz orgz = orgzMapper.selectById(currentUser.getOrgzId());
    	String code = "-1";
    	if(orgz != null){
    		if(orgz.getType() <= 1){
    			code = orgz.getCode();
    		}else{
    			Orgz orgz2 = orgzMapper.selectById(orgz.getPid());
    			code = orgz2.getCode();
    		}
    	}
    	pageInfo.getCondition().put("code", code);
        Page<ProPqInfoVo> page = new Page<ProPqInfoVo>(pageInfo.getNowpage(), pageInfo.getSize());
        List<ProPqInfoVo> list = proPqInfoMapper.selectVoList(page,pageInfo.getCondition(), pageInfo.getSort(), pageInfo.getOrder());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
		
	}

	@Override
	public String save(ProPqInfoVo proPqInfoVo, User currentUser) {
		String errorMessage = "";
		ProPqInfo proPqInfo = new ProPqInfo();
        BeanUtils.copyProperties(proPqInfoVo, proPqInfo);
        proPqInfoMapper.insert(proPqInfo);
    	return errorMessage;
		
	}

	@Override
	public String update(ProPqInfoVo proPqInfoVo, User currentUser) {
		String errorMessage = "";
		ProPqInfo proPqInfo = new ProPqInfo();
  	  	BeanUtils.copyProperties(proPqInfoVo, proPqInfo);	         
		int a=proPqInfoMapper.updateById(proPqInfo);
		if(a==1){
			  return errorMessage;
		  }else{
			  return errorMessage+"失败";
		  }
	}

	@Override
	public HSSFWorkbook generateExcelTemplate() {
		//列标题
		String[] header = { "*片区名称","*片区负责人","*联系电话","备注"};
		
		//sheet页名称
		String sheetName = "片区信息管理";
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
						ProPqInfo proPqInfo=new ProPqInfo();
						proPqInfo.setId(CommUtils.getUUID());
						proPqInfo.setPqName(rowMap.get("片区名称"));
						proPqInfo.setPqPerson(rowMap.get("片区负责人"));;
						proPqInfo.setPhone(String.valueOf(Long.parseLong(rowMap.get("联系电话"))));
						proPqInfo.setBeizhu(rowMap.get("备注"));
						this.insert(proPqInfo);
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

	@Override
	public List<ProPqInfoVo> selectByName() {
		List<ProPqInfoVo> list=proPqInfoMapper.selectByName();
		return list;
	}
	
	


}