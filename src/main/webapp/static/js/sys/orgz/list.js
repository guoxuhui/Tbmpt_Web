/**
 * 系统管理系统 组织机构模块
 * 
 * 创建时间：2016年11月05日 
 * 
 */

/**
 * 系统模块路径
 */
var syspath = "sys";
/**
 * 子模块路径
 */
var module = "orgz";
/**
 * 模块名称
 */
var moduleName = "组织机构";
/**
 * 名称字段
 */
var nameField = "name";
/**
 * 业务数据访问全路径
 */
var path = basePath +"/"+ syspath +"/"+ module;
/**
 * 列表树对象
 */
var treeGrid;
$(function() {
	
	/**
	 * 创建数据表格
	 *
	 */
	treeGrid = $('#treeGrid').treegrid({
		url : path+'/treeGrid',
        striped : true,
        rownumbers : true,
        singleSelect : true,
        idField : 'id',
        treeField : 'name',
        parentField : 'pid',
        fit : true,
        fitColumns : false,
        border : false,
        columns : [ [ 
        //{field : 'ck',checkbox : true},
        {field : 'name',title : '部门名称',width : 260,align : "left",
			formatter: function(value,row,index){
				return '<a href="#" onclick="showFun(\''+ row.id +'\')">' + value + '</a>';
			}
		}, 
        {field : 'code',title : '部门编号',width : 120,align : "left"},
    	{title : '部门类型',field : 'type',width : '120',
            formatter : function(value, row, index) {
                switch (value) {//0：机关单位 1：项目机构 2 项目部门
                case 0:
                    return '机关单位';
                case 1:
                    return '项目机构';
                case 2:
                	return '项目部门';
                }
            }
    	},
    	{field : 'createTime',title : '创建时间',width : 160},
        {field : 'address',title : '地址',width : 380,align : "left"}
        ] ],
        toolbar: '#toolbar'
        /*toolbar : [ 
					{text : '刷新',iconCls: 'icon-reload',
					    handler: function(){
					    	treeGrid.treegrid('reload');
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
		    				treeGrid.treegrid('clearSelections');
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
		    			text : '展开',
		    			iconCls: 'icon-redo',
		    			handler: function(){
		    				var node = treeGrid.treegrid('getSelected');
		    				if(node){
		    					treeGrid.treegrid('expand', node.id);
		    				}else{
		    					treeGrid.treegrid('expandAll');
		    				}
		    			}
		    		},
		    		'-',
		    		{
		    			text : '折叠',
		    			iconCls: 'icon-undo',
		    			handler: function(){
		    				var node = treeGrid.treegrid('getSelected');
		    				if(node){
		    					treeGrid.treegrid('collapse', node.id);
		    				}else{
		    					treeGrid.treegrid('collapseAll');
		    				}
		    			}
		    		}
		    		,'-',{
		    			text: '导出Excel',
		    			iconCls: 'icon-page_excel',
		    			handler: function(){
		    				var url = path + "/expXls";
		    				
		    				ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, moduleName+"信息", treeGrid, false);
		    			}
		    		},'-',{
		    			text: '导出Pdf',
		    			iconCls: 'icon-page_white_acrobat',
		    			handler: function(){
		    				var url = path + "/expPdf";
		    				
		    				ExportUtil.doExport(url, ExportUtil.TYPE_PDF, moduleName+"信息", treeGrid, false);
		    			}
		    		}
	           ]*/
	});
});
/**
 * 刷新
 */
function reloadFun(){
	treeGrid.treegrid('reload');
}
/**
 * 取消已选
 */
function clearSelections(){
	treeGrid.treegrid('clearSelections');
}
/**
 * 导出Excel
 */
function expXls(){
	var url = path + "/expXls";
	
	ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, moduleName+"信息", treeGrid, false);
}
/**
 * 导出pdf
 */
function expPdf(){
	var url = path + "/expPdf";
	
	ExportUtil.doExport(url, ExportUtil.TYPE_PDF, moduleName+"信息", treeGrid, false);
}
/**
 * 展开
 */
function expand(){
	var node = treeGrid.treegrid('getSelected');
	if(node){
		treeGrid.treegrid('expand', node.id);
	}else{
		treeGrid.treegrid('expandAll');
	}
}
/**
 * 折叠
 */
function collapse(){
	var node = treeGrid.treegrid('getSelected');
	if(node){
		treeGrid.treegrid('collapse', node.id);
	}else{
		treeGrid.treegrid('collapseAll');
	}
}


/**
 * 数据添加
 */
function addFun() {
	FormUtil.clearForm($("#addDialog"));
	$("#addDialog").dialog('open').dialog('setTitle', '添加［'+moduleName+"］");
    $('#add_pid').combotree({
        url : basePath+'/sys/orgz/tree',
        parentField : 'pid',
        lines : true,
        panelWidth:380,
        panelHeight :330
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
				treeGrid.treegrid('reload');
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
	var node = treeGrid.treegrid('getSelected');
	if (node == undefined) {
		$.messager.alert('提示', '请选择单条数据进行编辑！', 'info');
		return;
	}
	if (node) {
		
		$("#editDialog").dialog('open').dialog('setTitle', '编辑［'+moduleName+"］"+"-"+node[nameField]);
	    $('#edit_pid').combotree({
	        url : basePath+'/sys/orgz/tree?flag=false',
	        parentField : 'pid',
	        lines : true,
	        panelWidth:380,
	        panelHeight :330
	    });
		$('#editForm').form('load', node);
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
						treeGrid.treegrid('reload');
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
	var node = treeGrid.treegrid('getSelected');
	if (node == undefined) {
		$.messager.alert('提示', '请选择单条数据进行删除！', 'info');
		return;
	}
	$.messager.confirm('询问', '您是否要删除'+moduleName + '［'+node[nameField]+'］'+'？', function(b) {
		if (b) {
			
			//页面加载加载进度条启用
			progressLoad();
			$.post(path+'/delete', 
					{  id : node.id  }, 
					function(result) {
				   
				   if (result.success) {
					   /***
					    * result.msg
					    * 1、删除成功！
					    * 2、deleteOrgzTree 
					    */
					  if(result.msg =="deleteOrgzTree"){
							$.messager.confirm('询问', '您要删除的 '+moduleName + '［'+node[nameField]+'］，有子部门，是否要删除'+moduleName + '［'+node[nameField]+'］，以及它的子部门'+'？', function(b) {
								if (b) {
									
									$.post(path+'/deleteTree', {
										id : node.id
									}, function(result) {
										if (result.success) {
												$.messager.alert('提示', result.msg, 'info');
												//用旧地址 重新加载 地区列表
												treeGrid.treegrid('reload');	
										}
											
									}, 'JSON');
								}
							});
						}
						else
						{
							//用旧地址 重新加载 地区列表
							$.messager.alert('提示', result.msg, 'info');
							treeGrid.treegrid('reload');
					  }
					
				    //页面加载加载进度条关闭
					progressClose();
				}else {
					$.messager.alert('错误', result.msg, 'error');
					treeGrid.treegrid('reload');
					//页面加载加载进度条关闭
					progressClose();
				}
				
			}, 'JSON');
		}
  });


}

/**
 * 数据删除
 */
/*function deleteFun2() {
	var node = treeGrid.treegrid('getSelected');
	if (node == undefined) {
		$.messager.alert('提示', '请选择单条数据进行删除！', 'info');
		return;
	}
	$.messager.confirm('询问', '您是否要删除'+moduleName + '［'+node[nameField]+'］'+'？', function(b) {
		if (b) {
			progressLoad();
			$.post(path+'/delete', {
				id : node.id
			}, function(result) {
				if (result.success) {
					$.messager.show({title:'提示',msg:result.msg,showType:'show' });
					treeGrid.treegrid('reload');
				}
				progressClose();
			}, 'JSON');
		}
	});
}
*/
/**
 * 查看数据页面
 */
function showFun(currid) {
	var id;
	var row;
	if(currid){
		id = currid;
		treeGrid.treegrid("select", currid);
		row = treeGrid.treegrid("getSelected");
	}else{
		var rows = treeGrid.treegrid('getSelections');
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
	    $('#show_pid').combotree({
	        url : basePath+'/sys/orgz/tree?flag=false',
	        parentField : 'pid',
	        lines : true,
	        panelWidth:380,
	        panelHeight : 'auto'
	    });
		$('#showDialog').form('load', row);
		disableForm('showForm',true);
	}
}

/**
 * 表单查询
 */
function searchFun() {
	treeGrid.treegrid('reload');
}

/**
 * 表单重置
 */
function cleanFun() {
	$('#searchForm input').val('');
	$('#searchForm').form('clear');
	treeGrid.treegrid('load', {});
}