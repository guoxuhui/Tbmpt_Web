<%@page import="com.crfeb.tbmpt.commons.utils.WebUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">

	<head>
		<meta charset="UTF-8">
		<title>特种人员详情</title>
		<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
		<link rel="stylesheet" type="text/css" href="${staticPath}/static/open/css/main.css" />
		<link rel="stylesheet" type="text/css" href="${staticPath}/static/open/css/weui.min.css" />
    <%
    	String type = WebUtil.checkDeviceType(request);
	%>
	</head>

	<body ontouchstart>
		<div class="weui-toptips weui-toptips_warn js_tooltips">错误提示</div>
		<div class="weui-gallery" id="gallery" style="opacity: 0; display: none;">
			<span class="weui-gallery__img" id="galleryImg" style="background-image: url(./images/pic_160.png)"></span>
			<div class="weui-gallery__opr">
				<a href="javascript:" class="weui-gallery__del"> <i class="weui-icon-delete weui-icon_gallery-delete"></i>
				</a>
			</div>
		</div>
		<div class="container" id="container">
			<div class="page preview js_show">

				<div class="page__bd">
					
					<div class="weui-form-preview">
						
						<div class="weui-form-preview__hd">	
							<div class="weui-form-preview__item">	
								<label class="weui-form-preview__label">基本信息</label>
							</div>
						</div>
						
						
						<div class="weui-form-preview__bd">
							<div class="weui-form-preview__item">
								<label class="weui-form-preview__label">姓名</label> <span class="weui-form-preview__value">${info.name}</span>
							</div>
							
							<c:if test="${!empty info.sex}">
							<div class="weui-form-preview__item">
								<label class="weui-form-preview__label">性别</label> <span class="weui-form-preview__value">${info.sex}</span>
							</div>
							</c:if>
							
							<c:if test="${!empty info.cardNo}">
							<div class="weui-form-preview__item">
								<label class="weui-form-preview__label">身份证号</label> <span class="weui-form-preview__value">${info.cardNo}</span>
							</div>
							</c:if>
							
							<c:if test="${!empty info.birthDay}">
							<div class="weui-form-preview__item">
								<label class="weui-form-preview__label">生日</label> <span class="weui-form-preview__value">${info.birthDay}</span>
							</div>
							</c:if>
							
							<c:if test="${!empty info.age}">
							<c:choose>
							
							<c:when test="${info.age > 55}">
							<div class="weui-form-preview__item">
								<label class="weui-form-preview__label" id ="age_label" style ="color:red;">年龄
								</label> 
								<label class="weui-form-preview__value" id = "age_value" style ="color:red;">${info.age}
								</label>
							</div>
							</c:when>
							
							<c:when test="${info.age <= 55 && info.age >= 50}">
							<div class="weui-form-preview__item">
								<label class="weui-form-preview__label" id ="age_label" style ="color:#EEC900;">年龄
								</label> 
								<label class="weui-form-preview__value" id = "age_value" style ="color:#EEC900;">${info.age}
								</label>
							</div>
							</c:when>
							
							<c:otherwise> 
							<div class="weui-form-preview__item">
								<label class="weui-form-preview__label" id ="age_label">年龄
								</label> 
								<label class="weui-form-preview__value" id = "age_value">${info.age}
								</label>
							</div>
							</c:otherwise>
							
							</c:choose>
							</c:if>
							
							<c:if test="${!empty info.gongzhong}">
							<div class="weui-form-preview__item">
								<label class="weui-form-preview__label">工种</label> <span class="weui-form-preview__value">${info.gongzhong}</span>
							</div>
							</c:if>
							
							<c:if test="${!empty info.phone}">
							<div class="weui-form-preview__item">
								<label class="weui-form-preview__label">联系电话</label> <span class="weui-form-preview__value">${info.phone}</span>
							</div>
							</c:if>
						</div>
					</div>
					
					<br />
					
					<div class="weui-form-preview">
						<div class="weui-form-preview__hd">
							<div class="weui-form-preview__item">
								<label class="weui-form-preview__label">其他信息</label>
							</div>
						</div>
						
						<div class="weui-form-preview__bd">
							<c:if test="${!empty info.adress}">
							<div class="weui-form-preview__item">
								<label class="weui-form-preview__label">家庭住址</label> <span class="weui-form-preview__value">${info.adress}</span>
							</div>
							</c:if>
							
							<c:if test="${!empty info.demptName}">
							<div class="weui-form-preview__item">
								<label class="weui-form-preview__label">单位名称</label> <span class="weui-form-preview__value">${info.demptName}</span>
							</div>
							</c:if>
							
							<c:if test="${!empty info.inDate}">
							<div class="weui-form-preview__item">
								<label class="weui-form-preview__label">入职日期</label> <span class="weui-form-preview__value">${info.inDate}</span>
							</div>
							</c:if>
							
							<c:if test="${!empty info.outDate}">
							<div class="weui-form-preview__item">
								<label class="weui-form-preview__label">离职日期</label> <span class="weui-form-preview__value">${info.outDate}</span>
							</div>
							</c:if>
							
							<c:if test="${!empty info.peixunTime}">
							<div class="weui-form-preview__item">
								<label class="weui-form-preview__label">培训时间</label> <span class="weui-form-preview__value">${info.peixunTime}</span>
							</div>
							</c:if>
							
							<c:if test="${!empty info.state}">
							<div class="weui-form-preview__item">
								<label class="weui-form-preview__label">在职状态</label> <span class="weui-form-preview__value">${info.state}</span>
							</div>	
							</c:if>		
						</div>

	</body>

</html>