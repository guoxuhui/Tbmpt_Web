<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>盾构机故障停机对比分析</title>
<script src="${staticPath }/static/china.js"></script>
<script src="${staticPath }/static/js/tbmmg/dgstateinfo/dgstateinfo.js?v=2017051724"></script>

</head>
<body class="easyui-layout" data-options="fit:true,border:false">
   <!-- 查询表单 -->
    <div class="easyui-layout" data-options="region:'center',border:false" style="width:100%;height:100%;">
   <!-- 查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:60px;border: false;overflow: hidden;" data-options="collapsible:true">
		<form id="searchForm">
			<table class="grid" style="border: 0px">
				<tr>
					<td class="form-td-label" >日期时间:</td>
					<td>
						<input editable="false" id="riqi" name="riqi" data-options="prompt:'日期时间'" style="width:100%;">
	        		</td>
					<td style="text-align: left;" colspan="2">
						<a href="javascript:void(0);" id="asdasdasd" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" >查询</a> 
					</td>
				</tr>
			</table>
		</form>
	</div>
   
    <!-- 结果列表 -->
	<div class="easyui-panel" title="统计结果" data-options="region:'center',border:false" style="width:100%;height:100%;">
		<div id="main1" style="width: 100%;height:100%;"></div>
	</div>
</body>
</html>