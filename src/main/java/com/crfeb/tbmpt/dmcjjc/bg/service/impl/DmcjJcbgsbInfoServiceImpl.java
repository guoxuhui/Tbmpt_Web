package com.crfeb.tbmpt.dmcjjc.bg.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import com.crfeb.tbmpt.dmcjjc.bg.mapper.DmcjJcbgInfoMapper;
import com.crfeb.tbmpt.dmcjjc.bg.mapper.DmcjJcbgsbDetailsMapper;
import com.crfeb.tbmpt.dmcjjc.bg.mapper.DmcjJcbgsbInfoMapper;
import com.crfeb.tbmpt.dmcjjc.bg.model.JcbgDetail;
import com.crfeb.tbmpt.dmcjjc.bg.model.JcbgInfo;
import com.crfeb.tbmpt.dmcjjc.bg.model.JcbgsbDetails;
import com.crfeb.tbmpt.dmcjjc.bg.model.JcbgsbInfo;
import com.crfeb.tbmpt.dmcjjc.bg.model.vo.JcbgsbInfoDto;
import com.crfeb.tbmpt.dmcjjc.bg.service.IDmcjJcbgsbInfoService;
import com.crfeb.tbmpt.dmcjjc.info.mapper.DmcjJcPointMapper;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDetailsDto;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDto;

@Service
public class DmcjJcbgsbInfoServiceImpl
	extends SuperServiceImpl<DmcjJcbgsbInfoMapper, JcbgsbInfo> implements IDmcjJcbgsbInfoService {
	private static final Logger LOGGER = LogManager.getLogger(DmcjJcbgsbInfoServiceImpl.class);
	@Autowired
	private DmcjJcbgsbInfoMapper jcbgsbInfoMapper;
	@Autowired
	private DmcjJcPointMapper jcPointMapper;
	@Autowired
	private DmcjJcbgsbDetailsMapper bgsbDetailsMapper;
	@Autowired
	private DmcjJcbgInfoMapper jcbgInfoMapper;
    public void selectDataGrid(PageInfo pageInfo) {
        Page<JcbgsbInfo> page = new Page<JcbgsbInfo>(pageInfo.getNowpage(), pageInfo.getSize());
        List<JcbgsbInfoDto> list = jcbgsbInfoMapper.selectBgsbPage(page, pageInfo.getCondition());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }

  //根据id批量查询出基础数据，再生成excel返回给controller
  	public HSSFWorkbook expData(List<String> ids) {
  		List<JcbgsbInfo> list = jcbgsbInfoMapper.selectBgsb(ids);
  		//列标题
  		String[] header = { "序号","工程编号", "本次监测时间", 
  				"天气","备注"};
  		//sheet页名称
  		String sheetName = "监测报告上报数据";
  		String subSheetName = "子表数据";
	    String[] subheader = {"序号","监测点编号", "本次高程", "工程编号", 
	  				"区间","线路","位置","里程","备注"};
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
            JcbgsbInfo info = list.get(i);    
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
            String remarks = info.getRemarks();
            if(null != remarks)
            	row.createCell(4).setCellValue(remarks);
            //自动设置列宽度
            sheet.autoSizeColumn((short)0);
            sheet.autoSizeColumn((short)1);
            sheet.autoSizeColumn((short)2);
            sheet.autoSizeColumn((short)3);
            sheet.autoSizeColumn((short)4);
            
          //查询该记录对应子表记录
            List<DmcjJcInfoDetailsDto> details = jcbgsbInfoMapper.selectDetailsWithPoint(info.getId());
            detailsTotal.addAll(details);
        }    
          
        //生子表内容
          for (int j = 0; j < detailsTotal.size(); j++) {    
          	  subrow = subsheet.createRow(j + 1);    
          	  DmcjJcInfoDetailsDto detail = detailsTotal.get(j);    
          	  subrow.createCell(0).setCellValue(j+1);//行号 
                String jcdno = detail.getJcdNo();
                if(null != jcdno)
              	  subrow.createCell(1).setCellValue(jcdno);    
                Float bcgc = detail.getBcgc();
                if(null != bcgc)
              	  subrow.createCell(2).setCellValue(bcgc);    
                String subgcbh = detail.getGcbh();
                if(null != subgcbh)
              	  subrow.createCell(3).setCellValue(subgcbh);    
                String qujian = detail.getQujianName();
                if(null != qujian)
              	  subrow.createCell(4).setCellValue(qujian);
                String xianlu = detail.getXianluName();
                if(null != xianlu)
              	  subrow.createCell(5).setCellValue(xianlu);
                String weizhi = detail.getWeizhi();
                if(null != weizhi)
              	  subrow.createCell(6).setCellValue(weizhi);
                String licheng = detail.getLicheng();
                if(null != licheng)
              	  subrow.createCell(7).setCellValue(licheng);
                String subremarks = detail.getRemarks();
                if(null != subremarks)
              	  subrow.createCell(8).setCellValue(subremarks);
                
                subsheet.autoSizeColumn((short)0);
                subsheet.autoSizeColumn((short)1);
                subsheet.autoSizeColumn((short)2);
                subsheet.autoSizeColumn((short)3);
                subsheet.autoSizeColumn((short)4);
                subsheet.autoSizeColumn((short)5);
                subsheet.autoSizeColumn((short)6);
                subsheet.autoSizeColumn((short)7);
                subsheet.autoSizeColumn((short)8);
            }
        ExcelUtils.resizeWidth(wb,row,sheet);
  		ExcelUtils.resizeWidth(wb,subrow,subsheet);          
  		return wb;
  	}
    
  	//从监测报告表上报数据到监测报告上报表中
  	public void save(DmcjJcInfoDto dto) {
		String id = CommUtils.getUUID(); 
		//主表信息
		JcbgsbInfo info = new JcbgsbInfo();
		info.setGcbh(dto.getGcbh());
		info.setJcTime(dto.getJcTime());
		info.setTianqi(dto.getTianqi());
		info.setIfqr("未确认");//新上报的数据默认未确认
		info.setRemarks(dto.getRemarks());
		info.setId(id);
		String json = dto.getDetails();
		//子表信息
		if(!"[]".equals(json) && null != json){
			json = json.replace("&quot;", "\"");
			 List<JcbgsbDetails> list = new ArrayList<JcbgsbDetails>();  
		     list = JSONObject.parseArray(json, JcbgsbDetails.class);  
		     for(JcbgsbDetails det:list){
		    	 det.setFid(id);//设置外键
		    	 det.setGcbh(dto.getGcbh());//设置工程编号
		    	 det.setId(CommUtils.getUUID());//设置主键
		     }
		     this.insert(info);	//保存主表
		     jcbgsbInfoMapper.batchInsert(list);//保存子表
		}
	}

	public void del(Long id) {
		jcbgsbInfoMapper.deleteById(id);
	}

	public void update(JcbgsbInfo dmcjDDInfo) {
		jcbgsbInfoMapper.updateById(dmcjDDInfo);
	}

	public JcbgsbInfo findById(Long id) {
		return jcbgsbInfoMapper.selectById(id);
	}

	public List<DmcjJcInfoDetailsDto> selectDetailsWithPoint(String fid, String jcTime,String pid){
		List<DmcjJcInfoDetailsDto> list = jcbgsbInfoMapper.selectDetailsWithPoint(fid);
		for(DmcjJcInfoDetailsDto dto:list){
			//根据监测点编号和监测时间取该监测点上次高程，如果没有，则上次高程为初始高程
			Float scgc = jcPointMapper.getJcBgsbScgc(dto.getJcdNo(), jcTime,pid);
			if(null == scgc){
				scgc = dto.getCsgc();
			}
			dto.setScgc(scgc);
			CalcBhl.calc(dto);
		}
		return list;
	}
	
	//根据外键查询子表（编辑页面打开时进入）
		public List<DmcjJcInfoDetailsDto> calcBhl(String fid, String jcTime,PageInfo pageInfo,String pid){
//			List<DmcjJcInfoDetailsDto> result = jcbgInfoMapper.selectDetailsWithPoint(fid);
			Page<JcbgsbDetails> page = new Page<JcbgsbDetails>(pageInfo.getNowpage(),	pageInfo.getSize());
			List<DmcjJcInfoDetailsDto> result = bgsbDetailsMapper.selectJcbgsbDetailPage(page, pageInfo.getCondition());
			for(DmcjJcInfoDetailsDto dto : result){
				/**
				 * 1.1、没有上次高程使用初始高程
				 * 1.2、没有初始高程页面置空 0 页面显示置空 同时备注显示 初始高程
				 * 
				 * 2.1、没有上次高程使用初始高程
				 * 
				 * 3.1、有上次高程使用上次高程 （正常情况）
				 */
				// 根据监测点编号和监测时间取该监测点上次高程，如果没有，则上次高程为初始高程
				Float scgc = jcPointMapper.getJcBgsbScgc(dto.getJcdNo(), jcTime,pid);
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
			jcbgsbInfoMapper.deleteDetails(fid);
		}
		//删除主表和子表
		public void deleteAndDetails(String id){
			jcbgsbInfoMapper.deleteDetails(id);
			JcbgsbInfo info = new JcbgsbInfo();
			info.setId(id);
			jcbgsbInfoMapper.deleteSelective(info);
		}
		/**
		 * 批量删除主表和明细
		 * 2016年11月23日
		 */
		public Boolean deleteAndDetailsList(String[] ids){
			List<String> idss = new ArrayList<String>();
			Collections.addAll(idss, ids);
			List<JcbgsbInfo> list = jcbgsbInfoMapper.selectBgsb(idss);
			Boolean flat = true;
			for (JcbgsbInfo info : list) {
				if("已确认".equals(info.getIfqr())){
					flat = false;
				}
			}
			if(flat){
				for (String id : ids) {
					//封装上报主表
					JcbgsbInfo info = new JcbgsbInfo();
					info.setId(id);
					//撤销上报确认
					JcbgsbInfo jcbgsb = jcbgsbInfoMapper.selectOne(info);
					JcbgInfo jcbg = new JcbgInfo();
					jcbg.setGcbh(jcbgsb.getGcbh());
					jcbg.setJcTime(jcbgsb.getJcTime());
					JcbgInfo jc = jcbgInfoMapper.selectOne(jcbg);
					if(jc != null){
						jcbgInfoMapper.updateShangbaoStatusWsb(jc.getId());
					}
					//删除上报明细
					jcbgsbInfoMapper.deleteDetails(id);
					//删除上报主表
					jcbgsbInfoMapper.deleteSelective(info);
				}
				return true;
			}else{
				return false;
			}
		}
		/**
		 * 确认状态 设置状态字段为已确认
		 * 2016年11月23日
		 */
		public void qy(String id) {
			jcbgsbInfoMapper.qy(id);		
		}

		/**
		 * 撤销确认设置字段为未确认
		 * 2016年11月23日
		 */
		public void jy(String id) {
			jcbgsbInfoMapper.jy(id);
		}

		@Override
		public void updateAll(DmcjJcInfoDto dto) {
			JcbgsbInfo info = new JcbgsbInfo();
			BeanUtils.copyProperties(dto, info);
			String mstId = dto.getId();//主表主键
			String json = dto.getDetails();
			//子表信息
			if(!"[]".equals(json) && null != json){
				json = json.replace("&quot;", "\"");
				 List<JcbgsbDetails> list = new ArrayList<JcbgsbDetails>();  
			     list = JSONObject.parseArray(json, JcbgsbDetails.class);  
			     for(JcbgsbDetails det:list){
			    	 det.setFid(mstId);//设置外键
			    	 det.setGcbh(dto.getGcbh());//设置工程编号
			    	 det.setId(CommUtils.getUUID());//设置主键
			     }
			     jcbgsbInfoMapper.updateById(info);//保存主表
			     jcbgsbInfoMapper.deleteDetails(mstId);//先删除主表之前关联的所有明细信息
			     jcbgsbInfoMapper.batchInsert(list);//保存子表
			}
			
		}
}
