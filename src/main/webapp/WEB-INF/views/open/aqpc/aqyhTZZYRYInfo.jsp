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
								<label class="weui-form-preview__label">身份证号码</label> <span class="weui-form-preview__value">${info.cardNo}</span>
							</div>
							</c:if>
							
							<c:if test="${!empty info.workType}">
							<div class="weui-form-preview__item">
								<label class="weui-form-preview__label">工种</label> <span class="weui-form-preview__value">${info.workType}</span>
							</div>
							</c:if>
							
							<c:if test="${!empty info.renyuanType}">
							<div class="weui-form-preview__item">
								<label class="weui-form-preview__label">人员类别</label> <span class="weui-form-preview__value">${info.renyuanType}</span>
							</div>
							</c:if>
							<c:if test="${!empty info.jinchangriqi}">
							<div class="weui-form-preview__item">
								<label class="weui-form-preview__label">进场日期</label> <span class="weui-form-preview__value">${info.jinchangriqi}</span>
							</div>
							</c:if>
							<c:if test="${!empty info.lichangriqi}">
							<div class="weui-form-preview__item">
								<label class="weui-form-preview__label">离场日期</label> <span class="weui-form-preview__value">${info.lichangriqi}</span>
							</div>
							</c:if>
						</div>
					</div>
					
					<br />
					
					<div class="weui-form-preview">
						<div class="weui-form-preview__hd">
							<div class="weui-form-preview__item">
								<label class="weui-form-preview__label">证件信息</label>
							</div>
						</div>
						
						<div class="weui-form-preview__bd">
						<c:if test="${!empty info.zhengjianhaoma}">
							<div class="weui-form-preview__item">
								<label class="weui-form-preview__label">证件号码</label> <span class="weui-form-preview__value">${info.zhengjianhaoma}</span>
							</div>
							</c:if>
							
							<c:if test="${!empty info.fazhengjiguan}">
							<div class="weui-form-preview__item">
								<label class="weui-form-preview__label">发证机关</label> <span class="weui-form-preview__value">${info.fazhengjiguan}</span>
							</div>
							</c:if>
							
							<c:if test="${!empty info.youxiaoqi}">
							<div class="weui-form-preview__item">
								<label class="weui-form-preview__label">有效日期</label> <span class="weui-form-preview__value">${info.youxiaoqi}</span>
							</div>
							</c:if>
							
							<c:if test="${!empty info.quzhengriqi}">
							<div class="weui-form-preview__item">
								<label class="weui-form-preview__label">取证日期</label> <span class="weui-form-preview__value">${info.quzhengriqi}</span>
							</div>
							</c:if>
							
							<c:if test="${!empty info.fushengriqi}">
							<div class="weui-form-preview__item">
								<label class="weui-form-preview__label" id = "fushen_label">复审日期</label> 
								<span class="weui-form-preview__value" id = "fushen_value">${info.fushengriqi}</span>
							</div>
							</c:if>
							
							<!-- <div class="weui-form-preview__item">
								<label class="weui-form-preview__label">证件图片</label>
							</div>	 -->

						</div>
	
	<script>
			var fsDate =Date.parse("${info.fushengriqi}");
			var fusDate = new Date();
			fusDate.setTime(fsDate)
			var time = new Date();
			var twoDate = addMonth(time,2);
			if (fusDate < time) {
				document.getElementById("fushen_label").style.color = "red";
				document.getElementById("fushen_value").style.color = "red";
			}
			else if (fusDate<twoDate ){
				document.getElementById("fushen_label").style.color = "#EEC900";
				document.getElementById("fushen_value").style.color = "#EEC900";
			}
			
			
			// 对Date的扩展，将 Date 转化为指定格式的String   
			// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
			// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
			// 例子：   
			// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423   
			// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18   
			Date.prototype.Format = function(fmt) { //author: meizz   
				var o = {
					"M+": this.getMonth() + 1, //月份   
					"d+": this.getDate(), //日   
					"h+": this.getHours(), //小时   
					"m+": this.getMinutes(), //分   
					"s+": this.getSeconds(), //秒   
					"q+": Math.floor((this.getMonth() + 3) / 3), //季度   
					"S": this.getMilliseconds() //毫秒   
				};
				if(/(y+)/.test(fmt))
					fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
				for(var k in o)
					if(new RegExp("(" + k + ")").test(fmt))
						fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
				return fmt;
			}
			
			function addMonth(date, num) {
      num = parseInt(num);
 
 
      var sYear = date.getFullYear();
      var sMonth = date.getMonth() + 1;
      var sDay = date.getDate();
 
      var eYear = sYear;
      var eMonth = sMonth + num;
      var eDay = sDay;
      while (eMonth > 12) {
        eYear++;
        eMonth -= 12;
      }
 
      var eDate = new Date(eYear, eMonth - 1, eDay);
 
      while (eDate.getMonth() != eMonth - 1) {
        eDay--;
        eDate = new Date(eYear, eMonth - 1, eDay);
      }
 
      return eDate;
    }
		</script>
	
	</body>
	
	
</html>