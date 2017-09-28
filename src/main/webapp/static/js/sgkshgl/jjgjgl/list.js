
/**
 * 系统模块同路径
 */
var syspath = "sgkshgl";
/**
 * 子模块路径
 */
var module = "jjgjgl";
/**
 * 模块名称
 */
var moduleName = "掘进轨迹管理";
/**
 * 名称字段
 */
var nameField = "name";
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
	 * 组织机构树
	 */
	orgzTree = $('#orgzTree').tree({
	url : basePath+'/sgkshgl/jjgjgl/tree',
	parentField : 'pid',
	lines : true,
	onClick : function(node) {
		dataGrid.datagrid('load', {
	        proId: node.id
	    });
	}
	});
	
	/**
	 *获取工程信息
	 *
	 */
	$.ajax({
		url : basePath+'/project/line/getProDic',
		dataType : 'json',
		success : function(jsonstr) {
			jsonstr.unshift({
				'id' : '-1',
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
				 },
				 onSelect:function(newValue,oldValue){
			        	$('#search_sectionId').combobox("clear");
			        	$('#search_sectionId').combobox('reload', basePath+"/project/line/getProSectionDic?proId="+newValue.id);
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
			        	$('#add_sectionId').combobox('reload', basePath+"/project/line/getProSectionDic?proId="+newValue.id);
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
			        	$('#edit_sectionId').combobox('reload', basePath+"/project/line/getProSectionDic?proId="+newValue.id);
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
			        	$('#show_sectionId').combobox('reload', basePath+"/project/line/getProSectionDic?proId="+newValue.id);
			      }
			});

		}
	});
	
	/**
	 *获取工程区间信息
	 *
	 */
	$.ajax({
		url : basePath+'/project/line/getProSectionDic',
		dataType : 'json',
		success : function(jsonstr) {

			$('#search_sectionId').combobox({
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
				}
			});

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
				}
			});

		}
	});
	
	/**
	 * 创建数据表格
	 *
	 */
	dataGrid = $('#dataGrid').datagrid({
		url : path+'/dataGrid',
		striped : true,
		rownumbers : true,
		singleSelect : false,
		checkOnSelect : true,
		selectOnCheck : true,
		pagination : true,
		idField : 'id',
		sortName : 'id',
		sortOrder : 'desc',
		pageSize : 15,
		pageList : [ 10, 15, 20, 50 ],
		frozenColumns:[[
		                
			{field : 'ck',checkbox : true},
			{width : '100',title : '是否绘制',field : 'ifhz',sortable : true,
				formatter: function(value,row,index){
					var data={lineId:row.id};
					var a;
					$.ajax({
				        url: path+"/ifhz",              //请求地址
				        type: "POST",                       //请求方式
				        data: data,        //请求参数
				        async: false,
				        dataType: "json",
				        success: function (result) {
				        	if(result){
				        		a=true;
				        	}else{
				        		a=false;
				        	}
				        }				
					});	
					if(a){
						return "已绘制";
					}else{
						return "未绘制";
					}
				}
				},
			{width : '150',title : '线路名称',field : 'lineName',sortable : true, 
				formatter: function(value,row,index){
					return '<a href="#" onclick="showFun(\''+ row.id +'\', \'' + index + '\')">' + value + '</a>';
				}
			}, 
			{width : '150',title : '区间名称',field : 'sectionName',sortable : true}, 
            {width : '320',title : '所属工程',field : 'proName',sortable : true}
		     ]],
		columns : [ [
			{width : '100',title : '当前盾构机',field : 'usingTbmName',sortable : true}, 
			{width : '100',title : '盾构机',field : 'tbmName',sortable : true},
			{width : '100',title : '盾构机司机',field : 'dgChauffeur',align:'right'},
            {width : '100',title : '起始里程',field : 'startMileage',align:'right'}, 
            {width : '100',title : '终止里程',field : 'endMileage',align:'right'}, 
            {width : '100',title : '管环数量',field : 'ringCount',align:'right'}, 
            {width : '100',title : '里程前辍标识',field : 'mileagePrefix',sortable : true}, 
            {width : '100',title : '隧道长度(米)',field : 'tunnellength',align:'right'}, 
            
            {width : '140',title : '预计始发日期',field : 'yjsfrq',align:'right'}, 
            {width : '140',title : '预计出洞日期',field : 'yjcdrq',align:'right'}, 
            {width : '140',title : '实际始发日期',field : 'sjsfrq',align:'right'}, 
            {width : '140',title : '实际出洞日期',field : 'sjcdrq',align:'right'}, 
            
            {width : '100',title : '推进总工期(天)',field : 'tunneltime',align:'right'}, 
            {width : '100',title : '起始环号',field : 'startRingnum',align:'right'}, 
            {width : '100',title : '状态',field : 'lineStatus',sortable : true,formatter: function(value,row,index){
				if (row.lineStatus == 0){
					return "未开工";
				} else if(row.lineStatus == 1) {
					return "已开工";
				} else if(row.lineStatus == 2){
					return "已完工";
				}
			}}, 
            {width : '120',title : '录入时间',field : 'entertime',sortable : true}
			] ],
		onLoadSuccess : function(data) {},
		toolbar: '#toolbar'
		
	});
});
/**
 * 刷新
 */
function reloadFun(){
	dataGrid.datagrid('load', {});
}
/**
 * 取消已选
 */
function clearSelections(){
	dataGrid.datagrid('clearSelections');
}

/**
 * 数据添加
 */
function addFun() {
	var id;
	var rows = dataGrid.datagrid('getSelections');
	if (rows.length > 1) {
		$.messager.alert('提示', '请选择单条数据进行绘制！', 'info');
		return;
	}
	else if (rows.length == 1) {
		if (rows[0]) {
			id = rows[0].id;
		}
		else {
			$.messager.alert('提示', '请选择数据后再绘制！', 'info');
			return;
		}
	}
	else {
		$.messager.alert('提示', '请选择数据后再绘制！', 'info');
		return;
	}
	if (rows[0]) {
	$('#openMapIframe')[0].src=path+'/selectGj?lineId='+id;
	$('#openDiv').dialog('open');
	}
}

/**
 * 查看数据页面
 */
function showFun(oid,index) {
	var id;
	var row;
	if(oid){
		id = oid;
		var allrows = dataGrid.datagrid('getRows');
		row = allrows[index];
	}else{
		var rows = dataGrid.datagrid('getSelections');
		if (rows.length > 1) {
			$.messager.alert('提示', '请选择单条数据进行查看！', 'info');
			return;
		}
		else if (rows.length == 0) {
			$.messager.alert('提示', '请选择数据后再查看！', 'info');
			return;
		}
		if (rows[0]) {
			row = rows[0];
			id = rows[0].id;
		}
		else {
			$.messager.alert('提示', '请选择数据后再查看！', 'info');
			return;
		}	
	}
	if (row) {
		$("#showDialog").dialog('open').dialog('setTitle', '查看线路信息');
		$('#showDialog').form('load', row);
		disableForm('showForm',true);
	}
}

/**
 * 表单查询
 */
function searchFun() {
	dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
}

/**
 * 表单重置
 */
function cleanFun() {
	$('#searchForm input').val('');
	$('#searchForm').form('clear');
	dataGrid.datagrid('load', {});
}
