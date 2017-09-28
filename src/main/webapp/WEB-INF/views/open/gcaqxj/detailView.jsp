<%@page import="com.crfeb.tbmpt.commons.utils.WebUtil" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>巡检结果</title>
  <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
  <link rel="stylesheet" href="${staticPath}/static/open/css/weui.min.css"/>
  <link rel="stylesheet" href="${staticPath}/static/open/css/main.css"/>
  <%
    String type = WebUtil.checkDeviceType(request);
  %>
  <style>
    .isNotOk{
      background: red;
      color: black;

    }
  </style>

</head>
<body ontouchstart>
<div class="weui-toptips weui-toptips_warn js_tooltips">错误提示</div>

<div class="container" id="container">
  <div class="page input js_show">

    <div class="page__bd">

      <div class="weui-gallery" id="gallery">
        <span class="weui-gallery__img" id="galleryImg"></span>

        <div class="weui-gallery__opr">
          <a href="javascript:" class="weui-gallery__del"></a>
            <%--<i class="weui-icon-delete weui-icon_gallery-delete"></i></a>--%>
        </div>
      </div>

      <!-- 提交表单 -->
      <form id="form" method="post" action="${staticPath}/open/gcaqxj/gczlGCXJAddDo"
            enctype="multipart/form-data">

        <div class="weui-cells">
          <div class="weui-cell">
            <div class="weui-cell__bd">
              <p>巡检频次:${dto.jianchapc}</p>
              <p>上次巡检时间:${dto.lastCheckTime}</p>
            </div>
          </div>
        </div>
        <div class="hd">
          <h1 class="page_title" style="align-content: center;text-align: center;">${dto.name}</h1>
        </div>
        <hr/>
        <div class="weui_cells_title" style="text-align: center;padding-top:5px;padding-bottom: 5px;">检查内容</div>
        <div class="weui_cells weui_cells_access" >
          <hr/>
          <c:forEach var="aqxjXjnrInfo" items="${dto.aqxjXjjlDetails}" varStatus="xh" >
            <div class="weui_cell_bd weui_cell_primary">
              <c:choose>
                <c:when test="${aqxjXjnrInfo.jianchajg== '1'}">
                  <p id="${xh.count}"  style="padding:10px;">${xh.count}、${aqxjXjnrInfo.contentxunjian}</p>
                </c:when>
                <c:otherwise>
                  <p id="${xh.count}" class="isNotOk" style="padding:10px;" >${xh.count}、${aqxjXjnrInfo.contentxunjian}</p>
                </c:otherwise>
              </c:choose>
            </div>
            <div class="weui_cell_ft">
            </div>
            <hr/>
          </c:forEach>
        </div>
        <!--上传图片-->
        <div class="weui-cells__title">相关图片:</div>
        <c:if test="${!empty sysFujianList}">
          <div class="weui-form-preview__item">
								<span class="weui-form-preview__value" id="imgzgq" style="padding-left: 10px;">
									<c:forEach items="${sysFujianList}" var="fj">
                                      <img
                                              name="img"
                                              src="${imageBrowsePath}/${fj.minImgPath}"
                                              style="width: 80px; height: 80px; margin: 5px;" alt="${imageBrowsePath}/${fj.filePath}">
                                    </c:forEach>
								</span>
          </div>
        </c:if>
        <div class="weui-cells__title">备注信息:</div>
        <div class="weui-cells">
          <div class="weui-cell">
            <div class="weui-cell__bd">
              <input class="weui-input" type="text" style="padding:10px;" name="miaoshu" readonly="true" value="${dto.miaoshu}"/>
            </div>
          </div>
        </div>
      </form>

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
            $uploaderFiles = $("#uploaderFiles");
    $imgzgq = $("#imgzgq");
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
    $imgzgq.on("click","img",function() {
      $galleryImg.attr("style", "background-image: url('"+this.alt+"')");
      $gallery.fadeIn(100);
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
    })

    /**
     * 是否推送给责任人事件
     * */
    $("#switchCP").on("click",function(){
      var issend = $("#switchCP").val();
      if(issend==1){
        $("#switchCP").attr("value","0");
      }else{
        $("#switchCP").attr("value","1");
      }
    });
  });
  var closeCurrentWindow = function () {
    <%=type%>.
    closeCurrentWindow();
  }

</script>
</body>
</html>
