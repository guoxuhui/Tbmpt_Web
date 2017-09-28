<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>日掘进信息管理</title>
<script type="text/javascript" src="${staticPath}/static/js/dgjj/rjj/dgjjRjjInfo.js?v=20170421183537 " charset="utf-8"></script>
<style type="text/css">
	td{
		 white-space: nowrap;
	}
	td input{
		white-space: normal;
	}
</style>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
<!-- wpg:【日掘进信息管理】线路查询表单 --> 
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:71px;border: false;overflow: auto;" data-options="collapsible:true">
		<form id="searchForm_dgjjRjjInfoParent">
			<table class="grid" width='650px' border='0' cellspacing='0' style='padding:5px'>
			  <tr>
			  	<td class="form-td-label" style="width: 80px">项目名称：</td>
			    <td align="left" width="160px"colspan="3">
			      <input   class="easyui-combobox"  name='proId'  data-options="prompt:'请选择项目名称'" id="query_gcBh_dgjjRjjInfo"  style='width: 410px'>
			    </td>
			    <td align="left" width="170px" >
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchDgjjRjjInfoParentFun();">查询</a>
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleanDgjjRjjInfoParentFun();">重置</a>
			    </td>
			  </tr>
			</table>
		</form>
	</div>

	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
		<table id="dataGrid_dgjjRjjInfoParent" title="列表" data-options="fit:true,border:false"></table>
	</div>

<!-- wpg：“日掘进信息管理” 线路面按钮 --> 
<div id="toolbarParent" style="display: none;">
    	<a onclick="reloadDgjjRjjInfoParent();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<a onclick="clearDgjjRjjInfoParentSelections();" id="dataGrid_dgjjRjjInfoParent_button_seleClean" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    	<shiro:hasPermission name="/dgjj/rjj/dgjjRjjInfo/ImpExcel">  
    	    <a onclick="openDgjjRjjInfoImp();" id="dataGrid_dgjjRjjInfo_button_ImpExcel"  href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">Excle导入日掘进信息</a>
    	</shiro:hasPermission> 
</div>

<!------------------------------------------------- 以上为主表、以下为子表 ----------------------------------------------->
	
	
	
	<!-- wpg:“Excle导入日掘进信息” 选择文件 窗口 -->
	<div id="openImpexcel_dgjjRjjInfo" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#upload-dialog-buttons',closed:true,cache: false,modal:true"	
	style="width:470px;height:210px;padding:10px 10px;" >
			<form id="uploadFile_dgjjRjjInfo_Form"  method="post" data-options="novalidate:true" enctype="multipart/form-data">
				<table >
					<tr>
					    <td align="left" width="160px" >
				        	<input  class="easyui-filebox"  name='clumOne' label="请选择要导入的文件：" labelPosition="top"
				        	 accept=".xls"
				        	data-options="prompt:'选择文件',buttonText:'&nbsp;选&nbsp;择&nbsp;',required:true"   id="query_gcBh_dgjjRjjInfo"  style='width: 410px'>
				    	</td>
				  </tr>
				  <tr>
				  	<td><a href="${staticPath}/dgjj/rjj/dgjjRjjInfo/downLoadModel" >点击下载导入模板</a></td>
				  </tr>
				  <tr>
                	<td  colspan="2">
                	    <span >
		                	<font color='red'>
		                		备注：请严格按照模板格式录入数据，每行数据之间不得存在空行，
		                		<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                		           模板外部不得编辑或增添任何文字或图片，
		                		<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                		           模板中有“ * ”标注的列为必填项；
		                	</font>
	                	</span>
                	</td>
                </tr>
				</table>
			</form>
	</div>
	<!-- wpg:“Excle导入日掘进信息” 选择文件 窗口提交按钮 -->
	<div id="upload-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="selectBanZuByXlbh()">确定</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#openImpexcel_dgjjRjjInfo').dialog('close')">关闭</a>
	</div>
			
			

	<!-- wpg:“Excle导入日掘进信息” 选择班组页面菜单栏，班组 数据表    -->
	
	<div id="SelectBanZushowToolbar" style="display: none;">
	   <a onclick="reloadSelectBanZuShow();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
	   <a onclick="clearSelectBanZuSelections();" id="dataGrid_SelectBanZu_button_seleClean" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
	</div>
	<!-- wpg:“Excle导入日掘进信息” 选择班组页面 ，班组 数据表单 2   -->
   <form id="SelectBanZuForm_dgjjRjjInfo" method="post" data-options="novalidate:true">
       <div id="SelectBanZuDialog_dgjjRjjInfo" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#SelectBanZu-dialog-buttons',closed:true,cache: false,modal:true"	style="width:600px;height:450px;padding:10px 10px;" >
			<div class="easyui-panel" title="线路信息" data-options="region:'center',collapsible:true"  
		            style="width:98%;height:110px;border: false;overflow: hidden;">	
				<table class="grid" width='500px' border='0' cellspacing='0' style='padding:5px'>
					<tr>
				    <td  class="form-td-label" >项目名称：</td>
				    <td class="form-td-content" colspan="3">
				      	<span id="SelectBanZu_projectName"></span>
				    </td>
				  </tr>
				  <tr>
				    <td  class="form-td-label" >区间名称：</td>
				    <td class="form-td-content" >
				      	<span id="SelectBanZu_qujianName"></span>
				    </td>
				    <td  class="form-td-label" >线路名称：</td>
				    <td class="form-td-content" >
				      	<span id="SelectBanZu_lineName"></span>
				    </td>
				  </tr>
				</table>
			</div>	
			<!-- wpg:“Excle导入日掘进信息” 选择班组页面 ，班组 数据列表 2   -->
			<div class="easyui-panel" data-options="border:false" style="width:98%;height:100%;">
				<table id="dataGrid_SelectBanZu" title="日掘进信息管理" data-options="fit:true,border:false"></table>
			</div> 
		</div>
   </form>
   <!-- wpg:选择班组窗体按钮  -->
   <div id="SelectBanZu-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" id="uploadFile_dgjjRjjInfo" onclick="uploadFile_dgjjRjjInfo()">确定</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#SelectBanZuDialog_dgjjRjjInfo').dialog('close')">关闭</a>
   </div>
	
	
	
	
	
	
<!-- wpg:“Excle 方式 导入 【日掘进明细】 数据表单 3  列表菜单栏 -->
<div id="excleWayToolbar" style="display: none;">
	    <a onclick="deleteExcleWayDgjjRjjInfoFun();" id="datagrid_dgjjRjjInfo_delExcleWay" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除明细</a>
    	<a onclick="addExcleWayDgjjRjjInfoFun();" id="datagrid_dgjjRjjInfo_addExcleWay" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加明细</a>
    	<a onclick="clearExcleWayDgjjRjjInfoSelections();" id="dataGrid_dgjjRjjInfo_button_ExcleWayseleClean" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
</div>
	<!-- wpg:“Excle 方式 导入 【日掘进明细 数据表格】  窗口 3-->
   <form id="excleWayAddForm_dgjjRjjInfo"  method="post" data-options="novalidate:true">
   		<!-- 日掘进明细 集合 -->
   		<input type="hidden" id="excleWayAddForm_dgjjRjjInfoDetails" name="details">
   		<!-- 班组 Id -->
   		<input type="hidden" id="excleWayAdd_BId" name="BId">
		<div id="excleWayAddDialog_dgjjRjjInfo" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#excleWayAdd-dialog-buttons',closed:true,cache: false,modal:true"	style="width:1000px;height:450px;padding:10px 10px;" >
			<div class="easyui-panel" title="线路信息" data-options="region:'center',collapsible:true"  
		            style="width:98%;height:140px;border: false;overflow: hidden;">	
				<table class="grid" width='650px' border='0' cellspacing='0' style='padding:5px'>
					<tr>
				    <td  class="form-td-label" >项目名称：</td>
				    <td class="form-td-content" colspan="3">
				      	<span id="excleWayAdd_projectName"></span>
				    </td>
				  </tr>
				  <tr>
				    <td  class="form-td-label" >区间名称：</td>
				    <td class="form-td-content" >
				      	<span id=excleWayAdd_qujianName></span>
				    </td>
				    <td  class="form-td-label" >线路名称：</td>
				    <td class="form-td-content" >
				      	<span id="excleWayAdd_lineName"></span>
				    </td>
				  </tr>
				  <tr>
				    <td  class="form-td-label" >班次名称：</td>
				    <td class="form-td-content" colspan="3">
				      	<span id="excleWayAdd_bzname"></span>
				    </td>
				  </tr>
				</table>
			</div>
		    <!-- wpg:“Excle 方式 导入 【日掘进明细 数据表格】  结果列表 -->
			<div class="easyui-panel" data-options="border:false" style="width:98%;height:100%;">
				<table id="dataGrid_excleWay_dgjjRjjInfo" title="日掘进参数列表-<font color='red'>(*鼠标双击可直接编辑*)</font>" data-options="fit:true,border:false"></table>
			</div> 
		</div>
   </form>
   <!-- wpg:“Excle 方式 导入 【日掘进明细 数据表格】  窗体按钮-->
   <div id="excleWayAdd-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="excleWayAddDgjjRjjInfoAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="closeExcleWayDgjjRjjInfoDialog()">关闭</a>
   </div>
   
   
   
   
	<!-- wpg:“Excle 方式 导入 【日掘进明细 数据表格】 －新增明细 窗口 -->
	<div id="excleWayAddOneRjj" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#excleWayAddOneRjj-dialog-buttons',closed:true,cache: false"
		style="width:1000px;height:450px;padding:10px 10px;"  >
        <form id="excleWayAddOneRjjForm" method="post" data-options="novalidate:true">
	        <table class="grid">
	            <tr>
	                <!-- 第 0 列  -->
	                <!-- 整型 -->
	            	<td  class="form-td-label" ><span style="color: red;">*</span>施工环号：</td>
				    <td>
				        <input name='sghh' id='excleAddRjj_sghh' required="required"  data-options="prompt:'请输入施工环号',validType:'length[1,40]'" class="easyui-numberbox" min="0" max="100000000"   class="easyui-validatebox span2"    style='width: 100%;'>
	                </td>
			        <!-- 双精度型 -->
			        <td  class="form-td-label" ><span style="color: red;">*</span>管片变换关键里程：</td>
			        <td class="form-td-content" >
	                    <input name='gpbhgjlc' id='excleAddRjj_gpbhgjlc'  data-options="prompt:'请输入管片变换关键里程',validType:'length[1,40]'"  class="easyui-numberbox" precision="6" min="0.000001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>	
			        </td> 
			        <!-- 文本类型  --> 
		            <td  class="form-td-label" ><span style="color: red;">*</span>设计类型：</td>
				    <td class="form-td-content" >
				        <input name='sjlx'  id='excleAddRjj_sjlx' data-options="prompt:'请输入设计类型',validType:'length[1,40]'"  class="easyui-textbox"   class="easyui-validatebox span2"   style='width: 100%;'>
                    </td>
	            
	            </tr>
	            <tr>
	               
	                <!-- 文本类型  -->
                    <td  class="form-td-label" >设计轴线：</td>
				    <td>
				        <input name='sjzx'  id='excleAddRjj_sjzx' data-options="prompt:'请输入设计轴线',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
				    </td>
	                <!-- 文本类型  -->
                    <td  class="form-td-label" >施工类型：</td>
				    <td>
				        <input name='sglx'  id='excleAddRjj_sglx' data-options="prompt:'请输入施工类型',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
				    </td> 
			        <!-- 整型 -->
			        <td  class="form-td-label" >封顶块位置：</td>
			        <td class="form-td-content" >
	                    <input name='fdkwz' id='excleAddRjj_fdkwz'  data-options="prompt:'请输入封顶块位置',validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>
			        </td> 
	            </tr>
	            <tr>
	            	<td  class="form-td-label" >注浆压力Mpa：</td>
				    <td class="form-td-content" >
				        <!-- 双精度型  6-->
					    <input name='zjyl' id='excleAddRjj_zjyl'  data-options="prompt:'请输入注浆压力Mpa',validType:'length[1,40]'"  class="easyui-numberbox" precision="6" min="0.000001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>	
					</td>
					
					<td  class="form-td-label"   align="right" >同步注浆量：</td>
					<td class="form-td-content"  >
					    <!-- 整型 -->
					    <input name='tbzjl' id='excleAddRjj_tbzjl'  data-options="prompt:'请输入同步注浆量',validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>
	                </td> 
	            <!-- 第 8  列  -->
		            <td  class="form-td-label"  align="right" >同步注浆位置：</td>
					<td class="form-td-content"  >
					    <!-- 文本类型  -->
						<input name='tbzjwz'  id='excleAddRjj_tbzjwz' data-options="prompt:'请输入同步注浆位置',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
				</tr>
	            <tr>
	            	<td  class="form-td-label"  align="right" >管片注浆位置：</td>
					<td class="form-td-content"  >
					    <!-- 文本类型  -->
						<input name='gpzjwz'  id='excleAddRjj_gpzjwz' data-options="prompt:'请输入管片注浆位置',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
					<td  class="form-td-label"  align="right" >后续注浆时间：</td>
					<td class="form-td-content"  >
					    <!-- 文本类型  -->
						<input name='hxzjsj'  id='excleAddRjj_hxzjsj' data-options="prompt:'请输入后续注浆时间',validType:'length[1,40]'"  class="easyui-textbox"   class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
				</tr>
	            <tr>
					<td  class="form-td-label"  align="right" >土压（bar)：</td>
					<td class="form-td-content"  >
						<!-- 双精度型  3-->
						<input name='ty' id='excleAddRjj_ty'  data-options="prompt:'请输入土压（bar)',validType:'length[1,40]'"  class="easyui-numberbox" precision="3" min="0.001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>
	                </td>
			    </tr>
	            <tr>
	                <!-- 第 12 列  -->
		            <td  class="form-td-label"  align="right" >出土量(方)：</td>
					<td class="form-td-content"  >
						<!-- 文本类型  -->        
						<input name='ctl'  id='excleAddRjj_ctl' data-options="prompt:'请输入出土量(方)',validType:'length[1,40]'"  class="easyui-textbox"   class="easyui-validatebox span2"   style='width: 100%;'>
		            </td>
		            
		            <td  class="form-td-label"  align="right" >平均总推力(KN)：</td>
					<td class="form-td-content"  >
					    <!-- 双精度型  3-->
						<input name='pjztl' id='excleAddRjj_pjztl'  data-options="prompt:'请输入平均总推力(KN)',validType:'length[1,40]'"  class="easyui-numberbox" precision="3" min="0.001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>
					</td> 
					<td  class="form-td-label"  align="right" >平均扭矩（KN.m）：</td>
					<td class="form-td-content"  >
					    <!-- 双精度型  3-->
						<input name='pjnj' id='excleAddRjj_pjnj'  data-options="prompt:'请输入平均扭矩（KN.m）',validType:'length[1,40]'"  class="easyui-numberbox" precision="3" min="0.001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
	            </tr>
	            <tr>
	               <td  class="form-td-label"  align="right" >隧道轴线-高程：</td>
					<td class="form-td-content"  >
						<!-- 双精度型  6-->
						<input name='sdzxgc' id='excleAddRjj_sdzxgc'  data-options="prompt:'请输入隧道轴线-高程',validType:'length[1,40]'"  class="easyui-numberbox" precision="6" min="0.000001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>	
	         		</td>
	                <!-- 第 16 列  -->
	            	<td  class="form-td-label"  align="right" >隧道轴线-平面：</td>
					<td class="form-td-content"  >
						<!-- 双精度型  6-->
						<input name='sdzxpm' id='excleAddRjj_sdzxpm'  data-options="prompt:'请输入隧道轴线-平面',validType:'length[1,40]'"  class="easyui-numberbox" precision="6" min="0.000001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>	
	         		</td>
	            </tr>
	            <tr>
	                <td colspan='8'>
			            <table class="grid" title="推进千斤顶">
			            <tr><td >推进千斤顶:</td></tr>
				            <tr >
				                <td  class="form-td-label"  align="right" >推进千斤顶-管片安装前：</td>
								<td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpazqA' id='excleAddRjj_gpazqA'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>
							    </td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpazqB' id='excleAddRjj_gpazqB'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpazqC' id='excleAddRjj_gpazqC'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpazqD' id='excleAddRjj_gpazqD'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
				                
				            </tr>
				            <tr>
				                <td  class="form-td-label"  align="right" >推进千斤顶-管片安装后：</td>
								<td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpazhA' id='excleAddRjj_gpazhA'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>
							    </td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpazhB' id='excleAddRjj_gpazhB'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpazhC' id='excleAddRjj_gpazhC'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpazhD' id='excleAddRjj_gpazhD'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
				            </tr>
			            </table>
	                </td>
	            </tr>
	            
	            <tr>
	                <td colspan='8'>
			            <table class="grid" title="盾构机轴线姿态">
			                <tr><td >盾构机轴线姿态:</td></tr>
				            <tr>
				                <td  class="form-td-label"  align="right" >水平-切口：</td>
								<td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dgjzxspqk' id='excleAddRjj_dgjzxspqk'  data-options="prompt:'请输入水平-切口',validType:'length[1,40]'" class="easyui-numberbox"  max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>
							    </td>
							    <td  class="form-td-label"  align="right" >水平-盾尾：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dgjzxspdw' id='excleAddRjj_dgjzxspdw'  data-options="prompt:'请输入水平-盾尾',validType:'length[1,40]'" class="easyui-numberbox"  max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
							   
				            </tr>
				            <tr>
				               <td  class="form-td-label"  align="right" >垂直-切口：</td>
								<td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dgjzxczqk' id='excleAddRjj_dgjzxczqk'  data-options="prompt:'请输入垂直-切口',validType:'length[1,40]'" class="easyui-numberbox"  max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>
							    </td>
							    <td  class="form-td-label"  align="right" >垂直-盾尾：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dgjzxczdw' id='excleAddRjj_dgjzxczdw'  data-options="prompt:'请输入垂直-盾尾',validType:'length[1,40]'" class="easyui-numberbox" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
				            </tr>
			            </table>
	                </td>
	            </tr>
	            
	            <tr>
	                <td colspan='8'>
			            <table class="grid" title="盾尾间隙（mm）">
			            <tr><td >盾尾间隙（mm）:</td></tr>
				            <tr title="盾尾间隙-拼装前">
				                <td  class="form-td-label"  align="right" >拼装前：</td>
				                <td  class="form-td-label"  align="right" >上：</td>
								<td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dwjxpzqs' id='excleAddRjj_dwjxpzqs'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>
							    </td>
							    <td  class="form-td-label"  align="right" >下：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dwjxpzqx' id='excleAddRjj_dwjxpzqx'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
							    <td  class="form-td-label"  align="right" >左：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dwjxpzqz' id='excleAddRjj_dwjxpzqz'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
							    <td  class="form-td-label"  align="right" >右：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dwjxpzqy' id='excleAddRjj_dwjxpzqy'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
				                
				            </tr>
				            <tr title="盾尾间隙-拼装后">
				                <td  class="form-td-label"  align="right" >拼装后：</td>
				                <td  class="form-td-label"  align="right" >上：</td>
								<td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dwjxpzhs' id='excleAddRjj_dwjxpzhs'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>
							    </td>
							    <td  class="form-td-label"  align="right" >下：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dwjxpzhx' id='excleAddRjj_dwjxpzhx'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
							    <td  class="form-td-label"  align="right" >左：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dwjxpzhz' id='excleAddRjj_dwjxpzhz'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
							    <td  class="form-td-label"  align="right" >右：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dwjxpzhy' id='excleAddRjj_dwjxpzhy'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
				            </tr>
			            </table>
	                </td>
	            </tr>
	            
	            <tr>
	                <td colspan='8'>
			            <table class="grid" title="管片姿态（mm）">
			                <tr><td >管片姿态（mm）:</td></tr>
				            <tr title="管片姿态-拼装前">
				                <td  class="form-td-label"  align="right" >拼装前-高程：</td>
								<td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpztpzqgc' id='excleAddRjj_gpztpzqgc'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>
							    </td>
							    <td  class="form-td-label"  align="right" >拼装前-平面：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpztpzqpm' id='excleAddRjj_gpztpzqpm'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
							   
				                
				            </tr>
				            <tr title="管片姿态-拼装后">
				                 <td  class="form-td-label"  align="right" >拼装后-高程：</td>
								<td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpztpzhgc' id='excleAddRjj_gpztpzhgc'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>
							    </td>
							    <td  class="form-td-label"  align="right" >拼装后-平面：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpztpzhpm' id='excleAddRjj_gpztpzhpm'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
							   
				            </tr>
			            </table>
	                </td>
	            </tr>
	                
	            
	            <tr>
	                <td  class="form-td-label"  align="right" >盾构掘进完成起止时间：</td>
					<td class="form-td-content"  >
					    <!-- 文本类型  -->        
						<input name='dgjjwcqzsj'  id='excleAddRjj_dgjjwcqzsj' data-options="prompt:'请输入盾构掘进完成起止时间',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
					<td  class="form-td-label"  align="right" >施工日期：</td>
					<td class="form-td-content"  >
					    <!-- 文本类型  -->        
						<input name='sgrq'  id='excleAddRjj_sgrq' data-options="prompt:'请输入施工日期'" class="easyui-datebox"  class="easyui-validatebox span2" style="width:100%;">  
					</td>
	            </tr>
	            <tr>
		            <td  class="form-td-label"  valign="top" align="right" >备注：</td>
					<td class="form-td-content"  colspan='7'>
					     <!-- 文本类型  -->        
						<input name='remarks'  id='excleAddRjj_remarks' data-options="prompt:'请输入备注',multiline:true,validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
	            </tr>
			</table>
        </form>
	</div>
	<!-- wpg:“Excle 方式 导入 【日掘进明细 数据表格】 －新增明细 窗口按钮 -->
	<div id="excleWayAddOneRjj-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="excleWayAddOneRjjSave()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#excleWayAddOneRjj').dialog('close')">关闭</a>
	</div>
	


<!-- “查看日掘进明细‘单条信息 增、改’‘多条信息 删’” 数据表格 4  窗口 按钮 -->
 
        <!-- wpg:“ 查看【日掘进明细 】 窗口 菜单栏 -->
		<div id="show_toolbar" style="display: none;">
	    	<a onclick="reloadDgjjRjjInfo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
	    	<shiro:hasPermission name="/dgjj/rjj/dgjjRjjInfo/add"> 
	    	    <a onclick="addDgjjRjjInfoFun();" id="datagrid_dgjjRjjInfo_add" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
	    	</shiro:hasPermission> 
	    	<shiro:hasPermission name="/dgjj/rjj/dgjjRjjInfo/edit">  
	    	    <a onclick="editDgjjRjjInfoFun();" id="datagrid_dgjjRjjInfo_edit" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">修改</a>
	    	</shiro:hasPermission> 
	    	    <a onclick="lookDgjjRjjInfoFun();" id="datagrid_dgjjRjjInfo_book_previous" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-book_previous'">查看</a>
	    	<shiro:hasPermission name="/dgjj/rjj/dgjjRjjInfo/del">  
		        <a onclick="deleteDgjjRjjInfoFun();" id="datagrid_dgjjRjjInfo_del" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
		    </shiro:hasPermission>  
		    <a onclick="clearDgjjRjjInfoSelections();" id="dataGrid_dgjjRjjInfo_button_seleClean" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
		    <shiro:hasPermission name="/dgjj/rjj/dgjjRjjInfo/ExpExcel">  
	    	    <a onclick="expDgjjRjjInfoXls();" href="javascript:void(0);"  id="dataGrid_dgjjRjjInfo_button_ExpExcel" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
	    	</shiro:hasPermission> 
    	    <shiro:hasPermission name="/dgjj/rjj/dgjjRjjInfo/ExpPdf">  
	    	    <a onclick="expDgjjRjjInfoPdf();" href="javascript:void(0);"  id="dataGrid_dgjjRjjInfo_button_ExpPdf" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_white_acrobat'">导出Pdf</a>
	    	</shiro:hasPermission>
    	</div>
 
	<!-- wpg:“ 查看【日掘进明细 】 窗口  -->  
   <form id="showForm_dgjjRjjInfo" method="post" data-options="novalidate:true">
   		<input type="hidden" id="show_details" name="details"> 
		<div id="showDialog_dgjjRjjInfo" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#showAdd-dialog-buttons',closed:true,cache: false,modal:true"	style="width:1000px;height:450px;padding:10px 10px;" >
				<div class="easyui-panel" title="线路信息" data-options="region:'center',collapsible:true"  
		            style="width:98%;height:110px;border: false;overflow: hidden;">
					<table class="grid" width='650px' border='0' cellspacing='0' style='padding:5px'>
						<tr>
					    <td  class="form-td-label" >项目名称：</td>
					    <td class="form-td-content" colspan="3">
					      	<span id="show_projectName"></span>
					    </td>
					  </tr>
					  <tr>
					    <td  class="form-td-label" >区间名称：</td>
					    <td class="form-td-content" >
					      	<span id="show_qujianName"></span>
					    </td>
					    <td  class="form-td-label" >线路名称：</td>
					    <td class="form-td-content" >
					      	<span id="show_lineName"></span>
					    </td>
					  </tr>
					</table>
				</div>
			    <!-- wpg:“ 查看【日掘进明细 】 窗口 结果列表  -->
				<div class="easyui-panel" data-options="border:false" style="width:98%;height:100%;">
					<table id="dataGrid_dgjjRjjInfo" title="日掘进参数列表-<font color='red'>(*鼠标双击可直接编辑*)</font>" data-options="fit:true,border:false"></table>
				</div> 
		</div>
   </form>
   <!-- wpg:“ 查看【日掘进明细 】 窗口按钮  -->
   <div id="showAdd-dialog-buttons">
        <shiro:hasPermission name="/dgjj/rjj/dgjjRjjInfo/edit">  
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="showDgjjRjjInfoSaveAjax()">保存</a>
		</shiro:hasPermission>  
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="closeDgjjRjjInfoShowDialog()">关闭</a>
   </div>
   
   
   

</body>
</html>
