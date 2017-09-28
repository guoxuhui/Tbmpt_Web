 /** /** 【实测中线信息管理】管片姿态测量系统 基础模块
 * 创建时间：2016-12-20
   */
/** 系统模块同路径 */
var syspath = "gpztcl";
/** 子模块路径 */
var module = "base";
/** 菜单名称 */
var entityCnName = "实测中线信息管理";
/** 名称字段 */
var nameField = "";
/** 业务数据访问全路径 */
var path = basePath +"/"+ syspath +"/"+ module+"/gpztclSczxpc";
/** 子表业务数据访问全路径 */
var childPath = basePath +"/"+ syspath +"/"+ module+"/gpztclSczxInfo";
/** 列表对象 */
var dataGrid_gpztclSczxpc;
var dataGrid_gpztclSczxInfo;
/**若无一条勾选数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayZero = ["cancelSelect_button_gpztclSczxpc","edit_button_gpztclSczxpc","del_button_gpztclSczxpc","sb_button_gpztclSczxpc","cancelSb_button_gpztclSczxpc"];
/**若只勾选一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayOnlyOneItem = [];
/**若勾选大于一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayMoreOneItem =["edit_button_gpztclSczxpc"];
var operateType = 'add';//操作类型，默认为新增（导入并解析文件用同一套代码，因此用此值来区分 'add' 为新增；'edit' 为编辑）
var editRow = undefined;
$(function() {
	/** 创建数据表格	 */
	dataGrid_gpztclSczxpc = $('#dataGrid_gpztclSczxpc').datagrid({
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
		        	{width: '300',title: '所属项目',field: 'gcName'},
		        	{width: '150',title: '区间名称',field: 'qlName'},
		        	{width: '150',title: '线路名称',field: 'xlName'}
		        ]],
		columns:[[
			{width: '150',title: '测量日期',field: 'clTime',
				formatter: function(value,row,index){
					return '<a href="#" onclick="showGpztclSczxpcFun(\''+ row.id +'\')">' + value + '</a>';
				}
			},
			{width: '80',title: '测量类型',field: 'clType'},
			{width: '100',title: '上报状态',field: 'sendState'},
           {width: '80',title: '起始环号',field: 'starNo'},
           {width: '80',title: '截止环号',field: 'endNo'},
           {width: '100',title: '操作人员',field: 'impManName'},
           {width: '150',title: '操作时间',field: 'impTime'}
		]],
		toolbar : '#toolbar',
		onLoadSuccess : function(data) {
			hiddenButtonBase("dataGrid_gpztclSczxpc",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
		},
        onCheck : function(){
        	hiddenButtonBase("dataGrid_gpztclSczxpc",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onUncheck : function(){
        	hiddenButtonBase("dataGrid_gpztclSczxpc",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onCheckAll : function(){
        	hiddenButtonBase("dataGrid_gpztclSczxpc",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onUncheckAll : function(){
        	hiddenButtonBase("dataGrid_gpztclSczxpc",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onClickRow:function(){
        	hiddenButtonBase("dataGrid_gpztclSczxpc",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        }
	});
	_getProDic("gcBh_search","qlBh_search","xlBh_search");//构造查询项目，区间，线路下拉框
	createclTypeSelect('clType_search');//构造查询测量类型下拉框
	
	/**
	 * 新增窗口关闭前事件校验数据是否修改
	 */
	$("#addDialog_gpztclSczxpc").dialog({  
		onBeforeClose: function () {  
			var dataSta = $('#dataSta').val();//数据状态 original:原始状态；modified:修改状态;
        	if(dataSta !=null && dataSta =='modified'){
        		 $.messager.confirm('询问', '您尚未保存数据，确定离开页面吗？', function(b) {
    	  	        if (b) {
    	  	        	$('#dataSta').val('original');//关闭页面后，数据状态恢复为原始状态
    		            $("#addDialog_gpztclSczxpc").dialog('close');
    	  	        }
        		 })
        	}else{
        		return true;
        	}
        	return false;
	    }  
	});
	/**
	 * 修改窗口关闭前事件校验数据是否修改
	 */
	$("#editDialog_gpztclSczxpc").dialog({  
		onBeforeClose: function () {  
			var dataSta = $('#dataStaEdit').val();//数据状态 original:原始状态；modified:修改状态;
        	if(dataSta !=null && dataSta =='modified'){
        		 $.messager.confirm('询问', '您尚未保存数据，确定离开页面吗？', function(b) {
    	  	        if (b) {
    	  	        	$('#dataStaEdit').val('original');//关闭页面后，数据状态恢复为原始状态
    		            $("#editDialog_gpztclSczxpc").dialog('close');
    	  	        }
        		 })
        	}else{
        		return true;
        	}
        	return false;
	    }  
	});
});
/** 【实测中线信息管理】打开新增页面 */
function addGpztclSczxpcFun() {
//	var userType  = _getCurrOrgzType();
//	if(userType =='0'){
//		//机关单位
//		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
//		return;
//	}
	var testUrl =  basePath +"/zsjk/jt/zsJkJtXmxx/getXMXXList";
	AjaxAction(testUrl);
	//return ;
	operateType = 'add';
	FormUtil.clearForm($("#addDialog_gpztclSczxpc"));
	$("#add_gcName_gpztclSczxpc").html('');
	$("#add_qlName_gpztclSczxpc").html('');
	createclTypeSelect('add_clType_gpztclSczxpc',true);//构造查询测量类型下拉框
	_createLineGried("add_xlBh_gpztclSczxpc","callBackOfCreateXlSelect");
	initLoadChildGrid('dataGrid_gpztclSczxInfo','-1');//加载明细列表
	$("#addDialog_gpztclSczxpc").dialog('open').dialog('setTitle', '添加［'+entityCnName+"］");
}

/** 【实测中线信息管理】数据保存 */
function addGpztclSczxpcAjax() {
	
	var detailsInfo=new Array();
	var rows = $("#dataGrid_gpztclSczxInfo").datagrid("getRows"); 
	if(rows.length <=0 ){
		parent.$.messager.alert('提示', '列表无明细信息，不可新增。', 'info');
		return;
	}
	if (editRow != undefined) {
        $("#dataGrid_gpztclSczxInfo").datagrid('endEdit', editRow);
    }
	for(var i=0;i<rows.length;i++)	{
		var obj = new Object();
		obj.id=rows[i].id;
		obj.hh=rows[i].hh;
		obj.sczbX=rows[i].sczbX;
		obj.sczbY=rows[i].sczbY;
		obj.sczbH=rows[i].sczbH;
		
		obj.jszbX=rows[i].jszbX;
		obj.jszbY=rows[i].jszbY;
		obj.jszbH=rows[i].jszbH;
		
		obj.dxpyX=rows[i].dxpyX;
		obj.dxpyY=rows[i].dxpyY;
		
		obj.lc=rows[i].lc;
		obj.remarks=rows[i].remarks;
		
		detailsInfo[i] = obj;
	}
	var json = JSON.stringify(detailsInfo);
	$('#add_details').val(json);
	$('#addForm_gpztclSczxpc').form('submit', {
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
				dataGrid_gpztclSczxpc.datagrid('reload');
				$('#dataSta').val('original');//关闭页面后，数据状态恢复为原始状态
				$("#addDialog_gpztclSczxpc").dialog('close');
				FormUtil.clearForm($("#addDialog_gpztclSczxpc"));
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}

/** 【实测中线信息管理】打开数据编辑页面 */
function editGpztclSczxpcFun() {
	/*var userType  = _getCurrOrgzType();
	if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}*/
	var id;
	var rows = dataGrid_gpztclSczxpc.datagrid('getSelections');
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
	operateType = 'edit';
	if (rows[0]) {
		$("#editDialog_gpztclSczxpc").dialog('open').dialog('setTitle', '编辑［'+entityCnName+'］');
		$("#edit_gcName_gpztclSczxpc").html(rows[0].gcName);
		$("#edit_qlName_gpztclSczxpc").html(rows[0].qlName);
		createclTypeSelect('edit_clType_gpztclSczxpc',true);//构造查询测量类型下拉框
		_createLineGried("edit_xlBh_gpztclSczxpc","callBackOfCreateXlSelect");
		$('#editForm_gpztclSczxpc').form('load', rows[0]);
		initLoadChildGrid('dataGrid_gpztclSczxInfo_edit',rows[0].id);
//		initLoadChildGridEdit(rows[0].id)
	}
}

/** 【实测中线信息管理】提交修改 */
function editGpztclSczxpcAjax() {
	var isValid = $('#editForm_gpztclSczxpc').form('validate');
	if (!isValid) return;
	
	var detailsInfo=new Array();
	var rows = $("#dataGrid_gpztclSczxInfo_edit").datagrid("getRows"); 
	if(rows.length <=0 ){
		parent.$.messager.alert('提示', '列表无明细信息，不可保存数据。', 'info');
		return;
	}
	if (editRow != undefined) {
        $("#dataGrid_gpztclSczxInfo_edit").datagrid('endEdit', editRow);
    }
	for(var i=0;i<rows.length;i++)	{
		var obj = new Object();
		
		obj.id=rows[i].id;
		obj.hh=rows[i].hh;
		obj.sczbX=rows[i].sczbX;
		obj.sczbY=rows[i].sczbY;
		obj.sczbH=rows[i].sczbH;
		
		obj.jszbX=rows[i].jszbX;
		obj.jszbY=rows[i].jszbY;
		obj.jszbH=rows[i].jszbH;
		
		obj.dxpyX=rows[i].dxpyX;
		obj.dxpyY=rows[i].dxpyY;
		
		obj.remarks=rows[i].remarks;
		obj.lc=rows[i].lc;
		
		detailsInfo[i] = obj;
		
	}
	var json = JSON.stringify(detailsInfo);
	$('#edit_details').val(json);
	
	$.messager.confirm('提示', '您确定要保存数据吗？', function(b) {
		if (b) {
			$('#editForm_gpztclSczxpc').form('submit', {
				url : path+'/edit',
				onSubmit : function() {
				  progressLoad();
				},
				success : function(result) {
					progressClose();
					result = $.parseJSON(result);
					if (result.success) {
				        $.messager.show({title:'提示',msg:'修改操作：成功!',showType:'show' });
						dataGrid_gpztclSczxpc.datagrid('reload');
						$('#dataStaEdit').val('original');//关闭页面后，数据状态恢复为原始状态
						$("#editDialog_gpztclSczxpc").dialog('close');
					}
					else {
						$.messager.alert('错误', result.msg, 'error');
					}
				}
			});
		}
	});
}

/** 【实测中线信息管理】数据删除 */
function deleteGpztclSczxpcFun() {
	var userType  = _getCurrOrgzType();
	if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}
	var ids = new Array();
	var rows = dataGrid_gpztclSczxpc.datagrid('getSelections');
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
					dataGrid_gpztclSczxpc.datagrid('reload');
					dataGrid_gpztclSczxpc.datagrid('clearSelections');
				}
			}, 'JSON');
		}
	});
}

/** 【实测中线信息管理】查看数据页面 */
function showGpztclSczxpcFun(oid,index) {
	var id;
	var row;
	if(oid){
		id = oid;
		var allrows = dataGrid_gpztclSczxpc.datagrid('getRows');
		row = allrows[index];
	}else{
		var rows = dataGrid_gpztclSczxpc.datagrid('getSelections');
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
		$("#showDialog_gpztclSczxpc").dialog('open').dialog('setTitle', '查看［'+entityCnName+'］'+'-'+row[nameField]);
		$('#showDialog_gpztclSczxpc').form('load', row);
	}
}

/**
 * 【实测中线信息管理】导出excel
 * @returns
 */
function expGpztclSczxpcXls(){
	var url = path + "/expXls";
	ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, entityCnName+"信息", dataGrid_gpztclSczxpc, false);
}

/**
 * 【实测中线信息管理】刷新
 * @returns
 */
function reloadGpztclSczxpc(){
	dataGrid_gpztclSczxpc.datagrid('reload');
}
/**
 * 方法说明：子表添加页面子表明细列表刷新操作
 * @returns
 * @author:YangYj
 * @Time: 2016年12月23日 下午1:41:50
 */
function reloadGpztclSczxInfo(){
	if(null != operateType && 'edit' == operateType){
		//编辑页面
		gridShowId = "dataGrid_gpztclSczxInfo_edit";
	}else{
		//新增页面
		gridShowId = "dataGrid_gpztclSczxInfo";
	}
	$("#"+gridShowId).datagrid('reload');
}
/**
 * 方法说明：编辑页面子表明细列表刷新操作
 * @returns
 * @author:YangYj
 * @Time: 2016年12月23日 下午1:41:53
 */
function reloadGpztclSczxInfo_edit(){
	$("#dataGrid_gpztclSczxInfo_edit").datagrid('reload');
}

/**
 * 【实测中线信息管理】取消已选
 * @returns
 */
function clearGpztclSczxpcSelections(){
	dataGrid_gpztclSczxpc.datagrid('clearSelections');
}
/** 【实测中线信息管理】表单查询 */
function searchGpztclSczxpcFun() {
	dataGrid_gpztclSczxpc.datagrid('load', $.serializeObject($('#searchForm_gpztclSczxpc')));
}

/**【实测中线信息管理】表单重置 */
function cleanGpztclSczxpcFun() {
	$('#searchForm_gpztclSczxpc input').val('');
	dataGrid_gpztclSczxpc.datagrid('load', {});
}

/**
 * 构造测量了您下拉框
 */
function createclTypeSelect(id,required){
	if(null == required || '' == required || 'undefined' == required){
		required = false;
	} 
	$("#"+id).combobox({
		required : required,
		valueField:'id',
		textField:'text',
		data: 
		[{
			id: '首测',
			text: '首测'
		 },{
			 id: '复测',
			text: '复测'
		 }]
	});
	/*点击文本框内，就显示下拉面板*/
	$("#"+id).combobox('textbox').click(function(){		
		$("#"+id).combobox('showPanel');
	});
}


/**
 * 方法说明：构造区间下拉后回调函数
 * @param rowData
 * @returns
 * @author:YangYj
 * @Time: 2016年12月21日 下午2:48:48
 */
function callBackOfCreateXlSelect(rowData){
	if(null != operateType && 'edit' == operateType){
		//编辑页面
		$("#edit_gcName_gpztclSczxpc").html(rowData.proName);
		$("#edit_gcBh_gpztclSczxpc").val(rowData.proId);
		$("#edit_qlName_gpztclSczxpc").html(rowData.sectionName);
		$("#edit_qlBh_gpztclSczxpc").val(rowData.sectionId);
	}else{
		//新增页面
		$("#add_gcName_gpztclSczxpc").html(rowData.proName);
		$("#add_gcBh_gpztclSczxpc").val(rowData.proId);
		$("#add_qlName_gpztclSczxpc").html(rowData.sectionName);
		$("#add_qlBh_gpztclSczxpc").val(rowData.sectionId);
	}
}

/**
 * 方法说明：上报/撤销上报操作
 * @param state 0：撤销上报；1：上报
 * @returns
 * @author:YangYj
 * @Time: 2016年12月24日 下午2:57:59
 */
function ifSb(state) {
	var userType  = _getCurrOrgzType();
	if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}
	var ids = new Array();
	var rows = dataGrid_gpztclSczxpc.datagrid('getSelections');
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].id);
		if(state=='1' ){
			//已上报状态的数据不可再次上报
			if(rows[i].sendState !=null && rows[i].sendState =='已上报'){
				$.messager.alert('提示', '选择的数据上报状态为【已上报】，不可重复上报。', 'info');
				return;
			}
		}
		if(state=='0' ){
			//启用状态的数据不可再次启用
			if(rows[i].sendState !=null && rows[i].sendState !='已上报'){
				$.messager.alert('提示', '选择的数据未上报，不可撤销。', 'info');
				return;
			}
		}
	}
	$.messager.confirm('询问', '您是否要处理［'+entityCnName + '］所选行吗？', function(b) {
		if (b) {
			progressLoad();
			$.post(path+'/ifsB', {
				ids : ids.join(","),
				state:state
			}, function(result) {
				if (result.success) {
					$.messager.show({title:'提示',msg:result.msg,showType:'show' });
					dataGrid_gpztclSczxpc.datagrid('reload');
					dataGrid_gpztclSczxpc.datagrid('clearSelections');
				}
				progressClose();
			}, 'JSON');
		}
	});
}


//=======================================================子表操作方法=============================================//
/**
 * 方法说明：主表新增时初始化子表列表数据
 * @param gridShowId 列表页面显示id
 * @param fid 父id
 * @returns
 * @author:YangYj
 * @Time: 2016年12月23日 下午2:00:36
 */
function initLoadChildGrid(gridShowId,fid){
	/** 创建数据表格	 */
	dataGrid_gpztclSczxInfo = $('#'+gridShowId).datagrid({
		url : childPath+'/dataGrid?fid='+fid,
		striped : true,
		rownumbers : true,
		singleSelect : false,
		checkOnSelect : true,
		selectOnCheck : true,
		pagination : false,
		idField : 'id',
		pageSize : 500,
		pageList : [ 500, 600,800, 1000 ],
		columns:[[
			{field:'id',align:'center',title:'ID',width:40, checkbox:true,rowspan:2},
			{width: '60',title: '环号',field: 'hh',rowspan:2 },
			{width: '100',title: '里程K(m)',field: 'lc',rowspan:2 },
			{width: '80',title: '实测线路中心线坐标(m)', colspan:3},
			{width: '80',title: '线路中心线设计坐标(m)',colspan:3},
			{width: '80',title: '实测偏移量(mm)',colspan:2 },
			{width: '80',title: '导向系统(mm)',colspan:2 },
			{width: '80',title: '较差(mm)',colspan:2 },
			{width: '100',title: '备注',field: 'remarks',rowspan:2,
			  editor:{
                   type:'text'
                 },
                 formatter: function(value,row,index){
						return DataGridFormatter.tipsFormatter(value,20);
				  }
			}
		 ],[  
           {width: '85',title: 'X',field: 'sczbX'},
           {width: '85',title: 'Y',field: 'sczbY'},
           {width: '85',title: 'H',field: 'sczbH'},
           {width: '85',title: 'X',field: 'jszbX'},
           {width: '85',title: 'Y',field: 'jszbY'},
           {width: '85',title: 'H',field: 'jszbH'},
           {width: '50',title: '横向',field: 'scpylX'},
           {width: '50',title: '竖向',field: 'scpylY'},
           {width: '50',title: '横向',field: 'dxpyX',
	    	   editor:{
	                   type:'numberbox',
	                   options:{precision:0}
	                 }
           },
           {width: '50',title: '竖向',field: 'dxpyY',
        	   editor:{
                   type:'numberbox',
                   options:{precision:0}
                 }
           },
           {width: '50',title: '横向',field: 'jcX'},
           {width: '50',title: '竖向',field: 'jcY'}
		]],
		onLoadSuccess : function(data) {
		},
		onDblClickRow: function (rowIndex, rowData) {
            if (editRow != undefined) {
                $("#"+gridShowId).datagrid('endEdit', editRow);
            }
            if (editRow == undefined) {
                $("#"+gridShowId).datagrid('beginEdit', rowIndex);
                editRow = rowIndex;
            }
        },
        onClickRow: function (rowIndex, rowData) {
            if (editRow != undefined) {
                $("#"+gridShowId).datagrid('endEdit', editRow);
 
            }
        },
        onAfterEdit: function (rowIndex, rowData, changes) {
            editRow = undefined;
            var dxpyX = rowData.dxpyX;//导向系统横向
            var dxpyY = rowData.dxpyY;//导向系统竖向
            var remarks = rowData.remarks;//备注
            var jxX = calculateJxX(rowData.scpylX,rowData.dxpyX);
            var jxY = calculateJxY(rowData.scpylY,rowData.dxpyY);
           $('#'+gridShowId).datagrid('updateRow',{
         		index: rowIndex,
         		row: {
         			dxpyX: dxpyX,
         			dxpyY:dxpyY,
         			jcX:jxX,
         		    jcY:jxY,
         			remarks:remarks
         		}
         	});
           if(null != operateType && 'edit' == operateType){
	       		//编辑
	       		$('#dataStaEdit').val('modified');
	       	}else{
	       		//新增
	       		$('#dataSta').val('modified');
	       	}
            $('#'+gridShowId).datagrid('refreshRow', rowIndex);
        },
		toolbar : '#toolbar2' 
	});
}

/**
 * 方法说明：计算横向较差
 * @param scPylX 实测横向偏移量
 * @param dxPylX 导向系统横向偏移量
 * @returns 返回横向较差
 * @author:YangYj
 * @Time: 2016年12月28日 上午10:14:03
 */
function calculateJxX(scPylX,dxPylX){
	var jxX =0;
	if(null == scPylX || '' == scPylX || null == dxPylX || '' == dxPylX ){
		jxX = null;
	}else{
		jxX = scPylX*1 - dxPylX*1;
	}
	return jxX;
}

/**
 * 方法说明：计算竖向较差
 * @param scPylY 实测竖向偏移量
 * @param dxPylY 导向系统竖向偏移量
 * @returns 返回竖向较差
 * @author:YangYj
 * @Time: 2016年12月28日 上午10:17:51
 */
function calculateJxY(scPylY,dxPylY){
	var jxY =0;
	if(null == scPylY || '' == scPylY || null == dxPylY || '' == dxPylY ){
		jxY = null;
	}else{
		jxY = scPylY*1 - dxPylY*1;
	}
	return jxY;
}

/**
 * 方法说明：新增、编辑 加载明细列表
 * @param fid 主表物理主键
 * @returns
 * @author:YangYj
 * @Time: 2016年12月28日 上午10:15:27
 */
function initLoadChildGridEdit(fid){
	/** 创建数据表格	 */
	$('#dataGrid_gpztclSczxInfo_edit').datagrid({
		url : childPath+'/dataGrid.action?fid='+fid,
		striped : true,
		rownumbers : true,
		singleSelect : false,
		checkOnSelect : true,
		selectOnCheck : true,
		pagination : false,
		idField : 'id',
		pageSize : 500,
		pageList : [ 500, 600,800, 1000 ],
		columns:[[
			{field:'id',align:'center',title:'ID',width:40, checkbox:true,rowspan:2},
			{width: '60',title: '环号',field: 'hh',rowspan:2 },
			{width: '80',title: '实测线路中心线坐标(m)', colspan:3},
			{width: '80',title: '线路中心线设计坐标(m)',colspan:3},
			{width: '80',title: '实测偏移量(mm)',colspan:2 },
			{width: '80',title: '导向系统(mm)',colspan:2 },
			{width: '80',title: '较差(mm)',colspan:2 },
			{width: '100',title: '备注',field: 'remarks',rowspan:2}
		 ],[  
           {width: '85',title: 'X',field: 'sczbX'},
           {width: '85',title: 'Y',field: 'sczbY'},
           {width: '85',title: 'H',field: 'sczbH'},
           {width: '85',title: 'X',field: 'jszbX'},
           {width: '85',title: 'Y',field: 'jszbY'},
           {width: '85',title: 'H',field: 'jszbH'},
           {width: '50',title: '横向',field: 'scpylX'},
           {width: '50',title: '竖向',field: 'scpylY'},
           {width: '50',title: '横向',field: 'dxpyX'},
           {width: '50',title: '竖向',field: 'dxpyY'},
           {width: '50',title: '横向',field: 'jcX'},
           {width: '50',title: '竖向',field: 'jcY'}
		]],
		onLoadSuccess : function(data) {
		},
		toolbar : '#toolbar3' 
	});
//	$('#dataGrid_gpztclSczxInfo_edit').datagrid('loadData', [{}]);
}

/**
 * 方法说明：初始化解析文件数据展示表格
 * @returns
 * @author:YangYj
 * @Time: 2016年12月22日 下午1:08:33
 */
function initLoadParseFileGrid(data){
	/** 创建数据表格	 */
	$('#dataGrid_parseFileGpztclSczxInfo').datagrid({
//		data:data,
		striped : true,
		fit:true,
		fitColumns:true,
		rownumbers : true,
		singleSelect : true,
		checkOnSelect : true,
		selectOnCheck : true,
		pagination : false,
		idField : 'id',
		pageSize : 500,
		pageList : [ 500, 600,800, 1000 ],
		columns:[[
           {width: '120',title: '第一列',field: 'oneColumn'},
           {width: '120',title: '第二列',field: 'towColumn'},
           {width: '120',title: '第三列',field: 'threeColumn'},
           {width: '120',title: '第四列',field: 'fourColumn'}
		]],
		onLoadSuccess : function(data) {}
	});
	$('#dataGrid_parseFileGpztclSczxInfo').datagrid('loadData',data);
	
}


/**
 * 方法说明：打开导入全站仪数据弹出框
 * @returns
 * @author:YangYj
 * @Time: 2016年12月21日 下午5:40:20
 */
function impQzyFile(type){
//	operateType = type;
	FormUtil.clearForm($("#impQzyForm_gpztclSczxpc"));
    $("#impQzyDialog_gpztclSczxpc").dialog('open').dialog('setTitle', '导入全站仪数据');
}
/**
 * 方法说明：解析导入的全站仪数据
 * @returns
 * @author:YangYj
 * @Time: 2016年12月21日 下午5:41:46
 */
function impQzyFileActionAjax(){
	$('#impQzyForm_gpztclSczxpc').form('submit', {
		url : childPath+'/parseQzyData',
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
				$("#impQzyDialog_gpztclSczxpc").dialog('close');
				FormUtil.clearForm($("#parseFileForm_gpztclSczxpc"));
				//构造设置列下拉框
				createColumnSelect("p_hh",true);
				createColumnSelect("p_sczbX",true);
				createColumnSelect("p_sczbY",true);
				createColumnSelect("p_sczbH",true);
				//设置列默认值
				$("#p_hh").combobox('setValue','1');
				$("#p_sczbX").combobox('setValue','2');
				$("#p_sczbY").combobox('setValue','3');
				$("#p_sczbH").combobox('setValue','4');
			    $("#parseFileDialog_gpztclSczxpc").dialog('open').dialog('setTitle', '导入全站仪数据');
			    initLoadParseFileGrid(result.obj);
			    var json = JSON.stringify(result.obj);
			    $("#p_dataList").val(json);
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}
/**
 * 构造测量类型下拉框
 */
function createColumnSelect(id,required){
	if(null == required || '' == required || 'undefined' == required){
		required = false;
	} 
	$("#"+id).combobox({
		required : required,
		valueField:'id',
		textField:'text',
		data: 
		[{
			id: '1',
			text: '第一列'
		 },{
			 id: '2',
			text: '第二列'
		 },{
			 id: '3',
			 text: '第三列'
		 },{
			 id: '4',
		     text: '第四列'
		 }],
		 onChange:function(newvalue,oldvalue){
			 try{
				 //四列数据是互斥的，不能有两列是相同的
			    if(id=='p_hh'){
					 var x = $("#p_sczbX").combobox('getValue');
					 if(null != x && x == newvalue){
						 $("#p_sczbX").combobox('clear')
					 }
					 var y = $("#p_sczbY").combobox('getValue');
					 if(null != y && y == newvalue){
						 $("#p_sczbY").combobox('clear')
					 }
					 var h = $("#p_sczbH").combobox('getValue');
					 if(null != h && h == newvalue){
						 $("#p_sczbH").combobox('clear')
					 }
			    }
			    if(id=='p_sczbX'){
					 var hh = $("#p_hh").combobox('getValue');
					 if(null != hh && hh == newvalue){
						 $("#p_hh").combobox('clear')
					 }
					 var y = $("#p_sczbY").combobox('getValue');
					 if(null != y && y == newvalue){
						 $("#p_sczbY").combobox('clear')
					 }
					 var h = $("#p_sczbH").combobox('getValue');
					 if(null != h && h == newvalue){
						 $("#p_sczbH").combobox('clear')
					 }
			    }
			    if(id=='p_sczbY'){
					 var hh = $("#p_hh").combobox('getValue');
					 if(null != hh && hh == newvalue){
						 $("#p_hh").combobox('clear')
					 }
					 var x = $("#p_sczbX").combobox('getValue');
					 if(null != x && x == newvalue){
						 $("#p_sczbX").combobox('clear')
					 }
					 var h = $("#p_sczbH").combobox('getValue');
					 if(null != h && h == newvalue){
						 $("#p_sczbH").combobox('clear')
					 }
			    }
			    if(id=='p_sczbH'){
					 var hh = $("#p_hh").combobox('getValue');
					 if(null != hh && hh == newvalue){
						 $("#p_hh").combobox('clear')
					 }
					 var x = $("#p_sczbX").combobox('getValue');
					 if(null != x && x == newvalue){
						 $("#p_sczbX").combobox('clear')
					 }
					 var y = $("#sczbY").combobox('getValue');
					 if(null != y && y == newvalue){
						 $("#sczbY").combobox('clear')
					 }
			    }
			}catch(e){}
		 }
	});
	/*点击文本框内，就显示下拉面板*/
	$("#"+id).combobox('textbox').click(function(){		
		$("#"+id).combobox('showPanel');
	});
}

/**
 * 方法说明：全站仪导入数据确认解析后提交操作
 * @returns
 * @author:YangYj
 * @Time: 2016年12月23日 下午1:45:15
 */
function configParseFileGpztclSczxpcAjax(){
	$('#parseFileForm_gpztclSczxpc').form('submit', {
		url : childPath+'/configParseQzyData',
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
				$("#parseFileDialog_gpztclSczxpc").dialog('close');
				if(null !=operateType && 'edit' == operateType ){
					//编辑页面导入数据
					$('#dataGrid_gpztclSczxInfo_edit').datagrid('loadData', result.obj);
					$('#edit_details').val(result.obj);
					$('#dataStaEdit').val('modified');
				}else{
					//新增页面导入数据
					$('#dataGrid_gpztclSczxInfo').datagrid('loadData', result.obj);
					$('#add_details').val(result.obj);
					$('#dataSta').val('modified');
				}
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}
/**
 * 方法说明：主表新增页面明细列表删除操作
 * @param gridShowId 列表页面显示id
 * @returns
 * @author:YangYj
 * @Time: 2016年12月23日 下午2:03:05
 */
function deleteGpztclSczxInfoFun(gridShowId){
	if(null != operateType && 'edit' == operateType){
		//编辑页面
		gridShowId = "dataGrid_gpztclSczxInfo_edit";
		$('#dataStaEdit').val('modified');
	}else{
		//新增页面
		gridShowId = "dataGrid_gpztclSczxInfo";
		$('#dataSta').val('modified');
	}
	deleteGridRow(gridShowId);
	
}

/**
 * 方法说明：新增页面明细列表取消已选操作
 * @returns
 * @author:YangYj
 * @Time: 2016年12月23日 下午2:38:17
 */
function clearGpztclSczxInfoSelections(gridShowId){
	if(null != operateType && 'edit' == operateType){
		//编辑页面
		gridShowId = "dataGrid_gpztclSczxInfo_edit";
	}else{
		//新增页面
		gridShowId = "dataGrid_gpztclSczxInfo";
	}
	clearGridSelections(gridShowId);
}
/**
 * 方法说明：新增界面明细列表导入导向数据弹出框打开
 * @returns
 * @author:YangYj
 * @Time: 2016年12月23日 下午3:27:11
 */
function impDaoXiangSystemFile(type){
	var rows = null;
	if(null !=operateType && 'edit' == operateType ){
		//编辑页面导入数据
		rows = $('#dataGrid_gpztclSczxInfo_edit').datagrid('getRows');
	}else{
		//新增页面导入数据
		rows = $('#dataGrid_gpztclSczxInfo').datagrid('getRows');
	}
	if(null == rows || '' == rows || undefined == rows || rows.length <1){
		$.messager.alert('提示', "全站仪数据未导入，不可导入导向系统数据.", 'info');
		return;
	}
	FormUtil.clearForm($("#impDaoXiangSystemForm_gpztclSczxpc"));
    $("#impDaoXiangSystemDialog_gpztclSczxpc").dialog('open').dialog('setTitle', '导入导向系统数据');
    
    
    if(null != operateType && 'edit' == operateType){
		//编辑页面
		gridShowId = "dataGrid_gpztclSczxInfo_edit";
	}else{
		//新增页面
		gridShowId = "dataGrid_gpztclSczxInfo";
	}
	var detailsInfo=new Array();
	var rows = $("#"+gridShowId).datagrid("getRows"); 
	if(rows.length <=0 ){
		parent.$.messager.alert('提示', '列表无明细信息，不可新增。', 'info');
		return;
	}
	for(var i=0;i<rows.length;i++)	{
		var obj = new Object();
		obj.id=rows[i].id;
		obj.hh=rows[i].hh;
		obj.sczbX=rows[i].sczbX;
		obj.sczbY=rows[i].sczbY;
		obj.sczbH=rows[i].sczbH;
		
		obj.jszbX=rows[i].jszbX;
		obj.jszbY=rows[i].jszbY;
		obj.jszbH=rows[i].jszbH;
		
		obj.dxpyX=rows[i].dxpyX;
		obj.dxpyY=rows[i].dxpyY;
		
		obj.remarks=rows[i].remarks;
		obj.lc=rows[i].lc;
		
		detailsInfo[i] = obj;
	}
	var json = JSON.stringify(detailsInfo);
	$('#dx_dataList').val(json);
}

/**
 * 方法说明：解析导向系统数据
 * @returns
 * @author:YangYj
 * @Time: 2016年12月26日 下午12:40:37
 */
function parseDaoXiangSystemFileActionAjax(){
	var gridShowId="";
	if(null !=operateType && 'edit' == operateType ){
		//编辑页面导入数据
		gridShowId = "dataGrid_gpztclSczxInfo_edit";
	}else{
		//新增页面导入数据
		gridShowId = "dataGrid_gpztclSczxInfo";
	}
	var rows = $('#'+gridShowId).datagrid('getRows');
	$('#dx_endNo').val(rows[0].hh);
	$('#dx_startNo').val(rows[rows.length-1].hh);
	$('#impDaoXiangSystemForm_gpztclSczxpc').form('submit', {
		url : childPath+'/parseDaoXiangSystemFile',
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
				$("#parseFileDialog_gpztclSczxpc").dialog('close');
				if(null !=operateType && 'edit' == operateType ){
					//编辑页面导入数据
					$('#dataGrid_gpztclSczxInfo_edit').datagrid('loadData', result.obj);
					$('#dataStaEdit').val('modified');
				}else{
					//新增页面导入数据
					$('#dataGrid_gpztclSczxInfo').datagrid('loadData', result.obj);
					$('#dataSta').val('modified');
				}
				$('#impDaoXiangSystemDialog_gpztclSczxpc').dialog('close');
				$.messager.show({title:'提示',msg:'导向系统文件解析成功!',showType:'show' });
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}





/**
 * 方法说明：实测中线查看页面
 * @param id
 * @returns
 * @author:YangYj
 * @Time: 2016年12月24日 下午8:56:04
 */
function showGpztclSczxpcFun(id){
	parent.$.modalDialog({
    	maximizable:true,
        title : '查看',
        width : 1150,
        height : 500,
        href : path+"/showPage.action?id="+id,
        buttons : [ {
            text : '关闭',
            handler : function() {
                parent.$.modalDialog.handler.dialog('close');
            }
        } ]
    });
}

/**
 * 方法说明：计算线路中心线坐标
 * @returns
 * @author:YangYj
 * @Time: 2016年12月27日 下午6:21:09
 */
function countZb(){
	/*var userType  = _getCurrOrgzType();
	if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}*/
	var gridShowId ="";
	var xlshowId = "";//线路页面显示id
	if(null != operateType && 'edit' == operateType){
		//编辑页面
		gridShowId = "dataGrid_gpztclSczxInfo_edit";
		xlshowId = "edit_xlBh_gpztclSczxpc";
	}else{
		//新增页面
		gridShowId = "dataGrid_gpztclSczxInfo";
		xlshowId = "add_xlBh_gpztclSczxpc";
	}
	var g = $('#'+xlshowId).combogrid('grid');	// get datagrid object
	var r = g.datagrid('getSelected');	// get the selected row
	if(null == r || '' == r || undefined == r || null == r.id || '' == r.id){
		$.messager.alert('提示', "请先选择线路信息,再进行计算操作。", 'info');
		return;
	}
	var lineId = r.id;//线路id
	//alert(lineId);
	var rows = $('#'+gridShowId).datagrid('getRows');
	if(null == rows || '' == rows || undefined == rows || rows.length <1){
		$.messager.alert('提示', "明细列表无数据，不可进行计算操作.", 'info');
		return;
	}
	var detailData = findDetailData();
	progressLoad();
	$.post(childPath+'/countZb?lineId='+lineId, {
		dataList : detailData
	}, function(result) {
		progressClose();
		if (result.success) {
			$.messager.show({title:'提示',msg:'计算设计中线坐标：成功!',showType:'show' });
			$("#"+gridShowId).datagrid('loadData', result.obj);
		}
	}, 'JSON');
}

/**
 * 方法说明：获取明细列表数据集合
 * @returns 返回数据集合以json串格式
 * @author:YangYj
 * @Time: 2016年12月27日 下午6:33:21
 */
function findDetailData(){
	if(null != operateType && 'edit' == operateType){
		//编辑页面
		gridShowId = "dataGrid_gpztclSczxInfo_edit";
	}else{
		//新增页面
		gridShowId = "dataGrid_gpztclSczxInfo";
	}
	var detailsInfo=new Array();
	var rows = $("#"+gridShowId).datagrid("getRows"); 
	for(var i=0;i<rows.length;i++)	{
		var obj = new Object();
		obj.id=rows[i].id;
		obj.hh=rows[i].hh;
		obj.sczbX=rows[i].sczbX;
		obj.sczbY=rows[i].sczbY;
		obj.sczbH=rows[i].sczbH;
		
		obj.jszbX=rows[i].jszbX;
		obj.jszbY=rows[i].jszbY;
		obj.jszbH=rows[i].jszbH;
		
		obj.dxpyX=rows[i].dxpyX;
		obj.dxpyY=rows[i].dxpyY;
		
		obj.remarks=rows[i].remarks;
		
		detailsInfo[i] = obj;
	}
	var json = JSON.stringify(detailsInfo);
	return json;
}

