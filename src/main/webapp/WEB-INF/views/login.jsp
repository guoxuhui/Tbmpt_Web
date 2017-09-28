<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>欢迎使用盾构集群远程监控与智能化决策支持系统</title>
	<link href="${staticPath }/static/easyui/themes/bootstrap/easyui.css" rel="stylesheet" type="text/css"  />
	<link href="${staticPath }/static/style/css/login.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${staticPath }/static/easyui/jquery.min.js" charset="utf-8"></script>
	<script type="text/javascript" src="${staticPath }/static/easyui/jquery.easyui.min.js" charset="utf-8"></script>
	<script type="text/javascript" src="${staticPath }/static/easyui/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
	<script type="text/javascript" src="${staticPath }/static/extJs.js" charset="utf-8"></script>
	<script type="text/javascript" src="${staticPath }/static/common.js" charset="utf-8"></script>
    <script type="text/javascript" src="${staticPath }/static/login.js" charset="utf-8"></script>
	<script type="text/javascript">
	    var basePath = "${staticPath }";
	</script>
	<script type="text/javascript">
		$(function(){
		    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
			$(window).resize(function(){
		    	$('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2
		    });
	    })  
	});  
	</script>
</head>
<body onkeydown="enterlogin();" style="background-color:#1c77ac; background-image:url(${staticPath}/static/style/images/light.png); 
background-repeat:no-repeat; background-position:center top; overflow:hidden;">

	<div id="mainBody">
		<div id="cloud1" class="cloud"></div>
		<div id="cloud2" class="cloud"></div>
	</div>
	<div class="logintop">
		<span>盾构集群远程监控与智能化决策支持系统</span>
		<ul>
			<li><a href="#">相关下载</a></li>
			<li><a href="#">帮助</a></li>
			<li><a href="#">关于</a></li>
		</ul>
	</div>
	<div class="loginbody">
		<div class="loginbodyCon">
			<span class="systemlogo"></span>
			<form method="post" id="loginform">
				<div class="loginbox">
					<ul>
						<li>
						<input name="username" type="text" class="loginuser"/>
						</li>
						<li>
						<input name="password" type="password" class="loginpwd" onclick="JavaScript:this.value=''" />
						</li>
						<li>
						<input name="" type="button" class="loginbtn" value="登录" onclick="submitForm();" />
						<label><input name="" type="checkbox" value="" checked="checked" />记住密码</label>
						<label><a href="#">忘记密码？</a></label>
						</li>
					</ul>
				</div>
			</form>
		</div>
	</div>
	
	<div class="loginbm">Copyright ©2017 power by 中铁一局集团城市轨道交通工程有限公司</div>
</body>
</html>

