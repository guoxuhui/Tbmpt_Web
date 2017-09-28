<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>管片质量报表页面</title>
    <link rel="stylesheet" href="${staticPath}/static/open/css/weui.min.css"/>
    <link rel="stylesheet" href="${staticPath}/static/open/css/main.css"/>

</head>
<body ontouchstart>
    <div class="weui-toptips weui-toptips_warn js_tooltips">错误提示</div>

    <div class="container" id="container">
        <div class="page input js_show">

            <div class="page__bd">

                
            </div>

            <div class="page__ft">
                <a href="#"></a>
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
                    <a href="javascript:closeCurrentWindow();" class="weui-dialog__btn weui-dialog__btn_default">返回</a>
                    <a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_primary">取消</a>
                </div>
            </div>
        </div>
        <!--END dialogSuccess-->
    </div>
    <script src="${staticPath}/static/open/js/zepto.min.js"></script>
    <script src="${staticPath}/static/open/js/weui.min.js"></script>
    <script type="text/javascript">
        $(function(){
            
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
			
			var success = '${data.success}';
            
        });
        
        var closeCurrentWindow = function(){
        	Android.closeCurrentWindow();
        }
    </script>
</body>
</html>
