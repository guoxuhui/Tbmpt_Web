<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>日监测报表</title>
<script type="text/javascript">

function reloadUrl(index){
	
	$('#framePage').src = "${url}"+index;
	document.getElementById('framePage').src = "${url}"+index;
}

</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  
		style="width:100%;height:60px;border: false;overflow: hidden;" data-options="collapsible:true">
		<form id="searchForm">
			<table class="grid" style="border: 0px">
				<td style="text-align: center;">
				
					<c:forEach items="${pageList }" var="page">
	        			<a href="javascript:void(0);" 
							class="easyui-linkbutton" 
							data-options="iconCls:'icon-search',plain:true" 
							onclick="reloadUrl(${page});">第${page}页</a> 
	    			</c:forEach>
					
				</td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center'" style="overflow: hidden;">
		<iframe id="framePage" src="${url}1" frameborder="0" style="border:0;width:100%;height:100%;"></iframe>
	</div>

</body>
</html>