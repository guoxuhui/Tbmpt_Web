package com.crfeb.tbmpt.gpztcl.base.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.CommonMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.gpztcl.base.model.GpztclSczxInfo;
import com.crfeb.tbmpt.gpztcl.base.model.dto.GpztclSczxInfoDto;

/**
 * <p>实测中线信息明细管理Mapper</p>
 * <p>系统：管片姿态测量系统</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-12-20</p>
 * @version 1.0
 * @author YangYj
 */
public interface GpztclSczxInfoMapper extends CommonMapper<GpztclSczxInfo>{

    List<GpztclSczxInfoDto> selectGpztclSczxInfoList(Page<GpztclSczxInfo> page,@Param("condition")Map<String, Object> condition) throws Exception;
    
    /**
     * 方法说明：根据主表id删除明细信息 
     * @param fids 主表id串 ，可传多个主表id ，每个id用单引号引起来，id之间用逗号分隔；例如：'fid1','fid2'......
     * @author:YangYj
     * @Time: 2016年12月23日 下午5:40:53
     */
    public void deleteByFids(@Param("fids") String fids);

}