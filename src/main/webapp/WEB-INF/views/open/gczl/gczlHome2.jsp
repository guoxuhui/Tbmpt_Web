<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>工程质量巡检</title>
    <link rel="stylesheet" href="${staticPath}/static/open/css/weui.min.css"/>
    <link rel="stylesheet" href="${staticPath}/static/open/css/main.css"/>
    <link rel="stylesheet" href="${staticPath}/static/open/css/swipe.css"/>
    <link rel="stylesheet" href="${staticPath}/static/open/css/iconfont.css"/>

<style type="text/css">

.iconfont {
	color: #ababab;
	font-size: 25px;
}
/*修改样式,改变图标大小*/
.weui-grid__icon{
	width: 64px;
	height: 64px;
}

</style>
</head>
<body ontouchstart>
    <div class="weui-toptips weui-toptips_warn js_tooltips">错误提示</div>

    <div class="container" id="container">
    <div class="page input js_show">
        <div class="page__bd">
            <!-- 图片轮播 -->
            <div class="slide" id="slide1">
                <ul>
                    <li><>
                        <a href="javascript:;">
                            <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAANSURBVBhXYzh8+PB/AAffA0nNPuCLAAAAAElFTkSuQmCC" data-src="${staticPath}/static/open/images/gczl/ad1.png" alt="">
                        </a>
                        <div class="slide-desc">关爱生命 安全生产</div>
                    </li>
                    <li>
                        <a href="javascript:;">
                            <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAANSURBVBhXYzh8+PB/AAffA0nNPuCLAAAAAElFTkSuQmCC" data-src="${staticPath}/static/open/images/gczl/ad2.png" alt="">
                        </a>
                        <div class="slide-desc">施工安全警示标志</div>
                    </li>
                    <li>
                        <a href="javascript:;">
                            <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsQAAA7EAZUrDhsAAAANSURBVBhXYzh8+PB/AAffA0nNPuCLAAAAAElFTkSuQmCC" data-src="${staticPath}/static/open/images/gczl/ad3.png" alt="">
                        </a>
                        <div class="slide-desc">安全文明施工管理</div>
                    </li>
                </ul>
                <div class="dot">
                    <span></span>
                    <span></span>
                    <span></span>
                </div>
            </div>

            <!-- 主功能面板 -->
            <div class="weui-panel">
                <div class="weui-panel__hd">安全质量巡检</div>
                <div class="weui-panel__bd">

                    <div class="weui-grids">
                        <a href="javascript:Android.onJump('com.crfeb.tbmpt.app.GczlGPZLXJActivity');" class="weui-grid">
                            <div class="weui-grid__icon">
                                <img src="${staticPath}/static/open/images/gczl/icon_guanpian.png" alt="">
                            </div>
                            <p class="weui-grid__label">管片质量巡检</p>
                        </a>
                        <a href="javascript:Android.onJump('com.crfeb.tbmpt.app.GczlGPZLLSActivity');" class="weui-grid">
                            <div class="weui-grid__icon">
                                <img src="${staticPath}/static/open/images/gczl/icon_shigong.png" alt="">
                            </div>
                            <p class="weui-grid__label">施工质量巡检</p>
                        </a>
                        <a href="javascript:Android.onJump('com.crfeb.tbmpt.app.GczlSGZLXJActivity');" class="weui-grid">
                            <div class="weui-grid__icon">
                                <img src="${staticPath}/static/open/images/gczl/icon_guanpian_his.png" alt="">
                            </div>
                            <p class="weui-grid__label">管片质量历史</p>
                        </a>
                        <a href="javascript:Android.onJump('com.crfeb.tbmpt.app.GczlSGZLLSActivity');" class="weui-grid">
                            <div class="weui-grid__icon">
                                <img src="${staticPath}/static/open/images/gczl/icon_shigong_his.png" alt="">
                            </div>
                            <p class="weui-grid__label">施工质量历史</p>
                        </a>

                        <a href="javascript:Android.onJump('com.crfeb.tbmpt.app.GczlDCLSXActivity');" class="weui-grid">
                            <div class="weui-grid__icon">
                                <img src="${staticPath}/static/open/images/gczl/icon_dclsx.png" alt="">
                                <span class="weui-badge" style="position: absolute;top: -.4em;right: -.4em;">1</span>
                            </div>
                            <p class="weui-grid__label">待处理事项</p>
                        </a>

                    </div>

                </div>
            </div>

            <!-- 报表功能面板 -->
            <div class="weui-panel">
                <div class="weui-panel__hd">报表</div>
                <div class="weui-panel__bd">

                    <div class="weui-grids">
                        <a href="javascript:Android.onJump('com.crfeb.tbmpt.app.GczlGPReportActivity');" class="weui-grid">
                            <div class="weui-grid__icon">
                                <img src="${staticPath}/static/open/images/gczl/icon_guanpian_chart.png" alt="">
                            </div>
                            <p class="weui-grid__label">管片质量报表</p>
                        </a>
                        <a href="javascript:Android.onJump('com.crfeb.tbmpt.app.GczlSGReportActivity');" class="weui-grid">
                            <div class="weui-grid__icon">
                                <img src="${staticPath}/static/open/images/gczl/icon_shigong_chart.png" alt="">
                            </div>
                            <p class="weui-grid__label">施工质量报表</p>
                        </a>

                    </div>

                </div>
            </div>
            

        </div>

        <div class="page__ft">
            
        </div>
    </div>   
    </div>

    <script src="${staticPath}/static/open/js/zepto.min.js"></script>
    <script src="${staticPath}/static/open/js/weui.min.js"></script>
    <script src="${staticPath}/static/open/js/swipe.js"></script>
    <script type="text/javascript">
        $(function(){
            $('#slide1').swipeSlide({
            autoSwipe:true,//自动切换默认是
            speed:3000,//速度默认4000
            continuousScroll:true,//默认否
            transitionType:'cubic-bezier(0.22, 0.69, 0.72, 0.88)',//过渡动画linear/ease/ease-in/ease-out/ease-in-out/cubic-bezier
            lazyLoad:true,//懒加载默认否
            firstCallback : function(i,sum,me){
                        me.find('.dot').children().first().addClass('cur');
                    },
                    callback : function(i,sum,me){
                        me.find('.dot').children().eq(i).addClass('cur').siblings().removeClass('cur');
                    }
            });
        });
    </script>
</body>
</html>
