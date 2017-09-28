<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>工作日志</title>
<script type="text/javascript" src="${staticPath}/static/js/aqsc/base/worklog.js?v=20170226" charset="utf-8"></script>
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
<!-- 【工作日志】查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:95px;border: false;overflow: auto;" data-options="collapsible:true">
		<form id="searchForm_worklog">
			<table class="grid" width='650px' border='0' cellspacing='0' style='padding:5px'>
			  <tr>
			    <td class="form-td-label" style="width: 80px">单位名称：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='createOrgzname'   style='width: 149px'>
			    </td>
			    <td class="form-td-label" style="width: 80px">人员姓名：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='createUsername'   style='width: 149px'>
			    </td>
			    <td class="form-td-label" style="width: 80px">工作内容：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='content'   style='width: 149px'>
			    </td>
			  </tr>
			  <tr>
			    <td class="form-td-label" style="width: 80px">工作日期：</td>
			    <td align="left" width="160px" colspan="4">
			      <input   class="easyui-datebox"  name='starDay'   style='width: 149px'>&nbsp;&nbsp;至&nbsp;&nbsp;<input   class="easyui-datebox"  name='endDay'   style='width: 149px'>
			    </td>
			    <td align="left" width="170px" >
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchWorklogFun();">查询</a>
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleanWorklogFun();">重置</a>
			    </td>
			  </tr>
			</table>
		</form>
	</div>

<!-- 【工作日志】结果列表 -->
	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
		<table id="dataGrid_worklog" title="列表" data-options="fit:true,border:false"></table>
	</div>

<div id="toolbar" style="display: none;">
    	<a onclick="reloadWorklog();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<shiro:hasPermission name="/aqsc/base/worklog/add">
    	</shiro:hasPermission>
    	<a onclick="addWorklogFun();" id="button_add_worklog" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	<shiro:hasPermission name="/aqsc/base/worklog/edit">
    	</shiro:hasPermission>
    	<a onclick="editWorklogFun();" id="button_edit_worklog" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	<shiro:hasPermission name="/aqsc/base/worklog/del">
    	</shiro:hasPermission>
    	<a onclick="deleteWorklogFun();" id="button_del_worklog" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	<a onclick="clearWorklogSelections();" id="button_quxiao_worklog" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    	<a onclick="expWorklogXls();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
</div>
<!-- 【工作日志】新增弹出框-->	<div id="addDialog_worklog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal:true"	style="width:660px;height:378px;padding:10px 10px;display:none;" >
        <form id="addForm_worklog" method="post" data-options="novalidate:true">
			<table  class="grid" style="width: 550px">
			  <tr>
			    <td  class="form-td-label" >工作日期：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datebox" class="easyui-validatebox span2" name='workDay'  id='add_workDay_worklog' data-options="prompt:'请输入工作日期'" style='width: 150px'>			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" ><span style="color: red;">*</span>工作内容：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='content'  id='add_content_worklog'  data-options="prompt:'请输入工作内容',multiline:true,validType:'length[1,40]',required:true"  style='width: 100%;height:100px'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="add-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="addWorklogAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#addDialog_worklog').dialog('close')">关闭</a>
	</div>

<!-- 【工作日志】编辑弹出框 -->
	<div id="editDialog_worklog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#edit-dialog-buttons',closed:true,cache: false,modal:true"
	style="width:660px;height:378px;padding:10px 10px;display:none;" >
        <form id="editForm_worklog" method="post" data-options="novalidate:true">
           <input name="id" type="hidden" >
           <input name="updateTime" type="hidden" >
			<table  class="grid" style="width: 550px">
			  <tr>
			    <td  class="form-td-label" >工作日期：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datebox" class="easyui-validatebox span2" name='workDay'  id='edit_workDay_worklog' data-options="prompt:'请输入工作日期'" style='width: 150px'>			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" ><span style="color: red;">*</span>工作内容：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='content'  id='edit_content_worklog'  data-options="prompt:'请输入工作内容',multiline:true,validType:'length[1,40]',required:true"  style='width: 100%;height:100px'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="edit-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="editWorklogAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#editDialog_worklog').dialog('close')">关闭</a>
	</div>
</body>
</html>
