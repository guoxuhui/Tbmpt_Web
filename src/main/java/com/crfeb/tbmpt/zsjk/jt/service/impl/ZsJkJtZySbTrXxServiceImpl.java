package com.crfeb.tbmpt.zsjk.jt.service.impl;

import java.util.List;
import java.util.Map;
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
import com.crfeb.tbmpt.zsjk.jt.model.dto.ZsJkJtZySbTrXxDto;
import com.crfeb.tbmpt.zsjk.jt.mapper.ZsJkJtZySbTrXxMapper;
import com.crfeb.tbmpt.zsjk.jt.model.ZsJkJtZySbTrXx;
import com.crfeb.tbmpt.zsjk.jt.service.ZsJkJtZySbTrXxService;
/**
 * <p>集团角度 主要设备投入情况信息Service实现类</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：集团角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@Service
public class ZsJkJtZySbTrXxServiceImpl extends CommonServiceImpl<ZsJkJtZySbTrXxMapper, ZsJkJtZySbTrXx> implements ZsJkJtZySbTrXxService{

    @Autowired
    private ZsJkJtZySbTrXxMapper zsJkJtZySbTrXxMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<ZsJkJtZySbTrXx> page = new Page<ZsJkJtZySbTrXx>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<ZsJkJtZySbTrXx> list = zsJkJtZySbTrXxMapper.selectZsJkJtZySbTrXxList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(ZsJkJtZySbTrXxDto zsJkJtZySbTrXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkJtZySbTrXx zsJkJtZySbTrXx = new ZsJkJtZySbTrXx();
        BeanUtils.copyProperties(zsJkJtZySbTrXxDto, zsJkJtZySbTrXx);
    	  zsJkJtZySbTrXxMapper.insert(zsJkJtZySbTrXx);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  zsJkJtZySbTrXxMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(ZsJkJtZySbTrXxDto zsJkJtZySbTrXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkJtZySbTrXx zsJkJtZySbTrXx = this.zsJkJtZySbTrXxMapper.selectById(zsJkJtZySbTrXxDto.getId());
		  zsJkJtZySbTrXxMapper.updateById(zsJkJtZySbTrXx);
    	  return errorMessage;
    }

	   @Override
	   public ZsJkJtZySbTrXx findById(String id) throws Exception {
		   return zsJkJtZySbTrXxMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(ZsJkJtZySbTrXxDto zsJkJtZySbTrXxDto, String[] clumNames) throws Exception {
		   String id = zsJkJtZySbTrXxDto.getId();
		   Map map = BeanUtils.toMap(zsJkJtZySbTrXxDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<ZsJkJtZySbTrXx> lists = zsJkJtZySbTrXxMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (ZsJkJtZySbTrXx zsJkJtZySbTrXx2 : lists) {
				   String newId = zsJkJtZySbTrXx2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

	@Override
	public Map<String, HashMap<String, String>> getZysbtr() throws Exception {
		
		List<ZsJkJtZySbTrXx> list = this.zsJkJtZySbTrXxMapper.selectList(null);
		Map<String, HashMap<String, String>> resultMap = new HashMap<String,HashMap<String,String>>();
		if(null != list && list.size()>0){
			for(int i = 0;i<list.size();i++){
				HashMap<String, String> dataMap = new HashMap<String,String>();
				dataMap.put("zrSl", list.get(i).getZrSl());
				dataMap.put("zcSl", list.get(i).getZcSl());
				dataMap.put("jxSl", list.get(i).getJxSl());
				dataMap.put("yxSl", list.get(i).getYxSl());
				dataMap.put("ysSl", list.get(i).getYsSl());
				resultMap.put(list.get(i).getSbLb(), dataMap);
			}
		}
		return resultMap;
	}

	@Override
	public String updateSbTrXx(ZsJkJtZySbTrXx zsJkJtZySbTrXx) throws Exception {
		  String errorMessage = "";
	      ZsJkJtZySbTrXx sbTrXx = this.zsJkJtZySbTrXxMapper.selectById(zsJkJtZySbTrXx.getId());
		  if(sbTrXx!=null && !sbTrXx.getId().isEmpty()){
			  zsJkJtZySbTrXxMapper.updateById(zsJkJtZySbTrXx);
		  }else{
			  errorMessage = "数据已过期，编辑失败！";
		  }
	      
    	  return errorMessage;
	}
	/**     
     * 生成 Excel导入 模版
     * @param zsJkJtZySbTrXx 要修改的实体
     */
	@Override
	public HSSFWorkbook generateExcelTemplate() {
		//列标题
		String[] header = { "*设备类别","租入数量","租出数量","检修数量","运行数量","运输数量"};
		//sheet页名称
		String sheetName = "设备投入情况信息";
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
		total=0;
		try {
				
			if(null!=list && !list.isEmpty()){
				
				for(List<Map<String, String>> ListP : list){
					//计算 总条数
					total +=  ListP.size();
					for(Map<String, String> rowMap : ListP){
						//  对象
						ZsJkJtZySbTrXx zsJkJtZySbTrXx = new ZsJkJtZySbTrXx();
						zsJkJtZySbTrXx.setId(CommUtils.getUUID());
						zsJkJtZySbTrXx.setSbLb(rowMap.get("设备类别"));
						zsJkJtZySbTrXx.setZrSl(rowMap.get("租入数量"));
						zsJkJtZySbTrXx.setZcSl(rowMap.get("租出数量"));
						zsJkJtZySbTrXx.setJxSl(rowMap.get("检修数量"));
						zsJkJtZySbTrXx.setYxSl(rowMap.get("运行数量"));
						zsJkJtZySbTrXx.setYsSl(rowMap.get("运输数量"));
						this.insert(zsJkJtZySbTrXx);
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