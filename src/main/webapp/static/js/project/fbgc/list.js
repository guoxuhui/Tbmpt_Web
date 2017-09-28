/**
 * 工程管理系统 分部工程信息管理模块
 * 
 * 创建时间：2016年11月18日 
 * 
 */

/**
 * 系统模块同路径
 */
var syspath = "project";
/**
 * 子模块路径
 */
var module = "fbgc";
/**
 * 模块名称
 */
var moduleName = "分部工程";
/**
 * 名称字段
 */
var nameField = "fbgcName";
/**
 * 业务数据访问全路径
 */
var path = basePath +"/"+ syspath +"/"+ module;
/**
 * 列表对象
 */
var dataGrid;
$(function() {

	$('#search_proId').combobox({
		url : path+'/getProDic',
		valueField : "id",
		textField : "proName",
		onLoadSuccess : function() {
			
		},
		onSelect:function(newValue,oldValue){
			$('#search_dwgcId').combobox("clear");
			var pId = newValue.id;
			$('#search_dwgcId').combobox({
        		url : path+'/getDwgcDic?proId='+pId,
        		valueField : "id",
        		textField : "dwgcname",
        		onLoadSuccess : function() {

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
			{width : '200',title : '分部工程名称',field : 'fbgcname', 
				formatter: function(value,row,index){
					return '<a href="#" onclick="showFun(\''+ row.id +'\', \'' + index + '\')">' + value + '</a>';
				}
			}
		]],
		columns : [ [
			{width : '320',title : '工程名称',field : 'proName',sortable : true},
			{width : '200',title : '单位工程名称',field : 'dwgcName'}, 
			{width : '320',title : '备注',field : 'remarks'} 
			] ],
		onLoadSuccess : function(data) {},
		toolbar :  '#toolbar'
			
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
 * 分部工程保存数据表格
 */
function addSave_fbgc(){
	$('#addFbgc').dialog('close');
}




/**
 * 数据添加
 */
function addFun() {
	FormUtil.clearForm($("#addDialog"));
	$("#addDialog").dialog('open').dialog('setTitle', '添加［'+moduleName+"］");
	$("#add_proId").combobox({
		url : path+'/getProDic',
		valueField : "id",
		textField : "proName",
		onLoadSuccess : function() {

		},
		onSelect:function(newValue,oldValue){
			$('#add_dwgcId').combobox("clear");
        	$('#add_dwgcId').combobox('reload', path+"/getDwgcDic?proId="+newValue.id);
		}
	});
	$("#add_dwgcId").combobox({
		url : path+'/getDwgcDic',
		valueField : "id",
		textField : "dwgcname",
		onLoadSuccess : function() {

		}
	});

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
				dataGrid.datagrid('reload');
				$("#addDialog").dialog('close');
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}

/**
 * 数据编辑
 */
function editFun() {
	var id;
	var rows = dataGrid.datagrid('getSelections');
	if (rows.length > 1) {
		$.messager.alert('提示', '请选择单条数据进行编辑！', 'info');
		return;
	}
	else if (rows.length == 1) {
		if (rows[0]) {
			id = rows[0].id;
		}
		else {
			$.messager.alert('提示', '请选择数据后再编辑！', 'info');
			return;
		}
	}
	else {
		$.messager.alert('提示', '请选择数据后再编辑！', 'info');
		return;
	}
	if (rows[0]) {
		$("#editDialog").dialog('open').dialog('setTitle', '编辑［'+moduleName+"］"+"-"+rows[0][nameField]);
		
		$("#edit_proId").combobox({
			url : path+'/getProDic',
			valueField : "id",
			textField : "proName",
			onLoadSuccess : function() {
				$('#editForm').form('load', rows[0]);
			},
			onSelect:function(newValue,oldValue){
				$('#edit_dwgcId').combobox("clear");
	        	$('#edit_dwgcId').combobox('reload', path+"/getDwgcDic?proId="+newValue.id);
			}
		});

		$("#edit_dwgcId").combobox({
			url : path+'/getDwgcDic',
			valueField : "id",
			textField : "dwgcname",
			onLoadSuccess : function() {

			}
		});
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
 * 数据删除
 */
function deleteFun() {
	var ids = new Array();
	var rows = dataGrid.datagrid('getSelections');
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
					$.messager.alert('提示', result.msg, 'info');
					dataGrid.datagrid('reload');
					dataGrid.datagrid('clearSelections');
				}
				progressClose();
			}, 'JSON');
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
	dataGrid.datagrid('load', {});
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

