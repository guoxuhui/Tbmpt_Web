package com.crfeb.tbmpt.gpztcl.base.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.gpztcl.base.model.GpztclSczxpc;
import com.crfeb.tbmpt.gpztcl.base.model.dto.GpztclSczxpcDto;

/**
 * <p>实测中线信息管理Mapper</p>
 * <p>系统：管片姿态测量系统</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-12-20</p>
 * @version 1.0
 * @author YangYj
 */
public interface GpztclSczxpcMapper extends CommonMapper<GpztclSczxpc>{

    List<GpztclSczxpc> selectGpztclSczxpcList(Page<GpztclSczxpc> page,@Param("condition")Map<String, Object> condition) throws Exception;
    
    /**
     * 方法说明： 分页查询实测中线信息
     * @param page 分页信息
     * @param condition 查询参数
     * @return  返回实测中线dto list 集合
     * @throws Exception
     * @author:YangYj
     * @Time: 2016年12月21日 下午3:12:04
     */
    List<GpztclSczxpcDto> selectGpztclSczxpcDtoList(Page<GpztclSczxpc> page,@Param("condition")Map<String, Object> condition) throws Exception;
    
    /**
     * 方法说明：查询一条dto
     * @param id 主键值
     * @return 返回实测中线一条dto数据对象
     * @throws Exception
     * @author:YangYj
     * @Time: 2016年12月24日 下午9:33:00
     */
    public GpztclSczxpcDto selectDtoById(@Param("id")String id) throws Exception;
    
  
    /**
     * 方法说明：更新上报状态
     * @param ids 物理主键串，以逗号隔开，每个id用单引号括起来例如：'id1','id2'......
     * @param sendState 上报状态
     * @throws Exception
     * @author:YangYj
     * @Time: 2016年12月24日 下午1:55:47
     */
    public void  updateSbStateByIds(@Param("ids") String ids,@Param("sendState") String sendState) throws Exception;

}