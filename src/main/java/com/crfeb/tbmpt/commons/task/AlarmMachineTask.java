package com.crfeb.tbmpt.commons.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;   
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.crfeb.tbmpt.commons.getui.GPushService;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.machine.model.MachineAlarm;
import com.crfeb.tbmpt.machine.model.MachineAlarmConfig;
import com.crfeb.tbmpt.machine.service.IMachineAlarmConfigService;
import com.crfeb.tbmpt.machine.service.IMachineAlarmService;
import com.crfeb.tbmpt.sys.base.model.SysUserMsg;
import com.crfeb.tbmpt.sys.base.service.ISysUserMsgService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.sys.service.IUserService;
import com.crfeb.tbmpt.zsjk.plc.model.dto.ProSecLinePlcDto;
import com.crfeb.tbmpt.zsjk.plc.service.IProPlcRealService;

/**
 * 定时分析推送机器报警数据
 * @since 2017年5月24日
 * @author xhguo
 *
 */
//@Component
public class AlarmMachineTask {
	private static String defaultDatePattern = "yyyy-MM-dd HH:mm:ss";
	@Autowired
	private IProPlcRealService proPlcRealService;
	@Autowired
	private IUserService userService;
	@Autowired
	private IMachineAlarmConfigService machineAlarmConfigService;
	@Autowired
	private IMachineAlarmService machineAlarmService;
	@Autowired
	private ISysUserMsgService sysUserMsgService;
	
	private static String url = "/machine/alarmhis";
	
	private ScriptEngineManager manager = new ScriptEngineManager();
	private ScriptEngine engine = null;
	
	@Scheduled(cron = "0 40/30 * * * ?")
	public void run() {
		  
		if(engine == null){
			engine = manager.getEngineByName("js");			
		}
        
		System.out.println(DateUtils.getToday() + " alarmMachineTask run!");
		long openLong = System.currentTimeMillis();
		//1、第一步获取全部机器数据，组装Map对象，方便比对处理
		Map<String,ProSecLinePlcDto> machineMap = new HashMap<String,ProSecLinePlcDto>();
		List<ProSecLinePlcDto> plcList = new ArrayList<ProSecLinePlcDto>();
		try {
			plcList = proPlcRealService.selectProSecLinePlcAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(ProSecLinePlcDto dto : plcList){
			ProSecLinePlcDto pslp = machineMap.get(dto.getTbm_id());
			if(pslp == null){
				pslp = dto;
				machineMap.put(dto.getTbm_id(), pslp);
			}
			pslp.getPlcMap().put(dto.getDwid(), dto);
		}
		
		//2、第二步获取全部报警分类配置信息，遍历进行比对处理
		List<MachineAlarmConfig> macList = machineAlarmConfigService.selectAll();
		for(MachineAlarmConfig mac : macList){
			//2.1 获取条件,同时判定条件执行周期 是否到比对周期
			JSONObject tjJson = JSONObject.parseObject(mac.getAlarmcondition());
			String type = tjJson.getString("type");
			JSONObject tjCond = tjJson.getJSONObject("condition");

			boolean flatZq = false;
			Calendar c = Calendar.getInstance();
			String zq = mac.getAlarmcycle();
			String nextTime = mac.getNextalarmtime();
			if(StringUtils.isBlank(nextTime)){
				mac.setLastalarmtime(new SimpleDateFormat(defaultDatePattern).format(c.getTime()));
				c.add(Calendar.HOUR_OF_DAY, Integer.parseInt(zq));
				mac.setNextalarmtime(new SimpleDateFormat(defaultDatePattern).format(c.getTime()));
				machineAlarmConfigService.updateConfigById(mac);
				flatZq = true;
			}else{
				Date nextDate = new Date();
				try {
					nextDate = new SimpleDateFormat(defaultDatePattern).parse(nextTime);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if(nextDate.getTime()<=c.getTime().getTime()){
					mac.setLastalarmtime(new SimpleDateFormat(defaultDatePattern).format(c.getTime()));
					c.add(Calendar.HOUR_OF_DAY, Integer.parseInt(zq));
					mac.setNextalarmtime(new SimpleDateFormat(defaultDatePattern).format(c.getTime()));
					machineAlarmConfigService.updateConfigById(mac);
					flatZq = true;
				}else{
					
				}
			}
			
			if(flatZq){
				System.out.println(DateUtils.getToday() + " alarmMachineTask running alarmType "+ mac.getName());
				//2.2 遍历machineMap 分析每一台盾构机报警状态
				for(Entry<String, ProSecLinePlcDto> entry : machineMap.entrySet()){
					ProSecLinePlcDto dto = entry.getValue();
					
					//2.2.1 根据点位进行比对报警状态
					boolean flat0 = false;
					boolean flat1 = false;
					for(String key:tjCond.keySet()){
						if(dto.getPlcMap().get(key) == null){
							continue;
						}
						String tagValue = dto.getPlcMap().get(key).getTagvalue();
						tagValue = tagValue.replace("-", "");
						String tjStr = tjCond.getString(key);
						tjStr = tjStr.replace("?", tagValue);
						
						boolean fflat = false;
						try {
							String tagTime = dto.getPlcMap().get(key).getTagtime();
							Date tagDate = DateUtils.parse(tagTime);
							if(tagDate.getTime()+1000*60*60*2 >= System.currentTimeMillis()){
								fflat = (boolean)engine.eval(tjStr);
								flat0 = fflat;
								flat1 = !fflat;
							}else{
								fflat = false;
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					//2.2.2 报警则进行消息处理，消息写入、消息推送
					if((flat0 && "0".equals(type)) || (flat1 && "1".equals(type))){
						System.out.println(DateUtils.getToday() + " alarmMachineTask running machineAlarm "+ dto.getTbm_name());
						//2.2.2.1、根据盾构机以及所属的项目获取需要推送的用户列表信息
						List<User> userList = userService.selectUserCIdByUrlOrgz(url, dto.getOrgz_id());
						
						//2.2.2.2 消息数据写入MACHINE_ALARM表
						MachineAlarm alarm = new MachineAlarm();
						String content = dto.getSection_name()+dto.getLine_name()+mac.getName()+"。"+dto.getPro_name();
						alarm.setAlarmname(mac.getName());
						alarm.setAlarmcontent(content);
						alarm.setAlarmtype(mac.getName());
						alarm.setAlarmtime(DateUtils.getToday());
						alarm.setElectricalsystem(mac.getElectricalsystem());
						alarm.setTbmid(dto.getTbm_id());
						alarm.setTbmname(dto.getTbm_name());
						alarm.setProid(dto.getPro_id());
						alarm.setProname(dto.getPro_name());
						alarm.setSectionid(dto.getSection_id());
						alarm.setSectionname(dto.getSection_name());
						alarm.setLineid(dto.getLine_id());
						alarm.setLinename(dto.getLine_name());
						alarm.setParam(JSONObject.toJSONString(dto.getPlcMap()));
						machineAlarmService.insert(alarm);
						
						//2.2.2.3 进行消息推送以及消息写入用户消息表
						List<String> clist = new ArrayList<String>();
						for(User user : userList){
							clist.add(user.getCid());
							SysUserMsg msg = new SysUserMsg();
							msg.setMsgname(alarm.getAlarmname());
							msg.setMsgcontent(alarm.getAlarmcontent());
							msg.setMsgtype("设备报警消息");
							msg.setMsgtime(alarm.getAlarmtime());
							msg.setState("0");
							msg.setLinktable("MACHINE_ALARM");
							msg.setLinkid(alarm.getId());
							msg.setUrl("/machine/alarmhis/info?id="+alarm.getId());
							msg.setUserid(user.getId());
							msg.setOrgzid(user.getOrgzId());
							sysUserMsgService.insert(msg);
							System.out.println(DateUtils.getToday() + " alarmMachineTask running sysUserMsg "+ user.getName());
						}
						GPushService.getInstace().sendCommMultiMsg(clist, content);
					}
					
				}
			}
		}
		System.out.println(DateUtils.getToday() + " alarmMachineTask finsh,Processed "+(System.currentTimeMillis() - openLong)/1000+"S !");
	}
}
