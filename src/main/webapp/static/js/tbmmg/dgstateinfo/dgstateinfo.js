/** 系统模块同路径 */
var syspath = "tbmmg";

/** 子模块路径 */
var module = "dgstateinfo";

/** 菜单名称 */
var entityCnName = "盾构机故障停机对比分析";

/** 业务数据访问全路径 */
var path = basePath + "/" + syspath + "/" + module;

var myChart1 = null;
$(function() {

	var db = $('#riqi');
	db.datebox({
		onShowPanel : function() {// 显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
			span.trigger('click'); // 触发click事件弹出月份层
			// fix 1.3.x不选择日期点击其他地方隐藏在弹出日期框显示日期面板
			if (p.find('div.calendar-menu').is(':hidden'))
				p.find('div.calendar-menu').show();
			if (!tds)
				setTimeout(function() {// 延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
					tds = p.find('div.calendar-menu-month-inner td');
					tds.click(function(e) {
						e.stopPropagation(); // 禁止冒泡执行easyui给月份绑定的事件
						var year = /\d{4}/.exec(span.html())[0]// 得到年份
						, month = parseInt($(this).attr('abbr'), 10); // 月份，这里不需要+1
						db.datebox('hidePanel')// 隐藏日期对象
						.datebox('setValue', year + '-' + month); // 设置日期的值
					});
				}, 0);
			yearIpt.unbind();// 解绑年份输入框中任何事件
		},
		parser : function(s) {
			if (!s)
				return new Date();
			var arr = s.split('-');
			return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
		},

		formatter : function(d) {
			var mth = (d.getMonth() + 1) + "";
			if (mth.length == 1) {
				mth = "0" + mth;
			}
			return d.getFullYear() + '-' + (mth);/* getMonth返回的是0开始的，忘记了。。已修正 */

		}
	});
	var p = db.datebox('panel'), // 日期选择对象
	tds = false, // 日期选择对象中月份
	aToday = p.find('a.datebox-current'), yearIpt = p
			.find('input.calendar-menu-year'), // 年份输入框
	// 显示月份层的触发控件
	span = aToday.length ? p.find('div.calendar-title span') : // 1.3.x版本
	p.find('span.calendar-text'); // 1.4.x版本
	if (aToday.length) {// 1.3.x版本，取消Today按钮的click事件，重新绑定新事件设置日期框为今天，防止弹出日期选择面板

		aToday.unbind('click').click(
				function() {
					var now = new Date();
					var mth = (now.getMonth() + 1) + "";
					if (mth.length == 1) {
						mth = "0" + mth;
					}
					db.datebox('hidePanel').datebox('setValue',
							now.getFullYear() + '-' + (mth));
				});
	}
	
	
$("#asdasdasd").click(function(){
	loadChart();
});

var convertData = function(data,geoCoordMap) {
	var res = [];
	for (var i = 0; i < data.length; i++) {
		var geoCoord = geoCoordMap[data[i].wz];
		if (geoCoord) {
			res.push({
				name : data[i].name,
				value : geoCoord.concat(data[i].value).concat(
						data[i].wz)
			});
		}
	}
	return res;
};

function loadChart() {
	var riqi = $('#riqi').textbox("getValue");
	myChart1 = echarts.init(document.getElementById('main1'));
	myChart1.showLoading();
	$.ajax({
		type : "post",
		async : true, // 异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
		url : path + "/getData", // 请求发送到TestServlet处
		data : {
			riqi : riqi
		},
		dataType : "json", // 返回数据形式为json
		success : function(result) {
			
			// 请求成功时执行该函数内容，result即为服务器返回的json对象
			if (result.success) {
				myChart1.hideLoading();
				var geoCoordMap = result.obj[0];
				var data = result.obj[1];
				var cdd1 = convertData(data,geoCoordMap);
				var cdd2 = convertData(data.sort(function(a, b) {
					return b.value.split('/')[0]
					- a.value.split('/')[0];
					}),geoCoordMap);
				var convertedData = [cdd1,cdd2];
				var option = {
					backgroundColor : '#404a59',
					animation : true,
					animationDuration : 1000,
					animationEasing : 'cubicInOut',
					animationDurationUpdate : 1000,
					animationEasingUpdate : 'cubicInOut',
					title : [ {
						text : '全国盾构分布及停机分析',
						subtext : 'Data from 中铁一局集团城轨公司',
						sublink : 'http://118.178.93.159',
						left : 'left',
						textStyle : {
							color : '#fff'
						}
					}, {
						id : 'statistic',
						right : 120,
						top : 40,
						width : 100,
						textStyle : {
							color : '#fff',
							fontSize : 16
						}
					} ],
					toolbox : {
						iconStyle : {
							normal : {
								borderColor : '#fff'
							},
							emphasis : {
								borderColor : '#b1e4ff'
							}
						}
					},
					brush : {
						outOfBrush : {
							color : '#abc'
						},
						brushStyle : {
							borderWidth : 2,
							color : 'rgba(0,0,0,0.2)',
							borderColor : 'rgba(0,0,0,0.5)',
						},
						seriesIndex : [ 0, 1 ],
						throttleType : 'debounce',
						throttleDelay : 300,
						geoIndex : 0
					},
					geo : {
						map : 'china',
						left : '10',
						right : '30%',
						center : [ 113, 36 ],
						zoom : 0.7,
						label : {
							emphasis : {
								show : true,
								textStyle : {
									color : '#fff'
								}
							}
						},
						roam : true,
						itemStyle : {
							normal : {
								areaColor : '#323c48',
								borderColor : '#ffffdf'
							},
							emphasis : {
								areaColor : '#2a333d'
							}
						}
					},
					tooltip : {
						trigger : 'item'
					},
					grid : {
						right : 40,
						top : 100,
						bottom : 50,
						width : '30%'
					},
					xAxis : {
						type : 'value',
						scale : true,
						position : 'top',
						boundaryGap : false,
						splitLine : {
							show : false
						},
						axisLine : {
							show : false
						},
						axisTick : {
							show : false
						},
						axisLabel : {
							margin : 2,
							textStyle : {
								color : '#aaa'
							}
						},
					},
					yAxis : {
						type : 'category',
						name : '停机排名',
						nameGap : 16,
						axisLine : {
							show : false,
							lineStyle : {
								color : '#ddd'
							}
						},
						axisTick : {
							show : false,
							lineStyle : {
								color : '#ddd'
							}
						},
						axisLabel : {
							interval : 0,
							textStyle : {
								color : '#ddd'
							}
						},
						data : []
					},
					series : [ {
						name : '盾构机无故障',
						type : 'effectScatter',
						coordinateSystem : 'geo',
						data : convertedData[0],
						symbolSize : function(val) {
							if (parseFloat(val[2].split('/')[0]) == 0)
								return 10;
						},
						showEffectOn : 'emphasis',
						rippleEffect : {
							brushType : 'stroke'
						},
						hoverAnimation : true,
						itemStyle : {
							normal : {
								color : '#7CFC00',
								shadowBlur : 0
							}
						},
						zlevel : 1
					}, {
						name : '曾故障停机',
						type : 'effectScatter',
						coordinateSystem : 'geo',
						data : convertedData[1],
						symbolSize : function(val) {
							if (parseFloat(val[2].split('/')[0]) > 0)
								return 15;
						},
						showEffectOn : 'render',
						rippleEffect : {
							brushType : 'stroke'
						},
						hoverAnimation : true,

						itemStyle : {
							normal : {
								color : '#EC870E',
								shadowBlur : 0
							}
						},
						zlevel : 1
					}, {
						id : 'bar',
						zlevel : 2,
						type : 'bar',
						symbol : 'none',
						itemStyle : {
							normal : {
								color : '#ddb926'
							}
						},
						data : []
					} ]
				};

				myChart1.setOption(option);
				myChart1.on('brushselected', renderBrushed);
				
				setTimeout(function() {
					myChart1.dispatchAction({
						type : 'brush',
						areas : [ {
							geoIndex : 0,
							brushType : 'polygon',// 多边形
							coordRange : [ [ 100, 42 ], [ 125, 42 ],
									[ 125, 30 ], [ 100, 30 ] ]
						} ]
					});
				}, 1000);

				function renderBrushed(params) {
					var mainSeries = params.batch[0].selected[0];

					var selectedItems = [];
					var categoryData = [];
					var djtData_tj = [];
					var djtData_gztj = [];
					var maxBar = 10;
					var sum = 0;
					var count = 0;
					console.info(mainSeries);
					for (var i = 0; i < mainSeries.dataIndex.length; i++) {
						var rawIndex = mainSeries.dataIndex[i];
						
						console.info(i);
						console.info(rawIndex);
						
						var dataItem = convertedData[0][rawIndex];
						var pmValue = dataItem.value[2].split('/')[0];

						sum += parseFloat(pmValue);
						if (pmValue != '0')
							count++;

						selectedItems.push(dataItem);
					}

					selectedItems.sort(function(a, b) {
						return a.value[2].split('/')[0]
								- b.value[2].split('/')[0];
					});

					for (var i = 0; i < selectedItems.length; i++) {
						if (selectedItems[i].value[2].split('/')[0] != "0") {
							categoryData.push(selectedItems[i].name);
							djtData_gztj.push(selectedItems[i].value[2]
									.split('/')[0]);
							djtData_tj.push(selectedItems[i].value[2]
									.split('/')[1]);
						}
					}
					this.setOption({
						yAxis : {
							data : categoryData
						},
						xAxis : {
							axisLabel : {
								show : !!count
							}
						},
						title : {
							id : 'statistic',
							right : 20,
							text : count ? '分析月份 ：' + $('#riqi').textbox("getValue") + '； 区域内共 ' + count
									+ ' 台故障，平均故障停机 : '
									+ Math.round((sum / count) * 24) + ' 小时'
									: '',
							textStyle : {
								fontSize : 12,
								fontStyle : 'oblique',
								fontWeight : 'normal',
								color : '#EC870E'
							}
						},
						series : {
							id : 'bar',
							data : djtData_gztj
						}
					});
				}
			}
		}
	});
}

});

// 接口名称：对分布各城市中各盾构机故障停机对比分析

// 参数：年月；格式：yyyy-mm
// 对应数据库表：DGSTATE（盾构设备状态统计表）
// <对应sql及组装数据>:
// 1、根据统计月份，获取各盾构机所在位置、经纬度
// select t.szwz,t.zb from DGSTATE t where t.riqi='2017-03' group by t.szwz,t.zb
// 组装数据结果：
// 各盾构机所在位置、经纬度；格式：{"盾构机位置名称":[经度,纬度]}如：
// {
// "成都3号线4标":[104.080381,30.706539],
// "南京宁溧线TA08标":[118.753877,32.02318],
// "广州21号线20标":[113.773961,23.28116]
// }
// 2、根据统计月份，获取各盾构机详细信息
// select t.szwz,t.dgj,t.y_stop_days,t.zb,t.y_gzstop_days from DGSTATE t where
// t.riqi='2017-03'
// 组装数据结果：
// 各盾构机运行状态详细信息；格式：[name:盾构机名称,value:故障停机/总停机时间,wz:盾构机位置名称,zb:[经度,纬度]],例如：
// [
// {name: "辽宁三三66号机", value: "0/25",wz:"武汉8号线2标2分部",zb:[114.321546,30.455756]},
// {name: "辽宁三三67号机", value: "0/28",wz:"武汉8号线2标2分部",zb:[114.321546,30.455756]},
// {name: "小松TM641PMM-6", value:
// "0/1",wz:"杭州地铁2号线20标",zb:[120.158724,30.316057]},
// {name: "石川岛SS30600", value: "0/4",wz:"杭州地铁2号线20标",zb:[120.158724,30.316057]},
// {name: "SS32000", value:
// "0.83/2.83",wz:"南京宁溧线TA08标",zb:[118.753877,32.02318]}
// ]

