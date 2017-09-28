/**
 * 
 */
/** 系统模块同路径 */
 
var syspath = "gczlys";
/** 业务数据访问全路径 */
var path = basePath +"/"+ syspath +"/yanShouSearch";
/** 菜单名称 */
var entityCnName = "验收影像资料查询";
/**
 * 模块名称
 */
var moduleName = "工程质量验收";
var nameField = "miaoshu";
/**若无一条勾选数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayZero = ["copy_button_YanShouSearch","edit_button_YanShouSearch","cancelSelect_button_YanShouSearch","uploadPic_button_yxzl","del_button_YanShouSearch","show_button_YanShouSearch","print_button_YanShouSearch"];
/**若勾选大于一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayMoreOneItem =["copy_button_YanShouSearch","edit_button_YanShouSearch","show_button_YanShouSearch","uploadPic_button_yxzl","print_button_YanShouSearch"];
var hiddenClumArrayOnlyOneItem = [];

/** 列表对象 */
var yxyscx_dataGrid;
$(function() {
	/** 创建数据表格	 */
	yxyscx_dataGrid = $('#dataGrid_YanShouSearch').datagrid({
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
		frozenColumns:[[
		   {width: '100',title: 'id',field: 'id',align:'center', checkbox:true},    
           {width: '200',title: '工程名称',field: 'projectname',
			   formatter: function(value,row,index){
					return DataGridFormatter.tipsFormatter(value,30);
				}
           },
           {width: '100',title: '工程部位',field: 'gcbuwei'},
           
           ]],
   	    columns : [ [
           {width: '120',title: '分布分项工序名称',field: 'gongxu'},
           {width: '100',title: '施工班组',field: 'banzu'},
           {width: '100',title: '班组负责人',field: 'bzfuzr'},
           {width: '150',title: '验收时间',field: 'yanshousj'},
           {width: '100',title: '验收人',field: 'yanshour'},
           {width: '100',title: '天气',field: 'tianqi', hidden:true},
           {width: '180',title: '现场检查实际情况',field: 'miaoshu',
        	   formatter: function(value,row,index){
        		   return '<a href="#" onclick="showSearchFun(\''+ row.id +'\', \'' + index + '\')">' + DataGridFormatter.tipsFormatter(value,30) + '</a>';
//   				return '<a href="#" onclick="showFun(\''+ row.id +'\')">' + value + '</a>';
   			}
           },
           
           {width: '200',title: '检查验收意见和结论',field: 'jianchaqk',
        	   formatter: function(value,row,index){
					return DataGridFormatter.tipsFormatter(value,30);
				}
           }
           
           
           
		]],
		
		toolbar : '#toolbar',
		onLoadSuccess : function(data) {
			hiddenButtonBase("dataGrid_YanShouSearch",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
		},
        onCheck : function(){
        	hiddenButtonBase("dataGrid_YanShouSearch",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onUncheck : function(){
        	hiddenButtonBase("dataGrid_YanShouSearch",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onCheckAll : function(){
        	hiddenButtonBase("dataGrid_YanShouSearch",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onUncheckAll : function(){
        	hiddenButtonBase("dataGrid_YanShouSearch",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onClickRow:function(){
        	hiddenButtonBase("dataGrid_YanShouSearch",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        }
	});
	$('#searchForm_YanShouSearch').show();  
});

/**
 * 查看数据页面
 */
function showSearchFun(currid,index) { 
	
	var id;
	var row;
	if(currid){
		id = currid;
		var allrows = yxyscx_dataGrid.datagrid('getRows');
		row = allrows[index];
	}else{
		var rows = yxyscx_dataGrid.datagrid('getSelections');
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
		 
		$("#showDialog_ForSearch").dialog('open').dialog('setTitle', '查看［'+moduleName+"］"+"-"+row[nameField]);  
		$('#showDialog_ForSearch').form('load', row);
		
		_showAllFileInDiv2(row.id,'img_div_yxYszlYsSearch');
	}
}


/**
 * 打印页面
 */
function toPrintOut(currid,index) { 
	
	var id;
	var row;
	if(currid){
		id = currid;
		var allrows = yxyscx_dataGrid.datagrid('getRows');
		row = allrows[index];
	}else{
		var rows = yxyscx_dataGrid.datagrid('getSelections');
		if (rows.length > 1) {
			$.messager.alert('提示', '请选择单条数据进行打印！', 'info');
			return;
		}
		else if (rows.length == 0) {
			$.messager.alert('提示', '请选择数据后再打印！', 'info');
			return;
		}
		if (rows[0]) {
			row = rows[0];
			id = rows[0].id;
		}
		else {
			$.messager.alert('提示', '请选择数据后再打印！', 'info');
			return;
		}	
	}
	if (row) { 
		 
		$("#printDialog_ForSearch").dialog('open').dialog('setTitle', '打印［'+moduleName+"］"+"-"+row[nameField]);  
		$('#printDialog_ForSearch').form('load', row);
		$('#print_projectName').html(row['projectname']);
		var printTianqi;
		if("1"== row['tianqi']){
			printTianqi="晴";
		}
		if("2"== row['tianqi']){
			printTianqi="阴";
		}
		if("3"== row['tianqi']){
			printTianqi="雨";
		}
		if("4"== row['tianqi']){
			printTianqi="雪";
		} 
		$('#print_Tianqi').html(printTianqi);
		$('#print_jcrqsj').html(row['yanshousj']);
		$('#print_ysr').html(row['yanshour']);
		$('#print_gcbw').html(row['gcbuwei']);
		$('#print_gxmc').html(row['gongxu']);
		$('#print_sgbz').html(row['banzu']);
		$('#print_bzfzr').html(row['bzfuzr']);
		$('#print_xcjcqk').html(row['miaoshu']);
		$('#print_ysjl').html(row['jianchaqk']);
		//_showAllFileInDiv(row.id,'img_div_yxYszlYsPrint'); 
	}
}

/*
 * 重置
 * */
function cleandYanShouSearch() {
	$('#searchForm_YanShouSearch input').val('');
	yxys_dataGrid.datagrid('load', {});
}
/*
 * 刷新
 * */
function reloadYanShouSearch(){ 
	yxyscx_dataGrid.datagrid('reload');
}
/*
 * 查询
 * */
function searchYanShouSearchFun(){  
	yxyscx_dataGrid.datagrid('load', $.serializeObject($('#searchForm_YanShouSearch')));
}
/*
 * 打印
 * */
function printOut() {
	var  printDiv  = $('#printDialog_ForSearch').html();
	/*window.document.body.innerHTML = printDiv; 
	window.print(); */
	$("#printTable").printArea(printDiv);
	//alert(printDiv);printDialog_ForSearch
} 

 