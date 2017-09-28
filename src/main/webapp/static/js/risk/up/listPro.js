/**
 * 风险上报管理模块
 * 
 * 创建时间：2017年04月17日 
 * 
 */

/**
 * 系统模块同路径
 */
var syspath = "risk";
/**
 * 子模块路径
 */
var module = "up";
/**
 * 模块名称
 */
var moduleName = "风险上报";
/**
 * 名称字段
 */
var nameField = "proName";
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
		sortName : 'UP_TIME',
		sortOrder : 'desc',
		pageSize : 15,
		queryParams : $.serializeObject($('#searchForm')),
		pageList : [ 10, 15, 20, 50, 500],
		frozenColumns : [ [ 
			{field : 'ck',checkbox : true}, 
			{width : '80',title : '片区分类',field : 'section',sortable : true },
			{width : '320',title : '项目部名称',field : 'proName',sortable : true},
			{width : '100',title : '风险级别',field : 'rikeLevelStr',
				formatter: function(value,row,index){
                    if(value!="" && value!=undefined){
                        var valueSplit = value.split(",");
						return '' + (valueSplit.length>1?valueSplit[0]:"") + '';
					}else{
						return '' + value+ '';	
					}
				},
				styler: function (value, row, index) { 
					if(value!="" && value!=undefined){
						var valueSplit = value.split(",");
						return 'background-color:'+(valueSplit.length>1?valueSplit[1]:"")+';color:black';
					}else{
						return '' + value+ '';	
					} 
					
		           }		
			}
			]],
		columns : [ [
			{width : '150',title : '风险级别',field : 'rikeLevel', hidden:true},
			{width : '260',title : '风险描述',field : 'rikeDesc',
				formatter: function(value,row,index){
                    if(value!="" && value!=undefined){ 
                        var valueSplit = value.split(",");
                        var  tmp_value= valueSplit[0].length>20?value.substring(0,19)+'...':valueSplit[0];
                        var tmpstr=valueSplit[0]+"\r\n"+row.mainControlMethod;
						return '<a href="#" style="color:black" title="'+tmpstr+'" onclick="showFun(\''+ row.id +'\', \'' + index + '\')">'+tmp_value+'</a>';
					}   
				}},
			{width : '100',title : '上报人',field : 'upUserName',sortable : true},
			{width : '100',title : '上报人联系方式',field : 'upUserPhone',sortable : true},
			{width : '100',title : '填报时间',field : 'upTime',sortable : true},
			{width : '110',title : '监控开始时间',field : 'rikeTimeStart',sortable : true},
            {width : '110',title : '监控结束时间',field : 'rikeTimeEnd',sortable : true},
			{width : '100',title : '主要管控措施',field : 'mainControlMethod',sortable : true,hidden:true},
			{width : '100',title : '状态',field : 'isOut',
				formatter: function(value,row,index){
					if(value==0){ 
						return '未排除';
					}else{
						return '已排除';
					}
				}
			},
			{width : '100',title : '备注',field : 'remark',sortable : true,hidden:true},
			{width : '300',title : '负责部门',field : 'dptsStr',sortable : true},
			{width : '300',title : '负责部门',field : 'dpts',hidden:true},
			{width : '100',title : '负责人',field : 'persoonStr',sortable : true},
			{width : '100',title : '负责人',field : 'persoon',hidden:true}
			
			] ],
			
		onLoadSuccess : function(data) {},
		toolbar: '#toolbar' 
	});
});

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
	$('#add_proName').combobox({
		url : path+'/getProDic',
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
		onChange:function(){
			var proId= $('#add_proName').combobox('getValue');
			//部门
			$('#add_dpts').combotree({
		        //url :basePath+'/sys/orgz/tree',
		    	url :path+'/orgz_tree?proId='+proId,
		        multiple: true,
		        required: true,
		        panelWidth:300,
		        panelHeight:400
		    });
			//负责人
			$('#add_persoon').combotree({
		    	//url :basePath+'/sys/user/getUserTree',
				url :path+'/getUserTree?proId='+proId,
		        multiple: true,
		        required: true,
		        onlyLeafCheck:true,
		        panelWidth:300,
		        panelHeight:400
		    });
			
		}
	
	});
	
	$('#add_riskLevel').combobox({
		url : basePath+'/risk/level/getRiskLevelList',
		valueField : "id",
		textField : "levelName",
		onLoadSuccess : function() {
			var val = $(this).combobox('getData');
			for (var item in val[0]) {
				if (item == 'id') {
					$(this).combobox('select', val[0][item]);
				}
			}
		}
	});
	
	$("#add_section").combobox({                 
        url: basePath+'/project/pq/list',
        valueField:'id',
        textField:'pqName'
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
		$("#editDialog").dialog('open').dialog('setTitle', '编辑［'+moduleName+"］"+"-"+rows[0][nameField]);
		$('#editForm').form('load', rows[0]);
		
		$('#edit_proName').combobox({
			url : path+'/getProDic',
			valueField : "id",
			textField : "proName",
			onLoadSuccess: function (data) {
				
				for (var i = 0; i < data.length; i++) {
					if ( rows[0].proName == data[i].proName) {
						$(this).combobox('select', data[i].id);
					}
				}
	        },
			onChange:function(){
				var proId= $('#edit_proName').combobox('getValue');
				//部门
				$('#edit_dpts').combotree({
			    	//url :basePath+'/sys/orgz/tree',
					url :path+'/orgz_tree?proId='+proId,
			        multiple: true,
			        required: true,
			        panelWidth:300,
			        panelHeight:400,
			        onLoadSuccess: function (node, data) {
			        	$("#edit_dpts").combotree('setValues', rows[0].dpts);
			        }
			    });
				//负责人
				$('#edit_persoon').combotree({
			    	//url :basePath+'/sys/user/getUserTree',
					url :path+'/getUserTree?proId='+proId,
			        multiple: true,
			        required: true,
			        onlyLeafCheck:true,
			        panelWidth:300,
			        panelHeight:400,
			        onLoadSuccess: function (node, data) {
			        	$("#edit_persoon").combotree('setValues', rows[0].persoon);
			        }
			    });
			}
		});
		$('#edit_riskLevel').combobox({
			url : basePath+'/risk/level/getRiskLevelList',
			valueField : "id",
			textField : "levelName",
			onLoadSuccess : function() {
				var val = $(this).combobox('getData');
				for (var item in val[0]) {
					if (item == 'id') {
					}
				}
			}
		});
		
		
		$("#edit_section").combobox({                 
	        url: basePath+'/project/pq/list',
	        valueField:'id',
	        textField:'pqName'
	    });
		/*$("#edit_proId").combobox({
			url : path+'/getProDic',
			valueField : "id",
			textField : "proName",
			onLoadSuccess : function() {
				var val = $(this).combobox('getData');
				for (var item in val[0]) {
					if (item == 'id') {
						$(this).combobox('select', rows[0].proId);
					}
				}
			}
		});*/
		
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
	
    parent.$.modalDialog({
        title : '查看'+moduleName + '［'+row[nameField]+'］',
        width : 720,
        height : 430,
        href : path+'/showPage?id='+id,
        buttons : [ {
            text : '确定',
            handler : function() {
            	parent.$.modalDialog.handler.dialog('close');
            }
        } ]
    });
}

//消息推送
function pushMessage(oid,index) {



    var ids = new Array();
    var rows = dataGrid.datagrid('getSelections');
    if (rows.length == 0) {
        $.messager.alert('提示', '请选择数据后再推送！', 'info');
        return;
    }
    for(var i=0; i<rows.length; i++){
        ids.push(rows[i].id);
    }


    $.messager.confirm('询问', '您是否要推送［'+moduleName + '］所选行吗？', function(b) {
        if (b) {
            progressLoad();
            $.post(path+'/pushMessage', {
                ids : ids.join(",")
            }, function(result) {
                if (result.success) {
                    $.messager.show({title:'提示',msg:result.msg,showType:'show' });
                }else{
                    $.messager.alert('提示',result.msg, 'info');
                }
                progressClose();
            }, 'JSON');
        }
    });

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
	
	_uploadPicture(rows[0].id,syspath,'上传风险上报备注',dataGrid,'现场照片 ');

}

/**
* 获取本周、本季度、本月、上月的开始日期、结束日期
*/
var now = new Date(); //当前日期 
var nowDayOfWeek = now.getDay(); //今天本周的第几天 
var nowDay = now.getDate(); //当前日 
var nowMonth = now.getMonth(); //当前月 
var nowYear = now.getYear(); //当前年 
nowYear += (nowYear < 2000) ? 1900 : 0; //

var lastMonthDate = new Date(); //上月日期
lastMonthDate.setDate(1);
lastMonthDate.setMonth(lastMonthDate.getMonth()-1);
var lastYear = lastMonthDate.getYear();
var lastMonth = lastMonthDate.getMonth();

//格式化日期：yyyy-MM-dd 
function formatDate(date) { 
var myyear = date.getFullYear(); 
var mymonth = date.getMonth()+1; 
var myweekday = date.getDate(); 

if(mymonth < 10){ 
mymonth = "0" + mymonth; 
} 
if(myweekday < 10){ 
myweekday = "0" + myweekday; 
} 
return (myyear+"-"+mymonth + "-" + myweekday); 
} 

//获得某月的天数 
function getMonthDays(myMonth){ 
var monthStartDate = new Date(nowYear, myMonth, 1); 
var monthEndDate = new Date(nowYear, myMonth + 1, 1); 
var days = (monthEndDate - monthStartDate)/(1000 * 60 * 60 * 24); 
return days; 
} 

//获得本季度的开始月份 
function getQuarterStartMonth(){ 
var quarterStartMonth = 0; 
if(nowMonth<3){ 
quarterStartMonth = 0; 
} 
if(2<nowMonth && nowMonth<6){ 
quarterStartMonth = 3; 
} 
if(5<nowMonth && nowMonth<9){ 
quarterStartMonth = 6; 
} 
if(nowMonth>8){ 
quarterStartMonth = 9; 
} 
return quarterStartMonth; 
} 

//获得本周的开始日期 
function getWeekStartDate() { 
var weekStartDate = new Date(nowYear, nowMonth, nowDay - nowDayOfWeek); 
return formatDate(weekStartDate); 
} 

//获得本周的结束日期 
function getWeekEndDate() { 
var weekEndDate = new Date(nowYear, nowMonth, nowDay + (6 - nowDayOfWeek)); 
return formatDate(weekEndDate); 
} 

//获得本月的开始日期 
function getMonthStartDate(){ 
var monthStartDate = new Date(nowYear, nowMonth, 1); 
return formatDate(monthStartDate); 
} 

//获得本月的结束日期 
function getMonthEndDate(){ 
var monthEndDate = new Date(nowYear, nowMonth, getMonthDays(nowMonth)); 
return formatDate(monthEndDate); 
}

//获得上月开始时间
function getLastMonthStartDate(){
var lastMonthStartDate = new Date(nowYear, lastMonth, 1);
return formatDate(lastMonthStartDate); 
}

//获得上月结束时间
function getLastMonthEndDate(){
var lastMonthEndDate = new Date(nowYear, lastMonth, getMonthDays(lastMonth));
return formatDate(lastMonthEndDate); 
}

//获得本季度的开始日期 
function getQuarterStartDate(){ 

var quarterStartDate = new Date(nowYear, getQuarterStartMonth(), 1); 
return formatDate(quarterStartDate); 
} 

//或的本季度的结束日期 
function getQuarterEndDate(){ 
var quarterEndMonth = getQuarterStartMonth() + 2; 
var quarterStartDate = new Date(nowYear, quarterEndMonth, getMonthDays(quarterEndMonth)); 
return formatDate(quarterStartDate); 
}

//默认获取当前日期范围（七天）
function myformatter1(date) {
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    var d = date.getDate()-7;

    return y + '-' + (m < 10 ? ('0' + m) : m) + '-'
            + (d < 10 ? ('0' + d) : d);
}
function myformatter2(date) {
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    var d = date.getDate();

    return y + '-' + (m < 10 ? ('0' + m) : m) + '-'
            + (d < 10 ? ('0' + d) : d);
}
//得到当前日期
formatterDate = function(date,type) {
var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
+ (date.getMonth() + 1);

if(type=='start'){
return date.getFullYear() + '-' + month + day;
}
if(type=='end'){
return date.getFullYear() + '-' + month + '-' + day;
}


};
