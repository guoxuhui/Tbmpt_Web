package com.crfeb.tbmpt.sgkshgl.zxxwh.service.impl;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.ExcelUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.project.service.IProRProjectSectionService;
import com.crfeb.tbmpt.project.service.IProRSectionLineService;
import com.crfeb.tbmpt.sgkshgl.zxxwh.mapper.SgkshglZxxwhMapper;
import com.crfeb.tbmpt.sgkshgl.zxxwh.model.SgkshglZxxwh;
import com.crfeb.tbmpt.sgkshgl.zxxwh.model.dto.SgkshglZxxwhDto;
import com.crfeb.tbmpt.sgkshgl.zxxwh.service.ISgkshglZxxwhService;
import com.crfeb.tbmpt.sys.mapper.OrgzMapper;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.User;
import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;

/**
 * <p>CAD中心线维护 ServiceImpl</p>
 * <p>模块：施工可视化管理</p>
 * <p>日期：2017-04-12</p>
 * @version 1.0
 * @author wl_wpg
 */
@Service
public class SgkshglZxxwhServiceImpl extends CommonServiceImpl<SgkshglZxxwhMapper, SgkshglZxxwh> implements ISgkshglZxxwhService {

	@Autowired
    private SgkshglZxxwhMapper sgkshglZxxwhMapper ;
	//项目
	@Autowired
	IProProjectinfoService proProjectinfoService;
    //区间
    @Autowired
    private IProRProjectSectionService proSectionService;
    //线路
    @Autowired
    private IProRSectionLineService proRSectionLineService;
     
    //机构
    @Autowired
    private OrgzMapper orgzMapper;
    /***
     * 根据用户查询列表
     */
    @Override
    public void selectDataGrid(PageInfo pageInfo,User user) {
    	Orgz orgz = orgzMapper.selectById(user.getOrgzId());
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
    	Page<SgkshglZxxwhDto> page = new Page<SgkshglZxxwhDto>(pageInfo.getNowpage(), pageInfo.getSize());
        List<SgkshglZxxwhDto> list = sgkshglZxxwhMapper.selectList(page, pageInfo.getCondition(), pageInfo.getSort(), pageInfo.getOrder());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }
   
    @Override
	public String addSave(SgkshglZxxwhDto dto) {
    	int i=0;
    	SgkshglZxxwh zxxwh =null;
    	if( StringUtils.isNotBlank(dto.getLineId()) 
    	 && StringUtils.isNotBlank(dto.getSectionId())
    	 && StringUtils.isNotBlank(dto.getProId())){
    		ProRSectionLine line = proRSectionLineService.selectById(dto.getLineId());        
    		if(line !=null && dto.getSectionId().equals(line.getSectionId())){
    			ProRProjectSection section = proSectionService.selectById(dto.getSectionId());
        		if(section !=null && dto.getProId().equals(section.getProId())){
        			ProProjectinfo projectinfo = proProjectinfoService.selectById(dto.getProId());
        			if(projectinfo !=null){
        				//计算Id
        				SgkshglZxxwhDto maxId = sgkshglZxxwhMapper.selectMaxId(); //查询 最大Id
        				Double id = maxId.getObjectid();
        				id=id+1;
        				zxxwh = new SgkshglZxxwh();
        				BeanUtils.copyProperties(dto,zxxwh); //反射 对象
        				zxxwh.setObjectid(id);
        				zxxwh.setProName(projectinfo.getProName());
        				zxxwh.setSectionName(section.getSectionName());
        				zxxwh.setLineName(line.getLineName());
        				i = sgkshglZxxwhMapper.insert(zxxwh);
        				
        			}
        		}
    			
    		}
    		
    	}
    	
    	if(i>0){
    		return "";
    	}
		return "添加失败！";
	}
    /***
     * 编辑
     */
	public String edit(SgkshglZxxwh dto) {
		
		int i=0;
    	SgkshglZxxwh zxxwh =null;
    	if( StringUtils.isNotBlank(dto.getLineId()) 
    	 && StringUtils.isNotBlank(dto.getSectionId())
    	 && StringUtils.isNotBlank(dto.getProId())){
    		String id=String.valueOf(dto.getObjectid());
    		SgkshglZxxwh zxxwh2  = sgkshglZxxwhMapper.selectById(id);
    		ProRSectionLine line = proRSectionLineService.selectById(dto.getLineId()); 
    		if(line.getId().equals(zxxwh2.getLineId())){
    			zxxwh = new SgkshglZxxwh();
    			BeanUtils.copyProperties(dto,zxxwh); //反射 对象
    			i = sgkshglZxxwhMapper.updateById(zxxwh);
    		}else{
    			if(line !=null && dto.getSectionId().equals(line.getSectionId())){
        			ProRProjectSection section = proSectionService.selectById(dto.getSectionId());
            		if(section !=null && dto.getProId().equals(section.getProId())){
            			ProProjectinfo projectinfo = proProjectinfoService.selectById(dto.getProId());
            			if(projectinfo !=null){
            				zxxwh = new SgkshglZxxwh();
            				BeanUtils.copyProperties(dto,zxxwh); //反射 对象
            				zxxwh.setProName(projectinfo.getProName());
            				zxxwh.setSectionName(section.getSectionName());
            				zxxwh.setLineName(line.getLineName());
            				SgkshglZxxwh whereZxxwh = new SgkshglZxxwh();
            				whereZxxwh.setObjectid(zxxwh.getObjectid());
            				i = sgkshglZxxwhMapper.updateById(zxxwh);
            			}
            		}
        		}
        	}
    	}
    		
    	
    	if(i>0){
    		return "";
    	}
		return "编辑失败！";
		
	}
	/**     
     * 生成 Excel导入 模版
     * @param zxxwh 要修改的实体
     */
	@Override
	public HSSFWorkbook generateExcelTemplate() {
		//列标题
		String[] header = { "*环号","*类型(PM/DM)","里程","X","Y","Z"};
		
		//sheet页名称
		String sheetName = "CAD中心线维护";
		//生成excel模版
		HSSFWorkbook wb = ExcelUtils.excelSheet(header, sheetName);
		HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row = sheet.getRow(0);
        
        ExcelUtils.resizeWidth(wb,row,sheet);
		return wb;
	}
	
	 /***
     * 数字判断
     * @param str
     * @return
     */
    public boolean isNumeric(String str){ 
	   Pattern pattern = Pattern.compile("[0-9]*"); 
	   Matcher isNum = pattern.matcher(str);
	   if( !isNum.matches() ){
	       return false; 
	   } 
	   return true; 
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
	public String transformationBingPreservation(List<List<Map<String, String>>> list,SgkshglZxxwhDto dto) {
		String ring = "";
		su=0;
		total=0;
		try {
				
			if(null!=list && !list.isEmpty() && dto !=null 
					 && StringUtils.isNotBlank(dto.getLineId()) 
			    	 && StringUtils.isNotBlank(dto.getSectionId())
			    	 && StringUtils.isNotBlank(dto.getProId()) ){

	    		ProRSectionLine line = proRSectionLineService.selectById(dto.getLineId());        
	    		if(line !=null && dto.getSectionId().equals(line.getSectionId())){
	    			ProRProjectSection section = proSectionService.selectById(dto.getSectionId());
	        		if(section !=null && dto.getProId().equals(section.getProId())){
	        			ProProjectinfo projectinfo = proProjectinfoService.selectById(dto.getProId());
	        			if(projectinfo !=null){
	        				dto.setProName(projectinfo.getProName());
	        				dto.setSectionName(section.getSectionName());
	        				dto.setLineName(line.getLineName());
	        				
	        				for(List<Map<String, String>> ListP : list){
	        					//计算 总条数
	        					total +=  ListP.size();
	        					for(Map<String, String> rowMap : ListP){
	        						//对象
	        						SgkshglZxxwh zxxwh = new SgkshglZxxwh();
	        						zxxwh.setProId(dto.getProId());
	        						zxxwh.setSectionId(dto.getSectionId());
	        						zxxwh.setLineId(dto.getLineId());
	        						if(StringUtils.isNotBlank(rowMap.get("环号")) && this.isNumeric(rowMap.get("环号"))){
	        							zxxwh.setHh(Integer.parseInt(rowMap.get("环号")));
		        						if(StringUtils.isNotBlank(rowMap.get("类型(PM/DM)")) && ( rowMap.get("类型(PM/DM)").equals("PM")||rowMap.get("类型(PM/DM)").equals("DM"))){
		        							zxxwh.setType(rowMap.get("类型(PM/DM)"));
		        							int i=0;
			        				    	if( StringUtils.isNotBlank(zxxwh.getLineId()) 
			        				    	 && StringUtils.isNotBlank(zxxwh.getSectionId())
			        				    	 && StringUtils.isNotBlank(zxxwh.getProId())
			        				    	 && StringUtils.isNotBlank(zxxwh.getType())
			        				    	 && zxxwh.getHh()!=null ){
			        	        				//计算Id
			        	        				SgkshglZxxwhDto maxId = sgkshglZxxwhMapper.selectMaxId(); //查询 最大Id
			        	        				Double id = maxId.getObjectid();
			        	        				id=id+1;                       //在最后一条信息id上加 ‘1’作为当前信息Id
			        	        				zxxwh.setObjectid(id);
			        	        				zxxwh.setProName(dto.getProName());
			        	        				zxxwh.setSectionName(dto.getSectionName());
			        	        				zxxwh.setLineName(dto.getLineName());
			        	        				if( StringUtils.isNotBlank(rowMap.get("里程")) && this.isNumeric(rowMap.get("里程"))){
			        	        					zxxwh.setLc(Integer.parseInt(rowMap.get("里程")));
				        						}
			        							zxxwh.setX(rowMap.get("X"));
			        							zxxwh.setY(rowMap.get("Y"));
			        							zxxwh.setZ(rowMap.get("Z"));
			        	        				i = sgkshglZxxwhMapper.insert(zxxwh);
			        	        			}
			        				      
			        				    	if(i>0){
			        				    		su++;
			        				    	}
		        						}
	        						}
	        					}
	        				}
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
			return SuccessRing;
		}else{
			return null;
		}
	}
    
    
}