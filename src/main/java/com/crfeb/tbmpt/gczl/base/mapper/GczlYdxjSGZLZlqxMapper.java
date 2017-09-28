package com.crfeb.tbmpt.gczl.base.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLZlqx;

/**
 * <p>结构施工质量基础数据-质量缺陷Mapper</p>
 * <p>系统：</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-11-24</p>
 * @version 1.0
 * @author wangbinbin
 */
public interface GczlYdxjSGZLZlqxMapper extends CommonMapper<GczlYdxjSGZLZlqx>{

    List<GczlYdxjSGZLZlqx> selectGczlYdxjSGZLZlqxList(Page<GczlYdxjSGZLZlqx> page,@Param("condition")Map<String, Object> condition);
    
    /**
     * 查询指定的施工内容下的质量缺陷信息
     * @param sgNrId 施工内容id
     * @return 返回质量缺陷信息集合
     */
    List<GczlYdxjSGZLZlqx> selectGczlYdxjSGZLZlqxListBySgNr(@Param("sgNrId") String sgNrId);
    
    /**
     * 根据施工内容id集合查询质量缺陷信息
     * @param sgNrIds 施工内容id串,每个id以单引号扩起来，id之间以逗号间隔
     * @return
     */
    List<GczlYdxjSGZLZlqx> selectBySgNrIds(@Param("sgNrIds") String sgNrIds);
    
    /**
     * 根据具体位置实体信息查询具体位置列表
     * @param entity 查询条件实体
     * @return 返回具体位置实体集合
     */
    List<GczlYdxjSGZLZlqx> selectList(@Param("entity") GczlYdxjSGZLZlqx entity);

}