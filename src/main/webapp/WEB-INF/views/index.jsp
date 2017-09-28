<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>盾构集群远程监控与智能化决策支持系统</title>
<script type="text/javascript">
    var index_layout;
    var index_tabs;
    var layout_west_tree;
    
    $(function() {
        index_layout = $('#index_layout').layout({
            fit : true
        });
        index_tabs = $('#index_tabs').tabs({
            fit : true,
            border : false,
            tools : [{
                iconCls : 'icon-home',
                handler : function() {
                    index_tabs.tabs('select', 0);
                }
            }, {
                iconCls : 'icon-refresh',
                handler : function() {
                    var tab = index_tabs.tabs('getSelected');  // get selected panel
					if (tab && tab.find('iframe').length > 0) {
						tab.find('iframe')[0].contentWindow.location.reload(true);
					}
                }
            }, {
                iconCls : 'icon-arrow_out',
                handler : function() {
                	if(is_fullscreen){
                		exitFullscreen();
                		is_fullscreen = false;
                	}else{
                		fullscreen();
                		is_fullscreen = true;
                	}	
				}
            }, 
            {
                iconCls : 'icon-eject_blue',
                handler : function() {
                	if(is_topopen){
                		$('#index_layout').layout('collapse','north');
                		is_topopen = false;
                	}else{
                		$('#index_layout').layout('expand','north');
                		is_topopen = true;
                	}	
				}
            }, 
            {
                iconCls : 'icon-del',
                handler : function() {
                    var index = index_tabs.tabs('getTabIndex', index_tabs.tabs('getSelected'));
                    var tab = index_tabs.tabs('getTab', index);
                    if (tab.panel('options').closable) {
                        index_tabs.tabs('close', index);
                    }
                }
            } ]
        });

        $.ajax({  
            type : 'POST',  
            dataType : "json",  
            url : '${path }/sys/resource/menu',  
            success : function(data) {  
                $.each(data, function(i, n) {//加载父类节点即一级菜单  
                    if (i == 0) {//显示第一个一级菜单下的二级菜单  
                        $('#layout_west_accordion').accordion('add', {
                            title : n.text,  
                            iconCls : n.iconCls,  
                            selected : true,  
                            content : '<div class="well well-small" style="padding:0px"><ul name="'+n.text+'"></ul></div>',  
                        });
                    } 
                    /*
                    else {  
                        $('#layout_west_accordion').accordion('add', {
                            title : n.text,  
                            iconCls : n.iconCls,  
                            selected : false,  
                            content : '<div class="well well-small" style="padding:0px"><ul name="'+n.text+'"></ul></div>',  
                        });  
                    }  
                	*/
                	var menu = "<div class='menu-item' style='height: 20px;' data-options=\"iconCls:'"+n.iconCls+"'\" onclick=\"javascript:menuHandler('"+n.text+"','"+n.iconCls+"')\">";
                	menu = menu + "<div class='menu-text' style='height: 20px; line-height: 20px;'>"+n.text+"</div>";
                	if(n.iconCls != null){
                		menu = menu + "<div class='menu-icon "+n.iconCls+"'></div>";
                	}
                	menu = menu + "</div>";
                	var htmlObj = $(menu);
                	$('#supSysMenu').append(htmlObj);
                });  
            }  
        });
        //异步加载子节点，即二级菜单  
        $('#layout_west_accordion').accordion({  
            onSelect : function(title, index) {  
                $("ul[name='" + title + "']").tree({  
                    url : '${path }/sys/resource/menu2',
                    parentField : 'pid',
                    queryParams : {  
                        title : title  
                    },  
                    animate : true,  
                    //lines : true,//显示虚线效果    
                    onClick : function(node) {
                    	if(judge(node.children)){
                    		return;
                    	}
		            	if(node.attributes != null)
		                if (node.attributes.indexOf("http") >= 0) {
		                    var url = node.attributes;
		                    addTab({
		                        title : node.text,
		                        url : url,
		                        iconCls : node.iconCls
		                    });
		                } else if (node.attributes) {
		                    var url = '${path }' + node.attributes;
		                    addTab({
		                        title : node.text,
		                        url : url,
		                        iconCls : node.iconCls
		                    });
		                }		            	
		            }
                });  
            }  
        });
        
        
        
        /*2017-06-03;wl_wpg: 加载 主页右上角的 用户消息条数：*/
        $.post('${path }/sys/base/sysUserMsg/userMsgUnreadAmount', 
                function(result) {
        	      if(result.success){	
        	    	  if(result.obj!=null && result.obj !=""){
        	    		  
        	    		  var amount = result.obj;
	         		       $("#show_amount").html(amount);
	         		      
	                   	}
        	    	 
    	           }
    	     }, 'json'); 
        
        
    });
    //判断数组是否为空
    function judge(obj){
		for(var i in obj){
			return true;
		}
		return false;
	}
    
    function addTab(params) {
        var iframe = '<iframe src="' + params.url + '" frameborder="0" style="border:0;width:100%;height:99.5%;overflow: hidden;" ></iframe>';
        var t = $('#index_tabs');
        var opts = {
        	id:params.url,
            title : params.title,
            closable : true,
            iconCls : params.iconCls,
            content : iframe,
            border : false,
            fit : true
        };
        if (t.tabs('exists', opts.title)) {
            t.tabs('select', opts.title);
        } else {
            t.tabs('add', opts);
        }
    }

    function logout(){
        $.messager.confirm('提示','确定要退出?',function(r){
            if (r){
                progressLoad();
                $.post('${path }/logout', function(result) {
                    if(result.success){
                        progressClose();
                        window.location.href='${path }';
                    }
                }, 'json');
            }
        });
    }

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
    function closeAll() {  
    	var t = $('#index_tabs');
    	var tbbs = t.tabs('tabs');
    	for(var i=0;i<tbbs.length;i++){
    		//获取所有可关闭的选项卡  
            var tab = t.tabs('getTab', i);
            if (tab.panel('options').closable) {
                t.tabs('close', i);
            }
    	}
    }  
    function menuHandler(text,iconCls){
    	//closeAll();
    	var pp = $('#layout_west_accordion').accordion('getSelected');
    	if (pp){
    		var index = $('#layout_west_accordion').accordion('getPanelIndex',pp);
    		$('#layout_west_accordion').accordion('remove',index);
    	
    		$('#layout_west_accordion').accordion('add', {
                title : text,  
                iconCls : iconCls,  
                selected : true,  
                content : '<div class="well well-small" style="padding:0px"><ul name="'+text+'"></ul></div>',  
            });
    	}
    	
	}
    
    /*2017-06-03;wl_wpg:新窗体方式，打开用户消息窗体*/
    function userMsg(url) {
    	var param = {};
    	param.url = basePath + url;
    	param.title = "用户消息";
    	param.iconCls = "icon-email";
    	addTab(param);
    	
    }
    
</script>

</head>
<body>
    <div id="loading" style="position: fixed;top: -50%;left: -50%;width: 200%;height: 200%;background: #fff;z-index: 100;overflow: hidden;">
        <img src="${staticPath }/static/style/images/ajax-loader.gif" style="position: absolute;top: 0;left: 0;right: 0;bottom: 0;margin: auto;"/>
    </div>
    <div id="index_layout">
        <div data-options="region:'north',border:false" style=" overflow: hidden;background-color: rgb(14, 144, 210); ">
            <div>
                <span style="float: right; padding-right: 20px; line-height: 50px; color: #ffffff">
                	<span style="height: 26px">未读消息：</span>
	                <a href="javascript:void(0)" onclick="userMsg('/sys/base/sysUserMsg')" data-options="iconCls:'icon-email'" class="easyui-linkbutton c1" plain="true"  >
	                <span style="color: orange;"><span id="show_amount"></span></span>
	                </a>
	                &nbsp;&nbsp;
                	<span style="height: 26px">当前用户：</span>
	                <a href="javascript:void(0)" onclick="userInfo('<shiro:principal property="id"></shiro:principal>')" class="easyui-linkbutton c1" plain="true"  >
	                <span style="color: orange;"><shiro:principal property="name"></shiro:principal></span>
	                </a>
	                &nbsp;&nbsp;
	    			<span style="height: 26px;color: white;" class="easyui-menubutton" data-options="menu:'#supSysMenu'" >切换系统</span>
	    			<div id="supSysMenu" style="width:150px;">
					    
					</div>
	    			&nbsp;&nbsp; 
	                <a href="javascript:void(0)" onclick="editUserPwd()" class="easyui-linkbutton c1" plain="true" >修改密码</a>
	                <a href="javascript:void(0)" onclick="logout()" class="easyui-linkbutton c1" plain="true" >安全退出</a>
                </span>
                <span class="header"></span>
                <span style="float: left; padding-left: 20px;line-height: 50px;font-size: medium; color: #ffffff">盾构集群远程监控与智能化支撑决策系统</span>
            </div>
        </div>
        <div data-options="region:'west',split:true" title="菜单" style="width: 200px; overflow: hidden;overflow-y:auto; padding:0px">
			 <!-- 
			<div class="well well-small" style="padding:5px">
        		<ul id="layout_west_tree" ></ul>
			</div>
			  -->
			<div id="layout_west_accordion" class="easyui-accordion" fit="true" border="false"></div>
    	</div>
        <div data-options="region:'center'" ">
            <div id="index_tabs" data-options="region:'center'" style="overflow: auto;">
                <div title="首页" data-options="border:false" style="overflow: hidden;">
                	<iframe src="home" frameborder="0" style="border:0;width:100%;height:100%;"></iframe>
					<!--<div id="viewDiv"></div> -->
                </div>
            </div>
        </div>
        <div data-options="region:'south',border:false" style="height: 30px;line-height:30px; overflow: hidden;text-align: right;background-color: #eee" >Copyright © 2017 power by <!-- <a href="http://www.crefeb.com/" target="_blank"> -->中铁一局集团城市轨道交通工程有限公司     </a></div>
    </div>

    <!--[if lte IE 7]>
    <div id="ie6-warning"><p>您正在使用 低版本浏览器，在本页面可能会导致部分功能无法使用。建议您升级到 <a href="http://www.microsoft.com/china/windows/internet-explorer/" target="_blank">Internet Explorer 8</a> 或以下浏览器：
    <a href="http://www.mozillaonline.com/" target="_blank">Firefox</a> / <a href="http://www.google.com/chrome/?hl=zh-CN" target="_blank">Chrome</a> / <a href="http://www.apple.com.cn/safari/" target="_blank">Safari</a> / <a href="http://www.operachina.com/" target="_blank">Opera</a></p></div>
    <![endif]-->

    <style>
        /*ie6提示*/
        #ie6-warning{width:100%;position:absolute;top:0;left:0;background:#fae692;padding:5px 0;font-size:12px}
        #ie6-warning p{width:960px;margin:0 auto;}
    </style>
</body>
</html>