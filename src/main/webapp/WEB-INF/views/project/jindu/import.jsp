<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
/**
 * 项目管理系统 区间线路管理模块
 * 
 * 创建时间：2016年11月05日 
 * 
 */
/**
 * 系统模块同路径
 */
var syspath = "project";
/**
 * 子模块路径
 */
var module = "jindu";
/**
 * 模块名称
 */
var moduleName = "施工日进度";
/**
 * 名称字段
 */
var nameField = "jinduName";
/**
 * 业务数据访问全路径
 */
var path = basePath +"/"+ syspath +"/"+ module;
/**
 * 加载数据表格
 * 
 */
 var jcinfoDG;
 $(function() {
	jcinfoDG = $('#dataGrid').datagrid({
	url : path+'/dataGrid',
	iconCls:'icon-edit',
	striped : true,
	rownumbers : true,
	singleSelect : true,
	checkOnSelect : true,
	selectOnCheck : true,
	pagination : true,
	idField : 'id',
	sortName : 'id',
	sortOrder : 'desc',
	pageSize : 15,
	pageList : [ 10, 15, 20, 50 ],
	columns : [ [
		{field : 'ck',checkbox : true},
        {width : '320',title : '工程名称',field : 'proName',align:'center',editor:'text'},        
        {width : '100',title : '日期',field : 'rq',sortable : true,editor:'text'}     
        ]]
	
}); 
 });
 
 //表单提交
 $('#addForm').form({
            url : path+'/addForm',
            onSubmit : function() {
            	
            	var rq;
            	var proId;
            	var rows = jcinfoDG.datagrid('getSelections');
            	
            	if (rows.length > 1) {
            		$.messager.alert('提示', '请选择单条数据进行编辑！');
            		return;
            	}
            	else if(rows[0]==null){
            		$.messager.alert('提示', '请选择数据后再编辑！');
            		return;
            	}
            	else{
            		rq=rows[0].rq;
            		proId=rows[0].proId;    		
            		$('#rq').val(rq);
                    $('#proId').val(proId);
                   
            	}
            },
            success : function(result) {
            	var rqParam = $('#rq').val();
            	var proIdParam=$('#proId').val();
            	
            	progressClose();
                result = $.parseJSON(result);
                if (result.success) {
                	parent.$.modalDialog.handler.dialog('close');
                    addEditFun(rqParam,proIdParam);
                } else {
                    parent.$.messager.alert('错误', result.msg, 'error');
                }
            }
        });
 
 
 $('#showForm').form({
     url : path+'/addForm',
     onSubmit : function() {
     	
     	var rq;
     	var proId;
     	var rows = jcinfoDG.datagrid('getSelections');
     	
     	if (rows.length > 1) {
     		$.messager.alert('提示', '请选择单条数据进行编辑！');
     		return;
     	}
     	else if(rows[0]==null){
     		$.messager.alert('提示', '请选择数据后再编辑！');
     		return;
     	}
     	else{
     		rq=rows[0].rq;
     		proId=rows[0].proId;    		
     		$('#rq').val(rq);
             $('#proId').val(proId);
            
     	}
     },
     success : function(result) {
     	var rqParam = $('#rq').val();
     	var proIdParam=$('#proId').val();
     	
     	progressClose();
         result = $.parseJSON(result);
         if (result.success) {
         	parent.$.modalDialog.handler.dialog('close');
         	showFun(rqParam,proIdParam);
         } else {
             parent.$.messager.alert('错误', result.msg, 'error');
         }
     }
 });
 //明细页面
 function addEditFun(rq,proId) {
     parent.$.modalDialog({
         title : '导入内容',
         width : 900,
         height : 500,
         href : '${path}/project/jindu/addDetails?rq='+rq+'&proId='+proId,
        
         buttons : [ {
             text : '保存',
             handler : function() {
             	var f = parent.$.modalDialog.handler.find('#saveBtn');
             	$(f).trigger("onclick");
             }
         },{
             text : '关闭',
             handler : function() {
                 parent.$.modalDialog.handler.dialog('close');
             }
         } ]
     });
 }    
 
 //查看页面
  function showFun(rq,proId) {
     parent.$.modalDialog({
         title : '导入内容',
         width : 900,
         height : 500,
         href : '${path}/project/jindu/show?rq='+rq+'&proId='+proId,
        
         buttons : [ {
             text : '保存',
             handler : function() {
             	var f = parent.$.modalDialog.handler.find('#saveBtn');
             	$(f).trigger("onclick");
             }
         },{
             text : '关闭',
             handler : function() {
                 parent.$.modalDialog.handler.dialog('close');
             }
         } ]
     });
 }    

</script>
<!-- 结果列表 -->

	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
	<form id='addForm' method="post" >
	<input name='rq' type="hidden" id='rq' >
	<input name='proId' type="hidden" id='proId'>
	</form>
	<table id="dataGrid" title="列表" data-options="fit:true,border:false"></table>
	</div>

	
<!-- 结果列表 -->
<!-- 
	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
	<form id='showForm' method="post" >
	<input name='rq' type="hidden" id='rq' >
	<input name='proId' type="hidden" id='proId'>
	</form>
	<table id="dataGrid" title="列表" data-options="fit:true,border:false"></table>
	</div>	
 -->