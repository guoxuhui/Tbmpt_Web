<%@ page contentType="text/html;charset=utf-8" %>
<%
	String sgid = request.getParameter("sgid");
	if (sgid == null) sgid = "sg1";
	boolean isAggr = "1".equals(request.getParameter("isAggr"));
%>
<link href="toolbar.css" rel="stylesheet">
<div class="btnBar">
  <ul class="left">
    <%if (isAggr){ %>
    <li class="toggleBg borderRight">
      <ul class="fileOper">
		<li><a class="ICOhover" href="#" onClick="_inputDownloadExcel('<%=sgid %>');return false;"><span title="保存到excel" class="excel"></span></a></li>
       </ul>
    </li>
    <%} else { %>
    <li class="borderRight submitLi" onClick="_inputSubmit('<%=sgid %>');return false;" href="#"> <a title="提交" href="#" class="submit"></a></li>
    <li class="toggleBg borderRight">
      <ul class="fileOper">
		<li><a class="ICOhover" href="#" onClick="_inputDownloadExcel('<%=sgid %>');return false;"><span title="保存到excel" class="excel"></span></a></li>
		<li><a class="ICOhover" href="#" onClick="_inputLoadExcelData('<%=sgid %>');return false;"><span title="导入excel数据" class="excel-import"></span></a></li>
		<li id="inputDefaultAddRow" style="display:none;"><a class="ICOhover" href="#" onClick="_appendRow('<%=sgid %>');return false;"><span title="增加行" class="rowFillVer"></span></a></li>
		<li id="inputDefaultInsertRow" style="display:none;"><a class="ICOhover" href="#" onClick="_insertRow('<%=sgid %>');return false;"><span title="插入行" class="rowFillHor"></span></a></li>
		<li id="inputDefaultDeleteRow" style="display:none;"><a class="ICOhover" href="#" onClick="_deleteRow('<%=sgid %>');return false;"><span title="删除行" class="rowFillDel"></span></a></li>
       </ul>
    </li>
    <%} %>
  </ul>
</div>
<script>
	$(document).ready(function(){
		if (sheetRowArea && sheetRowArea.indexOf("1")>=0) {
			$("#inputDefaultAddRow,#inputDefaultInsertRow,#inputDefaultDeleteRow").css('display','block');
		}
	});
</script>
