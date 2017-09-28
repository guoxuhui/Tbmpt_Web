/**
 * 设备报警历史查询 设备报警历史查询
 * 
 * 创建时间：2017年05月26日 
 * 
 */
/**
 * 系统模块同路径
 */
var syspath = "machine";
/**
 * 子模块路径
 */
var module = "alarmhis";
/**
 * 模块名称
 */
var moduleName = "报警名称";
/**
 * 名称字段
 */
var nameField = "alarmname";
/**
 * 业务数据访问全路径
 */
var path = basePath +"/"+ syspath +"/"+ module;
var zdpath = basePath +"/project/line";
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
		url : zdpath+'/getProDic',
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
			        	$('#search_sectionId').combobox('reload', zdpath+"/getProSectionDic?proId="+newValue.id);
			      }
			});

		}
	});
	
	/**
	 *获取工程区间信息
	 *
	 */
	$.ajax({
		url : zdpath+'/getProSectionDic',
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
		sortName : 'alarmtime',
		sortOrder : 'desc',
		pageSize : 15,
		pageList : [ 10, 15, 20, 50 ],
		frozenColumns:[[
		                
			{field : 'ck',checkbox : true},
			{width : '150',title : '报警名称',field : 'alarmname',sortable : true, 
				formatter: function(value,row,index){
					return '<a href="#" onclick="showFun(\''+ row.id +'\', \'' + index + '\')">' + value + '</a>';
				}
			}, 
            {width : '320',title : '报警内容',field : 'alarmcontent',sortable : true}
		     ]],
		columns : [ [
			{width : '100',title : '报警类型',field : 'alarmtype',sortable : true}, 
			{width : '100',title : '盾构机',field : 'tbmname',sortable : true},
			{width : '100',title : '项目名称',field : 'proname',align:'right'},
            {width : '100',title : '区间名称',field : 'sectionname',align:'right'}, 
            {width : '100',title : '线路名称',field : 'linename',align:'right'}, 
            {width : '120',title : '报警时间',field : 'alarmtime',sortable : true}
			] ],
		onLoadSuccess : function(data) {},
		toolbar: '#toolbar'
	});
});
/**
 * 刷新
 */
function reloadFun(){
	dataGrid.datagrid('reload');
}
/**
 * 取消已选
 */
function clearSelections(){
	dataGrid.datagrid('clearSelections');
}
/**
 * 导出Excel
 */
function expXls(){
	var url = path + "/expXls";
	
	ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, moduleName+"信息", dataGrid, false);
}
/**
 * 导出pdf
 */
function expPdf(){
	var url = path + "/expPdf";
	
	ExportUtil.doExport(url, ExportUtil.TYPE_PDF, moduleName+"信息", dataGrid, false);
}

/**
 * 查看数据页面
 */
function showFun(id,index) {
	if (id == undefined) {
        var rows = dataGrid.datagrid('getSelections');
        id = rows[0].id;
    } else {
        dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
    }
    parent.$.modalDialog({
        title : '查看',
        width : 800,
        height : 600,
        href : path+'/info?id=' + id
    });
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