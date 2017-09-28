<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<!--[if IE 9 ]><html class="ie9"><![endif]-->
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
        <meta name="format-detection" content="telephone=no">
        <meta charset="UTF-8">

        <meta name="description" content="Violate Responsive Admin Template">
        <meta name="keywords" content="Super Admin, Admin, Template, Bootstrap">

        <title>相关参数</title>
            
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
.red_color {
	background-color: red
}

.green_color {
	background-color: #73BF00
}
</style>
</head>
<body id="skin-blur-violate" onload="show_category();get_data()">

	<header id="header" class="media"> 
		<a class="logo pull-left" href="index.html"> <img src="img/logo.png" alt="" /></a>
		<div class="media-body">
			<div class="media" id="top-menu">
	
				<div class="media-body">
					<div class="header-m1">
						<div class="clearfix"></div>
						<div class="attrs">
							项目名称：<span id="xmMc"></span>
						</div>
						<div class="block attrs">
							区间线路：<span id="qjxl"></span>
						</div>
						<div class="block attrs">
							盾构设备：<span id="dgjMc"></span>
						</div>
					</div>
					<div class="header-m2">
						<div class="clearfix"></div>
						<div class="attrs">
							项目经理：<span id="fzr"></span>
						</div>
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
						<div class="attrs">
							区间总长：<span id="qjzc"></span>
						</div>
						<div class="block attrs">
							主要地质：<span id="zydz"></span>
						</div>
						<div class="block attrs">
							始发日期：<span id="kgrq"></span>
						</div>
					</div>
					<div class="header-m5">
						<div class="clearfix"></div>
						<div class="attrs" id="status_j" style="text-align: center">掘进中</div>
						<div class="block attrs" id="status_p" style="text-align: center">拼装中</div>
						<div class="block attrs" id="status_t" style="text-align: center">停机中</div>
					</div>
					<div class="header-m6">
						<div class="clearfix"></div>
						<div class="attrs" style="text-align: center">
							<a href="#" id="baojing">【报警（<span id="baojingCount">2</span>）】</a>
						</div>
						<div class="block attrs" style="text-align: center">【数据连接中...】</div>
						<div class="block attrs" style="text-align: center">
	
							<div id="time">
								<span id="hours"></span> : <span id="min"></span> : <span
									id="sec"></span>
							</div>
	
						</div>
					</div>
				</div>
				<div class="yl-an" style="z-index:1000" >
                    	<button type="button"   class="btn btn-primary"  id="goBack">返回主界面</button>
                </div>
			</div>
		</div>
	</header>
	<div class="clearfix"></div><!-- Main Widgets  id="tableHover" -->
	<section id="main" class="p-relative" role="main">
            
            <!-- Sidebar -->
            
        
            <!-- Content -->
            <section id="content" class="container">
                
                <!-- Main Widgets -->
               
                <div class="block-area">
                    <div class="row">
                        <div class="col-md-8">
                        
                           
                            
                            <h2 class="tile-title">相关参数</h2>
                           
                           <!-- Table Hover -->
                                <div class="block-area" id="tableHover">
                                
                                   
                                    
                                </div>
   
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
	<script type="text/javascript" src="../js/jzk.tbm.js" />
	<script src="js/functions.js"></script>
	<!-- All JS functions -->
	<script type="text/javascript">
		function show_category() {
			$.ajax({
				type : "post",
				url : "../category.do",
				data : "",
				dataType : "json",
				success : function(data) {
					table_category(data);
				}
			});
		}
		function table_category(data) {
			console.log(data);
		}
		function show_param() {

		}
		//获取项目信息
		var xiangmuId = getQueryString("xmId");
		var dgjId = getQueryString("tbmId");
		var xlId = getQueryString("xlId");
		var xianluId = getQueryString("xlId");

		$('#baojing').click(
				function() {
					location.href = '../sys_3/index.jsp?xmId=' + xiangmuId
							+ '&tbmId=' + dgjId;
		});

		function get_data() {
			$.ajax({
				type : "post",
				url : "../param.do",
				data : "",
				dataType : "json",
				success : function(data) {
					create_table(data);
				}
			});
		}
		function create_table(data) {
			console.log(data);
			var nowType = null;
			var nowTable = null;
			for (var i = 0; i < data.length; i++) {
				var item = data[i];
				if (nowType == null || nowType != item.category_name) {
					nowType = item.category_name;
					var div = $('<div class="table-responsive overflow" style="float:left;width:32%;overflow:auto;margin-left:10px;border:1px solid rgba(0,100,255,0.8);height:250px;margin-bottom:10px"></div>');
					nowTable = $("<table/>").addClass("table").addClass(
							"table-bordered").addClass("table-hover").addClass(
							"tile").css('border-left','0px').css('border-top','0px').css('border-right','0px').attr("name", nowType);
					var tr = $("<tr/>");
					var td1 = $("<th/>").text("参数名称");
					var td2 = $("<th/>").text("值");
					var td3 = $("<th/>").text("单位");
					tr.append(td1).append(td2).append(td3);
					var trr = $("<tr/>");
					var tdd = $("<th/>").text(nowType).attr("colspan", "3");
					trr.append(tdd);
					var thead = $("<thead/>").append(trr).append(tr);
					nowTable.append(thead);
					div.append(nowTable);
					$("#tableHover").append(div);
				}
				var tr = $("<tr/>");
				var td1 = $("<td/>").text(item.name);
				var td2 = $("<td/>").text(0).attr("id", item.key);
				var td3 = $("<td/>").text(item.danwei);
				tr.append(td1).append(td2).append(td3);
				nowTable.append(tr);
			}

		}
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