/**
 * 管片质量上报系统 上报管理模块
 * 
 * 创建时间：2017年04月17日 
 * 
 */

/**
 * 系统模块同路径
 */
var syspath = "zl";
/**
 * 子模块路径
 */
var module = "up";
/**
 * 模块名称
 */
var moduleName = "管片质量上报";
/**
 * 名称字段
 */
var nameField = "cycleNo";
/**
 * 业务数据访问全路径
 */
var path = basePath +"/"+ syspath +"/"+ module;
/**
 * 列表对象
 */
var dataGrid;
/** 关联类型 平面*/
var type=[{id:'无',name:'无'},{id:'有',name:'有'}];
$(function() {
	
	//质量问题:是否有质量问题判断 */
	var addHasProblem;
	$('#add_hasProblem').combobox({
		data : type,
		valueField : "id",
		textField : "name",
		onChange:function() {
	    	var hasProblem=$(this).combobox('getValue');
	    	
	    	if(hasProblem=="有"){
	    		$("#add_hasProblem_table_after").after(addHasProblem);
	    	} 
	    	else if(hasProblem=="无"){
	    		addHasProblem=$("#add_hasProblem_tr").detach();
	        }
	    }
	});
	
	//质量问题:是否有质量问题判断 */
	var editHasProblem;
	$('#edit_hasProblem').combobox({
		data : type,
		valueField : "id",
		textField : "name",
		onChange:function() {
	    	var hasProblem=$(this).combobox('getValue');
	    	
	    	if(hasProblem=="有"){
	    		$("#edit_hasProblem_table_after").after(editHasProblem);
	    	} 
	    	else if(hasProblem=="无"){
	    		editHasProblem=$("#edit_hasProblem_tr").detach();
	    		
	        }
	    }
	});
	
	//质量问题:是否有质量问题判断 */
	var showHasProblem;
	$('#show_hasProblem').combobox({
		data : type,
		valueField : "id",
		textField : "name",
		onChange:function() {
	    	var hasProblem=$(this).combobox('getValue');
	    	
	    	if(hasProblem=="有"){
	    		$("#show_hasProblem_table_after").after(showHasProblem);
	    	} 
	    	else if(hasProblem=="无"){
	    		showHasProblem=$("#show_hasProblem_tr").detach();
	    		
	        }
	    }
	});
	
	//开始时间--结束时间 -
	$.extend($.fn.validatebox.defaults.rules, {  
	       equaldDate: {  
	           validator: function (value, param) {  
	               var start = $(param[0]).datebox('getValue');  //获取开始时间--结束时间
	               var a = param[0];
	               var boo;
	               if("#upDateEnd"==param[0]){       //判断是否是-开始时间
	            	   var boo = value <= start;      //开始时间小于结束时间
	               }else{
	            	   var boo = value >= start;      //结束时间大于开始时间    
	               }
	               if(start==""){
            		   return true;
            	   }else if(start!="" && boo==false){
            		  /* if("#upDateEnd"==param[0]){
            			    $("#searchForm input[name='upDateStart']").val("");//开始时间的值
            				$("#upDateStart").datebox('setValue', '');
            		   }else{
            			   $("#searchForm input[name='upDateEnd']").val("");
            			   $("#upDateEnd").datebox('setValue', '');
            		   }*/
            		   //$(this).val(start);
            		   return boo;
            	   }
            	   return boo;
	               
	           },  
	           message: '结束日期应大于开始日期!'                     //匹配失败消息  
	       }  
	   });
	
	
	
	/**
	 *获取工程信息
	 *
	 */
	$.ajax({
		url : path+'/getProDic',
		dataType : 'json',
		success : function(jsonstr) {
			jsonstr.unshift({
				'id' : '',
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
				 },onSelect:function(newValue,oldValue){
						$('#search_sectionId').combobox("clear");
						$('#search_sectionId').combobox('reload', path+"/getProSectionDic?proId="+newValue.id);
				 }
			});
		}
	});
	
	/**
	 *获取工程区间信息
	 *
	 */
	$.ajax({
		url : path+'/getProSectionDic',
		dataType : 'json',
		success : function(jsonstr) {
            $("#search_sectionId").combobox({
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
                    $('#search_lineId').combobox('reload', path+"/getProLineDic?sectionId="+newValue.id);
                }
            });
		}
	});

	/**
	 *获取工程线路信息
	 *
	 */
	$.ajax({
		url : path+'/getProLineDic',
		dataType : 'json',
		success : function(jsonstr) {
            $("#search_lineId").combobox({
                data : jsonstr,
                valueField : "id",
                textField : "lineName",
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
		rownumbers : true,
		singleSelect : false,
		checkOnSelect : true,
		selectOnCheck : true,
		pagination : true,
		idField : 'id',
		sortName : 'cycleNo',
		sortOrder : 'desc',
		pageSize : 15,
		pageList : [ 10, 15, 20, 50 ],
		frozenColumns : [ [
			{field : 'ck',checkbox : true},
			{width : '320',title : '项目部名称',field : 'proName1',sortable : true},
			{width : '200',title : '区间',field : 'sectionName',sortable : true},
			{width : '100',title : '线路',field : 'lineName',sortable : true},  
			{width : '100',title : '环号',field : 'cycleNo',sortable : true,
				formatter: function(value,row,index){
					return '<a href="#" onclick="showFun(\''+ row.id +'\', \'' + index + '\')">第' + value + '环</a>';
				}	
			},
			{width : '100',title : '有无质量问题',field : 'hasProblem',sortable : true}
			]], 
		columns : [ [
			{width : '100',title : '问题分类',field : 'problemType'},
			{width : '100',title : '问题描述',field : 'problemDesc'},
			{width : '160',title : '拼装日期',field : 'fixDate',sortable : true},
			{width : '160',title : '上报日期',field : 'sbDate',sortable : true}, 
			{width : '100',title : '上报人',field : 'upUserName'},
			{width : '100',title : '联系方式',field : 'linkWay'},
			{width : '100',title : '处理状态',field : 'status',sortable : true},
			{width : '150',title : '备注',field : 'remark'}
			
			] ],
			
		onLoadSuccess : function(data) {},
		toolbar: '#toolbar' 
	});
	
	
	$('#addDialog').css('visibility','visible'); 
	$('#editDialog').css('visibility','visible'); 
	$('#showDialog').css('visibility','visible'); 
	
});

//质量问题:是否有质量问题判断 */
//var addHasProblem;
function add_hasProblemEvent(){
	
	var hasProblem = $('#add_hasProblem').children('option:selected').val();//这就是selected的值
	if(hasProblem=="有"){
		$("#add_hasProblem_table").prepend(addHasProblem);
		$('#status').combobox('select', '未处理');
	} 
	else if(hasProblem=="无"){
		addLine=$("#add_hasProblem_tr").detach();
		//alet("addHasProblem="+addHasProblem);
    }
	
}

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
	$('#add_hasProblem').combobox('select', '无');

	/**
	 *获取工程信息
	 *
	 */
	$.ajax({
		url : path+'/getProDic',
		dataType : 'json',
		success : function(jsonstr) {
			jsonstr.unshift({
				'id' : '',
				'proName' : '-请选择-'
			}); //向json数组开头添加自定义数据  

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
			        	$('#add_sectionId').combobox('reload', path+"/getProSectionDic?proId="+newValue.id);
			      }
			});
		}
	});
	
	/**
	 *获取工程区间信息
	 *
	 */
	$.ajax({
		url : path+'/getProSectionDic',
		dataType : 'json',
		success : function(jsonstr) {
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
			        	$('#add_lineId').combobox('reload', path+"/getProLineDic?sectionId="+newValue.id);
                     	$("#add_cycleNo").val("");
			      }
			});

		}
	});

	/**
	 *获取工程线路信息
	 *
	 */
	$.ajax({
		url : path+'/getProLineDic',
		dataType : 'json',
		success : function(jsonstr) {
			$("#add_lineId").combobox({
				data : jsonstr,
				valueField : "id",
				textField : "lineName",
				onLoadSuccess : function() {
					var val = $(this).combobox('getData');
					for (var item in val[0]) {
						if (item == 'id') {
							$(this).combobox('select', val[0][item]);
						}
					}
				},
                onSelect:function(newValue,oldValue){
                    getHHByXlId(newValue.id);
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
		$("#editDialog").dialog('open').dialog('setTitle', '编辑［'+moduleName+"］"+"-环号：第"+rows[0][nameField]+"环");
		$('#editForm').form('load', rows[0]);
		
		/**
		 *获取工程信息
		 *
		 */
		$.ajax({
			url : path+'/getProDic',
			dataType : 'json',
			success : function(jsonstr) {
				jsonstr.unshift({
					'id' : '',
					'proName' : '-请选择-'
				}); //向json数组开头添加自定义数据  
				$("#edit_proId").combobox({
					data : jsonstr,
					valueField : "id",
					textField : "proName",
					onLoadSuccess : function() {
						$(this).combobox('select', rows[0]["proName"]);
						
					},
					 onSelect:function(newValue,oldValue){
				        	$('#edit_sectionId').combobox("clear");
				        	$('#edit_sectionId').combobox('reload', path+"/getProSectionDic?proId="+newValue.id);
				      }
				});
			
			}
		});
		
		/**
		 *获取工程区间信息
		 *
		 */
		$.ajax({
			url : path+'/getProSectionDic',
			dataType : 'json',
			success : function(jsonstr) {
				$("#edit_sectionId").combobox({
					data : jsonstr,
					valueField : "id",
					textField : "sectionName",
					onLoadSuccess : function() {
						$(this).combobox('select', rows[0]["section"]);
					},
					 onSelect:function(newValue,oldValue){
				        	$('#edit_lineId').combobox("clear");
				        	$('#edit_lineId').combobox('reload', path+"/getProLineDic?sectionId="+newValue.id);
	                     	//$("#edit_cycleNo").val("");

				      }
				});
			}
		});

		/**
		 *获取工程线路信息
		 *
		 */
		$.ajax({
			url : path+'/getProLineDic',
			dataType : 'json',
			success : function(jsonstr) {
				$("#edit_lineId").combobox({
					data : jsonstr,
					valueField : "id",
					textField : "lineName",
					onLoadSuccess : function() {
						$(this).combobox('select', rows[0]["line"]);
					},
	                onSelect:function(newValue,oldValue){
	                   // getHHByXlId(newValue.id);
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
		$("#showDialog").dialog('open').dialog('setTitle', '查看［'+moduleName+"］"+"-环号：第"+row[nameField]+"环");
		
		$('#showForm').form('load', row);
		/**
		 *获取工程信息
		 *
		 */
		$.ajax({
			url : path+'/getProDic',
			dataType : 'json',
			success : function(jsonstr) {
				jsonstr.unshift({
					'id' : '',
					'proName' : '-请选择-'
				}); //向json数组开头添加自定义数据  
				$("#show_proId").combobox({
					data : jsonstr,
					valueField : "id",
					textField : "proName",
					onLoadSuccess : function() {
						$(this).combobox('select', row["proName"]);
						
					},
					 onSelect:function(newValue,oldValue){
				        	$('#show_sectionId').combobox("clear");
				        	$('#show_sectionId').combobox('reload', path+"/getProSectionDic?proId="+newValue.id);
				      }
				});
			
			}
		});
		
		/**
		 *获取工程区间信息
		 *
		 */
		$.ajax({
			url : path+'/getProSectionDic',
			dataType : 'json',
			success : function(jsonstr) {
				$("#show_sectionId").combobox({
					data : jsonstr,
					valueField : "id",
					textField : "sectionName",
					onLoadSuccess : function() {
						$(this).combobox('select', row["section"]);
					},
					 onSelect:function(newValue,oldValue){
				        	$('#show_lineId').combobox("clear");
				        	$('#show_lineId').combobox('reload', path+"/getProLineDic?sectionId="+newValue.id);
	                     	//$("#edit_cycleNo").val("");

				      }
				});
			}
		});

		/**
		 *获取工程线路信息
		 *
		 */
		$.ajax({
			url : path+'/getProLineDic',
			dataType : 'json',
			success : function(jsonstr) {
				$("#show_lineId").combobox({
					data : jsonstr,
					valueField : "id",
					textField : "lineName",
					onLoadSuccess : function() {
						$(this).combobox('select', row["line"]);
					},
	                onSelect:function(newValue,oldValue){
	                   // getHHByXlId(newValue.id);
	                }
				});
		
			}
		});
		_showAllFileInDiv(id,'img_div_project','图片附件'); 
		//disableForm('showForm',true);
	}
}

/**
 * 表单查询
 */
function searchFun() {
	var upDateStart = $("#searchForm input[name='upDateStart']").val();//开始时间的值
	var upDateEnd = $("#searchForm input[name='upDateEnd']").val();//结束时间的值
	
 	var boo = upDateStart <= upDateEnd;      //开始时间小于结束时间
    if( boo==true || upDateStart=="" || upDateEnd =="" ){
		   dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
    }else{
		$.messager.alert('错误','开始时间不能大于结束时间！', 'error');
	}
	
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
 * 上传图片
 * wl_wpg
 * @returns
 */
function UploadPicture(){     
	
	/*var userType  = _getCurrOrgzType();
	if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}*/
	var rows = dataGrid.datagrid('getSelections');
	if (rows.length > 1) {
		$.messager.alert('提示', '请选择单条数据！', 'info');
		return;
	}
	else if (rows.length == 0) {
		$.messager.alert('提示', '请选择数据！', 'info');
		return;
	}
	
	_uploadPicture(rows[0].id,syspath,'图片链接',dataGrid,'现场照片 ');

}

//获取环号
function getHHByXlId(xlId){
    $("#add_cycleNo").textbox('setValue', "");
    $("#edit_cycleNo").textbox('setValue', "");
    $("#show_cycleNo").textbox('setValue', "");
    $.ajax({
        url : path+'/getHHByXlId?xlId='+xlId,
        dataType : 'json',
        success : function(jsonstr) {
           
			if (jsonstr.success) {
				$("#add_cycleNo").textbox('setValue', jsonstr.obj.jjhh);
	            $("#edit_cycleNo").textbox('setValue', jsonstr.obj.jjhh);
	            $("#show_cycleNo").textbox('setValue', jsonstr.obj.jjhh);
			}
			else {
				$.messager.alert('提示', jsonstr.msg, 'info');
			}
        }
    });
}

/**
 * 数据同步
 */
function dataSynchronous() {
    $.ajax({
        url : path+'/dataSynchronous',
        dataType : 'json',
        success : function(jsonstr) {
            $.messager.alert('提示', jsonstr.msg, 'info');
        }
    });
}