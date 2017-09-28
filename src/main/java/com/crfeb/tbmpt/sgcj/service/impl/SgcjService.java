package com.crfeb.tbmpt.sgcj.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crfeb.tbmpt.commons.utils.SysPropertieUtil;
import com.crfeb.tbmpt.commons.utils.XmlUtils;
import com.crfeb.tbmpt.project.model.ProProjectinfo;
import com.crfeb.tbmpt.project.model.ProRProjectSection;
import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.project.service.IProProjectinfoService;
import com.crfeb.tbmpt.project.service.IProRProjectSectionService;
import com.crfeb.tbmpt.project.service.IProRSectionLineService;
import com.crfeb.tbmpt.sgcj.model.SgcjLine;
import com.crfeb.tbmpt.sgcj.model.SgcjPro;
import com.crfeb.tbmpt.sgcj.model.SgcjSec;
import com.crfeb.tbmpt.sgcj.service.ISgcjService;
import com.crfeb.tbmpt.sgkshgl.jcdtgl.mapper.SgkshglJcdtglMapper;
import com.crfeb.tbmpt.sgkshgl.jcdtgl.model.SgkshglJcdtgl;
import com.crfeb.tbmpt.sys.base.model.dto.SysFujianDto;
import com.crfeb.tbmpt.sys.base.service.SysFujianService;

@Service
public class SgcjService implements ISgcjService {

	public static final String JC_URL = "/rest/services/MAIN/JCPOINT/FeatureServer/0";
	public static final String ZX_URL = "/rest/services/MAIN/ZXPOINT/FeatureServer/0";
	
	@Autowired
    private IProRSectionLineService proRSectionLineService;
    @Autowired
    private IProRProjectSectionService proRProjectSectionService;
    @Autowired
    private IProProjectinfoService proProjectinfoService;
    @Autowired
    private SysFujianService sysFujianService;
    @Autowired
    private SgkshglJcdtglMapper sgkshglJcdtglMapper ;
	@Override
	public String getConfig(String proId) {
		
		Element conf = null;
		ProProjectinfo pro = null;
		SgcjPro sgcjPro = new SgcjPro();
		try {
		pro =  proProjectinfoService.selectById(proId);
		List<ProRProjectSection> secs = null;
		if(pro != null){
			sgcjPro.setPro(pro);
			List<SysFujianDto> fjList = sysFujianService.findFuJianListByForeignId(pro.getId(), null, null);
			sgcjPro.setFjs(fjList);
			List<SgcjSec> sgcjSecs = new ArrayList<SgcjSec>();
			secs = proRProjectSectionService.getSectionByProId(proId);
			for(ProRProjectSection sec:secs){
				SgcjSec sgcjSec = new SgcjSec();
				sgcjSec.setSec(sec);
				List<SgcjLine> sgcjLines = new ArrayList<SgcjLine>();
				List<ProRSectionLine> lines = proRSectionLineService.getLineBySectionId(sec.getId());
				Map<String,Object> secMap = new HashMap<String,Object>();
				secMap.put("REF_ID", sec.getId());
				List<SgkshglJcdtgl> secjcdts = sgkshglJcdtglMapper.selectByMap(secMap);
				if(secjcdts != null && secjcdts.size()>0){
					sgcjSec.setJcdt(secjcdts.get(0));
				}else{
					continue;
				}
				for(ProRSectionLine li:lines){
					SgcjLine sgcjLine = new SgcjLine();
					sgcjLine.setLine(li);
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("REF_ID", li.getId());
					List<SgkshglJcdtgl> jcdts = sgkshglJcdtglMapper.selectByMap(map);
					if(jcdts != null && jcdts.size()>0){
						sgcjLine.setJcdt(jcdts.get(0));
					}else{
						continue;
					}
					sgcjLines.add(sgcjLine);
				}
				if(secjcdts != null && secjcdts.size()>0){
					sgcjSec.setJcdt(secjcdts.get(0));
				}
				sgcjSec.setLines(sgcjLines);
				sgcjSecs.add(sgcjSec);
			}
			sgcjPro.setSecs(sgcjSecs);
			
			
			conf = XmlUtils.getBaseConfigXml(pro.getProName());
			//创建项目XML
	 		Element project = conf.addElement("project");
	 		project.addAttribute("id", pro.getId());
	 		project.addAttribute("xmjc", pro.getDescription());
	 		project.addAttribute("cjdw", pro.getCjdw());
	 		project.addAttribute("jldw", pro.getJldw());
	 		project.addAttribute("jsdw", pro.getJsdw());
	 		project.addAttribute("country", pro.getCountry());
	 		project.addAttribute("area", pro.getArea());
	 		project.addAttribute("province", pro.getProvince());
	 		project.addAttribute("city", pro.getCity());
	 		List<SysFujianDto> fjs = sgcjPro.getFjs();
	 		if(fjs != null && fjs.size()>0){
	 			project.addAttribute("imgUrl", SysPropertieUtil.getInstince().getBrowsePath()+"/"+fjs.get(0).getFilePath());	
	 		}
	 		project.addAttribute("id", pro.getId());
			for(SgcjSec sec:sgcjSecs){
				Element section = project.addElement("section");
				section.addAttribute("id", sec.getSec().getId());
				section.addAttribute("name", sec.getSec().getSectionName());
				section.addAttribute("dcqk", sec.getSec().getDcqk());
				section.addAttribute("layerUrl", "");
				List<SgcjLine> sgcjLines = sec.getLines();
				for(SgcjLine li:sgcjLines){
					Element line = section.addElement("line");
					line.addAttribute("id", li.getLine().getId());
					line.addAttribute("name", li.getLine().getLineName());
					line.addAttribute("zhs", String.valueOf(li.getLine().getRingCount().intValue()));
					line.addAttribute("state", String.valueOf(li.getLine().getLineStatus()));
					line.addAttribute("layerUrl", "");
					line.addAttribute("tbmId", li.getLine().getTbmId());
					line.addAttribute("tbmMan", li.getLine().getDgChauffeur());
					line.addAttribute("sfrq", li.getLine().getSjsfrq());
					line.addAttribute("wgrq", li.getLine().getSjcdrq());
				}
				
			}
			//创建widget
			Element ele = conf.addElement("widget");
			ele.addAttribute("right", "0");
			ele.addAttribute("bottom", "0");
			ele.addAttribute("config", "widgets/OverviewMap/OverviewMapWidget.xml");
			ele.addAttribute("url", "widgets/OverviewMap/OverviewMapWidget.swf");
			ele = conf.addElement("widget");
			ele.addAttribute("right", "20");
			ele.addAttribute("top", "75");
			ele.addAttribute("config", "widgets/MapSwitcher/MapSwitcherWidget.xml");
			ele.addAttribute("url", "widgets/MapSwitcher/MapSwitcherWidget.swf");
			ele = conf.addElement("widget");
			ele.addAttribute("left", "0");
			ele.addAttribute("top", "0");
			ele.addAttribute("config", "widgets/HeaderController/HeaderControllerWidget.xml");
			ele.addAttribute("url", "widgets/HeaderController/HeaderControllerWidget.swf");
			ele = conf.addElement("widget");
			ele.addAttribute("left", "20");
			ele.addAttribute("top", "75");
			ele.addAttribute("config", "widgets/Draw/DrawWidget.xml");
			ele.addAttribute("url", "widgets/Draw/DrawWidget.swf");
			ele = conf.addElement("widget");
			ele.addAttribute("left", "3");
			ele.addAttribute("bottom", "3");
			ele.addAttribute("config", "widgets/Coordinate/CoordinateWidget.xml");
			ele.addAttribute("url", "widgets/Coordinate/CoordinateWidget.swf");
			ele = conf.addElement("widget");
			ele.addAttribute("right", "250");
			ele.addAttribute("bottom", "3");
			ele.addAttribute("config", "widgets/Bottom/BottomWidget.xml");
			ele.addAttribute("url", "widgets/Bottom/BottomWidget.swf");
			//创建map
			Element map = conf.addElement("map");
			map.addAttribute("wraparound180", "true");
			map.addAttribute("addarcgisbasemaps", "false");
			map.addAttribute("scaleBarVisible", "true");
			map.addAttribute("scalebar", "metric");
			
			//创建baseMap
			Element basemaps = map.addElement("basemaps");
			String url = SysPropertieUtil.getInstince().getMapPath()+"/rest/services/{0}/MapServer";
			String iconUrl = SysPropertieUtil.getInstince().getMapPath()+
					"/admin/services/{0}.MapServer/iteminfo/thumbnail/thumbnail.png?token="+
					SysPropertieUtil.getInstince().getArcgisToken();
			for(int i=0;i<sgcjSecs.size();i++){
				SgcjSec sec = sgcjSecs.get(i);
				Element layer = basemaps.addElement("layer");
				layer.addAttribute("label", sec.getSec().getSectionName());
				layer.addAttribute("type", "tiled");
				if(i == 0){
					layer.addAttribute("visible", "true");
				}else{
					layer.addAttribute("visible", "false");
				}
				layer.addAttribute("layerType", "PM");
				layer.addAttribute("refId", sec.getSec().getId());
				SgkshglJcdtgl seJcdt = sec.getJcdt();
				String secPath = seJcdt.getMapPath();
				layer.addAttribute("url", url.replace("{0}", secPath));
				layer.addAttribute("icon", iconUrl.replace("{0}", secPath));
				String secName = sec.getSec().getSectionName();
				List<SgcjLine> sgcjLines = sec.getLines();
				for(SgcjLine li:sgcjLines){
					Element ly = basemaps.addElement("layer");
					ly.addAttribute("label", secName+"-"+li.getLine().getLineName());
					ly.addAttribute("type", "tiled");
					ly.addAttribute("visible", "false");
					ly.addAttribute("layerType", "DM");
					ly.addAttribute("refId", li.getLine().getId());
					SgkshglJcdtgl liJcdt = li.getJcdt();
					String liPath = liJcdt.getMapPath();
					ly.addAttribute("url", url.replace("{0}", liPath));
					ly.addAttribute("icon", iconUrl.replace("{0}", liPath));
				}
			}
			Element operationallayers = map.addElement("operationallayers");
			Element layer_dm = operationallayers.addElement("layer");
			layer_dm.addAttribute("label", "地质信息");
			layer_dm.addAttribute("type", "feature");
			layer_dm.addAttribute("visible", "false");
			layer_dm.addAttribute("alpha", "0.5");
			layer_dm.addAttribute("popupconfig", "popups/PopUp_DmPoint.xml");
			layer_dm.addAttribute("url", "http://106.15.94.72:6080/arcgis/rest/services/WH0206/DM_XZ_ZX4/MapServer/3");
			
			
			//创建operationallayers
			Element layer_jc = operationallayers.addElement("layer");
			layer_jc.addAttribute("label", "监测点");
			layer_jc.addAttribute("type", "feature");
			layer_jc.addAttribute("visible", "false");
			layer_jc.addAttribute("alpha", "0.8");
			layer_jc.addAttribute("popupconfig", "popups/PopUp_JcPoint.xml");
			layer_jc.addAttribute("url", SysPropertieUtil.getInstince().getFeatureMaPath()+JC_URL);
			
			Element layer_zx = operationallayers.addElement("layer");
			layer_zx.addAttribute("label", "中线点");
			layer_zx.addAttribute("type", "feature");
			layer_zx.addAttribute("visible", "false");
			layer_zx.addAttribute("alpha", "0.8");
			layer_zx.addAttribute("popupconfig", "popups/PopUp_ZxPoint.xml");
			layer_zx.addAttribute("url", SysPropertieUtil.getInstince().getFeatureMaPath()+ZX_URL);
			
			
			//业务功能组件 
			Element widgetcontainer = conf.addElement("widgetcontainer");
			widgetcontainer.addAttribute("layout", "float");
			Element widget = widgetcontainer.addElement("widget");
			widget.addAttribute("label", "打印");
			widget.addAttribute("icon", "assets/images/i_print.png");
			widget.addAttribute("top", "180");
			widget.addAttribute("right", "5");
			widget.addAttribute("config", "widgets/Print/PrintWidget.xml");
			widget.addAttribute("url", "widgets/Print/PrintWidget.swf");
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(conf == null){
			conf = XmlUtils.getBaseConfigXml("项目暂无数据，请补录");
		}
		return conf.asXML();
	}

	
	
}
