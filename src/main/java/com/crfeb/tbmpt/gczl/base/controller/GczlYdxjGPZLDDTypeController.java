package com.crfeb.tbmpt.gczl.base.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crfeb.tbmpt.commons.base.BaseController;
import com.crfeb.tbmpt.commons.result.Tree;
import com.crfeb.tbmpt.commons.utils.ExportDto;
import com.crfeb.tbmpt.commons.utils.ExportExcelUtil;
import com.crfeb.tbmpt.commons.utils.ExportPdfUtil;
import com.crfeb.tbmpt.commons.utils.ExportSetUtil;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.gczl.base.model.GczlYdxjGPZLDDType;
import com.crfeb.tbmpt.gczl.base.service.GczlYdxjGPZLDDTypeService;

/**
 * @description：盾构施工质量基础数据分类
 * @author wangbin
 *
 */
@Controller
@RequestMapping("/gczl/gpzlType")
public class GczlYdxjGPZLDDTypeController extends BaseController {

    @Autowired
    private GczlYdxjGPZLDDTypeService gczlYdxjGPZLDDTypeService;

    /**
     * 盾构施工质量列表页
     *
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String manager() {
        return "gczl/base/gczlYdxjGPZLDDType";
    }

    /**
     * 盾构施工质量分类列表
     *
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(GczlYdxjGPZLDDType dto,Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        condition.put("typeName", dto.getTypeName());
        pageInfo.setCondition(condition);
       
        gczlYdxjGPZLDDTypeService.selectDataGrid(pageInfo);
        return pageInfo;
    }
    
    /**
     * 获取盾构施工质量列表数据（可用于公共下拉列表调用）
     * @return 返回list列表数据
     */
    @RequestMapping(value = "/gpDdDic", method = RequestMethod.POST)
    @ResponseBody
    public Object findGpzlDdTypdDic() {
    	 List<Tree> trees = new ArrayList<Tree>();
    	 List<GczlYdxjGPZLDDType> types = gczlYdxjGPZLDDTypeService.getAll();
    	 if(types!=null&&types.size()>0)
    	 for (GczlYdxjGPZLDDType type : types) {
    		 Tree tree = new Tree();
    		 tree.setId(type.getTypeName());
    		 tree.setText(type.getTypeName());
    		 trees.add(tree);
		}
    	return trees;
    }



    /**
     * 数据导出
     * @param exportDto
     * @param Dto
     * @param response
     */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@RequestMapping(value="/expXls")
	public void expXls(ExportDto exportDto,GczlYdxjGPZLDDType dto, HttpServletResponse response){
		try {
			if(StringUtils.isNotBlank(exportDto.getIds())){
				List<String> idsList =new ArrayList<String>();
				Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
				List list = gczlYdxjGPZLDDTypeService.selectBatchIds(idsList);
				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
			}else{
		        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
		        Map<String, Object> condition = new HashMap<String, Object>();
		        pageInfo.setCondition(condition);
		        if (StringUtils.isNotBlank(dto.getTypeName())) {
		            condition.put("typeName", dto.getTypeName());
		        }
		        if (StringUtils.isNotBlank(dto.getIfQy())) {
		        	condition.put("ifQy", dto.getIfQy());
		        }
		        if (StringUtils.isNotBlank(dto.getRemarks())) {
		        	condition.put("remarks", dto.getRemarks());
		        }
		        if (dto.getSortNum() >0) {
		            condition.put("sortNum", dto.getSortNum());
		        }
		        gczlYdxjGPZLDDTypeService.selectDataGrid(pageInfo);
				ExportExcelUtil.writeExcelToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    /**
     * 数据导出
     * @param exportDto
     * @param Dto
     * @param response
     */
	@SuppressWarnings({"unchecked", "rawtypes"})
	@RequestMapping(value="/expPdf")
	public void expPdf(ExportDto exportDto,GczlYdxjGPZLDDType dto, HttpServletResponse response){
		try {
			if(StringUtils.isNotBlank(exportDto.getIds())){
				List<String> idsList =new ArrayList<String>();
				 Collections.addAll(idsList, exportDto.getIds().split(ExportDto.SPLIT_STR));
				 List list = gczlYdxjGPZLDDTypeService.selectBatchIds(idsList);
				 ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(list));
			}else{
		        PageInfo pageInfo = new PageInfo(0, 65530, "id", "desc");
		        Map<String, Object> condition = new HashMap<String, Object>();
		        pageInfo.setCondition(condition);
		        if (StringUtils.isNotBlank(dto.getTypeName())) {
		            condition.put("typeName", dto.getTypeName());
		        }
		        if (StringUtils.isNotBlank(dto.getIfQy())) {
		        	condition.put("ifQy", dto.getIfQy());
		        }
		        if (StringUtils.isNotBlank(dto.getRemarks())) {
		        	condition.put("remarks", dto.getRemarks());
		        }
		        if (dto.getSortNum() >0) {
		            condition.put("sortNum", dto.getSortNum());
		        }
		        gczlYdxjGPZLDDTypeService.selectDataGrid(pageInfo);
				ExportPdfUtil.writePdfToClient(response, exportDto, ExportSetUtil.objectToListMap(pageInfo.getRows()));
			}
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}

}
