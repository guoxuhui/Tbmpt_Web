/** /** 【培训人员管理】安全生产管理 基础信息
 * 创建时间：2017-02-20
   */
/** 系统模块同路径 */
var syspath = "aqsc";
/** 子模块路径 */
var module = "base";
/** 菜单名称 */
var entityCnName = "培训人员管理";
/** 名称字段 */
var nameField = "name";
/** 业务数据访问全路径 */
var path = basePath +"/"+ syspath +"/"+ module+"/peiXunRenYuan";
/**若无一条勾选数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayZero = ["cancelSelect_button_peiXunRenYuan","edit_button_peiXunRenYuan","del_button_peiXunRenYuan"];
/**若只勾选一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayOnlyOneItem = [];
/**若勾选大于一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayMoreOneItem =["edit_button_peiXunRenYuan"];
var _gSort = "";//定义默认排序字段

/** 列表对象 */
var dataGrid_peiXunRenYuan;
$(function() {
	/** 创建数据表格	 */
	dataGrid_peiXunRenYuan = $('#dataGrid_peiXunRenYuan').datagrid({
		url : path+'/dataGrid',
		striped : true,
		fitColumns:true,
		rownumbers : true,
		singleSelect : false,
		checkOnSelect : true,
		selectOnCheck : true,
		pagination : true,
		idField : 'id',
		pageSize : 15,
		sortName : 'createTime',
		sortOrder : 'desc',
		pageList : [ 10, 15, 20, 50 ],
		frozenColumns:
	        [[
	        	{field:'id',align:'center',title:'ID',width:40, checkbox:true},
	        	{width: '80',title: '姓名',field: 'name',
	        		formatter: function(value,row,index){
	        			return '<a href="#" onclick="showPeiXunRenYuanFun(\''+ row.id +'\',\''+index+'\')">' + value + '</a>';
	        		}
	        	},
	        	{width: '80',title: '性别',field: 'sex',sortable : true,
	        	},
	        	{width: '200',title: '身份证号',field: 'cardNo'}
	 		]],
		columns:[[
           {width: '200',title: '生日',field: 'birthDay',sortable : true},
           {width: '80',title: '年龄',field: 'age'},
           {width: '80',title: '工种',field: 'gongzhong',sortable : true},
           {width: '100',title: '联系电话',field: 'phone'},
           {width: '200',title: '家庭住址',field: 'adress',
        	   formatter: function(value,row,index){
					return DataGridFormatter.tipsFormatter(value,20);
				}
           },
           {width: '80',title: '入职日期',field: 'inDate',sortable : true},
           {width: '80',title: '离职日期',field: 'outDate'},
           {width: '80',title: '培训时间',field: 'peixunTime',sortable : true},
           {width: '80',title: '状态',field: 'state',sortable : true},
           {width: '150',title: '单位名称',field: 'demptName',sortable : true,
        	   formatter: function(value,row,index){
					return DataGridFormatter.tipsFormatter(value,20);
				}
           },
           {width: '100',title: '备注',field: 'remark',
        	   formatter: function(value,row,index){
					return DataGridFormatter.tipsFormatter(value,20);
				}
           }
		]],
		onLoadSuccess : function(data) {},
		toolbar : '#toolbar' ,
		rowStyler:function(index,row){
			var age = row.age;
			if (age>=50 && age<=55){
				return 'background-color:#FFFF00;';
			}else if(age>55){
				return 'background-color:#EE6363;';
			}
		},
		onLoadSuccess : function(data) {
			hiddenButtonBase("dataGrid_peiXunRenYuan",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
		},
        onCheck : function(){
        	hiddenButtonBase("dataGrid_peiXunRenYuan",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onUncheck : function(){
        	hiddenButtonBase("dataGrid_peiXunRenYuan",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onCheckAll : function(){
        	hiddenButtonBase("dataGrid_peiXunRenYuan",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onUncheckAll : function(){
        	hiddenButtonBase("dataGrid_peiXunRenYuan",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onClickRow:function(){
        	hiddenButtonBase("dataGrid_peiXunRenYuan",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onBeforeLoad:function(param){  
        	if(null != _gSort && _gSort !='' && _gSort !=undefined){
        		param.sort='createTime';
        		param.order = 'desc';
        	}
        }  
	});
	createSexSelect('search_sex');
	createStateSelect('search_state');
});

/**
 * 方法说明：构建性别下拉框
 * @param id 页面显示id
 * @param required 是否必填
 * @returns 
 * @author:YangYj
 * @Time: 2017年2月20日 下午4:21:42
 */
function createStateSelect(id,required){
	if(null == required || '' == required || required == undefined){
		required = false;
	}
	
	$("#"+id).combobox({
		required : false,
		valueField:'id',
		textField:'text',
		data: 
		[{
			id: '在职',
			text: '在职'
		 },{
			 id: '离职',
			text: '离职'
		 }]
	});
	/*点击文本框内，就显示下拉面板*/
	$("#"+id).combobox('textbox').click(function(){		
		$("#"+id).combobox('showPanel');
	});
	
}

/** 【培训人员管理】打开新增页面 */
function addPeiXunRenYuanFun() {
	var userType  = _getCurrOrgzType();
	if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}
	FormUtil.clearForm($("#addDialog_peiXunRenYuan"));
	$("#addDialog_peiXunRenYuan").dialog('open').dialog('setTitle', '添加［'+entityCnName+"］");
	createStateSelect('add_state');
	$("#add_state").combobox('setValue','在职');
}

/** 【培训人员管理】数据保存 */
function addPeiXunRenYuanAjax() {
	$('#addForm_peiXunRenYuan').form('submit', {
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
				_gSort = "createTime";
				dataGrid_peiXunRenYuan.datagrid('reload');
				_gSort = "";
				$("#addDialog_peiXunRenYuan").dialog('close');
				FormUtil.clearForm($("#addDialog_peiXunRenYuan"));
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}

/** 【培训人员管理】打开数据编辑页面 */
function editPeiXunRenYuanFun() {
	var userType  = _getCurrOrgzType();
	if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}
	var id;
	var rows = dataGrid_peiXunRenYuan.datagrid('getSelections');
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
		$("#editDialog_peiXunRenYuan").dialog('open').dialog('setTitle', '编辑［'+entityCnName+'］'+'-'+rows[0][nameField]);
		createStateSelect('edit_state');
		$('#editForm_peiXunRenYuan').form('load', rows[0]);
	}
}

/** 【培训人员管理】提交修改 */
function editPeiXunRenYuanAjax() {
	var isValid = $('#editForm_peiXunRenYuan').form('validate');
	if (!isValid) return;
	$.messager.confirm('提示', '您确定要编辑［'+entityCnName + '］吗？', function(b) {
		if (b) {
			$('#editForm_peiXunRenYuan').form('submit', {
				url : path+'/edit',
				onSubmit : function() {
				  progressLoad();
				},
				success : function(result) {
					progressClose();
					result = $.parseJSON(result);
					if (result.success) {
				        $.messager.show({title:'提示',msg:'修改操作：成功!',showType:'show' });
						dataGrid_peiXunRenYuan.datagrid('reload');
						$("#editDialog_peiXunRenYuan").dialog('close');
					}
					else {
						$.messager.alert('错误', result.msg, 'error');
					}
				}
			});
		}
	});
}

/** 【培训人员管理】数据删除 */
function deletePeiXunRenYuanFun() {
	var userType  = _getCurrOrgzType();
	if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}
	var ids = new Array();
	var rows = dataGrid_peiXunRenYuan.datagrid('getSelections');
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
				progressClose();
				if (result.success) {
					$.messager.show({title:'提示',msg:'删除操作：成功!',showType:'show' });
					dataGrid_peiXunRenYuan.datagrid('reload');
					dataGrid_peiXunRenYuan.datagrid('clearSelections');
				}
			}, 'JSON');
		}
	});
}

/** 【培训人员管理】查看数据页面 */
function showPeiXunRenYuanFun(oid,index) {
	var id;
	var row;
	if(oid){
		id = oid;
		var allrows = dataGrid_peiXunRenYuan.datagrid('getRows');
		row = allrows[index];
	}else{
		var rows = dataGrid_peiXunRenYuan.datagrid('getSelections');
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
		$("#showDialog_peiXunRenYuan").dialog('open').dialog('setTitle', '查看［'+entityCnName+'］'+'-'+row[nameField]);
		$('#showForm_peiXunRenYuan').form('load', row);
	}
}

/**
 * 【培训人员管理】导出excel
 * @returns
 */
function expPeiXunRenYuanXls(){
	var url = path + "/expXls";
	ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, entityCnName+"信息", dataGrid_peiXunRenYuan, false);
}

/**
 * 【培训人员管理】刷新
 * @returns
 */
function reloadPeiXunRenYuan(){
	dataGrid_peiXunRenYuan.datagrid('reload');
}

/**
 * 【培训人员管理】取消已选
 * @returns
 */
function clearPeiXunRenYuanSelections(){
	dataGrid_peiXunRenYuan.datagrid('clearSelections');
}/** 【培训人员管理】表单查询 */
function searchPeiXunRenYuanFun() {
	dataGrid_peiXunRenYuan.datagrid('load', $.serializeObject($('#searchForm_peiXunRenYuan')));
}

/**【培训人员管理】表单重置 */
function cleanPeiXunRenYuanFun() {
	$('#searchForm_peiXunRenYuan input').val('');
	dataGrid_peiXunRenYuan.datagrid('load', {});
}


/**
 * 导入
 */
function impFun(){
	
	FormUtil.clearForm($("#impDialog"));
	$("#impDialog").dialog('open').dialog('setTitle', '添加［'+moduleName+"］");
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
		    			width:'320px',
		    			height:'180px'
		    		}); 
	            $('#impDialog').dialog('close'); 
	            dataGrid_peiXunRenYuan.datagrid('reload');
	        } else {
	            parent.$.messager.alert('错误', result.msg, 'error');
	        }
    	}
   });
}







