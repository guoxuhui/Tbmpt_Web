/**
 * 工程管理系统 区间线路管理模块
 * 
 * 创建时间：2016年11月05日 
 * 
 */
/**
 * 系统模块同路径
 */
var syspath = "dgjj";
/**
 * 子模块路径
 */
var module = "bzgl";
/**
 * 模块名称
 */
var moduleName = "班组";
/**
 * 名称字段
 */
var nameField = "lineName";
/**
 * 业务数据访问全路径
 */
var path = basePath +"/"+ syspath +"/"+ module;

var proPath = basePath +"/project/line";
/**
 * 列表对象
 */
var dataGrid;
var dataGrid_emp;
$(function() {
	
	$('#addForm_Emp').form({
		url : basePath+'/dgjj/bzglEmp/save',
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
        	$('#dataSta').val('original');
        	parent.$.messager.show({title:'提示',msg:result.msg,showType:'show' });               
        	$('#addEmp').dialog('close');
        } else {
            parent.$.messager.alert('错误', result.msg, 'error');
        }
    }
});
	
	/**
	 *获取工程信息
	 *
	 */
	$.ajax({
		url : proPath+'/getProDic',
		dataType : 'json',
		success : function(jsonstr) {
			jsonstr.unshift({
				'id' : '-1',
				'proName' : '-请选择-'
			}); //向json数组开头添加自定义数据  

			$('#search_proId').combobox({
				data : jsonstr,
				valueField : "id",
				textField : "proName",
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				 },
				 onSelect:function(newValue,oldValue){
			        	$('#search_sectionId').combobox("clear");
			        	$('#search_sectionId').combobox('reload', proPath+"/getProSectionDic?proId="+newValue.id);
			      }
			});

			$("#add_proId").combobox({
				data : jsonstr,
				valueField : "id",
				textField : "proName",
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				},
				 onSelect:function(newValue,oldValue){
			        	$('#add_sectionId').combobox("clear");
			        	$('#add_sectionId').combobox('reload', proPath+"/getProSectionDic?proId="+newValue.id);
			      }
			});

			$("#edit_proId").combobox({
				data : jsonstr,
				valueField : "id",
				textField : "proName",
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				},
				 onSelect:function(newValue,oldValue){
			        	$('#edit_sectionId').combobox("clear");
			        	$('#edit_sectionId').combobox('reload', proPath+"/getProSectionDic?proId="+newValue.id);
			      }
			});
			
			$("#show_proId").combobox({
				data : jsonstr,
				valueField : "id",
				textField : "proName",
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				},
				 onSelect:function(newValue,oldValue){
			        	$('#show_sectionId').combobox("clear");
			        	$('#show_sectionId').combobox('reload', proPath+"/getProSectionDic?proId="+newValue.id);
			      }
			});

		}
	});
	
	/**
	 *获取工程区间信息
	 *
	 */
	$.ajax({
		url : proPath+'/getProSectionDic',
		dataType : 'json',
		success : function(jsonstr) {

			$('#search_sectionId').combobox({
				data : jsonstr,
				valueField : "id",
				textField : "sectionName",
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				},
				onSelect:function(newValue,oldValue){
		        	
		        	$('#search_lineId').combobox("clear");
		        	$('#search_lineId').combobox('reload', proPath+"/getProLineDic?sectionId="+newValue.id);
		      }
			});

			$("#add_sectionId").combobox({
				data : jsonstr,
				valueField : "id",
				textField : "sectionName",
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				},
				onSelect:function(newValue,oldValue){
		        	
		        	$('#add_lineId').combobox("clear");
		        	$('#add_lineId').combobox('reload', proPath+"/getProLineDic?sectionId="+newValue.id);
		      }
			});

			$("#edit_sectionId").combobox({
				data : jsonstr,
				valueField : "id",
				textField : "sectionName",
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				},
				onSelect:function(newValue,oldValue){
		        	
		        	$('#edit_lineId').combobox("clear");
		        	$('#edit_lineId').combobox('reload', proPath+"/getProLineDic?sectionId="+newValue.id);
		      }
			});
			
			$("#show_sectionId").combobox({
				data : jsonstr,
				valueField : "id",
				textField : "sectionName",
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				},
				onSelect:function(newValue,oldValue){
		        	
		        	$('#show_lineId').combobox("clear");
		        	$('#show_lineId').combobox('reload', proPath+"/getProLineDic?sectionId="+newValue.id);
		      }
			});

		}
	});
	/**
	 *获取线路信息
	 *
	 */
	$.ajax({
		url : proPath+'/getProLineDic',
		dataType : 'json',
		success : function(jsonstr) {
		
			$('#search_lineId').combobox({
				data : jsonstr,
				valueField : "id",
				textField : "lineName",
				editable:false,
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							
						}
					}
				 }
			});

			$("#add_lineId").combobox({
				data : jsonstr,
				valueField : "id",
				textField : "lineName",
				editable:false,
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				}
			});

			$("#edit_lineId").combobox({
				data : jsonstr,
				valueField : "id",
				textField : "lineName",
				editable:false,
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				}
			});
			
			$("#show_lineId").combobox({
				data : jsonstr,
				valueField : "id",
				textField : "lineName",
				editable:false,
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
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
		rownumbers : false,
		singleSelect : false,
		checkOnSelect : true,
		selectOnCheck : true,
		pagination : true,
		idField : 'id',
		sortName : 'id',
		sortOrder : 'desc',
		pageSize : 15,
		pageList : [ 10, 15, 20, 50 ],
		columns:[[
		                
			{field : 'ck',checkbox : true},
			{width : '150',title : '班组名称',field : 'bzname',sortable : true}, 
			{width : '150',title : '上班时间',field : 'startTime',sortable : true},
			{width : '150',title : '下班时间',field : 'endTime',sortable : true}, 
			{width : '150',title : '线路名称',field : 'lineName',sortable : true}, 
			{width : '150',title : '区间名称',field : 'sectionName',sortable : true}, 
            {width : '320',title : '所属工程',field : 'proName',sortable : true},
            {width : '100',field:'action',title:'员工',align:'center',
				formatter:function(value,row,index){												
						return $.formatString('<a class="add" href="javascript:void(0)" iconCls="icon-add" onclick="addEmp(\'{0}\',\'{1}\',\'{2}\',\'{3}\',\'{4}\',\'{5}\',\'{6}\',\'{7}\');" ></a>',row.proName,row.sectionName,row.lineName,row.bzname,row.proId,row.sectionId,row.lineId,row.id);
				}
			}
		     ]],
		
		onLoadSuccess : function(data) {
			$("a.add").linkbutton();
		},
		toolbar : [ 
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
		    		'-',{
		    			text: '导出Excel',
		    			iconCls: 'icon-page_excel',
		    			handler: function(){
		    				var url = path + "/expXls";
		    				
		    				ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, "班组管理信息", dataGrid, false);
		    			}
		    		},'-',{
		    			text: '导出Pdf',
		    			iconCls: 'icon-page_white_acrobat',
		    			handler: function(){
		    				var url = path + "/expPdf";
		    				
		    				ExportUtil.doExport(url, ExportUtil.TYPE_PDF, "班组管理信息", dataGrid, false);
		    			}
		    		}
		            
		           ]
	});
	
	/**
	 * 员工数据表格
	 */
	dataGrid_emp = $('#dataGrid_emp').datagrid({
		url : basePath+'/dgjj/bzglEmp/dataGrid',
		striped : true,
		rownumbers : false,
		singleSelect : false,
		checkOnSelect : true,
		selectOnCheck : true,
		pagination : false,
		idField : 'id',
		pageSize : 15,
		pageList : [ 500,1000 ],
		columns:[[
		                
			{field : 'id',checkbox : true},
			{width : '150',title : '员工id',field :'empId',hidden:true},
			{width : '150',title : '姓名',field : 'empName'}, 			
			{width : '150',title : '性别',field : 'sex',
				formatter : function(value, row, index) {
	                switch (value) {
	                case '1':
	                    return '男';
	                case '0':
	                    return '女';
	                }
	            }
			}, 			
			{width : '150',title : '生日',field : 'birthday'}, 		
			{width : '100',title : '电话',field : 'phone'}, 
	        {width : '100',title : '部门',field : 'orgName'}        
	        
		     ]],		
		toolbar : [ 
					{text : '刷新',iconCls: 'icon-reload',
					    handler: function(){
					    	dataGrid_emp.datagrid('reload');
					    }
					},
					'-',
		            {text : "添加",iconCls : 'icon-add',handler : empSelection}, 
		            '-',
		            {text : "删除",iconCls : 'icon-del',handler : deleteEmp},
		            '-', 
		            {text: '取消已选',iconCls: 'icon-undo',
		    			handler: function(){
		    				dataGrid_emp.datagrid('clearSelections');
		    			}
		    		},	         
		    		'-',{
		    			text: '导出Excel',
		    			iconCls: 'icon-page_excel',
		    			handler: expXls
		    		},'-',{
		    			text: '导出Pdf',
		    			iconCls: 'icon-page_white_acrobat',
		    			handler: expPdf
		    		}
		            
		           ]	
	});

});




/**
 * 员工管理
 */
function addEmp(proName,sectionName,lineName,bzname,proId,sectionId,lineId,id){
	var rows = dataGrid.datagrid('getSelections');
	$("#add_projectName").html(proName);
	$("#add_qujianName").html(sectionName);
	$("#add_lineName").html(lineName);
	$("#add_bzName").html(bzname);
	
	$("#addForm_gcBh").val(proId);
	$("#addForm_qlBh").val(sectionId);
	$("#addForm_xlBh").val(lineId);
	$("#addForm_id").val(id);
	
	$("#addEmp").dialog('open').dialog('setTitle', '添加人员');
	dataGrid_emp.datagrid("load",{id:id});
	dataGrid_emp.datagrid('clearSelections');
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
 * 数据编辑
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
 * 表单重置
 */
function cleanFun() {
	$('#searchForm input').val('');
	$('#searchForm').form('clear');
	dataGrid.datagrid('load', {});
}

/**
 * 人员管理
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
            		addRow(rows);
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

/**
 * 员工增加
 * @param obj
 * @returns
 */
function addRow(rows){
	var emp=dataGrid_emp.datagrid('getRows');
	for(var j=0;j<rows.length;j++){
	var bool=true;
	for(var i=0;i<emp.length;i++){
		if(emp[i].empId==rows[j].id){
			$.messager.alert('提示', emp[i].empName+'员工已存在！', 'info');
			bool=false;
			break;
		}
	}
	if(bool){
	$('#dataGrid_emp').datagrid('appendRow',{		
		empName:rows[j].name,
		sex:rows[j].sex,
		birthday:rows[j].birthday,
		phone:rows[j].phone,
		orgName:rows[j].orgzName,
		address:rows[j].address,
		empId:rows[j].id
});
	}
	}
	
}

/**
 * 员工保存
 * @returns
 */
function addSave(){
	var detailsInfo=new Array();
  	var rows = $("#dataGrid_emp").datagrid("getRows");
  	
  	for(var i=0;i<rows.length;i++)	{
		var obj = new Object();
		obj.empId=rows[i].empId;
		obj.empName=rows[i].empName;
		obj.fid=$('#addForm_id').val();
		detailsInfo[i] = obj;
	}
	
	var json = JSON.stringify(detailsInfo);
	$('#details').val(json);
	var isValid = $('#addForm_Emp').form('validate')
	if(isValid){
    	$('#addForm_Emp').submit();
	}else{
		progressClose();
	}
}

/**
 * 删除员工
 * @returns
 */
function deleteEmp() {
	var ids = new Array();
	var rows = dataGrid_emp.datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择数据后再删除！', 'info');
		return;
	}
	for(var i=rows.length-1; i>=0; i--){
		var index=dataGrid_emp.datagrid('getRowIndex',rows[0]);
		dataGrid_emp.datagrid('deleteRow',index);
		
	}	
}
/**
 * 员工导出excel
 */
function expXls(){
	var id=$('#addForm_id').val();
	var href = basePath+'/dgjj/bzglEmp/expXls?bzId='+id;		  				
	ExportUtil.doExport(href, ExportUtil.TYPE_XLS_2003, "人员信息", dataGrid_emp, false);
}
/**
 * 员工导出pdf
 */
function expPdf(){
	var id=$('#addForm_id').val();
	var href = basePath+'/dgjj/bzglEmp/expPdf?bzId='+id;		  				
	ExportUtil.doExport(href, ExportUtil.TYPE_PDF, "人员信息", dataGrid_emp, false);
}
