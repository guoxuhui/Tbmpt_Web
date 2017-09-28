package com.crfeb.tbmpt.gcaqxj.mapper;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.aqxj.model.AqxjXjdfl;
import com.crfeb.tbmpt.gcaqxj.model.AqxjXjflInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 *
 * AqxjXjflInfo 表数据库控制层接口
 * AqxjXjflInfoMapper
 */
public interface AqxjXjflInfoMapper extends CommonMapper<AqxjXjflInfo> {

    /**
     * 按照指定条件分页查询巡检点分类信息
     * @param page
     * @param condition
     * @return
     */
    List<AqxjXjflInfo> selectAqxjXjdflList(Page<AqxjXjflInfo> page, @Param("condition") Map<String, Object> condition);

    /**
     * 查询所有父类巡检点信息
     * @return
     */
    List<AqxjXjflInfo> selectAqxjXjdflParentList( @Param("proId")String proId);

    /**
     * 根据父节点获取子节点
     * @param parentId
     * @return
     */
    List<AqxjXjflInfo>  selectListByParnetId(String parentId);

    /**
     * 查询所有巡检分类信息
     * @return
     */
    List<AqxjXjflInfo> selectAqxjXjdflAllList( @Param("proiectId")String proId);

    List<AqxjXjflInfo> selectByGcId(String proId);

    /**
     * 根据当前工程名查询该工程下分类信息
     * @param currentProName
     * @return
     */
    List<AqxjXjflInfo> flList(@Param("currentProName")String currentProName);


    /**
     * 根据当前工程名查询该工程下父分类信息

     * @param proName
     * @return
     */
    List<AqxjXjflInfo> selectParentList(@Param("currentProName")String proName);

    /**
     * 根据分类名 工程名查询分类信息
     * @param typeName
     * @param currentProName
     * @return
     */
    AqxjXjflInfo selectByTypeNameAndProName(@Param("typeName")String typeName,@Param("currentProName") String  currentProName);

}