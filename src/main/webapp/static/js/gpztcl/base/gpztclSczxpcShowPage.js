/**
 * 实测中线查看页面弹出框调用js
 */
/** 系统模块同路径 */
var syspath = "gpztcl";
/** 子模块路径 */
var module = "base";
/** 子表业务数据访问全路径 */
var childPath = basePath +"/"+ syspath +"/"+ module+"/gpztclSczxInfo";
var grid;
$(function() {
	var fid=$('#fid').val();
	/** 创建数据表格	 */
	grid = $('#dataGrid').datagrid({
		url : childPath+'/dataGrid.action?fid='+fid,
		striped : true,
		rownumbers : true,
		singleSelect : false,
		checkOnSelect : true,
		selectOnCheck : true,
		pagination : false,
		idField : 'id',
		pageSize : 500,
		pageList : [ 500, 600,800, 1000 ],
		columns:[[
			{field:'id',align:'center',title:'ID',width:40, checkbox:true,rowspan:2},
			{width: '60',title: '环号',field: 'hh',rowspan:2 },
			{width: '100',title: '里程K(m)',field: 'lc',rowspan:2 },
			{width: '80',title: '实测线路中心线坐标(m)', colspan:3},
			{width: '80',title: '线路中心线设计坐标(m)',colspan:3},
			{width: '80',title: '实测偏移量(mm)',colspan:2 },
			{width: '80',title: '导向系统(mm)',colspan:2 },
			{width: '80',title: '较差(mm)',colspan:2 },
			{width: '100',title: '备注',field: 'remarks',rowspan:2,
				 formatter: function(value,row,index){
						return DataGridFormatter.tipsFormatter(value,20);
				  }
			}
		 ],[  
           {width: '80',title: 'X',field: 'sczbX'},
           {width: '80',title: 'Y',field: 'sczbY'},
           {width: '80',title: 'H',field: 'sczbH'},
           {width: '80',title: 'X',field: 'jszbX'},
           {width: '80',title: 'Y',field: 'jszbY'},
           {width: '80',title: 'H',field: 'jszbH'},
           {width: '50',title: '横向',field: 'scpylX'},
           {width: '50',title: '竖向',field: 'scpylY'},
           {width: '50',title: '横向',field: 'dxpyX'},
           {width: '50',title: '竖向',field: 'dxpyY'},
           {width: '50',title: '横向',field: 'jcX'},
           {width: '50',title: '竖向',field: 'jcY'}
		]],
		onLoadSuccess : function(data) {
		},
		toolbar:"#showPagetoolbar"
	});
});

/**
 * 【实测中线信息管理】导出excel
 * @returns
 */
function expGpztclSczxInfoXls(){
	var fid = $('#fid').val();
	var url = childPath + "/expXls?fid="+fid;
	ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, "实测中线明细信息", grid, false);
}