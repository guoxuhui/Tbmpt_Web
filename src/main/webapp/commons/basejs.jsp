<%--标签 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="edge" />
<link rel="shortcut icon" href="${staticPath }/static/style/images/favicon.ico" />
<!-- [my97日期时间控件] -->
<script type="text/javascript" src="${staticPath }/static/My97DatePicker/WdatePicker.js" charset="utf-8"></script>
<!-- [jQuery] -->
<script type="text/javascript" src="${staticPath }/static/easyui/jquery.min.js" charset="utf-8"></script>
<!-- [EasyUI] -->
<link id="easyuiTheme" rel="stylesheet" type="text/css" href="${staticPath }/static/easyui/themes/bootstrap/easyui.css" />
<link id="easyuiTheme" rel="stylesheet" type="text/css" href="${staticPath }/static/easyui/themes/icon.css" />
<link id="easyuiTheme" rel="stylesheet" type="text/css" href="${staticPath }/static/easyui/icons.css" />
<link id="easyuiTheme" rel="stylesheet" type="text/css" href="${staticPath }/static/easyui/themes/color.css" />
<script type="text/javascript" src="${staticPath }/static/easyui/jquery.easyui.min.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/easyui/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/easyui/json2.js" charset="utf-8"></script>
<!-- [公共JS] -->
<script type="text/javascript" src="${staticPath }/static/common.js?v-201702231407" charset="utf-8"></script>
<!-- [扩展JS] -->
<script type="text/javascript" src="${staticPath }/static/extJs.js" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath }/static/buttonIfEnable.js" charset="utf-8"></script>
<!--                                                     /js/sys/base/uploadPicture.js -->
<script type="text/javascript" src="${staticPath }/static/js/sys/base/uploadPicture.js" charset="utf-8"></script>
<!-- 百度Echart -->
<script src="${staticPath }/static/echarts.min170516.js"></script>
<!-- [扩展样式] -->
<link rel="stylesheet" type="text/css" href="${staticPath }/static/style/css/main.css" />
<script type="text/javascript">
    var basePath = "${staticPath }";
    var imageBrowsePath = "${imageBrowsePath }";
    var _currOrgzType = <shiro:principal property="orgzType"></shiro:principal>;
</script>