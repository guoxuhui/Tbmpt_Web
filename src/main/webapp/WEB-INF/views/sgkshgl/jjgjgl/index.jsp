<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>掘进轨迹管理</title>
<script type="text/javascript" src="${staticPath }/static/js/sgkshgl/jjgjgl/list.js?v=20170516180022" charset="utf-8"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
<div data-options="region:'west',split:true" title="项目列表" style="width:230px;">
		<ul id="orgzTree"  style="margin: 10px 10px 10px 10px;width: 95%;"></ul>
</div>
<!-- 查询表单 -->
<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
<div class="easyui-layout" fit="true">
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
					<td class="form-td-label" style="width: 80px" >线路名称：</td>
					<td class="form-td-content" style="width: 160px" >
						<input id="search_lineName" name="lineName" class="easyui-textbox" data-options="prompt:'请输入线路名称'" style="width: 100%;"/>
					</td>
				</tr>
				<tr>
					<td class="form-td-label">起始时间：</td>
					<td class="form-td-content">
						<input id="startTime" name="startTime" class="easyui-datetimebox" data-options="prompt:'起始创建时间'" style="width:100%;"></td>
					<td class="form-td-label">结束时间：</td>
					<td class="form-td-content">
						<input id="endTime" name="endTime" class="easyui-datetimebox" data-options="prompt:'截止创建时间'" style="width:100%;"></td>
					<td style="text-align: right;" colspan="2">
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a> 
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleanFun();">重置</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center',border:false" style="width:100%;height:100%;">
		<table id="dataGrid" title="列表" data-options="fit:true,border:false"></table>
	</div>
	<div id="toolbar" style="display: none;">
		<a onclick="reloadFun();" href="javascript:void(0);" id="reload_button_sgkshgl_jjgjgl" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<shiro:hasPermission name="/sgkshgl/jjgjgl/add">
    	   <a onclick="addFun();" href="javascript:void(0);" id="add_button_sgkshgl_jjgjgl" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">绘制轨迹</a>
    	</shiro:hasPermission>    	
    	<a onclick="clearSelections();" href="javascript:void(0);" id="clear_button_sgkshgl_jjgjgl" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    	      
  	</div>
</div>
</div> 
	<!-- 弹出框－查看 -->
	<div id="showDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#show-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:600px;height:450px;padding:5px 5px;" >
        <form id="showForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden">
			<table class="grid">
				
				<tr>
					<td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>所属工程：</td>
                    <td class="form-td-content" colspan="3" style="width: 400px">
                    	<input name="proName" class="easyui-textbox" data-options="prompt:''" style="width:100%">
                	</td>
                </tr>
                <tr>
                	<td class="form-td-label"><span style="color: red;">*</span>线路名称：</td>
                	<td class="form-td-content">
                    	<input name="lineName" class="easyui-textbox" data-options="required:true,prompt:''" style="width:100%">
                   	</td>
					<td class="form-td-label"><span style="color: red;">*</span>所属区间：</td>
                	<td class="form-td-content">
                    	<input name="sectionName" class="easyui-textbox" data-options="prompt:''" style="width:100%">
                	</td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>起始里程：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input name="startMileage" class="easyui-numberbox" data-options="required:true,prompt:''" style="width:100%">
                    </td>
					<td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>终止里程：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input name="endMileage" class="easyui-numberbox" data-options="required:true,prompt:''" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>管环数量：</td>
                    <td class="form-td-content">
                    	<input name="ringCount" class="easyui-numberbox" data-options="required:true,prompt:''" style="width:100%">
                    </td>
					<td class="form-td-label">里程前辍标识：</td>
                    <td class="form-td-content">
                    	<input name="mileagePrefix" class="easyui-textbox" data-options="prompt:''" style="width:100%">
                    </td>
                </tr>
                
                <tr>
					<td class="form-td-label">预计始发日期：</td>
					<td class="form-td-content">
						<input name="yjsfrq"  class="easyui-textbox" data-options="prompt:''" style="width:100%;">
					</td>
					<td class="form-td-label">预计出洞日期：</td>
					<td class="form-td-content">
						<input name="yjcdrq"  class="easyui-textbox" data-options="prompt:''" style="width:100%;">
					</td>
                </tr>
                <tr>
					<td class="form-td-label">实际始发日期：</td>
					<td class="form-td-content">
						<input  name="sjsfrq"  class="easyui-textbox" data-options="prompt:''" style="width:100%;">
					</td>
					<td class="form-td-label">实际出洞日期：</td>
					<td class="form-td-content">
						<input name="sjcdrq"  class="easyui-textbox" data-options="prompt:''" style="width:100%;">
					</td>
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>隧道长度(米)：</td>
                    <td class="form-td-content">
                    	<input name="tunnellength" type="text" placeholder="隧道长度(米)" class="easyui-numberbox" data-options="required:true,prompt:''" style="width:100%">
                    </td>
					<td class="form-td-label">推进总工期(天)：</td>
                    <td class="form-td-content">
                    	<input name="tunneltime" class="easyui-numberbox" data-options="prompt:''" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>起始环号：</td>
                    <td class="form-td-content">
                    	<input name="startRingnum" class="easyui-numberbox" data-options="required:true,prompt:''" style="width:100%">
                    </td>
					<td class="form-td-label">起始位置：</td>
                    <td class="form-td-content">
                    	<input name="startPosition" class="easyui-textbox" data-options="prompt:''" style="width:100%">
                    </td>
                </tr>
                <tr>
                	<td class="form-td-label">施工盾构机：</td>
                	<td class="form-td-content" >
                	     <input name="tbmName" class="easyui-textbox" data-options="prompt:''" style="width:100%">
                    </td>
                	<td class="form-td-label">盾构机司机：</td>
                    <td class="form-td-content">
                    	<input name="dgChauffeur" class="easyui-textbox" data-options="prompt:''" style="width:100%">
                    </td>
                </tr>
        	</table>
        </form>
	</div>
	<div id="show-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#showDialog').dialog('close')">关闭</a>
	</div>
		<!-- 地图选择器 -->
	<div id="openDiv" class="easyui-window" closed="true" modal="true" title="位置选取" style="width:800px;height:500px;">
   		<iframe id='openMapIframe' src="" frameborder="0" style="width:100%;height:98%;"></iframe>
	</div>
</body>
</html>