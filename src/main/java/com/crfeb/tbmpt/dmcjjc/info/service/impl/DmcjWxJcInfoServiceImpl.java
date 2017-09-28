package com.crfeb.tbmpt.dmcjjc.info.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.calc.CalcBhl;
import com.crfeb.tbmpt.commons.utils.CommUtils;
import com.crfeb.tbmpt.commons.utils.ExcelUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.dmcjjc.bg.mapper.DmcjJcbgInfoMapper;
import com.crfeb.tbmpt.dmcjjc.bg.mapper.DmcjJcbgsbInfoMapper;
import com.crfeb.tbmpt.dmcjjc.bg.model.JcbgDetail;
import com.crfeb.tbmpt.dmcjjc.bg.model.JcbgInfo;
import com.crfeb.tbmpt.dmcjjc.bg.model.JcbgsbDetails;
import com.crfeb.tbmpt.dmcjjc.bg.model.JcbgsbInfo;
import com.crfeb.tbmpt.dmcjjc.bg.service.IDmcjJcbgInfoService;
import com.crfeb.tbmpt.dmcjjc.bg.service.IDmcjJcbgsbInfoService;
import com.crfeb.tbmpt.dmcjjc.info.mapper.DmcjJcInfoDetailsMapper;
import com.crfeb.tbmpt.dmcjjc.info.mapper.DmcjJcInfoMapper;
import com.crfeb.tbmpt.dmcjjc.info.mapper.DmcjJcPointMapper;
import com.crfeb.tbmpt.dmcjjc.info.model.JcDetails;
import com.crfeb.tbmpt.dmcjjc.info.model.JcInfo;
import com.crfeb.tbmpt.dmcjjc.info.model.JcPoint;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDetailsDto;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcInfoDto;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcPointDto;
import com.crfeb.tbmpt.dmcjjc.info.service.IDmcjJcPointService;
import com.crfeb.tbmpt.dmcjjc.info.service.IDmcjWxJcInfoService;

/**
 * <p>无锡地面沉降监测业务层  ServiceImpl</p>
 * <p>系统：地面沉降监测系统</p>
 * <p>日期：2017-02-16</p>
 * @version 1.0
 * @author wl_wpg
 */
@Service
public class DmcjWxJcInfoServiceImpl
extends SuperServiceImpl<DmcjJcInfoMapper, JcInfo>implements IDmcjWxJcInfoService {
	@Autowired
	private DmcjJcInfoMapper jcInfoMapper; 
	@Autowired
	private DmcjJcPointMapper jcPointMapper;
	@Autowired
	private DmcjJcInfoDetailsMapper jcInfoDetailsMapper;
	@Autowired
	private IDmcjJcPointService dmcjJcPointService;
	 
	//监测报告Service对象
	@Autowired
    private IDmcjJcbgInfoService bgInfoService;
	//监测报告Mapper对象
	@Autowired
	private DmcjJcbgInfoMapper jcbgInfoMapper;
	
	//监测报告上报Service对象
	@Autowired
    private IDmcjJcbgsbInfoService jcbgsbInfoService; 
	//监测报告上报Mapper对象
	@Autowired
	private DmcjJcbgsbInfoMapper jcbgsbInfoMapper;
	
	
	/***
     * 批量导入‘日常监测信息’保存；
     * @author wl_wpg
     * @param pId：项目Id
     * @param list： 日常监测报告明细集合（子表信息）；
     * @param fileUrlName 文件路径 （例如：/upload/dmcjjc/file/20161219/1482137493429161214A.DAT）
     * @param jcTime 监测时间   
     * @param tianqi 天气
     * @param remarks 备注
     * @return 返回‘提示语字符串’
     */
	public void save(String pid ,List<DmcjJcInfoDetailsDto> list,String fileUrlName,String jcTime,String tianqi ,String remarks) {
		String id = CommUtils.getUUID(); 
		//主表信息对象
		JcInfo info = new JcInfo();
		info.setId(id);//主表Id
		info.setGcbh(pid);//项目Id
		info.setJcTime(jcTime);//监测时间
		info.setTianqi(tianqi);//天气
		
		//文件路径+文件名（例如：/upload/dmcjjc/file/20161219/1482137493429161214A.DAT）
		info.setImpFilePath(fileUrlName);
		info.setRemarks(remarks);//备注
		
		
		//子表信息
		List<JcDetails> JcDetailsList = new ArrayList<JcDetails>(); 
		     for(DmcjJcInfoDetailsDto dto:list){
		    	 JcDetails det = new JcDetails();
		    	 BeanUtils.copyProperties(dto,det);
		    	 det.setFid(id);//设置外键（主表Id）
		    	 det.setGcbh(pid);//设置工程编号(项目Id)
		    	 det.setId(CommUtils.getUUID());//设置主键(子表Id)
		    	 JcDetailsList.add(det);
		     }
		     this.insert(info);	//保存主表
		  jcInfoDetailsMapper.batchInsert(JcDetailsList);//保存子表  
		  //修改主表：确认,（因无锡的数据是正确的，所以都‘已确认’）；
		  qy(id);
		  jcbgSave(JcDetailsList,info);
	}


	//计算变化量(第一次导入时进入)
	public List<DmcjJcInfoDetailsDto> addCalcBhl( Map<String,Map<String,String>> map,String  jcTime,String pid){
		List<DmcjJcInfoDetailsDto> details = new ArrayList<DmcjJcInfoDetailsDto>();
		//构造查询条件
		List<Map<String,String>> conds = new ArrayList<Map<String,String>>();
		Set<String> keys = map.keySet();
		for(String key : keys){ 
			//根据 k 获取 map 中的 v集合；
			Map<String,String>  ycjcMap = map.get(key);
			 
			conds.add(ycjcMap);
		}
		
		List<DmcjJcPointDto> points = jcPointMapper.selectJcPointsByJcdno(conds);
		for(DmcjJcPointDto point : points){
			DmcjJcInfoDetailsDto detail = new DmcjJcInfoDetailsDto();
			detail.setJcdNo(map.get(point.getJcdbh()).get("jcd"));
			
			
			/**
			 * 1.1、本次高程为“0”，则、用监测时间最近的历史有值的上次高程补充；
             * 1.2、没有上次高程“第一次测量”则、使用初始高程补充；
             * 1.3、本次高程为“0”，初始高程为“0”,则、不补充；
			 * 2.1、本次高程不为“0”，初始高程为“0”,则、本次高程补充初始高程；
			 * 3.1、本次高程不为“0”，上次高程为“0”,则、初始高程补充上次高程；
			 */
			Float bcgc = Float.parseFloat(map.get(point.getJcdbh()).get("bcgc"));
			
			//查询 监测时间最近的历史有值的上次高程；
			Float  scgc = jcInfoDetailsMapper.selectBeValueOfCenturiedScgcByJcdNo(point.getJcdbh(), pid);   
			if( null == bcgc || 0f == bcgc.floatValue()){
				//Float scgc = jcPointMapper.getJcInfoScgc(point.getJcdbh(), jcTime,pid);
				
				//1.1、没有上次高程使用初始高程
				if(null == scgc || 0f == scgc.floatValue()){
					scgc = point.getCsgc();
					
					//1.2、没有初始高程页面置空 0 页面显示置空 同时备注显示 初始高程
					if(null == scgc || 0f == scgc.floatValue()){
						/** 初始高程也为空时 */
						detail.setScgc(0f);
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
						
						//detail.setRemarks("初始高程");
						details.add(detail);
						
					}else{
						/** 第一次测量 */
						/** 初始高程不为空时 ，上次高程为初始高程*/
						detail.setScgc(scgc);
						detail.setBcgc(scgc);
						detail.setCsgc(point.getCsgc());
						detail.setQujian(point.getQjbh());
						detail.setXianlu(point.getXlbh());
						detail.setLicheng(point.getLicheng());
						detail.setWeizhi(point.getWeizhi());
						detail.setQujianName(point.getQujianName());
						detail.setXianluName(point.getXianluName());
						detail.setId(CommUtils.getUUID());
						detail.setRemarks("本次高程为空用初始高程！");
						CalcBhl.calc(detail);//计算变化量
						
						details.add(detail);
					}
				}else{
					/** 历史的上次高程不为空时，本次高程为空用历史的上次高程*/
					detail.setScgc(scgc);
					detail.setBcgc(scgc);
					detail.setRemarks("本次高程为空用历史的上次高程！");
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
				/** 本次高程不为空时 */
				detail.setBcgc(bcgc);
				 /**
				  * 没有初始高程以及上次高程时
				  * 1、更新监测点初始高程
				  * 2、更新本次明细状态--初始高程
				  */
				if(null == point.getCsgc() || 0f == point.getCsgc().floatValue()){
					 JcPoint jp = new JcPoint();
					 jp.setGcbh(point.getGcbh());
					 jp.setJcdbh(point.getJcdbh());
					 jp = jcPointMapper.selectOne(jp);
					 if(null == jp.getCsgc() || 0f == jp.getCsgc().floatValue()){
						 jp.setCsgc(detail.getBcgc());
						 jcPointMapper.updateSelectiveById(jp);
						 point.setCsgc(detail.getBcgc());
						 detail.setRemarks("初始高程为空本次高程为初始高程！");
					 }
				}
				else{
					detail.setCsgc(point.getCsgc());
				}
				
				if(null == scgc || 0f == scgc.floatValue()){
					/** 上次高程为空时 */
					scgc = point.getCsgc();
					detail.setScgc(scgc);
				}
				else{
					detail.setScgc(scgc);
				}
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
	 
	/***
     * 保存到‘监测报告表’
     * @author wl_wpg 
     * @param list：日常监测报告明细集合；（子表信息）
     * @param info 日常监测报告信息（主表信息）
     */
	public void jcbgSave(List<JcDetails> JcDetailsList,	JcInfo info) {
		String id = CommUtils.getUUID(); 
		//主表信息
		JcbgInfo jcInfo = new JcbgInfo();
		jcInfo.setGcbh(info.getGcbh());
		jcInfo.setJcTime(info.getJcTime());
		jcInfo.setTianqi(info.getTianqi());
		jcInfo.setIfqr("已确认");//（因无锡的数据是正确的，所以都‘已确认’）；
		jcInfo.setIfsb("未上报");//新数据默认为未上报
		jcInfo.setRemarks(info.getRemarks());
		jcInfo.setId(id);
		
		//子表信息
		List<JcbgDetail> jcbgList = new ArrayList<JcbgDetail>(); 
		for(JcDetails det:JcDetailsList){
			JcbgDetail jcbgDetail = new JcbgDetail();
			BeanUtils.copyProperties(det,jcbgDetail);
			jcbgDetail.setId(CommUtils.getUUID());//设置主键
			jcbgDetail.setFid(id);//设置外键(主表Id)
			jcbgDetail.setGcbh(info.getGcbh());//设置(项目Id) 
	    	jcbgList.add(jcbgDetail);
	     }
	     
	     bgInfoService.insert(jcInfo);	//保存主表
	     jcbgInfoMapper.batchInsert(jcbgList);//保存子表
	     //保存到‘监测报告上报表’中
	     jcBgSbSave( jcbgList, jcInfo); 
	}
	
	
	/***
     * 保存到‘监测报告上报表’中
     * @author wl_wpg 
     * @param jcbgList：监测报告明细集合；（子表信息）
     * @param jcInfo 监测报告信息（主表信息）
     */ 
  	public void jcBgSbSave(List<JcbgDetail> jcbgList, JcbgInfo jcInfo) {
		String id = CommUtils.getUUID(); 
		//主表信息
		JcbgsbInfo jcBgSbInfo = new JcbgsbInfo();
		jcBgSbInfo.setGcbh(jcInfo.getGcbh());
		jcBgSbInfo.setJcTime(jcInfo.getJcTime());
		jcBgSbInfo.setTianqi(jcInfo.getTianqi());
		jcBgSbInfo.setIfqr("已确认");//（因无锡的数据是正确的，所以都‘已确认’）；
		jcBgSbInfo.setRemarks(jcInfo.getRemarks());
		jcBgSbInfo.setId(id);
		 
		//子表信息
		List<JcbgsbDetails> jcbgSbList = new ArrayList<JcbgsbDetails>(); 
		for(JcbgDetail det:jcbgList){
			JcbgsbDetails jcbgSbDetail = new JcbgsbDetails();
			BeanUtils.copyProperties(det,jcbgSbDetail);
			jcbgSbDetail.setId(CommUtils.getUUID());//设置主键
			jcbgSbDetail.setFid(id);//设置外键(主表Id)
			jcbgSbDetail.setGcbh(jcInfo.getGcbh());//设置(项目Id) 
			jcbgSbList.add(jcbgSbDetail);
	     }
		
		
		jcbgsbInfoService.insert(jcBgSbInfo);	//保存主表
		 jcbgsbInfoMapper.batchInsert(jcbgSbList);//保存子表
		//更新为已上报
		 jcbgInfoMapper.updateShangbaoStatus(jcInfo.getId());
	}

	
	
  	
  	

    public void selectDataGrid(PageInfo pageInfo) {
        Page<DmcjJcInfoDto> page = new Page<DmcjJcInfoDto>(pageInfo.getNowpage(), pageInfo.getSize());
        List<DmcjJcInfoDto> list = jcInfoMapper.selectJcInfoPage(page, pageInfo.getCondition());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
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
	 * wl_wpg:监测日报确认
	 * id:主表Id
	 */
	public void qy(String id) {
		//主表信息对象
		JcInfo jc = new JcInfo();
		jc.setId(id);
		jc = jcInfoMapper.selectOne(jc);
		//根据 ‘主表Id’ 查询获取 ‘子表集合’；
		List<DmcjJcInfoDetailsDto> dtos = jcInfoDetailsMapper.selectDetailsWithPoint(id);
		for(DmcjJcInfoDetailsDto dto : dtos){
			//根据 ‘监测点编号’和‘监测时间’ ‘项目Id’ 查询该监测点的‘上次高程’，如果上次高程不存在，则上次高程取初始高程
			Float scgc = jcPointMapper.getJcInfoScgc(dto.getJcdNo(),jc.getJcTime(),jc.getGcbh());
			if(null == scgc || 0f == scgc.floatValue()){
				if(null == dto.getCsgc() || 0f == dto.getCsgc().floatValue()){
					//没有初始高程以及上次高程时
					//1、更新监测点初始高程
					//2、更新本次明细状态--初始高程
					//监测点 对象
					JcPoint jp = new JcPoint();
					jp.setGcbh(dto.getGcbh());
					jp.setJcdbh(dto.getJcdNo());
					//根据‘项目Id’‘监测点Id’ 查询获取‘监测点对象’
					jp = jcPointMapper.selectOne(jp);
					/** 初始高程 */
					jp.setCsgc(dto.getBcgc());
					//修改 监测点 的 ‘初次高程’
					jcPointMapper.updateSelectiveById(jp);
					
					//子表 对象
					JcDetails jd = new JcDetails();
					jd.setId(dto.getId());
					jd = jcInfoDetailsMapper.selectOne(jd);
					//修改子表Id
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
	
	/***
	 * 添加  ‘监测点’前缀， 点“.”替换成 杠“-”
	 */
	@Override
	public Map<String,Map<String,String>> bjJcdbh( Map<String,Map<String,String>> map ,Float baseNumber){
		Map<String,Map<String,String>> detailsMap = new HashMap<String,Map<String,String>>();
		 
		Set<String> keys = map.keySet();
		for(String key : keys){
			//获取 ‘首字母’
			char k = key.charAt(0);
			//判断是否是数字
            if(!((k>='a'&&k<='z')||(k>='A'&&k<='Z')))   
	        {    
            	k ='0';
	        }  
			String jcdbh ="";
			//获取 监测点的首字母 判断，并 追加‘监测点’前缀
			switch(k)
		      {
			     // 把 key(监测点) 中的‘代表字母’替换成‘监测点’前缀， 点“.”替换成 杠“-”
		         case '0' : 
		        	 jcdbh = "DBL"+key.replace(".", "-"); 
		             break;
		         case 'Q' : 
		        	 jcdbh = key.replace("Q", "QC").replace(".", "-"); 
		             break;
		         case 'L' : 
		        	 jcdbh = key.replace("L", "LZ").replace(".", "-"); 
		             break;
		         /*case 'J' : 
		        	 jcdbh = key.replace("J", "JC").replace(".", "-"); 
		        	 break;*/
		         case 'X' : 
		        	 jcdbh = key.replace("X", "GX").replace(".", "-"); 
		             break;
		         default : 
		        	 jcdbh = key.replace(".", "-"); 
		      }
			//根据 k 获取 map 中的 v集合；
			Map<String,String>  ycjcMap = map.get(key);
			//更换 有 前缀 的 ‘监测点’；
			ycjcMap.put("jcd", jcdbh);
			//计算 本次高程
			if(null !=baseNumber &&  baseNumber>0){
				String SBcgc = ycjcMap.get("bcgc");
				float FBcgc = Float.parseFloat(SBcgc);
				 //本次高程 = 控制点高程 + 监测数据；
				String Bcgc = String.valueOf(FBcgc + baseNumber);
				ycjcMap.put("bcgc", Bcgc);
			}
			// 更换 有 前缀 的 ‘监测点’的 日常监测信息集合；
			detailsMap.put(jcdbh, ycjcMap);  
		}
		return detailsMap;
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
					
					
				}else{
					dto.setScgc(scgc);
					if(0f == dto.getBcgc().floatValue()){
						dto.setBcgc(scgc);
						dto.setRemarks("本次高程和上次高程为空用初始高程！");
					}
					CalcBhl.calc(dto);// 计算变化量
					
				}
			}else{
				dto.setScgc(scgc);
				if(0f == dto.getBcgc().floatValue()){
					dto.setBcgc(scgc);
					dto.setRemarks("本次高程为空用上次高程！");
				}
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
