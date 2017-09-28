<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/commons/basejs.jsp" %>
	<meta http-equiv="X-UA-Compatible" content="edge" />
	<title>管片质量统计</title>
	<script type="text/javascript" src="${staticPath }/static/js/zl/fx/list.js?time=20170829183557" charset="utf-8"></script>
	<script type="text/javascript" src="${staticPath }/static/js/zl/fx/echarts.min.js?time=20170422" charset="utf-8"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
<!-- 查询表单 -->
<div class="easyui-panel" title="查询" data-options="region:'north',border:true"  style="width:900px;height:120px;border: true;">
	<form id="searchForm">
		<table class="grid" style="border: 0px">
			<tr>
				<td class="form-td-label" style="width: 85px">项目部名称：</td>
				<td class="form-td-content" style="width: 380px">
					<select id="search_proId" name="proName" class="easyui-combobox" data-options="prompt:'请输入工程名称'" style="width: 100%;"></select>
				</td>
				<td class="form-td-label" style="width: 85px">区间：</td>
				<td class="form-td-content" style="width: 180px">
					<select id="search_sectionId" name="section" class="easyui-combobox" data-options="prompt:'--请选择--'" style="width:100%"></select>
				</td>
				<td class="form-td-label" style="width: 85px">线路：</td>
				<td class="form-td-content" style="width: 180px">
					<select id="search_lineId" name="line" class="easyui-combobox" data-options="prompt:'--请选择--'" style="width:100%"></select>
				</td>
			</tr>
			<tr>
				<td class="form-td-label" style="width: 85px">选择日期(近)：</td>
				<td class="form-td-content">
					
					<select id="selectDay" name="selectDay" class="easyui-combobox" data-options="prompt:'天'" style="width: 100px;">
						<option value="">--日--</option>
						<option value="7">7</option>
						<option value="15">15</option>
						<option value="30">30</option>
					</select>
					或
					<select id="selectZhou" name="selectZhou" class="easyui-combobox" data-options="prompt:'周'" style="width: 100px;">
						<option value="">--周--</option>
						<option value="7">7</option>
						<option value="15">15</option>
						<option value="30">30</option>
					</select>
					或
					<select id="selectMonth" name="selectMonth" class="easyui-combobox" data-options="prompt:'月'" style="width: 100px;">
						<option value="">--月--</option>
						<option value="7">7</option>
						<option value="15">15</option>
						<option value="30">30</option>
					</select>
					
				</td>
				<td class="form-td-label" style="width: 85px">图表</td>
				<td class="form-td-content" style="width: 180px">
					<select id="picType" name="picType" class="easyui-combobox" data-options="prompt:'天'" style="width: 100%;">
						<option value="">--请选择--</option>
						<option value="0">柱状图</option>
						<option value="1">折线图</option>
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
	<div id="chars_1" style="width: 100%;height:430px;"></div>
	<div id="chars_2" style="width: 100%;height:430px;display: none;"></div>
</div>
<div id="toolbar" style="display: none;">
	<a onclick="reloadFun();" href="javascript:void(0);" id="reload_button_zl_up" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
</div>
</body>
</html>