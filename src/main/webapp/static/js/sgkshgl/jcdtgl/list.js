/**
 * 施工可视化管理： 基础底图管理模块
 * 
 * 创建时间：2017年04月12日 
 * 
 */

/**
 * 系统模块同路径
 */
var syspath = "sgkshgl";
/**
 * 子模块路径
 */
var module = "jcdtgl";
/**
 * 模块名称
 */
var moduleName = "基础底图";
/**
 * 名称字段
 */
var nameField = "name";
/**
 * 业务数据访问全路径
 */
var path = basePath +"/"+ syspath +"/"+ module;
/** 列表对象 */
var dataGrid;
/** 项目树对象 */
var proTree
/** 关联类型 平面*/
var type=[{id:'PM',name:'平面'},{id:'DM',name:'断面'}];


$(function() {
	
	//加载 关联类型,（PM--区间平面图） 线路（DM--纵断面图） */
	var addLine;
	
	$('#add_mapType').combobox({
		data : type,
		valueField : "id",
		textField : "name",
		onChange:function() {
	    	var b=$(this).combobox('getValue');
	    	if(b=="DM"){
	    		$("#add_line_table").prepend(addLine);
	    	} 
	    	else if(b=="PM"){
	    		addLine=$("#add_line_tr").detach();
	        }
	    	
	    }
	});
	
	var editLine;
	$('#edit_mapType').combobox({
		data : type,
		valueField : "id",
		textField : "name",
		onChange:function() {
	    	var b=$(this).combobox('getValue');
	    	if(b=="DM"){
	    		$("#edit_line_table").prepend(editLine);
	    	} 
	    	else if(b=="PM"){
	    		editLine=$("#edit_line_tr").detach();
	        }
	    	
	    }
	});
	var showLine;
	$('#show_mapType').combobox({
		data : type,
		valueField : "id",
		textField : "name",
		onChange:function() {
	    	var b=$(this).combobox('getValue');
	    	if(b=="DM"){
	    		$("#show_line_table").prepend(showLine);
	    	} 
	    	else if(b=="PM"){
	    		showLine=$("#show_line_tr").detach();
	        }
	    }
	});
	
	
	/** 项目树对象 */
	proTree = $('#proTree').tree({
	url : path+'/tree',
	parentField : 'pid',
	lines : true,
	onClick : function(node) {
	    dataGrid.datagrid('load', {
	    	refId: node.id
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
		pagination : false,
		idField : 'id',
		sortName : 'id',
		sortOrder : 'desc',
		pageSize : 15,
		pageList : [ 10, 15, 20, 50 ],
		frozenColumns : [ [
			{field : 'ck',checkbox : true},
			{width : '250',title : '关联名称',field : 'refName',sortable : true,
				formatter: function(value,row,index){
					return '<a href="#" onclick="showFun(\''+ row.id +'\', \'' + index + '\')">' + value + '</a>';
				}
			}
		] ],
		columns : [ [
            {width : '100',title : '关联类型',field : 'mapType',
                formatter : function(value, row, index) {
                    if(value=='PM') {//类型（PM--区间平面图） 线路（DM--纵断面图）
                    	return '平面';
                    }else if(value=='DM') {
                    	return '断面';
                    }
                }},
            {width : '350',title : '关联路径',field : 'mapPath'}
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
 * 表单重置
 */
function cleanFun() {
	dataGrid.datagrid('load', {});
}
/**
 * 数据添加
 */
function addFun() {
	
	FormUtil.clearForm($("#addDialog"));
	$("#addDialog").dialog('open').dialog('setTitle', '添加［'+moduleName+"］");
	
	/**
	 * 加载项目信息
	 */
	 $('#add_proId').combobox({
        url : basePath + '/project/info/getProjects',
        valueField : "id",
        textField : "proName",
        onChange:function(){
        	 $('#add_sectionId').combobox({
                 url : basePath+'/project/line/getProSectionDic?proId='+$('#add_proId').combobox('getValue'),
                 valueField: "id",
                 textField: "sectionName",
                 onChange:function(){
                    var qjid = $('#add_sectionId').combobox('getValue');
                    var Type = $("#mapType").val();
                    if(Type="DM"){  // 线路（DM--纵断面图）
                   	 $('#add_lineId').combobox({
                            url : basePath+'/project/line/getProLineDic?sectionId='+qjid,
                            valueField: "id",
                            textField: "lineName"
                        });
                    }
                }
             });
        }
    });
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
				FormUtil.clearForm($("#addDialog"));
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
	FormUtil.clearForm($("#editDialog"));
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
		
		$("#editDialog").dialog('open').dialog('setTitle', '编辑［'+moduleName+'］');
		$('#editForm').form('load', rows[0]);
		editGcQjXl(rows[0].proId,rows[0].sectionId,rows[0].lineId);
		
	}
}



/**
 * 编辑项目部信息
 * @returns
 */
function editGcQjXl(proId,sectionId,lineId){
	var l=1;
	//获得项目ID
	$('#edit_proId').combobox({
		url : path+'/getProDic',
		valueField : "id",
		textField : "proName",
		onLoadSuccess : function() {
			var val = $(this).combobox('getData');
			for(var i=0;i<val.length;i++){
				if(val[i].id==proId){
					$(this).combobox('setValue', proId);
				}
			}
		},
		onSelect:function(newValue,oldValue){
			$('#edit_sectionId').combobox("clear");
        	$('#edit_sectionId').combobox('reload', path+"/getProSectionDic?proId="+newValue.id);
		}
	});
	
	//获得区间ID
	$('#edit_sectionId').combobox({
		url : path+'/getProSectionDic',
		valueField : "id",
		textField : "sectionName",
		onLoadSuccess : function() {
			var val = $(this).combobox('getData');
			for(var i=0;i<val.length;i++){
				if(val[i].id==sectionId){
					$(this).combobox('setValue', sectionId);
				}
			}
		},
		onSelect:function(newValue,oldValue){
			$('#edit_lineId').combobox("clear");
        	$('#edit_lineId').combobox('reload', path+"/getProXlDic?sectionId="+newValue.id);
		}
	});
	
	if(lineId!=""&& l==1){
		//获得线路ID
		$('#edit_lineId').combobox({
			url : path+"/getProXlDic?sectionId="+sectionId,
			valueField : "id",
			textField : "lineName",
			editable:false,
			onLoadSuccess : function() {
				var val = $(this).combobox('getData');
				for(var i=0;i<val.length;i++){
					if(val[i].id==lineId){
						$(this).combobox('setValue', lineId);
						l++;
					}
				}
			 }
		});
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
					dataGrid.datagrid('reload');
					dataGrid.datagrid('clearSelections');
					$.messager.show({title:'提示',msg:result.msg,showType:'show' });
					
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
		$("#showDialog").dialog('open').dialog('setTitle', '查看［'+moduleName+'］');
	    
		//row.mapType
		
	    /**
		 * 加载项目信息
		 */
		 $('#show_proId').combobox({
	        url : basePath + '/project/info/getProjects',
	        valueField : "id",
	        textField : "proName",
	        onChange:function(){
	        	 $('#show_sectionId').combobox({
	                 url : basePath+'/project/line/getProSectionDic?proId='+$('#show_proId').combobox('getValue'),
	                 valueField: "id",
	                 textField: "sectionName",
	                 onChange:function(){
	                    var qjid = $('#show_sectionId').combobox('getValue');
	                    $('#show_lineId').combobox({
                            url : basePath+'/project/line/getProLineDic?sectionId='+qjid,
                            valueField: "id",
                            textField: "lineName"
                        });
	                }
	             });
	        }
	    });
		$('#showDialog').form('load', row);
	}
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




