package com.crfeb.tbmpt.zsjk.report.service.impl;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.zsjk.report.mapper.ZsJkReportDgjkycztMapper;
import com.crfeb.tbmpt.zsjk.report.model.ZsJkReportDgjkyczt;
import com.crfeb.tbmpt.zsjk.report.service.ZsJkReportDgjkycztService;


/**
 * <p>盾构监控异常状态  ServiceImpl </p>
 * <p>系统：展示界面接口</p>
 * <p>日期：2017-03-07</p>
 * @version 1.0
 * @author wl_wpg
 */
@Service
public class ZsJkReportDgjkycztServiceImpl extends CommonServiceImpl<ZsJkReportDgjkycztMapper, ZsJkReportDgjkyczt>  implements ZsJkReportDgjkycztService {

	@Autowired
	private ZsJkReportDgjkycztMapper zsJkReportDgjkycztMapper;
	
	/**
     * 获取 盾构监控异常状态集合
     * @param pageInfo 分页对象
     * @param typeList 类型集合
     * @return 返回查询 分页对象
     */
	@Override
	public void inquireDgjkycztDataPage(PageInfo pageInfo,List<String> typeList) {
		if(null != typeList && typeList.size() !=0 ){
			for(int i=0;i<typeList.size();i++){
				String typeId = typeList.get(i);
				if(typeId.equals("0")){
					pageInfo.getCondition().put("tjsj", typeId);
				}
				else if(typeId.equals("1")){
					pageInfo.getCondition().put("sjctl", typeId);
				}
	            else if(typeId.equals("2")){
	            	pageInfo.getCondition().put("zjl", typeId);
				}
	            else if(typeId.equals("3") ){
	            	pageInfo.getCondition().put("pjjjsd", typeId);
				}
	            else if(typeId.equals("4")){
	            	pageInfo.getCondition().put("tysjtj", typeId);
				}
	        }
   		}
		
        Page<ZsJkReportDgjkyczt> page = new Page<ZsJkReportDgjkyczt>(pageInfo.getNowpage(), pageInfo.getSize());
        List<ZsJkReportDgjkyczt> list = zsJkReportDgjkycztMapper.selectDgjkycztList(page,pageInfo.getCondition(), pageInfo.getSort(), pageInfo.getOrder());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
       
    }

}
