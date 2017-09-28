<%@page import="com.crfeb.tbmpt.commons.utils.WebUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>工程质量验收</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="stylesheet" href="${staticPath}/static/open/css/weui.min.css"/>
    <link rel="stylesheet" href="${staticPath}/static/open/css/main.css"/>
    <%
        String type = WebUtil.checkDeviceType(request);
    %>
</head>
<body ontouchstart>
<div class="weui-toptips weui-toptips_warn js_tooltips">错误提示</div>

<div class="container" id="container">
    <div class="page input js_show">

        <div class="page__bd">

            <div class="weui-gallery" id="gallery">
                <span class="weui-gallery__img" id="galleryImg"></span>

                <div class="weui-gallery__opr">
                    <a href="javascript:" class="weui-gallery__del">
                        <i class="weui-icon-delete weui-icon_gallery-delete"></i>
                    </a>
                </div>
            </div>

            <!-- 提交表单 -->
            <form id="form" method="post" action="${staticPath}/open/gczlys/gczlGCZLYSAddDo"
                  enctype="multipart/form-data">


                <div class="weui-cells weui-cells_form">
                    <div class="weui-cell">
                        <div class="weui-cell__hd"><label class="weui-label" style="width: 8em;">工程名称：</label></div>
                        <div class="weui-cell__bd">
                            ${pro.proName}
                            <input name="projectid" type="hidden" value="${pro.id}"/>
                            <input name="projectname" type="hidden" value="${pro.proName}"/>
                        </div>
                    </div>
                    <div class="weui-cell weui-cell_select weui-cell_select-after">
                        <div class="weui-cell__hd">
                            <label for="" class="weui-label" style="width: 8em;">天气：</label>
                        </div>
                        <div class="weui-cell__bd">
                            <select class="weui-select" name="tianqi" id='tianqi_select'>
                                <option value="晴">晴</option>
                                <option value="阴">阴</option>
                                <option value="小雨">小雨</option>
                                <option value="多云">多云</option>
                            </select>
                        </div>
                    </div>
                    <div class="weui-cell">
                        <div class="weui-cell__hd"><label class="weui-label" style="width: 8em;">工程部位：</label></div>
                        <div class="weui-cell__bd">
                            <input name="gcbuwei" class="weui-input" type="text" placeholder="请输入工程部位" required/>
                        </div>
                    </div>
                    <div class="weui-cell">
                        <div class="weui-cell__hd"><label class="weui-label" style="width: 8em;">分部分项工序位：</label></div>
                        <div class="weui-cell__bd">
                            <input name="gongxu" class="weui-input" type="text" placeholder="请输入分部分项工序位" required/>
                        </div>
                    </div>
                    <div class="weui-cell">
                        <div class="weui-cell__hd"><label class="weui-label" style="width: 8em;">施工班组：</label></div>
                        <div class="weui-cell__bd">
                            <input name="banzu" class="weui-input" type="text" placeholder="请输入施工班组" required/>
                        </div>
                    </div>
                    <div class="weui-cell">
                        <div class="weui-cell__hd"><label class="weui-label" style="width: 8em;">班组负责人：</label></div>
                        <div class="weui-cell__bd">
                            <input name="bzfuzr" class="weui-input" type="text" placeholder="请输入班组负责人" required/>
                        </div>
                    </div>
                </div>
                <div class="weui-cells__title">现场检查实际情况(写出各检查主要参数)</div>
                <div class="weui-cells weui-cells_form">
                    <div class="weui-cell">
                        <div class="weui-cell__bd">
								<textarea name="miaoshu" class="weui-textarea" placeholder="请输入现场检查实际情况(写出各检查主要参数)"
                                          rows="3"></textarea>

                            <div class="weui-textarea-counter">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="weui-cells__title">检查验收意见和结论</div>
                <div class="weui-cells weui-cells_form">
                    <div class="weui-cell">
                        <div class="weui-cell__bd">
								<textarea name="jianchaqk" class="weui-textarea" placeholder="请输入验收意见和结论"
                                          rows="3"></textarea>

                            <div class="weui-textarea-counter">
                            </div>
                        </div>
                    </div>
                </div>

                <div class="weui-cells__title">相关图片</div>
                <div class="weui-cells weui-cells_form">
                    <div class="weui-cell">
                        <div class="weui-cell__bd">
                            <div class="weui-uploader">
                                <div class="weui-uploader__hd">
                                    <p class="weui-uploader__title">图片上传</p>

                                    <div class="weui-uploader__info">0/1</div>
                                </div>
                                <div class="weui-uploader__bd">
                                    <ul class="weui-uploader__files" id="uploaderFiles">

                                    </ul>
                                    <div class="weui-uploader__input-box">
                                        <input id="uploaderInput" class="weui-uploader__input" name="file" type="file"
                                               accept="image/*" multiple/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

            </form>

            <!-- 上报提交施工质量巡检权限判断 -->
            <%--<div class="weui-btn-area">--%>
                <%--<a class="weui-btn weui-btn_primary" href="javascript:" id="formSubmitBtnAndriod">确定</a>--%>
            <%--</div>--%>
            <!-- 上报提交施工质量巡检权限判断 -->

            <div class="weui-btn-area">
                <a class="weui-btn weui-btn_primary" href="javascript:" id="formSubmitBtn">确定并提交</a>
            </div>

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
<script src="${staticPath}/static/open/js/selector.js"></script>
<script src="${staticPath}/static/open/js/jquery.chained.js"></script>
<script src="${staticPath}/static/open/js/weui.min.js"></script>
<script src="${staticPath}/static/open/js/ext.js"></script>
<script type="text/javascript">
    $(function () {
        var tmpl = '<li class="weui-uploader__file" style="background-image:url(#url#)"></li>',
                $gallery = $("#gallery"), $galleryImg = $("#galleryImg"),
                $uploaderInput = $("#uploaderInput"),
                $uploaderFiles = $("#uploaderFiles")
                ;
/*
        $uploaderInput.on("change", function (e) {
            var src, url = window.URL || window.webkitURL || window.mozURL, files = e.target.files;
            for (var i = 0, len = files.length; i < len; ++i) {
                var file = files[i];

                if (url) {
                    src = url.createObjectURL(file);
                } else {
                    src = e.target.result;
                }
                $uploaderFiles.append($(tmpl.replace('#url#', src)));
            }
        });
*/
        ////此处的on 需要改成 live ,因为jquery插入html，js事件会失效，采用live
        $uploaderInput.live("change", function (e) {
            var src, url = window.URL || window.webkitURL || window.mozURL, files = e.target.files;
            for (var i = 0, len = files.length; i < len; ++i) {
                var file = files[i];

                if (url) {
                    src = url.createObjectURL(file);
                } else {
                    src = e.target.result;
                }
                var src_split = src.split('/');
                $uploaderFiles.append($(tmpl.replace('#url#', src).replace('#imgname#', src_split[src_split.length-1])));
                //开启隐藏上传 p
                $(this).after('<input id="uploaderInput"   name="file"  class="weui-uploader__input" type="file"   accept="image/*" multiple/>');
                $(this).hide();
                $(this).attr({id:""+src_split[src_split.length-1]+"_input"});
            }
        });

        $uploaderFiles.on("click", "li", function () {
            $galleryImg.attr("style", this.getAttribute("style"));
            $gallery.fadeIn(100);
        });
        $gallery.on("click", function () {
            $gallery.fadeOut(100);
        });

        var $tooltips = $('.js_tooltips');

        $('#showTooltips').on('click', function () {
            if ($tooltips.css('display') != 'none') return;

            // toptips的fixed, 如果有`animation`, `position: fixed`不生效
            $('.page.cell').removeClass('slideIn');

            $tooltips.css('display', 'block');
            setTimeout(function () {
                $tooltips.css('display', 'none');
            }, 2000);
        });

        var $dialogTip = $('#dialogTip');
        var $dialogSuccess = $('#dialogSuccess');

        $('#dialogs').on('click', '.weui-dialog__btn', function () {
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
        var $form = $("#form");
        // 表单校验
        $("#formSubmitBtn").on("click", function () {
            $form.validate(function (error) {
                if (error) {
                    $('#dialogTip_content').empty();
                    $('#dialogTip_content').append("请完善输入框");
                    $dialogTip.fadeIn(200);
                } else {
                    $form.submit();
                }
            });

        });
        // 表单校验
        $("#formSubmitBtnAndriod").on("click", function () {
            $form.validate(function (error) {
                if (error) {
                    $('#dialogTip_content').empty();
                    $('#dialogTip_content').append("请完善输入框");
                    $dialogTip.fadeIn(200);
                } else {
                    <%=type%>.
                    savaForm($(form).serialize());
                }
            });
        });
    });
    var closeCurrentWindow = function () {
        <%=type%>.
        closeCurrentWindow();
    }
</script>
</body>
</html>
