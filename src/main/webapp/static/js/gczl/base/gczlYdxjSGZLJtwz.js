/** /** 【结构施工质量基础数据-具体位置】 基础模块
 * 创建时间：2016-11-24
   */
/** 系统模块同路径 */
var syspath = "gczl";
/** 子模块路径 */
var module = "base";
/** 菜单名称 */
var entityCnName = "结构施工质量基础数据-具体位置";
/** 名称字段 */
var nameField = "";
/** 业务数据访问全路径 */
var path = basePath +"/"+ syspath +"/"+ module+"/gczlYdxjSGZLJtwz";
/** 列表对象 */
var dataGrid_gczlYdxjSGZLJtwz;
$(function() {
	/** 创建数据表格	 */
	dataGrid_gczlYdxjSGZLJtwz = $('#dataGrid_gczlYdxjSGZLJtwz').datagrid({
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
           {field:'id',align:'center',title:'ID',width:40, checkbox:true},
           {width: '80',title: '具体位置名称',field: 'jtWz'},
           {width: '80',title: '排序号',field: 'sortNum'},
           {width: '80',title: '使用状态',field: 'ifQy'},
           {width: '80',title: '备注',field: 'remarks'},
           {width: '80',title: '施工内容',field: 'sgNr'}
		]],
		onLoadSuccess : function(data) {},
		toolbar : [ 
					{text : '刷新',iconCls: 'icon-reload',
					    handler: function(){
					    	dataGrid_gczlYdxjSGZLJtwz.datagrid('reload');
					    }
					},'-',
		            {text : "添加",iconCls : 'icon-add',handler : addGczlYdxjSGZLJtwzFun},'-',
		            {text : "编辑",iconCls : 'icon-edit',handler : editGczlYdxjSGZLJtwzFun},	'-', 
		            {text : "删除",iconCls : 'icon-del',handler : deleteGczlYdxjSGZLJtwzFun},'-', 
		            {text: '取消已选',iconCls: 'icon-undo',
		    			handler: function(){
		    				dataGrid_gczlYdxjSGZLJtwz.datagrid('clearSelections');
		    			}
		    		},
		            '-',
		    		{
		    			text: '导出Excel',
		    			iconCls: 'icon-page_excel',
		    			handler: function(){
		    				var url = path + "/expXls";
		    				ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, entityCnName+"信息", dataGrid_gczlYdxjSGZLJtwz, false);
		    			}
		    		},'-',{
		    			text: '导出Pdf',
		    			iconCls: 'icon-page_white_acrobat',
		    			handler: function(){
		    				var url = path + "/expPdf";
		    				ExportUtil.doExport(url, ExportUtil.TYPE_PDF, entityCnName+"信息", dataGrid_gczlYdxjSGZLJtwz, false);
		    			}
		    		},'-',
		           ]
	});
});

/** 【结构施工质量基础数据-具体位置】打开新增页面 */
function addGczlYdxjSGZLJtwzFun() {
	FormUtil.clearForm($("#addDialog_gczlYdxjSGZLJtwz"));
	$("#addDialog_gczlYdxjSGZLJtwz").dialog('open').dialog('setTitle', '添加［'+entityCnName+"］");
}

/** 【结构施工质量基础数据-具体位置】数据保存 */
function addGczlYdxjSGZLJtwzAjax() {
	$('#addForm_gczlYdxjSGZLJtwz').form('submit', {
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
				dataGrid_gczlYdxjSGZLJtwz.datagrid('reload');
				$("#addDialog_gczlYdxjSGZLJtwz").dialog('close');
				FormUtil.clearForm($("#addDialog_gczlYdxjSGZLJtwz"));
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}

/** 【结构施工质量基础数据-具体位置】打开数据编辑页面 */
function editGczlYdxjSGZLJtwzFun() {
	var id;
	var rows = dataGrid_gczlYdxjSGZLJtwz.datagrid('getSelections');
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
		$("#editDialog_gczlYdxjSGZLJtwz").dialog('open').dialog('setTitle', '编辑［'+entityCnName+'］'+'-'+rows[0][nameField]);
		$('#editForm_gczlYdxjSGZLJtwz').form('load', rows[0]);
	}
}

/** 【结构施工质量基础数据-具体位置】提交修改 */
function editGczlYdxjSGZLJtwzAjax() {
	var isValid = $('#editForm_gczlYdxjSGZLJtwz').form('validate');
	if (!isValid) return;
	$.messager.confirm('提示', '您确定要编辑［'+entityCnName + '］吗？', function(b) {
		if (b) {
			$('#editForm_gczlYdxjSGZLJtwz').form('submit', {
				url : path+'/edit',
				onSubmit : function() {
				},
				success : function(result) {
					progressClose();
					result = $.parseJSON(result);
					if (result.success) {
				$.messager.show({title:'提示',msg:'修改操作：成功!',showType:'show' });
						dataGrid_gczlYdxjSGZLJtwz.datagrid('reload');
						$("#editDialog_gczlYdxjSGZLJtwz").dialog('close');
					}
					else {
						$.messager.alert('错误', result.msg, 'error');
					}
				}
			});
		}
	});
}

/** 【结构施工质量基础数据-具体位置】数据删除 */
function deleteGczlYdxjSGZLJtwzFun() {
	var ids = new Array();
	var rows = dataGrid_gczlYdxjSGZLJtwz.datagrid('getSelections');
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
				if (result.success) {
					$.messager.show({title:'提示',msg:'删除操作：成功!',showType:'show' });
					dataGrid_gczlYdxjSGZLJtwz.datagrid('reload');
					dataGrid_gczlYdxjSGZLJtwz.datagrid('clearSelections');
				}
				progressClose();
			}, 'JSON');
		}
	});
}

/** 【结构施工质量基础数据-具体位置】查看数据页面 */
function showGczlYdxjSGZLJtwzFun(oid,index) {
	var id;
	var row;
	if(oid){
		id = oid;
		var allrows = dataGrid_gczlYdxjSGZLJtwz.datagrid('getRows');
		row = allrows[index];
	}else{
		var rows = dataGrid_gczlYdxjSGZLJtwz.datagrid('getSelections');
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
		$("#showDialog_gczlYdxjSGZLJtwz").dialog('open').dialog('setTitle', '查看［'+entityCnName+'］'+'-'+row[nameField]);
		$('#showDialog_gczlYdxjSGZLJtwz').form('load', row);
	}
}

/** 【结构施工质量基础数据-具体位置】表单查询 */
function searchGczlYdxjSGZLJtwzFun() {
	dataGrid_gczlYdxjSGZLJtwz.datagrid('load', $.serializeObject($('#searchForm_gczlYdxjSGZLJtwz')));
}

/**【结构施工质量基础数据-具体位置】表单重置 */
function cleanGczlYdxjSGZLJtwzFun() {
	$('#searchForm_gczlYdxjSGZLJtwz input').val('');
	dataGrid_gczlYdxjSGZLJtwz.datagrid('load', {});
}
