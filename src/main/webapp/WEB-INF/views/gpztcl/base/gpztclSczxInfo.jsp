<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>实测中线信息明细管理</title>
<script type="text/javascript" src="${staticPath}/static/js/gpztcl/base/gpztclSczxInfo.js" charset="utf-8"></script>
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
<!-- 【实测中线信息明细管理】查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:61px;border: false;overflow: auto;" data-options="collapsible:true">
		<form id="searchForm_gpztclSczxInfo">
			<table class="grid" width='650px' border='0' cellspacing='0' style='padding:5px'>
			  <tr>
			    <td align="left" width="170px" >
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchGpztclSczxInfoFun();">查询</a>
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleanGpztclSczxInfoFun();">重置</a>
			    </td>
			  </tr>
			</table>
		</form>
	</div>

<!-- 【实测中线信息明细管理】结果列表 -->
	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
		<table id="dataGrid_gpztclSczxInfo" title="列表" data-options="fit:true,border:false"></table>
	</div>

<div id="toolbar" style="display: none;">
    	<a onclick="reloadGpztclSczxInfo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<shiro:hasPermission name="/gpztcl/base/gpztclSczxInfo/add">
    	<a onclick="addGpztclSczxInfoFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/gpztcl/base/gpztclSczxInfo/edit">
    	<a onclick="editGpztclSczxInfoFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/gpztcl/base/gpztclSczxInfo/del">
    	<a onclick="deleteGpztclSczxInfoFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	</shiro:hasPermission>
    	<a onclick="clearGpztclSczxInfoSelections();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    	<a onclick="expGpztclSczxInfoXls();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
</div>
<!-- 【实测中线信息明细管理】新增弹出框-->	<div id="addDialog_gpztclSczxInfo" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false"	style="width:660px;height:378px;padding:10px 10px;" >
        <form id="addForm_gpztclSczxInfo" method="post" data-options="novalidate:true">
			<table  class="grid">
			  <tr>
			    <td  class="form-td-label" >主表主键：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='fid' id='add_fid_gpztclSczxInfo'  data-options="prompt:'请输入主表主键',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >环号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='hh' id='add_hh_gpztclSczxInfo'  data-options="prompt:'请输入环号',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >实测横向坐标：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sczbX' id='add_sczbX_gpztclSczxInfo'  data-options="prompt:'请输入实测横向坐标',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >实测纵向坐标：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sczbY' id='add_sczbY_gpztclSczxInfo'  data-options="prompt:'请输入实测纵向坐标',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >实测高程：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sczbH' id='add_sczbH_gpztclSczxInfo'  data-options="prompt:'请输入实测高程',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >计算横向坐标：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='jszbX' id='add_jszbX_gpztclSczxInfo'  data-options="prompt:'请输入计算横向坐标',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >计算纵向坐标：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='jszbY' id='add_jszbY_gpztclSczxInfo'  data-options="prompt:'请输入计算纵向坐标',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >计算高程：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='jszbH' id='add_jszbH_gpztclSczxInfo'  data-options="prompt:'请输入计算高程',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >导向测量横向偏移：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='dxpyX' id='add_dxpyX_gpztclSczxInfo'  data-options="prompt:'请输入导向测量横向偏移',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >导向测量纵向偏移：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='dxpyY' id='add_dxpyY_gpztclSczxInfo'  data-options="prompt:'请输入导向测量纵向偏移',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="add-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="addGpztclSczxInfoAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#addDialog_gpztclSczxInfo').dialog('close')">关闭</a>
	</div>

<!-- 【实测中线信息明细管理】编辑弹出框 -->
	<div id="editDialog_gpztclSczxInfo" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#edit-dialog-buttons',closed:true,cache: false"
	style="width:660px;height:378px;padding:10px 10px;" >
        <form id="editForm_gpztclSczxInfo" method="post" data-options="novalidate:true">
           <input name="id" type="hidden" >
           <input name="updateTime" type="hidden" >
			<table  class="grid">
			  <tr>
			    <td  class="form-td-label" >主表主键：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='fid' id='edit_fid_gpztclSczxInfo'  data-options="prompt:'请输入主表主键',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >环号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='hh' id='edit_hh_gpztclSczxInfo'  data-options="prompt:'请输入环号',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >实测横向坐标：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sczbX' id='edit_sczbX_gpztclSczxInfo'  data-options="prompt:'请输入实测横向坐标',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >实测纵向坐标：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sczbY' id='edit_sczbY_gpztclSczxInfo'  data-options="prompt:'请输入实测纵向坐标',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >实测高程：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sczbH' id='edit_sczbH_gpztclSczxInfo'  data-options="prompt:'请输入实测高程',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >计算横向坐标：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='jszbX' id='edit_jszbX_gpztclSczxInfo'  data-options="prompt:'请输入计算横向坐标',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >计算纵向坐标：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='jszbY' id='edit_jszbY_gpztclSczxInfo'  data-options="prompt:'请输入计算纵向坐标',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >计算高程：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='jszbH' id='edit_jszbH_gpztclSczxInfo'  data-options="prompt:'请输入计算高程',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >导向测量横向偏移：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='dxpyX' id='edit_dxpyX_gpztclSczxInfo'  data-options="prompt:'请输入导向测量横向偏移',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >导向测量纵向偏移：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='dxpyY' id='edit_dxpyY_gpztclSczxInfo'  data-options="prompt:'请输入导向测量纵向偏移',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="edit-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="editGpztclSczxInfoAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#editDialog_gpztclSczxInfo').dialog('close')">关闭</a>
	</div>
</body>
</html>
