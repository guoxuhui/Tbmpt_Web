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

var dwgc_path=basePath+"/"+ syspath +"/dwgc";
/**
 * 列表对象
 */
var dataGrid;
var dwgcfl=[{text:'盾构掘进'},{text:'主体结构'}];
$(function() {

	$("#add_dwgcfl").combobox({
		data : dwgcfl,
		valueField : "text",
		textField : "text",
		onLoadSuccess : function() {
		var val = $(this).combobox('getData');
		for (var item in val[0]) {
			if (item == 'id') {
				$(this).combobox('select', val[0][item]);
			}
		}
	}
	});
	$("#edit_dwgcfl").combobox({
		data : dwgcfl,
		valueField : "text",
		textField : "text",
		onLoadSuccess : function() {
		var val = $(this).combobox('getData');
		for (var item in val[0]) {
			if (item == 'id') {
				$(this).combobox('select', val[0][item]);
			}
		}
	}
	});
	$("#show_dwgcfl").combobox({
		data : dwgcfl,
		valueField : "text",
		textField : "text",
		onLoadSuccess : function() {
		var val = $(this).combobox('getData');
		for (var item in val[0]) {
			if (item == 'id') {
				$(this).combobox('select', val[0][item]);
			}
		}
	}
	});

	$('#search_proId').combobox({
		url : path+'/getProDic',
		valueField : "id",
		textField : "proName",
		onLoadSuccess : function() {
			
		},
		onSelect:function(newValue,oldValue){
			$('#search_qjId').combobox("clear");
        	$('#search_qjId').combobox('reload', path+"/getProSectionDic?proId="+newValue.id);
		}
	});
	
	$('#search_qjId').combobox({
		url : path+'/getProSectionDic',
		valueField : "id",
		textField : "sectionName",
		onLoadSuccess : function() {

		}
	});
	/**
	 * 创建单位工程表格
	 */
	dwgc_dataGrid=$('#dwgc_dataGrid').datagrid({
		url : dwgc_path+'/dataGrid',
		striped : true,
		rownumbers : false,
		singleSelect : false,
		checkOnSelect : true,
		selectOnCheck : true,
		pagination : true,
		idField : 'id',
		sortName : 'id',
		sortOrder : 'desc',
		pageSize : 15,
		pageList : [ 10, 15, 20, 50 ],
//		frozenColumns:[[]],
		columns : [ [
			{field : 'ck',checkbox : true},
			{width : '200',title : '单位工程名称',field : 'dwgcname', 
				formatter: function(value,row,index){
					return '<a href="#" onclick="showFun(\''+ row.id +'\', \'' + index + '\')">' + value + '</a>';
				}
			},
			{width : '320',title : '工程名称',field : 'proName',sortable : true},
			{width : '150',title : '单位工程分类',field : 'dwgcfl',sortable : true},
			{width : '320',title : '分部工程',field : 'fbgcname',sortable : false,
				formatter:function(value,row,index){												
					return $.formatString('<a class="add" href="javascript:void(0)" iconCls="icon-add" onclick="addFbgc(\'{0}\',\'{1}\',\'{2}\',\'{3}\');" ></a>',row.id,row.proName,row.dwgcname,row.proId);
			}
			},			
			{width : '320',title : '备注',field : 'remarks'} 
			] ],
		onLoadSuccess : function(data) {
			$("a.add").linkbutton();
		},
		toolbar : '#toolbar'
		
	});
	
	
	/**
	 * 创建分部工程数据表格
	 *
	 */
	dataGrid_fbgc = $('#dataGrid_fbgc').datagrid({
		url : path+'/dataGridByDwgcId',
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
			{field : 'cke',checkbox : true},
			{width : '200',title : '分部工程名称',field : 'fbgcname', 
				formatter: function(value,row,index){
					return '<a href="#" onclick="showFun_fbgc(\''+ row.id +'\', \'' + index + '\')">' + value + '</a>';
				}
			}
		]],
		columns : [ [
			{width : '320',title : '工程名称',field : 'proName',sortable : true},
			{width : '320',title : '备注',field : 'remarks'} 
			] ],
		onLoadSuccess : function(data) {},
		toolbar :'#toolbar_fbgc'
			
	});
	
	
	
	
	
	
	
	/**
	 * 分部工程查看表格
	 */
	dataGrid_fbgc_show = $('#dataGrid_fbgc_show').datagrid({
		url : path+'/dataGridByDwgcId',
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
			{field : 'cke',checkbox : true},
			{width : '200',title : '分部工程名称',field : 'fbgcname', 
				formatter: function(value,row,index){
					return '<a href="#" onclick="showFun_fbgc_show(\''+ row.id +'\', \'' + index + '\')">' + value + '</a>';
				}
			}
		]],
		columns : [ [
			{width : '320',title : '工程名称',field : 'proName',sortable : true},
			{width : '320',title : '备注',field : 'remarks'} 
			] ],
		onLoadSuccess : function(data) {},
		toolbar : []
	});
	
	
});


			
/**
 * 刷新
 */
function reloadFun_fbgc(){
	dataGrid_fbgc.datagrid('reload');
}

/**
 * 取消已选
 */
function clearSelections_fbgc(){
	dataGrid_fbgc.datagrid('clearSelections');
}

/**
 * 分部工程增加
 */
function addFun_fbgc(){
	FormUtil.clearForm($("#addDialog_fbcg"));
	$("#addDialog_fbcg").dialog('open').dialog('setTitle', '添加［'+moduleName+"］");
	var proName=$("#add_projectName").html();
	var proId=$("#addForm_gcBh").val();
	var dwgcId=$("#addForm_id").val();
	$("#add_proId_fbgc").textbox('setText',proName);
	$("#addForm_proId").val(proId);
	$("#addForm_dwgcId").val(dwgcId);
	

	
}

/**
 * 分部工程增加单条数据保存
 */
function addAjax_fbgc() {
	$('#addForm_fbgc').form('submit', {
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
				dataGrid_fbgc.datagrid('reload');
				$("#addDialog_fbcg").dialog('close');
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
	$("#addDialog_fbcg").dialog('close');
	$("#addDialog_fbcg").dialog('reload');
}
/**
 * 分部工程保存数据表格
 */
function addSave_fbgc(){
	$('#addFbgc').dialog('close');
}
/**
 * 分部工程数据编辑
 */
function editFun_fbgc(){
	var id;
	var rows = dataGrid_fbgc.datagrid('getSelections');	

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
		$("#editDialog_fbgc").dialog('open').dialog('setTitle', '编辑［'+moduleName+"］");
		var proName=$("#add_projectName").html();
		var proId=$("#addForm_gcBh").val();
		var dwgcId=$("#addForm_id").val();
		$('#editForm_fbgc').form('load', rows[0]);
		$("#edit_proId_fbgc").textbox('setText',proName);
		$("#editForm_proId").val(proId);
		$("#editForm_dwgcId").val(dwgcId);		
	}
}
/**
 * 更新数据表格
 */
function editAjax_fbgc(){
	$('#editForm_fbgc').form('submit', {
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
				dataGrid_fbgc.datagrid('reload');
				$("#editDialog").dialog('close');
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
//	dataGrid_fbgc.datagrid('clearSelections');
//	dataGrid_fbgc.datagrid('clearChecked');
	$("#editDialog_fbgc").dialog('close');
	$("#addDialog_fbcg").dialog('reload');
}

/**
 * 分部工程删除数据表格数据
 */
function deleteFun_fbgc(){
	var ids = new Array();
	var rows = dataGrid_fbgc.datagrid('getSelections');
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
					dataGrid_fbgc.datagrid('reload');
					dataGrid_fbgc.datagrid('clearSelections');
				}
				progressClose();
			}, 'JSON');
		}
	});
}
/**
 * 查看
 */
function showFun_fbgc(oid,index){
	var id;
	var row;
	if(oid){
		id = oid;
		var allrows = dataGrid_fbgc.datagrid('getRows');
		row = allrows[index];
	}else{
		var rows = dataGrid_fbgc.datagrid('getSelections');
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
		$("#showDialog_fbgc").dialog('open').dialog('setTitle', '查看［'+moduleName+"］");
		$('#showDialog_fbgc').form('load', row);
		
		disableForm('showForm',true);
	}
}

/**
 * 导出Excel
 */
function expXls_fbgc(){
	var dwgcId=$('#addForm_id').val();
	var url = path + "/expXlsByDwgcId?dwgcId="+dwgcId;
	
	ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, moduleName+"信息", dataGrid_fbgc, false);
}
/**
 * 导出pdf
 */
function expPdf_fbgc(){
	var dwgcId=$('#addForm_id').val();
	var url = path + "/expPdfByDwgcId?dwgcId="+dwgcId;
	
	ExportUtil.doExport(url, ExportUtil.TYPE_PDF, moduleName+"信息", dataGrid_fbgc, false);
}











/**
 * 刷新
 */
function reloadFun(){
	dwgc_dataGrid.datagrid('reload');
}
/**
 * 取消已选
 */
function clearSelections(){
	dwgc_dataGrid.datagrid('clearSelections');
}
/**
 * 导出Excel
 */
function expXls(){
	var url = dwgc_path + "/expXls";
	
	ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, "单位工程信息", dwgc_dataGrid, false);
}
/**
 * 导出pdf
 */
function expPdf(){
	var url = dwgc_path + "/expPdf";
	
	ExportUtil.doExport(url, ExportUtil.TYPE_PDF,"单位工程信息", dwgc_dataGrid, false);
}

/**
 * 查看分部工程
 */
function showFun_fbgc_show(oid,index){
	var id;
	var row;
	if(oid){
		id = oid;
		var allrows = dataGrid_fbgc_show.datagrid('getRows');
		row = allrows[index];
	}else{
		var rows = dataGrid_fbgc.datagrid('getSelections');
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
		$("#showDialog_fbgc").dialog('open').dialog('setTitle', '查看［'+moduleName+"］");
		$('#showDialog_fbgc').form('load', row);
		disableForm('showForm',true);
	}
}


/**
 * 增加分部工程
 */
function addFbgc(id,proName,dwgcname,proId){
	var rows = dwgc_dataGrid.datagrid('getSelections');
	
	$("#add_projectName").html(proName);
	$("#add_dwgcname").html(dwgcname);
	
	$("#addForm_gcBh").val(proId);
	
	$("#addForm_id").val(id);
	
	$("#addFbgc").dialog('open').dialog('setTitle', '添加分部工程');
	dataGrid_fbgc.datagrid("load",{id:id});
	dataGrid_fbgc.datagrid('clearSelections');
}


/**
 * 数据添加
 */
function addFun() {
	FormUtil.clearForm($("#addDialog"));
	$("#addDialog").dialog('open').dialog('setTitle', '添加单位工程');
	$("#add_proId").combobox({
		url : path+'/getProDic',
		valueField : "id",
		textField : "proName",
		onLoadSuccess : function() {

		},
		onSelect:function(newValue,oldValue){
			$('#add_qjId').combobox("clear");
        	$('#add_qjId').combobox('reload', path+"/getProSectionDic?proId="+newValue.id);
		}
	});
	$("#add_qjId").combobox({
		url : path+'/getProSectionDic',
		valueField : "id",
		textField : "sectionName",
		onLoadSuccess : function() {

		}
	});

}

/**
 * 数据保存
 */
function addAjax() {
	$('#addForm').form('submit', {
		url : dwgc_path+'/add',
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
				dwgc_dataGrid.datagrid('reload');
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
	var rows = dwgc_dataGrid.datagrid('getSelections');
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
		$("#editDialog").dialog('open').dialog('setTitle', '编辑单位工程');
		$("#edit_qjId").combobox({
			url : path+'/getProSectionDic',
			valueField : "id",
			textField : "sectionName",
			onLoadSuccess : function() {

			}
		});
		$("#edit_proId").combobox({
			url : path+'/getProDic',
			valueField : "id",
			textField : "proName",
			onLoadSuccess : function() {
				$('#editForm').form('load', rows[0]);
			},
			onSelect:function(newValue,oldValue){
				$('#edit_qjId').combobox("clear");
	        	$('#edit_qjId').combobox('reload', path+"/getProSectionDic?proId="+newValue.id);
			}
		});

		
	}
}

/**
 * 修改提交
 */
function editAjax() {
	$('#editForm').form('submit', {
		url : dwgc_path+'/edit',
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
				dwgc_dataGrid.datagrid('reload');
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
	var rows = dwgc_dataGrid.datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择数据后再删除！', 'info');
		return;
	}
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].id);
	}
	$.messager.confirm('询问', '您是否要删除所选行？', function(b) {
		if (b) {
			progressLoad();
			$.post(dwgc_path+'/delete', {
				ids : ids.join(",")
			}, function(result) {
				if (result.success) {
					$.messager.alert('提示', result.msg, 'info');
					dwgc_dataGrid.datagrid('reload');
					dwgc_dataGrid.datagrid('clearSelections');
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
		var allrows = dwgc_dataGrid.datagrid('getRows');
		row = allrows[index];
	}else{
		var rows = dwgc_dataGrid.datagrid('getSelections');
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
		$("#showDialog").dialog('open').dialog('setTitle', '查看单位工程');
		$('#showDialog').form('load', row);
		/*$("#show_qjId").combobox({
			url : path+'/getProSectionDic',
			valueField : "id",
			textField : "sectionName",
			onLoadSuccess : function() {

			}
		});
		$("#show_proId").combobox({
			url : path+'/getProDic',
			valueField : "id",
			textField : "proName",
			onLoadSuccess : function() {
				$('#showDialog').form('load', row);
			},
			onSelect:function(newValue,oldValue){
				$('#show_qjId').combobox("clear");
	        	$('#show_qjId').combobox('reload', path+"/getProSectionDic?proId="+newValue.id);
			}
		});
*/
		
		disableForm('showForm',true);
		dataGrid_fbgc_show.datagrid("load",{id:id});
	}
}


/**
 * 表单查询
 */
function searchFun() {
	dwgc_dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
}

/**
 * 表单重置
 */
function cleanFun() {
	$('#searchForm input').val('');
	dwgc_dataGrid.datagrid('load', {});
}