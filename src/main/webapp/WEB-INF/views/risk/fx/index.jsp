<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/commons/basejs.jsp" %>
	<meta http-equiv="X-UA-Compatible" content="edge" />
	<title>风险统计分析</title>
	<script type="text/javascript" src="${staticPath }/static/js/risk/fx/list.js?time=20170830202457" charset="utf-8"></script>
	<script type="text/javascript" src="${staticPath }/static/js/risk/fx/echarts.min.js?time=20170823" charset="utf-8"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
<!-- 查询表单 -->
<div class="easyui-panel" title="查询" data-options="region:'north',border:true"  style="width:900px;height:67px;border: true;">
	<form id="searchForm">
		<table class="grid" style="border: 0px">
			<tr>
				<td class="form-td-label" style="width: 80px">监控时段：</td>
				<td class="form-td-content" style="width: 280px">
					<input id="sdata" name="sdata" class="easyui-datebox" data-options="prompt:'起始时间'" style="width:100px;">~
					<input id="edata" name="edata" class="easyui-datebox" data-options="prompt:'截止时间'" style="width:100px;">
				</td>
				<td class="form-td-label" style="width: 85px">图表</td>
				<td class="form-td-content" style="width: 180px">
					<select id="type" name="type" class="easyui-combobox" style="width: 100%;">
						<option value="bar">柱状图</option>
						<option value="line">折线图</option>
					</select>
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

<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:600px;padding: 20px;">
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<div id="chars_1" style="width: 100%;height:370px;"></div>
	<div id="chars_2" style="width: 100%;height:370px;display: none;"></div>
</div>
<div id="toolbar" style="display: none;">
	<a onclick="reloadFun();" href="javascript:void(0);" id="reload_button_zl_up" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
</div>
</body>
</html>