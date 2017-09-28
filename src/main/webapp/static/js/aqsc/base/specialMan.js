/** /** 【特种人员管理】安全生产管理 基础信息
 * 创建时间：2017-02-20
   */
/** 系统模块同路径 */
var syspath = "aqsc";
/** 子模块路径 */
var module = "base";
/** 菜单名称 */
var entityCnName = "特种人员管理";
/** 名称字段 */
var nameField = "name";
/** 业务数据访问全路径 */
var path = basePath +"/"+ syspath +"/"+ module+"/specialMan";
/** 列表对象 */
var dataGrid_specialMan;
/**若无一条勾选数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayZero = ["cancelSelect_button_specialMan","edit_button_specialMan","del_button_specialMan"];
/**若只勾选一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayOnlyOneItem = [];
/**若勾选大于一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayMoreOneItem =["edit_button_specialMan"];
var _gSort = "";//定义默认排序字段

$(function() {
	/** 创建数据表格	 */
	dataGrid_specialMan = $('#dataGrid_specialMan').datagrid({
		url : path+'/dataGrid',
//		fitColumns:true,
		striped : true,
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
	        			return '<a href="#" onclick="showSpecialManFun(\''+ row.id +'\',\''+index+'\')">' + value + '</a>';
	        		}
	        	},
	        	{width: '80',title: '性别',field: 'sex',sortable : true
	        	},
	        	{width: '120',title: '工种',field: 'workType',sortable : true}
	 		]],
		columns:[[
			{width: '200',title: '身份证号',field: 'cardNo',sortable : true},
           {width: '80',title: '人员类别',field: 'renyuanType',sortable : true},
           {width: '150',title: '证件号码',field: 'zhengjianhaoma'},
           {width: '150',title: '发证机关',field: 'fazhengjiguan',sortable : true,
        	   formatter: function(value,row,index){
					return DataGridFormatter.tipsFormatter(value,30);
				}
           },
           {width: '100',title: '取证日期',field: 'quzhengriqi',sortable : true},
           {width: '100',title: '复审日期',field: 'fushengriqi',sortable : true},
           {width: '100',title: '有效日期',field: 'youxiaoqi',sortable : true},
           {width: '100',title: '进场日期',field: 'jinchangriqi',sortable : true},
           {width: '100',title: '离场日期',field: 'lichangriqi',sortable : true},
           {width: '300',title: '备注',field: 'remark',sortable : true,
        	   formatter: function(value,row,index){
					return DataGridFormatter.tipsFormatter(value,30);
				}
           }
		]],
		onLoadSuccess : function(data) {},
		toolbar : '#toolbar',
		rowStyler:function(index,row){
			var fushengriqi = row.fushengriqi;//复审日期
			var youxiaoqi = row.youxiaoqi;//有效日期
			var currentDate = new Date().format('yyyy-MM-dd');//当前日期
			var beforeMonth = addMonth(currentDate,3);
			if (compareDate(fushengriqi,currentDate) || compareDate(youxiaoqi,currentDate)){
				//复审过期数据变红
				return 'background-color:#EE6363;';
			}else if(compareDate(fushengriqi,beforeMonth) || compareDate(youxiaoqi,beforeMonth)){
				//复审到期前两个月数据变黄
				return 'background-color:#FFFF00;';
			}
		},
		onLoadSuccess : function(data) {
			hiddenButtonBase("dataGrid_specialMan",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
		},
        onCheck : function(){
        	hiddenButtonBase("dataGrid_specialMan",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onUncheck : function(){
        	hiddenButtonBase("dataGrid_specialMan",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onCheckAll : function(){
        	hiddenButtonBase("dataGrid_specialMan",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onUncheckAll : function(){
        	hiddenButtonBase("dataGrid_specialMan",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onClickRow:function(){
        	hiddenButtonBase("dataGrid_specialMan",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onBeforeLoad:function(param){  
        	if(null != _gSort && _gSort !='' && _gSort !=undefined){
        		param.sort='createTime';
        		param.order = 'desc';
        	}
        }  
	});
	createSexSelect('search_sex');
	createRyTypeSelect("query_rqType",false)
});


/**
 * 上传图片
 * @returns
 */
function myUploadPicture(){
	var rows = dataGrid_specialMan.datagrid('getSelections');
	if (rows.length > 1) {
		$.messager.alert('提示', '请选择单条数据进行操作！', 'info');
		return;
	}else if (rows.length == 0) {
		$.messager.alert('提示', '请选择数据后再操作！', 'info');
		return;
	}
	_uploadPicture(rows[0].id,syspath,'',dataGrid_specialMan,entityCnName);
}

/** 【特种人员管理】打开新增页面 */
function addSpecialManFun() {
	var userType  = _getCurrOrgzType();
	if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}
	FormUtil.clearForm($("#addDialog_specialMan"));
	$("#addDialog_specialMan").dialog('open').dialog('setTitle', '添加［'+entityCnName+"］");
	createSexSelect('add_sex');
	$("#add_sex").combobox('setValue','男');
}

/** 【特种人员管理】数据保存 */
function addSpecialManAjax() {
	$('#addForm_specialMan').form('submit', {
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
				dataGrid_specialMan.datagrid('reload');
				_gSort = "";
				$("#addDialog_specialMan").dialog('close');
				FormUtil.clearForm($("#addDialog_specialMan"));
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}

/** 【特种人员管理】打开数据编辑页面 */
function editSpecialManFun() {
	var userType  = _getCurrOrgzType();
	if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}
	var id;
	var rows = dataGrid_specialMan.datagrid('getSelections');
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
		$("#editDialog_specialMan").dialog('open').dialog('setTitle', '编辑［'+entityCnName+'］'+'-'+rows[0][nameField]);
		createSexSelect('edit_sex');
		$('#editForm_specialMan').form('load', rows[0]);
	}
}

/** 【特种人员管理】提交修改 */
function editSpecialManAjax() {
	var isValid = $('#editForm_specialMan').form('validate');
	if (!isValid) return;
	$.messager.confirm('提示', '您确定要编辑［'+entityCnName + '］吗？', function(b) {
		if (b) {
			$('#editForm_specialMan').form('submit', {
				url : path+'/edit',
				onSubmit : function() {
				  progressLoad();
				},
				success : function(result) {
					progressClose();
					result = $.parseJSON(result);
					if (result.success) {
				        $.messager.show({title:'提示',msg:'修改操作：成功!',showType:'show' });
						dataGrid_specialMan.datagrid('reload');
						$("#editDialog_specialMan").dialog('close');
					}
					else {
						$.messager.alert('错误', result.msg, 'error');
					}
				}
			});
		}
	});
}

/** 【特种人员管理】数据删除 */
function deleteSpecialManFun() {
	var userType  = _getCurrOrgzType();
	if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}
	var ids = new Array();
	var rows = dataGrid_specialMan.datagrid('getSelections');
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
					dataGrid_specialMan.datagrid('reload');
					dataGrid_specialMan.datagrid('clearSelections');
				}
			}, 'JSON');
		}
	});
}

/** 【特种人员管理】查看数据页面 */
function showSpecialManFun(oid,index) {
	
	var id;
	var row;
	if(oid){
		id = oid;
		var allrows = dataGrid_specialMan.datagrid('getRows');
		row = allrows[index];
	}else{
		var rows = dataGrid_specialMan.datagrid('getSelections');
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
		 $("#showDialog_specialMan").dialog('open').dialog('setTitle', '查看［'+entityCnName+'］'+'-'+row[nameField]);
		createSexSelect('show_sex');
		$('#showForm_specialMan').form('load', row);
		_showAllFileInDiv(row.id,'img_div_specialMal')
	}
}

/**
 * 【特种人员管理】导出excel
 * @returns
 */
function expSpecialManXls(){
	var url = path + "/expXls";
	ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, entityCnName+"信息", dataGrid_specialMan, false);
}

/**
 * 【特种人员管理】刷新
 * @returns
 */
function reloadSpecialMan(){
	dataGrid_specialMan.datagrid('reload');
}

/**
 * 【特种人员管理】取消已选
 * @returns
 */
function clearSpecialManSelections(){
	dataGrid_specialMan.datagrid('clearSelections');
}/** 【特种人员管理】表单查询 */
function searchSpecialManFun() {
	dataGrid_specialMan.datagrid('load', $.serializeObject($('#searchForm_specialMan')));
}

/**【特种人员管理】表单重置 */
function cleanSpecialManFun() {
	$('#searchForm_specialMan input').val('');
	dataGrid_specialMan.datagrid('load', {});
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
	_showAllPic(keyId);
}

function createRyTypeSelect(id,required){
	if(null == required || '' == required || required == undefined){
		required = false;
	}
	
	$("#"+id).combobox({
		required : false,
		valueField:'id',
		textField:'text',
		panelHeight:'auto',
		data: 
		[{
			id: '自有人员',
			text: '自有人员'
		 },{
			 id: '外协人员',
			text: '外协人员'
		 }]
	});
	/*点击文本框内，就显示下拉面板*/
	$("#"+id).combobox('textbox').click(function(){		
		$("#"+id).combobox('showPanel');
	});
	
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
	            dataGrid_specialMan.datagrid('reload');
	        } else {
	            parent.$.messager.alert('错误', result.msg, 'error');
	        }
    	}
   });
}









