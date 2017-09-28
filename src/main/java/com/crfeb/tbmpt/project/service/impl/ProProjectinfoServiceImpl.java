package com.crfeb.tbmpt.project.service.impl;

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
import com.crfeb.tbmpt.commons.utils.ProNoUtils;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.project.mapper.ProProjectinfoMapper;
import com.crfeb.tbmpt.project.mapper.ProRProjectSectionMapper;
import com.crfeb.tbmpt.project.mapper.ProRSectionLineMapper;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.project.model.vo.ProjectinfoVo;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.sys.mapper.OrgzMapper;
import com.crfeb.tbmpt.sys.mapper.UserMapper;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.Res;
import com.crfeb.tbmpt.sys.model.SysEmployee;
import com.crfeb.tbmpt.sys.model.User;

/**
 *
 * ProProjectinfo 表数据服务层接口实现类
 *
 */
@Service
public class ProProjectinfoServiceImpl extends CommonServiceImpl<ProProjectinfoMapper, ProProjectinfo> implements IProProjectinfoService {

    @Autowired
    private ProProjectinfoMapper proProjectinfoMapper;
    @Autowired
    private ProRProjectSectionMapper proProjectinfoSectionMapper;
    @Autowired
    private ProRSectionLineMapper proRSectionLineMapper;
    @Autowired
    private OrgzMapper orgzMapper;
    @Autowired
    private UserMapper userMapper;
    
    public void saveProjectInfo(ProProjectinfo pro,User user){
    	
    	//0、Code；计算值
    	Orgz orgz = orgzMapper.selectById(user.getOrgzId());
    	String code = orgzMapper.selectMaxByPId(orgz.getId());
    	if(!StringUtils.isEmpty(code)){
    		if(code.startsWith("0")){
    			code = "0"+String.valueOf(Integer.parseInt(code) + 1);
    		}else{
    			code = String.valueOf(Integer.parseInt(code) + 1);
    		}
    	}else{
    		code = orgz.getCode()+"01";
    	}
 
    	//3、创建项目
    	pro.setOrgzId(pro.getParentId());
    	proProjectinfoMapper.insert(pro);
    }
    
    public void selectDataGrid(PageInfo pageInfo) {
        Page<ProjectinfoVo> page = new Page<ProjectinfoVo>(pageInfo.getNowpage(), pageInfo.getSize());
        List<ProjectinfoVo> list = proProjectinfoMapper.selectVoList2(page,pageInfo.getCondition(), pageInfo.getSort(), pageInfo.getOrder());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }
    
    public void selectDataGrid(PageInfo pageInfo,User user) {
//    	Orgz orgz = orgzMapper.selectById(user.getOrgzId());
//    	String code = "-1";
//    	if(orgz != null){
//    		if(orgz.getType() <= 1){
//    			code = orgz.getCode();
//    		}else{
//    			Orgz orgz2 = orgzMapper.selectById(orgz.getPid());
//    			code = orgz2.getCode();
//    		}
//    	}
//    	pageInfo.getCondition().put("code", code);
        Page<ProjectinfoVo> page = new Page<ProjectinfoVo>(pageInfo.getNowpage(), pageInfo.getSize());
        List<ProjectinfoVo> list = proProjectinfoMapper.selectVoList2(page,pageInfo.getCondition(), pageInfo.getSort(), pageInfo.getOrder());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }
    
	public ProProjectinfo selectByProId(String id) {
		ProProjectinfo proinfo = proProjectinfoMapper.selectByProId(id);
		return proinfo;
	}

	/**
	 *  根据用户编号获取用户所在项目信息
	 * @param uid
	 * @return
	 */
	public ProProjectinfo getProjectInfoByUserId(String uid) {
//		User user = userMapper.selectById(uid);
//		Orgz orgz = orgzMapper.selectById(user.getOrgzId());
//		List<ProProjectinfo> list = null;
//		if(orgz != null){
//			if(orgz.getType() <= 1){
//				if(orgz != null){
//					list = proProjectinfoMapper.selectListByOrgzCode(orgz.getCode());
//				}
//			}else{
//				Map<String,Object> map = new HashMap<String,Object>(); 
//				map.put("ORGZ_ID", orgz.getPid());
//				list = proProjectinfoMapper.selectByMap(map);
//			}
//		}
//		if(list != null && list.size() >= 1){
//			return list.get(0);
//		}else{
//			return null;
//		}
		
		List<ProProjectinfo> projects = proProjectinfoMapper.selectByUserId(uid);
    	if(projects!=null&&projects.size()==1){
    		return projects.get(0);
    	}
    	return null;
		
	}

	public Boolean deleteByProId(String id) {
		int i = proProjectinfoMapper.deleteByProId(id);
		return Boolean.valueOf(i>0);
	}

	/**
	 *  根据用户编号获取用户所在项目信息
	 * @param userId
	 * @return
	 */
	public List<ProProjectinfo> getProjectInfosByUserId(String userId) {
		User user = userMapper.selectById(userId);
		Orgz orgz = orgzMapper.selectById(user.getOrgzId());
		List<ProProjectinfo> list = new ArrayList<ProProjectinfo>();
		if(orgz != null){
			if(orgz.getType() <= 1){
				if(orgz != null){
					if(orgz.getType()==0){
						list=proProjectinfoMapper.selectAllByNull();
					}else{
						list = proProjectinfoMapper.selectListByOrgzCode(orgz.getCode());						
					}
				}
			}else{
				Map<String,Object> map = new HashMap<String,Object>(); 
				map.put("ORGZ_ID", orgz.getPid());
				list = proProjectinfoMapper.selectByMap(map);
			}
		}
		return list;
	}
	
	
	

	@Override
	public List<ProProjectinfo> getAllProjectInfos() {
		List<ProProjectinfo> list = proProjectinfoMapper.selectAllByNull();
		return list;
	}

	@Override
	public List<Orgz> selectByOrgzId(String orgzId) {
		Orgz orgz=orgzMapper.selectById(orgzId);
		List<Orgz> list = new ArrayList<Orgz>();
		list.add(orgz);
		return list;
	}
	
	
	/**
	 * 项目资源树
	 * @author wl_zjh
	 */
	@Override
	public Object selectTreeByUser(User user, String id) {
		List<Tree> trees = new ArrayList<Tree>();
			ProProjectinfo proProjectinfo = proProjectinfoMapper.selectById(user.getOrgzId());
			Tree treeOne = new Tree();
            treeOne.setId(proProjectinfo.getId());
            treeOne.setText(proProjectinfo.getProName());
            trees.add(treeOne);
        return trees;
	}

	@Override
	public Object selectTree() {       
        List<Tree> treeOneList = new ArrayList<Tree>();

        // 查询所有的一级树
        List<ProProjectinfo> proProjectinfoList = proProjectinfoMapper.selectAllByNull();
        if (proProjectinfoList == null) {
            return null;
        }

        for (ProProjectinfo proProjectinfo : proProjectinfoList) {
            Tree treeOne = new Tree();

            treeOne.setId(proProjectinfo.getId());
            treeOne.setText(proProjectinfo.getProName());            
            List<ProRProjectSection> proRProjectSectionList = proProjectinfoSectionMapper.getSectionByProId(proProjectinfo.getId());

            if (proRProjectSectionList == null) {
                treeOne.setState("closed");
            } else {
                List<Tree> treeTwoList = new ArrayList<Tree>();

                for (ProRProjectSection proRProjectSection : proRProjectSectionList) {
                    Tree treeTwo = new Tree();

                    treeTwo.setId(proRProjectSection.getId());
                    treeTwo.setText(proRProjectSection.getSectionName());
                    /***************************************************/
                    List<ProRSectionLine> proRSectionLineList = proRSectionLineMapper.getLineBySectionId(proRProjectSection.getId());

                    if (proRSectionLineList == null) {
                        treeTwo.setState("closed");
                    } else {
                        List<Tree> treeThreeList = new ArrayList<Tree>();

                        for (ProRSectionLine proRSectionLine : proRSectionLineList) {
                            Tree treeThree = new Tree();

                            treeThree.setId(proRSectionLine.getId());
                            treeThree.setText(proRSectionLine.getLineName());
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
	
	@Override
	public Object selectTreeByUser(User user) {

		List<Tree> treeOneList = new ArrayList<Tree>();		
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
    	List<ProProjectinfo> proProjectinfoList = proProjectinfoMapper.selectListByOrgzCode(code);
    	if (proProjectinfoList == null) {
            return null;
        }

        for (ProProjectinfo proProjectinfo : proProjectinfoList) {
        Tree treeOne = new Tree();

        treeOne.setId(proProjectinfo.getId());
        treeOne.setText(proProjectinfo.getProName());            
        List<ProRProjectSection> proRProjectSectionList = proProjectinfoSectionMapper.getSectionByProId(proProjectinfo.getId());

        if (proRProjectSectionList == null) {
            treeOne.setState("closed");
        } else {
            List<Tree> treeTwoList = new ArrayList<Tree>();

            for (ProRProjectSection proRProjectSection : proRProjectSectionList) {
                Tree treeTwo = new Tree();

                treeTwo.setId(proRProjectSection.getId());
                treeTwo.setText(proRProjectSection.getSectionName());
                /***************************************************/
                List<ProRSectionLine> proRSectionLineList = proRSectionLineMapper.getLineBySectionId(proRProjectSection.getId());

                if (proRSectionLineList == null) {
                    treeTwo.setState("closed");
                } else {
                    List<Tree> treeThreeList = new ArrayList<Tree>();

                    for (ProRSectionLine proRSectionLine : proRSectionLineList) {
                        Tree treeThree = new Tree();

                        treeThree.setId(proRSectionLine.getId());
                        treeThree.setText(proRSectionLine.getLineName());
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
	 *  获取所有项目
	 * @return
	 */
	public List<ProProjectinfo> getProjectInfosBylist(){
		return proProjectinfoMapper.getProjectInfosBylist();
	}

    /**
     * 获取所有的项目信息
     *
     * @return
     */
    @Override
    public List<ProProjectinfo> selectAll() {
        return proProjectinfoMapper.selectAllByNull();
    }


}