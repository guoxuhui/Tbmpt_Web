<%@page import="com.crfeb.tbmpt.commons.utils.WebUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!doctype html>
<html>

<head>
<meta charset="UTF-8">
<title>导向系统参数</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link href="${staticPath}/static/open/css/mui.min.css?v=20170914" rel="stylesheet" />
<style>
.title {
	margin: 20px 15px 10px;
	color: #6d6d72;
	font-size: medium;
}

.mui-input-row {
	font-size: small;
}

.mui-input-clear {
	font-size: small;
}
</style>
</head>

<body class="mui-android">
		<!--
		<header class="mui-bar mui-bar-nav">
	    <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
	    <h1 class="mui-title">监控列表</h1>
		</header>
		-->
	<div class="mui-content">
		<div id="hd" style="" class="mui-card">
			<h2 class="title">
				项目概况
			</h2>
			<div>


				<div class="mui-input-group">
			
					<c:choose>
    <c:when test="${type == 0}">
       <div class="mui-input-row mui-select">
						<label>项目名称：</label> 
						<select id="pro" name="pro"
							style="font-size: small">
							<c:forEach items="${proList}" var="pro">
								<c:choose>
									<c:when test="${pro.id == proId}">
										<option value="${pro.id}" selected="selected">${pro.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${pro.id}">${pro.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
    </c:when>
    <c:otherwise>
        <div class="mui-input-row">
						<label>项目名称：</label> 
						<select id="pro" name="pro"
							style="font-size: small" disabled="disabled"
							>
							<c:forEach items="${proList}" var="pro">
								<option value="${pro.id}">${pro.name}</option>
							</c:forEach>
						</select>
					</div>
    </c:otherwise>
</c:choose>
					
					
					<div class="mui-input-row">
						<label>负责人：</label>
						<%-- <input type="text" class="mui-input-clear"
							id="empNmae" name="empName" value="${fuzeren.name}"
							disabled="true"> --%>
						<select id="empName" name="empName" style="font-size: small"
							disabled="disabled">
							<c:forEach items="${proList}" var="pro">
								<option value="${pro.empName}" class="${pro.id}">${pro.empName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="mui-input-row mui-select">
						<label>区间：</label> <select id="section" name="section"
							style="font-size: small">
							<c:forEach items="${proList}" var="pro">
								<c:forEach items="${pro.proSecs}" var="sec">
									<option value="${sec.id}" class="${pro.id}">${sec.name}</option>
								</c:forEach>
							</c:forEach>
						</select>
					</div>

					<div class="mui-input-row mui-select">
						<label>线路：</label> <select id="line" name="line"
							style="font-size: small">
							<c:forEach items="${proList}" var="pro">
								<c:forEach items="${pro.proSecs}" var="sec">
									<c:forEach items="${sec.secLines}" var="line">
										<option value="${line.id}" class="${sec.id}">${line.name}</option>
									</c:forEach>
								</c:forEach>
							</c:forEach>
						</select>
					</div>

					<div class="mui-input-row">
						<label>盾构机名称：</label> <input type="text" class="mui-input-clear"
							value="" disabled="true" id="tbmName">
					</div>

					<div class="mui-input-row">
						<label>盾构机状态：</label> <input type="text" class="mui-input-clear"
							value="运行中" id="tbmZhuangTai">
					</div>
				</div>
			</div>
		</div>
<div id="Gallery" class="mui-slider">
			<div class="mui-slider-group">	
</div>
				</div>			
				<div class="mui-slider-item">

					<div id="fd" class="mui-card" style="height: 80%;">
						<h2 class="title">
							导向系统
						</h2>

						<div style="font-size: 10px; padding: 10px; padding-bottom: 20px;">
							<table width="100%" class="mui-table">
								<tr>
									<td>掘进环数</td>
									<td style="color: blue;"><span id="jueJinHuanShu2">0</span><span
										style="color: black;"> 环</span></td>
									<td>导向里程</td>
									<td style="color: blue;"><span id="jueJinLiCheng">0</span><span
										style="color: black;"> m</span></td>
								</tr>
								<tr>

									<td>水平前点</td>
									<td style="color: blue;"><span id="shuiPingQianDian">0</span>
										<span style="color: black;"> mm</span></td>

									<td>垂直前点</td>
									<td style="color: blue;"><span id="chuiZhiQianDian">0</span>
										<span style="color: black;"> mm</span></td>

								</tr>
								<tr>
									<td>水平后点</td>
									<td style="color: blue;"><span id="shuiPingHouDian">0</span>
										<span style="color: black;"> mm</span></td>

									<td>垂直后点</td>
									<td style="color: blue;"><span id="chuiZhiHouDian">0</span>
										<span style="color: black;"> mm</span></td>

								</tr>

							</table>
						</div>

						<div style="text-align: center;">
							<canvas id="canvas_dx"></canvas>
						</div>
					</div>

				</div>
				
		
		
			</div>
		</div>

	</div>
	<script src="${staticPath}/static/open/js/zepto.min.js"></script>
	<script src="${staticPath}/static/open/js/selector.js"></script>
	<script src="${staticPath}/static/open/js/jquery.chained.js"></script>
	<script src="${staticPath}/static/open/js/mui.min.js"></script>
	<script src="${staticPath}/static/open/js/draw.js?v=20170915"></script>

	<script type="text/javascript" id="draw">
		mui.init()
	</script>
	<script>
		//新建推进系统，导向系统画布
		var daoxiangSystem = new DaoXiangSystem('canvas_dx');

		//保持两个div高度一致

		var int = '';
		var tbmId = '';

		function getJQSJ(id) {
			mui
					.ajax(
							'${staticPath}/open/jqsj/getjqsj',
							{
								data : {
									id : tbmId
								},
								dataType : 'json', //服务器返回json格式数据
								type : 'get', //HTTP请求类型
								success : function(data) {

									//俯仰角和滚动角数值
									var fyValue, gdValue;
									//土压
									var value1, value2, value3, value4, value5;

									//纸飞机坐标
									var hx, hy, fx, fy;
									for ( var i in data.obj) {
										var id = data.obj[i].dwid;
										
										//掘进里程
										if ("TY_DXXT_0002" == id) {
											$("#jueJinLiCheng")
													.text(
															Math
																	.round(data.obj[i].tagvalue * 100) / 100);
										}
										//掘进环数
										else if ("TY_DXXT_0001" == id) {
											$("#jueJinHuanShu2").text(
													data.obj[i].tagvalue);
										}
										//俯仰角
										else if ("TY_DXXT_0003" == id) {
											fyValue = data.obj[i].tagvalue;
										}
										//滚动角
										else if ("TY_DXXT_0004" == id) {
											gdValue = data.obj[i].tagvalue;
										}
										//导向水平前点
										else if ("TY_DXXT_0007" == id) {
											hx = data.obj[i].tagvalue;
										}
										//导向垂直前点
										else if ("TY_DXXT_0005" == id) {
											hy = data.obj[i].tagvalue;
										}
										//导向水平后点
										else if ("TY_DXXT_0008" == id) {
											fx = data.obj[i].tagvalue;
										}
										//导向垂直后点
										else if ("TY_DXXT_0006" == id) {
											fy = data.obj[i].tagvalue;
										}
										
										//盾构机状态
										else if ("TY_ZGZT_0001" == id) {
											if (data.obj[i].tagvalue == "1") {
												$("#tbmZhuangTai").val("掘进开始");
											}
										} else if ("TY_ZGZT_0002" == id) {
											if (data.obj[i].tagvalue == "1") {
												$("#tbmZhuangTai").val("管片拼装");
											}
										} else if ("TY_ZGZT_0003" == "1") {
											if (data.obj[i].tagvalue == true) {
												$("#tbmZhuangTai").val("停机模式");
											}
										}
										//贯入度
										else if ("TY_TJXT_0005" == id) {
											$("#daoPanNiuJu").text(
													data.obj[i].tagvalue);
										}
										//水平趋向
										else if ("TY_DXXT_0010" == id) {
											$("#shuiPingQuXiang").text(
													data.obj[i].tagvalue);
										}
										//垂直趋向
										else if ("TY_DXXT_0009" == id) {
											$("#chuiZhiQuXiang").text(
													data.obj[i].tagvalue);
										}

									}

									/**画布设置*/
									//清空画布
									daoxiangSystem.clearCanvas();
									//导向系统画圆
									daoxiangSystem.drawCircle();
									//导向系统画XY轴
									daoxiangSystem.drawXYZhou();
									//导向系统画红色虚线矩形
									daoxiangSystem.drawRect();
									//导向系统画俯仰角和滚动角
									daoxiangSystem.drawFYJandGDJ();

									//导向系统画刻度数值
									daoxiangSystem.drawFYJandGDJValue(fyValue,
											gdValue)

									//设置水平盾尾和切口
									$("#shuiPingHouDian").text(
											Math.round(fx * 100) / 100);
									$("#shuiPingQianDian").text(
											Math.round(hx * 100) / 100);
									//设置垂直盾尾和切口
									$("#chuiZhiHouDian").text(
											Math.round(fy * 100) / 100);
									$("#chuiZhiQianDian").text(
											Math.round(hy * 100) / 100);
									//替换成纸飞机显示
									//						daoxiangSystem.drawQK(hx, hy);
									//						daoxiangSystem.drawDw(fx, fy);
									//						daoxiangSystem.drawFx(hx, hy, fx, fy);
									daoxiangSystem.drawAirPlane(hx, hy, fx, fy);

								},
								error : function(xhr, type, errorThrown) {
									//异常处理；
									console.log(type);
								}
							});
		}
		var selected = $("#line").children('option:selected').val()
		mui.ajax('${staticPath}/open/jqsj/getTbmInfo', {
			data : {
				lineId : selected
			},
			dataType : 'json', //服务器返回json格式数据
			type : 'get', //HTTP请求类型
			success : function(data) {
				if (data.success == false) {
					$("#tbmName").val(data.msg);
					$("#Gallery").hide();
				} else {
					$("#Gallery").show();
					$("#tbmName").val(data.tbmName);
					tbmId = data.id;
					getJQSJ(tbmId);
					clearInterval(int);
					int = setInterval(getJQSJ, 30000);
				}
			},
			error : function(xhr, type, errorThrown) {
				//异常处理；
				console.log(type);
			}
		});
		$("#line").change(function() {
			var selected = $(this).children('option:selected').val()
			mui.ajax('${staticPath}/open/jqsj/getTbmInfo', {
				data : {
					lineId : selected
				},
				dataType : 'json', //服务器返回json格式数据
				type : 'get', //HTTP请求类型
				success : function(data) {
					if (data.success == false) {
						$("#tbmName").val(data.msg);
						$("#Gallery").hide();
					} else {
						$("#Gallery").show();
						$("#tbmName").val(data.tbmName);
						tbmId = data.id;
						getJQSJ(tbmId);
						clearInterval(int);
						int = setInterval(getJQSJ, 30000);
					}

				},
				error : function(xhr, type, errorThrown) {
					//异常处理；
					console.log(type);
				}
			});
		});

		$("#section").chained("#pro");
		$("#line").chained("#section");
		$("#empName").chained("#pro");
	</script>
</body>

</html>