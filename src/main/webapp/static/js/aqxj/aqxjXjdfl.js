/**
 * 
 */
/** 系统模块同路径 */
var syspath = "aqxj";
/** 业务数据访问全路径 */
var path = basePath +"/"+ syspath +"/aqxjXjdfl";

/** 名称字段 */
var nameField = "fenleiMc";

/** 菜单名称 */
var entityCnName = "巡检点分类";

/**
 * 模块名称
 */
var moduleName = "巡检点分类";

/**若无一条勾选数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayZero = ["edit_button_aqxjXjdfl","cancelSelect_button_aqxjXjdfl","del_button_aqxjXjdfl"];
/**若勾选大于一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayMoreOneItem =["edit_button_aqxjXjdfl","del_button_aqxjXjdfl","add_button_aqxjXjdfl"];
var hiddenClumArrayOnlyOneItem = [];
/** 列表对象 */
var dataGrid;
$(function() {
	/** 创建数据表格	 */
	dataGrid = $('#dataGrid_aqxjXjdfl').treegrid({
  		url : path+'/treeGrid',
		striped : true,
		rownumbers : true,
		singleSelect : false,
		checkOnSelect : true,
		selectOnCheck : true,
		pagination : true,
		idField : 'id',
        treeField : 'fenleiMc',
        parentField : 'pid',
		pageSize : 15,
		pageList : [ 10, 15, 20, 50 ],
		frozenColumns:[[
           {field:'id',align:'center',title:'ID',width:40, checkbox:true},
           {width: '310',title: '工程名称',field: 'gcMc'},
           {width: '280',title: '分类名称',field: 'fenleiMc'},         
           {width: '280',title: '备注',field: 'beiZ',
				formatter: function(value,row,index){
					return DataGridFormatter.tipsFormatter(value,20);
				}
			}
		]],
		toolbar : '#toolbar',
			onLoadSuccess : function(data) {
				hiddenButtonBase("dataGrid_aqxjXjdfl",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
			},
	        onCheck : function(){
	        	hiddenButtonBase("dataGrid_aqxjXjdfl",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
	        },
	        onUncheck : function(){
	        	hiddenButtonBase("dataGrid_aqxjXjdfl",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
	        },
	        onCheckAll : function(){
	        	hiddenButtonBase("dataGrid_aqxjXjdfl",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
	        },
	        onUncheckAll : function(){
	        	hiddenButtonBase("dataGrid_aqxjXjdfl",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
	        },
	        onClickRow:function(){
	        	hiddenButtonBase("dataGrid_aqxjXjdfl",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
	        }
		
	});
	createProjectSelect("gcName_query_id",false,"search");

	/**
	 *获取工程信息
	 *
	 */
/*	$('#search_gcMc').combobox({
		url : basePath+'/project/section/getProDic',
		valueField : "proName",
		textField : "proName",
		onLoadSuccess : function() {
			var val = $(this).combobox('getData');
			for (var item in val[0]) {
				if (item == 'id') {
					$(this).combobox('select', val[0][item]);
				}
			}
		}
	});*/
	
	
	
	
});


/**
 * 构建巡检分类下拉框
 * @param id 页面显示id
 * @param required 是否必填 true/false
 * @param oparate add/edit/search/show  用于构建其他下拉框时使用
 * @returns
 */
/*function createSgNrSelect(id,required,oparate){
	$("#"+id).combobox({
		required : required,
		valueField:'sgNr',
		textField:'sgNr',
		editable:true,
		url:basePath +"/"+ syspath +"/"+ module+"/gczlYdxjSGZLSgnr/createSgNrSelect",
		onChange:function(newValue,oldValue){
			createJtwzSelect(oparate+"_jtWz_gczlYdxjSGZLXJInfo",newValue,required);
			createZlqxSelect(oparate+"_zlQx_gczlYdxjSGZLXJInfo",newValue,required);
		}
	});
	点击文本框内，就显示下拉面板
	$("#"+id).combobox('textbox').click(function(){		
		$("#"+id).combobox('showPanel');
	});
}*/


/*
 * 分类名称树下拉框
 * */
/*function create_FlMc_Select_gl(showId,required,oparate){
	var row = dataGrid.treegrid('getSelected');
	//alert(rows);
	//var gcmc=rows[0].id;
	$("#"+showId).combotree({
		url :basePath+'/aqxj/aqxjXjdfl/getFl',
	    multiple: false,
	    required: required,
	    panelHeight : 'auto'
	    queryParams: {
			"gcMc" :rows[0].gcMc
		}
  
		onChange:function(newValue,oldValue){
			createDwgcSelect(oparate+"_dwgcBh_gczlYdxjSGZLXJInfo",newValue,required,oparate)
	        createFbgcSelect(oparate+"_fbGcbh_gczlYdxjSGZLXJInfo","-1",false);//构建分部工程下拉框
		}
	});
	点击文本框内，就显示下拉面板
	$("#"+showId).combotree('textbox').click(function(){		
		$("#"+showId).combotree('showPanel');
	});
}*/


/** 【巡检点管理】打开新增页面 */
/*function addAqxjXjdflFun(){
	
	var userType  = _getCurrOrgzType();
	if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}
	
	FormUtil.clearForm($("#addDialog_AqxjXjdfl"));
	$("#addDialog_AqxjXjdfl").dialog('open').dialog('setTitle', '添加［'+entityCnName+"］");
	var rows = dataGrid.treegrid('getSelections');
	if(rows.length != 0){
	id = rows[0].id;
	pname=rows[0].fenleiMc;
	gcmc=rows[0].gcMc;
	$("#add_pid_aqxjXjdfl").val(id);
	//$("#add_pid_mc_aqxjXjdfl").val(id);
	$('#add_pName_aqxjXjdfl').textbox('setValue',pname);
	$('#search_gcMc').combobox('setValue',gcmc);
	$('#search_gcMc').combobox('readonly');
	
	}	
}
*/
/**
 * 数据添加
 */
function addAqxjXjdflFun() {
	FormUtil.clearForm($("#addDialog_AqxjXjdfl"));
	$("#addDialog_AqxjXjdfl").dialog('open').dialog('setTitle', '添加［'+entityCnName+"］");
	var rows = dataGrid.treegrid('getSelections');
	if(rows.length != 0){
		id = rows[0].id;
		pname=rows[0].fenleiMc;
		gcmc=rows[0].gcMc;
		$("#add_pid_aqxjXjdfl").val(id);
		//$("#add_pid_mc_aqxjXjdfl").val(id);
		$('#add_pName_aqxjXjdfl').textbox('setValue',pname);
		$('#search_gcMc').combobox('setValue',gcmc);
		$('#search_gcMc').combobox('readonly');
//		$('#add_pName_aqxjXjdfl').combobox('readonly');
		$('#add_pName_aqxjXjdfl').combo('readonly', true);
		//$('#search_gcMc').combobox('disable').combobox('textbox').prev().hide();
		//$('#add_pName_aqxjXjdfl').combobox('disable').combobox('textbox').prev().hide();
	}else{
		//$('#add_pName_aqxjXjdfl').combo('readonly', false);
		create_FlMc_Select_gl("add_pName_aqxjXjdfl", "search_gcMc",false,"search");
		
	}

}
//创建分类名称关联工程下拉框
function create_FlMc_Select_gl(flshowId, gcshowId,required,oparate){

	//FormUtil.clearForm($("#addDialog_AqxjXjdfl"));
//	$('#'+gcshowId).combobox('readonly',false);
//	alert(11);
//	$('#'+flshowId).combobox('readonly',false);
//	alert(22);
	$('#'+gcshowId).combobox({
		url : basePath+'/project/section/getProDic',
		valueField : "proName",
		textField : "proName",
		onLoadSuccess : function() {
			$('#'+gcshowId).combobox('readonly',false);
		},
		onSelect:function(newValue){
			$('#'+flshowId).combotree("clear");
	    	$('#'+flshowId).combotree('reload', basePath+"/aqxj/aqxjXjdfl/getFl?gcmc="+newValue.proName);  
		}
	});
	$('#'+flshowId).combotree({
		url : basePath+'/aqxj/aqxjXjdfl/getFl',
		lines : true,
		valueField : "fenleiMc",
		textField : "fenleiMc",
		panelHeight : 'auto',
		onLoadSuccess : function() {
			$('#'+flshowId).combobox('readonly',false);
		},
		onSelect:function(newValue){
			//var flName=$("#add_pName_aqxjXjdfl").combobox("getText");
			$('#add_pid_aqxjXjdfl').val(newValue.id);
		}
		
	});

	
}


/** 【巡检点分类管理】数据保存 */
function addAqxjXjdflAjax() {

	$('#addForm_AqxjXjdfl').form('submit', {		
		url : path+'/add',	
		onSubmit : function() {
			progressLoad();
			//表单验证
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
				$.messager.show({title:'提示',msg:'新增操作：成功!',showType:'show' });
				dataGrid.treegrid('reload');
				$('#search_gcMc').combobox('readonly',false);
				//$('#add_pName_aqxjXjdfl').combobox('readonly',false);
				dataGrid.treegrid('clearSelections');
				$("#addDialog_AqxjXjdfl").dialog('close');
				//$("#addDialog_AqxjXjdfl" ).panel('refresh' );
				FormUtil.clearForm($("#addDialog_AqxjXjdfl"));
				
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}


/** 【施工内容管理】打开数据编辑页面 */
function editAqxjXjdflFun() {
	var userType  = _getCurrOrgzType();
	/*if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}*/
	var id;
	var rows = dataGrid.treegrid('getSelections');
	if (rows.length > 1) {
		$.messager.alert('提示', '请选择单条数据进行编辑！', 'info');
		return;
	} else if (rows.length == 1) {
		if (rows[0]) {
			id = rows[0].id;
			
		}else {
			$.messager.alert('提示', '请选择数据后再编辑！', 'info');
			return;
		}
	} else {
		$.messager.alert('提示', '请选择数据后再编辑！', 'info');
		return;
	}
	if (rows[0]) {
		
		//打开编辑页面  选择 上级下拉列表框 更新加载
		/* $('#edit_aqxjXjdfl').combotree({
		        url :path+'/tree?flag=false',
		        parentField : 'pid',
		        lines : true,
		        panelWidth:280,
		        panelHeight :350
		    });*/
		$("#editDialog_AqxjXjdfl").dialog('open').dialog('setTitle', '编辑［'+entityCnName+'］'+'-'+rows[0][nameField]);
		$('#editForm_aqxjXjdfl').form('load', rows[0]);
	}
	
	
	$.post(
			path+'/childSelect', 
			{  id : rows[0].id  }, 
			function(result) {
				result = $.parseJSON(result);
				if(result.success){
					$('#edit_search_pro_Id').combobox('readonly');
					$('#edit_pName_aqxjXjdfl').combobox('readonly');
					//dataGrid.treegrid('clearSelections');
				}else{

					$('#edit_search_pro_Id').combobox({
						url : basePath+'/project/section/getProDic',
						valueField : "proName",
						textField : "proName",
						onLoadSuccess : function() {
							$('#'+gcshowId).combobox('readonly',false);
						},
						onSelect:function(newValue){
							//$('#edit_pName_aqxjXjdfl').combotree("clear");
					    	$('#edit_pName_aqxjXjdfl').combotree('reload', basePath+"/aqxj/aqxjXjdfl/getFl?gcmc="+newValue.proName);  
						}
					});
					$('#edit_pName_aqxjXjdfl').combotree({
						url : basePath+'/aqxj/aqxjXjdfl/getFl',
						lines : true,
						valueField : "fenleiMc",
						textField : "fenleiMc",
						panelHeight : 'auto',
						onLoadSuccess : function() {
							$('#edit_pName_aqxjXjdfl').combobox('readonly',false);
						},
						onSelect:function(newValue){
							//var flName=$("#add_pName_aqxjXjdfl").combobox("getText");
							$('#edit_pid_aqxjXjdfl').val(newValue.id);
						}
						
					});
					//createProjectSelect("edit_search_pro_Id",false,"search");
					//dataGrid.treegrid('clearSelections');
				}
				
			})
	
	/*if(rows[0].pid!=null||child=="childNode"){
		alert(child);
		$('#edit_search_pro_Id').combobox('readonly');
	}else if(rows[0].pid==null){
		$.post(path+'/childSelect', 
				{  id : rows[0].id  }, 
				function(result) {
					if(result){
						$('#edit_search_pro_Id').combobox('readonly');
					}
					
				})
		
	}else{
		alert(child);
		alert(2);
		createProjectSelect("edit_search_pro_Id",false,"search");
	}*/
}


/** 【施工内容管理】提交修改 */
function editAqxjXjdflAjax() {
	var isValid = $('#editForm_aqxjXjdfl').form('validate');
	if (!isValid) return;
	$.messager.confirm('提示', '您确定要编辑［'+entityCnName + '］吗？', function(b) {
		if (b) {
			$('#editForm_aqxjXjdfl').form('submit', {
				url : path+'/edit',
				onSubmit : function() {
				},
				success : function(result) {
					progressClose();
					result = $.parseJSON(result);
					if (result.success) {
				        $.messager.show({title:'提示',msg:'修改操作：成功!',showType:'show' });
				        dataGrid.treegrid('reload');
						$("#editDialog_AqxjXjdfl").dialog('close');
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
 * 展开
 */
function expand(){
	var node = dataGrid.treegrid('getSelected');
	if(node){
		dataGrid.treegrid('expand', node.id);
	}else{
		dataGrid.treegrid('expandAll');
	}
}


/**
 * 折叠
 */
function collapse(){
	var node = dataGrid.treegrid('getSelected');
	if(node){
		dataGrid.treegrid('collapse', node.id);
	}else{
		dataGrid.treegrid('collapseAll');
	}
}

/** 【施工内容管理】数据删除 *//*
function deleteAqxjXjdflFun() {
	//var userType  = _getCurrOrgzType();
	if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}
	var ids = new Array();
	var rows = dataGrid.datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择数据后再删除！', 'info');
		return;
	}
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].id);
	}
	$.messager.confirm('询问', '您是否要删除［'+entityCnName + '］所选行吗？', function(b) {
		if (b) {
			progressLoad();
			$.post(path+'/delete', {
				ids : ids.join(",")
			}, function(result) {
				if (result.success) {
					$.messager.show({title:'提示',msg:'删除操作：成功!',showType:'show' });
					dataGrid.treegrid('reload');
					dataGrid.datagrid('clearSelections');
				}
				progressClose();
			}, 'JSON');
		}
	});
}*/

/**
 * 数据删除
 */
function deleteAqxjXjdflFun() {
	var node = dataGrid.treegrid('getSelected');
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
					   
					   
					   $.messager.show({title:'提示',msg:'删除操作：成功!',showType:'show' });
					  // $.messager.alert('提示', result.msg, 'info');
						dataGrid.treegrid('reload');
						dataGrid.treegrid('clearSelections');
					   /***
					    * result.msg
					    * 1、删除成功！
					    * 2、deleteOrgzTree 
					    * 
					    */
					   
					   
					  /*if(result.msg =="deleteAqxjTree"){
							$.messager.confirm('询问', '您要删除的 '+moduleName + '［'+node[nameField]+'］，有子部门，是否要删除'+moduleName + '［'+node[nameField]+'］，以及它的子部门'+'？', function(b) {
								if (b) {
									
									$.post(path+'/deleteTree', {
										id : node.id
									}, function(result) {
										if (result.success) {
												$.messager.alert('提示', result.msg, 'info');
												//用旧地址 重新加载 地区列表
												dataGrid.treegrid('reload');	
										}
											
									}, 'JSON');
								}
							});
						}
						else
						{
							//用旧地址 重新加载 地区列表
							$.messager.alert('提示', result.msg, 'info');
							dataGrid.treegrid('reload');
							dataGrid.treegrid('clearSelections');
					  }*/
					
				    //页面加载加载进度条关闭
					progressClose();
				}else {
					$.messager.alert('错误', result.msg, 'error');
					dataGrid.treegrid('reload');
					//页面加载加载进度条关闭
					progressClose();
				}
				
			}, 'JSON');
		}
  });
}
/**
 * 构建所属工程下拉框
 * @param showId  页面显示id
 * @param required 是否必填 true/false
 * @param oparate    add/edit/search/show  用于构建其他下拉框时使用
 * @returns
 */
function createProjectSelect(showId,required,oparate){
	$("#"+showId).combobox({
		required : required,
		valueField:'proName',
		textField:'proName',
		url:basePath +"/project/info/getProjects"
		/*onChange:function(newValue,oldValue){
			createDwgcSelect(oparate+"_dwgcBh_gczlYdxjSGZLXJInfo",newValue,required,oparate)
	        createFbgcSelect(oparate+"_fbGcbh_gczlYdxjSGZLXJInfo","-1",false);//构建分部工程下拉框
		}*/
	});
	/*点击文本框内，就显示下拉面板*/
	$("#"+showId).combobox('textbox').click(function(){		
		$("#"+showId).combobox('showPanel');
	});
}


/** 表单查询 */
function searchAqxjXjdflFun() {
	dataGrid.treegrid('load', $.serializeObject($('#searchForm_AqxjXjdfl')));
}


/**表单重置 */
function cleanAqxjXjdflFun() {
	$('#searchForm_AqxjXjdfl input').val('');
	dataGrid.treegrid('load', {});
}

/**表单重置 *//*
function cleanAqxjXjdflFun() {
	$('#searchForm_AqxjXjdfl input').val('');
	dataGrid.datagrid('load', {});
}*/

/**
 * 取消已选
 * @returns
 */
function clearAqxjXjdflSelections(){
	dataGrid.treegrid('clearSelections');
}

/*
 * 刷新
 * */
function reloadAqxjXjdfl(){
	dataGrid.treegrid('reload');
}


