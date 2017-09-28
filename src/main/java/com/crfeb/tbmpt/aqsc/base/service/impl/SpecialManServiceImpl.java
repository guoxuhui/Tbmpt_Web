package com.crfeb.tbmpt.aqsc.base.service.impl;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.ExcelUtils;
import com.crfeb.tbmpt.commons.utils.IdCard;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.project.mapper.ProProjectinfoMapper;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.base.BaseService;
import com.crfeb.tbmpt.aqsc.base.model.dto.SpecialManDto;
import com.crfeb.tbmpt.aqsc.base.mapper.SpecialManMapper;
import com.crfeb.tbmpt.aqsc.base.model.SpecialMan;
import com.crfeb.tbmpt.aqsc.base.service.SpecialManService;
/**
 * <p>特种人员管理Service实现类</p>
 * <p>系统：安全生产管理</p>
 * <p>模块：基础信息</p>
 * <p>日期：2017-02-20</p>
 * @version 1.0
 * @author wangbinbin
 */
@Service
public class SpecialManServiceImpl extends CommonServiceImpl<SpecialManMapper, SpecialMan> implements SpecialManService{

    @Autowired
    private SpecialManMapper specialManMapper;
    @Autowired
    private ProProjectinfoMapper projectinfoMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<SpecialManDto> page = new Page<SpecialManDto>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
//       List<SpecialMan> list = specialManMapper.selectSpecialManList(page,condition);
       List<SpecialManDto> list = specialManMapper.selectSpecialManDtoList(page,condition,pageInfo.getSort(),pageInfo.getOrder());
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(SpecialManDto specialManDto,User user) throws Exception {
    	  String errorMessage = "";
        SpecialMan specialMan = new SpecialMan();
        BeanUtils.copyProperties(specialManDto, specialMan);
    	  BaseService.operatorMessage(specialMan, null, user);
    	  List<ProProjectinfo> projects = projectinfoMapper.selectByUserId(user.getId());
	      	if(projects!=null&&projects.size()==1){
	      		ProProjectinfo pp  =  projects.get(0);
	      		specialMan.setXmBh(pp.getId());
	      		specialMan.setXmName(pp.getProName());
	      	}
    	  specialManMapper.insert(specialMan);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  specialManMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(SpecialManDto specialManDto,User user) throws Exception {
    	  String errorMessage = "";
        SpecialMan specialMan = this.specialManMapper.selectById(specialManDto.getId());
        errorMessage = BaseService.operatorMessage(specialMan, specialManDto, user);
        BeanUtils.copyProperties(specialManDto, specialMan);
        List<ProProjectinfo> projects = projectinfoMapper.selectByUserId(user.getId());
      	if(projects!=null&&projects.size()==1){
      		ProProjectinfo pp  =  projects.get(0);
      		specialMan.setXmBh(pp.getId());
      		specialMan.setXmName(pp.getProName());
      	}
		  specialManMapper.updateById(specialMan);
    	  return errorMessage;
    }

	   @Override
	   public SpecialMan findById(String id) throws Exception {
		   return specialManMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(SpecialManDto specialManDto, String[] clumNames) throws Exception {
		   String id = specialManDto.getId();
		   Map map = BeanUtils.toMap(specialManDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<SpecialMan> lists = specialManMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (SpecialMan specialMan2 : lists) {
				   String newId = specialMan2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

    @Override
	public void selectDataGrid(PageInfo pageInfo, User user) throws Exception {
		Map<String, Object> condition = pageInfo.getCondition();
		if(condition==null){
			condition = new HashMap<>();
		}
		condition.put("sqlPurview", this.sqlPurview(null,"xmBh",user));
		pageInfo.setCondition(condition);
		this.selectDataGrid(pageInfo);
		
	}
	
	public String sqlPurview(String tableAnotherName,String gcBhTableClum,User user){
    	String sql = " and "+(StringUtils.isNotBlank(tableAnotherName)?tableAnotherName:"t")+"."+(StringUtils.isNotBlank(gcBhTableClum)?gcBhTableClum:"gc_Bh")+" in (";
    	List<ProProjectinfo> projects = projectinfoMapper.selectByUserId(user.getId());
		for (int i = 0; i < projects.size(); i++) {
			if(i!=0)sql+=",";
			sql+="'"+projects.get(i).getId()+"'";
		}
    	sql+=")";
    	return sql;
    }

	
	
	/**     
     * 生成 Excel导入 模版
     */
	@Override
	public HSSFWorkbook generateExcelTemplate() {
		//列标题
		String[] header = { "*姓名","*工种","身份证号","性别","人员类别",
				"发证机关","证件号码","取证日期","复审日期","有效日期",
				"进场日期","离场日期","备注"};
		
		//sheet页名称
		String sheetName = "特种人员信息";
		//生成excel模版
		HSSFWorkbook wb = ExcelUtils.excelSheet(header, sheetName);
		HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row = sheet.getRow(0);
        
        ExcelUtils.resizeWidth(wb,row,sheet);
		return wb;
	}
	
	/*** 
     * @author  wl_wpg
     * 2017-05-24
	 * 作用：把 Excel表格数据集合 转成 对象  并保存数据库
	 * 
	 * 调用 调用 this.save() 保存 数据库;
	 * 调用 MosaicRing() 把所有 保存失败的 编号 拼接;
	 * 调用calculationPrompt(su,ex,total) 计算 操作 结果 提示;
	 * .trim() 去掉前后  空格
	 * 
	 * @param ring  保存失败的 编号 拼接
	 * @param su 保存条数
	 * @param ex 点号重复条数（数据库已存在）
	 * @param total 总条数    
	 * @return
	 */
	private int su;
	private int ex;
	private int total;
	@Override
	public String transformationBingPreservation(List<List<Map<String, String>>> list,User user) {
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
						String errmessage = "";
						SpecialManDto specialManDto = new SpecialManDto();
						
						specialManDto.setName(rowMap.get("姓名"));
						specialManDto.setWorkType(rowMap.get("工种"));
						//姓名 和 工种 不可为空
						if(StringUtils.isNotBlank(specialManDto.getName()) ){
							if(StringUtils.isNotBlank(specialManDto.getWorkType())){
								
								specialManDto.setCardNo(rowMap.get("身份证号"));
								if(StringUtils.isNotBlank(specialManDto.getCardNo())){
									//判断 “身份证号” 是否合法，返回 null 为 合法；
									String errorInfo = IdCard.IDCardValidate(specialManDto.getCardNo().trim());
									if(StringUtils.isNotBlank(errorInfo)){
										ring = MosaicRing(ring,"姓名为：“"+rowMap.get("姓名")+"”的，“身份证号”不合法!");
										continue;
									}
								}
								
								specialManDto.setSex(rowMap.get("性别"));
								specialManDto.setRenyuanType(rowMap.get("人员类别"));
								specialManDto.setFazhengjiguan(rowMap.get("发证机关"));
								specialManDto.setZhengjianhaoma(rowMap.get("证件号码"));
								specialManDto.setQuzhengriqi(rowMap.get("取证日期"));
								specialManDto.setFushengriqi(rowMap.get("复审日期"));
								specialManDto.setYouxiaoqi(rowMap.get("有效日期"));
								specialManDto.setJinchangriqi(rowMap.get("进场日期"));
								specialManDto.setLichangriqi(rowMap.get("离场日期"));
								specialManDto.setRemark(rowMap.get("备注"));
								
								//通过 “姓名” 和 “工种”判断 该数据 是否已存在
						    	errmessage = this.checkClumIfexits(specialManDto,new String[]{"name","workType"});
						    	if(errmessage==null){
						    		this.save(specialManDto,user);
						    		su++;
						    	}
						    	else{
						    		//“姓名” 和 “工种” 相同的信息  已存在， 拼接保存失败 提示
						    		ring = MosaicRing(ring,"姓名为：“"+rowMap.get("姓名")+"”、工种为：“"+rowMap.get("工种")+"”的信息已存在!");
						    	}
							}else{
					    		//“工种”为空， 拼接保存失败 提示
					    		ring = MosaicRing(ring,"姓名为：“"+rowMap.get("姓名")+"”的，“工种”信息为空!");
					    	}
						}else{
				    		//“姓名”为空， 拼接保存失败 提示
				    		ring = MosaicRing(ring,"工种为：“"+rowMap.get("工种")+"”的，“姓名”为空!");
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
	 * 作用：出错 或 不能为空的 属性  为空时  拼接保存失败 提示，把所有 保存失败的 编号 拼接
	 * @param ring  提示文
	 * @param bh 编号
	 * @return
	 */
	private String MosaicRing(String ring,String bh){
		ex++;  //计算 点号 已存在 条数
		if(StringUtils.isNotBlank(ring)){
			ring =ring+"、<br/>"+"“"+bh+"”";
		}else{
			ring = "“"+bh+"”";
		}
		return ring;
	}
	
    /***
     * 计算操作结果提示文
     * @param ring
     * @return
     */
    private String calculationPrompt(String ring){
		
		if(su > 0){
			String SuccessRing =String.valueOf("总操作 "+total+" 条信息！<br/>");
			
			SuccessRing = String.valueOf(SuccessRing+"成功保存 "+su+" 条信息！<br/> ");
			
			if(ex > 0){
				SuccessRing = String.valueOf(SuccessRing+"导入失败信息:<br/>"+ring+"，<br/>以上"+ex+" 条信息保存失败！");
			}
			return SuccessRing;
		}else{
			return null;
		}
	}
	
	
	
}