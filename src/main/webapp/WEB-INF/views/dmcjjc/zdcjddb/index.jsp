<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>无锡日常监测管理</title>
<script src="${staticPath }/static/js/dmcjjc/zdjcddb/zdjcddb.js?v=201705182030"></script>

</head>
<body class="easyui-layout" data-options="fit:true,border:false">
   <!-- 查询表单 -->
    <div class="easyui-layout" data-options="region:'center',border:false" style="width:100%;height:100%;">
   <!-- 查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:100px;border: false;overflow: hidden;" data-options="collapsible:true">
		<form id="searchForm">
			<table class="grid" style="border: 0px">
				<tr>
					<td class="form-td-label" style="width: 80px">工程名称:</td>
					<td class="form-td-content" colspan="3" style="width: 480px">
						<select editable="false" id="gcbh" name="gcbh" class="easyui-combobox" data-options="prompt:'请输入工程名称'" style="width: 100%;"></select>
					</td>
					<td class="form-td-label" style="width: 80px">区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间:</td>
					<td class="form-td-content" style="width: 200px">
						<select editable="false" id="qjbh" name="qjbh" class="easyui-combobox" data-options="prompt:'请输入区间'" style="width: 100%;"></select>
					</td>
				</tr>
				<tr>
					<td class="form-td-label" >起始监测时间:</td>
					<td>
						<input editable="false" id="beginTime" name="beginTime" class="easyui-datebox" data-options="prompt:'起始监测时间'" style="width:100%;">
	        		</td>
					<td class="form-td-label" >截止监测时间:</td>
					<td>
						<input editable="false" id="endTime" name="endTime" class="easyui-datebox" data-options="prompt:'截止监测时间'" style="width:100%;">
	        		</td>
					<td style="text-align: left;" colspan="2">
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="loadChart();">查询</a> 
					</td>
				</tr>
			</table>
		</form>
	</div>
   
    <!-- 结果列表 -->
	<div class="easyui-panel" title="统计结果" data-options="region:'center',border:false" style="width:100%;height:100%;">
		<div id="main1" style="width: 100%;height:400px;"></div>
	</div>
</body>
</html>