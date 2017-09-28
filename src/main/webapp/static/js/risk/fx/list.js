/**
 * 风险上报系统 上报管理模块
 * 
 * 创建时间：2017年04月17日 
 * 
 */

/**
 * 系统模块同路径
 */
var syspath = "risk";
/**
 * 子模块路径
 */
var module = "fx";
/**
 * 模块名称
 */
var moduleName = "风险统计分析";
/**
 * 名称字段
 */
var nameField = "proName";
/**
 * 业务数据访问全路径
 */
var path = basePath +"/"+ syspath +"/"+ module;
/**
 * 列表对象
 */
var dataGrid;
$(function() {
	
	
});

/**
 * 刷新
 */
function reloadFun(){
    searchFun();
}
/**
 * 取消已选
 */
function clearSelections(){
	dataGrid.datagrid('clearSelections');
}

window.onload = function(){
    searchFun();//初始化
}

/**
 * 表单查询
 */
function searchFun() {
	//图表类型
    var type = $('#type').combobox('getValue');
    progressLoad();
    $.ajax({
        url : path+"/getChartData",
		data:$.serializeObject($('#searchForm')),
        dataType : 'json',
        success : function(json) {
            progressClose();
			var jsonObj = json.obj;
			var type = json.obj.type;
			if(type=="bar"){
                charsZhu(jsonObj);
            }else{
                charsZhe(jsonObj);
            }
        }
    });
}

//绘制折线图
function charsZhe(jsonObj){
	$("#chars_1").hide();
	$("#chars_2").show();
	for(var i=0;i<jsonObj.series.length;i++){
		jsonObj.series[i].label.normal['formatter'] = labelFun;
	};
	
    // 基于准备好的dom，初始化echarts实例
    var imyChart_2 = echarts.init(document.getElementById('chars_2'));
    var option = {
        title: {
            text: '风险上报分析折线图',
            padding: [
                0,  // 上
                0, // 右
                0,  // 下
                0, // 左
            ]
            	
        },
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
        	top:'35px',
            left: '2%',
            right: '2%',
            bottom: '35px',
            containLabel: true 
        },
        legend: {
            data:jsonObj.legend
        },
        xAxis:  {
        	
            type: 'category',
            interval:0,
            data: jsonObj.xaxis
        },
        yAxis: {
            type: 'value',
            min:0,
	        max:20,
            minInterval:1 
        },
        series: jsonObj.series
    };
    // 使用刚指定的配置项和数据显示图表。
    imyChart_2.setOption(option);
    imyChart_2.on('click', function (params) {
		var startUpTime = $('#sdata').datebox('getValue');
		var endUpTime = $('#edata').datebox('getValue');
		var url = basePath+'/risk/up/indexPro?proName=' + encodeURIComponent(params.name)+
    		'&startUpTime=' + encodeURIComponent(startUpTime)+
    		'&endUpTime=' + encodeURIComponent(endUpTime);
    
		parent.addTab({
        title : params.name,
        url : url
		});
    });
}

//绘制柱状图
function charsZhu(jsonObj){
    $("#chars_1").show();
    $("#chars_2").hide();
    
    for(var i=0;i<jsonObj.series.length;i++){
    		jsonObj.series[i].label.normal['formatter'] = labelFun;
    };
    
    // 基于准备好的dom，初始化echarts实例
    var myChart_1 = echarts.init(document.getElementById('chars_1'));
    var option = {
        title: {
            text: '风险上报分析柱状图',
            padding: [
                0,  // 上
                0, // 右
                0,  // 下
                0, // 左
            ]
        },
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        dataZoom: {
        	bottom:'0px',    
        	show: true,
                realtime: true,
                start:0,
                end: 40
        },
        grid: {
            top:'35px',
        	left: '2%',
            right: '2%',
            bottom: '35px',
            containLabel: true 
        },
        legend: {
            data:jsonObj.legend
        },
        xAxis:  {
            type: 'category',
            interval:0,
            data: jsonObj.xaxis,
            
        },
        yAxis: {
        	type: 'value',
        	minInterval:1 ,
            min:0,
            max:20
        	
        },
        series: jsonObj.series
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart_1.setOption(option);
    myChart_1.on('click', function (params) {
    		var startUpTime = $('#sdata').datebox('getValue');
    		var endUpTime = $('#edata').datebox('getValue');
        var url = basePath+'/risk/up/indexPro?proName=' + encodeURIComponent(params.name)+
        		'&startUpTime=' + encodeURIComponent(startUpTime)+
        		'&endUpTime=' + encodeURIComponent(endUpTime);
        
        parent.addTab({
            title : params.name,
            url : url
        });
    });
}

var labelFun = function(obj){
    if(obj.value === 0){
        return "";
    }else{
        return obj.value;
    }
}

/**
 * 表单重置
 */
function cleanFun() {
    $('#searchForm').form('clear');
    searchFun();
}