<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>角色管理</title>
<script type="text/javascript" src="${staticPath }/static/js/sys/role/list.js?v=20170602160803" charset="utf-8"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
<div data-options="region:'west',split:true" title="组织机构" style="width:230px;">
		<ul id="orgzTree"  style="margin: 10px 10px 10px 10px;width: 95%;"></ul>
</div>
<!-- 查询表单 -->
<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
<div class="easyui-layout" fit="true">
	<div title="查询" data-options="region:'north',border:false"  style="width:100%;height:100px;border: false;overflow: hidden;" data-options="collapsible:true">
		<form id="searchForm">
			<table class="grid" style="border: 0px">
				<tr>
					<td class="form-td-label" style="width: 100px">角色名称：</td>
					<td class="form-td-content" style="width: 180px">
						<input id="search_name" name="name" class="easyui-textbox" data-options="prompt:'请输入角色名称'" style="width: 100%;" />
					</td>
					<td class="form-td-label" style="width: 100px">当前状态：</td>
					<td class="form-td-content" style="width: 180px">
                        <select name="status" class="easyui-combobox" data-options="editable:false,prompt:'请输入当前状态'" style="width: 100%;">
                            <option value="">--请选择--</option>
                            <option value="0">启用</option>
                            <option value="1">禁用</option>
                        </select>
					</td>
					<td style=""></td>
				</tr>
				<tr>
					<td class="form-td-label">创建起始时间：</td>
					<td class="form-td-content">
						<input id="startTime" name="startTime" class="easyui-datetimebox" data-options="prompt:'创建起始时间'" style="width:100%;"></td>
					<td class="form-td-label">创建截止时间：</td>
					<td class="form-td-content">
						<input id="endTime" name="endTime" class="easyui-datetimebox" data-options="prompt:'创建截止时间'" style="width:100%;"></td>
					<td style="text-align: right;" colspan="2">
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a> 
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleanFun();">重置</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 结果列表 -->
	<div data-options="region:'center',border:false" style="width:100%;height:100%;">
		<table id="dataGrid" title="角色列表" data-options="fit:true,border:false"></table>
	</div>
	<div id="toolbar" style="display: none;">
		<a onclick="reloadFun();" href="javascript:void(0);" id="reload_button_sys_role" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<shiro:hasPermission name="/sys/role/add">
    	   <a onclick="addFun();" href="javascript:void(0);" id="add_button_sys_role" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/sys/role/edit">
    	   <a onclick="editFun();" href="javascript:void(0);" id="edit_button_sys_role" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/sys/role/del">
    	   <a onclick="deleteFun();" href="javascript:void(0);" id="del_button_sys_role" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	</shiro:hasPermission>
    	<a onclick="clearSelections();" href="javascript:void(0);" id="clear_button_sys_role" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    	<shiro:hasPermission name="/sys/role/list">
    	   <a onclick="showFun();" href="javascript:void(0);" id="show_button_sys_role" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-book_previous'">查看</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/sys/role/grant">
    	<a onclick="grantFun();" href="javascript:void(0);" id="pdreset_button_sys_role" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">授权</a>
    	</shiro:hasPermission>    	
    	<a onclick="expXls();" href="javascript:void(0);" id="expXls_button_sys_role" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
    	<a onclick="expPdf();" href="javascript:void(0);" id="expPdf_button_sys_role" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_white_acrobat'">导出Pdf</a>       
  	</div>
</div>
</div>
	<!-- 弹出框－新增 -->
	<div id="addDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:550px;height:230px;padding:5px 5px;" >
        <form id="addForm" method="post" data-options="novalidate:true">
            <table class="grid">
                <tr>
                    <td class="form-td-label" style="width: 80px"><span style="color: red;">*</span>角色名称：</td>
                    <td class="form-td-content" style="width: 160px">
                    	<input name="name" class="easyui-textbox" data-options="required:true,prompt:'请输入角色名称'" style="width: 100%;">
                    </td>
                    <td class="form-td-label" style="width: 80px"><span style="color: red;">*</span>状态：</td>
                    <td class="form-td-content" style="width: 160px">
                        <select name="status" class="easyui-combobox" data-options="editable:false,required:true,prompt:'请输入状态'" style="width: 100%;">
                            <option value="0">正常</option>
                            <option value="1">停用</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label">部门：</td>
                    <td class="form-td-content" colspan="3">
                    	<select id="add_orgzId" name="orgzId" class="easyui-combotree" data-options="required:true" style="width:100%;"></select>
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label">备注：</td>
                    <td class="form-td-content" colspan="3">
                    	<input name="description" class="easyui-textbox" data-options="required:false,prompt:'请输入备注',multiline:true" style="width:100%;height:50px">
                    </td>
                </tr>
            </table>
        </form>
	</div>
	<div id="add-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="addAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#addDialog').dialog('close')">关闭</a>
	</div>
	
	<!-- 弹出框－编辑 -->
	<div id="editDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#edit-dialog-buttons',closed:true,cache: false,modal: true"
		style="width:550px;height:230px;padding:5px 5px;" >
        <form id="editForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden" >
			<table class="grid">
                <tr>
                    <td class="form-td-label" style="width: 80px"><span style="color: red;">*</span>角色名称：</td>
                    <td class="form-td-content" style="width: 160px">
                    	<input name="name" class="easyui-textbox" data-options="required:false,prompt:'请输入角色名称'" style="width: 100%;">
                    </td>
                    <td class="form-td-label" style="width: 80px"><span style="color: red;">*</span>状态：</td>
                    <td class="form-td-content" style="width: 160px">
                        <select name="status" class="easyui-combobox" data-options="required:true,prompt:'请输入状态'" style="width: 100%;">
                            <option value="0">正常</option>
                            <option value="1">停用</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label">部门：</td>
                    <td class="form-td-content" colspan="3">
                    	<select id="edit_orgzId" name="orgzId" class="easyui-combotree" data-options="required:true" style="width:100%;"></select>
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label">备注：</td>
                    <td class="form-td-content" colspan="3">
                    	<input name="description" class="easyui-textbox" data-options="required:false,prompt:'请输入备注',multiline:true" style="width:100%;height:50px">
                    </td>
                </tr>
            </table>
        </form>
	</div>
	<div id="edit-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="editAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#editDialog').dialog('close')">关闭</a>
	</div>
	
	<!-- 弹出框－编辑 -->
	<div id="showDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#show-dialog-buttons',closed:true,cache: false,modal: true"
		style="width:550px;height:230px;padding:5px 5px;" >
        <form id="showForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden" >
			<table class="grid">
                <tr>
                    <td class="form-td-label" style="width: 80px"><span style="color: red;">*</span>角色名称：</td>
                    <td class="form-td-content" style="width: 160px">
                    	<input name="name" class="easyui-textbox" data-options="required:false" style="width: 100%;">
                    </td>
                    <td class="form-td-label" style="width: 80px"><span style="color: red;">*</span>状态：</td>
                    <td class="form-td-content" style="width: 160px">
                        <select name="status" class="easyui-combobox" data-options="editable:false,required:true" style="width: 100%;">
                            <option value="0">正常</option>
                            <option value="1">停用</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label">部门：</td>
                    <td class="form-td-content" colspan="3">
                    	<select id="show_orgzId" name="orgzId" class="easyui-combotree" data-options="required:true" style="width:100%;"></select>
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label">备注：</td>
                    <td class="form-td-content" colspan="3">
                    	<input name="description" class="easyui-textbox" data-options="required:false,multiline:true" style="width:100%;height:50px">
                    </td>
                </tr>
            </table>
        </form>
	</div>
	<div id="show-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#showDialog').dialog('close')">关闭</a>
	</div>
</body>
</html>