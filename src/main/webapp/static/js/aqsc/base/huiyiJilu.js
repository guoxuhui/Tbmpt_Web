/** /** 【培训记录】安全生产管理 基础信息
 * 创建时间：2017-02-21
   */
/** 系统模块同路径 */
var syspath = "aqsc";
/** 子模块路径 */
var module = "base";
/** 菜单名称 */
var entityCnName = "培训记录";
/** 名称字段 */
var nameField = "";
/** 业务数据访问全路径 */
var path = basePath +"/"+ syspath +"/"+ module+"/huiyiJilu";
/** 列表对象 */
var dataGrid_huiyiJilu;
/**若无一条勾选数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayZero = ["button_edit_huiyiJilu","button_del_huiyiJilu","button_quxiao_huiyiJilu"];
/**若只勾选一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayOnlyOneItem = [];
/**若勾选大于一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayMoreOneItem =["button_edit_huiyiJilu"];
$(function() {
	/** 创建数据表格	 */
	dataGrid_huiyiJilu = $('#dataGrid_huiyiJilu').datagrid({
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
           //{width: '300',title: '项目部',field: 'xmName'},
           {width: '80',title: '培训日期',field: 'hydate',sortable : true},
           {width: '180',title: '培训名称',field: 'name',sortable : true,
       		formatter: function(value,row,index){
   				return '<a href="#" onclick="showHuiyiJiluFun(\''+ row.id +'\',\''+index+'\')">' + value + '</a>';
   			  }
    	   },
           {width: '180',title: '培训地点',field: 'adress',sortable : true},
           {width: '180',title: '培训摘要',field: 'content',
				formatter: function(value,row,index){
					return DataGridFormatter.tipsFormatter(value,20);
				}
		   },
           {width: '80',title: '培训主持人',field: 'zhuchiren'},
           {width: '180',title: '培训参加人',field: 'chuxiren',
				formatter: function(value,row,index){
					return DataGridFormatter.tipsFormatter(value,20);
				}
		   },
//           {width: '80',title: '培训记录人',field: 'jiluren'}
		]],
		onLoadSuccess : function(data) {},
		toolbar : '#toolbar' ,
		onLoadSuccess : function(data) {
			hiddenButtonBase("dataGrid_huiyiJilu",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
		},
        onCheck : function(){
        	hiddenButtonBase("dataGrid_huiyiJilu",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onUncheck : function(){
        	hiddenButtonBase("dataGrid_huiyiJilu",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onCheckAll : function(){
        	hiddenButtonBase("dataGrid_huiyiJilu",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onUncheckAll : function(){
        	hiddenButtonBase("dataGrid_huiyiJilu",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onClickRow:function(){
        	hiddenButtonBase("dataGrid_huiyiJilu",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        }
	});
});

/** 【培训记录】打开新增页面 */
function addHuiyiJiluFun() {
	FormUtil.clearForm($("#addDialog_huiyiJilu"));
	$("#addDialog_huiyiJilu").dialog('open').dialog('setTitle', '添加［'+entityCnName+"］");
}

/** 【培训记录】数据保存 */
function addHuiyiJiluAjax() {
	$('#addForm_huiyiJilu').form('submit', {
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
				dataGrid_huiyiJilu.datagrid('reload');
				$("#addDialog_huiyiJilu").dialog('close');
				FormUtil.clearForm($("#addDialog_huiyiJilu"));
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}

/** 【培训记录】打开数据编辑页面 */
function editHuiyiJiluFun() {
	var id;
	var rows = dataGrid_huiyiJilu.datagrid('getSelections');
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
		$("#editDialog_huiyiJilu").dialog('open').dialog('setTitle', '编辑［'+entityCnName+'］'+(nameField?'-'+rows[0][nameField]:""));
		$('#editForm_huiyiJilu').form('load', rows[0]);
	}
}

/** 【培训记录】提交修改 */
function editHuiyiJiluAjax() {
	var isValid = $('#editForm_huiyiJilu').form('validate');
	if (!isValid) return;
	$.messager.confirm('提示', '您确定要编辑［'+entityCnName + '］吗？', function(b) {
		if (b) {
			$('#editForm_huiyiJilu').form('submit', {
				url : path+'/edit',
				onSubmit : function() {
				  progressLoad();
				},
				success : function(result) {
					progressClose();
					result = $.parseJSON(result);
					if (result.success) {
				        $.messager.show({title:'提示',msg:'修改操作：成功!',showType:'show' });
						dataGrid_huiyiJilu.datagrid('reload');
						$("#editDialog_huiyiJilu").dialog('close');
					}
					else {
						$.messager.alert('错误', result.msg, 'error');
					}
				}
			});
		}
	});
}

/** 【培训记录】数据删除 */
function deleteHuiyiJiluFun() {
	var ids = new Array();
	var rows = dataGrid_huiyiJilu.datagrid('getSelections');
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
					dataGrid_huiyiJilu.datagrid('reload');
					dataGrid_huiyiJilu.datagrid('clearSelections');
				}
			}, 'JSON');
		}
	});
}

/** 【培训记录】查看数据页面 */
function showHuiyiJiluFun(oid,index) {
	var id;
	var row;
	if(oid){
		id = oid;
		var allrows = dataGrid_huiyiJilu.datagrid('getRows');
		row = allrows[index];
	}else{
		var rows = dataGrid_huiyiJilu.datagrid('getSelections');
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
		$("#showDialog_huiyiJilu").dialog('open').dialog('setTitle', '查看［'+entityCnName+'］'+(nameField?'-'+rows[0][nameField]:""));
		$('#showDialog_huiyiJilu').form('load', row);
		_showAllFileInDiv(row.id,'img_div_huiyiJilu')
	}
}

/**
 * 【培训记录】导出excel
 * @returns
 */
function expHuiyiJiluXls(){
	var url = path + "/expXls";
	ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, entityCnName+"信息", dataGrid_huiyiJilu, false);
}

/**
 * 【培训记录】刷新
 * @returns
 */
function reloadHuiyiJilu(){
	dataGrid_huiyiJilu.datagrid('reload');
}

/**
 * 【培训记录】取消已选
 * @returns
 */
function clearHuiyiJiluSelections(){
	dataGrid_huiyiJilu.datagrid('clearSelections');
}/** 【培训记录】表单查询 */
function searchHuiyiJiluFun() {
	dataGrid_huiyiJilu.datagrid('load', $.serializeObject($('#searchForm_huiyiJilu')));
}

/**【培训记录】表单重置 */
function cleanHuiyiJiluFun() {
	$('#searchForm_huiyiJilu input').val('');
	dataGrid_huiyiJilu.datagrid('load', {});
}

/**
 * 上传图片
 * @returns
 */
function myUploadPicture(){
	var rows = dataGrid_huiyiJilu.datagrid('getSelections');
	if (rows.length > 1) {
		$.messager.alert('提示', '请选择单条数据进行操作！', 'info');
		return;
	}else if (rows.length == 0) {
		$.messager.alert('提示', '请选择数据后再操作！', 'info');
		return;
	}
	_uploadPicture(rows[0].id,syspath,'',dataGrid_huiyiJilu,entityCnName);
}
