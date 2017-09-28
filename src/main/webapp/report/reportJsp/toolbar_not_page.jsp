<%@ page contentType="text/html;charset=utf-8" %>
<%
	if(request.getProtocol().compareTo("HTTP/1.1")==0 ) response.setHeader("Cache-Control","no-cache");
	else response.setHeader("Pragma","no-cache");
%>
<div class="btnBar">
  <ul class="left">
    <li class="toggleBg borderRight">
      <ul class="fileOper">
        <li><a class="ICOhover" href="#" onClick="exportExcel('report1');return false;"><span title="导出excel" class="excel"></span></a></li>
        <li><a class="ICOhover" href="#" onClick="exportPdf('report1');return false;"><span title="导出pdf" class="pdf"></span></a></li>
		<li><a class="ICOhover" href="#" onClick="exportWord('report1');return false;"><span title="导出word" class="word"></span></a></li>
        <li><a class="ICOhover" href="#" onClick="printReport('report1');return false;"><span title="打印" class="print"></span></a></li>
		<li><a class="ICOhover" href="#" onClick="flashPrintReport('report1');return false;"><span title="flash打印" class="flashprint"></span></a></li>
        <li><a class="ICOhover" href="#" onClick="pdfPrintReport('report1');return false;"><span title="pdf打印" class="pdfprint"></span></a></li>
       </ul>
    </li>
  </ul>

</div>
