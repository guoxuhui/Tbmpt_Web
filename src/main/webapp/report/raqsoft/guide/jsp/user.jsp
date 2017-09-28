<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/report/raqsoftReport.tld" prefix="report" %>
<%@ page import="com.raqsoft.report.view.*" %>
<%@ page import="com.raqsoft.report.util.*" %>
<%@ page import="com.raqsoft.report.model.*" %>
<%@ page import="com.raqsoft.report.usermodel.*" %>
<%@ page import="com.raqsoft.guide.web.dl.*" %>
<%@ page import="com.raqsoft.guide.*" %>
<%@ page import="com.raqsoft.guide.web.*" %>
<%@ page import="com.raqsoft.common.*" %>
<%@ page import="java.sql.*" %>
<%@ taglib uri="/WEB-INF/raqsoftGuide.tld" prefix="guide" %>
<%
String cp = request.getContextPath();
String title = "超维报表";
String guideDir = cp + ReportConfig.raqsoftDir + "/guide/";
String v = "13";//修改这个值更新浏览器端的旧js文件缓存
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
<head>
	<title><%=title %></title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge;" /><!-- 强制以IE8模式运行 -->
	<link rel="stylesheet" href="<%=guideDir %>css/style.css" type="text/css">
	<link rel="stylesheet" href="<%=guideDir %>js/chosen.css" type="text/css">
	<link rel="stylesheet" href="<%=guideDir %>js/jquery-powerFloat/css/powerFloat.css" type="text/css">
	<link rel="stylesheet" href="<%=guideDir %>js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<link rel="stylesheet" type="text/css" href="<%=guideDir %>js/selectBoxIt/src/stylesheets/jquery.selectBoxIt.css">
	<script type="text/javascript" src="<%=guideDir %>js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="<%=guideDir %>js/common.js"></script>
	<script type="text/javascript" src="<%=guideDir %>js/json2.js"></script>
	<script type="text/javascript" src="<%=guideDir %>js/jquery.cookie.js"></script>
	<script type="text/javascript" src="<%=guideDir %>js/chosen.jquery.min.js"></script>
	<script type="text/javascript" src="<%=guideDir %>js/jquery.layout.js"></script>
	<!-- 
	<script type="text/javascript" src="<%=guideDir %>js/jquery-ui.js"></script>
	-->
	<script type="text/javascript" src="<%=guideDir %>js/jquery-ui-1.10.1.custom.min.js"></script>
	<script type="text/javascript" src="<%=guideDir %>js/jquery-ui-timepicker-addon.js"></script>
	<script type="text/javascript" src="<%=guideDir %>js/lgl_es_4.js?v=<%=v %>"></script>
	<script type="text/javascript" src="<%=guideDir %>js/jquery.tooltip.js"></script>
	<script type="text/javascript" src="<%=guideDir %>js/jquery.bgiframe.js"></script>
	<script type="text/javascript" src="<%=guideDir %>js/jquery.qtip-1.0.0-rc3.min.js"></script>
	<script type="text/javascript" src="<%=guideDir %>js/raphael-min.js"></script>
	<script type="text/javascript" src="<%=guideDir %>js/jquery.tools.min.js"></script>
	<script type="text/javascript" src="<%=guideDir %>js/jquery.blockUI.js"></script>
	<script type="text/javascript" src="<%=guideDir %>js/artDialog/jquery.artDialog.source.js?skin=twitter"></script>
	<script type="text/javascript" src="<%=guideDir %>js/ztree/js/jquery.ztree.all-3.5.min.js"></script>
	<script type="text/javascript" src="<%=guideDir %>js/selectBoxIt/src/javascripts/jquery.selectBoxIt.min.js"></script>  
	<script type="text/javascript" src="<%=guideDir %>js/ztree/js/jquery.ztree.exhide-3.5.min.js"></script>
	<script type="text/javascript" src="<%=guideDir %>js/jquery-powerFloat/js/mini/jquery-powerFloat-min.js"></script>	
	<script type="text/javascript" src="<%=guideDir %>js/where.js?v=<%=v %>"></script>
	<script language=javascript>
	</script>
</head>
<body style="margin:0;padding:0;">
	<input type="button" value="设置外部条件" onclick='top.frames["frame2"].grpxApi.setOuterCondition("${T}.市=10102");alert("设置成功");'/>
	<br/>
	top.frames["frame2"].grpxApi.setOuterCondition("${T}.市=10102");
	<br/><br/>
	<form id="form1" action="<%=cp %>/guide.jsp" target="top.frame2" method="post" accept-charset="UTF-8">
		<input type='text' name='fixedTable' placeholder='表'/><br/>
		<input type='text' name='outerCondition' placeholder='外部条件'/><br/>
		<input type="button" value="提交" onclick='$("#form1")[0].submit();'/>
	</form>
</body>
</html>

