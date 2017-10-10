package com.crfeb.tbmpt.commons.task;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;   
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.zsjk.plc.model.ProLineWork;
import com.crfeb.tbmpt.zsjk.plc.model.dto.ProPlcBzRealDto;
import com.crfeb.tbmpt.zsjk.plc.service.IProLineWorkService;
import com.crfeb.tbmpt.zsjk.plc.service.IProPlcRealService;

@Component
public class LineWorkTask {
	private static String defaultDatePattern = "MM月dd日";
	@Autowired
	private IProLineWorkService proLineWorkService;
	@Autowired
    private IProPlcRealService proPlcRealService;
	
	@Scheduled(cron = "0 50 23 * * ?")
	public void run() {
		  
		System.out.println(DateUtils.getToday() + " LineWorkTask run!");
		long openLong = System.currentTimeMillis();
		ProLineWork plw = new ProLineWork();
		plw.setLine_id("e59ef5a2c91d49a3829bd593346c718d");
		plw.setTbm_id("f4cfd546005845c9998750701abf0b58");
		plw.setWork_date(DateUtils.format(new Date(), defaultDatePattern));
		plw.setTime_jj("1");
		plw.setTime_ph("1");
		plw.setTime_tj("1");
		plw.setWork_hs_sum("0");
		plw.setWork_hs_start("0");
		plw.setWork_hs_end("0");
		
		EntityWrapper<ProLineWork> ew = new EntityWrapper<ProLineWork>();
		ew.orderBy("WORK_DATE", false);
		List<ProLineWork> list = proLineWorkService.selectList(ew);
		String endHs = null;
		if(list != null && list.size() > 0) {
			endHs = list.get(0).getWork_hs_end();
			System.out.println(DateUtils.getToday() + " LineWorkTask opt s " + list.get(0).getWork_date() +"  --  "+ list.get(0).getWork_hs_end());
		}
		if(endHs != null) {
			plw.setWork_hs_start(endHs);
		}
		
		List<ProPlcBzRealDto> pbzs = null;
		try {
			pbzs = proPlcRealService.getSsJqsjByDw("f4cfd546005845c9998750701abf0b58", "TY_DXXT_0001");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(pbzs != null && pbzs.size()>0) {
			plw.setWork_hs_end(pbzs.get(0).getTagvalue());	
			System.out.println(DateUtils.getToday() + " LineWorkTask opt e " + pbzs.get(0).getTagtime() +"  --  "+ pbzs.get(0).getTagvalue());
		}
		
		String s = plw.getWork_hs_start();
		String e = plw.getWork_hs_end();
		if(!s.equals("0") && !e.equals("0")) {
			int sum = Integer.parseInt(e) - Integer.parseInt(s);
			plw.setWork_hs_sum(String.valueOf(sum));
		}
		proLineWorkService.insert(plw);
		System.out.println(DateUtils.getToday() + " LineWorkTask finsh,Processed "+(System.currentTimeMillis() - openLong)/1000+"S !");
	}
}
