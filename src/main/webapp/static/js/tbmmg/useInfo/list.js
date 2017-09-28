/**
 * 盾构机管理系统 盾构机历史使用信息纪录
 * 
 * 创建时间：2017年1月7日14:55:39
 * 
 */

/**
 * 系统模块同路径
 */
var syspath = "tbmmg";
/**
 * 子模块路径
 */
var module = "useInfo";
/**
 * 模块名称
 */
var moduleName = "盾构机历史使用信息纪录";
/**
 * 名称字段
 */
var nameField = "tbmUseInfo";
/**
 * 业务数据访问全路径
 */
var path = basePath +"/"+ syspath +"/"+ module;
/**
 * 列表对象
 */
var dataGrid;
var addXmb;
var editXmb;
var szdw=[{text:'维修中心'},{text:'项目部'}];
var tbmState=[{text:'进场'},{text:'组装'},{text:'准备始发'},{text:'始发'},{text:'掘进'},{text:'正常停机'},{text:'非正常停机'},
	{text:'准备出洞'},{text:'出洞'},{text:'运输'},{text:'维修'},{text:'存放'}];
$(function() {
	addXmb = $("#add_hidden_tr").detach();
	//新增页面加载分类
	$('#add_szdw').combobox({
		data : szdw,
		valueField : "text",
		textField : "text",
		onChange:function() {
			addXmb.detach();	    	
	    	var b=$(this).combobox('getText');
	    	if(b=="项目部"){
	    		$("#add_hidden_szdw").prepend(addXmb);
	    		addGcQjXl();
	    	}
	    }
	});
	//新增页面加载分类
	$('#search_szdw').combobox({
		data : szdw,
		valueField : "text",
		textField : "text",
		onChange:function() {
			addXmb.detach();	    	
	    	var b=$(this).combobox('getText');
	    	
	    }
	});
	//编辑项目部
	editXmb=$("#edit_hidden_tr").detach();
	$('#edit_szdw').combobox({
		data : szdw,
		valueField : "text",
		textField : "text",
		onChange:function() {
			editXmb.detach();	    	
	    	var b=$(this).combobox('getText');
	    	if(b=="项目部"){
	    		$("#edit_hidden_szdw").prepend(editXmb);
	    		editGcQjXl();
	    	}
	    }
	});
	//查看项目部
	//编辑项目部
	showXmb=$("#show_hidden_tr").detach();
	$('#show_szdw').combobox({
		data : szdw,
		valueField : "text",
		textField : "text",
		onChange:function() {
			showXmb.detach();	    	
	    	var b=$(this).combobox('getText');
	    	if(b=="项目部"){
	    		$("#show_hidden_szdw").prepend(showXmb);
	    		editGcQjXl();
	    	}
	    }
	});
	
	//盾构机状态
	$("#search_tbmState").combobox({
		data : tbmState,
		valueField : "text",
		textField : "text",
		onLoadSuccess : function() {
		var val = $(this).combobox('getData');
		for (var item in val[0]) {
			if (item == 'id') {
				$(this).combobox('select', val[0][item]);
			}
		}
	}
	});
	
	//盾构机状态
	$("#add_tbmState").combobox({
		data : tbmState,
		valueField : "text",
		textField : "text",
		onLoadSuccess : function() {
		var val = $(this).combobox('getData');
		for (var item in val[0]) {
			if (item == 'id') {
				$(this).combobox('select', val[0][item]);
			}
		}
	}
	});
	
	//盾构机状态
	$("#edit_tbmState").combobox({
		data : tbmState,
		valueField : "text",
		textField : "text",
		onLoadSuccess : function() {
		var val = $(this).combobox('getData');
		for (var item in val[0]) {
			if (item == 'id') {
				$(this).combobox('select', val[0][item]);
			}
		}
	}
	});
	
	//获取盾构机
	$("#search_tbmbh").combogrid({
		url : basePath+'/project/line/getTbmDic',
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
		      ]],
		onSelect: function () {//选中处理
			var a=$("#search_tbmbh").combogrid('getText');
			$("#search_tbmName").val(a);       
      }
	});
	
	//获取盾构机
	$("#add_tbmbh").combogrid({
		url : basePath+'/project/line/getTbmDic',
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
		      ]],
		onSelect: function () {//选中处理
			var a=$("#add_tbmbh").combogrid('getText');
			$("#add_tbmName").val(a);       
      }
	});
	
	//获得项目ID
	$('#search_proId').combobox({
		url : path+'/getProDic',
		valueField : "id",
		textField : "proName",
		onLoadSuccess : function() {
			
		},
		onSelect:function(newValue,oldValue){
			$('#search_qjId').combobox("clear");
        	$('#search_qjId').combobox('reload', path+"/getProSectionDic?proId="+newValue.id);
		}
	});
	//获得区间ID
	$('#search_qjId').combobox({
		url : path+'/getProSectionDic',
		valueField : "id",
		textField : "sectionName",
		onLoadSuccess : function() {
			
		},
		onSelect:function(newValue,oldValue){
			$('#search_xlId').combobox("clear");
        	$('#search_xlId').combobox('reload', path+"/getProXlDic?sectionId="+newValue.id);
		}
	});
	//获得线路ID
	$('#search_xlId').combobox({
		valueField : "id",
		textField : "lineName",
		editable:false,
		onLoadSuccess : function() {
			
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
		columns:[[
			{field : 'ck',checkbox : true},
			{width:'200',title:'盾构机名称',field:'tbmName',
				formatter: function(value,row,index){
					return '<a href="#" onclick="showFun(\''+ row.id +'\', \'' + index + '\')">' + value + '</a>';
				}
			},
			 {width : '200',title : '所在单位',field : 'szdw',sortable : true}, 
			 {width : '200',title : '负责人',field : 'person'}, 	 
			 {width : '100',title : '联系电话',field : 'phone'},
			 {width : '200',title : '盾构机状态',field : 'tbmState',sortable : true}, 
			 {width : '200',title : '填写日期',field : 'rq'}            
		    ]],
		onLoadSuccess : function(data) {},
		toolbar : '#toolbar'
		          
	});
});

/**
 * 
 * @returns
 */
function reload(){
	dataGrid.datagrid('reload');
}


/**
 * 
 * @returns
 */
function clearSelections(){
	dataGrid.datagrid('clearSelections');
}

/**
 * 
 * @returns
 */
function expXls(){
	var url = path + "/expXls";
	ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, moduleName+"信息", dataGrid, false);
}

/**
 * 
 * @returns
 */
function expPdf(){
	var url = path + "/expPdf";
	ExportUtil.doExport(url, ExportUtil.TYPE_PDF, moduleName+"信息", dataGrid, false);
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

/**
 * 数据添加
 */
function addFun() {
	$("#addDialog").dialog('open').dialog('setTitle', '添加［'+moduleName+"］");
	addXmb.detach();
	FormUtil.clearForm($("#addDialog"));
//	$("#addForm").form('reset');

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
		//获取盾构机
		$("#edit_tbmbh").combogrid({
			url : basePath+'/project/line/getTbmDic',
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
			      ]],
			onSelect: function () {//选中处理
				var a=$("#edit_tbmbh").combogrid('getText');
				$("#edit_tbmName").val(a);       
	      }
		});
		$('#editForm').form('load', rows[0]);
		editGcQjXl(rows[0].gcid,rows[0].qjid,rows[0].xlid);
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
		//获取盾构机
		$("#show_tbmbh").combogrid({
			url : basePath+'/project/line/getTbmDic',
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
			      ]],
			onSelect: function () {//选中处理
				var a=$("#show_tbmbh").combogrid('getText');
				$("#show_tbmName").val(a);       
	      }
		});
		$('#showDialog').form('load', row);
		showGcQjXl(row.gcid,row.qjid,row.xlid);
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
 * 增加项目部信息
 * @returns
 */
function addGcQjXl() {
    	//获得项目ID
    	$('#add_gcid').combobox({
    		url : path+'/getProDic',
    		valueField : "id",
    		textField : "proName",
    		onLoadSuccess : function() {
    			
    		},
    		onSelect:function(newValue,oldValue){
    			$('#add_qjid').combobox("clear");
            	$('#add_qjid').combobox('reload', path+"/getProSectionDic?proId="+newValue.id);
    		}
    	});
    	//获得区间ID
    	$('#add_qjid').combobox({
    		url : path+'/getProSectionDic',
    		valueField : "id",
    		textField : "sectionName",
    		onLoadSuccess : function() {
    			
    		},
    		onSelect:function(newValue,oldValue){
    			$('#add_xlid').combobox("clear");
            	$('#add_xlid').combobox('reload', path+"/getProXlDic?sectionId="+newValue.id);
    		}
    	});
    	//获得线路ID
    	$('#add_xlid').combobox({
    		valueField : "id",
    		textField : "lineName",
    		editable:false,
    		onLoadSuccess : function() {
    			
    		 }
    	});
    }
/**
 * 编辑项目部信息
 * @returns
 */
function editGcQjXl(gcid,qjid,xlid){
	//获得项目ID
	$('#edit_gcid').combobox({
		url : path+'/getProDic',
		valueField : "id",
		textField : "proName",
		onLoadSuccess : function() {
			var val = $(this).combobox('getData');
			for(var i=0;i<val.length;i++){
				if(val[i].id==gcid){
					$(this).combobox('setValue', gcid);
				}
			}
		},
		onSelect:function(newValue,oldValue){
        	$('#edit_qjid').combobox('reload', path+"/getProSectionDic?proId="+newValue.id);
		}
	});
	//获得区间ID
	$('#edit_qjid').combobox({
		url : path+'/getProSectionDic',
		valueField : "id",
		textField : "sectionName",
		onLoadSuccess : function() {
			var val = $(this).combobox('getData');
			for(var i=0;i<val.length;i++){
				if(val[i].id==qjid){
					$(this).combobox('setValue', qjid);
				}
			}
		},
		onSelect:function(newValue,oldValue){
			$('#edit_xlid').combobox("clear");
        	$('#edit_xlid').combobox('reload', path+"/getProXlDic?sectionId="+newValue.id);
		}
	});
	//获得线路ID
	$('#edit_xlid').combobox({
		valueField : "id",
		textField : "lineName",
		editable:false,
		onLoadSuccess : function() {
			var val = $(this).combobox('getData');
			for(var i=0;i<val.length;i++){
				if(val[i].id==xlid){
					$(this).combobox('setValue', xlid);
				}
			}
		 }
	});
}
/**
 * 查看项目部信息
 * @returns
 */
function showGcQjXl(gcid,qjid,xlid){
	//获得项目ID
	$('#show_gcid').combobox({
		url : path+'/getProDic',
		valueField : "id",
		textField : "proName",
		onLoadSuccess : function() {
			var val = $(this).combobox('getData');
			for(var i=0;i<val.length;i++){
				if(val[i].id==gcid){
					$(this).combobox('setValue', gcid);
				}
			}
		},
		onSelect:function(newValue,oldValue){
        	$('#show_qjid').combobox('reload', path+"/getProSectionDic?proId="+newValue.id);
		}
	});
	//获得区间ID
	$('#show_qjid').combobox({
		url : path+'/getProSectionDic',
		valueField : "id",
		textField : "sectionName",
		onLoadSuccess : function() {
			var val = $(this).combobox('getData');
			for(var i=0;i<val.length;i++){
				if(val[i].id==qjid){
					$(this).combobox('setValue', qjid);
				}
			}
		},
		onSelect:function(newValue,oldValue){
			$('#show_xlid').combobox("clear");
        	$('#show_xlid').combobox('reload', path+"/getProXlDic?sectionId="+newValue.id);
		}
	});
	//获得线路ID
	$('#show_xlid').combobox({
		valueField : "id",
		textField : "lineName",
		editable:false,
		onLoadSuccess : function() {
			var val = $(this).combobox('getData');
			for(var i=0;i<val.length;i++){
				if(val[i].id==xlid){
					$(this).combobox('setValue', xlid);
				}
			}
		 }
	});
}