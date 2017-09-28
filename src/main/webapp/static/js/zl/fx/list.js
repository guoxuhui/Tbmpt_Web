/**
 * 管片质量上报系统 上报管理模块
 * 
 * 创建时间：2017年04月17日 
 * 
 */

/**
 * 系统模块同路径
 */
var syspath = "zl";
/**
 * 子模块路径
 */
var module = "fx";
/**
 * 模块名称
 */
var moduleName = "管片质量分析";
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
	
	/**
	 *获取工程信息
	 *
	 */
	$.ajax({
		url : path+'/getProDic',
		dataType : 'json',
		success : function(jsonstr) {
			jsonstr.unshift({
				'id' : '',
				'proName' : '-请选择-'
			}); //向json数组开头添加自定义数据  

			$('#search_proId').combobox({
				data : jsonstr,
				valueField : "id",
				textField : "proName",
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				 },onSelect:function(newValue,oldValue){
						$('#search_sectionId').combobox("clear");
						$('#search_sectionId').combobox('reload', path+"/getProSectionDic?proId="+newValue.id);
				 }
			});

			$("#add_proId").combobox({
				data : jsonstr,
				valueField : "id",
				textField : "proName",
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				},
				 onSelect:function(newValue,oldValue){
			        	$('#add_sectionId').combobox("clear");
			        	$('#add_sectionId').combobox('reload', path+"/getProSectionDic?proId="+newValue.id);
			      }
			});

			$("#edit_proId").combobox({
				data : jsonstr,
				valueField : "id",
				textField : "proName",
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				},
				 onSelect:function(newValue,oldValue){
			        	$('#edit_sectionId').combobox("clear");
			        	$('#edit_sectionId').combobox('reload', path+"/getProSectionDic?proId="+newValue.id);
			      }
			});
			
			$("#show_proId").combobox({
				data : jsonstr,
				valueField : "id",
				textField : "proName",
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				}, 
				 onSelect:function(newValue,oldValue){
			        	$('#show_sectionId').combobox("clear");
			        	$('#show_sectionId').combobox('reload', path+"/getProSectionDic?proId="+newValue.id);
			      }
			});

		}
	});
	
	/**
	 *获取工程区间信息
	 *
	 */
	$.ajax({
		url : path+'/getProSectionDic',
		dataType : 'json',
		success : function(jsonstr) {
			$("#add_sectionId").combobox({
				data : jsonstr,
				valueField : "id",
				textField : "sectionName",
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				},
				 onSelect:function(newValue,oldValue){
			        	$('#add_lineId').combobox("clear");
			        	$('#add_lineId').combobox('reload', path+"/getProLineDic?sectionId="+newValue.id);
                     	$("#add_cycleNo").val("");
			      }
			});

            $("#search_sectionId").combobox({
                data : jsonstr,
                valueField : "id",
                textField : "sectionName",
                onLoadSuccess : function() {
                    var val = $(this).combobox('getData');
                    for (var item in val[0]) {
                        if (item == 'id') {
                            $(this).combobox('select', val[0][item]);
                        }
                    }
                },
                onSelect:function(newValue,oldValue){
                    $('#search_lineId').combobox("clear");
                    $('#search_lineId').combobox('reload', path+"/getProLineDic?sectionId="+newValue.id);
                }
            });

			$("#edit_sectionId").combobox({
				data : jsonstr,
				valueField : "id",
				textField : "sectionName",
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				},
				 onSelect:function(newValue,oldValue){
			        	$('#edit_lineId').combobox("clear");
			        	$('#edit_lineId').combobox('reload', path+"/getProLineDic?sectionId="+newValue.id);
                     	$("#edit_cycleNo").val("");

			      }
			});
			
			$("#show_sectionId").combobox({
				data : jsonstr,
				valueField : "id",
				textField : "sectionName",
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				},
				 onSelect:function(newValue,oldValue){
			        	$('#show_lineId').combobox("clear");
			        	$('#show_lineId').combobox('reload', path+"/getProLineDic?sectionId="+newValue.id);
					 	$("#show_cycleNo").val("");
			      }
			});

		}
	});

	/**
	 *获取工程线路信息
	 *
	 */
	$.ajax({
		url : path+'/getProLineDic',
		dataType : 'json',
		success : function(jsonstr) {
			$("#add_lineId").combobox({
				data : jsonstr,
				valueField : "id",
				textField : "lineName",
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				},
                onSelect:function(newValue,oldValue){
                    getHHByXlId(newValue.id);
                }
			});

            $("#search_lineId").combobox({
                data : jsonstr,
                valueField : "id",
                textField : "lineName",
                onLoadSuccess : function() {
                    var val = $(this).combobox('getData');
                    for (var item in val[0]) {
                        if (item == 'id') {
                            $(this).combobox('select', val[0][item]);
                        }
                    }
                }
            });

			$("#edit_lineId").combobox({
				data : jsonstr,
				valueField : "id",
				textField : "lineName",
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				},
                onSelect:function(newValue,oldValue){
                    getHHByXlId(newValue.id);
                }
			});
			 
			$("#show_lineId").combobox({
				data : jsonstr,
				valueField : "id",
				textField : "lineName",
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				},
                onSelect:function(newValue,oldValue){
                    getHHByXlId(newValue.id);
                }
			});

		}
	});

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
    $('#selectDay').combobox({
        onChange:function(newValue,oldValue){
			$("#selectZhou").combobox("clear");
            $("#selectMonth").combobox("clear");
            $(this).combobox('setValue', newValue);
        }
    });

    $('#selectZhou').combobox({
        onChange:function(newValue,oldValue){
            $("#selectDay").combobox("clear");
            $("#selectMonth").combobox("clear");
            $(this).combobox('setValue', newValue);
        }
    });

    $('#selectMonth').combobox({
        onChange:function(newValue,oldValue){
            $("#selectDay").combobox("clear");
            $("#selectZhou").combobox("clear");
            $(this).combobox('setValue', newValue);
        }
    });

    $('#picType').combobox({
        onChange:function(newValue,oldValue){
        	if(newValue!=""){
                searchFun();
            }
        }
    });
    searchFun();//初始化
}

/**
 * 表单查询
 */
function searchFun() {
	//项目名称
    var search_proId = $('#search_proId').combobox('getValue');
    //区间名称
    var search_sectionId = $('#search_sectionId').combobox('getValue');
    //线路名称
    var search_lineId = $('#search_lineId').combobox('getValue');
    //天
    var selectDay = $('#selectDay').combobox('getValue');
    //星期
    var selectZhou = $('#selectZhou').combobox('getValue');
    //月
    var selectMonth = $('#selectMonth').combobox('getValue');
	//图表类型
    var picType = $('#picType').combobox('getValue');
    progressLoad();
    $.ajax({
        url : path+"/getChartData",
		data:{"proName":search_proId,"section":search_sectionId,"line":search_lineId,"day":selectDay,"zhou":selectZhou,"month":selectMonth,"picType":picType},
        dataType : 'json',
        success : function(json) {
            progressClose();
			var charts = json.charts;
			var problemType = eval(json.problemType);
			if(picType=="" || picType=="0" || picType==undefined){
                charsZhu(charts,problemType,selectDay,selectZhou,selectMonth);
            }else{
                charsZhe(charts,problemType,selectDay,selectZhou,selectMonth);
            }
        }
    });
}

//绘制折线图
function charsZhe(charts,problemType,selectDay,selectZhou,selectMonth){
	$("#chars_1").hide();
	$("#chars_2").show();
    var seriesJSON = [];
    for(var i=0;i<problemType.length;i++){
        var seriesJSON_i = {
        	name: problemType[i].key,
            type: 'line',
            data: [320, 302, 301, 334, 390, 330, 320],
            markPoint: {
                data: [
                    {type: 'max', name: '最多'},
                    {type: 'min', name: '最少'}
                ]
            }
        };
        var seriesJSON_i_data = [];
        var chartsJProArray = charts[i].proArray;
        for(var j=0;j<chartsJProArray.length;j++){
			seriesJSON_i_data.push(chartsJProArray[j].infos.length);
        }
        seriesJSON_i.data = seriesJSON_i_data;
        seriesJSON.push(seriesJSON_i);
    }
    // 基于准备好的dom，初始化echarts实例
    var imyChart_2 = echarts.init(document.getElementById('chars_2'));
    var option = {
        title: {
            text: '管片分析折线图'
        },
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true 
        },
        legend: {
            data: getCharsTitle(problemType)
        },
        toolbox: {
            show: true,
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                dataView: {readOnly: false},
                magicType: {type: ['line', 'bar']},
                restore: {},
                saveAsImage: {}
            }
        },
        xAxis:  {
            type: 'category',
            boundaryGap: false,
            data: getCharsCloum(selectDay,selectZhou,selectMonth)
        },
        yAxis: {
            type: 'value',
	        //min:0,
	        //max:20,
            minInterval:1,
            axisLabel: {
                formatter: '{value} 个'
            }
        },
        series: seriesJSON
    };
    // 使用刚指定的配置项和数据显示图表。
    imyChart_2.setOption(option);
    imyChart_2.on('click', function (params) {
		var searchProId = $('#search_proId').combobox('getValue');
		var searchSectionId = $('#search_sectionId').combobox('getValue');
		var searchLineId = $('#search_lineId').combobox('getValue');
		var selectDay = $('#selectDay').combobox('getValue');
		var selectZhou = $('#selectZhou').combobox('getValue');
		var selectMonth = $('#selectMonth').combobox('getValue');
		var url = basePath+'/zl/up/indexPro?proName=' + encodeURIComponent(searchProId)+
    		'&section=' + encodeURIComponent(searchSectionId)+
    		'&line=' + encodeURIComponent(searchLineId)+
    		'&rq='+encodeURIComponent(params.name);
    
		parent.addTab({
        title : params.name,
        url : url
		});
    });
}

var problemTypeColorS  = ['#c23531','#2f4554','#61a0a8','#d48265','#91c7c2'];
//绘制柱状图
function charsZhu(charts,problemType,selectDay,selectZhou,selectMonth){
    $("#chars_1").show();
    $("#chars_2").hide();
    var seriesJSON = [];
    for(var i=0;i<problemType.length;i++){
        var seriesJSON_i = {name: problemType[i].key,
            type: 'bar',
            stack: '总量',
            label: {
                normal: {
                    show: true,
                    position: 'inside'
                }
            },
            data: [320, 302, 301, 334, 390, 330, 320]
        };
        var seriesJSON_i_data = [];
        var chartsJProArray = charts[i].proArray;
        for(var j=0;j<chartsJProArray.length;j++){
            if(chartsJProArray[j].infos.length>0){
                seriesJSON_i_data.push(chartsJProArray[j].infos.length);
            }else{
                seriesJSON_i_data.push('');
            }
        }
        seriesJSON_i.data = seriesJSON_i_data;
        var itemStyle = {
        		normal:{
        			color:problemTypeColorS[i]
        		}
        }
        seriesJSON_i.itemStyle = itemStyle;
        seriesJSON.push(seriesJSON_i);
    }
    // 基于准备好的dom，初始化echarts实例
    var myChart_1 = echarts.init(document.getElementById('chars_1'));
    myChart_1.title = '管片分析柱状图';
    var option = {
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        legend: {
            data: getCharsTitle(problemType)
        },
        grid: {
            left: '10%',
            right: '10%',
            bottom: '10%',
            containLabel: true
        },
        xAxis:  {
            type: 'category',
            data: getCharsCloum(selectDay,selectZhou,selectMonth)
        },
        yAxis: {
        	minInterval:2,
            axisLabel: {
                formatter: '{value} 个'
            },
            min:0,
            max:20
        },
        series: seriesJSON
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart_1.setOption(option);
    myChart_1.on('click', function (params) {
		var searchProId = $('#search_proId').combobox('getValue');
		var searchSectionId = $('#search_sectionId').combobox('getValue');
		var searchLineId = $('#search_lineId').combobox('getValue');
		var selectDay = $('#selectDay').combobox('getValue');
		var selectZhou = $('#selectZhou').combobox('getValue');
		var selectMonth = $('#selectMonth').combobox('getValue');
		var url = basePath+'/zl/up/indexPro?proName=' + encodeURIComponent(searchProId)+
    		'&section=' + encodeURIComponent(searchSectionId)+
    		'&line=' + encodeURIComponent(searchLineId)+
    		'&rq='+encodeURIComponent(params.name);
    
		parent.addTab({
        title : params.name,
        url : url
		});
    });
}

function getCharsTitle(jsonType){
    var chartsTitle = [];
    for(var i=0;i<jsonType.length;i++){
        chartsTitle.push(jsonType[i].key);
    }
    return chartsTitle;
}

function getCharsCloum(day,zhou,month){
	var chartsClom = ['近1天','近2天','近3天','近4天','近5天','近6天','近7天'];
	if(day!=""){
        chartsClom = [];
		for(var i=0;i<parseInt(day);i++){
            chartsClom.push("近"+(i+1)+"天");
		}
	}
	if(zhou!=""){
        chartsClom = [];
        for(var i=0;i<parseInt(zhou);i++){
            chartsClom.push("近"+(i+1)+"周");
        }
	}
    if(month!=""){
        chartsClom = [];
        for(var i=0;i<parseInt(month);i++){
            chartsClom.push("近"+(i+1)+"月");
        }
    }
    return chartsClom;
}

/**
 * 表单重置
 */
function cleanFun() {
    $('#searchForm').form('clear');
    searchFun();
}