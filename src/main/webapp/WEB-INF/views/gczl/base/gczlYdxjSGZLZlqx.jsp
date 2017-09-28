<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>结构施工质量基础数据-质量缺陷</title>
<script type="text/javascript" src="${staticPath}/static/js/gczl/base/gczlYdxjSGZLZlqx.js" charset="utf-8"></script>
<style type="text/css">	td{		 white-space: nowrap;	}	td input{		white-space: normal;	}</style></head>
<body class="easyui-layout" data-options="fit:true,border:false">
<!-- 【结构施工质量基础数据-质量缺陷】查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:61px;border: false;overflow: hidden;" data-options="collapsible:true">
		<form id="searchForm_gczlYdxjSGZLZlqx">
			<table class="grid" width='650px' border='0' cellspacing='0' style='padding:5px'>
			  <tr>
			    <td class="form-td-label" style="width: 80px">施工内容：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='sgNr'   style='width: 149px'>
			    </td>
			    <td align="left" width="170px" >
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchGczlYdxjSGZLZlqxFun();">查询</a>
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleanGczlYdxjSGZLZlqxFun();">重置</a>
			    </td>
			  </tr>
			</table>
		</form>
	</div>

<!-- 【结构施工质量基础数据-质量缺陷】结果列表 -->
	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
		<table id="dataGrid_gczlYdxjSGZLZlqx" title="列表" data-options="fit:true,border:false"></table>
	</div>

<!-- 【结构施工质量基础数据-质量缺陷】新增弹出框-->	
<div id="addDialog_gczlYdxjSGZLZlqx" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false"	style="width:660px;height:378px;padding:10px 10px;display:none;" >
        <form id="addForm_gczlYdxjSGZLZlqx" method="post" data-options="novalidate:true">
			<table  class="grid">
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" ><span style="color: red;">*</span>存在质量问题、缺陷说明的名称：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='zlQx'  id='add_zlQx_gczlYdxjSGZLZlqx'  data-options="prompt:'请输入存在质量问题、缺陷说明的名称',multiline:true,validType:'length[1,40]',required:true"  style='width: 100%;height:100px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >排序号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sortNum' id='add_sortNum_gczlYdxjSGZLZlqx'  data-options="prompt:'请输入排序号',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >使用状态：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='ifQy' id='add_ifQy_gczlYdxjSGZLZlqx'  data-options="prompt:'请输入使用状态',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >备注：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='remarks'  id='add_remarks_gczlYdxjSGZLZlqx'  data-options="prompt:'请输入备注',multiline:true,validType:'length[1,40]'"  style='width: 100%;height:100px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" ><span style="color: red;">*</span>施工内容：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sgNr'  id='add_sgNr_gczlYdxjSGZLZlqx'  data-options="prompt:'请输入施工内容',multiline:true,validType:'length[1,40]',required:true"  style='width: 100%;height:100px'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="add-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="addGczlYdxjSGZLZlqxAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#addDialog_gczlYdxjSGZLZlqx').dialog('close')">关闭</a>
	</div>

<!-- 【结构施工质量基础数据-质量缺陷】编辑弹出框 -->
	<div id="editDialog_gczlYdxjSGZLZlqx" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#edit-dialog-buttons',closed:true,cache: false"
	style="width:660px;height:378px;padding:10px 10px;display:none;" >
        <form id="editForm_gczlYdxjSGZLZlqx" method="post" data-options="novalidate:true">
           <input name="id" type="hidden" >
			<table  class="grid">
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" ><span style="color: red;">*</span>存在质量问题、缺陷说明的名称：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='zlQx'  id='edit_zlQx_gczlYdxjSGZLZlqx'  data-options="prompt:'请输入存在质量问题、缺陷说明的名称',multiline:true,validType:'length[1,40]',required:true"  style='width: 100%;height:100px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >排序号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sortNum' id='edit_sortNum_gczlYdxjSGZLZlqx'  data-options="prompt:'请输入排序号',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >使用状态：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='ifQy' id='edit_ifQy_gczlYdxjSGZLZlqx'  data-options="prompt:'请输入使用状态',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >备注：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='remarks'  id='edit_remarks_gczlYdxjSGZLZlqx'  data-options="prompt:'请输入备注',multiline:true,validType:'length[1,40]'"  style='width: 100%;height:100px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" ><span style="color: red;">*</span>施工内容：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sgNr'  id='edit_sgNr_gczlYdxjSGZLZlqx'  data-options="prompt:'请输入施工内容',multiline:true,validType:'length[1,40]',required:true"  style='width: 100%;height:100px'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="edit-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="editGczlYdxjSGZLZlqxAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#editDialog_gczlYdxjSGZLZlqx').dialog('close')">关闭</a>
	</div>
</body>
</html>
