<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<html>
<head>
	<title>位置选取</title>
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body,html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
		#allmap{height:95%;width:100%;}
		#r-result{width:100%; font-size:14px;}
		#bt{float:right}
	</style>
	<script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery/jquery-1.7.min.js";;;></script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=sM8k8zBNvLR5mUQFIsauKOFVFvyB2R9Q"></script>
	<!--加载鼠标绘制工具-->
	<script type="text/javascript" src="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.js"></script>
	<link rel="stylesheet" href="http://api.map.baidu.com/library/DrawingManager/1.4/src/DrawingManager_min.css" />
	
</head>
<body>

  	<!-- <input type="text" tabindex="3" class="form-control" name="d.points" placeholder="线路坐标" id="points" readonly="readonly"> -->
  	<input type="hidden" id="points"/>
	<div id="allmap"></div>
	<div id="bt">
		<!-- <a class="easyui-linkbutton" href="javascript:map.clearOverlays();$('#points').val('');"> 重绘</a> -->
		<input type="button" onclick="javascript:map.clearOverlays();$('#points').val('');" value="重绘">
		<input type="button" onclick="save()" value="提交">		
	</div>
</body>
</html>
<script type="text/javascript">
var basePath = "${staticPath }";
var imageBrowsePath = "${imageBrowsePath }";
var _currOrgzType = <shiro:principal property="orgzType"></shiro:principal>;
	var lineId=${lineId};
	var list=${list};
	// 百度地图API功能
    var map = new BMap.Map('allmap');
    var geoc = new BMap.Geocoder();
	map.enableScrollWheelZoom(true);   
	map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
	// 添加带有定位的导航控件
	var navigationControl = new BMap.NavigationControl({
	  // 靠左上角位置
	  anchor: BMAP_ANCHOR_TOP_LEFT,
	  // LARGE类型
	  type: BMAP_NAVIGATION_CONTROL_LARGE,
	  // 启用显示定位
	  enableGeolocation: true
	});
	map.addControl(navigationControl);    
	
	
    var poi = new BMap.Point(116.307852,40.057031);
    map.centerAndZoom(poi, 16);
    map.enableScrollWheelZoom();  
    var overlays = [];
	var overlaycomplete = function(e){
        overlays.push(e.overlay);
    	var num = e.overlay.getPath();
    	var paths = "";
    	if(num != null)
    	for(var i=0;i<num.length;i++){
    		paths = paths + num[i].lng+","+num[i].lat
    		if(i < num.length-1){
    			paths = paths +";";
    		}
    	}
    	$("#points").val($("#points").val()+paths);
    };
    var styleOptions = {
        strokeColor:"red",    //边线颜色。
        fillColor:"red",      //填充颜色。当参数为空时，圆形将没有填充效果。
        strokeWeight: 3,       //边线的宽度，以像素为单位。
        strokeOpacity: 0.8,	   //边线透明度，取值范围0 - 1。
        fillOpacity: 0.6,      //填充的透明度，取值范围0 - 1。
        strokeStyle: 'solid' //边线的样式，solid或dashed。
    }
    //实例化鼠标绘制工具
    var drawingManager = new BMapLib.DrawingManager(map, { 
        isOpen: false, //是否开启绘制模式
        enableDrawingTool: true, //是否显示工具栏
        drawingToolOptions: {
            anchor: BMAP_ANCHOR_TOP_RIGHT, //位置
            offset: new BMap.Size(200, 5), //偏离值
            drawingModes:[
            	BMAP_DRAWING_POLYLINE
            ]
        },
        polylineOptions: styleOptions 
    });  
	 //添加鼠标绘制工具监听事件，用于获取绘制结果
    drawingManager.addEventListener('overlaycomplete', overlaycomplete);
	 
    var points = [];
    var su='';
    for(var i=0;i<list.length;i++){    	
    	points.push(new BMap.Point(list[i].lng,list[i].lat));
/*     	su+''+list[i].lng+','+list[i].lat+';' */
		su+=list[i].lng;
		su+=',';
		su+=list[i].lat;
		su+=';';
    }
    $('#points').val(su);
    polyline = new BMap.Polyline(points, 
    {strokeColor:"blue", strokeWeight:2, strokeOpacity:0.5});   //创建折线
	map.addOverlay(polyline);   //增加折线
	
	if(list.length == 0){
		map.centerAndZoom(poi, 16);
	}else{
		map.centerAndZoom(points[parseInt(points.length/2)], 16);
	}
	
	function save(){
		var points=$("#points").val();
		var data={points:points,lineId:lineId};
		jQuery.ajax({
	        url: basePath+'/sgkshgl/jjgjgl/save',              //请求地址
	        type: "GET",                       //请求方式
	        data: data,        //请求参数
	        async: true,
	        dataType: "json",
	        success: function (result,status) {
	        	if(status=="success"){
	        	parent.$('#openDiv').dialog('close');
	        	}
	        }				
		});
	} 
</script>