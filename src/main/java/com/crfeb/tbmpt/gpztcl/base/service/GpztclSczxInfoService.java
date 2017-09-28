package com.crfeb.tbmpt.gpztcl.base.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.gpztcl.base.model.GpztclSczxInfo;
import com.crfeb.tbmpt.gpztcl.base.model.dto.GpztclSczxInfoDto;
import com.crfeb.tbmpt.gpztcl.base.model.dto.GpztclSczxpcDto;
import com.crfeb.tbmpt.gpztcl.base.model.dto.TextFileDto;
import com.crfeb.tbmpt.sys.model.User;

/**
 * <p>实测中线信息明细管理Service接口</p>
 * <p>系统：管片姿态测量系统</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-12-20</p>
 * @version 1.0
 * @author YangYj
 */
public interface GpztclSczxInfoService extends ICommonService<GpztclSczxInfo>{

    void selectDataGrid(PageInfo pageInfo) throws Exception;

    /**
     * 新增实测中线信息明细管理
     * @param gpztclSczxInfo 要保存的实体
     */
    String save(GpztclSczxInfoDto gpztclSczxInfoDto,User user) throws Exception;

    /**
     * 通过集合删除实测中线信息明细管理
     * @param ids String字符串，中间用“,”间隔开
     */
    String del(String[] ids,User user) throws Exception;

    /**     * 修改实测中线信息明细管理
     * @param gpztclSczxInfo 要修改的实体
     */
    String update(GpztclSczxInfoDto gpztclSczxInfoDto,User user) throws Exception;

   /**
     * 通过ID查找实测中线信息明细管理
     * @param id 数据ID
     */
    GpztclSczxInfo findById(String id) throws Exception;

	/**
	 * 校验当前字段是否已存在
	 * @param gpztclSczxInfo 实体信息
	 * @param clumName 需校验的字段
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(GpztclSczxInfoDto gpztclSczxInfoDto, String[] clumNames) throws Exception;
	
	/**
	 * 方法说明：解析text文件并封装成java对象集合返回
	 * @param file text文本文件
	 * @return  封装成java对象集合返回
	 * @throws Exception
	 * @author:YangYj
	 * @Time: 2016年12月22日 上午10:19:37
	 */
	public List<TextFileDto> parseTextFile(MultipartFile file) throws Exception;
	
	
	/**
	 * 方法说明：将解析的文件列按照自定义进行调整，拼装成对应的实测中线明细数据
	 * @param dto 配置信息
	 * @return 返回对应的实测中线数据集合
	 * @throws Exception
	 * @author:YangYj
	 * @Time: 2016年12月22日 下午3:48:23
	 */
	public List<GpztclSczxInfoDto> configParseQzyData(GpztclSczxInfoDto dto)  throws Exception;
	/**
	 * 方法说明：将数组转换成dto对象
	 * @param array 数组
	 * @param dto java对象
	 * @author:YangYj
	 * @Time: 2016年12月22日 上午11:23:08
	 */
	public void arrayToDto(String[] array,TextFileDto dto);
	
	/**
	 * 方法说明：解析导向系统db文件
	 * @param file  导向系统db文件
	 * @param dto 带有全站仪数据的数据集合
	 * @return 返回完整的数据集合
	 * @throws Exception
	 * @author:YangYj
	 * @Time: 2016年12月26日 下午12:28:02
	 */
	public List<GpztclSczxInfoDto> parseDaoXiangDbFile(MultipartFile file,GpztclSczxpcDto dto) throws Exception;
	
	/**
	 * 方法说明：计算线路中心线设计坐标
	 * @param dto 参数对象
	 * @param lineId 线路id
	 * @return 返回完整的数据集合
	 * @throws Exception
	 * @author:YangYj
	 * @Time: 2016年12月27日 下午6:41:31
	 */
	public List<GpztclSczxInfoDto> countZb(GpztclSczxInfoDto dto,String lineId) throws Exception;
	
	/**
	 * 方法说明：创建Workbook
	 * @param exportDto 系统封装的导出对象dto
	 * @param resultList 数据集合
	 * @return  返回创建的 的 Workbook
	 * @throws Exception
	 * @author:YangYj
	 * @Time: 2016年12月28日 上午10:42:01
	 */
	public  HSSFWorkbook myCreateWorkbook(ExportDto exportDto, List<Map<String, Object>> resultList) throws Exception;
	
	/**
	 * 方法说明：根据列表map输出Excel文件
	 * @param response
	 * @param exportDto 系统封装的导出对象dto
	 * @param resultList 数据集合
	 * @throws Exception
	 * @author:YangYj
	 * @Time: 2016年12月28日 上午10:44:04
	 */
	public  void myWriteExcelToClient(HttpServletResponse response, ExportDto exportDto,List<Map<String, Object>> resultList) throws Exception;

}