
/**
 * 系统模块同路径
 */
var syspath = "dgjjdw";
/**
 * 子模块路径
 */
var module = "tbmdw";
/**
 * 模块名称
 */
var moduleName = "盾构掘进对比信息";

/**
 * 业务数据访问全路径
 */
var path = basePath +"/"+ syspath +"/"+ module;

/**
 * 列表对象
 */
var dataGrid;
var editRow = undefined;
var tbmcode;
$(function() {
	$('#searchForm').form('clear');
	
	/**
	 * 对比结果保存，发送到后台
	 */
	$('#addForm_tbmdw').form({
		url : path+'/save',
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
        	parent.$.messager.show({title:'提示',msg:result.msg,showType:'show' });               
        	$("#addDialog_tbmdw").dialog('close');
        	dataGrid.datagrid('reload');
        } else {
            parent.$.messager.alert('错误', result.msg, 'error');
        }
    }
});
	
	
	/**
	 * 创建数据表格
	 *
	 */
	dataGrid = $('#dataGrid').datagrid({
		url : path+'/tbmmgDataGrid',
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
			{width : '100',title : '是否比对',field : 'ifbd',sortable : true,
			formatter: function(value,row,index){
				var data={tbmid:row.id};
				var bd=false;
				$.ajax({
			        url: path+"/ifExists",              //请求地址
			        type: "GET",                       //请求方式
			        data: data,        //请求参数
			        async: false,
			        dataType: "json",
			        success: function (result) {
			        	var a=false;
			        	for(var i in result){
			    			a=true;
			    			break;
			    		}
			            if(a){
			            	bd=true;
			            }
			        }				
				});
				if(bd){
					return "已对比";
				}else{
					return "未对比";
				}
				
			}
			},
			{width : '100',title : '盾构机编号',field : 'tbmCode',sortable : true,
				formatter: function(value,row,index){
					return '<a href="#" onclick="showFun(\''+ row.id +'\', \'' + index + '\')">' + value + '</a>';
				}
			}, 
            {width : '200',title : '盾构机名称',field : 'tbmName',sortable : true,align:'left'}
		    ]],
		columns : [ [
            {width : '80',title : '管理号',field : 'manageno'}, 
            {width : '100',title : '负责人名称',field : 'functionaryName'},
            {width : '100',title : '联系方式',field : 'contactNumber'},
            {width : '200',title : '生产厂商',field : 'manufacturer'}, 
            {width : '120',title : '出厂编号',field : 'factorynumber'}, 
            {width : '120',title : '规格',field : 'specification'}, 
            {width : '120',title : '型号',field : 'model'}, 
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
            {width : '200',title : '出厂日期',field : 'factorydate'}, 
            {width : '280',title : '资产归属',field : 'tbmVest'}, 
            {width : '120',title : '购置日期',field : 'buydate' }, 
            {width : '60',title : '状态',field : 'tbmState',sortable : true,
                formatter : function(value, row, index) {
                    switch (value) {
                    case 0:
                        return '未分配';
                    case 1:
                        return '已分配';
                    }
                }
            }
			] ], 
		onLoadSuccess : function(data) {},
		toolbar :'#toolbar'
	});
			
	/**
	 * 创建数据表格
	 *
	 */
//	dataGrid = $('#dataGrid').datagrid({
//		url : path+'/dataGrid',
//		striped : true,
//		rownumbers : false,
//		singleSelect : false,
//		checkOnSelect : true,
//		selectOnCheck : true,
//		pagination : true,
//		idField : 'id',
//		sortName : 'id',
//		sortOrder : 'desc',
//		pageSize : 15,
//		pageList : [ 10, 15, 20, 50 ],
//		columns:[[
//		                
//			{field : 'ck',checkbox : true},
//			//{width : '150',title : '盾构机ID',field : 'tbmid',sortable : true}, 
//			{width : '150',title : '盾构机CODE',field : 'tbmcode'},
//			{width : '150',title : 'PLC点位名称',field : 'tagname'}, 
//			{width : '150',title : '标准ID',field : 'dwid'},
//			{width : '150',title : '标准名称',field : 'dwname'},
//			{width : '150',title : '标准类型',field : 'dwlx'}
//		     ]],
//		
//		onLoadSuccess : function() {},
//		toolbar :'#toolbar'
//	});
	
	
	dataGrid_tbmdw = $('#dataGrid_tbmdw').datagrid({
		url : path+'/tbmdwDataGrid',
		striped : true,
		rownumbers : true,
		singleSelect : true,
		checkOnSelect : true,
		selectOnCheck : true,
		pagination : false,
		idField : 'id',
		sortName : 'id',
		sortOrder : 'desc',
		pageSize : 15,
		pageList : [ 10, 15, 20, 50 ],
		columns:[[
		                
			{field : 'ck',checkbox : true},
			{width : '150',title : '标准ID',field : 'dwid'},
			{width : '150',title : '标准名称',field : 'dwname'},
			{width : '200',title : 'PLC点位名称',field : 'tagname',
				editor: {  
					type: 'combogrid',  
					options: {                                 
					},
					
					required: true  
				}
			}, 
			{width : '80',title : '标准类型',field : 'dwlx'}
		     ]],		     
			onBeforeLoad: function (param) {  
	                var firstLoad = $(this).attr("firstLoad");  
	                if (firstLoad == "false" || typeof (firstLoad) == "undefined")  
	                {  
	                    $(this).attr("firstLoad","true");  
	                    return false;  
	                }  
	                return true;  
		            }, 
		     onLoadSuccess:function(data){
 	        },
 	        onDblClickRow: function (rowIndex, rowData) {
		               if (editRow != undefined) {
		                   $("#dataGrid_tbmdw").datagrid('endEdit', editRow);
		                   editRow = undefined;
		               }
		               if (editRow == undefined) {
		                   $("#dataGrid_tbmdw").datagrid('beginEdit', rowIndex);
		                   editRow = rowIndex;
		               }		               
		           	var ed=new Array();
		            ed = $('#dataGrid_tbmdw').datagrid('getEditor', {index:rowIndex,field:'tagname'});
		            tbmcode=$("#add_ss_tbm_name").html();
		        	tbmName=$("#ss_tbmName").val();
		        	$(ed.target).combogrid({ 		
		        		panelWidth:500,
		        	    url: path+'/tagname',
		        	    queryParams : {tbmcode:tbmcode},
		        	    idField:'id',
		        	    textField:'tagname',
		        	    editable:true,
		        	    mode:'remote',
		        	    fitColumns:true,
		        	    columns:[[
		        	        {field:'tbmcode',title:'盾构机CODE',width:60},
		        	        {field:'tagname',title:'工业库点位名称',align:'centre',width:80},
		        	        {field:'tagtype',title:'工业库点位数据类型',align:'centre',width:60,
		        	        	formatter: function(value,row,index){
		        	        		switch (value) {
		                            case '1':
		                                return 'Boolean';
		                            case '4':
		                                return 'Int';
		                            case '10':
		                            	return 'Float';
		                            }		        					
		        				}
		        	        },
		        	        		        
		        	        {field:'tagtime',title:'时间',align:'centre',width:60}	        
		        	    ]]
		        		});		         	
		           },
		           onClickRow: function (rowIndex, rowData) {
		               if (editRow != undefined) {
		                   $("#dataGrid_tbmdw").datagrid('endEdit', editRow);
		    
		               }
		           },
		           onAfterEdit: function (rowIndex, rowData, changes) {
		               editRow = undefined;		               		        
		               $('#dataGrid_tbmdw').datagrid('refreshRow', rowIndex);
		           },
		           toolbar:[
		        	   {text : '重新比对',iconCls: 'icon-reload',
					    handler: function(){
					    	$.messager.confirm('询问', '您是否要重新比对,重新比对将清空当前界面的数据重新生成', function(b) {
					    		if (b) {
					    			var tbmCode=$('#add_bz_tbm_id').html();
					    			var data={tbmid:tbmCode};
					    			$.ajax({
					    		        url: path+"/ifExists",              //请求地址
					    		        type: "GET",                       //请求方式
					    		        data: data,        //请求参数
					    		        dataType: "json",
					    		        success: function (result) {
					    		        	var a=false;
					    		        	for(var i in result){
					    		    			a=true;
					    		    		}
					    		            if(a){
					    		            	//已经存在对比信息，则取出加载到数据列表中					    		            	 
					    		            	dataGrid_tbmdw.datagrid("loadData",result);
					    		            }
					    		        }
					    		    });
					    		}
					    	});
					    	
					    }},
					    '-',
					    {text: '取消已选',iconCls: 'icon-undo',
			    			handler: function(){
			    				dataGrid_tbmdw.datagrid('clearSelections');
			    			}
			    		}
					]
	});
	
});

function realAjax(){
	var ed=new Array();
    ed = $('#dataGrid_tbmdw').datagrid('getEditor', {index:editRow,field:'tagname'});
    tbmcode=$("#add_ss_tbm_name").html();
	tbmName=$("#ss_tbmName").val();
	$(ed.target).combogrid({ 		
//			url : path+'/tagname?tbmName='+tbmName+'&tbmcode='+tbmcode,
//			method: 'get',
//			valueField: 'id',
//			textField: 'tagname',
//         panelWidth:200,
//         panelHeight :200
		panelWidth:500,
	    url: path+'/tagname?tbmName='+tbmName+'&tbmcode='+tbmcode,
	    idField:'id',
	    textField:'tagname',
	    mode:'remote',
	    fitColumns:true,
	    columns:[[
	        {field:'tbmcode',title:'TBMCODE',width:60},
	        {field:'tagname',title:'TAGNAME',align:'right',width:80},
	        {field:'tagtype',title:'TAGTYPE',align:'right',width:60},
	        {field:'tagtime',title:'TAGTIME',align:'right',width:60}	        
	    ]]
		});
	$("#realDialog").dialog('close')
}
/**
 * 判断是否选择多条对比
 * @returns
 */
function openbiduiFun(){
	var rows = $('#dataGrid').datagrid('getSelections');
	if (rows.length > 1) {
		$.messager.alert('提示', '请选择单条数据进行比对！', 'info');
		return;
	}
	
	else if(rows.length==0){
		$.messager.alert('提示', '请选择数据后再比对！', 'info');
		return;
	}
	if (rows[0]) {
		biduiFun(rows[0].tbmCode,rows[0].tbmName,rows[0].id);
	}
}
/**
 * 打开比对对话框
 */
function biduiFun(tbmCode,tbmName,id){
	var data={tbmid:id};
	$.ajax({
        url: path+"/ifExists",              //请求地址
        type: "GET",                       //请求方式
        data: data,        //请求参数
        dataType: "json",
        success: function (result) {
        	var a=false;
        	for(var i in result){
    			a=true;
    		}
            if(a){
            	//已经存在对比信息，则取出加载到数据列表中
            	FormUtil.clearForm($("#addForm_tbmdw"));
            	$("#add_bz_tbm_name").html(tbmName);
        		$("#add_ss_tbm_name").html(result[0].tbmcode);
        		$("#add_bz_tbm_id").html(result[0].tbmid);
        		$("#add_bz_tbm_code").html(tbmCode);        		
            	$("#addDialog_tbmdw").dialog('open').dialog('setTitle', '比对'); 
            	dataGrid_tbmdw.datagrid("loadData",result);
            }else{
            	//没有对比信息则提示输入实时盾构机
            	FormUtil.clearForm($("#tbmDialog"));
            	$("#tbmDialog").dialog('open').dialog('setTitle', '比对［'+moduleName+"］");
            	$("#bz_tbm").textbox('setText',tbmCode);
            	$('#bz_tbm').textbox('readonly');
            }
        }
    });
}
/**
 * 提交盾构机信息
 */
function tbmAjax(){
		var row=$("#dataGrid").datagrid('getSelected');
		var a=$("#bz_tbm").textbox('getText');
		var b=$("#ss_tbm").textbox('getText');
		tbmcode=b;
		$("#tbmDialog").dialog('close');

		$("#addDialog_tbmdw").dialog('open').dialog('setTitle', '比对');
		$("#add_bz_tbm_name").html(row.tbmName);
		$("#add_ss_tbm_name").html(b);
		$("#add_bz_tbm_id").html(row.id);
		$("#add_bz_tbm_code").html(row.tbmCode);
		dataGrid_tbmdw.datagrid("load",{'bztbmname':a,'tbmcode':b});	
}

/**
 * 提交对比点位信息
 */
function saveTbmdw(){
	var detailsInfo=new Array();
  	var rows = $("#dataGrid_tbmdw").datagrid("getRows");
  	if (editRow != undefined) {
        $("#dataGrid_tbmdw").datagrid('endEdit', editRow);
    }
  	for(var i=0;i<rows.length;i++)	{
		var obj = new Object();
		obj.bztbmCode=$("#add_bz_tbm_code").html();
		obj.tbmid=rows[i].tbmid;
		obj.tbmcode=rows[i].tbmcode;
		obj.tagname=rows[i].tagname;
		obj.dwid=rows[i].dwid;
		obj.dwname=rows[i].dwname;
		obj.dwlx = rows[i].dwlx;		
		detailsInfo[i] = obj;
	}
	
	var json = JSON.stringify(detailsInfo);
	$('#details').val(json);
	var isValid = $('#addForm_tbmdw').form('validate')
	if(isValid){
    	$('#addForm_tbmdw').submit();
	}else{
		progressClose();
	}
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


///**
// * 查看数据页面
// */
//function showFun(oid,index) {
//	var id;
//	var row;
//	if(oid){
//		id = oid;
//		var allrows = dataGrid.datagrid('getRows');
//		row = allrows[index];
//	}else{
//		var rows = dataGrid.datagrid('getSelections');
//		if (rows.length > 1) {
//			$.messager.alert('提示', '请选择单条数据进行查看！', 'info');
//			return;
//		}
//		else if (rows.length == 0) {
//			$.messager.alert('提示', '请选择数据后再查看！', 'info');
//			return;
//		}
//		if (rows[0]) {
//			row = rows[0];
//			id = rows[0].id;
//		}
//		else {
//			$.messager.alert('提示', '请选择数据后再查看！', 'info');
//			return;
//		}	
//	}
//	if (row) {
//		$("#showDialog").dialog('open').dialog('setTitle', '查看［'+moduleName+"］");
//		$('#showDialog').form('load', row);
//	}
//}

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
 * 表单重置
 */
function cleanFun() {
	$('#searchForm input').val('');
	$('#searchForm').form('clear');
	dataGrid.datagrid('load', {});
}

function searchFun(){
	dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
}


/**
 * 查看盾构机详细页面
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
        title : '查看盾构机信息',
        width : 620,
        height : 520,
        href : basePath+'/tbmmg/info/showPage?id='+id,
        buttons : [ {
            text : '确定',
            handler : function() {
            	parent.$.modalDialog.handler.dialog('close');
            }
        } ]
    });
}


