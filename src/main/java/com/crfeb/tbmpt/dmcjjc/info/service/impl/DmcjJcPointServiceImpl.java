package com.crfeb.tbmpt.dmcjjc.info.service.impl;

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
import com.crfeb.tbmpt.commons.utils.CommUtils;
import com.crfeb.tbmpt.commons.utils.ExcelUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.dmcjjc.info.mapper.DmcjJcPointMapper;
import com.crfeb.tbmpt.dmcjjc.info.model.JcPoint;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcPointDto;
import com.crfeb.tbmpt.dmcjjc.info.service.IDmcjJcPointService;
import com.crfeb.tbmpt.project.mapper.ProRProjectSectionMapper;
import com.crfeb.tbmpt.project.mapper.ProRSectionLineMapper;

@Service
public class DmcjJcPointServiceImpl 
	extends SuperServiceImpl<DmcjJcPointMapper, JcPoint> implements IDmcjJcPointService {

	@Autowired
	private DmcjJcPointMapper jcPointMapper;
	
	//wpg:项目区间信息 数据访问层接口 Mapper
	@Autowired
	private ProRProjectSectionMapper proRProjectSectionService;
	
	//wpg:业务管理——掘进线路信息 数据访问层接口 Mapper
    @Autowired
    private ProRSectionLineMapper proRSectionLineMapper;
	
	//根据监测点编号和监测时间和当前登录用户的工程编号查询日常监测中的上次高程
	public Float getJcInfoScgc(String jcdno,String jcTime,String pid){
		return jcPointMapper.getJcInfoScgc(jcdno, jcTime,pid);
	}
	
	//根据监测点编号和监测时间和当前登录用户的工程编号查询监测报告中的上次高程
	public Float getJcBgScgc(String jcdno,String jcTime,String pid){
		return jcPointMapper.getJcBgScgc(jcdno, jcTime,pid);
	}
	
	//根据监测点编号和监测时间和当前登录用户的工程编号查询监测报告中的上次高程
	public Float getJcBgsbScgc(String jcdno,String jcTime,String pid){
		return jcPointMapper.getJcBgsbScgc(jcdno, jcTime,pid);
	}
	
    public void selectDataGrid(PageInfo pageInfo) {
        Page<JcPoint> page = new Page<JcPoint>(pageInfo.getNowpage(), pageInfo.getSize());
        List<DmcjJcPointDto> list = jcPointMapper.selectJcPointPage(page, pageInfo.getCondition(),pageInfo.getSort(), pageInfo.getOrder());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }

	public void save(JcPoint dmcjJcPoint) {
		jcPointMapper.insert(dmcjJcPoint);		
	}

	public void update(JcPoint dmcjJcPoint) {
		jcPointMapper.updateById(dmcjJcPoint);
	}

	public JcPoint findById(Long id) {
		return jcPointMapper.selectById(id);
	}

	public void qy(String id) {
		jcPointMapper.qy(id);		
	}

	public void jy(String id) {
		jcPointMapper.jy(id);
	}

	//根据id批量查询出基础数据，再生成excel返回给controller
	public HSSFWorkbook expData(List<String> ids) {
		List<JcPoint> list = jcPointMapper.selectJcPoints(ids);
		//列标题
		String[] header = { "序号","*工程编号", "*点位编号", 
				"*区间编号","线路编号","*点位设计类型","*环号/位置",
				"*里程","初始高程","*点位设计时间","备注","使用状态"};
		//sheet页名称
		String sheetName = "监测点数据";
		HSSFWorkbook wb = ExcelUtils.excelSheet(header, sheetName);
		HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row = sheet.getRow(0);
        //生成单元格内容
        for (int i = 0; i < list.size(); i++) {    
          row = sheet.createRow(i + 1);    
          JcPoint info = list.get(i);    
          row.createCell(0).setCellValue(i+1);//行号
          String gcbh = info.getGcbh();
          if(null != gcbh)
        	  row.createCell(1).setCellValue(gcbh);    
          String jcdbh = info.getJcdbh();
          if(null != jcdbh)
        	  row.createCell(2).setCellValue(jcdbh);
          String qjbh = info.getQjbh();
          if(null != qjbh)
        	  row.createCell(3).setCellValue(qjbh);
          String xlbh = info.getXlbh();
          if(null != xlbh)
        	  row.createCell(4).setCellValue(xlbh);
          String sjtype = info.getSjType();
          if(null != sjtype)
        	  row.createCell(5).setCellValue(sjtype);
          String weizhi = info.getWeizhi();
          if(null != weizhi)
        	  row.createCell(6).setCellValue(weizhi);
          String licheng = info.getLicheng();
          if(null != licheng)
        	  row.createCell(7).setCellValue(licheng);
          Float csgh = info.getCsgc();
          if(null != csgh){
        	  row.createCell(8).setCellValue(Double.parseDouble(String.valueOf(csgh)));
          }
          String sjtime = info.getSjTimeType();
          if(null != sjtime)
        	  row.createCell(9).setCellValue(sjtime);
          String status = info.getIfqy();
          if(null != status)
        	  row.createCell(10).setCellValue(status);
          String remarks = info.getRemarks();
          if(null != remarks)
          	row.createCell(11).setCellValue(remarks);
      }    
        ExcelUtils.resizeWidth(wb,row,sheet);
		return wb;
	}

	public void findByJCDNO(String JcdNo, String gcNo) {
		
	}

	public List<DmcjJcPointDto> selectJcPointsByJcdno(List<Map<String,String>> list){
		return jcPointMapper.selectJcPointsByJcdno(list);
	}
	
	public int countPointByJcdno(String jcdno){
		return jcPointMapper.countPointByJcdno(jcdno);
	}
	
	/**
	 *  根据项目编号获取全部检测点信息
	 * @param pid
	 * @return
	 */
	public List<JcPoint> getJcPointsByPid(String pid) {
		List<JcPoint> list = null;
		Map<String,Object> map = new HashMap<String,Object>(); 
		map.put("GCBH", pid);
		list = jcPointMapper.selectByMap(map);
		return list;
	}
	
	
	//--------一wpg:以下代码 新添加---------------------------------------------	
		
		/***
		 * @author Administrator wpg
	     * 2016-12-13
		 * 作用：生成excel模版 返回给controller
		 */
	    @Override
		public HSSFWorkbook generateExcelTemplate() {
			
			//列标题
			String[] header = { "*点位编号","*区间", "路线", 
					"*点位类型","*环号/位置","*里程","初始高程（米）",
					"*设计时间","*控制值上限","*控制值下限","*报警值上限",
					"*报警值下限","备注"};
			
			//sheet页名称
			String sheetName = "监测点模版";
			//生成excel模版
			HSSFWorkbook wb = ExcelUtils.excelSheet(header, sheetName);
			HSSFSheet sheet = wb.getSheetAt(0);
	        HSSFRow row = sheet.getRow(0);
	        
	        ExcelUtils.resizeWidth(wb,row,sheet);
			return wb;
		}
	  
		
	    /*** 
	     * @author Administrator wpg
	     * 2016-12-15
		 * 作用：把 Excel表格数据集合 转成 监测点 对象  并保存数据库
		 * 
		 * 调用 调用 this.insert(JcPoint) 保存 数据库;
		 * 调用 MosaicRing(String ring,String jcdbh) 把所有 保存失败的 监测点编号 拼接;
		 * 调用calculationPrompt(su,ex,total) 计算 操作 结果 提示;
		 * .trim() 去掉前后  空格
		 * 
		 * @param ring  保存失败的 监测点编号 拼接
		 * @param su 保存条数
		 * @param ex 点号重复条数（数据库已存在点号）
		 * @param total 总条数    
		 * @return
		 */
	    int su;
		int ex;
		int total;
		@Override
		public String transformationBingPreservation(List<List<Map<String, String>>> list,String gcbh) throws Exception {
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
							//监测点  对象
							JcPoint JcPoint = new JcPoint();
							if(StringUtils.isBlank(rowMap.get("点位编号").trim())){
								continue ; //“点位编号”为空；跳过当次循环；
							}
							if(StringUtils.isNotBlank(rowMap.get("区间")) ){
								
								try {
									//通过 区间 名称   查询   区间 id
									String sectionId = proRProjectSectionService.getSectionBySectionName(rowMap.get("区间").trim());
									
									if(StringUtils.isNotBlank(sectionId)){
										String lineId ="";
										if(StringUtils.isNotBlank(rowMap.get("路线"))){
											//通过 区间 id、路线名称 查询   路线 id
										     lineId =	proRSectionLineMapper.getLineBySectionIdLineName(sectionId,rowMap.get("路线").trim());
										     if(StringUtils.isNotBlank(lineId)){
										    	//路线 id
											    JcPoint.setXlbh(lineId);
										     }
										}
										
									   /* if(StringUtils.isNotBlank(lineId)){*/
									    	//生成 UUID
											JcPoint.setId(CommUtils.getUUID());
											//工程编号 
											JcPoint.setGcbh(gcbh);
											JcPoint.setJcdbh(rowMap.get("点位编号"));
											//区间 id
									    	JcPoint.setQjbh(sectionId);
									    	//路线 id
										   /* JcPoint.setXlbh(lineId);*/
									    	JcPoint.setSjType(rowMap.get("点位类型"));
											JcPoint.setWeizhi(rowMap.get("环号/位置"));
											JcPoint.setLicheng(rowMap.get("里程"));
											if(""!=rowMap.get("初始高程（米）") && null != rowMap.get("初始高程（米）")){
												JcPoint.setCsgc(Float.parseFloat(rowMap.get("初始高程（米）")));
											}
											JcPoint.setSjTimeType(rowMap.get("设计时间"));
											JcPoint.setMaxControl(rowMap.get("控制值上限"));
											JcPoint.setMinControl(rowMap.get("控制值下限"));
											JcPoint.setMaxAlarm(rowMap.get("报警值上限"));
											JcPoint.setMinAlarm(rowMap.get("报警值下限"));
											JcPoint.setRemarks(rowMap.get("备注"));
											
											JcPoint info = new JcPoint();
											info.setGcbh(JcPoint.getGcbh());
											info.setJcdbh(JcPoint.getJcdbh());
											
											//查询 该点号 是否已存在  ==0 为不存在
											if ( 0 == this.selectCount(info )){
												//保存  监测点  对象
												this.insert(JcPoint);
												su++;  //计算 保存条数
											}else{
												//监测点已存在:覆盖；
												JcPoint info1 = null;
												info1 = this.selectOne(info);
												if(info1 !=null && StringUtils.isNotBlank(info1.getId())){
													JcPoint.setId(info1.getId());
													this.updateById(JcPoint);
													su++;  //计算 保存条数
												}
												//监测点已存在：拼接保存失败 提示，把所有 保存失败的 监测点编号 拼接
												/*ring = MosaicRing(ring,rowMap.get("点位编号"));*/
											}
											
									   /* }else{
									    	// 路线 id为空：拼接保存失败 提示，把所有 保存失败的 监测点编号 拼接
											ring = MosaicRing(ring,rowMap.get("点位编号"));
									    }*/
										
									}else{
										//区间 id为空：拼接保存失败 提示，把所有 保存失败的 监测点编号 拼接
										ring = MosaicRing(ring,rowMap.get("点位编号"));
									}
									
								} catch (Exception e) {
								    e.printStackTrace();
								    //查询区间 id，或查询路线 id 报错：拼接保存失败 提示，把所有 保存失败的 监测点编号 拼接
									ring = MosaicRing(ring,rowMap.get("点位编号"));
								}
								
					    	}else{
					    		//区间名 或 路线名 为空：拼接保存失败 提示，把所有 保存失败的 监测点编号 拼接
								ring = MosaicRing(ring,rowMap.get("点位编号"));
					    	}
							
						}
						
					}
				    
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			//返回  拼接 提示 文 
			return calculationPrompt(ring);
		}
		/***
		 * 作用：出错 或 不能为空的 属性  为空时  拼接保存失败 提示，把所有 保存失败的 监测点编号 拼接
		 * @param ring  提示文
		 * @param jcdbh 监测点编号
		 * @return
		 */
		private String MosaicRing(String ring,String jcdbh){
			ex++;  //计算 点号 已存在 条数
			if(StringUtils.isNotBlank(ring)){
				ring =ring+"、"+"“"+jcdbh+"”";
			}else{
				ring = "“"+jcdbh+"”";
			}
			return ring;
		}
		
		/***
		 * @author Administrator wpg
	     * 2016-12-15
		 * 作用：计算 操作 结果 提示  String ring
		 * 
		 * @param su 保存条数
		 * @param ex 点号重复条数（数据库已存在点号）
		 * @param total 总条数
		 * @return
		 */
		private String calculationPrompt(String ring){
			
			if(su > 0){
				String SuccessRing =String.valueOf("总操作 "+total+" 条信息！");
				
				SuccessRing = String.valueOf(SuccessRing+"成功保存 "+su+" 条信息！ ");
				
				if(ex > 0){
					SuccessRing = String.valueOf(SuccessRing+"监测点编号为"+ring+"的 "+ex+" 条信息保存失败！");
				}
				return SuccessRing;
			}else{
				return null;
			}
		}
		
		
}
