package com.crfeb.tbmpt.gczl.base.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.gczl.base.mapper.GczlOpenPushMapper;
import com.crfeb.tbmpt.gczl.base.model.GczlOpenPush;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjSGZLXJInfo;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjGPZLXJInfoDto;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjSGZLJtwzDto;
import com.crfeb.tbmpt.gczl.base.model.dto.GczlYdxjSGZLXJInfoDto;
import com.crfeb.tbmpt.gczl.base.service.GczlOpenPushService;
import com.crfeb.tbmpt.gczl.base.service.GczlYdxjGPZLXJInfoService;
import com.crfeb.tbmpt.gczl.base.service.GczlYdxjSGZLXJInfoService;
import com.crfeb.tbmpt.sys.base.model.dto.SysFujianDto;
import com.crfeb.tbmpt.sys.base.service.SysFujianService;
import com.crfeb.tbmpt.sys.model.User;

@Service
public class GczlOpenPushServiceImpl extends CommonServiceImpl<GczlOpenPushMapper, GczlOpenPush> implements GczlOpenPushService{
	 @Autowired
	 private GczlOpenPushMapper gczlOpenPushMapper;
	 @Autowired
	 private GczlYdxjGPZLXJInfoService gczlYdxjGPZLXJInfoService;
	 @Autowired
	 private GczlYdxjSGZLXJInfoService gczlYdxjSGZLXJInfoService;
	 @Autowired
	 private SysFujianService sysFujianService;
	 
	 	@Override
	    public void selectDataGrid(PageInfo pageInfo) {
	       Page<GczlOpenPush> page = new Page<GczlOpenPush>(pageInfo.getNowpage(), pageInfo.getSize());
	       Map<String, Object> condition = pageInfo.getCondition();
	       List<GczlOpenPush> listDto = gczlOpenPushMapper.selectGczlOpenPushList(page, condition);
	       pageInfo.setRows(listDto);
	       pageInfo.setTotal(page.getTotal());
	    }

		@Override
		public String saveGPZLXJInfo(GczlYdxjGPZLXJInfoDto gczlYdxjGPZLXJInfoDto, User user) {
			String uuid = UUID.randomUUID().toString();
			try {
				gczlYdxjGPZLXJInfoDto.setId(uuid);
				gczlYdxjGPZLXJInfoService.insertDto(gczlYdxjGPZLXJInfoDto, user);
			} catch (Exception e) {
				return null;
//				e.printStackTrace();
			}
			return uuid;
		}

		@Override
		public String sbGPZLXJInfo(String id, User user) {
			String message = "";
			try {
				gczlYdxjGPZLXJInfoService.sendUp(id, user);
			}catch (Exception e) {
				message="上报失败！";
			}finally {
				return message;
			}
		}

		@Override
		public String shGPZLXJInfo(GczlYdxjGPZLXJInfoDto gczlYdxjGPZLXJInfoDto, User user) {
			gczlYdxjGPZLXJInfoService.shengHe(gczlYdxjGPZLXJInfoDto, user);
			return null;
		}

		@Override
		public GczlYdxjGPZLXJInfoDto getGPZLXJInfo(String id) {
			return gczlYdxjGPZLXJInfoService.findDtoById(id);
		}

		@Override
		public List<SysFujianDto> getGPZLXJInfoPic(String id,String backupOne,String backupTwo) {
			List<SysFujianDto> sfdtos = new ArrayList<>();
			try {
				sfdtos = sysFujianService.findFuJianListByForeignId(id, backupOne, backupTwo);
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				return sfdtos;
			}
		}

		@Override
		public String saveGPZLXJInfoPic(List<SysFujianDto> sfdtos, User user) {
			String message = "";
			try {
				sysFujianService.save(sfdtos, user);
			} catch (Exception e) {
				message = "数据保存失败!";
				e.printStackTrace();
			}finally{
				return message;
			}
		}

		@Override
		public String saveSGZLXJInfo(GczlYdxjSGZLXJInfoDto gczlYdxjSGZLXJInfoDto, User user) {
			String uuid = UUID.randomUUID().toString();
			try {
				GczlYdxjSGZLXJInfo gczlYdxjSGZLXJInf = new GczlYdxjSGZLXJInfo();
				BeanUtils.copyProperties(gczlYdxjSGZLXJInfoDto, gczlYdxjSGZLXJInf);
				gczlYdxjSGZLXJInf.setId(uuid);
				gczlYdxjSGZLXJInfoService.save(gczlYdxjSGZLXJInf);
			} catch (BeansException e) {
				uuid = null;
				e.printStackTrace();
			}finally{
				return uuid;
			}
		}

		@Override
		public String sbSGZLXJInfo(String id, User user) {
			String[] ids = {id};
			return gczlYdxjSGZLXJInfoService.sbInfo(ids, user);
		}

		@Override
		public String shSGZLXJInfo(GczlYdxjSGZLXJInfoDto gczlYdxjSGZLXJInfoDto, User user) {
			return gczlYdxjSGZLXJInfoService.shInfo(gczlYdxjSGZLXJInfoDto, user);
		}

		@Override
		public GczlYdxjSGZLXJInfoDto getSGZLXJInfo(String id) {
			GczlYdxjSGZLXJInfoDto gczlYdxjSGZLXJInfoDto = new GczlYdxjSGZLXJInfoDto();
			GczlYdxjSGZLXJInfo gczifo = gczlYdxjSGZLXJInfoService.findById(id);
			if(gczifo!=null){
				gczlYdxjSGZLXJInfoService.BeanUtilsEntityToDto(gczifo, gczlYdxjSGZLXJInfoDto);
			}else{
				gczlYdxjSGZLXJInfoDto = null;
			}
			return gczlYdxjSGZLXJInfoDto;
		}

	 	
}
