/** /** 【盾构施工质量巡检信息】 基础模块
 * 创建时间：2016-11-24
   */
/** 系统模块同路径 */
var syspath = "gczl";
/** 子模块路径 */
var module = "base";
/** 菜单名称 */
var entityCnName = "盾构施工质量巡检信息";
/** 名称字段 */
var nameField = "typeName";
/** 业务数据访问全路径 */
var path = basePath +"/"+ syspath +"/"+ module+"/gczlYdxjGPZLXJInfo";
/** 列表对象 */
var dataGrid_gczlYdxjGPZLXJInfo;

var selectGpps;
var selectGpct;
var selectLsfj;
var selectSls;
var selectSbShow;
var edit_saveButton;//编辑页面保存按钮

/**若无一条勾选数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayZero = ["unSelect_gczlYdxjGPZLXJInfo","del_button_gczlYdxjGPZLXJInfo","edit_GczlYdxjGPZLXJInfo","shengHe_button","sendup_button","zhenggai_button","uploadImg_button","uploadZghImg_button"];
/**若只勾选一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayOnlyOneItem = [];
/**若勾选大于一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayMoreOneItem =["edit_GczlYdxjGPZLXJInfo","shengHe_button","zhenggai_button","uploadImg_button","uploadZghImg_button"];
$(function() {
	/** 创建数据表格	 */
	dataGrid_gczlYdxjGPZLXJInfo = $('#dataGrid_gczlYdxjGPZLXJInfo').datagrid({
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
		toolbar : '#toolbar',
		 frozenColumns:
		        [[
		        	{field:'id',align:'center',title:'ID',width:40, checkbox:true},
		        	// {width: '80',title: '工程名称',field: 'gcName'},
		        	{width: '100',title: '质量问题分类',field: 'typeName',sortable : true,
		        		formatter: function(value,row,index){
		        			return '<a href="#" onclick="showGczlYdxjGPZLXJInfoFun(\''+ row.id +'\',\''+index+'\')">' + value + '</a>';
		        		}
		        	},
		        	{width: '200',title: '区间',field: 'qlName',sortable : true},
		        	{width: '70',title: '线路',field: 'xlName'},
		        	{width: '70',title: '环号',field: 'hh',sortable : true},
		        	{width: '70',title: '点位',field: 'dw'},
		        	{width: '120',title: '巡检时间',field: 'xjtime',sortable : true}
		        ]],
		columns:[[
			
           {width: '380',title: '情况描述',field: 'qkms',align:'left',
        		formatter: function(value,row,index){
        			return DataGridFormatter.tipsFormatter(value,80);
        		}
           },
           {width: '60',title: '巡检人员',field: 'xjryName'},
           {width: '80',title: '巡检人部门',field: 'xjbmName'},
           {width: '60',title: '上报状态',field: 'sbzt',sortable : true},
           {width: '60',title: '审核状态',field: 'shzt',sortable : true},
           {width: '60',title: '整改状态',field: 'zgzt',sortable : true},
           {width: '60',title: '上报人员',field: 'sbryName'},
           {width: '120',title: '上报时间',field: 'sbtime',sortable : true},
           {width: '120',title: '审核时间',field: 'shtime',sortable : true},
           {width: '80',title: '审核人员',field: 'shryName'},
           {width: '80',title: '指定整改人员',field: 'zgry'},
           {width: '120',title: '整改反馈时间',field: 'zgfktime',sortable : true},
           {width: '80',title: '整改反馈人员',field: 'zgfkryName'}
		]],
		onLoadSuccess : function(data) {
			hiddenButtonBase("dataGrid_gczlYdxjGPZLXJInfo",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
		},
        onCheck : function(){
        	hiddenButtonBase("dataGrid_gczlYdxjGPZLXJInfo",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onUncheck : function(){
        	hiddenButtonBase("dataGrid_gczlYdxjGPZLXJInfo",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onCheckAll : function(){
        	hiddenButtonBase("dataGrid_gczlYdxjGPZLXJInfo",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onUncheckAll : function(){
        	hiddenButtonBase("dataGrid_gczlYdxjGPZLXJInfo",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onClickRow:function(){
        	hiddenButtonBase("dataGrid_gczlYdxjGPZLXJInfo",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        }
		 
	});
	//新增页面加载分类
	$('#add_typeName_gczlYdxjGPZLXJInfo').combotree({
		url :basePath+'/gczl/gpzlType/gpDdDic',
	    multiple: false,
	    required: true,
	    panelHeight : 'auto',
	    onSelect:function(node) {
	    	  var typeName = node.id;
	    	  selectDivChild(typeName,"selectChild_gczlYdxjGPZLXJInfo","add");
	    }
	});
	
	/**
	 *获取组织机构
	 *
	 */
    $('#query_xjbm').combotree({
    	url : basePath+'/sys/orgz/tree?flag=true',
        lines : true,
        panelHeight : 'auto'
    });
	
	//新增页同加载区间信息
	$('#add_qlBh_gczlYdxjGPZLXJInfo').combobox({
		url :basePath+'/project/line/getProSectionDic?proId='+$("#sushuGcId_gczlYdxjGPZLXJInfo").val(),
		valueField : "id",
		textField : "sectionName",
		multiple: false,
		required: true,
		panelHeight : 'auto',
		onLoadSuccess : function(data) {
//			showJson("",data)
		},
		onSelect:function(node) {
	    	loadXlComboBox(node.id,"add_xlBh_gczlYdxjGPZLXJInfo");
	    }
	});
	//修改页面加载分类
	$('#upd_typeName_gczlYdxjGPZLXJInfo').combotree({
		url :basePath+'/gczl/gpzlType/gpDdDic',
		multiple: false,
		required: true,
		panelHeight : 'auto',
		onSelect:function(node) {
			var typeName = node.id;
			selectDivChild(typeName,"selectChild_gczlYdxjGPZLXJInfo_upd",null);
		}
	});
	
	//修改页同加载区间信息
	$('#upd_qlBh_gczlYdxjGPZLXJInfo').combobox({
		url :basePath+'/project/line/getProSectionDic?proId='+$("#sushuGcId_gczlYdxjGPZLXJInfo").val(),
		valueField : "id",
		textField : "sectionName",
		multiple: false,
		required: true,
		panelHeight : 'auto',
		onLoadSuccess : function(data) {
//			showJson("",data)
		},
		onSelect:function(node) {
			loadXlComboBox(node.id,"upd_xlBh_gczlYdxjGPZLXJInfo");
		}
	});
	selectGpps = $("#gpps_div_gczlYdxjGPZLXJInfo").detach();
	selectGpct = $("#gpct_div_gczlYdxjGPZLXJInfo").detach();
	selectLsfj = $("#lsfj_div_gczlYdxjGPZLXJInfo").detach();
	selectSls = $("#sls_div_gczlYdxjGPZLXJInfo").detach();
	selectSbShow = $("#shangBaoShow_div_gczlYdxjGPZLXJInfo").detach();
	selectZgShow = $("#zhengGaiShow_div_gczlYdxjGPZLXJInfo").detach();
	selectAllShow = $("#allShow_div_gczlYdxjGPZLXJInfo").detach();
	edit_saveButton = $("#edit_saveButton_gczlYdxjGPZLXJInfo").detach();
	
	//查询条件加载分类
	$('#query_typeName_gczlYdxjGPZLXJInfo').combotree({
		url :basePath+'/gczl/gpzlType/gpDdDic',
	    multiple: false,
	    required: true,
	    panelHeight : 'auto',
		onSelect:function(node) {
			$('#query_typeChild_gczlYdxjGPZLXJInfo').combotree({
				multiple: false,
				required: true,
				panelHeight : 'auto'
			});
			loadTypInfoDic("query_typeChild_gczlYdxjGPZLXJInfo",node.text);
		}
	});
	
	/**
	 *获取项目信息
	 *
	 */
	$('#query_gcBh_gczlYdxjGPZLXJInfo').combobox({
		url : basePath+'/project/section/getProDic',
		valueField : "id",
		textField : "proName",
		onLoadSuccess : function() {
			var val = $(this).combobox('getData');
			for (var item in val[0]) {
				if (item == 'id') {
					$(this).combobox('select', val[0][item]);
				}
			}
		}
	});
});


/**
 * 根据区间加载线路信息
 */
function loadXlComboBox(sectionId,xlbhId){
	$('#'+xlbhId).combobox({
		url :basePath+'/project/line/getProLineDic?sectionId='+sectionId,
		valueField : "id",
		textField : "lineName",
		multiple: false,
		required: true,
		panelHeight : 'auto',
		onLoadSuccess : function(data) {
//			showJson("",data)
		}
	});
}
//公共属性页面
function selectDivChild(typeName,divId,option){
	selectGpps.detach();
	selectGpct.detach();
	selectLsfj.detach();
	selectSls.detach();
	selectSbShow.detach();
	selectZgShow.detach();
	selectAllShow.detach();
	
	if(typeName=="管片破损"){
		$("#"+divId).prepend(selectGpps);
		loadTypInfoDic("add_psqkms_gczlYdxjGPZLXJInfo","管片破损");
	}
	if(typeName=="管片错台")$("#"+divId).prepend(selectGpct);
	if(typeName=="螺栓复紧")$("#"+divId).prepend(selectLsfj);
	if(typeName=="渗漏水"){
		$("#"+divId).prepend(selectSls);
		loadTypInfoDic("add_psqksls_gczlYdxjGPZLXJInfo","渗漏水");
	}
	
	if(option=="shh"){//审核
		$("#"+divId).append(selectSbShow);
	}else if(option=="zhg"){//整改
		$("#"+divId).append(selectZgShow);
	}else if(option=="show"){//查看
		$("#"+divId).append(selectAllShow);
	}else{//新增或修改时，允许页面可编辑
		disableTag("gpps_div_gczlYdxjGPZLXJInfo",false);
		disableTag("gpct_div_gczlYdxjGPZLXJInfo",false);
		disableTag("lsfj_div_gczlYdxjGPZLXJInfo",false);
		disableTag("sls_div_gczlYdxjGPZLXJInfo",false);
		FormUtil.clearForm($("#gpps_div_gczlYdxjGPZLXJInfo"));
		FormUtil.clearForm($("#gpct_div_gczlYdxjGPZLXJInfo"));
		FormUtil.clearForm($("#lsfj_div_gczlYdxjGPZLXJInfo"));
		FormUtil.clearForm($("#sls_div_gczlYdxjGPZLXJInfo"));
	}
}

/** 【盾构施工质量巡检信息】打开新增页面 */
function addGczlYdxjGPZLXJInfoFun() {
	if(_getCurrOrgzType() =='0'){$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');	return;}//非项目部下人员无权操作数据
	FormUtil.clearForm($("#addDialog_gczlYdxjGPZLXJInfo"));
	$("#addDialog_gczlYdxjGPZLXJInfo").dialog('open').dialog('setTitle', '添加［'+entityCnName+"］");
	selectDivChild(null,"selectChild_gczlYdxjGPZLXJInfo","add");
}

/** 【盾构施工质量巡检信息】数据保存 */
function addGczlYdxjGPZLXJInfoAjax() {
	$('#addForm_gczlYdxjGPZLXJInfo').form('submit', {
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
				dataGrid_gczlYdxjGPZLXJInfo.datagrid('reload');
				$("#addDialog_gczlYdxjGPZLXJInfo").dialog('close');
				FormUtil.clearForm($("#addDialog_gczlYdxjGPZLXJInfo"));
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}

/** 【盾构施工质量巡检信息】打开数据编辑页面 */
function editGczlYdxjGPZLXJInfoFun() {
//	disableForm('editForm_gczlYdxjGPZLXJInfo',false);
	if(_getCurrOrgzType() =='0'){$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');	return;}//非项目部下人员无权操作数据
	var id;
	var rows = dataGrid_gczlYdxjGPZLXJInfo.datagrid('getSelections');
	if (rows[0]) {
		var typeName = rows[0].typeName;
		var qkms = rows[0].qkms;
		if(rows[0].zgzt=="已整改"){
			$.messager.alert('提示', '盾构施工质量巡检信息已整改，不能修改。', 'info');
			return;
		}else if(rows[0].shzt=="已审核"){
			$.messager.alert('提示', '盾构施工质量巡检信息已审核，不能修改。', 'info');
			return;
		}else if(rows[0].sbzt=="已上报"){
			$.messager.alert('提示', '盾构施工质量巡检信息已上报，不能修改。', 'info');
			return;
		}
		selectDivChild(typeName,"selectChild_gczlYdxjGPZLXJInfo_upd","edit");
		miaoshuParamter(typeName,qkms,rows[0]);
		
		$("#editDialog_gczlYdxjGPZLXJInfo").dialog('open').dialog('setTitle', '编辑［'+entityCnName+'］'+'-'+rows[0][nameField]);
		$('#editForm_gczlYdxjGPZLXJInfo').form('load', rows[0]);
	}
	$("#edit_optionName").val("修改");
	disableTag("editForm_gczlYdxjGPZLXJInfo",false);
	$("#edit-dialog-buttons").prepend(edit_saveButton);
}

/** 【盾构施工质量巡检信息】提交修改 */
function editGczlYdxjGPZLXJInfoAjax() {
	var isValid = $('#editForm_gczlYdxjGPZLXJInfo').form('validate');
	if (!isValid) return;
//	alert($("#editId").val());
	disableTag("editId",false);
	$.messager.confirm('提示', '您确定要编辑［'+entityCnName + '］吗？', function(b) {
		if (b) {
			progressLoad();
			$('#editForm_gczlYdxjGPZLXJInfo').form('submit', {
				url : path+'/edit',
				onSubmit : function() {
				},
				success : function(result) {
					result = $.parseJSON(result);
					if (result.success) {
						$.messager.show({title:'提示',msg:result.msg,showType:'show' });
						dataGrid_gczlYdxjGPZLXJInfo.datagrid('reload');
						$("#editDialog_gczlYdxjGPZLXJInfo").dialog('close');
					}
					else {
						$.messager.alert('错误', result.msg, 'error');
					}
				}
			});
			progressClose();
		}
	});
}

/** 【盾构施工质量巡检信息】数据删除 */
function deleteGczlYdxjGPZLXJInfoFun() {
	if(_getCurrOrgzType() =='0'){$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');	return;}//非项目部下人员无权操作数据
	var ids = new Array();
	var rows = dataGrid_gczlYdxjGPZLXJInfo.datagrid('getSelections');
	for(var i=0; i<rows.length; i++){
		if(rows[i].sbzt=="已上报"){
			$.messager.alert('提示', '您勾选了已上报的数据，不能删除！', 'info');
			return;
		}else{
			ids.push(rows[i].id);
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
					dataGrid_gczlYdxjGPZLXJInfo.datagrid('reload');
					dataGrid_gczlYdxjGPZLXJInfo.datagrid('clearSelections');
				}
				progressClose();
			}, 'JSON');
		}
	});
}

/** 【盾构施工质量巡检信息】查看数据页面 */
function showGczlYdxjGPZLXJInfoFun(oid,index) {
	var id;
	var row;
	if(oid){
		id = oid;
		var allrows = dataGrid_gczlYdxjGPZLXJInfo.datagrid('getRows');
		row = allrows[index];
	}else{
		var rows = dataGrid_gczlYdxjGPZLXJInfo.datagrid('getSelections');
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
		var typeName = row.typeName;
		var qkms = row.qkms;
		selectDivChild(typeName,"selectChild_gczlYdxjGPZLXJInfo_upd","show");
		miaoshuParamter(typeName,qkms,row);
		$("#editDialog_gczlYdxjGPZLXJInfo").dialog('open').dialog('setTitle', '查看［'+entityCnName+'］'+'-'+row[nameField]);
		$('#editForm_gczlYdxjGPZLXJInfo').form('load', row);
		disableTag("editForm_gczlYdxjGPZLXJInfo",true)
		edit_saveButton.detach();//隐藏保存按钮
	}
}

/**
 * 【盾构施工质量巡检信息】导出excel
 * @returns
 */
function expGczlYdxjGPZLXJInfoXls(){
	var url = path + "/expXls";
	ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, entityCnName+"信息", dataGrid_gczlYdxjGPZLXJInfo, false);
}

/**
 * 【盾构施工质量巡检信息】刷新
 * @returns
 */
function reloadGczlYdxjGPZLXJInfo(){
	dataGrid_gczlYdxjGPZLXJInfo.datagrid('reload');
}
/**
 * 【盾构施工质量巡检信息】上报
 * @returns
 */
function sendUpGczlYdxjGPZLXJInfo(){
	if(_getCurrOrgzType() =='0'){$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');	return;}//非项目部下人员无权操作数据
	var ids = new Array();
	var rows = dataGrid_gczlYdxjGPZLXJInfo.datagrid('getSelections');
	for(var i=0; i<rows.length; i++){
		if(rows[i].sbzt=="已上报"){
			$.messager.alert('提示', '环号:'+rows[i].hh+' 点位:'+rows[i].dw+' 盾构施工质量巡检信息已上报，不能重复上报！', 'info');
			return;
		}else{
			ids.push(rows[i].id+"#"+rows[i].updateTime);
		}
	}
	$.messager.confirm('询问', '您是否要上报［'+entityCnName + '］所选行吗？', function(b) {
		if (b) {
			progressLoad();
			$.post(path+'/shb', {
				ids : ids.join(",")
			}, function(result) {
				if (result.success) {
					$.messager.show({title:'提示',msg:result.msg,showType:'show' });
					dataGrid_gczlYdxjGPZLXJInfo.datagrid('reload');
					//dataGrid_gczlYdxjGPZLXJInfo.datagrid('clearSelections');
					progressClose();
				}else{
					$.messager.alert('提示', result.msg, 'info');
					progressClose();
					return;
				}
			}, 'JSON');
		}
	});
}
/**
 * 【盾构施工质量巡检信息】审核
 * @returns
 */
function shengHeGczlYdxjGPZLXJInfo(){
	if(_getCurrOrgzType() =='0'){$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');	return;}//非项目部下人员无权操作数据
	var id;
	var rows = dataGrid_gczlYdxjGPZLXJInfo.datagrid('getSelections');
	var row = rows[0];
	if (row) {
		if(row.sbzt!="已上报"){
			$.messager.alert('提示', '该盾构施工质量巡检信息'+row.sbzt+'，不能审核！', 'info');
			return;
		}else if(row.shzt=="已审核"||row.shzt=="已打回"){
			$.messager.alert('提示', '该盾构施工质量巡检信息'+row.shzt+'，不能审核！', 'info');
			return;
		}
		var typeName = row.typeName;
		var qkms = row.qkms;
		selectDivChild(typeName,"selectChild_gczlYdxjGPZLXJInfo_upd","shh");
		
		miaoshuParamter(typeName,qkms,row);
		$("#editDialog_gczlYdxjGPZLXJInfo").dialog('open').dialog('setTitle', '审核［'+entityCnName+'］'+'-'+row[nameField]);
		$('#editForm_gczlYdxjGPZLXJInfo').form('load', row);
//		alert($("#editId").val());
//		showJson("",row);
		$("#edit_optionName").val("审核");
		disableTag("editForm_gczlYdxjGPZLXJInfo",true)
		disableTag("shangBaoShow_tr_shqk",false);
		disableTag("shangBaoShow_tr_shyj",false);
		disableTag("sh_zgjzTime",false);		
	}
	$("#edit-dialog-buttons").prepend(edit_saveButton);
}

/**
 * 【盾构施工质量巡检信息】整改
 * @returns
 */
function zhengGaiFkGczlYdxjGPZLXJInfo(){
	if(_getCurrOrgzType() =='0'){$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');	return;}//非项目部下人员无权操作数据
	var id;
	var rows = dataGrid_gczlYdxjGPZLXJInfo.datagrid('getSelections');
	var row = rows[0];
	if (row) {
		
		if(row.shzt!="已审核"){
			$.messager.alert('提示', '该盾构施工质量巡检信息'+row.shzt+'，不能反馈！', 'info');
			return;
		}else if(row.zgzt=="已整改"){
			$.messager.alert('提示', '该盾构施工质量巡检信息'+row.zgzt+'，不能反馈！', 'info');
			return;
		}
		var typeName = row.typeName;
		var qkms = row.qkms;
		miaoshuParamter(typeName,qkms,row);
		selectDivChild(typeName,"selectChild_gczlYdxjGPZLXJInfo_upd","zhg");
		$("#editDialog_gczlYdxjGPZLXJInfo").dialog('open').dialog('setTitle', '整改［'+entityCnName+'］'+'-'+row[nameField]);
		$('#editForm_gczlYdxjGPZLXJInfo').form('load', row);
		$("#edit_optionName").val("整改");
		disableTag("editForm_gczlYdxjGPZLXJInfo",true)
		disableTag("zhengGaiShow_tr_zgjg",false);
		disableTag("zhengGaiShow_tr_zgsm",false);
	}
	$("#edit-dialog-buttons").prepend(edit_saveButton);
}

/**
 * 根据“管片问题类型”将描述信息中的值抽出赋到row中
 * @param typeName 类型名称
 * @param qkms 情况描述
 * @param row 行属性
 * @returns
 */
function miaoshuParamter(typeName,qkms,row){
//	alert(typeName+"\r\n"+qkms+"\r\n"+showJson("",row));
	if(typeName=="管片破损"){
		//破损情况描述：角部破损；破损尺寸描述（长X宽）：12mm×23mm。
		var gppsQkMiaoshu = qkms.substring(0,qkms.indexOf('；'));
		gppsQkMiaoshu = gppsQkMiaoshu.replace("破损情况描述：","");
		qkms = qkms.substring(qkms.indexOf('；')+1);
		qkms = qkms.replace("破损尺寸描述（长X宽）：","");
		qkms = qkms.replace("mm×",",");
		qkms = qkms.replace("mm。","");
		var lengthAndwide = qkms.split(",");
		row.gppsLength = lengthAndwide[0];
		row.gppsWidth = lengthAndwide[1];
		row.gppsQkMiaoshu = gppsQkMiaoshu;
	}else if(typeName=="管片错台"){
		//最大错台尺寸：径向（23mm），环向（32mm）。
		qkms = qkms.replace("最大错台尺寸：径向（","");
		qkms = qkms.replace("mm），环向（",",");
		qkms = qkms.replace("mm）。","");
		var jinAndHuan = qkms.split(",");
		row.gpctJinXiang = jinAndHuan[0];
		row.gpctHuanXiang = jinAndHuan[1];
	}else if(typeName=="螺栓复紧"){
		//复紧不到位数量：23处。
		qkms = qkms.replace("复紧不到位数量：","");
		qkms = qkms.replace("处。","");
		row.lsfjNums = qkms;
	}else if(typeName=="渗漏水"){
		//渗水情况描述：滴水。
		qkms = qkms.replace("渗水情况描述：","");
		qkms = qkms.replace("。","");
		row.slsMiaoShu = qkms;
	}
}


/**
 * 【盾构施工质量巡检信息】取消已选
 * @returns
 */
function clearGczlYdxjGPZLXJInfoSelections(){
	dataGrid_gczlYdxjGPZLXJInfo.datagrid('clearSelections');
}/** 【盾构施工质量巡检信息】表单查询 */
function searchGczlYdxjGPZLXJInfoFun() {
	dataGrid_gczlYdxjGPZLXJInfo.datagrid('load', $.serializeObject($('#searchForm_gczlYdxjGPZLXJInfo')));
}

/**【盾构施工质量巡检信息】表单重置 */
function cleanGczlYdxjGPZLXJInfoFun() {
	$('#searchForm_gczlYdxjGPZLXJInfo input').val('');
	dataGrid_gczlYdxjGPZLXJInfo.datagrid('load', {});
}
//加载管片分类列表
function loadTypDic(id){
	$('#'+id).combotree({
		url :basePath+'/gczl/gpzlType/gpDdDic',
	    multiple: false,
	    required: true,
	    panelHeight : 'auto'
	});
}

//
////加载区间信息
//	$('#'+id).combobox({
//		url :basePath+'/project/line/getProSectionDic',
//		valueField : "id",
//		textField : "sectionName",
//		multiple: false,
//		required: true,
//		panelHeight : 'auto',
//		onLoadSuccess : function() {}
//	});
	
/**
 * 员工选择框
 * @returns
 */
function empSelection(e){
	var id_input = "add_empId";
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
            		$(e.data.target).textbox("setValue",rows[0].name);
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

/**
 * 上传图片
 * @returns
 */
function uploadGczlYdxjGPZLXJInfoImg(param){
	if(_getCurrOrgzType() =='0'){$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');	return;}//非项目部下人员无权操作数据
	var rows = dataGrid_gczlYdxjGPZLXJInfo.datagrid('getSelections');
	var beizhu = '整改前';
	if(param=='1'){
		beizhu='整改后';
	}
	_uploadPicture(rows[0].id,syspath,beizhu,dataGrid_gczlYdxjGPZLXJInfo,entityCnName);
}
/**
 * @param sltId
 * @param ystId
 * @returns
 */
function showMyPic(keyIdShow){
	var keyId = $("#"+keyIdShow).val();//缩略图路径
	_showPic(keyId);
}

//加载基础数据列表
function loadTypInfoDic(id,typeName){
	$.post(basePath+'/gczl/base/gczlYdxjGPZLDDInfo/gpDdInfoDic', {typeName : typeName}, 
	 function(result) {
		$('#'+id).combotree("loadData",result);
	}, 'JSON');
}
