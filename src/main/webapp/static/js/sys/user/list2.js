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
/**
 * 组织机构
 */
var orgzTree;
$(function() {

	/**
	 *获取组织机构
	 *
	 */
	$.ajax({
		url : basePath+'/sys/orgz/tree',
		dataType : 'json',
		success : function(jsonstr) {

			orgzTree = $('#orgzTree').tree({
		        data : jsonstr,
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
			
	        $('#add_orgzId').combotree({
	        	data : jsonstr,
	            parentField : 'pid',
	            lines : true,
	            panelHeight : 'auto'
	        });
	        
	        $('#edit_orgzId').combotree({
	        	data : jsonstr,
	            parentField : 'pid',
	            lines : true,
	            panelHeight : 'auto'
	        });
		}
	});
	
	/**
	 *获取组织机构
	 *
	 */
	$.ajax({
		url :basePath+'/sys/role/tree',
		dataType : 'json',
		success : function(jsonstr) {

	        $('#add_roleIds').combotree({
	        	data : jsonstr,
	            multiple: true,
	            required: true,
	            panelHeight : 'auto'
	        });
	        
	        $('#edit_roleIds').combotree({
	        	data : jsonstr,
	            multiple: true,
	            required: true,
	            panelHeight : 'auto'
	        });
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
        	{width : '100',title : '登录名',field : 'loginName',sortable : true}, 
        	{width : '100',title : '姓名',field : 'name',sortable : true}
		     ]],
		columns : [ [
        	{width : '160',title : '所属部门',field : 'orgzName'},
        	{width : '150',title : '创建时间',field : 'createTime',sortable : true},  
        	{width : '50',title : '性别',field : 'sex',
                formatter : function(value, row, index) {
                    switch (value) {
                    case 0:
                        return '男';
                    case 1:
                        return '女';
                    }
                }
        	},
        	{width : '150',title : '电话',field : 'phone'}, 
        	{width : '200',title : '角色',field : 'rolesList',
                formatter : function(value, row, index) {
                    var roles = [];
                    for(var i = 0; i< value.length; i++) {
                        roles.push(value[i].name);  
                    }
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
		toolbar : [ 
		            {text : "添加",iconCls : 'icon-add',handler : addFun}, 
		            '-',
		            {text : "编辑",iconCls : 'icon-edit',handler : editFun}, 
		            '-', 
		            {text : "删除",iconCls : 'icon-del',handler : deleteFun}
		           ]
	});
});

/**
 * 数据添加
 */
function addFun() {
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
		$('#editForm').form('load', rows[0]);
	}
}

/**
 * 修改提交
 */
function editAjax() {
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


/**
 * 数据删除
 */
function deleteFun() {
	var id;
	var rows = dataGrid.datagrid('getSelections');
	if (rows.length > 1) {
		$.messager.alert('提示', '请选择单条数据进行删除！', 'info');
		return;
	}
	else if (rows.length == 0) {
		$.messager.alert('提示', '请选择数据后再删除！', 'info');
		return;
	}
	if (rows[0]) {
		id = rows[0].id;
	}
	else {
		$.messager.alert('提示', '请选择数据后再删除！', 'info');
		return;
	}
	
	$.messager.confirm('询问', '您是否要删除'+moduleName + '［'+rows[0][nameField]+'］'+'？', function(b) {
		if (b) {
			progressLoad();
			$.post(path+'/delete', {
				id : id
			}, function(result) {
				if (result.success) {
					$.messager.show({title:'提示',msg:result.msg,showType:'show' });
					dataGrid.datagrid('reload');
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
	dataGrid.datagrid('load', {});
}