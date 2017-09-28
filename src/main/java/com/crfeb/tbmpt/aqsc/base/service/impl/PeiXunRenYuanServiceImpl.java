package com.crfeb.tbmpt.aqsc.base.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.aqsc.base.mapper.PeiXunRenYuanMapper;
import com.crfeb.tbmpt.aqsc.base.model.PeiXunRenYuan;
import com.crfeb.tbmpt.aqsc.base.model.dto.PeiXunRenYuanDto;
import com.crfeb.tbmpt.aqsc.base.service.PeiXunRenYuanService;
import com.crfeb.tbmpt.commons.base.BaseService;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.CommUtils;
import com.crfeb.tbmpt.commons.utils.ExcelUtils;
import com.crfeb.tbmpt.commons.utils.IdCard;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.project.mapper.ProProjectinfoMapper;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.sddzgl.model.SddzglZkxx;
import com.crfeb.tbmpt.sys.model.User;

/**
 * <p>培训人员管理Service实现类</p>
 * <p>系统：安全生产管理</p>
 * <p>模块：基础信息</p>
 * <p>日期：2017-02-20</p>
 * @version 1.0
 * @author wangbinbin
 */
@Service
public class PeiXunRenYuanServiceImpl extends CommonServiceImpl<PeiXunRenYuanMapper, PeiXunRenYuan> implements PeiXunRenYuanService{

    @Autowired
    private PeiXunRenYuanMapper peiXunRenYuanMapper;
    @Autowired
    private ProProjectinfoMapper projectinfoMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<PeiXunRenYuan> page = new Page<PeiXunRenYuan>(pageInfo.getNowpage(), pageInfo.getSize());
//       List<PeiXunRenYuanDto> dtoList = new ArrayList<PeiXunRenYuanDto>();
//       PeiXunRenYuanDto dto = new PeiXunRenYuanDto();
       Map<String, Object> condition = pageInfo.getCondition();
       List<PeiXunRenYuanDto> list = peiXunRenYuanMapper.selectPeiXunRenYuanDtoList(page,condition,pageInfo.getSort(),pageInfo.getOrder());
       if(null != list && list.size()>0){
    	   for(PeiXunRenYuanDto entity :list){
    		   int age = IdCard.getAgeByIdCard(entity.getCardNo());//年龄
    		   entity.setAge(String.valueOf(age));
//    		   dtoList.add(dto);
    	   }
       }
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(PeiXunRenYuanDto peiXunRenYuanDto,User user) throws Exception {
    	  String errorMessage = "";
        PeiXunRenYuan peiXunRenYuan = new PeiXunRenYuan();
        BeanUtils.copyProperties(peiXunRenYuanDto, peiXunRenYuan);
    	  BaseService.operatorMessage(peiXunRenYuan, null, user);
    	  String birthDay = IdCard.getBirthByIdCard(peiXunRenYuan.getCardNo());
    	  peiXunRenYuan.setBirthDay(birthDay.substring(0, 4)+"-"+birthDay.substring(4,6)+"-"+birthDay.substring(6));
    	  List<ProProjectinfo> projects = projectinfoMapper.selectByUserId(user.getId());
	      	if(projects!=null&&projects.size()==1){
	      		ProProjectinfo pp  =  projects.get(0);
	      		peiXunRenYuan.setXmBh(pp.getId());
	    		peiXunRenYuan.setXmName(pp.getProName());
	      	}
    	  peiXunRenYuanMapper.insert(peiXunRenYuan);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  peiXunRenYuanMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(PeiXunRenYuanDto peiXunRenYuanDto,User user) throws Exception {
    	String errorMessage = "";
        PeiXunRenYuan peiXunRenYuan = this.peiXunRenYuanMapper.selectById(peiXunRenYuanDto.getId());
        errorMessage = BaseService.operatorMessage(peiXunRenYuan, peiXunRenYuanDto, user);
        BeanUtils.copyProperties(peiXunRenYuanDto, peiXunRenYuan);
        String birthDay = IdCard.getBirthByIdCard(peiXunRenYuan.getCardNo());
  	    peiXunRenYuan.setBirthDay(birthDay.substring(0, 4)+"-"+birthDay.substring(4,6)+"-"+birthDay.substring(6, 8));
  	  List<ProProjectinfo> projects = projectinfoMapper.selectByUserId(user.getId());
    	if(projects!=null&&projects.size()==1){
    		ProProjectinfo pp  =  projects.get(0);
    		peiXunRenYuan.setXmBh(pp.getId());
    		peiXunRenYuan.setXmName(pp.getProName());
    	}
	    peiXunRenYuanMapper.updateById(peiXunRenYuan);
	    return errorMessage;
    }

	   @Override
	   public PeiXunRenYuan findById(String id) throws Exception {
		   return peiXunRenYuanMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(PeiXunRenYuanDto peiXunRenYuanDto, String[] clumNames) throws Exception {
		   String id = peiXunRenYuanDto.getId();
		   Map map = BeanUtils.toMap(peiXunRenYuanDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<PeiXunRenYuan> lists = peiXunRenYuanMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (PeiXunRenYuan peiXunRenYuan2 : lists) {
				   String newId = peiXunRenYuan2.getId();
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
		String[] header = { "*姓名","*身份证号","联系电话","工种","家庭住址",
				"入职日期","离职日期","培训时间","状态","单位名称","备注"};
		
		//sheet页名称
		String sheetName = "培训人员信息";
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
	 * @param ring  保存失败的 监测点编号 拼接
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
						PeiXunRenYuanDto peiXunRenYuanDto = new PeiXunRenYuanDto();
						peiXunRenYuanDto.setName(rowMap.get("姓名"));
						peiXunRenYuanDto.setCardNo(rowMap.get("身份证号"));
						//姓名 和 身份证号 不可为空
						if(StringUtils.isNotBlank(peiXunRenYuanDto.getName()) ){
							if(StringUtils.isNotBlank(peiXunRenYuanDto.getCardNo())){
								
								//判断 “身份证号” 是否合法，返回 null 为 合法；
								String errorInfo = IdCard.IDCardValidate(peiXunRenYuanDto.getCardNo().trim());
								if(StringUtils.isNotBlank(errorInfo)){
									ring = MosaicRing(ring,rowMap.get("身份证号")+",该“身份证号”不合法!");
									continue;
								}
								peiXunRenYuanDto.setPhone(rowMap.get("联系电话"));
								peiXunRenYuanDto.setGongzhong(rowMap.get("工种"));
								peiXunRenYuanDto.setAdress(rowMap.get("家庭住址"));
								peiXunRenYuanDto.setInDate(rowMap.get("入职日期"));
								peiXunRenYuanDto.setOutDate(rowMap.get("离职日期"));
								peiXunRenYuanDto.setPeixunTime(rowMap.get("培训时间"));
								peiXunRenYuanDto.setState(rowMap.get("状态"));
								peiXunRenYuanDto.setDemptName(rowMap.get("单位名称"));
								peiXunRenYuanDto.setRemark(rowMap.get("备注"));
								
								//通过 “身份证号”判断 该数据 是否已存在
						    	errmessage = this.checkClumIfexits(peiXunRenYuanDto,new String[]{"cardNo"});
						    	if(errmessage==null){
						    		//通过 “身份证号”，获取 该 人员 性别
						    		String sex = IdCard.getGenderByIdCard(peiXunRenYuanDto.getCardNo());//性别
							    	peiXunRenYuanDto.setSex(sex);
							    	//通过 调用“添加单条信息”的方法，获取用户信息，并保存信息
						    		this.save(peiXunRenYuanDto,user);
						    		su++;
						    	}
						    	else{
						    		//“身份证号” 已存在， 拼接保存失败 提示
						    		ring = MosaicRing(ring,rowMap.get("身份证号")+"，“身份证号” 已存在!");
						    	}
							}else{
					    		//“身份证号”为空， 拼接保存失败 提示
					    		ring = MosaicRing(ring,"姓名为：“"+rowMap.get("姓名")+"”的，“身份证号”为空!");
					    	}
						}else{
				    		//“姓名”或“身份证号”为空， 拼接保存失败 提示
				    		ring = MosaicRing(ring,rowMap.get("身份证号")+"的，“姓名”为空!");
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