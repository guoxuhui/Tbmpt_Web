package com.crfeb.tbmpt.aqxj.xjdgl.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.aqxj.mapper.AqxjXjdflMapper;
import com.crfeb.tbmpt.aqxj.model.AqxjXjdfl;
import com.crfeb.tbmpt.aqxj.xjdgl.mapper.AqxjXjdMapper;
import com.crfeb.tbmpt.aqxj.xjdgl.mapper.AqxjXjnrMapper;
import com.crfeb.tbmpt.aqxj.xjdgl.model.AqxjXjd;
import com.crfeb.tbmpt.aqxj.xjdgl.model.AqxjXjnr;
import com.crfeb.tbmpt.aqxj.xjdgl.model.dto.AqxjXjnrDto;
import com.crfeb.tbmpt.aqxj.xjdgl.service.AqxjXjnrService;
import com.crfeb.tbmpt.commons.base.BaseService;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.sys.model.User;
/**
 * <p>安全巡检内容Service实现类</p>
 * <p>系统：工程项目安全巡检系统</p>
 * <p>模块：编辑巡检内容</p>
 * <p>日期：2017-05-27</p>
 * @version 1.0
 * @author zhuyabing
 */
@Service
public class AqxjXjnrServiceImpl extends CommonServiceImpl<AqxjXjnrMapper, AqxjXjnr> implements AqxjXjnrService{

    @Autowired
    private AqxjXjnrMapper aqxjXjnrMapper;
    @Autowired
    private AqxjXjdMapper aqxjXjdMapper;
    @Autowired
	private AqxjXjdflMapper aqxjXjdflMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<AqxjXjnr> page = new Page<AqxjXjnr>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<AqxjXjnr> list = aqxjXjnrMapper.selectAqxjXjnrList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(AqxjXjnrDto aqxjXjnrDto,User user) throws Exception {
    	  String errorMessage = "";
        AqxjXjnr aqxjXjnr = new AqxjXjnr();
        BeanUtils.copyProperties(aqxjXjnrDto, aqxjXjnr);
    	  BaseService.operatorMessage(aqxjXjnr, null, user);
    	  aqxjXjnrMapper.insert(aqxjXjnr);
    	  return errorMessage;
    }

    
    /**
	 * 【复制工程】复制巡检内容
	 * */
	@Override
	public Object copyAqxjxjnr(String currentProName, String proName) {
		   //查找需要复制其内容的巡检点
		   List<AqxjXjd> xjdList=aqxjXjdMapper.selectByProName(proName);
		   List list = new ArrayList();
		   String str1=null;
		   /**
		    * 遍历巡检点，根据巡检点id查询巡检内容
		    * */
			if(xjdList!=null && !xjdList.isEmpty()){
				for(AqxjXjd aqxjXjd: xjdList){
					String xjdId=aqxjXjd.getId();
					String mc=aqxjXjd.getMingCheng();
					//根据巡检点的名称和工程查找已经复制到当前项目下的巡检点
					AqxjXjd newAqxjXjd=aqxjXjdMapper.selectByGcAndMc(mc,currentProName);
					//String newItemId=newAqxjXjd.getId();
					if(newAqxjXjd!=null){
						String newId=newAqxjXjd.getId();
						//根据巡检点Id查找需要复制的巡检内容
						List<AqxjXjnr> oldXjnrList = aqxjXjnrMapper.selectByItemId(xjdId);
						if(oldXjnrList!=null && !oldXjnrList.isEmpty()){
							
							for(AqxjXjnr xjnr: oldXjnrList){
								AqxjXjnr aqxjXjnr = new AqxjXjnr();
								String nMingcheng=xjnr.getMingCheng();
								//验证当前项目该巡检点下是否存在该内容
								AqxjXjnr isaqxjXjnr = aqxjXjnrMapper.selectByMcAndItemId(nMingcheng,newId);
								//不存在可以复制
								if(isaqxjXjnr==null){
								aqxjXjnr.setItemId(newId);
								aqxjXjnr.setMingCheng(xjnr.getMingCheng());
								aqxjXjnr.setXuHao(xjnr.getXuHao());
								this.aqxjXjnrMapper.insert(aqxjXjnr);
								}else{
									list.add(nMingcheng);
									str1 = StringUtils.collectionToDelimitedString(list, ",");
									System.out.println(str1);
								}
							}
						}
					}
				}
				return str1;
			}
			return null;
	}
	
	/**
	 * 【复制分类】复制巡检内容
	 * */
	@Override
	public Object copyFlAqxjxjnr(String id, String currId,String currproName) {
		   //查找需要复制其内容的巡检点
			AqxjXjdfl xjFl=aqxjXjdflMapper.selectById(id);
			String flmc=xjFl.getFenleiMc();
			String typeId= xjFl.getId();
			//查询已复制到当前工程对应的分类
			AqxjXjdfl cxjFl=null;
			if(StringUtils.isEmpty(currId)){
			cxjFl=aqxjXjdflMapper.selectFlByNullPid(flmc, currproName);
			}else{
			cxjFl=aqxjXjdflMapper.seledtByPidAndMc(currId,flmc);
			}
			String cTypeId=cxjFl.getId();
			//根据TypeId查询巡检点
			List<AqxjXjd> xjdList=aqxjXjdMapper.selectByTypeId(typeId);
			if(xjdList!=null && !xjdList.isEmpty()){
				for(AqxjXjd aqxjXjd: xjdList){
					String cMc=aqxjXjd.getMingCheng();
					String itemId=aqxjXjd.getId();
					//查询已复制到当前工程对应的巡检点
					AqxjXjd cAqxjXjd=aqxjXjdMapper.selectByTypIdAndMc(cTypeId, cMc);
					String cItemId=cAqxjXjd.getId();
					//根据itemId查询巡检内容
					List<AqxjXjnr> xjnrList=aqxjXjnrMapper.selectByItemId(itemId);
					if(xjnrList!=null && !xjnrList.isEmpty()){
						for(AqxjXjnr aqxjXjnr: xjnrList){
							AqxjXjnr newAqxjXjnr=new AqxjXjnr();
							newAqxjXjnr.setItemId(cItemId);
							newAqxjXjnr.setMingCheng(aqxjXjnr.getMingCheng());
							newAqxjXjnr.setXuHao(aqxjXjnr.getXuHao());
							this.aqxjXjnrMapper.insert(newAqxjXjnr);
						}
					}
				}
				/**
				 * 复制子分类巡检内容
				 * typeId源工程父分类id
				 * cTypeId当前工程父分类id
				 * */
				copyChilFlAqxjxjnr(typeId,cTypeId);
				return true;
			}
			return false;
	}
	
	
	/**
	 * 【复制分类】复制巡检内容子分类内容
	 *  
	 * */
	public void copyChilFlAqxjxjnr(String typeId,String cTypeId){
		//根据Pid查询需要复制的下一级分类
		List<AqxjXjdfl> nextFlList=aqxjXjdflMapper.selectAllByPId(typeId);
		if(nextFlList!=null && !nextFlList.isEmpty()){
			for(AqxjXjdfl aqxjXjdfl: nextFlList){
				String nextTypeId=aqxjXjdfl.getId();
				String nextFlMc=aqxjXjdfl.getFenleiMc();
				//查询已经复制到当前工程对应的分类
				AqxjXjdfl cAqxjXjdfl=aqxjXjdflMapper.seledtByPidAndMc(cTypeId,nextFlMc);
				String ccTypeId=cAqxjXjdfl.getId();
				//根据TypeId查找对应的巡检点
				List<AqxjXjd> nextXjdList=aqxjXjdMapper.selectByTypeId(nextTypeId);
				if(nextXjdList!=null && !nextXjdList.isEmpty()){
					for(AqxjXjd aqxjXjd: nextXjdList){
						String nextItemId=aqxjXjd.getId();
						String cMc=aqxjXjd.getMingCheng();
						//查找已经复制到当前工程对应的巡检点
						AqxjXjd cAqxjXjd=aqxjXjdMapper.selectByTypIdAndMc(ccTypeId, cMc);
						String ItemId=cAqxjXjd.getId();
						//根据ItemId查找要复制的巡检内容
						List<AqxjXjnr> nextXjnrList=aqxjXjnrMapper.selectByItemId(nextItemId);
						if(nextXjnrList!=null && !nextXjnrList.isEmpty()){
							for(AqxjXjnr aqxjXjnr: nextXjnrList){
								//String nextMc=aqxjXjnr.getMingCheng();
								AqxjXjnr newAqxjXjnr=new AqxjXjnr();
								newAqxjXjnr.setItemId(ItemId);
								newAqxjXjnr.setMingCheng(aqxjXjnr.getMingCheng());
								newAqxjXjnr.setXuHao(aqxjXjnr.getXuHao());
								this.aqxjXjnrMapper.insert(newAqxjXjnr);
							}
						}
					}
				}
				copyChilFlAqxjxjnr(nextTypeId,ccTypeId);
			}
		}
	}
	
	
	
	
	/**
	 * 【复制巡检点】复制巡检内容
	 * */
	@Override
	public String copyAqxjxjdXjnr(List<String> idlist, String currId) {
		 List list = new ArrayList();
		   String str1=null;
		if(idlist!=null && !idlist.isEmpty()){
			for(String id: idlist){
				//查找源工程巡检点
				AqxjXjd aqxjXjd=aqxjXjdMapper.selectById(id);
				String cMc=aqxjXjd.getMingCheng();
				//查找当前工程以插入的巡检点
				AqxjXjd cAqxjXjd=aqxjXjdMapper.selectByTypIdAndMc(currId, cMc);
				String itenId=cAqxjXjd.getId();
				//查找巡检内容
				List<AqxjXjnr> aqxjXjnrList=aqxjXjnrMapper.selectByItemId(id);
				if(aqxjXjnrList!=null && !aqxjXjnrList.isEmpty()){
					for(AqxjXjnr aqxjXjnr: aqxjXjnrList){
						String ccMc=aqxjXjnr.getMingCheng();
						//验证唯一
						AqxjXjnr nAqxjXjnr=aqxjXjnrMapper.selectByMcAndItemId(ccMc, itenId);
						if(nAqxjXjnr==null){
						AqxjXjnr newAqxjXjnr=new AqxjXjnr();
						newAqxjXjnr.setItemId(itenId);
						newAqxjXjnr.setMingCheng(aqxjXjnr.getMingCheng());
						newAqxjXjnr.setXuHao(aqxjXjnr.getXuHao());
						this.aqxjXjnrMapper.insert(newAqxjXjnr);
						}else{
							list.add(ccMc);
							str1 = StringUtils.collectionToDelimitedString(list, ",");
						}
					}
				}
				
			}
			return str1;
		}
		return null;
	}

	@Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  aqxjXjnrMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(AqxjXjnrDto aqxjXjnrDto,User user) throws Exception {
    	  String errorMessage = "";
        AqxjXjnr aqxjXjnr = this.aqxjXjnrMapper.selectById(aqxjXjnrDto.getId());
       errorMessage = BaseService.operatorMessage(aqxjXjnr, aqxjXjnrDto, user);
		  BeanUtils.copyProperties(aqxjXjnrDto, aqxjXjnr);
		  aqxjXjnrMapper.updateById(aqxjXjnr);
    	  return errorMessage;
    }

	   @Override
	   public AqxjXjnr findById(String id) throws Exception {
		   return aqxjXjnrMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(AqxjXjnrDto aqxjXjnrDto, String[] clumNames) throws Exception {
		   String id = aqxjXjnrDto.getId();
		   Map map = BeanUtils.toMap(aqxjXjnrDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<AqxjXjnr> lists = aqxjXjnrMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据的巡检内容已存在!";
		   }else{//修改
			    for (AqxjXjnr aqxjXjnr2 : lists) {
				   String newId = aqxjXjnr2.getId();
				   if(!newId.equals(id)){
					   return "当前数据的巡检内容已存在!";
				   }
			    }
		   }
		   return null;
    }

    
    
    /**
   	 * 根据巡检点查询巡检内容
   	 * */
	@Override
	public String selectXjnrByItems(String[] ids) {
		String idsss="";
		String idsss1="";
		for (int i = 0; i < ids.length; i++) {
			String id = ids[i];
			//List<String> list = new ArrayList<String>();
			List<AqxjXjnr> deAqxjXjnr=aqxjXjnrMapper.selectByItemId(id);
			//将集合转化成一个用逗号隔开的字符串
			if(deAqxjXjnr!=null&&!deAqxjXjnr.isEmpty()){
				for(AqxjXjnr aqxjXjnr: deAqxjXjnr){
					String aqxjXjnrId=aqxjXjnr.getId();
					idsss1+=aqxjXjnrId+",";
				}
			}
		}
		idsss+=idsss1+",";
		String str=idsss.substring(0,idsss.length()-1);//去掉字符串的最后一个字符
		return str;
		
	}

}