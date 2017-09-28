<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>

<style type="text/css">
* {
	font-size: 9pt;
	border: 0;
	margin: 0;
	padding: 0;
}

body {
	font-family: '微软雅黑';
	margin: 0 auto;
	min-width: 980px;
}

ul {
	display: block;
	margin: 0;
	padding: 0;
	list-style: none;
}

li {
	display: block;
	margin: 0;
	padding: 0;
	list-style: none;
}

img {
	border: 0;
}

dl, dt, dd, span {
	margin: 0;
	padding: 0;
	display: block;
}

a, a:focus {
	text-decoration: none;
	color: #000;
	outline: none;
	blr: expression(this.onFocus = this.blur ());
}

a:hover {
	color: #00a4ac;
	text-decoration: none;
}

table {
	border-collapse: collapse;
	border-spacing: 0;
}

cite {
	font-style: normal;
}

h2 {
	font-weight: normal;
}
/*index*/
.mainindex {
	padding: 20px;
	overflow: hidden;
}

.welinfo {
	height: 32px;
	line-height: 32px;
	padding-bottom: 8px;
}

.welinfo span {
	float: left;
}

.welinfo b {
	padding-left: 8px;
}

.welinfo a {
	padding-left: 15px;
	color: #3186c8;
}

.welinfo a:hover {
	color: #F60;
}

.welinfo i {
	font-style: normal;
	padding-left: 8px;
}

.xline {
	border-bottom: solid 1px #dfe9ee;
	height: 5px;
}

.iconlist {
	padding-left: 40px;
	overflow: hidden;
}

.iconlist li {
	text-align: center;
	float: left;
	margin-right: 25px;
	margin-top: 25px;
}

.iconlist li p {
	line-height: 25px;
}

.box {
	height: 15px;
}

.infolist {
	padding-left: 40px;
	padding-bottom: 15px;
}

.infolist li {
	line-height: 23px;
	height: 23px;
	margin-bottom: 8px;
}

.infolist li span {
	float: left;
	display: block;
	margin-right: 10px;
}

.infolist a {
	padding-left: 15px;
	color: #3186c8;
}

.infolist a:hover {
	color: #F60;
}
</style>

<div class="mainindex">

	<div class="welinfo">
		<span><img src="static/style/images/sun.png" alt="天气" /></span> <b><shiro:principal property="name"></shiro:principal>
			您好，欢迎使用盾构集群远程监控与智能化支撑决策系统</b>(dys@crfeb.com.cn) 
			<a href="javascript:parent.userInfo(<shiro:principal property='id'></shiro:principal>)" >帐号信息</a>
	</div>

	<div class="welinfo">
		<span><img src="static/style/images/time.png" alt="时间" /></span> <i>您上次登录的时间：<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd" /></i> （不是您登录的？<a href="javascript:parent.editUserPwd();">请点这里</a>）
	</div>

	<div class="xline"></div>

	<div class="welinfo">
		<span><img src="static/style/images/dp.png" alt="提醒" /></span> <b>盾构集群远程监控与智能化支撑决策系统使用指南</b>
	</div>

	<ul class="infolist">

		<li><span>您可以进行密码修改、账户设置等操作</span><a href="javascript:parent.userInfo(<shiro:principal property='id'></shiro:principal>)">账户管理</a></li>
		<li><span>《项目风险上报管理使用手册》</span><a href="docs/项目风险上报管理系统操作手册v1.0.pdf" target="_blank">使用手册下载</a></li>
		 
	</ul>

</div>

