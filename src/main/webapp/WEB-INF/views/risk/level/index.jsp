<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>风险级别管理</title>
<script type="text/javascript" src="${staticPath }/static/js/risk/level/list.js?v=20170814190879" charset="utf-8"></script>
<script src="${staticPath }/static/minicolors/jquery.minicolors.js"></script>
<link rel="stylesheet" href="${staticPath }/static/minicolors/jquery.minicolors.css">
<script>
	$(document).ready( function() {
		 $("#text-field").minicolors();
	});
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<!-- 查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:80px;border: false;overflow: hidden;" data-options="collapsible:true">
		<form id="searchForm">
		<table class="grid" style="border: 0px">
				<tr>
					<td class="form-td-label" style="width: 85px">风险级别名称：</td> 
					<td class="form-td-content" style="width: 180px">
						<input id="search_levelName" name="levelName" class="easyui-textbox" data-options="prompt:'请输入风险级别名称'" style="width: 100%;"/>
					</td>
					<td style=""></td>
					<td  style="text-align: center;">
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
		<a onclick="reloadFun();" href="javascript:void(0);" id="reload_button_level_info" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<a onclick="clearSelections();" href="javascript:void(0);" id="clear_button_sys_emp" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    	<shiro:hasPermission name="/risk/level/add">
    	   <a onclick="addFun();" href="javascript:void(0);" id="add_button_level_info" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/risk/level/edit">
    	   <a onclick="editFun();" href="javascript:void(0);" id="edit_button_level_info" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/risk/level/list">
    	   <a onclick="showFun();" href="javascript:void(0);" id="show_button_level_info" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-list'">查看</a>
    	</shiro:hasPermission>    	
    	<shiro:hasPermission name="/risk/level/del">
    	   <a onclick="deleteFun();" href="javascript:void(0);" id="del_button_level_info" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	</shiro:hasPermission>
  	</div>
	<!-- 弹出框－新增 -->
	<div id="addDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:450px;height:320px;padding:5px 5px;" >
        <form id="addForm" method="post" data-options="novalidate:true">
            <table class="grid">
            	<tr>
                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>风险级别名称:</td>
                	<td class="form-td-content" colspan="3" style="width:280px">
                    	<input name="levelName" class="easyui-textbox" data-options="required:true,prompt:'请输入风险级别名称'" style="width:100%;">
                	</td>
            	</tr>
                <tr>
                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>颜色标识:</td>
                    <td class="" colspan="3" style="width:280px">
                    	<input type="text" name="colorFlag" id="text-field"  class="form-control demo" value="" style="width:100%;">
                    </td>
                </tr>
                <tr>
                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>排序号:</td>
                    <td class="form-td-content" colspan="3" style="width:280px">
                    	<input name="sort" class="easyui-numberbox" data-options="required:true,prompt:'请输入排序号'" style="width:100%;">
                    </td>
                </tr>
                <tr>
                	<td class="form-td-label" style="width:100px">说明:</td>
                    <td class="form-td-content" colspan="3" style="width:280px">
                    	<input name="riskDesc" class="easyui-textbox" data-options="required:false,prompt:'请输说明',multiline:true" validType="length[0,200]" style="width:100%;height:50px">
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
		 style="width:450px;height:320px;padding:5px 5px;" >
        <form id="editForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden">
           <table class="grid">
            	<tr>
                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>风险级别名称:</td>
                	<td class="form-td-content" colspan="3" style="width:280px">
                    	<input name="levelName" class="easyui-textbox" data-options="required:true,prompt:'请输入风险级别名称'" style="width:100%;">
                	</td>
            	</tr>
                <tr>
                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>颜色标识:</td>
                    <td class="form-td-content" colspan="3" style="width:280px">
                    		<input type="text" name="colorFlag" id="text-field-edit" class="form-control demo" value="" style="width:100%;">
                    </td>
                </tr>
                <tr>
                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>排序号:</td>
                    <td class="form-td-content" colspan="3" style="width:280px">
                    	<input name="sort" class="easyui-numberbox" data-options="required:true,prompt:'请输入排序号'" style="width:100%;">
                    </td>
                </tr>
                <tr>
                	<td class="form-td-label" style="width:100px">说明:</td>
                    <td class="form-td-content" colspan="3" style="width:280px">
                    	<input name="riskDesc" class="easyui-textbox" data-options="required:false,prompt:'请输说明',multiline:true" validType="length[0,200]" style="width:100%;height:50px">
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
		 style="width:450px;height:320px;padding:5px 5px;" >
        <form id="showForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden">
           <table class="grid">
            	<tr>
                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>风险级别名称:</td>
                	<td class="form-td-content" colspan="3" style="width:280px">
                    	<input name="levelName" class="easyui-textbox" data-options="required:true,prompt:'请输入风险级别名称'" style="width:100%;" readonly>
                	</td>
            	</tr>
                <tr>
                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>颜色标识:</td>
                    <td class="form-td-content" colspan="3" style="width:280px">
                    		<input type="text" name="colorFlag" id="text-field-show" class="form-control demo" value="" style="width:100%;" readonly>
                    </td>
                </tr>
                <tr>
                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>排序号:</td>
                    <td class="form-td-content" colspan="3" style="width:280px">
                    	<input name="sort" class="easyui-numberbox" data-options="required:true,prompt:'请输入排序号'" style="width:100%;" readonly>
                    </td>
                </tr>
                <tr>
                	<td class="form-td-label" style="width:100px">说明:</td>
                    <td class="form-td-content" colspan="3" style="width:280px">
                    	<input name="riskDesc" class="easyui-textbox" data-options="required:false,prompt:'请输说明',multiline:true" validType="length[0,200]" style="width:100%;height:50px" readonly>
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