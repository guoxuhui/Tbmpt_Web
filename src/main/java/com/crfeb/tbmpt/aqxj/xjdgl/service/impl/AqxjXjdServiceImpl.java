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
import com.crfeb.tbmpt.aqxj.xjdgl.model.AqxjXjd;
import com.crfeb.tbmpt.aqxj.xjdgl.model.dto.AqxjXjdDto;
import com.crfeb.tbmpt.aqxj.xjdgl.service.AqxjXjdService;
import com.crfeb.tbmpt.commons.base.BaseService;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.sys.model.User;
/**
 * <p>安全巡检点Service实现类</p>
 * <p>系统：工程项目安全巡检系统</p>
 * <p>模块：巡检点管理</p>
 * <p>日期：2017-05-26</p>
 * @version 1.0
 * @author zhuyabing
 */
@Service
public class AqxjXjdServiceImpl extends CommonServiceImpl<AqxjXjdMapper, AqxjXjd> implements AqxjXjdService{

    @Autowired
    private AqxjXjdMapper aqxjXjdMapper;
    @Autowired
	private AqxjXjdflMapper aqxjXjdflMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<AqxjXjd> page = new Page<AqxjXjd>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<AqxjXjd> list = aqxjXjdMapper.selectAqxjXjdList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    
   
    /**
     * 复制巡检点
     * 
     * */
    @Override
	public Object copyAqxjxjd(String currentProName,String proName) {
    	//查找需要复制的巡检点
		List<AqxjXjd> xjdList=aqxjXjdMapper.selectByProName(proName);
		List list = new ArrayList();
		String str=null;
		if(xjdList!=null && !xjdList.isEmpty()){
			for(AqxjXjd aqxjXjd: xjdList){
				String mc=aqxjXjd.getMingCheng();
				//验证当前要添加的巡检点是否存在
				AqxjXjd isAqxjXjd=aqxjXjdMapper.selectByGcAndMc(mc,currentProName);
				//如果为空就复制
				if(isAqxjXjd==null){
					AqxjXjd newAqxjXjd=new AqxjXjd();
					newAqxjXjd.setProjectName(currentProName);
					newAqxjXjd.setMingCheng(mc);
					newAqxjXjd.setAddress(aqxjXjd.getAddress());
					newAqxjXjd.setXuHao(aqxjXjd.getXuHao());
					newAqxjXjd.setZeRenrmc(aqxjXjd.getZeRenrmc());
					newAqxjXjd.setJianDurmc(aqxjXjd.getJianDurmc());
					newAqxjXjd.setJianChapc(aqxjXjd.getJianChapc());
					newAqxjXjd.setTypeName(aqxjXjd.getTypeName());
					newAqxjXjd.setBeiZhu(aqxjXjd.getBeiZhu());
					aqxjXjdMapper.insert(newAqxjXjd);
				}else{
					list.add(mc);
					//String str = Joiner.on(",").join(list); 
					//String str = StringUtils.join(list.toArray(), ","); 
					//把集合中的字符串转化成用“，”分隔的字符串
					str = StringUtils.collectionToDelimitedString(list, ",");
					System.out.println(str);
				}
			}
			return str;
		}
		return null;
	}


    
    /**
     * 【复制分类】复制巡检点
     * 
     * */
    @Override
	public Object copyFlXjd(String id,String currId,String currProName,String fengL) {
    	//根据分类id查找要复制的跟巡检点
    	List<AqxjXjd> xjdList = aqxjXjdMapper.selectByTypeId(id);
    	//根据id查找当前工程
    	AqxjXjdfl currselct=null;
    	if(!StringUtils.isEmpty(currId)){
    		currselct=aqxjXjdflMapper.seledtByPidAndMc(currId,fengL);
    	//fid=currselct.getId();
    	}else{
    		currselct=aqxjXjdflMapper.selectFlByNullPid(fengL, currProName);
    	}
    	String fid=currselct.getId();
    	List list = new ArrayList();
    	String str=null;
    	//查找源工程选中节点的分类
    	AqxjXjdfl getqxjXjdfl=aqxjXjdflMapper.selectById(id);
    	String fl=getqxjXjdfl.getFenleiMc();
    	/*//根据Id和mc查找当前工程子巡检分类
    	AqxjXjdfl chilXjfl=aqxjXjdflMapper.seledtByPidAndMc(fid,fl);
    	String newId=null;*/
    	/*if(chilXjfl!=null){
    		newId=chilXjfl.getId();
    	}*/
    	//String newMc=chilXjfl.getFenleiMc();
    	
    	if(xjdList!=null && !xjdList.isEmpty()){
    		for(AqxjXjd cAqxjXjd: xjdList){
    			String mc=cAqxjXjd.getMingCheng();
    			//验证唯一性
    			AqxjXjd isAqxjXjd=aqxjXjdMapper.selectByTypIdAndMc(fid,mc);
    			if(isAqxjXjd==null){
    				AqxjXjd newAqxjXjd=new AqxjXjd();
					newAqxjXjd.setProjectName(currProName);
					newAqxjXjd.setMingCheng(mc);
					newAqxjXjd.setAddress(cAqxjXjd.getAddress());
					newAqxjXjd.setXuHao(cAqxjXjd.getXuHao());
					newAqxjXjd.setZeRenrmc(cAqxjXjd.getZeRenrmc());
					newAqxjXjd.setJianDurmc(cAqxjXjd.getJianDurmc());
					newAqxjXjd.setJianChapc(cAqxjXjd.getJianChapc());
					newAqxjXjd.setTypeName(cAqxjXjd.getTypeName());
					newAqxjXjd.setTypeId(fid);
					newAqxjXjd.setBeiZhu(cAqxjXjd.getBeiZhu());
					aqxjXjdMapper.insert(newAqxjXjd);
					
					//复制子类
		    		//childCopyFlXjd(id,newId,currProName);
    			}else{
    				list.add(mc);
					//String str = Joiner.on(",").join(list); 
					//String str = StringUtils.join(list.toArray(), ","); 
					//把集合中的字符串转化成用“，”分隔的字符串
					str = StringUtils.collectionToDelimitedString(list, ",");
    			}
    		}
    		//复制子类
    		childCopyFlXjd(id,fid,currProName);
    		
    	}
		return str;
	}

    
    //【复制分类】循环子类复制巡检点
    public void childCopyFlXjd(String fid,String newId,String currProName){
    	//根据Pid查找要复制的子分类
    	List<AqxjXjdfl> cList = aqxjXjdflMapper.selectAllByPId(fid);
    	
    	if(cList!=null && !cList.isEmpty()){
    		String TpId=null;
    		for(AqxjXjdfl cAqxjXjdfl: cList){
    			String cTypeId=cAqxjXjdfl.getId();
    			String cflMc=cAqxjXjdfl.getFenleiMc();
    			//根据TypeId查找要复制的巡检点
    			List<AqxjXjd> XjdList =  aqxjXjdMapper.selectByTypeId(cTypeId);
    			for(AqxjXjd cAqxjXjd: XjdList){
    				String cMc=cAqxjXjd.getTypeName();
    				String mingC=cAqxjXjd.getMingCheng();
    				//获取插入到当前工程的对应分类，获取分类的Id
    				AqxjXjdfl aqxjXjdfl=aqxjXjdflMapper.seledtByPidAndMc(newId,cflMc);
    				if(aqxjXjdfl!=null){
    					TpId=aqxjXjdfl.getId();
    				}
    				//验证唯一性
        			AqxjXjd isAqxjXjd=aqxjXjdMapper.selectByTypIdAndMc(TpId,cMc);
        			if(isAqxjXjd==null){
        				AqxjXjd newAqxjXjd=new AqxjXjd();
    					newAqxjXjd.setProjectName(currProName);
    					newAqxjXjd.setMingCheng(mingC);
    					newAqxjXjd.setAddress(cAqxjXjd.getAddress());
    					newAqxjXjd.setXuHao(cAqxjXjd.getXuHao());
    					newAqxjXjd.setZeRenrmc(cAqxjXjd.getZeRenrmc());
    					newAqxjXjd.setJianDurmc(cAqxjXjd.getJianDurmc());
    					newAqxjXjd.setJianChapc(cAqxjXjd.getJianChapc());
    					newAqxjXjd.setTypeName(cAqxjXjd.getTypeName());
    					newAqxjXjd.setTypeId(TpId);
    					newAqxjXjd.setBeiZhu(cAqxjXjd.getBeiZhu());
    					aqxjXjdMapper.insert(newAqxjXjd);
    					
    					//复制子类
    		    		//childCopyFlXjd(cTypeId,TpId,currProName);
        			}
    			}
    			//复制子类
        		childCopyFlXjd(cTypeId,TpId,currProName);
    		}
    		//复制子类
    		//childCopyFlXjd(cTypeId,TpId,currProName);
    	}
    	
    }

    
    
    /**
     * 【巡检点复制】复制巡检点
     * */
	@Override
	public String copyAqxjxjdXjd(List<String> list,String currId,String currProName,String currFengL) {
		String str=null;
		if(list!=null && !list.isEmpty()){
			List<String> idList=new ArrayList<String>();
			for(String xjdid: list){
				//根据id查找要复制的源工程巡检点
				AqxjXjd aqxjXjd = aqxjXjdMapper.selectById(xjdid);
				String Mc=aqxjXjd.getMingCheng();
				//查找当前工程是否有对应的巡检点
				AqxjXjd cAqxjXjd = aqxjXjdMapper.selectByTypIdAndMc(currId, Mc);
				if(cAqxjXjd==null){
					AqxjXjd nAqxjXjd=new AqxjXjd();
					nAqxjXjd.setProjectName(currProName);
					nAqxjXjd.setMingCheng(Mc);
					nAqxjXjd.setAddress(aqxjXjd.getAddress());
					nAqxjXjd.setXuHao(aqxjXjd.getXuHao());
					nAqxjXjd.setZeRenrmc(aqxjXjd.getZeRenrmc());
					nAqxjXjd.setJianDurmc(aqxjXjd.getJianDurmc());
					nAqxjXjd.setJianChapc(aqxjXjd.getJianChapc());
					nAqxjXjd.setTypeName(currFengL);
					nAqxjXjd.setTypeId(currId);
					nAqxjXjd.setBeiZhu(aqxjXjd.getBeiZhu());
					aqxjXjdMapper.insert(nAqxjXjd);
				}else{
					idList.add(Mc);
					//把集合中的字符串转化成用“，”分隔的一个整体字符串
					str = StringUtils.collectionToDelimitedString(idList, ",");
				}
			}
		}
		
		return str;
	}



	@Override
    public String save(AqxjXjdDto aqxjXjdDto,User user) throws Exception {
    	  String errorMessage = "";
        AqxjXjd aqxjXjd = new AqxjXjd();
        BeanUtils.copyProperties(aqxjXjdDto, aqxjXjd);
    	  BaseService.operatorMessage(aqxjXjd, null, user);
    	  aqxjXjdMapper.insert(aqxjXjd);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  aqxjXjdMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }
	   
	   

    @Override
	public List<AqxjXjd> getAqxjXjd() {
		return aqxjXjdMapper.getAll();
	}

	@Override
    public String update(AqxjXjdDto aqxjXjdDto,User user) throws Exception {
    	  String errorMessage = "";
        AqxjXjd aqxjXjd = this.aqxjXjdMapper.selectById(aqxjXjdDto.getId());
       errorMessage = BaseService.operatorMessage(aqxjXjd, aqxjXjdDto, user);
		  BeanUtils.copyProperties(aqxjXjdDto, aqxjXjd);
		  aqxjXjdMapper.updateById(aqxjXjd);
    	  return errorMessage;
    }

	   @Override
	   public AqxjXjd findById(String id) throws Exception {
		   return aqxjXjdMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(AqxjXjdDto aqxjXjdDto, String[] clumNames){
		   String id = aqxjXjdDto.getId();
		   Map map = BeanUtils.toMap(aqxjXjdDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<AqxjXjd> lists = aqxjXjdMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据的巡检点名称已存在!";
		   }else{//修改
			    for (AqxjXjd aqxjXjd2 : lists) {
				   String newId = aqxjXjd2.getId();
				   if(!newId.equals(id)){
					   return "当前数据的巡检点名称已存在!";
				   }
			    }
		   }
		   return null;
    }

}