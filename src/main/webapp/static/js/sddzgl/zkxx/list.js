/**
 * 隧道地质管理：钻孔信息管理
 * 
 * 创建时间：2017年03月28日 
 * 
 */
/**
 * 系统模块同路径
 */
var syspath = "sddzgl";
/**
 * 子模块路径
 */
var module = "zkxx";
/**
 * 模块名称
 */
var moduleName = "钻孔信息";

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
	 * 加载项目信息
	 */
	 $('#gcbh').combobox({
         url : basePath + '/project/info/getProjects',
         valueField : "id",
         textField : "proName",
         onChange:function(){
         	 $('#qjbh').combobox({
                  url : basePath+'/project/line/getProSectionDic?proId='+$('#gcbh').combobox('getValue'),
                  valueField: "id",
                  textField: "sectionName",
                  onChange:function(){
                     var qjid = $('#qjbh').combobox('getValue');
                      $('#xlbh').combobox({
                          url : basePath+'/project/line/getProLineDic?sectionId='+qjid,
                          valueField: "id",
                          textField: "lineName"
                      });
                 }
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
		frozenColumns : [ [
   			{field : 'ck',checkbox : true},
   			{width : '150',title : '钻孔编号',field : 'zkbh',sortable : true, 
				formatter: function(value,row,index){
					return '<a href="#" onclick="showFun(\''+ row.id +'\', \'' + index + '\')">' + value + '</a>';
				}
			},
			{width : '150',title : '钻孔施工单位',field : 'zksgdw',sortable : true}
		          ]],
		columns : [ [
			{width : '150',title : '钻孔类型 ',field : 'zklx',sortable : true},
			{width : '320',title : '项目名称',field : 'xmMc',sortable : true},
			{width : '200',title : '区间名称',field : 'qjMc',sortable : true},
			{width : '150',title : '线路名称',field : 'xlMc',sortable : true},
			{width : '150',title : '钻孔位置(X)',field : 'zkwzX'},
			{width : '150',title : '钻孔位置(Y)',field : 'zkwzY'},
			{width : '150',title : '对应环号',field : 'dyhh'},
			{width : '150',title : '钻孔里程',field : 'zklc',sortable : true},
			{width : '150',title : '备注',field : 'bz'}
		     ]],
		
		onLoadSuccess : function() {},
		toolbar :'#toolbar'
	});
	
	/***
	 * 构造查询项目，区间，线路下拉框
	 */
	_getProDic("search_xmId",null,null);
	
});




/**
 * 表单重置
 */
function cleanFun() {
	$('#searchForm input').val('');
	$('#searchForm').form('clear');
	dataGrid.datagrid('load', {});
}
/**
 * 表单查询
 */
function searchFun(){
	dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
}


/**
 * 刷新
 */
function reloadFun(){
	dataGrid.datagrid('reload');
}

/**
 * 取消已选
 * @returns
 */
function clearSelections(){
	dataGrid.datagrid('clearSelections');
}

/**
 * 打开添加页面
 * @returns
 */
function addFun() {
	FormUtil.clearForm($("#addDialog"));
	$("#addDialog").dialog('open').dialog('setTitle', '添加［'+moduleName+"］");
	/**
	 * 加载项目信息
	 */
	 $('#add_xmId').combobox({
         url : basePath + '/project/info/getProjects',
         valueField : "id",
         textField : "proName",
         onChange:function(){
         	 $('#add_qjId').combobox({
                  url : basePath+'/project/line/getProSectionDic?proId='+$('#add_xmId').combobox('getValue'),
                  valueField: "id",
                  textField: "sectionName",
                  onChange:function(){
                     var qjid = $('#add_qjId').combobox('getValue');
                      $('#add_xlId').combobox({
                          url : basePath+'/project/line/getProLineDic?sectionId='+qjid,
                          valueField: "id",
                          textField: "lineName"
                      });
                 }
              });
         }
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
				$('#dataGrid').datagrid('reload');
				$("#addDialog").dialog('close');
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}

/**
 * 打开编辑页面
 * @returns
 */
function editFun() {
	
	var id;
	var rows = $('#dataGrid').datagrid('getSelections');
	if (rows.length > 1) {
		$.messager.alert('提示', '请选择单条数据进行编辑！', 'info');
		return;
	}
	
	else if(rows.length==0){
		$.messager.alert('提示', '请选择数据后再编辑！', 'info');
		return;
	}
	if (rows[0]) {
		$("#editDialog").dialog('open').dialog('setTitle', '编辑［'+moduleName+"］");
		
		$('#editForm').form('load', rows[0]);
		/**
		 * 加载项目信息
		 */
		var i=0;
	    $('#edit_xmId').combobox({
	        url :  basePath + '/project/info/getProjects',
	        valueField: "id",
	        textField: "proName",
	        onChange:function(){

	       },
	       onLoadSuccess:function(){
	           $('#edit_xmId').combobox('setValue',rows[0].xmId);
	           var pid = $('#edit_xmId').combobox('getValue');
	           $('#edit_qjId').combobox({
	               url :  basePath + '/project/line/getProSectionDic?proId='+pid,
	               valueField: "id",
	               textField: "sectionName",
	               onChange:function(){

	              },
	              onLoadSuccess:function(){
	                  if(i ==0){
	                      $('#edit_qjId').combobox('setValue',rows[0].qjId);
	                  }
	                  var qjid = $('#edit_qjId').combobox('getValue');
	                  $('#edit_xlId').combobox({
	                     url :  basePath + '/project/line/getProLineDic?sectionId='+qjid,
	                     valueField: "id",
	                     textField: "lineName",
	                     onLoadSuccess:function(){
	                         if(i == 0 ){
	                             $('#edit_xlId').combobox('setValue',rows[0].xlId);
	                         }
	                         i = 1;
	                     }
	                  });
	              }
	           });
	       }
	    });
		
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
		$("#showDialog").dialog('open').dialog('setTitle', '查看［'+moduleName+"］");
		$('#showDialog').form('load', row);
	}
}

/**
 * 数据删除
 */
function deleteFun() {
	var ids = new Array();
	var rows =$('#dataGrid').datagrid('getSelections');
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
					$('#dataGrid').datagrid('reload');
					$('#dataGrid').datagrid('clearSelections');
				}else{
					$.messager.alert('提示',result.msg, 'info');
				}
				progressClose();
			}, 'JSON');
		}
	});
}


/**
 * 导出excel
 * @returns
 */
function expXls(){
	var url = path + "/expXls";
	ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, moduleName,dataGrid, false);
	
}

/**
 * 导出excel 
 * @returns
 */
function expPdf(){ 
	var url = path + "/expPdf";
	ExportUtil.doExport(url, ExportUtil.TYPE_PDF, moduleName,dataGrid, false);
	 
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
		    			height:'180px'
		    		}); 
	            $('#impDialog').dialog('close'); 
	            $('#dataGrid').datagrid('reload');
	        } else {
	            parent.$.messager.alert('错误', result.msg, 'error');
	        }
    	}
   });
}




