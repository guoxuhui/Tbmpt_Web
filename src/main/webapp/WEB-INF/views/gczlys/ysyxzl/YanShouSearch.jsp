<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>验收影像资料管理</title>
<script type="text/javascript" src="${staticPath}/static/js//gczlys/YanShouSearch.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath}/static/PrintArea/jquery.PrintArea.js" charset="utf-8"></script>


<style>
<!--
 /* Font Definitions */
 @font-face
	{font-family:宋体;
	panose-1:2 1 6 0 3 1 1 1 1 1;}
@font-face
	{font-family:黑体;
	panose-1:2 1 6 9 6 1 1 1 1 1;}
@font-face
	{font-family:"Cambria Math";
	panose-1:2 4 5 3 5 4 6 3 2 4;}
@font-face
	{font-family:Calibri;
	panose-1:2 15 5 2 2 2 4 3 2 4;}
@font-face
	{font-family:仿宋_GB2312;}
@font-face
	{font-family:"\@宋体";
	panose-1:2 1 6 0 3 1 1 1 1 1;}
@font-face
	{font-family:"\@黑体";
	panose-1:2 1 6 9 6 1 1 1 1 1;}
@font-face
	{font-family:"\@仿宋_GB2312";}
 /* Style Definitions */
 p.MsoNormal, li.MsoNormal, div.MsoNormal
	{margin:0cm;
	margin-bottom:.0001pt;
	text-align:justify;
	text-justify:inter-ideograph;
	font-size:10.5pt;
	font-family:"Times New Roman","serif";}
p.MsoHeader, li.MsoHeader, div.MsoHeader
	{margin:0cm;
	margin-bottom:.0001pt;
	text-align:center;
	layout-grid-mode:char;
	border:none;
	padding:0cm;
	font-size:9.0pt;
	font-family:"Times New Roman","serif";}
p.MsoFooter, li.MsoFooter, div.MsoFooter
	{margin:0cm;
	margin-bottom:.0001pt;
	layout-grid-mode:char;
	font-size:9.0pt;
	font-family:"Times New Roman","serif";}
p.MsoDate, li.MsoDate, div.MsoDate
	{mso-style-link:"日期 Char";
	margin-top:0cm;
	margin-right:0cm;
	margin-bottom:0cm;
	margin-left:5.0pt;
	margin-bottom:.0001pt;
	text-align:justify;
	text-justify:inter-ideograph;
	font-size:10.5pt;
	font-family:"Times New Roman","serif";}
p.MsoDocumentMap, li.MsoDocumentMap, div.MsoDocumentMap
	{mso-style-link:"文档结构图 Char";
	margin:0cm;
	margin-bottom:.0001pt;
	text-align:justify;
	text-justify:inter-ideograph;
	font-size:9.0pt;
	font-family:宋体;}
span.Char
	{mso-style-name:"文档结构图 Char";
	mso-style-link:文档结构图;
	font-family:宋体;}
span.Char0
	{mso-style-name:"日期 Char";
	mso-style-link:日期;}
 /* Page Definitions */
 @page Section1
	{size:595.3pt 841.9pt;
	margin:104.9pt 73.7pt 99.25pt 79.4pt;
	layout-grid:15.6pt;}
div.Section1
	{page:Section1;}
-->

</style>
	<style>
		.div-a{ float:left;width:41%;}
		.div-b{ float:left;width:41%;}
		.div-c{ float:left;width:14%;height:250px;overflow-y:scroll;}
	</style>

</head>
<body class="easyui-layout" data-options="fit:true,border:false"> 
	<!-- 【验收影像资料管理】查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:105px;border: false;overflow: hidden;" data-options="collapsible:true" >
		<form id="searchForm_YanShouSearch" style="display:none"> 
			<table class="grid" border='0' cellspacing='0' style='width:90%;padding:5px'>
			  <tr>
			    <td class="form-td-label" style="width: 120px">工程名称：</td>
				<td class="form-td-content" style="width: 100px"> 
					<input name="projectname" class="easyui-textbox" data-options="prompt:'请输入工程名称'" id = "gcName_query_id" style="width: 180px;"  />
				</td>
			    <td class="form-td-label" style="width: 120px">验收人：</td>
				<td class="form-td-content" style="width:100px"> 
					<input name="yanshour" class="easyui-textbox" data-options="prompt:'请输入验收人'" id = "yanshour_query_id" style="width: 180px;"  />
				</td>
				
			    </tr>
			   <tr>
			     
			     <td class="form-td-label" style="width: 120px">验收时间：</td>
			     <td align="left" width="3200px" colspan="4">
			      <input   class="easyui-datetimebox"  name='startYanshousj'   style='width: 149px' data-options="prompt:'请输入验收时间'" > --- 
			      <input   class="easyui-datetimebox"  name='endYanshousj'   style='width: 149px' data-options="prompt:'请输入验收时间'" >
			    </td>
			    <td align="right" width="250px" >
			      <shiro:hasPermission name="/gczlys/YanShouSearch/search">
			     	 <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchYanShouSearchFun();">查询</a>
			      </shiro:hasPermission>
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleandYanShouSearch();">重置</a>
			    </td>
			  </tr>
			</table>
		</form>
	</div>
	
	<!-- 【施工内容管理】结果列表 -->
	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
		<table id="dataGrid_YanShouSearch" title="列表" data-options="fit:true,border:false"></table>
	</div>
 
 <div id="toolbar" style="display: none;"> 
    	        <a onclick="reloadYanShouSearch();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a> 
    	    	<shiro:hasPermission name="/gczlys/YanShouSearch/info">
    	    		<a onclick="showSearchFun();" href="javascript:void(0);" id="show_button_YanShouSearch" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-book_previous'">查看</a> 
    	    	</shiro:hasPermission>
    	    	<shiro:hasPermission name="/gczlys/YanShouSearch/print">
    	        	<a onclick="toPrintOut();" href="javascript:void(0);" id="print_button_YanShouSearch" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-printer_go'">打印</a>
  				</shiro:hasPermission>
  </div>
 
 <!-- 【工程质量验收信息】查看-->	
    	<div id="showDialog_ForSearch" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache:false,modal:true"	
    	style="width:75%;height:380px;padding:10px 10px;display:none;" >
			<div class="div-a">
				<div>
					<span style="color: red;">*</span>工程名称：<input class="easyui-textbox" class="easyui-validatebox span2"  name='projectname'   style='width: 230px'>
				</div>
				<div>
					<span style="color: red;">*</span>工程部位：<input class="easyui-textbox" class="easyui-validatebox span2"  name='gcbuwei'   style='width: 230px'>
				</div>
				<div>
					<span style="color: red;">*</span>施工班组：<input class="easyui-textbox" class="easyui-validatebox span2"  name='banzu'   style='width: 230px'>
				</div>
				<div>
					<div><span style="color: red;">*</span>现场检查实际情况(写出各检查主要参数)：</div>
					<div>
						<input class="easyui-textbox" name='miaoshu'  data-options="multiline:true"  style="width:90%;height:150px">
					</div>
				</div>
			</div>
			<div class="div-b">
				<div>
					<div style="float: left;width:120px;text-align: right;">天气：</div>
					<div style="float: left;">
						<select class="easyui-combobox" class="easyui-validatebox span2"  name='tianqi' id='show_tq_YanShouYingXiang'   style='width: 150px'>
							<option value="1">晴</option>
							<option value="2">阴</option>
							<option value="3">雨</option>
							<option value="4">雪</option>
						</select>
					</div>
				</div>
				<div style="clear:both;">
					<div style="float: left;width:120px;text-align: right;"><span style="color: red;">*</span>分布分项工序名称：</div>
					<div style="float: left">
						<input class="easyui-textbox" class="easyui-validatebox span2"  name='gongxu'   style='width: 150px'>
					</div>
				</div>
				<div style="clear:both;">
					<div style="float: left;width:120px;text-align: right;"><span style="color: red;">*</span>班组负责人：</div>
					<div style="float: left">
						<input class="easyui-textbox" class="easyui-validatebox span2"  name='bzfuzr' style='width: 150px'>
					</div>
				</div>
				<div style="clear:both;">
					<div>检查验收意见和结论：</div>
					<div>
						<input class="easyui-textbox" name='jianchaqk'  data-options="multiline:true"  style="width:90%;height:150px">
					</div>
				</div>
			</div>
			<div id="img_div_yxYszlYsSearch" class="div-c" ></div>
		</div>

	<div id="show-dialog-buttons" style="display:none">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#showDialog').dialog('close')">关闭</a>
	</div>
   
   <!-- 【工程质量验收信息】打印-->	
    	<div id="printDialog_ForSearch"  align=center class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache:false,modal:true"	
    	style="width:800px;height:480px;padding:10px 10px;display:none;" >
        <form id="showForm_YanShouSearch" method="post" data-options="novalidate:true">
        
  <div class=Section1  id="printTable"  style='width:600px;height:890px; vertical-align: middle;'>
</br></br>
<p class=MsoNormal align=center style='text-align:center;page-break-before:
always'><span style='font-size:18.0pt;font-family:黑体'>工序质量检查验收记录</span></p>


 
<table align=center width='600px'>
<p align=left>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style='font-size:12.0pt;font-family:宋体'>工程名称：</span> <span id="print_projectName" style='font-size:12.0pt;font-family:宋体'> </span>&nbsp;&nbsp;&nbsp;&nbsp;<span
lang=GBK style='font-size:12.0pt'> </span><span style='font-size:12.0pt;font-family:宋体'>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;天气：</span><span id='print_Tianqi' style='font-size:12.0pt;font-family:宋体'></span></p>
 <tr style='height:1.0cm'>
  <td width=163 style='width:122.4pt;border:solid black 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:1.0cm'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>检查日期时间</span></p>
  </td>
  <td width=149 colspan=2 style='width:131.55pt;border:solid black 1.0pt;
  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:1.0cm'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=GBK
  style='font-size:12.0pt' id='print_jcrqsj'></span></p>
  </td>
  <td width=198 style='width:128.85pt;border:solid black 1.0pt;border-left:
  none;padding:0cm 5.4pt 0cm 5.4pt;height:1.0cm'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>验收人（质检工程师）</span></p>
  </td>
  <td width=113 style='width:3.0cm;border:solid black 1.0pt;border-left:none;
  padding:0cm 5.4pt 0cm 5.4pt;height:1.0cm'>
  <p class=MsoNormal align=center style='text-align:center'><span id='print_ysr' lang=GBK
  style='font-size:12.0pt'></span></p>
  </td>
 </tr>
 <tr style='height:1.0cm'>
  <td width=163 style='width:122.4pt;border:solid black 1.0pt;border-top:none;
  padding:0cm 5.4pt 0cm 5.4pt;height:1.0cm'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>工程部位</span></p>
  </td>
  <td width=149 colspan=2 style='width:111.55pt;border-top:none;border-left:
  none;border-bottom:solid black 1.0pt;border-right:solid black 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:1.0cm'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=GBK
  style='font-size:12.0pt' id='print_gcbw'></span></p>
  </td>
  <td width=198 style='width:128.85pt;border-top:none;border-left:none;
  border-bottom:solid black 1.0pt;border-right:solid black 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:1.0cm'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>分部分项工序名称</span></p>
  </td>
  <td width=113 style='width:3.0cm;border-top:none;border-left:none;border-bottom:
  solid black 1.0pt;border-right:solid black 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:1.0cm'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=GBK
  style='font-size:12.0pt' id='print_gxmc'></span></p>
  </td>
 </tr>
 <tr style='height:1.0cm'>
  <td width=163 style='width:122.4pt;border:solid black 1.0pt;border-top:none;
  padding:0cm 5.4pt 0cm 5.4pt;height:1.0cm'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>施工班组</span></p>
  </td>
  <td width=149 colspan=2 style='width:111.55pt;border-top:none;border-left:
  none;border-bottom:solid black 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:1.0cm'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=GBK
  style='font-size:12.0pt'  id='print_sgbz'></span></p>
  </td>
  <td width=198 style='width:128.85pt;border-top:none;border-left:none;
  border-bottom:solid black 1.0pt;border-right:solid windowtext 1.0pt;
  padding:0cm 5.4pt 0cm 5.4pt;height:1.0cm'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>班组负责人</span></p>
  </td>
  <td width=113 style='width:3.0cm;border-top:none;border-left:none;border-bottom:
  solid black 1.0pt;border-right:solid black 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:1.0cm'>
  <p class=MsoNormal align=center style='text-align:center'><span lang=GBK
  style='font-size:12.0pt' id='print_bzfzr'></span></p>
  </td>
 </tr>
 <tr style='height:1.0cm'>
  <td width=624 colspan=5 style='width:467.85pt;border:solid black 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:1.0cm'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>现场检查实际情况</span></p>
  </td>
 </tr>
 <tr style='height:372.15pt'>
  <td width=624 colspan=5 valign=top style='width:467.85pt;border:solid black 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:372.15pt'>
  <p class=MsoNormal><span lang=GBK style='font-size:12.0pt'></span></p>
  <p class=MsoNormal>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span lang=GBK style='font-size:12.0pt' id='print_xcjcqk'></span></p>
  </td>
 </tr>
 <tr style='height:54.55pt'>
  <td width=208 colspan=2 style='width:156.0pt;border:solid black 1.0pt;
  border-top:none;padding:0cm 5.4pt 0cm 5.4pt;height:54.55pt'>
  <p class=MsoNormal align=center style='text-align:center'><span
  style='font-size:12.0pt;font-family:宋体'>检查验收意见和结论</span></p>
  </td>
  <td width=416 colspan=3 style='width:11.0cm;border-top:none;border-left:none;
  border-bottom:solid black 1.0pt;border-right:solid black 1.0pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:54.55pt'>
  <p class=MsoNormal align=left style='text-align:left'><span lang=GBK
  style='font-size:12.0pt'  id='print_ysjl'></span></p>
  </td>
 </tr> 
</table> 
</div>
    
    <div id="img_div_yxYszlYsPrint">
	</div>
	</div>
	<div id="add-dialog-buttons" style="display:none"> 
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-printer_go'" onclick="printOut()">打印</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#printDialog_ForSearch').dialog('close')">关闭</a>
	</div>
   
  
</body>
</html>