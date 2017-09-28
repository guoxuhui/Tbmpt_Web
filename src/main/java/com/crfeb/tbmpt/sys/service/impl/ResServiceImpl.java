package com.crfeb.tbmpt.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.result.Tree;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.sys.mapper.ResMapper;
import com.crfeb.tbmpt.sys.mapper.RoleMapper;
import com.crfeb.tbmpt.sys.mapper.User_roleMapper;
import com.crfeb.tbmpt.sys.model.Res;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.sys.service.IResService;

/**
 *
 * Resource 表数据服务层接口实现类
 *
 */
@Service
public class ResServiceImpl extends CommonServiceImpl<ResMapper, Res> implements IResService {

    private static final int RESOURCE_MENU = 0; // 菜单
    private static final int RESOURCE_BUTTON = 1; // 按钮

    @Autowired
    private ResMapper resourceMapper;
    @Autowired
    private User_roleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;
    
    
    public List<Res> selectAll() {
        return resourceMapper.selectAll();
    }

    public void selectDataGrid(PageInfo pageInfo) {
        Page<Res> page = new Page<Res>(pageInfo.getNowpage(), pageInfo.getSize());
        List<Res> list = resourceMapper.selectResList(page, pageInfo.getSort(), pageInfo.getOrder());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }
    
    public List<Tree> selectAllTree() {
        List<Tree> trees = new ArrayList<Tree>();
        // 查询所有的一级树
        List<Res> resources = resourceMapper.selectAllByTypeAndPIdNull(RESOURCE_MENU);
        if (resources == null) {
            return null;
        }
        for (Res resourceOne : resources) {
            Tree treeOne = new Tree();

            treeOne.setId(resourceOne.getId());
            treeOne.setText(resourceOne.getName());
            treeOne.setIconCls(resourceOne.getIcon());
            treeOne.setAttributes(resourceOne.getUrl());
            // 查询所有一级树下的菜单
            List<Res> resourceSon = resourceMapper.selectAllByTypeAndPId(RESOURCE_MENU, resourceOne.getId());

            if (resourceSon != null) {
                List<Tree> tree = new ArrayList<Tree>();
                for (Res resourceTwo : resourceSon) {
                    Tree treeTwo = new Tree();
                    treeTwo.setId(resourceTwo.getId());
                    treeTwo.setText(resourceTwo.getName());
                    treeTwo.setIconCls(resourceTwo.getIcon());
                    treeTwo.setAttributes(resourceTwo.getUrl());
                    tree.add(treeTwo);
                }
                treeOne.setChildren(tree);
            } else {
                treeOne.setState("closed");
            }
            trees.add(treeOne);
        }
        return trees;
    }

    
    public List<Tree> selectAllTrees() {
        List<Tree> treeOneList = new ArrayList<Tree>();

        // 查询所有的一级树
        List<Res> resources = resourceMapper.selectAllByTypeAndPIdNull(RESOURCE_MENU);
        if (resources == null) {
            return null;
        }

        for (Res resourceOne : resources) {
            Tree treeOne = new Tree();

            treeOne.setId(resourceOne.getId());
            treeOne.setText(resourceOne.getName());
            treeOne.setIconCls(resourceOne.getIcon());
            treeOne.setAttributes(resourceOne.getUrl());

            List<Res> resourceSon = resourceMapper.selectAllByTypeAndPId(RESOURCE_MENU, resourceOne.getId());

            if (resourceSon == null) {
                treeOne.setState("closed");
            } else {
                List<Tree> treeTwoList = new ArrayList<Tree>();

                for (Res resourceTwo : resourceSon) {
                    Tree treeTwo = new Tree();

                    treeTwo.setId(resourceTwo.getId());
                    treeTwo.setText(resourceTwo.getName());
                    treeTwo.setIconCls(resourceTwo.getIcon());
                    treeTwo.setAttributes(resourceTwo.getUrl());

                    /***************************************************/
                    List<Res> resourceSons = resourceMapper.selectAllByTypeAndPId(RESOURCE_BUTTON, resourceTwo.getId());

                    if (resourceSons == null) {
                        treeTwo.setState("closed");
                    } else {
                        List<Tree> treeThreeList = new ArrayList<Tree>();

                        for (Res resourceThree : resourceSons) {
                            Tree treeThree = new Tree();

                            treeThree.setId(resourceThree.getId());
                            treeThree.setText(resourceThree.getName());
                            treeThree.setIconCls(resourceThree.getIcon());
                            treeThree.setAttributes(resourceThree.getUrl());

                            treeThreeList.add(treeThree);
                        }
                        treeTwo.setChildren(treeThreeList);
                    }
                    /***************************************************/

                    treeTwoList.add(treeTwo);
                }
                treeOne.setChildren(treeTwoList);
            }

            treeOneList.add(treeOne);
        }
        return treeOneList;
    }
    
    /**
     * 查询所有的资源树
     */

    public List<Tree> allSelectTree(){
    	List<Res> list = resourceMapper.selectAll();
    	//一级树
    	List<Res> listOne=new ArrayList<Res>();

    	List<Tree> treeOneList = new ArrayList<Tree>();
    	  	

    	for(Res res:list){
    		if(res.getPid()==null){
    			listOne.add(res);
    		}
    	}
    	list.removeAll(listOne);
    	for (Res resourceOne : listOne) {
    		List<Tree> treeTwoList = new ArrayList<Tree>();
    		Tree treeOne = new Tree();
            treeOne.setId(resourceOne.getId());
            treeOne.setText(resourceOne.getName());
            treeOne.setIconCls(resourceOne.getIcon());
            treeOne.setAttributes(resourceOne.getUrl());
            for(Res res:list){
            	if(resourceOne.getId().equals(res.getPid())){
            		Tree treeTwo = new Tree();
            		List<Tree> treeThreeList = new ArrayList<Tree>();
                    treeTwo.setId(res.getId());
                    treeTwo.setText(res.getName());
                    treeTwo.setIconCls(res.getIcon());
                    treeTwo.setAttributes(res.getUrl());
                    treeTwoList.add(treeTwo);                   
                    for(Res ress:list){
                    	if(treeTwo.getId().equals(ress.getPid())){                    		
                    		Tree treeThree = new Tree();
                    		List<Tree> treeFourList = new ArrayList<Tree>();
                            treeThree.setId(ress.getId());
                            treeThree.setText(ress.getName());
                            treeThree.setIconCls(ress.getIcon());
                            treeThree.setAttributes(ress.getUrl());
                            treeThreeList.add(treeThree);
                            for(Res resss:list){
                            	if(treeThree.getId().equals(resss.getPid())){                    		
                            		
                            		Tree treeFour = new Tree();
                            		treeFour.setId(resss.getId());
                            		treeFour.setText(resss.getName());
                            		treeFour.setIconCls(resss.getIcon());
                            		treeFour.setAttributes(resss.getUrl());
                                    treeFourList.add(treeFour);                                   
                            	}
                            }
                            treeThree.setChildren(treeFourList);
                    	}
                    }
                    treeTwo.setChildren(treeThreeList);
            	}
            }
            treeOne.setChildren(treeTwoList);
            treeOneList.add(treeOne);
    	}
		return treeOneList;
    }
    
    public List<Tree> selectTree(User user) {
        List<Tree> trees = new ArrayList<Tree>();
        // 超级管理
        if (user.getLoginName().equals("admin")) {
            List<Res> resourceFather = resourceMapper.selectAllByTypeAndPIdNull(RESOURCE_MENU);
            if (resourceFather == null) {
                return null;
            }

            for (Res resourceOne : resourceFather) {
                Tree treeOne = new Tree();

                treeOne.setId(resourceOne.getId());
                treeOne.setText(resourceOne.getName());
                treeOne.setIconCls(resourceOne.getIcon());
                treeOne.setAttributes(resourceOne.getUrl());
                List<Res> resourceSon = resourceMapper.selectAllByTypeAndPId(RESOURCE_MENU, resourceOne.getId());

                if (resourceSon != null) {
                    List<Tree> tree = new ArrayList<Tree>();
                    for (Res resourceTwo : resourceSon) {
                        Tree treeTwo = new Tree();
                        treeTwo.setId(resourceTwo.getId());
                        treeTwo.setText(resourceTwo.getName());
                        treeTwo.setIconCls(resourceTwo.getIcon());
                        treeTwo.setAttributes(resourceTwo.getUrl());
                        tree.add(treeTwo);
                    }
                    treeOne.setChildren(tree);
                } else {
                    treeOne.setState("closed");
                }
                trees.add(treeOne);
            }
            return trees;
        }

        // 普通用户
        List<Res> resourceIdList = new ArrayList<Res>();
        List<String> roleIdList = userRoleMapper.selectRoleIdListByUserId(user.getId());
        for (String id : roleIdList) {
            List<Res> resourceIdLists = roleMapper.selectResourceIdListByRoleIdAndType(id);
            for (Res resource: resourceIdLists) {
                resourceIdList.add(resource);
            }
        }
        for (Res resource : resourceIdList) {
            if (resource != null && resource.getPid() == null) {
                Tree treeOne = new Tree();
                treeOne.setId(resource.getId());
                treeOne.setText(resource.getName());
                treeOne.setIconCls(resource.getIcon());
                treeOne.setAttributes(resource.getUrl());
                List<Tree> tree = new ArrayList<Tree>();
                for (Res resourceTwo : resourceIdList) {
                    if (resourceTwo.getPid() != null && resource.getId().equals(resourceTwo.getPid())) {
                        Tree treeTwo = new Tree();
                        treeTwo.setId(resourceTwo.getId());
                        treeTwo.setText(resourceTwo.getName());
                        treeTwo.setIconCls(resourceTwo.getIcon());
                        treeTwo.setAttributes(resourceTwo.getUrl());
                        tree.add(treeTwo);
                    }
                }
                treeOne.setChildren(tree);
                trees.add(treeOne);
            }
        }
        return trees;
    }
    
	public List<Tree> selectMenu(User user) {
        List<Tree> trees = new ArrayList<Tree>();
        // 超级管理
        if (user.getLoginName().equals("admin")) {
            List<Res> resourceFather = resourceMapper.selectAllByTypeAndPIdNull(RESOURCE_MENU);
            if (resourceFather == null) {
                return null;
            }
            for (Res resourceOne : resourceFather) {
                Tree treeOne = new Tree();
                treeOne.setId(resourceOne.getId());
                treeOne.setText(resourceOne.getName());
                treeOne.setIconCls(resourceOne.getIcon());
                treeOne.setAttributes(resourceOne.getUrl());
                trees.add(treeOne);
            }
        }else{
        	 // 普通用户
            List<Res> resourceIdList = new ArrayList<Res>();
            List<String> roleIdList = userRoleMapper.selectRoleIdListByUserId(user.getId());
            for (String id : roleIdList) {
                List<Res> resourceIdLists = roleMapper.selectResourceIdListByRoleIdAndType(id);
                for (Res resource: resourceIdLists) {
                    resourceIdList.add(resource);
                }
            }
            for (Res resource : resourceIdList) {
                if (resource != null && resource.getPid() == null) {
                    Tree treeOne = new Tree();
                    treeOne.setId(resource.getId());
                    treeOne.setText(resource.getName());
                    treeOne.setIconCls(resource.getIcon());
                    treeOne.setAttributes(resource.getUrl());
                    trees.add(treeOne);
                }
            }
        }
        return trees;
	}


	public List<Tree> selectMenu22(User user, String name) {
		List<Tree> trees = new ArrayList<Tree>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", name);
		List<Res> ress = resourceMapper.selectByMap(map);
		if(ress != null && ress.size() >= 1){
			String pid = ress.get(0).getId(); 
	        // 超级管理
	        if (user.getLoginName().equals("admin")) {
	            List<Res> resourceFather = resourceMapper.selectAllByTypeAndPId(RESOURCE_MENU, pid);
	            if (resourceFather == null) {
	                return null;
	            }
	            for (Res resourceOne : resourceFather) {
	                Tree treeOne = new Tree();
	                treeOne.setId(resourceOne.getId());
	                treeOne.setText(resourceOne.getName());
	                treeOne.setIconCls(resourceOne.getIcon());
	                treeOne.setAttributes(resourceOne.getUrl());
	                trees.add(treeOne);
	            }
	        }else{
	        	 // 普通用户
	            List<Res> resourceIdList = new ArrayList<Res>();
	            List<String> roleIdList = userRoleMapper.selectRoleIdListByUserId(user.getId());
	            for (String id : roleIdList) {
	                List<Res> resourceIdLists = roleMapper.selectResourceIdListByRoleIdAndType(id);
	                for (Res resource: resourceIdLists) {
	                    resourceIdList.add(resource);
	                }
	            }
	            for (Res resource : resourceIdList) {
	                if (resource != null && pid != null && pid.equals(resource.getPid())){
	                    Tree treeOne = new Tree();
	                    treeOne.setId(resource.getId());
	                    treeOne.setText(resource.getName());
	                    treeOne.setIconCls(resource.getIcon());
	                    treeOne.setAttributes(resource.getUrl());
	                    
	                    trees.add(treeOne);
	                }
	            }
	        }
	        return trees;
		}else{
			return null;
		}
	}
	
	
	public List<Tree> selectMenu2(User user, String name) {
		List<Tree> trees = new ArrayList<Tree>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name", name);
		List<Res> ress = resourceMapper.selectByMap(map);
		if(ress != null && ress.size() >= 1){
			String pid = ress.get(0).getId(); 
	        // 超级管理
	        if (user.getLoginName().equals("admin")) {
	            List<Res> resourceFather = resourceMapper.selectAll();
	            System.out.println(resourceFather);
	            if (resourceFather == null) {
	                return null;
	            }
	            for (Res resourceOne : resourceFather) {
	            	if(resourceOne.getResourceType() == 0){
	            		Tree treeOne = new Tree();
		                treeOne.setId(resourceOne.getId());
		                treeOne.setText(resourceOne.getName());
		                treeOne.setIconCls(resourceOne.getIcon());
		                treeOne.setAttributes(resourceOne.getUrl());
		                treeOne.setPid(resourceOne.getPid());
		                trees.add(treeOne);
	            	}
	            }
	            
	        }else{
	        	 // 普通用户
	            List<Res> resourceIdList = new ArrayList<Res>();
	            List<String> roleIdList = userRoleMapper.selectRoleIdListByUserId(user.getId());
	            for (String id : roleIdList) {
	                List<Res> resourceIdLists = roleMapper.selectResourceIdListByRoleIdAndType(id);
	                for (Res resource: resourceIdLists) {
	                		boolean falt = true;
	                		for(Res res : resourceIdList) {
	                			if(res.getId().equals(resource.getId())) {
	                				falt = false;
	                			}
	                		}
	                		if(falt)
	                		resourceIdList.add(resource);
	                }
	            }
	            
	            for (Res resource : resourceIdList) {
                    Tree treeOne = new Tree();
                    treeOne.setId(resource.getId());
                    treeOne.setText(resource.getName());
                    treeOne.setIconCls(resource.getIcon());
                    treeOne.setAttributes(resource.getUrl());
                    treeOne.setPid(resource.getPid());
                    trees.add(treeOne);
	            }
	        }
	        
	        List<Tree> list = optRootTree(trees);
	        List<Tree> root = new ArrayList<Tree>();
	        for(Tree tree:list){
	        	if(tree.getId().equals(pid)){
	        		root = tree.getChildren();
	        	}
	        }
	        return root;
		}else{
			List<Tree> root = new ArrayList<Tree>();
			return root;
		}
	}
	
	
	public List<Tree> optTree(List<Tree> trees){
        List<Tree> rootTrees = new ArrayList<Tree>();
        for (Tree tree : trees) {
            if(tree.getPid() == null){
                rootTrees.add(tree);
            }
            for (Tree t : trees) {
                if(t.getPid() == tree.getId()){
                    if(tree.getChildren() == null){
                        List<Tree> myChildrens = new ArrayList<Tree>();
                        myChildrens.add(t);
                        tree.setChildren(myChildrens);
                    }else{
                        tree.getChildren().add(t);
                    }
                }
            }
        }
        return rootTrees;
	}

	public List<Tree> optRootTree(List<Tree> menuList ){
		
		List<Tree> nodeList = new ArrayList<Tree>();  
		for(Tree node1 : menuList){  
		    boolean mark = false;  
		    for(Tree node2 : menuList){  
		        if(node1.getPid()!=null && node1.getPid().equals(node2.getId())){  
		            mark = true;  
		            if(node2.getChildren() == null)  
		                node2.setChildren(new ArrayList<Tree>());  
		            node2.getChildren().add(node1);   
		            break;  
		        }  
		    }  
		    if(!mark){  
		        nodeList.add(node1);   
		    }  
		} 
		return nodeList;
	}
}