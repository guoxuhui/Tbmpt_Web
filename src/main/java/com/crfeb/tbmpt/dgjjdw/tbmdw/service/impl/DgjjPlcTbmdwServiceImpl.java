package com.crfeb.tbmpt.dgjjdw.tbmdw.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crfeb.tbmpt.commons.utils.PageInfo;
import com.crfeb.tbmpt.dgjjdw.bzdw.mapper.DgjjdwPlcBzdwMapper;
import com.crfeb.tbmpt.dgjjdw.bzdw.model.dto.DgjjdwPlcBzdwDto;
import com.crfeb.tbmpt.dgjjdw.real.mapper.DgjjdwPlcRealMapper;
import com.crfeb.tbmpt.dgjjdw.real.model.dto.DgjjdwPlcRealDto;
import com.crfeb.tbmpt.dgjjdw.tbmdw.mapper.DgjjPlcTbmdwMapper;
import com.crfeb.tbmpt.dgjjdw.tbmdw.model.DgjjPlcTbmdw;
import com.crfeb.tbmpt.dgjjdw.tbmdw.model.dto.DgjjPlcTbmdwDto;
import com.crfeb.tbmpt.dgjjdw.tbmdw.service.IDgjjPlcTbmdwService;
import com.crfeb.tbmpt.tbmmg.mapper.ProTbminfoMapper;
import com.crfeb.tbmpt.tbmmg.model.ProTbminfo;
import com.crfeb.tbmpt.zsjk.jt.model.ZsJkJtAqZlXx;
import com.baomidou.framework.service.impl.CommonServiceImpl;
import com.baomidou.framework.service.impl.SuperServiceImpl;
import com.baomidou.mybatisplus.plugins.Page;

/**
 *
 * ProPlcTbmdw 表数据服务层接口实现类
 *
 */
@Service
public class DgjjPlcTbmdwServiceImpl extends CommonServiceImpl<DgjjPlcTbmdwMapper, DgjjPlcTbmdw> implements IDgjjPlcTbmdwService {
	@Autowired
	private DgjjPlcTbmdwMapper dgjjPlcTbmdwMapper;
	@Autowired
	private ProTbminfoMapper proTbminfoMapper;
	@Autowired
	private DgjjdwPlcRealMapper dgjjdwPlcRealMapper;
	@Autowired
	private DgjjdwPlcBzdwMapper dgjjdwPlcBzdwMapper;
	
	/**
	 * 查询数据表格
	 */
	@Override
	public void selectDataGrid(PageInfo pageInfo) {
		Page<DgjjPlcTbmdw> page = new Page<DgjjPlcTbmdw>(pageInfo.getNowpage(), pageInfo.getSize());
	       Map<String, Object> condition = pageInfo.getCondition();
	       List<DgjjPlcTbmdw> list = dgjjPlcTbmdwMapper.selectDataGridList(page,condition);
	       pageInfo.setRows(list);
	       pageInfo.setTotal(page.getTotal());
		
	}
	/**
	 * 批量对比信息数据表格
	 */
	@Override
	public List<DgjjPlcTbmdwDto> selecttbmdwDataGrid(DgjjPlcTbmdwDto dto) {
		// TODO Auto-generated method stub
		
		String bztbmname=dto.getBztbmname();
		String tbmcode=dto.getTbmcode();
		//查询工业库点位和标准点位
		List<DgjjdwPlcRealDto> sslist=dgjjdwPlcRealMapper.selectTbmCode(tbmcode);
		List<DgjjdwPlcBzdwDto> bzlist=dgjjdwPlcBzdwMapper.listAllBzdw();
		//根据盾构机名称查询当前盾构机
		ProTbminfo proTbminfo=proTbminfoMapper.selectByName(bztbmname);
		List<DgjjPlcTbmdwDto> list=duibi(sslist,bzlist,proTbminfo);
		return list;
	}
	

	/**
	 * 循环对比，选出相似度大于3的对象放在数据表格中
	 * @param sslist
	 * @param bzlist
	 * @param proTbminfo
	 * @return
	 */
	public List<DgjjPlcTbmdwDto> duibi(List<DgjjdwPlcRealDto>sslist,List<DgjjdwPlcBzdwDto> bzlist,ProTbminfo proTbminfo){
		//相似度最高的对比信息集合
		List<DgjjPlcTbmdwDto> tbmdwlist=new ArrayList<DgjjPlcTbmdwDto>();
		for(DgjjdwPlcBzdwDto bzdto:bzlist){
			DgjjPlcTbmdwDto tbmdwdto=new DgjjPlcTbmdwDto();
			//获取标准点位名称
			String bzDwName=bzdto.getName();
			//存放所有的实时点位对象
			List<DgjjdwPlcRealDto> list=new ArrayList<DgjjdwPlcRealDto>();
			for(DgjjdwPlcRealDto ssdto:sslist){
				int xiangsi=0;
				//获取实时点位名称
				String ssDwName=ssdto.getTagname();
//				ssDwName=ssDwName.substring(ssdto.getTbmcode().length()+1,ssDwName.length());
				if(ssDwName.contains("_")){
					String[] str=ssDwName.split("_");
					if(str.length>2){
						ssDwName=str[str.length-1];
					}else{
						ssDwName=str[1];
					}
				}
				if(ssDwName.contains("/")){
					String[] str=ssDwName.split("/");
					if(str.length>2){
						ssDwName=str[str.length-1];
					}else{
						ssDwName=str[1];
					}
				}
				char [ ] bzchArr=bzDwName.toCharArray();
				char [ ] sschArr=ssDwName.toCharArray();
				for(int i=0;i<bzchArr.length;i++){
					//取标准点位一个字符与实时点位所有字符对比，相同则xiangsi+1
					for(int j=0;j<sschArr.length;j++){
						if(bzchArr[i]==sschArr[j]){
							xiangsi++;
						}
					}
				}
				ssdto.setXiangsi(xiangsi);
				list.add(ssdto);
			}
			//取相似度最高的实时对象
			DgjjdwPlcRealDto max=maxXingsi(list);
			tbmdwdto.setDwid(bzdto.getId());
			tbmdwdto.setDwname(bzdto.getName());
			tbmdwdto.setDwlx(bzdto.getDwlx());
			tbmdwdto.setTbmid(proTbminfo.getId());
			tbmdwdto.setTbmcode(max.getTbmcode());
			if(max.getXiangsi()>=3){
			tbmdwdto.setTagname(max.getTagname());
			}
			tbmdwlist.add(tbmdwdto);
			
		}
		return tbmdwlist;
	}
	/**
	 * 获取相似度最大对象
	 * @param list
	 * @return
	 */
	public DgjjdwPlcRealDto maxXingsi(List<DgjjdwPlcRealDto> list){
		List<Integer> i=new ArrayList<Integer>();
		DgjjdwPlcRealDto max=list.get(0);
		for(DgjjdwPlcRealDto dto:list){
			if(dto.getXiangsi()>max.getXiangsi()){
				max=dto;
			}
		}
		return max;
	}
	/**
	 * 根据工业库定位名称，盾构机CODE查询实时点位信息
	 */
	public List<DgjjdwPlcRealDto>getTagName(String tbmName,String tbmcode){
		List<DgjjdwPlcRealDto> list=dgjjdwPlcRealMapper.selectTbmName(tbmName,tbmcode);
		return list;
	}
	/**
	 * 根据盾构机ID查询对比信息
	 */
	@Override
	public List<DgjjPlcTbmdwDto> selectByTbmid(String tbmid) {
//		ProTbminfo proTbminfo=proTbminfoMapper.selectByName(tbmid);
		List<DgjjPlcTbmdwDto>list=dgjjPlcTbmdwMapper.selectTbmid(tbmid);
		return list;
	}
	/**
	 * 根据盾构机CODE查询实时点位信息
	 */
	@Override
	public List<DgjjdwPlcRealDto> getTagCode(String tbmcode) {
		List<DgjjdwPlcRealDto> sslist=dgjjdwPlcRealMapper.selectTbmCode(tbmcode);
		return sslist;
	}
}