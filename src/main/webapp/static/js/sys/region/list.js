/**
 * 系统管理系统 区划管理模块
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
var module = "region";
/**
 * 模块名称
 */
var moduleName = "区划管理";
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
                     {field : 'name',title : '地区名称',width : 260,align : "left",
             			formatter: function(value,row,index){
             				return '<a href="#" onclick="showFun(\''+ row.id +'\')">' + value + '</a>';
             			}
             		}, 
                     {field : 'code',title : '地区编号',width : 120,align : "left"},
                     {field : 'seq',title : '排序',width : 50,align : "right"}, 
                 	{field : 'type',title : '地区类型',width : '120'}
                     ] ],
        toolbar : [ 
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
	           ]
	});
});

/**
 * 数据添加
 */
function addFun() {
	//打开添加页面时   重置  或 清空 添加  页面 数据
	FormUtil.clearForm($("#addDialog"));
	//打开添加页面  选择 上级下拉列表框 更新加载
	$('#add_pid').combotree({ 
        url : basePath+'/sys/region/tree',
        parentField : 'pid',
        lines : true,
        panelWidth:280,
        panelHeight :350
    });
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
				//用 旧的 url 加载数据
				treeGrid.treegrid('reload');
				//关闭  添加  页面
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
		 //打开编辑页面  选择 上级下拉列表框 更新加载
		 $('#edit_pid').combotree({
		        url : basePath+'/sys/region/tree?flag=false',
		        parentField : 'pid',
		        lines : true,
		        panelWidth:280,
		        panelHeight :350
		    });
		$("#editDialog").dialog('open').dialog('setTitle', '编辑［'+moduleName+"］"+"-"+node[nameField]);
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
						//关闭  添加  页面//用 旧的 url 加载数据
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
					    * 2、deleteRegionTree 
					    */
					  if(result.msg =="deleteRegionTree"){
							$.messager.confirm('询问', '您要删除的 '+moduleName + '［'+node[nameField]+'］，有子对象，是否要删除'+moduleName + '［'+node[nameField]+'］，以及它的子对象'+'？', function(b) {
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
					
					
				}
				//页面加载加载进度条关闭
				progressClose();
			}, 'JSON');
		}
  });


}

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
		//打开查看页面  选择 上级下拉列表框 更新加载
		$('#show_pid').combotree({
	        url : basePath+'/sys/region/tree?flag=false',
	        parentField : 'pid',
	        lines : true,
	        panelWidth:280,
	        panelHeight :350
	    });
		$("#showDialog").dialog('open').dialog('setTitle', '查看［'+moduleName+"］"+"-"+row[nameField]);
		$('#showDialog').form('load', row);
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
	treeGrid.treegrid('load', {});
}