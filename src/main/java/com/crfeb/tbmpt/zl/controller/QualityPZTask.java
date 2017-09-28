package com.crfeb.tbmpt.zl.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.crfeb.tbmpt.commons.scan.SpringUtils;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.project.service.IProRSectionLineService;
import com.crfeb.tbmpt.tbmmg.model.ProTbminfo;
import com.crfeb.tbmpt.tbmmg.service.IProTbminfoService;
import com.crfeb.tbmpt.webservice.model.Tunnel;
import com.crfeb.tbmpt.zl.model.QualityInfo;
import com.crfeb.tbmpt.zl.service.IQualityInfoService;
import com.crfeb.tbmpt.zsjk.plc.model.dto.ProPlcBzRealDto;
import com.crfeb.tbmpt.zsjk.plc.service.IProPlcRealService;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管片上报定时任务
 */
public class QualityPZTask {

    private static final Log log = LogFactory.getLog(QualityPZTask.class);

    
    @Autowired
    private  IProRSectionLineService proRSectionLineService;
    
    @Autowired
    private IProPlcRealService proPlcRealService;
    
    @Autowired
    private IProTbminfoService proTbminfoService;
    
    @Autowired
	IQualityInfoService  iQualityInfoService;
    
    /**
     * 管片上报定时任务
     */
    public  int qualitypzOpen() {
        int result = 0;
        proRSectionLineService = SpringUtils.getBean(IProRSectionLineService.class);
        proTbminfoService = SpringUtils.getBean(IProTbminfoService.class);
        proRSectionLineService = SpringUtils.getBean(IProRSectionLineService.class);
        proPlcRealService = SpringUtils.getBean(IProPlcRealService.class);
        iQualityInfoService = SpringUtils.getBean(IQualityInfoService.class);
        List<ProRSectionLine> lineList = proRSectionLineService.selectLineList();
        if(lineList !=null && lineList.size()>0){
        	//for (int i = 0; i < lineList.size(); i++) {
    		for (ProRSectionLine line : lineList) {
                try {
                	//线路数据 为空 跳过
                	if(StringUtils.isBlank(line.getId())) continue;
                		
            		//查询 当前盾构掘进环号 iQualityInfoService
            		
                    //ProRSectionLine line = lineList.get(i);
                    //线路盾构机数据 为空 跳过
					if(StringUtils.isBlank(line.getTbmId())) continue;
					ProTbminfo tbm = proTbminfoService.selectById(line.getTbmId());
					List<ProPlcBzRealDto> pbzs = new ArrayList<ProPlcBzRealDto>();
					String dwIds = "TY_DXXT_0001";
					//String dwIds = "";
					//盾构机数据 为空 跳过
					if(tbm==null || (tbm != null && StringUtils.isBlank(tbm.getId()))) continue;
					pbzs = proPlcRealService.getSsJqsjByDw(tbm.getId(), dwIds);
					//盾构机机器数据实时数据 为空 跳过
					if(pbzs == null || (pbzs != null && pbzs.size()!=1)) continue;
					if(!"TY_DXXT_0001".equals(pbzs.get(0).getDwid())) continue;
					String jjhh = pbzs.get(0).getTagvalue();
					String jjsj = pbzs.get(0).getTagtime();
					//环号为空 跳过
					if(StringUtils.isBlank(jjhh)) continue;
					int ring = Integer.parseInt(jjhh);
					if(ring>0){
						//保存 没有 管片上报信息 的环号 
						List<String> cycleNoList = new ArrayList<String>();
						//查询 当前线路 所有已上报的管片 的环号  
						List<QualityInfo> qualityList = iQualityInfoService.selectListByLineId(line.getId());
						if(qualityList !=null && qualityList.size()>0){
							List<String> cycleNoList2 = new ArrayList<String>();
							for(QualityInfo info : qualityList){
								cycleNoList2.add(info.getCycleNo());
							}
							for(int i=ring; i>=1;i--){
								String s=String.valueOf(i);
								
								if(!cycleNoList2.contains(s)){
									cycleNoList.add(s);
								}
							}
						}else{
							for(int i=ring; i>=1;i--){
								String s=String.valueOf(i);
								cycleNoList.add(s);
							}
						}
						if(cycleNoList !=null && cycleNoList.size()>0){
							List<QualityInfo> qualityInfoList = new ArrayList<QualityInfo>();
							int ing =0;
							for(String no : cycleNoList){
								QualityInfo t = new QualityInfo();
								t.setProName(line.getProId());//项目Id
								t.setSection(line.getSectionId());//区间Id
								t.setLine(line.getId());//线路Id
								t.setCycleNo(no);//环号
								t.setHasProblem("无");
								qualityInfoList.add(t);
								ing++;
							}
							if(qualityInfoList !=null && qualityInfoList.size()>0){
								int ing2 =iQualityInfoService.saveList(qualityInfoList);
								result +=ing2;
							}
						}
					}
                } catch (Exception e) {
                    log.info(e.getMessage());
                }
            }
        }
        
        return result;
    }
    
    /**
     * 管片上报定时任务
     */
    /*public static int qualitypzOpen(final HttpServletRequest request, final HttpServletResponse response, final IQualityInfoService iQualityInfoService, final IProProjectinfoService proProjectinfoService) {
        int result = 0;
        List<ProProjectinfo> proList = proProjectinfoService.getProjectInfosBylist();
        for (int i = 0; i < proList.size(); i++) {
            try {
                String endpoint = "http://127.0.0.1:8080/services/jqsjService";
                Service service = new Service();
                Call call = (Call) service.createCall();// 通过service创建call对象
                // 设置service所在URL
                call.setTargetEndpointAddress(new java.net.URL(endpoint));
                // 方法名(processService)与MyService.java方法名保持一致
                call.setOperationName("getTunnelInfo");
                // Object 数组封装了参数，参数为"This is Test!",调用processService(String arg)
                String parates = "{\"xmId\":\"" + proList.get(i).getId() + "\",\"qjId\":\"" + proList.get(i).getArea() + "\"," + "\"xlId\":\"" + proList.get(i).getLine() + "\"}";
                String tunnelReturn = (String) call.invoke(new Object[]{parates});
                if (tunnelReturn != null) {
                    JSONObject returnJson = JSON.parseObject(tunnelReturn);
                    if (returnJson != null) {
                        if (returnJson.containsKey("obj")) {
                            List<Tunnel> listArray = JSONArray.parseArray(returnJson.get("obj").toString(), Tunnel.class);
                            for (int j = 0; j < listArray.size(); j++) {
                                try {
                                    QualityInfo t = new QualityInfo();
                                    t.setProName(listArray.get(j).getXmId());//项目Id
                                    t.setSection(listArray.get(j).getQjId());//区间Id
                                    t.setLine(listArray.get(j).getXlId());//线路Id
                                    t.setCycleNo(listArray.get(j).getRing());//环号
                                    t.setFixDate(listArray.get(j).getInstalTime());//日期
                                    iQualityInfoService.save(t);
                                    result++;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                log.info(e.getMessage());
            }
        }
        return result;
    }*/

}
