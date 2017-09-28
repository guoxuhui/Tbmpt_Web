/**
 * 
 */
/** 系统模块同路径 */ 
var syspath = "dgjjdw";
/** 业务数据访问全路径 */
var path = basePath +"/"+ syspath +"/dwsjzh";
/** 菜单名称 */
var entityCnName = "点位数据转换关系";
/**
 * 模块名称
 */
var moduleName = "盾构掘进点位管理"; 
/**若无一条勾选数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayZero = ["cancelSelect_button_Dwsjzh","edit_button_Dwsjzh","del_button_Dwsjzh","show_button_Dwsjzh"];
/**若勾选大于一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayMoreOneItem =["cancelSelect_button_Dwsjzh","edit_button_Dwsjzh","del_button_Dwsjzh","show_button_Dwsjzh"];
var hiddenClumArrayOnlyOneItem = [];

/** 列表对象 */
var yxyscx_dataGrid;
$(function() {
	/** 创建数据表格	 */
	dwsjzh_dataGrid = $('#dataGrid_DwsjzhSearch').datagrid({
		url : path+'/dwsjzhDataGrid',
		striped : true,
		rownumbers : true,
		singleSelect : false,
		checkOnSelect : true,
		selectOnCheck : true,
		pagination : true,
		sortName : 'id',
		sortOrder : 'desc',
		idField : 'id',
		pageSize : 15,
		pageList : [ 10, 15, 20, 50 ],
		frozenColumns:[[
		   {width: '100',title: 'id',field: 'id',align:'center', checkbox:true}, 
		   {width: '100',title: '盾构机名称',field: 'dgjname',sortable : true },
           {width: '300',title: '标准点位名称',field: 'dwname',sortable : true, 
			   formatter: function(value,row,index){
					return DataGridFormatter.tipsFormatter(value,30);
				}
           },
           {width: '90',title: '跨度开始',field: 'kdStart'},
           {width: '90',title: '跨度结束',field: 'kdEnd'},
           {width: '90',title: '数据值开始',field: 'dataStart'},
           {width: '90',title: '数据值结束',field: 'dataEnd'},
           {width: '80',title: '单位',field: 'dw'},
           {width: '60',title: '正负数',field: 'dataZf',
        	   formatter: function(value,row,index){
					return (value==1?"正数":"负数");
				}
           },
           {width: '80',title: '计算方式',field: 'calType'}          
		]],
		
		toolbar : '#toolbar',
		onLoadSuccess : function(data) {
			hiddenButtonBase("dataGrid_DwsjzhSearch",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
		},
        onCheck : function(){
        	hiddenButtonBase("dataGrid_DwsjzhSearch",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onUncheck : function(){
        	hiddenButtonBase("dataGrid_DwsjzhSearch",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onCheckAll : function(){
        	hiddenButtonBase("dataGrid_DwsjzhSearch",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onUncheckAll : function(){
        	hiddenButtonBase("dataGrid_DwsjzhSearch",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onClickRow:function(){
        	hiddenButtonBase("dataGrid_DwsjzhSearch",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        }
	});
	$('#dataGrid_DwsjzhSearch').show();  
});

/**
 * 查看数据页面
 */
function showDwsjzhFun(currid,index) { 
	
	var id;
	var row;
	if(currid){
		id = currid;
		var allrowshowDwsjzhFuns = dwsjzh_dataGrid.datagrid('getRows');
		row = allrows[index];
	}else{
		var rows = dwsjzh_dataGrid.datagrid('getSelections');
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
		 
		$("#show_dwsjzh_Dialog").dialog('open').dialog('setTitle', '查看［'+moduleName+"］");  
		$('#show_dwsjzh_Dialog').form('load', row);
		
		//_showAllFileInDiv(row.id,'img_div_yxYszlYsSearch'); 
	}
}


 
/*
 * 重置
 * */
function cleandDwsjzh() {
	$('#searchDwsjzhForm input').val('');
	dwsjzh_dataGrid.datagrid('load', {});
}
/*
 * 刷新
 * */
function reloadFun(){ 
	dwsjzh_dataGrid.datagrid('reload');
}
/*
 * 查询
 * */
function searchDwsjzhFun(){  
	dwsjzh_dataGrid.datagrid('load', $.serializeObject($('#searchDwsjzhForm')));
} 
/**
 * 取消已选
 * @returns
 */
function clearDwsjzhSelections(){
	dwsjzh_dataGrid.datagrid('clearSelections');
}


/**打开新增页面 */
function addDwsjzhFun() {  
	//FormUtil.clearForm($("#dwsjzhForm")); 
	$("#add_dwsjzh_Dialog").dialog('open').dialog('setTitle', '添加［'+entityCnName+"］"); 
}


/**数据更新*/
function editDwsjzh(){
	$('#editDwsjzhForm').form('submit', {
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
				$.messager.show({title:'提示',msg:'修改操作：成功!',showType:'show' });
				dwsjzh_dataGrid.datagrid('reload');
				$("#edit_dwsjzh_Dialog").dialog('close');
				dwsjzh_dataGrid.datagrid('clearSelections');
				FormUtil.clearForm($("#edit_dwsjzh_Dialog"));
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}

/**数据新增 */
function addDwsjzh(){
	$('#dwsjzhForm').form('submit', {
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
				dwsjzh_dataGrid.datagrid('reload');
				$("#add_dwsjzh_Dialog").dialog('close');
				dwsjzh_dataGrid.datagrid('clearSelections');
				FormUtil.clearForm($("#add_dwsjzh_Dialog"));
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}
/** 数据删除 */
function deleteDwsjzhFun() {
	//if(_getCurrOrgzType() =='0'){$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');	return;}//非项目部下人员无权操作数据
	var ids = new Array();
	var rows = dwsjzh_dataGrid.datagrid('getSelections');
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
					dwsjzh_dataGrid.datagrid('reload');
					dwsjzh_dataGrid.datagrid('clearSelections');
					
				}
				progressClose();
			}, 'JSON');
		}
	});
}


/**打开数据编辑页面 */
function editDwsjzhFun() {
	var userType  = _getCurrOrgzType(); 
	var id;
	var rows = dwsjzh_dataGrid.datagrid('getSelections');
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
		$("#edit_dwsjzh_Dialog").dialog('open').dialog('setTitle', '编辑［'+entityCnName+'］');
		$('#editDwsjzhForm').form('load', rows[0]);
	}
}
 