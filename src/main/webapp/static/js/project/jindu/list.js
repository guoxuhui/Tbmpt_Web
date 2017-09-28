/**
 * 工程管理系统 区间线路管理模块
 * 
 * 创建时间：2016年11月05日 
 * 
 */
/**
 * 系统模块同路径
 */
var syspath = "project";
/**
 * 子模块路径
 */
var module = "jindu";
/**
 * 模块名称
 */
var moduleName = "施工日进度";
/**
 * 名称字段
 */
var nameField = "jinduName";
/**
 * 业务数据访问全路径
 */
var path = basePath +"/"+ syspath +"/"+ module;
/**
 * 列表对象
 */

var dataGrid;
var jcinfoDG;


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
				'proName' : ''
			}); //向json数组开头添加自定义数据  

			$('#search_proId').combobox({
				data : jsonstr,
				valueField : "id",
				textField : "proName",
				editable:false,
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							
							
						}
					}
				 },
				 onSelect:function(newValue,oldValue){
			        	$('#search_sectionId').combobox("clear");
			        	$('#search_sectionId').combobox('reload', path+"/getProSectionDic?proId="+newValue.id);
			        	
			      }
				
			});

			$("#add_proId").combobox({
				
				data : jsonstr,
				valueField : "id",
				textField : "proName",
				editable:false,
				onLoadSuccess : function() {
					val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							
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
				editable:false,
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
				editable:false,
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

			$('#search_sectionId').combobox({
				data : jsonstr,
				valueField : "id",
				textField : "sectionName",
				editable:false,
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							
						}
					}
				},
				onSelect:function(newValue,oldValue){
		        	
		        	$('#search_LineId').combobox("clear");
		        	$('#search_LineId').combobox('reload', path+"/getProLineDic?sectionId="+newValue.id);
		      }
				
			});

			$("#add_sectionId").combobox({
				data : jsonstr,
				valueField : "id",
				textField : "sectionName",
				editable:false,
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				},
				onSelect:function(newValue,oldValue){
	        	
	        	$('#add_LineId').combobox("clear");
	        	$('#add_LineId').combobox('reload', path+"/getProLineDic?sectionId="+newValue.id);
	      }
				
			});

			$("#edit_sectionId").combobox({
				data : jsonstr,
				valueField : "id",
				textField : "sectionName",
				editable:false,
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				},
			onSelect:function(newValue,oldValue){
	        	
	        	$('#edit_LineId').combobox("clear");
	        	$('#edit_LineId').combobox('reload', path+"/getProLineDic?sectionId="+newValue.id);
	      }
				
			});
			
			$("#show_sectionId").combobox({
				data : jsonstr,
				valueField : "id",
				textField : "sectionName",
				editable:false,
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				},
				onSelect:function(newValue,oldValue){
	        	
	        	$('#show_LineId').combobox("clear");
	        	$('#show_LineId').combobox('reload', path+"/getProLineDic?sectionId="+newValue.id);
	      }
				
			});

		}
	});
	/**
	 *获取线路信息
	 *
	 */
	$.ajax({
		url : path+'/getProLineDic',
		dataType : 'json',
		success : function(jsonstr) {
		
			$('#search_LineId').combobox({
				data : jsonstr,
				valueField : "id",
				textField : "lineName",
				editable:false,
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							
						}
					}
				 }
			});

			$("#add_LineId").combobox({
				data : jsonstr,
				valueField : "id",
				textField : "lineName",
				editable:false,
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				}
			});

			$("#edit_LineId").combobox({
				data : jsonstr,
				valueField : "id",
				textField : "lineName",
				editable:false,
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				}
			});
			
			$("#show_LineId").combobox({
				data : jsonstr,
				valueField : "id",
				textField : "lineName",
				editable:false,
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
		iconCls:'icon-edit',
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
		columns : [ [
			{field : 'ck',checkbox : true},
            {width : '350',title : '工程名称',field : 'proName',align:'center'}, 
             
            {width : '150',title : '日期',field : 'rq',sortable : true,
            	formatter : function(value, row, index) {
                    var str = $.formatString('<a href="javascript:void(0)" onclick="show(\'{0}\',\'{1}\');" >{2}</a>',row.proId,row.rq,value);
                    return str;
                }
            }
                   
			] ],
		onLoadSuccess : function(data) {},
		toolbar: '#toolbar'
		/*toolbar : [ 
					{text : '刷新',iconCls: 'icon-reload',
					    handler: function(){
					    	dataGrid.datagrid('reload');
					    }
					},
					'-',
		            {text : "添加",iconCls : 'icon-add',handler : addFun}, 
		            '-',
		            {text : "编辑",iconCls : 'icon-edit',handler : detailsFun}, 
		            '-', 
		            {text : "删除",iconCls : 'icon-del',handler : deleteFun},
		            '-', 
		            {text: '取消已选',iconCls: 'icon-undo',
		    			handler: function(){
		    				dataGrid.datagrid('clearSelections');
		    			}
		    		},
		            '-',
		            {text : "查看",iconCls : 'icon-book_previous',
		    			handler: function(){
		            		showFun();
		    			}
		            },
		            
		    		'-',{
		    			text: '导出Excel',
		    			iconCls: 'icon-page_excel',
		    			handler: function(){
		    				var url = path + "/expXls";
		    				
		    				ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, moduleName+"信息", dataGrid, false);
		    			}
		    		},'-',{
		    			text: '导出Pdf',
		    			iconCls: 'icon-page_white_acrobat',
		    			handler: function(){
		    				var url = path + "/expPdf";
		    				
		    				ExportUtil.doExport(url, ExportUtil.TYPE_PDF, moduleName+"信息", dataGrid, false);
		    			}
		    		}
		            
		           ]*/
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
 * 更新数据表格
 * @param index
 * @returns
 */
function updateActions(index){
	$('#dataGrid').datagrid('updateRow',{
		index: index,
		row:{}
	});
}

function addFun() {
	FormUtil.clearForm($("#addDialog"));
	$("#addDialog").dialog('open').dialog('setTitle', '添加［'+moduleName+"］");

}

/**
 * 加载施工日进度窗口
 */
function detailsFun() {
    parent.$.modalDialog({
        title : '请选择施工日进度信息',
        width : 600,
        height : 400,
        href : path+'/import',
        buttons : [ {
            text : '确定',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#addForm');
                f.submit();
            }
        } ]
    });
}

/**
 * 查看页面
 * @param oid
 * @param index
 * @returns
 */
function showFun(oid,index) {
	var id;
	var rq;
	var proId;
	var row;
	if(oid){
		id = oid;
		var allrows = dataGrid.datagrid('getRows');
		row = allrows[index];
		rq=row.rq;
		proId=row.proId;
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
		parent.$.modalDialog({
	         title : '导入内容',
	         width : 900,
	         height : 500,
	         href : path+'/show?rq='+row.rq+'&proId='+row.proId,
	        
	         buttons : [{
	             text : '关闭',
	             handler : function() {
	                 parent.$.modalDialog.handler.dialog('close');
	             }
	         } ]
	     });
	}
}

//列表中点击检测日期，打开明细页面通用方法
function show(proId,rq){
	parent.$.modalDialog({
        title : '导入内容',
        width : 900,
        height : 500,
        href : path+'/show?rq='+rq+'&proId='+proId,
       
        buttons : [{
            text : '关闭',
            handler : function() {
                parent.$.modalDialog.handler.dialog('close');
            }
        } ]
    });
    //dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
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
		$("#editDialog").dialog('open').dialog('setTitle', '编辑［'+moduleName+"］"+"-"+rows[0][nameField]);
		$('#editForm').form('load', rows[0]);
	}
}

/**
 * 修改提交
 */
function editAjax() {
	$.messager.confirm('提示', '您确定要编辑［'+moduleName + '］吗？', function(b) {
		if (b) {
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

/**
 * 数据删除
 */
function deleteFun() {
	var times = new Array();
	var rows = dataGrid.datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择数据后再删除！', 'info');
		return;
	}
	for(var i=0; i<rows.length; i++){
		times.push(rows[i].rq);
	}
	$.messager.confirm('询问', '您是否要删除［'+moduleName + '］所选行吗？', function(b) {
		if (b) {
			progressLoad();
			$.post(path+'/delete', {
				times : times.join(",")
			}, function(result) {
				if (result.success) {
					$.messager.alert('提示', result.msg, 'info');
					dataGrid.datagrid('reload');
					dataGrid.datagrid('clearSelections');
				}
				progressClose();
			}, 'JSON');
		}
	});
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
	dataGrid.datagrid('load', {});
}


 



