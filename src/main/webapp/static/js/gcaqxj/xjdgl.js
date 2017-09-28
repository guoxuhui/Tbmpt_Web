/**
 *
 */
/** 系统模块同路径 */
var syspath = "gcaqxj";
/** 业务数据访问全路径 */
var path = basePath +"/"+ syspath +"/xjdgl";
/** 菜单名称 */
var entityCnName = "巡检点";
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
var xjdgl_dataGrid;
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
		url:basePath +"/gcaqxj/xjdfl/getType"
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
function createUserSelect(showId,required,orgzId){
	$("#"+showId).combobox({
		required : required,
		valueField:'id',
		textField:'name',
		url:basePath +"/sys/user/userList" ,
		onBeforeLoad: function(param){
			param.orgzId = orgzId;
		}
	});
	/*点击文本框内，就显示下拉面板*/
	$("#"+showId).combobox('textbox').click(function(){
		$("#"+showId).combobox('showPanel');
	});
}

$(function() {

	/** 创建数据表格	 */
	xjdgl_dataGrid = $('#dataGrid_xunjiandianGL').datagrid({
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
			{width: '240',title: '工程名称',field: 'projectname',
				formatter: function(value,row,index){
					return DataGridFormatter.tipsFormatter(value,50);
				}
			},
			{width: '80',title: '检查点名称',field:'mingcheng' 	},
			{
				width: '100', title: '所在位置', field:  'xujiandianweizhi'
			}
			 ]],
	    	    columns : [ [
			{
				width: '80', title: '需检查频次', field:  'jianchapc'
			},
			{
				width: '60',title: '责任人',field: 'zerenrmc'
			},
			{
				width: '60',title: '监督人',field: 'jiandurmc'
			},
			{
				width: '60',title: '责任人id',field: 'zerenrid',hidden:'true',
			},
			{
				width: '60',title: '监督人id',field: 'jiandurid',hidden:'true',
			},
			{
				width: '30',title: '顺序号',field: 'xuhao'
			},
			{
				width: '80',title: '检查点分类',field: 'typeName'
			},
			{
				width: '80',title: '检查点分类id',field: 'typeId',hidden:'true',
			},

			{width: '180',title: '备注',field: 'beizhu',hidden:'true',
				formatter: function(value,row,index){
					return DataGridFormatter.tipsFormatter(value,30);
				}
			},

			{
				width: '100',title: '工程id',field: 'proiectId',hidden:'true'
			},

		]],

		toolbar : '#toolbar',
		onLoadSuccess : function(data) {
			hiddenButtonBase("dataGrid_xunjiandianGL",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
		},
		onCheck : function(){
			hiddenButtonBase("dataGrid_xunjiandianGL",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
		},
		onUncheck : function(){
			hiddenButtonBase("dataGrid_xunjiandianGL",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
		},
		onCheckAll : function(){
			hiddenButtonBase("dataGrid_xunjiandianGL",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
		},
		onUncheckAll : function(){
			hiddenButtonBase("dataGrid_xunjiandianGL",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
		},
		onClickRow:function(){
			hiddenButtonBase("dataGrid_xunjiandianGL",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
		}
	});
	$('#searchForm_XunJianDianGL').show();
	//初始化查询条件
	createProjectSelect("projectid_query_id",true);

});

/** 表单查询 */
function searchXunJianDianGLFun() {
	var prjName = $("#projectid_query_id").combobox('getText');
	$('#proiectname_query').val(prjName);
	//var typeName = $("#typeName_query_id").combobox('getText');
	//$('#typeName_search').val(typeName);
	xjdgl_dataGrid.datagrid('load', $.serializeObject($('#searchForm_XunJianDianGL')));
}

/*
 * 重置
 * */
function cleandXunJianDianGLFun() {
	$('#searchForm_XunJianDianGL input').val('');
	xjdgl_dataGrid.datagrid('load', {});
}
/*
 * 刷新
 * */
function reloadXunJianDianGLFun(){
	xjdgl_dataGrid.datagrid('reload');
}


/**
 * 打开新增界面
 */
function addXunJianDianGLFun(){
	FormUtil.clearForm($("#addForm_XunJianGL"));
	//createProjectSelect("projectid_commbox_add",true);
	$("#add_projectName").text($("#projectName-golbal").val());
	$("#projectname_add").val($("#projectName-golbal").val());
	$("#projectid_add").val($("#projectId-golbal").val());
	createTypeSelect("typeName_commbox_add",true);
	var proId =$("#orgId-global").val();
	//创建监督人下拉列表
	createUserSelect("jiandurmc_commbox_add",true,proId);
	//创建责任人下拉列表
	createUserSelect("zerenrmc_commbox_add",true,proId);
	$("#addDialog_XunJianGL").dialog('open').dialog('setTitle', '添加［'+entityCnName+"］");
}

/**
 * 新增保存操作
 */
function addXunJianGLAjax(){
	var typeName = $("#typeName_commbox_add").combobox('getText');
	var typeId = $("#typeName_commbox_add").combobox('getValue');
	$('#typeId_add').val(typeId);
	$('#typeName_add').val(typeName);
	var zerenrId = $("#zerenrmc_commbox_add").combobox('getValue');
	var zerenrName =$("#zerenrmc_commbox_add").combobox('getText');
	$('#zerenrid_add').val(zerenrId);
	$("#zerenrmc_add").val(zerenrName);

	var jiandurid = $("#jiandurmc_commbox_add").combobox('getValue');
	var jiandurmc =$("#jiandurmc_commbox_add").combobox('getText');
	$('#jiandurid_add').val(jiandurid);
	$("#jiandurmc_add").val(jiandurmc);
	$('#addForm_XunJianGL').form('submit', {
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
				xjdgl_dataGrid.datagrid('reload');
				$("#addDialog_XunJianGL").dialog('close');
				xjdgl_dataGrid.datagrid('clearSelections');
				FormUtil.clearForm($("#addDialog_XunJianGL"));
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
function  editXunJianDianGLFun() {
	var proId =$("#orgId-global").val();
	var id;
	var rows = xjdgl_dataGrid.datagrid('getSelections');
	if (rows.length > 1) {
		$.messager.alert('提示', '请选择单条数据进行编辑！', 'info');
		return;
	} else if (rows.length == 1) {
		if (rows[0]) {
			id = rows[0].id;
		} else {
			$.messager.alert('提示', '请选择数据后再编辑！', 'info');
			return;
		}
	} else {
		$.messager.alert('提示', '请选择数据后再编辑！', 'info');
		return;
	}
	if (rows[0]) {
		//打开编辑页面
		$("#editDialog_XunJianGL").dialog('open').dialog('setTitle', '编辑［' + entityCnName + '］' + '-' + rows[0][nameField]);
		$('#editForm_XunJianGL').form('load', rows[0]);
		//设置数据回显
		$("#edit_projectName").text($("#projectName-golbal").val());
		$("#projectname_edit").val($("#projectName-golbal").val());
		$("#projectid_edit").val($("#projectId-golbal").val());
		createTypeSelect("typeName_commbox_edit", true);
		createUserSelect("zerenrmc_commbox_edit",true,proId);
		createUserSelect("jiandurmc_commbox_edit",true,proId);

		$("#typeName_commbox_edit").combobox("select", rows[0]['typeName']);
		$("#typeName_commbox_edit").combobox("setValue", rows[0]['typeId']);
		$("#zerenrmc_commbox_edit").combobox("select", rows[0]['zerenrmc']);
		$("#zerenrmc_commbox_edit").combobox("setValue", rows[0]['zerenrid']);
		$("#jiandurmc_commbox_edit").combobox("select", rows[0]['jiandurmc']);
		$("#jiandurmc_commbox_edit").combobox("setValue", rows[0]['jiandurid']);
	}
}
/**
 * 提交编辑数据 保存
 */
function editXunJianGLAjax(){
	var typeName = $("#typeName_commbox_edit").combobox('getText');
	var typeId = $("#typeName_commbox_edit").combobox('getValue');
	$('#typeId_edit').val(typeId);
	$('#typeName_edit').val(typeName);
	var zerenrId = $("#zerenrmc_commbox_edit").combobox('getValue');
	var zerenrName =$("#zerenrmc_commbox_edit").combobox('getText');
	$('#zerenrid_edit').val(zerenrId);
	$("#zerenrmc_edit").val(zerenrName);
	var jiandurid = $("#jiandurmc_commbox_edit").combobox('getValue');
	var jiandurmc =$("#jiandurmc_commbox_edit").combobox('getText');
	$('#jiandurid_edit').val(jiandurid);
	$("#jiandurmc_edit").val(jiandurmc);
	var isValid = $('#editForm_XunJianGL').form('validate');
	if (!isValid) return;
	$.messager.confirm('提示', '您确定要编辑［'+entityCnName + '］吗？', function(b) {
		if (b) {
			$('#editForm_XunJianGL').form('submit', {
				url : path+'/edit',
				onSubmit : function() {
				},
				success : function(result) {
					progressClose();
					result = $.parseJSON(result);
					if (result.success) {
						$.messager.show({title:'提示',msg:'修改操作：成功!',showType:'show' });

						$("#editDialog_XunJianGL").dialog('close');
						xjdgl_dataGrid.datagrid('reload');
						xjdgl_dataGrid.datagrid('clearSelections');
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
function delXunJianDianGLFun() {
	//if(_getCurrOrgzType() =='0'){$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');	return;}//非项目部下人员无权操作数据
	var ids = new Array();
	var rows = xjdgl_dataGrid.datagrid('getSelections');
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
					xjdgl_dataGrid.datagrid('reload');
					xjdgl_dataGrid.datagrid('clearSelections');
				}
				progressClose();
			}, 'JSON');
		}
	});
}
/**
 * 取消已选
 * @returns
 */
function clearXunJianDianGLFunSelections(){
	xjdgl_dataGrid.datagrid('clearSelections');
}
/**
 * 编辑巡检内容
 */
function editXunJianDianNeiRongFun(){
	var id;
	var rows = xjdgl_dataGrid.datagrid('getSelections');
	if (rows.length > 1) {
		$.messager.alert('提示', '请选择单条数据进行编辑！', 'info');
		return;
	} else if (rows.length == 1) {
		if (rows[0]) {
			id = rows[0].id;
		} else {
			$.messager.alert('提示', '请选择数据后再编辑！', 'info');
			return;
		}
	} else {
		$.messager.alert('提示', '请选择数据后再编辑！', 'info');
		return;
	}
	if (rows[0]) {
		//打开编辑页面
		$("#editDialog_XunJianNeiRongGL").dialog('open').dialog('setTitle', '编辑［' + entityCnName + '］' + '-' + rows[0][nameField] +'巡检内容');
		//设置数据回显
		$("#edit_NeiRongprojectName").text($("#projectName-golbal").val());
		$("#edit_NeiRongMingCheng").text( rows[0]['mingcheng']);
		$("#edit_NeiRongTypeName").text( rows[0]['typeName']);
		$("#edit_NeiRongXuJianDianWeiZhi").text( rows[0]['xujiandianweizhi']);
		$("#edit_NeiRongZeRenMC").text( rows[0]['zerenrmc']);
		$("#edit_NeiRongJianDuRenMC").text( rows[0]['jiandurmc']);
		$("#edit_NeiRongJianChaPC").text( rows[0]['jianchapc']);
		$("#edit_NeiRongBeiZhu").text(rows[0]['beizhu']==null ?'无':rows[0]['beizhu']);
	}
	$('#dg').edatagrid({

		url : basePath+'/gcaqxj/xjdgl/xjnr/dataGrid?id='+rows[0].id,

		saveUrl :basePath+'/gcaqxj/xjdgl/xjnr/add?itemid='+rows[0]['id'],
		updateUrl :basePath+'/gcaqxj/xjdgl/xjnr/update?itemid='+rows[0]['id'],
		destroyUrl :basePath+'/gcaqxj/xjdgl/xjnr/delete',
		onError : function(index, data) {
			$.messager.alert('错误提示', data.msg, 'error');
		}
	});
}


/**
 * 查看巡检内容
 */
function viewXunJianDianNeiRongFun(){
	var id;
	var rows = xjdgl_dataGrid.datagrid('getSelections');
	if (rows.length > 1) {
		$.messager.alert('提示', '请选择单条数据进行编辑！', 'info');
		return;
	} else if (rows.length == 1) {
		if (rows[0]) {
			id = rows[0].id;
		} else {
			$.messager.alert('提示', '请选择数据后再编辑！', 'info');
			return;
		}
	} else {
		$.messager.alert('提示', '请选择数据后再编辑！', 'info');
		return;
	}
	if (rows[0]) {
		//打开编辑页面
		$("#viewDialog_XunJianNeiRongGL").dialog('open').dialog('setTitle', '编辑［' + entityCnName + '］' + '-' + rows[0][nameField] +'巡检内容');
		//设置数据回显
		$("#view_NeiRongprojectName").text($("#projectName-golbal").val());
		$("#view_NeiRongMingCheng").text( rows[0]['mingcheng']);
		$("#view_NeiRongTypeName").text( rows[0]['typeName']);
		$("#view_NeiRongXuJianDianWeiZhi").text( rows[0]['xujiandianweizhi']);
		$("#view_NeiRongZeRenMC").text( rows[0]['zerenrmc']);
		$("#view_NeiRongJianDuRenMC").text( rows[0]['jiandurmc']);
		$("#view_NeiRongJianChaPC").text( rows[0]['jianchapc']);
		$("#view_NeiRongBeiZhu").text(rows[0]['beizhu']==null ?'无':rows[0]['beizhu']);
	}
	$('#dg_view').edatagrid({
		url : path+'/xjnr/dataGrid?id='+rows[0].id,
		saveUrl :'',
		updateUrl :'',
		destroyUrl : '',
		onError : function(index, data) {
			$.messager.alert('错误提示', data.msg, 'error');
		}
	});


}
/**
 * 打印
 */
function printXunJianDianGLFun(){
	var id;
	var rows = xjdgl_dataGrid.datagrid('getSelections');
	if (rows.length > 1) {
		$.messager.alert('提示', '请选择单条数据进行打印！', 'info');
		return;
	} else if (rows.length == 1) {
		if (rows[0]) {
			id = rows[0].id;
		} else {
			$.messager.alert('提示', '请选择数据后再打印！', 'info');
			return;
		}
	} else {
		$.messager.alert('提示', '请选择数据后再打印！', 'info');
		return;
	}
	if (rows[0]) {
		//打开编辑页面

		//设置数据回显
		//id
		var  id = rows[0].id;
		var xunjiandianName=  rows[0]['mingcheng'];
		$("#print_NeiRongTypeName").text(xunjiandianName);
		var zerenrmc = rows[0]['zerenrmc'];
		$("#print_NeiRongZeRenMC").text(xunjiandianName);

		var jiandurmc =  rows[0]['jiandurmc'];
		$("#print_NeiRongJianDuRenMC").text(jiandurmc);
		var jianchapc =  rows[0]['jianchapc'];
		$("#print_NeiRongJianChaPC").text(jianchapc);

		$('#dg_XJNR').datagrid({
			url:basePath+'/gcaqxj/xjdgl/xjnr/list?nrid='+ id,
			rownumbers : true,
			frozenColumns:[[
				{field:'contentXunjian',title:'巡检内容',width:540}
			]],
		});

		$('#qrcode').html("");
		//生成二维码
		var qrcode = new QRCode(document.getElementById("qrcode"), {
			width : 200,
			height : 200
		});
		qrcode.clear();
		qrcode.makeCode(basePath+"/open/gcaqxj/gcaqxjAddPage?id="+id );
		$("#printDialog_XunJianNeiRongGL").dialog('open').dialog('setTitle', '打印［' + entityCnName + '］' + '-' + rows[0][nameField] +'巡检内容');
	}
}
/**
 * 导出Excel
 */
function expXlsXunJianDianGLFun(){
	var arr =["id","zerenrid","proiectId","typeId","jiandurid"];
	var url = path + "/expXls";
	ExportUtil.doExport1(url, ExportUtil.TYPE_XLS_2003, moduleName+"信息", xjdgl_dataGrid, false,arr);
}
/**
 * 导出pdf
 */
function expPdfXunJianDianGLFun(){
	var arr =["id","zerenrid","proiectId","typeId","jiandurid"];
	var url = path + "/expPdf";
	ExportUtil.doExport1(url, ExportUtil.TYPE_PDF, moduleName+"信息", xjdgl_dataGrid, false,arr);
}
/**
 * 打印
 */
function printView(){
	var  printDiv  = $('#printDialog_XunJianNeiRongGL').html();
	$("#printTable").printArea(printDiv);
	//$('#printDialog_XunJianNeiRongGL').printArea();
}