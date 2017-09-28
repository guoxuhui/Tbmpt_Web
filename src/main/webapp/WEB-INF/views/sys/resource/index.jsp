<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>资源管理</title>
<script type="text/javascript" src="${staticPath }/static/js/sys/resource/list.js" charset="utf-8"></script>
<script type="text/javascript">
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
    <!-- 结果列表 -->
	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
		<table id="treeGrid" data-options="fit:true,border:false"></table>
	</div>
	<div id="toolbar" style="display: none;">
		<a onclick="reloadFun();" href="javascript:void(0);" id="reload_button_sys_resource" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<shiro:hasPermission name="/sys/resource/add">
    	   <a onclick="addFun();" href="javascript:void(0);" id="add_button_sys_resource" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/sys/resource/edit">
    	   <a onclick="editFun();" href="javascript:void(0);" id="edit_button_sys_resource" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/sys/resource/del">
    	   <a onclick="deleteFun();" href="javascript:void(0);" id="del_button_sys_resource" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	</shiro:hasPermission>
    	<a onclick="clearSelections();" href="javascript:void(0);" id="clear_button_sys_resource" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    	<shiro:hasPermission name="/sys/resource/list">
    	   <a onclick="showFun();" href="javascript:void(0);" id="show_button_sys_resource" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-book_previous'">查看</a>
    	</shiro:hasPermission>
    	<a onclick="expand();" href="javascript:void(0);" id="expXls_button_sys_resource" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-redo'">展开</a>
    	<a onclick="collapse();" href="javascript:void(0);" id="expXls_button_sys_resource" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">折叠</a>   	
    	<a onclick="expXls();" href="javascript:void(0);" id="expXls_button_sys_resource" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
    	<a onclick="expPdf();" href="javascript:void(0);" id="expPdf_button_sys_resource" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_white_acrobat'">导出Pdf</a>       
  	</div> 
	<!-- 弹出框－新增 -->
	<div id="addDialog" class="easyui-dialog" data-options="icon:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:600px;height:350px;padding:5px 5px;" >
        <form id="addForm" method="post" data-options="novalidate:true">
        	<input name="status" type="hidden" value="0" >
            <table class="grid">
				<tr>
	                <td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>资源名称：</td>
	                <td class="form-td-content" style="width: 150px">
	                	<input name="name" class="easyui-textbox"  data-options="required:true,prompt:'请输入资源名称'" style="width: 100%">
	                </td>
	                <td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>资源类型：</td>
	                <td class="form-td-content" style="width: 150px">
	                    <select name="resourceType" class="easyui-combobox" data-options="editable:false,required:true,prompt:'请输入资源类型'" style="width:100%">
	                        <option value="0">菜单</option>
	                        <option value="1">按钮</option>
	                    </select>
	                </td>
	            </tr>
	            <tr>
	                <td class="form-td-label"><span style="color: red;">*</span>资源路径：</td>
	                <td class="form-td-content">
	                	<input name="url" class="easyui-textbox"  data-options="required:true,prompt:'请输入资源路径'" style="width: 100%">
	                </td>
	                <td class="form-td-label"><span style="color: red;">*</span>排序：</td>
	                <td class="form-td-content">
	                	<input name="seq" value="0"  class="easyui-numberspinner" data-options="editable:false,required:true,prompt:'请输入资源路径'" style="width: 100%">
	                </td>
	            </tr>
	            <tr>
	                <td class="form-td-label">菜单图标：</td>
	                <td class="form-td-content">
	                	<input id="add_icon" name="icon" class="easyui-textbox" data-options="prompt:'请输入菜单图标',buttonAlign:'right',buttonIcon:'icon-search',editable:false,onClickButton:iconSelection" style="width: 100%;">
	                	<!-- <input  name="icon" style="width: 95%;margin-right: 15px"/> -->
	                </td>
	                <td class="form-td-label"><!-- <span style="color: red;">*</span>状态： </td>
	                <td class="form-td-content">
	                	<input name="status" type="hidden" value="0" >
	                    <!-- <select name="status" class="easyui-combobox" data-options="editable:false,panelHeight:'auto'" style="width: 95%;margin-right: 15px">
	                        <option value="0">正常</option>
	                        <option value="1">停用</option>
	                    </select> -->
	                </td>
	            </tr>
	            <tr>
	                <td class="form-td-label">上级资源：</td>
	                <td class="form-td-content" colspan="3">
	                	<select id="add_pid" name="pid" class="easyui-combotree"  data-options="prompt:'请输入上级资源'" style="width: 100%" ></select>
	                </td>
	            </tr>
	            <tr>
	                <td class="form-td-label">资源描述：</td>
	                <td class="form-td-content" colspan="3">
	                	<input name="description" class="easyui-textbox" data-options="required:false,prompt:'请输入资源描述',multiline:true" style="width:100%;height:50px">
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
		 style="width:600px;height:350px;padding:5px 5px;" >
        <form id="editForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden" >
            <input name="createTime" type="hidden" >
            <input name="status" type="hidden" >
				<table class="grid">
				<tr>
	                <td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>资源名称：</td>
	                <td class="form-td-content" style="width: 150px">
	                	<input name="name" class="easyui-textbox"  data-options="required:true,prompt:'请输入资源名称'" style="width: 100%">
	                </td>
	                <td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>资源类型：</td>
	                <td class="form-td-content" style="width: 150px">
	                    <select name="resourceType" class="easyui-combobox" data-options="editable:false,required:true,prompt:'请输入资源类型'" style="width:100%">
	                        <option value="0">菜单</option>
	                        <option value="1">按钮</option>
	                    </select>
	                </td>
	            </tr>
	            <tr>
	                <td class="form-td-label"><span style="color: red;">*</span>资源路径：</td>
	                <td class="form-td-content">
	                	<input name="url" class="easyui-textbox"  data-options="required:true,prompt:'请输入资源路径'" style="width: 100%">
	                </td>
	                <td class="form-td-label"><span style="color: red;">*</span>排序：</td>
	                <td class="form-td-content">
	                	<input name="seq" value="0"  class="easyui-numberspinner" data-options="editable:false,required:true,prompt:'请输入资源路径'" style="width: 100%">
	                </td>
	            </tr>
	            <tr>
	                <td class="form-td-label">菜单图标：</td>
	                <td class="form-td-content">
	                	<input id="edit_icon" name="icon" class="easyui-textbox" data-options="prompt:'请输入菜单图标',buttonText:'',buttonAlign:'right',buttonIcon:'icon-search',onClickButton:iconSelection" style="width: 100%;">
	                	<!-- <input  name="icon" style="width: 95%;margin-right: 15px"/> -->
	                </td>
	                <td class="form-td-label"><!-- <span style="color: red;">*</span>状态： </td>
	                <td class="form-td-content">
	                	<input name="status" type="hidden" value="0" >
	                    <!-- <select name="status" class="easyui-combobox" data-options="editable:false,panelHeight:'auto'" style="width: 95%;margin-right: 15px">
	                        <option value="0">正常</option>
	                        <option value="1">停用</option>
	                    </select> -->
	                </td>
	            </tr>
	            <tr>
	                <td class="form-td-label">上级资源：</td>
	                <td class="form-td-content" colspan="3">
	                	<select id="edit_pid" name="pid" class="easyui-combotree"  data-options="prompt:'请输入上级资源'" style="width: 100%" ></select>
	                </td>
	            </tr>
	            <tr>
	                <td class="form-td-label">资源描述：</td>
	                <td class="form-td-content" colspan="3">
	                	<input name="description" class="easyui-textbox" data-options="required:false,prompt:'请输入资源描述',multiline:true" style="width:100%;height:50px">
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
	<div id="showDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#edit-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:600px;height:350px;padding:5px 5px;" >
        <form id="showForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden" >
            <input name="createTime" type="hidden" >
            <input name="status" type="hidden" >
				<table class="grid">
				<tr>
	                <td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>资源名称：</td>
	                <td class="form-td-content" style="width: 150px">
	                	<input name="name" class="easyui-textbox"  data-options="required:true" style="width: 100%">
	                </td>
	                <td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>资源类型：</td>
	                <td class="form-td-content" style="width: 150px">
	                    <select name="resourceType" class="easyui-combobox" data-options="editable:false,required:true" style="width:100%">
	                        <option value="0">菜单</option>
	                        <option value="1">按钮</option>
	                    </select>
	                </td>
	            </tr>
	            <tr>
	                <td class="form-td-label"><span style="color: red;">*</span>资源路径：</td>
	                <td class="form-td-content">
	                	<input name="url" class="easyui-textbox"  data-options="required:true" style="width: 100%">
	                </td>
	                <td class="form-td-label"><span style="color: red;">*</span>排序：</td>
	                <td class="form-td-content">
	                	<input name="seq" value="0"  class="easyui-numberspinner" data-options="editable:false,required:true" style="width: 100%">
	                </td>
	            </tr>
	            <tr>
	                <td class="form-td-label">菜单图标：</td>
	                <td class="form-td-content">
	                	<input id="show_icon" name="icon" class="easyui-textbox" data-options="buttonAlign:'right',buttonIcon:'icon-search',onClickButton:iconSelection" style="width: 100%;">
	                	<!-- <input  name="icon" style="width: 95%;margin-right: 15px"/> -->
	                </td>
	                <td class="form-td-label"><!-- <span style="color: red;">*</span>状态： </td>
	                <td class="form-td-content">
	                	<input name="status" type="hidden" value="0" >
	                    <!-- <select name="status" class="easyui-combobox" data-options="editable:false,panelHeight:'auto'" style="width: 95%;margin-right: 15px">
	                        <option value="0">正常</option>
	                        <option value="1">停用</option>
	                    </select> -->
	                </td>
	            </tr>
	            <tr>
	                <td class="form-td-label">上级资源：</td>
	                <td class="form-td-content" colspan="3">
	                	<select id="show_pid" name="pid" class="easyui-combotree"  data-options="" style="width: 100%" ></select>
	                </td>
	            </tr>
	            <tr>
	                <td class="form-td-label">资源描述：</td>
	                <td class="form-td-content" colspan="3">
	                	<input name="description" class="easyui-textbox" data-options="required:false,multiline:true" style="width:100%;height:50px">
	                </td>
	            </tr>
            </table>
        </form>
	</div>
	<div id="edit-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#showDialog').dialog('close')">关闭</a>
	</div>
</body>
</html>