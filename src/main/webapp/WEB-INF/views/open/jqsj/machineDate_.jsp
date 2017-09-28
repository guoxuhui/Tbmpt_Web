<%@page import="com.crfeb.tbmpt.commons.utils.WebUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!doctype html>
<html>

<head>
<meta charset="UTF-8">
<title>盾构掘进参数</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link href="${staticPath}/static/open/css/mui.min.css" rel="stylesheet" />
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

		<header class="mui-bar mui-bar-nav">
	    <a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
	    <h1 class="mui-title">机器数据</h1>
		</header>

	<div class="mui-content">
		<div id="hd" style="" class="mui-card">
			<h2 class="title">
				<span class="mui-icon mui-icon-home"></span>项目概况
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
							value="数据连接中" disabled="true" id="tbmZhuangTai">
					</div>
				</div>
			</div>
		</div>

		<div id="Gallery" class="mui-slider" style="margin-top: 15px;">
			<div class="mui-slider-group">
				<div class="mui-slider-item">
					<div id="bd" class="mui-card" style="height: 80%;">
						<h2 class="title">
							<span class="mui-icon mui-icon-flag"></span>推进系统
						</h2>

						<div style="font-size: 10px; padding: 10px; padding-bottom: 20px;">
							<table width="100%" class="mui-table">
								<tr>
									<td>掘进环数：</td>
									<td style="color: blue;"><span id="jueJinHuanShu1">0</span>
										<span style="color: black;"> 环</span></td>
									<td>推进压力：</td>
									<td style="color: blue;"><span id="tuiJinYaLi">0</span> <span
										style="color: black;"> bar</span></td>
								</tr>
								<tr>
									<td>推进速度：</td>
									<td style="color: blue;"><span id="tuiJinSuDu">0</span> <span
										style="color: black;"> mm/min</span></td>
									<td>总推进力：</td>
									<td style="color: blue;"><span id="zongTuiJinLi">0</span>
										<span style="color: black;"> kN</span></td>
								</tr>

								<tr>
									<td>刀盘转速：</td>
									<td style="color: blue;"><span id="daoPanZhuanSu">0</span>
										<span style="color: black;"> r/min</span></td>
									<td>刀盘扭矩：</td>
									<td style="color: blue;"><span id="daoPanNiuJu">0</span> <span
										style="color: black;"> kN·m</span></td>
								</tr>

								<tr>
									<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;贯入度：</td>
									<td style="color: blue;"><span id="guanRuDu">0</span> <span
										style="color: black;"> mm/rev</span></td>
								</tr>


							</table>
						</div>

						<table width="100%" style="background-color: white;">
							<tr>
								<td width="20%" style="text-align: center;">
									<div style="font-size: 8px;">
										C 组推进行程<br /> <span style="font-size: 8px; color: blue;"
											id="CZuTuiJinWeiYi">0</span> mm
									</div>
									<div style="font-size: 8px;">
										C 组推进压力 <br /> <span style="font-size: 8px; color: blue;"
											id="CZuTuiJinYaLi">0</span> bar
									</div>
								</td>
								<td width="60%" style="text-align: center;" id="canvas_td">
									<div
										style="width: 100%; height: 100%; background-color: white; text-align: center;">

										<div style="font-size: 8px;">
											D 组推进行程 <span style="font-size: 8px; color: blue;"
												id="DZuTuiJinWeiYi">0</span> mm
										</div>
										<div style="font-size: 8px;">
											D 组推进压力 <span style="font-size: 8px; color: blue;"
												id="DZuTuiJinYaLi">0</span> bar
										</div>

										<canvas id="canvas" width="100%" height="50%">
			
								</canvas>

										<div style="font-size: 8px;">
											B 组推进行程 <span style="font-size: 8px; color: blue;"
												id="BZuTuiJinWeiYi">0</span> mm
										</div>
										<div style="font-size: 8px;">
											B 组推进压力 <span style="font-size: 8px; color: blue;"
												id="BZuTuiJinYaLi">0</span> bar
										</div>
									</div>
								</td>
								<td width="20%" style="text-align: center;">
									<div style="font-size: 8px;">
										A 组推进行程 <br /> <span style="font-size: 8px; color: blue;"
											id="AZuTuiJinWeiYi">0</span> mm
									</div>
									<div style="font-size: 8px;">
										A 组推进压力<br /> <span style="font-size: 8px; color: blue;"
											id="AZuTuiJinYaLi">0</span> bar
									</div>
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="mui-slider-item">

					<div id="fd" class="mui-card" style="height: 80%;">
						<h2 class="title">
							<span class="mui-icon mui-icon-paperplane"></span>导向系统
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

								<tr>
									<td>水平趋向</td>
									<td style="color: blue;"><span id="shuiPingQuXiang">0</span>
										<span style="color: black;"> mm/m</span></td>

									<td>垂直趋向</td>
									<td style="color: blue;"><span id="chuiZhiQuXiang">0</span>
										<span style="color: black;"> mm/m</span></td>

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
	<script src="${staticPath}/static/open/js/draw.js"></script>

	<script type="text/javascript" id="draw">
		mui.init()
	</script>
	<script>
		//新建推进系统，导向系统画布
		var tuiJinSystem = new TuiJinSystem('canvas');
		var daoxiangSystem = new DaoXiangSystem('canvas_dx');

		//保持两个div高度一致
		if (document.getElementById("bd").clientHeight < document
				.getElementById("fd").clientHeight) {

			document.getElementById("bd").style.height = document
					.getElementById("fd").offsetHeight
					+ "px";

		} else {

			document.getElementById("fd").style.height = document
					.getElementById("bd").offsetHeight
					+ "px";

		}

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
										//A组推进油缸压力
										if ("TY_TJXT_0008" == id) {
											$("#AZuTuiJinYaLi")
													.text(
															Math
																	.round(data.obj[i].tagvalue * 100) / 100);
										}
										//B组推进油缸压力
										else if ("TY_TJXT_0009" == id) {
											$("#BZuTuiJinYaLi")
													.text(
															Math
																	.round(data.obj[i].tagvalue * 100) / 100);
										}
										//C组推进油缸压力
										else if ("TY_TJXT_0010" == id) {
											$("#CZuTuiJinYaLi")
													.text(
															Math
																	.round(data.obj[i].tagvalue * 100) / 100);
										}
										//D组推进油缸压力
										else if ("TY_TJXT_0011" == id) {
											$("#DZuTuiJinYaLi")
													.text(
															Math
																	.round(data.obj[i].tagvalue * 100) / 100);
										}
										//A组推进位移
										else if ("TY_TJXT_0012" == id) {
											$("#AZuTuiJinWeiYi")
													.text(
															Math
																	.round(data.obj[i].tagvalue * 100) / 100);
										}
										//B组推进位移
										else if ("TY_TJXT_0013" == id) {
											$("#BZuTuiJinWeiYi")
													.text(
															Math
																	.round(data.obj[i].tagvalue * 100) / 100);
										}
										//C组推进位移
										else if ("TY_TJXT_0014" == id) {
											$("#CZuTuiJinWeiYi")
													.text(
															Math
																	.round(data.obj[i].tagvalue * 100) / 100);
										}
										//D组推进位移
										else if ("TY_TJXT_0015" == id) {
											$("#DZuTuiJinWeiYi")
													.text(
															Math
																	.round(data.obj[i].tagvalue * 100) / 100);
										}
										//推进速度
										else if ("TY_TJXT_0002" == id) {
											$("#TY_TJXT_0002")
													.text(
															Math
																	.round(data.obj[i].tagvalue * 100) / 100);
										}
										//推进压力
										else if ("TY_TJXT_0001" == id) {
											$("#tuiJinYaLi")
													.text(
															Math
																	.round(data.obj[i].tagvalue * 100) / 100);
										}
										//总推进力
										else if ("TY_TJXT_0003" == id) {
											$("#zongTuiJinLi")
													.text(
															Math
																	.round(data.obj[i].tagvalue * 100) / 100);
										}
										//掘进里程
										else if ("TY_DXXT_0002" == id) {
											$("#jueJinLiCheng")
													.text(
															Math
																	.round(data.obj[i].tagvalue * 100) / 100);
										}
										//掘进环数
										else if ("TY_DXXT_0001" == id) {
											$("#jueJinHuanShu1").text(
													data.obj[i].tagvalue);
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
										//一号土仓压力
										else if ("TY_TCYL_0001" == id) {
											value1 = data.obj[i].tagvalue;
										}
										//二号土仓压力
										else if ("TY_TCYL_0002" == id) {
											value2 = data.obj[i].tagvalue;
										}
										//三号土仓压力
										else if ("TY_TCYL_0003" == id) {
											value3 = data.obj[i].tagvalue;
										}
										//四号土仓压力
										else if ("TY_TCYL_0004" == id) {
											value4 = data.obj[i].tagvalue;
										}
										//五号土仓压力
										else if ("TY_TCYL_0005" == id) {
											value5 = data.obj[i].tagvalue;
										}
										//刀盘转速
										else if ("TY_TJXT_0004" == id) {
											$("#daoPanZhuanSu").text(
													data.obj[i].tagvalue);
										}
										//贯入度
										else if ("TY_TJXT_0007" == id) {
											$("#guanRuDu").text(
													data.obj[i].tagvalue);
										}
										//盾构机状态
										else if ("TY_ZGZT_0001" == id) {
											if (data.obj[i].tagvalue == true) {
												$("#tbmZhuangTai").text("掘进开始");
											}
										} else if ("TY_ZGZT_0002" == id) {
											if (data.obj[i].tagvalue == true) {
												$("#tbmZhuangTai").text("管片拼装");
											}
										} else if ("TY_ZGZT_0003" == id) {
											if (data.obj[i].tagvalue == true) {
												$("#tbmZhuangTai").text("停机模式");
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
									tuiJinSystem.clearCanvas();
									daoxiangSystem.clearCanvas();
									//推进系统画圆
									tuiJinSystem.drawCircle();
									//推进系统画土压bar单位
									tuiJinSystem.drawBar();
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
									//推进系统画中间土压数值

									tuiJinSystem.drawValue(value1, value2,
											value3, value4, value5);

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