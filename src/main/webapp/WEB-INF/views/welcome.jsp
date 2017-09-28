<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
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






<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>盾构集群远程监控与智能化决策支持系统</title>
<link type="text/css" href="${staticPath }/static/index/css/style.css" rel="stylesheet"  />
<link href="${staticPath }/static/style/css/login.css" rel="stylesheet" type="text/css" />
   
<script language="javascript" >
	var index_layout;
	var index_tabs;
	var shiro ;
	$(function(){
		/* 调整窗体样式 */
		 $('.loginbox0').css({'position':'absolute','left':($(window).width()-608)/2});
		 $(window).resize(function(){  
		 $('.loginbox0').css({'position':'absolute','left':($(window).width()-608)/2});
			 index_layout = $('#index_layout').layout({
			        fit : true
			 });
		 });
		
		
		 /*wl_wpg: 获取  id="shiro" 的隐藏标签的值，用于判断用户是否已登录*/
		 shiro = $("#shiro").val();
		 /*wl_wpg： 判断用户是否已登录，显示或隐藏li: 登录、 用户信息、修改密码、退出、按钮栏 */
		 if(shiro !=null && shiro !=""){
			 document.getElementById('login').style.display = "none";
			 document.getElementById('yhbq').style.display = "";
	      }
	     else {
		     document.getElementById('yhbq').style.display = "none";
	         document.getElementById('login').style.display = "";
	     }
		 
		 /*wl_wpg: 加载 用户信息：权限 ，项目Id,未登录不能加载 /sys/user/getUserInfo */
		 if(shiro !=null && shiro !=""){
			 $.post('${path }/sys/user/getUserInfo', 
	                 function(result) {
	                    if(result.success){
	                    	if(result.msg!=null && result.msg !="未登录！"){
	                    		/*wl_wpg: 获取  id="shiro" 的隐藏标签的值，用于判断用户是否已登录*/
	                   		    var orgzType = $("#shiroOrgzType").val();
	                    		if(orgzType >= 1){
	                    			url =result.msg;
		                    	    document.getElementById('AGroup').href='${path }/tbm/project.jsp'+url;
	                    		}else{
	                    			url =result.msg;
		                    		document.getElementById('AGroup').href='${path }/tbm/group.jsp'+url;
	                    		}
	                    		
	                    	}
			            }
			     }, 'json');
				
		 }
		
	}) 
	
	 /* wl_wpg:退出 */
    function logout(){
        $.messager.confirm('提示','确定要退出?',function(r){
            if (r){
                progressLoad();
                $.post('${path }/logout', 
                function(result) {
                    if(result.success){
                        progressClose();
                        window.location.href='${path }';
                    }
                }, 'json');
            }
        });
    }
	 /* wl_wpg:修改密码 */
    function editUserPwd() {
        parent.$.modalDialog({
            title : '修改密码',
            width : 300,
            height : 250,
            href : '${path }/sys/user/editPwdPage',
            buttons : [ {
                text : '确定',
                handler : function() {
                    var f = parent.$.modalDialog.handler.find('#editUserPwdForm');
                    f.submit();
                }
            } ]
        });
    }
	/*wl_wpg: 用户登录后 点击 右上角 用户名称 查看 用户信息 */
    function userInfo(id) {
        parent.$.modalDialog({
            title : '用户信息',
            width : 500,
            height : 300,
            href : '${path }/sys/user/userInfo?id='+id,
            buttons : [ {
                text : '取消',
                handler : function() {
                	parent.$.modalDialog.handler.dialog('close');
                }
            } ]
        });
    }
   
</script> 
<style type="text/css">
.info-app {
    background:none repeat scroll 0 0 #FFFFFF;
    bottom:80px;
    padding:0 0 2px;
    position:fixed;
    left:30px;
    text-align:center;
    width:100px;
    _position:absolute;
    _bottom:auto;
    cursor:hand;
    _top:expression(eval(document.documentElement.scrollTop+document.documentElement.clientHeight-this.offsetHeight-(parseInt(this.currentStyle.marginTop,10)||0)-(parseInt(this.currentStyle.marginBottom,10)||0)));
}

/*wl_wpg: li: 登录、 当前用户信息、修改密码、退出、按钮样式*/

.logintop span {
    color: #fff;
    line-height: 47px;
    text-indent: 0px;
    color: #afc5d2;
    float: left;
}
.span_title{
     text-indent: 30px !important;
}
</style>
</head>

<body style="background-color:#1c77ac; background-image:url(${staticPath }/static/index/images/light1.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">


<div class="logintop">    
    <span class="span_title"  >盾构集群远程监控与智能化决策支持系统</span>    
    <ul>
	    <li id="yhbq" ><!-- 当前用户名称 -->
	        <span style="height: 26px">当前用户：</span>
		    <a href="javascript:void(0)"style="height: 26px;" onclick="userInfo('<shiro:principal property="id"></shiro:principal>')" class="easyui-linkbutton c1" plain="true"  >
		         <span style="color: orange;" >
		              <shiro:principal property="name"> </shiro:principal>
	             </span>
		    </a>
		    &nbsp;&nbsp; 
	         <a href="javascript:void(0)" style="height: 26px;" onclick="editUserPwd()" class="easyui-linkbutton c1" plain="true" >修改密码</a>
	         <a href="javascript:void(0)" style="height: 26px;" onclick="logout()" class="easyui-linkbutton c1" plain="true" >安全退出</a>
	    </li>
	    <li id="login"  >
	       <a href="${staticPath }/login"style="height: 26px;"  class="easyui-linkbutton c1" plain="true"  >
		         <span style="color: orange;" >&nbsp;&nbsp; 登&nbsp;录 &nbsp;&nbsp; </span>
		   </a>
	    </li>
	    <li><a href="#">相关下载</a></li>
	    <li><a href="#">帮助</a></li>
	    <li><a href="#">关于</a></li>
    </ul>    
</div>
    <div class="loginbody1">
    <span class="systemlogo"></span> 
    <div class="loginbox0">
	    <ul class="loginlist">
		    <!-- 保存用户Id,被 js 获取，用于判断用户是否登录 -->
		    <input id="shiro" type="hidden" value="<shiro:principal property="id"></shiro:principal>">
		    <!-- 保存用户Id,被 js 获取，用于判断用户是否登录 -->
		    <input id="shiroOrgzType" type="hidden" value="<shiro:principal property="orgzType"></shiro:principal>">
		    <!-- 默认路径是登录页面，登录后，js从新 赋值新路径 -->
		    <li><a href="${staticPath }/login" id="AGroup"><img src="${staticPath }/static/index/images/l01.png"  alt="综合展示系统"/><p>综合展示系统</p></a></li> 
		    <li><a href="${staticPath }/webgis/index.html"><img src="${staticPath }/static/index/images/l04.png"  alt="远程监控系统"/><p>远程监控系统</p></a></li>
		    <li><a href="${staticPath }/index"><img src="${staticPath }/static/index/images/l02.png"  alt="业务支撑系统"/><p>业务支撑系统</p></a></li>
	    </ul>
    </div>
    
    </div>
    
    <div class="loginbm">Copyright © 2017 power by 中铁一局集团城市轨道交通工程有限公司</div>
	
	<div class="info-app" style="cursor:pointer;">
	    <div class="bd" onclick="javascript:window.open('static/qrcode/er.html')">
	        <img src="static/qrcode/app.png" alt="APP下载:扫一扫" width="98" height="98" title="APP下载:扫一扫">
	    </div>
	    <div class="ft">APP客户端下载</div>
	</div>
    
</body>

</html>
