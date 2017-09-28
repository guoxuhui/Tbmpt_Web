package com.crfeb.tbmpt.commons.task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.crfeb.kdbdata.KdbClient;
import com.crfeb.kdbdata.model.KdbData;
import com.crfeb.tbmpt.commons.utils.DateUtils;
import com.crfeb.tbmpt.dgjjdw.dwsjzh.model.DgjjdDwsjzh;
import com.crfeb.tbmpt.dgjjdw.dwsjzh.service.DgjjdDwsjzhService;
import com.crfeb.tbmpt.zsjk.plc.model.ProPlcReal;
import com.crfeb.tbmpt.zsjk.plc.service.IProPlcRealService;
import com.ibm.icu.text.DecimalFormat;

/**
 * 数据同步任务，定时同步工业务实时数据到实时表
 * 
 * @since 2017年3月7日
 * @author xhguo
 *
 */
//@Component
public class SyncJqsjTask {

	@Autowired
	private IProPlcRealService proPlcRealService;
	@Autowired
	private DgjjdDwsjzhService dgjjdDwsjzhService;

	@Scheduled(cron = "0/60 * * * * ?")
	public void syncJqsjJob() {
		// 调试注释：正式发布需取消注释  
		System.out.println(DateUtils.getToday() +  " syncJqsjJob run!");
		long openLong = System.currentTimeMillis();
		KdbClient client = KdbClient.getInstatice();
		if (!client.kDBServerIsConnected()) {
			client.kDBServerConnect("10.4.10.212", 6789, "sa", "sa");
		} else {
			Map<Integer, String> map = client.findAllGroupIds();
			//key ：工业库中盾构机分组ID，可根据key查询盾构机分组名称
			List<ProPlcReal> allList = new ArrayList<ProPlcReal>();
			for (Integer key : map.keySet()) {
				
				List<ProPlcReal> list = new ArrayList<ProPlcReal>();
				long startLong = System.currentTimeMillis();
				List<String> tagNames = client.findKDBTagNamesByGroupId(key);// 获取所有点表名称
				Map<String, KdbData> mapKdb = client.queryCurDataInfo(tagNames);// 根据点表名称获取该盾构机该点表的最新值
				// 1、获取盾构机名称
				String dgjName = map.get(key);				
				// 2、用盾构机名称（dgjName）、点位名称（Tagname）获取小松点位表对比表中转换关系：跨度、数据值
				// 调用接口 dgjjdDwsjzhService.getDwsjzhList，获取所有该盾构的点位数据转换关系
				List<DgjjdDwsjzh> list2 = dgjjdDwsjzhService.getDwsjzhList(dgjName, "");
				
				for (String keyKdb : mapKdb.keySet()) {
					KdbData kdb = mapKdb.get(keyKdb);
					ProPlcReal real = new ProPlcReal();
					real.setId(keyKdb);
					real.setTagname(keyKdb);
					real.setTagtime(kdb.getTagTime());
					real.setTagtype(String.valueOf(kdb.getTagType()));
					Object val1 = kdb.getTagValue();// 获取采集值
					Object val2 = null;// 获取采集值
					// 3、判断是否需要数据转换
					if (list2.size() > 0) {
						//System.out.println("需要数据转换："+dgjName);
						try{
							float f=0;
							short s=0;
							boolean ifnull=(val1==null)?true:false;
							DgjjdDwsjzh dgjjdDwsjzh=new DgjjdDwsjzh();
							for (int i = 0; i < list2.size(); i++) {
								//4、获取该点位的数据转换关系
								//根据数据类型判断，获取采集值
								if(list2.get(i).getCalType().equals("float")){
									
									//判断采集值是否是科学计数法
									if(val1.toString().indexOf("E")>0)
										f=0;
									else
										//判断采集值是否是空
										f=Float.parseFloat((val1.toString().equals("")||val1.toString()=="NaN")?"0":
								    	(val1.toString()));
								    if ((list2.get(i).getDwname().equals(keyKdb))
											&&(list2.get(i).getKdStart()<=f)
											&&(list2.get(i).getKdEnd()>=f)) {
										dgjjdDwsjzh = list2.get(i);
										break;
									}
								}
								if(list2.get(i).getCalType().equals("short")){
									//判断采集值是否是科学计数法
									if(val1.toString().indexOf("E")>0)
										s=0;
									else
										//判断采集值是否是空或者是0.0
										s=Short.parseShort((
												val1.toString().equals("")||
												val1.toString().equals("0.0")||
												val1.toString()=="NaN")?"0":
										(val1.toString()));
									if ((list2.get(i).getDwname().equals(keyKdb))
											&&(list2.get(i).getKdStart()<=s)
											&&(list2.get(i).getKdEnd()>=s)) {
										dgjjdDwsjzh = list2.get(i);
										break;
									}
								}
							}
							
							// 5、如果存在数据类型定义
							if (!ifnull&&dgjjdDwsjzh.getCalType()!=null&&dgjjdDwsjzh.getCalType().equals("float"))
							{
								// 6、判断何种计算方式
								if(dgjjdDwsjzh.getKdStart()==0&&dgjjdDwsjzh.getDataStart()==0) {
									// 计算方式A：结果正值，结果值从0开始计算
									// =(data_end-data_start)/(kd_end-kd_start)*输入采集数据
									val2 = (dgjjdDwsjzh.getDataEnd() - dgjjdDwsjzh.getDataStart())
											/ (dgjjdDwsjzh.getKdEnd() - dgjjdDwsjzh.getKdStart()) *f;
								} else if (dgjjdDwsjzh.getKdStart()>0&&dgjjdDwsjzh.getDataStart()==0) {
									// 计算方式B：正值，非0开始计算，需减B列
									// =(输入采集数据-kd_start)/(kd_end-kd_start)*(data_end-data_start)
									val2 = (f - dgjjdDwsjzh.getKdStart())
											/ (dgjjdDwsjzh.getKdEnd() - dgjjdDwsjzh.getKdStart())
											* (dgjjdDwsjzh.getDataEnd() - dgjjdDwsjzh.getDataStart());
								} else if (dgjjdDwsjzh.getKdStart()==0&&dgjjdDwsjzh.getDataStart()<0&&dgjjdDwsjzh.getDataEnd()==0) {
									// 计算方式C：负值，结果值以0结束，需乘G列
									// =(data_end-data_start)/(kd_end-kd_start)*data_zf*输入采集数据
									val2 = (dgjjdDwsjzh.getDataEnd() - dgjjdDwsjzh.getDataStart())
											/ (dgjjdDwsjzh.getKdEnd() - dgjjdDwsjzh.getKdStart()) * dgjjdDwsjzh.getDataZf()
											*f;
								} else if (dgjjdDwsjzh.getKdStart()<0&&dgjjdDwsjzh.getDataStart()<0&&dgjjdDwsjzh.getDataEnd()==0) {
									// 计算方式E：负值,非0开始，结果值以0结束
									// =(data_end-data_start)/(kd_end-kd_start)*输入采集数据
									val2 = (dgjjdDwsjzh.getDataEnd() - dgjjdDwsjzh.getDataStart())
											/ (dgjjdDwsjzh.getKdEnd() - dgjjdDwsjzh.getKdStart()) * f;
								}else {
									// 计算方式D：无需换算
									val2 =f; 
								}
							}else if (!ifnull&&dgjjdDwsjzh.getCalType()!=null&&dgjjdDwsjzh.getCalType().equals("short")){
								// 6、判断何种计算方式
								if(dgjjdDwsjzh.getKdStart()==0&&dgjjdDwsjzh.getDataStart()==0) {
									// 计算方式A：结果正值，结果值从0开始计算
									// =(data_end-data_start)/(kd_end-kd_start)*输入采集数据
									val2 = (dgjjdDwsjzh.getDataEnd() - dgjjdDwsjzh.getDataStart())
											/ (dgjjdDwsjzh.getKdEnd() - dgjjdDwsjzh.getKdStart()) * s;
								} else if (dgjjdDwsjzh.getKdStart()>0&&dgjjdDwsjzh.getDataStart()==0) {
									// 计算方式B：正值，非0开始计算，需减B列
									// =(输入采集数据-kd_start)/(kd_end-kd_start)*(data_end-data_start)
									val2 = (s - dgjjdDwsjzh.getKdStart())
											/ (dgjjdDwsjzh.getKdEnd() - dgjjdDwsjzh.getKdStart())
											* (dgjjdDwsjzh.getDataEnd() - dgjjdDwsjzh.getDataStart());
								} else if (dgjjdDwsjzh.getKdStart()==0&&dgjjdDwsjzh.getDataStart()<0&&dgjjdDwsjzh.getDataEnd()==0) {
									// 计算方式C：负值，结果值以0结束，需乘G列
									// =(data_end-data_start)/(kd_end-kd_start)*data_zf*输入采集数据
									val2 = (dgjjdDwsjzh.getDataEnd() - dgjjdDwsjzh.getDataStart())
											/ (dgjjdDwsjzh.getKdEnd() - dgjjdDwsjzh.getKdStart()) * dgjjdDwsjzh.getDataZf()
											* s;
								} else if (dgjjdDwsjzh.getKdStart()<0&&dgjjdDwsjzh.getDataStart()<0&&dgjjdDwsjzh.getDataEnd()==0) {
									// 计算方式E：负值,非0开始，结果值以0结束
									// =(data_end-data_start)/(kd_end-kd_start)*输入采集数据
									val2 = (dgjjdDwsjzh.getDataEnd() - dgjjdDwsjzh.getDataStart())
											/ (dgjjdDwsjzh.getKdEnd() - dgjjdDwsjzh.getKdStart()) * s;
									
								}else {
									// 计算方式D：无需换算
									val2 =s; 
								}
								if(val2.toString().indexOf(".")!=-1){
									val2=val2.toString().substring(0, val2.toString().indexOf("."));
								}
							}else if(ifnull){
								val2=null;
							}
							//如果计算结果值是-0.0,修改为0
							if(val2.toString().equals("-0.0")){
								val2=0;
							}
						}catch(Exception e){
							//System.out.println(e.getMessage());
							//System.out.println(keyKdb+"："+val1);
						}
					}
					//7、调试数据转换结果，正式发布需注释 
					//if(val2!=null)
						//System.out.println( String.format("%-40s", keyKdb)+"      val1= "+String.format("%-20s", val1)+"       calc:"+String.format("%-20s", String.valueOf((val2!=null)?val2:val1)));
					real.setTagvalue(String.valueOf((val2!=null)?val2:val1));
					real.setTbmcode(map.get(key));
					list.add(real);
				}
				allList.addAll(list);
				System.out.println(map.get(key)+"-获取数据-"+list.size()+"条-耗时："+(System.currentTimeMillis()- startLong)+"毫秒");
			}
			try {
				long insertLong = System.currentTimeMillis();
				proPlcRealService.insertOrUpdateBatch(allList);
				// 调试执行状态与耗时：正式发布需注释 
				System.out.println("数据库写入-"+allList.size()+"条-耗时："+(System.currentTimeMillis()- insertLong)/1000+"秒");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 调试执行状态与耗时：正式发布需取消注释 
		System.out.println(DateUtils.getToday() + " syncJqsjJob finsh,Processed "+(System.currentTimeMillis() -openLong)/1000+"S !");
	}
}