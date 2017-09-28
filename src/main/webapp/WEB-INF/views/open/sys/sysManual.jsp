<%@page import="com.crfeb.tbmpt.commons.utils.WebUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>用户手册</title>
    <link rel="stylesheet" href="${staticPath}/static/open/css/weui.min.css"/>
    <link rel="stylesheet" href="${staticPath}/static/open/css/main.css"/>
    <%
    	String type = WebUtil.checkDeviceType(request);
	%>
</head>
<body ontouchstart>
    <div class="weui-toptips weui-toptips_warn js_tooltips">错误提示</div>

    <div class="container" id="container">
        <div class="page msg_success js_show">
		    <div class="weui-msg">
		        <div class="weui-msg__icon-area"><i class="weui-icon-info weui-icon_msg"></i></div>
		        <div class="weui-msg__text-area">
		            <h2 class="weui-msg__title">用户手册</h2>
					<p class="weui-msg__desc">
						<a href="/docs/地面沉降监测管理系统操作手册.pdf">1、地面沉降监测管理系统操作手册</a>
					</p>
		        </div>
		        
		        <div class="weui-msg__extra-area">
		            <div class="weui-footer">
		                <p class="weui-footer__links">
		                    <a href="javascript:void(0);" class="weui-footer__link">中铁一局城市轨道交通工程有限公司</a>
		                </p>
		                <p class="weui-footer__text">Copyright © 2005-2017 crfeb.com.cn</p>
		            </div>
		        </div>
		    </div>
		</div>
    </div>
	
    <script type="text/javascript">
        var closeCurrentWindow = function(){
        	<%=type%>.closeCurrentWindow();
        }
    </script>
</body>
</html>
