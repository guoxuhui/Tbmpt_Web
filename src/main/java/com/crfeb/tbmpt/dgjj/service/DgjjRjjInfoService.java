package com.crfeb.tbmpt.dgjj.service;

import java.io.IOException;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.baomidou.framework.service.ICommonService; 
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.dgjj.model.DgjjBzgl;
import com.crfeb.tbmpt.dgjj.model.DgjjRjjInfo;
import com.crfeb.tbmpt.dgjj.model.dto.DgjjRjjInfoDto;
import com.crfeb.tbmpt.sys.model.User; 

/**
 * <p>日掘进信息管理Service接口</p>
 * <p>系统：盾构掘进参数管理系统</p>
 * <p>模块：基础模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author wpg
 */
public interface DgjjRjjInfoService extends ICommonService<DgjjRjjInfo>{

	
    
    /**
     * 获取 项目、区间、线路 信息列表
     * @author wpg
     * @param pageInfo
     * @throws Exception
     */
    void selectDataGridParent(PageInfo pageInfo,User user) throws Exception;

    /**
     * 根据 线路Id 查询 班组信息  返回   班组集合
     * @param xlBh
     * @return
     * @throws Exception
     */
    List<DgjjBzgl> selecDgjjBzgltListByXlbh(String xlBh) throws Exception;
    
   
    /**
     *  解析 Excle 文件 日掘进信息   
     *  返回  日掘进 列表类集合; 
     */
    List<DgjjRjjInfo> readXlsxIs(MultipartFile file)throws IOException;
    
    
    /**
     * 根据线路编号 分页  查询 日掘进信息  
     * @param pageInfo 分页工具类
     * @throws Exception
     */ 
    void selectDgjjRjjInfoListByXlBh(PageInfo pageInfo) throws Exception;
    
    
    /***
     * 作用：编辑日掘进信息，查询 单条班组、日掘进信息
     * @param id 日掘进Id
     * @return
     * @throws Exception
     */
    DgjjRjjInfoDto selectDgjjRjj(String id) ;
  
    /***
     * Excel 数据导出  的时候 用到  
     * @param pageInfo 分页工具类
     */
    void selectDataGrid(PageInfo pageInfo) throws Exception;
    
    
}