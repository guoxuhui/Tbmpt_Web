<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>检测点管理</title>
 <script type="text/javascript" src="${staticPath}/static/js/dmcjjc/jcpoint/list.js?v=20170526151348" charset="utf-8"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
    <!-- 查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:140px;border: false;overflow: hidden;" data-options="collapsible:true">
		<form id="searchForm">
			<table class="grid" style="border: 0px">
				<tr>
					<td class="form-td-label" style="width: 80px">工程名称:</td>
					<td class="form-td-content" colspan="3" style="width: 480px">
						<select editable="false" id="gcbh" name="gcbh" class="easyui-combobox" data-options="prompt:'请输入工程名称'" style="width: 100%;"></select>
					</td>
					<td class="form-td-label" style="width: 80px">点位编号:</td>
					<td class="form-td-content" style="width: 200px">
						<input id="jcdbh" name="jcdbh" class="easyui-textbox" data-options="prompt:'请输入点位编号'" style="width: 100%;" /></td>
				</tr>
				<tr>
					<td class="form-td-label" style="width: 80px">区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间:</td>
					<td class="form-td-content" style="width: 200px">
						<select editable="false" id="qjbh" name="qjbh" class="easyui-combobox" data-options="prompt:'请输入区间'" style="width: 100%;"></select>
					</td>
					<td class="form-td-label" style="width: 80px">点位类型:</td>
					<td class="form-td-content" style="width: 200px">
						<select editable="false" id="sjType" name="sjType" class="easyui-combobox" data-options="prompt:'请输入点位类型'" style="width: 100%;"></select>
					<td style="text-align: left;" colspan="2">
					</td>
				</tr>
				<tr>
					<td class="form-td-label" style="width: 80px">线&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;路:</td>
					<td class="form-td-content" style="width: 200px">
						<select editable="false" id="xlbh" name="xlbh" class="easyui-combobox" data-options="prompt:'请输入线路'" style="width: 100%;"></select>
					</td>
					<td class="form-td-label" style="width: 80px">设计时间:</td>
					<td class="form-td-content" style="width: 200px">
						<select editable="false" id="sjTimeType" name="sjTimeType" class="easyui-combobox" data-options="prompt:'请输入设计时间'" style="width: 100%;"></select>
					
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
	
    <!-- 放一个隐藏表单用提交于下载文件 -->
    <form id="download" name="download" style="display:" method="post" action="${path}/dmcjjc/dmcjjcpoint/expJcPoint">
    	<!-- 存放选中的记录id -->
    	<input type="type" id="ids" name="ids"/>
    </form>
</body>
</html>