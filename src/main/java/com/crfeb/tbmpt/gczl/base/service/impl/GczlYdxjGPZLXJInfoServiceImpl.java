package com.crfeb.tbmpt.gczl.base.service.impl;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.base.BaseService;
import com.crfeb.tbmpt.commons.model.BaseModel;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.gczl.base.mapper.GczlOpenPushMapper;
import com.crfeb.tbmpt.gczl.base.mapper.GczlYdxjGPZLXJInfoMapper;
import com.crfeb.tbmpt.gczl.base.model.GczlOpenPush;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjGPZLXJInfo;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjGPZLXJInfoDto;
import com.crfeb.tbmpt.gczl.base.service.GczlYdxjGPZLXJInfoService;
import com.crfeb.tbmpt.project.mapper.ProRProjectSectionMapper;
import com.crfeb.tbmpt.project.mapper.ProRSectionLineMapper;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.sys.mapper.OrgzMapper;
import com.crfeb.tbmpt.sys.mapper.SysEmployeeMapper;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.SysEmployee;
import com.crfeb.tbmpt.sys.model.User;
/**
 * <p>盾构施工质量巡检信息Service实现类</p>
 * <p>系统：</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-11-23</p>
 * @version 1.0
 * @author wangbinbin
 */
@Service
public class GczlYdxjGPZLXJInfoServiceImpl extends CommonServiceImpl<GczlYdxjGPZLXJInfoMapper, GczlYdxjGPZLXJInfo> implements GczlYdxjGPZLXJInfoService{

    @Autowired
    private GczlYdxjGPZLXJInfoMapper gczlYdxjGPZLXJInfoMapper;
    @Autowired
    private ProRProjectSectionMapper proRProjectSectionMapper;
    @Autowired
    private ProRSectionLineMapper proRSectionLineMapper;
    @Autowired
    private OrgzMapper orgzMapper;
    @Autowired
    private SysEmployeeMapper sysEmployeeMapper;
    @Autowired
    private IProProjectinfoService iproProjectinfoService;
    @Autowired
    private GczlOpenPushMapper gczlOpenPushMapper;
    
    @Override
    public void selectDataGrid(PageInfo pageInfo) {
       Page<GczlYdxjGPZLXJInfo> page = new Page<GczlYdxjGPZLXJInfo>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<GczlYdxjGPZLXJInfoDto> listDto = gczlYdxjGPZLXJInfoMapper.selectGczlYdxjGPZLXJInfoList2(page,condition, pageInfo.getSort(), pageInfo.getOrder());
       pageInfo.setRows(listDto);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public void save(GczlYdxjGPZLXJInfo gczlYdxjGPZLXJInfo) {
    	  gczlYdxjGPZLXJInfoMapper.insert(gczlYdxjGPZLXJInfo);
    }

	   @Override
    public void del(String[] ids) {
		  List<String> idList = Arrays.asList(ids);
		  gczlYdxjGPZLXJInfoMapper.deleteBatchIds(idList);
    }

    @Override
    public void update(GczlYdxjGPZLXJInfo gczlYdxjGPZLXJInfo) {
		  gczlYdxjGPZLXJInfoMapper.updateById(gczlYdxjGPZLXJInfo);
    }

	   @Override
	   public GczlYdxjGPZLXJInfo findById(String id) {
		   return gczlYdxjGPZLXJInfoMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(GczlYdxjGPZLXJInfoDto gczlYdxjGPZLXJInfoDto, String[] clumNames) {
		   String id = gczlYdxjGPZLXJInfoDto.getId();
		   Map map = BeanUtils.toMap(gczlYdxjGPZLXJInfoDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        if(clum.equals("gcBh")){
		        	columnMap.put("gc_Bh", ss);
		        }else if(clum.equals("qlBh")){
		        	columnMap.put("ql_Bh", ss);
		        }else if(clum.equals("xlBh")){
		        	columnMap.put("xl_Bh", ss);
		        }else{
		        	columnMap.put(clum, ss);
		        }
		   }
		   List<GczlYdxjGPZLXJInfo> lists = gczlYdxjGPZLXJInfoMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "相同盾构施工质量巡检信息已存在，新增失败。";
		   }else{//修改
			    for (GczlYdxjGPZLXJInfo gczlYdxjGPZLXJInfo2 : lists) {
				   String newId = gczlYdxjGPZLXJInfo2.getId();
				   if(!newId.equals(id)){
					   return "相同盾构施工质量巡检信息已存在，修改失败。";
				   }
			    }
		   }
		   return null;
    }

	@Override
	public void insertDto(GczlYdxjGPZLXJInfoDto gczlYdxjGPZLXJInfoDto,User user) {
		GczlYdxjGPZLXJInfo gczlYdxjGPZLXJInfo = new GczlYdxjGPZLXJInfo();
    	BeanUtils.copyProperties(gczlYdxjGPZLXJInfoDto, gczlYdxjGPZLXJInfo);
    	String typeName = gczlYdxjGPZLXJInfoDto.getTypeName();
    	String qkms = "";
    	if(typeName.equals("管片破损")){
    		qkms ="破损情况描述："+gczlYdxjGPZLXJInfoDto.getGppsQkMiaoshu()+"；破损尺寸描述（长X宽）："+gczlYdxjGPZLXJInfoDto.getGppsLength()+"mm×"+gczlYdxjGPZLXJInfoDto.getGppsWidth()+"mm。";
    	}else if(typeName.equals("管片错台")){
    		qkms ="最大错台尺寸："+"径向（"+gczlYdxjGPZLXJInfoDto.getGpctJinXiang()+"mm），环向（"+gczlYdxjGPZLXJInfoDto.getGpctHuanXiang()+"mm）。";
    	}else if(typeName.equals("螺栓复紧")){
    		qkms ="复紧不到位数量："+gczlYdxjGPZLXJInfoDto.getLsfjNums()+"处。";
    	}else if(typeName.equals("渗漏水")){
    		qkms ="渗水情况描述："+gczlYdxjGPZLXJInfoDto.getSlsMiaoShu()+"。";
    	}
    	gczlYdxjGPZLXJInfo.setQkms(qkms);
    	gczlYdxjGPZLXJInfo.setXjry(user.getEmpId());
    	gczlYdxjGPZLXJInfo.setXjbm(user.getOrgzId());
    	gczlYdxjGPZLXJInfo.setWhtime(DateUtils.getToday());
    	gczlYdxjGPZLXJInfo.setSbzt("未上报");
    	gczlYdxjGPZLXJInfo.setShzt("未审核");
    	gczlYdxjGPZLXJInfo.setZgzt("未整改");
    	BaseService.operatorMessage(gczlYdxjGPZLXJInfo, null, user);
    	ProProjectinfo projectinfo = iproProjectinfoService.getProjectInfoByUserId(user.getId());
    	gczlYdxjGPZLXJInfo.setGcBh(projectinfo.getId());
		gczlYdxjGPZLXJInfoMapper.insert(gczlYdxjGPZLXJInfo);
		
	}
	
	@Override
	public String updateDto(GczlYdxjGPZLXJInfoDto gczlYdxjGPZLXJInfoDto, User user) {
		String errorMessage = "";
		GczlYdxjGPZLXJInfo gczlYdxjGPZLXJInfo = this.gczlYdxjGPZLXJInfoMapper.selectById(gczlYdxjGPZLXJInfoDto.getId());
		errorMessage = BaseService.operatorMessage(gczlYdxjGPZLXJInfo, gczlYdxjGPZLXJInfoDto, user);
		if(StringUtils.isNotBlank(errorMessage)) return errorMessage;
    	BeanUtils.copyProperties(gczlYdxjGPZLXJInfoDto, gczlYdxjGPZLXJInfo);
    	String typeName = gczlYdxjGPZLXJInfoDto.getTypeName();
    	String qkms = "";
    	if(typeName.equals("管片破损")){
    		qkms ="破损情况描述："+gczlYdxjGPZLXJInfoDto.getGppsQkMiaoshu()+"；破损尺寸描述（长X宽）："+gczlYdxjGPZLXJInfoDto.getGppsLength()+"mm×"+gczlYdxjGPZLXJInfoDto.getGppsWidth()+"mm。";
    	}else if(typeName.equals("管片错台")){
    		qkms ="最大错台尺寸："+"径向（"+gczlYdxjGPZLXJInfoDto.getGpctJinXiang()+"mm），环向（"+gczlYdxjGPZLXJInfoDto.getGpctHuanXiang()+"mm）。";
    	}else if(typeName.equals("螺栓复紧")){
    		qkms ="复紧不到位数量："+gczlYdxjGPZLXJInfoDto.getLsfjNums()+"处。";
    	}else if(typeName.equals("渗漏水")){
    		qkms ="渗水情况描述："+gczlYdxjGPZLXJInfoDto.getSlsMiaoShu()+"。";
    	}
    	gczlYdxjGPZLXJInfo.setQkms(qkms);
    	gczlYdxjGPZLXJInfo.setXjry(user.getEmpId());
    	gczlYdxjGPZLXJInfo.setXjbm(user.getOrgzId());
    	gczlYdxjGPZLXJInfo.setSbzt("未上报");
    	gczlYdxjGPZLXJInfo.setShzt("未审核");
    	gczlYdxjGPZLXJInfo.setZgzt("未整改");
    	
    	ProProjectinfo projectinfo = iproProjectinfoService.getProjectInfoByUserId(user.getId());
    	gczlYdxjGPZLXJInfo.setGcBh(projectinfo.getId());
		gczlYdxjGPZLXJInfoMapper.updateById(gczlYdxjGPZLXJInfo);
		
		return errorMessage;
		
	}

	@Override
	public void sendUp(String id,User user) {
		GczlYdxjGPZLXJInfo gczlYdxjGPZLXJInfo = this.gczlYdxjGPZLXJInfoMapper.selectById(id);
		gczlYdxjGPZLXJInfo.setSbzt("已上报");
		gczlYdxjGPZLXJInfo.setShzt("未审核");
		gczlYdxjGPZLXJInfo.setSbtime(DateUtils.getToday());
		gczlYdxjGPZLXJInfo.setSbry(user.getEmpId());
		gczlYdxjGPZLXJInfo.setSbbm(user.getOrgzId());
		gczlYdxjGPZLXJInfo.setUpdateTime(DateUtils.getToday());
		gczlYdxjGPZLXJInfoMapper.updateById(gczlYdxjGPZLXJInfo);
		
		//手机端个推消息保存到数据库
    	GczlOpenPush gczlOpenPush = new GczlOpenPush();
    	gczlOpenPush.setGczlId(id);
    	gczlOpenPush.setCreateTime(DateUtils.getToday());
    	gczlOpenPush.setUserId(user.getId());
    	gczlOpenPush.setType("管片");
    	gczlOpenPush.setZt("已上报");
    	gczlOpenPush.setNrms("问题质量分类："+gczlYdxjGPZLXJInfo.getTypeName()+"|"+gczlYdxjGPZLXJInfo.getQkms());
    	gczlOpenPush.setXjzpslt(gczlYdxjGPZLXJInfo.getXjzpslt());
    	gczlOpenPush.setXjry(user.getName());
    	gczlOpenPush.setUpdateTime(gczlYdxjGPZLXJInfo.getUpdateTime());
    	gczlOpenPushMapper.insert(gczlOpenPush);
		
	}
	
	@Override
	public void sendUp(User user,GczlYdxjGPZLXJInfoDto gczlYdxjGPZLXJInfoDto) {
		GczlYdxjGPZLXJInfo gczlYdxjGPZLXJInfo = this.gczlYdxjGPZLXJInfoMapper.selectById(gczlYdxjGPZLXJInfoDto.getId());
		gczlYdxjGPZLXJInfo.setSbzt("已上报");
		gczlYdxjGPZLXJInfo.setShzt("未审核");
		gczlYdxjGPZLXJInfo.setSbtime(DateUtils.getToday());
		gczlYdxjGPZLXJInfo.setSbry(user.getEmpId());
		gczlYdxjGPZLXJInfo.setSbbm(user.getOrgzId());
		gczlYdxjGPZLXJInfo.setUpdateTime(DateUtils.getToday());
		gczlYdxjGPZLXJInfo.setZgry(gczlYdxjGPZLXJInfoDto.getZgry());

		//整改截止时间
		String zgjzTime = gczlYdxjGPZLXJInfoDto.getZgjzTime();
				/*zgjzTime = zgjzTime.replace("T", " ");
    	if(zgjzTime.length() < 19){
    		zgjzTime = zgjzTime+":00";
    	}else if(zgjzTime.length() > 19){
    		zgjzTime = zgjzTime.substring(0,19);
    	}*/
		gczlYdxjGPZLXJInfo.setZgjzTime(zgjzTime);
		gczlYdxjGPZLXJInfoMapper.updateById(gczlYdxjGPZLXJInfo);
		
		//手机端个推消息保存到数据库
    	GczlOpenPush gczlOpenPush = new GczlOpenPush();
    	gczlOpenPush.setGczlId(gczlYdxjGPZLXJInfo.getId());
    	gczlOpenPush.setCreateTime(DateUtils.getToday());
    	gczlOpenPush.setUserId(user.getId());
    	gczlOpenPush.setType("管片");
    	gczlOpenPush.setZt("已上报");
    	gczlOpenPush.setNrms("问题质量分类："+gczlYdxjGPZLXJInfo.getTypeName()+"|"+gczlYdxjGPZLXJInfo.getQkms());
    	gczlOpenPush.setXjzpslt(gczlYdxjGPZLXJInfo.getXjzpslt());
    	gczlOpenPush.setXjry(user.getName());
    	gczlOpenPush.setUpdateTime(gczlYdxjGPZLXJInfo.getUpdateTime());
    	gczlOpenPushMapper.insert(gczlOpenPush);
		
	}
	
	@Override
	public String shengHe(GczlYdxjGPZLXJInfoDto gczlYdxjGPZLXJInfoDto,User user) {
		String errorMessage = "";
		GczlYdxjGPZLXJInfo gczlYdxjGPZLXJInfo = this.gczlYdxjGPZLXJInfoMapper.selectById(gczlYdxjGPZLXJInfoDto.getId());
		errorMessage = BaseService.operatorMessage(gczlYdxjGPZLXJInfo, gczlYdxjGPZLXJInfoDto, user);
		if(StringUtils.isNotBlank(errorMessage))return errorMessage;
		gczlYdxjGPZLXJInfo.setShzt(gczlYdxjGPZLXJInfoDto.getShzt());
		if(gczlYdxjGPZLXJInfoDto.getShzt().equals("已打回")){
			gczlYdxjGPZLXJInfo.setSbzt("未上报");
		}
		gczlYdxjGPZLXJInfo.setShtime(DateUtils.getToday());
		gczlYdxjGPZLXJInfo.setShry(user.getEmpId());
		gczlYdxjGPZLXJInfo.setShbm(user.getOrgzId());
		gczlYdxjGPZLXJInfo.setShsm(gczlYdxjGPZLXJInfoDto.getShsm());
		gczlYdxjGPZLXJInfo.setZgry(gczlYdxjGPZLXJInfoDto.getZgry());
		gczlYdxjGPZLXJInfo.setUpdateTime(DateUtils.getToday());
		
		//设置整改时间
		String zgjzTime = gczlYdxjGPZLXJInfoDto.getZgjzTime();
		zgjzTime = zgjzTime.replace("T", " ");
		if (zgjzTime.length() < 19) {
			zgjzTime = zgjzTime + ":00";
		} else if (zgjzTime.length() > 19) {
			zgjzTime = zgjzTime.substring(0, 19);
		}
		gczlYdxjGPZLXJInfo.setZgjzTime(zgjzTime);
		
		gczlYdxjGPZLXJInfoMapper.updateById(gczlYdxjGPZLXJInfo);
		
		
		
		
		//手机端个推消息保存到数据库
		GczlOpenPush gczlOpenPush = new GczlOpenPush();
    	gczlOpenPush.setGczlId(gczlYdxjGPZLXJInfoDto.getId());
    	gczlOpenPush.setCreateTime(DateUtils.getToday());
    	gczlOpenPush.setUserId(user.getId());
    	gczlOpenPush.setType("管片");
    	gczlOpenPush.setZt("已审核");
    	gczlOpenPush.setNrms("问题质量分类："+gczlYdxjGPZLXJInfo.getTypeName()+"|"+gczlYdxjGPZLXJInfo.getQkms());
    	gczlOpenPush.setXjzpslt(gczlYdxjGPZLXJInfo.getXjzpslt());
    	gczlOpenPush.setXjry(user.getName());
    	gczlOpenPush.setUpdateTime(gczlYdxjGPZLXJInfo.getUpdateTime());
    	gczlOpenPushMapper.insert(gczlOpenPush);
		
		return errorMessage;
	}

	@Override
	public String zhengGai(GczlYdxjGPZLXJInfoDto gczlYdxjGPZLXJInfoDto,User user) {
		String errorMessage = "";
		GczlYdxjGPZLXJInfo gczlYdxjGPZLXJInfo = this.gczlYdxjGPZLXJInfoMapper.selectById(gczlYdxjGPZLXJInfoDto.getId());
		errorMessage = BaseService.operatorMessage(gczlYdxjGPZLXJInfo, gczlYdxjGPZLXJInfoDto, user);
		if(StringUtils.isNotBlank(errorMessage))return errorMessage;
		gczlYdxjGPZLXJInfo.setZgfktime(DateUtils.getToday());
		gczlYdxjGPZLXJInfo.setZgfkry(user.getEmpId());
		gczlYdxjGPZLXJInfo.setZgzt(gczlYdxjGPZLXJInfoDto.getZgzt());
		gczlYdxjGPZLXJInfo.setZgjg(gczlYdxjGPZLXJInfoDto.getZgjg());
		gczlYdxjGPZLXJInfo.setZgbm(user.getOrgzId());
		gczlYdxjGPZLXJInfo.setZgtime(gczlYdxjGPZLXJInfoDto.getZgtime());
		gczlYdxjGPZLXJInfo.setUpdateTime(DateUtils.getToday());
		gczlYdxjGPZLXJInfoMapper.updateById(gczlYdxjGPZLXJInfo);
		
		//手机端个推消息保存到数据库
		GczlOpenPush gczlOpenPush = new GczlOpenPush();
    	gczlOpenPush.setGczlId(gczlYdxjGPZLXJInfoDto.getId());
    	gczlOpenPush.setCreateTime(DateUtils.getToday());
    	gczlOpenPush.setUserId(user.getId());
    	gczlOpenPush.setType("管片");
    	gczlOpenPush.setZt("已整改");
    	gczlOpenPush.setNrms("问题质量分类："+gczlYdxjGPZLXJInfo.getTypeName()+"|"+gczlYdxjGPZLXJInfo.getQkms());
    	gczlOpenPush.setXjzpslt(gczlYdxjGPZLXJInfo.getXjzpslt());
    	gczlOpenPush.setXjry(user.getName());
    	gczlOpenPush.setUpdateTime(gczlYdxjGPZLXJInfo.getUpdateTime());
    	gczlOpenPushMapper.insert(gczlOpenPush);
		return errorMessage;
	}

	@Override
	public GczlYdxjGPZLXJInfoDto findDtoById(String id) {
		return gczlYdxjGPZLXJInfoMapper.selectGczlYdxjGPZLXJInfoById(id);
	}

	@Override
	public String checkIfguoQi(String id, String time) {
		Map<String, Object> columnMap = new HashMap<String, Object>();
		columnMap.put("id", id);
		if(!StringUtils.isEmpty(time) && !"null".equals(time)){
			columnMap.put("update_time", time);
		}
		List<GczlYdxjGPZLXJInfo> ls = this.gczlYdxjGPZLXJInfoMapper.selectByMap(columnMap);
		if(ls.isEmpty())return "当前数据已过期,请刷新页面重试！";
		return null;
	}

	@Override
	public int findStateNum(String proId, String typeName) {
		if(StringUtils.isBlank(proId)||StringUtils.isBlank(typeName)){
			return 0;
		}else{
			return this.gczlYdxjGPZLXJInfoMapper.findStateNum(proId, typeName);
		}
	}

}