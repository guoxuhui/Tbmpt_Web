<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.net.*" %>
<html>
<body>
<script type="text/javascript" src="../easyui/jquery.min.js"></script>
<%
	//request.setCharacterEncoding( "GBK" );
	String src = request.getParameter( "src" );
	String url = URLEncoder.encode( src, "UTF-8" );
%>
<script language=javascript>
	var ua = window.navigator.userAgent.toLowerCase();
	if( ua.indexOf( "msie" ) >= 0 || ua.indexOf( "edge" ) >= 0 || ua.indexOf( "trident" ) >= 0 ) {
		document.writeln( '<object classid="clsid:CA8A9780-280D-11CF-A24D-444553540000" id=pdfobj width="100%" height="100%" border="1">' );
		document.writeln( '<param name="SRC" value="<%=src%>">' );
		document.writeln( '</object>' )
		$( document ).ready( function(){ myprint(); } );
	}
	else {
		var url = "<%=url%>";
		document.writeln( '<iframe src="web/viewer.html?file=' + url + '" style="width:600px;height:500px"></iframe>' );
	}
	
	function myprint() {
		document.getElementById( "pdfobj" ).PrintAll();
		parent.closeToast();
		try{ parent.raqsoft_printOver();}catch(ex){}
	}
	
</script>
</body>
</html>
