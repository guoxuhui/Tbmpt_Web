<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>用户消息查询</title>
<script type="text/javascript" src="${staticPath }/static/js/sys/sysUserMsg/list.js?v=2017060000696911" charset="utf-8"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<!-- 查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:100px;border: false;overflow: hidden;" data-options="collapsible:true">
		<form id="searchForm">
			<table class="grid">
				<tr>
					<td class="form-td-label" style="width: 80px">消息标题：</td>
					<td class="form-td-content" style="width: 160px">
						<input id="search_msgname" name="msgname" class="easyui-textbox" data-options="prompt:'请输入消息标题'" style="width: 100%;">
					</td>
					<td class="form-td-label" style="width: 80px">消息分类：</td>
					<td class="form-td-content" style="width: 160px">
						<select id="search_msgtype" name="msgtype" class="easyui-combobox" data-options="prompt:'请选择消息分类'" style="width: 100%;">
						    <option value="">--请选择--</option>
		  					<option value="系统消息">系统消息</option>
		  					<option value="设备报警消息">设备报警消息</option>
		  					<option value="工程质量巡检消息">工程质量巡检消息</option>
						</select>
					</td>
				</tr>
				<tr>
				    <td class="form-td-label" style="width: 80px" >消息时间：</td>
					<td class="form-td-content" style="width: 160px" >
						<input id="search_msgtime" name="msgtime" class="easyui-datetimebox" data-options="prompt:'消息时间'" style="width:100%;">
					</td>
					<td class="form-td-label" style="width: 80px">消息状态 ：</td>
					<td class="form-td-content">
					    <select id="search_state" name="state" class="easyui-combobox" data-options="prompt:'请输入消息分类'" style="width: 100%;">
						    <option value="">--请选择--</option>
		  					<option value="0" selected="selected">未读</option>
		  					<option value="1">已读</option>
						</select>
					<td style="text-align: right;" colspan="2">
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a> 
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleanFun();">重置</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 结果列表 -->
	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
		<table id="dataGrid" title="列表" data-options="fit:true,border:false"></table>
	</div>
	<div id="toolbar" style="display: none;">
		<a onclick="reloadFun();" href="javascript:void(0);" id="reload_button_line_info" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<a onclick="clearSelections();" href="javascript:void(0);" id="clear_button_line_info" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    	<a onclick="showFun();" href="javascript:void(0);" id="show_button_line_info" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-book_previous'">消息查看</a>
    	<a onclick="closeFun();" href="javascript:void(0);" id="close_buttion_msg" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-comment_delete'">标记已读</a>
  	</div>
	
</body>
</html>