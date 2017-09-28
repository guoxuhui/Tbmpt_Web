/** 系统模块同路径 */
var syspath = "aqxj";
/** 子模块路径 */
var module = "xjdgl";
/** 菜单名称 */
var entityCnName = "安全巡检点";
var entityCnName1="巡检内容"
	/**
	 * 模块名称
	 */
	var moduleName = "巡检点管理";
/** 名称字段 */
var nameField = "mingCheng";
/** 业务数据访问全路径 */
var path = basePath +"/"+ syspath +"/"+ module+"/aqxjXjd";
/**若无一条勾选数据，需屏蔽按钮ID的数组集合*//*
var hiddenClumArrayZero = ["edit_bj_aqxjXjd","cancelSelect_bj_aqxjXjd","del_sc_aqxjXjd","edit_AqxjXjnr"];
*//**若勾选大于一条数据，需屏蔽按钮ID的数组集合*//*
var hiddenClumArrayMoreOneItem =["edit_bj_aqxjXjd","edit_AqxjXjnr"];
var hiddenClumArrayOnlyOneItem = [];*/
/** 列表对象 */
var dataGrid_aqxjXjxxyy;
$(function() {
	/** 创建数据表格	 */
	dataGrid_aqxjXjxxyy = $('#aqxjXjxxyy_dataGrid').datagrid({
		url : path+'/dataGrid',
		striped : true,
		rownumbers : true,
		singleSelect : false,
		checkOnSelect : true,
		selectOnCheck : true,
		pagination : true,
		idField : 'id',
		pageSize : 15,
		pageList : [ 10, 15, 20, 50 ],
		columns:[[
           {field:'id',align:'center',title:'ID',width:40, checkbox:true},
           {width: '280',title: '工程项目名称',field: 'projectName'},
           {width: '120',title: '巡检点名称',field: 'mingCheng'},
           {width: '120',title: '检查点所在位置',field: 'address'},
           {width: '60',title: '顺序号',field: 'xuHao'},
           {width: '80',title: '责任人',field: 'zeRenrmc'},
           {width: '80',title: '监督人',field: 'jianDurmc'},
           {width: '80',title: '检查频次',field: 'jianChapc'},
           {width: '110',title: '检查点分类',field: 'typeName'},
           {width: '90',title: '备注',field: 'beiZhu'}
		]],
		onLoadSuccess : function(data) {},
		toolbar : '#toolbar',
		/*onLoadSuccess : function(data) {
			hiddenButtonBase("dataGrid_aqxjXjd",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
		},
        onCheck : function(){
        	hiddenButtonBase("dataGrid_aqxjXjd",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onUncheck : function(){
        	hiddenButtonBase("dataGrid_aqxjXjd",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onCheckAll : function(){
        	hiddenButtonBase("dataGrid_aqxjXjd",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onUncheckAll : function(){
        	hiddenButtonBase("dataGrid_aqxjXjd",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onClickRow:function(){
        	hiddenButtonBase("dataGrid_aqxjXjd",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        }*/
	});
	
	/**
	 *获取项目信息
	 *
	 */
	/*$('#search_proName').combobox({
		url : basePath+'/project/section/getProDic',
		valueField : "proName",
		textField : "proName",
		onLoadSuccess : function() {
			var val = $(this).combobox('getData');
			for (var item in val[0]) {
				if (item == 'proName') {
					$(this).combobox('select', val[0][item]);
				}
			}
		}
	});*/
	
	//工程下拉框
	$('#search_proName').combobox({
		url : basePath+'/project/section/getAllProDic',
		valueField : "proName",
		textField : "proName",
		onLoadSuccess : function() {
			$('#search_proName').combobox('readonly',false);
		},
		onSelect:function(newValue){
			//$('#search_fenleiMcid').combotree("clear");
	    	//$('#search_fenleiMcid').combotree('reload', basePath+"/aqxj/aqxjXjdfl/getFl?gcmc="+newValue.proName);
			orgzTree = $('#fengleiMc_Tree').tree({
				url : basePath+"/aqxj/aqxjXjdfl/getFl?gcmc="+newValue.proName,
				parentField : 'pid',
				lines : true,
				onClick : function(node) {
					$("#search_fenleiMc").val(node.text);
					dataGrid_aqxjXjxxyy.datagrid('load', $.serializeObject($('#showProForm')));
					/*dataGrid.datagrid('load', {
				        proId: node.id
				    });*/
				}
				});
		},
		onChange:function(){
			document.getElementById("search_fenleiMc").value="";
			dataGrid_aqxjXjxxyy.datagrid('load', $.serializeObject($('#showProForm')));
		}
	});
});


//表单查询
function search_xxyyFun() {
	dataGrid_aqxjXjxxyy.datagrid('load', $.serializeObject($('#search_cxForm')));
}


/*表单重置 */
function clean_xxyyFun() {
	$('#search_cxForm input').val('');
	dataGrid_aqxjXjxxyy.datagrid('load', {});
}

//复制工程弹出框
function copyProject() {
	var userType  = _getCurrOrgzType();
	/*if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}*/
	var proName=$("#search_proName").combobox("getText");
	if (proName!=null&&proName!="") {
		$("#copyProject_showDialog").dialog('open').dialog('setTitle', '复制工程');
		//var gcmingcheng=$("#search_gcMingcheng").textbox("getValue");
		var prjName = $("#search_proName").combobox('getText');
		document.getElementById("projec_tName_aqxjXjxxyy").innerHTML = prjName;
		document.getElementById("projec_Name_aqxjXjxxyy").innerHTML = prjName;
	} else{
			$.messager.alert('提示', '请选择工程进行复制！', 'info');
			return;
		//$('#copyForm_YanShouYingXiang').form('load', rows[0]);
		//createProjectSelect("copy_gcid_YanShouYingXiang",true);
		
	}
}

/** 工程复制保存 */
function copyproject_save() {
	var prjName = $("#search_proName").combobox('getText');
	//progressLoad();
	$.post(basePath+'/aqxj/aqxjXjdfl/copyPro', {
		proName : prjName
	}, function(result) {
		if (result.success) {
			if(result.msg =="noCopy"){
				$.messager.alert('提示','当前工程已有分类，请选择其他的复制方案！','info');
				$("#copyProject_showDialog").dialog('close');
			}else{
				//复制巡检点请求
				$.post(basePath+'/aqxj/aqxjXjdfl/copyProXjd', {
					proName : prjName
				}, function(result) {
					if (result.success) {
						if(result.msg!=null){
							$.messager.alert('提示', '巡检点'+result.msg+'已存在！', 'info');
						}
					}
					//复制巡检内容
					$.post(basePath+'/aqxj/aqxjXjdfl/copyProXjnr', {
						proName : prjName
					}, function(result) {
						if (result.success) {
							if(result.msg!=null){
								$.messager.alert('提示', '巡检内容'+result.msg+'已存在！', 'info');
							}
						}
					}, 'JSON');
					
				}, 'JSON');
				$.messager.show({title:'提示',msg:'复制操作：成功!',showType:'show' });
				$("#copyProject_showDialog").dialog('close');
				//dataGrid_aqxjXjxxyy.datagrid('reload');
			}
		}
		//progressClose();
	}, 'JSON');

}


//复制分类弹出框
function copyProjectFl() {
	var userType  = _getCurrOrgzType();
	/*if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}*/
	//需要复制的工程名称
	var proName=$("#search_proName").combobox("getText");
	//当前工程名称
	var currProName=document.getElementById("curr_projec_Name_aqxjXjxxyy").innerHTML;
	if (proName!=null&&proName!="") {
		$("#copyFl_showDialog").dialog('open').dialog('setTitle', '复制分类');
		//复制分类下拉框
		$("#copy_getTypeName_aqxjXjxxyy").combotree({
			url : basePath+"/aqxj/aqxjXjdfl/getFl?gcmc="+proName,
			//parentField : 'pid',
			lines : true,
			valueField : 'typeName',
			textField : 'typeName',
			panelHeight : 'auto',
			onLoadSuccess : function() {

			},
			onChange:function(){
				/*//$("#add_typeId_aqxjXjd").combobox("clear");
				var flName=$("#add_typeId_aqxjXjd").combobox("getText");
				var prName = document.getElementById("add_projec_tName_aqxjXjd").innerHTML;
				//$("#add_typeId_aqxjXjd").combobox("clear");
				$('#add_typeName_aqxjXjd').val(flName);
				//$('#add_projectName_aqxjXjd').val(prName);
*/			}
		});
		
		$("#copy_inTypeName_aqxjXjxxyy").combotree({
			url : basePath+"/aqxj/aqxjXjdfl/getFl?gcmc="+currProName,
			//parentField : 'pid',
			lines : true,
			valueField : 'typeName',
			textField : 'typeName',
			panelHeight : 'auto',
			onLoadSuccess : function() {

			},
			onChange:function(){
				/*//$("#add_typeId_aqxjXjd").combobox("clear");
				var flName=$("#add_typeId_aqxjXjd").combobox("getText");
				var prName = document.getElementById("add_projec_tName_aqxjXjd").innerHTML;
				//$("#add_typeId_aqxjXjd").combobox("clear");
				$('#add_typeName_aqxjXjd').val(flName);
				//$('#add_projectName_aqxjXjd').val(prName);
*/			}
		});
		
		/*$('#checkBox_aqxjXjxxyy').click(function(){
			 //判断是否被选中
			 var bischecked=$('#checkBox_aqxjXjxxyy').is(':checked');
			 alert(bischecked);
			 if(bischecked){
				 $("#isCheck_button").val("checked");
			 }
			 });*/
	} else{
			$.messager.alert('提示', '请选择工程进行复制！', 'info');
			return;
	}
}


//复制分类保存
function save_copyFl(){
	//需要复制的工程名称
	var proName=$("#search_proName").combobox("getText");
	//当前工程名称
	var currProName=document.getElementById("curr_projec_Name_aqxjXjxxyy").innerHTML;
	/*var check=$("#isCheck_button").text();
	alert(check);*/
	 var bischecked=$('#checkBox_aqxjXjxxyy').is(':checked');
	var fengL=$("#copy_getTypeName_aqxjXjxxyy").combobox("getText");
	var currFengL=$("#copy_inTypeName_aqxjXjxxyy").combobox("getText");
	var currId=$("#copy_inTypeName_aqxjXjxxyy").combobox("getValue");
	var id=$("#copy_getTypeName_aqxjXjxxyy").combobox("getValue");
	//复制分类请求
	$.post(basePath+'/aqxj/aqxjXjdfl/copyfengL', {
		currProName:currProName, fengL:fengL, currFengL:currFengL, id:id, currId:currId
	}, function(result) {
		if (result.success) {
			if(result.msg =="noCopy"){
				$.messager.alert('提示','当前分类已存在，请选择其他的复制方案！','info');
				$("#copyProject_showDialog").dialog('close');
			}else{
				if(bischecked){
				//复制巡检点请求
				$.post(basePath+'/aqxj/aqxjXjdfl/copyFlXjd', {
					id:id, currId:currId, currProName:currProName,fengL:fengL
				}, function(result) {
					if (result.success) {
						if(result.msg!="noCopy"){
							/*$.messager.alert('提示', '当前巡检点已存在！', 'info');*/
						}
					}
					//复制巡检内容
					$.post(basePath+'/aqxj/aqxjXjdfl/copyFlXjnr', {
						id:id, currId:currId, currProName:currProName
					}, function(result) {
						if (result.success) {
							if(result.msg!=null){
								/*$.messager.alert('提示', '巡检内容'+result.msg+'已存在！', 'info');*/
							}
						}
					}, 'JSON');
					
				}, 'JSON');
			}
				$.messager.show({title:'提示',msg:'复制操作：成功!',showType:'show' });
				$("#copyFl_showDialog").dialog('close');
				//dataGrid_aqxjXjxxyy.datagrid('reload');
				
			}
		}
		//progressClose();
	}, 'JSON');
}

//复制巡检点弹出框
function copyProjectXjd() {
	//当前工程名称
	var currProName=document.getElementById("curr_projec_Name_aqxjXjxxyy").innerHTML;
	var userType  = _getCurrOrgzType();
	var rows = dataGrid_aqxjXjxxyy.datagrid('getSelections');
	/*if(userType =='0'){
		//机关单位
		$.messager.alert('提示', '仅当前项目部人员可操作！', 'info');
		return;
	}*/
	var proName=$("#search_proName").combobox("getText");
	if (proName!=null&&proName!="") {
		if (rows.length == 0) {
			$.messager.alert('提示', '请选择数据后再复制！', 'info');
			return;
		}
		$("#copyXjd_showDialog").dialog('open').dialog('setTitle', '复制巡检点');
		//分类下拉框
		$("#copy_inId_aqxjXjxxyy").combotree({
			url : basePath+"/aqxj/aqxjXjdfl/getFl?gcmc="+currProName,
			lines : true,
			valueField : 'typeName',
			textField : 'typeName',
			panelHeight : 'auto',
			onLoadSuccess : function() {

			},
			onChange:function(){
				
		}
		});

	} else{
			$.messager.alert('提示', '请选择工程进行复制！', 'info');
			return;
		//$('#copyForm_YanShouYingXiang').form('load', rows[0]);
		//createProjectSelect("copy_gcid_YanShouYingXiang",true);
		
	}
}


//复制巡检点保存
function save_copyXjd(){
	var currFengL=$("#copy_inId_aqxjXjxxyy").combobox("getText");
	var currProName=document.getElementById("curr_projec_Name_aqxjXjxxyy").innerHTML;
	var currId=$("#copy_inId_aqxjXjxxyy").combobox("getValue");
	var ids = new Array();
	var rows = dataGrid_aqxjXjxxyy.datagrid('getSelections');
	/*if (rows.length == 0) {
		$.messager.alert('提示', '请选择数据后再复制！', 'info');
		return;
	}*/
	for(var i=0; i<rows.length; i++){
		ids.push(rows[i].id);
	}
			progressLoad();
	$.post(path+'/copyXjdXjd', {
			ids : ids.join(","), currId: currId, currProName:currProName,currFengL,currFengL//将数组元素存入一个字符串中，分隔符来分隔数组中的元素
		}, function(result) {
				progressClose();
				if (result.success) {
					if(result.msg!=null){
						$.messager.alert('提示', '巡检点'+result.msg+'已存在！', 'info');
					}
					alert(2);
					//复制巡检内容
					$.post(basePath+'/aqxj/xjdgl/aqxjXjd/copyXjdXjnr', {
						ids : ids.join(","), currId: currId
					}, function(result) {
						if (result.success) {
							if(result.msg!=null){
								$.messager.alert('提示', '巡检内容'+result.msg+'已存在！', 'info');
							}
						}
					}, 'JSON');
					
					
					$.messager.show({title:'提示',msg:'复制操作：成功!',showType:'show' });
					$("#copyXjd_showDialog").dialog('close');
					dataGrid_aqxjXjxxyy.datagrid('reload');
					dataGrid_aqxjXjxxyy.datagrid('clearSelections');
					
				}
			}, 'JSON');
}























