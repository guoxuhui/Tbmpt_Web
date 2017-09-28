/** /** 【安全巡检点】工程项目安全巡检系统 巡检点管理
 * 创建时间：2017-05-26
   */
/** 系统模块同路径 */
var syspath = "aqxj";
/** 子模块路径 */
var module = "xjdgl";
/** 菜单名称 */
var entityCnName = "安全巡检点";
var entityCnName1="巡检内容"
	/**
	 * 模块名称
	 */
	var moduleName = "巡检点管理";
/** 名称字段 */
var nameField = "mingCheng";
/** 业务数据访问全路径 */
var path = basePath +"/"+ syspath +"/"+ module+"/aqxjXjd";
/**若无一条勾选数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayZero = ["edit_bj_aqxjXjd","cancelSelect_bj_aqxjXjd","del_sc_aqxjXjd","edit_AqxjXjnr"];
/**若勾选大于一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayMoreOneItem =["edit_bj_aqxjXjd","edit_AqxjXjnr"];
var hiddenClumArrayOnlyOneItem = [];
/** 列表对象 */
var dataGrid_aqxjXjd;
$(function() {
	/** 创建数据表格	 */
	dataGrid_aqxjXjd = $('#dataGrid_aqxjXjd').datagrid({
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
		columns:[[
           {field:'id',align:'center',title:'ID',width:40, checkbox:true},
           {width: '310',title: '工程项目名称',field: 'projectName'},
           {width: '120',title: '巡检点名称',field: 'mingCheng'},
           {width: '120',title: '检查点所在位置',field: 'address'},
           {width: '80',title: '顺序号',field: 'xuHao'},
           {width: '80',title: '责任人',field: 'zeRenrmc'},
           {width: '80',title: '监督人名称',field: 'jianDurmc'},
           {width: '80',title: '检查频次',field: 'jianChapc'},
           {width: '120',title: '分类名称',field: 'typeName'},
           {width: '120',title: '备注',field: 'beiZhu'}
		]],
		onLoadSuccess : function(data) {},
		toolbar : '#toolbar',
		onLoadSuccess : function(data) {
			hiddenButtonBase("dataGrid_aqxjXjd",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
		},
        onCheck : function(){
        	hiddenButtonBase("dataGrid_aqxjXjd",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onUncheck : function(){
        	hiddenButtonBase("dataGrid_aqxjXjd",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onCheckAll : function(){
        	hiddenButtonBase("dataGrid_aqxjXjd",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onUncheckAll : function(){
        	hiddenButtonBase("dataGrid_aqxjXjd",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onClickRow:function(){
        	hiddenButtonBase("dataGrid_aqxjXjd",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        }
	});
	
	/**
	 *获取项目信息
	 *
	 */
	$('#query_gcBh_aqxjXjd').combobox({
		url : basePath+'/project/section/getProDic',
		valueField : "proName",
		textField : "proName",
		/*onLoadSuccess : function() {
			var val = $(this).combobox('getData');
			for (var item in val[0]) {
				if (item == 'proName') {
					$(this).combobox('select', val[0][item]);
				}
			}
		}*/
	});
	
	create_FlMc_Select("query_flmc_aqxjXjd",false,"search");
	//create_FlMc_Select("add_typeName_aqxjXjd",false,"search");
	//create_FlMc_Select("edit_fl_typeName_aqxjXjd",false,"search");
	//查询条件加载分类
	/*$('#query_flmc_aqxjXjd').combotree({
		url :basePath+'/aqxj/aqxjXjdfl/getFl',
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
	});*/
});


/*$("#add_pName_aqxjXjdfl").combotree({
	url : basePath+'/aqxj/aqxjXjdfl/getFl',
	parentField : 'pid',
	lines : true,
	valueField : "fenleiMc",
	textField : "fenleiMc",
	panelHeight : 'auto',
	onLoadSuccess : function() {

	},
});*/

/*
 * 分类名称树下拉框
 * */
function create_FlMc_Select(showId,required,oparate){
	$("#"+showId).combotree({
		url : basePath+'/aqxj/aqxjXjdfl/getcxFl',
		parentField : 'pid',
		lines : true,
		valueField : "fenleiMc",
		textField : "fenleiMc",
		panelHeight : 'auto',
		onLoadSuccess : function() {

		}
		/*onChange:function(newValue,oldValue){
			createDwgcSelect(oparate+"_dwgcBh_gczlYdxjSGZLXJInfo",newValue,required,oparate)
	        createFbgcSelect(oparate+"_fbGcbh_gczlYdxjSGZLXJInfo","-1",false);//构建分部工程下拉框
		}*/
	});
	/*点击文本框内，就显示下拉面板*/
	$("#"+showId).combotree('textbox').click(function(){		
		$("#"+showId).combotree('showPanel');
	});
}


/** 【安全巡检点】打开新增页面 */
function addAqxjXjdFun() {
	FormUtil.clearForm($("#addDialog_aqxjXjd"));
	$("#addDialog_aqxjXjd").dialog('open').dialog('setTitle', '添加［'+entityCnName+"］");
	var proName=$("#add_projec_tName_aqxjXjd").text();
	$("#add_projectName_aqxjXjd").val(proName);
	$("#add_typeId_aqxjXjd").combotree({
		url : basePath+"/aqxj/aqxjXjdfl/getFl?gcmc="+proName,
		//parentField : 'pid',
		lines : true,
		valueField : 'typeName',
		textField : 'typeName',
		panelHeight : 'auto',
		onLoadSuccess : function() {

		},
		onChange:function(){
			//$("#add_typeId_aqxjXjd").combobox("clear");
			var flName=$("#add_typeId_aqxjXjd").combobox("getText");
			var prName = document.getElementById("add_projec_tName_aqxjXjd").innerHTML;
			//$("#add_typeId_aqxjXjd").combobox("clear");
			$('#add_typeName_aqxjXjd').val(flName);
			//$('#add_projectName_aqxjXjd').val(prName);
		}
	});
}

/** 【安全巡检点】数据保存 */
function addAqxjXjdAjax() {
	$('#addForm_aqxjXjd').form('submit', {
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
				dataGrid_aqxjXjd.datagrid('reload');
				$("#addDialog_aqxjXjd").dialog('close');
				FormUtil.clearForm($("#addDialog_aqxjXjd"));
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}

/** 【安全巡检点】打开数据编辑页面 */
function editAqxjXjdFun() {
	var id;
	var rows = dataGrid_aqxjXjd.datagrid('getSelections');
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
		$("#editDialog_aqxjXjd").dialog('open').dialog('setTitle', '编辑［'+entityCnName+'］'+'-'+rows[0][nameField]);
		var proName=$("#edit_projec_tName_aqxjXjd").text();
		$("#edit_projectName_aqxjXjd").val(proName);
		$('#editForm_aqxjXjd').form('load', rows[0]);
		$("#edit_fl_typeId_aqxjXjd").combotree({
			url : basePath+"/aqxj/aqxjXjdfl/getFl?gcmc="+proName,
			lines : true,
			valueField : "id",
			textField : "fenleiMc",
			panelHeight : 'auto',
			onLoadSuccess : function() {

			},
			onChange:function(){
				var flName=$("#edit_fl_typeId_aqxjXjd").combobox("getText");
				alert(flName);
				$("#edit_fl_typeId_aqxjXjd").dialog('clear');
				$('#edit_typeName_aqxjXjd').val(flName);
				alert(1);
			}
			/*onSelect:function(){
				var flName=$("#edit_fl_typeId_aqxjXjd").combobox("getText");
				alert(flName);
				$('#edit_typeName_aqxjXjd').val(flName);
				$('#edit_typeName_aqxjXjd').val(flName);
			}*/
		});
	}
}

/** 【安全巡检点】提交修改 */
function editAqxjXjdAjax() {
	var isValid = $('#editForm_aqxjXjd').form('validate');
	if (!isValid) return;
	$.messager.confirm('提示', '您确定要编辑［'+entityCnName + '］吗？', function(b) {
		if (b) {
			$('#editForm_aqxjXjd').form('submit', {
				url : path+'/edit',
				onSubmit : function() {
				  progressLoad();
				},
				success : function(result) {
					progressClose();
					result = $.parseJSON(result);
					if (result.success) {
				        $.messager.show({title:'提示',msg:'修改操作：成功!',showType:'show' });
						dataGrid_aqxjXjd.datagrid('reload');
						$("#editDialog_aqxjXjd").dialog('close');
						//$("#editDialog_aqxjXjd").dialog('clear');
					}
					else {
						$.messager.alert('错误', result.msg, 'error');
					}
				}
			});
		}
	});
}

/** 【安全巡检点】数据删除 */
function deleteAqxjXjdFun() {
	var ids = new Array();
	var rows = dataGrid_aqxjXjd.datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择数据后再删除！', 'info');
		return;
	}
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].id);
	}
	$.messager.confirm('询问', '您是否要删除［'+entityCnName + '］所选行以及其所对应的巡检内容吗？', function(b) {
		if (b) {
			progressLoad();
			$.post(path+'/delete', {
				ids : ids.join(",")//将数组元素存入一个字符串中，分隔符来分隔数组中的元素
			}, function(result) {
				progressClose();
				if (result.success) {
					$.messager.show({title:'提示',msg:'删除操作：成功!',showType:'show' });
					dataGrid_aqxjXjd.datagrid('reload');
					dataGrid_aqxjXjd.datagrid('clearSelections');
				}
			}, 'JSON');
		}
	});
}

/** 【安全巡检点】查看数据页面 */
function showAqxjXjdFun(oid,index) {
	var id;
	var row;
	if(oid){
		id = oid;
		var allrows = dataGrid_aqxjXjd.datagrid('getRows');
		row = allrows[index];
	}else{
		var rows = dataGrid_aqxjXjd.datagrid('getSelections');
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
		$("#showDialog_aqxjXjd").dialog('open').dialog('setTitle', '查看［'+entityCnName+'］'+'-'+row[nameField]);
		$('#showDialog_aqxjXjd').form('load', row);
	}
}



/**
 * 【安全巡检点】刷新
 * @returns
 */
function reloadAqxjXjd(){
	dataGrid_aqxjXjd.datagrid('reload');
}

/**
 * 【安全巡检点】取消已选
 * @returns
 */
function clearAqxjXjdSelections(){
	dataGrid_aqxjXjd.datagrid('clearSelections');
}/** 【安全巡检点】表单查询 */
function searchAqxjXjdFun() {
	dataGrid_aqxjXjd.datagrid('load', $.serializeObject($('#searchForm_aqxjXjd')));
}

/**【安全巡检点】表单重置 */
function cleanAqxjXjdFun() {
	$('#searchForm_aqxjXjd input').val('');
	dataGrid_aqxjXjd.datagrid('load', {});
}

//新增页同加载区间信息
$('#add_qlBh_gczlYdxjGPZLXJInfo').combobox({
	url :basePath+'/project/line/getProSectionDic?proId='+$("#sushuGcId_gczlYdxjGPZLXJInfo").val(),
	valueField : "id",
	textField : "sectionName",
	multiple: false,
	required: true,
	panelHeight : 'auto',
	onLoadSuccess : function(data) {
//		showJson("",data)
	},
	onSelect:function(node) {
    	loadXlComboBox(node.id,"add_xlBh_gczlYdxjGPZLXJInfo");
    }
});

function createXuHaoSelect(showId,required,oparate){
$("#"+showId).combobox({
	url : basePath+'/aqxj/xjdgl/aqxjXjd/getSxuHao',
	valueField : "xuHao",
	textField : "xuHao",
	/*onLoadSuccess : function() {
		alert();
		var val = $(this).combobox('getData');
		for (var item in val[0]) {
			alrt(90);
			if (item == 'id') {
				$(this).combobox('select', val[0][item]);
			}
		}
	}*/
});
}



/**
 * 打开巡检内容编辑页面
 */
function editAqxjXjnrFun() {

		var rows = dataGrid_aqxjXjd.datagrid('getSelections');
		if (rows.length > 1) {
			$.messager.alert('提示', '请选择单条数据进行编辑！', 'info');
			return;
		}
		else if (rows.length == 0) {
			$.messager.alert('提示', '请选择数据后再编辑！', 'info');
			return;
		}
		if (rows[0]) {
			row = rows[0];
			id = rows[0].id;
		}
		else {
			$.messager.alert('提示', '请选择数据后再编辑！', 'info');
			return;
		}	
	
	if (row) {
		$("#dategridDialog_aqxjXjnr").dialog('open').dialog('setTitle', '编辑巡检内容');
		//$("#showDialog").dialog('open').dialog('setTitle', '查看［'+moduleName+"］"+"-"+row[nameField]);
	    /*$('#show_pid').combotree({
	        url : basePath+'/sys/orgz/tree?flag=false',
	        parentField : 'pid',
	        lines : true,
	        panelWidth:380,
	        panelHeight : 'auto'
	    });*/
		$('#show_aqxjXjd_nr').form('load', row);
		var itmId=$("#show_itemsId_aqxjXjnr").val();
		$("#show_itemsId_aqxjXjnr1").val(itmId);
		disableForm('editForm_aqxjXjd_nr',true);
		AqxjXjnrDataGridShow();
		
	}
	dataGrid_aqxjXjnr.datagrid('load', $.serializeObject($('#search_itenid_aqxjXjd')));
	//createXuHaoSelect("add_xuHao_aqxjXjnr",false,"search");
}


/**
 * 导出Excel
 */


function expXlsAqxjXjnrFun(){
	var url = path + "/expXls";
	ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, moduleName+"信息", dataGrid_aqxjXjd, false);
}
/**
 * 导出pdf
 */
function expPdfAqxjXjnrFun(){
	var url = path + "/expPdf";
	
	ExportUtil.doExport(url, ExportUtil.TYPE_PDF, moduleName+"信息", dataGrid_aqxjXjd, false);
}





























