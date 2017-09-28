package com.crfeb.tbmpt.sys.service;



import java.util.List;

import com.baomidou.framework.service.ICommonService;
import com.crfeb.tbmpt.commons.result.Tree;
import com.crfeb.tbmpt.sys.model.Region;
/**
 *
 * Region 表数据服务层接口
 *
 */
public interface IRegionService extends ICommonService<Region> {
   
	 /***
     * 树形  查询
     * @return
     */
    public List<Tree> selectTree();

    /***
     * 查询 所有 地区
     * @return
     */
   
    public List<Region> selectTreeGrRegion();
     
    /***
	 * 添加 新 地区
	 * @param region
	 * @return
	 */
    public int save(Region region);
    
    /***
     * 保存 修改
     */
    public boolean editRegion(Region region);
   
    /***
     * 删除 机制
     */
    /***
     * 方式 1
     * 删除 地区 对象 前 把 该对象 下的 子对象 的 父级Pid 改为空；
     * 
     * @param id
     * @return
     */
    public boolean deleteRegion1(String id);
    
    /***
     * 判断 该对象是否有 子对象 
     * @param id
     * @return
     */
    public int deleteByRid(String id);

     /***
      * 方式 2
      * 删除 地区 对象 前 把 该对象 下的 子对象 全部 删除
      *  
      * @param id
      * @return
      */
    public boolean deleteRegion(String id);
}