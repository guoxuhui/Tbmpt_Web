package com.crfeb.tbmpt.zsjk.xmb.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.zsjk.xmb.mapper.ZsJkXmbSbWbXxMapper;
import com.crfeb.tbmpt.zsjk.xmb.model.ZsJkXmbSbWbXx;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbSbWbXxDto;
import com.crfeb.tbmpt.zsjk.xmb.service.ZsJkXmbSbWbXxService;
/**
 * <p>项目部角度 当前设备的维保（维修、保养）明细Service实现类</p>
 * <p>系统：展示界面接口</p>
 * <p>模块：项目部角度模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author YangYJ
 */
@Service
public class ZsJkXmbSbWbXxServiceImpl extends CommonServiceImpl<ZsJkXmbSbWbXxMapper, ZsJkXmbSbWbXx> implements ZsJkXmbSbWbXxService{

    @Autowired
    private ZsJkXmbSbWbXxMapper zsJkXmbSbWbXxMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<ZsJkXmbSbWbXx> page = new Page<ZsJkXmbSbWbXx>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<ZsJkXmbSbWbXx> list = zsJkXmbSbWbXxMapper.selectZsJkXmbSbWbXxList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(ZsJkXmbSbWbXxDto zsJkXmbSbWbXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbSbWbXx zsJkXmbSbWbXx = new ZsJkXmbSbWbXx();
        BeanUtils.copyProperties(zsJkXmbSbWbXxDto, zsJkXmbSbWbXx);
    	  zsJkXmbSbWbXxMapper.insert(zsJkXmbSbWbXx);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  zsJkXmbSbWbXxMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(ZsJkXmbSbWbXxDto zsJkXmbSbWbXxDto,User user) throws Exception {
    	  String errorMessage = "";
        ZsJkXmbSbWbXx zsJkXmbSbWbXx = this.zsJkXmbSbWbXxMapper.selectById(zsJkXmbSbWbXxDto.getId());
		  zsJkXmbSbWbXxMapper.updateById(zsJkXmbSbWbXx);
    	  return errorMessage;
    }

	   @Override
	   public ZsJkXmbSbWbXx findById(String id) throws Exception {
		   return zsJkXmbSbWbXxMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(ZsJkXmbSbWbXxDto zsJkXmbSbWbXxDto, String[] clumNames) throws Exception {
		   String id = zsJkXmbSbWbXxDto.getId();
		   Map map = BeanUtils.toMap(zsJkXmbSbWbXxDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<ZsJkXmbSbWbXx> lists = zsJkXmbSbWbXxMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (ZsJkXmbSbWbXx zsJkXmbSbWbXx2 : lists) {
				   String newId = zsJkXmbSbWbXx2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

	@Override
	public List<ZsJkXmbSbWbXxDto> zysbwxby(String sbId, String dateType) throws Exception {
		List<ZsJkXmbSbWbXxDto> dtoList = new ArrayList<ZsJkXmbSbWbXxDto>();
		List<ZsJkXmbSbWbXxDto> resultList = new ArrayList<ZsJkXmbSbWbXxDto>();
		if(StringUtils.isNotBlank(sbId) && StringUtils.isNotBlank(dateType)){
			Map<String,Object> columnMap = new HashMap<String,Object>();
			columnMap.put("sbId", sbId);
			if("日".equals(dateType)){
				//统计7日数据
				String startTime = DateUtils.addDayOfCurrentDay(-6);//当前日期减去6天为查询起始日期
				String endTime = DateUtils.format(new Date(),"yyyy-MM-dd");//当前日期为查询截止日期
				columnMap.put("startTime", startTime);
				columnMap.put("endTime", endTime);
				dtoList = this.zsJkXmbSbWbXxMapper.selectZsJkXmbSbWbXxDtoList(columnMap);
			}else if("周".equals(dateType)){
				//统计7周数据
				SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
				Date starDate = sdf.parse(DateUtils.addDayOfCurrentDay(-42));
				String startTime = DateUtils.getDateWeekNumber(starDate);//当前日期减去6天为查询起始日期
				String endTime = DateUtils.format(new Date(),"yyyy-MM-dd");//当前日期为查询截止日期
				endTime = DateUtils.getDateWeekNumber(sdf.parse(endTime));
				columnMap.put("startTime", startTime);
				columnMap.put("endTime", endTime);
				dtoList = this.zsJkXmbSbWbXxMapper.selectZsJkXmbSbWbXxDtoListByWeek(columnMap);
				
			}else if("月".equals(dateType)){
				//统计7月数据
				String startTime = DateUtils.format(DateUtils.addMonth(new Date(), -6),"yyyy-MM");//当前日期减去6为查询起始日期
				String endTime = DateUtils.format(new Date(),"yyyy-MM");//当前日期为查询截止日期
				columnMap.put("startTime", startTime);
				columnMap.put("endTime", endTime);
				dtoList = this.zsJkXmbSbWbXxMapper.selectZsJkXmbSbWbXxDtoListByMonth(columnMap);
			}
			resultList = findAllDateListByDataList(dtoList,dateType);
		}
		return resultList;
	}
	
	/**
	 * 方法说明：此处提供接口时需要返回连续的7次的数据，根据查询出来的数据补齐7条数据
	 * @param dataList 数据库查询出的数据列表
	 * @param dateType 日期类型（日，周，月）
	 * @return 返回完整的数据集合
	 * @author:YangYj
	 * @Time: 2017年1月11日 上午11:23:51
	 */
	public List<ZsJkXmbSbWbXxDto> findAllDateListByDataList(List<ZsJkXmbSbWbXxDto> dataList,String dateType){
		List<ZsJkXmbSbWbXxDto> list = new ArrayList<ZsJkXmbSbWbXxDto>();
		if(dataList.size()==7){
			list = dataList;
		}else{
			//7天中有数据不存在的则需要填充空值
			Map<String,ZsJkXmbSbWbXxDto> dataMap = new HashMap<String,ZsJkXmbSbWbXxDto>();
			String sbId="",sbmc="";
			for(ZsJkXmbSbWbXxDto entity: dataList){
				sbId = entity.getSbId();
				sbmc = entity.getSbmc();
				dataMap.put(entity.getRq(), entity);
			}
			if(StringUtils.isNotBlank(dateType)){
				ZsJkXmbSbWbXxDto dto = new ZsJkXmbSbWbXxDto();
				if("日".equals(dateType)){
					String time = "";
					for(int i=6;i>-1;i--){
						dto = new ZsJkXmbSbWbXxDto();
						time =DateUtils.addDayOfCurrentDay(-i);
						if(null != dataMap.get(time)){
							dto = dataMap.get(time);
						}else{
							dto.setSbId(sbId);
							dto.setSbmc(sbmc);
							dto.setCs("0");
							dto.setRq(time);
						}
						list.add(dto);
					}
					
				}else if("周".equals(dateType)){
					SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
					Date starDate;
					try {
						String time = "";
						for(int i=6;i>-1;i--){
							dto = new ZsJkXmbSbWbXxDto();
							starDate = sdf.parse(DateUtils.addDayOfCurrentDay(-i*7));
							time = DateUtils.getDateWeekNumber(starDate);
							if(null != dataMap.get(time)){
								dto = dataMap.get(time);
							}else{
								dto.setSbId(sbId);
								dto.setSbmc(sbmc);
								dto.setCs("0");
								dto.setRq(time);
							}
							list.add(dto);
						}
						
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}else if("月".equals(dateType)){
					String time = "";
					for(int i=6;i>-1;i--){
						dto = new ZsJkXmbSbWbXxDto();
						time =DateUtils.format(DateUtils.addMonth(new Date(), -i),"yyyy-MM");
						if(null != dataMap.get(time)){
							dto = dataMap.get(time);
						}else{
							dto.setSbId(sbId);
							dto.setSbmc(sbmc);
							dto.setCs("0");
							dto.setRq(time);
						}
						list.add(dto);
					}
				}
			}
		}
		return list;
	}

}