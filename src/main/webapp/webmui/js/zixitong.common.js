/**
 * 
 */

var flag = 0;
window.setInterval(function(){
	if(flag == 0){
		$('#dataConnect').text("【数据连接中...】");
	}else if(flag == 1){
		$('#dataConnect').text("【数据连接中....】");
	}else if(flag == 2){
		$('#dataConnect').text("【数据连接中.....】");
		timeDataConnection = 5000;
	}else if(flag == 3){
		$('#dataConnect').text("【数据正常】");
		flag = 0;
		timeDataConnection = 1000;
	}
	flag++;
}, 2000);


var t = null;
t = setTimeout(time, 1000);// 开始执行
function time() {
	clearTimeout(t);// 清除定时器
	dt = new Date();
	var h = dt.getHours();
	var m = dt.getMinutes();
	var s = dt.getSeconds();
	var year = dt.getFullYear();//
	var month = dt.getMonth() + 1;
	;
	var date = dt.getDate();// 日数
	document.getElementById("time").innerHTML = year + "-" + month + "-" + date
			+ " " + h + ":" + m + ":" + s;
	t = setTimeout(time, 1000); // 设定定时器，循环执行
}

var startx,starty,endx,endy;

$(window).load(function(){
	parent.alertMsg.loading();
	$.ajax({
		url : "../../webmui/getProjectLineData",
		type : "POST",
		data : {
			_model : "2.1",
			appId : appId,
			appSecret : appSecret,
			xmId : xiangmuId,
			xlId : xianluId,
			t:Math.random()
		},
		dataType : 'json',
		success : function(jsonObj) {
			if (jsonObj && jsonObj.success) {
				jsonObj = jsonObj.obj;
				initData(jsonObj);
			} else {
				errMeg(jsonObj.msg);
			}
		}
	});
	
	$('#goBack').click(function(){
		window.history.go(-1);
	});
	
});
function initData(data) {
	$("#xmMc").text( data.xmMc);
	$("#dgjMc").text(data.dgjMc);
	$("#kgrq").text(data.kgrq);
	$("#fzr").text(data.fzr);
	for(i=0;i<data.xlList.length;i++){
		var xianlObj = data.xlList[i];
		if(xianlObj.xlId == xianluId){
			$('#qjxl').text(xianlObj.xlMc);
		}
	}
	//$("#baibsj").text(data.baibsj);
	//$("#yebsj").text(data.yebsj);
	//$("#zydz").text(data.zydz);
	//$("#qjzc").text(data.qjzc);
	
	initJQ();
}

/**
 * 机器界面
 */
var jueStatus;
function initJQ(){
	$.ajax({
		url : "../../webmui/getJqjmData",
		type : "POST",
		data : {
			_model : "3.1",
			appId : appId,
			appSecret : appSecret,
			xmId : xiangmuId,
			dgjId : dgjId,
			tbmId : dgjId,
			xlId : xlId
		},
		dataType : 'json',
		success : function(jsonObj) {
			if (jsonObj.success) {
				jsonObj = jsonObj.obj;
				for (i = 0; i < jsonObj.length; i++) {
					var objectData = jsonObj[i];
					//console.log(objectData);
					if (objectData.dwid == 'TY_DXXT_0001') {
						$('#huanhao').text(objectData.tagvalue);
					}else if (objectData.dwid == 'TY_DXXT_0002') {
						$('#' + objectData.dwid).text(objectData.tagvalue);
					}else 
					
					
					/*-------------   掘进状态控制   开始             ---------------*/
					if (objectData.dwid == 'TY_ZGZT_0001') {
						jueStatus = objectData.tagvalue;
						if (objectData.tagvalue == 'true') {
							$('#status_j').addClass('green_color');
							$('#status_p').removeClass('green_color');
							$('#status_t').removeClass('red_color');
						}
					}else if (objectData.dwid == 'TY_ZGZT_0002') {
						if (objectData.tagvalue == 'true' && jueStatus == 'false') {
							$('#status_j').removeClass('green_color');
							$('#status_p').addClass('green_color');
							$('#status_t').removeClass('red_color');
						}else if(objectData.tagvalue == 'false' && jueStatus == 'false'){
							$('#status_j').removeClass('green_color');
							$('#status_p').removeClass('green_color');
							$('#status_t').addClass('red_color');
						}
						jueStatus = '';
					}
					else {
						var vale = isNaN(objectData.tagvalue)?"0.0":parseFloat(objectData.tagvalue).toFixed(1);
						var values = (vale == 0 ? "0.0" : vale);
						if ($('#' + objectData.dwid).length > 0) {
							$('#' + objectData.dwid).text(values);
						}
					}
					/*-------------   掘进状态控制   结束             ---------------*/
					
				}
				
				
				$('#TY_DXXT_0001').text($('#huanhao').text());
				
				
				
				layer.closeAll();
				initPlane();
			} else {
				errMeg(jsonObj.msg);
			}
			
		}
	});
}

function initPlane(){
	var daoQS = $('#TY_DXXT_0007').text();
	var daoQC = $('#TY_DXXT_0005').text();
	var daoHS = $('#TY_DXXT_0008').text();
	var daoHC = $('#TY_DXXT_0006').text();//导向系统的前后垂直、水平变量
	console.log(daoQS+' - '+ daoQC +' -- '+daoHS+' --- '+daoHC);
	drawPlaneZi(parseFloat(daoQS) ,parseFloat(daoQC) , parseFloat(daoHS),parseFloat(daoHC));
}
