/** /** 【施工内容管理】 基础模块
 * 创建时间：2016-11-23
   */
/** 系统模块同路径 */
var syspath = "gczl";
/** 子模块路径 */
var module = "base";
/** 菜单名称 */
var entityCnName = "结构施工质量基础信息";
var entityCnName_jtwz="结构施工质量基础信息-具体位置"
var entityCnName_zlqx="结构施工质量基础信息-质量缺陷"
/** 名称字段 */
var nameField = "sgNr";
/** 业务数据访问全路径 */
var path = basePath +"/"+ syspath +"/"+ module+"/gczlYdxjSGZLSgnr";//施工内容
var jtwz_path = basePath +"/"+ syspath +"/"+ module+"/gczlYdxjSGZLJtwz";//具体位置
var zlqx_path = basePath +"/"+ syspath +"/"+ module+"/gczlYdxjSGZLZlqx";//质量缺陷
/** 列表对象 */
var dataGrid_gczlYdxjSGZLSgnr;
var dataGrid_gczlYdxjSGZLJtwz;//具体位置列表对象
var dataGrid_gczlYdxjSGZLZlqx;//质量缺陷列表对象

/**若无一条勾选数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayZero = ["cancelSelect_button_gczlYdxjSGZLSgnr","edit_button_gczlYdxjSGZLSgnr","del_button_gczlYdxjSGZLSgnr","jtwz_button_gczlYdxjSGZLSgnr","zlqx_button_gczlYdxjSGZLSgnr","qy_button_gczlYdxjSGZLSgnr","jy_button_gczlYdxjSGZLSgnr"];
/**若只勾选一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayOnlyOneItem = [];
/**若勾选大于一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayMoreOneItem =["edit_button_gczlYdxjSGZLSgnr","jtwz_button_gczlYdxjSGZLSgnr","zlqx_button_gczlYdxjSGZLSgnr"];

$(function() {
	/** 创建数据表格	 */
	dataGrid_gczlYdxjSGZLSgnr = $('#dataGrid_gczlYdxjSGZLSgnr').datagrid({
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
		frozenColumns:[[
	           {field:'id',align:'center',title:'ID',width:40, checkbox:true},
	           {width: '200',title: '施工内容',field: 'sgNr',
	        	   formatter: function(value,row,index){
	   				return '<a href="#" onclick="showGczlYdxjSGZLSgnrFun(\''+ row.id +'\',\''+index+'\')">' + value + '</a>';
	   			  }
	           },
	           {width: '250',title: '具体位置',field: 'jtWz',
	        	   formatter: function(value,row,index){
						return DataGridFormatter.tipsFormatter(value,30);
					}
	           }
			]],
		columns:[[
           {width: '400',title: '质量缺陷',field: 'zlQx',
        	   formatter: function(value,row,index){
					return DataGridFormatter.tipsFormatter(value,30);
				}
           },
           {width: '100',title: '使用状态',field: 'ifQy'},
           {width: '100',title: '排序号',field: 'sortNum'},
           {width: '200',title: '备注',field: 'remarks',
        	   formatter: function(value,row,index){
					return DataGridFormatter.tipsFormatter(value,20);
				}
           }
		]],
		toolbar : '#toolbar',
		onLoadSuccess : function(data) {
			hiddenButtonBase("dataGrid_gczlYdxjSGZLSgnr",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
		},
        onCheck : function(){
        	hiddenButtonBase("dataGrid_gczlYdxjSGZLSgnr",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onUncheck : function(){
        	hiddenButtonBase("dataGrid_gczlYdxjSGZLSgnr",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onCheckAll : function(){
        	hiddenButtonBase("dataGrid_gczlYdxjSGZLSgnr",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onUncheckAll : function(){
        	hiddenButtonBase("dataGrid_gczlYdxjSGZLSgnr",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onClickRow:function(){
        	hiddenButtonBase("dataGrid_gczlYdxjSGZLSgnr",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        }
	});
});

/**
 * 取消已选
 * @returns
 */
function clearGczlYdxjSGZLSgnrSelections(){
	dataGrid_gczlYdxjSGZLSgnr.datagrid('clearSelections');
}
/**
 * 导出excel
 * @returns
 */
function expGczlYdxjSGZLSgnrXls(){
	var url = path + "/expXls";
	ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, entityCnName+"信息", dataGrid_gczlYdxjSGZLSgnr, false);
}
/**
 * 导出pdf
 * @returns
 */
function expGczlYdxjSGZLSgnrPdf(){
	var url = path + "/expPdf";
	ExportUtil.doExport(url, ExportUtil.TYPE_PDF, entityCnName+"信息", dataGrid_gczlYdxjSGZLSgnr, false);
}
/**
 * 【结构施工质量基础数据】刷新
 * @returns
 */
function reloadGczlYdxjSGZLSgnr(){
	dataGrid_gczlYdxjSGZLSgnr.datagrid('reload');
}

/** 【施工内容管理】打开新增页面 */
function addGczlYdxjSGZLSgnrFun() {
	var userType  = _getCurrOrgzType();
	if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}
	FormUtil.clearForm($("#addDialog_gczlYdxjSGZLSgnr"));
	createStaSelect("add_ifQy_gczlYdxjSGZLSgnr");
	$("#add_ifQy_gczlYdxjSGZLSgnr").combobox('setValue','启用');
	$("#addDialog_gczlYdxjSGZLSgnr").dialog('open').dialog('setTitle', '添加［'+entityCnName+"］");
}

/** 【施工内容管理】数据保存 */
function addGczlYdxjSGZLSgnrAjax() {
	$('#addForm_gczlYdxjSGZLSgnr').form('submit', {
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
				dataGrid_gczlYdxjSGZLSgnr.datagrid('reload');
				$("#addDialog_gczlYdxjSGZLSgnr").dialog('close');
				FormUtil.clearForm($("#addDialog_gczlYdxjSGZLSgnr"));
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}

/** 【施工内容管理】打开数据编辑页面 */
function editGczlYdxjSGZLSgnrFun() {
	var userType  = _getCurrOrgzType();
	if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}
	var id;
	var rows = dataGrid_gczlYdxjSGZLSgnr.datagrid('getSelections');
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
		$("#editDialog_gczlYdxjSGZLSgnr").dialog('open').dialog('setTitle', '编辑［'+entityCnName+'］'+'-'+rows[0][nameField]);
		createStaSelect("edit_ifQy_gczlYdxjSGZLSgnr");
		$('#editForm_gczlYdxjSGZLSgnr').form('load', rows[0]);
	}
}

/** 【施工内容管理】提交修改 */
function editGczlYdxjSGZLSgnrAjax() {
	var isValid = $('#editForm_gczlYdxjSGZLSgnr').form('validate');
	if (!isValid) return;
	$.messager.confirm('提示', '您确定要编辑［'+entityCnName + '］吗？', function(b) {
		if (b) {
			$('#editForm_gczlYdxjSGZLSgnr').form('submit', {
				url : path+'/edit',
				onSubmit : function() {
				},
				success : function(result) {
					progressClose();
					result = $.parseJSON(result);
					if (result.success) {
				        $.messager.show({title:'提示',msg:'修改操作：成功!',showType:'show' });
						dataGrid_gczlYdxjSGZLSgnr.datagrid('reload');
						$("#editDialog_gczlYdxjSGZLSgnr").dialog('close');
					}
					else {
						$.messager.alert('错误', result.msg, 'error');
					}
				}
			});
		}
	});
}

/** 【施工内容管理】数据删除 */
function deleteGczlYdxjSGZLSgnrFun() {
	var userType  = _getCurrOrgzType();
	if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}
	var ids = new Array();
	var rows = dataGrid_gczlYdxjSGZLSgnr.datagrid('getSelections');
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
				if (result.success) {
					$.messager.show({title:'提示',msg:'删除操作：成功!',showType:'show' });
					dataGrid_gczlYdxjSGZLSgnr.datagrid('reload');
					dataGrid_gczlYdxjSGZLSgnr.datagrid('clearSelections');
				}
				progressClose();
			}, 'JSON');
		}
	});
}

/** 【施工内容管理】查看数据页面 */
function showGczlYdxjSGZLSgnrFun(oid,index) {
	var id;
	var row;
	if(oid){
		id = oid;
		var allrows = dataGrid_gczlYdxjSGZLSgnr.datagrid('getRows');
		row = allrows[index];
	}else{
		var rows = dataGrid_gczlYdxjSGZLSgnr.datagrid('getSelections');
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

		$("#showSgNrDialog").dialog('open').dialog('setTitle', '查看［'+entityCnName+'］'+'-'+row[nameField]);
		$('#showSgNrForm').form('load', row);
		showJtwzList(id);
		showZlqxList(id);
	}
}

/** 【施工内容管理】表单查询 */
function searchGczlYdxjSGZLSgnrFun() {
	dataGrid_gczlYdxjSGZLSgnr.datagrid('load', $.serializeObject($('#searchForm_gczlYdxjSGZLSgnr')));
}

/**【施工内容管理】表单重置 */
function cleanGczlYdxjSGZLSgnrFun() {
	$('#searchForm_gczlYdxjSGZLSgnr input').val('');
	dataGrid_gczlYdxjSGZLSgnr.datagrid('load', {});
}



/**
 * 构造状态下拉框
 */
function createStaSelect(id){
	$("#"+id).combobox({
		required : false,
		valueField:'id',
		textField:'text',
		data: 
		[{
			id: '启用',
			text: '启用'
		 },{
			 id: '禁用',
			text: '禁用'
		 }]
	});
	/*点击文本框内，就显示下拉面板*/
	$("#"+id).combobox('textbox').click(function(){		
		$("#"+id).combobox('showPanel');
	});
	
}

/** 【施工内容管理】打开设置具体位置编辑页面 */
function editJtwzFun() {
	var id;
	var rows = dataGrid_gczlYdxjSGZLSgnr.datagrid('getSelections');
	if (rows.length > 1) {
		$.messager.alert('提示', '请选择单条数据进行设置！', 'info');
		return;
	} else if (rows.length == 1) {
		if (rows[0]) {
			id = rows[0].id;
		}else {
			$.messager.alert('提示', '请选择数据后再设置！', 'info');
			return;
		}
	} else {
		$.messager.alert('提示', '请选择数据后再设置！', 'info');
		return;
	}
	if (rows[0]) {
		$("#gczlYdxjSGZLJtwz_list").dialog('open').dialog('setTitle', '设置具体位置［'+entityCnName+'］'+'-'+rows[0][nameField]);
		initJtwzList(rows[0].id);
		$('#jtwzForm_sgnr').form('load', rows[0]);
	}
}

/** 【施工内容管理】打开设置质量缺陷编辑页面 */
function editZlqxFun() {
	var id;
	var rows = dataGrid_gczlYdxjSGZLSgnr.datagrid('getSelections');
	if (rows.length > 1) {
		$.messager.alert('提示', '请选择单条数据进行设置！', 'info');
		return;
	} else if (rows.length == 1) {
		if (rows[0]) {
			id = rows[0].id;
		}else {
			$.messager.alert('提示', '请选择数据后再设置！', 'info');
			return;
		}
	} else {
		$.messager.alert('提示', '请选择数据后再设置！', 'info');
		return;
	}
	if (rows[0]) {
		$("#gczlYdxjSGZLZlqx_list").dialog('open').dialog('setTitle', '设置质量缺陷［'+entityCnName+'］'+'-'+rows[0][nameField]);
		initZlqxList(rows[0].id);
		$('#zlqxForm_sgnr').form('load', rows[0]);
	}
}
function ifQyQ(){ifQy("1");}
function ifQyJ(){ifQy("0");}

/**
 * 启用、禁用
 * @param state 1、启用 2、禁用
 * @returns 
 */
function ifQy(state) {
	var userType  = _getCurrOrgzType();
	if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}
	var ids = new Array();
	var rows = dataGrid_gczlYdxjSGZLSgnr.datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请先选择要操作的数据！', 'info');
		return;
	}
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].id);
		if(state=='1' ){
			//启用状态的数据不可再次启用
			if(rows[i].ifQy !=null && rows[i].ifQy =='启用'){
				$.messager.alert('提示', '选择的施工内容基础数据使用状态为启用，操作失败。', 'info');
				return;
			}
		}
		if(state=='0' ){
			//启用状态的数据不可再次启用
			if(rows[i].ifQy !=null && rows[i].ifQy =='禁用'){
				$.messager.alert('提示', '选择的施工内容基础数据使用状态为禁用，操作失败。', 'info');
				return;
			}
		}
	}
	$.messager.confirm('询问', '您是否要处理［'+entityCnName + '］所选行吗？', function(b) {
		if (b) {
			progressLoad();
			$.post(path+'/ifQy', {
				ids : ids.join(","),
				state:state
			}, function(result) {
				if (result.success) {
					$.messager.show({title:'提示',msg:result.msg,showType:'show' });
					dataGrid_gczlYdxjSGZLSgnr.datagrid('reload');
					dataGrid_gczlYdxjSGZLSgnr.datagrid('clearSelections');
				}
				progressClose();
			}, 'JSON');
		}
	});
}

/**
 * 查看具体位置列表信息
 * @returns
 */
function showJtwzList(sgNr){
	/** 创建数据表格	 */
	$('#show_dataGrid_gczlYdxjSGZLJtwz').datagrid({
		url : jtwz_path+'/dataGrid.action?sgNr='+sgNr,
		fitColumns:true,
		striped : false,
		rownumbers : true,
		singleSelect : true,
		checkOnSelect : true,
		selectOnCheck : true,
		pagination : true,
		idField : 'id',
		pageSize : 5,
		pageList : [ 5, 10, 15, 20 ],
		columns:[[
           {width: '300',title: '具体位置',field: 'jtWz'},
           {width: '100',title: '使用状态',field: 'ifQy'}
		]]
	});
}

/**
* 查看质量缺陷列表信息
* @returns
*/
function showZlqxList(sgNr){
	/** 创建数据表格	 */
	 $('#show_dataGrid_gczlYdxjSGZLZlqx').datagrid({
		url : zlqx_path+'/dataGrid.action?sgNr='+sgNr,
		striped : false,
		fitColumns:true,
		rownumbers : true,
		singleSelect : true,
		checkOnSelect : true,
		selectOnCheck : true,
		pagination : true,
		idField : 'id',
		pageSize : 5,
		pageList : [ 5, 10, 15, 20 ],
		columns:[[
           {width: '300',title: '质量缺陷',field: 'zlQx'},
           {width: '100',title: '使用状态',field: 'ifQy'}
		]]
	});
}




//==========================================结构施工质量基础信息--具体位置信息操作方法=========================================================//
/**
 * 初始化具体位置列表信息
 * @returns
 */
function initJtwzList(sgNr){
	/** 创建数据表格	 */
	dataGrid_gczlYdxjSGZLJtwz = $('#dataGrid_gczlYdxjSGZLJtwz').datagrid({
		url : jtwz_path+'/dataGrid.action?sgNr='+sgNr,
		fitColumns:true,
		
		striped : false,
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
           {width: '80',title: '排序号',field: 'sortNum'},
           {width: '200',title: '具体位置',field: 'jtWz',
        	   formatter: function(value,row,index){
	   				return '<a href="#" onclick="showGczlYdxjSGZLJtwzFun(\''+ row.id +'\',\''+index+'\')">' + value + '</a>';
             }
           },
           {width: '100',title: '使用状态',field: 'ifQy'},
           {width: '300',title: '具体位置说明',field: 'remarks',
        	   formatter: function(value,row,index){
   				return DataGridFormatter.tipsFormatter(value,20);
   			   }
           }
           
		]],
		onLoadSuccess : function(data) {},
		onOpen:function(){
			try{
				if(dataGrid_gczlYdxjSGZLJtwz){
					dataGrid_gczlYdxjSGZLJtwz.datagrid('clearSelections');
				}
			}catch(e){}
			},
		toolbar : [ 
					{text : '刷新',iconCls: 'icon-reload',
					    handler: function(){
					    	dataGrid_gczlYdxjSGZLJtwz.datagrid('reload');
					    }
					},'-',
		            {text : "添加",iconCls : 'icon-add',handler : addGczlYdxjSGZLJtwzFun},'-',
		            {text : "编辑",iconCls : 'icon-edit',handler : editGczlYdxjSGZLJtwzFun},	'-', 
		            {text : "删除",iconCls : 'icon-del',handler : deleteGczlYdxjSGZLJtwzFun},'-', 
		            {text: '取消已选',iconCls: 'icon-undo',
		    			handler: function(){
		    				dataGrid_gczlYdxjSGZLJtwz.datagrid('clearSelections');
		    			}
		    		},
		            '-',
		    		{
		    			text: '导出Excel',
		    			iconCls: 'icon-page_excel',
		    			handler: function(){
		    				var url = jtwz_path + "/expXls.action?sgNr="+sgNr;
		    				ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, entityCnName_jtwz+"信息", dataGrid_gczlYdxjSGZLJtwz, false);
		    			}
		    		},'-',{
		    			text: '导出Pdf',
		    			iconCls: 'icon-page_white_acrobat',
		    			handler: function(){
		    				var url = jtwz_path + "/expPdf.action?sgNr="+sgNr;
		    				ExportUtil.doExport(url, ExportUtil.TYPE_PDF, entityCnName_jtwz+"信息", dataGrid_gczlYdxjSGZLJtwz, false);
		    			}
		    		},'-',
		    		 {text : "启用",iconCls : 'icon-edit',handler : ifQyWzQ},'-', 
		    		 {text : "禁用",iconCls : 'icon-edit',handler : ifQyWzJ}
		           ]
	});
}


/** 【结构施工质量基础数据-具体位置】打开新增页面 */
function addGczlYdxjSGZLJtwzFun() {
	var userType  = _getCurrOrgzType();
	if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}
	FormUtil.clearForm($("#addDialog_gczlYdxjSGZLJtwz"));
	$("#addDialog_gczlYdxjSGZLJtwz").dialog('open').dialog('setTitle', '添加［'+entityCnName_jtwz+"］");
	var sgNrId = $("#jtwz_sgnr_id").val();
	$("#add_sgNr_gczlYdxjSGZLJtwz").val(sgNrId);
	var sgNrName = $("#jtwz_sgNr_gczlYdxjSGZLSgnr").val();
	$("#add_sgNrName_gczlYdxjSGZLJtwz").html(sgNrName);
	createJtwzStaSelect("add_ifQy_gczlYdxjSGZLJtwz");
	$("#add_ifQy_gczlYdxjSGZLJtwz").combobox('setValue','启用');
}

/** 【结构施工质量基础数据-具体位置】数据保存 */
function addGczlYdxjSGZLJtwzAjax() {
	
	$('#addForm_gczlYdxjSGZLJtwz').form('submit', {
		url : jtwz_path+'/add',
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
				dataGrid_gczlYdxjSGZLJtwz.datagrid('reload');
				dataGrid_gczlYdxjSGZLSgnr.datagrid('reload');
				dataGrid_gczlYdxjSGZLJtwz.datagrid('clearSelections');
				$("#addDialog_gczlYdxjSGZLJtwz").dialog('close');
				FormUtil.clearForm($("#addDialog_gczlYdxjSGZLJtwz"));
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}

/** 【结构施工质量基础数据-具体位置】打开数据编辑页面 */
function editGczlYdxjSGZLJtwzFun() {
	var userType  = _getCurrOrgzType();
	if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}
	var id;
	var rows = dataGrid_gczlYdxjSGZLJtwz.datagrid('getSelections');
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
		$("#editDialog_gczlYdxjSGZLJtwz").dialog('open').dialog('setTitle', '编辑［'+entityCnName_jtwz+'］'+'-'+rows[0].jtWz);
		createJtwzStaSelect("edit_ifQy_gczlYdxjSGZLJtwz");
		var sgNrName = $("#jtwz_sgNr_gczlYdxjSGZLSgnr").val();
		$("#edit_sgNrName_gczlYdxjSGZLJtwz").html(sgNrName);
		$('#editForm_gczlYdxjSGZLJtwz').form('load', rows[0]);
	}
}

/** 【结构施工质量基础数据-具体位置】提交修改 */
function editGczlYdxjSGZLJtwzAjax() {
	var isValid = $('#editForm_gczlYdxjSGZLJtwz').form('validate');
	if (!isValid) return;
	$.messager.confirm('提示', '您确定要编辑［'+entityCnName_jtwz + '］吗？', function(b) {
		if (b) {
			$('#editForm_gczlYdxjSGZLJtwz').form('submit', {
				url : jtwz_path+'/edit',
				onSubmit : function() {
				},
				success : function(result) {
					progressClose();
					result = $.parseJSON(result);
					if (result.success) {
				$.messager.show({title:'提示',msg:'修改操作：成功!',showType:'show' });
						dataGrid_gczlYdxjSGZLJtwz.datagrid('reload');
						dataGrid_gczlYdxjSGZLSgnr.datagrid('reload');
						dataGrid_gczlYdxjSGZLJtwz.datagrid('clearSelections');
						$("#editDialog_gczlYdxjSGZLJtwz").dialog('close');
					}
					else {
						$.messager.alert('错误', result.msg, 'error');
					}
				}
			});
		}
	});
}

/** 【结构施工质量基础数据-具体位置】数据删除 */
function deleteGczlYdxjSGZLJtwzFun() {
	var userType  = _getCurrOrgzType();
	if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}
	var ids = new Array();
	var rows = dataGrid_gczlYdxjSGZLJtwz.datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择数据后再删除！', 'info');
		return;
	}
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].id);
	}
	$.messager.confirm('询问', '您是否要删除［'+entityCnName_jtwz + '］所选行吗？', function(b) {
		if (b) {
			progressLoad();
			$.post(jtwz_path+'/delete', {
				ids : ids.join(",")
			}, function(result) {
				if (result.success) {
					$.messager.show({title:'提示',msg:'删除操作：成功!',showType:'show' });
					dataGrid_gczlYdxjSGZLJtwz.datagrid('reload');
					dataGrid_gczlYdxjSGZLSgnr.datagrid('reload');
					dataGrid_gczlYdxjSGZLJtwz.datagrid('clearSelections');
				}
				progressClose();
			}, 'JSON');
		}
	});
}

/** 【结构施工质量基础数据-具体位置】查看数据页面 */
function showGczlYdxjSGZLJtwzFun(oid,index) {
	var id;
	var row;
	if(oid){
		id = oid;
		var allrows = dataGrid_gczlYdxjSGZLJtwz.datagrid('getRows');
		row = allrows[index];
	}else{
		var rows = dataGrid_gczlYdxjSGZLJtwz.datagrid('getSelections');
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
		$("#showDialog_gczlYdxjSGZLJtwz").dialog('open').dialog('setTitle', '查看［'+entityCnName_jtwz+'］'+'-'+row.jtWz);
		$('#showForm_gczlYdxjSGZLJtwz').form('load', row);
		var sgNrName = $("#jtwz_sgNr_gczlYdxjSGZLSgnr").val();
		$("#show_sgNrName_gczlYdxjSGZLJtwz").html(sgNrName);
	}
}

/**
 * 构造具体位置状态下拉框
 */
function createJtwzStaSelect(id){
	$("#"+id).combobox({
		required : false,
		valueField:'id',
		textField:'text',
		data: 
		[{
			id: '启用',
			text: '启用'
		 },{
			 id: '禁用',
			text: '禁用'
		 }]
	});
	/*点击文本框内，就显示下拉面板*/
	$("#"+id).combobox('textbox').click(function(){		
		$("#"+id).combobox('showPanel');
	});
	
}


function ifQyWzQ(){ifQyWz("1");}
function ifQyWzJ(){ifQyWz("0");}

/**
 * 启用、禁用 具体位置
 * @param state 1、启用 2、禁用
 * @returns 
 */
function ifQyWz(state) {
	var userType  = _getCurrOrgzType();
	if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}
	var ids = new Array();
	var rows = dataGrid_gczlYdxjSGZLJtwz.datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请先选择要操作的数据！', 'info');
		return;
	}
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].id);
		if(state=='1' ){
			//启用状态的数据不可再次启用
			if(rows[i].ifQy !=null && rows[i].ifQy =='启用'){
				$.messager.alert('提示', '选择的具体位置基础数据使用状态为启用，操作失败。', 'info');
				return;
			}
		}
		if(state=='0' ){
			//启用状态的数据不可再次启用
			if(rows[i].ifQy !=null && rows[i].ifQy =='禁用'){
				$.messager.alert('提示', '选择的具体位置基础数据使用状态为禁用，操作失败。', 'info');
				return;
			}
		}
	}
	$.messager.confirm('询问', '您是否要处理［'+entityCnName_jtwz + '］所选行吗？', function(b) {
		if (b) {
			progressLoad();
			$.post(jtwz_path+'/ifQy', {
				ids : ids.join(","),
				state:state
			}, function(result) {
				if (result.success) {
					$.messager.show({title:'提示',msg:result.msg,showType:'show' });
					dataGrid_gczlYdxjSGZLJtwz.datagrid('reload');
					dataGrid_gczlYdxjSGZLJtwz.datagrid('clearSelections');
				}
				progressClose();
			}, 'JSON');
		}
	});
}

//==========================================【结构施工质量基础信息--质量缺陷信息】操作方法=========================================================//
/**
* 初始化质量缺陷列表信息
* @returns
*/
function initZlqxList(sgNr){
	/** 创建数据表格	 */
	dataGrid_gczlYdxjSGZLZlqx = $('#dataGrid_gczlYdxjSGZLZlqx').datagrid({
		url : zlqx_path+'/dataGrid.action?sgNr='+sgNr,
		fitColumns:true,
		striped : false,
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
           {width: '80',title: '排序号',field: 'sortNum'},
           {width: '180',title: '质量缺陷',field: 'zlQx',
        	   formatter: function(value,row,index){
	   				return '<a href="#" onclick="showGczlYdxjSGZLZlqxFun(\''+ row.id +'\',\''+index+'\')">' + value + '</a>';
        	   }
           },
           {width: '80',title: '使用状态',field: 'ifQy'},
           {width: '200',title: '质量缺陷说明',field: 'remarks',
        	   formatter: function(value,row,index){
					return DataGridFormatter.tipsFormatter(value,20);
				}
           }
		]],
		onLoadSuccess : function(data) {},
		onOpen:function(){
			try{
				if(dataGrid_gczlYdxjSGZLZlqx){
					dataGrid_gczlYdxjSGZLZlqx.datagrid('clearSelections');
				}
			}catch(e){}
			},
		toolbar : [ 
					{text : '刷新',iconCls: 'icon-reload',
					    handler: function(){
					    	dataGrid_gczlYdxjSGZLZlqx.datagrid('reload');
					    }
					},'-',
		            {text : "添加",iconCls : 'icon-add',handler : addGczlYdxjSGZLZlqxFun},'-',
		            {text : "编辑",iconCls : 'icon-edit',handler : editGczlYdxjSGZLZlqxFun},	'-', 
		            {text : "删除",iconCls : 'icon-del',handler : deleteGczlYdxjSGZLZlqxFun},'-', 
		            {text: '取消已选',iconCls: 'icon-undo',
		    			handler: function(){
		    				dataGrid_gczlYdxjSGZLZlqx.datagrid('clearSelections');
		    			}
		    		},
		            '-',
		    		{
		    			text: '导出Excel',
		    			iconCls: 'icon-page_excel',
		    			handler: function(){
		    				var url = zlqx_path + "/expXls.action?sgNr="+sgNr;
		    				ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, entityCnName_zlqx+"信息", dataGrid_gczlYdxjSGZLZlqx, false);
		    			}
		    		},'-',{
		    			text: '导出Pdf',
		    			iconCls: 'icon-page_white_acrobat',
		    			handler: function(){
		    				var url = zlqx_path + "/expPdf.action?sgNr="+sgNr;
		    				ExportUtil.doExport(url, ExportUtil.TYPE_PDF, entityCnName_zlqx+"信息", dataGrid_gczlYdxjSGZLZlqx, false);
		    			}
		    		},'-',
		    		{text : "启用",iconCls : 'icon-edit',handler : ifQyZlQxQ},'-', 
		    		{text : "禁用",iconCls : 'icon-edit',handler : ifQyZlQxJ}
		           ]
	});
}


/** 【结构施工质量基础数据-质量缺陷】打开新增页面 */
function addGczlYdxjSGZLZlqxFun() {
	var userType  = _getCurrOrgzType();
	if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}
	FormUtil.clearForm($("#addDialog_gczlYdxjSGZLZlqx"));
	$("#addDialog_gczlYdxjSGZLZlqx").dialog('open').dialog('setTitle', '添加［'+entityCnName_zlqx+"］");
	var sgNrId = $("#zlqx_sgnr_id").val();
	$("#add_sgNr_gczlYdxjSGZLZlqx").val(sgNrId);
	var sgNrName = $("#zlqx_sgNr_gczlYdxjSGZLSgnr").val();
	$("#add_sgNrName_gczlYdxjSGZLZlqx").html(sgNrName);
	createZlQxStaSelect("add_ifQy_gczlYdxjSGZLZlqx");
	$("#add_ifQy_gczlYdxjSGZLZlqx").combobox('setValue','启用');
}

/** 【结构施工质量基础数据-质量缺陷】数据保存 */
function addGczlYdxjSGZLZlqxAjax() {
	$('#addForm_gczlYdxjSGZLZlqx').form('submit', {
		url : zlqx_path+'/add',
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
				dataGrid_gczlYdxjSGZLZlqx.datagrid('reload');
				dataGrid_gczlYdxjSGZLSgnr.datagrid('reload');
				$("#addDialog_gczlYdxjSGZLZlqx").dialog('close');
				dataGrid_gczlYdxjSGZLZlqx.datagrid('clearSelections');
				FormUtil.clearForm($("#addDialog_gczlYdxjSGZLZlqx"));
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}

/** 【结构施工质量基础数据-质量缺陷】打开数据编辑页面 */
function editGczlYdxjSGZLZlqxFun() {
	var userType  = _getCurrOrgzType();
	if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}
	var id;
	var rows = dataGrid_gczlYdxjSGZLZlqx.datagrid('getSelections');
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
		$("#editDialog_gczlYdxjSGZLZlqx").dialog('open').dialog('setTitle', '编辑［'+entityCnName_zlqx+'］'+'-'+rows[0].zlQx);
		createZlQxStaSelect("edit_ifQy_gczlYdxjSGZLZlqx");
		$('#editForm_gczlYdxjSGZLZlqx').form('load', rows[0]);
		var sgNrName = $("#zlqx_sgNr_gczlYdxjSGZLSgnr").val();
		$("#edit_sgNrName_gczlYdxjSGZLZlqx").html(sgNrName);
	}
}

/** 【结构施工质量基础数据-质量缺陷】提交修改 */
function editGczlYdxjSGZLZlqxAjax() {
	var isValid = $('#editForm_gczlYdxjSGZLZlqx').form('validate');
	if (!isValid) return;
	$.messager.confirm('提示', '您确定要编辑［'+entityCnName_zlqx + '］吗？', function(b) {
		if (b) {
			$('#editForm_gczlYdxjSGZLZlqx').form('submit', {
				url : zlqx_path+'/edit',
				onSubmit : function() {
				},
				success : function(result) {
					progressClose();
					result = $.parseJSON(result);
					if (result.success) {
				$.messager.show({title:'提示',msg:'修改操作：成功!',showType:'show' });
						dataGrid_gczlYdxjSGZLZlqx.datagrid('reload');
						dataGrid_gczlYdxjSGZLSgnr.datagrid('reload');
						dataGrid_gczlYdxjSGZLZlqx.datagrid('clearSelections');
						$("#editDialog_gczlYdxjSGZLZlqx").dialog('close');
					}
					else {
						$.messager.alert('错误', result.msg, 'error');
					}
				}
			});
		}
	});
}

/** 【结构施工质量基础数据-质量缺陷】数据删除 */
function deleteGczlYdxjSGZLZlqxFun() {
	var userType  = _getCurrOrgzType();
	if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}
	var ids = new Array();
	var rows = dataGrid_gczlYdxjSGZLZlqx.datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择数据后再删除！', 'info');
		return;
	}
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].id);
	}
	$.messager.confirm('询问', '您是否要删除［'+entityCnName_zlqx + '］所选行吗？', function(b) {
		if (b) {
			progressLoad();
			$.post(zlqx_path+'/delete', {
				ids : ids.join(",")
			}, function(result) {
				if (result.success) {
					$.messager.show({title:'提示',msg:'删除操作：成功!',showType:'show' });
					dataGrid_gczlYdxjSGZLZlqx.datagrid('reload');
					dataGrid_gczlYdxjSGZLSgnr.datagrid('reload');
					dataGrid_gczlYdxjSGZLZlqx.datagrid('clearSelections');
				}
				progressClose();
			}, 'JSON');
		}
	});
}

/** 【结构施工质量基础数据-质量缺陷】查看数据页面 */
function showGczlYdxjSGZLZlqxFun(oid,index) {
	var id;
	var row;
	if(oid){
		id = oid;
		var allrows = dataGrid_gczlYdxjSGZLZlqx.datagrid('getRows');
		row = allrows[index];
	}else{
		var rows = dataGrid_gczlYdxjSGZLZlqx.datagrid('getSelections');
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
		$("#showDialog_gczlYdxjSGZLZlqx").dialog('open').dialog('setTitle', '查看［'+entityCnName_zlqx+'］'+'-'+row.zlQx);
		$('#showDialog_gczlYdxjSGZLZlqx').form('load', row);
		var sgNrName = $("#zlqx_sgNr_gczlYdxjSGZLSgnr").val();
		$("#show_sgNrName_gczlYdxjSGZLZlqx").html(sgNrName);
	}
}


/**
 * 构造具体位置状态下拉框
 */
function createZlQxStaSelect(id){
	$("#"+id).combobox({
		required : false,
		valueField:'id',
		textField:'text',
		data: 
		[{
			id: '启用',
			text: '启用'
		 },{
			 id: '禁用',
			text: '禁用'
		 }]
	});
	/*点击文本框内，就显示下拉面板*/
	$("#"+id).combobox('textbox').click(function(){		
		$("#"+id).combobox('showPanel');
	});
	
}


function ifQyZlQxQ(){ifQyZlQx("1");}
function ifQyZlQxJ(){ifQyZlQx("0");}

/**
 * 启用、禁用 具体位置
 * @param state 1、启用 2、禁用
 * @returns 
 */
function ifQyZlQx(state) {
	var userType  = _getCurrOrgzType();
	if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}
	var ids = new Array();
	var rows = dataGrid_gczlYdxjSGZLZlqx.datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请先选择要操作的数据！', 'info');
		return;
	}
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].id);
		if(state=='1' ){
			//启用状态的数据不可再次启用
			if(rows[i].ifQy !=null && rows[i].ifQy =='启用'){
				$.messager.alert('提示', '选择的质量缺陷基础数据使用状态为启用，操作失败。', 'info');
				return;
			}
		}
		if(state=='0' ){
			//启用状态的数据不可再次启用
			if(rows[i].ifQy !=null && rows[i].ifQy =='禁用'){
				$.messager.alert('提示', '选择的质量缺陷基础数据使用状态为禁用，操作失败。', 'info');
				return;
			}
		}
	}
	$.messager.confirm('询问', '您是否要处理［'+entityCnName_zlqx + '］所选行吗？', function(b) {
		if (b) {
			progressLoad();
			$.post(zlqx_path+'/ifQy', {
				ids : ids.join(","),
				state:state
			}, function(result) {
				if (result.success) {
					$.messager.show({title:'提示',msg:result.msg,showType:'show' });
					dataGrid_gczlYdxjSGZLZlqx.datagrid('reload');
					dataGrid_gczlYdxjSGZLZlqx.datagrid('clearSelections');
				}
				progressClose();
			}, 'JSON');
		}
	});
}


