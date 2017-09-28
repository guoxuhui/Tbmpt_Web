<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>盾构掘进点位对比信息</title>
<script type="text/javascript" src="${staticPath }/static/js/dgjjdw/tbmdw/dgjjPlcTbmdw.js" charset="utf-8"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<!-- 查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:100px;border: false;overflow: hidden;" data-options="collapsible:true">
		<form id="searchForm">
			<table class="grid" style="border: 0px">
				<tr>
					<td class="form-td-label" style="width: 80px">盾构机编号：</td>
					<td class="form-td-content" style="width: 180px">
						<input name="bztbmCode" class="easyui-textbox"  data-options="prompt:'请输入盾构机编号'" style="width: 100%" />
					</td>
					<td class="form-td-label" style="width: 80px">盾构机名称：</td>
					<td class="form-td-content" style="width: 180px">
						<input name="bztbmname" class="easyui-textbox"  data-options="prompt:'请输入盾构机名称'" style="width: 100%"/>
					</td>
					<td style=""></td>
				</tr>
				<tr>
					<td class="form-td-label">开始时间：</td>
					<td class="form-td-content">
						<input id="startTime" name="startTime" class="easyui-datebox" data-options="prompt:'起始监测时间'" style="width:100%;"></td>
					<td class="form-td-label">结束时间：</td>
					<td class="form-td-content">
						<input id="endTime" name="endTime" class="easyui-datebox" data-options="prompt:'起始监测时间'" style="width:100%;"></td>
					<td style="text-align: left;">
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
    	<a onclick="reloadFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<a onclick="clearSelections();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    	<a onclick="openbiduiFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">比对</a>    	    	    	   	
   	</div>
    <!-- 弹出框-输入盾构机 -->
    <div id="tbmDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#tbm-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:400px;height:200px;padding:5px 5px;">
		 <form id="tbmForm" method="post" data-options="novalidate:true">
		 	<table class="grid" style="width:100%">
		 		<tr>
		 			<td class="form-td-label" style="width:150px"><span style="color: red;">*</span>当前盾构机编号：</td>
                	<td class="form-td-content">
                    	<input id="bz_tbm" name="bzTbm" class="easyui-textbox" data-options="editable:true,required:true,prompt:'请输入标准盾构机'" style="width:100%">
                	</td>
		 		</tr>
		 		<tr>
		 			<td class="form-td-label" style="width:150px"><span style="color: red;">*</span>工业库盾构机名称：</td>
                	<td class="form-td-content">
                    	<input id="ss_tbm" name="ssTbm" class="easyui-textbox" data-options="editable:true,required:true,prompt:'请输入实时盾构机'" style="width:100%">
                	</td>
		 		</tr>
		 	</table>
		 </form>
	</div>	 
    <div id="tbm-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="tbmAjax()">确定</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#tbmDialog').dialog('close')">关闭</a>
	</div>
	<div id="realDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#real-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:400px;height:150px;padding:5px 5px;">
		 <form id="realForm" method="post" data-options="novalidate:true">
		 	<table class="grid">
		 		
		 		<tr>
		 			<td class="form-td-label"><span style="color: red;">*</span>PLC点位名称：</td>
                	<td class="form-td-content">
                    	<input id="ss_tbmName" name="ssTbmName" class="easyui-textbox" data-options="editable:true,required:true,prompt:'请输入实时盾构机名称'" style="width:100%">
                	</td>
		 		</tr>
		 	</table>
		 </form>
	</div>
	<div id="real-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="realAjax()">确定</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#realDialog').dialog('close')">关闭</a>
	</div>
	
		<!-- 点位对比信息数据表格-->
   <form id="addForm_tbmdw" method="post" data-options="novalidate:true">
   		<input type='hidden' id='details' name='details'/>
		<div id="addDialog_tbmdw" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-tbmdw-buttons',closed:true,cache: false,modal:true"	style="width:1000px;height:600px;padding:10px 10px;" >
				<table class="grid" width='650px' border='0' cellspacing='0' style='padding:5px'>
				<tr>
		 			<td class="form-td-label">当前盾构机名称：</td>
                	<td class="form-td-content" colspan="3">
				      	<span id="add_bz_tbm_name" name="bztbmName"></span>
				    </td>
				    <td class="form-td-label">当前盾构机ID：</td>
                	<td class="form-td-content" colspan="3">
				      	<span id="add_bz_tbm_id" name="bztbmId"></span>
				    </td>
		 		</tr>
		 		<tr>
		 			<td class="form-td-label">当前盾构机编号：</td>
                	<td class="form-td-content" colspan="3">
				      	<span id="add_bz_tbm_code" name="bztbmCode"></span>
				    </td>
				    <td class="form-td-label">工业库盾构机名称：</td>
                	<td class="form-td-content" colspan="3">
				      	<span id="add_ss_tbm_name" name="sstbmName"></span>
				    </td>
		 		</tr>
				</table>
			<!-- 【线路中心线信息管理】结果列表 -->
				<div class="easyui-panel" data-options="border:false" style="width:98%;height:85%;">
					<table id="dataGrid_tbmdw" title="点位对比信息管理" data-options="fit:true,border:false"></table>
				</div>
		</div>
   </form>
	<div id="add-tbmdw-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="saveTbmdw()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#addDialog_tbmdw').dialog('close')">关闭</a>
	</div>
</body>
</html>