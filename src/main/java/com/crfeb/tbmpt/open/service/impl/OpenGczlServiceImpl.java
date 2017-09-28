package com.crfeb.tbmpt.open.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjGPZLDDInfo;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjGPZLDDType;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjGPZLXJInfo;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLJtwz;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLSgnr;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLXJInfo;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLZlqx;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjGPZLXJInfoDto;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjSGZLXJInfoDto;
import com.crfeb.tbmpt.gczl.base.service.GczlOpenPushService;
import com.crfeb.tbmpt.gczl.base.service.GczlYdxjGPZLDDInfoService;
import com.crfeb.tbmpt.gczl.base.service.GczlYdxjGPZLDDTypeService;
import com.crfeb.tbmpt.gczl.base.service.GczlYdxjGPZLXJInfoService;
import com.crfeb.tbmpt.gczl.base.service.GczlYdxjSGZLJtwzService;
import com.crfeb.tbmpt.gczl.base.service.GczlYdxjSGZLSgnrService;
import com.crfeb.tbmpt.gczl.base.service.GczlYdxjSGZLXJInfoService;
import com.crfeb.tbmpt.gczl.base.service.GczlYdxjSGZLZlqxService;
import com.crfeb.tbmpt.open.model.DicInfo;
import com.crfeb.tbmpt.open.service.IOpenGczlService;

@Service
public class OpenGczlServiceImpl implements IOpenGczlService {
	@Autowired
	private GczlYdxjGPZLXJInfoService gczlYdxjGPZLXJInfoService;
	@Autowired
	private GczlYdxjSGZLXJInfoService gczlYdxjSGZLXJInfoService;

	@Autowired
	private GczlYdxjGPZLDDInfoService gczlYdxjGPZLDDInfoService;
	@Autowired
	private GczlYdxjGPZLDDTypeService gczlYdxjGPZLDDTypeService;

	@Autowired
	private GczlYdxjSGZLZlqxService gczlYdxjSGZLZlqxService;
	@Autowired
	private GczlYdxjSGZLJtwzService gczlYdxjSGZLJtwzService;
	@Autowired
	private GczlYdxjSGZLSgnrService gczlYdxjSGZLSgnrService;
	@Autowired
	private GczlOpenPushService gczlOpenPushService;

	@Override
	public int saveGpzl(GczlYdxjGPZLXJInfo gczlYdxjGPZLXJInfo) {
		gczlYdxjGPZLXJInfoService.insert(gczlYdxjGPZLXJInfo);
		return 1;
	}

	// 获得管片基础信息
	@Override
	public List<DicInfo> getGpzlDDInfo() {
		List<DicInfo> dics = new ArrayList<DicInfo>();
		List<GczlYdxjGPZLDDType> list = gczlYdxjGPZLDDTypeService.getAll();
		for (GczlYdxjGPZLDDType info : list) {
			DicInfo di = new DicInfo();
			di.setId(info.getId());
			di.setName(info.getTypeName());

			List<DicInfo> infoDics = new ArrayList<DicInfo>();

			Map<String, Object> columnMap = new HashMap<>();
			columnMap.put("typeName", info.getTypeName());
			columnMap.put("ifQy", "启用");
			List<GczlYdxjGPZLDDInfo> types = gczlYdxjGPZLDDInfoService.selectByMap(columnMap);
			for (GczlYdxjGPZLDDInfo in : types) {
				DicInfo idic = new DicInfo();
				idic.setId(in.getId());
				idic.setName(in.getDdName());
				infoDics.add(idic);
			}
			di.setGpzlDDinfos(infoDics);

			dics.add(di);
		}
		return dics;
	}

	// 获得管片质量历史信息
	@Override
	public Object getGpzlList(String gcbh, String type, int page, int rows) {
		PageInfo pageInfo = new PageInfo(page, rows, "xjtime", "desc");
		Map<String, Object> condition = new HashMap<String, Object>();
		if ("未上报".equals(type)) {
			condition.put("sbzt", type);
		} else if ("未审核".equals(type)) {
			condition.put("shzt", type);
		} else if ("未整改".equals(type)) {
			condition.put("zgzt", type);
		}  else if ("已整改".equals(type)) {
			condition.put("zgzt", type);
		} else if ("待上报".equals(type)) {
			condition.put("sbzt","未上报");
		}else if ("待审核".equals(type)) {
			condition.put("sbzt","已上报");
			condition.put("shzt", "未审核");
		}else if ("待整改".equals(type)) {
			condition.put("shzt","已审核");
			condition.put("zgzt", "未整改");
		}else if (type == null) {

		} else {
			condition.put("sbzt", "sbzt");
		}
		condition.put("gcBh", gcbh);
		pageInfo.setCondition(condition);
		gczlYdxjGPZLXJInfoService.selectDataGrid(pageInfo);
		return pageInfo;
	}

	@Override
	public GczlYdxjGPZLXJInfoDto getGpzlInfo(String id) {
		PageInfo pageInfo = new PageInfo(0, 10, "id", "desc");
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("id", id);
		pageInfo.setCondition(condition);
		gczlYdxjGPZLXJInfoService.selectDataGrid(pageInfo);
		@SuppressWarnings("unchecked")
		List<Object> list = pageInfo.getRows();
		GczlYdxjGPZLXJInfoDto dto = null;
		if (list != null && list.size() >= 1) {
			dto = (GczlYdxjGPZLXJInfoDto) list.get(0);
		}
		return dto;
	}

	@Override
	public int saveSgzl(GczlYdxjSGZLXJInfo gczlYdxjSGZLXJInfo) {
		gczlYdxjSGZLXJInfoService.insert(gczlYdxjSGZLXJInfo);
		return 1;
	}

	@Override
	public List<DicInfo> getSgzlDDInfo() {
		List<DicInfo> dics = new ArrayList<DicInfo>();
		List<GczlYdxjSGZLSgnr> list = gczlYdxjSGZLSgnrService.selectBySta("1");
		for (GczlYdxjSGZLSgnr info : list) {
			DicInfo di = new DicInfo();
			di.setId(info.getId());
			di.setName(info.getSgNr());

			List<DicInfo> wzs = new ArrayList<DicInfo>();
			List<DicInfo> qxs = new ArrayList<DicInfo>();

			Map<String, Object> columnMap = new HashMap<>();
			columnMap.put("sgNr", info.getId());
			columnMap.put("ifQy", "启用");
			List<GczlYdxjSGZLJtwz> wzList = gczlYdxjSGZLJtwzService.selectByMap(columnMap);
			for (GczlYdxjSGZLJtwz in : wzList) {
				DicInfo idic = new DicInfo();
				idic.setId(in.getId());
				idic.setName(in.getJtWz());
				wzs.add(idic);
			}

			Map<String, Object> columnMapqx = new HashMap<>();
			columnMapqx.put("sgNr", info.getId());
			columnMapqx.put("ifQy", "启用");
			List<GczlYdxjSGZLZlqx> qxList = gczlYdxjSGZLZlqxService.selectByMap(columnMapqx);
			for (GczlYdxjSGZLZlqx in : qxList) {
				DicInfo idic = new DicInfo();
				idic.setId(in.getId());
				idic.setName(in.getZlQx());
				qxs.add(idic);
			}
			di.setSgzlJtwzinfos(wzs);
			di.setSgzlZlqxinfos(qxs);
			dics.add(di);
		}
		return dics;
	}

	// 获得施工质量列表
	@Override
	public Object getSgzlList(String gcbh, String type, int page, int rows) {
		PageInfo pageInfo = new PageInfo(page, rows, "xjtime", "desc");
		Map<String, Object> condition = new HashMap<String, Object>();
		if ("未上报".equals(type)) {
			condition.put("sbZt", type);
		} else if ("未审核".equals(type)) {
			condition.put("shZt", type);
		} else if ("未整改".equals(type)) {
			condition.put("zgZt", type);
		} else if ("已整改".equals(type)) {
			condition.put("zgZt", type);
		}else if ("待上报".equals(type)) {
			condition.put("sbZt", "未上报");
		} else if ("待审核".equals(type)) {
			condition.put("sbZt", "已上报");
			condition.put("shZt", "未审核");
		}else if ("待整改".equals(type)) {
			condition.put("shZt", "已审核");
			condition.put("zgZt", "未整改");
		}else if (type == null) {

		} else {
			condition.put("sbZt", "sbzt");
		}
		condition.put("gcBh", gcbh);
		pageInfo.setCondition(condition);
		gczlYdxjSGZLXJInfoService.selectDataGrid(pageInfo);
		return pageInfo;

	}

	@Override
	public GczlYdxjSGZLXJInfoDto getSgzlInfo(String id) {
		PageInfo pageInfo = new PageInfo(0, 10, "id", "desc");
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("id", id);
		pageInfo.setCondition(condition);
		gczlYdxjSGZLXJInfoService.selectDataGrid(pageInfo);
		@SuppressWarnings("unchecked")
		List<Object> list = pageInfo.getRows();
		GczlYdxjSGZLXJInfoDto dto = null;
		if (list != null && list.size() >= 1) {
			dto = (GczlYdxjSGZLXJInfoDto) list.get(0);
		}
		return dto;
	}

	/**
	 * 获取个推消息记录
	 */
	@Override
	public Object getGczlMessageList(int page, int rows, String userId) {
		PageInfo pageInfo = new PageInfo(page, rows);
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("user_id", userId);
		pageInfo.setCondition(condition);
		gczlOpenPushService.selectDataGrid(pageInfo);
		return pageInfo;
	}
}
