package com.crfeb.tbmpt.gpztcl.base.service.impl;

import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.ParseExcelFile;
import com.crfeb.tbmpt.commons.utils.ParseXLSXFile;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.base.BaseService;
import com.crfeb.tbmpt.gpztcl.base.model.dto.GpztclSjzxInfoDto;
import com.crfeb.tbmpt.gpztcl.base.model.dto.GpztclSjzxInfoParentDto;
import com.crfeb.tbmpt.gpztcl.base.mapper.GpztclSjzxInfoMapper;
import com.crfeb.tbmpt.gpztcl.base.model.GpztclSjzxInfo;
import com.crfeb.tbmpt.gpztcl.base.service.GpztclSjzxInfoService;
import com.crfeb.tbmpt.project.mapper.ProRSectionLineMapper;
import com.crfeb.tbmpt.project.model.ProRSectionLine;
import com.crfeb.tbmpt.project.model.vo.SectionLineVo;
/**
 * <p>线路中心线信息管理Service实现类</p>
 * <p>系统：管片姿态测量系统</p>
 * <p>模块：基础模块</p>
 * <p>日期：2016-12-20</p>
 * @version 1.0
 * @author wangbinbin
 */
@Service
public class GpztclSjzxInfoServiceImpl extends CommonServiceImpl<GpztclSjzxInfoMapper, GpztclSjzxInfo> implements GpztclSjzxInfoService{

    @Autowired
    private GpztclSjzxInfoMapper gpztclSjzxInfoMapper;
    @Autowired
    private ProRSectionLineMapper proRSectionLineMapper;

    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<GpztclSjzxInfo> page = new Page<GpztclSjzxInfo>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<GpztclSjzxInfo> list = gpztclSjzxInfoMapper.selectGpztclSjzxInfoList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }
    
	@Override
	public void selectDataGridParent(PageInfo pageInfo) throws Exception {
		  Page<GpztclSjzxInfoParentDto> page = new Page<GpztclSjzxInfoParentDto>(pageInfo.getNowpage(), pageInfo.getSize());
	       List<GpztclSjzxInfoParentDto> dtos = new ArrayList<>();
	       List<SectionLineVo> list = proRSectionLineMapper.selectVoList(page,pageInfo.getCondition(), pageInfo.getSort(), pageInfo.getOrder());
	       for (SectionLineVo sectionLineVo : list) {
	    	   GpztclSjzxInfoParentDto gpztclSjzxInfoParentDto = new GpztclSjzxInfoParentDto();
	    	   BeanUtils.copyProperties(sectionLineVo, gpztclSjzxInfoParentDto);
	    	   dtos.add(gpztclSjzxInfoParentDto);
	       }
	       pageInfo.setRows(dtos);
	       pageInfo.setTotal(page.getTotal());
	}

    @Override
    public String save(GpztclSjzxInfoDto gpztclSjzxInfoDto,User user) throws Exception {
    	String errorMessage = "";
    	String oneClum = gpztclSjzxInfoDto.getClumOne();
    	oneClum  = oneClum.replace("&quot;", "\"");
    	List<GpztclSjzxInfo> addlist = new ArrayList<>();
    	
    	List<GpztclSjzxInfoDto> dtos = JSONArray.parseArray(oneClum,GpztclSjzxInfoDto.class);
    	int i=1;
    	for (GpztclSjzxInfoDto gpztclSjzxInfoDto2 : dtos) {
    		GpztclSjzxInfo gpztclSjzxInfo = new GpztclSjzxInfo();
    		BeanUtils.copyProperties(gpztclSjzxInfoDto2, gpztclSjzxInfo);
    		BaseService.operatorMessage(gpztclSjzxInfo, null, user);
    		gpztclSjzxInfo.setGcBh(gpztclSjzxInfoDto.getGcBh());
    		gpztclSjzxInfo.setQlBh(gpztclSjzxInfoDto.getQlBh());
    		gpztclSjzxInfo.setXlBh(gpztclSjzxInfoDto.getXlBh());
    		gpztclSjzxInfo.setSxh(i);
    		addlist.add(gpztclSjzxInfo);
    		i++;
		}
    	  if(addlist.size()>0)gpztclSjzxInfoMapper.insertBatch(addlist);
    	return errorMessage;
    }

	   @Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  gpztclSjzxInfoMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(GpztclSjzxInfoDto gpztclSjzxInfoDto,User user) throws Exception {
    	  String errorMessage = "";
        GpztclSjzxInfo gpztclSjzxInfo = this.gpztclSjzxInfoMapper.selectById(gpztclSjzxInfoDto.getId());
        errorMessage = BaseService.operatorMessage(gpztclSjzxInfo, gpztclSjzxInfoDto, user);
		  gpztclSjzxInfoMapper.updateById(gpztclSjzxInfo);
    	  return errorMessage;
    }

	   @Override
	   public GpztclSjzxInfo findById(String id) throws Exception {
		   return gpztclSjzxInfoMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(GpztclSjzxInfoDto gpztclSjzxInfoDto, String[] clumNames) throws Exception {
		   String id = gpztclSjzxInfoDto.getId();
		   Map map = BeanUtils.toMap(gpztclSjzxInfoDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<GpztclSjzxInfo> lists = gpztclSjzxInfoMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (GpztclSjzxInfo gpztclSjzxInfo2 : lists) {
				   String newId = gpztclSjzxInfo2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

	@Override
	public List<GpztclSjzxInfoDto> readXlsxIs(MultipartFile file) throws IOException {
		List<GpztclSjzxInfoDto> dtos = new ArrayList<>();
				XSSFWorkbook xssfWorkbook = new XSSFWorkbook(file.getInputStream());
				// 循环工作表Sheet
				for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
					XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
					if (xssfSheet == null) {
						continue;
					}
					// 循环行Row
					int i =1;
					for (int rowNum = 2; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
						XSSFRow xssfRow = xssfSheet.getRow(rowNum);
						if (xssfRow == null)continue;
						GpztclSjzxInfoDto dto = new GpztclSjzxInfoDto();
						XSSFCell xssfCell = null;
						xssfCell = xssfRow.getCell(0);
						if(xssfCell!=null)dto.setLc(ParseExcelFile.getValue(xssfCell));
						xssfCell = xssfRow.getCell(1);
						if(xssfCell!=null)dto.setSjzbX(ParseExcelFile.getValue(xssfCell));
						xssfCell = xssfRow.getCell(2);
						if(xssfCell!=null)dto.setSjzbY(ParseExcelFile.getValue(xssfCell));
						xssfCell = xssfRow.getCell(3);
						if(xssfCell!=null)dto.setSjzbH(ParseExcelFile.getValue(xssfCell));
						xssfCell = xssfRow.getCell(4);
						if(xssfCell!=null)dto.setRemark(ParseExcelFile.getValue(xssfCell));
						dto.setSxh(i);
						dtos.add(dto);
						i++;
					}
				}
		return dtos;
	}

	@Override
	public List<GpztclSjzxInfo> selectGpztclSjzxInfoList(String xlbh) throws Exception {
		return gpztclSjzxInfoMapper.selectGpztclSjzxInfoListByXlbh(xlbh);
	}

}