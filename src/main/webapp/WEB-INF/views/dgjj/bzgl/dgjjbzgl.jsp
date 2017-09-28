<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>班组管理</title>
<script type="text/javascript" src="${staticPath }/static/js/dgjj/dgjjBzgl.js" charset="utf-8"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<!-- 查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:100px;border: false;overflow: hidden;" data-options="collapsible:true">
		<form id="searchForm">
			<table class="grid">
				<tr>
					<td class="form-td-label" style="width: 80px">工程名称：</td>
					<td class="form-td-content" style="width: 160px">
						<select id="search_proId" name="proId" class="easyui-combobox" data-options="prompt:'请输入工程名称'" style="width: 100%;"></select>
					</td>
					<td class="form-td-label" style="width: 80px">区间名称：</td>
					<td class="form-td-content" style="width: 160px">
						<select id="search_sectionId" name="sectionId" class="easyui-combobox" data-options="prompt:'请输入区间名称'" style="width: 100%;"></select>
					</td>
					
				</tr>
				<tr>
					<td class="form-td-label" style="width: 80px" >线路名称：</td>
					<td class="form-td-content" style="width: 160px" >
						<input id="search_lineName" name="lineName" class="easyui-textbox" data-options="prompt:'请输入线路名称'" style="width: 100%;"/>
					</td>
					<td class="form-td-label" style="width: 80px" >班组名称：</td>
					<td class="form-td-content" style="width: 160px" >
						<input id="search_bzname" name="bzname" class="easyui-textbox" data-options="prompt:'请输入班组名称'" style="width: 100%;"/>
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
	<!-- 弹出框－新增 -->
	<div id="addDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:600px;height:200px;padding:5px 5px;" >
        <form id="addForm" method="post" data-options="novalidate:true">
			<table class="grid">
                
                <tr>
                	<td class="form-td-label"><span style="color: red;">*</span>所属工程：</td>
                	<td class="form-td-content">
                    	<select id="add_proId" name="proId" class="easyui-combobox" data-options="editable:false,required:true,prompt:'请输入所属工程'" style="width:100%"></select>
                	</td>
					<td class="form-td-label"><span style="color: red;">*</span>所属区间：</td>
                	<td class="form-td-content">
                    	<select id="add_sectionId" name="sectionId" class="easyui-combobox" data-options="editable:false,required:true,prompt:'请输入所属区间'" style="width:100%"></select>
                	</td>
                </tr>
                <tr>
                	<td class="form-td-label"><span style="color: red;">*</span>所属线路：</td>
                	<td class="form-td-content">
                    	<select id="add_lineId" name="lineId" class="easyui-combobox" data-options="editable:false,required:true,prompt:'请输入所属线路'" style="width:100%"></select>
                	</td>
                	<td class="form-td-label" style="width: 80px"><span style="color: red;">*</span>班组名称：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="add_bzname" name="bzname" class="easyui-textbox" data-options="editable:true,required:true,prompt:'请输入班组名称'" style="width:100%">                    	
                    </td>
                </tr>
                <tr>
                	<td class="form-td-label"><span style="color: red;">*</span>上班时间：</td>
                	<td class="form-td-content">
                    	<select id="start" name="startTime" class="easyui-timespinner" data-options="editable:false,required:true,prompt:'',showSeconds:true" style="width:100%"></select>
                	</td>
					<td class="form-td-label"><span style="color: red;">*</span>下班时间：</td>
                	<td class="form-td-content">
                    	<select id="end" name="endTime" class="easyui-timespinner" data-options="editable:false,required:true,prompt:'',showSeconds:true" style="width:100%"></select>
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
		 style="width:600px;height:200px;padding:5px 5px;" >
        <form id="editForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden">
			<table class="grid">
                
                <tr>
                	<td class="form-td-label"><span style="color: red;">*</span>所属工程：</td>
                	<td class="form-td-content">
                    	<select id="edit_proId" name="proId" class="easyui-combobox" data-options="editable:false,required:true,prompt:'请输入所属工程'" style="width:100%"></select>
                	</td>
					<td class="form-td-label"><span style="color: red;">*</span>所属区间：</td>
                	<td class="form-td-content">
                    	<select id="edit_sectionId" name="sectionId" class="easyui-combobox" data-options="editable:false,required:true,prompt:'请输入所属区间'" style="width:100%"></select>
                	</td>
                </tr>
                <tr>
                	<td class="form-td-label"><span style="color: red;">*</span>所属线路：</td>
                	<td class="form-td-content">
                    	<select id="edit_lineId" name="lineId" class="easyui-combobox" data-options="editable:false,required:true,prompt:'请输入所属线路'" style="width:100%"></select>
                	</td>
                	<td class="form-td-label" style="width: 80px"><span style="color: red;">*</span>班组名称：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="edit_bzname" name="bzname" class="easyui-textbox" data-options="editable:true,required:true,prompt:'请输入班组名称'" style="width:100%">                    	
                    </td>
                </tr>
                <tr>
                	<td class="form-td-label"><span style="color: red;">*</span>上班时间：</td>
                	<td class="form-td-content">
                    	<select id="start" name="startTime" class="easyui-timespinner" data-options="editable:false,required:true,prompt:'',showSeconds:true" style="width:100%"></select>
                	</td>
					<td class="form-td-label"><span style="color: red;">*</span>下班时间：</td>
                	<td class="form-td-content">
                    	<select id="end" name="endTime" class="easyui-timespinner" data-options="editable:false,required:true,prompt:'',showSeconds:true" style="width:100%"></select>
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
		 style="width:600px;height:200px;padding:5px 5px;" >
        <form id="showForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden">
			<table class="grid">
                
                <tr>
                	<td class="form-td-label"><span style="color: red;">*</span>所属工程：</td>
                	<td class="form-td-content">
                    	<select id="show_proId" name="proId" class="easyui-combobox" data-options="editable:false,required:true,prompt:'请输入所属工程'" style="width:100%"></select>
                	</td>
					<td class="form-td-label"><span style="color: red;">*</span>所属区间：</td>
                	<td class="form-td-content">
                    	<select id="show_sectionId" name="sectionId" class="easyui-combobox" data-options="editable:false,required:true,prompt:'请输入所属区间'" style="width:100%"></select>
                	</td>
                </tr>
                <tr>
                	<td class="form-td-label"><span style="color: red;">*</span>所属线路：</td>
                	<td class="form-td-content">
                    	<select id="show_lineId" name="lineId" class="easyui-combobox" data-options="editable:false,required:true,prompt:'请输入所属线路'" style="width:100%"></select>
                	</td>
                	<td class="form-td-label" style="width: 80px"><span style="color: red;">*</span>班组名称：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="show_bzname" name="bzname" class="easyui-textbox" data-options="editable:true,required:true,prompt:'请输入班组名称'" style="width:100%">                    	
                    </td>
                </tr>
                <tr>
                	<td class="form-td-label"><span style="color: red;">*</span>上班时间：</td>
                	<td class="form-td-content">
                    	<select id="start" name="startTime" class="easyui-timespinner" data-options="editable:false,required:true,prompt:'',showSeconds:true" style="width:100%"></select>
                	</td>
					<td class="form-td-label"><span style="color: red;">*</span>下班时间：</td>
                	<td class="form-td-content">
                    	<select id="end" name="endTime" class="easyui-timespinner" data-options="editable:false,required:true,prompt:'',showSeconds:true" style="width:100%"></select>
                	</td>
                </tr>
        	</table>
        </form>
	</div>
	<div id="show-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#showDialog').dialog('close')">关闭</a>
	</div>
	<!-- 人员增加页面 -->

   <form id="addForm_Emp" method="post" data-options="novalidate:true">
   		<input type="hidden" id="details" name="details">
   		<input type="hidden" id="addForm_gcBh" name="gcBh">
   		<input type="hidden" id="addForm_qlBh" name="qlBh">
   		<input type="hidden" id="addForm_xlBh" name="xlBh">
   		<input type="hidden" id="addForm_id" name="id">
   		
		<div id="addEmp" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal:true"	style="width:1000px;height:450px;padding:10px 10px;" >
				<table class="grid" width='650px' border='0' cellspacing='0' style='padding:5px'>
					<tr>
				    <td  class="form-td-label" >项目名称：</td>
				    <td class="form-td-content">
				      	<span id="add_projectName"></span>
				    </td>
				    <td  class="form-td-label" >班次：</td>
				    <td class="form-td-content">
				      	<span id="add_bzName"></span>
				    </td>
				  </tr>
				  <tr>
				    <td  class="form-td-label" >区间名称：</td>
				    <td class="form-td-content" >
				      	<span id="add_qujianName"></span>
				    </td>
				    <td  class="form-td-label" >线路名称：</td>
				    <td class="form-td-content" >
				      	<span id="add_lineName"></span>
				    </td>
				  </tr>
				</table>
			<!--人员管理结果列表 -->
				<div class="easyui-panel" data-options="border:false" style="width:98%;height:80%;">
					<table id="dataGrid_emp" title="人员信息管理" data-options="fit:true,border:false"></table>
				</div>
		</div>
   </form>
   <div id="add-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="addSave()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#addEmp').dialog('close')">关闭</a>
   </div>
   
   
</body>
</html>