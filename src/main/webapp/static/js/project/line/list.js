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
var module = "line";
/**
 * 模块名称
 */
var moduleName = "线路";
/**
 * 名称字段
 */
var nameField = "lineName";
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

			$('#search_sectionId').combobox({
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
				}
			});

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
				}
			});

		}
	});
	
	/**
	 *获取盾构机信息
	 *
	 */


	$("#add_tbmId").combogrid({
		url : path+'/getTbmDic',
		panelWidth:520,
		idField : "id",
		textField : "tbmCode",
		fitColumns: true,  
        striped: true,  
        editable:false,  
        rownumbers:true,
        collapsible:false,  
        fit: true,
		sortName : 'tbmCode',
		sortOrder : 'desc',
		pagination: true,
		pageSize : 100,
		pageList : [ 100, 150, 200, 500 ],
		columns:[[
		          {field:'tbmCode',title:'盾构机编号',width:100},
		          {field:'tbmName',title:'盾构机名称',width:120},
		          {field:'manageno',title:'管理编号',width:120},
		          {field:'manufacturer',title:'生产厂商',width:180}
		      ]]
	});

	$("#edit_tbmId").combogrid({
		url : path+'/getTbmDic',
		panelWidth:520,
		idField : "id",
		textField : "tbmCode",
		fitColumns: true,  
        striped: true,  
        editable:false,  
        rownumbers:true,
        collapsible:false,  
        fit: true,
		sortName : 'tbmCode',
		sortOrder : 'desc',
		pagination: true,
		pageSize : 100,
		pageList : [ 100, 150, 200, 500 ],
		columns:[[
		          {field:'tbmCode',title:'盾构机编号',width:100},
		          {field:'tbmName',title:'盾构机名称',width:120},
		          {field:'manageno',title:'管理编号',width:120},
		          {field:'manufacturer',title:'生产厂商',width:180}
		      ]]
	});
	
	$("#show_tbmId").combogrid({
		url : path+'/getTbmDic',
		panelWidth:520,
		idField : "id",
		textField : "tbmCode",
		fitColumns: true,  
        striped: true,  
        editable:false,  
        rownumbers:true,
        collapsible:false,  
        fit: true,
		sortName : 'tbmCode',
		sortOrder : 'desc',
		pagination: true,
		pageSize : 100,
		pageList : [ 100, 150, 200, 500 ],
		columns:[[
		          {field:'tbmCode',title:'盾构机编号',width:100},
		          {field:'tbmName',title:'盾构机名称',width:120},
		          {field:'manageno',title:'管理编号',width:120},
		          {field:'manufacturer',title:'生产厂商',width:180}
		      ]]
	});


	/**
	 * 创建数据表格
	 *
	 */
	dataGrid = $('#dataGrid').datagrid({
		url : path+'/dataGrid',
		striped : true,
		rownumbers : true,
		singleSelect : true,
		checkOnSelect : true,
		selectOnCheck : true,
		pagination : true,
		idField : 'id',
		sortName : 'id',
		sortOrder : 'desc',
		pageSize : 15,
		pageList : [ 10, 15, 20, 50 ],
		frozenColumns:[[
		                
			{field : 'ck',checkbox : true},
			{width : '150',title : '线路名称',field : 'lineName',sortable : true, 
				formatter: function(value,row,index){
					return '<a href="#" onclick="showFun(\''+ row.id +'\', \'' + index + '\')">' + value + '</a>';
				}
			}, 
			{width : '150',title : '区间名称',field : 'sectionName',sortable : true}, 
            {width : '320',title : '所属工程',field : 'proName',sortable : true}
		     ]],
		columns : [ [
			{width : '100',title : '当前盾构机',field : 'usingTbmName',sortable : true}, 
			{width : '100',title : '盾构机',field : 'tbmName',sortable : true},
			{width : '100',title : '盾构机司机',field : 'dgChauffeur',align:'right'},
            {width : '100',title : '起始里程',field : 'startMileage',align:'right'}, 
            {width : '100',title : '终止里程',field : 'endMileage',align:'right'}, 
            {width : '100',title : '管环数量',field : 'ringCount',align:'right'}, 
            {width : '100',title : '里程前辍标识',field : 'mileagePrefix',sortable : true}, 
            {width : '100',title : '隧道长度(米)',field : 'tunnellength',align:'right'}, 
            
            {width : '140',title : '预计始发日期',field : 'yjsfrq',align:'right'}, 
            {width : '140',title : '预计出洞日期',field : 'yjcdrq',align:'right'}, 
            {width : '140',title : '实际始发日期',field : 'sjsfrq',align:'right'}, 
            {width : '140',title : '实际出洞日期',field : 'sjcdrq',align:'right'}, 
            
            {width : '100',title : '推进总工期(天)',field : 'tunneltime',align:'right'}, 
            {width : '100',title : '起始环号',field : 'startRingnum',align:'right'}, 
            {width : '100',title : '状态',field : 'lineStatus',sortable : true,formatter: function(value,row,index){
				if (row.lineStatus == 0){
					return "未开工";
				} else if(row.lineStatus == 1) {
					return "已开工";
				} else if(row.lineStatus == 2){
					return "已完工";
				}
			}}, 
            {width : '120',title : '录入时间',field : 'entertime',sortable : true}
			] ],
		onLoadSuccess : function(data) {},
		toolbar: '#toolbar'
/*		toolbar : [ 
					{text : '刷新',iconCls: 'icon-reload',
					    handler: function(){
					    	dataGrid.datagrid('reload');
					    }
					},
					'-',
		            {text : "添加",iconCls : 'icon-add',handler : addFun}, 
		            '-',
		            {text : "编辑",iconCls : 'icon-edit',handler : editFun}, 
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
		            '-', 
		            {text : "已开工",iconCls : 'icon-package_start',handler : startFun},
		            '-', 
		            {text : "已完工",iconCls : 'icon-package_stop',handler : endFun},
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
 * 数据添加
 */
function addFun() {
	FormUtil.clearForm($("#addDialog"));
	$("#addDialog").dialog('open').dialog('setTitle', '添加［'+moduleName+"］");

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
			if(rows[0].lineStatus == 2){
				$.messager.alert('提示', '当前线路已经完工，您无法操作！', 'info');
				return;
			}else if(rows[0].lineStatus == 1){
				$.messager.alert('提示', '当前线路已经开工，您无法操作！', 'info');
				return;
			}
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
 * 已开工按钮事件
 */
function startFun() {
	var id;
	var rows = dataGrid.datagrid('getSelections');
	if (rows.length > 1) {
		$.messager.alert('提示', '请选择单条数据进行操作！', 'info');
		return;
	}
	else if (rows.length == 0) {
		$.messager.alert('提示', '请选择数据后再操作！', 'info');
		return;
	}
	if (rows[0]) {
		id = rows[0].id;
		if(rows[0].lineStatus == 2){
			$.messager.alert('提示', '当前线路已经完工，您无法操作！', 'info');
			return;
		}else if(rows[0].lineStatus == 1){
			$.messager.alert('提示', '当前线路正在施工，您无法操作！', 'info');
			return;
		}
	}
	else {
		$.messager.alert('提示', '请选择数据后再操作！', 'info');
		return;
	}
	$.messager.confirm('询问', '您是否要操作'+moduleName + '［'+rows[0][nameField]+'］'+'？'+'操作后，线路为开工状态，之后线路信息您将无法进行编辑操作！如需编辑请联系系统管理员。', function(b) {
		if (b) {
			progressLoad();
			$.post(path+'/startWork', {
				id : id
			}, function(result) {
				if (result.success) {
					$.messager.show({title:'提示',msg:result.msg,showType:'show' });
					dataGrid.datagrid('reload');
				}
				progressClose();
			}, 'JSON');
		}
	});
}

/**
 * 已完工按钮事件
 */
function endFun() {
	var id;
	var rows = dataGrid.datagrid('getSelections');
	if (rows.length > 1) {
		$.messager.alert('提示', '请选择单条数据进行操作！', 'info');
		return;
	}
	else if (rows.length == 0) {
		$.messager.alert('提示', '请选择数据后再操作！', 'info');
		return;
	}
	if (rows[0]) {
		id = rows[0].id;
		if(rows[0].lineStatus == 2){
			$.messager.alert('提示', '当前线路已经完工，您无法操作！', 'info');
			return;
		}else if(rows[0].lineStatus == 0){
			$.messager.alert('提示', '当前线路还未开工，不能操作', 'info');
			return;
		}
	}
	else {
		$.messager.alert('提示', '请选择数据后再操作！', 'info');
		return;
	}
	
	$.messager.confirm('询问', '您是否要操作'+moduleName + '［'+rows[0][nameField]+'］'+'？', function(b) {
		if (b) {
			progressLoad();
			$.post(path+'/endWork', {
				id : id
			}, function(result) {
				if (result.success) {
					$.messager.show({title:'提示',msg:result.msg,showType:'show' });
					dataGrid.datagrid('reload');
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
		$("#showDialog").dialog('open').dialog('setTitle', '查看［'+moduleName+"］"+"-"+row[nameField]);
		$('#showDialog').form('load', row);
		disableForm('showForm',true);
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