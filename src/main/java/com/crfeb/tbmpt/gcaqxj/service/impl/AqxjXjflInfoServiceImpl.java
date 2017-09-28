package com.crfeb.tbmpt.gcaqxj.service.impl;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.aqxj.model.AqxjXjdfl;
import com.crfeb.tbmpt.commons.base.BaseService;
import com.crfeb.tbmpt.commons.result.Tree;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.gcaqxj.mapper.AqxjXjdInfoMapper;
import com.crfeb.tbmpt.gcaqxj.mapper.AqxjXjnrInfoMapper;
import com.crfeb.tbmpt.gcaqxj.model.AqxjXjdInfo;
import com.crfeb.tbmpt.gcaqxj.model.AqxjXjjlInfo;
import com.crfeb.tbmpt.gcaqxj.model.AqxjXjnrInfo;
import com.crfeb.tbmpt.gcaqxj.model.dto.AqxjXjflInfoDto;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.sys.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crfeb.tbmpt.gcaqxj.mapper.AqxjXjflInfoMapper;
import com.crfeb.tbmpt.gcaqxj.model.AqxjXjflInfo;
import com.crfeb.tbmpt.gcaqxj.service.IAqxjXjflInfoService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * AqxjXjflInfo 表数据服务层接口实现类
 *
 */
@Service("aqxjXjflInfoService")
public class AqxjXjflInfoServiceImpl extends CommonServiceImpl<AqxjXjflInfoMapper, AqxjXjflInfo> implements IAqxjXjflInfoService {

    @Autowired
    AqxjXjflInfoMapper aqxjXjflInfoMapper;

    @Autowired
    AqxjXjdInfoMapper aqxjXjdInfoMapper;

    @Autowired
    AqxjXjnrInfoMapper aqxjXjnrInfoMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) {
        Page<AqxjXjflInfo> page = new Page<AqxjXjflInfo>(pageInfo.getNowpage(), pageInfo.getSize());
        Map<String, Object> condition = pageInfo.getCondition();
        List<AqxjXjflInfo> list = aqxjXjflInfoMapper.selectAqxjXjdflList(page,condition);
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }

    @Override
    public int insert(AqxjXjflInfo aqxjXjflInfo, User user) {
        BaseService.operatorMessage(aqxjXjflInfo, null, user);  //存储
        return  this.aqxjXjflInfoMapper.insert(aqxjXjflInfo);
    }

    /**
     * 更新
     *
     * @param aqxjXjflInfo
     * @param user
     * @return
     */
    @Override
    public String update(AqxjXjflInfo aqxjXjflInfo, User user) {
        AqxjXjflInfo oldEntity = this.aqxjXjflInfoMapper.selectById(aqxjXjflInfo.getId());
        String errorMessage = BaseService.operatorMessage(oldEntity, aqxjXjflInfo, user);
        if(StringUtils.isNotBlank(errorMessage)) return errorMessage;
        BeanUtils.copyProperties(aqxjXjflInfo, oldEntity);
        int i = aqxjXjflInfoMapper.updateById(oldEntity);
        if(i<1){
            return "数据更新失败,请重试!";
        }
        return "";
    }

    /**
     *  获取所有巡检点包含子节点分类信息
     *
     * @return
     */
    @Override
    public List<AqxjXjflInfo> getTypes(String proId){
        return aqxjXjflInfoMapper.selectAqxjXjdflParentList(proId);
    }


    public List<AqxjXjflInfo> getAllTypes(String proId) {
        return aqxjXjflInfoMapper.selectAqxjXjdflAllList(proId);
    }


    /**
     * 根据父节点获取子节点
     *
     * @param parentId
     * @return
     */
    @Override
    public List<AqxjXjflInfo> selectListByParnetId(String parentId) {
        return aqxjXjflInfoMapper.selectListByParnetId(parentId);
    }

    /**
     * 根据工程id获取巡检点分类信息
     *
     * @param proId
     * @return
     */
    @Override
    public List<Tree> selectflTree(String proId) {
        List<Tree> trees = new ArrayList<Tree>();
        List<AqxjXjflInfo> aqxjFather = aqxjXjflInfoMapper.selectByGcId(proId);
        // List<AqxjXjjlInfo> aqxjFather = aqxjXjflInfoMapper.selectByGcId(proId);

        if (aqxjFather!=null && !aqxjFather.isEmpty()) {
            for (AqxjXjflInfo aqxjXjdfl : aqxjFather) {
                Tree treeOne = new Tree();

                treeOne.setId(aqxjXjdfl.getId());
                treeOne.setText(aqxjXjdfl.getTypeName());
                treeOne.setType(aqxjXjdfl.getTypeName());

                this.aqxjXjdflTree(treeOne);

                trees.add(treeOne);
            }
        }

        return trees;
    }



    private void aqxjXjdflTree(Tree treeOne){
        List<AqxjXjflInfo> aqxjSon = aqxjXjflInfoMapper.selectListByParnetId(treeOne.getId());
        /***
         * 如果 数据库 没查询到值，返回空是 :"" ,非等于：null ,
         * 所以：!=null 判断 是多余的；不起作用；
         * 所以：else { treeOne.setState("closed");} 代码段，是永远都不会被执行；
         * 因为：以下代码运行结果是正确的，所以：treeOne.setState("closed");是多余的；或错误的；
         * 如果用：regionSon.isEmpty() 判断是否为空要注释treeOne.setState("closed");
         */
        if (aqxjSon !=null && !aqxjSon.isEmpty()) {
            List<Tree> tree = new ArrayList<Tree>();
            for (AqxjXjflInfo aqxjTwo : aqxjSon) {
                Tree treeTwo = new Tree();
                treeTwo.setId(aqxjTwo.getId());
                treeTwo.setText(aqxjTwo.getTypeName());
                treeTwo.setType(aqxjTwo.getTypeName());

                this.aqxjXjdflTree(treeTwo);

                tree.add(treeTwo);
            }
            treeOne.setChildren(tree);
        }

    }


    /**
     * 获取巡检点分类信息
     *
     * @return
     */
    @Override
    public List<Tree> selectTree() {
        return null;
    }


    @Override
    public boolean addCopyAqxjxjdfl(String proName, User user, ProProjectinfo proProjectinfo) {
        //查询当前工程有无分类，如果有分类不复制
        List<AqxjXjflInfo> currFl=aqxjXjflInfoMapper.flList(proProjectinfo.getProName());
        //分类为空可以复制
        if(currFl.isEmpty()){
            //查询需要复制到当前工程的父分类
            List<AqxjXjflInfo> fatherList=aqxjXjflInfoMapper.selectParentList(proName);
            if (fatherList!=null && !fatherList.isEmpty()) {
                for (AqxjXjflInfo aqxjXjdfl : fatherList) {
                    String id1 = aqxjXjdfl.getId();
                    String typeName=aqxjXjdfl.getTypeName();
                    AqxjXjflInfo newfl=new AqxjXjflInfo();
                    //要复制的分类在当前工程不存在，可以复制
                    AqxjXjflInfo currentaqx=aqxjXjflInfoMapper.selectByTypeNameAndProName(typeName, proProjectinfo.getProName());
                    if(currentaqx==null){
                        newfl.setIsParent("0");
                        newfl.setProiectName( proProjectinfo.getProName());
                        newfl.setProiectId(proProjectinfo.getId());
                        newfl.setRemark(aqxjXjdfl.getRemark());
                        newfl.setTypeName(typeName);
                        this.insert(newfl,user);
                    }
                    /**
                     * 复制当前分类下巡检点及巡检内容开始
                     */
                    Map<String,Object> condition = new HashMap<>();
                    condition.put("typeId",id1);
                    condition.put("proiectName",proName);
                    List<AqxjXjdInfo> xjdInfoList = aqxjXjdInfoMapper.selectListByCondition(condition);
                    List<AqxjXjdInfo> targetXjdInfoList = new ArrayList<>();
                    for(AqxjXjdInfo src:xjdInfoList){
                        String  src_id = src.getId();
                        AqxjXjdInfo target = new AqxjXjdInfo();
                        BeanUtils.copyProperties(src,target);
                        target.setId("");
                        target.setProjectid(proProjectinfo.getId());
                        target.setProjectname(proProjectinfo.getProName());
                        target.setTypeName(typeName);
                        target.setTypeId(newfl.getId());
                        aqxjXjdInfoMapper.insert(target);

                        List<AqxjXjnrInfo> xjnrInfos =aqxjXjnrInfoMapper.selectAll(src_id);
                        for(AqxjXjnrInfo nrInfo_src:xjnrInfos){
                            AqxjXjnrInfo aqxjXjnrInfo = new AqxjXjnrInfo();
                            BeanUtils.copyProperties(nrInfo_src,aqxjXjnrInfo);
                            aqxjXjnrInfo.setId("");
                            aqxjXjnrInfo.setItemid(target.getId());
                            aqxjXjnrInfoMapper.insert(aqxjXjnrInfo);
                        }
                    }



                    /**
                     * 复制当前分类下巡检点及巡检内容结束
                     */
                    //复制子类
                    addCopyChildFl(id1, typeName, proProjectinfo, user);
                }
            }
            return true;
        }
        return false;
    }
    //【复制工程】复制子分类
    public void addCopyChildFl(String pid,String fenleiMc,ProProjectinfo proProjectinfo,User user){
        //查询需要复制到当前工程的子类
        List<AqxjXjflInfo> childAqxjXjdfl=aqxjXjflInfoMapper.selectListByParnetId(pid);
        //查询已经插入的分类，获取该分类的id
        AqxjXjflInfo fatherAqxjXjdfl=aqxjXjflInfoMapper.selectByTypeNameAndProName(fenleiMc, proProjectinfo.getProName());
        //复制子类
        if (childAqxjXjdfl!=null && !childAqxjXjdfl.isEmpty()) {
            for (AqxjXjflInfo child : childAqxjXjdfl) {
                String fid=fatherAqxjXjdfl.getId();
                String id2=child.getId();
                String typeName=child.getTypeName();
                AqxjXjflInfo newchild=new AqxjXjflInfo();


                newchild.setIsParent("1");
                newchild.setParentId(fid);
                newchild.setParentName(fatherAqxjXjdfl.getTypeName());
                newchild.setProiectName(proProjectinfo.getProName());
                newchild.setProiectId(proProjectinfo.getId());
                newchild.setRemark(child.getRemark());
                newchild.setTypeName(typeName);
                this.insert(newchild,user);
                /**
                 * 复制当前分类下巡检点及巡检内容开始
                 */
                Map<String,Object> condition = new HashMap<>();
                condition.put("typeId",id2);

                List<AqxjXjdInfo> xjdInfoList = aqxjXjdInfoMapper.selectListByCondition(condition);
                List<AqxjXjdInfo> targetXjdInfoList = new ArrayList<>();
                for(AqxjXjdInfo src:xjdInfoList){
                    String  src_id = src.getId();
                    AqxjXjdInfo target = new AqxjXjdInfo();
                    BeanUtils.copyProperties(src,target);
                    target.setId("");
                    target.setProjectid(proProjectinfo.getId());
                    target.setProjectname(proProjectinfo.getProName());
                    target.setTypeName(typeName);
                    target.setTypeId(newchild.getId());
                    aqxjXjdInfoMapper.insert(target);

                    List<AqxjXjnrInfo> xjnrInfos =aqxjXjnrInfoMapper.selectAll(src_id);
                    for(AqxjXjnrInfo nrInfo_src:xjnrInfos){
                        AqxjXjnrInfo aqxjXjnrInfo = new AqxjXjnrInfo();
                        BeanUtils.copyProperties(nrInfo_src,aqxjXjnrInfo);
                        aqxjXjnrInfo.setId("");
                        aqxjXjnrInfo.setItemid(target.getId());
                        aqxjXjnrInfoMapper.insert(aqxjXjnrInfo);
                    }
                }
                /**
                 * 复制当前分类下巡检点及巡检内容结束
                 */
                //this.copyChildFl(id2,typeName,proProjectinfo,user);
            }
        }
    }

    /**
     * 检查是否存在
     *
     * @param aqxjXjflInfo
     * @param clumNames
     * @return
     */
    @Override
    public String checkClumIfexits(AqxjXjflInfo aqxjXjflInfo, String[] clumNames) {
        String id = aqxjXjflInfo.getId();
        Map map = BeanUtils.toMap(aqxjXjflInfo);
        Map<String,Object> columnMap = new HashMap<String,Object>();
        for (String clum : clumNames){
            Object ss =  map.get(clum);
            columnMap.put(clum, ss);
        }
        List<AqxjXjflInfo> lists = aqxjXjflInfoMapper.selectByMap(columnMap);
        if(StringUtils.isBlank(id)){//新增
            if(lists!=null&&lists.size()>0)return "当前工程下已经存在同名的巡检分类!";
        }else{//修改
            for (AqxjXjflInfo aqxjXjflInfo1 : lists) {
                String newId = aqxjXjflInfo1.getId();
                if(!newId.equals(id)){
                    return "当前工程下已经存在同名的巡检分类!";
                }
            }
        }
        return null;
    }
}