package com.crfeb.tbmpt.sys.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.crfeb.tbmpt.sys.model.Log; 

/**
 *
 * SysLog 表数据库控制层接口
 *
 */
public interface LogMapper extends CommonMapper<Log> {

    List<Log> selectSysLogList(Pagination page);
    

    /***
     * 日志列表 条件查询
     * @param 分页对象；
     * @param params 查询条件集合；
     * @param sort 字段名称；
     * @param order 排序方式；
     * @return 
     */
    List<Log> selectLogList(Pagination page,@Param("params") Map<String, Object> params, @Param("sort") String sort, @Param("order") String order);
    List<Log> selectAll();
    
    @Select("TRUNCATE TABLE sys_log")
    void deleteAll();
}