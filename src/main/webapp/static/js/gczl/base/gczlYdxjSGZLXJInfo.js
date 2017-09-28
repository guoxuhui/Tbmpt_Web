/** /** 【结构施工质量巡检管理】 基础模块
 * 创建时间：2016-11-24
   */
/** 系统模块同路径 */
var syspath = "gczl";
/** 子模块路径 */
var module = "base";
/** 菜单名称 */
var entityCnName = "结构施工质量巡检管理";
/** 名称字段 */
var nameField = "";
/** 业务数据访问全路径 */
var path = basePath +"/"+ syspath +"/"+ module+"/gczlYdxjSGZLXJInfo";
/** 列表对象 */
var dataGrid_gczlYdxjSGZLXJInfo;
/**若无一条勾选数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayZero = ["cancelSelect_button_gczlYdxjSGZLXJInfo","edit_button_gczlYdxjSGZLXJInfo","del_button_gczlYdxjSGZLXJInfo","sb_button_gczlYdxjSGZLXJInfo","sh_button_gczlYdxjSGZLXJInfo","zgfk_button_gczlYdxjSGZLXJInfo","scxctp_button_gczlYdxjSGZLXJInfo","sczghtp_button_gczlYdxjSGZLXJInfo"];
/**若只勾选一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayOnlyOneItem = [];
/**若勾选大于一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayMoreOneItem =["edit_button_gczlYdxjSGZLXJInfo","sh_button_gczlYdxjSGZLXJInfo","zgfk_button_gczlYdxjSGZLXJInfo","scxctp_button_gczlYdxjSGZLXJInfo","sczghtp_button_gczlYdxjSGZLXJInfo"];
$(function() {
	/** 创建数据表格	 */
	dataGrid_gczlYdxjSGZLXJInfo = $('#dataGrid_gczlYdxjSGZLXJInfo').datagrid({
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
		 frozenColumns:
		        [[
		        	{field:'id',align:'center',title:'ID',width:40, checkbox:true},
		            {width: '150',title: '施工内容',field: 'sgNr',sortable : true,
		        		formatter: function(value,row,index){
			   				return '<a href="#" onclick="showGczlYdxjSGZLXJInfoFun(\''+ row.id +'\',\''+index+'\')">' + value + '</a>';
			   			  }
		        	},
		            {width: '120',title: '单位工程',field: 'dwgcName',sortable : true},
		            {width: '120',title: '分部工程',field: 'fbGcName',sortable : true},
		            {width: '100',title: '施工段',field: 'sgd'}
		 		]],
		columns:[[
           {width: '120',title: '具体位置',field: 'jtWz'
           },
           {width: '120',title: '质量缺陷',field: 'zlQx'},
           {width: '150',title: '其他需说明情况',field: 'remarks',
        	   formatter: function(value,row,index){
					return DataGridFormatter.tipsFormatter(value,20);
				}
           },
           {width: '120',title: '巡检时间',field: 'xjTime',sortable : true},
           {width: '100',title: '巡检人员',field: 'xjRyName'},
           {width: '100',title: '巡检人员所在部门',field: 'xjBmName'},
           {width: '80',title: '上报状态',field: 'sbZt',sortable : true},
           {width: '100',title: '审核状态',field: 'shZt',sortable : true},
           {width: '100',title: '整改状态',field: 'zgZt',sortable : true},
           {width: '100',title: '上报人员',field: 'sbRyName'},
           {width: '100',title: '上报部门',field: 'sbBmName'},
           {width: '120',title: '上报时间',field: 'sbTime'},
           {width: '100',title: '审核人员',field: 'shRyName'},
           {width: '120',title: '审核部门',field: 'shBmName'},
           {width: '120',title: '审核时间',field: 'shTime'},
           {width: '120',title: '审核说明',field: 'shSm'},
           {width: '100',title: '整改反馈人员',field: 'zgFkryName'},
           {width: '100',title: '整改反馈部门',field: 'zgBmName'},
           {width: '120',title: '整改反馈时间',field: 'zgfkTime',sortable : true},
           {width: '120',title: '整改说明',field: 'zgJg',
        	   formatter: function(value,row,index){
					return DataGridFormatter.tipsFormatter(value,20);
				}
           },
           {width: '120',title: '整改时间',field: 'zgTime'}
		]],
		toolbar : '#toolbar',
		   		onLoadSuccess : function(data) {
					hiddenButtonBase("dataGrid_gczlYdxjSGZLXJInfo",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
				},
		        onCheck : function(){
		        	hiddenButtonBase("dataGrid_gczlYdxjSGZLXJInfo",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
		        },
		        onUncheck : function(){
		        	hiddenButtonBase("dataGrid_gczlYdxjSGZLXJInfo",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
		        },
		        onCheckAll : function(){
		        	hiddenButtonBase("dataGrid_gczlYdxjSGZLXJInfo",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
		        },
		        onUncheckAll : function(){
		        	hiddenButtonBase("dataGrid_gczlYdxjSGZLXJInfo",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
		        },
		        onClickRow:function(){
		        	hiddenButtonBase("dataGrid_gczlYdxjSGZLXJInfo",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
		        }
	});
	createProjectSelect("search_gcBh_gczlYdxjSGZLXJInfo",false,"search");
	createDwgcSelect("search_dwgcBh_gczlYdxjSGZLXJInfo","-1",true,"search")
	createFbgcSelect("search_fbGcbh_gczlYdxjSGZLXJInfo","-1",false);//构建分部工程下拉框
	createSgNrSelect("search_sgNr_gczlYdxjSGZLXJInfo",false,"search");//构建施工内容下拉框
	createJtwzSelect("search_jtWz_gczlYdxjSGZLXJInfo","-1",false);//构建具体位置下拉框
	createZlqxSelect("search_zlQx_gczlYdxjSGZLXJInfo","-1",false);//构建质量缺陷下拉框
	createZgStaSelect("search_zgZt",false);//构建整改状态下拉框
	createShStaSelect("search_shZt",false);//构建审核状态下拉框
	createSbStaSelect("search_sbZt",false);//构建整改状态下拉框
});


/**
 * 取消已选
 * @returns
 */
function clearGczlYdxjSGZLXJInfoSelections(){
	dataGrid_gczlYdxjSGZLXJInfo.datagrid('clearSelections');
}
/**
 * 导出excel
 * @returns
 */
function expGczlYdxjSGZLXJInfoXls(){
	var url = path + "/expXls";
	ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, entityCnName+"信息", dataGrid_gczlYdxjSGZLXJInfo, false);
}
/**
 * 导出pdf
 * @returns
 */
function expGczlYdxjSGZLXJInfoPdf(){
	var url = path + "/expPdf";
	ExportUtil.doExport(url, ExportUtil.TYPE_PDF, entityCnName+"信息", dataGrid_gczlYdxjSGZLXJInfo, false);
}
/**
 * 刷新
 * @returns
 */
function reloadGczlYdxjSGZLXJInfo(){
	dataGrid_gczlYdxjSGZLXJInfo.datagrid('reload');
}

/**
 * 上传图片
 * @returns
 */
function myUploadPicture(param){
	var userType  = _getCurrOrgzType();
	if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}
	var beizhu = '整改前';
	if(param=='1'){
		beizhu='整改后';
	}
	var rows = dataGrid_gczlYdxjSGZLXJInfo.datagrid('getSelections');
	alert("111");
	_uploadPicture(rows[0].id,syspath,beizhu,dataGrid_gczlYdxjSGZLXJInfo,entityCnName);
}

/**
 * 方法说明：查看附件
 * @param keyIdShow 业务主键在界面显示id
 * @returns
 * @author:YangYj
 * @Time: 2017年2月21日 下午5:18:23
 */
function showMyPic(keyIdShow){
	var keyId = $("#"+keyIdShow).val();//缩略图路径
	_showPic(keyId);
}

/**
 * 上报操作
 * @returns
 */
function sbInfo(){
	var userType  = _getCurrOrgzType();
	if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}
	var ids = new Array();
	var rows = dataGrid_gczlYdxjSGZLXJInfo.datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请先选择要上报的数据！', 'info');
		return;
	}
	for(var i=0; i<rows.length; i++){
		//已上报状态的数据不可再次上报
		if(rows[i].sbZt !=null && rows[i].sbZt =='已上报'){
			$.messager.alert('提示', '结构施工质量巡检信息已上报，不能重复上报。', 'info');
			return;
		}
		ids.push(rows[i].id);
	}
	$.messager.confirm('询问', '您是否要处理［'+entityCnName + '］所选行吗？', function(b) {
		if (b) {
			progressLoad();
			$.ajax({
				url: path+'/sbInfo',
				type: 'post',
				data: {ids:ids.join(",")},
				dataType : 'JSON',
				success : function(data) {
					if(data.success){
						progressClose();
						$.messager.show({title:'提示',msg:data.msg,showType:'show' });
						dataGrid_gczlYdxjSGZLXJInfo.datagrid('reload');
						dataGrid_gczlYdxjSGZLXJInfo.datagrid('clearSelections');
					}else{
//						$.messager.show({title:'提示',msg:data.msg,showType:'show' });
						$.messager.alert('提示', data.msg, 'info');
						progressClose();
					}
				}
			});
		}
	});
	
}

/**
 * 审核操作
 * @returns
 */
function shInfo(){
	var userType  = _getCurrOrgzType();
	if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}
	var id;
	var rows = dataGrid_gczlYdxjSGZLXJInfo.datagrid('getSelections');
	if (rows.length > 1) {
		$.messager.alert('提示', '请选择单条数据进行审核！', 'info');
		return;
	} else if (rows.length == 1) {
		if (rows[0]) {
			id = rows[0].id;
			if(rows[0].shZt != null && (rows[0].shZt =='已审核' || rows[0].shZt =='已打回') ){
				$.messager.alert('提示', '结构施工质量巡检信息已进行审核操作，不能重复审核。', 'info');
				return;
			}
			if(rows[0].sbZt != null && (rows[0].sbZt =='未上报') ){
				$.messager.alert('提示', '结构施工质量巡检信息未上报，不能进行审核操作。', 'info');
				return;
			}
		}else {
			$.messager.alert('提示', '请选择数据后再审核！', 'info');
			return;
		}
	} else {
		$.messager.alert('提示', '请选择数据后再审核！', 'info');
		return;
	}
	if (rows[0]) {
		FormUtil.clearForm($("#shDialog_gczlYdxjSGZLXJInfo"));
		$("#shDialog_gczlYdxjSGZLXJInfo").dialog('open').dialog('setTitle', '审核［'+entityCnName+"］");
		createShStaSelect("sh_shZt_gczlYdxjSGZLXJInfo",true);//构建审核情况下拉框
		/**判断该信息是否有附件 */
		judgeFuJian(id,"sh");
		$('#shForm_gczlYdxjSGZLXJInfo').form('load', rows[0]);
	}
}

/**
 * 审核保存操作
 * @returns
 */
function shGczlYdxjSGZLXJInfoAjax(){
	var isValid = $('#shForm_gczlYdxjSGZLXJInfo').form('validate');
	if (!isValid) return;
	$.messager.confirm('提示', '您确定要审核［'+entityCnName + '］吗？', function(b) {
		if (b) {
			$('#shForm_gczlYdxjSGZLXJInfo').form('submit', {
				url : path+'/shInfo',
				onSubmit : function() {
				},
				success : function(result) {
					progressClose();
					result = $.parseJSON(result);
					if (result.success) {
				       $.messager.show({title:'提示',msg:'审核操作：成功!',showType:'show' });
						dataGrid_gczlYdxjSGZLXJInfo.datagrid('reload');
						$("#shDialog_gczlYdxjSGZLXJInfo").dialog('close');
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
 * 整改反馈操作
 * @returns
 */
function zgfkInfo(){
	var userType  = _getCurrOrgzType();
	if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}
	var id;
	var rows = dataGrid_gczlYdxjSGZLXJInfo.datagrid('getSelections');
	if (rows.length > 1) {
		$.messager.alert('提示', '请选择单条数据进行整改反馈！', 'info');
		return;
	} else if (rows.length == 1) {
		if (rows[0]) {
			id = rows[0].id;
			if(rows[0].shZt != null && rows[0].shZt !='已审核'){
				$.messager.alert('提示', '该结构施工质量巡检信息审核未通过，不能进行整改反馈。', 'info');
				return;
			}
			if(rows[0].zgZt != null && rows[0].zgZt =='已整改' ){
				$.messager.alert('提示', '该结构施工质量巡检信息已进行整改反馈，不能重复反馈。', 'info');
				return;
			}
		}else {
			$.messager.alert('提示', '请选择数据后再整改反馈！', 'info');
			return;
		}
	} else {
		$.messager.alert('提示', '请选择数据后再整改反馈！', 'info');
		return;
	}
	if (rows[0]) {
		FormUtil.clearForm($("#zgDialog_gczlYdxjSGZLXJInfo"));
		$("#zgDialog_gczlYdxjSGZLXJInfo").dialog('open').dialog('setTitle', '整改反馈［'+entityCnName+"］");
		createZgStaSelect("zg_zgZt_gczlYdxjSGZLXJInfo",true);
		/**判断该信息是否有附件 */
		judgeFuJian(id,"zg");
		$('#zgForm_gczlYdxjSGZLXJInfo').form('load', rows[0]);
	}
	
}

/**
 * 整改反馈保存操作
 * @returns
 */
function zgGczlYdxjSGZLXJInfoAjax(){
	var isValid = $('#zgForm_gczlYdxjSGZLXJInfo').form('validate');
	if (!isValid) return;
	$.messager.confirm('提示', '您确定要提交吗？', function(b) {
		if (b) {
			$('#zgForm_gczlYdxjSGZLXJInfo').form('submit', {
				url : path+'/zgInfo',
				onSubmit : function() {
				},
				success : function(result) {
					progressClose();
					result = $.parseJSON(result);
					if (result.success) {
				$.messager.show({title:'提示',msg:'整改反馈操作：成功!',showType:'show' });
						dataGrid_gczlYdxjSGZLXJInfo.datagrid('reload');
						$("#zgDialog_gczlYdxjSGZLXJInfo").dialog('close');
					}
					else {
						$.messager.alert('错误', result.msg, 'error');
					}
				}
			});
		}
	});
}
/** 【结构施工质量巡检管理】打开新增页面 */
function addGczlYdxjSGZLXJInfoFun() {
	var userType  = _getCurrOrgzType();
	if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}
	FormUtil.clearForm($("#addDialog_gczlYdxjSGZLXJInfo"));
	$.ajax({
		url: path +"/findProjectByUserId",
		type: 'post',
		data: {},
		dataType : 'JSON',
		success : function(data) {
			if(data){
				$("#add_gcName_gczlYdxjSGZLXJInfo").html(data.proName);
				$("#add_gcBh_gczlYdxjSGZLXJInfo").val(data.id);
				$("#addDialog_gczlYdxjSGZLXJInfo").dialog('open').dialog('setTitle', '添加［'+entityCnName+"］");
				createDwgcSelect("add_dwgcBh_gczlYdxjSGZLXJInfo",data.id,true,"add")
				createFbgcSelect("add_fbGcbh_gczlYdxjSGZLXJInfo","-1",true);//构建分部工程下拉框
				createSgNrSelect("add_sgNr_gczlYdxjSGZLXJInfo",true,"add");//构建施工内容下拉框
				createJtwzSelect("add_jtWz_gczlYdxjSGZLXJInfo","-1",true);//构建具体位置下拉框
				createZlqxSelect("add_zlQx_gczlYdxjSGZLXJInfo","-1",true);//构建质量缺陷下拉框
			}else{
			}
		}
	});
	
	
}

/** 【结构施工质量巡检管理】数据保存 */
function addGczlYdxjSGZLXJInfoAjax() {
	$('#addForm_gczlYdxjSGZLXJInfo').form('submit', {
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
				dataGrid_gczlYdxjSGZLXJInfo.datagrid('reload');
				dataGrid_gczlYdxjSGZLXJInfo.datagrid('clearSelections');
				$("#addDialog_gczlYdxjSGZLXJInfo").dialog('close');
				FormUtil.clearForm($("#addDialog_gczlYdxjSGZLXJInfo"));
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}



/** 【结构施工质量巡检管理】打开数据编辑页面 */
function editGczlYdxjSGZLXJInfoFun() {
	var userType  = _getCurrOrgzType();
	if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}
	var id;
	var rows = dataGrid_gczlYdxjSGZLXJInfo.datagrid('getSelections');
	if (rows.length > 1) {
		$.messager.alert('提示', '请选择单条数据进行编辑！', 'info');
		return;
	} else if (rows.length == 1) {
		if (rows[0]) {
			id = rows[0].id;
			if(rows[0].sbZt != null && (rows[0].sbZt =='已上报')){
				$.messager.alert('提示', '结构施工质量巡检信息已上报，不能修改。', 'info');
				return;
			}
		}else {
			$.messager.alert('提示', '请选择数据后再编辑！', 'info');
			return;
		}
	} else {
		$.messager.alert('提示', '请选择数据后再编辑！', 'info');
		return;
	}
	if (rows[0]) {
		$.ajax({
			url: path +"/findProjectByUserId",
			type: 'post',
			data: {},
			dataType : 'JSON',
			success : function(data) {
				if(data){
					$("#edit_gcName_gczlYdxjSGZLXJInfo").html(data.proName);
					$("#editDialog_gczlYdxjSGZLXJInfo").dialog('open').dialog('setTitle', '编辑［'+entityCnName+']');
					createDwgcSelect("edit_dwgcBh_gczlYdxjSGZLXJInfo",rows[0].gcBh,true,"edit");//构建区间下拉框
					createFbgcSelect("edit_fbGcbh_gczlYdxjSGZLXJInfo",rows[0].qjBh,true);//构建分部工程下拉框
					createSgNrSelect("edit_sgNr_gczlYdxjSGZLXJInfo",true,"edit");//构建施工内容下拉框
					createJtwzSelect("edit_jtWz_gczlYdxjSGZLXJInfo",rows[0].sgNr,true);//构建具体位置下拉框
					createZlqxSelect("edit_zlQx_gczlYdxjSGZLXJInfo",rows[0].sgNr,true);//构建质量缺陷下拉框
					$('#editForm_gczlYdxjSGZLXJInfo').form('load', rows[0]);
				}else{
				}
			}
		});
	}
}

/** 【结构施工质量巡检管理】提交修改 */
function editGczlYdxjSGZLXJInfoAjax() {
	var isValid = $('#editForm_gczlYdxjSGZLXJInfo').form('validate');
	if (!isValid) return;
	$.messager.confirm('提示', '您确定要编辑［'+entityCnName + '］吗？', function(b) {
		if (b) {
			$('#editForm_gczlYdxjSGZLXJInfo').form('submit', {
				url : path+'/edit.action',
				type: 'post',
				onSubmit : function() {
				},
				success : function(result) {
					progressClose();
					result = $.parseJSON(result);
					if (result.success) {
				$.messager.show({title:'提示',msg:'修改操作：成功!',showType:'show' });
						dataGrid_gczlYdxjSGZLXJInfo.datagrid('reload');
						$("#editDialog_gczlYdxjSGZLXJInfo").dialog('close');
						dataGrid_gczlYdxjSGZLXJInfo.datagrid('clearSelections');
					}
					else {
						$.messager.alert('错误', result.msg, 'error');
					}
				}
			});
		}
	});
}

/** 【结构施工质量巡检管理】数据删除 */
function deleteGczlYdxjSGZLXJInfoFun() {
	var userType  = _getCurrOrgzType();
	if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}
	var ids = new Array();
	var rows = dataGrid_gczlYdxjSGZLXJInfo.datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择数据后再删除！', 'info');
		return;
	}
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].id);
		if(rows[i].sbZt != null && (rows[i].sbZt =='已上报') ){
			$.messager.alert('提示', '所选择的结构施工质量巡检信息已上报，不能删除。', 'info');
			return;
		}
	}
	$.messager.confirm('询问', '您是否要删除［'+entityCnName + '］所选行吗？', function(b) {
		if (b) {
			progressLoad();
			$.post(path+'/delete', {
				ids : ids.join(",")
			}, function(result) {
				if (result.success) {
					$.messager.show({title:'提示',msg:'删除操作：成功!',showType:'show' });
					dataGrid_gczlYdxjSGZLXJInfo.datagrid('reload');
					dataGrid_gczlYdxjSGZLXJInfo.datagrid('clearSelections');
				}
				progressClose();
			}, 'JSON');
		}
	});
}

/** 【结构施工质量巡检管理】查看数据页面 */
function showGczlYdxjSGZLXJInfoFun(oid,index) {
	var id;
	var row;
	if(oid){
		id = oid;
		var allrows = dataGrid_gczlYdxjSGZLXJInfo.datagrid('getRows');
		row = allrows[index];
	}else{
		var rows = dataGrid_gczlYdxjSGZLXJInfo.datagrid('getSelections');
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
		
		$("#showDialog_gczlYdxjSGZLXJInfo").dialog('open').dialog('setTitle', '查看［'+entityCnName+'］');
		/**判断该信息是否有附件 */
		judgeFuJian(id,"show");
		
		$('#showForm_gczlYdxjSGZLXJInfo').form('load', row);
	}
}
/**判断该信息是否有附件 */
function judgeFuJian(id,fun) {
	if(id !="" && fun !=""){
		//获取该信息的所有附件图片
		var url = basePath+'/sys/base/sysFujian/findDataByKeyidAndBeiZhu?foreignId='+id;
		$.ajax({
			url: url,
			type: 'get',
			data: null,
			dataType : 'JSON',
			async:true,
			success : function(data) {
				if(data.length>0){
					
					$("#"+fun+"WuFuJian").hide();
					$("#"+fun+"YouFuJian").show();
				}else{
					$("#"+fun+"YouFuJian").hide();
					$("#"+fun+"WuFuJian").show();
				}
			}
		});
	}
	
}

/** 【结构施工质量巡检管理】表单查询 */
function searchGczlYdxjSGZLXJInfoFun() {
	dataGrid_gczlYdxjSGZLXJInfo.datagrid('load', $.serializeObject($('#searchForm_gczlYdxjSGZLXJInfo')));
}

/**【结构施工质量巡检管理】表单重置 */
function cleanGczlYdxjSGZLXJInfoFun() {
	$('#searchForm_gczlYdxjSGZLXJInfo input').val('');
	dataGrid_gczlYdxjSGZLXJInfo.datagrid('load', {});
}


/**
 * 构建所属工程下拉框
 * @param showId  页面显示id
 * @param required 是否必填 true/false
 * @param oparate    add/edit/search/show  用于构建其他下拉框时使用
 * @returns
 */
function createProjectSelect(showId,required,oparate){
	$("#"+showId).combobox({
		required : required,
		valueField:'id',
		textField:'proName',
		url:basePath +"/project/info/getProjects",
		onChange:function(newValue,oldValue){
			createDwgcSelect(oparate+"_dwgcBh_gczlYdxjSGZLXJInfo",newValue,required,oparate)
	        createFbgcSelect(oparate+"_fbGcbh_gczlYdxjSGZLXJInfo","-1",false);//构建分部工程下拉框
		}
	});
	/*点击文本框内，就显示下拉面板*/
	$("#"+showId).combobox('textbox').click(function(){		
		$("#"+showId).combobox('showPanel');
	});
}

/**
 * 方法说明：构建单位工程下拉框
 * @param showId 页面元素显示id
 * @param projectId 项目id
 * @param required  是否必填 true/false
 * @param oparate  add/edit/search/show  用于构建其他下拉框时使用
 * @returns
 * @author:YangYj
 * @Time: 2017年2月16日 下午3:19:57
 */
function createDwgcSelect(showId,projectId,required,oparate){
	$("#"+showId).combobox({
		required : required,
		valueField:'id',
		textField:'dwgcname',
		url:basePath +"/project/dwgc/dwgcListByProId.action?projectId="+projectId,
		onChange:function(newValue,oldValue){
			createFbgcSelect(oparate+"_fbGcbh_gczlYdxjSGZLXJInfo",newValue,required);
		}
	});
	/*点击文本框内，就显示下拉面板*/
	$("#"+showId).combobox('textbox').click(function(){		
		$("#"+showId).combobox('showPanel');
	});
}

/**
 * 构建区间下拉框
 * @param showId 页面显示id
 * @param projectId 项目id
 * @param required  是否必填 true/false
 * @param oparate  add/edit/search/show  用于构建其他下拉框时使用
 * @returns
 */
function createProjectSectionSelect(showId,projectId,required,oparate){
	$("#"+showId).combobox({
		required : required,
		valueField:'id',
		textField:'sectionName',
		url:basePath +"/project/line/getProSectionDic.action?proId="+projectId,
		onChange:function(newValue,oldValue){
			createSectionLineSelect(oparate+"_xlBh_gczlYdxjSGZLXJInfo",newValue,required);
			createFbgcSelect(oparate+"_fbGcbh_gczlYdxjSGZLXJInfo",newValue,required);
		}
	});
	/*点击文本框内，就显示下拉面板*/
	$("#"+showId).combobox('textbox').click(function(){		
		$("#"+showId).combobox('showPanel');
	});
}



/**
 * 构建线路下拉框
 * @param showId 页面显示id
 * @param sectionId 区间id
 * @param required  是否必填 true/false
 * @returns
 */
function createSectionLineSelect(showId,sectionId,required){
	$("#"+showId).combobox({
		required : required,
		valueField:'id',
		textField:'lineName',
		url:basePath +"/project/line/getProLineDic.action?sectionId="+sectionId,
		onChange:function(newValue,oldValue){
		}
	});
	/*点击文本框内，就显示下拉面板*/
	$("#"+showId).combobox('textbox').click(function(){		
		$("#"+showId).combobox('showPanel');
	});
}


/**
 * 构建分部工程下拉框
 * @param showId 页面显示id
 * @param sectionId 区间id
 * @returns
 */
function createFbgcSelect(showId,dwgcId,required){
	$("#"+showId).combobox({
		required : required,
		valueField:'id',
		textField:'fbgcname',
		url:basePath +"/project/fbgc/fbgcListBydwGcId.action?dwgcId="+dwgcId,
		onChange:function(newValue,oldValue){
		}
	});
	/*点击文本框内，就显示下拉面板*/
	$("#"+showId).combobox('textbox').click(function(){		
		$("#"+showId).combobox('showPanel');
	});
}





/**
 * 构建施工内容下拉框
 * @param id 页面显示id
 * @param required 是否必填 true/false
 * @param oparate add/edit/search/show  用于构建其他下拉框时使用
 * @returns
 */
function createSgNrSelect(id,required,oparate){
	$("#"+id).combobox({
		required : required,
		valueField:'sgNr',
		textField:'sgNr',
		editable:true,
		url:basePath +"/"+ syspath +"/"+ module+"/gczlYdxjSGZLSgnr/createSgNrSelect",
		onChange:function(newValue,oldValue){
			createJtwzSelect(oparate+"_jtWz_gczlYdxjSGZLXJInfo",newValue,required);
			createZlqxSelect(oparate+"_zlQx_gczlYdxjSGZLXJInfo",newValue,required);
		}
	});
	/*点击文本框内，就显示下拉面板*/
	$("#"+id).combobox('textbox').click(function(){		
		$("#"+id).combobox('showPanel');
	});
}

/**
 * 构建具体位置下拉框
 * @param id 页面显示id
 * @returns
 */
function createJtwzSelect(showId,sgNr,required){
	$("#"+showId).combobox({
		required : required,
		valueField:'jtWz',
		textField:'jtWz',
		editable:true,
		url:basePath +"/"+ syspath +"/"+ module+"/gczlYdxjSGZLJtwz/createJtwzSelect.action?sgNr="+encodeURI(sgNr),
		onChange:function(newValue,oldValue){
		}
	});
	/*点击文本框内，就显示下拉面板*/
	$("#"+showId).combobox('textbox').click(function(){		
		$("#"+showId).combobox('showPanel');
	});
}
/**
 * 构建质量缺陷下拉框
 * @param id 页面显示id
 * @returns
 */
function createZlqxSelect(showId,sgNr,required){
	$("#"+showId).combobox({
		required : required,
		valueField:'zlQx',
		textField:'zlQx',
		editable:true,
		url:basePath +"/"+ syspath +"/"+ module+"/gczlYdxjSGZLZlqx/createZlqxSelect.action?sgNr="+encodeURI(sgNr),
		onChange:function(newValue,oldValue){
		}
	});
	/*点击文本框内，就显示下拉面板*/
	$("#"+showId).combobox('textbox').click(function(){		
		$("#"+showId).combobox('showPanel');
	});
}

/**
 * 构造审核状态下拉框
 * @param showId 页面显示id
 * @param required 布尔值；是否必填；true/false
 * @returns
 */
function createShStaSelect(showId,required){
	$("#"+showId).combobox({
		required : required,
		valueField:'id',
		textField:'text',
		data: 
		[{
			id: '未审核',
			text: '未审核'
		 },{
			 id: '已审核',
			text: '已审核'
		 },
		 {
			 id: '已打回',
			text: '已打回'
		 }]
	});
	/*点击文本框内，就显示下拉面板*/
	$("#"+showId).combobox('textbox').click(function(){		
		$("#"+showId).combobox('showPanel');
	});
}
/**
 * 构造审核状态下拉框
 * @param showId 页面显示id
 * @param required 布尔值；是否必填；true/false
 * @returns
 */
function createZgStaSelect(showId,required){
	$("#"+showId).combobox({
		required : required,
		valueField:'id',
		textField:'text',
		data: 
		[{
			id: '未整改',
			text: '未整改'
		 },{
			 id: '已整改',
			 text: '已整改'
		 }]
	});
	/*点击文本框内，就显示下拉面板*/
	$("#"+showId).combobox('textbox').click(function(){		
		$("#"+showId).combobox('showPanel');
	});
}
/**
 * 构造上报状态下拉框
 * @param showId 页面显示id
 * @param required 布尔值；是否必填；true/false
 * @returns
 */
function createSbStaSelect(showId,required){
	$("#"+showId).combobox({
		required : required,
		valueField:'id',
		textField:'text',
		data: 
		[{
			id: '未上报',
			text: '未上报'
		 },{
			 id: '已上报',
			 text: '已上报'
		 }]
	});
	/*点击文本框内，就显示下拉面板*/
	$("#"+showId).combobox('textbox').click(function(){		
		$("#"+showId).combobox('showPanel');
	});
}

