<%@page import="com.crfeb.tbmpt.commons.utils.WebUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>用户信息</title>
	<meta name="viewport"
		content="width=device-width,initial-scale=1,user-scalable=0">
	<link rel="stylesheet" href="${staticPath}/static/open/css/weui.min.css" />
	<link rel="stylesheet" href="${staticPath}/static/open/css/main.css" />
	<style>
	.weui-input {
		text-align: right;
	}
	</style>
	
	<script>
		function input_onclick(obj) {
			obj.style.color = 'black';
		}
	
		function input_onblur(obj) {
			obj.style.color = '#999999';
		}
	</script>
    <%
    	String type = WebUtil.checkDeviceType(request);
	%>
</head>

<body>
	<div class="container" id="container">
		<form>
			<div class="weui-cells weui-cells_form">

				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label" style="width: 8em;">头像</label>
					</div>
					<div align="right" style="width: 100%;">
						<img class="weui-img" src="${staticPath}/static/open/images/user_img.png"
							align="right"> </img>
					</div>
				</div>

				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label" style="width: 8em;">账号：</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" 
							value="${user.loginName}" disabled="disabled" />
					</div>
				</div>

				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label" style="width: 8em;">名称：</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" 
							value="${user.name}" disabled="disabled"/>
					</div>
				</div>

				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label" style="width: 8em;">单位：</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" 
							value="${orgz.name}" disabled="disabled"/>
					</div>
				</div>

				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label" style="width: 8em;">地址：</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" 
						value="${emp.phone}"  disabled="disabled"/>
					</div>
				</div>
			</div>
			<div class="weui-cells weui-cells_form">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label" style="width: 8em;">手机：</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="number" 
							maxlength="11" value="${emp.phone}" disabled="disabled"/>
					</div>
				</div>

				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label" style="width: 8em;">邮箱：</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="email" 
							value="${emp.email}" disabled="disabled"/>
					</div>
				</div>

			</div>

		</form>
	</div>
</body>

</html>
