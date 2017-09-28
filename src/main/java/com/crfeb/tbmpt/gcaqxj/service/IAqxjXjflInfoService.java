package com.crfeb.tbmpt.gcaqxj.service;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.commons.result.Tree;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.gcaqxj.model.AqxjXjflInfo;
import com.baomidou.framework.service.ISuperService;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.sys.model.User;

import java.util.List;

/**
 *
 * AqxjXjflInfo 表数据服务层接口
 *
 */
public interface IAqxjXjflInfoService extends ICommonService<AqxjXjflInfo> {

    void selectDataGrid(PageInfo pageInfo);
    /**
     *  获取所有巡检点包含子节点分类信息
     * @return
     */
    List<AqxjXjflInfo> getTypes(String proId);

    int insert(AqxjXjflInfo aqxjXjflInfo, User user);

    /**
     * 更新
     * @param aqxjXjflInfo
     * @param user
     * @return
     */
    public String update(AqxjXjflInfo aqxjXjflInfo, User user);

    /**
     * 根据父节点获取子节点
     * @param parentId
     * @return
     */
    List<AqxjXjflInfo>  selectListByParnetId(String parentId);

    /**
     * 根据父id获取巡检点分类信息
     * @param id
     * @return
     */
    List<Tree> selectflTree(String id);


    /**
     * 获取巡检点分类信息
     * @return
     */
    List<Tree> selectTree();

    List<AqxjXjflInfo> getAllTypes(String proId);

    /**
     * 复制巡检分类信息
     * @param proName
     * @param user
     * @param proProjectinfo
     * @return
     */
    boolean addCopyAqxjxjdfl(String proName, User user, ProProjectinfo proProjectinfo);

    /**
     * 检查是否存在
     * @param aqxjXjflInfo
     * @param clumNames
     * @return
     */
    String checkClumIfexits(AqxjXjflInfo aqxjXjflInfo, String[] clumNames);



}