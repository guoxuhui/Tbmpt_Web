<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<html>
<head>
	<title>位置选取</title>
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		body,html{width: 100%;height: 100%;margin:0;font-family:"微软雅黑";}
		#allmap{height:86%;width:100%;}
		#r-result{width:100%; font-size:14px;}
	</style>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=sM8k8zBNvLR5mUQFIsauKOFVFvyB2R9Q"></script>
</head>
<body>
	<div id="r-result">
		城市名: 
		<input id="cityName" type="text" style="width:100px; margin-right:10px;" />
		<input type="button" value="查询" onclick="theLocation()" />
		<input type="button" value="确定" onclick="ok()" />
		<p id="info">详细地址</p>
	</div>
	<div id="allmap"></div>
</body>
</html>
<script type="text/javascript">
	// 百度地图API功能
	var map = new BMap.Map("allmap");    // 创建Map实例
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
	
	map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);  // 初始化地图,设置中心点坐标和地图级别
	map.setCurrentCity("北京");          // 设置地图显示的城市 此项是必须设置的
	
	function theLocation(){
		var city = document.getElementById("cityName").value;
		if(city != ""){
			map.centerAndZoom(city,11);      // 用城市名设置地图中心点
		}
	}
	
	var marker;
	var position;
	var province;
	var city;
	var projectaddress;
	//单击获取点击的经纬度
	map.addEventListener("click",function(e){
		var pt = new BMap.Point(e.point.lng, e.point.lat);
      	marker = new BMap.Marker(pt); // 创建点
      	map.clearOverlays();
      	position = "";
      	addComp = "";
      	map.addOverlay(marker);//增加点
      	var label = new BMap.Label("位置:"+e.point.lng+" "+e.point.lat,{offset:new BMap.Size(20,-10)});
		marker.setLabel(label);
		
		position = e.point.lng+","+e.point.lat;
		//设置地址
		geoc.getLocation(pt, function(rs){
			addComp = rs.addressComponents;
			province = addComp.province;
			city = addComp.city;
			projectaddress = addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber;
			document.getElementById("info").innerHTML = position+";"+projectaddress
		}); 
		
	});
	
	function ok(){
		if(position && position != "" &&
			addComp && addComp != ""){
			
			parent.$('#addForm').form('load', {position:position,province:province,city:city,projectaddress:projectaddress});
			parent.$('#editForm').form('load', {position:position,province:province,city:city,projectaddress:projectaddress});
			
			parent.$('#openDiv').dialog('close');
		}else{
			parent.$.messager.alert('提示','请选取位置！','info');
		}
	}
	

</script>