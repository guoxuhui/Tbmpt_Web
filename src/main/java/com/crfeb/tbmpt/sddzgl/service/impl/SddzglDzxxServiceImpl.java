package com.crfeb.tbmpt.sddzgl.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.CommUtils;
import com.crfeb.tbmpt.commons.utils.ExcelUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.sddzgl.mapper.SddzglDzxxMapper;
import com.crfeb.tbmpt.sddzgl.model.SddzglDzxx;
import com.crfeb.tbmpt.sddzgl.model.dto.SddzglDzxxDto;
import com.crfeb.tbmpt.sddzgl.service.ISddzglDzxxService;
import com.crfeb.tbmpt.sys.mapper.OrgzMapper;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.User;
/**
 * <p>地质信息 业务实现类 ServiceImpl</p>
 * <p>模块：隧道地质管理</p>
 * <p>日期：2017-03-28</p>
 * @version 1.0
 * @author wl_wpg
 */
@Service
public class SddzglDzxxServiceImpl extends CommonServiceImpl<SddzglDzxxMapper, SddzglDzxx> implements ISddzglDzxxService {

	@Autowired 
	private SddzglDzxxMapper sddzglDzxxMapper;

	@Autowired
    private OrgzMapper orgzMapper;
	
	/**
     * 分页查询 easyUi列表
     * @param pageInfo 分页公用类
     */
	@Override
	public void selectDataGrid(PageInfo pageInfo,User user) throws Exception {
		Orgz orgz = orgzMapper.selectById(user.getOrgzId());
    	String code = "-1";
    	if(orgz != null){
    		if(orgz.getType() <= 1){
    			code = orgz.getCode();
    		}else{
    			Orgz orgz2 = orgzMapper.selectById(orgz.getPid());
    			code = orgz2.getCode();
    		}
    	}
    	pageInfo.getCondition().put("code", code);
		Page<SddzglDzxxDto> page = new Page<SddzglDzxxDto>(pageInfo.getNowpage(), pageInfo.getSize());
        Map<String, Object> condition = pageInfo.getCondition();
        List<SddzglDzxxDto> list = sddzglDzxxMapper.selectDtoList(page,condition,pageInfo.getSort(), pageInfo.getOrder());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
	}

	@Override
    public String save(SddzglDzxxDto sddzglDzxxDto,User user) throws Exception {
    	  String errorMessage = "";
        SddzglDzxx sddzglDzxx = new SddzglDzxx();
        BeanUtils.copyProperties(sddzglDzxxDto, sddzglDzxx);
        
        //钻孔、岩土名称 不可为空；
        if(StringUtils.isNotBlank(sddzglDzxx.getZkId())
           && StringUtils.isNotBlank(sddzglDzxx.getYtmc())){
        	sddzglDzxxMapper.insert(sddzglDzxx);
        	return errorMessage;
        }
    	  
    	  return "保存失败！";
    }


	@Override
	public String update(SddzglDzxx sddzglDzxx) throws Exception {
		  String errorMessage = "";
		  if(sddzglDzxx ==null){
			  return "保存失败！";
		  }
	      SddzglDzxx dzxx = this.sddzglDzxxMapper.selectById(sddzglDzxx.getId());
		  if(dzxx!=null && !dzxx.getId().isEmpty()){
			  
			//钻孔、岩土名称 不可为空；
	        if(StringUtils.isNotBlank(sddzglDzxx.getZkId())
	           && StringUtils.isNotBlank(sddzglDzxx.getYtmc())){
	        	sddzglDzxxMapper.updateById(sddzglDzxx);
	        	return errorMessage;
	        }  
		  }else{
			  errorMessage = "数据已过期，编辑失败！";
		  }
	      
		  return "保存失败！";
	}
	/**     
     * 生成 Excel导入 模版
     * @param sddzglDzxx 要修改的实体
     */
	@Override
	public HSSFWorkbook generateExcelTemplate() {
		//列标题
		String[] header = { "*所属钻孔Id","*岩土名称","岩土编号","承载力特征值",
				"压缩模量","岩土施工分级","地层埋深","地层描述","备注"};
		
		//sheet页名称
		String sheetName = "隧道地质信息";
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
						SddzglDzxx sddzglDzxx = new SddzglDzxx();
						sddzglDzxx.setId(CommUtils.getUUID());
						sddzglDzxx.setYtmc(rowMap.get("岩土名称"));
						sddzglDzxx.setYtbh(rowMap.get("岩土编号"));
						sddzglDzxx.setZkId(rowMap.get("所属钻孔Id"));
						sddzglDzxx.setCzltzz(rowMap.get("承载力特征值"));
						sddzglDzxx.setYsml(rowMap.get("压缩模量"));
						sddzglDzxx.setYtsgfc(rowMap.get("岩土施工分级"));
						sddzglDzxx.setTcms(rowMap.get("地层埋深"));
						sddzglDzxx.setDcms(rowMap.get("地层描述"));
						sddzglDzxx.setBz(rowMap.get("备注"));
						if(StringUtils.isNotBlank(sddzglDzxx.getId())
						   && StringUtils.isNotBlank(sddzglDzxx.getZkId())
				           && StringUtils.isNotBlank(sddzglDzxx.getYtmc())){
							
							this.insert(sddzglDzxx);
							su++;
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

    @Override
	/**
	 * @author wl_zjh
	 * 查找地层信息
	 */
	public List<SddzglDzxxDto> selectDzxx() {
		List<SddzglDzxxDto> list=sddzglDzxxMapper.listDzxx();
		return list;
	}

}
