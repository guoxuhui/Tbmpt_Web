<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!-- [扩展JS] -->

<script type="text/javascript" src="${staticPath }/static/buttonIfEnable.js" charset="utf-8"></script>
<script type="text/javascript">
var syspath = "project";
/**
 * 子模块路径
 */
var module = "info";
/**
 * 模块名称
 */
var moduleName = "工程";
/** 菜单名称 */
var entityCnName = "工程管理";
/**
 * 名称字段
 */
var nameField = "proName";
/**
 * 业务数据访问全路径
 */
var path = basePath +"/"+ syspath +"/"+ module;
var country = [{text:"中国"}];
var state = [{"id":-1,"text":"--请选择--"},{"id":0,"text":"未开工"},{"id":1,"text":"在建"},{"id":2,"text":"完工"}];
$(function(){
$('#add_proName').textbox();
$('#add_abbreviation').textbox();
$('#add_cjdw').textbox();
$('#add_jldw').textbox();
$('#add_jsdw').textbox();
$('#add_position').textbox();
$('#add_country').textbox();
$('#add_area').textbox();
$('#add_province').textbox();
$('#add_city').textbox();
$('#add_tunnellength').textbox();
$('#add_ringwidth').textbox();
$('#add_startdate').textbox();
$('#add_estimateenddate').textbox();
$('#add_line').textbox();
$('#add_htje').textbox();
$('#add_klwc').textbox();
$('#add_country').combobox({
	data : country,
	valueField : "text",
	textField : "text",
	onLoadSuccess : function() {
		var val = $(this).combobox('getData');
		for (var item in val[0]) {
			if (item == 'text') {
				//$(this).combobox('select', val[0][item]);
			}
		}
	 },
	 onSelect:function(newValue,oldValue){
        	$('#add_area').combobox("clear");
		 
      }
});

/**
 * 数据保存
 */
$('#addForm').form({
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
			$.modalDialog.handler.dialog('close'); 
            $.modalDialog.openner_dataGrid.datagrid('reload');
		}
		else {
			$.messager.alert('错误', result.msg, 'error');
		}
	}
});

$("#add_area").combobox({                 
    url: basePath+'/project/pq/list',
    valueField:'pqName',
    textField:'pqName'
        });

$("#add_proState").combobox({
	data : state,
	valueField : "id",
	textField : "text",
	onLoadSuccess : function() {
		var val = $(this).combobox('getData');
		for (var item in val[0]) {
			if (item == 'id') {
				$(this).combobox('select', val[0][item]);
			}
		}
	}
});

$("#add_parentId").combotree({
	url : basePath+'/sys/orgz/tree',
    parentField : 'parentId',
    lines : true,
    panelWidth:280,
    panelHeight :350,
    onBeforeSelect : function(node){
		if(node.type != "1"){
			$.messager.alert('错误', '请选择工程部', 'error');
			return false;
		}else{
		}
    }
});

/**
 * 承建单位
 */
$("#add_cjdw").textbox('setValue','中铁一局城轨公司');
});

/**
 * 员工选择框
 * @returns
 */
function empSelection(){
	$.modalDialog1 = function(options) {
	    if ($.modalDialog1.handler == undefined) {// 避免重复弹出
	        var opts = $.extend({
	            title : '',
	            width : 840,
	            height : 680,
	            modal : true,
	            loadingMessage : "数据在加载中...",
	            onClose : function() {
	                $.modalDialog1.handler = undefined;
	                $(this).dialog('destroy');
	            },
	            onOpen : function() {
	            }
	        }, options);
	        opts.modal = true;// 强制此dialog为模式化，无视传递过来的modal参数
	        return $.modalDialog1.handler = $('<div/>').dialog(opts);
	    }
	};
	var id_input = this.alt+"Id";
	var name_input = this.alt+"Name";
	$.modalDialog1({
        title : '员工管理',
        width : 800,
        height : 450,
        href : basePath+'/sys/emp/empSelection',
        buttons : [ {
            text : '确定',
            handler : function() {
                var udatagrid = $.modalDialog1.handler.find('#dataGrid');
            	var rows = udatagrid.datagrid('getSelections');
            	if (rows.length >= 1) {
            		$("#"+id_input).val(rows[0].id);
            		$("#"+name_input).textbox("setValue",rows[0].name);
            		$.modalDialog1.handler.dialog('close');
            	}else{
            		$.messager.alert('提示', '请选择一个员工！ ', 'info');
            	}
            	
            }
        },{
            text : '取消',
            handler : function() {
            	$.modalDialog1.handler.dialog('close');
            }
        } ]
    });

	
/*  	$('#openEmpIframe')[0].src=basePath+'/sys/emp/empSelection';
	$('#openEmp').dialog('open'); */
}
function selectAddress() {
	$('#openMapIframe')[0].src=basePath+'/project/info/selectAddress';
	$('#openDiv').dialog('open');
}


/**
 * 关闭窗口
 */
 function closePan(){
	 parent.$.modalDialog.handler.dialog('close');
}
/**
 * 确认按钮
 */
 function queDing(){
	$("#addAjax").onclick();
}
</script>

    <!-- 弹出框－添加 -->
<div id="pp" class="easyui-layout" data-options="fit:true,border:false">
	<div class="easyui-panel" data-options="region:'center',border:false,collapsible:false"  
		style="width:100%;height:100%;border: false;style="overflow-y:auto;">
       <form id="addForm" method="post" data-options="novalidate:true">
			<table class="grid">
	            <tr>
					<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>工程名称：</td>
                    <td class="form-td-content" colspan="5" >
                    	<input id="add_proName" name="proName" class="easyui-textbox" data-options="required:true,prompt:'请输入工程名称'"style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label">工程简称：</td>
                    <td class="form-td-content">
                    	<input id="add_abbreviation" name="abbreviation" class="easyui-textbox" class="easyui-textbox" data-options="prompt:'请输入工程简称'" style="width:100%">
                    </td>
                    <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>负责人：</td>
                    <td class="form-td-content" style="width:150px">
                    	<input id="add_empName" name="empName" class="easyui-textbox" alt="add_emp" data-options="required:true,buttonText:'',buttonAlign:'right',buttonIcon:'icon-search',editable:false,prompt:'请输入负责人',onClickButton:empSelection" style="width:100%">
                    	<input id="add_empId" name="empId" type="hidden"/>
                    </td>
                    <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>施工单位：</td>
                    <td class="form-td-content" style="width:150px">
                    	<input id="add_parentId" name="parentId" class="easyui-combotree" data-options="required:true,prompt:'请输入施工单位'" style="width:100%">
                    </td>
					
                </tr>
                <tr>
                    <td class="form-td-label">承建单位：</td>
                    <td class="form-td-content">
                    	<input id="add_cjdw" name="cjdw" class="easyui-textbox" data-options="prompt:'请输入承建单位'" style="width:100%" readonly="readonly">
                    </td>
                    <td class="form-td-label">监理单位：</td>
                    <td class="form-td-content">
                    	<input id="add_jldw" name="jldw" class="easyui-textbox" data-options="prompt:'请输入监理单位'" style="width:100%">
                    </td>
                    <td class="form-td-label">建设单位：</td>
                    <td class="form-td-content">
                    	<input id="add_jsdw" name="jsdw" class="easyui-textbox" data-options="prompt:'请输入建设单位'" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>地理位置：</td>
                    <td class="form-td-content" style="width:150px">
                    	<input id="add_position" name="position" class="easyui-textbox" class="easyui-textbox" data-options="required:true,buttonText:'',buttonAlign:'right',buttonIcon:'icon-search',editable:false,prompt:'请输入地理位置',onClickButton:selectAddress" style="width:100%">
                    </td>	
					<td class="form-td-label"><span style="color: red;">*</span>所在国家：</td>
                    <td class="form-td-content">
                    	<input id="add_country" name="country"  class="easyui-textbox" data-options="required:true,prompt:'请输入所在国家'" value="" style="width:100%">
                    </td>
					<td class="form-td-label"><span style="color: red;">*</span>所在片区：</td>
                    <td class="form-td-content">
                    	<input id="add_area" name="area"  class="easyui-textbox" data-options="required:true,prompt:'请输入所在片区'" value="" style="width:100%">
                    </td>
                    
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>所在省份：</td>
                    <td class="form-td-content">
                    	<input id="add_province" name="province"  class="easyui-textbox" data-options="required:true,prompt:'请输入所在省份'" value="" style="width:100%">
                    </td>
                	<td class="form-td-label"><span style="color: red;">*</span>所在城市：</td>
                    <td class="form-td-content">
                    	<input id="add_city" name="city"  class="easyui-textbox" data-options="required:true,prompt:'请输入所在城市'" value="" style="width:100%">
                    </td>
                    <td class="form-td-label">总长度(米)：</td>
                	<td class="form-td-content">
                		<input id="add_tunnellength" name="tunnellength"  class="easyui-numberbox" data-options="prompt:'请输入掘进总长度(米)'" value="" style="width:100%">
                	</td>
                	
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>管片长度(m)：</td>
                	<td class="form-td-content">
                		<input id="add_ringwidth" name="ringwidth"  class="easyui-numberbox" data-options="required:true,prompt:'请输入工程环宽(米)',precision:1" value="" style="width:100%">
                	</td>
                	<td class="form-td-label">合同工期起始：</td>
                	<td class="form-td-content">
                		<input id="add_startdate" name="startdate" class="easyui-datebox" data-options="prompt:'请输入进场日期'" style="width:100%">
                	</td>
                    <td class="form-td-label">合同工期截止：</td>
                	<td class="form-td-content">
                		<input id="add_estimateenddate" name="estimateenddate" data-options="prompt:'请输入预计完成日期'" class="easyui-datebox" style="width:100%">
                	</td>
                </tr>
                <tr>
                	<td class="form-td-label">地铁线路：</td>
                	<td class="form-td-content">
                		<input id="add_line" name="line"  class="easyui-textbox" data-options="required:false,prompt:'请输入地铁线路',precision:1" value="" style="width:100%">
                	</td>
                	<td class="form-td-label">合同金额：</td>
                	<td class="form-td-content">
                		<input id="add_htje" name="htje"  class="easyui-textbox" data-options="required:false,prompt:'请输入合同金额',precision:1" value="" style="width:100%">
                	</td>
                	<td class="form-td-label">开累完成：</td>
                	<td class="form-td-content">
                		<input id="add_klwc" name="klwc"  class="easyui-textbox" data-options="required:false,prompt:'请输入开累完成',precision:1" value="" style="width:100%">
                	</td>
                </tr>
                <tr>
                	<td class="form-td-label">管片外径(m)：</td>
                	<td class="form-td-content">
                		<input id="add_gpwj" name="gpwj"  class="easyui-textbox" data-options="required:false,prompt:'请输入管片外径(m)',precision:1" value="" style="width:100%">
                	</td>
                	<td class="form-td-label"><span style="color: red;">*</span>施工状态：</td>
                	<td class="form-td-content">
                		<input id="add_proState" name="proState"  class="easyui-combobox" data-options="required:true,prompt:'请选择施工状态',precision:1" value="" style="width:100%">
                	</td>
                </tr>
                <tr>
                	<td class="form-td-label">施工地址：</td>
                    <td class="form-td-content" colspan="5">
                    	<input id="add_projectaddress" name="projectaddress" data-options="prompt:'请输入施工地址'" class="easyui-textbox" value="" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label" rowspan="5">工程简介：</td>
                    <td class="form-td-content" colspan="5" rowspan="5">
                    	<input id="add_description" name="description" class="easyui-textbox" data-options="required:false,prompt:'请输入备注',multiline:true" style="width:100%;height:100%">
                    </td>
                </tr>
                <tr>
                	<td ></td>
                </tr>
                <tr>
                	<td ></td>
                </tr>
                <tr>
                	<td ></td>
                </tr>
                <tr>
                	<td ></td>
                </tr>
            </table>
        </form>
	</div>	
</div>
<!-- 地图选择器 -->
<div id="openDiv" class="easyui-window" closed="true" modal="true" title="位置选取" style="width:800px;height:500px;">
  		<iframe id='openMapIframe' src="" frameborder="0" style="width:100%;height:98%;"></iframe>
</div>
<!-- 员工选择 -->
<div id="openEmp" class="easyui-dialog" closed="true" modal="true" title="员工选择" style="width:800px;height:500px;">
  		<iframe id='openEmpIframe' src="" frameborder="0" style="width:100%;height:98%;"></iframe>
</div>
	