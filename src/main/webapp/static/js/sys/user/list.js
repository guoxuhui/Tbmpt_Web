/**
 * 系统管理系统 用户管理模块
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
var module = "user";
/**
 * 模块名称
 */
var moduleName = "用户";
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
		frozenColumns:[[
			{field : 'ck',checkbox : true},
        	{width : '100',title : '登录名',field : 'loginName',sortable : true,
				formatter: function(value,row,index){
					return '<a href="#" onclick="showFun(\''+ row.id +'\', \'' + index + '\')">' + value + '</a>';
				}
			}, 
        	{width : '100',title : '姓名',field : 'name',sortable : true}
		     ]],
		columns : [ [
        	{width : '160',title : '所属部门',field : 'orgzName',sortable : true},
        	{width : '150',title : '创建时间',field : 'createTime',sortable : true},  
        	{width : '200',title : '角色',field : 'rolesList',
                formatter : function(value, row, index) {
                    var roles = [];
                    var ids = [];
                    for(var i = 0; i< value.length; i++) {
                        roles.push(value[i].name);  
                        ids.push(value[i].id);  
                    }
                    row.roleIds = ids.join(',');
                    return(roles.join('\n'));
                }
        	}, 
        	{width : '80',title : '用户类型',field : 'userType',
                formatter : function(value, row, index) {
                    if(value == 0) {
                        return "管理员";
                    }else if(value == 1) {
                        return "用户";
                    }
                    return "未知类型";
                }
        	},
        	{width : '80',title : '状态',field : 'status',
                formatter : function(value, row, index) {
                    switch (value) {
                    case 0:
                        return '正常';
                    case 1:
                        return '停用';
                    }
                }
        	}
			] ],
		onLoadSuccess : function(data) {},
		onClickRow: function(rowIndex,rowData){
			$("#search_loginName").textbox('setValue',rowData.loginName);
			$("#search_name").textbox('setValue',rowData.name);
		},
		toolbar: '#toolbar'
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
		    		{text : "密码重置",iconCls : 'icon-ok',handler : pdresetFun}, 
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
	dataGrid.datagrid('reload');
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
    $('#add_roleIds').combotree({
    	url :basePath+'/sys/role/tree',
        multiple: true,
        required: true,
        panelWidth:200,
        panelHeight : 'auto'
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
	    $('#edit_roleIds').combotree({
	    	url :basePath+'/sys/role/tree',
	        multiple: true,
	        required: true,
	        panelWidth:200,
	        panelHeight : 'auto'
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
	    $('#show_roleIds').combotree({
	    	url :basePath+'/sys/role/tree',
	        multiple: true,
	        required: true,
	        panelWidth:200,
	        panelHeight : 'auto'
	    });
		$('#showDialog').form('load', row);
		disableForm('showForm',true);
	}
}

function pdresetFun(){
	var ids = new Array();
	var rows = dataGrid.datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择数据后再删除！', 'info');
		return;
	}
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].id);
	}
	$.messager.confirm('提示', '您是否要重置密码吗？，重置后密码为123456！', function(b) {
		if (b) {
			progressLoad();
			$.post(path+'/pdreset', {
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


/**
 * 员工选择框
 * @returns
 */
function empSelection(){
	var id_input = this.alt+"Id";
	var name_input = this.alt+"Name";
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
            		$("#"+name_input).textbox("setValue",rows[0].name);
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

