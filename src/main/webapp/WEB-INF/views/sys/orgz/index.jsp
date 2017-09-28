<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>机构管理</title>
<script type="text/javascript" src="${staticPath }/static/js/sys/orgz/list.js?v=20170508114909" charset="utf-8"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
    
    <!-- 结果列表 -->
	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
		<table id="treeGrid" title="资源列表树" data-options="fit:true,border:false"></table>
	</div>
	<div id="toolbar" style="display: none;">
		<a onclick="reloadFun();" href="javascript:void(0);" id="reload_button_sys_orgz" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<shiro:hasPermission name="/sys/orgz/add">
    	   <a onclick="addFun();" href="javascript:void(0);" id="add_button_sys_orgz" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/sys/orgz/edit">
    	   <a onclick="editFun();" href="javascript:void(0);" id="edit_button_sys_orgz" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/sys/orgz/del">
    	   <a onclick="deleteFun();" href="javascript:void(0);" id="del_button_sys_orgz" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	</shiro:hasPermission>
    	<a onclick="clearSelections();" href="javascript:void(0);" id="clear_button_sys_orgz" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    	<shiro:hasPermission name="/sys/orgz/list">
    	   <a onclick="showFun();" href="javascript:void(0);" id="show_button_sys_orgz" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-book_previous'">查看</a>
    	</shiro:hasPermission>
    	<a onclick="expand();" href="javascript:void(0);" id="expXls_button_sys_orgz" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-redo'">展开</a>
    	<a onclick="collapse();" href="javascript:void(0);" id="expXls_button_sys_orgz" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">折叠</a>    	   	
    	<a onclick="expXls();" href="javascript:void(0);" id="expXls_button_sys_orgz" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
    	<a onclick="expPdf();" href="javascript:void(0);" id="expPdf_button_sys_orgz" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_white_acrobat'">导出Pdf</a>       
  	</div> 
	<!-- 弹出框－新增 -->
	<div id="addDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:580px;height:260px;padding:5px 5px;" >
        <form id="addForm" method="post" data-options="novalidate:true">
        	<input  name="seq" type="hidden" value="0"/>
	        <table class="grid">
	            <tr>
	                <td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>部门名称：</td>
	                <td class="form-td-content" style="width: 160px">
	                	<input name="name" type="text" class="easyui-textbox" data-options="required:true,prompt:'请输入部门名称'" style="width:100%;">
	                </td>
	                <td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>部门类型：</td>
	                <!-- 机构类型0：机关单位 1：项目机构 2 项目部门 -->
	                <td class="form-td-content" style="width: 160px">
	                    <select name="type" class="easyui-combobox" data-options="editable:false,required:true,prompt:'请输入部门类型'" style="width:100%;">
	                        <option value="0">机关单位</option>
	                        <option value="1">项目机构</option>
	                        <option value="2">项目部门</option>
	                    </select>
	                </td>
	            </tr>
	            <tr>
	                <td class="form-td-label">上级部门：</td>
	                <td class="form-td-content" colspan="3">
	                	<select id="add_pid" name="pid" class="easyui-combotree" data-options="editable:false,prompt:'请输入上级部门'" style="width:100%;"></select>
	                </td>
	            </tr>
	            <tr>
	                <td class="form-td-label">地址：</td>
	                <td class="form-td-content" colspan="3">
	                	<input name="address" class="easyui-textbox" data-options="required:false,prompt:'请输入地址',multiline:true" style="width:100%;height:50px">
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
		 style="width:580px;height:260px;padding:5px 5px;" >
        <form id="editForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden" >
        	<input  name="icon" type="hidden"/>
        	<input  name="code" type="hidden"/>
        	<input  name="seq" type="hidden"/>
            <input name="createTime" type="hidden" >
	        <table class="grid">
	            <tr>
	                <td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>部门名称：</td>
	                <td class="form-td-content" style="width: 160px">
	                	<input name="name" type="text" class="easyui-textbox" data-options="required:true,prompt:'请输入部门名称'" style="width:100%;">
	                </td>
	                <td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>部门类型：</td>
	                <!-- 机构类型0：机关单位 1：项目机构 2 项目部门 -->
	                <td class="form-td-content" style="width: 160px">
	                    <select name="type" class="easyui-combobox" data-options="editable:false,required:true,prompt:'请输入部门类型'" style="width:100%;">
	                        <option value="0">机关单位</option>
	                        <option value="1">项目机构</option>
	                        <option value="2">项目部门</option>
	                    </select>
	                </td>
	            </tr>
	            <tr>
	                <td class="form-td-label">上级部门：</td>
	                <td class="form-td-content" colspan="3">
	                	<select id="edit_pid" name="pid" class="easyui-combotree" data-options="editable:false,prompt:'请输入上级部门'" style="width:100%;"></select>
	                </td>
	            </tr>
	            <tr>
	                <td class="form-td-label">地址：</td>
	                <td class="form-td-content" colspan="3">
	                	<input name="address" class="easyui-textbox" data-options="required:false,prompt:'请输入地址',multiline:true" style="width:100%;height:50px">
	                </td>
	            </tr>
	        </table>
        </form>
	</div>
	<div id="edit-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="editAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#editDialog').dialog('close')">关闭</a>
	</div>
	
	<!-- 弹出框－查看 -->
	<div id="showDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#show-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:580px;height:260px;padding:5px 5px;" >
        <form id="showForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden" >
        	<input  name="icon" type="hidden"/>
        	<input  name="code" type="hidden"/>
        	<input  name="seq" type="hidden"/>
            <input name="createTime" type="hidden" >
	        <table class="grid">
	            <tr>
	                <td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>部门名称：</td>
	                <td class="form-td-content" style="width: 160px">
	                	<input name="name" type="text" class="easyui-textbox" data-options="required:true" style="width:100%;">
	                </td>
	                <td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>部门类型：</td>
	                <!-- 机构类型0：机关单位 1：项目机构 2 项目部门 -->
	                <td class="form-td-content" style="width: 160px">
	                    <select name="type" class="easyui-combobox" data-options="editable:false,required:true" style="width:100%;">
	                        <option value="0">机关单位</option>
	                        <option value="1">项目机构</option>
	                        <option value="2">项目部门</option>
	                    </select>
	                </td>
	            </tr>
	            <tr>
	                <td class="form-td-label">上级部门：</td>
	                <td class="form-td-content" colspan="3">
	                	<select id="show_pid" name="pid" class="easyui-combotree" data-options="editable:false," style="width:100%;"></select>
	                </td>
	            </tr>
	            <tr>
	                <td class="form-td-label">地址：</td>
	                <td class="form-td-content" colspan="3">
	                	<input name="address" class="easyui-textbox" data-options="required:false,multiline:true" style="width:100%;height:50px">
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