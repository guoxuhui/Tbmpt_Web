/** 【盾构施工质量基础数据】 基础模块
 * 创建时间：2016-11-19
  */

/** 系统模块同路径 */
var syspath = "gczl";

/** 子模块路径 */
var module = "base";

/** 菜单名称 */
var entityCnName = "盾构施工质量基础数据";

/** 名称字段 */
var nameField = "ddName";

/** 业务数据访问全路径 */
var path = basePath +"/"+ syspath +"/"+ module+"/gczlYdxjGPZLDDInfo";

/**若无一条勾选数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayZero = ["query_button_edit","query_button_del","query_button_unselect","query_button_qiYong","query_button_jinYong"];
/**若只勾选一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayOnlyOneItem = [];
/**若勾选大于一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayMoreOneItem =["query_button_edit"];

/** 列表对象 */
var dataGrid;
$(function() {
	loadTypDic("typeName_add_id");
	loadTypDic("typeName_edit_id");
	loadTypDic("typeName_query_id");
	/** 创建数据表格	 */
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
           {field:'id',align:'center',title:'ID',width:40, checkbox:true},
           {width: '180',title: '分类名称',field: 'typeName'},
           {width: '180',title: '基础数据名称',field: 'ddName',
			  formatter: function(value,row,index){
				return '<a href="#" onclick="showFun(\''+ row.id +'\',\''+index+'\')">' + value + '</a>';
			  }
		   },
           {width: '80',title: '使用状态',field: 'ifQy'},
           {width: '80',title: '排序号',field: 'sortNum'},
           {width: '280',title: '备注',field: 'remarks',
				formatter: function(value,row,index){
					return DataGridFormatter.tipsFormatter(value,20);
				}
			}
		]],
		toolbar : '#toolbar',
		onLoadSuccess : function(data) {
			hiddenButtonBase("dataGrid",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
		},
        onCheck : function(){
        	hiddenButtonBase("dataGrid",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onUncheck : function(){
        	hiddenButtonBase("dataGrid",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onCheckAll : function(){
        	hiddenButtonBase("dataGrid",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onUncheckAll : function(){
        	hiddenButtonBase("dataGrid",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onClickRow:function(){
        	hiddenButtonBase("dataGrid",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        }
	});
});

/** 数据添加 */
function addFun() {
	FormUtil.clearForm($("#addDialog"));
	$("#addDialog").dialog('open').dialog('setTitle', '添加［'+entityCnName+"］");
}

/** 数据保存 */
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
				FormUtil.clearForm($("#addDialog"));
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}

/** 数据编辑 */
function editFun() {
	var id;
	var rows = dataGrid.datagrid('getSelections');
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
		$("#editDialog").dialog('open').dialog('setTitle', '编辑［'+entityCnName+"］"+"-"+rows[0][nameField]); 
		$('#editForm').form('load', rows[0]);
	}
}

/** 修改提交 */
function editAjax() {
	var isValid = $('#editForm').form('validate');
	if (!isValid) return;
	$.messager.confirm('提示', '您确定要编辑［'+entityCnName + '］吗？', function(b) {
		if (b) {
			$('#editForm').form('submit', {
				url : path+'/edit',
				onSubmit : function() {
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
	});
}


/** 数据删除 */
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
	$.messager.confirm('询问', '您是否要删除［'+entityCnName + '］所选行吗？', function(b) {
		if (b) {
			progressLoad();
			$.post(path+'/delete', {
				ids : ids.join(",")
			}, function(result) {
				if (result.success) {
					$.messager.show({title:'提示',msg:result.msg,showType:'show' });
					dataGrid.datagrid('reload');
					dataGrid.datagrid('clearSelections');
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
	ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, entityCnName+"信息", dataGrid, false);
}

/**
 * 刷新
 * @returns
 */
function reload(){
	dataGrid.datagrid('reload');
}

/**
 * 取消已选
 * @returns
 */
function clearSelections(){
	dataGrid.datagrid('clearSelections');
}

function ifQyQ(){ifQy("1");}
function ifQyJ(){ifQy("0");}

/**
 * 启用、禁用
 * @param state 1、启用 2、禁用
 * @returns 
 */
function ifQy(state) {
	var ids = new Array();
	var rows = dataGrid.datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请先选择要操作的数据！', 'info');
		return;
	}
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].id);
	}
	$.messager.confirm('询问', '您是否要处理［'+entityCnName + '］所选行吗？', function(b) {
		if (b) {
			progressLoad();
			$.post(path+'/ifQy', {
				ids : ids.join(","),
				state:state
			}, function(result) {
				if (result.success) {
					$.messager.show({title:'提示',msg:result.msg,showType:'show' });
					dataGrid.datagrid('reload');
					dataGrid.datagrid('clearSelections');
				}
				progressClose();
			}, 'JSON');
		}
	});
}



/** 查看数据页面 */
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
		$("#showDialog").dialog('open').dialog('setTitle', '查看［'+entityCnName+"］"+"-"+row[nameField]);
	    $('#show_orgzId').combotree({
	    	url : basePath+'/sys/orgz/tree',
	        parentField : 'pid',
	        lines : true,
	        panelHeight : 'auto'
	    });
		$('#showDialog').form('load', row);
	}
}


//加载管片分类列表
function loadTypDic(id){
	$('#'+id).combotree({
		url :basePath+'/gczl/gpzlType/gpDdDic',
	    multiple: false,
	    required: true,
	    panelWidth:200,
	    panelHeight : 'auto'
	});
}

/** 表单查询 */
function searchFun() {
	dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
}

/**表单重置 */
function cleanFun() {
	$('#searchForm input').val('');
	dataGrid.datagrid('load', {});
}
