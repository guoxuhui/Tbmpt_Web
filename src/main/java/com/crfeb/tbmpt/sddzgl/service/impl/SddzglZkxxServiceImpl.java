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
import com.crfeb.tbmpt.sddzgl.mapper.SddzglZkxxMapper;
import com.crfeb.tbmpt.sddzgl.model.SddzglZkxx;
import com.crfeb.tbmpt.sddzgl.model.dto.SddzglZkxxDto;
import com.crfeb.tbmpt.sddzgl.service.ISddzglZkxxService;
import com.crfeb.tbmpt.sys.mapper.OrgzMapper;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.User;

/**
 * <p>钻孔信息 业务实现类 ServiceImpl</p>
 * <p>模块：隧道地质管理</p>
 * <p>日期：2017-03-28</p>
 * @version 1.0
 * @author wl_wpg
 */
@Service
public class SddzglZkxxServiceImpl extends CommonServiceImpl<SddzglZkxxMapper, SddzglZkxx> implements ISddzglZkxxService {

	@Autowired 
	private SddzglZkxxMapper sddzglZkxxMapper;
	
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
		Page<SddzglZkxxDto> page = new Page<SddzglZkxxDto>(pageInfo.getNowpage(), pageInfo.getSize());
        Map<String, Object> condition = pageInfo.getCondition();
        List<SddzglZkxxDto> list = sddzglZkxxMapper.selectDtoList(page,condition,pageInfo.getSort(), pageInfo.getOrder());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
	}

	 /**
     * 查询集合
     */
	@Override
	public List<SddzglZkxxDto> selectList() {
		
		return sddzglZkxxMapper.selectList();
	}

	@Override
    public String save(SddzglZkxxDto sddzglZkxxDto,User user) throws Exception {
    	  String errorMessage = "";
          SddzglZkxx sddzglZkxx = new SddzglZkxx();
          BeanUtils.copyProperties(sddzglZkxxDto, sddzglZkxx);
          if(StringUtils.isNotBlank(sddzglZkxx.getId())
				&& StringUtils.isNotBlank(sddzglZkxx.getXmId())
				&& StringUtils.isNotBlank(sddzglZkxx.getQjId())
				&& StringUtils.isNotBlank(sddzglZkxx.getXlId())){
        	  sddzglZkxxMapper.insert(sddzglZkxx);
        	  return errorMessage;
		  }
          return "保存失败！";
    }


	@Override
	public String update(SddzglZkxx sddzglZkxx) throws Exception {
		  String errorMessage = "";
	      SddzglZkxx Zkxx = this.sddzglZkxxMapper.selectById(sddzglZkxx.getId());
		  if(Zkxx!=null && !Zkxx.getId().isEmpty()){
			  if(StringUtils.isNotBlank(sddzglZkxx.getId())
					&& StringUtils.isNotBlank(sddzglZkxx.getXmId())
					&& StringUtils.isNotBlank(sddzglZkxx.getQjId())
					&& StringUtils.isNotBlank(sddzglZkxx.getXlId())){
				  sddzglZkxxMapper.updateById(sddzglZkxx);
	        	  return errorMessage;
			  }
		  }else{
			  errorMessage = "数据已过期，编辑失败！";
		  }
	      
		  return "保存失败！";
	}
	/**     
     * 生成 Excel导入 模版
     * @param sddzglZkxx 要修改的实体
     */
	@Override
	public HSSFWorkbook generateExcelTemplate() {
		//列标题
		String[] header = { "*项目Id","*区间Id","*线路Id","*钻孔编号","钻孔类型",
				"钻孔施工单位","钻孔位置(X)","钻孔位置(Y)","对应环号","钻孔里程","备注"};
		
		//sheet页名称
		String sheetName = "隧道钻孔信息";
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
						SddzglZkxx sddzglZkxx = new SddzglZkxx();
						sddzglZkxx.setId(CommUtils.getUUID());
						sddzglZkxx.setZkbh(rowMap.get("钻孔编号"));
						sddzglZkxx.setZksgdw(rowMap.get("钻孔施工单位"));
						sddzglZkxx.setZklx(rowMap.get("钻孔类型"));
						sddzglZkxx.setXmId(rowMap.get("项目Id"));
						sddzglZkxx.setQjId(rowMap.get("区间Id"));
						sddzglZkxx.setXlId(rowMap.get("线路Id"));
						sddzglZkxx.setZkwzX(rowMap.get("钻孔位置(X)"));
						sddzglZkxx.setZkwzY(rowMap.get("钻孔位置(Y)"));
						sddzglZkxx.setDyhh(rowMap.get("对应环号"));
						sddzglZkxx.setZklc(rowMap.get("钻孔里程"));
						sddzglZkxx.setBz(rowMap.get("备注"));
						if(StringUtils.isNotBlank(sddzglZkxx.getId())
							&& StringUtils.isNotBlank(sddzglZkxx.getXmId())
							&& StringUtils.isNotBlank(sddzglZkxx.getQjId())
							&& StringUtils.isNotBlank(sddzglZkxx.getXlId())){
							this.insert(sddzglZkxx);
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

}
