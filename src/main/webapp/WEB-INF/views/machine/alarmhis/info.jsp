<%@page import="com.crfeb.tbmpt.commons.utils.WebUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">

<head>
<meta charset="UTF-8">
<title>报警详情</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,user-scalable=0">
<link rel="stylesheet" type="text/css"
	href="${staticPath}/static/open/css/main.css" />
<link rel="stylesheet" type="text/css"
	href="${staticPath}/static/open/css/weui.min.css" />
	<%
		String type = WebUtil.checkDeviceType(request);
	%>
</head>

<body ontouchstart>

	<div class="container" id="container">
		<div class="page preview js_show">

			<div class="page__bd">
				
				<br />

				<div class="weui-form-preview">
					<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">设备状态</label>
						</div>
					</div>

					<div class="weui-form-preview__bd">
						
						<c:forEach items="${requestScope.param}" var="obj">
							<c:if test="${fn:contains(obj.dwid, 'DXXT_000')}">
								<c:if test="${!fn:contains(alarm.alarmname, obj.dwname)}">
									<div class="weui-form-preview__item">
										<label class="weui-form-preview__label">${obj.dwname}</label> <span
											class="weui-form-preview__value">${obj.tagvalue}</span>
									</div>
								</c:if>
								<c:if test="${fn:contains(alarm.alarmname, obj.dwname)}">
									<div class="weui-form-preview__item" style="color: red;">
										<label class="weui-form-preview__label" style="color: red;">${obj.dwname}</label> <span
											class="weui-form-preview__value" style="color: red;">${obj.tagvalue}</span>
									</div>
								</c:if>
							</c:if>
							<c:if test="${fn:contains(obj.dwid, 'DXXT_0010')}">
								<c:if test="${!fn:contains(alarm.alarmname, obj.dwname)}">
									<div class="weui-form-preview__item">
										<label class="weui-form-preview__label">${obj.dwname}</label> <span
											class="weui-form-preview__value">${obj.tagvalue}</span>
									</div>
								</c:if>
								<c:if test="${fn:contains(alarm.alarmname, obj.dwname)}">
									<div class="weui-form-preview__item" style="color: red;">
										<label class="weui-form-preview__label" style="color: red;">${obj.dwname}</label> <span
											class="weui-form-preview__value" style="color: red;">${obj.tagvalue}</span>
									</div>
								</c:if>
							</c:if>
						</c:forEach>
					</div>
					<div class="weui-form-preview__ft">
						<a class="weui-form-preview__btn weui-form-preview__btn_primary" href="${staticPath}/machine/alarmhis/machine?id=${alarm.id}">查看机器界面</a>
					</div>
				</div>
				
				<br />
				
				<div class="weui-panel weui-panel_access">
					<div class="weui-panel__hd">报警信息</div>
					<div class="weui-panel__bd">
						<div class="weui-media-box weui-media-box_text">
							<h4 class="weui-media-box__title">${alarm.alarmname}</h4>
							<p class="weui-media-box__desc">${alarm.alarmcontent}</p>
							<ul class="weui-media-box__info">
								<li class="weui-media-box__info__meta">所属系统</li>
								<li class="weui-media-box__info__meta">${alarm.electricalsystem}</li>
								<li class="weui-media-box__info__meta weui-media-box__info__meta_extra">${alarm.alarmtime}</li>
							</ul>
						</div>
					</div>
				</div>
				
				<br />
				
				<div class="weui-panel weui-panel_access">
					<div class="weui-panel__hd">所属项目</div>
					<div class="weui-panel__bd">
						
						<div class="weui-media-box weui-media-box_text">
							<h4 class="weui-media-box__title">${alarm.proname}</h4>
							<p class="weui-media-box__desc">项目名称:${alarm.proname}</p>
							<p class="weui-media-box__desc">区间名称:${alarm.sectionname}</p>
							<p class="weui-media-box__desc">线路名称:${alarm.linename}</p>
							<p class="weui-media-box__desc">盾构机:${alarm.tbmname}</p>
							
							<ul class="weui-media-box__info">
								<li class="weui-media-box__info__meta"></li>
								<li class="weui-media-box__info__meta"></li>
								<li class="weui-media-box__info__meta weui-media-box__info__meta_extra"></li>
							</ul>
						</div>
						
					</div>
					<!--
					<div class="weui-panel__ft">
						<a href="javascript:void(0);" class="weui-cell weui-cell_access weui-cell_link">
							<div class="weui-cell__bd">查看更多</div>
							<span class="weui-cell__ft"></span>
						</a>    
					</div>
					-->
				</div>
			
				<br />
			
			</div>
		
		</div>
	</div>

</body>

</html>