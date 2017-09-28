/** /** 【线路中心线信息管理】管片姿态测量系统 基础模块
 * 创建时间：2016-12-20
   */
/** 系统模块同路径 */
var syspath = "gpztcl";
/** 子模块路径 */
var module = "base";
/** 菜单名称 */
var entityCnName = "线路中心线信息管理";
/** 名称字段 */
var nameField = "";
/** 业务数据访问全路径 */
var path = basePath +"/"+ syspath +"/"+ module+"/gpztclSjzxInfo";

var xyfysPath=basePath +"/"+ syspath +"/"+ module+"/gpztclXyfys";

/**若无一条勾选数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayZero = ["dataGrid_gpztclSjzxInfoParent_button_seleClean","dataGrid_gpztclSjzxInfoParent_button_Imp","dataGrid_gpztclSjzxInfoParent_button_Xyfys"];
/**若只勾选一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayOnlyOneItem = [];
/**若勾选大于一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayMoreOneItem =["dataGrid_gpztclSjzxInfoParent_button_Imp"];

/**若无一条勾选数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayZero2 = ["datagrid_gpztclSjzxInfo_del","dataGrid_gpztclSjzxInfo_button_seleClean"];
/**若只勾选一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayOnlyOneItem2 = [];
/**若勾选大于一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayMoreOneItem2 =[];

/** 列表对象 */
var dataGrid_gpztclSjzxInfoParent;
var dataGrid_gpztclSjzxInfo;
var showDataGrid_gpztclSjzxInfo;

$(function() {
	
	/** 创建数据表格  1	 */
	dataGrid_gpztclSjzxInfoParent = $('#dataGrid_gpztclSjzxInfoParent').datagrid({
		url : path+'/dataGridParent',
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
			{field : 'ck',checkbox : true},
			{width : '170',title : '区间名称',field : 'sectionName',sortable : true}, 
			{width : '100',title : '线路名称',field : 'lineName',sortable : true, 
				formatter: function(value,row,index){
					return '<a href="#" onclick="showGpztclSjzxInfoFun(\''+ row.id +'\', \'' + index + '\')">' + value + '</a>';
				}
			}
		]], 
        columns : [ [
        	{width : '350',title : '所属项目',field : 'proName',sortable : true},
			{width : '80',title : '管环数量',field : 'ringCount',align:'right'},
			{width : '80',title : '起始环号',field : 'startRingnum',align:'right'},
			{width :'110',title : '设置竖曲线要素',field : 'addQxys',align:'center',
				formatter: function(value,row,index){
					var a=$.formatString('<a class="add" style="width:24px;height:22px;" href="javascript:void(0)" iconCls="icon-add" onclick="addQxys(\''+ row.id +'\')"></a>');
					return a;
				}
			},
			{width : '110',field:'action',title:'设置平曲线要素',align:'center',
				formatter:function(value,row,index){
						var e = $.formatString('<a class="add" style="width:24px;height:22px;" href="javascript:void(0)" iconCls="icon-add" onclick="addXyfys(\'{0}\',\'{1}\',\'{2}\',\'{3}\',\'{4}\',\'{5}\');" ></a>',row.proName,row.sectionName,row.lineName,row.proId,row.sectionId,row.id);						
						return e;
				}
			}
		     ]],
			onLoadSuccess : function(data) {
				$("a.add").linkbutton();
				$("a.show").linkbutton();
				hiddenButtonBase("dataGrid_gpztclSjzxInfoParent",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
			},
	        onCheck : function(){
	        	hiddenButtonBase("dataGrid_gpztclSjzxInfoParent",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
	        },
	        onUncheck : function(){
	        	hiddenButtonBase("dataGrid_gpztclSjzxInfoParent",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
	        },
	        onCheckAll : function(){
	        	hiddenButtonBase("dataGrid_gpztclSjzxInfoParent",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
	        },
	        onUncheckAll : function(){
	        	hiddenButtonBase("dataGrid_gpztclSjzxInfoParent",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
	        },
	        onClickRow:function(){
	        	hiddenButtonBase("dataGrid_gpztclSjzxInfoParent",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
	        },
		toolbar : '#toolbarParent' 
	});
	

	
	
	/** 创建数据表格 2 ------------------------------	 */

	
	/** 创建数据表格	 */

	dataGrid_gpztclSjzxInfo = $('#dataGrid_gpztclSjzxInfo').datagrid({
		url : path+'/dataGrid',
		striped : true,
		rownumbers : false,
		singleSelect : false,
		checkOnSelect : true,
		selectOnCheck : true,
		pagination : false,
		idField : 'id',
		pageSize : 15,
		pageList : [ 500, 1000 ],
		columns:[[
           {field:'id',align:'center',title:'ID',width:40, checkbox:true},
           {width: '40',title: '序号',field: 'sxh'},
           {width: '100',title: '里程',field: 'lc',align:'center',editor:{type:'numberbox',options:{precision:12}}},
           {width: '140',title: '横向坐标',field: 'sjzbX',align:'right',editor:{type:'numberbox',options:{precision:12}}},
           {width: '140',title: '纵向坐标',field: 'sjzbY',align:'right',editor:{type:'numberbox',options:{precision:12}}},
           {width: '140',title: '高程',field: 'sjzbH',align:'right',editor:{type:'numberbox',options:{precision:12}}},
           {width: '300',title: '备注',field: 'remark',align:'center',editor:'textbox'}
		]],
		onLoadSuccess : function(data) {
			hiddenButtonBase("dataGrid_gpztclSjzxInfo",hiddenClumArrayZero2,hiddenClumArrayOnlyOneItem2,hiddenClumArrayMoreOneItem2);
		},
        onCheck : function(){
        	hiddenButtonBase("dataGrid_gpztclSjzxInfo",hiddenClumArrayZero2,hiddenClumArrayOnlyOneItem2,hiddenClumArrayMoreOneItem2);
        },
        onUncheck : function(){
        	hiddenButtonBase("dataGrid_gpztclSjzxInfo",hiddenClumArrayZero2,hiddenClumArrayOnlyOneItem2,hiddenClumArrayMoreOneItem2);
        },
        onCheckAll : function(){
        	hiddenButtonBase("dataGrid_gpztclSjzxInfo",hiddenClumArrayZero2,hiddenClumArrayOnlyOneItem2,hiddenClumArrayMoreOneItem2);
        },
        onUncheckAll : function(){
        	hiddenButtonBase("dataGrid_gpztclSjzxInfo",hiddenClumArrayZero2,hiddenClumArrayOnlyOneItem2,hiddenClumArrayMoreOneItem2);
        },
        onClickRow:function(){
        	hiddenButtonBase("dataGrid_gpztclSjzxInfo",hiddenClumArrayZero2,hiddenClumArrayOnlyOneItem2,hiddenClumArrayMoreOneItem2);
        },
		onClickCell: onClickCell,
		onEndEdit: onEndEdit,
		toolbar : '#toolbar' 
	});
	
	
	
	
	
	/** 创建数据表格 3	 */
	showDataGrid_gpztclSjzxInfo = $('#showDataGrid_gpztclSjzxInfo').datagrid({
		url : path+'/dataGrid',
		striped : true,
		rownumbers : false,
		singleSelect : false,
		checkOnSelect : true,
		selectOnCheck : true,
		pagination : false,
		idField : 'id',
		pageSize : 15,
		pageList : [ 500, 1000 ],
		columns:[[
			{field:'id',align:'center',title:'ID',width:40, checkbox:true},
			{width: '40',title: '序号',field: 'sxh'},
			{width: '100',title: '里程',field: 'lc',align:'center'},
			{width: '140',title: '横向坐标',field: 'sjzbX',align:'right'},
			{width: '140',title: '纵向坐标',field: 'sjzbY',align:'right'},
			{width: '140',title: '高程',field: 'sjzbH',align:'right'},
			{width: '300',title: '备注',field: 'remark',align:'center'}
			]],
			onLoadSuccess : function(data) {},
			toolbar : '#showToolbar' 
	});
	_getProDic("query_gcBh_gcztclSjzxInfo",null,null);
});


function loadDatagrid(){
	
}

/** 【线路中心线信息管理】打开新增页面 */
function addGpztclSjzxInfoFun() {
	var rows = dataGrid_gpztclSjzxInfoParent.datagrid('getSelections');
	$("#add_projectName").html(rows[0].proName);
	$("#add_qujianName").html(rows[0].sectionName);
	$("#add_lineName").html(rows[0].lineName);
	
	$("#addForm_gcBh").val(rows[0].proId);
	$("#addForm_qlBh").val(rows[0].sectionId);
	$("#addForm_xlBh").val(rows[0].id);
	
	
	$("#addDialog_gpztclSjzxInfo").dialog('open').dialog('setTitle', '添加［'+entityCnName+"］");
	dataGrid_gpztclSjzxInfo.datagrid("load",{xlBh:rows[0].id});
}









/**【线路中心线信息管理】打开“设置曲线要素”页面*/
function addQxys(id){
	
	parent.$.modalDialog({
        title : '设置竖曲线要素',
        width : 800,
        height : 500,
        href : basePath+'/gpztcl/base/qxys?xlBh='+id,
        buttons : [ {
            text : '保存',
            handler : function() {
            	var f = parent.$.modalDialog.handler.find('#saveBtn');
            	$(f).trigger("onclick");          	
            }
        },{
            text : '关闭',
            handler : function() {
                parent.$.modalDialog.handler.dialog('close');
            }
        } ],
        onBeforeClose:function(){
        	var item = parent.$.modalDialog.handler.find('#dataSta');
        	dataSta = item.val();//数据状态 original:原始状态；modified:修改状态;
        	if(dataSta !=null && dataSta =='modified'){
        		 parent.$.messager.confirm('询问', '您尚未保存数据，确定离开页面吗？', function(b) {
        	  	        if (b) {
        	  	        	item.val('original');//关闭页面后，数据状态恢复为原始状态
        	  	        	parent.$.modalDialog.handler.dialog('close');
        	  	        }
        		 })
        	}else{
        		return true;
        	}
        	return false;
        }
    });
}
 

/**【线路中心线信息管理】打开平曲线要素新增页面*/
function addXyfys(proName,sectionName,lineName,proId,sectionId,id){
	parent.$.modalDialog({
        title : '新增平曲线要素',
        width : 1100,
        height : 500,
        href : xyfysPath+'/xyfys?proId='+proId+'&sectionId='+sectionId+'&id='+id,  
        buttons : [ {
            text : '保存',
            handler : function() {
            	var f = parent.$.modalDialog.handler.find('#saveBtn');
            	$(f).trigger("onclick");          	
            }
        },{
            text : '关闭',
            handler : function() {
                parent.$.modalDialog.handler.dialog('close');
            }
        } ],
        onBeforeClose:function(){
        	var item = parent.$.modalDialog.handler.find('#dataSta');
        	dataSta = item.val();//数据状态 original:原始状态；modified:修改状态;
        	if(dataSta !=null && dataSta =='modified'){
        		 parent.$.messager.confirm('询问', '您尚未保存数据，确定离开页面吗？', function(b) {
        	  	        if (b) {
        	  	        	item.val('original');//关闭页面后，数据状态恢复为原始状态
        	  	        	parent.$.modalDialog.handler.dialog('close');
        	  	        }
        		 })
        	}else{
        		return true;
        	}
        	return false;
        }
    });
}


/** 【线路中心线信息管理】打开查看页面 */

function showGpztclSjzxInfoFun(id,index) {

	var rows = dataGrid_gpztclSjzxInfoParent.datagrid('getRows');
	//显示  项目  名称
	$("#show_projectName").html(rows[index].proName);
	
	//区间名称
	$("#show_qujianName").html(rows[index].sectionName);
	//路线名称
	$("#show_lineName").html(rows[index].lineName);

	//打开   页面

	$("#show_lineNameId").val(rows[index].id);
	

	$("#showDialog_gpztclSjzxInfo").dialog('open').dialog('setTitle', '查看［'+entityCnName+"］");
	showDataGrid_gpztclSjzxInfo.datagrid("load",{xlBh:id});
}
/** 【线路中心线信息管理】导入excel页面 */
function openGpztclSjzxInfoImp() {
	$("#openImpexcel_gpztclSjzxInfo").dialog('open').dialog('setTitle', '导入设计中心线信息');
}
/** 【线路中心线信息管理】上传文件 */
function uploadFile_gpztclsjzx() {
	$('#uploadFile_gpztclsjzxInfo_Form').form('submit', {
		url : path+'/upload',
		onSubmit : function() {
			progressLoad();
		},
		success : function(result) {
			progressClose();
			result = $.parseJSON(result);
			if (result.success) {
//				$.messager.show({title:'提示',msg:'新增操作：成功!',showType:'show' });
				dataGrid_gpztclSjzxInfo.datagrid('loadData',result.obj);
				$("#openImpexcel_gpztclSjzxInfo").dialog('close');
				FormUtil.clearForm($("#uploadFile_gpztclsjzxInfo_Form"));
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}

/** 【线路中心线信息管理】数据保存 */
function addGpztclSjzxInfoAjax() {
	accept();
	var allData = dataGrid_gpztclSjzxInfo.datagrid("getData").rows;
//	showJson("",allData);
	$("#addForm_gpztclSjzxInfoClumOne").val(JSON.stringify(allData));
	$('#addForm_gpztclSjzxInfo').form('submit', {
		url : path+'/add',
		onSubmit : function() {
			progressLoad();
		},
		success : function(result) {
			progressClose();
			result = $.parseJSON(result);
			if (result.success) {
				$.messager.show({title:'提示',msg:'新增操作：成功!',showType:'show' });
				dataGrid_gpztclSjzxInfo.datagrid('reload');
				$("#addDialog_gpztclSjzxInfo").dialog('close');
				FormUtil.clearForm($("#addDialog_gpztclSjzxInfo"));
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}

/** 【线路中心线信息管理】打开数据编辑页面 */
function editGpztclSjzxInfoFun() {
	var id;
	var rows = dataGrid_gpztclSjzxInfo.datagrid('getSelections');
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
		$("#editDialog_gpztclSjzxInfo").dialog('open').dialog('setTitle', '编辑［'+entityCnName+'］'+'-'+rows[0][nameField]);
		$('#editForm_gpztclSjzxInfo').form('load', rows[0]);
	}
}

/** 【线路中心线信息管理】提交修改 */
function editGpztclSjzxInfoAjax() {
	var isValid = $('#editForm_gpztclSjzxInfo').form('validate');
	if (!isValid) return;
	$.messager.confirm('提示', '您确定要编辑［'+entityCnName + '］吗？', function(b) {
		if (b) {
			$('#editForm_gpztclSjzxInfo').form('submit', {
				url : path+'/edit',
				onSubmit : function() {
				  progressLoad();
				},
				success : function(result) {
					progressClose();
					result = $.parseJSON(result);
					if (result.success) {
				        $.messager.show({title:'提示',msg:'修改操作：成功!',showType:'show' });
						dataGrid_gpztclSjzxInfo.datagrid('reload');
						$("#editDialog_gpztclSjzxInfo").dialog('close');
					}
					else {
						$.messager.alert('错误', result.msg, 'error');
					}
				}
			});
		}
	});
}


/** 【线路中心线信息管理】下载模板 */
function downLoadModelAjax() {
	$.post(path+'/downLoadModel', {}, 
		function(result) {
			
		}, 'JSON');
}


/** 【线路中心线信息管理】数据删除 */
function deleteGpztclSjzxInfoFun() {
	var ids = new Array();
	var rows = dataGrid_gpztclSjzxInfo.datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择数据后再删除！', 'info');
		return;
	}
	for(var i=rows.length-1; i>=0; i--){
		var index=dataGrid_gpztclSjzxInfo.datagrid('getRowIndex',rows[i]);
		dataGrid_gpztclSjzxInfo.datagrid('deleteRow',index);
	}
	
}



/**
 * 【线路中心线信息管理】导出excel
 * @returns
 */
function expGpztclSjzxInfoXls(){
	var lineId = $("#show_lineNameId").val();
	var url = path + "/expXls?ids="+lineId;
	ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, "线路设计中心线信息", dataGrid_gpztclSjzxInfo, false);
}

/**
 * 【线路中心线信息管理】刷新
 * @returns
 */
function reloadGpztclSjzxInfo(){
	dataGrid_gpztclSjzxInfo.datagrid('reload');
}

function reloadGpztclSjzxInfoShow(){
	showDataGrid_gpztclSjzxInfo.datagrid('reload');
}

/**
 * 【线路中心线信息管理】取消已选
 * @returns
 */
function clearGpztclSjzxInfoSelections(){
	dataGrid_gpztclSjzxInfo.datagrid('clearSelections');
}

/** 【线路中心线信息管理】表单查询 */
function searchGpztclSjzxInfoFun() {
	dataGrid_gpztclSjzxInfo.datagrid('load', $.serializeObject($('#searchForm_gpztclSjzxInfo')));
}

/**【线路中心线信息管理】表单重置 */
function cleanGpztclSjzxInfoFun() {
	$('#searchForm_gpztclSjzxInfo input').val('');
	dataGrid_gpztclSjzxInfo.datagrid('load', {});
}

/**
 * 【线路中心线信息管理】刷新
 * @returns
 */
function reloadGpztclSjzxInfoParent(){
	dataGrid_gpztclSjzxInfoParent.datagrid('reload');
}

/**
 * 【线路中心线信息管理】取消已选
 * @returns
 */
function clearGpztclSjzxInfoParentSelections(){
	dataGrid_gpztclSjzxInfoParent.datagrid('clearSelections');
}

/** 【线路中心线信息管理】表单查询 */
function searchGpztclSjzxInfoParentFun() {
	dataGrid_gpztclSjzxInfoParent.datagrid('load', $.serializeObject($('#searchForm_gpztclSjzxInfoParent')));
}

/**【线路中心线信息管理】表单重置 */
function cleanGpztclSjzxInfoParentFun() {
	$('#searchForm_gpztclSjzxInfoParent input').val('');
	dataGrid_gpztclSjzxInfoParent.datagrid('load', {});
}

/*********编辑列表行***********************************************************************/

var editIndex = undefined;
function endEditing(){
    if (editIndex == undefined){return true}
    if (dataGrid_gpztclSjzxInfo.datagrid('validateRow', editIndex)){
        dataGrid_gpztclSjzxInfo.datagrid('endEdit', editIndex);
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
}
function onClickCell(index, field){
//	alert(index+"---------------"+field);
    if (editIndex != index){
        if (endEditing()){
            dataGrid_gpztclSjzxInfo.datagrid('selectRow', index)
                    .datagrid('beginEdit', index);
            var ed = dataGrid_gpztclSjzxInfo.datagrid('getEditor', {index:index,field:field});
            if (ed){
                ($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
            }
            editIndex = index;
        } else {
            setTimeout(function(){
                dataGrid_gpztclSjzxInfo.datagrid('selectRow', editIndex);
            },0);
        }
    }
}
function onEndEdit(index, row){
//    var ed = $(this).datagrid('getEditor', {
//        index: index,
//        field: 'productid'
//    });
//    row.productname = $(ed.target).combobox('getText');
}
function append(){
    if (endEditing()){
        dataGrid_gpztclSjzxInfo.datagrid('appendRow',{status:'P'});
        editIndex = dataGrid_gpztclSjzxInfo.datagrid('getRows').length-1;
        dataGrid_gpztclSjzxInfo.datagrid('selectRow', editIndex)
                .datagrid('beginEdit', editIndex);
    }
}
function removeit(){
    if (editIndex == undefined){return}
    dataGrid_gpztclSjzxInfo.datagrid('cancelEdit', editIndex)
            .datagrid('deleteRow', editIndex);
    editIndex = undefined;
}
function accept(){
    if (endEditing()){
        dataGrid_gpztclSjzxInfo.datagrid('acceptChanges');
    }
}
function reject(){
    dataGrid_gpztclSjzxInfo.datagrid('rejectChanges');
    editIndex = undefined;
}
function getChanges(){
    var rows = dataGrid_gpztclSjzxInfo.datagrid('getChanges');
    alert(rows.length+' rows are changed!');
}





