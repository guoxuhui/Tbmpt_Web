<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp"%>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>分部工程管理</title>
<script type="text/javascript" src="${staticPath }/static/js/project/fbgc/list.js?v=20170407142611" charset="utf-8"></script>
</head>

<body class="easyui-layout" data-options="fit:true,border:false">
	<!-- 查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:100px;border: false;overflow: hidden;" data-options="collapsible:true">
        <form id="searchForm">
            <table class="grid">
				<tr>
					<td class="form-td-label" style="width: 100px">工程名称：</td>
					<td class="form-td-content" style="width: 320px">
						<select id="search_proId" name="proId" class="easyui-combobox" data-options="prompt:'请输入工程名称'" style="width: 100%;"></select>
					</td>
					<td class="form-td-label" style="width: 100px">单位工程：</td>
					<td class="form-td-content" style="width: 160px">
						<select id="search_dwgcId" name="dwgcId" class="easyui-combobox" data-options="prompt:'请输入单位工程'" style="width: 100%;"></select>
					</td>
				</tr>
				<tr>
					<td class="form-td-label" style="width: 100px" >分部工程名称：</td>
					<td class="form-td-content" style="width: 320px" >
						<input name="fbgcname" class="easyui-textbox" data-options="prompt:'请输入分部工程名称'" style="width: 100%;"/>
					</td>
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
	
		<a onclick="reloadFun();" href="javascript:void(0);" id="reload_button_project_fbgc" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<shiro:hasPermission name="/project/fbgc/add">
    	   <a onclick="addFun();" href="javascript:void(0);" id="add_button_project_fbgc" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/project/fbgc/edit">
    	   <a onclick="editFun();" href="javascript:void(0);" id="edit_button_project_fbgc" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/project/fbgc/del">
    	   <a onclick="deleteFun();" href="javascript:void(0);" id="del_button_project_fbgc" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	</shiro:hasPermission>
    	<a onclick="clearSelections();" href="javascript:void(0);" id="clear_button_project_fbgc" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    	<shiro:hasPermission name="/project/fbgc/list">
    	   <a onclick="showFun();" href="javascript:void(0);" id="show_button_project_fbgc" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-book_previous'">查看</a>
    	</shiro:hasPermission>    	
    	<a onclick="expXls();" href="javascript:void(0);" id="expXls_button_project_fbgc" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
    	<a onclick="expPdf();" href="javascript:void(0);" id="expPdf_button_project_fbgc" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_white_acrobat'">导出Pdf</a>      
      
  	</div>
	<!-- 弹出框－新增 -->
	<div id="addDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:420px;height:300px;padding:5px 5px;" >
        <form id="addForm" method="post" data-options="novalidate:true">
            <table class="grid">
            	<tr>
                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>所属工程:</td>
                	<td class="form-td-content" colspan="3" style="width:260px">
                    	<select id="add_proId" name="proId" class="easyui-combobox" data-options="required:true,prompt:'请输入所属工程'" style="width:100%;"></select>
                	</td>
            	</tr>
                <tr>
                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>所属单位工程:</td>
                    <td class="form-td-content" colspan="3" style="width:260px">
                    	<select id="add_dwgcId" name="dwgcId" class="easyui-combobox" data-options="required:true,prompt:'请输入所属单位工程'" style="width:100%;"></select>
                    </td>
                </tr>
                <tr>
					<td class="form-td-label"><span style="color: red;">*</span>分部工程名称：</td>
                    <td class="form-td-content" colspan="3">
                    	<input name="fbgcname" class="easyui-textbox" data-options="required:true,prompt:'请输入分部工程名称'" style="width:100%;">
                    </td>
                </tr>
                
                <tr>
                    <td class="form-td-label">备注:</td>
                   	<td colspan="3" class="form-td-content">
                   		<input name="remarks" class="easyui-textbox" data-options="required:false,prompt:'请输入备注',multiline:true" style="width:100%;height:50px">
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
		 style="width:420px;height:300px;padding:5px 5px;" >
        <form id="editForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden">
            <table class="grid">
            	<tr>
                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>所属工程:</td>
                	<td class="form-td-content" colspan="3" style="width:260px">
                    	<select id="edit_proId" name="proId" class="easyui-combobox" data-options="required:true,prompt:'请输入所属工程'" style="width:100%;"></select>
                	</td>
            	</tr>
                <tr>
                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>所属单位工程:</td>
                    <td class="form-td-content" colspan="3" style="width:260px">
                    	<select id="edit_dwgcId" name="dwgcId" class="easyui-combobox" data-options="required:true,prompt:'请输入所属单位工程'" style="width:100%;"></select>
                    </td>
                </tr>
                <tr>
					<td class="form-td-label"><span style="color: red;">*</span>分部工程名称：</td>
                    <td class="form-td-content" colspan="3">
                    	<input name="fbgcname" class="easyui-textbox" data-options="required:true,prompt:'请输入分部工程名称'" style="width:100%;">
                    </td>
                </tr>
                
                <tr>
                    <td class="form-td-label">备注:</td>
                   	<td colspan="3" class="form-td-content">
                   		<input name="remarks" class="easyui-textbox" data-options="required:false,prompt:'请输入备注',multiline:true" style="width:100%;height:50px">
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
		 style="width:420px;height:300px;padding:5px 5px;" >
        <form id="showForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden">
            <table class="grid">
            	<tr>
                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>所属工程:</td>
                	<td class="form-td-content" colspan="3" style="width:260px">
                    	<input name="proName" class="easyui-textbox" data-options="required:true" style="width:100%;">
                	</td>
            	</tr>
                <tr>
                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>所属单位工程:</td>
                    <td class="form-td-content" colspan="3" style="width:260px">
                    	<input name="dwgcName" class="easyui-textbox" data-options="required:true" style="width:100%;">
                    </td>
                </tr>
                <tr>
					<td class="form-td-label"><span style="color: red;">*</span>分部工程名称：</td>
                    <td class="form-td-content" colspan="3">
                    	<input name="fbgcname" class="easyui-textbox" data-options="required:true" style="width:100%;">
                    </td>
                </tr>
                
                <tr>
                    <td class="form-td-label">备注:</td>
                   	<td colspan="3" class="form-td-content">
                   		<input name="remarks" class="easyui-textbox" data-options="required:false,multiline:true" style="width:100%;height:50px">
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