package com.crfeb.tbmpt.dmcjjc.bg.service.impl;

import java.util.ArrayList;
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
import com.crfeb.tbmpt.dmcjjc.bg.mapper.DmcjJcbgDetailsMapper;
import com.crfeb.tbmpt.dmcjjc.bg.mapper.DmcjJcbgInfoMapper;
import com.crfeb.tbmpt.dmcjjc.bg.model.JcbgDetail;
import com.crfeb.tbmpt.dmcjjc.bg.model.JcbgInfo;
import com.crfeb.tbmpt.dmcjjc.bg.service.IDmcjJcbgInfoService;
import com.crfeb.tbmpt.dmcjjc.info.mapper.DmcjJcPointMapper;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDetailsDto;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDto;

@Service
public class DmcjJcbgInfoServiceImpl
	extends SuperServiceImpl<DmcjJcbgInfoMapper, JcbgInfo> implements IDmcjJcbgInfoService {
	private static final Logger LOGGER = LogManager.getLogger(DmcjJcbgInfoServiceImpl.class);
	@Autowired
	private DmcjJcbgInfoMapper jcbgInfoMapper;
	@Autowired
	private DmcjJcbgDetailsMapper bgDetailsMapper;
	@Autowired
	private DmcjJcPointMapper jcPointMapper;
	
	//根据外键删除子表
	public void deleteDetails(String fid){
		jcbgInfoMapper.deleteDetails(fid);
	}
	
	public void selectDataGrid(PageInfo pageInfo) {
        Page<JcbgInfo> page = new Page<JcbgInfo>(pageInfo.getNowpage(), pageInfo.getSize());
        List<JcbgInfo> list = jcbgInfoMapper.selectBgPage(page, pageInfo.getCondition());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }

	public List<DmcjJcInfoDetailsDto> selectDetailsWithPoint(String fid,String jcTime,String pid){
		List<DmcjJcInfoDetailsDto> list = jcbgInfoMapper.selectDetailsWithPoint(fid);
		for(DmcjJcInfoDetailsDto dto:list){
			//根据监测点编号和监测时间取该监测点上次高程，如果没有，则上次高程为初始高程
			Float scgc = jcPointMapper.getJcBgScgc(dto.getJcdNo(), jcTime,pid);
			if(null == scgc){
				scgc = dto.getCsgc();
				dto.setScgc(scgc);
			}
			dto.setScgc(scgc);
			CalcBhl.calc(dto);
		}
		return list;
	}
	
  //根据id批量查询出基础数据，再生成excel返回给controller
	public HSSFWorkbook expData(List<String> ids,String pid) {
		List<JcbgInfo> list = jcbgInfoMapper.selectBg(ids);
		// 列标题
		String[] header = { "序号", "工程编号", "本次监测时间", "天气", "备注", "确认状态", "上报状态" };
		// sheet页名称
		String sheetName = "监测报告数据";
		String subSheetName = "子表数据";
		String[] subheader = { "序号", "监测点编号", "本次高程", "上次高程", "初始高程", "本次变化量",
				"累计变化量", "变化速率", "工程编号", "区间", "线路", "位置", "里程", "备注" };
		// 子表总记录
		List<DmcjJcInfoDetailsDto> detailsTotal = new ArrayList<DmcjJcInfoDetailsDto>();

		HSSFWorkbook wb = ExcelUtils.excelSheet(header, sheetName, subheader,
				subSheetName);
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow row = sheet.getRow(0);
		HSSFSheet subsheet = wb.getSheetAt(1);
		HSSFRow subrow = subsheet.getRow(0);

		// 生成单元格内容
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow(i + 1);
			JcbgInfo info = list.get(i);
			row.createCell(0).setCellValue(i + 1);// 行号
			String gcbh = info.getGcbh();
			if (null != gcbh)
				row.createCell(1).setCellValue(gcbh);
			String time = info.getJcTime();
			if (null != time)
				row.createCell(2).setCellValue(time);
			String tianqi = info.getTianqi();
			if (null != tianqi)
				row.createCell(3).setCellValue(tianqi);
			String remarks = info.getRemarks();
			if (null != remarks)
				row.createCell(4).setCellValue(remarks);
			String qr = info.getIfqr();
			if (null != qr)
				row.createCell(5).setCellValue(qr);
			String sb = info.getIfsb();
			if (null != sb)
				row.createCell(6).setCellValue(sb);
			// 自动设置列宽度
			sheet.autoSizeColumn((short) 0);
			sheet.autoSizeColumn((short) 1);
			sheet.autoSizeColumn((short) 2);
			sheet.autoSizeColumn((short) 3);
			sheet.autoSizeColumn((short) 4);
			sheet.autoSizeColumn((short) 5);
			sheet.autoSizeColumn((short) 6);

			// 查询该记录对应子表记录
			List<DmcjJcInfoDetailsDto> details = jcbgInfoMapper
					.selectDetailsWithPoint(info.getId());
			detailsTotal.addAll(details);
		}

		// 生子表内容
		for (int j = 0; j < detailsTotal.size(); j++) {
			subrow = subsheet.createRow(j + 1);
			DmcjJcInfoDetailsDto detail = detailsTotal.get(j);
			JcbgInfo bg = new JcbgInfo();
			bg.setId(detail.getFid());
			Float scgc = jcPointMapper.getJcBgScgc(detail.getJcdNo(),
					jcbgInfoMapper.selectOne(bg).getJcTime(),pid);
			if (null == scgc) {
				scgc = detail.getCsgc();
			}
			detail.setScgc(scgc);

			ExcelUtils.makeDetailSheet(j, subrow, subsheet, detail);
		}
		ExcelUtils.resizeWidth(wb,row,sheet);
		ExcelUtils.resizeWidth(wb,subrow,subsheet);
		return wb;
	}

  	public void save(DmcjJcInfoDto dto) {
		String id = CommUtils.getUUID(); 
		//主表信息
		JcbgInfo info = new JcbgInfo();
		info.setGcbh(dto.getGcbh());
		info.setJcTime(dto.getJcTime());
		info.setTianqi(dto.getTianqi());
		info.setIfqr("未确认");//新上报的数据默认未确认
		info.setIfsb("未上报");//新数据默认为未上报
		info.setRemarks(dto.getRemarks());
		info.setId(id);
		String json = dto.getDetails();
		//子表信息
		if(!"[]".equals(json) && null != json){
			json = json.replace("&quot;", "\"");
			 List<JcbgDetail> list = new ArrayList<JcbgDetail>();  
		     list = JSONObject.parseArray(json, JcbgDetail.class);  
		     for(JcbgDetail det:list){
		    	 det.setFid(id);//设置外键
		    	 det.setGcbh(dto.getGcbh());//设置工程编号
		    	 det.setId(CommUtils.getUUID());//设置主键
		     }
		     this.insert(info);	//保存主表
		     jcbgInfoMapper.batchInsert(list);//保存子表
		}
	}

	public void del(Long id) {
		jcbgInfoMapper.deleteById(id);
	}

	public void update(DmcjJcInfoDto dto) {
		JcbgInfo info = new JcbgInfo();
		info.setGcbh(dto.getGcbh());
		info.setJcTime(dto.getJcTime());
		info.setTianqi(dto.getTianqi());
		info.setIfqr(dto.getIfqr());
		info.setRemarks(dto.getRemarks());
		info.setId(dto.getId());
		info.setIfsb(dto.getIfsb());
		//保存主表
		jcbgInfoMapper.updateById(info);
	}

	public JcbgInfo findById(Long id) {
		return jcbgInfoMapper.selectById(id);
	}

	public void qy(String id) {
		jcbgInfoMapper.qy(id);		
	}

	public void jy(String id) {
		jcbgInfoMapper.jy(id);
	}
	//更新为已上报
	public void updateShangbaoStatus(String id){
		jcbgInfoMapper.updateShangbaoStatus(id);
	}
	
	//根据外键查询子表（编辑页面打开时进入）
	public List<DmcjJcInfoDetailsDto> calcBhl(String fid, String jcTime,PageInfo pageInfo,String pid){
//		List<DmcjJcInfoDetailsDto> result = jcbgInfoMapper.selectDetailsWithPoint(fid);
		Page<JcbgDetail> page = new Page<JcbgDetail>(pageInfo.getNowpage(),	pageInfo.getSize());
		List<DmcjJcInfoDetailsDto> result = bgDetailsMapper.selectJcbgDetailPage(page, pageInfo.getCondition());
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
			Float scgc = jcPointMapper.getJcBgScgc(dto.getJcdNo(), jcTime,pid);
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
	
	//根据主键批量删除子表
	public void deleteDetailsByIds(List<String> list){
		bgDetailsMapper.deleteDetailsByIds(list);
	}
	
	//保存监测报告明细
	public void saveDetail(JcbgDetail detail){
		bgDetailsMapper.insert(detail);
	}
	
	//根据外键查询子表
	public List<DmcjJcInfoDetailsDto> selectDetailsByFid(String fid){
		return bgDetailsMapper.selectDetailsWithPoint(fid);
	}

	@Override
	public void updateAll(DmcjJcInfoDto dto) {
		JcbgInfo info = new JcbgInfo();
		BeanUtils.copyProperties(dto, info);
		String mstId = dto.getId();//主表主键
		String json = dto.getDetails();
		//子表信息
		if(!"[]".equals(json) && null != json){
			json = json.replace("&quot;", "\"");
			 List<JcbgDetail> list = new ArrayList<JcbgDetail>();  
		     list = JSONObject.parseArray(json, JcbgDetail.class);  
		     for(JcbgDetail det:list){
		    	 det.setFid(mstId);//设置外键
		    	 det.setGcbh(dto.getGcbh());//设置工程编号
		    	 det.setId(CommUtils.getUUID());//设置主键
		     }
		     jcbgInfoMapper.updateById(info);//保存主表
		     bgDetailsMapper.deleteDetails(mstId);//先删除主表之前关联的所有明细信息
		     jcbgInfoMapper.batchInsert(list);//保存子表
		}
		
	}
}
