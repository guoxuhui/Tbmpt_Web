package com.crfeb.tbmpt.gczl.base.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLXJInfo;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjSGZLXJInfoDto;

/**
 * <p>结构施工质量巡检管理Mapper</p>
 * <p>系统：</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-11-24</p>
 * @version 1.0
 * @author wangbinbin
 */
public interface GczlYdxjSGZLXJInfoMapper extends CommonMapper<GczlYdxjSGZLXJInfo>{

    List<GczlYdxjSGZLXJInfo> selectGczlYdxjSGZLXJInfoList(Page<GczlYdxjSGZLXJInfo> page,@Param("condition")Map<String, Object> condition);
    
    /**
     * 方法说明：分页查询列表信息，包含外键所关联的名称显示
     * @param page 分页信息
     * @param condition  查询条件
     * @return 返回dto 集合信息
     * @author:YangYj
     * @Time: 2016年12月16日 下午3:34:37
     */
    List<GczlYdxjSGZLXJInfoDto> selectGczlYdxjSGZLXJInfoDtoList(Page<GczlYdxjSGZLXJInfo> page,@Param("condition")Map<String, Object> condition, @Param("sort") String sort, @Param("order") String order);
     
    /**
     * 方法说明：根据主键串和其他条件进行查询
     * @param ids 主键串 如：'id1','id2'......
     * @param condition 其他条件,放在map 中
     * @return 返回查询列表信息
     * @author:YangYj
     * @Time: 2016年12月16日 下午2:22:27
     */
    List<GczlYdxjSGZLXJInfoDto>  selectListByIdsAndOther(@Param("ids")String ids,@Param("condition")Map<String, Object> condition);
    
    /**
     * 方法说明：查询记录中未上报/未审核/未整改的数据记录数量
     * @param condition 参数
     * @return 返回查询记录条数
     * @author:YangYj
     * @Time: 2016年12月16日 下午6:30:37
     */
    public int countGczlYdxjSGZLXJInfoStaNum(@Param("condition")Map<String, Object> condition);

}