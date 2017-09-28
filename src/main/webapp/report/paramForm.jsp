<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/report/raqsoftReport.tld" prefix="report" %>

<html>
<head>
<title>集算HTML报表</title>
<LINK rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>

<report:html name="student" reportFileName="/功能演示/基本报表/E-网格式报表.raq"
	funcBarLocation="boTh"
	needPageMark="yes"
	functionBarColor="#fff5ee"
	funcBarFontSize="9pt"
	funcBarFontColor="blue"
	separator="&nbsp;&nbsp;"
	needSaveAsExcel="yes"
	needSaveAsPdf="yes"
	needPrint="yes"
	pageMarkLabel="页号{currpage}/{totalPage}"
	printLabel="打印"
	displayNoLinkPageMark="yes"
	saveAsName="学生成绩表"
/>

</body>
</html>
