package com.crfeb.tbmpt.aqxj.service;

import java.util.List;
import java.util.Map;

import com.baomidou.framework.service.ICommonService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.aqxj.model.AqxjXjdfl;
import com.crfeb.tbmpt.aqxj.model.dto.AqxjXjdflDto;
import com.crfeb.tbmpt.commons.result.Tree;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjGPZLDDType;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjSGZLSgnrDto;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.User;

/**
 * <p>巡检点分类管理Service接口</p>
 * <p>系统：</p>
 * <p>模块：基础模块</p>
 * <p>日期：2017-5-17</p>
 * @version 1.0
 * @author zhuyabing
 */
public interface AqxjxjdflService extends ICommonService<AqxjXjdfl>{

	
    /***
     * 删除操作
     * @param id
     * @return
     */
    String deleteOperate(String id);
    
    
    List<AqxjXjdfl> selectByPid(String id);
    
    List<AqxjXjdfl> getAll();
    
    List<AqxjXjdfl> getFlMc(String gcmc);
    
    boolean copyAqxjxjdfl(String proName,User user,String currentProName);
    
    boolean copyFLAqxjxjdfl(User user,String currProName,String fengL,String currFengL,String id,String currId);
    /***
     * 删除巡检点树操作
     * @param id
     * @return
     */
    boolean deleteAqxj(String id);
	
    
    //void getFlMc(Tree treeOne);
    
	
	 /***
     * 树形  查询
     * @return
     */
    public List<Tree> selectTree();
    
    public List<Tree> selectAllFlMcTree();
    
    public List<Tree> selectflTree(String gcmc);
	
	void selectDataGrid(PageInfo pageInfo);

	int insert(AqxjXjdflDto dto, User user);

	
	/**
	 * 校验当前字段是否已存在
	 * @param aqxjXjdfl 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	 /**     * 修改施工内容管理
     * @param gczlYdxjSGZLSgnr 要修改的实体
     */
	
	
    public String update(AqxjXjdflDto aqxjXjdfl, User user);

	String checkClumIfexits(AqxjXjdflDto aqxjXjdfl, String[] clumNames);
	
	
	 /**
     * 根据物理主键集合删除施工内容及其对应的具体位置及质量缺陷信息
     * @param idlist 施工内容物理主键集合
     */
    public void deleteByIds(List<String> idlist);
	

    
    List<AqxjXjdflDto> selectTreeGridByUser(User user,Map<String, Object> condition);
}
