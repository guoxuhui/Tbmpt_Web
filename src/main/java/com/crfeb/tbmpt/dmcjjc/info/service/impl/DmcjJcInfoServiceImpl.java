package com.crfeb.tbmpt.dmcjjc.info.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.calc.CalcBhl;
import com.crfeb.tbmpt.commons.utils.CommUtils;
import com.crfeb.tbmpt.commons.utils.ExcelUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.dmcjjc.info.mapper.DmcjJcInfoDetailsMapper;
import com.crfeb.tbmpt.dmcjjc.info.mapper.DmcjJcInfoMapper;
import com.crfeb.tbmpt.dmcjjc.info.mapper.DmcjJcPointMapper;
import com.crfeb.tbmpt.dmcjjc.info.model.JcDetails;
import com.crfeb.tbmpt.dmcjjc.info.model.JcInfo;
import com.crfeb.tbmpt.dmcjjc.info.model.JcPoint;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDetailsDto;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDto;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcPointDto;
import com.crfeb.tbmpt.dmcjjc.info.service.IDmcjJcInfoService;

@Service
public class DmcjJcInfoServiceImpl
extends SuperServiceImpl<DmcjJcInfoMapper, JcInfo>implements IDmcjJcInfoService {
	@Autowired
	private DmcjJcInfoMapper jcInfoMapper; 
	@Autowired
	private DmcjJcPointMapper jcPointMapper;
	@Autowired
	private DmcjJcInfoDetailsMapper jcInfoDetailsMapper;
	
    public void selectDataGrid(PageInfo pageInfo) {
        Page<DmcjJcInfoDto> page = new Page<DmcjJcInfoDto>(pageInfo.getNowpage(), pageInfo.getSize());
        List<DmcjJcInfoDto> list = jcInfoMapper.selectJcInfoPage(page, pageInfo.getCondition());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }
	
	public void save(DmcjJcInfoDto dto) {
		String id = CommUtils.getUUID(); 
		//主表信息
		JcInfo info = new JcInfo();
		info.setGcbh(dto.getGcbh());
		info.setJcTime(dto.getJcTime());
		info.setTianqi(dto.getTianqi());
		info.setIfqr(dto.getIfqr());
		info.setImpFilePath(dto.getImpFilePath());
		info.setRemarks(dto.getRemarks());
		info.setId(id);
		String json = dto.getDetails();
		//子表信息
		if(!"[]".equals(json)){
			json = json.replace("&quot;", "\"");
			 List<JcDetails> list = new ArrayList<JcDetails>();  
		     list = JSONObject.parseArray(json, JcDetails.class);  
		     for(JcDetails det:list){
		    	 det.setFid(id);//设置外键
		    	 det.setGcbh(dto.getGcbh());//设置工程编号
		    	 det.setId(CommUtils.getUUID());//设置主键
		     }
		     this.insert(info);	//保存主表
		  jcInfoDetailsMapper.batchInsert(list);//保存子表  
		}
		
	}

	public void update(DmcjJcInfoDto dto) {
		JcInfo info = new JcInfo();
		info.setGcbh(dto.getGcbh());
		info.setJcTime(dto.getJcTime());
		info.setTianqi(dto.getTianqi());
		info.setIfqr(dto.getIfqr());
		info.setImpFilePath(dto.getImpFilePath());
		info.setRemarks(dto.getRemarks());
		info.setId(dto.getId());
		//保存主表
		jcInfoMapper.updateById(info);
		//现在只需要保存主表信息，子表信息在编辑的时候已经保存了
	}

	public JcInfo findById(Long id) {
		return jcInfoMapper.selectById(id);
	}

	/**
	 * 监测日报确认
	 * 
	 */
	public void qy(String id) {
		JcInfo jc = new JcInfo();
		jc.setId(id);
		jc = jcInfoMapper.selectOne(jc);
		List<DmcjJcInfoDetailsDto> dtos = jcInfoDetailsMapper.selectDetailsWithPoint(id);
		for(DmcjJcInfoDetailsDto dto : dtos){
			//根据监测点编号和监测时间查询该监测点的上次高程，如果上次高程不存在，则上次高程取初始高程
			Float scgc = jcPointMapper.getJcInfoScgc(dto.getJcdNo(),jc.getJcTime(),jc.getGcbh());
			if(null == scgc || 0f == scgc.floatValue()){
				if(null == dto.getCsgc() || 0f == dto.getCsgc().floatValue()){
					//没有初始高程以及上次高程时
					//1、更新监测点初始高程
					//2、更新本次明细状态--初始高程
					JcPoint jp = new JcPoint();
					jp.setGcbh(dto.getGcbh());
					jp.setJcdbh(dto.getJcdNo());
					jp = jcPointMapper.selectOne(jp);
					jp.setCsgc(dto.getBcgc());
					jcPointMapper.updateSelectiveById(jp);
					
					JcDetails jd = new JcDetails();
					jd.setId(dto.getId());
					jd = jcInfoDetailsMapper.selectOne(jd);
					jd.setRemarks("初始高程");
					jcInfoDetailsMapper.updateSelectiveById(jd);
				}
			}
		}
		jcInfoMapper.qy(id);		
	}

	public void jy(String id) {
		jcInfoMapper.jy(id);
	}
	
	//根据id批量查询出基础数据，再生成excel返回给controller
	public HSSFWorkbook expData(List<String> ids,String pid) {
		List<JcInfo> list = jcInfoMapper.selectJcInfos(ids);
		//列标题
		String[] header = { "序号","工程编号", "本次监测时间", 
				"天气","确认状态","备注"};
		//sheet页名称
		String sheetName = "日常监测数据";
		String subSheetName = "子表数据";
	    String[] subheader = {"序号","监测点编号", "本次高程", 
	    		"上次高程","初始高程","本次变化量","累计变化量","变化速率",
	    		"工程编号","区间","线路","位置","里程","备注"};
	    //子表总记录    
	    List<DmcjJcInfoDetailsDto> detailsTotal = new ArrayList<DmcjJcInfoDetailsDto>();
	    
		HSSFWorkbook wb = ExcelUtils.excelSheet(header, sheetName,subheader,subSheetName);
		HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row = sheet.getRow(0);
        HSSFSheet subsheet = wb.getSheetAt(1);
        HSSFRow subrow = subsheet.getRow(0);
        
        //生成单元格内容
        for (int i = 0; i < list.size(); i++) {    
          row = sheet.createRow(i + 1);    
          JcInfo info = list.get(i);    
          row.createCell(0).setCellValue(i+1);//行号 
          String gcbh = info.getGcbh();
          if(null != gcbh)
        	  row.createCell(1).setCellValue(gcbh);    
          String time = info.getJcTime();
          if(null != time)
        	  row.createCell(2).setCellValue(time);    
          String tianqi = info.getTianqi();
          if(null != tianqi)
        	  row.createCell(3).setCellValue(tianqi);  
          String status = info.getIfqr();
          if(null != status)
        	  row.createCell(4).setCellValue(status);  
          String remarks = info.getRemarks();
          if(null != remarks)
          	row.createCell(5).setCellValue(remarks);

          //查询该记录对应子表记录
          List<DmcjJcInfoDetailsDto> details = jcInfoDetailsMapper.selectDetailsWithPoint(info.getId());
          detailsTotal.addAll(details);
      }    
        ExcelUtils.resizeWidth(wb,row,sheet);
        
      //生成子表内容
		for (int j = 0; j < detailsTotal.size(); j++) {
			subrow = subsheet.createRow(j + 1);
			DmcjJcInfoDetailsDto detail = detailsTotal.get(j);
			JcInfo jcinfo = new JcInfo();
			jcinfo.setId(detail.getFid());
			Float scgc = jcPointMapper.getJcInfoScgc(detail.getJcdNo(),
					jcInfoMapper.selectOne(jcinfo).getJcTime(), pid);
			if (null == scgc) {
				scgc = detail.getCsgc();
			}
			detail.setScgc(scgc);
			
			ExcelUtils.makeDetailSheet(j, subrow, subsheet, detail);
		}
		ExcelUtils.resizeWidth(wb,subrow,subsheet);
		return wb;
	}
	//不存在的监测点
	public List<String> isno( Map<String,Map<String,String>> map,String  jcTime,String pid){
		List<Map<String,String>> conds = new ArrayList<Map<String,String>>();
		List<String> exist = new ArrayList<String>();
		List<String> all = new ArrayList<String>();
		Set<String> keys = map.keySet();
		for(String key : keys){
			conds.add(map.get(key));
		}
		for(String key : keys){
			all.add(key);
		}
		List<DmcjJcPointDto> points = jcPointMapper.selectJcPointsByJcdno(conds);
		for(DmcjJcPointDto point : points){
			exist.add(point.getJcdbh());
		}
		all.removeAll(exist);
		all.remove("");
		return all;
		
	}
	//计算变化量(第一次导入时进入)
	public List<DmcjJcInfoDetailsDto> calcBhl( Map<String,Map<String,String>> map,String  jcTime,String pid){
		List<DmcjJcInfoDetailsDto> details = new ArrayList<DmcjJcInfoDetailsDto>();
		//构造查询条件
		List<Map<String,String>> conds = new ArrayList<Map<String,String>>();
		Set<String> keys = map.keySet();
		for(String key : keys){
			conds.add(map.get(key));
		}
		
		List<DmcjJcPointDto> points = jcPointMapper.selectJcPointsByJcdno(conds);
		for(DmcjJcPointDto point : points){
			DmcjJcInfoDetailsDto detail = new DmcjJcInfoDetailsDto();
			detail.setJcdNo(map.get(point.getJcdbh()).get("jcd"));
			//根据监测点编号和监测时间查询该监测点的上次高程，如果上次高程不存在，则上次高程取初始高程
			
			/**
			 * 1.1、没有上次高程使用初始高程
			 * 1.2、没有初始高程页面置空 0 页面显示置空 同时备注显示 初始高程
			 * 
			 * 2.1、没有上次高程使用初始高程
			 * 
			 * 3.1、有上次高程使用上次高程 （正常情况）
			 */
			Float scgc = jcPointMapper.getJcInfoScgc(point.getJcdbh(), jcTime,pid);
			//1.1、没有上次高程使用初始高程
			if(null == scgc || 0f == scgc.floatValue()){
				scgc = point.getCsgc();
				//1.2、没有初始高程页面置空 0 页面显示置空 同时备注显示 初始高程
				if(null == scgc || 0f == scgc.floatValue()){
					detail.setScgc(0f);
					Float bcgc = Float.parseFloat(map.get(point.getJcdbh()).get("bcgc"));
					detail.setBcgc(bcgc);
					detail.setCsgc(0f);
					detail.setQujian(point.getQjbh());
					detail.setXianlu(point.getXlbh());
					detail.setLicheng(point.getLicheng());
					detail.setWeizhi(point.getWeizhi());
					detail.setQujianName(point.getQujianName());
					detail.setXianluName(point.getXianluName());
					detail.setId(CommUtils.getUUID());
					
					detail.setBcbhl(0f);
					detail.setLjbhl(0f);
					detail.setBhsl(0f);
					
					detail.setRemarks("初始高程");
					details.add(detail);
					
				}else{
					detail.setScgc(scgc);
					Float bcgc = Float.parseFloat(map.get(point.getJcdbh()).get("bcgc"));
					detail.setBcgc(bcgc);
					detail.setCsgc(point.getCsgc());
					detail.setQujian(point.getQjbh());
					detail.setXianlu(point.getXlbh());
					detail.setLicheng(point.getLicheng());
					detail.setWeizhi(point.getWeizhi());
					detail.setQujianName(point.getQujianName());
					detail.setXianluName(point.getXianluName());
					detail.setId(CommUtils.getUUID());
					
					CalcBhl.calc(detail);//计算变化量
					
					details.add(detail);
				}
			}else{
				detail.setScgc(scgc);
				Float bcgc = Float.parseFloat(map.get(point.getJcdbh()).get("bcgc"));
				detail.setBcgc(bcgc);
				detail.setCsgc(point.getCsgc());
				detail.setQujian(point.getQjbh());
				detail.setXianlu(point.getXlbh());
				detail.setLicheng(point.getLicheng());
				detail.setWeizhi(point.getWeizhi());
				detail.setQujianName(point.getQujianName());
				detail.setXianluName(point.getXianluName());
				detail.setId(CommUtils.getUUID());
				
				CalcBhl.calc(detail);//计算变化量
				
				details.add(detail);
			}
				

		}
		return details;
	}
	
	//根据外键查询子表（编辑页面打开时进入）
	public List<DmcjJcInfoDetailsDto> calcBhl(String fid, String jcTime,PageInfo pageInfo,String pid) {
		Page<JcDetails> page = new Page<JcDetails>(pageInfo.getNowpage(),	pageInfo.getSize());
		List<DmcjJcInfoDetailsDto> result = jcInfoDetailsMapper.selectJcInfoDetailPage(page, pageInfo.getCondition());
		// List<DmcjJcInfoDetailsDto> result = jcInfoDetailsMapper.selectDetailsWithPoint(fid);
		for (DmcjJcInfoDetailsDto dto : result) {
			
			/**
			 * 1.1、没有上次高程使用初始高程
			 * 1.2、没有初始高程页面置空 0 页面显示置空 同时备注显示 初始高程
			 * 
			 * 2.1、没有上次高程使用初始高程
			 * 
			 * 3.1、有上次高程使用上次高程 （正常情况）
			 */
			// 根据监测点编号和监测时间取该监测点上次高程，如果没有，则上次高程为初始高程
			Float scgc = jcPointMapper.getJcInfoScgc(dto.getJcdNo(), jcTime,pid);
			//1.1、没有上次高程使用初始高程
			if(null == scgc || 0f == scgc.floatValue()){
				scgc = dto.getCsgc();
				//1.2、没有初始高程页面置空 0 页面显示置空 同时备注显示 初始高程
				if(null == scgc || 0f == scgc.floatValue()){
					dto.setScgc(scgc);
					dto.setBcbhl(0f);
					dto.setLjbhl(0f);
					dto.setBhsl(0f);
					
					dto.setRemarks("初始高程");
				}else{
					dto.setScgc(scgc);
					CalcBhl.calc(dto);// 计算变化量
				}
			}else{
				dto.setScgc(scgc);
				CalcBhl.calc(dto);// 计算变化量
			}
		}

		pageInfo.setRows(result);
		pageInfo.setTotal(page.getTotal());
		return result;
	}
	
	//根据外键删除子表
	public void deleteDetails(String fid){
		jcInfoDetailsMapper.deleteDetails(fid);
	}
	
	//根据监测点编号查询子表是否存在该监测点
	public int selectDetailsByJcd(String jcdbh){
		JcDetails cond = new JcDetails();
		cond.setJcdNo(jcdbh);
		return jcInfoDetailsMapper.selectCount(cond);
	}
	
	//根据外键查询子表
	public List<DmcjJcInfoDetailsDto> selectDetailsByFid(String fid){
		return jcInfoDetailsMapper.selectDetailsWithPoint(fid);
	}
	
	//根据主键批量删除子表
	public void deleteDetailsByIds(List<String> list){
		jcInfoDetailsMapper.deleteDetailsByIds(list);
	}
	
	//保存监测信息明细
	public void saveDetail(JcDetails detail){
		jcInfoDetailsMapper.insert(detail);
	}
	
	//创建excel模板
	@Override
	public HSSFWorkbook generateExcelTemplate() {
		//列标题
		String[] header = { "*点位编号","*本次高程"};
		
		//sheet页名称
		String sheetName = "rcjcmuban";
		//生成excel模版
		HSSFWorkbook wb = ExcelUtils.excelSheet(header, sheetName);
		HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row = sheet.getRow(0);
        
        ExcelUtils.resizeWidth(wb,row,sheet);
		return wb;
	}


	//更新所有
	public void updateAll(DmcjJcInfoDto dto) {
		JcInfo info = new JcInfo();
		BeanUtils.copyProperties(dto, info);
		String mstId = dto.getId();//主表主键
		String json = dto.getDetails();//获取新的明细信息然后全部插入
		//子表信息
		if(!"[]".equals(json)){
			json = json.replace("&quot;", "\"");
			 List<JcDetails> list = new ArrayList<JcDetails>();  
		     list = JSONObject.parseArray(json, JcDetails.class);  
		     for(JcDetails det:list){
		    	 det.setFid(mstId);//设置外键
		    	 det.setGcbh(dto.getGcbh());//设置工程编号
		    	 det.setId(CommUtils.getUUID());//设置主键
		     }
		     jcInfoMapper.updateById(info);//保存主表
		     jcInfoDetailsMapper.deleteDetails(mstId);//先删除主表之前关联的所有明细信息
		     jcInfoDetailsMapper.batchInsert(list);//保存子表
		}
		
	}

}
