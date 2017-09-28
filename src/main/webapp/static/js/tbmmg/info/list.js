/**
 * 盾构机管理系统 盾构机基本信息模块
 * 
 * 创建时间：2016年11月05日 
 * 
 */

/**
 * 系统模块同路径
 */
var syspath = "tbmmg";
/**
 * 子模块路径
 */
var module = "info";
/**
 * 模块名称
 */
var moduleName = "盾构机";
/**
 * 名称字段
 */
var nameField = "tbmCode";
/**
 * 业务数据访问全路径
 */
var path = basePath +"/"+ syspath +"/"+ module;
/**
 * 列表对象
 */
var dataGrid;
var tbmState=[{id:'0',text:'进场'},{id:'1',text:'组装'},{id:'2',text:'准备始发'},{id:'3',text:'始发'},{id:'4',text:'掘进'},{id:'5',text:'正常停机'},{id:'6',text:'非正常停机'},
	{id:'7',text:'准备出洞'},{id:'8',text:'出洞'},{id:'9',text:'运输'},{id:'10',text:'维修'},{id:'11',text:'存放'}];
$(function() {
	//盾构机状态
	$("#add_tbmState").combobox({
		data : tbmState,
		valueField : "id",
		textField : "text",
		onLoadSuccess : function() {
			var val = $(this).combobox('getData');
			for (var item in val[0]) {
				if (item == 'id') {
					$(this).combobox('select', val[0][item]);
				}
			}
	    }
	});
	
	
	/**
	 * 适应地层信息
	 */
	$.ajax({
		url : path+'/dzxxList',
		dataType : 'json',
		success : function(jsonstr) {

//			$('#search_sydc').combobox({
//				data : jsonstr,
//				valueField : "ytmc",
//				textField : "ytmc",
//				onLoadSuccess : function() {
//					var val = $(this).combobox('getData');
//					for (var item in val[0]) {
//						if (item == 'id') {
//							$(this).combobox('select', val[0][item]);
//						}
//					}
//				}
//			});

//			$("#add_sydc").combobox({
//				data : jsonstr,
//				valueField : "ytmc",
//				textField : "ytmc",
//				onLoadSuccess : function() {
//					var val = $(this).combobox('getData');
//					for (var item in val[0]) {
//						if (item == 'id') {
//							$(this).combobox('select', val[0][item]);
//						}
//					}
//				}
//			});

	
	}
	});

	/**
	 * 创建数据表格
	 *
	 */
	dataGrid = $('#dataGrid').datagrid({
		url : path+'/dataGrid',
		striped : true,
		rownumbers : true,
		singleSelect : true,
		checkOnSelect : true,
		selectOnCheck : true,
		pagination : true,
		idField : 'id',
		sortName : 'id',
		sortOrder : 'desc',
		pageSize : 15,
		pageList : [ 10, 15, 20, 50 ],
		frozenColumns:[[
			{field : 'ck',checkbox : true},
			{width : '100',title : '盾构机编号',field : 'tbmCode',sortable : true,
				formatter: function(value,row,index){
					return '<a href="#" onclick="showFun(\''+ row.id +'\', \'' + index + '\')">' + value + '</a>';
				}
			}, 
            {width : '200',title : '盾构机名称',field : 'tbmName',sortable : true,align:'left'}
		    ]],
		columns : [ [
            {width : '80',title : '管理号',field : 'manageno'}, 
            {width : '100',title : '负责人名称',field : 'functionaryName'
            	/*,
				formatter : function(value, row, index) {
                	var data={tbmid:row.id};
    				var bd=true;
    				var re;
    				$.ajax({
    			        url: basePath+"/tbmmg/useInfo/tbmId",              //请求地址
    			        type: "GET",                       //请求方式
    			        data: data,        //请求参数
    			        async: false,
    			        dataType: "json",
    			        success: function (result) {
				        	if(result[0]==null){
				        		bd=false;
				        	}else{
				        		re=result;
				        	}
				        }				
					});
					if(bd){
						return re[0].person;
					}else {
						return value;
					}
                }
				*/
            },
            {width : '100',title : '联系方式',field : 'contactNumber'
				/*,
            	formatter : function(value, row, index) {
                	var data={tbmid:row.id};
    				var bd=true;
    				var re;
    				$.ajax({
    			        url: basePath+"/tbmmg/useInfo/tbmId",              //请求地址
    			        type: "GET",                       //请求方式
    			        data: data,        //请求参数
    			        async: false,
    			        dataType: "json",
    			        success: function (result) {
				        	if(result[0]==null){
				        		bd=false;
				        	}else{
				        		re=result;
				        	}
				        }				
					});
					if(bd){
						return re[0].phone;
					}else {
						return value;
					}
                }*/
            },
            {width : '200',title : '生产厂商',field : 'manufacturer'}, 
            {width : '120',title : '盾构机类型',field : 'type',sortable : true,
                formatter : function(value, row, index) {
                    switch (value) {
                    case 1:
                        return '土压盾构机';
                    case 2:
                        return '泥水盾构机';
                    }
                }
            },
			{width : '280',title : '资产归属',field : 'tbmVest'}, 
			{width : '200',title : '出厂日期',field : 'factorydate'},
            {width : '120',title : '购置日期',field : 'buydate' },
			{width : '60',title : '状态',field : 'tbmState',sortable : true
				
				,
                formatter : function(value, row, index) {
                	var data={tbmid:row.id};
    				var bd=true;
    				var re;
    				$.ajax({
    			        url: basePath+"/tbmmg/useInfo/tbmId",              //请求地址
    			        type: "GET",                       //请求方式
    			        data: data,        //请求参数
    			        async: false,
    			        dataType: "json",
    			        success: function (result) {
				        	if(result[0]==null){
				        		bd=false;
				        	}else{
				        		re=result;
				        	}
				        }				
					});
					if(bd){
						return re[0].tbmState;
					}else if(value==0){
						return "进场";
					}else if(value==1){
						return "组装";
					}else if(value==2){
						return "准备始发";
					}else if(value==3){
						return "始发";
					}else if(value==4){
						return "掘进";
					}else if(value==5){
						return "正常停机";
					}else if(value==6){
						return "非正常停机";
					}else if(value==7){
						return "准备出洞";
					}else if(value==8){
						return "出洞";
					}else if(value==9){
						return "运输";
					}else if(value==10){
						return "维修";
					}else if(value==11){
						return "存放";
					}
                }
				
            },
			{width : '280',title : '备注',field : 'remark'}
			/*,
            {width : '280',title : '适应地层',field : 'sydc'}*/
            
			] ],
		onLoadSuccess : function(data) {},
		toolbar : '#toolbar'/*[ 
					{text : '刷新',iconCls: 'icon-reload',
					    handler: function(){
					    	dataGrid.datagrid('reload');
					    }
					},
					'-',
		            {text : "添加",iconCls : 'icon-add',handler : addFun}, 
		            '-',
		            {text : "编辑",iconCls : 'icon-edit',handler : editFun}, 
		            '-', 
		            {text : "删除",iconCls : 'icon-del',handler : deleteFun},
		            '-', 
		            {text: '取消已选',iconCls: 'icon-undo',
		    			handler: function(){
		    				dataGrid.datagrid('clearSelections');
		    			}
		    		},
		            '-',
		            {text : "查看",iconCls : 'icon-book_previous',
		    			handler: function(){
		            		showFun();
		    			}
		            }
		    		,'-',{
		    			text: '导出Excel',
		    			iconCls: 'icon-page_excel',
		    			handler: function(){
		    				var url = path + "/expXls";
		    				
		    				ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, moduleName+"信息", dataGrid, false);
		    			}
		    		},'-',{
		    			text: '导出Pdf',
		    			iconCls: 'icon-page_white_acrobat',
		    			handler: function(){
		    				var url = path + "/expPdf";
		    				
		    				ExportUtil.doExport(url, ExportUtil.TYPE_PDF, moduleName+"信息", dataGrid, false);
		    			}
		    		}
		           ]
		           */
	});
});

/**
 * 
 * @returns
 */
function reload(){
	dataGrid.datagrid('reload');
}


/**
 * 
 * @returns
 */
function clearSelections(){
	dataGrid.datagrid('clearSelections');
}

/**
 * 
 * @returns
 */
function expXls(){
	var url = path + "/expXls";
	ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, moduleName+"信息", dataGrid, false);
}

/**
 * 
 * @returns
 */
function expPdf(){
	var url = path + "/expPdf";
	ExportUtil.doExport(url, ExportUtil.TYPE_PDF, moduleName+"信息", dataGrid, false);
}

/**
 * 数据添加
 */
function addFun() {
	FormUtil.clearForm($("#addDialog"));
	$("#addDialog").dialog('open').dialog('setTitle', '添加［'+moduleName+"］");
}

/**
 * 数据保存
 */
function addAjax() {
	$('#addForm').form('submit', {
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
				$.messager.show({title:'提示',msg:result.msg,showType:'show' });
				dataGrid.datagrid('reload');
				$("#addDialog").dialog('close');
				FormUtil.clearForm($("#addDialog"));
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}

/**
 * 数据编辑
 */
function editFun() {
	
	var id;
	var rows = dataGrid.datagrid('getSelections');
	if (rows.length > 1) {
		$.messager.alert('提示', '请选择单条数据进行编辑！', 'info');
		return;
	}
	else if (rows.length == 1) {
		if (rows[0]) {
			id = rows[0].id;
		}
		else {
			$.messager.alert('提示', '请选择数据后再编辑！', 'info');
			return;
		}
	}
	else {
		$.messager.alert('提示', '请选择数据后再编辑！', 'info');
		return;
	}
	if (rows[0]) {
		FormUtil.clearForm($("#editDialog"));
		$("#editDialog").dialog('open').dialog('setTitle', '编辑［'+moduleName+"］"+"-"+rows[0][nameField]);
		//盾构机状态
		$("#edit_tbmState").combobox({
			data : tbmState,
			valueField : "id",
			textField : "text"
			
		});
		$('#editForm').form('load', rows[0]);
		
//		var r=rows[0].sydc;
//		$("#edit_sydc").combobox({
//			url : path+'/dzxxList',
//			valueField : "ytmc",
//			textField : "ytmc",
//			onLoadSuccess : function() {
//				var val = $(this).combobox('getData');
//				$(this).combobox('setValue', r);
//			}							
//		});		
	}
}

/**
 * 修改提交
 */
function editAjax() {
	
	$.messager.confirm('提示', '您确定要编辑［'+moduleName + '］吗？', function(b) {
		if (b) {
			$('#editForm').form('submit', {
				url : path+'/edit',
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
						$.messager.show({title:'提示',msg:result.msg,showType:'show' });
						dataGrid.datagrid('reload');
						$("#editDialog").dialog('close');
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
 * 数据删除
 */
function deleteFun() {
	var ids = new Array();
	var rows = dataGrid.datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择数据后再删除！', 'info');
		return;
	}
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].id);
	}
	$.messager.confirm('询问', '您是否要删除［'+moduleName + '］所选行吗？', function(b) {
		if (b) {
			progressLoad();
			$.post(path+'/delete', {
				ids : ids.join(",")
			}, function(result) {
				if (result.success) {
					$.messager.show({title:'提示',msg:result.msg,showType:'show' });
					dataGrid.datagrid('reload');
					dataGrid.datagrid('clearSelections');
				}
				progressClose();
			}, 'JSON');
		}
	});
}

/**
 * 查看数据页面
 */
function showFun(oid,index) {
	var id;
	var row;
	if(oid){
		id = oid;
		var allrows = dataGrid.datagrid('getRows');
		row = allrows[index];
	}else{
		var rows = dataGrid.datagrid('getSelections');
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
	
    parent.$.modalDialog({
        title : '查看'+moduleName + '［'+row[nameField]+'］',
        width : 620,
        height : 520,
        href : path+'/showPage?id='+id,
        buttons : [ {
            text : '确定',
            handler : function() {
            	parent.$.modalDialog.handler.dialog('close');
            }
        } ]
    });
}

/**
 * 表单查询
 */
function searchFun() {
	dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
}

/**
 * 表单重置
 */
function cleanFun() {
	$('#searchForm input').val('');
	$('#searchForm').form('clear');
	dataGrid.datagrid('load', {});
}