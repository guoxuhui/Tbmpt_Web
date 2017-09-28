/**
 * 盾构机报警管理模块：机器数据报警类别配置
 * 
 * 创建时间：2017年06月06日 
 * 
 */
/**
 * 系统模块同路径
 */
var syspath = "machine";
/**
 * 子模块路径
 */
var module = "alarmhisConfig";
/**
 * 模块名称
 */
var moduleName = "机器数据报警类别配置信息";

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
		frozenColumns : [ [
   			{field : 'ck',checkbox : true},
   			{width : '150',title : '报警分类名称',field : 'name',sortable : true, 
				formatter: function(value,row,index){
					return '<a href="#" onclick="showFun(\''+ row.id +'\', \'' + index + '\')">' + value + '</a>';
				}
			},
			{width : '150',title : '所属电器系统',field : 'electricalsystem',sortable : true},
			{width : '100',title : '报警周期',field : 'alarmcycle'}
		          ]],
		columns : [ [
			{width : '150',title : '上次报警时间',field : 'lastalarmtime',sortable : true},
			{width : '150',title : '下次报警时间',field : 'nextalarmtime',sortable : true},
			{width : '200',title : '报警条件 ',field : 'alarmcondition'},
			{width : '100',title : '是否开启 ',field : 'isopen',sortable : true,
	            formatter : function(value, row, index) {
	            	
	                switch (value) {
	                case '0':
	                    return '开启';
	                case '1':
	                    return '关闭';
	                }
	            }
	        },
			{width : '300',title : '备注',field : 'remark'}
		     ]],
		
		onLoadSuccess : function() {},
		toolbar :'#toolbar'
	});
	
	
	
});




/**
 * 表单重置
 */
function cleanFun() {
	$('#searchForm input').val('');
	$('#searchForm').form('clear');
	dataGrid.datagrid('load', {});
}
/**
 * 表单查询
 */
function searchFun(){
	dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
}


/**
 * 刷新
 */
function reloadFun(){
	dataGrid.datagrid('reload');
}

/**
 * 取消已选
 * @returns
 */
function clearSelections(){
	dataGrid.datagrid('clearSelections');
}

/**
 * 打开添加页面
 * @returns
 */
function addFun() {
	FormUtil.clearForm($("#addDialog"));
	$("#addDialog").dialog('open').dialog('setTitle', '添加［'+moduleName+"］");
	
}

/**
 * 数据保存
 */
function addAjax() {
	$('#addForm').form('submit', {
		url : path+'/add',
		onSubmit : function() {
			progressLoad();
			var isValid = $(this).form('validate');
			if (!isValid) {
				progressClose();
			}
			return isValid;
		},
		success : function(result) {
			progressClose();
			result = $.parseJSON(result);
			if (result.success) {
				$.messager.show({title:'提示',msg:result.msg,showType:'show' });
				$('#dataGrid').datagrid('reload');
				$("#addDialog").dialog('close');
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}

/**
 * 打开编辑页面
 * @returns
 */
function editFun() {
	
	var id;
	var rows = $('#dataGrid').datagrid('getSelections');
	if (rows.length > 1) {
		$.messager.alert('提示', '请选择单条数据进行编辑！', 'info');
		return;
	}
	
	else if(rows.length==0){
		$.messager.alert('提示', '请选择数据后再编辑！', 'info');
		return;
	}
	if (rows[0]) {
		$("#editDialog").dialog('open').dialog('setTitle', '编辑［'+moduleName+"］");
		
		$('#editForm').form('load', rows[0]);
		
	}

}


/**
 * 修改提交
 */
function editAjax() {
	$('#editForm').form('submit', {
		url : path+'/edit',
		onSubmit : function() {
			progressLoad();
			var isValid = $(this).form('validate');
			if (!isValid) {
				progressClose();
			}
			return isValid;
		},
		success : function(result) {
			progressClose();
			result = $.parseJSON(result);
			if (result.success) {
				$.messager.show({title:'提示',msg:result.msg,showType:'show' });
				dataGrid.datagrid('reload');
				$("#editDialog").dialog('close');
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
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
		$("#showDialog").dialog('open').dialog('setTitle', '查看［'+moduleName+"］");
		$('#showDialog').form('load', row);
		disableForm('showForm',true);
	}
}

/**
 * 数据删除
 */
function deleteFun() {
	var ids = new Array();
	var rows =$('#dataGrid').datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择数据后再删除！', 'info');
		return;
	}
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].id);
	}
	$.messager.confirm('询问', '您是否要删除［'+moduleName + '］所选行吗？', function(b) {
		if (b) {
			progressLoad();
			$.post(path+'/delete', {
				ids : ids.join(",")
			}, function(result) {
				if (result.success) {
					$.messager.show({title:'提示',msg:result.msg,showType:'show' });
					$('#dataGrid').datagrid('reload');
					$('#dataGrid').datagrid('clearSelections');
				}else{
					$.messager.alert('提示',result.msg, 'info');
				}
				progressClose();
			}, 'JSON');
		}
	});
}


/**
 * 导出excel
 * @returns
 */
function expXls(){
	var url = path + "/expXls";
	ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, moduleName,dataGrid, false);
	
}

/**
 * 导出excel 
 * @returns
 */
function expPdf(){ 
	var url = path + "/expPdf";
	ExportUtil.doExport(url, ExportUtil.TYPE_PDF, moduleName,dataGrid, false);
	 
}



