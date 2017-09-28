package com.crfeb.tbmpt.sys.service;

import java.util.List;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.commons.result.Tree;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.sys.model.Res;
import com.crfeb.tbmpt.sys.model.User;

/**
 *
 * Resource 表数据服务层接口
 *
 */
public interface IResService extends ICommonService<Res> {

    List<Res> selectAll();

    List<Tree> selectAllTree();

    List<Tree> selectAllTrees();

    List<Tree> selectTree(User currentUser);
    
    List<Tree> selectMenu(User currentUser);
    
    List<Tree> selectMenu2(User currentUser,String title);

    public void selectDataGrid(PageInfo pageInfo);
    
    /**
     * 所有选择树
     * @return
     */
    public List<Tree> allSelectTree();
}