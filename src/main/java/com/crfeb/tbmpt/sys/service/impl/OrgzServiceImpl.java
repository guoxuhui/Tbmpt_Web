package com.crfeb.tbmpt.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.crfeb.tbmpt.commons.result.Tree;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.sys.mapper.OrgzMapper;
import com.crfeb.tbmpt.sys.mapper.SysEmployeeMapper;
import com.crfeb.tbmpt.sys.mapper.UserMapper;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.SysEmployee;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.sys.service.IOrgzService;

/**
 *
 * orgz 表数据服务层接口实现类
 *
 */
@Service
public class OrgzServiceImpl extends CommonServiceImpl<OrgzMapper, Orgz> implements IOrgzService {

	@Autowired
	private OrgzMapper orgzMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private SysEmployeeMapper sysEmployeeMapper;

	public List<Tree> selectTree() {
		List<Tree> trees = new ArrayList<Tree>();

		List<Orgz> orgzFather = orgzMapper.selectByPIdNull();

		if (orgzFather != null) {
			for (Orgz orgzOne : orgzFather) {
				Tree treeOne = new Tree();

				treeOne.setId(orgzOne.getId());
				treeOne.setText(orgzOne.getName());
				treeOne.setIconCls(orgzOne.getIcon());
				treeOne.setType(String.valueOf(orgzOne.getType()));

				optOrgzTree(treeOne);

				trees.add(treeOne);
			}
		}
		return trees;
	}

	private void optOrgzTree(Tree treeOne) {

		List<Orgz> orgzSon = orgzMapper.selectAllByPId(treeOne.getId());
		if (orgzSon != null) {
			List<Tree> tree = new ArrayList<Tree>();
			for (Orgz orgzTwo : orgzSon) {
				Tree treeTwo = new Tree();
				treeTwo.setId(orgzTwo.getId());
				treeTwo.setText(orgzTwo.getName());
				treeTwo.setIconCls(orgzTwo.getIcon());
				treeTwo.setType(String.valueOf(orgzTwo.getType()));

				optOrgzTree(treeTwo);

				tree.add(treeTwo);
			}
			treeOne.setChildren(tree);
		} else {
			treeOne.setState("closed");
		}
	}

	public List<Orgz> selectTreeGrid() {
		return orgzMapper.selectAll();
	}

	public Orgz getOrgzInfoByUserId(String uid) {
		User user = userMapper.selectById(uid);
		return orgzMapper.selectById(user.getOrgzId());
	}

	public List<Tree> selectTreeByUser(User user) {
		List<Tree> trees = new ArrayList<Tree>();
		Orgz orgz = orgzMapper.selectById(user.getOrgzId());
		if (orgz != null) {
			Tree treeOne = new Tree();

			treeOne.setId(orgz.getId());
			treeOne.setText(orgz.getName());
			treeOne.setIconCls(orgz.getIcon());
			treeOne.setType(String.valueOf(orgz.getType()));

			optOrgzTree(treeOne);

			trees.add(treeOne);
		}
		return trees;
	}
	
	public List<Tree> selectTreeByOrgzPid(User user) {
		List<Tree> trees = new ArrayList<Tree>();
		Orgz orgz = orgzMapper.selectById(user.getOrgzId());
		if (orgz != null) {
			if(orgz.getPid()!=null){
				orgz=orgzMapper.selectById(orgz.getPid());
				Tree treeOne = new Tree();
				
				treeOne.setId(orgz.getId());
				treeOne.setText(orgz.getName());
				treeOne.setIconCls(orgz.getIcon());
				treeOne.setType(String.valueOf(orgz.getType()));
				
				optOrgzTree(treeOne);
				
				trees.add(treeOne);				
			}
		}
		return trees;
	}

	public List<Tree> selectTreeByUser(User user, String id) {
		List<Tree> trees = new ArrayList<Tree>();
		if (StringUtils.isBlank(id)) {
			Orgz orgz = orgzMapper.selectById(user.getOrgzId());
			Tree treeOne = new Tree();
			treeOne.setId(orgz.getId());
			treeOne.setText(orgz.getName());
			List<Orgz> ls = orgzMapper.selectAllByPId(orgz.getId());
			if (ls != null && ls.size() > 0) {
				treeOne.setState("closed");
			}
			treeOne.setIconCls(orgz.getIcon());
			treeOne.setType(String.valueOf(orgz.getType()));
			trees.add(treeOne);
		} else {
			List<Orgz> orgzs = orgzMapper.selectAllByPId(id);
			for (Orgz orgz : orgzs) {
				Tree treeOne = new Tree();
				treeOne.setId(orgz.getId());
				treeOne.setText(orgz.getName());
				List<Orgz> ls = orgzMapper.selectAllByPId(orgz.getId());
				if (ls != null && ls.size() > 0) {
					treeOne.setState("closed");
				}
				treeOne.setIconCls(orgz.getIcon());
				treeOne.setType(String.valueOf(orgz.getType()));
				trees.add(treeOne);
			}
		}
		return trees;
	}

	public List<Tree> selectTreeByPId(String id) {
		List<Tree> trees = new ArrayList<Tree>();

		Orgz orgz1 = orgzMapper.selectById(id);
		Tree treeOne1 = new Tree();
		treeOne1.setId(orgz1.getId());
		treeOne1.setText(orgz1.getName());
		treeOne1.setIconCls(orgz1.getIcon());
		treeOne1.setType(String.valueOf(orgz1.getType()));
		trees.add(treeOne1);

		List<Tree> treess = new ArrayList<Tree>();
		List<Orgz> orgzs = orgzMapper.selectAllByPId(orgz1.getId());
		for (Orgz orgz : orgzs) {
			Tree treeOne = new Tree();
			treeOne.setId(orgz.getId());
			treeOne.setText(orgz.getName());
			treeOne.setIconCls(orgz.getIcon());
			treeOne.setType(String.valueOf(orgz.getType()));
			treess.add(treeOne);
		}
		treeOne1.setChildren(treess);
		return trees;
	}

	/***
	 * 查询部门树 人员、子部门都为空，则不取
	 * @param treeOne
	 * @return
	 */
	public List<Tree> selectUserTreeByUser(User user) {
		List<Tree> trees = new ArrayList<Tree>();
		Orgz orgz = orgzMapper.selectById(user.getOrgzId());
		if (orgz != null) {
			boolean boo = true;
			Tree treeOne = new Tree();

			treeOne.setId(orgz.getId());
			treeOne.setText(orgz.getName());
			treeOne.setIconCls(orgz.getIcon());
			treeOne.setType(String.valueOf(orgz.getType()));
			
			
			boo = this.selectOrgzEmployeeTree(treeOne);
			if(boo){
				trees.add(treeOne);
			}
			//trees.add(treeOne);
		}
		return trees;
	}

	/***
	 * 查询部门人员树， 人员、子部门都为空，则不取
	 * @param treeOne
	 * @return
	 */
	private boolean selectOrgzEmployeeTree(Tree treeOne) {
        
		boolean boo = true;
		List<Orgz> orgzSon = orgzMapper.selectAllByPId(treeOne.getId());
		List<SysEmployee> empList = sysEmployeeMapper.selectByOrgzId(treeOne.getId());
		/*if ((orgzSon == null && orgzSon == null) ||(orgzSon !=null && orgzSon.size()<=0 && empList !=null && empList.size()<=0)) {
			treeOne.setState("closed");
		}*/
		if (orgzSon != null) {
			List<Tree> tree = new ArrayList<Tree>();

				for (Orgz orgzTwo : orgzSon) {
					Tree treeTwo = new Tree();
					treeTwo.setId(orgzTwo.getId());
					treeTwo.setText(orgzTwo.getName());
					treeTwo.setIconCls(orgzTwo.getIcon());
					treeTwo.setAttributes(orgzTwo);
					treeTwo.setType("0");
					
					boolean boo2 = this.selectOrgzEmployeeTree(treeTwo);
					if(boo2){
						tree.add(treeTwo);
					}
				}


			
			for (SysEmployee emp : empList) {
				Tree treeTwo = new Tree();
				treeTwo.setId(emp.getId());
				treeTwo.setText(emp.getName());
				treeTwo.setAttributes(emp);
				treeTwo.setType("1");
				tree.add(treeTwo);
			}
            if(tree.size()<1){
            	boo = false;
            }
			treeOne.setChildren(tree);
		} else {
			treeOne.setState("closed");
		}
		return boo;
	}

	
	public List<Tree> selectUserTreeByOrgzPid(User user) {
		List<Tree> trees = new ArrayList<Tree>();
		Orgz orgz = orgzMapper.selectById(user.getOrgzId());
		if (orgz != null) {
			if (orgz.getPid() != null) {
				orgz = orgzMapper.selectById(orgz.getPid());
				Tree treeOne = new Tree();

				treeOne.setId(orgz.getId());
				treeOne.setText(orgz.getName());
				treeOne.setIconCls(orgz.getIcon());
				treeOne.setType(String.valueOf(orgz.getType()));

				optUserTree(treeOne);

				trees.add(treeOne);

			}
		}
		return trees;
	}

	private void optUserTree(Tree treeOne) {

		List<Orgz> orgzSon = orgzMapper.selectAllByPId(treeOne.getId());
		List<SysEmployee> empList = sysEmployeeMapper.selectByOrgzId(treeOne.getId());
		if (orgzSon != null) {
			List<Tree> tree = new ArrayList<Tree>();
			for (Orgz orgzTwo : orgzSon) {
				Tree treeTwo = new Tree();
				treeTwo.setId(orgzTwo.getId());
				treeTwo.setText(orgzTwo.getName());
				treeTwo.setIconCls(orgzTwo.getIcon());
				treeTwo.setAttributes(orgzTwo);
				treeTwo.setType("0");
				optUserTree(treeTwo);
				tree.add(treeTwo);
			}

			for (SysEmployee emp : empList) {
				Tree treeTwo = new Tree();
				treeTwo.setId(emp.getId());
				treeTwo.setText(emp.getName());
				treeTwo.setAttributes(emp);
				treeTwo.setType("1");
				tree.add(treeTwo);
			}

			treeOne.setChildren(tree);
		} else {
			treeOne.setState("closed");
		}
	}

	public List<Orgz> selectTreeGridByUser(User user) {
		Orgz orgz = orgzMapper.selectById(user.getOrgzId());
		if ("admin".equals(user.getLoginName())) {
			return orgzMapper.selectAll();
		} else if (orgz == null) {
			return new ArrayList<Orgz>();
		} else {
			return orgzMapper.selectAllBycode(orgz.getCode());
		}

	}

	public int save(Orgz orgz) {
		orgz.setCreateTime(DateUtils.getToday());
		orgz.setSeq(0);
		// 0、Code；计算值
		this.code(orgz); // 计算部门编号
		orgzMapper.insert(orgz);
		return 0;
	}

	/**
	 * 编辑保存
	 * 
	 * @param orgz
	 * @return
	 */
	@Override
	public String editSave(Orgz orgz) {
		String result = "";
		if (orgz.getId().equals(orgz.getPid())) {
			return "上级部门不能是该部门自身！";
		}
		Orgz r = orgzMapper.selectById(orgz.getId()); // 获取部门
		if (r != null && !r.getPid().equals(orgz.getPid())) {// 判断 上级机构 是否被编辑

			this.code(orgz); // 计算部门编号

			List<Orgz> subordinateList = new ArrayList<Orgz>();
			subordinateList = orgzMapper.selectAllByPId(orgz.getId()); // 通过Id获取该部门下的所以子部门
			if (subordinateList != null && subordinateList.size() > 0) {
				String maxCode = "";
				for (Orgz subordinateOrgz : subordinateList) {
					if (subordinateOrgz.getId().equals(orgz.getPid())) {
						result = "上级部门不能是该部门的下级部门！"; // 上级部门不能是该部门的下级部门，不能修改；
						break;
					}
					List<Orgz> subordinateOrgzList = orgzMapper.selectAllByPId(subordinateOrgz.getId());
					if (subordinateOrgzList != null && subordinateOrgzList.size() > 0) {
						result = "该部门的下级部门超过三级，该部门不能修改上级部门！"; // 如果下级部门，还有下级部门，就不能修改；
						break;
					}
					// 计算部门编号
					if (!StringUtils.isEmpty(maxCode)) {
						if (maxCode.startsWith("0")) { // 如果
														// 编号开头是0，转换类型后，会被去掉,所以要在前面加上“0”;
							maxCode = "0" + String.valueOf(Long.parseLong(maxCode) + 1);
						} else {
							maxCode = String.valueOf(Long.parseLong(maxCode) + 1);
						}
					} else {
						maxCode = orgz.getCode() + "01";
					}
					subordinateOrgz.setCode(maxCode);

				}
			}
			if (result.equals("")) {
				this.updateById(orgz);
				if (subordinateList != null && subordinateList.size() > 0) {
					this.updateBatchById(subordinateList);
				}

			}

		} else {
			this.updateById(orgz);
		}

		return result;
	}

	/***
	 * 计算部门编号
	 * 
	 * @param orgz
	 */
	private void code(Orgz orgz) {
		String code = "";
		if (!StringUtils.isEmpty(orgz.getPid())) {
			Orgz porgz = orgzMapper.selectById(orgz.getPid());
			code = orgzMapper.selectMaxByPId(porgz.getId());
			if (!StringUtils.isEmpty(code)) {
				if (code.startsWith("0")) {
					code = "0" + String.valueOf(Long.parseLong(code) + 1);
				} else {
					code = String.valueOf(Long.parseLong(code) + 1);
				}
			} else {
				code = porgz.getCode() + "01";
			}
		} else {
			code = orgzMapper.selectMaxByPIdNull();
			if (!StringUtils.isEmpty(code)) {
				if (code.startsWith("0")) {
					code = "0" + String.valueOf(Long.parseLong(code) + 1);
				} else {
					code = String.valueOf(Long.parseLong(code) + 1);
				}
			} else {
				code = "01";
			}
		}

		orgz.setCode(code);
	}

	/**
	 * 通过Id获取组织机构信息 lw_wpg
	 * 
	 * @param uid
	 * @return
	 */
	@Override
	public Orgz selectOrgzInfoGridById(String rId) {
		return orgzMapper.selectById(rId);
	}

	/***
	 * 删除操作,判断是否有 下级部门，有则返回提示
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public String deleteOperate(String id) {

		Orgz orgz = orgzMapper.selectById(id);
		if (orgz != null && !StringUtils.isEmpty(orgz.getId())) {
			List<Orgz> subordinateList = orgzMapper.selectAllByPId(id); // 通过Id获取该部门下的所以子部门
			if (subordinateList == null || subordinateList.isEmpty()) {
				// 删除 该对象
				this.deleteById(id);
				return "";
			}
			for (Orgz subordinateOrgz : subordinateList) {

				List<Orgz> subordinateOrgzList = orgzMapper.selectAllByPId(subordinateOrgz.getId());
				if (subordinateOrgzList != null && subordinateOrgzList.size() > 0) {
					return "该部门的下级部门超过三级"; // 如果下级部门，还有下级部门，就不能删除；

				}
			}
			return "deleteOrgzTree";

		} else {
			return "删除失败！";
		}

	}

	/***
	 * wl_wpg
	 * 
	 * 2017-05-06
	 * 
	 * 删除 部门 对象 前 把 该对象 下的 子对象 全部 删除
	 * 
	 * 以免造成 脏数据；
	 */
	@Override
	public boolean deleteOrgz(String id) {
		try {
			// 查询 并 删除 子对象
			List<Orgz> subordinateList = orgzMapper.selectAllByPId(id); // 通过Id获取该部门下的所以子部门
			if (subordinateList != null && subordinateList.size() > 0) {
				List<String> idList = new ArrayList<String>();
				for (Orgz subordinateOrgz : subordinateList) {
					idList.add(subordinateOrgz.getId());

				}
				this.deleteBatchIds(idList);
			}
			// 删除 该对象
			this.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}