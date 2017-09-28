package com.crfeb.tbmpt.gpztcl.base.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.gpztcl.base.model.dto.GpztclSjzxInfoDto;
import com.crfeb.tbmpt.gpztcl.base.model.GpztclSjzxInfo;

/**
 * <p>线路中心线信息管理Service接口</p>
 * <p>系统：管片姿态测量系统</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-12-20</p>
 * @version 1.0
 * @author wangbinbin
 */
public interface GpztclSjzxInfoService extends ICommonService<GpztclSjzxInfo>{

    void selectDataGrid(PageInfo pageInfo) throws Exception;
    
    /**
     * 获取父及列表
     * @param pageInfo
     * @throws Exception
     */
    void selectDataGridParent(PageInfo pageInfo) throws Exception;

    /**
     * 新增线路中心线信息管理
     * @param gpztclSjzxInfo 要保存的实体
     */
    String save(GpztclSjzxInfoDto gpztclSjzxInfoDto,User user) throws Exception;

    /**
     * 通过集合删除线路中心线信息管理
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;

    /**     * 修改线路中心线信息管理
     * @param gpztclSjzxInfo 要修改的实体
     */
    String update(GpztclSjzxInfoDto gpztclSjzxInfoDto,User user) throws Exception;

   /**
     * 通过ID查找线路中心线信息管理
     * @param id 数据ID
     */
    GpztclSjzxInfo findById(String id) throws Exception;
    
    
    /**
     * 根据线路编号查询设计中线信息
     * @param xlbh
     * @return
     * @throws Exception
     */
    List<GpztclSjzxInfo> selectGpztclSjzxInfoList(String xlbh) throws Exception;

	/**
	 * 校验当前字段是否已存在
	 * @param gpztclSjzxInfo 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(GpztclSjzxInfoDto gpztclSjzxInfoDto, String[] clumNames) throws Exception;
	
	List<GpztclSjzxInfoDto> readXlsxIs(MultipartFile file)throws IOException;

}