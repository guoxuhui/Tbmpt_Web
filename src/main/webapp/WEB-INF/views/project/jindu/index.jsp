<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>施工日进度</title>
<script type="text/javascript" src="${staticPath }/static/js/project/jindu/list.js?v=20170517152131" charset="utf-8"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<!-- 查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:100px;border: false;overflow: hidden;" data-options="collapsible:true">
		<form id="searchForm">
			<table class="grid" style="border: 0px">
				<tr>
					<td class="form-td-label" style="width: 80px">工程名称：</td>
					<td class="form-td-content" style="width: 200px">
						<select id="search_proId" name="proId" class="easyui-combobox" data-options="prompt:'请选择工程名称'" style="width: 100%;margin-right: 15px"></select>
					</td>
					<td class="form-td-label" style="width: 80px">区间名称：</td>
					<td class="form-td-content" style="width: 200px">
						<select id="search_sectionId" name="qjId" class="easyui-combobox" data-options="prompt:'请选择区间名称'" style="width:100% ;margin-right: 15px"></select>
					</td>
					<td class="form-td-label" style="width: 80px" >线路名称：</td>
					<td class="form-td-content" style="width: 200px">
						<select id="search_LineId" name="xlId" class="easyui-combobox" data-options="prompt:'请选择线路名称'" style="width: 100%;margin-right: 15px"></select>
					</td>
				</tr>
				<tr>
					<td class="form-td-label">开始时间：</td>
					<td class="form-td-content" style="width: 200px">
						<input id="startTime" name="startTime" class="easyui-datebox" data-options="prompt:'请选择开始时间'"style="width: 100%;" /></td>
					<td class="form-td-label">结束时间：</td>
					<td class="form-td-content" style="width: 200px">
						<input id="endTime" name="endTime" class="easyui-datebox" data-options="prompt:'请选择结束时间'"style="width: 100%;" /></td>
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
		<a onclick="reloadFun();" href="javascript:void(0);" id="reload_button_project_jindu" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<shiro:hasPermission name="/project/jindu/add">
    	   <a onclick="addFun();" href="javascript:void(0);" id="add_button_project_jindu" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/project/jindu/edit">
    	   <a onclick="editFun();" href="javascript:void(0);" id="edit_button_project_jindu" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/project/jindu/del">
    	   <a onclick="deleteFun();" href="javascript:void(0);" id="del_button_project_jindu" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	</shiro:hasPermission>
    	<a onclick="clearSelections();" href="javascript:void(0);" id="clear_button_project_jindu" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    	<shiro:hasPermission name="/project/jindu/list">
    	   <a onclick="showFun();" href="javascript:void(0);" id="show_button_project_jindu" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-book_previous'">查看</a>
    	</shiro:hasPermission>    	
    	<a onclick="expXls();" href="javascript:void(0);" id="expXls_button_project_jindu" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
    	<a onclick="expPdf();" href="javascript:void(0);" id="expPdf_button_project_jindu" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_white_acrobat'">导出Pdf</a>       
  	</div>
    <!-- 弹出框--新增 -->
	<div id="addDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:420px;height:350px;padding:5px 5px;" >
        <form id="addForm" method="post" data-options="novalidate:true">
			<table class="grid">
               
                <tr>
                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>所属工程：</td>
                	<td class="form-td-content" colspan="3" style="width:260px">
                    	<select id="add_proId" name="proId" class="easyui-combobox" data-options="required:true" style="width: 100%;margin-right: 15px"></select>
                	</td>
                	
                </tr>
                <tr>
					<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>所属区间：</td>
                	<td class="form-td-content" colspan="3" style="width:260px">
                    	<select id="add_sectionId" name="qjId" class="easyui-combobox" data-options="required:true" style="width:100% ;margin-right: 15px"></select>
                	</td>
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>线路编号：</td>
                    <td class="form-td-content" colspan="3" style="width:260px">
                    	<select id="add_LineId" name="xlId" class="easyui-combobox" data-options="required:true" style="width: 100%;margin-right: 15px"></select>
                    </td>
                 </tr>
                 <tr>
					<td class="form-td-label"><span style="color: red;">*</span>掘进环数：</td>
                    <td class="form-td-content" colspan="3" style="width:260px">
						<input name="rjjnum" type="text" placeholder="管环数量" class="easyui-numberbox" data-options="required:true" value="" style="width: 100%;margin-right: 15px">
					</td>
                </tr>
                <tr>
                	<td class="form-td-label"><span style="color: red;">*</span>日期：</td>
                	<td class="form-td-content" colspan="3" style="width:260px">		
						<input name="rq" class="easyui-datebox" data-options="required:true" style="width:100%;"></td>
					</td>
                </tr>
                <tr>
                	<td class="form-td-label">备注：</td>
                	<td class="form-td-content" colspan="3" style="width:260px">
                    	<textarea name="remarks"  class="easyui-textbox"  data-options="required:false,prompt:'请输入备注',multiline:true" style="width:100%;height:50px"></textarea>
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
		 style="width:420px;height:350px;padding:5px 5px;" >
        <form id="editForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden"  >
			<table class="grid">
               
                <tr>
                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>所属工程：</td>
                	<td class="form-td-content" colspan="3" style="width:260px">
                    	<select id="edit_proId" name="proId" class="easyui-combobox" data-options="required:true" style="width: 100%;margin-right: 15px"></select>
                	</td>
                	
                </tr>
                <tr>
					<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>所属区间：</td>
                	<td class="form-td-content" colspan="3" style="width:260px">
                    	<select id="edit_sectionId" name="qjId" class="easyui-combobox" data-options="required:true" style="width:100% ;margin-right: 15px"></select>
                	</td>
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>线路编号：</td>
                    <td class="form-td-content" colspan="3" style="width:260px">
                    	<select id="edit_LineId" name="xlId" class="easyui-combobox" data-options="required:true" style="width: 100%;margin-right: 15px"></select>
                    </td>
                 </tr>
                 <tr>
					<td class="form-td-label"><span style="color: red;">*</span>掘进环数：</td>
                    <td class="form-td-content" colspan="3" style="width:260px">
						<input name="rjjnum" type="text" placeholder="管环数量" class="easyui-numberbox" data-options="required:true" value="" style="width: 100%;margin-right: 15px">
					</td>
                </tr>
                <tr>
                	<td class="form-td-label">日期：</td>
                	<td class="form-td-content" colspan="3" style="width:260px">		
						<input name="rq" class="easyui-datebox" data-options="prompt:''" style="width:100%;"></td>
					</td>
                </tr>
                <tr>
                	<td class="form-td-label">备注：</td>
                	<td class="form-td-content" colspan="3" style="width:260px">
                    	<textarea name="remarks"  class="easyui-textbox"  data-options="required:false,prompt:'请输入备注',multiline:true" style="width:100%;height:50px"></textarea>
                    </td>
                </tr>
                
        	</table>                
        	
        </form>
	</div>
	<div id="edit-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="editAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#editDialog').dialog('close')">关闭</a>
	</div>
	<!-- 弹出框－查看-->
	<div id="showDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#show-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:420px;height:350px;padding:5px 5px;" >
        <form id="showForm" method="post" data-options="novalidate:true">
			<table class="grid">
               
                <tr>
                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>所属工程：</td>
                	<td class="form-td-content" colspan="3" style="width:260px">
                    	<select id="show_proId" name="proId" class="easyui-combobox" data-options="required:true" style="width: 100%;margin-right: 15px"></select>
                	</td>
                	
                </tr>
                <tr>
					<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>所属区间：</td>
                	<td class="form-td-content" colspan="3" style="width:260px">
                    	<select id="show_sectionId" name="qjId" class="easyui-combobox" data-options="required:true" style="width:100% ;margin-right: 15px"></select>
                	</td>
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>线路编号：</td>
                    <td class="form-td-content" colspan="3" style="width:260px">
                    	<select id="show_LineId" name="xlId" class="easyui-combobox" data-options="required:true" style="width: 100%;margin-right: 15px"></select>
                    </td>
                 </tr>
                 <tr>
					<td class="form-td-label"><span style="color: red;">*</span>掘进环数：</td>
                    <td class="form-td-content" colspan="3" style="width:260px">
						<input name="rjjnum" type="text" placeholder="管环数量" class="easyui-numberbox" data-options="required:true" value="" style="width: 100%;margin-right: 15px">
					</td>
                </tr>
                <tr>
                	<td class="form-td-label">日期：</td>
                	<td class="form-td-content" colspan="3" style="width:260px">		
						<input name="rq" class="easyui-datebox" data-options="prompt:''" style="width:100%;"></td>
					</td>
                </tr>
                <tr>
                	<td class="form-td-label">备注：</td>
                	<td class="form-td-content" colspan="3" style="width:260px">
                    	<textarea name="remarks"  class="easyui-textbox"  data-options="required:false,prompt:'',multiline:true" style="width:100%;height:50px"></textarea>
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