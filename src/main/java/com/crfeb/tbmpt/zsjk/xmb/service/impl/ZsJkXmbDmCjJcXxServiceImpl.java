package com.crfeb.tbmpt.zsjk.xmb.service.impl;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.commons.utils.UtilMath;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.base.BaseService;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbDmCjJcXxDto;
import com.crfeb.tbmpt.zsjk.xmb.mapper.ZsJkXmbDmCjJcXxMapper;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbDmCjJcXx;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbDmCjJcXxService;
/**
 * <p>项目部角度  地面沉降情况（默认取当前里程的前后50米）Service实现类</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@Service
public class ZsJkXmbDmCjJcXxServiceImpl extends CommonServiceImpl<ZsJkXmbDmCjJcXxMapper, ZsJkXmbDmCjJcXx> implements ZsJkXmbDmCjJcXxService{

    @Autowired
    private ZsJkXmbDmCjJcXxMapper zsJkXmbDmCjJcXxMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<ZsJkXmbDmCjJcXx> page = new Page<ZsJkXmbDmCjJcXx>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<ZsJkXmbDmCjJcXx> list = zsJkXmbDmCjJcXxMapper.selectZsJkXmbDmCjJcXxList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(ZsJkXmbDmCjJcXxDto zsJkXmbDmCjJcXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbDmCjJcXx zsJkXmbDmCjJcXx = new ZsJkXmbDmCjJcXx();
        BeanUtils.copyProperties(zsJkXmbDmCjJcXxDto, zsJkXmbDmCjJcXx);
    	  zsJkXmbDmCjJcXxMapper.insert(zsJkXmbDmCjJcXx);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  zsJkXmbDmCjJcXxMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(ZsJkXmbDmCjJcXxDto zsJkXmbDmCjJcXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbDmCjJcXx zsJkXmbDmCjJcXx = this.zsJkXmbDmCjJcXxMapper.selectById(zsJkXmbDmCjJcXxDto.getId());
		  zsJkXmbDmCjJcXxMapper.updateById(zsJkXmbDmCjJcXx);
    	  return errorMessage;
    }

	   @Override
	   public ZsJkXmbDmCjJcXx findById(String id) throws Exception {
		   return zsJkXmbDmCjJcXxMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(ZsJkXmbDmCjJcXxDto zsJkXmbDmCjJcXxDto, String[] clumNames) throws Exception {
		   String id = zsJkXmbDmCjJcXxDto.getId();
		   Map map = BeanUtils.toMap(zsJkXmbDmCjJcXxDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<ZsJkXmbDmCjJcXx> lists = zsJkXmbDmCjJcXxMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (ZsJkXmbDmCjJcXx zsJkXmbDmCjJcXx2 : lists) {
				   String newId = zsJkXmbDmCjJcXx2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

	@Override
	public List<ZsJkXmbDmCjJcXxDto> cjjc(String xlId, String startLic, String dianbh, String fazhi) throws Exception {
		List<ZsJkXmbDmCjJcXxDto> dtoList = new ArrayList<ZsJkXmbDmCjJcXxDto>();
		if(StringUtils.isNotBlank(xlId) && StringUtils.isNotBlank(startLic)){
			Map<String,Object> columnMap = new HashMap<String,Object>();
			columnMap.put("xlId", xlId);
			columnMap.put("dianbh", dianbh);
			//此处计算起始里程与终止里程时数据写死 计算方式为： 传进来的里程点*2193.166+54983.487 里程处的前后50米为起止里程
			startLic = String.valueOf(UtilMath.add(UtilMath.multiply(Double.parseDouble(startLic), 2193.166d, 3), 54983.487d, 3));
			String startLc = String.valueOf(UtilMath.subtract(Double.parseDouble(startLic), 50d, 6));
			String endLc = String.valueOf(UtilMath.add(Double.parseDouble(startLic), 50d, 6));
			columnMap.put("startLc", startLc);
			columnMap.put("endLc", endLc);
			columnMap.put("fazhi", fazhi);
			dtoList = this.zsJkXmbDmCjJcXxMapper.selectZsJkXmbDmCjJcXxDtoList(columnMap);
		}
		return dtoList;
	}

}