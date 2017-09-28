/**
 * for tunnel boring machine project
 */
var projectArrJson = [];
var projectJson = {};
var nowOpenProjectId = null;
var projectInfoDialogRect = ["600px","515px"];
//
var appId = "321";
var appSecret = "999999";
var role = "";
//模块2
var xmId = "";

/************************************************************************************************************************/
/** -工具- **/

var alertMsg = {};
alertMsg.loadingDialogId = null;
alertMsg.loading = function(){
	alertMsg.loadingDialogId = layer.load(1,{shade:0.3});
};
alertMsg.closeLoading = function(){
	layer.close(alertMsg.loadingDialogId);
	alertMsg.loadingDialogId = null;
}
/**
 * 求日期之间天数
 * @param date1
 * @param date2
 * @returns {Number}
 */
function dateDiff(date1, date2){
	return parseInt(date1.getTime() - date2.getTime())/(24 * 60 * 60 * 1000);
}

/**
 * 取url参数
 * @param name
 * @returns
 */
function getQueryString(name){
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var str = decodeURI(window.location.search.substr(1));
     var r = str.match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}

//TODO 错误提示
function errMeg(msg){
	console.log("time: "+new Date()+"errMsg: "+msg);
}

function setTitle(t){
	var msg = "盾构集群远程监控管理平台";
	if(t)
		msg = t;
	$("#topLogoText").text(msg);
}
/************************************************************************************************************************/
/** -模块1- **/
/**1.1
 * 获取项目信息列表
 */
function loadProjects(){
	appId 		= getQueryString("appId");
	appSecret 	= getQueryString("appSecret");
	role 		= getQueryString("role");
	setTitle();
	$.ajax({
		url:"getData.do",
		type:"POST",
		data:{
			_model:"1.1",
			appId: appId,
			appSecret: appSecret
			},
		dataType:'json',
		success:function(jsonObj){
			if(jsonObj.success){
				jsonObj = jsonObj.obj;
				//TODO test项目坐标点
				_mesh.push( addPointWithNameTextInEarth("16040002",0.333,1.169,"fix") );//
				_mesh.push( addPointWithNameTextInEarth("15090005",0.302,1.113,"running") );//西安
				_mesh.push( addPointWithNameTextInEarth("15090002",0.323,1.158,"running") );
	        	_mesh.push( addPointWithNameTextInEarth("15090003",0.374,1.130,"running") );
				_mesh.push( addPointWithNameTextInEarth("15090004",0.333,1.145,"fix") );
				_mesh.push( addPointWithNameTextInEarth("15090006",0.255,1.198,"running") );
				_mesh.push( addPointWithNameTextInEarth("15090007",0.370,1.141,"running") );
				_mesh.push( addPointWithNameTextInEarth("15090008",0.290,1.08,"running") );
				_mesh.push( addPointWithNameTextInEarth("15090009",0.273,1.142,"running") );
				_mesh.push( addPointWithNameTextInEarth("15120001",0.330,1.103,"running") );
			    _mesh.push( addPointWithNameTextInEarth("15120002",0.323,1.08,"fix") );
				_mesh.push( addPointWithNameTextInEarth("16040001",0.323,1.172,"running") );
				_mesh.push( addPointWithNameTextInEarth("16040003",0.360,1.160,"fix") );
				_mesh.push( addPointWithNameTextInEarth("16100001",0.333,1.140,"running") );
				_mesh.push( addPointWithNameTextInEarth("16110001",0.330,1.08,"running") );
				_mesh.push( addPointWithNameTextInEarth("16110002",0.323,1.153,"fix") );
				_mesh.push( addPointWithNameTextInEarth("16120001",0.280,1.142,"fix") );
				_mesh.push( addPointWithNameTextInEarth("16120002",0.338,1.145,"fix") );
				_mesh.push( addPointWithNameTextInEarth("15060001",0.315,1.172,"fix") );
				_mesh.push( addPointWithNameTextInEarth("15090001",0.370,1.135,"fix") );
				projectArrJson = jsonObj;
				initProjectList();
				addLocalFloatBall();
			}else{
				errMeg(jsonObj.msg);
			}
        }
	});
//	项目总累计产值
	$.ajax({
		url:"getData.do",
		type:"POST",
		data:{
			_model:"1.2",
			appId: appId,
			appSecret: appSecret
			},
		dataType:'json',
		success:function(jsonObj){
			if(jsonObj.success){
				jsonObj = jsonObj.obj;
				initTopCharts(jsonObj);
			}else{
				errMeg(jsonObj.msg);
			}
        }
	}); 
	//安全质量
	var mydate = new Date();
	var m = mydate.getMonth()+1;
	var d = mydate.getDate()+1;
	var nowTemp = mydate.getFullYear()+"-"+(m<10?"0"+m:m)+"-"+(d<10?"0"+d:d);
	mydate.setDate(mydate.getDate()-7);
	var startTemp = mydate.getFullYear()+"-"+(m<10?"0"+m:m)+"-"+(d<10?"0"+d:d);;
	$.ajax({
		url:"getData.do",
		type:"POST",
		type:"POST",
		data:{
			_model:"1.4",
			appId: appId,
			appSecret: appSecret,
			strTime: startTemp, 
			endTime: nowTemp
			},
		dataType:'json',
		success:function(jsonObj){
			if(jsonObj.success){
				jsonObj = jsonObj.obj;
				if(jsonObj.length==0){//TODO test
					jsonObj = [
							   {"rq" : "日期","zlwts" : "2","aqwts" : "6","yhs" : "0" },
							   {"rq" : "日期","zlwts" : "1","aqwts" : "2","yhs" : "5" },
							   {"rq" : "日期","zlwts" : "5","aqwts" : "1","yhs" : "2" },
							   {"rq" : "日期","zlwts" : "4","aqwts" : "5","yhs" : "3" },
							   {"rq" : "日期","zlwts" : "0","aqwts" : "3","yhs" : "2" },
							   {"rq" : "日期","zlwts" : "2","aqwts" : "7","yhs" : "7" },
							   {"rq" : "日期","zlwts" : "1","aqwts" : "2","yhs" : "1" },
							  ];
						for(var i =0;i<7;i++){
							var dateTemp = mydate.getFullYear()+"-"+(mydate.getMonth()+1)+"-"+mydate.getDate();
							jsonObj[i].rq=dateTemp;
							mydate.setDate(mydate.getDate()+1);
						}
				}
				initMiddleCharts(jsonObj);
			}else{
				errMeg(jsonObj.msg);
			}
        }
	}); 
	
	var date = new Date();
	var year = date.getFullYear();
	year = 2016;//TODO test
	$.ajax({
		url:"getData.do",
		type:"POST",
		data:{
			_model:	"1.6",
			appId: appId,
			appSecret: appSecret,
			nd: year
			},
		dataType:'json',
		success:function(jsonObj){
			if(jsonObj.success){
				jsonObj = jsonObj.obj;
				initBottomChartsLeft(jsonObj);
			}else{
				errMeg(jsonObj.msg);
			}
        }
	}); 
	
	$.ajax({
		url:"getData.do",
		type:"POST",
		data:{
			_model:	"1.7",
			appId: appId,
			appSecret: appSecret,
			nd: year
			},
		dataType:'json',
		success:function(jsonObj){
			if(jsonObj.success){
				jsonObj = jsonObj.obj;
				initBottomChartsRight(jsonObj);
			}else{
				errMeg(jsonObj.msg);
			}
        }
	}); 
}


/**左侧菜单 */
function initProjectList(obj){
	var title = "掘进进度";
	if(obj==null || obj==undefined){
		$("div.right_caidan1_div").children().first().removeClass("right_xuanxiang2").addClass("right_xuanxiang1");
	}else{
		$("div.right_caidan1_div").children("a").removeClass("right_xuanxiang1").addClass("right_xuanxiang2");
		$(obj).removeClass("right_xuanxiang2").addClass("right_xuanxiang1");
		title = $(obj).text();
	}
	
	var span = $("span.right_list_div");
    span.empty();
    for(var i=0;i<projectArrJson.length;i++){
    	var j = projectArrJson[i];
     	
     	var rootSpan 	= $("<span/>").addClass("right_list");
     	var numSpan 	= $("<span/>").addClass("right_num2").text(i+1);//1:red;2:yellow;3:gray
     	var titleLink	= $("<a/>").addClass("right_name").text(j.xmMc);
     	var progressSpan= $("<span/>").addClass("right_jindu_div");
     	var progSpan1	= $("<span/>").addClass("right_jindu_l2");//right_jindu_l1:red;right_jindu_l2:yellow;right_jindu_l3:gray
     	var progSpan2	= $("<span/>").addClass("right_jindu_r2");
     	var progSpan2Num= $("<span/>").addClass("right_jindu_m2");
     	if(title=="掘进进度"){
     		if(j.dqjzjhs==""){//TODO test
     			j.dqjzjhs = parseInt(Math.random()*100);
     			j.zhs = 100;
     		}
     		progSpan2Num.css("width",(j.dqjzjhs/j.zhs*100)+"%");
     	}else if(title=="产值"){
     		if(j.dqxmwcz==""){
     			j.dqxmwcz = parseInt(Math.random()*100);
     			j.xmcz = 100;
     		}
     		progSpan2Num.css("width",(j.dqxmwcz/j.xmcz*100)+"%");
     	}else if(title=="工期"){	
     		if(j.kgrq==""){
     			j.kgrq = "2016-"+parseInt(Math.random()*12+1)+"-"+parseInt(Math.random()*25+1);
     		}
     		if(j.zts==""){
     			if(j.yjwcrq==""){
     				j.zts = 365;
     			}else{
     				j.zts = dateDiff(new Date(j.yjwcrq),new Date(j.kgrq));
     			}
     		}
     		
     		progSpan2Num.css("width",(dateDiff(new Date(),new Date(j.kgrq))/j.zts*100)+"%");
     	}
     	progSpan2.append(progSpan2Num);
     	progressSpan.append(progSpan1).append(progSpan2);
     	rootSpan.append(numSpan).append(titleLink).append(progressSpan).data("json",j).click(function(){
     		var rect = this.getBoundingClientRect();
     		var width = parseInt(projectInfoDialogRect[0].replace("px",""));
     		var left = rect.left-width;
     		var top = rect.top;//+rect.height*0.5;
     		var j = $(this).data("json");
         	addMarkPoint(j.xmId);
     		alertProjectInfo(j.xmId,[top+"px",left+"px"])
     	});
     	span.append(rootSpan);
     }
}

/**弹出项目信息页面层
 * @param id 项目ID
 * @param offset 弹出位置
 */
function alertProjectInfo(id,offset){
	if(nowOpenProjectId==id){//不重复打开
		return;
	}
	if(alertMsgDivId==null){
		layer.closeAll();
	}
	nowOpenProjectId=id;
	addMarkPoint(id);
	//高度判断
	var windowH = window.innerHeight;
	var height = parseInt(projectInfoDialogRect[1].replace("px",""));
	var top = parseInt(offset[0].replace("px",""));
	if((height+top)>windowH){
		offset[0] = (windowH-height)+"px";
	}
	
	var j = {};
	for(var i=0;i<projectArrJson.length;i++){
		var json = projectArrJson[i];
		if(json.xmId==id){
			j = json;
			break;
		}
	}
	
	layer.open({
		type: 2,
		title: '',
		content: 'pages/projectInfo.html',
		area: projectInfoDialogRect,
		shade: 0,
		resize:false,
		fixed :true,
		move:false,
		offset:offset,
		closeBtn: 0,
		skin: "alertDiv-class",
//		btn:['进入项目','关闭'],
//		yes: function(index, layero){
//			layer.close(index);
//		},
		success: function(layero, index){
			var body = layer.getChildFrame('body', index);
			body.find('#closeBtn').click(function(){
				layer.closeAll();alertMsgDivId=null;
			});
			body.find('#yesBtn').click(function(){
				gotoProjectPage(appId,appSecret,role,json.xmId);
			});
			body.find('#xmMc').text(j.xmMc);
			body.find('#zbjg').text(j.zbjg);
			body.find('#xmcz').text(j.xmcz);
			body.find('#dqxmwcz').text(j.dqxmwcz);
			body.find('#csdq').text(j.csdq);
			body.find('#zts').text(j.zts);
			body.find('#kgrq').text(j.kgrq);
			body.find('#yjwcrq').text(j.yjwcrq);
			body.find('#dtxl').text(j.dtxl);
			body.find('#sgbd').text(j.sgbd);
			body.find('#fzr').text(j.fzr);
			body.find('#zhs').text(j.zhs);
			body.find('#dqjzjhs').text(j.dqjzjhs);
		},
		end: function(index){ 
			nowOpenProjectId = null;
			removeMarkPoint();
		}
	});
}

function alertPage(page,areaSize){
	layer.closeAll();
	if(page==null || page==undefined){
		return;
	}
	try{
		removeMarkPoint();//project.jsp页面不涉及删除point功能
	}catch(err){
		console.log(err);
	}
	
	var area = ["975px","600px"];
	if(areaSize!=null && areaSize!=undefined){
		area = areaSize;
	}
	alertMsgDivId = layer.open({
		type: 2,
		title: '',
		closeBtn: 0,
//		shadeClose :true,
		content: page,
		area: area,
		shade: 0.5,
		resize:false,
		skin: "alertDivShowTitle-class",
		fixed :true,
		move:false,
		yes: function(index, layero){
			layer.close(index);
		},
		success: function(layero, index){
			var body = layer.getChildFrame('body', index);
			body.find('#closeBtn').click(function(){
				layer.closeAll();alertMsgDivId=null;
			});
			$("#top_div").addClass("blur");
			$("#content_div").addClass("blur");
			$("#svgContent").css("-webkit-filter","blur(5px)").css("-moz-filter","blur(5px)").css("-ms-filter","blur(5px)")
			.css("filter","blur(5px)").css("filter","progid:DXImageTransform.Microsoft.Blur(PixelRadius=5, MakeShadow=false)");
		},
		end: function(index){
			$("#top_div").removeClass("blur");
			$("#content_div").removeClass("blur");
			$("#svgContent").css("-webkit-filter","blur(0px)").css("-moz-filter","blur(0px)").css("-ms-filter","blur(0px)")
			.css("filter","blur(0px)").css("filter","progid:DXImageTransform.Microsoft.Blur(PixelRadius=0, MakeShadow=false)");
		}
	});
}

/*
function demo(){
	layer.closeAll();
	removeMarkPoint();
	alertMsgDivId = layer.open({
		type: 2,
		title: '',
		closeBtn: 0,
		content: "pages/chartDemo.html",
		area: ["50%","80%"],
		shade: 0.5,
		resize:false,
		skin: "alertDivShowTitle-class",
		fixed :true,
		move:false,
		yes: function(index, layero){
			layer.close(index);
		},
		success: function(layero, index){
			var body = layer.getChildFrame('body', index);
			body.find('#closeBtn').click(function(){
				layer.closeAll();alertMsgDivId=null;
			});
			$("#top_div").addClass("blur");
			$("#content_div").addClass("blur");
		},
		end: function(index){
			$("#top_div").removeClass("blur");
			$("#content_div").removeClass("blur");
		}
	});
}
function demo1(){
	layer.closeAll();
	removeMarkPoint();
	alertMsgDivId = layer.open({
		type: 2,
		title: '',
		content: "pages/tableDemo.html",
		area: ["50%","80%"],
		shade: 0.5,
		resize:false,
		closeBtn: 0,
		skin: "alertDivShowTitle-class",
		fixed :true,
		move:false,
		yes: function(index, layero){
			layer.close(index);
		},
		success: function(layero, index){
			var body = layer.getChildFrame('body', index);
			body.find('#closeBtn').click(function(){
				layer.closeAll();alertMsgDivId=null;
			});
			$("#top_div").addClass("blur");
			$("#content_div").addClass("blur");
		},end: function(index){ 
			$("#top_div").removeClass("blur");
			$("#content_div").removeClass("blur");
		}
	});
}
*/

function gotoProjectPage(appId,appSecret,role,xmId){
	
	var ua = window.navigator.userAgent;  
    var isIE = window.ActiveXObject != undefined && ua.indexOf("MSIE") != -1;  
    var isFirefox = ua.indexOf("Firefox") != -1;  
    var isOpera = window.opr != undefined;  
    var isChrome = ua.indexOf("Chrome") && window.chrome;  
    var isSafari = ua.indexOf("Safari") != -1 && ua.indexOf("Version") != -1;  
    if (isIE) {  
    	window.location.href="project.jsp?appId="+appId+"&appSecret="+appSecret+"&role="+role+"&xmId="+xmId;
    } else if (isFirefox) {  
    	window.location.href="project.jsp?appId="+appId+"&appSecret="+appSecret+"&role="+role+"&xmId="+xmId;
    } else if (isOpera) {  
    	window.location.href="project.jsp?appId="+appId+"&appSecret="+appSecret+"&role="+role+"&xmId="+xmId;
    } else if (isChrome) { 
    	window.location.href="../project.jsp?appId="+appId+"&appSecret="+appSecret+"&role="+role+"&xmId="+xmId;
    } else if (isSafari) { 
    	window.location.href="project.jsp?appId="+appId+"&appSecret="+appSecret+"&role="+role+"&xmId="+xmId;
    } else {  
    	window.location.href="project.jsp?appId="+appId+"&appSecret="+appSecret+"&role="+role+"&xmId="+xmId;
    }
}

/************************************************************************************************************************/
/** -模块2- **/
function loadProject(){
	appId 		= getQueryString("appId");
	appSecret 	= getQueryString("appSecret");
	role 		= getQueryString("role");
	xmId 		= getQueryString("xmId");
	$.ajax({
		url:"getData.do",
		type:"POST",
		data:{
			_model:"2.1",
			appId: appId,
			appSecret: appSecret,
			xmId: xmId
			},
		dataType:'json',
		success:function(jsonObj){
			if(jsonObj.success){
				jsonObj = jsonObj.obj;
				projectJson = jsonObj;
				
				$("#xmMc").text(jsonObj.xmMc).attr("title",jsonObj.xmMc);
		        $("#xmcz").text(jsonObj.xmCz).attr("title",jsonObj.xmCz);
		        $("#csdq").text(jsonObj.csdq).attr("title",jsonObj.csdq);
		        $("#kgrq").text(jsonObj.kgrq).attr("title",jsonObj.kgrq);
		        $("#yjwcrq").text(jsonObj.yjwcrq).attr("title",jsonObj.yjwcrq);
		        $("#dtxl").text(jsonObj.dtxl).attr("title",jsonObj.dtxl);
		        $("#fzr").text(jsonObj.fzr).attr("title",jsonObj.fzr);
		        $("#zhs").text(jsonObj.zhs).attr("title",jsonObj.zhs);
		    	setTitle(jsonObj.xmMc);
			}else{
				errMeg(jsonObj.msg);
			}
        }
	});
	var rizhiArray = new Array();
 	var rizhiColorArray = new Array();
 	var rizhiColorStrs = ["#fff","#fff600","#ff6600","#00ff42","#00e4ff","#ff0000"];
 	getrizhiQuxianData();
 	var rizhi_task1,rizhi_task2;
    function  getrizhiQuxianData(){
		var infostr = [{"id":"1","jibie":"-","leixing":"2","neirong":"无锡三号线五标山靖区间左线盾构机数据传输正常。","shijian":"11-24 15:40"},
		               {"id":"4","jibie":"-","leixing":"5","neirong":"右线盾构机数据传输正常。","shijian":"11-24 15:40"},
		               {"id":"1","jibie":"-","leixing":"2","neirong":"左线盾构机数据传输正常。","shijian":"11-24 15:40"},
		               {"id":"1","jibie":"-","leixing":"2","neirong":"测试数据…","shijian":"11-24 15:40"}];
// 		var jsonObj = eval('(' + infostr + ')');
		var jsonObj = infostr;
		var len = jsonObj.length;
// 		rizhiArray.length = 0;
// 		rizhiColorArray.length = 0;
// 		var arra = new Array(),arrb= new Array();
		for(i=0;i<len;i++){
			var obj = jsonObj[i];
			//var str = obj.neirong+"  "+obj.shijian;
			var str = obj.neirong;
			rizhiArray.push(str);
			rizhiColorArray.push(obj.leixing);
		}
// 		console.log(rizhiArray)
		rizhi_buildInfoWrite('content1',rizhiArray,0,'',5,false,rizhiColorArray);
	}
    function rizhi_buildInfoWrite(divId, dataSet, flagStop,className,removeId,stop,colorData) {
    	var l = 0;
    	var removeIdvar = removeId;
    	var lensss = dataSet.length;
    	var task = setInterval(function(){
    		if(l == (lensss-1)){
   				clearInterval(task);
    			getrizhiQuxianData();
    		}
    		if($("#"+divId).children().size() > removeId){
    			$("#"+divId).find("div:first").remove();
    		}
    		var colorId = colorData[l] - 1;
    		//console.log("colorId="+colorData);
    		rizhi_xunhuanshuzu(dataSet[l],divId,flagStop,className,rizhiColorStrs[colorId]);
    		l++;
    	},1500);
    }
    function rizhi_xunhuanshuzu(str,id,flagStop,className,color){
    	var key = 0;
    	var	divstr = $("<div class='"+className+"'></div>");
    	$("#"+id).append(divstr);
    	$(divstr).css("color",color);
    	var strs = "";
    	var lens = str.length;
    	var  task1 = setInterval(function(){
    		if(key == (lens-1)){
    			clearInterval(task1);
    		}	
    		$(divstr).text(divstr.text() + str[key]);
    		key++;
    	},100);
    }
//	showData();
	//菜单  ../Table/DP-SMZL.htm','输煤走廊
	var menu=moreButtonAction().init('morebutton',[]);
	menu.addData('管片姿态',"pages/2.8guanpian.html?xmId="+xmId,1450,860);
	menu.addData('人员投入情况',"pages/2.9personInfo.html?xmId="+xmId,1400);
	menu.addData('设备投入情况',"pages/2.10projectDetail.html?xmId="+xmId,1400);
	var menu_safe=moreButtonAction().init('morebutton_safe',[]);
	menu_safe.addData('管片质量',"pages/2.5.1guanpianQuality.html?xmId="+xmId,['975px','780px']);
	menu_safe.addData('施工质量',"pages/2.5.3constructQuality.html?xmId="+xmId,1400);
}

function alertProjectInfo_svg(){
	var j = projectJson;
	$("#top_div").addClass("blur");
	$("#content_div").addClass("blur");
	$("#svgContent").css("-webkit-filter","blur(5px)").css("-moz-filter","blur(5px)").css("-ms-filter","blur(5px)")
	.css("filter","blur(5px)").css("filter","progid:DXImageTransform.Microsoft.Blur(PixelRadius=5, MakeShadow=false)");
	layer.open({
		type: 2,
		title: '',
		content: 'pages/2.1projectInfo.html',
		area: ["400px","530px"],
		shade: 0,
		resize:false,
		fixed :true,
		move:false,
		closeBtn: 0,
		skin: "alertDiv-class",
//		btn:['进入项目','关闭'],
//		yes: function(index, layero){
//			layer.close(index);
//		},
		success: function(layero, index){
			var body = layer.getChildFrame('body', index);
			body.find('#closeBtn').click(function(){
				layer.closeAll();alertMsgDivId=null;
			});
			body.find('#xmMc').text(j.xmMc);
			body.find('#zbjg').text(j.zbjg);
			body.find('#xmcz').text(j.xmCz);
			body.find('#dqxmwcz').text(j.dqxmwcz);
			body.find('#csdq').text(j.csdq);
			body.find('#zts').text(j.zts);
			body.find('#kgrq').text(j.kgrq);
			body.find('#yjwcrq').text(j.yjwcrq);
			body.find('#dtxl').text(j.dtxl);
			body.find('#sgbd').text(j.sgbd);
			body.find('#fzr').text(j.fzr);
			body.find('#zhs').text(j.zhs);
			body.find('#dqjzjhs').text(j.dqjzjhs);
		},
		end: function(index){
			$("#top_div").removeClass("blur");
			$("#content_div").removeClass("blur");
			$("#svgContent").css("-webkit-filter","blur(0px)").css("-moz-filter","blur(0px)").css("-ms-filter","blur(0px)")
			.css("filter","blur(0px)").css("filter","progid:DXImageTransform.Microsoft.Blur(PixelRadius=0, MakeShadow=false)");
		}
	});
}


