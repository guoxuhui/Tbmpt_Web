/**
 * 
 */
/** 系统模块同路径 */
var syspath = "gczlys";
/** 业务数据访问全路径 */
var path = basePath +"/"+ syspath +"/yanShouYingXiang";
/** 菜单名称 */
var entityCnName = "验收影像资料";
/**
 * 模块名称
 */
var moduleName = "工程质量验收";
/**若无一条勾选数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayZero = ["copy_button_YanShouYingXiang","edit_button_YanShouYingXiang","cancelSelect_button_YanShouYingXiang","uploadPic_button_yxzl","del_button_YanShouYingXiang","show_button_YanShouYingXiang"];
/**若勾选大于一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayMoreOneItem =["copy_button_YanShouYingXiang","edit_button_YanShouYingXiang","show_button_YanShouYingXiang","uploadPic_button_yxzl"];
var hiddenClumArrayOnlyOneItem = [];
/** 列表对象 */
/** 名称字段 */
var nameField = "miaoshu";
/** 列表对象 */
var yxys_dataGrid;
$(function() {
	/** 创建数据表格	 */
	yxys_dataGrid = $('#dataGrid_YanShouYingXiang').datagrid({
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
		   {width: '100',title: 'id',field: 'id',align:'center', checkbox:true},
		   {width: '200',title: '工程名称',field: 'projectname',
			   formatter: function(value,row,index){
					return DataGridFormatter.tipsFormatter(value,30);
				}
           },
           {width: '100',title: '工程部位',field: 'gcbuwei'},	
	    ]],
	    columns : [ [
           
           {width: '120',title: '分布分项工序名称',field: 'gongxu'},
           {width: '100',title: '施工班组',field: 'banzu'},
           {width: '100',title: '班组负责人',field: 'bzfuzr'},
           {width: '150',title: '验收时间',field: 'yanshousj'},
           {width: '100',title: '验收人',field: 'yanshour'},
           {width: '180',title: '现场检查实际情况',field: 'miaoshu',
        	   formatter: function(value,row,index){
        		   return '<a href="#" onclick="showFun(\''+ row.id +'\', \'' + index + '\')">' + DataGridFormatter.tipsFormatter(value,30) + '</a>';
//   				return '<a href="#" onclick="showFun(\''+ row.id +'\')">' + value + '</a>';
   			}
           },
           
           {width: '200',title: '检查验收意见和结论',field: 'jianchaqk',
        	   formatter: function(value,row,index){
					return DataGridFormatter.tipsFormatter(value,30);
				}
           }
            
		]],
		
		toolbar : '#toolbar',
		onLoadSuccess : function(data) {
			hiddenButtonBase("dataGrid_YanShouYingXiang",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
		},
        onCheck : function(){
        	hiddenButtonBase("dataGrid_YanShouYingXiang",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onUncheck : function(){
        	hiddenButtonBase("dataGrid_YanShouYingXiang",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onCheckAll : function(){
        	hiddenButtonBase("dataGrid_YanShouYingXiang",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onUncheckAll : function(){
        	hiddenButtonBase("dataGrid_YanShouYingXiang",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onClickRow:function(){
        	hiddenButtonBase("dataGrid_YanShouYingXiang",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        }
	});
	$('#searchForm_YanShouYingXiang').show();
	
	
});



function createProjectSelect(showId,required){
	$("#"+showId).combobox({
		required : required,
		valueField:'id',
		textField:'proName',
		url:basePath +"/project/info/getProjects"
	});
	/*点击文本框内，就显示下拉面板*/
	$("#"+showId).combobox('textbox').click(function(){		
		$("#"+showId).combobox('showPanel');
	});
}


/** 表单查询 */
function searchYanShouYingXiangFun() {
	yxys_dataGrid.datagrid('load', $.serializeObject($('#searchForm_YanShouYingXiang')));
}

/** 【验收影像】打开新增页面 */
function addYanShouYingXiangFun() {
	//if(_getCurrOrgzType() =='0'){$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');	return;}//非项目部下人员无权操作数据
	FormUtil.clearForm($("#addDialog_YanShouYingXiang"));
	createProjectSelect("add_gcid_YanShouYingXiang",true);
	$("#addDialog_YanShouYingXiang").dialog('open').dialog('setTitle', '添加［'+entityCnName+"］");
}

/** 【验收影像】数据保存 */
function addYanShouYingXiangAjax() {
	var prjName = $("#add_gcid_YanShouYingXiang").combobox('getText');
	$('#add_gcname_YanShouYingXiang').val(prjName);
	$('#addForm_YanShouYingXiang').form('submit', {
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
				yxys_dataGrid.datagrid('reload');
				$("#addDialog_YanShouYingXiang").dialog('close');
				yxys_dataGrid.datagrid('clearSelections');
				FormUtil.clearForm($("#addDialog_YanShouYingXiang"));
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}


/** 【盾构施工质量巡检信息】数据删除 */
function deleteYanShouYingXiangFun() {
	//if(_getCurrOrgzType() =='0'){$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');	return;}//非项目部下人员无权操作数据
	var ids = new Array();
	var rows = yxys_dataGrid.datagrid('getSelections');
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
					yxys_dataGrid.datagrid('reload');
					yxys_dataGrid.datagrid('clearSelections');
				}
				progressClose();
			}, 'JSON');
		}
	});
}

/** 【施工内容管理】打开数据编辑页面 */
function editYanShouYingXiangFun() {
	var userType  = _getCurrOrgzType(); 
	var id;
	var rows = yxys_dataGrid.datagrid('getSelections');
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
		$("#editDialog_YanShouYingXiang").dialog('open').dialog('setTitle', '编辑［'+entityCnName+'］'+'-'+rows[0][nameField]);
		$('#editForm_YanShouYingXiang').form('load', rows[0]);
		createProjectSelect("edit_gcid_YanShouYingXiang",true); 
	}
}

/** 【施工内容管理】提交修改 */
function editYanShouYingXiangAjax() {
	var prjName = $("#edit_gcid_YanShouYingXiang").combobox('getText');
	$('#edit_gcname_YanShouYingXiang').val(prjName);
	var isValid = $('#editForm_YanShouYingXiang').form('validate');
	if (!isValid) return;
	$.messager.confirm('提示', '您确定要编辑［'+entityCnName + '］吗？', function(b) {
		if (b) {
			$('#editForm_YanShouYingXiang').form('submit', {
				url : path+'/edit',
				onSubmit : function() {
				},
				success : function(result) {
					progressClose();
					result = $.parseJSON(result);
					if (result.success) {
				        $.messager.show({title:'提示',msg:'修改操作：成功!',showType:'show' });
				        yxys_dataGrid.datagrid('reload');
						$("#editDialog_YanShouYingXiang").dialog('close');
						yxys_dataGrid.datagrid('clearSelections');
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
 * 查看数据页面
 */
function showFun(currid,index) {
	var id;
	var row;
	if(currid){
		id = currid;
		var allrows = yxys_dataGrid.datagrid('getRows');
		row = allrows[index];
	}else{
		var rows = yxys_dataGrid.datagrid('getSelections');
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
	    /*$('#show_pid').combotree({
	        url : basePath+'/sys/orgz/tree?flag=false',
	        parentField : 'pid',
	        lines : true,
	        panelWidth:380,
	        panelHeight : 'auto'
	    });*/
		$('#showDialog').form('load', row);
		_showAllFileInDiv2(row.id,'img_div_yxYszlYs');
		disableForm('showForm_YanShouYingXiang',true);
	}
}


/** 打开复制页面 */
function copyYanShouYingXiangFun() {
	var userType  = _getCurrOrgzType(); 
	var id;
	var rows = yxys_dataGrid.datagrid('getSelections');
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
		$("#copyDialog_YanShouYingXiang").dialog('open').dialog('setTitle', '编辑［'+entityCnName+'］'+'-'+rows[0][nameField]);
		$('#copyForm_yanshouyingxiang').form('load', rows[0]);
		createProjectSelect("copy_gcid_YanShouYingXiang",true);
		
	}
}

/** 【验收影像】数据复制保存 */
function copyYanShouYingXiangAjax() {
	var prjName = $("#copy_gcid_YanShouYingXiang").combobox('getText');
	$('#copy_gcname_YanShouYingXiang').val(prjName);
	$('#copyForm_yanshouyingxiang').form('submit', {
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
				yxys_dataGrid.datagrid('reload');
				$("#copyDialog_YanShouYingXiang").dialog('close');
				yxys_dataGrid.datagrid('clearSelections');
				FormUtil.clearForm($("#copyDialog_YanShouYingXiang"));
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}


/*
 * 重置
 * */
function cleandYanShouYingXiang() {
	$('#searchForm_YanShouYingXiang input').val('');
	yxys_dataGrid.datagrid('load', {});
}


/*
 * 刷新
 * */
function reloadYanShouYingXiang(){
	yxys_dataGrid.datagrid('reload');
}



/**
 * 取消已选
 * @returns
 */
function clearYanShouYingXiangSelections(){
	yxys_dataGrid.datagrid('clearSelections');
}

/**
 * 上传图片
 * @returns
 */
function myUploadPicture(){
	var rows = yxys_dataGrid.datagrid('getSelections');
	if (rows.length > 1) {
		$.messager.alert('提示', '请选择单条数据进行操作！', 'info');
		return;
	}else if (rows.length == 0) {
		$.messager.alert('提示', '请选择数据后再操作！', 'info');
		return;
	}
	_uploadPicture(rows[0].id,syspath,'',yxys_dataGrid,entityCnName);
}