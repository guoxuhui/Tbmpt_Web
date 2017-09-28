package com.crfeb.tbmpt.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.crfeb.tbmpt.sys.model.Orgz;

/**
 *
 * orgz 表数据库控制层接口
 *
 */
public interface OrgzMapper extends CommonMapper<Orgz> {

    List<Orgz> selectByPIdNull();

    List<Orgz> selectAllByPId(@Param("pId") String pid);
    
    String selectMaxByPId(@Param("pId") String pid);
    String selectMaxByPIdNull();

    List<Orgz> selectAll();
    
    List<Orgz> selectAllBycode(@Param("code") String code);

}