/** /** 【安全巡检内容】工程项目安全巡检系统 编辑巡检内容
 * 创建时间：2017-05-27
   */
/** 系统模块同路径 */
var nrsyspath = "aqxj";
/** 子模块路径 */
var nrmodule = "xjdgl";
/** 菜单名称 */
var nrentityCnName = "安全巡检内容";
/** 名称字段 */
var nrnameField = "";
/** 业务数据访问全路径 */
var nrpath = basePath +"/"+ nrsyspath +"/"+ nrmodule+"/aqxjXjnr";
/**若无一条勾选数据，需屏蔽按钮ID的数组集合*/
var nrhiddenClumArrayZero = ["edit_nrbutton_aqxjXjd","del_nrbutton_aqxjXjd","clearselect_nrbutton_aqxjXjnr"];
/**若勾选大于一条数据，需屏蔽按钮ID的数组集合*/
var nrhiddenClumArrayMoreOneItem =["edit_nrbutton_aqxjXjd"];
var nrhiddenClumArrayOnlyOneItem = [];
/** 列表对象 */
var dataGrid_aqxjXjnr;
function AqxjXjnrDataGridShow() {
	/** 创建数据表格	 */
	dataGrid_aqxjXjnr = $('#dataGrid_aqxjXjnr').datagrid({
		url : nrpath+'/dataGrid',
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
           {width: '100',title: '顺序号',field: 'xuHao'},
           {width: '550',title: '巡检内容',field: 'mingCheng'}
		]],
		onLoadSuccess : function(data) {},
		toolbar : '#toolbar2',
		onLoadSuccess : function(data) {
			hiddenButtonBase("dataGrid_aqxjXjnr",nrhiddenClumArrayZero,nrhiddenClumArrayOnlyOneItem,nrhiddenClumArrayMoreOneItem);
		},
        onCheck : function(){
        	hiddenButtonBase("dataGrid_aqxjXjnr",nrhiddenClumArrayZero,nrhiddenClumArrayOnlyOneItem,nrhiddenClumArrayMoreOneItem);
        },
        onUncheck : function(){
        	hiddenButtonBase("dataGrid_aqxjXjnr",nrhiddenClumArrayZero,nrhiddenClumArrayOnlyOneItem,nrhiddenClumArrayMoreOneItem);
        },
        onCheckAll : function(){
        	hiddenButtonBase("dataGrid_aqxjXjnr",nrhiddenClumArrayZero,nrhiddenClumArrayOnlyOneItem,nrhiddenClumArrayMoreOneItem);
        },
        onUncheckAll : function(){
        	hiddenButtonBase("dataGrid_aqxjXjnr",nrhiddenClumArrayZero,nrhiddenClumArrayOnlyOneItem,nrhiddenClumArrayMoreOneItem);
        },
        onClickRow:function(){
        	hiddenButtonBase("dataGrid_aqxjXjnr",nrhiddenClumArrayZero,nrhiddenClumArrayOnlyOneItem,nrhiddenClumArrayMoreOneItem);
        }
	});
}

/** 【安全巡检内容】打开新增页面 */
function addAqxjXjnrFun() {
	FormUtil.clearForm($("#addDialog_aqxjXjnr"));
	$("#addDialog_aqxjXjnr").dialog('open').dialog('setTitle', '添加［'+nrentityCnName+"］");
	var itemID=$("#show_itemsId_aqxjXjnr").val();
	$("#add_itemsId_aqxjXjnr").val(itemID);
}

/** 【安全巡检内容】数据保存 */
function addAqxjXjnrAjax() {
	$('#addForm_aqxjXjnr').form('submit', {
		url : nrpath+'/add',
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
				dataGrid_aqxjXjnr.datagrid('reload');
				$("#addDialog_aqxjXjnr").dialog('close');
				FormUtil.clearForm($("#addDialog_aqxjXjnr"));
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}

/** 【安全巡检内容】打开数据编辑页面 */
function editAqxjXjnrFun2() {
	var id;
	var rows = dataGrid_aqxjXjnr.datagrid('getSelections');
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
		$("#editDialog_aqxjXjnr").dialog('open').dialog('setTitle', '编辑［'+nrentityCnName+'］'+'-'+rows[0][nrnameField]);
		var iteID=$("#show_itemsId_aqxjXjnr").val();
		$("#edit_itemsId_aqxjXjnr").val(iteID);
		$('#editForm_aqxjXjnr').form('load', rows[0]);
	}
}

/** 【安全巡检内容】提交修改 */
function editAqxjXjnrAjax() {
	var isValid = $('#editForm_aqxjXjnr').form('validate');
	if (!isValid) return;
	$.messager.confirm('提示', '您确定要编辑［'+nrentityCnName + '］吗？', function(b) {
		if (b) {
			$('#editForm_aqxjXjnr').form('submit', {
				url : nrpath+'/edit',
				onSubmit : function() {
				  progressLoad();
				},
				success : function(result) {
					progressClose();
					result = $.parseJSON(result);
					if (result.success) {
				        $.messager.show({title:'提示',msg:'修改操作：成功!',showType:'show' });
						dataGrid_aqxjXjnr.datagrid('reload');
						dataGrid_aqxjXjnr.datagrid('clearSelections');
						$("#editDialog_aqxjXjnr").dialog('close');
					}
					else {
						$.messager.alert('错误', result.msg, 'error');
					}
				}
			});
		}
	});
}

/** 【安全巡检内容】数据删除 */
function deleteAqxjXjnrFun() {
	var ids = new Array();
	var rows = dataGrid_aqxjXjnr.datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择数据后再删除！', 'info');
		return;
	}
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].id);
	}
	$.messager.confirm('询问', '您是否要删除［'+nrentityCnName + '］所选行吗？', function(b) {
		if (b) {
			progressLoad();
			$.post(nrpath+'/delete', {
				ids : ids.join(",")
			}, function(result) {
				progressClose();
				if (result.success) {
					$.messager.show({title:'提示',msg:'删除操作：成功!',showType:'show' });
					dataGrid_aqxjXjnr.datagrid('reload');
					dataGrid_aqxjXjnr.datagrid('clearSelections');
				}
			}, 'JSON');
		}
	});
}

/** 【安全巡检内容】查看数据页面 */
function showAqxjXjnrFun(oid,index) {
	var id;
	var row;
	if(oid){
		id = oid;
		var allrows = dataGrid_aqxjXjnr.datagrid('getRows');
		row = allrows[index];
	}else{
		var rows = dataGrid_aqxjXjnr.datagrid('getSelections');
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
		$("#showDialog_aqxjXjnr").dialog('open').dialog('setTitle', '查看［'+nrentityCnName+'］'+'-'+row[nrnameField]);
		$('#showDialog_aqxjXjnr').form('load', row);
	}
}

/**
 * 【安全巡检内容】导出excel
 * @returns
 */
function expAqxjXjnrXls(){
	var url = nrpath + "/expXls";
	ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, nrentityCnName+"信息", dataGrid_aqxjXjnr, false);
}

/**
 * 【安全巡检内容】刷新
 * @returns
 */
function reloadAqxjXjnr(){
	dataGrid_aqxjXjnr.datagrid('reload');
}

/**
 * 【安全巡检内容】取消已选
 * @returns
 */
function clearAqxjXjnrSelections(){
	dataGrid_aqxjXjnr.datagrid('clearSelections');
}/** 【安全巡检内容】表单查询 */
function searchAqxjXjnrFun() {
	dataGrid_aqxjXjnr.datagrid('load', $.serializeObject($('#searchForm_aqxjXjnr')));
}

/**【安全巡检内容】表单重置 */
function cleanAqxjXjnrFun() {
	$('#searchForm_aqxjXjnr input').val('');
	dataGrid_aqxjXjnr.datagrid('load', {});
}
