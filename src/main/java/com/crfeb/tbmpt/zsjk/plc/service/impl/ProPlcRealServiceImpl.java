package com.crfeb.tbmpt.zsjk.plc.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crfeb.tbmpt.commons.utils.BeanUtils;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.commons.utils.StringUtils;
import com.crfeb.tbmpt.sys.model.User;
import com.crfeb.tbmpt.zsjk.plc.mapper.ProPlcRealMapper;
import com.crfeb.tbmpt.zsjk.plc.model.ProPlcReal;
import com.crfeb.tbmpt.zsjk.plc.model.dto.ProPlcBzRealDto;
import com.crfeb.tbmpt.zsjk.plc.model.dto.ProPlcRealDto;
import com.crfeb.tbmpt.zsjk.plc.model.dto.ProSecLinePlcDto;
import com.crfeb.tbmpt.zsjk.plc.service.IProPlcRealService;
import com.crfeb.tbmpt.zsjk.xmb.model.dto.ZsJkXmbClYdXhXxDto;
import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;

/**
 *
 * ProPlcReal 表数据服务层接口实现类
 *
 */
@Service
public class ProPlcRealServiceImpl extends CommonServiceImpl<ProPlcRealMapper, ProPlcReal> implements IProPlcRealService {

	@Autowired
    private ProPlcRealMapper proPlcRealMapper;
	@Autowired
	private DruidDataSource dataSource;
	/**
	 * 判断数据库操作是否成功
	 *
	 * @param result
	 *            数据库操作返回影响条数
	 * @return boolean
	 */
	protected boolean retBool(int result) {
		return result >= 1;
	}
	
    @Override
    public void selectDataGrid(PageInfo pageInfo) throws Exception {
       Page<ProPlcReal> page = new Page<ProPlcReal>(pageInfo.getNowpage(), pageInfo.getSize());
       Map<String, Object> condition = pageInfo.getCondition();
       List<ProPlcReal> list = proPlcRealMapper.selectProPlcRealXxList(page,condition);
       pageInfo.setRows(list);
       pageInfo.setTotal(page.getTotal());
    }

    @Override
    public String save(ProPlcRealDto proPlcRealDto,User user) throws Exception {
    	  String errorMessage = "";
        ProPlcReal proPlcReal = new ProPlcReal();
        BeanUtils.copyProperties(proPlcRealDto, proPlcReal);
    	  proPlcRealMapper.insert(proPlcReal);
    	  return errorMessage;
    }
    
    @Override
	public String insertOrUpdateBatch(List<ProPlcReal> list) throws Exception {
		String errorMessage = "";
		System.out.println(DateUtils.getToday() + " insertOrUpdateBatch start");
		long openLong = System.currentTimeMillis();
		
		PreparedStatement pstm = null; 
		ResultSet rst = null;
		Connection con = dataSource.getConnection();
		try {
			if(list != null && list.size()>0) {
				
				String retSql = "select id from PRO_PLC_REAL where id = ? ";
				String updateSql = "update PRO_PLC_REAL set tagvalue = ?,tagtime = ? where id = ? ";
				String insertSql = "insert into PRO_PLC_REAL(id,tbmcode,tagname,tagtype,tagvalue,tagtime) values(?,?,?,?,?,?) ";
				String sql = "declare num number; begin select count(1) into num from PRO_PLC_REAL where id = ?; if num > 0 then update PRO_PLC_REAL set tagvalue = ?,tagtime = ? where id = ?; else insert into PRO_PLC_REAL(id,tbmcode,tagname,tagtype,tagvalue,tagtime) values(?,?,?,?,?,?); end if; end; ";
				con.setAutoCommit(false);
				pstm = con.prepareStatement(sql);
				for(int i=0;i<list.size();i++){
					ProPlcReal plc = list.get(i);
					/*
					//第一步 检查是否为空
					pstm = con.prepareStatement(retSql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
					pstm.setString(1,plc.getId());
					rst = pstm.executeQuery();
					if(rst.first()) {
						rst.close();
						pstm.close();
						//第二步 存在进行更新
						
						
						pstm.setString(1,plc.getTagvalue());
						pstm.setString(2,plc.getTagtime());
						pstm.setString(3,plc.getId());
						pstm.execute();
						*/
					/*
					}else {
						//第三步 不存在进行写入
						pstm.close();
						pstm = con.prepareStatement(insertSql);
						pstm.setString(1,plc.getId());
						pstm.setString(2,plc.getTbmcode());
						pstm.setString(3,plc.getId());
						pstm.setString(4,plc.getTagtype());
						pstm.setString(5,plc.getTagvalue());
						pstm.setString(6,plc.getTagtime());
						pstm.execute();
						pstm.close();
					}*/
					pstm.setString(1,plc.getId());
					pstm.setString(2,plc.getTagvalue());
					pstm.setString(3,plc.getTagtime());
					pstm.setString(4,plc.getId());
					pstm.setString(5,plc.getId());
					pstm.setString(6,plc.getTbmcode());
					pstm.setString(7,plc.getId());
					pstm.setString(8,plc.getTagtype());
					pstm.setString(9,plc.getTagvalue());
					pstm.setString(10,plc.getTagtime());
					pstm.execute();
					if (i >= 1 && i % 500 == 0) {
	                    con.commit();
	                }
				}
				con.commit();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm != null ){
					pstm.close();
					pstm = null;
				}
			} catch (SQLException e) {}
			try {
				if(con != null && !con.isClosed()){
					con.close();
					con = null;
				}
			} catch (SQLException e) {}
		}
		System.out.println(DateUtils.getToday() + " insertOrUpdateBatch finsh,Processed "+(System.currentTimeMillis() -openLong)/1000+"S !");
		return errorMessage;
	}

	@Override
    public String del(String[] ids,User user) throws Exception {
    	  String errorMessage = "";
		  List<String> idList = Arrays.asList(ids);
		  proPlcRealMapper.deleteBatchIds(idList);
    	  return errorMessage;
    }

    @Override
    public String update(ProPlcRealDto proPlcRealDto,User user) throws Exception {
    	  String errorMessage = "";
        ProPlcReal proPlcReal = this.proPlcRealMapper.selectById(proPlcRealDto.getId());
		  proPlcRealMapper.updateById(proPlcReal);
    	  return errorMessage;
    }

	   @Override
	   public ProPlcReal findById(String id) throws Exception {
		   return proPlcRealMapper.selectById(id);
	   }

    @Override
    public String checkClumIfexits(ProPlcRealDto proPlcRealDto, String[] clumNames) throws Exception {
		   String id = proPlcRealDto.getId();
		   Map map = BeanUtils.toMap(proPlcRealDto);
		   Map<String,Object> columnMap = new HashMap<String,Object>();
		   for (String clum : clumNames) {
		        Object ss =  map.get(clum);
		        columnMap.put(clum, ss);
		   }
		   List<ProPlcReal> lists = proPlcRealMapper.selectByMap(columnMap);
		   if(StringUtils.isBlank(id)){//新增
			    if(lists!=null&&lists.size()>0)return "当前数据已存在!";
		   }else{//修改
			    for (ProPlcReal proPlcReal2 : lists) {
				   String newId = proPlcReal2.getId();
				   if(!newId.equals(id)){
					   return "当前数据已存在!";
				   }
			    }
		   }
		   return null;
    }

	@Override
	public List<ProPlcBzRealDto> getSsJqsj(String tbmId) throws Exception {
		List<ProPlcBzRealDto>  dtoList = new ArrayList<ProPlcBzRealDto>();
		if(StringUtils.isNotBlank(tbmId)){
			Map<String,Object> columnMap = new HashMap<String,Object>();
			columnMap.put("tbmId", tbmId);
			dtoList = this.proPlcRealMapper.selectProPlcBzRealXxDtoList(columnMap);
		}
		return dtoList;
	}
	
	@Override
	public List<ProPlcBzRealDto> getSsJqsjByDw(String tbmId,String dwIds) throws Exception {
		List<ProPlcBzRealDto>  dtoList = new ArrayList<ProPlcBzRealDto>();
		if(StringUtils.isNotBlank(tbmId)){
			Map<String,Object> columnMap = new HashMap<String,Object>();
			columnMap.put("tbmId", tbmId);
			StringBuffer sb = new StringBuffer();
			if(dwIds != null){
				String[] dwstrs = dwIds.split(",");
				for(int i=0;i<dwstrs.length;i++){
					sb.append("'"+dwstrs[i]+"'");
					if(i<dwstrs.length-1){
						sb.append(",");
					}
				}
				columnMap.put("dwIds", sb.toString());
			}
			dtoList = this.proPlcRealMapper.selectProPlcBzRealXxDtoList(columnMap);
		}
		return dtoList;
	}
    
	public List<ProSecLinePlcDto> selectProSecLinePlcAll() throws Exception{
		return this.proPlcRealMapper.selectProSecLinePlcAll();
	}
}