package com.crfeb.tbmpt.aqxj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.aqxj.mapper.AqxjXjdflMapper;
import com.crfeb.tbmpt.aqxj.model.AqxjXjdfl;
import com.crfeb.tbmpt.aqxj.model.dto.AqxjXjdflDto;
import com.crfeb.tbmpt.aqxj.service.AqxjxjdflService;
import com.crfeb.tbmpt.commons.base.BaseService;
import com.crfeb.tbmpt.commons.result.Tree;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjGPZLDDInfo;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLJtwz;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLSgnr;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLZlqx;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjSGZLSgnrDto;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.Region;
import com.crfeb.tbmpt.sys.model.User;

@Service
public class AqxjxjdflServiceImpl extends CommonServiceImpl<AqxjXjdflMapper, AqxjXjdfl> implements AqxjxjdflService{
	
	
	@Autowired
	private AqxjXjdflMapper aqxjXjdflMapper;
	
	
	
	
	
	
	
	
	
	/*
	 * 查询上级资源树
	 * 
	 * */

	@Override
	public String deleteOperate(String id) {
		AqxjXjdfl aqxjXjdfl = aqxjXjdflMapper.selectById(id);
		if(aqxjXjdfl !=null && !StringUtils.isEmpty(aqxjXjdfl.getId())){
			List<AqxjXjdfl> aqxjList = aqxjXjdflMapper.selectAllByPId(id);  //通过Id获取该部门下的所以子部门
	        if (aqxjList ==null || aqxjList.isEmpty()) {
	        	//删除该对象  
				this.deleteById(id);
				return "";
	        }
        	/*for(AqxjXjdfl aqxj :  aqxjList){
				
				List<AqxjXjdfl> subordinateOrgzList = aqxjXjdflMapper.selectAllByPId(aqxj.getId());
				if(subordinateOrgzList !=null && subordinateOrgzList.size() >0){
					return "该部门的下级部门超过三级";  //如果下级部门，还有下级部门，就不能删除；
						
				}
			}*/
        	return "deleteAqxjTree";
	        
		}else{
			return "删除失败！";
		}
	}


	
	
	
	@Override
	public List<AqxjXjdfl> selectByPid(String id) {
		List<AqxjXjdfl> list = aqxjXjdflMapper.selectAllByPId(id);  //通过Id获取该部门下的所以子部门
        return list;
	}




	@Override
	public List<AqxjXjdfl> getAll() {
		return aqxjXjdflMapper.selectList(null);
	}





	@Override
	public List<AqxjXjdfl> getFlMc(String gcmc) {
		
		return aqxjXjdflMapper.flmcList(gcmc);
	}



/*
 * 复制巡检分类
 * 
 * */
	@Override
	public boolean copyAqxjxjdfl(String proName,User user,String currentProName) {
		//查询当前工程有无分类，如果有分类不复制
		List<AqxjXjdfl> currFl=aqxjXjdflMapper.flList(currentProName);
		//分类为空可以复制
		if(currFl.isEmpty()){
			//查询需要复制到当前工程的父分类
		List<AqxjXjdfl> fatherList=aqxjXjdflMapper.flList(proName);
	    if (fatherList!=null && !fatherList.isEmpty()) {
	    	for (AqxjXjdfl aqxjXjdfl : fatherList) {
	    		String id1=aqxjXjdfl.getId();
	    		String flMc=aqxjXjdfl.getFenleiMc();
	    		AqxjXjdflDto newfl=new AqxjXjdflDto();
	    		//要复制的分类在当前工程不存在，可以复制
	    		AqxjXjdfl currentaqx=aqxjXjdflMapper.selectFather(flMc, currentProName);
	    		if(currentaqx==null){
	    			newfl.setGcMc(currentProName);
	    			newfl.setFenleiMc(flMc);
	    			newfl.setBeiZ(aqxjXjdfl.getBeiZ());
	    			this.insert(newfl,user);
	    		}
	    		
	    		//String newId=newfl.getId();
	    		//复制子类
	    		copyChildFl(id1,flMc,currentProName,user);
	    	}
	     }
	    return true;
		}
		return false;
	}
	
	
	/*
	 * 【复制分类】复制巡检分类
	 * 
	 * */
		@Override
		public boolean copyFLAqxjxjdfl(User user,String currProName,String fengL,String currFengL,String id,String currId) {
			//查询要复制的父分类
			AqxjXjdfl fFengl=aqxjXjdflMapper.selectById(id);
			String fid=fFengl.getId();
			String flMc=fFengl.getFenleiMc();
			String beiZ=fFengl.getBeiZ();
		if(StringUtils.isEmpty(currId)){
			/**
			 * 当前工程不勾选
			 * */
			//查询当前工程是否存在pid为空分类名称与要复制的分类相同的分类(唯一性判断)
			List<AqxjXjdfl> fengl=aqxjXjdflMapper.selectFlNullPid(fengL, currProName);
			if(fengl.isEmpty()){
				//复制父分类
				AqxjXjdflDto nnewfl=new AqxjXjdflDto();
				nnewfl.setGcMc(currProName);
				nnewfl.setFenleiMc(flMc);
				nnewfl.setBeiZ(beiZ);
				this.insert(nnewfl,user);
				//查询上面插入的分类，获取Id
				List<AqxjXjdfl> ffengl=aqxjXjdflMapper.selectFlNullPid(fengL, currProName);
				String pId=ffengl.get(0).getId();
				//复制子类
				copyChildFl1(fid,currProName,user,pId,flMc);
		    return true;
			}
			}else{
			/**
			 * 当前工程勾选
			 * */
			/*String currPid=null;
			if(!StringUtils.isEmpty(currId)){
			AqxjXjdfl currFengl=aqxjXjdflMapper.selectById(currId);
			currPid=currFengl.getId();
			}*/
			//查询所选节点的子是否有该数据
			AqxjXjdfl chilFengl=aqxjXjdflMapper.seledtByPidAndMc(currId,fengL);
			if(!StringUtils.isEmpty(currId)&&chilFengl==null){
				//复制父分类
				AqxjXjdflDto newfl=new AqxjXjdflDto();
				newfl.setGcMc(currProName);
				newfl.setFenleiMc(flMc);
				newfl.setPid(currId);
				newfl.setBeiZ(beiZ);
				this.insert(newfl,user);
				
				AqxjXjdfl cfengl=aqxjXjdflMapper.seledtByPidAndMc(currId, flMc);
				String pId=cfengl.getId();
				//复制子类
				copyChildFl1(fid,currProName,user,pId,flMc);
		    return true;
			}
			}
			return false;
		}

		//【复制工程】复制子分类
	public void copyChildFl(String pid,String fenleiMc,String currentProName,User user){
		//查询需要复制到当前工程的子类
		List<AqxjXjdfl> childAqxjXjdfl=aqxjXjdflMapper.selectAllByPId(pid);
		//查询已经插入的分类，获取该分类的id
		AqxjXjdfl fatherAqxjXjdfl=aqxjXjdflMapper.selectFather(fenleiMc, currentProName);
		//复制子类
		 if (childAqxjXjdfl!=null && !childAqxjXjdfl.isEmpty()) {
		    	for (AqxjXjdfl child : childAqxjXjdfl) {
		    		String fid=fatherAqxjXjdfl.getId();
		    		String id2=child.getId();
		    		String flMc1=child.getFenleiMc();
		    		AqxjXjdflDto newchild=new AqxjXjdflDto();
		    		//验证唯一
		    		AqxjXjdfl currentaqx1=aqxjXjdflMapper.selectFather(flMc1, currentProName);
		    		if(currentaqx1==null){
		    			newchild.setGcMc(currentProName);
		    			newchild.setFenleiMc(flMc1);
		    			newchild.setBeiZ(child.getBeiZ());
		    			newchild.setPid(fid);
		    			this.insert(newchild,user);
		    		}
		    		
		    		this.copyChildFl(id2,flMc1,currentProName,user);
		    	}
		 }
	}

	//【复制分类】复制子分类
	public void copyChildFl1(String pid,String currentProName,User user,String currPid,String fengL){
		//查询需要复制到当前工程的子类
		List<AqxjXjdfl> childAqxjXjdfl=aqxjXjdflMapper.selectAllByPId(pid);
		//查询已经插入的父类，获取该父类的id
		//AqxjXjdfl fatherAqxjXjdfl=aqxjXjdflMapper.selectFather(fenleiMc, currentProName);
		//AqxjXjdfl fatherAqxjXjdfl=aqxjXjdflMapper.seledtByPidAndMc(currPid,currFengL);
		//复制子类
		 if (childAqxjXjdfl!=null && !childAqxjXjdfl.isEmpty()) {
			/*//查询已经插入的父类，获取该父类的id
			 AqxjXjdfl fatherAqxjXjdfl=null;
			 if(!StringUtils.isEmpty(currPid)){//当前工程选中节点
				fatherAqxjXjdfl=aqxjXjdflMapper.seledtByPidAndMc(currPid,fengL);
			 }else{//当前工程不勾选节点
				fatherAqxjXjdfl=aqxjXjdflMapper.selectFlByNullPid(fengL,currentProName);
			 }
			 String iffId=null;
			 if(fatherAqxjXjdfl!=null){
				 iffId=fatherAqxjXjdfl.getId();
			 }*/
		    	for (AqxjXjdfl child : childAqxjXjdfl) {
		    		String id2=child.getId();
		    		String flMc1=child.getFenleiMc();
		    		AqxjXjdflDto newchild=new AqxjXjdflDto();
		    		//验证唯一
		    		AqxjXjdfl chilFengl=aqxjXjdflMapper.seledtByPidAndMc(currPid,flMc1);
		    		if(chilFengl==null){
		    			newchild.setGcMc(currentProName);
		    			newchild.setFenleiMc(flMc1);
		    			newchild.setBeiZ(child.getBeiZ());
		    			newchild.setPid(currPid);
		    			this.insert(newchild,user);
		    		}
		    		AqxjXjdfl cfengl=aqxjXjdflMapper.seledtByPidAndMc(currPid, flMc1);
					String pId=cfengl.getId();
		    		
		    		this.copyChildFl1(id2,currentProName,user,pId,flMc1);
		    	}
		 }
	}

	@Override
	public boolean deleteAqxj(String id) {
		try {
			//查询 并 删除 子对象
			List<AqxjXjdfl> aqxjList = aqxjXjdflMapper.selectAllByPId(id);  //通过Id获取该部门下的所以子部门
			if(aqxjList !=null && aqxjList.size()>0){
				List<String> idList = new ArrayList<String>();
		    	for(AqxjXjdfl aqxj :  aqxjList){
		    		idList.add(aqxj.getId());
		             
				}
		    	this.deleteBatchIds(idList);
			}
			//删除 该对象
			this.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public List<Tree> selectTree() {
		 List<Tree> trees = new ArrayList<Tree>();

	        List<AqxjXjdfl> aqxjFather = aqxjXjdflMapper.selectNull();

	        if (aqxjFather!=null && !aqxjFather.isEmpty()) {
	            for (AqxjXjdfl aqxjXjdfl : aqxjFather) {
	                Tree treeOne = new Tree();

	                treeOne.setId(aqxjXjdfl.getId());
	                treeOne.setText(aqxjXjdfl.getFenleiMc());
	                treeOne.setType(aqxjXjdfl.getGcMc());
	                
	                this.aqxjXjdflTree(treeOne);
	                
	                trees.add(treeOne);
	            }
	        }
	       
	        return trees;
	}
	
	
	@Override
	public List<Tree> selectAllFlMcTree() {
		 List<Tree> trees = new ArrayList<Tree>();

	        List<AqxjXjdfl> aqxjFather = aqxjXjdflMapper.selectAllFlmc();

	        if (aqxjFather!=null && !aqxjFather.isEmpty()) {
	            for (AqxjXjdfl aqxjXjdfl : aqxjFather) {
	                Tree treeOne = new Tree();

	                treeOne.setId(aqxjXjdfl.getId());
	                treeOne.setText(aqxjXjdfl.getFenleiMc());
	                treeOne.setType(aqxjXjdfl.getGcMc());
	                
	                this.aqxjXjdflTree(treeOne);
	                
	                trees.add(treeOne);
	            }
	        }
	       
	        return trees;
	}


	@Override
	public List<Tree> selectflTree(String gcmc) {
		 List<Tree> trees = new ArrayList<Tree>();
		 	
	        List<AqxjXjdfl> aqxjFather = aqxjXjdflMapper.selectByGcMc(gcmc);

	        if (aqxjFather!=null && !aqxjFather.isEmpty()) {
	            for (AqxjXjdfl aqxjXjdfl : aqxjFather) {
	                Tree treeOne = new Tree();
	                
	                treeOne.setId(aqxjXjdfl.getId());
	                treeOne.setText(aqxjXjdfl.getFenleiMc());
	                treeOne.setType(aqxjXjdfl.getGcMc());
	                
	                this.aqxjXjdflTree(treeOne);
	                
	                trees.add(treeOne);
	            }
	        }
	       
	        return trees;
	}
	
	 /***
     * 
     * 树形  查询  
     * 
     * wpg  
     * 
     * 2016-11-23
     * 
     */        
	private void aqxjXjdflTree(Tree treeOne){
    	List<AqxjXjdfl> aqxjSon = aqxjXjdflMapper.selectAllByPId(treeOne.getId());
    	/***
    	 * 如果 数据库 没查询到值，返回空是 :"" ,非等于：null ,
    	 * 所以：!=null 判断 是多余的；不起作用；
    	 * 所以：else { treeOne.setState("closed");} 代码段，是永远都不会被执行；
    	 * 因为：以下代码运行结果是正确的，所以：treeOne.setState("closed");是多余的；或错误的；
    	 * 如果用：regionSon.isEmpty() 判断是否为空要注释treeOne.setState("closed");
    	 */
        if (aqxjSon !=null && !aqxjSon.isEmpty()) {
            List<Tree> tree = new ArrayList<Tree>();
            for (AqxjXjdfl aqxjTwo : aqxjSon) {
                Tree treeTwo = new Tree();
                treeTwo.setId(aqxjTwo.getId());
                treeTwo.setText(aqxjTwo.getFenleiMc());
                treeTwo.setType(aqxjTwo.getGcMc());
                
                this.aqxjXjdflTree(treeTwo);
                
                tree.add(treeTwo);
            }
            treeOne.setChildren(tree);
        } 
        
    }




	/*@Override
	public void selectDataGrid(PageInfo pageInfo) {
		 Page<AqxjXjdfl> page = new Page<AqxjXjdfl>(pageInfo.getNowpage(), pageInfo.getSize());
	       Map<String, Object> condition = pageInfo.getCondition();
	       List<AqxjXjdfl> list = aqxjXjdflMapper.selectAqxjXjdflList(page, condition);
	       List<AqxjXjdflDto> dtoList = new ArrayList<AqxjXjdflDto>();
	       AqxjXjdflDto target = null;
	       for(AqxjXjdfl entity :list){
	    	   target = new AqxjXjdflDto();
	    	  // this.BeanUtilsEntityToDto(entity, target);
	    	   BeanUtils.copyProperties(entity, target);
	    	   dtoList.add(target);
	       }
	       pageInfo.setRows(dtoList);
	       pageInfo.setTotal(page.getTotal());
		
	}*/
	
	  @Override
	    public void selectDataGrid(PageInfo pageInfo){
	       Page<AqxjXjdfl> page = new Page<AqxjXjdfl>(pageInfo.getNowpage(), pageInfo.getSize());
	       Map<String, Object> condition = pageInfo.getCondition();
	       List<AqxjXjdfl> list = aqxjXjdflMapper.selectAqxjXjdflList(page,condition);
	       pageInfo.setRows(list);
	       pageInfo.setTotal(page.getTotal());
	    }
	
	


	@Override
	public String update(AqxjXjdflDto dto, User user) {
		AqxjXjdfl oldEntity = this.aqxjXjdflMapper.selectById(dto.getId());
    	String errorMessage = BaseService.operatorMessage(oldEntity, dto, user);
    	if(StringUtils.isNotBlank(errorMessage)) return errorMessage;
    	BeanUtils.copyProperties(dto, oldEntity);
		int i = aqxjXjdflMapper.updateById(oldEntity);
		if(i<1){
			return "数据更新失败,请重试!";
		}
		return "";
	}






	/*
	 * 添加巡检点
	 */
	@Override
	public int insert(AqxjXjdflDto dto, User user){
		AqxjXjdfl entity = new AqxjXjdfl();
    	BeanUtils.copyProperties(dto, entity);  //将dto复制到实体
    	BaseService.operatorMessage(entity, null,user );  //存储
    	return this.aqxjXjdflMapper.insert(entity);
	}
	

	/*
	 * 验证要添加的数据或修改的数据是否已存在
	 */
	@Override
	public String checkClumIfexits(AqxjXjdflDto aqxjXjdfl, String[] clumNames) {
		String id = aqxjXjdfl.getId();
		   Map map = BeanUtils.toMap(aqxjXjdfl);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames){
			   if("fenlei_mc".equals(clum)){
				   Object ss =  map.get("fenleiMc");
			       columnMap.put(clum, ss);
			   }else{
				   Object ss =  map.get(clum);
				   columnMap.put(clum, ss);
			   }
		   }
		   List<AqxjXjdfl> lists = aqxjXjdflMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据的分类名称已存在!";
		   }else{//修改
			    for (AqxjXjdfl aqxjXjdfl2 : lists) {
				   String newId = aqxjXjdfl2.getId();
				   if(!newId.equals(id)){
					   return "当前数据的分类名称已存在!";
				   }
			    }
		   }
		   return null;
	}






	@Override
	public void deleteByIds(List<String> idlist) {
		if(null != idlist && idlist.size()>0){
			String XjIds ="";
			for(String id:idlist){
				XjIds +=",'"+id+"'";
			}
			XjIds = XjIds.substring(1);			
			this.aqxjXjdflMapper.deleteBatchIds(idlist);//删除施工内容信息
		}
		
	}






	@Override
	public List<AqxjXjdflDto> selectTreeGridByUser(User user,Map<String, Object> condition){
		List<AqxjXjdflDto> dtoList = new ArrayList<AqxjXjdflDto>(); 
		List<AqxjXjdfl> list = aqxjXjdflMapper.selectAll(condition);
		if(null != list && list.size()>0){
			AqxjXjdflDto dto = null;
			AqxjXjdfl aqxjXjdfl = null;
			for(AqxjXjdfl entity :list){
				dto = new AqxjXjdflDto();
				BeanUtils.copy(entity, dto);
				if(StringUtils.isNotBlank(entity.getPid())){
					 aqxjXjdfl = new AqxjXjdfl();
					 aqxjXjdfl = aqxjXjdflMapper.selectById(entity.getPid());
					 if(null != aqxjXjdfl){
						 dto.setpName(aqxjXjdfl.getFenleiMc());
					 }
				}
				dtoList.add(dto);
			}
		}
		return dtoList;
	}













	

	
	
}
