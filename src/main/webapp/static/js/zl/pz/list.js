/**
 * 管片质量上报管理系统 质量上报管理模块
 * 
 * 创建时间：2017年04月17日 
 * 
 */

/**
 * 系统模块同路径
 */
var syspath = "zl";
/**
 * 子模块路径
 */
var module = "pz";
/**
 * 模块名称
 */
var moduleName = "管片质量上报配置";
/**
 * 名称字段
 */
var nameField = "id";
/**
 * 业务数据访问全路径
 */
var path = basePath +"/"+ syspath +"/"+ module;
/**
 * 列表对象
 */
var dataGrid;
$(function() {
	
	
	/**
	 *获取工程信息
	 *
	 */
	$.ajax({
		url : path+'/getProDic',
		dataType : 'json',
		success : function(jsonstr) {
			jsonstr.unshift({
				'id' : '-1',
				'proName' : '-请选择-'
			}); //向json数组开头添加自定义数据  

			$('#search_proId').combobox({
				data : jsonstr,
				valueField : "id",
				textField : "proName",
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				 }
			});

			$("#add_proId").combobox({
				data : jsonstr,
				valueField : "id",
				textField : "proName",
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				},
				 onSelect:function(newValue,oldValue){
			        	$('#add_sectionId').combobox("clear");
			        	$('#add_sectionId').combobox('reload', path+"/getProSectionDic?proId="+newValue.id);
			      }
			});

			$("#edit_proId").combobox({
				data : jsonstr,
				valueField : "id",
				textField : "proName",
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				},
				 onSelect:function(newValue,oldValue){
			        	$('#edit_sectionId').combobox("clear");
			        	$('#edit_sectionId').combobox('reload', path+"/getProSectionDic?proId="+newValue.id);
			      }
			});
			
			$("#show_proId").combobox({
				data : jsonstr,
				valueField : "id",
				textField : "proName",
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				}, 
				 onSelect:function(newValue,oldValue){
			        	$('#show_sectionId').combobox("clear");
			        	$('#show_sectionId').combobox('reload', path+"/getProSectionDic?proId="+newValue.id);
			      }
			});

		}
	});
	
	/**
	 *获取工程区间信息
	 *
	 */
	$.ajax({
		url : path+'/getProSectionDic',
		dataType : 'json',
		success : function(jsonstr) {
			$("#add_sectionId").combobox({
				data : jsonstr,
				valueField : "id",
				textField : "sectionName",
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				},
				 onSelect:function(newValue,oldValue){
			        	$('#add_lineId').combobox("clear");
			        	$('#add_lineId').combobox('reload', path+"/getProLineDic?sectionId="+newValue.id);
			      }
			});

			$("#edit_sectionId").combobox({
				data : jsonstr,
				valueField : "id",
				textField : "sectionName",
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				},
				 onSelect:function(newValue,oldValue){
			        	$('#edit_lineId').combobox("clear");
			        	$('#edit_lineId').combobox('reload', path+"/getProLineDic?sectionId="+newValue.id);
			      }
			});
			
			$("#show_sectionId").combobox({
				data : jsonstr,
				valueField : "id",
				textField : "sectionName",
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				},
				 onSelect:function(newValue,oldValue){
			        	$('#show_lineId').combobox("clear");
			        	$('#show_lineId').combobox('reload', path+"/getProLineDic?sectionId="+newValue.id);
			      }
			});

		}
	});
	/**
	 *获取工程线路信息
	 *
	 */
	$.ajax({
		url : path+'/getProLineDic',
		dataType : 'json',
		success : function(jsonstr) {
			$("#add_lineId").combobox({
				data : jsonstr,
				valueField : "id",
				textField : "lineName",
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				}
			});
             
			$("#edit_lineId").combobox({
				data : jsonstr,
				valueField : "id",
				textField : "lineName",
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				}
			});
			 
			$("#show_lineId").combobox({
				data : jsonstr,
				valueField : "id",
				textField : "lineName",
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
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
		frozenColumns : [ [
			{field : 'ck',checkbox : true},
			{width : '260',title : '项目部名称',field : 'proName1',sortable : true, 
				formatter: function(value,row,index){
					return '<a href="#" onclick="showFun(\''+ row.id +'\', \'' + index + '\')">' + value + '</a>';
				} 
			},
			{width : '320',title : '区间',field : 'sectionName'}, 
			{title : '线路',field : 'lineName'},  
			{title : '设置上报时间(天)',field : 'setUpDay'} 
			]], 
		columns : [ [
			
			
			] ],
			
		onLoadSuccess : function(data) {},
		toolbar: '#toolbar' 
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


/**
 * 数据添加
 */
function addFun() {
	FormUtil.clearForm($("#addDialog"));
	$("#addDialog").dialog('open').dialog('setTitle', '添加［'+moduleName+"］");
	/*$("#add_proId").combobox({
		url : path+'/getProDic',
		valueField : "id",
		textField : "proName",
		onLoadSuccess : function() {
			
			var val = $(this).combobox('getData');
			for (var item in val[0]) {
				if (item == 'id') {
					$(this).combobox('select', val[0][item]);
				}
			}
		}
	});*/

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
				$.messager.show({title:'提示',msg:result.msg,showType:'show' });
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
		$("#editDialog").dialog('open').dialog('setTitle', '编辑［'+moduleName+"］");
		$('#editForm').form('load', rows[0]);
		   
		/*
		 $("#edit_proId").combobox({
			valueField : "id", 
			textField : "proName",
			onLoadSuccess : function() {
				var val = $(this).combobox('getData');
				for (var item in val[0]) {
					if (item == 'id') {
						$(this).combobox('select', rows[0].proId);
					}
				}
			}
		});
		$("#edit_sectionId").combobox({
			valueField : "id",
			textField : "sectionName",
			onLoadSuccess : function() {
				var val = $(this).combobox('getData');
				for (var item in val[0]) {
					if (item == 'id') {
						$(this).combobox('select', val[0][item]);
					}
				}
			},
			 onSelect:function(newValue,oldValue){
		        	$('#edit_lineId').combobox("clear");
		        	$('#edit_lineId').combobox('reload', path+"/getProLineDic?sectionId="+newValue.id);
		      }
		});
		$("#edit_lineId").combobox({
			valueField : "id",
			textField : "lineName",
			onLoadSuccess : function() {
				var val = $(this).combobox('getData');
				for (var item in val[0]) {
					if (item == 'id') {
						$(this).combobox('select', val[0][item]);
					}
				}
			}
		});
		*/ 
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
				$.messager.show({title:'提示',msg:result.msg,showType:'show' });
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
					$.messager.show({title:'提示',msg:result.msg,showType:'show' });
					dataGrid.datagrid('reload');
					dataGrid.datagrid('clearSelections');
				}else{
					$.messager.alert('提示',result.msg, 'info');
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
		$("#showDialog").dialog('open').dialog('setTitle', '查看［'+moduleName+"］");
		$("#show_proId").combobox({
			url : path+'/getProDic',
			valueField : "id",
			textField : "proName",
			onLoadSuccess : function() {
				var val = $(this).combobox('getData');
				for (var item in val[0]) {
					if (item == 'id') {
						$(this).combobox('select', val[0][item]);
					}
				}
			}
		});
		$('#showForm').form('load', row);
		//disableForm('showForm',true);
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
	$('#searchForm').form('clear');
	dataGrid.datagrid('load', {});
}