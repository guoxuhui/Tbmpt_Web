/** /** 【线路中心线信息管理】管片姿态测量系统 基础模块
 * 创建时间：2016-12-20
   */
/** 系统模块同路径 */
var syspath = "gpztcl";
/** 子模块路径 */
var module = "base";
/** 菜单名称 */
var entityCnName = "线路中心线信息管理";
/** 名称字段 */
var nameField = "";
/** 业务数据访问全路径 */
var path = basePath +"/"+ syspath +"/"+ module+"/gpztclSjzxInfoParent";
/** 列表对象 */
var dataGrid_gpztclSjzxInfoParent;
$(function() {
	/** 创建数据表格	 */
	dataGrid_gpztclSjzxInfoParent = $('#dataGrid_gpztclSjzxInfoParent').datagrid({
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
            
			{field : 'ck',checkbox : true},
			{width : '150',title : '线路名称',field : 'lineName',sortable : true, 
				formatter: function(value,row,index){
					return '<a href="#" onclick="showFun(\''+ row.id +'\', \'' + index + '\')">' + value + '</a>';
				}
			}, 
			{width : '150',title : '区间名称',field : 'sectionName',sortable : true}, 
            {width : '320',title : '所属项目',field : 'proName',sortable : true}
		     ]],
		columns : [ [
			{width : '100',title : '当前盾构机',field : 'usingTbmName',sortable : true}, 
			{width : '100',title : '盾构机',field : 'tbmName',sortable : true},
            {width : '100',title : '起始里程',field : 'startMileage',align:'right'}, 
            {width : '100',title : '终止里程',field : 'endMileage',align:'right'}, 
            {width : '100',title : '管环数量',field : 'ringCount',align:'right'}, 
            {width : '100',title : '里程前辍标识',field : 'mileagePrefix',sortable : true}, 
            {width : '100',title : '隧道长度(米)',field : 'tunnellength',align:'right'}, 
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
		toolbar : '#toolbar' 
	});
});

/** 【线路中心线信息管理】打开新增页面 */
function addGpztclSjzxInfoParentFun() {
	FormUtil.clearForm($("#addDialog_gpztclSjzxInfoParent"));
	$("#addDialog_gpztclSjzxInfoParent").dialog('open').dialog('setTitle', '添加［'+entityCnName+"］");
}

/** 【线路中心线信息管理】数据保存 */
function addGpztclSjzxInfoParentAjax() {
	$('#addForm_gpztclSjzxInfoParent').form('submit', {
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
				$.messager.show({title:'提示',msg:'新增操作：成功!',showType:'show' });
				dataGrid_gpztclSjzxInfoParent.datagrid('reload');
				$("#addDialog_gpztclSjzxInfoParent").dialog('close');
				FormUtil.clearForm($("#addDialog_gpztclSjzxInfoParent"));
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}

/** 【线路中心线信息管理】打开数据编辑页面 */
function editGpztclSjzxInfoParentFun() {
	var id;
	var rows = dataGrid_gpztclSjzxInfoParent.datagrid('getSelections');
	if (rows.length > 1) {
		$.messager.alert('提示', '请选择单条数据进行编辑！', 'info');
		return;
	} else if (rows.length == 1) {
		if (rows[0]) {
			id = rows[0].id;
		}else {
			$.messager.alert('提示', '请选择数据后再编辑！', 'info');
			return;
		}
	} else {
		$.messager.alert('提示', '请选择数据后再编辑！', 'info');
		return;
	}
	if (rows[0]) {
		$("#editDialog_gpztclSjzxInfoParent").dialog('open').dialog('setTitle', '编辑［'+entityCnName+'］'+'-'+rows[0][nameField]);
		$('#editForm_gpztclSjzxInfoParent').form('load', rows[0]);
	}
}

/** 【线路中心线信息管理】提交修改 */
function editGpztclSjzxInfoParentAjax() {
	var isValid = $('#editForm_gpztclSjzxInfoParent').form('validate');
	if (!isValid) return;
	$.messager.confirm('提示', '您确定要编辑［'+entityCnName + '］吗？', function(b) {
		if (b) {
			$('#editForm_gpztclSjzxInfoParent').form('submit', {
				url : path+'/edit',
				onSubmit : function() {
				  progressLoad();
				},
				success : function(result) {
					progressClose();
					result = $.parseJSON(result);
					if (result.success) {
				        $.messager.show({title:'提示',msg:'修改操作：成功!',showType:'show' });
						dataGrid_gpztclSjzxInfoParent.datagrid('reload');
						$("#editDialog_gpztclSjzxInfoParent").dialog('close');
					}
					else {
						$.messager.alert('错误', result.msg, 'error');
					}
				}
			});
		}
	});
}

/** 【线路中心线信息管理】数据删除 */
function deleteGpztclSjzxInfoParentFun() {
	var ids = new Array();
	var rows = dataGrid_gpztclSjzxInfoParent.datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择数据后再删除！', 'info');
		return;
	}
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].id);
	}
	$.messager.confirm('询问', '您是否要删除［'+entityCnName + '］所选行吗？', function(b) {
		if (b) {
			progressLoad();
			$.post(path+'/delete', {
				ids : ids.join(",")
			}, function(result) {
				progressClose();
				if (result.success) {
					$.messager.show({title:'提示',msg:'删除操作：成功!',showType:'show' });
					dataGrid_gpztclSjzxInfoParent.datagrid('reload');
					dataGrid_gpztclSjzxInfoParent.datagrid('clearSelections');
				}
			}, 'JSON');
		}
	});
}

/** 【线路中心线信息管理】查看数据页面 */
function showGpztclSjzxInfoParentFun(oid,index) {
	var id;
	var row;
	if(oid){
		id = oid;
		var allrows = dataGrid_gpztclSjzxInfoParent.datagrid('getRows');
		row = allrows[index];
	}else{
		var rows = dataGrid_gpztclSjzxInfoParent.datagrid('getSelections');
		if (rows.length > 1) {
			$.messager.alert('提示', '请选择单条数据进行查看！', 'info');
			return;
		}else if (rows.length == 0) {
			$.messager.alert('提示', '请选择数据后再查看！', 'info');
			return;
		}
		if (rows[0]) {
			row = rows[0];
			id = rows[0].id;
		}else {
			$.messager.alert('提示', '请选择数据后再查看！', 'info');
			return;
		}
	}
	if (row) {
		$("#showDialog_gpztclSjzxInfoParent").dialog('open').dialog('setTitle', '查看［'+entityCnName+'］'+'-'+row[nameField]);
		$('#showDialog_gpztclSjzxInfoParent').form('load', row);
	}
}

/**
 * 【线路中心线信息管理】导出excel
 * @returns
 */
function expGpztclSjzxInfoParentXls(){
	var url = path + "/expXls";
	ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, moduleName+"信息", dataGrid_gpztclSjzxInfoParent, false);
}

/**
 * 【线路中心线信息管理】刷新
 * @returns
 */
function reloadGpztclSjzxInfoParent(){
	dataGrid_gpztclSjzxInfoParent.datagrid('reload');
}

/**
 * 【线路中心线信息管理】取消已选
 * @returns
 */
function clearGpztclSjzxInfoParentSelections(){
	dataGrid_gpztclSjzxInfoParent.datagrid('clearSelections');
}/** 【线路中心线信息管理】表单查询 */
function searchGpztclSjzxInfoParentFun() {
	dataGrid_gpztclSjzxInfoParent.datagrid('load', $.serializeObject($('#searchForm_gpztclSjzxInfoParent')));
}

/**【线路中心线信息管理】表单重置 */
function cleanGpztclSjzxInfoParentFun() {
	$('#searchForm_gpztclSjzxInfoParent input').val('');
	dataGrid_gpztclSjzxInfoParent.datagrid('load', {});
}
