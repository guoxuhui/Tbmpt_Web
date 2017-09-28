<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>基础底图管理</title>
<script type="text/javascript" src="${staticPath }/static/js/sgkshgl/jcdtgl/list.js?v=20170823200821" charset="utf-8"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
<div data-options="region:'west',split:true" title="项目列表" style="width:230px;">
		<ul id="proTree"  style="margin: 10px 10px 10px 10px;width: 95%;"></ul>
</div>
<!-- 列表信息 -->
<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
<div class="easyui-layout" fit="true">
	<div data-options="region:'center',border:false" style="width:100%;height:100%;">
		<table id="dataGrid" title="列表" data-options="fit:true,border:false"></table>
	</div>
	<div id="toolbar" style="display: none;">
		<a onclick="reloadFun();" href="javascript:void(0);" id="reload_button_sys_emp" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<a onclick="clearSelections();" href="javascript:void(0);" id="clear_button_sys_emp" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    	<shiro:hasPermission name="/sgkshgl/jcdtgl/add">
    	   <a onclick="addFun();" href="javascript:void(0);" id="add_button_sys_emp" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/sgkshgl/jcdtgl/edit">
    	   <a onclick="editFun();" href="javascript:void(0);" id="edit_button_sys_emp" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/sgkshgl/jcdtgl/del">
    	   <a onclick="deleteFun();" href="javascript:void(0);" id="del_button_sys_emp" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/sgkshgl/jcdtgl/list">
    	   <a onclick="showFun();" href="javascript:void(0);" id="show_button_sys_emp" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-book_previous'">查看</a>
    	</shiro:hasPermission>    	
    	<shiro:hasPermission name="/sgkshgl/jcdtgl/ExpExcel">
    	    <a onclick="expXls();" href="javascript:void(0);" id="expXls_button_sys_emp" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
    	</shiro:hasPermission> 
    	<shiro:hasPermission name="/sgkshgl/jcdtgl/ExpPdf">
    	   <a onclick="expPdf();" href="javascript:void(0);" id="expPdf_button_sys_emp" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_white_acrobat'">导出Pdf</a>       
  	    </shiro:hasPermission> 
    	<a onclick="cleanFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" >重置</a>
    </div>
</div>
</div>

	<!-- 弹出框－新增 -->
	<div id="addDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:410px;height:280px;padding:5px 5px;" >
        <form id="addForm" method="post" data-options="novalidate:true">
            <table class="grid" >
                 
                <tr>
                   <td class="form-td-label" style="width: 80px"><span style="color: red;">*</span>关联类型：</td>
                    <td class="form-td-content" class="form-td-content" colspan="3" style="width:280px">
                        <input id="add_mapType" name="mapType" class="easyui-combobox" data-options="required:true,prompt:'请选择关联类型'" style="width:100%">
                    </td>
                </tr>
                <tr>
                	<td class="form-td-label" style="width:80px"><span style="color: red;">*</span>关联路径:</td>
                    <td class="form-td-content" colspan="3" style="width:280px">
                    	<input id="add_mapPath" name="mapPath" class="easyui-textbox" data-options="required:true,prompt:'请输入关联路径'" style="width:100%;">
                    </td>
                </tr>
               <tr>
                	<td class="form-td-label" style="width:80px"><span style="color: red;">*</span>所属工程:</td>
                	<td class="form-td-content" colspan="3" style="width:280px">
                    	<select id="add_proId" name="proId" class="easyui-combobox"  data-options="required:true,prompt:'请选择所属工程'" style="width:100%;"></select>
                	</td>
            	</tr>
                <tr>
                	<td class="form-td-label" style="width:80px"><span style="color: red;">*</span>项目区间:</td>
                    <td class="form-td-content" colspan="3" style="width:280px">
                    	<select id="add_sectionId"  name="sectionId" class="easyui-combobox" data-options="required:true,prompt:'请选择项目区间'" style="width: 100%;"></select>
                    </td>
                </tr>
            </table>
            <div id="add_line_table">
            <div id="add_line_tr">
            	<table class="grid">
	               	<tr>
	               	   <td class="form-td-label" style="width:80px"><span style="color: red;">*</span>区间线路:</td>
	                   <td class="form-td-content" colspan="3" style="width:280px">
	                   	   <select id="add_lineId" name="lineId"  class="easyui-combobox" data-options="required:true,prompt:'请选择区间线路'" style="width: 100%;"></select>
	                   </td>
	               </tr>
                </table>
           </div>
           </div>
        </form>
	</div>
	<div id="add-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="addAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#addDialog').dialog('close')">关闭</a>
	</div>
	
	<!-- 弹出框－编辑 -->
	<div id="editDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#edit-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:420px;height:280px;padding:5px 5px;" >
        <form id="editForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden" >
        	<table class="grid">
        	    
                 <tr>
                   <td class="form-td-label" style="width: 80px"><span style="color: red;">*</span>关联类型：</td>
                    <td class="form-td-content" class="form-td-content" colspan="3" style="width:280px">
                        <input id="edit_mapType" name="mapType" class="easyui-combobox" data-options="required:true,prompt:'请选择关联类型'" style="width:100%">
                    </td>
                </tr>
                <tr>
                	<td class="form-td-label" style="width:80px"><span style="color: red;">*</span>关联路径:</td>
                    <td class="form-td-content" colspan="3" style="width:280px">
                    	<input id="edit_mapPath" name="mapPath" class="easyui-textbox" data-options="required:true,prompt:'请输入关联路径'" style="width:100%;">
                    </td>
                </tr>
               <tr>
                	<td class="form-td-label" style="width:80px"><span style="color: red;">*</span>所属工程:</td>
                	<td class="form-td-content" colspan="3" style="width:280px">
                    	<select id="edit_proId" name="proId" class="easyui-combobox" data-options="required:true,prompt:'请输入所属工程'" style="width:100%;"></select>
                	</td>
            	</tr>
                <tr>
                	<td class="form-td-label" style="width:80px"><span style="color: red;">*</span>项目区间:</td>
                    <td class="form-td-content" colspan="3" style="width:280px">
                    	<select id="edit_sectionId" name="sectionId" class="easyui-combobox" data-options="required:true,prompt:'请选择项目区间'" style="width: 100%;"></select>
                    </td>
                </tr>
               
            </table>
            <div id="edit_line_table">
            <div id="edit_line_tr">
            	<table class="grid">
	               	<tr >
	               	   <td class="form-td-label" style="width:80px"><span style="color: red;">*</span>区间线路:</td>
	                   <td class="form-td-content" colspan="3" style="width:280px">
	                   	   <select id="edit_lineId" name="lineId"  class="easyui-combobox" data-options="required:true,prompt:'请选择区间线路'" style="width: 100%;"></select>
	                   </td>
	               </tr>
                </table>
           </div>
           </div>
        </form>
	</div>
	<div id="edit-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="editAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#editDialog').dialog('close')">关闭</a>
	</div>
	
	<!-- 弹出框－查看 -->
	<div id="showDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#show-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:420px;height:280px;padding:5px 5px;" >
        <form id="showForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden" >
            <table class="grid">
        	    
                 <tr>
                   <td class="form-td-label" style="width: 80px"><span style="color: red;">*</span>关联类型：</td>
                    <td class="form-td-content" class="form-td-content" colspan="3" style="width:280px">
                        <input id="show_mapType" name="mapType" readonly="readonly" class="easyui-combobox" data-options="required:true,prompt:''" style="width:100%">
                    </td>
                </tr>
                <tr>
                	<td class="form-td-label" style="width:80px"><span style="color: red;">*</span>关联路径:</td>
                    <td class="form-td-content" colspan="3" style="width:280px">
                    	<input id="show_mapPath" name="mapPath" class="easyui-textbox"readonly="readonly" data-options="required:true,prompt:''" style="width:100%;">
                    </td>
                </tr>
               <tr>
                	<td class="form-td-label" style="width:80px"><span style="color: red;">*</span>所属工程:</td>
                	<td class="form-td-content" colspan="3" style="width:280px">
                    	<select id="show_proId" name="proId" class="easyui-combobox"readonly="readonly" data-options="required:true,prompt:''" style="width:100%;"></select>
                	</td>
            	</tr>
                <tr>
                	<td class="form-td-label" style="width:80px"><span style="color: red;">*</span>项目区间:</td>
                    <td class="form-td-content" colspan="3" style="width:280px">
                    	<select id="show_sectionId" name="sectionId" class="easyui-combobox"readonly="readonly" data-options="required:true,prompt:''" style="width: 100%;"></select>
                    </td>
                </tr>
               
            </table>
            <div id="show_line_table">
            <div id="show_line_tr">
            	<table class="grid">
	               	<tr >
	               	   <td class="form-td-label" style="width:80px"><span style="color: red;">*</span>区间线路:</td>
	                   <td class="form-td-content" colspan="3" style="width:280px">
	                   	   <select id="show_lineId" name="lineId"  class="easyui-combobox" readonly="readonly" data-options="prompt:''" style="width: 100%;"></select>
	                   </td>
	               </tr>
                </table>
           </div>
           </div>
        </form>
	</div>
	<div id="show-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#showDialog').dialog('close')">关闭</a>
	</div>
</body>
</html>