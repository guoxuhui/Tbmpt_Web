package com.crfeb.tbmpt.sys.base.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.sys.base.model.SysFujian;

/**
 * <p>公共统一附件Mapper</p>
 * <p>系统：系统管理</p>
 * <p>模块：公共模块</p>
 * <p>日期：2017-02-17</p>
 * @version 1.0
 * @author wangbinbin
 */
public interface SysFujianMapper extends CommonMapper<SysFujian>{

    List<SysFujian> selectSysFujianList(Page<SysFujian> page,@Param("condition")Map<String, Object> condition) throws Exception;

}