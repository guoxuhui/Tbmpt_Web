<%@ page contentType="text/html;charset=UTF-8" %>
<%
String cp = request.getContextPath();
String guideDir = cp + request.getParameter("guideDir");
String v = "9";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
<head>
	<title></title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge;" /><!-- 强制以IE8模式运行 -->
	<script type="text/javascript" src="<%=guideDir %>/js/jquery-1.9.1.js?v=<%=v %>"></script>
	<script type="text/javascript" src="<%=guideDir %>/js/common.js?v=<%=v %>"></script>
	<script language=javascript>
		var reportConfName = '<%=request.getParameter("confName") %>';
		$(document).ready(function(){
			parent.$('img[confNameLoading="'+reportConfName+'"]').css('visibility','hidden');
			function resize() {
				$('#mainDiv').css({width:$(window).width()-6,height:$(window).height()-31});
			}
			$('body').click(function(){
				parent.aly.focusReport(reportConfName);
			});
			$(window).scroll(function(){
				//$('#toolbarDiv').css('top',$(window).scrollTop()+"px");
			}).resize(function(){
				resize();
			});
			resize();
			
		});
	</script>
</head>
<body style="overflow:hidden;margin:3px;width:2000px;height:2000px;">
&nbsp;
</body>
</html>
