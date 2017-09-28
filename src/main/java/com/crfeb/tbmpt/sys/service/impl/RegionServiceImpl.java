package com.crfeb.tbmpt.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.crfeb.tbmpt.commons.result.Tree;
import com.crfeb.tbmpt.sys.mapper.RegionMapper;
import com.crfeb.tbmpt.sys.model.Region;
import com.crfeb.tbmpt.sys.service.IRegionService;

/**
 *
 * region 表数据服务层接口实现类
 *
 */
@Service
public class RegionServiceImpl extends CommonServiceImpl<RegionMapper, Region> implements IRegionService {


    
    @Autowired
    private RegionMapper regionMapper;
    

    
    
    
    /***
     * 
     * 树形  查询  用  Tree  类  方式
     * 
     * wpg  
     * 
     * 2016-11-23
     * 
     */
   
	@Override
	public List<Tree> selectTree() {
		
		   List<Tree> trees = new ArrayList<Tree>();

	        List<Region> regionFather = regionMapper.selectByPIdNull();

	        if (regionFather!=null && !regionFather.isEmpty()) {
	            for (Region regionOne : regionFather) {
	                Tree treeOne = new Tree();

	                treeOne.setId(regionOne.getId());
	                treeOne.setText(regionOne.getName());
	                treeOne.setType(regionOne.getType());
	                
	                this.optRegionTree(treeOne);
	                
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
	private void optRegionTree(Tree treeOne){
    	List<Region> regionSon = regionMapper.selectAllByPId(treeOne.getId());
    	/***
    	 * 如果 数据库 没查询到值，返回空是 :"" ,非等于：null ,
    	 * 所以：!=null 判断 是多余的；不起作用；
    	 * 所以：else { treeOne.setState("closed");} 代码段，是永远都不会被执行；
    	 * 因为：以下代码运行结果是正确的，所以：treeOne.setState("closed");是多余的；或错误的；
    	 * 如果用：regionSon.isEmpty() 判断是否为空要注释treeOne.setState("closed");
    	 */
        if (regionSon !=null && !regionSon.isEmpty()) {
            List<Tree> tree = new ArrayList<Tree>();
            for (Region regionTwo : regionSon) {
                Tree treeTwo = new Tree();
                treeTwo.setId(regionTwo.getId());
                treeTwo.setText(regionTwo.getName());
                treeTwo.setType(regionTwo.getType());
                
                this.optRegionTree(treeTwo);
                
                tree.add(treeTwo);
            }
            treeOne.setChildren(tree);
        } 
        
    }
	
	
	/***
	 * 查询 所有 地区
	 * 查询 地区列表 
	 * @return
	 */
	@Override
	public List<Region> selectTreeGrRegion() {
		
	    return regionMapper.selectAll();
	
	}


	
   
    
    /***
	 * 添加 新 地区
	 * @param region
	 * @return
	 */
    @Override
	public int save(Region region) {
    	//判断 输入 的 地区对象 的 父级 是否  存在 或   Pid  是否 为空！
    	int i = this.JudgePidNull(region);
    	if(i==1 )
    	{
    		region.setPid("");
    		regionMapper.insert(region);
    	}
    	else
    	{
    		
        	if(i==2){
        		regionMapper.insert(region);
        	}
        	else
        	{
        		region.setPid("null");
        		regionMapper.insert(region);
        	}
    	}
		
		return 0;
	}

    /***
     * 判断 输入 的 地区对象 的 父级 是否  存在 或   Pid  是否 为空！
     * @return
     */
    private int JudgePidNull(Region region){
    	
    	int i =0;
    	
    	//父级Pid 是为空
    	if(region.getPid()==null||region.getPid().isEmpty()){
    		i=1;
    		
    	}else{
    		//查询 父级数据  是否 存在
        	Region pregion= regionMapper.selectById(region.getPid());
        	
        	//父级数据 切实存在 
        	if(pregion!=null && !pregion.equals("")){
        		i=2;
        	}else{
        		//父级数据 不存在 
        		i=3;
        	}
    	}
    	return i;
    }
   
    /***
     * 保存 修改  
     */
    @Override
	public boolean editRegion(Region region) {

        try {
        	//判断父Pid 是否 是它自己Id
        	if(!region.getId().equals(region.getPid())){
	        	//判断 输入 的 地区对象 的 父级 是否  存在 或   Pid  是否 为空！
	        	int i = this.JudgePidNull(region);
	        	
	        	//父级   存在 
	        	if(i==2 ){
	        		//判断 修改后 的 父Pid 是否 是 它 的 子对象Id
	        		//是：false，不是：true;
	        		if(JudgePidIfSon(region)) {
	        		    this.updateById(region);
	        		    return true;
	        		}
	        		
	        	}else{
	        		//父级 已被 删除  或   Pid  是 为空""！
	        		region.setPid("");
	        		this.updateById(region);
	                return true;
	        	}
            }
		} catch (Exception e) {
			e.printStackTrace();
			
		}
       return false; 
		
	}
    /***
     * 判断 修改后 的 父Pid 是否 是 它 的 子对象Id
     * 如果 false 不可以 保存 被修改的 对象
     * @param region  被修改的 对象
     * @param bool   false 为时 他的 父Pid 是 修改的 对象下的 子对象 id
     * 
     */
    private String pid="";
    private boolean bool;
    private boolean JudgePidIfSon(Region region){
    	bool = true;
    	pid = region.getPid();
    	this.JudgePid(region);
    	return bool;
    }
    /***
     * 判断 修改后 的 父Pid 是否 是 它 的 子对象Id
     * 如果 false 不可以 保存 被修改的 对象
     * @param region  被修改的 对象
     * @param bool   false 为时 他的 父Pid 是 修改的 对象下的 子对象 id
     * 
     */
    private void JudgePid(Region region){
    	List<Region> regionSon = regionMapper.selectAllByPId(region.getId());
        if (regionSon !=null && !regionSon.isEmpty()) {
            for (Region regionTwo : regionSon) {
               if(regionTwo.getPid().equals(pid)){
            	   bool = false;
            	   break;
               }else{
            	   JudgePid(regionTwo);
               }
            }
            
        } 
    }

    
    /***
     * 删除 机制
     */
    /***
     * wpg
     * 
     * 2016-11-26
     * 
     * 方式1
     * 
     * 删除 地区 对象 前 把 该对象 下的 子对象 的 父级Pid 改为空；
     * 
     * 以免造成 脏数据；
     * 
     */
	@Override
	public boolean deleteRegion1(String id) {
	
	   try {
			List<Region> regionSon = regionMapper.selectAllByPId(id);
	        if (regionSon !=null && !regionSon.isEmpty()) {
	            for (Region regionTwo : regionSon) {
	            	regionTwo.setPid("");
	        		this.updateById(regionTwo);
	            }
	            
	        } 
	        this.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}


	
	/***
     * wpg
     * 
     * 2016-11-26
     * 
     * 方式2
     * 
     * 地区 对象 前 判断 该对象下是否有 子对象 ,
     * 如果有：返回 2 ,
     * 没有子对象就直接删除该对象，返回1；
     * 失败：返回3；
     * 
     * 以免造成 脏数据；
     * 
     */
	@Override
	public int deleteByRid(String id) {
		try {
			List<Region> regionSon = regionMapper.selectAllByPId(id);
	        if (regionSon ==null || regionSon.isEmpty()) {
	        	//删除 该对象  
				this.deleteById(id);
	        	return 1;
	        }else{
	        	return 2;
	        }
		} catch (Exception e) {
			e.printStackTrace();
			return 3;
		}
	}
	
	/***
	 * wpg
     * 
     * 2016-11-26
     * 
	 * 删除 地区 对象 前 把 该对象 下的 子对象 全部 删除
     * 
     * 以免造成 脏数据；
	 */
	public boolean deleteRegion(String id) {
		try {
			//调用方法 查询 并 删除 子对象
			this.deleteRegionTree(id);
			//删除 该对象
			this.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
    /***
     * 删除 地区 对象 前 把 该对象 下的 子对象 全部 删除
     * @param id
     */
	private void deleteRegionTree(String id){
    	List<Region> regionSon = regionMapper.selectAllByPId(id);
        if (regionSon !=null && !regionSon.isEmpty()) {
           
            for (Region regionTwo : regionSon) {
                
                this.deleteRegionTree(regionTwo.getId());
                this.deleteById(regionTwo.getId());
               
            }
           
        } 
        
    }



	
}