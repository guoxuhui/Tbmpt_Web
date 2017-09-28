package com.crfeb.tbmpt.sgkshgl.jcdzbgl.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crfeb.tbmpt.commons.utils.CommUtils;
import com.crfeb.tbmpt.commons.utils.ExcelUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.dmcjjc.info.model.JcPoint;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcPointDto;
import com.crfeb.tbmpt.project.mapper.ProProjectinfoMapper;
import com.crfeb.tbmpt.project.mapper.ProRProjectSectionMapper;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.project.model.vo.ProjectSectionVo;
import com.crfeb.tbmpt.sgkshgl.jcdzbgl.mapper.SgkshglJcdzbglMapper;
import com.crfeb.tbmpt.sgkshgl.jcdzbgl.model.SgkshglJcdzbgl;
import com.crfeb.tbmpt.sgkshgl.jcdzbgl.model.dto.SgkshglJcdzbglDto;
import com.crfeb.tbmpt.sgkshgl.jcdzbgl.service.ISgkshglJcdzbglService;
import com.crfeb.tbmpt.zsjk.jt.model.ZsJkJtAqZlMxXx;
import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;

/**
 *
 * JcpointNew 表数据服务层接口实现类
 *
 */
@Service
public class SgkshglJcdzbglServiceImpl extends CommonServiceImpl<SgkshglJcdzbglMapper, SgkshglJcdzbgl> implements ISgkshglJcdzbglService {
	@Autowired
	private SgkshglJcdzbglMapper sgkshglJcdzbglMapper;
	@Autowired
	private ProProjectinfoMapper proProjectinfoMapper;
	@Autowired
	private ProRProjectSectionMapper proRProjectSectionMapper;
	
	private int su;
	private int ex;
	private int total;
	
	@Override
	public void selectDataGrid(PageInfo pageInfo) {
		Page<JcPoint> page = new Page<JcPoint>(pageInfo.getNowpage(), pageInfo.getSize());
        List<SgkshglJcdzbglDto> list = sgkshglJcdzbglMapper.selectJcPointPage(page, pageInfo.getCondition());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
		
	}

	@Override
	public void save(SgkshglJcdzbgl sgkshglJcdzbgl) {
		// TODO Auto-generated method stub
		List<SgkshglJcdzbglDto> list = sgkshglJcdzbglMapper.selectAll();
		Double objectId=list.get(0).getObjectid();
		double i=objectId.doubleValue();
		i++;	
		sgkshglJcdzbgl.setObjectid(new Double(i));
		sgkshglJcdzbglMapper.insertJcdzb(sgkshglJcdzbgl);
	}

	@Override
	public SgkshglJcdzbglDto selectOne(String objectid) {
		SgkshglJcdzbglDto sgkshglJcdzbglDto=sgkshglJcdzbglMapper.selectByObjectid(objectid);
		return sgkshglJcdzbglDto;
	}

	@Override
	public HSSFWorkbook generateExcelTemplate() {
		//列标题
		String[] header = { "*项目","*区间","*点位编号","*点位类型","*里程",
				"*环号","*报警下限","*报警上限","X坐标","Y坐标","Z坐标","备注"};
		
		//sheet页名称
		String sheetName = "监测点坐标管理";
		//生成excel模版
		HSSFWorkbook wb = ExcelUtils.excelSheet(header, sheetName);
		HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row = sheet.getRow(0);
        
        ExcelUtils.resizeWidth(wb,row,sheet);
		return wb;
	}

//	@Override
//	public String transformationBingPreservation(List<List<Map<String, String>>> list) {
//		String ring = "";
//		su=0;
//		ex=0;
//		total=0;
//		List<SgkshglJcdzbglDto> sgkshglJcdzbgllist = sgkshglJcdzbglMapper.selectAll();
//		Double objectId=sgkshglJcdzbgllist.get(0).getObjectid();
//		double i=objectId.doubleValue();
//		try {
//				
//			if(null!=list && !list.isEmpty()){
//				
//				for(List<Map<String, String>> ListP : list){
//					//计算 总条数
//					total +=  ListP.size();
//					for(Map<String, String> rowMap : ListP){
//						//监测点  对象
//						SgkshglJcdzbgl sgkshglJcdzbgl=new SgkshglJcdzbgl();
//						if(StringUtils.isBlank(rowMap.get("点位编号").trim())){
//							continue ; //“点位编号”为空；跳过当次循环；
//						}						
//						if(StringUtils.isNotBlank(rowMap.get("项目")) && StringUtils.isNotBlank(rowMap.get("区间"))){
//							
//							try {
//								//通过 区间 名称   查询   区间 id
////								String sectionId = proRProjectSectionService.getSectionBySectionName(rowMap.get("区间").trim());
//								String proId=proProjectinfoMapper.selectByProName(rowMap.get("项目").trim());
//								
//								if(StringUtils.isNotBlank(proId)){
//									//通过 区间 id、路线名称 查询   路线 id
//									String sectionId = proRProjectSectionMapper.getSectionBySectionName(rowMap.get("区间").trim());
//								    
//								    if(StringUtils.isNotBlank(sectionId)){
//								    	//生成 UUID
//								    	sgkshglJcdzbgl.setObjectid(new Double(i++));
//										//工程编号 
//								    	sgkshglJcdzbgl.setProId(proId);
//								    	sgkshglJcdzbgl.setJcId(rowMap.get("点位编号".trim()));
//										//区间 id
//								    	sgkshglJcdzbgl.setSecId(sectionId);
////	String[] header = { "项目","区间","点位编号","点位类型","里程","环号","报警下限","报警上限","X坐标","Y坐标","Z坐标","备注"};
//								    	sgkshglJcdzbgl.setJcType(rowMap.get("点位类型").trim());
//								    	sgkshglJcdzbgl.setLc(rowMap.get("里程").trim());
//								    	sgkshglJcdzbgl.setHh(rowMap.get("环号").trim());
//								    	sgkshglJcdzbgl.setXx(rowMap.get("报警下限").trim());
//								    	sgkshglJcdzbgl.setSx(rowMap.get("报警上限").trim());
//								    	sgkshglJcdzbgl.setX(rowMap.get("X坐标").trim());
//								    	sgkshglJcdzbgl.setY(rowMap.get("Y坐标").trim());
//								    	sgkshglJcdzbgl.setZ(rowMap.get("Z坐标").trim());
//								    	sgkshglJcdzbgl.setBz(rowMap.get("备注").trim());
//								    	this.insert(sgkshglJcdzbgl);
//										su++;
//								    	
//								    }else{
//								    	// 路线 id为空：拼接保存失败 提示，把所有 保存失败的 监测点编号 拼接
//										ring = MosaicRing(ring,rowMap.get("点位编号"));
//								    }
//									
//								}else{
//									//区间 id为空：拼接保存失败 提示，把所有 保存失败的 监测点编号 拼接
//									ring = MosaicRing(ring,rowMap.get("点位编号"));
//								}
//								
//							} catch (Exception e) {
//							    e.printStackTrace();
//							    //查询区间 id，或查询路线 id 报错：拼接保存失败 提示，把所有 保存失败的 监测点编号 拼接
//								ring = MosaicRing(ring,rowMap.get("点位编号"));
//							}
//							
//				    	}else{
//				    		//区间名 或 路线名 为空：拼接保存失败 提示，把所有 保存失败的 监测点编号 拼接
//							ring = MosaicRing(ring,rowMap.get("点位编号"));
//				    	}											
//						
//					}
//					
//				}
//			    
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
////		返回  拼接 提示 文 
//		return calculationPrompt(ring);
//	}
	
	private String MosaicRing(String ring,String jcdbh){
		ex++;  //计算 点号 已存在 条数
		if(StringUtils.isNotBlank(ring)){
			ring =ring+"、"+"“"+jcdbh+"”";
		}else{
			ring = "“"+jcdbh+"”";
		}
		return ring;
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
	public List<SgkshglJcdzbgl> readExcel(List<List<Map<String, String>>> list) {
		List<SgkshglJcdzbglDto> sgkshglJcdzbgllist = sgkshglJcdzbglMapper.selectAll();
		List<SgkshglJcdzbgl> importList=new ArrayList<SgkshglJcdzbgl>();
		Double objectId=sgkshglJcdzbgllist.get(0).getObjectid();
		double i=objectId.doubleValue();
		try {
				
			if(null!=list && !list.isEmpty()){
				
				for(List<Map<String, String>> ListP : list){
					//计算 总条数
					total +=  ListP.size();
					for(Map<String, String> rowMap : ListP){
						//监测点  对象
						SgkshglJcdzbgl sgkshglJcdzbgl=new SgkshglJcdzbgl();
						if(StringUtils.isBlank(rowMap.get("项目").trim())){
							continue ; //“点位编号”为空；跳过当次循环；
						}
						if(StringUtils.isNotBlank(rowMap.get("项目")) && StringUtils.isNotBlank(rowMap.get("区间"))){
							
							try {
								//通过 区间 名称   查询   区间 id
//								String sectionId = proRProjectSectionService.getSectionBySectionName(rowMap.get("区间").trim());
								String proId=proProjectinfoMapper.selectByProName(rowMap.get("项目").trim());
								ProProjectinfo p=proProjectinfoMapper.selectById(proId);
								if(StringUtils.isNotBlank(proId)){
									//通过 区间 id、路线名称 查询   路线 id
									String sectionId = proRProjectSectionMapper.getSectionBySectionName(rowMap.get("区间").trim());
								    
								    if(StringUtils.isNotBlank(sectionId)){
								    	//生成 UUID
								    	sgkshglJcdzbgl.setObjectid(new Double(++i));
										//工程编号 
								    	
								    	sgkshglJcdzbgl.setProId(proId);
								    	sgkshglJcdzbgl.setProName(p.getProName());
								    	if(rowMap.get("点位编号")!=null){
								    		sgkshglJcdzbgl.setJcId(rowMap.get("点位编号").trim());								    		
								    	}
										//区间 id
								    	sgkshglJcdzbgl.setSecId(sectionId);
								    	ProRProjectSection s=proRProjectSectionMapper.selectBySectionId(sectionId);
								    	sgkshglJcdzbgl.setSecName(s.getSectionName());
								    	if(rowMap.get("点位类型")!=null){
								    		sgkshglJcdzbgl.setJcType(rowMap.get("点位类型").trim());								    		
								    	}
								    	if(rowMap.get("里程")!=null){
								    		sgkshglJcdzbgl.setLc(rowMap.get("里程").trim());
								    	}
								    	if(rowMap.get("环号")!=null){
								    		sgkshglJcdzbgl.setHh(rowMap.get("环号").trim());								    		
								    	}
								    	if(rowMap.get("报警下限")!=null){
								    		sgkshglJcdzbgl.setXx(rowMap.get("报警下限").trim());
								    	}
								    	if(rowMap.get("报警上限")!=null){
								    		sgkshglJcdzbgl.setSx(rowMap.get("报警上限").trim());
								    	}
								    	if(rowMap.get("X坐标")!=null){
								    		sgkshglJcdzbgl.setX(rowMap.get("X坐标").trim());
								    	}
								    	if(rowMap.get("Y坐标")!=null){
								    		sgkshglJcdzbgl.setY(rowMap.get("Y坐标").trim());
								    	}
								    	if(rowMap.get("Z坐标")!=null){
								    		sgkshglJcdzbgl.setZ(rowMap.get("Z坐标").trim());
								    	}
								    	if(rowMap.get("备注")!=null){
								    		sgkshglJcdzbgl.setBz(rowMap.get("备注").trim());
								    	}
								    	if(StringUtils.isNotBlank(sgkshglJcdzbgl.getJcId())){
								    		importList.add(sgkshglJcdzbgl);							    		
								    	}
								    	
								    	
								    }else{
								    	return null;
								    }
									
								}else{
									return null;
								}
								
							} catch (Exception e) {
							    e.printStackTrace();
							    return null;
							}
							
				    	}else{
				    		return null;
				    	}											
						
					}
					
				}
			    
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
//		返回  拼接 提示 文 
		return importList;
		
	}

	@Override
	public String transformationBingPreservation(List<List<Map<String, String>>> list) {
		// TODO Auto-generated method stub
		return null;
	}


}