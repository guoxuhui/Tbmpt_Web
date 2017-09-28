package com.crfeb.tbmpt.dgjj.service.impl;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.dgjj.mapper.DgjjBzglMapper;
import com.crfeb.tbmpt.dgjj.mapper.DgjjRjjInfoMapper;
import com.crfeb.tbmpt.dgjj.model.DgjjBzgl;
import com.crfeb.tbmpt.dgjj.model.DgjjRjjInfo;
import com.crfeb.tbmpt.dgjj.model.dto.DgjjRjjInfoDto;
import com.crfeb.tbmpt.dgjj.model.dto.DgjjRjjInfoParentDto;
import com.crfeb.tbmpt.dgjj.service.DgjjRjjInfoService;
import com.crfeb.tbmpt.dmcjjc.info.model.JcPoint;
import com.crfeb.tbmpt.dmcjjc.info.model.vo.DmcjJcPointDto;
import com.crfeb.tbmpt.sys.mapper.OrgzMapper;
import com.crfeb.tbmpt.sys.model.Orgz;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.commons.base.BaseService;
import com.crfeb.tbmpt.gpztcl.base.model.dto.GpztclSjzxInfoDto;
import com.crfeb.tbmpt.gpztcl.base.mapper.GpztclSjzxInfoMapper;
import com.crfeb.tbmpt.gpztcl.base.model.GpztclSjzxInfo;
import com.crfeb.tbmpt.project.mapper.ProRSectionLineMapper;
import com.crfeb.tbmpt.project.model.vo.SectionLineVo;


/**
 * <p>日掘进信息管理Service实现类</p>
 * <p>系统：盾构掘进参数管理系统</p>
 * <p>模块：基础模块</p>
 * <p>日期：2017-01-09</p>
 * @version 1.0
 * @author wpg
 */
@Service
public class DgjjRjjInfoServiceImpl extends CommonServiceImpl<DgjjRjjInfoMapper, DgjjRjjInfo> implements DgjjRjjInfoService{

	//日掘进 Mapper 层  对象
	@Autowired
    private DgjjRjjInfoMapper dgjjRjjInfoMapper; 
	
	//班组 Mapper 层  对象
	@Autowired
	DgjjBzglMapper dgjjBzglMapper;
  
    @Autowired
    private ProRSectionLineMapper proRSectionLineMapper;

    @Autowired
    private OrgzMapper orgzMapper;
    
    /**
     * 获取 项目、区间、线路 信息列表
     * @author wpg
     * @param pageInfo
     * @throws Exception
     */
	@Override
	public void selectDataGridParent(PageInfo pageInfo,User user) throws Exception {
		Orgz orgz = orgzMapper.selectById(user.getOrgzId());
    	String code = "-1";
    	if(orgz != null){
    		if(orgz.getType() <= 1){
    			code = orgz.getCode();
    		}else{
    			Orgz orgz2 = orgzMapper.selectById(orgz.getPid());
    			code = orgz2.getCode();
    		}
    	}
    	pageInfo.getCondition().put("code", code);
		  Page<DgjjRjjInfoParentDto> page = new Page<DgjjRjjInfoParentDto>(pageInfo.getNowpage(), pageInfo.getSize());
	       List<DgjjRjjInfoParentDto> dtos = new ArrayList<>();
	       List<SectionLineVo> list = proRSectionLineMapper.selectVoList(page,pageInfo.getCondition(), pageInfo.getSort(), pageInfo.getOrder());
	       for (SectionLineVo sectionLineVo : list) {
	    	   DgjjRjjInfoParentDto dgjjRjjInfoParentDto = new DgjjRjjInfoParentDto();
	    	   BeanUtils.copyProperties(sectionLineVo, dgjjRjjInfoParentDto);
	    	   dtos.add(dgjjRjjInfoParentDto);
	       }
	       pageInfo.setRows(dtos);
	       pageInfo.setTotal(page.getTotal());
	}

	/***
	 * 作用:根据 线路Id：xlbh，查询 班组集合；
	 * 返回班组集合：List<DgjjBzgl>；
	 * @param xlBh 线路Id
	 */
	@Override
	public List<DgjjBzgl> selecDgjjBzgltListByXlbh(String xlBh) throws Exception {
		return dgjjBzglMapper.selectDgjjBzglListByXlbh(xlBh);
		
	}

	
	/**
     *  解析 Excle 文件 日掘进信息   
     *  返回  日掘进 列表类集合; 
     */
	@SuppressWarnings("null")
	@Override
	public List<DgjjRjjInfo> readXlsxIs(MultipartFile file ) throws IOException {
		//声明  List<GpztclSjzxInfoDto> dtos 集合

		List<DgjjRjjInfo> dtos = new ArrayList<>();
		
	    Workbook wb = null;
	        
	        try {
	        	//获取 文件  扩展名
	            String fileName = file.getOriginalFilename();
	    	    String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
	    	    if (fileType.equals("xls")) {
	 	        	//处理  .xls 文件
	 	            wb = new HSSFWorkbook(file.getInputStream());
	 	        } else if (fileType.equals("xlsx")) {
	 	        	//处理  .xlsx 文件 
	 	            wb = new XSSFWorkbook(file.getInputStream());
	 	        } else {
	 	            throw new Exception("读取的不是excel文件");
	 	        }

 				// 循环工作表Sheet  
 				for (int numSheet = 0; numSheet < wb.getNumberOfSheets(); numSheet++) {
 					//获取 一页  数据
 		            Sheet sheet = wb.getSheetAt(numSheet);
 					if (sheet == null) {
 						continue;
 					}
 					// 根据行码(从0开始) 循环行Row
 					for (int rowNum = 3; rowNum <= sheet.getLastRowNum(); rowNum++) {
 						//获取 行数据
 						Row row = sheet.getRow(rowNum);
 						if (row == null)continue;
 					    // row 长度 （列）
 		                int cellSize = row.getLastCellNum();//行中有多少个单元格，也就是有多少列
 						//获取   列  属性数据 给dto赋值 
 		                Cell  cell = null;
 						
						//声明一个 新的 实体对象；
 						DgjjRjjInfo dto = new DgjjRjjInfo();
                        cell = row.getCell(0);
                        if(cell!=null && !cell.toString().equals("")){
                        	dto.setSghh((new Double(cell.toString()).intValue()));
                        }else{
                        	//施工环号 为空 说明  数据 没有下一行，结束 循环；
                        	break;
                        }
 						cell = row.getCell(1);
 						if(cell!=null && !cell.toString().equals(""))dto.setGpbhgjlc(Double.parseDouble(cell.toString()));
 						cell = row.getCell(2);
 						if(cell!=null && !cell.toString().equals(""))dto.setSjlx(cell.toString());
 						cell = row.getCell(3);
 						if(cell!=null && !cell.toString().equals(""))dto.setSjzx(cell.toString());
 						cell = row.getCell(4);
 						if(cell!=null && !cell.toString().equals(""))dto.setSglx(cell.toString());
 						cell = row.getCell(5);
 						if(cell!=null && !cell.toString().equals(""))dto.setFdkwz((new Double(cell.toString()).intValue()));
 						cell = row.getCell(6);
 						if(cell!=null && !cell.toString().equals(""))dto.setZjyl(Double.parseDouble(cell.toString()));
 						cell = row.getCell(7);
 						if(cell!=null && !cell.toString().equals(""))dto.setTbzjl((new Double(cell.toString()).intValue()));
 						cell = row.getCell(8);
 						if(cell!=null && !cell.toString().equals(""))dto.setTbzjwz(cell.toString());
 						cell = row.getCell(9);
 						if(cell!=null && !cell.toString().equals(""))dto.setGpzjwz(cell.toString());
 						cell = row.getCell(10);
 						if(cell!=null && !cell.toString().equals(""))dto.setHxzjsj(cell.toString());
 						cell = row.getCell(11);
 						if(cell!=null && !cell.toString().equals(""))dto.setTy(Double.parseDouble(cell.toString()));
 						cell = row.getCell(12);
 						if(cell!=null && !cell.toString().equals(""))dto.setCtl(cell.toString());
 						cell = row.getCell(13);
 						if(cell!=null && !cell.toString().equals(""))dto.setPjztl(Double.parseDouble(cell.toString()));
 						cell = row.getCell(14);
 						if(cell!=null && !cell.toString().equals(""))dto.setPjnj(Double.parseDouble(cell.toString()));
 						cell = row.getCell(15);
 						if(cell!=null && !cell.toString().equals(""))dto.setSdzxgc(Double.parseDouble(cell.toString()));
 						cell = row.getCell(16);
 						if(cell!=null && !cell.toString().equals(""))dto.setSdzxpm(Double.parseDouble(cell.toString()));
 						cell = row.getCell(17);
 						if(cell!=null && !cell.toString().equals(""))dto.setGpazqA((new Double(cell.toString()).intValue()));
 						cell = row.getCell(18);
 						if(cell!=null && !cell.toString().equals(""))dto.setGpazqB((new Double(cell.toString()).intValue()));
 						cell = row.getCell(19);
 						if(cell!=null && !cell.toString().equals(""))dto.setGpazqC((new Double(cell.toString()).intValue()));
 						cell = row.getCell(20);
 						if(cell!=null && !cell.toString().equals(""))dto.setGpazqD((new Double(cell.toString()).intValue()));
 						cell = row.getCell(21);
 						if(cell!=null && !cell.toString().equals(""))dto.setGpazhA((new Double(cell.toString()).intValue()));
 						cell = row.getCell(21);
 						if(cell!=null && !cell.toString().equals(""))dto.setGpazhB((new Double(cell.toString()).intValue()));
 						cell = row.getCell(22);
 						if(cell!=null && !cell.toString().equals(""))dto.setGpazhC((new Double(cell.toString()).intValue()));
 						cell = row.getCell(23);
 						if(cell!=null && !cell.toString().equals(""))dto.setGpazhC((new Double(cell.toString()).intValue()));
 						cell = row.getCell(24);
 						if(cell!=null && !cell.toString().equals(""))dto.setGpazhD((new Double(cell.toString()).intValue()));
 						cell = row.getCell(25);
 						if(cell!=null && !cell.toString().equals(""))dto.setDgjzxspqk((new Double(cell.toString()).intValue()));
 						cell = row.getCell(26);
 						if(cell!=null && !cell.toString().equals(""))dto.setDgjzxspdw((new Double(cell.toString()).intValue()));
 						cell = row.getCell(27);
 						if(cell!=null && !cell.toString().equals(""))dto.setDgjzxczqk((new Double(cell.toString()).intValue()));
 						cell = row.getCell(28);
 						if(cell!=null && !cell.toString().equals(""))dto.setDgjzxczdw((new Double(cell.toString()).intValue()));
 						cell = row.getCell(29);
 						if(cell!=null && !cell.toString().equals(""))dto.setDwjxpzqs((new Double(cell.toString()).intValue()));
 						cell = row.getCell(30);
 						if(cell!=null && !cell.toString().equals(""))dto.setDwjxpzqx((new Double(cell.toString()).intValue()));
 						cell = row.getCell(31);
 						if(cell!=null && !cell.toString().equals(""))dto.setDwjxpzqz((new Double(cell.toString()).intValue()));
 						cell = row.getCell(32);
 						if(cell!=null && !cell.toString().equals(""))dto.setDwjxpzqy((new Double(cell.toString()).intValue()));
 						cell = row.getCell(33);
 						if(cell!=null && !cell.toString().equals(""))dto.setDwjxpzhs((new Double(cell.toString()).intValue()));
 						cell = row.getCell(34);
 						if(cell!=null && !cell.toString().equals(""))dto.setDwjxpzhx((new Double(cell.toString()).intValue()));
 						cell = row.getCell(35);
 						if(cell!=null && !cell.toString().equals(""))dto.setDwjxpzhz((new Double(cell.toString()).intValue()));
 						cell = row.getCell(36);
 						if(cell!=null && !cell.toString().equals(""))dto.setDwjxpzhy((new Double(cell.toString()).intValue()));
 						cell = row.getCell(37);
 						if(cell!=null && !cell.toString().equals(""))dto.setGpztpzqgc((new Double(cell.toString()).intValue()));
 						cell = row.getCell(38);
 						if(cell!=null && !cell.toString().equals(""))dto.setGpztpzqpm((new Double(cell.toString()).intValue()));
 						cell = row.getCell(39);
 						if(cell!=null && !cell.toString().equals(""))dto.setGpztpzhgc((new Double(cell.toString()).intValue()));
 						cell = row.getCell(40);
 						if(cell!=null && !cell.toString().equals(""))dto.setGpztpzhpm((new Double(cell.toString()).intValue()));
 						cell = row.getCell(41);
 						if(cell!=null && !cell.toString().equals(""))dto.setDgjjwcqzsj(cell.toString());
 						cell = row.getCell(42);
 						if(cell!=null && !cell.toString().equals(""))dto.setSgrq(cell.toString());
 						cell = row.getCell(43);
 						if(cell!=null && !cell.toString().equals(""))dto.setRemarks(cell.toString());
 						dtos.add(dto); 
	                   
 					}
 				} 
			} catch (Exception e) {
				e.printStackTrace();
			} 
	       
		return dtos;
	}


	 /**
     * 根据线路编号 分页  查询 日掘进信息  
     * @param pageInfo 分页工具类
     * @throws Exception
     */
	@Override
	public void selectDgjjRjjInfoListByXlBh(PageInfo pageInfo) throws Exception {
		Page<DgjjRjjInfoDto> page = new Page<DgjjRjjInfoDto>(pageInfo.getNowpage(), pageInfo.getSize());
	       
	       List<DgjjRjjInfoDto> list = dgjjRjjInfoMapper.selectDgjjRjjInfoList(page,pageInfo.getCondition(), pageInfo.getSort(), pageInfo.getOrder());
	      
	       pageInfo.setRows(list);
	       pageInfo.setTotal(page.getTotal());
		
	}

	/***
     * 作用：编辑日掘进信息，查询 单条班组、日掘进信息
     * @param id 日掘进Id
     * @return
     * @throws Exception
     */
	@Override
	public DgjjRjjInfoDto selectDgjjRjj(String id) {
		try {
			return dgjjRjjInfoMapper.selectDgjjRjjById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	
	/***
     * Excel 数据导出  的时候 用到  
     * @param pageInfo 分页工具类
     */
    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
    	Page<JcPoint> page = new Page<JcPoint>(pageInfo.getNowpage(), pageInfo.getSize());
        List<DgjjRjjInfoDto> list = dgjjRjjInfoMapper.selectDgjjRjjPage(page, pageInfo.getCondition());
        pageInfo.setRows(list);
        pageInfo.setTotal(page.getTotal());
    }




}