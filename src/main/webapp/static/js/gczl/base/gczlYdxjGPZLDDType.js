/**
 * 日志管理系统 日志信息模块
 * 创建时间：2016年11月17日 
 */

/**
 * 系统模块同路径
 */
var syspath = "gczl";
/**
 * 子模块路径
 */
var module = "gpzlType";
/**
 * 模块名称
 */
var moduleName = "盾构施工质量基础数据";
/**
 * 名称字段
 */
var nameField = "typeName";
/**
 * 业务数据访问全路径
 */
var path = basePath +"/"+ syspath +"/"+ module;
//alert(path);



/**
 * 列表对象
 */
var dataGrid;
$(function() {
	/**
	 * 创建数据表格
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
		pageSize : 15,
		pageList : [ 10, 15, 20, 50 ],
		frozenColumns:[[
			{width: '180',title: '分类名称',field: 'typeName'}, 
			{width: '80',title: '排序号',field: 'sortNum'}, 
			{width: '80',title: '使用状态',field: 'ifQy'}, 
			{width: '250',title: '备注',field: 'remarks'}
		]],
		onLoadSuccess : function(data) {},
		toolbar : [ 
					{text : '刷新',iconCls: 'icon-reload',
					    handler: function(){
					    	dataGrid.datagrid('reload');
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

