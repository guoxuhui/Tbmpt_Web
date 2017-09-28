<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>线路中心线信息管理</title>
<script type="text/javascript" src="${staticPath}/static/js/gpztcl/base/gpztclSjzxInfoParent.js" charset="utf-8"></script>
<style type="text/css">
	td{
		 white-space: nowrap;
	}
	td input{
		white-space: normal;
	}
</style>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
<!-- 【线路中心线信息管理】查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:61px;border: false;overflow: auto;" data-options="collapsible:true">
		<form id="searchForm_gpztclSjzxInfoParent">
			<table class="grid" width='650px' border='0' cellspacing='0' style='padding:5px'>
			  <tr>
			    <td align="left" width="170px" >
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchGpztclSjzxInfoParentFun();">查询</a>
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleanGpztclSjzxInfoParentFun();">重置</a>
			    </td>
			  </tr>
			</table>
		</form>
	</div>

<!-- 【线路中心线信息管理】结果列表 -->
	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
		<table id="dataGrid_gpztclSjzxInfoParent" title="列表" data-options="fit:true,border:false"></table>
	</div>

<div id="toolbar" style="display: none;">
    	<a onclick="reloadGpztclSjzxInfoParent();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<shiro:hasPermission name="/gpztcl/base/gpztclSjzxInfoParent/add">
    	<a onclick="addGpztclSjzxInfoParentFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/gpztcl/base/gpztclSjzxInfoParent/edit">
    	<a onclick="editGpztclSjzxInfoParentFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/gpztcl/base/gpztclSjzxInfoParent/del">
    	<a onclick="deleteGpztclSjzxInfoParentFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	</shiro:hasPermission>
    	<a onclick="clearGpztclSjzxInfoParentSelections();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    	<a onclick="expGpztclSjzxInfoParentXls();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
</div>
<!-- 【线路中心线信息管理】新增弹出框-->	<div id="addDialog_gpztclSjzxInfoParent" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false"	style="width:660px;height:378px;padding:10px 10px;" >
        <form id="addForm_gpztclSjzxInfoParent" method="post" data-options="novalidate:true">
			<table  class="grid">
			  <tr>
			    <td  class="form-td-label" >工程（项目）编号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='GC_BH' id='add_GC_BH_gpztclSjzxInfoParent'  data-options="prompt:'请输入工程（项目）编号',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >区间编号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='QL_BH' id='add_QL_BH_gpztclSjzxInfoParent'  data-options="prompt:'请输入区间编号',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >线路编号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='XL_BH' id='add_XL_BH_gpztclSjzxInfoParent'  data-options="prompt:'请输入线路编号',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >管环数量：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='impman' id='add_impman_gpztclSjzxInfoParent'  data-options="prompt:'请输入管环数量',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >已导入环数：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='imptime' id='add_imptime_gpztclSjzxInfoParent'  data-options="prompt:'请输入已导入环数',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="add-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="addGpztclSjzxInfoParentAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#addDialog_gpztclSjzxInfoParent').dialog('close')">关闭</a>
	</div>

<!-- 【线路中心线信息管理】编辑弹出框 -->
	<div id="editDialog_gpztclSjzxInfoParent" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#edit-dialog-buttons',closed:true,cache: false"
	style="width:660px;height:378px;padding:10px 10px;" >
        <form id="editForm_gpztclSjzxInfoParent" method="post" data-options="novalidate:true">
           <input name="id" type="hidden" >
           <input name="updateTime" type="hidden" >
			<table  class="grid">
			  <tr>
			    <td  class="form-td-label" >工程（项目）编号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='GC_BH' id='edit_GC_BH_gpztclSjzxInfoParent'  data-options="prompt:'请输入工程（项目）编号',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >区间编号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='QL_BH' id='edit_QL_BH_gpztclSjzxInfoParent'  data-options="prompt:'请输入区间编号',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >线路编号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='XL_BH' id='edit_XL_BH_gpztclSjzxInfoParent'  data-options="prompt:'请输入线路编号',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >管环数量：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='impman' id='edit_impman_gpztclSjzxInfoParent'  data-options="prompt:'请输入管环数量',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >已导入环数：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='imptime' id='edit_imptime_gpztclSjzxInfoParent'  data-options="prompt:'请输入已导入环数',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="edit-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="editGpztclSjzxInfoParentAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#editDialog_gpztclSjzxInfoParent').dialog('close')">关闭</a>
	</div>
</body>
</html>
