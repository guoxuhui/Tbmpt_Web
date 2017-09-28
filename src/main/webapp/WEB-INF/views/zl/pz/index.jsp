<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>管片质量配置管理</title>
<script type="text/javascript" src="${staticPath }/static/js/zl/pz/list.js?t=20170829171844" charset="utf-8"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<!-- 查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:true"  style="width:100%;height:80px;overflow: hidden;" data-options="collapsible:true">
		<form id="searchForm">
		<table class="grid" style="border: 0px">
				<tr>
					<td class="form-td-label" style="width: 85px">项目部名称：</td> 
					<td class="form-td-content" style="width: 180px">
						<select id="search_proId" name="proName" class="easyui-combobox" data-options="prompt:'请输入工程名称'" style="width: 100%;"></select>
					</td>
					<td colspan="4" style="text-align: center;">
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
		<a onclick="reloadFun();" href="javascript:void(0);" id="reload_button_zl_pz" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<a onclick="clearSelections();" href="javascript:void(0);" id="clear_button_sys_emp" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    	<shiro:hasPermission name="/zl/pz/add">
    	   <a onclick="addFun();" href="javascript:void(0);" id="add_button_zl_pz" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/zl/pz/edit">
    	   <a onclick="editFun();" href="javascript:void(0);" id="edit_button_zl_pz" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/zl/pz/del">
    	   <a onclick="deleteFun();" href="javascript:void(0);" id="del_button_zl_pz" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	</shiro:hasPermission>
    	 
  	</div> 
	<!-- 弹出框－新增 -->
	<div id="addDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:500px;height:280px;padding:5px 5px;" >
        <form id="addForm" method="post" data-options="novalidate:true">
            <table class="grid">
            	<tr>
					<td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>所属工程：</td>
                    <td class="form-td-content" colspan="3" style="width: 360px">
                    	<select id="add_proId" name="proName" class="easyui-combobox" data-options="editable:false,required:true,prompt:'请输入所属工程'" style="width:100%"></select>
                   	</td>
                </tr>
                <tr>   
                    <td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>所属区间：</td>
                		<td class="form-td-content">
                    	<select id="add_sectionId" name="section" class="easyui-combobox" data-options="editable:false,required:true,prompt:'请输入所属区间'" style="width:100%"></select>
                		</td>
                </tr>
                <tr>  	
                		<td class="form-td-label"><span style="color: red;">*</span>线路名称：</td>  
                		<td class="form-td-content">
                    	<select id="add_lineId"  name="line" class="easyui-combobox" data-options="editable:false,required:true,prompt:'请输入线路名称'" style="width:100%"></select>
                   	</td> 
					
                </tr>
                <tr>
                		<td class="form-td-label" style="width:100px">上报时间(天):</td>
                    <td class="form-td-content" colspan="3" style="width:280px"> 
                    	<input name="setUpDay" class="easyui-textbox" data-options="required:false,prompt:'请设置提醒上报时间(天)'" validType="length[0,200]" style="width:100%;height:50px">
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
		 style="width:500px;height:280px;padding:5px 5px;" >
        <form id="editForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden">
           <table class="grid">
            	 	<tr>
					<td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>所属工程：</td>
                    <td class="form-td-content" style="width: 360px">
                    	<select id="edit_proId" name="proName" class="easyui-combobox" data-options="editable:false,required:true,prompt:'请输入所属工程'" style="width:100%"></select>
                   	</td>
                </tr>
                <tr>   
                    <td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>所属区间：</td>
                	<td class="form-td-content">
                    	<select id="edit_sectionId" name="section" class="easyui-combobox" data-options="editable:false,required:true,prompt:'请输入所属区间'" style="width:100%"></select>
                	</td>
                  </tr>
                <tr>  	
                	<td class="form-td-label"><span style="color: red;">*</span>线路名称：</td>  
                	<td class="form-td-content">
                    	<select id="edit_lineId"  name="line" class="easyui-combobox" data-options="editable:false,required:true,prompt:'请输入线路名称'" style="width:100%"></select>
                   	</td> 
					
                </tr>
                <tr>
                	<td class="form-td-label" style="width:100px">上报时间(天):</td>
                    <td class="form-td-content" colspan="3" > 
                    	<input name="setUpDay" class="easyui-textbox" data-options="required:false,prompt:'请设置提醒上报时间(天)'" validType="length[0,200]" style="width:100%;height:50px">
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
		style="width:580px;height:310px;padding:5px 5px;" >
        <form id="showForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden">
            <table class="grid">
            		 	<tr>
					<td class="form-td-label" style="width: 140px">所属工程：</td>
                    <td class="form-td-content" colspan="3" style="width: 400px">
                    	<select readonly="readonly"  id="edit_proId" name="proName1" class="easyui-combobox" data-options="editable:false,required:true,prompt:'请输入所属工程'" style="width:100%"></select>
                   	</td>
                </tr>
                <tr>   
                    <td class="form-td-label" style="width: 140px">所属区间：</td>
                	<td class="form-td-content">
                    	<select readonly="readonly"  id="edit_sectionId" name="sectionName" class="easyui-combobox" data-options="editable:false,required:true,prompt:'请输入所属区间'" style="width:100%"></select>
                	</td>
                  </tr>
                <tr>  	
                	<td class="form-td-label">线路名称：</td>  
                	<td class="form-td-content">
                    	<select readonly="readonly"  id="edit_lineId"  name="lineName" class="easyui-combobox" data-options="editable:false,required:true,prompt:'请输入线路名称'" style="width:100%"></select>
                   	</td> 
					
                </tr>
                <tr>
                	<td class="form-td-label" style="width:100px">设置上报时间(天):</td>
                    <td class="form-td-content" colspan="3" style="width:280px"> 
                    	<input readonly="readonly"  name="setUpDay" class="easyui-textbox" data-options="required:false,prompt:'请设置提醒上报时间(天)'" style="width:100%;height:50px">
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