/** /** 【公共统一附件】系统管理 公共模块
 * 创建时间：2017-02-17
   */
/** 系统模块同路径 */
var syspath = "sys";
/** 子模块路径 */
var module = "base";
/** 菜单名称 */
var entityCnName = "公共统一附件";
/** 名称字段 */
var nameField = "";
/** 业务数据访问全路径 */
var path = basePath +"/"+ syspath +"/"+ module+"/sysFujian";
/** 列表对象 */
var dataGrid_sysFujian;
$(function() {
	/** 创建数据表格	 */
	dataGrid_sysFujian = $('#dataGrid_sysFujian').datagrid({
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
           {width: '80',title: '文件名称',field: 'fileName'},
           {width: '80',title: '文件路径',field: 'filePath'},
           {width: '80',title: '缩略图路径',field: 'minImgPath'},
           {width: '80',title: '文件类型',field: 'fileType'},
           {width: '80',title: '文件大小',field: 'fileSize'},
           {width: '80',title: '模块ID',field: 'resId'},
           {width: '80',title: '上传时间',field: 'createTime'},
           {width: '80',title: '上传人员',field: 'createUsername'},
           {width: '80',title: '上传单位名称',field: 'createOrgzname'},
           {width: '80',title: '引用表ID',field: 'foreignId'},
           {width: '80',title: '顺序号',field: 'orderno'},
           {width: '80',title: '备用字段1',field: 'backupOne'},
           {width: '80',title: '备用字段2',field: 'backupTwo'}
		]],
		onLoadSuccess : function(data) {},
		toolbar : '#toolbar' 
	});
});

/** 【公共统一附件】打开新增页面 */
function addSysFujianFun() {
	FormUtil.clearForm($("#addDialog_sysFujian"));
	$("#addDialog_sysFujian").dialog('open').dialog('setTitle', '添加［'+entityCnName+"］");
}

/** 【公共统一附件】数据保存 */
function addSysFujianAjax() {
	$('#addForm_sysFujian').form('submit', {
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
				dataGrid_sysFujian.datagrid('reload');
				$("#addDialog_sysFujian").dialog('close');
				FormUtil.clearForm($("#addDialog_sysFujian"));
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}

/** 【公共统一附件】打开数据编辑页面 */
function editSysFujianFun() {
	var id;
	var rows = dataGrid_sysFujian.datagrid('getSelections');
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
		$("#editDialog_sysFujian").dialog('open').dialog('setTitle', '编辑［'+entityCnName+'］'+'-'+rows[0][nameField]);
		$('#editForm_sysFujian').form('load', rows[0]);
	}
}

/** 【公共统一附件】提交修改 */
function editSysFujianAjax() {
	var isValid = $('#editForm_sysFujian').form('validate');
	if (!isValid) return;
	$.messager.confirm('提示', '您确定要编辑［'+entityCnName + '］吗？', function(b) {
		if (b) {
			$('#editForm_sysFujian').form('submit', {
				url : path+'/edit',
				onSubmit : function() {
				  progressLoad();
				},
				success : function(result) {
					progressClose();
					result = $.parseJSON(result);
					if (result.success) {
				        $.messager.show({title:'提示',msg:'修改操作：成功!',showType:'show' });
						dataGrid_sysFujian.datagrid('reload');
						$("#editDialog_sysFujian").dialog('close');
					}
					else {
						$.messager.alert('错误', result.msg, 'error');
					}
				}
			});
		}
	});
}

/** 【公共统一附件】数据删除 */
function deleteSysFujianFun() {
	var ids = new Array();
	var rows = dataGrid_sysFujian.datagrid('getSelections');
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
					dataGrid_sysFujian.datagrid('reload');
					dataGrid_sysFujian.datagrid('clearSelections');
				}
			}, 'JSON');
		}
	});
}

/** 【公共统一附件】查看数据页面 */
function showSysFujianFun(oid,index) {
	var id;
	var row;
	if(oid){
		id = oid;
		var allrows = dataGrid_sysFujian.datagrid('getRows');
		row = allrows[index];
	}else{
		var rows = dataGrid_sysFujian.datagrid('getSelections');
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
		$("#showDialog_sysFujian").dialog('open').dialog('setTitle', '查看［'+entityCnName+'］'+'-'+row[nameField]);
		$('#showDialog_sysFujian').form('load', row);
	}
}

/**
 * 【公共统一附件】导出excel
 * @returns
 */
function expSysFujianXls(){
	var url = path + "/expXls";
	ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, moduleName+"信息", dataGrid_sysFujian, false);
}

/**
 * 【公共统一附件】刷新
 * @returns
 */
function reloadSysFujian(){
	dataGrid_sysFujian.datagrid('reload');
}

/**
 * 【公共统一附件】取消已选
 * @returns
 */
function clearSysFujianSelections(){
	dataGrid_sysFujian.datagrid('clearSelections');
}/** 【公共统一附件】表单查询 */
function searchSysFujianFun() {
	dataGrid_sysFujian.datagrid('load', $.serializeObject($('#searchForm_sysFujian')));
}

/**【公共统一附件】表单重置 */
function cleanSysFujianFun() {
	$('#searchForm_sysFujian input').val('');
	dataGrid_sysFujian.datagrid('load', {});
}
