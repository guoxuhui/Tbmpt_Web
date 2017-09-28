/**
 * 工程管理系统 工程管理模块
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
var module = "info";
/**
 * 模块名称
 */
var moduleName = "工程";
/** 菜单名称 */
var entityCnName = "工程管理";
/**
 * 名称字段
 */
var nameField = "proName";
/**
 * 业务数据访问全路径
 */
var path = basePath +"/"+ syspath +"/"+ module;

var country = [{text:"中国"}];
var state = [{"id":-1,"text":"--请选择--"},{"id":0,"text":"未开工"},{"id":1,"text":"在建"},{"id":2,"text":"完工"}];
/**
 * 列表对象
 */
var dataGrid;
$(function() {
	/**
	 * 查询表单的施工状态
	 */
	$("#search_proState").combobox({
		data : state,
		valueField : "id",
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
		frozenColumns : [ [
   			{field : 'ck',checkbox : true},
   			{width : '320',title : '工程名称',field : 'proName',sortable : true, 
				formatter: function(value,row,index){
					return '<a href="#" onclick="showFun(\''+ row.id +'\', \'' + index + '\')">' + value + '</a>';
				}
			}
		          ]],
		columns : [ [
			{width : '200',title : '工程简称',field : 'abbreviation'},
			{width : '80',title : '负责人',field : 'empName'},
			{width : '150',title : '联系方式',field : 'phone'},
			{width : '260',title : '施工单位',field : 'parentName'}, 
			
			{width : '200',title : '承建单位',field : 'cjdw'},
			{width : '200',title : '监理单位',field : 'jldw'},
			{width : '200',title : '建设单位',field : 'jsdw'},			
            {width : '120',title : '所在省份',field : 'province'}, 
            {width : '120',title : '掘进总长度(米)',field : 'tunnellength',align:'right'}, 
            {width : '120',title : '工程环宽(米)', field : 'ringwidth', align:'right'}, 
            {width : '200',title : '进场日期',field : 'startdate'}, 
            {width : '60',title : '状态',field : 'proState',sortable : true,
                formatter : function(value, row, index) {
                    switch (value) {
                    case -1:
                    	return '';
                    case 0:
                        return '未开工';
                    case 1:
                        return '在建';
                    case 2:
                    	return '完工';
                    }
                }
            }
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
 * 上传图片
 * @returns
 */
function UploadPicture(){
	
	var rows = dataGrid.datagrid('getSelections');
	if (rows.length > 1) {
		$.messager.alert('提示', '请选择单条数据！', 'info');
		return;
	}
	else if (rows.length == 0) {
		$.messager.alert('提示', '请选择数据！', 'info');
		return;
	}
	

	_uploadPicture(rows[0].id,syspath,'图片链接',dataGrid,'工程管理');

}


/**
 * 数据添加
 */
function addFun() {
//	FormUtil.clearForm($("#addDialog"));
//	$("#addDialog").dialog('open').dialog('setTitle', '添加［'+moduleName+"］");
//	$('#add_country').combobox({
//		data : country,
//		valueField : "text",
//		textField : "text",
//		onLoadSuccess : function() {
//			var val = $(this).combobox('getData');
//			for (var item in val[0]) {
//				if (item == 'text') {
//					//$(this).combobox('select', val[0][item]);
//				}
//			}
//		 },
//		 onSelect:function(newValue,oldValue){
//	        	$('#add_area').combobox("clear");
////	        	$('#add_area').combobox('loadData', area);
//			 
//	      }
//	});
//	$("#add_area").combobox({                 
//        url: basePath+'/project/pq/list',
//        valueField:'pqName',
//        textField:'pqName'
//            });
//	
//	$("#add_proState").combobox({
//		data : state,
//		valueField : "id",
//		textField : "text",
//		onLoadSuccess : function() {
//			var val = $(this).combobox('getData');
//			for (var item in val[0]) {
//				if (item == 'id') {
//					$(this).combobox('select', val[0][item]);
//				}
//			}
//		}
//	});
//	
//	$("#add_parentId").combotree({
//		url : basePath+'/sys/orgz/tree',
//	    parentField : 'parentId',
//	    lines : true,
//	    panelWidth:280,
//        panelHeight :350,
//        onBeforeSelect : function(node){
//			if(node.type != "1"){
//				$.messager.alert('错误', '请选择工程部', 'error');
//				return false;
//			}else{
//			}
//        }
//	});
//
//	/**
//	 * 承建单位
//	 */
//	$("#add_cjdw").textbox('setValue','中铁一局城轨公司');
	parent.$.modalDialog({
        title : '添加'+moduleName,
        width : 850,
        height : 580,
        href : path+'/addPage',
        buttons : [{
            text : '保存',
            handler : function() {
            	parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好                
                var f = parent.$.modalDialog.handler.find('#addForm');
                f.submit();
            }
        },{
            text : '关闭',
            handler : function() {
            	parent.$.modalDialog.handler.dialog('close');
            }
        } ]
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
//	var id;
//	var rows = dataGrid.datagrid('getSelections');
//	if (rows.length > 1) {
//		$.messager.alert('提示', '请选择单条数据进行编辑！', 'info');
//		return;
//	}
//	else if (rows.length == 1) {
//		if (rows[0]) {
//			id = rows[0].id;
//		}
//		else {
//			$.messager.alert('提示', '请选择数据后再编辑！', 'info');
//			return;
//		}
//	}
//	else {
//		$.messager.alert('提示', '请选择数据后再编辑！', 'info');
//		return;
//	}
//	if (rows[0]) {
//		$("#editDialog").dialog('open').dialog('setTitle', '编辑［'+moduleName+"］"+"-"+rows[0][nameField]);
//		$('#edit_country').combobox({
//			data : country,
//			valueField : "text",
//			textField : "text",
//			onLoadSuccess : function() {
//				var val = $(this).combobox('getData');
//				for (var item in val[0]) {
//					if (item == 'text') {
//						//$(this).combobox('select', val[0][item]);
//					}
//				}
//			 },
//			 onSelect:function(newValue,oldValue){
//		        	$('#edit_area').combobox("clear");
////		        	$('#edit_area').combobox('loadData', area);
//		      }
//		});
//		$("#edit_proState").combobox({
//			data : state,
//			valueField : "id",
//			textField : "text",
//			onLoadSuccess : function() {
//				var val = $(this).combobox('getData');
//				for (var item in val[0]) {
//					if (item == 'id') {
//						$(this).combobox('select', val[0][item]);
//					}
//				}
//			}
//		});
////		$("#edit_area").combobox({
////			data : area,
////			valueField : "text",
////			textField : "text",
////			onLoadSuccess : function() {
////				var val = $(this).combobox('getData');
////				for (var item in val[0]) {
////					if (item == 'id') {
////						$(this).combobox('select', val[0][item]);
////					}
////				}
////			}
////		});
//		$("#edit_area").combobox({                 
//	        url: basePath+'/project/pq/list',
//	        valueField:'pqName',
//	        textField:'pqName'
//	            });
//		
//		$("#edit_parentId").combotree({
//			url : basePath+'/sys/orgz/tree',
//		    parentField : 'parentId',
//		    lines : true,
//		    panelWidth:280,
//	        panelHeight :350,
//            onBeforeSelect : function(node){
//    			if(node.type != "1"){
//    				$.messager.alert('错误', '请选择工程部', 'error');
//    				return false;
//    			}else{
//    			}
//            }
//		});
//		/**
//		 * 建设单位
//		 */
////		$("#edit_jsdw").combotree({
////			url : basePath+'/sys/orgz/tree',
////		    parentField : 'parentId',
////		    lines : true,
////		    panelWidth:280,
////	        panelHeight :350,
////	        onBeforeSelect : function(node){
////				if(node.type != "0"){
////					$.messager.alert('错误', '请选择机构单位', 'error');
////					return false;
////				}else{
////				}
////	        }
////		});
//		/**
//		 * 承建单位
//		 */
//		$("#edit_cjdw").textbox('setValue','中铁一局城轨公司');
//		$('#editForm').form('load', rows[0]);
//	}
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
	parent.$.modalDialog({
        title : '编辑'+moduleName,
        width : 850,
        height : 580,
        href : path+'/editPage?id='+id,
        buttons : [{
            text : '保存',
            handler : function() {
            	parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好 
                var f = parent.$.modalDialog.handler.find('#editForm');
                f.submit();
            }
        },{
            text : '关闭',
            handler : function() {
            	parent.$.modalDialog.handler.dialog('close');
            }
        } ]
    });
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
	
    parent.$.modalDialog({
        title : '查看'+moduleName + '［'+row[nameField]+'］',
        width : 850,
        height : 580,
        href : path+'/showPage?id='+id,
        buttons : [ {
            text : '取消',
            handler : function() {
            	parent.$.modalDialog.handler.dialog('close');
            }
        } ]
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
	$('#searchForm').form('clear');
	dataGrid.datagrid('load', {});
}

/**
 * 打开地图选择器
 */
function selectAddress() {
	$('#openMapIframe')[0].src=basePath+'/project/info/selectAddress';
	$('#openDiv').dialog('open');
}



/**
 * 员工选择框
 * @returns
 */
function empSelection(){
	var id_input = this.alt+"Id";
	var name_input = this.alt+"Name";
    parent.$.modalDialog({
        title : '员工管理',
        width : 800,
        height : 450,
        href : basePath+'/sys/emp/empSelection',
        buttons : [ {
            text : '确定',
            handler : function() {
                var udatagrid = parent.$.modalDialog.handler.find('#dataGrid');
            	var rows = udatagrid.datagrid('getSelections');
            	if (rows.length >= 1) {
            		$("#"+id_input).val(rows[0].id);
            		$("#"+name_input).textbox("setValue",rows[0].name);
            		parent.$.modalDialog.handler.dialog('close');
            	}else{
            		parent.$.messager.alert('提示', '请选择一个员工！ ', 'info');
            	}
            	
            }
        },{
            text : '取消',
            handler : function() {
            	parent.$.modalDialog.handler.dialog('close');
            }
        } ]
    });
	
}