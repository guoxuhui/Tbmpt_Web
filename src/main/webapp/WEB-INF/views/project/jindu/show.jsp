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
 var dataGrid;
 var rq;
 var proName;
 var proId;
 var editRow = undefined;
 var details=new Array();
 var froName;
 var qjId;

 var xlName;
 var xlId;
 
 $(function() {
	 
	 var detailData = ${list};
	 rq=detailData[0].rq;
	 proName=detailData[0].proName;
	 proId=detailData[0].proId;
	 
	 $('#rq').val(rq);
	 $('#proName').val(proName);
	 
	 
	 $('#addForm').form({
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
					parent.$.messager.show({title:'提示',msg:result.msg,showType:'show' });
	             parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
	             parent.$.modalDialog.handler.dialog('close');
				}
				else {
					$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
	 
	dataGrid = $('#dataGrid').datagrid({
	
	
		striped : true,
        rownumbers : false,
        pagination : false,
        singleSelect : false,
        idField : 'id',
        pageSize : 10,
        pageList : [ 10, 15, 20, 25, 50],
	columns : [ [
		 {field : 'ck',checkbox : true},
         {width : '320',title : '工程名称',field : 'proName',align:'center'}, 
         {width : '100',title : '区间名称',field : 'sectionName',align:'center'}, 
         {width : '100',title : '线路名称',field : 'lineName',align:'center'}, 
         {width : '100',title : '日期',field : 'rq',sortable : true}, 
         {width : '100',title : '日掘进环数',field : 'rjjnum',align:'center',sortable : false}, 
         {width : '100',title : '备注',field : 'remarks',align:'center',editor:'text'
        	 
         }      
        ]],

	toolbar : '#toolbar'
	
}); 
	$('#dataGrid').datagrid('loadData', detailData);
 });
 </script>
<!-- 结果列表 -->
<div class="easyui-layout" data-options="fit:true,border:false" >
<div class="easyui-panel" title="监测报告信息" data-options="region:'north',border:false,collapsible:true"  
		style="width:100%;height:160px;border: false;overflow: hidden;">
	<form id="addForm" method="post">
		<input type='hidden' id='details' name='details' />
			<table class="grid">
               
                <tr>
                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>所属项目：</td>
                	<td class="form-td-content" colspan="3" style="width:260px">
                    	<input id="proName" name="proName" class="easyui-textbox" style="width: 100%;margin-right: 15px">
                	</td>
                	<td class="form-td-label">备注：</td>
                	<td class="form-td-content" colspan="3" style="width:260px">
                    	<textarea name="remarks"  class="easyui-textbox"  data-options="required:false,prompt:'',multiline:true" style="width:100%;height:50px"></textarea>
                    </td>
                	
                </tr>
                <tr>
                	<td class="form-td-label">日期：</td>
                	<td class="form-td-content" colspan="3" style="width:260px">		
						<input id="rq" class="easyui-datebox" data-options="prompt:''" style="width:100%;" ></td>
					</td>
                </tr>
                
               
                
        	</table>
        </form>
        </div>
    <div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100px;">
        <table id="dataGrid" title="列表" data-options="fit:true,border:false"></table>
        <div id="toolbar">
	    	
	    </div>
	</div>
</div>	