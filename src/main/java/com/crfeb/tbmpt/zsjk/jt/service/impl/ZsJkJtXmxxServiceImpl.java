package com.crfeb.tbmpt.zsjk.jt.service.impl;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.crfeb.tbmpt.commons.utils.CommUtils;
import com.crfeb.tbmpt.commons.utils.ExcelUtils;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtXmxxDto;
import com.crfeb.tbmpt.zsjk.jt.mapper.ZsJkJtXmxxMapper;
import com.crfeb.tbmpt.zsjk.jt.model.ZsJkJtXmxx;
import com.crfeb.tbmpt.zsjk.jt.service.ZsJkJtXmxxService;
/**
 * <p>展示接口集团角度项目信息Service实现类</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：集团角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@Service
public class ZsJkJtXmxxServiceImpl extends CommonServiceImpl<ZsJkJtXmxxMapper, ZsJkJtXmxx> implements ZsJkJtXmxxService{

    @Autowired
    private ZsJkJtXmxxMapper zsJkJtXmxxMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<ZsJkJtXmxx> page = new Page<ZsJkJtXmxx>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<ZsJkJtXmxx> list = zsJkJtXmxxMapper.selectZsJkJtXmxxList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(ZsJkJtXmxxDto zsJkJtXmxxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkJtXmxx zsJkJtXmxx = new ZsJkJtXmxx();
        BeanUtils.copyProperties(zsJkJtXmxxDto, zsJkJtXmxx);
    	  zsJkJtXmxxMapper.insert(zsJkJtXmxx);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  zsJkJtXmxxMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(ZsJkJtXmxxDto zsJkJtXmxxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkJtXmxx zsJkJtXmxx = this.zsJkJtXmxxMapper.selectById(zsJkJtXmxxDto.getId());
		  zsJkJtXmxxMapper.updateById(zsJkJtXmxx);
    	  return errorMessage;
    }

	   @Override
	   public ZsJkJtXmxx findById(String id) throws Exception {
		   return zsJkJtXmxxMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(ZsJkJtXmxxDto zsJkJtXmxxDto, String[] clumNames) throws Exception {
		   String id = zsJkJtXmxxDto.getId();
		   Map map = BeanUtils.toMap(zsJkJtXmxxDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<ZsJkJtXmxx> lists = zsJkJtXmxxMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (ZsJkJtXmxx zsJkJtXmxx2 : lists) {
				   String newId = zsJkJtXmxx2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

	@Override
	public List<ZsJkJtXmxxDto> getXMXXList() throws Exception {
		List<ZsJkJtXmxxDto> dtoList = new ArrayList<ZsJkJtXmxxDto>();
		List<ZsJkJtXmxx> list = new ArrayList<ZsJkJtXmxx>();
		list = this.zsJkJtXmxxMapper.selectList(null);
		if(null != list && list.size()>0){
			ZsJkJtXmxxDto dto = null;
			for(int i = 0;i < list.size();i++){
				dto = new ZsJkJtXmxxDto();
				BeanUtils.copy(list.get(i), dto);
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

	@Override
	public String updateXmxx(ZsJkJtXmxx zsJkJtXmxx) throws Exception {
		 String errorMessage = "";
		 ZsJkJtXmxx xmXx = this.zsJkJtXmxxMapper.selectById(zsJkJtXmxx.getId());
		 if(xmXx!=null && !xmXx.getId().isEmpty()){
			 zsJkJtXmxxMapper.updateById(zsJkJtXmxx);
		 }else{
			 errorMessage = "数据已过期，编辑失败！";
		 }
   	     return errorMessage;
		
	}

	 /**     
     * 生成 Excel导入 模版
     * @param zsJkJtXmxx 要修改的实体
     */
	@Override
	public HSSFWorkbook generateExcelTemplate() {
		//列标题
		String[] header = { "*项目名称","*项目Id","负责人","中标价格",
				"项目产值","当前项目完成产值","城市地区","坐标经度","坐标纬度",
				"总工期天数","开工日期","预计完成日期","地铁线路","施工标段",
				"总环数","当前掘进总环数"};
		//sheet页名称
		String sheetName = "集团角度项目信息";
		//生成excel模版
		HSSFWorkbook wb = ExcelUtils.excelSheet(header, sheetName);
		HSSFSheet sheet = wb.getSheetAt(0);
        HSSFRow row = sheet.getRow(0);
        
        ExcelUtils.resizeWidth(wb,row,sheet);
		return wb;
	}

	/***
	 * 全局变量
	 * 记录操作结果
	 */
	private int su;
	private int ex;
	private int total;
	/**
     * 把 Excel表格数据集合 转成  对象  并  保存
     * @param list
     * @return 操作结果提示
     */
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
						//  对象
						ZsJkJtXmxx zsJkJtXmxx = new ZsJkJtXmxx();
						zsJkJtXmxx.setId(CommUtils.getUUID());
						zsJkJtXmxx.setXmMc(rowMap.get("项目名称"));
						zsJkJtXmxx.setXmId(rowMap.get("项目Id"));
						zsJkJtXmxx.setFzr(rowMap.get("负责人"));
						zsJkJtXmxx.setZbjg(rowMap.get("中标价格"));
						zsJkJtXmxx.setXmcz(rowMap.get("项目产值"));
						zsJkJtXmxx.setDqxmwcz(rowMap.get("当前项目完成产值"));
						zsJkJtXmxx.setCsdq(rowMap.get("城市地区"));
						zsJkJtXmxx.setZbjd(rowMap.get("坐标经度"));
						zsJkJtXmxx.setZbwd(rowMap.get("坐标纬度"));
						zsJkJtXmxx.setZts(rowMap.get("总工期天数"));
						zsJkJtXmxx.setKgrq(rowMap.get("开工日期"));
						zsJkJtXmxx.setYjwcrq(rowMap.get("预计完成日期"));
						zsJkJtXmxx.setDtxl(rowMap.get("地铁线路"));
						zsJkJtXmxx.setSgbd(rowMap.get("施工标段"));
						zsJkJtXmxx.setZhs(rowMap.get("总环数"));
						zsJkJtXmxx.setDqjzjhs(rowMap.get("当前掘进总环数"));
						this.insert(zsJkJtXmxx);
						su++;
						
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