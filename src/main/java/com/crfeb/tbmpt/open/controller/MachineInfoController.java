package com.crfeb.tbmpt.open.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.open.model.ProInfo;
import com.crfeb.tbmpt.open.service.IOpenCommService;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.project.service.IProRProjectSectionService;
import com.crfeb.tbmpt.project.service.IProRSectionLineService;
import com.crfeb.tbmpt.sgkshgl.jjgjgl.model.dto.SgkshglJjgjglDto;
import com.crfeb.tbmpt.sgkshgl.jjgjgl.service.ISgkshglJjgjglService;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.tbmmg.model.ProTbminfo;
import com.crfeb.tbmpt.tbmmg.service.IProTbminfoService;
import com.crfeb.tbmpt.zsjk.plc.model.ProLineWork;
import com.crfeb.tbmpt.zsjk.plc.model.dto.ProPlcBzRealDto;
import com.crfeb.tbmpt.zsjk.plc.service.IProLineWorkService;
import com.crfeb.tbmpt.zsjk.plc.service.IProPlcRealService;

/**
 * @description：机器数据
 * @author：lzh @date：2017-3-13
 */
@Controller
@RequestMapping("/open/jqsj")
public class MachineInfoController extends BaseController {
	@Autowired
	IOpenCommService openCommServiceImpl;
	@Autowired
	IProTbminfoService iProTbminfoService;
	@Autowired
	IProRSectionLineService iProRSectionLineService;
	@Autowired 
	IProProjectinfoService iProProjectinfoService;
	
	@Autowired
    private ISgkshglJjgjglService sgkshgJjjgjglService;
	@Autowired
    private IProProjectinfoService proProjectinfoService;
	@Autowired
    private IProRProjectSectionService proRProjectSectionService;
	@Autowired
    private IProRSectionLineService proRSectionLineService;
	
	@Autowired
    private IProPlcRealService proPlcRealService;
	@Autowired
    private IProLineWorkService proLineWorkService;
	
	@RequestMapping(value = "/getMachineDate")
	@ResponseBody
	public String getMachineDate() {

		return null;
	}

	/**
	 * 方法说明：获得机器数据页面
	 * 
	 * @param model
	 *            数据model
	 * @param token
	 * @return 机器数据页面地址
	 */
	@RequestMapping(value = "/machineDatePage", method = RequestMethod.GET)
	public String getMachineDatePage(Model model, String token,String proId) {
		if (!StringUtils.isEmpty(token)) {
			openCommServiceImpl.relogin(token);
		}
		//User user = getCurrentUser();
		//List<ProProjectinfo> pro = openCommServiceImpl.getProsByUserId(user.getId());
		proId ="fb730f0b50124a698be208f24c395a46";
		List<ProProjectinfo> pro = new ArrayList<ProProjectinfo>();
		pro.add(proProjectinfoService.selectById(proId));
		
		List<ProInfo> proInfos  = new ArrayList<>();
		for (ProProjectinfo proProjectinfo : pro) {
			proInfos.add(openCommServiceImpl.getProInfoByProId(proProjectinfo.getId()));
		}
		model.addAttribute("proList", proInfos);
		//获得组织机构类型
		model.addAttribute("type",1);
		model.addAttribute("proId",proId);
		
		return "open/jqsj/machineDate";
	}
	
	@RequestMapping(value = "/machineDatePage_jj", method = RequestMethod.GET)
	public String getMachineDatePage_jj(Model model, String token,String proId) {
		if (!StringUtils.isEmpty(token)) {
			openCommServiceImpl.relogin(token);
		}
		//User user = getCurrentUser();
		//List<ProProjectinfo> pro = openCommServiceImpl.getProsByUserId(user.getId());
		proId ="fb730f0b50124a698be208f24c395a46";
		List<ProProjectinfo> pro = new ArrayList<ProProjectinfo>();
		pro.add(proProjectinfoService.selectById(proId));
		
		List<ProInfo> proInfos  = new ArrayList<>();
		for (ProProjectinfo proProjectinfo : pro) {
			proInfos.add(openCommServiceImpl.getProInfoByProId(proProjectinfo.getId()));
		}
		model.addAttribute("proList", proInfos);
		//获得组织机构类型
		model.addAttribute("type",1);
		model.addAttribute("proId",proId);
		
		return "open/jqsj/machineDate_jj";
	}
	
	@RequestMapping(value = "/machineDatePage_dx", method = RequestMethod.GET)
	public String getMachineDatePage_dx(Model model, String token,String proId) {
		if (!StringUtils.isEmpty(token)) {
			openCommServiceImpl.relogin(token);
		}
		//User user = getCurrentUser();
		//List<ProProjectinfo> pro = openCommServiceImpl.getProsByUserId(user.getId());
		proId ="fb730f0b50124a698be208f24c395a46";
		List<ProProjectinfo> pro = new ArrayList<ProProjectinfo>();
		pro.add(proProjectinfoService.selectById(proId));
		
		List<ProInfo> proInfos  = new ArrayList<>();
		for (ProProjectinfo proProjectinfo : pro) {
			proInfos.add(openCommServiceImpl.getProInfoByProId(proProjectinfo.getId()));
		}
		model.addAttribute("proList", proInfos);
		//获得组织机构类型
		model.addAttribute("type",1);
		model.addAttribute("proId",proId);
		
		return "open/jqsj/machineDate_dx";
	}
	
	/**
	 * 方法说明：获得机器数据页面
	 * 
	 * @param model
	 *            数据model
	 * @param token
	 * @return 机器数据页面地址
	 */
	/*
	@RequestMapping(value = "/machineDatePage", method = RequestMethod.GET)
	public String getMachineDatePage(Model model, String token) {
		if (!StringUtils.isEmpty(token)) {
			openCommServiceImpl.relogin(token);
		}
		User user = getCurrentUser();
		ProProjectinfo proinfo = openCommServiceImpl.getProByUserId(user.getId());
		ProInfo info = openCommServiceImpl.getProInfoByProId(proinfo.getId());

		SysEmployee emp = openCommServiceImpl.getSysEmployeeById(proinfo.getEmpId());
		model.addAttribute("info", info);
		model.addAttribute("fuzeren", emp);
		return "open/jqsj/machineDate";
	}*/

	/**
	 * 方法说明：根据线路id获取tbm信息
	 * 
	 * @param lineId
	 *            线路Id
	 * @return tbm信息
	 */
	@RequestMapping(value = "/getTbmInfo", method = RequestMethod.GET)
	@ResponseBody
	public Object getTbmInfo(String lineId) {
		try {
			ProRSectionLine line = iProRSectionLineService.selectById(lineId);
			ProTbminfo tbm = iProTbminfoService.selectById(line.getTbmId());
			if (tbm == null) {
				return renderError("该线路暂未分配盾构机");
			} else {
				return tbm;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 方法说明：获取机器数据
	 * 
	 * @param tbmId
	 *            盾构机id
	 * @return 机器数据
	 */
	@RequestMapping(value = "/getjqsj", method = RequestMethod.GET)
	@ResponseBody
	public Object getJQSJ(String id) {
		try {
			String endpoint = "http://localhost:8080/services/jqsjService";
			Service service = new Service();
			Call call = (Call) service.createCall();// 通过service创建call对象
			// 设置service所在URL
			call.setTargetEndpointAddress(new java.net.URL(endpoint));
			// 方法名(processService)与MyService.java方法名保持一致
			call.setOperationName("getSsJqsj");
			// Object 数组封装了参数，参数为"This is Test!",调用processService(String arg)
			String parates = "{\"appId\":\"321\",\"appSecret\":\"999999\",\"arg\":" +
							"{\"dgjId\":\""+id+"\"}" + "}";
			String ret = (String) call.invoke(new Object[] { parates });
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	 @RequestMapping(value = "/getGeoLine", method = RequestMethod.GET)
	    public String addPage(String pid,Model model) {
	    	
	    	ProProjectinfo project = proProjectinfoService.selectById(pid);
	    	model.addAttribute("projectPoint",JSON.toJSONString(project.getPosition()));
	    	model.addAttribute("projectId",JSON.toJSONString(project.getId()));

	    	List<List<SgkshglJjgjglDto>> qtLineLists = new ArrayList<List<SgkshglJjgjglDto>>();
	    	List<ProRProjectSection> secs = proRProjectSectionService.getSectionByProId(pid);
	    	for(ProRProjectSection sec:secs){
	    		//查找这个区间的其他线路在地图上显示
		    	List<ProRSectionLine> sectionList = proRSectionLineService.getLineBySectionId(sec.getId());
		    	for(ProRSectionLine l:sectionList){
	    			List<SgkshglJjgjglDto> qtLineList=sgkshgJjjgjglService.selectBylineId(l.getId());
	    			if(!qtLineList.isEmpty()){
	    				qtLineList.get(0).setLineName(l.getLineName());
	    				qtLineLists.add(qtLineList);
	    	    	}
		    	}
	    	}
	    	model.addAttribute("project",JSON.toJSONString(project));
	    	model.addAttribute("qtLineLists",JSON.toJSONString(qtLineLists));  
	        return "open/jqsj/geoLinePage";
	    }
	 
	 
		/**
		 * 根据盾构机编号获取数据
		 * @param parameter
		 * @return
		 */
	    @RequestMapping("/getDataByDgjId")
	    @ResponseBody
		public Object getDataByDgjId(String dgjId){
	    	List<ProPlcBzRealDto> list = null;
	    Map<String,ProPlcBzRealDto> nlist = new HashMap<String,ProPlcBzRealDto>();
	    	try {
	    		if(org.apache.commons.lang.StringUtils.isEmpty(dgjId)) {
	    			dgjId = "f4cfd546005845c9998750701abf0b58";
	    		}
	    		list = proPlcRealService.getSsJqsj(dgjId);
	    		if(list != null){
	    			for(ProPlcBzRealDto dto:list){
	    				nlist.put(dto.getDwid(), dto);
	    			}
	    		}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return renderSuccess(nlist);
		}
	    
	    
	    
	    /**
		 * 根据盾构机编号获取数据
		 * @param parameter
		 * @return
		 */
	    @RequestMapping("/DrivingBehavior")
	    @ResponseBody
		public Object DrivingBehavior(String id){
		    	String lineId = "e59ef5a2c91d49a3829bd593346c718d";

		    	Map<String,Object> result = new HashMap<String, Object>();
	    		result.put("res", true);
		    	try {
		    		
		    		Map<String,Object> res = new HashMap<String,Object>();
		    		
		    		ProRSectionLine line = proRSectionLineService.selectById(lineId);
		    		ProRProjectSection section = proRProjectSectionService.selectById(line.getSectionId());
		    		ProProjectinfo pro = proProjectinfoService.selectById(line.getProId());
		    		ProTbminfo tbm = iProTbminfoService.selectById(line.getTbmId());
		    		
				Map<String,ProPlcBzRealDto> cmap = new HashMap<String,ProPlcBzRealDto>();
				List<ProPlcBzRealDto> list = proPlcRealService.getSsJqsj(line.getTbmId());
				List<ProLineWork> wlist = proLineWorkService.selectByLineId(line.getId());
		    		if(list != null){
		    			for(ProPlcBzRealDto dto:list){
		    				cmap.put(dto.getDwid(), dto);
		    			}
		    		}
		    		
		    		//获取线路信息LineData
		    		Map<String,Object> LineData = new HashMap<String,Object>();
		    		LineData.put("Line_Name", line.getLineName());
		    		LineData.put("Line_Id", line.getId());
		    		LineData.put("Section_Name", section.getSectionName());
		    		LineData.put("Section_Id", section.getId());
		    		LineData.put("Pro_Name", pro.getProName());
		    		LineData.put("Pro_Id", pro.getId());
		    		LineData.put("Tbm_Name", tbm.getTbmName());
		    		LineData.put("Tbm_Id", tbm.getId());
		    		LineData.put("Start_RingNum", line.getStartRingnum());
		    		LineData.put("Ring_Count", line.getRingCount());
		    		LineData.put("Start_Mileage", line.getStartMileage());
		    		LineData.put("End_Mileage", line.getEndMileage());
		    		LineData.put("TunnelTime", line.getTunneltime());
		    		LineData.put("TunnelLength", line.getTunnellength());
		    		LineData.put("Mileage_Prefix", line.getMileagePrefix());
		    		
		    		//获取盾构机信息TbmData
		    		Map<String,Object> TbmData = new HashMap<String,Object>();
		    		TbmData.put("currRing", Integer.parseInt(cmap.get("TY_DXXT_0001").getTagvalue()));
		    		TbmData.put("rings", Integer.parseInt(cmap.get("TY_DXXT_0001").getTagvalue()));
		    		
		    		TbmData.put("zgzt_jj", cmap.get("TY_ZGZT_0001").getTagvalue());
		    		TbmData.put("zgzt_ph", cmap.get("TY_ZGZT_0002").getTagvalue());
		    		TbmData.put("zgzt_stop", cmap.get("TY_ZGZT_0003").getTagvalue());
		    		
		    		TbmData.put("dataTime", DateUtils.getToday());
		    		TbmData.put("ring_Count", line.getRingCount());
		    		
		    		int chs = Integer.parseInt(cmap.get("TY_DXXT_0001").getTagvalue());
		    		int zhs = line.getRingCount().intValue();
		    		
		    		int days = (zhs - chs)/6;
		    		int fsh = (chs*100/zhs);
		    		
		    		TbmData.put("days", days);
		    		TbmData.put("tunnelTime", line.getTunneltime());
		    		TbmData.put("ageRing", 6);
		    		TbmData.put("finish", fsh);
		    		TbmData.put("line_mileage", line.getTunnellength());
		    		//获取线路信息GeoData
		    		Map<String,Object> GeoData = new HashMap<String,Object>();
		    		GeoData.put("cur_position", "120.32957373,31.5841011052");
		    		GeoData.put("line_paths", "120.32978653907776,31.584110747598675;120.3297758102417,31.584275260205174;120.32975971698761,31.58441235382209;120.32975971698761,31.58441235382209;120.32975971698761,31.58441235382209;120.32975971698761,31.58441235382209;120.32974362373352,31.58458600544731;120.32971680164337,31.58489217989301;120.3296685218811,31.585280608712295;120.32959342002868,31.585860964049086;120.32951831817627,31.586313364521178;120.32946467399597,31.58648244292168;120.32939493656158,31.58661953329145;120.32928764820099,31.58681145947031;120.32909989356994,31.587085639040044;120.32886385917663,31.58734153924395;120.32854199409485,31.58761571725394;120.32824158668518,31.587862476773175;120.32786071300506,31.588122944445608;120.32764077186584,31.588260032401795;120.32743692398071,31.588324006712326;120.3270936012268,31.58839712015631;120.32667517662048,31.588433676856773;120.32611191272735,31.58846109437272;120.32566130161285,31.588488511880577;120.32511413097381,31.58852963812726;120.32445967197418,31.58857533393563;120.32406270503998,31.588602751409873;120.32292544841766,31.58867586463511;120.32183110713959,31.588739838660153;120.3208976984024,31.58879924307262;120.32043099403381,31.58883123004825;120.32018423080444,31.58884950831513");
			
		    		res.put("LineData", LineData);
		    		res.put("TbmData", TbmData);
		    		res.put("GeoData", GeoData);
		    		
		    		res.put("WorData", wlist);
		    		
		    		result.put("result", res);
		    	
		    	} catch (Exception e) {
				e.printStackTrace();
			}
		    	
			return result;
		}
	    
	    public static void main(String[] args) {
    			int days = (1652 - 867)/6;
    			int fsh = (867*100/1652);
    			System.out.println(days + " " + fsh);
		}
}
