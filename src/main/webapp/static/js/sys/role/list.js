/**
 * 系统管理系统 角色管理模块
 * 
 * 创建时间：2016年11月05日 
 * 
 */

/**
 * 系统模块同路径
 */
var syspath = "sys";
/**
 * 子模块路径
 */
var module = "role";
/**
 * 模块名称
 */
var moduleName = "角色";
/**
 * 名称字段
 */
var nameField = "name";
/**
 * 业务数据访问全路径
 */
var path = basePath +"/"+ syspath +"/"+ module;
/**
 * 列表对象
 */
var dataGrid;
$(function() {
	
	/**
	 * 组织机构树
	 */
	orgzTree = $('#orgzTree').tree({
	url : basePath+'/sys/orgz/tree',
	parentField : 'pid',
	lines : true,
	onClick : function(node) {
	    dataGrid.datagrid('load', {
	        orgzId: node.id
	    });
	    $('#search_orgzId').combotree('setValue',node.id);
	    $('#search_orgzId').combotree('setText',node.text);
	},
	onLoadSuccess:function(node,data){  
		//先 关闭所有节点
		$("#orgzTree").tree("collapseAll");
		//默认展开前三级
		
		//展开 第一级 节点
        $("#orgzTree li:eq(0)").find("div").addClass("tree-node-selected");   //通过添加样式class，设置第一个节点高亮（选中节点）  
        var n = $("#orgzTree").tree("getSelected");   //获取（选中节点）的值
        if(n!=null){  
             $('#orgzTree').tree('expand',n.target);   //展开指定的节点
             $("#orgzTree li:eq(0)").find("div").removeClass("tree-node-selected"); //通过删除样式class，删除该节点高亮（选中）样式，准备展开下一个节点
             //展开 第二级 节点
             $("#orgzTree li:eq(1)").find("div").addClass("tree-node-selected");
             var m = $("#orgzTree").tree("getSelected");
             if(m!=null){
                  $('#orgzTree').tree('expand',m.target);
                  $("#orgzTree li:eq(1)").find("div").removeClass("tree-node-selected");
                  //展开 第三级 节点
                  $("#orgzTree li:eq(2)").find("div").addClass("tree-node-selected");
                  var b = $("#orgzTree").tree("getSelected");
                  if(b!=null){
                       $('#orgzTree').tree('expand',b.target);
                       $("#orgzTree li:eq(2)").find("div").removeClass("tree-node-selected");
                  }
             }
        }  
        
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
		singleSelect : false,
		checkOnSelect : true,
		selectOnCheck : true,
		pagination : true,
		idField : 'id',
		sortName : 'id',
		sortOrder : 'desc',
		pageSize : 15,
		pageList : [ 10, 15, 20, 50 ],
		columns : [ [
			{field : 'ck',checkbox : true},
			{width : '120',title : '名称',field : 'name',sortable : true,
				formatter: function(value,row,index){
					return '<a href="#" onclick="showFun(\''+ row.id +'\', \'' + index + '\')">' + value + '</a>';
				}
			}, 
			{width : '350',title : '描述',field : 'description',fit:true}, 
			/*
			{width : '60',title : '状态',field : 'status',sortable : true,
                formatter : function(value, row, index) {
                    switch (value) {
                    case 0:
                        return '正常';
                    case 1:
                        return '停用';
                    }
                }
			},*/
			{width:150,title:'创建时间',field:'createTime',sortable:true},
			{width:70,title:'创&nbsp;建&nbsp;人',field:'createUser'},
			{width:150,title:'修改时间',field:'updateTime',sortable:true},
			{width:70,title:'修&nbsp;改&nbsp;人',field:'updateUser'}
			] ],
		onLoadSuccess : function(data) {},
		onClickRow: function(rowIndex,rowData){			
			$("#search_name").textbox('setValue',rowData.name);
		},
		toolbar : '#toolbar'
		/*toolbar : [ 
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
		            {text : "授权",iconCls : 'icon-ok',handler : grantFun}, 
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
		            },
		    		'-',
		    		{
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
		           ]*/
	});
});
/**
 * 刷新
 */
function reloadFun(){
//	dataGrid.datagrid('loadData', {total: 0, rows:[]}); 
//	dataGrid.datagrid('reload');
	dataGrid.datagrid("load",{orgzId:'',name:'',status:null,startTime:null,endTime:null});
}
/**
 * 取消已选
 */
function clearSelections(){
	dataGrid.datagrid('clearSelections');
}
/**
 * 导出Excel
 */
function expXls(){
	var url = path + "/expXls";
	
	ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, moduleName+"信息", dataGrid, false);
}
/**
 * 导出pdf
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
    $('#add_orgzId').combotree({
    	url : basePath+'/sys/orgz/tree',
        parentField : 'pid',
        lines : true,
        panelWidth:380,
        panelHeight :350
    });
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
		$("#editDialog").dialog('open').dialog('setTitle', '编辑［'+moduleName+"］"+"-"+rows[0][nameField]);
	    $('#edit_orgzId').combotree({
	    	url : basePath+'/sys/orgz/tree',
	        parentField : 'pid',
	        lines : true,
	        panelWidth:280,
	        panelHeight :350
	    });
		$('#editForm').form('load', rows[0]);
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
	if (row) {
		$("#showDialog").dialog('open').dialog('setTitle', '查看［'+moduleName+"］"+"-"+row[nameField]);
	    $('#show_orgzId').combotree({
	    	url : basePath+'/sys/orgz/tree',
	        parentField : 'pid',
	        lines : true,
	        panelWidth:280,
	        panelHeight :350
	    });
		$('#showForm').form('load', row);
		disableForm('showForm',true);
	}
}

/**
 * 表单查询
 */
function searchFun() {
	dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
}

/**
 * 授权
 */
function grantFun() {
	var id;
	var rows = dataGrid.datagrid('getSelections');
	if (rows.length > 1) {
		$.messager.alert('提示', '请选择单条数据进行授权！', 'info');
		return;
	}
	else if (rows.length == 0) {
		$.messager.alert('提示', '请选择数据后再授权！', 'info');
		return;
	}
	if (rows[0]) {
		id = rows[0].id;
	}
	else {
		$.messager.alert('提示', '请选择数据后再授权！', 'info');
		return;
	}
    
    parent.$.modalDialog({
        title : '授权',
        width : 500,
        height : 500,
        href : path+'/grantPage?id=' + id,
        buttons : [ {
            text : '确定',
            iconCls:"icon-ok",
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#roleGrantForm');
                f.submit();
            }
        },{
            text : '取消',
            iconCls:"icon-cancel",
            handler : function() {
            	parent.$.modalDialog.handler.dialog('close');
            }
        }  ]
    });
}

/**
 * 表单重置
 */
function cleanFun() {
	$('#searchForm input').val('');
	$('#searchForm').form('clear');
	dataGrid.datagrid('load', {});
}