
/** 系统模块同路径 */
var syspath = "dmcjjc";

/** 子模块路径 */
var module = "zdcjddb";

/** 菜单名称 */
var entityCnName = "对比分析";

/** 业务数据访问全路径 */
var path = basePath +"/"+ syspath +"/"+ module;

var myChart1 = null;
$(function() {
	myChart1 = echarts.init(document.getElementById('main1'));
	$('#gcbh')
			.combobox(
					{
						url : basePath + '/project/info/getProjects',
						valueField : "id",
						textField : "proName",
						onChange : function() {
							$('#qjbh')
									.combobox(
											{
												url : basePath
														+ '/project/line/getProSectionDic?proId='
														+ $('#gcbh').combobox(
																'getValue'),
												valueField : "id",
												textField : "sectionName"
											});
						}
					});
});
// 分别查询最近七天最大上浮的点与最大下沉的点
// 组装数据：
// 1、历时七天的日期；格式：年月日,字符型需加单引号；例如：['20170101','20170102','20170103','20170104','20170105','20170106']
// 2、对应历史七天最大上浮的点编号与最大下沉的点编号；例如：['DB001','DB002']
// 3、对应历史七天最大上浮的点的每一天的最大上浮值；例如：[11, 11, 15, 13, 12, 13, 10,1,1,1,1,1]
// 4、对应历史七天最大下沉的点的每一天的最大下沉值；例如：[1, -2, 2, 5, 3, 2, 0,-1,-1,-1,-1,-1]

function loadChart() {
	var xmId = $('#gcbh').combobox("getValue");
	var qjId = $('#qjbh').combobox("getValue");
	
	var sDate = $('#beginTime').textbox("getValue");
	var eDate = $('#endTime').textbox("getValue");
	
	if(xmId == null || xmId == ""){
		$.messager.alert('提示', '请输入工程编号', 'info');
		return;
	}
	if(sDate == null || eDate == null
		|| sDate == "" || eDate == ""){
		$.messager.alert('提示', '请输入统计时间', 'info');
		return;
	}
	
	myChart1.showLoading();
	$.ajax({
		type : "post",
		async : true, // 异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
		url : path+"/getData", // 请求发送到TestServlet处
		data : {
			xmId : xmId,
			qjId : qjId,
			sDate : sDate,
			eDate : eDate
		},
		dataType : "json", // 返回数据形式为json
		success : function(result) {
			// 请求成功时执行该函数内容，result即为服务器返回的json对象
			if (result) {
				setData(result.obj);
			}
		}
	});
}

function setData(result) {
	myChart1.hideLoading();
	var d1 = result[0];
	var d2 = result[1];
	var d3 = result[2];
	var d4 = result[3];
	var option1 = {
		title : {
			text : '最大沉降点对比',
			subtext : '地面沉降监测系统--沉降分析'
		},
		tooltip : {
			trigger : 'axis'
		},
		legend : {
			data : d2
		},
		toolbox : {
			show : true,
			right : 15,
			feature : {
				dataView : {
					title : '查看详细',
					lang : [ '查看详细', '关闭' ],
					readOnly : true
				},
				magicType : {
					type : [ 'line', 'bar' ]
				},
				restore : {
					title : '刷新'
				},
				saveAsImage : {
					title : '保存图片'
				}
			}
		},
		xAxis : {

			type : 'category',
			data : d1
		// 历史七天格式：****-**-**
		},
		yAxis : {
			type : 'value',
			axisLabel : {
				formatter : '{value} mm'
			}
		},
		dataZoom : [ {
			type : 'slider',
			yAxisIndex : 0,
			filterMode : 'empty'
		}, {
			type : 'inside',
			yAxisIndex : 0,
			filterMode : 'empty'
		} ],
		series : [ {
			name : d2[0],
			type : 'line',
			data : d3, // 对应历史七天该监测点的每一天的最大上浮值
			smooth : true,
			markPoint : {
				data : [ {
					type : 'max',
					name : '最大值'
				} ]
			},
			markLine : {
				data : [ {
					type : 'average',
					name : '平均值'
				} ]
			}
		}, {
			name : d2[1],
			type : 'line',
			data : d4, // 对应对历史七天该监测点的每一天的最大下沉值
			smooth : true,
			markPoint : {
				data : [ {
					type : 'min',
					name : '最小值'
				} ]
			},

			markLine : {
				data : [ {
					type : 'average',
					name : '平均值'
				}, [ {
					symbol : 'none',
					x : '90%',
					yAxis : 'max'
				}, {
					symbol : 'circle',
					label : {
						normal : {
							position : 'start',
							formatter : '最小下沉'
						}
					},
					type : 'max',
					name : '最小'
				} ] ]
			}
		} ]
	};
	myChart1.setOption(option1);
}