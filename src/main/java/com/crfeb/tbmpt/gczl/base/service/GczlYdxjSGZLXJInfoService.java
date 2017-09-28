package com.crfeb.tbmpt.gczl.base.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLXJInfo;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjSGZLSgnrDto;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjSGZLXJInfoDto;
import com.crfeb.tbmpt.sys.model.User;

/**
 * <p>结构施工质量巡检管理Service接口</p>
 * <p>系统：</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-11-24</p>
 * @version 1.0
 * @author wangbinbin
 */
public interface GczlYdxjSGZLXJInfoService extends ICommonService<GczlYdxjSGZLXJInfo>{

    void selectDataGrid(PageInfo pageInfo);

    /**
     * 新增结构施工质量巡检管理
     * @param gczlYdxjSGZLXJInfo 要保存的实体
     */
    void save(GczlYdxjSGZLXJInfo gczlYdxjSGZLXJInfo);

    /**
     * 通过集合删除结构施工质量巡检管理
     * @param ids String字符串，中间用“,”间隔开
     */
    void del(String[] ids);

    /**     * 修改结构施工质量巡检管理
     * @param gczlYdxjSGZLXJInfo 要修改的实体
     */
    public String update(GczlYdxjSGZLXJInfoDto gczlYdxjSGZLXJInfo,User user);
    String insert(GczlYdxjSGZLXJInfoDto gczlYdxjSGZLXJInfo,User user);

   /**
     * 通过ID查找结构施工质量巡检管理
     * @param id 数据ID
     */
    GczlYdxjSGZLXJInfo findById(String id);

	/**
	 * 校验当前字段是否已存在
	 * @param gczlYdxjSGZLXJInfo 实体信息
	 * @param clumName 需校验的字段(数据库字段)
	 * @return 若为null则不重复，若不为null则重复
	 */
	String checkClumIfexits(GczlYdxjSGZLXJInfoDto gczlYdxjSGZLXJInfo, String[] clumNames);
	
	  /**
     * 拷贝实体信息到dto中
     * @param source 实体信息
     * @param target dto信息
     */
    public void BeanUtilsEntityToDto(GczlYdxjSGZLXJInfo source,GczlYdxjSGZLXJInfoDto target);
    
    /**
     * 上报信息
     * @param ids 要操作的对象ID
     * @param user 操作人员信息
     * @return 返回空则代表操作成功，否则返回错误信息
     */
    String sbInfo(String[] ids,User user);
    
    /**
     * 方法说明：审核信息
     * @param gczlYdxjSGZLXJInfo 实体dto
     * @return  若成功则返回空字符串，否则返回错误信息
     * @author:YangYj
     * @Time: 2016年12月7日 下午5:33:54
     */
    public String shInfo(GczlYdxjSGZLXJInfoDto gczlYdxjSGZLXJInfo,User user);
    
    /**
     * 方法说明：整改反馈
     * @param gczlYdxjSGZLXJInfo 实体dto
     * @param user 当前登录用户
     * @return  若成功则返回空字符串，否则返回错误信息
     * @author:YangYj
     * @Time: 2016年12月7日 下午5:40:22
     */
    public String zgInfo(GczlYdxjSGZLXJInfoDto gczlYdxjSGZLXJInfo,User user);
    
    /**
     * 根据主键查询结构施工质量巡检管理内容信息，返回对应的dto集合
     * @param idList 物理主键集合
     * @return 返回对应的结构施工质量巡检dto集合
     */
    public List<GczlYdxjSGZLXJInfoDto> selectDtoBatchIds(List<String> idList);
    
    /**
     * 方法说明：根据主键串和其他条件进行查询
     * @param ids 主键串 如：'id1','id2'......
     * @param condition 其他条件,放在map 中
     * @return 返回查询列表信息
     * @author:YangYj
     * @Time: 2016年12月16日 下午2:22:27
     */
    public List<GczlYdxjSGZLXJInfoDto>  selectListByIdsAndOther(String ids,Map<String, Object> condition);
    
    
    /**
     * 方法说明：查询记录中未上报/未审核/未整改的数据记录数量
     * @param gcbh 工程编号
     * @param type 查询类型：（未上报/未审核/未整改）
     * @return  返回查询记录条数
     * @author:YangYj
     * @Time: 2016年12月16日 下午6:37:17
     */
    public int countGczlYdxjSGZLXJInfoStaNum(String gcbh,String type);
    
    /**上报（需填写整改人和整改截止日期）*/
	String sbInfo(User user, GczlYdxjSGZLXJInfoDto gczlYdxjSGZLXJInfoDto);

}