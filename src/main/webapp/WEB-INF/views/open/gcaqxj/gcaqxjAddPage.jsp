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
          <a href="javascript:" class="weui-gallery__del">
            <i class="weui-icon-delete weui-icon_gallery-delete"></i>
          </a>
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
            <div class="weui-cell__ft"> <a href="javascript:;" id="checkHistory" class="weui-btn weui-btn_mini weui-btn_primary">巡检历史</a></div>
          </div>
        </div>
        <div class="hd">
          <h1 class="page_title" style="align-content: center;text-align: center;">${dto.name}</h1>
        </div>
        <hr/>
        <div class="weui_cells_title" style="text-align: center;">检查内容<font color="green">(点击标记不合格项目)</font></div>
        <div class="weui_cells weui_cells_access" >
          <hr/>
          <c:forEach var="aqxjXjnrInfo" items="${dto.aqxjXjnrInfoList}" varStatus="xh" >
            <div class="weui_cell_bd weui_cell_primary">
              <p id="${xh.count}" >${xh.count}、${aqxjXjnrInfo.contentXunjian}</p>
              <input type="hidden" name="aqxjXjjlDetails[${xh.index}].contentxunjian" value="${aqxjXjnrInfo.contentXunjian}">
              <input type="hidden"  id="detail_${xh.count}"  name="aqxjXjjlDetails[${xh.index}].jianchajg" value="1">
            </div>
            <div class="weui_cell_ft">
            </div>
            <hr/>
          </c:forEach>
        </div>
        <!--上传图片-->
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
        <div class="weui-cells">
          <div class="weui-cell">
            <div class="weui-cell__bd">
              <input class="weui-input" type="text"  name="miaoshu" placeholder="请填写备注信息"/>
            </div>
          </div>
        </div>
        <!- 是否推送给责任人-->
        <div class="weui-cell weui-cell_switch">
          <div class="weui-cell__bd"> <label for="switchCP" class="weui-switch-cp">
            <input id="switchCP" class="weui-switch-cp__input" name="isSend" type="checkbox" checked="checked" value="1" onchange="isSend();"/>
            <div class="weui-switch-cp__box"></div>
          </label></div>
          <div class="weui-cell__ft">
            是否推送给责任人
          </div>
        </div>
        <input type="hidden" id="mingcheng" name="name" value="${dto.name}">
        <input type="hidden" name="jianchapc" value="${dto.jianchapc}">
        <input type="hidden" name="foreignid" value="${dto.foreignid}">
        <input type="hidden" name="typeName" value="${dto.typeName}">
        <input type="hidden" name="zerenrid" value="${dto.zerenrid}">
        <input type="hidden" name="zerenrmc" value="${dto.zerenrmc}">
        <input type="hidden" name="jiandurid" value="${dto.jiandurid}">
        <input type="hidden" name="jiandurmc" value="${dto.jiandurmc}">
        <input type="hidden" name="itemAddress" value="${dto.itemAddress}">

      </form>
      <!-- 上报提交巡检权限判断 -->
      <div class="weui-btn-area">
        <a class="weui-btn weui-btn_primary" href="javascript:" id="formSubmitBtn">提交</a>
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
    $("#checkHistory").on("click",function(){
      <%=type%>.checkHistory($("#mingcheng").val());
    });
    /**
     * 增加巡检内容点击事件
     * */
    $("p").on("click",function(){
      var id = $(this).attr("id");
      id="detail_"+id;
      if($("#"+id).val()==1){
        $("#"+id).val("0");
        $(this).addClass('isNotOk');
        return;
      }
      if($("#"+id).val()==0){
        $("#"+id).val("1");
        $(this).removeClass('isNotOk');
        return;
      }
    });
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
    var tmpl = '<li class="weui-uploader__file" style="background-image:url(#url#)"></li>',
            $gallery = $("#gallery"), $galleryImg = $("#galleryImg"),
            $uploaderInput = $("#uploaderInput"),
            $uploaderFiles = $("#uploaderFiles")
            ;
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
