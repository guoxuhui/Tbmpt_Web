package com.crfeb.tbmpt.dmcjjc.zdcjddb.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crfeb.tbmpt.dmcjjc.info.mapper.DmcjJcPointMapper;
import com.crfeb.tbmpt.dmcjjc.zdcjddb.model.Zdcjddb;
import com.crfeb.tbmpt.dmcjjc.zdcjddb.model.ZdcjddbDetails;
import com.crfeb.tbmpt.dmcjjc.zdcjddb.service.IZdcjddbService;

@Service
public class ZdcjddbServiceImpl implements IZdcjddbService {

	@Autowired
	private DmcjJcPointMapper jcPointMapper;
	
	@Override
	public Object getZdcjddbData(List<String> qujians, String sDate, String eDate) throws ParseException {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		
		ArrayList<String> tli = new ArrayList<String>();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar sdc = Calendar.getInstance();
        sdc.setTime(sf.parse(sDate));
        Calendar edc = Calendar.getInstance();
        edc.setTime(sf.parse(eDate));
		for(int i=0;sdc.getTime().getTime()<=edc.getTime().getTime();i++){
			sdc.add(Calendar.DAY_OF_MONTH, 1);
			tli.add(sf.format(sdc.getTime()));
		}
		list.add(tli);
		
		List<Zdcjddb> listZdcjddb = jcPointMapper.getZdcjddbData(qujians, sDate, eDate);
		ArrayList<String> jli = new ArrayList<String>();
		jli.add(listZdcjddb.get(1).getJcdno());
		jli.add(listZdcjddb.get(0).getJcdno());
		
		list.add(jli);
		
		ArrayList<String> dli1 = new ArrayList<String>();
		List<ZdcjddbDetails> listDetails1 = jcPointMapper.getZdcjddbDetailsData(qujians, listZdcjddb.get(1).getJcdno(), sDate, eDate);
		for(ZdcjddbDetails d:listDetails1){
			dli1.add(d.getMaxjs());
		}
		list.add(dli1);
		
		ArrayList<String> dli0 = new ArrayList<String>();
		List<ZdcjddbDetails> listDetails0 = jcPointMapper.getZdcjddbDetailsData(qujians, listZdcjddb.get(0).getJcdno(), sDate, eDate);
		for(ZdcjddbDetails d:listDetails0){
			dli0.add(d.getMaxjs());
		}
		list.add(dli0);
		
		return list;
	}

}
