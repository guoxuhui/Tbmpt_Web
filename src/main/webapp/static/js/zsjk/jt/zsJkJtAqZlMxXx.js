
/**
 * 系统模块同路径
 */
var syspath = "zsjk";
/**
 * 子模块路径
 */
var module = "zsJkJtAqZlMxXx";
/**
 * 模块名称
 */
var moduleName = "安全质量明细信息";

/**
 * 业务数据访问全路径
 */
var path = basePath +"/"+ syspath +"/jt/"+ module;

var proPath = basePath +"/project/line";
/**
 * 列表对象
 */
var dataGrid;
var dataGrid_emp;
$(function() {
	$('#searchForm').form('clear');
	/**
	 *获取上报单位信息
	 *
	 */
	$.ajax({
		url : basePath+'/zsjk/jt/zsJkJtXmxx/getXMXXList',
		dataType : 'json',
		success : function(jsonstr) {
			jsonstr.unshift({
				
				'xmMc' : '-请选择-'
			}); //向json数组开头添加自定义数据  

			$('#search_proId').combobox({
				data : jsonstr,
				valueField : "xmMc",
				textField : "xmMc",
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				 },
				 onSelect:function(newValue,oldValue){
			        	
			      }
			});

			$("#add_proId").combobox({
				data : jsonstr,
				valueField : "xmMc",
				textField : "xmMc",
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				},
				 onSelect:function(newValue,oldValue){
			        	
			      }
			});
			
			$("#edit_proId").combobox({
				data : jsonstr,
				valueField : "xmMc",
				textField : "xmMc",
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				},
				 onSelect:function(newValue,oldValue){
			        	
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
		rownumbers : false,
		singleSelect : false,
		checkOnSelect : true,
		selectOnCheck : true,
		pagination : true,
		idField : 'id',
		sortName : 'id',
		sortOrder : 'desc',
		pageSize : 15,
		pageList : [ 10, 15, 20, 50 ],
		columns:[[
		                
			{field : 'ck',checkbox : true},
			{width : '150',title : '上报日期',field : 'sbrq',sortable : true,
				formatter: function(value,row,index){
                    return '<a href="#" onclick="showFun(\'1' +'\',\''+ index +'\')">' + value + '</a>';
            }
			}, 			
			{width : '150',title : '上报单位',field : 'sbdw'},
			{width : '150',title : '信息分类',field : 'xxfl'}, 
			{width : '150',title : '详细信息',field : 'xxxx'}, 
			{width : '150',title : '上报人',field : 'sbr'}         
		     ]],
		
		onLoadSuccess : function() {},
		toolbar :'#toolbar'
	});
	
});

function addFun() {
	FormUtil.clearForm($("#addDialog"));
	$("#addDialog").dialog('open').dialog('setTitle', '添加［'+moduleName+"］");

}

function editFun() {
	var id;
	var rows = $('#dataGrid').datagrid('getSelections');
	if (rows.length > 1) {
		$.messager.alert('提示', '请选择单条数据进行编辑！', 'info');
		return;
	}
	
	else if(rows.length==0){
		$.messager.alert('提示', '请选择数据后再编辑！', 'info');
		return;
	}
	if (rows[0]) {
		$("#editDialog").dialog('open').dialog('setTitle', '编辑［'+moduleName+"］");
		$('#editForm').form('load', rows[0]);
	}

}

function impFun(){
	FormUtil.clearForm($("#impDialog"));
	$("#impDialog").dialog('open').dialog('setTitle', '添加［'+moduleName+"］");
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
				$('#dataGrid').datagrid('reload');
				$("#addDialog").dialog('close');
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
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
		$('#showDialog').form('load', row);
		_showAllFileInDiv(row.id,'img_div_aqZlMxXx')
	}
}


/**
 * 数据删除
 */
function deleteFun() {
	var ids = new Array();
	var rows =$('#dataGrid').datagrid('getSelections');
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
					$('#dataGrid').datagrid('reload');
					$('#dataGrid').datagrid('clearSelections');
				}else{
					$.messager.alert('提示',result.msg, 'info');
				}
				progressClose();
			}, 'JSON');
		}
	});
}

/**
 * 上传数据
 */
function impAjax(){
	$('#impForm').form('submit',{
		url : path+'/upload',
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
		        	$.messager.alert({
		    			title:'提示',
		    			msg:result.msg,
		    			height:'180px'
		    		}); 
	            $('#impDialog').dialog('close'); 
	            $('#dataGrid').datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为list.jsp页面预定义好了
	        } else {
	            parent.$.messager.alert('错误', result.msg, 'error');
	        }
    	}
   });
}

/**
 * 上传图片
 * @returns
 */
function uploadGczlYdxjGPZLXJInfoImg(){
//	if(_getCurrOrgzType() =='0'){$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');	return;}//非项目部下人员无权操作数据
	var rows = dataGrid.datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择数据！', 'info');
		return;
	}
	if(rows.length>1){
		$.messager.alert('提示','请选择单条数据！','info');
		return;
	}
	_uploadPicture(rows[0].id,syspath,'图片链接',dataGrid,'安全质量巡检明细信息');
	
}

/**
 * 刷新
 */
function reloadFun(){
	dataGrid.datagrid('reload');
}

/**
 * 取消已选
 * @returns
 */
function clearSelections(){
	dataGrid.datagrid('clearSelections');
}

/**
 * 表单重置
 */
function cleanFun() {
	$('#searchForm input').val('');
	$('#searchForm').form('clear');
	dataGrid.datagrid('load', {});
}

function searchFun(){
	dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
}

/**
 * 数据导出
 * @returns
 */
function expXls(){
	var url = path + "/expXls";
	
	ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, moduleName, dataGrid, false);
}

function expPdf(){
	var url = path + "/expPdf";
	
	ExportUtil.doExport(url, ExportUtil.TYPE_PDF, moduleName, dataGrid, false);
}
