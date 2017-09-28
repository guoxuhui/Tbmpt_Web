<%@page import="com.crfeb.tbmpt.commons.utils.WebUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />
	<meta name="viewport"
		content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	<title>安全日志记录</title>
	<link rel="stylesheet" type="text/css" href="${staticPath}/static/open/css/main.css" />
	<link rel="stylesheet" type="text/css" href="${staticPath}/static/open/css/weui.min.css" />
    <%
    	String type = WebUtil.checkDeviceType(request);
	%>
</head>

<body>
	<div class="weui-toptips weui-toptips_warn js_tooltips">错误提示</div>
	<div class="container" id="container">
		<div class="page input js_show">
			<p class="page__desc">
				<a class="link" href="#gallery"></a>
			</p>
			<div class="page__bd">
				<form id="form" method="post" action="${staticPath}/open/aqyh/addWorklogDo">
					<div class="weui-cells__title">安全日志记录</div>
					<div class="weui-cells weui-cells_form">
						<div class="weui-cell">
                            <div class="weui-cell__hd"><label for="" class="weui-label" style="width: 8em;">日志日期：</label></div>
                            <div class="weui-cell__bd">
                                <input name="workDay" class="weui-input" type="date" placeholder="日志日期" required/>
                            </div>
                        </div>
						<div class="weui-cell">
							<div class="weui-cell__bd">
								<textarea name="content" class="weui-textarea" placeholder="请输入安全日志，内容不超过500个字！" rows="5" required></textarea>
								<div class="weui-textarea-counter">
									<span>0</span>/500
								</div>
							</div>
						</div>
					</div>

				</form>
				<div class="weui-btn-area">
					<a class="weui-btn weui-btn_primary" href="javascript:"
						id="formSubmitBtn">确定</a>
				</div>
			</div>
		</div>
	</div>
	<div id="dialogs">
        <!--BEGIN dialogTip-->
        <div class="js_dialog" id="dialogTip" style="opacity: 0; display: none;">
            <div class="weui-mask"></div>
            <div class="weui-dialog">
                <div id="dialogTip_content" class="weui-dialog__bd"></div>
                <div class="weui-dialog__ft">
                    <a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_primary">确定</a>
                </div>
            </div>
        </div>
        <!--END dialogTip-->
        <!--BEGIN dialogSuccess-->
        <div class="js_dialog" id="dialogSuccess" style="opacity: 0; display: none;">
            <div class="weui-mask"></div>
            <div class="weui-dialog weui-skin_android">
                <div class="weui-dialog__hd"><strong class="weui-dialog__title">操作成功</strong></div>
                <div id="dialogSuccess_content" class="weui-dialog__bd"></div>
                <div class="weui-dialog__ft">
                    <a href="javascript:closeCurrentWindow();" class="weui-dialog__btn weui-dialog__btn_default">确定</a>
                    <a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_primary">取消</a>
                </div>
            </div>
        </div>
        <!--END dialogSuccess-->
    </div>
	<script src="${staticPath}/static/open/js/zepto.min.js"></script>
	<script src="${staticPath}/static/open/js/weui.min.js"></script>
	<script src="${staticPath}/static/open/js/ext.js"></script>
	<script type="text/javascript">
		$(function() {
            
            var $dialogTip = $('#dialogTip');
            var $dialogSuccess = $('#dialogSuccess');

	        $('#dialogs').on('click', '.weui-dialog__btn', function(){
	            $(this).parents('.js_dialog').fadeOut(200);
	        });
	        
			<c:choose>
			   <c:when test="${isDo}">
			   		<c:choose>
			   			<c:when test="${data.success}">
				   			$('#dialogSuccess_content').empty();
					    	$('#dialogSuccess_content').append('${data.msg}');
					   		$dialogSuccess.fadeIn(200);
			   			</c:when>
					    <c:otherwise>
					    	$('#dialogTip_content').empty();
					    	$('#dialogTip_content').append('${data.msg}');
					   		$dialogTip.fadeIn(200);
					    </c:otherwise>
			   		</c:choose>
			   </c:when>
			   <c:otherwise> 
			   </c:otherwise>
			</c:choose>
			
	        var $form = $("#form");
	        // 表单校验
	        $("#formSubmitBtn").on("click", function(){
	            $form.validate(function(error){
	                if(error){
	                	$('#dialogTip_content').empty();
				    	$('#dialogTip_content').append("请完善输入框");
				   		$dialogTip.fadeIn(200);
	                }else{
	                	$form.submit();
	                }
	            });

	        });
		});
	</script>
</body>

</html>
