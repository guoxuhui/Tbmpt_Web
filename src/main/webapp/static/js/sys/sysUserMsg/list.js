/**
 * 系统消息模块 用户消息信息
 * 
 * 创建时间：2017年06月03日 
 * 
 */
/**
 * 系统模块同路径
 */

var syspath = "sys/base";
/** 
 * 子模块路径
 */
var module = "sysUserMsg";
/**
 * 模块名称
 */
var moduleName = "用户消息信息";
/**
 * 名称字段
 */
var nameField = "sysUserMsg";
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
		sortName : 'msgtime',
		sortOrder : 'desc',
		pageSize : 15,
		pageList : [ 5,10, 15, 20, 50 ],
		frozenColumns:[[
		                
			{field : 'ck',checkbox : true},
			{width : '150',title : '消息标题',field : 'msgname',sortable : true, 
				formatter: function(value,row,index){
					return '<a href="#" onclick="showFun(\''+ row.id +'\', \'' + index + '\', \'' + row.url + '\')">' + value + '</a>';
				}
			},
            {width : '150',title : '消息分类',field : 'msgtype',sortable : true},
            {width : '500',title : '消息内容',field : 'msgcontent',align:'center'},
			{width : '150',title : '消息时间',field : 'msgtime',sortable : true}
		     ]],
		columns : [ [
			{width : '100',title : '消息状态',field : 'state',sortable : true,
                formatter : function(value, row, index) {
                    switch (value) {
                    case '0':
                        return '未读';
                    case '1':
                        return '已读';
                    }
                }
			},
			{width : '200',title : '备注',field : 'remark',align:'center'},
			{width : '100',title : '设置状态',field : 'userid',align:'center',
				formatter: function(value,row,index){
					var a ;
					var sign ="<font color='#227700'>消息未读</font>";
					var sign2 ="标记已读";
					if(row.state =="0"){
						a=$.formatString('<a href="javascript:void(0)"  onclick="signStateAjax(\''+ row.id +'\',\''+ sign2 +'\')">'+sign+'</a>');
					}else{
						a = "消息已读";	
					}
					
					return a;
				}
			}
			
			] ],
		onLoadSuccess : function(data) {
			$("a.add").linkbutton();
		},
		toolbar: '#toolbar'
	});
});


/** 标记状态*/
function signStateAjax(id,sign2){
	
	if(id !=null && id !=""){
		var url =basePath+'/sys/base/sysUserMsg/signState';
		parent.$.messager.confirm('询问', '您是否要把该消'+sign2+'？', function(b) {
	        if (b) {
	            progressLoad();
	        	var ids = [];
	        	ids.push(id);
	        	$.post(url, {
	            	ids : ids.toString()
	            }, function(result) {
	                if (result.success) {
	                    parent.$.messager.show({title:'提示',msg:sign2+result.msg,showType:'show' });
	                    dataGrid.datagrid('reload');
	                }else{
	                	parent.$.messager.alert('错误', sign2+result.msg, 'error');
	                }
	                
	                progressClose();
	            }, 'JSON');
	        }
	    });
	}

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
 * 刷新
 */
function reloadFun(){
	dataGrid.datagrid('reload');
}


/**
 * 查看数据页面
 */
function showFun(id,index,url) {

	if (id == undefined) {
        var rows = dataGrid.datagrid('getSelections');
        id = rows[0].id;
        url = rows[0].url
    } else {
        dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
    }
    parent.$.modalDialog({
        title : '查看',
        width : 800,
        height : 600,
        href : basePath+url
    });
    
	$.post(basePath+'/sys/base/sysUserMsg/signState', {
		ids : id
	}, function(result) {
	}, 'JSON');
}

function closeFun() {
	var ids = new Array();
	var rows = dataGrid.datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择数据后再操作！', 'info');
		return;
	}
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].id);
	}
	$.messager.confirm('询问', '您是否要处理［'+moduleName + '］所选行吗？', function(b) {
		if (b) {
			progressLoad();
			$.post(basePath+'/sys/base/sysUserMsg/signState', {
				ids : ids.join(",")
			}, function(result) {
				if (result.success) {
					$.messager.show({title:'提示',msg:result.msg,showType:'show' });
					dataGrid.datagrid('reload');
					dataGrid.datagrid('clearSelections');
				}else{
					$.messager.alert('提示',result.msg, 'info');
				}
				progressClose();
			}, 'JSON');
		}
	});
}


/*2017-06-05;wl_wpg:返回主页*/
function backtrackIndex() {
	
	window.location.href = basePath + "/index";
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


