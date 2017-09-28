/**
 * 
 */
/** 系统模块同路径 */
var syspath = "gcaqxj";
/** 业务数据访问全路径 */
var path = basePath +"/"+ syspath +"/xjdfl";
/** 菜单名称 */
var entityCnName = "巡检点分类管理";
/**
 * 模块名称
 */
var moduleName = "工程项目安全巡检系统";
/**若无一条勾选数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayZero = ["edit_button_xunjiandianfenlei","delete__button_xunjiandianfenlei","edit_button_XunJianDianFenLei","cancelSelect_button_xunjiandianfenlei"];
/**若勾选大于一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayMoreOneItem =["add_button_xunjiandianfenlei","edit_button_xunjiandianfenlei","refresh_button_xunjiandianfenlei"];
var hiddenClumArrayOnlyOneItem = [];
/** 列表对象 */
/** 名称字段 */
var nameField = "typeName";
/** 名称字段 */
var yanshour = "yanshour";
/** 列表对象 */
var xjfl_dataGrid;
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
/**
 * 创建巡检分类下拉列表
 * @param showId
 * @param required
 */
function createTypeSelect(showId,required){
	$("#"+showId).combobox({
		required : required,
		valueField:'id',
		textField:'typeName',
		url:basePath +"/gcaqxj/xjdfl/getParentType"
	});
	/*点击文本框内，就显示下拉面板*/
	$("#"+showId).combobox('textbox').click(function(){
		$("#"+showId).combobox('showPanel');
	});
}
var treeGrid;
$(function() {

	/** 创建数据表格	 */
	treeGrid = $('#dataGrid_xunjiandianfenlei').treegrid({
		url : path+'/treeGrid',
		striped : true,
		rownumbers : true,
		singleSelect : true,
		idField : 'id',
		treeField : 'typeName',
		parentField : 'parentId',
		fit : true,
		fitColumns : false,
		animate:true,
		border : false,
		columns : [ [
			{width: '240',title: '工程名称',field: 'proiectName',
				formatter: function(value,row,index){
					return DataGridFormatter.tipsFormatter(value,50);
				}
			},
			{
				width: '200', title: '分类名称', field:  'typeName'
			},
			{width: '200',title: '备注',field: 'remark',
						formatter: function(value,row,index){
							return DataGridFormatter.tipsFormatter(value,30);
						}
					},
		] ],
		toolbar: '#toolbar'
	});
	$('#searchForm_XunJianDianFenLei').show();
	//初始化查询条件
	createProjectSelect("projectid_query_id",true);
});

/** 表单查询 */
function searchXunJianDianFenLeiFun() {
	var prjName = $("#projectid_query_id").combobox('getText');
	$('#proiectname_query').val(prjName);
	xjfl_dataGrid.datagrid('load', $.serializeObject($('#searchForm_XunJianDianFenLei')));
}


/**
 * 刷新
 */
function reloadFun(){
	treeGrid.treegrid('reload');
}

/**
 * 取消已选
 */
function clearXunJianDianFenLeiFunSelections(){
	treeGrid.treegrid('clearSelections');
}
/*
 * 重置
 * */
function cleandXunJianDianFenLeiFun() {
	$('#searchForm_XunJianDianFenLei input').val('');
	treeGrid.treegrid('reload', {});
	//xjfl_dataGrid.datagrid('load', {});
}

/**
 * 展开
 */
function expand(){
	var node = treeGrid.treegrid('getSelected');
	if(node){
		treeGrid.treegrid('expand', node.id);
	}else{
		treeGrid.treegrid('expandAll');
	}
}
/**
 * 折叠
 */
function collapse(){
	var node = treeGrid.treegrid('getSelected');
	if(node){
		treeGrid.treegrid('collapse', node.id);
	}else{
		treeGrid.treegrid('collapseAll');
	}
}

/**
 * 打开新增巡检分类界面
 */
function addXunJianDianFenLeiFun(){
	FormUtil.clearForm($("#addForm_XunJianFenLei"));
	createProjectSelect("projectid_commbox_add",true);
	createTypeSelect("typeParent_commbox_add",true);
	$("#addDialog_XunJianFenLei").dialog('open').dialog('setTitle', '添加［'+entityCnName+"］");
}

/**
 * 新增保存操作
 */
function addXunJianFenLeiAjax(){
	 
	var prjName = $("#projectid_commbox_add").combobox('getText');
	var prjId = $("#projectid_commbox_add").combobox('getValue');
	var parentId = $("#typeParent_commbox_add").combobox('getValue');
	var parentName =$("#typeParent_commbox_add").combobox('getText');

	$('#proiectname_add').val(prjName);
	$('#proiectid_add').val(prjId);
	$('#parentId_add').val(parentId);
	$("#parentName_add").val(parentName);
	$('#addForm_XunJianFenLei').form('submit', {
		url : path+'/add',
		onSubmit : function() {
			progressLoad();
			var isValid = $(this).form('validate');
			if (!isValid) {
			alert("表单验证未通过!");
				progressClose();
			}
			return isValid;
		},
		success : function(result) {
			progressClose();
			result = $.parseJSON(result);
			if (result.success) {
				$.messager.show({title:'提示',msg:'新增操作：成功!',showType:'show' });
				treeGrid.treegrid('reload');
				$("#addDialog_XunJianFenLei").dialog('close');

			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}

/**
 * 打开编辑界面
 */
function  editXunJianDianFenLeiFun() {
	var id;
	var node = treeGrid.treegrid('getSelected');
	if (node == undefined) {
		$.messager.alert('提示', '请选择单条数据进行编辑！', 'info');
		return;
	}
	if (node) {
		//打开编辑页面
		$("#editDialog_XunJianFenLei").dialog('open').dialog('setTitle', '编辑［' + entityCnName + '］');
		$('#editForm_XunJianFenLei').form('load', node);
		createProjectSelect("projectid_commbox_edit", true);
		createTypeSelect("typeParent_commbox_edit", true);
		//设置数据回显
		$("#projectid_commbox_edit").combobox("select", node['proiectName']);
		$("#projectid_commbox_edit").combobox("setValue",node['proiectId']);
		$("#typeParent_commbox_edit").combobox("select", node['parentName']);
		$("#typeParent_commbox_edit").combobox("setValue", node['parentId']);
	}
}

/**
 * 提交编辑数据 保存
 */
function editXunJianFenLeiAjax(){
	var prjName = $("#projectid_commbox_edit").combobox('getText');
	var prjId = $("#projectid_commbox_edit").combobox('getValue');
	var parentId = $("#typeParent_commbox_edit").combobox('getValue');
	var parentName =$("#typeParent_commbox_edit").combobox('getText');

	$('#proiectname_edit').val(prjName);
	$('#proiectid_edit').val(prjId);
	$('#parentId_edit').val(parentId);
	$("#parentName_edit").val(parentName);
	var isValid = $('#editForm_XunJianFenLei').form('validate');
	if (!isValid) return;
	$.messager.confirm('提示', '您确定要编辑［'+entityCnName + '］吗？', function(b) {
		if (b) {
			$('#editForm_XunJianFenLei').form('submit', {
				url : path+'/edit',
				onSubmit : function() {
				},
				success : function(result) {
					progressClose();
					result = $.parseJSON(result);
					if (result.success) {
						$.messager.show({title:'提示',msg:'修改操作：成功!',showType:'show' });
						treeGrid.treegrid('reload');
						$("#editDialog_XunJianFenLei").dialog('close');

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
 * 删除操作
 */
function  deleteYanShouYingXiangFun() {
	var node = treeGrid.treegrid('getSelected');
	if (node == undefined) {
		$.messager.alert('提示', '请选择单条数据进行删除！', 'info');
		return;
	}
	if (node) {
		$.messager.confirm('询问', '您是否要删除［'+entityCnName + '］所选行吗？', function(b) {
			if (b) {
				progressLoad();
				$.post(path+'/delete', {
					ids : node.id
				}, function(result) {
					if (result.success) {
						$.messager.show({title:'提示',msg:'删除操作：成功!',showType:'show' });
						treeGrid.treegrid('reload');
					}else {
						$.messager.alert('错误', result.msg, 'error');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}
}




