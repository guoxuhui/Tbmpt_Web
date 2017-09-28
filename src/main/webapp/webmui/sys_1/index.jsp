<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--[if IE 9 ]><html class="ie9"><![endif]-->
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
<meta name="format-detection" content="telephone=no">
<meta charset="UTF-8">

<meta name="description" content="Violate Responsive Admin Template">
<meta name="keywords" content="Super Admin, Admin, Template, Bootstrap">

<title>导向系统</title>

<!-- CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/animate.min.css" rel="stylesheet">
<link href="css/font-awesome.min.css" rel="stylesheet">
<link href="css/form.css" rel="stylesheet">
<link href="css/calendar.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="css/icons.css" rel="stylesheet">
<link href="css/generics.css" rel="stylesheet">
<style type="text/css">
	.red_color{background-color:red}
	.green_color{background-color:#73BF00}
</style>
</head>
<body id="skin-blur-violate">

	<header id="header" class="media">
		<a class="logo pull-left" href="index.jsp"> <img
			src="img/logo.png" alt="" />
		</a>

		<div class="media-body">
			<div class="media" id="top-menu">

				<div class="media-body">
					<div class="header-m1">
						<div class="clearfix"></div>
						<div class="attrs">项目部：<span id="xmMc"></span></div>
						<div class="block attrs">线路区间：<span id="qjxl"></span></div>
						<div class="block attrs">盾构设备：<span id="dgjMc"></span></div>
					</div>
					<div class="header-m2">
						<div class="clearfix"></div>
						<div class="attrs">项目经理：<span id="fzr"></span></div>
						<div class="block attrs">白班司机：<span id="baibsj"></span></div>
						<div class="block attrs">夜班司机：<span id="yebsj"></span></div>
					</div>
					<div class="header-m3">
						<div class="clearfix"></div>
						<div class="m-a">
							环号<br>
							<big style="font-size: 32px" id="huanhao">0</big>
						</div>
					</div>
					<div class="header-m4">
						<div class="clearfix"></div>
						<div class="attrs">区间总长：<span id="qjzc"></span></div>
						<div class="block attrs">主要地质：<span id="zydz"></span></div>
						<div class="block attrs">始发日期：<span id="kgrq"></span></div>
					</div>
					<div class="header-m5">
						<div class="clearfix"></div>
						<div class="attrs" id="status_j" style="text-align:center">掘进中</div>
						<div class="block attrs" id="status_p" style="text-align:center">拼装中</div>
						<div class="block attrs" id="status_t" style="text-align:center">停机中</div>
					</div>
					<div class="header-m6">
						<div class="clearfix"></div>
						<div class="attrs" style="text-align: center"><a href="#" id="baojing">【报警（<span id="baojingCount">2</span>）】</a></div>
						<div class="block attrs" style="text-align: center"  id="dataConnect">【数据连接中...】</div>
						<div class="block attrs" style="text-align: center">

							<div id="time">
								<span id="hours"></span> : <span id="min"></span> : <span
									id="sec"></span>
							</div>

						</div>
					</div>
					<!-- 链接按钮 -->
                    <div class="yl-an" style="z-index:1000">
                        <button class="btn btn-primary"  id="goBack" >返回主界面</button>
                    </div>
				</div>

			</div>
		</div>
	</header>

	<div class="clearfix"></div>

	<section id="main" class="p-relative" role="main">

		<!-- Sidebar -->


		<!-- Content -->
		<section id="content" class="container">


			<!-- Main Widgets -->

			<div class="block-area">
				<div class="row">

					<div class="col-md-4">
						<!-- Main Chart 折线图 -->
						<div class="tile">
							<h2 class="tile-title">导向系统</h2>

							<!--左侧参数-->
							<div class="dx1">

								<div class="chart-dx-0-info">
									<ul class="list-unstyled">
										<li>当前环数：<span id="TY_DXXT_0001">0</span>环</li>
									</ul>
								</div>
								<div class="chart-dx-1-info">
									<ul class="list-unstyled">
										<li class="m-b-10">掘进里程</li>
										<li class="m-b-10">342.00m</li>
									</ul>
								</div>
								<div class="chart-dx-2-info">
									<ul class="list-unstyled">
										<li class="m-b-10">左右推进油缸</li>
										<li></li>
										<li class="m-b-10">上下推进油缸</li>
										<li></li>
									</ul>
								</div>

							</div>


							<!--导向系统圈圈-->
							<div class="p-10" style="margin: 0 auto; width: 600px; text-align: center; position:relative;">

								<canvas id="myCanvas1" width="600" height="600"  style="position:absolute;left:-0px;top:-18px;"></canvas>
								<script>
								function  drawCoordinate(width,height){
                            		var canvas = document.getElementById("myCanvas1");
									var context = canvas.getContext("2d");
									
									var centerX = width/2,centerY = height/2;
									 //圆圈范围
									 var x = 220;
									 var r = 200;
									 for(var n=0;n<16;n++){
										 //cir(330+n*10,300,250-n*10,1-n*0.05,context);
										 cir(x,300,r,1-n*0.05,context);
										 x = x + 15;
										 r = r - (10-n*0.7)*2;
									 }
									 //cir(290,300,210,0.4,context);
									// 绘制横线
									 context.beginPath();
									 context.lineWidth="1";
									 context.strokeStyle='#ccc';
									 context.globalAlpha=0.5;
									 context.moveTo(100,300);
									 context.lineTo(500,300);
									 context.stroke(); // 进行绘制
									 
									 // 绘制竖线
									 context.beginPath();
									 context.lineWidth="1";
									 context.strokeStyle='#ccc';
									 context.globalAlpha=1;
									 context.moveTo(300,100);
									 context.lineTo(300,500);
									 context.stroke(); // 进行绘制
									
									//50  和 100 的范围
									 //画正方形区域范围
                                     context.beginPath();   // 50 
                                     context.strokeStyle='#fff';
                                     context.moveTo(centerX-100,centerY-100);
                                     context.lineTo(centerX+100,centerY-100);
                                     context.lineTo(centerX+100,centerY+100);
                                     context.lineTo(centerX-100,centerY+100);
                                     context.lineTo(centerX-100,centerY-100);
                                     context.stroke(); 
						 
                                     context.beginPath();  //100
                                     context.strokeStyle='#fff';
                                     context.lineWidth="1";
                                     context.moveTo(centerX-200,centerY-200);
                                     context.lineTo(centerX+200,centerY-200);
                                     context.lineTo(centerX+200,centerY+200);
                                     context.lineTo(centerX-200,centerY+200);
                                     context.lineTo(centerX-200,centerY-200);
                                     context.stroke(); 
									
									
									
									
									var xCells = 50,//x轴显示几个单元格
									yCells = 50,//y轴显示几个单元格
									xWidth = 400,//x轴显示宽度
									yWidth = 400,//y轴显示宽度
									stepX = parseInt(xWidth/xCells), //步长
									stepY = parseInt(yWidth/yCells), //步长
									startX = 0,//初始坐标点
									startY = 0,//初始坐标点
									xSpacing = 35,//x轴距容器底部边距
									ySpacing = 15,//y轴距容器左侧边距
									xScaleNum = -5,//x轴刻度开始
									yScaleNum = 5,//x轴刻度开始
									xLineBegin = 100,//x轴线开始坐标(xLineBegin,height-xSpacing)
									yLineBegin = 100;//y轴线开始坐标(ySpacing,yLineBegin)
									//x轴坐标线
									context.beginPath();
									context.strokeStyle='#ccc';
									context.moveTo(xLineBegin,height-xSpacing);  //x开始坐标25
									context.lineTo(xLineBegin+xWidth,height-xSpacing);
									context.stroke();   
									//纵坐标轴
									for(i= 0 ;i < xCells ;i++){
										  if(i % 5 == 0){
											  context.moveTo(xLineBegin,height-xSpacing+5);
											  context.lineTo(xLineBegin,height-xSpacing-5);
											  //横坐标的数字
											  context.fillStyle='#ccc';
											  context.font = "12px Microsoft Yahei";
											  context.fillText(xScaleNum, xLineBegin-2,height-xSpacing+15);
											  xScaleNum = xScaleNum + 1;
										  }else{
											 context.moveTo(xLineBegin,height-xSpacing);
											 context.lineTo(xLineBegin,height-xSpacing-3);
										  }
										  context.stroke();   
										  xLineBegin = xLineBegin + stepX;
									 }
									 //横坐标轴
									 context.beginPath();
									 context.strokeStyle='#ccc';
									 context.moveTo(ySpacing,yLineBegin);
									 context.lineTo(ySpacing,yLineBegin+yWidth);
									 context.stroke();   
									 for(i= 0 ;i < yCells;i++){
										  if(i % 5 == 0){
											 context.moveTo(ySpacing-5,yLineBegin);
											 context.lineTo(ySpacing+5,yLineBegin);
											 //横坐标的数字
											 context.fillStyle='#ccc';
											 context.font = "12px Microsoft Yahei";
											 context.fillText(yScaleNum, ySpacing-15,yLineBegin+5);
											 yScaleNum = yScaleNum - 1;
										  }else{
											  context.moveTo(ySpacing,yLineBegin);
											  context.lineTo(ySpacing+5,yLineBegin);
										  }
										  context.stroke();   
										  yLineBegin = yLineBegin + stepY;
									 }
									 
									 
									//纵轴线坐标
                                     context.textAlign="100";context.fillText("100",  centerX+5,(centerY -100)/2);
                                     context.textAlign="50";context.fillText("50",    centerX+5,centerY-100);
                                     context.textAlign="-50";context.fillText("-50",  centerX+5,centerY+100);
                                     context.textAlign="-100";context.fillText("-100",centerX+5,centerY+200);
                                     context.stroke(); // 进行context
                                     //横轴县坐标
                                     context.textAlign="-100";context.fillText("-100",centerX-200,centerY-5);
                                     context.textAlign="-50";context.fillText("-50",centerX-100,centerY-5);
                                     context.textAlign="50";context.fillText("50",centerX+100,centerY-5);
                                     context.textAlign="100";context.fillText("100",centerX+200,centerY-5);
                                     context.stroke(); // 进行绘制
                            	}
								function cir(x,y,r,a,ctx){
								     ctx.beginPath();
                                     ctx.arc(x,y,r,0,2*Math.PI);
								 	 ctx.strokeStyle='#ccc';
									 ctx.globalAlpha=a;
                                     ctx.stroke();
								     //ctx.closePath();
								}
                           		window.onload = function(){
											//横线与竖线的间隔距离
											drawCoordinate(600,600);
								};

					   </script>


								<canvas id="myCanvas" width="500" height="500"></canvas>
								<script>
									function drawPlaneZi(x1,y1,x2,y2){
                                         var c=document.getElementById("myCanvas1");
                                         var ctx=c.getContext("2d");
                                         x1 = x1*2;
                                         y1 = y1*2;
                                         x2 = x2*2;
                                         y2 = y2*2;
                              
										 ///----------------------------飞机    开始--------------------------------------
										 /* var step = 250;
                                         var tX = step + startX, tY = step - startY;   //分机头控制
										 var eX = step + endX  , eY = step - endY;   //飞机尾部控制 308 361用来计算偏移量
										 var pX = eX - 308, pY = eY - 361;// px = 160+x2-308,py=160-y2-361;   偏移量
                                         ctx.globalAlpha=1;
                                         ctx.beginPath();//1
                                         ctx.fillStyle="#F2D761";
										 ctx.moveTo(tX,tY);
										 ctx.lineTo(step+28 + pX,step+67 -pY);
										 ctx.lineTo(step+48 + pX,step+81 -pY );
										 ctx.fill();
										 
										 ctx.beginPath();//2
                                         ctx.fillStyle="#D0983A";
										 ctx.moveTo(tX,tY);
										 ctx.lineTo(step+48 + pX,step+81 -pY);
										 ctx.lineTo(eX,eY);
										 ctx.fill();
										 
										 ctx.beginPath();//3
                                         ctx.fillStyle="#7A4A26";
										 ctx.moveTo(tX,tY);
										 ctx.lineTo(eX,eY);
										 ctx.lineTo(step+58 + pX,step+87 -pY);
										 ctx.fill();
										 
										 ctx.beginPath();//4
                                         ctx.fillStyle="#F3CC57";
										 ctx.moveTo(tX,tY);
										 ctx.lineTo(step+58 + pX,step+87 -pY);
										 ctx.lineTo(step+93 + pX,step+107-pY);
										 ctx.fill(); */
										 ////---------------------分机   结束----------------------------------
										 //知道开始坐标  在计算四个点
										 var step = 300;
										 var beishu = 60;
                                         var startX = step + x1, startY = step - y1; //(startX,startY)
                                         var endX = step + x2  , endY = step - y2;   //(endX,endY)
                                         var sX = endX,sY = endY - beishu;
                                         var xX = endX,xY = endY + beishu;
                                         var zX = endX -beishu,zY = endY;
                                         var yX = endX +beishu,yY = endY;
                                         
                                        // cir(startX,startY,2,1,ctx);
                                        // cir(endX,endY,2,1,ctx);
                                         
                                         
                                         ctx.globalAlpha=1;
                                         ctx.beginPath();//1
                                         ctx.fillStyle="#bfbfc1";
										 ctx.moveTo(startX,startY);
										 ctx.lineTo(endX,endY);
										 ctx.lineTo(xX,xY);
										 ctx.fill();
										 
										 ctx.beginPath();//2
                                         ctx.fillStyle="#f4f4f4";
										 ctx.moveTo(startX,startY);
										 ctx.lineTo(endX,endY);
										 ctx.lineTo(zX,zY);
										 ctx.fill();
										 
										 ctx.beginPath();//3
                                         ctx.fillStyle="#bfbfc1";
										 ctx.moveTo(startX,startY);
										 ctx.lineTo(endX,endY);
										 ctx.lineTo(sX,sY);
										 ctx.fill();
										 
										 ctx.beginPath();//4
                                         ctx.fillStyle="#f4f4f4";
										 ctx.moveTo(startX,startY);
										 ctx.lineTo(endX,endY);
										 ctx.lineTo(yX,yY);
										 ctx.fill(); 
										 
										//canvas 是基于状态的绘制，而不是对象
										 ctx.beginPath();
                                         ctx.moveTo(startX,startY);
                                         ctx.lineTo(endX,endY);
                                         ctx.lineWidth = 1;//线条的宽度
                                         ctx.strokeStyle = "blue";//线条的颜色
                                         ctx.stroke();//绘制
										 
									}  
                                         
                                      </script>

								<div class="gdj" style="left:-40px; bottom: -40px;">
                                    <div class="bk_blue2">俯仰角</div><br>
                                    <span class="bk_blue" style="width:120px">
                                    	<span  id="TY_DXXT_0003" style="color:#fff">1</span>
                                    	mm/m
                                    </span>
                                </div>
								<div class="gdj1">
                                     <div class="bk_blue1">滚动角</div>
                                     <span class="bk_blue3" style="width:100px">
                                     	<span  id="TY_DXXT_0004" style="color:#fff"></span>
                                     	mm/m
                                     </span>
                                </div>

							</div>


							<!--右侧参数-->
							<div class="dx2">

								<div class="chart-dx-3-info">
									<ul class="list-unstyled">
										<li class="m-b-10 z-fd">
                                        <div class="bk_blue1">盾尾</div>
                                        <div class="bk_blue" id="TY_DXXT_0008"  style="border:0px">0</div>
										</li>
										<li class="m-b-10 y-fd">
                                        <div class="bk_blue1">切口</div>
                                        <div class="bk_blue" id="TY_DXXT_0007"  style="border:0px">0</div>
										</li>
										<li class="m-b-10"></li>
										<li class="m-b-10 w-fd">
                                        <div class="bk_blue1">水平偏航(mm)</div>
                                        <div class="bk_blue"  style="border:0px"><span id="TY_DXXT_0010">0</span></div>
										</li>
									</ul>
								</div>
								<div class="chart-dx-4-info">
									<ul class="list-unstyled">
										<li class="m-b-10 z-fd">
                                        <div class="bk_blue1">盾尾</div> 
                                        <div class="bk_blue" id="TY_DXXT_0006"  style="border:0px">0</div>
										</li>
										<li class="m-b-10 y-fd">
                                        <div class="bk_blue1">切口</div> 
                                        <div class="bk_blue" id="TY_DXXT_0005"  style="border:0px">0</div>
										</li>
										<li class="m-b-10"></li>
										<li class="m-b-10 w-fd">
										<div class="bk_blue1">垂直偏航(mm)</div>
                                        <div class="bk_blue"  style="border:0px"><span id="TY_DXXT_0009">0</span></div>
										</li>
									</ul>
								</div>
								<div class="chart-dx-5-info">
									<table>
										<tbody>
											<tr>
												<td>切口里程</td>
												<td>盾尾里程</td>
												<td>掘进进度</td>
											</tr>
											<tr>
												<td>0</td>
												<td>0</td>
												<td>0%</td>
											</tr>
										</tbody>
									</table>
									<ul class="list-unstyled">
										<li></li>
									</ul>
								</div>
                                
                                

							</div>

						</div>
					</div>

					、
				</div>

			</div>
			</div>


		</section>

		<!-- Older IE Message -->
		<!--[if lt IE 9]>
                <div class="ie-block">
                    <h1 class="Ops">Ooops!</h1>
                    <p>You are using an outdated version of Internet Explorer, upgrade to any of the following web browser in order to access the maximum functionality of this website. </p>
                    <ul class="browsers">
                        <li>
                            <a href="https://www.google.com/intl/en/chrome/browser/">
                                <img src="img/browsers/chrome.png" alt="">
                                <div>Google Chrome</div>
                            </a>
                        </li>
                        <li>
                            <a href="http://www.mozilla.org/en-US/firefox/new/">
                                <img src="img/browsers/firefox.png" alt="">
                                <div>Mozilla Firefox</div>
                            </a>
                        </li>
                        <li>
                            <a href="http://www.opera.com/computer/windows">
                                <img src="img/browsers/opera.png" alt="">
                                <div>Opera</div>
                            </a>
                        </li>
                        <li>
                            <a href="http://safari.en.softonic.com/">
                                <img src="img/browsers/safari.png" alt="">
                                <div>Safari</div>
                            </a>
                        </li>
                        <li>
                            <a href="http://windows.microsoft.com/en-us/internet-explorer/downloads/ie-10/worldwide-languages">
                                <img src="img/browsers/ie.png" alt="">
                                <div>Internet Explorer(New)</div>
                            </a>
                        </li>
                    </ul>
                    <p>Upgrade your browser for a Safer and Faster web experience. <br/>Thank you for your patience...</p>
                </div>   
            <![endif]-->
	</section>

	<!-- Javascript Libraries -->
	<!-- jQuery -->
	<script src="js/jquery.min.js"></script>
	<!-- jQuery Library -->
	<script src="js/jquery-ui.min.js"></script>
	<!-- jQuery UI -->
	<script src="js/jquery.easing.1.3.js"></script>
	<!-- jQuery Easing - Requirred for Lightbox + Pie Charts-->

	<!-- Bootstrap -->
	<script src="js/bootstrap.min.js"></script>

	<!-- Charts -->
	<script src="js/charts/jquery.flot.js"></script>
	<!-- Flot Main -->
	<script src="js/charts/jquery.flot.time.js"></script>
	<!-- Flot sub -->
	<script src="js/charts/jquery.flot.animator.min.js"></script>
	<!-- Flot sub -->
	<script src="js/charts/jquery.flot.resize.min.js"></script>
	<!-- Flot sub - for repaint when resizing the screen -->
	<script src="js/charts/jquery.flot.pie.min.js"></script>
	<!-- Flot Pie chart -->

	<script src="js/sparkline.min.js"></script>
	<!-- Sparkline - Tiny charts -->
	<script src="js/easypiechart.js"></script>
	<!-- EasyPieChart - Animated Pie Charts -->
	<script src="js/charts.js"></script>
	<!-- All the above chart related functions -->

	<!-- Map -->
	<script src="js/maps/jvectormap.min.js"></script>
	<!-- jVectorMap main library -->
	<script src="js/maps/usa.js"></script>
	<!-- USA Map for jVectorMap -->

	<!--  Form Related -->
	<script src="js/icheck.js"></script>
	<!-- Custom Checkbox + Radio -->

	<!-- UX -->
	<script src="js/scroll.min.js"></script>
	<!-- Custom Scrollbar -->

	<!-- Other -->
	<script src="js/calendar.min.js"></script>
	<!-- Calendar -->
	<script src="js/feeds.min.js"></script>
	<!-- News Feeds -->


	<!-- All JS functions -->
	<script src="js/functions.js"></script>
	
	
	<script type="text/javascript" src="../js/jzk.tbm.js"/></script>
 	<script src="js/functions.js"></script>
	<!-- All JS functions -->
	<script type="text/javascript">
		//获取项目信息
		var xiangmuId = getQueryString("xmId");
		var dgjId = getQueryString("tbmId");
		var xlId = getQueryString("xlId");
		var xianluId = getQueryString("xlId");
		$('#baojing').click(function(){
			location.href = '../sys_3/index.jsp?xmId='+xiangmuId+'&tbmId='+dgjId;
		});
	</script>
	<!-- 加载数据所需js -->
    <script src="../layui/layui.js" type="text/javascript"></script>
    <script src="../js/jzk.tbm.js"></script>
  	<script type="text/javascript">
		layui.use([ 'layer' ], function() {
		});
	</script>
    <script src="../js/zixitong.common.js"></script>
	
</body>
</html>
