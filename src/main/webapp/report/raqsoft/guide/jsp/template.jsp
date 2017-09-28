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
<%
String jsv = request.getParameter("jsv");
String cp = request.getContextPath();
String title = request.getParameter("title");
if (title == null) title = "超维报表";
String guideDir = cp + request.getParameter("guideDir");
String v = ""+System.currentTimeMillis();
v = "461";//v.substring(v.length()-5);
/*
		DBSession dbs = null;
		Connection con = null;
		try {
			ISessionFactory isf = (ISessionFactory)com.raqsoft.dm.Env.getDBSessionFactory("DataLogic");
			Logger.debug(new String("query metadata!"));
			dbs = isf.getSession();
			con = (Connection)dbs.getSession();
			ResultSet rs = con.createStatement().executeQuery("SELECT T0.姓名 姓名 FROM 雇员 T0");
			while (rs.next()) {
				System.out.println("--------");
				System.out.println(rs.getString(1));
				//System.out.println(rs.getString(1));
			}
		} catch (Exception e) {
			//return "";
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				Logger.warn(e);
			}
			try {
				dbs.close();
			} catch (Exception e) {
				Logger.warn(e);
			}
		}
*/

%>
<script>
</script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html>
<head>
	<title><%=title %></title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge;" /><!-- 强制以IE8模式运行 -->
	<link rel="stylesheet" href="<%=guideDir %>css/style.css" type="text/css">
	<link rel="stylesheet" href="<%=guideDir %>js/chosen_v1.5.1/chosen.css" type="text/css">
	<link rel="stylesheet" href="<%=guideDir %>js/jquery-powerFloat/css/powerFloat.css" type="text/css">
	<link rel="stylesheet" href="<%=guideDir %>js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<style>
		#feedback { font-size: 1.4em; }
		#dimItemsDiv .ui-selecting { background: #FECA40; }
		#dimItemsDiv { list-style-type: none; margin: 0; padding: 0; }
		#dimItemsDiv li { margin: 1px; padding: 3px 10px 3px 10px; float: left; height: 22px; font-size: 12pt; text-align: center; }
		#table {table-layout:fixed;}
		#td {white-space:nowrap;overflow:hidden;word-break:keep-all;}
	</style>
	<script type="text/javascript" src="<%=guideDir %>js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="<%=guideDir %>js/common.js?v=<%=v %>"></script>
	<script language=javascript>
		//menu/jquery.js
		var contextPath = '<%=cp%>';
		if (guideConf.guideDir == '') guideConf.guideDir = "/raqsoft/guide/";
		if (guideConf.guideDir.substring(guideConf.guideDir.length-1) != '/') guideConf.guideDir = guideConf.guideDir+"/";
		var selfUrl = window.location.href;
		if (selfUrl.indexOf('?')>=0) selfUrl = selfUrl.substring(0,selfUrl.indexOf('?'));

		if (guideConf.grpxSourcePage=='') guideConf.grpxSourcePage = selfUrl;
		else guideConf.grpxSourcePage = "<%=cp%>/"+guideConf.grpxSourcePage;
		guideConf.grpxSourcePage = guideConf.grpxSourcePage.replaceAll("//","/");

		if (guideConf.grpxDataPage=='') guideConf.grpxDataPage = selfUrl;
		else guideConf.grpxDataPage = "<%=cp%>/"+guideConf.grpxDataPage;
		guideConf.grpxDataPage = guideConf.grpxDataPage.replaceAll("//","/");

		if (guideConf.grpxReportPage=='') guideConf.grpxReportPage = selfUrl;
		else guideConf.grpxReportPage = "<%=cp%>/"+guideConf.grpxReportPage;
		guideConf.grpxReportPage = guideConf.grpxReportPage.replaceAll("//","/");

		if (guideConf.queryPage=='') guideConf.queryPage = selfUrl;
		else guideConf.queryPage = "<%=cp%>/"+guideConf.queryPage;
		guideConf.queryPage = guideConf.queryPage.replaceAll("//","/");

		if (guideConf.simplePage=='') guideConf.simplePage = selfUrl;
		else guideConf.simplePage = "<%=cp%>/"+guideConf.simplePage;
		guideConf.simplePage = guideConf.simplePage.replaceAll("//","/");

		if (guideConf.olapPage=='') guideConf.olapPage = selfUrl;
		else guideConf.olapPage = "<%=cp%>/"+guideConf.olapPage;
		guideConf.olapPage = guideConf.olapPage.replaceAll("//","/");

		if (guideConf.reportPage=='') guideConf.reportPage = selfUrl;
		else guideConf.reportPage = "<%=cp%>/"+guideConf.reportPage;
		guideConf.reportPage = guideConf.reportPage.replaceAll("//","/");
		
		guideConf.defaultWidth = 25;
		guideConf.defaultHeight = 8;
	</script>
	<script type="text/javascript" src="<%=guideDir %>js/json2.js"></script>
	<script type="text/javascript" src="<%=guideDir %>js/jquery.cookie.js"></script>
	<script type="text/javascript" src="<%=guideDir %>js/jquery.layout.js"></script>
	<script type="text/javascript" src="<%=guideDir %>js/chosen_v1.5.1/chosen.jquery.min.js"></script>
	<!-- 
	<link rel="stylesheet" type="text/css" href="<%=guideDir %>js/selectBoxIt/src/stylesheets/jquery.selectBoxIt.css">
	<script type="text/javascript" src="<%=guideDir %>js/jquery-ui.js?v=<%=v %>"></script>
	<script type="text/javascript" src="<%=guideDir %>js/selectBoxIt/src/javascripts/jquery.selectBoxIt.min.js"></script>  
	<script type="text/javascript" src="<%=guideDir %>js/raphael-min.js"></script>
	<script type="text/javascript" src="<%=guideDir %>js/jquery.tooltip.js"></script>
	<script type="text/javascript" src="<%=guideDir %>js/jquery.qtip-1.0.0-rc3.min.js"></script>
	<script type="text/javascript" src="<%=guideDir %>js/report_v1.js?v=<%=v %>"></script>
	<script type="text/javascript" src="<%=guideDir %>js/report.js?v=<%=v %>"></script>
	-->
	<script type="text/javascript" src="<%=guideDir %>js/jquery-ui-1.10.1.custom.min.js"></script>
	<script type="text/javascript" src="<%=guideDir %>js/jquery-ui-timepicker-addon.js"></script>
	<script type="text/javascript" src="<%=guideDir %>js/jquery.bgiframe.js"></script>
	<script type="text/javascript" src="<%=guideDir %>js/jquery.tools.min.js"></script>
	<script type="text/javascript" src="<%=guideDir %>js/jquery.blockUI.js"></script>
	<script type="text/javascript" src="<%=guideDir %>js/artDialog/jquery.artDialog.source.js?skin=twitter"></script>
	<script type="text/javascript" src="<%=guideDir %>js/ztree/js/jquery.ztree.all-3.5.min.js"></script>
	<script type="text/javascript" src="<%=guideDir %>js/ztree/js/jquery.ztree.exhide-3.5.min.js"></script>
	<script type="text/javascript" src="<%=guideDir %>js/where.js?v=<%=v %>"></script>
	<script type="text/javascript" src="<%=guideDir %>js/jquery-powerFloat/js/mini/jquery-powerFloat-min.js"></script>	
	<%if("v1".equals(jsv)){%>
	<script type="text/javascript" src="<%=guideDir %>js/query_v1.js?v=<%=v %>"></script>
	<script type="text/javascript" src="<%=guideDir %>js/dqlreport_v1.js?v=<%=v %>"></script>
	<%}else{%>
	<script type="text/javascript" src="<%=guideDir %>js/query.js?v=<%=v %>"></script>
	<script type="text/javascript" src="<%=guideDir %>js/dqlreport.js?v=<%=v %>"></script>
	<%}%>
	<script type="text/javascript" src="<%=guideDir %>js/raqsoftApi.js?v=<%=v %>"></script>
</head>
<body style="margin:0;padding:0;overflow:hidden;">
<div id="queryView" style="display:none;">
	<div id='bodyDiv' style='width:100%;height:100%;display:none;'>
		<div style="display:none;">
			<iframe id='hiddenFrame' name='hiddenFrame' height="100px" width="100px"></iframe>
			<form id=downloadForm method=post ACTION="<%=cp%>/servlet/dataSphereServlet?action=11" target=hiddenFrame>
				<input type=hidden name=path id=path value="">
				<input type=hidden name=content id=content value="">
				<input type=hidden name=mode id=mode value="">
			</form>
		</div>

		<div style='display:none;position:absolute;z-index:501;top:50px;left:467px;padding:1px 10px;margin-top:1px;' id="exceptNullGroup" title=''></div>
		<div style='display:none;position:absolute;z-index:502;top:50px;left:580px;padding:1px 10px;margin-top:1px;' id="autoFindLevel" title='自动探测数据项的深度层数'></div>

		<div class='mainPanel' style="border:0px;width:100%;height:100%;overflow:hidden;position:;">
			<div class='ui-layout-toolbar' style="overflow:hidden;font-size:14px;position:absolute;top:0;border:0px;height:50px;z-index:30001;background-color:#41455A;width:100%;">
				<img src="<%=guideDir %>img/guide/46.png" style=""/>
				<a href="javascript:submitQuery()" style="color:#DFDFE3;vertical-align:20px;text-decoration: none;"><img src="<%=guideDir %>img/guide/3.png" style="vertical-align:-7px;margin:0 10px;"/></a>
				<a href="javascript:operations.undo()" style="color:#DFDFE3;vertical-align:20px;text-decoration: none;"><img src="<%=guideDir %>img/guide/4.png" style="vertical-align:-7px;margin:0 10px;"/></a>
				<a href="javascript:operations.redo()" style="color:#DFDFE3;vertical-align:20px;text-decoration: none;"><img src="<%=guideDir %>img/guide/32.png" style="vertical-align:-7px;margin:0 10px;"/></a>

				<a href="javascript:saveQyx()" style="color:#DFDFE3;vertical-align:20px;text-decoration: none;"><img src="<%=guideDir %>img/guide/save.png" style="vertical-align:-7px;margin:0 10px;"/>保存</a>&nbsp;&nbsp;
				<a href="javascript:openQyx()" style="color:#DFDFE3;vertical-align:20px;text-decoration: none;"><img src="<%=guideDir %>img/guide/2.png" style="vertical-align:-7px;margin:0 10px;"/>打开</a>&nbsp;&nbsp;

				<div style='float:right;padding:1px 10px;margin-top:1px;visibility:hidden;' id="dqlDataSources" title='请选择dql类型数据源'></div>
				<a href="javascript:moreQuery()" style="display:none;float:right;color:#DFDFE3;vertical-align:20px;text-decoration: none;margin: 15px;" title='更多其它类型的查询方式的数据源'>通用数据源</a>
				<div style="clear:both;"></div>
			</div>
			<div id='innerLayout' style="border:0px;">
				<div id='dqlDiv' style="border: 0;position: absolute; margin: 0px; z-index: 2; left: 367px;" class='dl_c9'>
				</div>
				<div class='inner-north dl_c9' style="" id='tableDiv'>
		 		</div>

				<div id="sourceArea">
					<div class="dl_c9" id="mytabs" style='overflow-y:hidden;background-color:#F8F8F8;padding:0;margin:0;'>
						<div class="top_nav" style='display:none;'>
							<ul style='list-style-type:none;margin:0px;padding: 0 0 0 15px;'>
								<li title='拖拽维到左边维区域'><a style='width:75px;padding:5px 0 7px 0;' id='tabs-btn-1' onclick='selectTab(1)' class='selected' href="#"><img class='dl_img6' style='vertical-align:middle;border:0px;' src=''>维</a></li>
								<li style='display:none;' title='双击表，打开附表组'><a style='width:75px;padding:5px 0 7px 0;' id='tabs-btn-4' onclick='selectTab(4)' href="#"><img class='dl_img12' style='vertical-align:middle;border:0px;' src=''>表</a></li>
								<li title='双击字段，打开该字段的所在附表'><a style='width:75px;padding:5px 0 7px 0;' id='tabs-btn-2' onclick='selectTab(2)' href="#"><img class='dl_img7' style='vertical-align:middle;border:0px;' src=''>数据项</a></li>
								<li title='拖拽字段到左边字段区域、维表关系列'><a style='width:75px;padding:5px 0 7px 0;' id='tabs-btn-3' onclick='selectTab(3)' href="#"><img class='dl_img8' style='vertical-align:middle;border:0px;' src=''>字段</a></li>
							</ul>
						</div>
						<div id="tabs-2" style='overflow:hidden;'>
							<div id="tabs-2-top" style='clear:both;border:0px;width:98%;padding:5px;margin-bottom:0px;background-color:#F0F0F0;'>
								<div style='padding-top:0px;float:left;'><input type="button" id="fieldShowStyle" onclick='changeShowStyle(this.value);' value="分类-数据项" style="width:100%;height:27px;border: 0;margin: 2px 1px; background-repeat:no-repeat;background-image:url('<%=guideDir %>img/guide/12.png');background-position:6% 6px;color:#333333;border:1px solid #E4E4E4;padding-left:19px;background-color:#F8F8F8;"></div>
								<div style='float:left;padding:0 0 0 10px;margin-top:1px;' id="hideNoRela" title=''></div>
								<div style="display:none;float:left;padding:0 0 0 10px;margin-top:1px;" id="classTableDiv"></div>
								<input type="text" bak="" idx="0" value="输入过滤字" id="filter" filterbak="" class="filter" style="height: 26px; width: 280px; margin: 2px 0 2px 1px;"><img id="filterUp" style="cursor:pointer;border-top:1px solid #E4E4E4;border-bottom:1px solid #E4E4E4;padding:4px 6px;vertical-align:-9px;" src="<%=guideDir %>img/guide/41.png"><img id="filterDown" style="cursor:pointer;border:1px solid #E4E4E4;padding:4px 6px;vertical-align:-9px;" src="<%=guideDir %>img/guide/42.png">
								<div class='filterButton' id="icon_filter" style='border:0px;float:right;height:21px;display:none;'>&nbsp;&nbsp;&nbsp;&nbsp;</div>
							</div>
							<div class="east-west" style='border:0px;overflow-y:auto;margin:0px;padding:0px 2px;'>
								<div id='contentDiv' style='border:0;padding:0px;' class="ztree"></div>
							</div>
						</div>
					</div>
					<div class='' style="background-color:#F8F8F8;" id='dimDiv'>
					</div>
				</div>

			</div>

			<div class="inner-center dl_c9" id='resultSetDiv' style='overflow:hidden;display:none;'>
				<div id='designResultSet' style='padding:5px;'>
					<table class='dl_c5' style='table-layout:fixed;border-collapse:collapse;' border=0 cellspacing=0 cellpadding=0>
						<tr id="resultSetHeader" style='height:26px;text-align:center;'></tr>
						<tbody id="resultSetBody"></tbody>
					</table>
				</div>
				<div id='rsDiv' style='display:none;width:100%;height:100%;overflow:hidden;'>
					<iframe name="rsFrame" id="rsFrame" height="100%" width="100%" style='border:0;'></iframe>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="dataView" style="display:none;">
	<div style="display:none;">
		<iframe id='hiddenFrame' name='hiddenFrame' height="100px" width="100px"></iframe>
		<form id=downloadForm method=post ACTION="<%=cp%>/servlet/dataSphereServlet?action=11" target=hiddenFrame>
			<input type=hidden name=path id=path value="">
			<input type=hidden name=content id=content value="">
			<input type=hidden name=mode id=mode value="">
		</form>
	</div>
	<div class='ui-layout-toolbar' style="font-size:14px;position:absolute;top:0;border:0px;height:50px;z-index:30001;background-color:#41455A;width:100%;">
		<img src="<%=guideDir %>img/guide/logo.png" style=""/><img style="" src="<%=guideDir %>img/guide/title-2.png"/>
		<a href="javascript:doReport()" style="color:#DFDFE3;vertical-align:18px;text-decoration: none;"><img src="<%=guideDir %>img/guide/15.png" style="vertical-align:-7px;margin:0 10px;"/>生成报表</a>&nbsp;&nbsp;
		<a href="javascript:downloadData('txt')" style="color:#DFDFE3;vertical-align:18px;text-decoration: none;"><img src="<%=guideDir %>img/guide/txt.png" style="vertical-align:-7px;margin:0 10px;"/>TXT</a>&nbsp;&nbsp;
		<a href="javascript:downloadData('csv')" style="color:#DFDFE3;vertical-align:18px;text-decoration: none;"><img src="<%=guideDir %>img/guide/txt.png" style="vertical-align:-7px;margin:0 10px;"/>CSV</a>&nbsp;&nbsp;

		<a href="javascript:saveGrpx()" style="color:#DFDFE3;vertical-align:18px;text-decoration: none;"><img src="<%=guideDir %>img/guide/save.png" style="vertical-align:-7px;margin:0 10px;"/>保存</a>&nbsp;&nbsp;
		<a href="javascript:openGrpx()" style="color:#DFDFE3;vertical-align:18px;text-decoration: none;"><img src="<%=guideDir %>img/guide/2.png" style="vertical-align:-7px;margin:0 10px;"/>打开</a>&nbsp;&nbsp;

		<img src='<%=guideDir %>img/guide/35.png' id='prevBut' onclick='getPageRows(-1)' type='button' title='上一页' style='border:0px;cursor:pointer;vertical-align:11px;'>
		<input type="text" id="currPage" style="vertical-align:18px;width:40px;text-align:center" value="0"/><span style="vertical-align:18px;color:#DFDFE3;" id="totalPage"></span>
		<img src='<%=guideDir %>img/guide/28.png' id='nextBut' onclick='getPageRows(1)' type='button' title='下一页' style='border:0px;cursor:pointer;vertical-align:11px;'>
	
	
		<div id="queryStatus" style="float:right;padding-right:5px;margin:16px;color:#DFDFE3;"></div>
		<div id="tableNameSpan" style="float:right;padding-right:5px;margin:14px 5px;color:#DFDFE3;"></div>
		<a href="javascript:moreQuery()" style="float:right;color:#DFDFE3;vertical-align:20px;text-decoration: none;margin: 15px;" title=''>数据源</a>
		<div style="clear:both;"></div>
	</div>
	<div style="margin:60px 25px 0;" id="whereDiv"></div>
	<div style="margin:10px 25px;">
		<div style="float:left" id="resultDiv"></div>
		<div style="float:left;display:none;" id="addCalcFieldDiv"><img src="<%=guideDir %>img/guide/9.png" style="cursor:pointer;margin:1px 2px;" title="添加计算字段" onclick="rpx.editCalcField()"/></div>
		<div style="clear:both"></div>
	</div>
</div>
<div id="reportView" style="display:none;">
	<div style="display:none;">
		<iframe id='hiddenFrame' name='hiddenFrame' height="100px" width="100px"></iframe>
		<form id=downloadForm method=post ACTION="<%=cp%>/servlet/dataSphereServlet?action=11" target=hiddenFrame>
			<input type=hidden name=path id=path value="">
			<input type=hidden name=content id=content value="">
			<input type=hidden name=mode id=mode value="">
		</form>
	</div>
	<div id="confFieldFloat" style="display:none;background-color:#F3F3F3;border: 1px solid #CCC;">
		<div seq="1" style="margin:2px 5px;" onclick="rpx.confField.order(1)"><img src="<%=guideDir %>img/guide/42.png" style="vertical-align:-4px;visibility:hidden;"/>升序</div>
		<div seq="2" style="margin:2px 15px" onclick="rpx.confField.order(2)"><img src="<%=guideDir %>img/guide/42.png" style="vertical-align:-4px;visibility:hidden;" />降序</div>
		<div seq="3" style="margin:2px 5px;" onclick="rpx.confField.order(0)"><img src="<%=guideDir %>img/guide/42.png" style="vertical-align:-4px;visibility:hidden;" />不排序</div>
		<span seq="4" style="font-size:1px;display:block;width:100%;border-top:1px solid #AAA;margin:3px 0;"></span>
		<div seq="5" style="margin:2px 5px;" onclick="rpx.confField.edit()"><img src="<%=guideDir %>img/guide/31.png" style="vertical-align:-3px;" />编辑</div>
		<div seq="6" style="margin:2px 5px;" onclick="rpx.confField.format()"><img src="<%=guideDir %>img/guide/16.png" style="vertical-align:-3px;padding-right:1px;width:15px;height:16px;" />显示格式</div>
		<span seq="7" style="font-size:1px;display:block;width:100%;border-top:1px solid #AAA;margin:3px 0;"></span>
		<div seq="8" style="color:red;margin:2px 5px;" onclick="rpx.confField.del()"><img src="<%=guideDir %>img/guide/13.png" style="vertical-align:-3px;" />删除</div>
	</div>
	<div id="calcFieldFloat" style="display:none;background-color:#F3F3F3;border: 1px solid #CCC;">
		<div seq="1" style="margin:2px 5px;" onclick="aly.calcField.edit()"><img src="<%=guideDir %>img/guide/31.png" style="vertical-align:-3px;" />编辑</div>
		<div seq="2" style="color:red;margin:2px 5px;" onclick="aly.calcField.del()"><img src="<%=guideDir %>img/guide/13.png" style="vertical-align:-3px;" />删除</div>
	</div>
	
	<div class='ui-layout-north' style="font-size:14px;position:absolute;top:0;border:0px;height:50px;z-index:30001;background-color:#41455A;width:100%;">
		<img src="<%=guideDir %>img/guide/logo.png"/><img src="<%=guideDir %>img/guide/title-3.png"/>

		<a href="javascript:saveGrpx()" style="color:#DFDFE3;vertical-align:20px;text-decoration: none;"><img src="<%=guideDir %>img/guide/save.png" style="vertical-align:-7px;margin:0 10px;"/>保存</a>&nbsp;&nbsp;
		<a href="javascript:openGrpx()" style="color:#DFDFE3;vertical-align:20px;text-decoration: none;"><img src="<%=guideDir %>img/guide/2.png" style="vertical-align:-7px;margin:0 10px;"/>打开</a>&nbsp;&nbsp;

		<a href="javascript:relocalReports(1)" style="color:#DFDFE3;vertical-align:20px;text-decoration: none;"><img src="<%=guideDir %>img/guide/8.png" style="vertical-align:-7px;margin:0 10px;"/>横向平铺</a>&nbsp;&nbsp;
		<a href="javascript:relocalReports(2)" style="color:#DFDFE3;vertical-align:20px;text-decoration: none;"><img src="<%=guideDir %>img/guide/34.png" style="vertical-align:-7px;margin:0 10px;"/>纵向平铺</a>&nbsp;&nbsp;
		<a href="javascript:relocalReports(3)" style="color:#DFDFE3;vertical-align:20px;text-decoration: none;"><img src="<%=guideDir %>img/guide/5.png" style="vertical-align:-7px;margin:0 10px;"/>重叠显示</a>&nbsp;&nbsp;

		<div id="queryStatus" style="float:right;padding-right:5px;margin:16px;color:#DFDFE3;"></div>
		<a href="javascript:refreshData()" style="float:right;color:#DFDFE3;vertical-align:20px;text-decoration: none;margin: 15px;" title=''>更新数据</a>
		<a href="javascript:moreQuery()" style="float:right;color:#DFDFE3;vertical-align:20px;text-decoration: none;margin: 15px;" title=''>数据源</a>
		<a href="javascript:dqlQueryInReport()" id="dqlQueryBut" style="float:right;color:#DFDFE3;vertical-align:20px;text-decoration: none;margin: 15px;" title=''>DQL查询</a>
		<div style="clear:both;"></div>
	</div>
	<div class='ui-layout-center' style="border:0px;">
	</div>
	<div id="reportConfBut" style="background-color:#41455A;color:#FFFFFF;top:40px;position: absolute;z-index:20001;"></div>
	<div id="reportConf" style="top:70px;position: absolute;z-index:20000;"></div>
</div>
<div id="analyseView" style="display:none;">
	<div style="display:none;">
		<iframe id='hiddenFrame' name='hiddenFrame' height="100px" width="100px"></iframe>
		<form id=downloadForm method=post ACTION="<%=cp%>/servlet/dataSphereServlet?action=11" target=hiddenFrame>
			<input type=hidden name=path id=path value="">
			<input type=hidden name=content id=content value="">
			<input type=hidden name=mode id=mode value="">
		</form>
	</div>
	<div id="confFieldFloat" style="display:none;background-color:#F3F3F3;border: 1px solid #CCC;">
		<div seq="17" style="margin:2px 5px;" onclick="aly.confField.aggr('')"><img src="<%=guideDir %>img/guide/42.png" aggr='' style="vertical-align:-4px;visibility:hidden;"/>不汇总</div>
		<div seq="15" style="margin:2px 5px;" onclick="aly.confField.aggr('sum')"><img src="<%=guideDir %>img/guide/42.png" aggr='sum' style="vertical-align:-4px;visibility:hidden;"/>求和</div>
		<div seq="14" style="margin:2px 5px" onclick="aly.confField.aggr('count')"><img src="<%=guideDir %>img/guide/42.png" aggr='count' style="vertical-align:-4px;visibility:hidden;" />计数</div>
		<div seq="13" style="margin:2px 5px;" onclick="aly.confField.aggr('max')"><img src="<%=guideDir %>img/guide/42.png" aggr='max' style="vertical-align:-4px;visibility:hidden;" />最大</div>
		<div seq="12" style="margin:2px 5px;" onclick="aly.confField.aggr('min')"><img src="<%=guideDir %>img/guide/42.png" aggr='min' style="vertical-align:-4px;visibility:hidden;" />最小</div>
		<div seq="11" style="margin:2px 5px;" onclick="aly.confField.aggr('avg')"><img src="<%=guideDir %>img/guide/42.png" aggr='avg' style="vertical-align:-4px;visibility:hidden;" />平均</div>
		<div seq="10" style="margin:2px 5px;" onclick="aly.confField.aggr('countd')"><img src="<%=guideDir %>img/guide/42.png" aggr='countd' style="vertical-align:-4px;visibility:hidden;" />值计数</div>
		<span seq="9" style="font-size:1px;display:block;width:100%;border-top:1px solid #AAA;margin:3px 0;"></span>
		<div seq="1" style="margin:2px 5px;" onclick="aly.confField.order(1)"><img src="<%=guideDir %>img/guide/42.png"  order="1" style="vertical-align:-4px;visibility:hidden;"/>升序</div>
		<div seq="2" style="margin:2px 5px" onclick="aly.confField.order(2)"><img src="<%=guideDir %>img/guide/42.png"  order="2" style="vertical-align:-4px;visibility:hidden;" />降序</div>
		<div seq="3" style="margin:2px 5px;" onclick="aly.confField.order(0)"><img src="<%=guideDir %>img/guide/42.png" style="vertical-align:-4px;visibility:hidden;" order="0"/>不排序</div>
		<span seq="4" style="font-size:1px;display:block;width:100%;border-top:1px solid #AAA;margin:3px 0;"></span>
		<div seq="5" style="margin:2px 5px;" onclick="aly.confField.edit()"><img src="<%=guideDir %>img/guide/48.png" style="vertical-align:-3px;" />编辑</div>
		<div seq="16" style="margin:2px 5px;" onclick="aly.confField.where()"><img src="<%=guideDir %>img/guide/49.png" style="vertical-align:-3px;" />切片过滤</div>
		<div seq="6" style="margin:2px 5px;" onclick="aly.confField.format()"><img src="<%=guideDir %>img/guide/50.png" style="vertical-align:-3px;padding-right:1px;width:15px;height:16px;" />显示格式</div>
		<div seq="18" style="margin:2px 5px;" onclick="aly.confField.editStyle()"><img src="<%=guideDir %>img/guide/51.png" style="vertical-align:-3px;padding-right:1px;width:15px;height:16px;" />显示值</div>
		<span seq="7" style="font-size:1px;display:block;width:100%;border-top:1px solid #AAA;margin:3px 0;"></span>
		<div seq="8" style="color:red;margin:2px 5px;" onclick="aly.confField.del()"><img src="<%=guideDir %>img/guide/13.png" style="vertical-align:-3px;" />删除</div>
	</div>
	<div id="calcFieldFloat" style="display:none;background-color:#F3F3F3;border: 1px solid #CCC;">
		<div seq="1" style="margin:2px 5px;" onclick="aly.calcField.edit()"><img src="<%=guideDir %>img/guide/31.png" style="vertical-align:-3px;" />编辑</div>
		<div seq="2" style="color:red;margin:2px 5px;" onclick="aly.calcField.del()"><img src="<%=guideDir %>img/guide/13.png" style="vertical-align:-3px;" />删除</div>
	</div>
	
	<div class='ui-layout-north' style="font-size:14px;position:absolute;top:0;border:0px;height:50px;z-index:30001;background-color:#41455A;width:100%;">
		<img src="<%=guideDir %>img/guide/47.png"/>

		<a href="javascript:saveOlap()" style="color:#DFDFE3;vertical-align:20px;text-decoration: none;"><img src="<%=guideDir %>img/guide/save.png" style="vertical-align:-7px;margin:0 10px;"/>保存</a>&nbsp;&nbsp;
		<a href="javascript:openOlap()" style="color:#DFDFE3;vertical-align:20px;text-decoration: none;"><img src="<%=guideDir %>img/guide/2.png" style="vertical-align:-7px;margin:0 10px;"/>打开</a>&nbsp;&nbsp;

		<a href="javascript:relocalReports(1)" style="color:#DFDFE3;vertical-align:20px;text-decoration: none;"><img src="<%=guideDir %>img/guide/8.png" style="vertical-align:-7px;margin:0 10px;"/>横向平铺</a>&nbsp;&nbsp;
		<a href="javascript:relocalReports(2)" style="color:#DFDFE3;vertical-align:20px;text-decoration: none;"><img src="<%=guideDir %>img/guide/34.png" style="vertical-align:-7px;margin:0 10px;"/>纵向平铺</a>&nbsp;&nbsp;
		<a href="javascript:relocalReports(3)" style="color:#DFDFE3;vertical-align:20px;text-decoration: none;"><img src="<%=guideDir %>img/guide/5.png" style="vertical-align:-7px;margin:0 10px;"/>重叠显示</a>&nbsp;&nbsp;

		<div id="queryStatus" style="float:right;padding-right:5px;margin:16px;color:#DFDFE3;"></div>
		<a href="javascript:refreshData()" style="display:none;float:right;color:#DFDFE3;vertical-align:20px;text-decoration: none;margin: 15px;" title=''>更新数据</a>
		<a href="javascript:manageDataSet()" id="dataSetBut" style="display:none;float:right;color:#DFDFE3;vertical-align:20px;text-decoration: none;margin: 15px;" title=''>数据集</a>
		<a href="javascript:dqlQueryInReport()" id="dqlQueryBut" style="float:right;color:#DFDFE3;vertical-align:20px;text-decoration: none;margin: 15px;display:none;" title=''>DQL查询</a>
		<div style="clear:both;"></div>
	</div>
	<div class='ui-layout-center' style="border:0px;"></div>
	<div id="analyseConf1" style="width: 493px;display:none;top: 60px; position: absolute; z-index: 20000; right: 20px;">
		<div id="analyseConf2" style="width:483px;height:22px;background-color:#FCA323;padding:4px 0 0 8px;">数据分析设置器<img src="<%=guideDir %>img/guide/45.png" style="cursor:pointer;padding:0 4px;vertical-align:-2px;"/><img src="<%=guideDir %>img/guide/20.png" style="cursor:pointer;padding:0 4px 4px;float:right;"/><div style="clear:both;"></div></div>
		<div id="analyseConf" style="width:491px;background-color:#EDF5FC;overflow:hidden;"></div>
	</div>
</div>

</body>
</html>

	