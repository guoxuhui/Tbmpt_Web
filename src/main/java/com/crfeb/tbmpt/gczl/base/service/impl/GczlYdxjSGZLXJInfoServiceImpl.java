package com.crfeb.tbmpt.gczl.base.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.base.BaseService;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.gczl.base.mapper.GczlOpenPushMapper;
import com.crfeb.tbmpt.gczl.base.mapper.GczlYdxjSGZLJtwzMapper;
import com.crfeb.tbmpt.gczl.base.mapper.GczlYdxjSGZLSgnrMapper;
import com.crfeb.tbmpt.gczl.base.mapper.GczlYdxjSGZLXJInfoMapper;
import com.crfeb.tbmpt.gczl.base.mapper.GczlYdxjSGZLZlqxMapper;
import com.crfeb.tbmpt.gczl.base.model.GczlOpenPush;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLJtwz;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLSgnr;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLXJInfo;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLZlqx;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjSGZLXJInfoDto;
import com.crfeb.tbmpt.gczl.base.service.GczlYdxjSGZLXJInfoService;
import com.crfeb.tbmpt.open.service.IOpenPushService;
import com.crfeb.tbmpt.project.mapper.ProFbgcInfoMapper;
import com.crfeb.tbmpt.project.mapper.ProProjectinfoMapper;
import com.crfeb.tbmpt.project.mapper.ProRProjectSectionMapper;
import com.crfeb.tbmpt.project.mapper.ProRSectionLineMapper;
import com.crfeb.tbmpt.project.model.ProFbgcInfo;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.sys.mapper.OrgzMapper;
import com.crfeb.tbmpt.sys.mapper.SysEmployeeMapper;
import com.crfeb.tbmpt.sys.mapper.UserMapper;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.SysEmployee;
import com.crfeb.tbmpt.sys.model.User;
import com.mysql.jdbc.TimeUtil;

import oracle.net.aso.g;
/**
 * <p>结构施工质量巡检管理Service实现类</p>
 * <p>系统：</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-11-24</p>
 * @version 1.0
 * @author wangbinbin
 */
@Service
public class GczlYdxjSGZLXJInfoServiceImpl extends CommonServiceImpl<GczlYdxjSGZLXJInfoMapper, GczlYdxjSGZLXJInfo> implements GczlYdxjSGZLXJInfoService{

    @Autowired
    private GczlYdxjSGZLXJInfoMapper gczlYdxjSGZLXJInfoMapper;
    
    @Autowired
    private ProProjectinfoMapper proProjectinfoMapper;  
    @Autowired
    private ProRProjectSectionMapper proRProjectSectionMapper;
    @Autowired
    private ProRSectionLineMapper proRSectionLineMapper;
    @Autowired
    private ProFbgcInfoMapper proFbgcInfoMapper;
    @Autowired
    private GczlYdxjSGZLSgnrMapper gczlYdxjSGZLSgnrMapper;
    @Autowired
    private GczlYdxjSGZLJtwzMapper gczlYdxjSGZLJtwzMapper;
    @Autowired
    private GczlYdxjSGZLZlqxMapper gczlYdxjSGZLZlqxMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private OrgzMapper orgzMapper;
    @Autowired
    private GczlOpenPushMapper gczlOpenPushMapper;
    @Autowired
    private SysEmployeeMapper sysEmployeeMapper;
    @Resource
	private IOpenPushService iOpenPushService;

    
    private String resUrl = "/gczl/base/gczlYdxjSGZLXJInfo";//模块菜单url
    @Override
    public void selectDataGrid(PageInfo pageInfo) {
       Page<GczlYdxjSGZLXJInfo> page = new Page<GczlYdxjSGZLXJInfo>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
//       List<GczlYdxjSGZLXJInfo> list = gczlYdxjSGZLXJInfoMapper.selectGczlYdxjSGZLXJInfoList(page,condition);
       List<GczlYdxjSGZLXJInfoDto> list = gczlYdxjSGZLXJInfoMapper.selectGczlYdxjSGZLXJInfoDtoList(page,condition, pageInfo.getSort(), pageInfo.getOrder());
//       List<GczlYdxjSGZLXJInfoDto> dtoList = new ArrayList<GczlYdxjSGZLXJInfoDto>();
//       GczlYdxjSGZLXJInfoDto target = null;
//       for(GczlYdxjSGZLXJInfo entity :list){
//    	   target = new GczlYdxjSGZLXJInfoDto();
//    	   this.BeanUtilsEntityToDto(entity, target);
//    	   dtoList.add(target);
//       }
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public void save(GczlYdxjSGZLXJInfo gczlYdxjSGZLXJInfo) {
    	  gczlYdxjSGZLXJInfoMapper.insert(gczlYdxjSGZLXJInfo);
    }

	   @Override
    public void del(String[] ids) {
		  List<String> idList = Arrays.asList(ids);
		  gczlYdxjSGZLXJInfoMapper.deleteBatchIds(idList);
    }

    @Override
    public String update(GczlYdxjSGZLXJInfoDto dto,User user) {
    	    GczlYdxjSGZLXJInfo oldEntity = this.gczlYdxjSGZLXJInfoMapper.selectById(dto.getId());
			String errorMessage = BaseService.operatorMessage(oldEntity, dto, user);
			if(StringUtils.isNotBlank(errorMessage)) return errorMessage;
			BeanUtils.copyProperties(dto, oldEntity);
		    int i = gczlYdxjSGZLXJInfoMapper.updateById(oldEntity);
            if(i<1){
            	return "更新数据失败，请重试";
            }
		    return "";
    }

	   @Override
	   public GczlYdxjSGZLXJInfo findById(String id) {
		   return gczlYdxjSGZLXJInfoMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(GczlYdxjSGZLXJInfoDto gczlYdxjSGZLXJInfo, String[] clumNames) {
		   String id = gczlYdxjSGZLXJInfo.getId();
		   Map map = BeanUtils.toMap(gczlYdxjSGZLXJInfo);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        if(clum.equals("gcBh")){
		        	columnMap.put("gc_Bh", ss);
		        }else if(clum.equals("dwgcBh")){
		        	columnMap.put("dwgc_Bh", ss);
		        }else if(clum.equals("fbGcbh")){
		        	columnMap.put("fbGc_bh", ss);
		        }else{
		        	columnMap.put(clum, ss);
		        }
		        
		        
		   }
		   List<GczlYdxjSGZLXJInfo> lists = gczlYdxjSGZLXJInfoMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (GczlYdxjSGZLXJInfo gczlYdxjSGZLXJInfo2 : lists) {
				   String newId = gczlYdxjSGZLXJInfo2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

	@Override
	public void BeanUtilsEntityToDto(GczlYdxjSGZLXJInfo source, GczlYdxjSGZLXJInfoDto target) {
		if(null != source){
			if(null == target){
				target = new GczlYdxjSGZLXJInfoDto();
			}
			BeanUtils.copyProperties(source, target);
			//=======================获取工程名称====================================//
			if(StringUtils.isNotBlank(source.getGcBh())){
				ProProjectinfo project = proProjectinfoMapper.selectById(source.getGcBh());
				if(null != project){
					target.setGcName(project.getProName());
				}
			}
			//=======================获取区间名称====================================//
			if(StringUtils.isNotBlank(source.getQjBh())){
				ProRProjectSection section = this.proRProjectSectionMapper.selectById(source.getQjBh());
				if(null != section){
					target.setQjName(section.getSectionName());
				}
			}
			//=======================获取线路名称====================================//
			if(StringUtils.isNotBlank(source.getXlBh())){
				ProRSectionLine line = this.proRSectionLineMapper.selectById(source.getXlBh());
				if(null != line){
					target.setXlName(line.getLineName());
				}
			}
			//=======================获取分部工程名称====================================//
			if(StringUtils.isNotBlank(source.getFbGcbh())){
				ProFbgcInfo fbgc = this.proFbgcInfoMapper.selectById(source.getFbGcbh());
				if(null != fbgc){
					target.setFbGcName(fbgc.getFbgcname());
				}
			}
			//=======================获取施工内容名称====================================//
			if(StringUtils.isNotBlank(source.getSgNr())){
				GczlYdxjSGZLSgnr sgnr = this.gczlYdxjSGZLSgnrMapper.selectById(source.getSgNr());
				if(null != sgnr){
					target.setSgNrName(sgnr.getSgNr());
				}
			}
			//=======================获取具体位置名称====================================//
			if(StringUtils.isNotBlank(source.getJtWz())){
				GczlYdxjSGZLJtwz jtwz = this.gczlYdxjSGZLJtwzMapper.selectById(source.getJtWz());
				if(null != jtwz){
					target.setJtWzName(jtwz.getJtWz());
				}
			}
			//=======================获取质量缺陷名称====================================//
			if(StringUtils.isNotBlank(source.getZlQx())){
				GczlYdxjSGZLZlqx zlqx = this.gczlYdxjSGZLZlqxMapper.selectById(source.getZlQx());
				if(null != zlqx){
					target.setZlQxName(zlqx.getZlQx());
				}
			}
			//=======================获取巡检人员名称====================================//
			if(StringUtils.isNotBlank(source.getXjRy())){
				User user = this.userMapper.selectById(source.getXjRy());
				if(null != user){
					target.setXjRyName(user.getName());
				}
			}
			//=======================获取巡检人员所在部门名称====================================//
			if(StringUtils.isNotBlank(source.getXjBm())){
				Orgz org = this.orgzMapper.selectById(source.getXjBm());
				if(null != org){
					target.setXjBmName(org.getName());
				}
			}
			//=======================获取上报人员名称====================================//
			if(StringUtils.isNotBlank(source.getSbRy())){
				User user = this.userMapper.selectById(source.getSbRy());
				if(null != user){
					target.setSbRyName(user.getName());
				}
			}
			//=======================获取上报人员所在部门名称====================================//
			if(StringUtils.isNotBlank(source.getSbBm())){
				Orgz org = this.orgzMapper.selectById(source.getSbBm());
				if(null != org){
					target.setSbBmName(org.getName());
				}
			}
			//=======================获取审核人员名称====================================//
			if(StringUtils.isNotBlank(source.getShRy())){
				User user = this.userMapper.selectById(source.getShRy());
				if(null != user){
					target.setShRyName(user.getName());
				}
			}
			//=======================获取审核人员所在部门名称====================================//
			if(StringUtils.isNotBlank(source.getShBm())){
				Orgz org = this.orgzMapper.selectById(source.getShBm());
				if(null != org){
					target.setShBmName(org.getName());
				}
			}
			
			//=======================整改反馈人员名称====================================//
			if(StringUtils.isNotBlank(source.getZgFkry())){
				User user = this.userMapper.selectById(source.getZgFkry());
				if(null != user){
					target.setZgFkryName(user.getName());
				}
			}
			//=======================整改反馈人员所在部门名称====================================//
			if(StringUtils.isNotBlank(source.getZgBm())){
				Orgz org = this.orgzMapper.selectById(source.getZgBm());
				if(null != org){
					target.setZgBmName(org.getName());
				}
			}
		}
		
	}

	@Override
	public String sbInfo(String[] ids,User user) {
		GczlOpenPush gczlOpenPush = new GczlOpenPush();
		
		List<String> idList = Arrays.asList(ids);
		for (String string : idList) {
			gczlOpenPush.setId(string);
			gczlOpenPush.setUserId(user.getId());
			gczlOpenPush.setType("施工");
			gczlOpenPush.setCreateTime(DateUtils.getToday());
		}
		
		List<GczlYdxjSGZLXJInfo> entityList = gczlYdxjSGZLXJInfoMapper.selectBatchIds(idList);
		String tempIds = "";
		for (GczlYdxjSGZLXJInfo gczlYdxjSGZLXJInfo : entityList) {
			gczlYdxjSGZLXJInfo.setSbZt("已上报");
			if("已打回".equals(gczlYdxjSGZLXJInfo.getShZt())){
				gczlYdxjSGZLXJInfo.setShZt("未审核");
			}
			if(null != user){
				gczlYdxjSGZLXJInfo.setSbRy(user.getId());
				gczlYdxjSGZLXJInfo.setSbBm(user.getOrgzId());
				gczlYdxjSGZLXJInfo.setSbTime(DateUtils.getToday());
			}
			gczlYdxjSGZLXJInfo.setUpdateUser(user.getEmpId());
			gczlYdxjSGZLXJInfo.setUpdateUsername(user.getName());
			gczlYdxjSGZLXJInfo.setUpdateOrgz(user.getOrgzId());
			gczlYdxjSGZLXJInfo.setUpdateOrgzname(user.getOrgzName());
			gczlYdxjSGZLXJInfo.setUpdateTime(DateUtils.getToday());
			tempIds +=",'"+gczlYdxjSGZLXJInfo.getId()+"'";
			
			//手机端个推消息保存到数据库
			gczlOpenPush.setGczlId(gczlYdxjSGZLXJInfo.getId());
			gczlOpenPush.setNrms("施工内容："+gczlYdxjSGZLXJInfo.getSgNr()+"|具体位置："+gczlYdxjSGZLXJInfo.getJtWz());
			gczlOpenPush.setXjry(user.getName());
			gczlOpenPush.setUpdateTime(gczlYdxjSGZLXJInfo.getUpdateTime());
			
			if (gczlYdxjSGZLXJInfo.getXjZpslt() != null) {
			gczlOpenPush.setXjzpslt(gczlYdxjSGZLXJInfo.getXjZpslt());
			}
			
			//iOpenPushService.gczlPushMsg(user,resUrl, "有新的结构施工质量巡检信息已上报");
			iOpenPushService.gczlPushMsgV2("施工", "已上报", gczlYdxjSGZLXJInfo, user,"/gczl/base/gczlYdxjSGZLXJInfo", "有新的结构施工质量巡检信息已上报");
		}
		if(tempIds.length()>0){
			tempIds = tempIds.substring(1);
			 Map<String, Object> condition = new HashMap<String, Object>();
			 condition.put("sbZt", "已上报");
			 List<GczlYdxjSGZLXJInfoDto> resultList = this.gczlYdxjSGZLXJInfoMapper.selectListByIdsAndOther(tempIds, condition);
			 if(null != resultList && resultList.size()>0){
				 return "数据已过期,请刷新页面重试。";
			 }
		}
		gczlOpenPush.setZt("已上报");
		gczlYdxjSGZLXJInfoMapper.updateBatchById(entityList);
		
		gczlOpenPushMapper.insert(gczlOpenPush);
		
		
		return "";
	}
	
	/**上报（需填写整改人和整改截止日期）*/
	@Override
	public String sbInfo(User user,GczlYdxjSGZLXJInfoDto gczlYdxjSGZLXJInfoDto) {
		GczlOpenPush gczlOpenPush = new GczlOpenPush();
		GczlYdxjSGZLXJInfo gczlYdxjSGZLXJInfo = gczlYdxjSGZLXJInfoMapper.selectById(gczlYdxjSGZLXJInfoDto.getId());
		
		gczlYdxjSGZLXJInfo.setSbZt("已上报");
		if("已打回".equals(gczlYdxjSGZLXJInfo.getShZt())){
			gczlYdxjSGZLXJInfo.setShZt("未审核");
		}
		if(null != user){
			gczlYdxjSGZLXJInfo.setSbRy(user.getId());
			gczlYdxjSGZLXJInfo.setSbBm(user.getOrgzId());
			gczlYdxjSGZLXJInfo.setSbTime(DateUtils.getToday());
		}
		gczlYdxjSGZLXJInfo.setUpdateUser(user.getEmpId());
		gczlYdxjSGZLXJInfo.setUpdateUsername(user.getName());
		gczlYdxjSGZLXJInfo.setUpdateOrgz(user.getOrgzId());
		gczlYdxjSGZLXJInfo.setUpdateOrgzname(user.getOrgzName());
		gczlYdxjSGZLXJInfo.setUpdateTime(DateUtils.getToday());
		
		gczlYdxjSGZLXJInfo.setZgRy(gczlYdxjSGZLXJInfoDto.getZgRy());
		gczlYdxjSGZLXJInfo.setZgjzTime(gczlYdxjSGZLXJInfoDto.getZgjzTime());
		
		gczlYdxjSGZLXJInfoMapper.updateById(gczlYdxjSGZLXJInfo);
			
		//手机端个推消息保存到数据库
		gczlOpenPush.setId(gczlYdxjSGZLXJInfoDto.getId());
		gczlOpenPush.setUserId(user.getId());
		gczlOpenPush.setType("施工");
		gczlOpenPush.setCreateTime(DateUtils.getToday());
		gczlOpenPush.setGczlId(gczlYdxjSGZLXJInfo.getId());
		gczlOpenPush.setNrms("施工内容："+gczlYdxjSGZLXJInfo.getSgNr()+"|具体位置："+gczlYdxjSGZLXJInfo.getJtWz());
		gczlOpenPush.setXjry(user.getName());
		gczlOpenPush.setUpdateTime(gczlYdxjSGZLXJInfo.getUpdateTime());
			
		if (gczlYdxjSGZLXJInfo.getXjZpslt() != null) {
		gczlOpenPush.setXjzpslt(gczlYdxjSGZLXJInfo.getXjZpslt());
		
		}
		
		gczlOpenPush.setZt("已上报");
		
		//iOpenPushService.gczlPushMsg(user,resUrl, "有新的结构施工质量巡检信息已上报");
		iOpenPushService.gczlPushMsgV2("施工", "已上报", gczlYdxjSGZLXJInfo, user,"/gczl/base/gczlYdxjSGZLXJInfo", "有新的结构施工质量巡检信息已上报");
		gczlOpenPushMapper.insert(gczlOpenPush);
		
		
		return "";
	}
	
	
	@Override
	public List<GczlYdxjSGZLXJInfoDto> selectDtoBatchIds(List<String> idList) {
		List<GczlYdxjSGZLXJInfo> list = this.selectBatchIds(idList);
		List<GczlYdxjSGZLXJInfoDto> dtoList = new ArrayList<GczlYdxjSGZLXJInfoDto>();
		if(null != list && list.size()>0){
			GczlYdxjSGZLXJInfoDto dto = null;
			for(GczlYdxjSGZLXJInfo entity:list){
				dto = new GczlYdxjSGZLXJInfoDto();
				 this.BeanUtilsEntityToDto(entity, dto);
				 dtoList.add(dto);
			}
		}
		return dtoList;
	}

	@Override
	public String shInfo(GczlYdxjSGZLXJInfoDto gczlYdxjSGZLXJInfo,User user) {
		GczlYdxjSGZLXJInfo newEntity = new GczlYdxjSGZLXJInfo();
    	newEntity = this.selectById(gczlYdxjSGZLXJInfo.getId());
    	newEntity.setShRy(user.getId());
    	newEntity.setShBm(user.getOrgzId());
    	newEntity.setShTime(DateUtils.getToday());
    	newEntity.setShZt(gczlYdxjSGZLXJInfo.getShZt());
    	newEntity.setShSm(gczlYdxjSGZLXJInfo.getShSm());
    	newEntity.setZgRy(gczlYdxjSGZLXJInfo.getZgRy());
    	
    	//设置整改时间
    	String zgjzTime = gczlYdxjSGZLXJInfo.getZgjzTime();
    	zgjzTime = zgjzTime.replace("T", " ");
    	if (zgjzTime.length() < 19) {
    		zgjzTime = zgjzTime + ":00";
    	} else if (zgjzTime.length() > 19) {
    		zgjzTime = zgjzTime.substring(0, 19);
    	}
    	newEntity.setZgjzTime(zgjzTime);
    			
    			
    	if("已打回".equals(gczlYdxjSGZLXJInfo.getShZt())){
    		newEntity.setSbZt("未上报");
    	}
    	newEntity.setUpdateTime(gczlYdxjSGZLXJInfo.getUpdateTime());
    	GczlYdxjSGZLXJInfoDto dto = new GczlYdxjSGZLXJInfoDto();
    	BeanUtils.copyProperties(newEntity, dto);
    	String errorMessage = this.update(dto,user);
    	if(StringUtils.isNotBlank(errorMessage)) return errorMessage;
    	String qjName="";
    	String xlName = "";
    	//=======================获取区间名称====================================//
		if(StringUtils.isNotBlank(newEntity.getQjBh())){
			ProRProjectSection section = this.proRProjectSectionMapper.selectById(newEntity.getQjBh());
			if(null != section){
				qjName = section.getSectionName();
			}
		}
		//=======================获取线路名称====================================//
		if(StringUtils.isNotBlank(newEntity.getXlBh())){
			ProRSectionLine line = this.proRSectionLineMapper.selectById(newEntity.getXlBh());
			if(null != line){
				xlName = line.getLineName();
			}
		}
    	//iOpenPushService.gczlPushMsg(user,resUrl,qjName+xlName+"有新的结构施工质量巡检信息"+newEntity.getShZt());
    	iOpenPushService.gczlPushMsgV2("施工", newEntity.getShZt(), gczlYdxjSGZLXJInfo, user,"/gczl/base/gczlYdxjSGZLXJInfo", "有新的结构施工质量巡检信息"+newEntity.getShZt());
    	//手机端个推消息保存到数据库
    	GczlOpenPush gczlOpenPush = new GczlOpenPush();
    	gczlOpenPush.setGczlId(gczlYdxjSGZLXJInfo.getId());
    	gczlOpenPush.setCreateTime(DateUtils.getToday());
    	gczlOpenPush.setUserId(user.getId());
    	gczlOpenPush.setType("施工");
    	gczlOpenPush.setZt("已审核");
    	GczlYdxjSGZLXJInfo gczlYdxjSGZLXJInfoOld = gczlYdxjSGZLXJInfoMapper.selectById(gczlYdxjSGZLXJInfo.getId());
    	gczlOpenPush.setNrms("施工内容："+gczlYdxjSGZLXJInfoOld.getSgNr()+"|具体位置："+gczlYdxjSGZLXJInfoOld.getJtWz());
    	gczlOpenPush.setXjzpslt(gczlYdxjSGZLXJInfo.getXjZpslt());
    	gczlOpenPush.setXjry(user.getName());
    	gczlOpenPush.setUpdateTime(gczlYdxjSGZLXJInfo.getUpdateTime());
    	gczlOpenPushMapper.insert(gczlOpenPush);
    	return "";
	}

	@Override
	public String zgInfo(GczlYdxjSGZLXJInfoDto gczlYdxjSGZLXJInfo, User user) {
		GczlYdxjSGZLXJInfo newEntity = new GczlYdxjSGZLXJInfo();
    	newEntity = this.selectById(gczlYdxjSGZLXJInfo.getId());
    	newEntity.setZgFkry(user.getId());
    	newEntity.setZgBm(user.getOrgzId());
    	newEntity.setZgfkTime(DateUtils.getToday());
    	newEntity.setZgJg(gczlYdxjSGZLXJInfo.getZgJg());
    	newEntity.setZgZt(gczlYdxjSGZLXJInfo.getZgZt());
    	newEntity.setZgTime(gczlYdxjSGZLXJInfo.getZgTime());
    	
    	GczlYdxjSGZLXJInfoDto dto = new GczlYdxjSGZLXJInfoDto();
    	BeanUtils.copyProperties(newEntity, dto);
    	String errorMessage = this.update(dto,user);
    	if(StringUtils.isNotBlank(errorMessage)) return "数据整改反馈失败,请重试。";
    	String qjName="";
    	String xlName = "";
    	//=======================获取区间名称====================================//
		if(StringUtils.isNotBlank(newEntity.getQjBh())){
			ProRProjectSection section = this.proRProjectSectionMapper.selectById(newEntity.getQjBh());
			if(null != section){
				qjName = section.getSectionName();
			}
		}
		//=======================获取线路名称====================================//
		if(StringUtils.isNotBlank(newEntity.getXlBh())){
			ProRSectionLine line = this.proRSectionLineMapper.selectById(newEntity.getXlBh());
			if(null != line){
				xlName = line.getLineName();
			}
		}
    	//iOpenPushService.gczlPushMsg(user,resUrl,qjName+xlName+"有新的结构施工质量巡检信息"+newEntity.getZgZt());
    	iOpenPushService.gczlPushMsgV2("施工", newEntity.getShZt(), gczlYdxjSGZLXJInfo, user,"/gczl/base/gczlYdxjSGZLXJInfo", "有新的结构施工质量巡检信息"+newEntity.getShZt());
    	//手机端个推消息保存到数据库
    	GczlOpenPush gczlOpenPush = new GczlOpenPush();
    	gczlOpenPush.setGczlId(gczlYdxjSGZLXJInfo.getId());
    	gczlOpenPush.setCreateTime(DateUtils.getToday());
    	gczlOpenPush.setUserId(user.getId());
    	gczlOpenPush.setType("施工");
    	gczlOpenPush.setZt("已整改");
    	GczlYdxjSGZLXJInfo gczlYdxjSGZLXJInfoOld = gczlYdxjSGZLXJInfoMapper.selectById(gczlYdxjSGZLXJInfo.getId());
    	gczlOpenPush.setNrms("施工内容："+gczlYdxjSGZLXJInfoOld.getSgNr()+"|具体位置："+gczlYdxjSGZLXJInfoOld.getJtWz());
    	gczlOpenPush.setXjzpslt(gczlYdxjSGZLXJInfo.getXjZpslt());
    	gczlOpenPush.setXjry(user.getName());
    	gczlOpenPush.setUpdateTime(gczlYdxjSGZLXJInfo.getUpdateTime());
    	gczlOpenPushMapper.insert(gczlOpenPush);
		return "";
	}
	@Override
	public String insert(GczlYdxjSGZLXJInfoDto dto, User user) {
		GczlYdxjSGZLXJInfo entity = new GczlYdxjSGZLXJInfo();
    	BeanUtils.copyProperties(dto, entity);
    	BaseService.operatorMessage(entity, null,user );
    	int i = this.gczlYdxjSGZLXJInfoMapper.insert(entity);
    	if(i<1){
    		return "新增数据失败";
    	}
    	return "";
		
	}

	@Override
	public List<GczlYdxjSGZLXJInfoDto> selectListByIdsAndOther(String ids, Map<String, Object> condition) {
		return this.gczlYdxjSGZLXJInfoMapper.selectListByIdsAndOther(ids, condition);
	}

	@Override
	public int countGczlYdxjSGZLXJInfoStaNum(String gcbh, String type) {
		if(StringUtils.isBlank(gcbh)){
			return 0;
		}
		if(StringUtils.isBlank(type)){
			return 0;
		}
		Map<String,Object> condition = new HashMap<String,Object>();
		condition.put("gcBh", gcbh);
		condition.put("type", type);
		return this.gczlYdxjSGZLXJInfoMapper.countGczlYdxjSGZLXJInfoStaNum(condition);
	}

}