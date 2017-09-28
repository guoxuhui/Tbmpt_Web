/**
 * 施工可视化管理： CAD中心线维护模块
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
var module = "zxxwh";
/**
 * 模块名称
 */
var moduleName = " CAD中心线";
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

	/** 项目树对象 */
	proTree = $('#proTree').tree({
	url : path+'/tree',
	parentField : 'pid',
	lines : true,
	onClick : function(node) {
	    dataGrid.datagrid('load', {
	    	proInquireId: node.id
	    });
	}
	});
	/**
	 * 加载项目信息
	 */
	 $('#search_proId').combobox({
        url : basePath + '/project/info/getProjects',
        valueField : "id",
        textField : "proName",
        onChange:function(){
        	 $('#search_sectionId').combobox({
                 url : basePath+'/project/line/getProSectionDic?proId='+$('#search_proId').combobox('getValue'),
                 valueField: "id",
                 textField: "sectionName",
                 onChange:function(){
                    var qjid = $('#search_sectionId').combobox('getValue');
                    $('#search_lineId').combobox({
                            url : basePath+'/project/line/getProLineDic?sectionId='+qjid,
                            valueField: "id",
                            textField: "lineName"
                        });
                    }
             });
        }
    });
	 $('#search_type').combobox({
			data : type,
			valueField : "id",
			textField : "name",
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
		idField : 'objectid',
		sortName : 'objectid',
		sortOrder : 'desc',
		pageSize : 15,
		pageList : [ 10, 15, 20, 50 ],
		frozenColumns : [ [
			{field : 'ck',checkbox : true},
			{width : '80',title : '环号',field : 'hh',sortable : true,
				formatter: function(value,row,index){
					return '<a href="#" onclick="showFun(\''+ row.id +'\', \'' + index + '\')">' + value + '</a>';
				}
			},
			{width : '100',title : '线路名称',field : 'lineName',sortable : true},
			{width : '150',title : '区间名称',field : 'sectionName',sortable : true}
            
		] ],
		columns : [ [
			{width : '320',title : '所属工程',field : 'proName',sortable : true},
            {width : '100',title : '类型',field : 'type',sortable : true,
                formatter : function(value, row, index) {
                    if(value=='PM') {//类型（PM--区间平面图） 线路（DM--纵断面图）
                    	return '平面';
                    }else if(value=='DM') {
                    	return '断面';
                    }
                }},
            {width : '100',title : '里程',field : 'lc'}, 
            {width : '100',title : 'X',field : 'x'},
            {width : '100',title : 'Y',field : 'y'},
            {width : '100',title : 'Z',field : 'z'}
			] ],
		onLoadSuccess : function(data) {},
		toolbar: '#toolbar'
		
	});
	
	
});

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
                    $('#add_lineId').combobox({
                            url : basePath+'/project/line/getProLineDic?sectionId='+qjid,
                            valueField: "id",
                            textField: "lineName"
                    });
                 }
             });
        }
    });
	 $('#add_type').combobox({
			data : type,
			valueField : "id",
			textField : "name"
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
			id = rows[0].objectid;
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
		$("#edit_type").combobox({
			data : type,
			valueField : "id",
			textField : "name"
	    });
		/**
		 * 加载项目信息
		 */
		 $('#edit_proId').combobox({
	        url : basePath + '/project/info/getProjects',
	        valueField : "id",
	        textField : "proName",
	        onChange:function(){
	        	 $('#edit_sectionId').combobox({
	                 url : basePath+'/project/line/getProSectionDic?proId='+$('#edit_proId').combobox('getValue'),
	                 valueField: "id",
	                 textField: "sectionName",
	                 onChange:function(){
	                    var qjid = $('#edit_sectionId').combobox('getValue');
	                    $('#edit_lineId').combobox({
	                            url : basePath+'/project/line/getProLineDic?sectionId='+qjid,
	                            valueField: "id",
	                            textField: "lineName"
	                    });
	                 }
	             });
	        }
	    });
		$('#editForm').form('load', rows[0]);
		//editGcQjXl(rows[0].proId,rows[0].sectionId,rows[0].lineId);
		
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
		ids.push(rows[i].objectid);
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
			id = rows[0].objectid;
		}
		else {
			$.messager.alert('提示', '请选择数据后再查看！', 'info');
			return;
		}	
	}
	if (row) {
		$("#showDialog").dialog('open').dialog('setTitle', '查看［'+moduleName+"］"+"-"+row[nameField]);
	    
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
		 $('#show_type').combobox({
				data : type,
				valueField : "id",
				textField : "name"
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

/**
 * 导入
 */
function impFun(){
	FormUtil.clearForm($("#impDialog"));
	$("#impDialog").dialog('open').dialog('setTitle', '添加［'+moduleName+"］+［请使用下载模版！］");
	/**
	 * 加载项目信息
	 */
	 $('#imp_proId').combobox({
        url : basePath + '/project/info/getProjects',
        valueField : "id",
        textField : "proName",
        onChange:function(){
        	 $('#imp_sectionId').combobox({
                 url : basePath+'/project/line/getProSectionDic?proId='+$('#imp_proId').combobox('getValue'),
                 valueField: "id",
                 textField: "sectionName",
                 onChange:function(){
                    var qjid = $('#imp_sectionId').combobox('getValue');
                    $('#imp_lineId').combobox({
                            url : basePath+'/project/line/getProLineDic?sectionId='+qjid,
                            valueField: "id",
                            textField: "lineName"
                    });
                 }
             });
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
	            $('#dataGrid').datagrid('reload');
	        } else {
	            parent.$.messager.alert('错误', result.msg, 'error');
	        }
    	}
   });
}


