/** /** 【工作日志】安全生产管理 基础信息
 * 创建时间：2017-02-20
   */
/** 系统模块同路径 */
var syspath = "aqsc";
/** 子模块路径 */
var module = "base";
/** 菜单名称 */
var entityCnName = "工作日志";
/** 名称字段 */
var nameField = "";
/** 业务数据访问全路径 */
var path = basePath +"/"+ syspath +"/"+ module+"/worklog";
/** 列表对象 */
var dataGrid_worklog;

/**若无一条勾选数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayZero = ["button_edit_worklog","button_del_worklog","button_quxiao_worklog"];
/**若只勾选一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayOnlyOneItem = [];
/**若勾选大于一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayMoreOneItem =["button_edit_worklog"];

$(function() {
	/** 创建数据表格	 */
	dataGrid_worklog = $('#dataGrid_worklog').datagrid({
		url : path+'/dataGrid',
		fitColumns:true,
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
           {width: '200',title: '单位名称',field: 'createOrgzname',
				formatter: function(value,row,index){
					return DataGridFormatter.tipsFormatter(value,20);
				}
		   },
           {width: '120',title: '人员姓名',field: 'createUsername'},
           {width: '100',title: '工作日期',field: 'workDay'},
           {width: '250',title: '工作内容',field: 'content',
				formatter: function(value,row,index){
					return DataGridFormatter.tipsFormatter(value,80);
				}
		   }
		]],
		onLoadSuccess : function(data) {},
		toolbar : '#toolbar' ,
		onLoadSuccess : function(data) {
			hiddenButtonBase("dataGrid_worklog",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
		},
        onCheck : function(){
        	hiddenButtonBase("dataGrid_worklog",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onUncheck : function(){
        	hiddenButtonBase("dataGrid_worklog",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onCheckAll : function(){
        	hiddenButtonBase("dataGrid_worklog",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onUncheckAll : function(){
        	hiddenButtonBase("dataGrid_worklog",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onClickRow:function(){
        	hiddenButtonBase("dataGrid_worklog",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        }
	});
});

/** 【工作日志】打开新增页面 */
function addWorklogFun() {
	FormUtil.clearForm($("#addDialog_worklog"));
	$("#addDialog_worklog").dialog('open').dialog('setTitle', '添加［'+entityCnName+"］");
}

/** 【工作日志】数据保存 */
function addWorklogAjax() {
	$('#addForm_worklog').form('submit', {
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
				dataGrid_worklog.datagrid('reload');
				$("#addDialog_worklog").dialog('close');
				FormUtil.clearForm($("#addDialog_worklog"));
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}

/** 【工作日志】打开数据编辑页面 */
function editWorklogFun() {
	var id;
	var rows = dataGrid_worklog.datagrid('getSelections');
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
		$("#editDialog_worklog").dialog('open').dialog('setTitle', '编辑［'+entityCnName+'］'+'-'+rows[0][nameField]);
		$('#editForm_worklog').form('load', rows[0]);
	}
}

/** 【工作日志】提交修改 */
function editWorklogAjax() {
	var isValid = $('#editForm_worklog').form('validate');
	if (!isValid) return;
	$.messager.confirm('提示', '您确定要编辑［'+entityCnName + '］吗？', function(b) {
		if (b) {
			$('#editForm_worklog').form('submit', {
				url : path+'/edit',
				onSubmit : function() {
				  progressLoad();
				},
				success : function(result) {
					progressClose();
					result = $.parseJSON(result);
					if (result.success) {
				        $.messager.show({title:'提示',msg:'修改操作：成功!',showType:'show' });
						dataGrid_worklog.datagrid('reload');
						$("#editDialog_worklog").dialog('close');
					}
					else {
						$.messager.alert('错误', result.msg, 'error');
					}
				}
			});
		}
	});
}

/** 【工作日志】数据删除 */
function deleteWorklogFun() {
	var ids = new Array();
	var rows = dataGrid_worklog.datagrid('getSelections');
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
					dataGrid_worklog.datagrid('reload');
					dataGrid_worklog.datagrid('clearSelections');
				}
			}, 'JSON');
		}
	});
}

/** 【工作日志】查看数据页面 */
function showWorklogFun(oid,index) {
	var id;
	var row;
	if(oid){
		id = oid;
		var allrows = dataGrid_worklog.datagrid('getRows');
		row = allrows[index];
	}else{
		var rows = dataGrid_worklog.datagrid('getSelections');
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
		$("#showDialog_worklog").dialog('open').dialog('setTitle', '查看［'+entityCnName+'］'+'-'+row[nameField]);
		$('#showDialog_worklog').form('load', row);
	}
}

/**
 * 【工作日志】导出excel
 * @returns
 */
function expWorklogXls(){
	var url = path + "/expXls";
	ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, entityCnName+"信息", dataGrid_worklog, false);
}

/**
 * 【工作日志】刷新
 * @returns
 */
function reloadWorklog(){
	dataGrid_worklog.datagrid('reload');
}

/**
 * 【工作日志】取消已选
 * @returns
 */
function clearWorklogSelections(){
	dataGrid_worklog.datagrid('clearSelections');
}/** 【工作日志】表单查询 */
function searchWorklogFun() {
	dataGrid_worklog.datagrid('load', $.serializeObject($('#searchForm_worklog')));
}

/**【工作日志】表单重置 */
function cleanWorklogFun() {
	$('#searchForm_worklog input').val('');
	dataGrid_worklog.datagrid('load', {});
}
