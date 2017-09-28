<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>巡检信息应用</title>
<script type="text/javascript" src="${staticPath }/static/js/aqxj/aqxjXjxxyy.js" charset="utf-8"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
<div data-options="region:'west',split:true" title="项目列表" style="width:230px;">
	<span id="curr_projec_Name_aqxjXjxxyy" style="display:none;">${projectName}</span>
	<form id="showProForm">		
			<table class="grid">
				<tr>
				<td class="form-td-content" style="width: 260px" >
				<input id="search_proName" name="projectName" class="easyui-combobox" data-options="prompt:'请选择工程名称'" style="width: 90%;"/>
				<input id="search_fenleiMc" name="typeName"  type="hidden"/></td>
				</tr>
				<!-- <tr>
				<td class="form-td-content" style="width: 260px" >
				<input id="search_fenleiMcid" name="typeId" class="easyui-combobox" data-options="prompt:'请选择分类'" style="width: 80%;"/>
				<input id="search_fenleiMc" name="typeName" type="hidden"/></td>
				</tr> -->
			</table>
	<ul id="fengleiMc_Tree"  style="margin: 10px 10px 10px 10px;width: 95%;"></ul>
	</form>
</div>
<!-- 查询表单 -->
<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
<div class="easyui-layout" fit="true">
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:70px;border: false;overflow: hidden;" data-options="collapsible:true">
		<form id="search_cxForm">
			<table class="grid">
				<tr>
					<td class="form-td-label" style="width: 80px" >巡检点名称：</td>
					<td class="form-td-content" style="width: 160px" >
						<input id="search_mc_aqxjXjxxyy" name="mingCheng" class="easyui-textbox" data-options="" style="width: 100%;"/>
					</td>
					<td style="text-align: right;" colspan="2">
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="search_xxyyFun();">查询</a> 
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="clean_xxyyFun();">重置</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false" style="width:100%;height:100%;">
		<table id="aqxjXjxxyy_dataGrid" title="列表" data-options="fit:true,border:false"></table>
	</div>
	<div id="toolbar" style="display: none;">
		<a onclick="copyProject();" href="javascript:void(0);" id="copy_button_project_aqxjXjxxyy" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">复制当前工程</a>
    	<a onclick="copyProjectFl();" href="javascript:void(0);" id="add_button_sgkshgl_jjgjgl" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">复制当前工程的分类</a>
    	<a onclick="copyProjectXjd();" href="javascript:void(0);" id="clear_button_sgkshgl_jjgjgl" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">复制巡检点</a>
    	      
  	</div>
</div>
</div> 
	<!-- 弹出框－复制工程 -->
	<div id="copyProject_showDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#show-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:545px;height:215px;padding:5px 5px;" >
		 <table  class="grid" style="width:520px">
			<tr>
				<td  class="form-td-label" >复制内容：</td>
			    <td class="form-td-content" align="center"><span><b id="projec_tName_aqxjXjxxyy"></b></span>
			      <input type="hidden" name="OrgzName" id='projectName_aqxjXjxxyy' style='width: 150px'>
			    </td>
			</tr>
			  <tr>
			    <td  class="form-td-label" ></td>
			    <td class="form-td-content" >
			      所有巡检内容（包含巡检点分类、巡检点、巡检内容）
			    </td>
			  </tr>
			  <tr>
			  	<td colspan='2' align="center"><h3>复制<span style="color: red;" id="projec_Name_aqxjXjxxyy"></span>  所有巡检内容至当前  <span style="color: red;" id="projec_tName_aqxjXjxxyy">${projectName}</span></h3></td>
			  </tr>
			 </table>
	</div>
	<div id="show-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="copyproject_save()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#copyProject_showDialog').dialog('close')">关闭</a>
	</div>
	
	
	<!--弹出框－复制分类  -->
	<div id="copyFl_showDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#show-dialog-buttons1',closed:true,cache: false,modal: true"
		 style="width:545px;height:215px;padding:5px 5px;" >
		 <table  class="grid" style="width:520px">
			<tr>
				<td  class="form-td-label" >请选择复制分类：</td>
				<td class="form-td-content" >
				<input class="easyui-combobox"   name='typeId' id='copy_getTypeName_aqxjXjxxyy'  data-options="validType:'length[1,40]',required:true"  style='width: 150px'></td>
			</tr>
			  <tr>
				<td  class="form-td-label" >复制到本项目分类：</td>
				<td class="form-td-content" >
				<input class="easyui-combobox"   name='typeId' id='copy_inTypeName_aqxjXjxxyy'  data-options="validType:'length[1,40]',required:true"  style='width: 150px'></td>
			  </tr>
			  <tr>
			  	<td colspan='2' align="center"><input id="checkBox_aqxjXjxxyy" name="mingCheng" type="checkbox"/><span>是否复制分类下的巡检点及其巡检内容</span>
			  	<!-- <input type="text" name="checkButton" id="isCheck_button"/></td> -->
			  </tr>
			 </table>
	</div>
	<div id="show-dialog-buttons1">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="save_copyFl()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#copyFl_showDialog').dialog('close')">关闭</a>
	</div>
	
	
	<!--弹出框－复制巡检点  -->
	<div id="copyXjd_showDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#show-dialog-buttons1',closed:true,cache: false,modal: true"
		 style="width:545px;height:215px;padding:5px 5px;" >
		 <table  class="grid" style="width:520px">
			  <tr>
				<td  class="form-td-label" >复制到本项目分类：</td>
				<td class="form-td-content" style='height:100px'>
				<input class="easyui-combobox" name='typeId' id='copy_inId_aqxjXjxxyy' data-options="validType:'length[1,40]',required:true" style='width: 150px'></td>
			  </tr>
			 </table>
	</div>
	<div id="show-dialog-buttons1">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="save_copyXjd()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#copyXjd_showDialog').dialog('close')">关闭</a>
	</div>
	
</body>
</html>