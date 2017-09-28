/** /** 【实测中线信息明细管理】管片姿态测量系统 基础模块
 * 创建时间：2016-12-20
   */
/** 系统模块同路径 */
var syspath = "gpztcl";
/** 子模块路径 */
var module = "base";
/** 菜单名称 */
var entityCnName = "实测中线信息明细管理";
/** 名称字段 */
var nameField = "";
/** 业务数据访问全路径 */
var path = basePath +"/"+ syspath +"/"+ module+"/gpztclSczxInfo";
/** 列表对象 */
var dataGrid_gpztclSczxInfo;
$(function() {
	/** 创建数据表格	 */
	dataGrid_gpztclSczxInfo = $('#dataGrid_gpztclSczxInfo').datagrid({
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
		columns:[[
           {field:'id',align:'center',title:'ID',width:40, checkbox:true},
           {width: '80',title: '主表主键',field: 'fid'},
           {width: '80',title: '环号',field: 'hh'},
           {width: '80',title: '实测横向坐标',field: 'sczbX'},
           {width: '80',title: '实测纵向坐标',field: 'sczbY'},
           {width: '80',title: '实测高程',field: 'sczbH'},
           {width: '80',title: '计算横向坐标',field: 'jszbX'},
           {width: '80',title: '计算纵向坐标',field: 'jszbY'},
           {width: '80',title: '计算高程',field: 'jszbH'},
           {width: '80',title: '导向测量横向偏移',field: 'dxpyX'},
           {width: '80',title: '导向测量纵向偏移',field: 'dxpyY'}
		]],
		onLoadSuccess : function(data) {},
		toolbar : '#toolbar' 
	});
});

/** 【实测中线信息明细管理】打开新增页面 */
function addGpztclSczxInfoFun() {
	FormUtil.clearForm($("#addDialog_gpztclSczxInfo"));
	$("#addDialog_gpztclSczxInfo").dialog('open').dialog('setTitle', '添加［'+entityCnName+"］");
}

/** 【实测中线信息明细管理】数据保存 */
function addGpztclSczxInfoAjax() {
	$('#addForm_gpztclSczxInfo').form('submit', {
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
				dataGrid_gpztclSczxInfo.datagrid('reload');
				$("#addDialog_gpztclSczxInfo").dialog('close');
				FormUtil.clearForm($("#addDialog_gpztclSczxInfo"));
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}

/** 【实测中线信息明细管理】打开数据编辑页面 */
function editGpztclSczxInfoFun() {
	var id;
	var rows = dataGrid_gpztclSczxInfo.datagrid('getSelections');
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
		$("#editDialog_gpztclSczxInfo").dialog('open').dialog('setTitle', '编辑［'+entityCnName+'］'+'-'+rows[0][nameField]);
		$('#editForm_gpztclSczxInfo').form('load', rows[0]);
	}
}

/** 【实测中线信息明细管理】提交修改 */
function editGpztclSczxInfoAjax() {
	var isValid = $('#editForm_gpztclSczxInfo').form('validate');
	if (!isValid) return;
	$.messager.confirm('提示', '您确定要编辑［'+entityCnName + '］吗？', function(b) {
		if (b) {
			$('#editForm_gpztclSczxInfo').form('submit', {
				url : path+'/edit',
				onSubmit : function() {
				  progressLoad();
				},
				success : function(result) {
					progressClose();
					result = $.parseJSON(result);
					if (result.success) {
				        $.messager.show({title:'提示',msg:'修改操作：成功!',showType:'show' });
						dataGrid_gpztclSczxInfo.datagrid('reload');
						$("#editDialog_gpztclSczxInfo").dialog('close');
					}
					else {
						$.messager.alert('错误', result.msg, 'error');
					}
				}
			});
		}
	});
}

/** 【实测中线信息明细管理】数据删除 */
function deleteGpztclSczxInfoFun() {
	var ids = new Array();
	var rows = dataGrid_gpztclSczxInfo.datagrid('getSelections');
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
					dataGrid_gpztclSczxInfo.datagrid('reload');
					dataGrid_gpztclSczxInfo.datagrid('clearSelections');
				}
			}, 'JSON');
		}
	});
}

/** 【实测中线信息明细管理】查看数据页面 */
function showGpztclSczxInfoFun(oid,index) {
	var id;
	var row;
	if(oid){
		id = oid;
		var allrows = dataGrid_gpztclSczxInfo.datagrid('getRows');
		row = allrows[index];
	}else{
		var rows = dataGrid_gpztclSczxInfo.datagrid('getSelections');
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
		$("#showDialog_gpztclSczxInfo").dialog('open').dialog('setTitle', '查看［'+entityCnName+'］'+'-'+row[nameField]);
		$('#showDialog_gpztclSczxInfo').form('load', row);
	}
}

/**
 * 【实测中线信息明细管理】导出excel
 * @returns
 */
function expGpztclSczxInfoXls(){
	var url = path + "/expXls";
	ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, moduleName+"信息", dataGrid_gpztclSczxInfo, false);
}

/**
 * 【实测中线信息明细管理】刷新
 * @returns
 */
function reloadGpztclSczxInfo(){
	dataGrid_gpztclSczxInfo.datagrid('reload');
}

/**
 * 【实测中线信息明细管理】取消已选
 * @returns
 */
function clearGpztclSczxInfoSelections(){
	dataGrid_gpztclSczxInfo.datagrid('clearSelections');
}/** 【实测中线信息明细管理】表单查询 */
function searchGpztclSczxInfoFun() {
	dataGrid_gpztclSczxInfo.datagrid('load', $.serializeObject($('#searchForm_gpztclSczxInfo')));
}

/**【实测中线信息明细管理】表单重置 */
function cleanGpztclSczxInfoFun() {
	$('#searchForm_gpztclSczxInfo input').val('');
	dataGrid_gpztclSczxInfo.datagrid('load', {});
}
