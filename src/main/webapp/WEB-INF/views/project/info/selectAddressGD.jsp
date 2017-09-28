<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>鼠标拾取地图坐标</title>
    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main1119.css"/>
    <script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery/jquery-1.7.min.js";;;></script>
    <script type="text/javascript"
            src="http://webapi.amap.com/maps?v=1.3&key=3d8917e8432eba19a08b60d9409ac7ef&plugin=AMap.Autocomplete"></script>
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
</head>
<body>
<div id="container"></div>
<div id="myPageTop">
    <table>
        <tr>
            <td>
                <label>按关键字搜索：</label>
            </td>
            <td class="column2">
                <label>左击获取经纬度：</label>
            </td>
        </tr>
        <tr>
            <td>
                <input type="text" placeholder="请输入关键字进行搜索" id="tipinput">
            </td>
            <td class="column2">
                <input type="text" readonly="true" id="lnglat">
            </td>
            <td>
            	<input type="button" value="确定" onclick="ok()" style="width:50px"/>
            </td>
        </tr>
    </table>
</div>
<script type="text/javascript">
	var proPosition=${proPosition};
	if(proPosition!=""){
		point=proPosition.split(",");
	   var map = new AMap.Map("container", {
	       resizeEnable: true,
	       center: point
	   });		
	}else{
		var map = new AMap.Map("container", {
		   resizeEnable: true
		});
	}
    var marker    =   new AMap.Marker({
        map: map
    });
    //为地图注册click事件获取鼠标点击出的经纬度坐标
    var clickEventListener = map.on('click', function(e) {
        document.getElementById("lnglat").value = e.lnglat.getLng() + ',' + e.lnglat.getLat()
        lnglatXY = [e.lnglat.getLng(),e.lnglat.getLat()]; //已知点坐标   
        marker.setPosition(lnglatXY); 
        regeocoder(lnglatXY);
    });
    var auto = new AMap.Autocomplete({
        input: "tipinput"
    });
    AMap.event.addListener(auto, "select", select);//注册监听，当选中某条记录时会触发
    function select(e) {
        if (e.poi && e.poi.location) {
            map.setZoom(15);
            map.setCenter(e.poi.location);
        }else{
        	map.setZoom(15);
        	map.setCity($('#tipinput').val());
        }
    }
    
	function ok(){
		if($("#lnglat").val()!=""){			
			/* parent.$('#addForm').form('load', $("#lnglat").val());
			parent.$('#editForm').form('load', $("#lnglat").val()); */
			parent.$('#add_position').textbox("setValue","");
			parent.$('#add_position').textbox("setValue",$("#lnglat").val());

			parent.$('#edit_position').textbox("setValue","");
			parent.$('#edit_position').textbox("setValue",$("#lnglat").val());
			parent.$('#openDiv').dialog('close');
		}else{
			parent.$.messager.alert('提示','请选取位置！','info');
		}
	}
	
	//逆地理编码
    function regeocoder(lnglatXY) {  
          AMap.plugin('AMap.Geocoder',function(){   
           var geocoder = new AMap.Geocoder({
                radius: 1000,
                extensions: "all"
            });             
            /* var marker1 = new AMap.Marker({  //加点
                map: map,
                position: lnglatXY
            }); */
            marker.setPosition(lnglatXY);
            map.setFitView();
          })    
    }
</script>
</body>
</html>