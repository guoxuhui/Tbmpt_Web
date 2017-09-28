package com.crfeb.tbmpt.gcaqxj.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.base.BaseService;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.gcaqxj.mapper.AqxjXjnrInfoMapper;
import com.crfeb.tbmpt.gcaqxj.model.AqxjXjnrInfo;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.sys.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crfeb.tbmpt.gcaqxj.mapper.AqxjXjdInfoMapper;
import com.crfeb.tbmpt.gcaqxj.model.AqxjXjdInfo;
import com.crfeb.tbmpt.gcaqxj.service.IAqxjXjdInfoService;
import com.baomidou.framework.service.impl.CommonServiceImpl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * AqxjXjdInfo 表数据服务层接口实现类
 *
 */
@Service("aqxjXjdInfoService")
public class AqxjXjdInfoServiceImpl extends CommonServiceImpl<AqxjXjdInfoMapper, AqxjXjdInfo> implements IAqxjXjdInfoService {


    @Autowired
    AqxjXjdInfoMapper aqxjXjdInfoMapper;

    @Autowired
    AqxjXjnrInfoMapper aqxjXjnrInfoMapper;
    /**
     * 获取巡检点分页列表信息
     *
     * @param pageInfo
     * @return
     */
    @Override
    public void selectDataGrid(PageInfo pageInfo) {
        Page<AqxjXjdInfo> page = new Page<AqxjXjdInfo>(pageInfo.getNowpage(), pageInfo.getSize());
        Map<String, Object> condition = pageInfo.getCondition();
        List<AqxjXjdInfo> list = aqxjXjdInfoMapper.selectAqxjXjdGLList(page,condition);
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }

    /**
     * 添加
     *
     * @param aqxjXjdInfo
     * @param user
     * @return
     */
    @Override
    public int insert(AqxjXjdInfo aqxjXjdInfo, User user) {
        BaseService.operatorMessage(aqxjXjdInfo, null, user);  //存储
        return this.aqxjXjdInfoMapper.insert(aqxjXjdInfo);
    }

    /**
     * 更新
     *
     * @param aqxjXjdInfo
     * @param user
     * @return
     */
    @Override
    public String update(AqxjXjdInfo aqxjXjdInfo, User user) {
        AqxjXjdInfo oldEntity = this.aqxjXjdInfoMapper.selectById(aqxjXjdInfo.getId());
        String errorMessage = BaseService.operatorMessage(oldEntity, aqxjXjdInfo, user);
        if(StringUtils.isNotBlank(errorMessage)) return errorMessage;
        BeanUtils.copyProperties(aqxjXjdInfo, oldEntity);
        int i = aqxjXjdInfoMapper.updateById(oldEntity);
        if(i<1){
            return "数据更新失败,请重试!";
        }
        return "";
    }

    @Override
    public String addCopyXunJianDian(String ids, String typeName, String typeId, ProProjectinfo project) {
        String[] idss = ids.split(",");
        try{
            List<AqxjXjdInfo> xjdInfoList = aqxjXjdInfoMapper.selectBatchIds(Arrays.asList(ids));
            for(AqxjXjdInfo src:xjdInfoList){
                String  src_id = src.getId();
                AqxjXjdInfo target = new AqxjXjdInfo();
                BeanUtils.copyProperties(src,target);
                target.setId("");
                target.setProjectid(project.getId());
                target.setProjectname(project.getProName());
                target.setTypeName(typeName);
                target.setTypeId(typeId);
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
        }catch (Exception e){
            return "复制出错!";
        }
        return "";
    }

    /**
     * 检查是否存在
     *
     * @param aqxjXjdInfo
     * @param clumNames
     * @return
     */
    @Override
    public String checkClumIfexits(AqxjXjdInfo aqxjXjdInfo, String[] clumNames) {
        String id = aqxjXjdInfo.getId();
        Map map = BeanUtils.toMap(aqxjXjdInfo);
        Map<String,Object> columnMap = new HashMap<String,Object>();
        for (String clum : clumNames){
            Object ss =  map.get(clum);
            columnMap.put(clum, ss);
        }
        List<AqxjXjdInfo> lists = aqxjXjdInfoMapper.selectByMap(columnMap);
        if(StringUtils.isBlank(id)){//新增
            if(lists!=null&&lists.size()>0)return "当前工程下已经存在同名的巡检点!";
        }else{//修改
            for (AqxjXjdInfo aqxjXjdInfo1 : lists) {
                String newId = aqxjXjdInfo1.getId();
                if(!newId.equals(id)){
                    return "当前工程下已经存在同名的巡检点!";
                }
            }
        }
        return null;
    }
}