/**
 * 风险管理系统 级别管理模块
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
var module = "level";
/**
 * 模块名称
 */
var moduleName = "风险级别";
/**
 * 名称字段
 */
var nameField = "levelName";
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
	 *获取工程信息
	 *
	 */
	$('#search_proName').combobox({
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
		sortName : 'sort',
		sortOrder : 'asc',
		pageSize : 15,
		pageList : [ 10, 15, 20, 50 ],
		frozenColumns : [ [
			{field : 'ck',checkbox : true},
			{width : '140',title : '风险级别名称',field : 'levelName',
				formatter: function(value,row,index){
                    if(value!="" && value!=undefined){ 
                        var valueSplit = value.split(",");
                        var  tmp_value= valueSplit[0].length>20?value.substring(0,19)+'...':valueSplit[0];
                        var tmpstr="查看风险级别";
						return '<a href="#" style="color:black" title="'+tmpstr+'" onclick="showClick(\''+ row.id +'\', \'' + index + '\')">'+tmp_value+'</a>';
					}
				}
			},
			]],
		columns : [ [
			{width : '120',title : '颜色标识',field : 'colorFlag',
				styler: function (value, row, index) { 
		              return 'background-color:'+value+';color:black';
		           }	 
			},  
			{width : '70',title : '排序号',field : 'sort',sortable : true},
			{width : '660',title : '说明',field : 'riskDesc'}
			
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
	$("#add_proId").combobox({
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
			var colorFlag = $("#text-field").val();
			if (!isValid ) {
				progressClose();
				return false;
			}
			if(colorFlag==""){
				progressClose();
				$.messager.alert('提示', '颜色标识不能为空、请选择颜色！', 'info');
				return false;
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
		FormUtil.clearForm($("#editDialog"));
		
		$('#editForm').form('load', rows[0]);
		//$(".minicolors-swatch .minicolors-swatch-color").css("background-color",rows[0].colorFlag); 
		$("#text-field-edit").minicolors();
		var input = $("#text-field-edit");
		
		//选中 已有的 颜色值
		updateFromInput(input,rows[0].colorFlag);
		 
		$("#edit_proId").combobox({
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
		});
		
	}
}
function hex2hsb(hex) {
	var hsb = rgb2hsb(hex2rgb(hex));
	if( hsb.s === 0 ) hsb.h = 360;
	return hsb;
}
//Converts a hex string to an RGB object
function hex2rgb(hex) {
	hex = parseInt(((hex.indexOf('#') > -1) ? hex.substring(1) : hex), 16);
	return {
		r: hex >> 16,
		g: (hex & 0x00FF00) >> 8,
		b: (hex & 0x0000FF)
	};
}
function rgb2hsb(rgb) {
	var hsb = { h: 0, s: 0, b: 0 };
	var min = Math.min(rgb.r, rgb.g, rgb.b);
	var max = Math.max(rgb.r, rgb.g, rgb.b);
	var delta = max - min;
	hsb.b = max;
	hsb.s = max !== 0 ? 255 * delta / max : 0;
	if( hsb.s !== 0 ) {
		if( rgb.r === max ) {
			hsb.h = (rgb.g - rgb.b) / delta;
		} else if( rgb.g === max ) {
			hsb.h = 2 + (rgb.b - rgb.r) / delta;
		} else {
			hsb.h = 4 + (rgb.r - rgb.g) / delta;
		}
	} else {
		hsb.h = -1;
	}
	hsb.h *= 60;
	if( hsb.h < 0 ) {
		hsb.h += 360;
	}
	hsb.s *= 100/255;
	hsb.b *= 100/255;
	return hsb;
}
function updateFromInput(input,colorFlag) {
	
	var hex,
		hsb,
		opacity,
		x, y, r, 
		
		// Helpful references
		minicolors = input.parent(),
		//settings = input.data('minicolors-settings'),
		swatch = minicolors.find('.minicolors-swatch'),
		
		// Panel objects
		grid = minicolors.find('.minicolors-grid'),
		slider = minicolors.find('.minicolors-slider'),
		//opacitySlider = minicolors.find('.minicolors-opacity-slider'),
		
		// Picker objects
		gridPicker = grid.find('[class$=-picker]'),
		sliderPicker = slider.find('[class$=-picker]');
		//opacityPicker = opacitySlider.find('[class$=-picker]');
	
	// Determine hex/HSB values
	hex = colorFlag;
	
	hsb = hex2hsb(hex);
	
	input.val(hex);
	
	
	// Update swatch
	swatch.find('SPAN').css('backgroundColor', hex);
	
	// Determine picker locations
	x = keepWithin(Math.ceil(hsb.s / (100 / grid.width())), 0, grid.width());
	y = keepWithin(grid.height() - Math.ceil(hsb.b / (100 / grid.height())), 0, grid.height());
	gridPicker.css({
		top: y + 'px',
		left: x + 'px'
	});
	
	// Set slider position
	y = keepWithin(slider.height() - (hsb.h / (360 / slider.height())), 0, slider.height());
	sliderPicker.css('top', y + 'px');
	
	
	grid.css('backgroundColor', hex );
	
	
}

//Keeps value within min and max
function keepWithin(value, min, max) {
	if( value < min ) value = min;
	if( value > max ) value = max;
	return value;
}



//Converts an HSB object to a hex string
function hsb2hex(hsb) {
	return rgb2hex(hsb2rgb(hsb));
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
			var colorFlag = $("#text-field-edit").val();
			if (!isValid || colorFlag=="") {
				progressClose();
				return false;
			}
			if(colorFlag==""){
				progressClose();
				$.messager.alert('提示', '颜色标识不能为空、请选择颜色！', 'info');
				return false;
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
		$("#showDialog").dialog('open').dialog('setTitle', '查看［'+moduleName+"］"+"-"+rows[0][nameField]);
		FormUtil.clearForm($("#showDialog"));
		
		$('#showForm').form('load', rows[0]);
		//$(".minicolors-swatch .minicolors-swatch-color").css("background-color",rows[0].colorFlag); 
		$("#text-field-show").minicolors();
		var input = $("#text-field-show");
		
		//选中 已有的 颜色值
		updateFromInput(input,rows[0].colorFlag);
		 
		$("#edit_proId").combobox({
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
		});
	}
}

function showClick(oid,index) {
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
	$("#showDialog").dialog('open').dialog('setTitle', '查看风险级别');
	FormUtil.clearForm($("#showDialog"));	
	$('#showForm').form('load', row);
	$("#text-field-show").minicolors();
	var input = $("#text-field-show");
	
	//选中 已有的 颜色值
	updateFromInput(input,row.colorFlag);
	 
	$("#edit_proId").combobox({
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