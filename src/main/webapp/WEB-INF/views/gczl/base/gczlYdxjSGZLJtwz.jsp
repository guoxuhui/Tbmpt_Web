<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>结构施工质量基础数据-具体位置</title>
<script type="text/javascript" src="${staticPath}/static/js/gczl/base/gczlYdxjSGZLJtwz.js" charset="utf-8"></script>


<!-- 【结构施工质量基础数据-具体位置】新增弹出框-->	<div id="addDialog_gczlYdxjSGZLJtwz" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false"	style="width:660px;height:378px;padding:10px 10px;display:none;" >
        <form id="addForm_gczlYdxjSGZLJtwz" method="post" data-options="novalidate:true">
			<table  class="grid">
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>具体位置名称：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='jtWz' id='add_jtWz_gczlYdxjSGZLJtwz'  data-options="prompt:'请输入具体位置名称',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >排序号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sortNum' id='add_sortNum_gczlYdxjSGZLJtwz'  data-options="prompt:'请输入排序号',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >使用状态：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='ifQy' id='add_ifQy_gczlYdxjSGZLJtwz'  data-options="prompt:'请输入使用状态',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >备注：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='remarks'  id='add_remarks_gczlYdxjSGZLJtwz'  data-options="prompt:'请输入备注',multiline:true,validType:'length[1,40]'"  style='width: 100%;height:100px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" ><span style="color: red;">*</span>施工内容：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sgNr'  id='add_sgNr_gczlYdxjSGZLJtwz'  data-options="prompt:'请输入施工内容',multiline:true,validType:'length[1,40]',required:true"  style='width: 100%;height:100px'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="add-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="addGczlYdxjSGZLJtwzAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#addDialog_gczlYdxjSGZLJtwz').dialog('close')">关闭</a>
	</div>

<!-- 【结构施工质量基础数据-具体位置】编辑弹出框 -->
	<div id="editDialog_gczlYdxjSGZLJtwz" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#edit-dialog-buttons',closed:true,cache: false"
	style="width:660px;height:378px;padding:10px 10px;display:none;" >
        <form id="editForm_gczlYdxjSGZLJtwz" method="post" data-options="novalidate:true">
           <input name="id" type="hidden" >
			<table  class="grid">
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>具体位置名称：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='jtWz' id='edit_jtWz_gczlYdxjSGZLJtwz'  data-options="prompt:'请输入具体位置名称',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >排序号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sortNum' id='edit_sortNum_gczlYdxjSGZLJtwz'  data-options="prompt:'请输入排序号',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >使用状态：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='ifQy' id='edit_ifQy_gczlYdxjSGZLJtwz'  data-options="prompt:'请输入使用状态',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >备注：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='remarks'  id='edit_remarks_gczlYdxjSGZLJtwz'  data-options="prompt:'请输入备注',multiline:true,validType:'length[1,40]'"  style='width: 100%;height:100px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" ><span style="color: red;">*</span>施工内容：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sgNr'  id='edit_sgNr_gczlYdxjSGZLJtwz'  data-options="prompt:'请输入施工内容',multiline:true,validType:'length[1,40]',required:true"  style='width: 100%;height:100px'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="edit-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="editGczlYdxjSGZLJtwzAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#editDialog_gczlYdxjSGZLJtwz').dialog('close')">关闭</a>
	</div>
</html>
