/**
 * 日志管理系统 日志信息模块
 * 
 * 创建时间：2016年11月05日 
 * 
 */

/**
 * 系统模块同路径
 */
var syspath = "log";
/**
 * 子模块路径
 */
var module = "syslog";
/**
 * 模块名称
 */
var moduleName = "日志";
/**
 * 名称字段
 */
var nameField = "optName";
/**
 * 业务数据访问全路径
 */
var path = basePath +"/"+ syspath +"/"+ module;
/**
 * 列表对象
 */
var dataGrid;
$(function() {
	disableForm('showForm',true);
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
			{width: '80',title: '登录名',field: 'loginName',sortable: true}, 
			{width: '80',title: '用户名',field: 'roleName'}, 
			{width: '200',title: '创建时间',field: 'createTime',
				formatter: function(value,row,index){
					return '<a href="#" onclick="showFun(\''+ row.id +'\', \'' + index + '\')">' + value + '</a>';
				}
			}
		    ]],
		columns : [ [
			{width : 140,title : '操作系统',field: 'sysName'}, 
			{width : 140,title : '操作模块',field: 'moduleName'}, 
			{width : 100,title : '操作',field : 'optName'},
			{width: '100',title: 'IP地址',field: 'clientIp',sortable: true}, 
			{width: '800',title: '内容',field: 'optContent'}
			] ],
		onLoadSuccess : function(data) {},
		toolbar : [ 
					{text : '刷新',iconCls: 'icon-reload',
					    handler: function(){
					    	dataGrid.datagrid('reload');
					    }
					},
					'-',
		            {text : "清空",iconCls : 'icon-del',handler : clearLogs},
		            '-', 
		            {text : "查看",iconCls : 'icon-book_previous',
		    			handler: function(){
		            		showFun();
		    			}
		            },
		    		'-',
		    		{
		    			text: '导出Excel',
		    			iconCls: 'icon-page_excel',
		    			handler: function(){
		    				var url = path + "/expXls";
		    				
		    				ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, moduleName+"信息", dataGrid, false);
		    			}
		    		},'-',{
		    			text: '导出Pdf',
		    			iconCls: 'icon-page_white_acrobat',
		    			handler: function(){
		    				var url = path + "/expPdf";
		    				
		    				ExportUtil.doExport(url, ExportUtil.TYPE_PDF, moduleName+"信息", dataGrid, false);
		    			}
		    		}
		           ]
	});
});

//清空所有系统日志
function clearLogs(){
	$.messager.confirm('询问', '您确定要清空所有系统日志吗？此操作将不可恢复！', function(r){
		if(r){			
			$.ajax({
				url : path + "/clearLogs",
				type : 'POST',
				dataType : 'json',
				success : function(data){
					if(data.success){
						dataGrid.datagrid('load');//刷新
					}
					$.messager.alert('提示', data.msg, '');
				}
			});
		}
	
	});
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
		$("#showDialog").dialog('open').dialog('setTitle', '查看［'+moduleName+"］"+"-"+row[nameField]);
		$('#showDialog').form('load', row);
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
	dataGrid.datagrid('load', {});
}