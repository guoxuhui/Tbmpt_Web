<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>设备报警历史查询</title>
<script type="text/javascript" src="${staticPath }/static/js/machine/alarmhis/list.js?v=20170407083518" charset="utf-8"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<!-- 查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:100px;border: false;overflow: hidden;" data-options="collapsible:true">
		<form id="searchForm">
			<table class="grid">
				<tr>
					<td class="form-td-label" style="width: 80px">工程名称：</td>
					<td class="form-td-content" style="width: 160px">
						<select id="search_proId" name="proId" class="easyui-combobox" data-options="prompt:'请输入工程名称'" style="width: 100%;"></select>
					</td>
					<td class="form-td-label" style="width: 80px">区间名称：</td>
					<td class="form-td-content" style="width: 160px">
						<select id="search_sectionId" name="sectionId" class="easyui-combobox" data-options="prompt:'请输入区间名称'" style="width: 100%;"></select>
					</td>
					<td class="form-td-label" style="width: 80px" >线路名称：</td>
					<td class="form-td-content" style="width: 160px" >
						<input id="search_lineName" name="lineName" class="easyui-textbox" data-options="prompt:'请输入线路名称'" style="width: 100%;"/>
					</td>
				</tr>
				<tr>
					<td class="form-td-label">起始时间：</td>
					<td class="form-td-content">
						<input id="startTime" name="startTime" class="easyui-datetimebox" data-options="prompt:'起始创建时间'" style="width:100%;"></td>
					<td class="form-td-label">结束时间：</td>
					<td class="form-td-content">
						<input id="endTime" name="endTime" class="easyui-datetimebox" data-options="prompt:'截止创建时间'" style="width:100%;"></td>
					<td style="text-align: right;" colspan="2">
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a> 
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleanFun();">重置</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 结果列表 -->
	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100px;">
		<table id="dataGrid" title="列表" data-options="fit:true,border:false"></table>
	</div>
	<div id="toolbar" style="display: none;">
		<a onclick="reloadFun();" href="javascript:void(0);" id="reload_button_line_info" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<a onclick="clearSelections();" href="javascript:void(0);" id="clear_button_line_info" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    	<a onclick="showFun();" href="javascript:void(0);" id="show_button_line_info" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-book_previous'">查看</a>
    	<a onclick="expXls();" href="javascript:void(0);" id="expXls_button_line_info" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
    	<a onclick="expPdf();" href="javascript:void(0);" id="expPdf_button_line_info" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_white_acrobat'">导出Pdf</a>       
  	</div>
	
</body>
</html>