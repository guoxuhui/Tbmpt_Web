<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<html>
<head>
	<METAHTTP-EQUIV="Pragma"CONTENT="no-cache">
	<METAHTTP-EQUIV="Cache-Control"CONTENT="no-cache">
	<METAHTTP-EQUIV="Expires"CONTENT="0">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>鼠标绘制点线面</title>
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery/jquery-1.7.min.js";;;></script>
    <script src="http://webapi.amap.com/maps?v=1.3&key=3d8917e8432eba19a08b60d9409ac7ef&plugin=AMap.MouseTool"></script>
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
</head>
<body>
<div id="container"></div>
<div class="button-group">
<!--     <input type="button" class="button" value="鼠标绘制点" id="point"/>
    <input type="button" class="button" value="鼠标绘制面" id="polygon"/> -->
    <input type="hidden" readonly="true" id="lnglat">
    <input type="button" class="button" value="点击绘制轨迹" id="line"/>
    <input type="button" class="button" value="重绘" onclick="removeLine()"/>
    <input type="button" class="button" value="保存" onclick="save()"/>
</div>
<script>
	var basePath = "${staticPath }";
	var imageBrowsePath = "${imageBrowsePath }";
	//var point="${line.startPosition}";
	var str=${projectPoint};
    var list=${list};
    var lineId=${lineId};
    var projectId=${projectId};
    
	var point=new Array()
	if(str==""){
		if(list!=""){
			point[0]=list[0].lng;
			point[1]=list[0].lat;			
		}else{
			point[0]=116.397428;
			point[1]=39.90923;
		}
	}else{
		point=str.split(",");	
	}
    
	//var point=new AMap.Point(${line.startPosition});
    var map = new AMap.Map("container", {
        resizeEnable: true,
        center: point,
        zoom: 13
    });	
    var clickEventListener = map.on('click', function(e) {
//        document.getElementById("lnglat").value = e.lnglat.getLng() + ',' + e.lnglat.getLat();
        var path="";
        path=e.lnglat.getLng() + ',' + e.lnglat.getLat()+";";
        $("#lnglat").val($("#lnglat").val()+path);
    });
    //在地图中添加MouseTool插件
    var mouseTool = new AMap.MouseTool(map);
/*     AMap.event.addDomListener(document.getElementById('point'), 'click', function() {
        mouseTool.marker({offset:new AMap.Pixel(-14,-11)});
    }, false);
    AMap.event.addDomListener(document.getElementById('polygon'), 'click', function() {
        mouseTool.polygon();
    }, false); */
    AMap.event.addDomListener(document.getElementById('line'), 'click', function() {
        mouseTool.polyline();
    }, false);
	AMap.plugin([ 'AMap.ToolBar', 'AMap.Scale', 'AMap.MapType' ],
		function() {
			map.addControl(new AMap.ToolBar());

			map.addControl(new AMap.Scale());

			map.addControl(new AMap.MapType({
				defaultType : 1,
				showRoad : true
			}));
		});
    
    var points = [];
    var su='';
    for(var i=0;i<list.length;i++){    	
//    	points.push(new AMap.LngLat(list[i].lng,list[i].lat));
/*     	su+''+list[i].lng+','+list[i].lat+';' */
		points[i]=[];
		points[i].push(list[i].lng);
		points[i].push(list[i].lat);
		su+=list[i].lng;
		su+=',';
		su+=list[i].lat;
		su+=';';
    }

    var polyline = new AMap.Polyline({
        path: points,          //设置线覆盖物路径
        strokeColor: "#3366FF", //线颜色
        strokeOpacity: 1,       //线透明度
        strokeWeight: 3,        //线宽
        strokeStyle: "solid",   //线样式
        strokeDasharray: [10, 5] //补充线样式
    });
    polyline.setMap(map);
    $('#lnglat').val(su);
    
    //在地图上显示该区间的其他线路轨迹
    var qtLineList=${qtLineList};
    var qtpoints = [];
    for(var i=0;i<qtLineList.length;i++){    	
		qtpoints[i]=[];
		qtpoints[i].push(qtLineList[i].lng);
		qtpoints[i].push(qtLineList[i].lat);
    }
    var qtpolyline = new AMap.Polyline({
        path: qtpoints,          //设置线覆盖物路径
        strokeColor: "#3366FF", //线颜色
        strokeOpacity: 1,       //线透明度
        strokeWeight: 3,        //线宽
        strokeStyle: "solid",   //线样式
        strokeDasharray: [10, 5] //补充线样式
    });
    qtpolyline.setMap(map);
    
    //重绘
    function removeLine(){
    	polyline.setMap(null);
    	mouseTool.close(true);
    	$("#lnglat").val("");
    }

    
    //保存绘制
	function save(){
		var points=$("#lnglat").val();
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
	        	var number = Math.random();
//	        	parent.$('#dataGrid').datagrid('load',{'proId':projectId,'random':number});
//	        	parent.$('#dataGrid').datagrid("options").url =basePath+"/sgkshgl/jjgjgl/dataGrid?proId="+projectId+"&random="+number;  
	        	parent.$('#dataGrid').datagrid('reload');	        	
	        }				
		});
	} 
</script>
</body>
</html>