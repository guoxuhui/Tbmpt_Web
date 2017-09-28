package com.crfeb.tbmpt.gpztcl.base.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.Region;
import org.apache.poi.util.SystemOutLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.base.BaseService;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.CommUtils;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.FileUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.commons.utils.UtilMath;
import com.crfeb.tbmpt.gpztcl.base.mapper.DaoXiangMapper;
import com.crfeb.tbmpt.gpztcl.base.mapper.GpztclSczxInfoMapper;
import com.crfeb.tbmpt.gpztcl.base.model.DaoXiang;
import com.crfeb.tbmpt.gpztcl.base.model.GpztclSczxInfo;
import com.crfeb.tbmpt.gpztcl.base.model.dto.GpztclSczxInfoDto;
import com.crfeb.tbmpt.gpztcl.base.model.dto.GpztclSczxpcDto;
import com.crfeb.tbmpt.gpztcl.base.model.dto.TextFileDto;
import com.crfeb.tbmpt.gpztcl.base.service.GpztclCalService;
import com.crfeb.tbmpt.gpztcl.base.service.GpztclSczxInfoService;
import com.crfeb.tbmpt.sys.model.User;
/**
 * <p>实测中线信息明细管理Service实现类</p>
 * <p>系统：管片姿态测量系统</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-12-20</p>
 * @version 1.0
 * @author YangYj
 */
@Service
public class GpztclSczxInfoServiceImpl extends CommonServiceImpl<GpztclSczxInfoMapper, GpztclSczxInfo> implements GpztclSczxInfoService{

    @Autowired
    private GpztclSczxInfoMapper gpztclSczxInfoMapper;
    @Autowired
    private DaoXiangMapper daoXiangMapper;
    
    @Autowired
    private GpztclCalService gpztclCalService;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<GpztclSczxInfo> page = new Page<GpztclSczxInfo>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<GpztclSczxInfoDto> list = gpztclSczxInfoMapper.selectGpztclSczxInfoList(page,condition);
       for(GpztclSczxInfoDto entity :list){
    	  this.count4Px(entity);//计算实测偏差和较差四列数据
       }
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }
    
    
    /**
     * 方法说明：计算实测偏差和较差四列数据
     * @param dto 实测中线数据dto
     * @author:YangYj
     * @Time: 2016年12月28日 上午9:51:39
     */
    public void count4Px(GpztclSczxInfoDto dto){
    	 //实测横向偏差
 	   if(StringUtils.isNotBlank(dto.getJszbX()) && StringUtils.isNotBlank(dto.getSczbX())){
 		   dto.setScpylX(String.valueOf((int)UtilMath.multiply(UtilMath.subtract(Double.parseDouble(dto.getJszbX()), Double.parseDouble(dto.getSczbX()), 4), 1000, 0))); 
 	   }
 	   
 	    //实测竖向偏差
 	   if(StringUtils.isNotBlank(dto.getJszbY()) && StringUtils.isNotBlank(dto.getSczbY())){
 		   dto.setScpylY(String.valueOf((int)UtilMath.multiply(UtilMath.subtract(Double.parseDouble(dto.getJszbY()), Double.parseDouble(dto.getSczbY()), 4), 1000, 0))); 
 	   }
 	   //较差横向
 	   if(StringUtils.isNotBlank(dto.getScpylX()) && StringUtils.isNotBlank(dto.getDxpyX())){
 		   dto.setJcX(String.valueOf((int)UtilMath.subtract(Double.parseDouble(dto.getScpylX()), Double.parseDouble(dto.getDxpyX()), 0))); 
 	   }
 	   //较差竖向
 	   if(StringUtils.isNotBlank(dto.getScpylY()) && StringUtils.isNotBlank(dto.getDxpyY())){
 		   dto.setJcY(String.valueOf((int)UtilMath.subtract(Double.parseDouble(dto.getScpylY()), Double.parseDouble(dto.getDxpyY()), 0))); 
 	   }
    }

    @Override
    public String save(GpztclSczxInfoDto gpztclSczxInfoDto,User user) throws Exception {
    	  String errorMessage = "";
          GpztclSczxInfo gpztclSczxInfo = new GpztclSczxInfo();
          BeanUtils.copyProperties(gpztclSczxInfoDto, gpztclSczxInfo);
    	  BaseService.operatorMessage(gpztclSczxInfo, null, user);
    	  gpztclSczxInfoMapper.insert(gpztclSczxInfo);
    	  return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  gpztclSczxInfoMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(GpztclSczxInfoDto gpztclSczxInfoDto,User user) throws Exception {
    	  String errorMessage = "";
        GpztclSczxInfo gpztclSczxInfo = this.gpztclSczxInfoMapper.selectById(gpztclSczxInfoDto.getId());
        errorMessage = BaseService.operatorMessage(gpztclSczxInfo, gpztclSczxInfoDto, user);
		  gpztclSczxInfoMapper.updateById(gpztclSczxInfo);
    	  return errorMessage;
    }

	   @Override
	   public GpztclSczxInfo findById(String id) throws Exception {
		   return gpztclSczxInfoMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(GpztclSczxInfoDto gpztclSczxInfoDto, String[] clumNames) throws Exception {
		   String id = gpztclSczxInfoDto.getId();
		   Map map = BeanUtils.toMap(gpztclSczxInfoDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<GpztclSczxInfo> lists = gpztclSczxInfoMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (GpztclSczxInfo gpztclSczxInfo2 : lists) {
				   String newId = gpztclSczxInfo2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

	@Override
	public List<TextFileDto> parseTextFile(MultipartFile mulFile) throws Exception {
		
		List<TextFileDto> resultList = new ArrayList<TextFileDto>();
         if(!mulFile.isEmpty()){ //判断文件是否存在
	         InputStreamReader read = new InputStreamReader(mulFile.getInputStream(),"GBK");//编码格式GBK
	         BufferedReader bufferedReader = new BufferedReader(read);
	         String lineTxt = null;
	         String[] tempArray = null;
	         TextFileDto dto = null;
	         while((lineTxt = bufferedReader.readLine()) != null){
	             System.out.println(lineTxt);
	             if(StringUtils.isNotBlank(lineTxt)){
	            	 if(!CommUtils.judgeContainsStr(lineTxt.trim())){
	            		 //若数据行包含字母，则认为是错误数据，排除在外
	            		 dto = new TextFileDto();
	            		 tempArray = lineTxt.split(",");
	            		 this.arrayToDto(tempArray, dto);
	            		 resultList.add(dto);
	            	 }
	             }
	         }
	         read.close();
	     }
         return resultList;
   }
	
	/**
	 * 方法说明：将数组转换成dto对象
	 * @param array 数组
	 * @param dto java对象
	 * @author:YangYj
	 * @Time: 2016年12月22日 上午11:23:08
	 */
	@Override
	public void arrayToDto(String[] array,TextFileDto dto){
		if(null != array && array.length>0){
			int length = array.length;
			for(int i =0;i<length;i++){
				switch(i)
				{
				  case 0:dto.setOneColumn(array[i]) ;break;
				  case 1:dto.setTowColumn(array[i]);break;
				  case 2:dto.setThreeColumn(array[i]);break;
				  case 3:dto.setFourColumn(array[i]);break;
				  case 4:dto.setFiveColumn(array[i]);break;
				  case 5:dto.setSixColumn(array[i]);break;
				  case 6:dto.setSevenColumn(array[i]);break;
				  case 7:dto.setEightColumn(array[i]);break;
				  case 8:dto.setNineColumn(array[i]);break;
				  case 9:dto.setTenColumn(array[i]);break;
				  default :System.out.println("超出10列>>>>>>");break;
				}
			}
		}
	}

	@Override
	public List<GpztclSczxInfoDto> configParseQzyData(GpztclSczxInfoDto dto) throws Exception {
		List<GpztclSczxInfoDto> resultList = new ArrayList<GpztclSczxInfoDto>();
		String json = dto.getDataList();
		if(!"[]".equals(json) && null != json){
			json = json.replace("&quot;", "\"");
			 List<TextFileDto> list = new ArrayList<TextFileDto>();  
		     list = JSONObject.parseArray(json, TextFileDto.class);
		     String hh =String.valueOf( Integer.parseInt(dto.getHh()));//环号列
		     String sczbX = dto.getSczbX();//横向列
		     String sczbY = dto.getSczbY();//纵向列
		     String sczbH = dto.getSczbH();//高程列
		     GpztclSczxInfoDto resutlDto = null;
		     for(TextFileDto textDto:list){
		    	 resutlDto = new GpztclSczxInfoDto();
		    	 resutlDto.setId(CommUtils.getUUID());
		    	 switch(hh)
		    	 {
			    	 case "1":resutlDto.setHh(textDto.getOneColumn());break;
			    	 case "2":resutlDto.setHh(textDto.getTowColumn());break;
			    	 case "3":resutlDto.setHh(textDto.getThreeColumn());break;
			    	 case "4":resutlDto.setHh(textDto.getFourColumn());break;
			    	 default : break;
		    	 }
		    	 switch(sczbX)
		    	 {
			    	 case "1":resutlDto.setSczbX(textDto.getOneColumn());break;
			    	 case "2":resutlDto.setSczbX(textDto.getTowColumn());break;
			    	 case "3":resutlDto.setSczbX(textDto.getThreeColumn());break;
			    	 case "4":resutlDto.setSczbX(textDto.getFourColumn());break;
			    	 default : break;
		    	 }
		    	 switch(sczbY)
		    	 {
			    	 case "1":resutlDto.setSczbY(textDto.getOneColumn());break;
			    	 case "2":resutlDto.setSczbY(textDto.getTowColumn());break;
			    	 case "3":resutlDto.setSczbY(textDto.getThreeColumn());break;
			    	 case "4":resutlDto.setSczbY(textDto.getFourColumn());break;
			    	 default : break;
		    	 }
		    	 switch(sczbH)
		    	 {
			    	 case "1":resutlDto.setSczbH(textDto.getOneColumn());break;
			    	 case "2":resutlDto.setSczbH(textDto.getTowColumn());break;
			    	 case "3":resutlDto.setSczbH(textDto.getThreeColumn());break;
			    	 case "4":resutlDto.setSczbH(textDto.getFourColumn());break;
			    	 default : break;
		    	 }
		    	 if(StringUtils.isNotBlank(resutlDto.getHh())){
		    		 resultList.add(resutlDto);
		    	 }
		     }
		}
		return resultList;
	}

	@Override
	public List<GpztclSczxInfoDto> parseDaoXiangDbFile(MultipartFile file, GpztclSczxpcDto dto) throws Exception {
		String startNo = dto.getStarNo();//起始环号
		String endNo = dto.getEndNo();//截止环号
		Map<String,String[]> tempMap = this.parseDxData(file,startNo,endNo);//或许导向数据的横竖向数据
		List<GpztclSczxInfoDto> resultList = new ArrayList<GpztclSczxInfoDto>();
		String json = dto.getDataList();//获取全站仪数据
		if(!"[]".equals(json) && null != json){
			json = json.replace("&quot;", "\"");
			 List<GpztclSczxInfoDto> list = new ArrayList<GpztclSczxInfoDto>();  
		     list = JSONObject.parseArray(json, GpztclSczxInfoDto.class);
		     String key = "";
		     //========根据环号将全站仪的数据与导向系统的数据对应起来==================//
		     for(GpztclSczxInfoDto dbDto:list){
		    	 key = String.valueOf(Integer.parseInt(dbDto.getHh()));
		    	 if(null != tempMap.get(key)){
		    		 dbDto.setDxpyX(tempMap.get(key)[0]==null?null:String.valueOf((int)UtilMath.multiply(Double.parseDouble(tempMap.get(key)[0]), 1000, 0)));
		    		 dbDto.setDxpyY(tempMap.get(key)[1] == null ? null : String.valueOf((int)UtilMath.multiply(Double.parseDouble(tempMap.get(key)[1]), 1000, 0)));
		    		 this.count4Px(dbDto);//计算实测偏差和较差四列数据
		    	 }
		    	 resultList.add(dbDto);
		     }
		}
		return resultList;
	}
	
	/**
	 * 方法说明：根据导向系统文件数据提取出导向系统的横、竖向数据
	 * @param file 解析文件
	 * @param startNo 起始环号
	 * @param endNo 截止环号
	 * @return 返回map值<key=环号，value="{横向,竖向}">
	 * @author:YangYj
	 * @Time: 2016年12月27日 下午4:21:41
	 */
	public Map<String,String[]> parseDxData(MultipartFile file,String startNo,String endNo) throws Exception{
		Map<String,String[]> tempMap = new HashMap<String,String[]>();
		 int d = 16,aa = 0;
		 char DLE = (char)d;
		 char NUL = (char)aa;
		 String content = FileUtil.readMultipartFile(file);//File(path);
		 String[] items = content.split(String.valueOf(NUL)+String.valueOf(NUL)+String.valueOf(DLE));
		 List<DaoXiang> dxs = new ArrayList<DaoXiang>();
		 for (String item : items) {
				DaoXiang dx = new DaoXiang();
				String[] strs = item.split(String.valueOf(NUL)+String.valueOf(NUL));
				String[] vle = new String[200];
				int j = 0;
				for (String str : strs) {
					String sss = str.replace(String.valueOf(NUL), "");
					sss = sss.replaceAll("(?:߀|ڀ|טݸ|�|ߣ|ـ|܀|ޖ|׀|ٷ|ގ|֥|&|ߴ|ۀ|ހ|܈|\n|\r|	)", "");  
					sss = sss.replace("̸", "");
					if(!sss.equals("")){
						vle[j] = sss;
						j++;
					}
				}
				if(vle.length>3 && StringUtils.isNotBlank(vle[2] )&& CommUtils.judgeContainsNumber(vle[2])){
					dx.copyValue(vle);
					dxs.add(dx);
				}
		}
	    String preHh ="-1";//上次环号
	    String nextHh = "0";//预计下个环号
	    String hh = "";//本次环号 
	    String source = "";
	    String[] xy= {"",""};
//	    this.daoXiangMapper.deleteAll();//删除之前所有的记录
		for (DaoXiang dx : dxs) {
			xy = new String[2];
			source = dx.getAdvanceno();
			xy[0]=dx.getRbhos();
			xy[1]=dx.getRbvos();
		    hh = this.findTrueHhValue(source);
			if(StringUtils.isNotBlank(hh)){
				if(Integer.parseInt(hh) != Integer.parseInt(nextHh)){
					if(hh.endsWith(nextHh)){
						hh = nextHh;
					}else{
						if(Integer.parseInt(preHh) < 0){
							hh = "-"+hh;
						}
					}
				}
//				dx.setAdvanceno(hh);
//				this.daoXiangMapper.insert(dx);
				preHh = hh;
				nextHh =String.valueOf(Integer.parseInt(hh)+1); 
				if(Integer.parseInt(hh) >= Integer.parseInt(startNo) && Integer.parseInt(hh) <=  Integer.parseInt(endNo)){
					//只需要 【 起始环号<=环号<=截止环号 】的数据封装到map中返回
					tempMap.put(hh,xy);
				}
				
			}
		}
		return tempMap;
	}
	
	
	
	/**
	 * 方法说明：从环号原始解析数据中获取数值值
	 * @param source 原始数据
	 * @return 返回数值
	 * @author:YangYj
	 * @Time: 2016年12月27日 下午2:02:26
	 */
	public String findTrueHhValue(String source){
		if(StringUtils.isNotBlank(source) && CommUtils.judgeContainsNumber(source)  ){
			source = source.trim();
			int i = 0;
			boolean flag = true;
			i = CommUtils.findNumberFirstLocationInstr(source);
			source = source.substring(i);
			flag = CommUtils.judgeNotNumberInstr(source);
		   while(flag)
		   {
			   i = CommUtils.findChartFirstLocationInstr(source);
			   source = source.substring(i+1);
		       flag = CommUtils.judgeNotNumberInstr(source);
		   }
		}
		return source;
	}

	@Override
	public List<GpztclSczxInfoDto> countZb(GpztclSczxInfoDto dto,String lineId) throws Exception {
		List<GpztclSczxInfoDto> resultList = new ArrayList<GpztclSczxInfoDto>();
		String json = dto.getDataList();//获取列表数据
		if(!"[]".equals(json) && null != json){
			json = json.replace("&quot;", "\"");
			 List<GpztclSczxInfoDto> list = new ArrayList<GpztclSczxInfoDto>();  
		     list = JSONObject.parseArray(json, GpztclSczxInfoDto.class);
		     JSONArray arr = new JSONArray();
		     JSONObject scJson = null;
		     for(GpztclSczxInfoDto dbDto:list){
		    	 scJson = new JSONObject();
		    	 scJson.put("gphs", dbDto.getHh());//环号
		    	 scJson.put("zbX", dbDto.getSczbX());//坐标X
		    	 scJson.put("zbY", dbDto.getSczbY());//坐标Y
		    	 arr.add(scJson);
		     }
		     String jszb= this.gpztclCalService.calSjzxInfo(arr,lineId );
		     Map<String,String[]> jsMap = new HashMap<String,String[]>();
		     if(StringUtils.isNotBlank(jszb)){
		    	 JSONArray jsArray = new JSONArray();
		    	 JSONObject jsJson = null;
		    	 jsArray = (JSONArray) JSONArray.parse(jszb);
		    	 if(null != jsArray && jsArray.size()>0){
		    		 String[] tempArray = new String[4];
		    		 for(int i =0;i<jsArray.size();i++){
		    			 jsJson = (JSONObject) jsArray.get(i);
		    			 tempArray = new String[4];
		    			 tempArray[0]=String.valueOf(jsJson.get("坐标X"));
		    			 tempArray[1]=String.valueOf(jsJson.get("坐标Y"));
		    			 tempArray[2]=String.valueOf(jsJson.get("高程"));
		    			 tempArray[3]=String.valueOf(jsJson.get("里程"));
		    			 jsMap.put(String.valueOf(jsJson.get("环号")), tempArray);
		    			 System.out.println("环号："+jsJson.get("环号")+"x坐标："+jsJson.get("坐标X")+"坐标Y:"+jsJson.get("坐标Y"));;
		    		 }
		    	 }
		    	 
		     }
		     for(GpztclSczxInfoDto dbDto:list){
		    	 if(null !=jsMap && jsMap.size()>0){
		    		 if(null != jsMap.get(dbDto.getHh()) && jsMap.get(dbDto.getHh()).length>0){
		    			 dbDto.setJszbX(jsMap.get(dbDto.getHh())[0]);
		    			 dbDto.setJszbY(jsMap.get(dbDto.getHh())[1]);
		    			 dbDto.setJszbH(jsMap.get(dbDto.getHh())[2]);
		    			 dbDto.setLc(jsMap.get(dbDto.getHh())[3]);
		    		 }else{
		    			 dbDto.setJszbX(null);
		    			 dbDto.setJszbY(null);
		    			 dbDto.setJszbH(null);
		    			 dbDto.setLc(null);
		    		 }
		    	 }else{
		    		 dbDto.setJszbX(null);
	    			 dbDto.setJszbY(null);
	    			 dbDto.setJszbH(null);
	    			 dbDto.setLc(null);
		    	 }
		    	 this.count4Px(dbDto);//计算实测偏差和较差四列数据
		    	 resultList.add(dbDto);
		     }
		}
		return resultList;
	}

	@Override
	public void myWriteExcelToClient(HttpServletResponse response, ExportDto exportDto,
			List<Map<String, Object>> resultList) throws Exception {
		OutputStream os = this.getWriteOutputStream(response, "application/ms-excel", exportDto);
		try {
			Workbook wb = this.myCreateWorkbook(exportDto, resultList);
			wb.write(os);
			os.flush();
		} catch (Exception e) {
//			log.error("导出Excel出错，" + e.getMessage());
			throw e;
		} finally {
			os.close();
		}
		
	}

	@Override
	public HSSFWorkbook myCreateWorkbook(ExportDto exportDto, List<Map<String, Object>> resultList) throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet();
		CellStyle headerCs = ExportExcelUtil.getHeaderCellStyle(wb);
		//定义标题行(跨两行)
		HSSFRow row1 = sheet.createRow(0);
		HSSFRow row2 = sheet.createRow((short) 1);
		//标题第一行内容 二维数组 数组内容为{"标题内容","跨行数(不跨行则为0)","跨列数(不跨列则为0)"}
		String[][] title = {
				{"环号","1","0"},{"里程","1","0"},{"实测线路中心线坐标(m)","0","3"},
				{"线路中心线设计坐标(m)","0","3"},{"实测偏移量(mm)","0","2"},
				{"导向系统(mm)","0","2"},{"较差(mm)","0","2"},
				{"备注","1","0"}
				};
		//=====跨行跨列参数设置==========//
		int startRowNo = 0;//起始行
		int endRowNo = 0;//结束行
		int startColNo = 0;//起始列
		int endColNo = -1;//结束列
		//=========================头标题第一行设置=================//
		for(int i =0;i<title.length;i++){
			String[] tempTitle = title[i];
			if(Integer.parseInt(tempTitle[1]) == 0){
				endRowNo = 0;
			}else{
				endRowNo = 1;
			}
			startColNo = endColNo+1;
			if(Integer.parseInt(tempTitle[2]) == 0){
				endColNo = startColNo;
			}else{
				endColNo = startColNo+Integer.parseInt(tempTitle[2])-1;
			}
			sheet.addMergedRegion(new Region(startRowNo, (short)startColNo, endRowNo, (short)endColNo));// 四个参数分别是：起始行，起始列，结束行，结束列
			HSSFCell ce1 =  row1.createCell((short) startColNo);
			ce1.setCellValue(tempTitle[0]); // 跨单元格显示的数据
			ce1.setCellStyle(headerCs);// 样式
		}
		
		//=======================标题第二行设置====================================//
		List<String> subTitle = exportDto.getTitleList();
		for(int j =2;j<subTitle.size()-1;j++){
			//此处做了特殊处理，因为第一列环号跨行，第二列里程跨行，所以子列从第3列开始，且最后一列也不处理，最后一列在合并标题中已经展现
			HSSFCell ce2_1 = row2.createCell((short) j);
			// cell1.setEncoding(HSSFCell.ENCODING_UTF_16);
			ce2_1.setCellValue(subTitle.get(j));
			ce2_1.setCellStyle(headerCs);
		}
		//=====================加载数据行=====================================//
		int dataRowNo = 2;
		List<String> fieldList = exportDto.getFieldList();
		CellStyle conCs = ExportExcelUtil.getNormalCellStyle(wb);
		Object cellValue = null;
		HSSFRow dataRow; 
		for (int i = 0; i < resultList.size(); i++) {
			 dataRow = sheet.createRow(dataRowNo++);
			Map<String, Object> dmap = resultList.get(i);
			for (int j = 0; j < fieldList.size(); j++) {
				HSSFCell c = dataRow.createCell(j);
				c.setCellStyle(conCs);
				cellValue = dmap.get(fieldList.get(j));
				if (cellValue != null) {
					c.setCellValue(cellValue.toString());
				}
			}
		}
		return wb;
	}
	
	/**
	 * 获取输入流
	 * @param response
	 * @param contentType
	 * @param exportDto
	 * @return
	 * @throws Exception
	 */
	public   OutputStream getWriteOutputStream(HttpServletResponse response, String contentType,
			ExportDto exportDto) throws Exception {
		response.reset();
		response.setContentType(contentType);

		String extName = "." + exportDto.getFileType();

		response.addHeader("Content-Disposition",
				"attachment; filename=" + new String(exportDto.getFileName().getBytes("GBK"), "ISO-8859-1") + extName);

		return response.getOutputStream();
	}


}