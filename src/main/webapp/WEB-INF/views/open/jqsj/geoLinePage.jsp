<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<html>
<head>
	<METAHTTP-EQUIV="Pragma"CONTENT="no-cache">
	<METAHTTP-EQUIV="Cache-Control"CONTENT="no-cache">
	<METAHTTP-EQUIV="Expires"CONTENT="0">
	<base href="//webapi.amap.com/ui/1.0/ui/overlay/SimpleMarker/examples/" />
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>项目掘进线路</title>

    <script type="text/javascript" src="http://ajax.microsoft.com/ajax/jquery/jquery-1.7.min.js";;;></script>
    <script src="http://webapi.amap.com/maps?v=1.3&key=3d8917e8432eba19a08b60d9409ac7ef&plugin=AMap.MouseTool"></script>
    <script type="text/javascript" src="http://cache.amap.com/lbs/static/addToolbar.js"></script>
     
        <style type="text/css">
        /*自定义标注内容*/
           html,
    	   body,
		   #container {
		       width: 100%;
		       height: 100%;
		       margin: 0px;
		   }
		
		   
		   /**
		    * 利用 containerClassNames: 'my-marker' 设置label样式
		    */
		   
		   .amap-simple-marker.my-marker .amap-simple-marker-label {
		       color: #fff;
		       font-size: 1px;
		       font-style: italic;
		       text-decoration: line-through;
		   }
		   
       /*项目信息窗口样式*/
        .info {
            border: solid 1px silver;
        }
        div.info-top {
            position: relative;
            background: none repeat scroll 0 0 #F9F9F9;
            border-bottom: 1px solid #CCC;
            border-radius: 5px 5px 0 0;
        }
        div.info-top div {
            display: inline-block;
            color: #333333;
            font-size: 14px;
            font-weight: bold;
            line-height: 31px;
            padding: 0 10px;
        }
        div.info-top img {
            position: absolute;
            top: 10px;
            right: 10px;
            transition-duration: 0.25s;
        }
        div.info-top img:hover {
            box-shadow: 0px 0px 5px #000;
        }
        div.info-middle {
            font-size: 12px;
            padding: 6px;
            line-height: 20px;
        }
        div.info-bottom {
            height: 0px;
            width: 100%;
            clear: both;
            text-align: center;
        }
        div.info-bottom img {
            position: relative;
            z-index: 104;
        }
        span {
            margin-left: 5px;
            font-size: 11px;
        }
        .info-middle img {
            float: left;
            margin-right: 6px;
        }
        
 	
    </style>
</head>
<body>
<script src="//webapi.amap.com/ui/1.0/main.js"></script>
<div id="container"></div>

<script>
	var basePath = "${staticPath }";
	var imageBrowsePath = "${imageBrowsePath }";
	var str=${projectPoint};
    var projectId=${projectId};
    var project=${project};
    
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

    var map = new AMap.Map("container", {
        resizeEnable: true,
        center: point,
        zoom: 17
    });	
    addMarker();
    //添加marker标记
    function addMarker() {
        map.clearMap();
        var marker = new AMap.Marker({
            map: map,
            position: point            
        });
     	// 设置点标记的动画效果，此处为弹跳效果
        //marker.setAnimation('AMAP_ANIMATION_BOUNCE');
        //鼠标点击marker弹出自定义的信息窗体
        marker.setTitle('点击查看项目信息');
        AMap.event.addListener(marker, 'click', function() {
            infoWindow.open(map, marker.getPosition());
        });
    }
    //在地图上显示该区间的其他线路轨迹
    var qtLineLists=${qtLineLists};
	var line
	AMapUI.loadUI(['overlay/SimpleMarker'], function(SimpleMarker) {
    for(var ii=0;ii<qtLineLists.length;ii++){
    	var qtLineList = qtLineLists[ii];
    	var po=[];
        var qtpoints = [];
        for(var i=0;i<qtLineList.length;i++){    	
    		qtpoints[i]=[];
    		qtpoints[i].push(qtLineList[i].lng);
    		qtpoints[i].push(qtLineList[i].lat);
    		if(i==0){
    		    po[0]=qtLineList[i].lng;
    		    po[1]=qtLineList[i].lat;
    		    var l='';
    		    l=qtLineList[i].lineName;
    		    
    		        new SimpleMarker({
    		            //设置节点属性
    		            iconLabel: {
    		                //普通文本
    		                innerHTML: ''+l,
    		                //设置样式
    		                style: {
    		                    color: '#fff',
    		                    fontSize: '50%',
    		                    marginTop: '2px'
    		                }
    		            },

    		            iconStyle: 'green',
    		            map: map,
    		            position:po
    		        });
    		    
    		}
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
    	
    }
	});


//实例化信息窗体
var title = project.proName,
    content = [];
/* content.push("<img src='http://tpc.googlesyndication.com/simgad/5843493769827749134'>地址：北京市朝阳区阜通东大街6号院3号楼东北8.3公里");
content.push("电话：010-64733333");
content.push("<a href='http://ditu.amap.com/detail/B000A8URXB?citycode=110105'>详细信息</a>"); */
content.push("工程名称："+project.proName);
if(project.abbreviation!=undefined){
	content.push("工程简称："+project.abbreviation);	
}
content.push("承建单位:"+project.cjdw);
if(project.jldw!=undefined){
	content.push("监理单位:"+project.jldw);	
}
if(project.jsdw!=undefined){
	content.push("建设单位:"+project.jsdw);	
}
if(project.country!=undefined){
	content.push("所在国家:"+project.country);
}
if(project.area!=undefined){
	content.push("所在片区:"+project.area);	
}
if(project.province!=undefined){
	content.push("所在省份:"+project.province);
}
if(project.city!=undefined){
	content.push("所在城市:"+project.city);
}
if(project.tunnellength!=undefined){
	content.push("总长度:"+project.tunnellength);
}
if(project.ringwidth!=undefined){
	content.push("管片长度:"+project.ringwidth);
}
if(project.startdate!=undefined){
	content.push("合同工期起始:"+project.startdate);
}
if(project.estimateenddate!=undefined){
	content.push("合同工期截止:"+project.estimateenddate);
}
if(project.htje!=undefined){
	content.push("合同金额:"+project.htje);
}
if(project.klwc!=undefined){
	content.push("开累完成:"+project.klwc);
}
if(project.gpwj!=undefined){
	content.push("管片外径:"+project.gpwj);
}
if(project.proState!=undefined){
	if(project.proState==0){
		content.push("施工状态: 未开工");
	}
	if(project.proState==1){
		content.push("施工状态: 在建");
	}
	if(project.proState==2){
		content.push("施工状态: 完工");
	}
}
if(project.projectaddress!=undefined){
	content.push("施工地址:"+project.projectaddress);
}
if(project.description!=undefined){
	content.push("工程简介:"+project.description);
}


var infoWindow = new AMap.InfoWindow({
    isCustom: true,  //使用自定义窗体
    content: createInfoWindow(title, content.join("<br/>")),
    offset: new AMap.Pixel(16, -45)
});

//构建自定义信息窗体
function createInfoWindow(title, content) {
    var info = document.createElement("div");
    info.className = "info";

    //可以通过下面的方式修改自定义窗体的宽高
    //info.style.width = "400px";
    //info.style.height="600px";
    // 定义顶部标题
    var top = document.createElement("div");
    var titleD = document.createElement("div");
    var closeX = document.createElement("img");
    top.className = "info-top";
    titleD.innerHTML = title;
    closeX.src = "http://webapi.amap.com/images/close2.gif";
    closeX.onclick = closeInfoWindow;

    top.appendChild(titleD);
    top.appendChild(closeX);
    info.appendChild(top);

    // 定义中部内容
    var middle = document.createElement("div");
    middle.className = "info-middle";
    middle.style.backgroundColor = 'white';
    middle.innerHTML = content;
    info.appendChild(middle);

    // 定义底部内容
    var bottom = document.createElement("div");
    bottom.className = "info-bottom";
    bottom.style.position = 'relative';
    bottom.style.top = '0px';
    bottom.style.margin = '0 auto';
    var sharp = document.createElement("img");
    sharp.src = "http://webapi.amap.com/images/sharp.png";
    bottom.appendChild(sharp);
    info.appendChild(bottom);
    return info;
}

//关闭信息窗体
function closeInfoWindow() {
    map.clearInfoWindow();
}    
</script>
</body>
</html>