



<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${staticPath}/static/js/aqxj/xjdgl/aqxjXjnr.js" charset="utf-8"></script>


<div id="dategridDialog_aqxjXjnr" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#show-dialog-buttons',closed:true,cache: false,modal:true"	style="width:790px;height:500px;padding:10px 10px;display:none;" >
<div class="easyui-panel"  data-options="region:'north',border:false"  style="display:none;" data-options="collapsible:true">
		<form id="search_itenid_aqxjXjd">
			<input name="ItemId" type="hidden" id="show_itemsId_aqxjXjnr1">
		</form>
	</div>

	<div id="show_aqxjXjd_nr" padding:10px 10px;" >
        <form id="editForm_aqxjXjd_nr" method="post" data-options="novalidate:true">
           <input name="id" type="hidden" id="show_itemsId_aqxjXjnr">
           
           <input name="updateTime" type="hidden" >
			<table  class="grid" style='width: 600px'>
			  <tr>
				<td  class="form-td-label" >工程项目名称：</td>
			    <td class="form-td-content" colspan='3'>${projectName}
			      <input type="hidden"  id='edit_projectName_aqxjXjd'  value="${projectId}"  style='width: 150px'>
			    </td>
			</tr>
			  <tr>
			    <td  class="form-td-label" >巡检点名称：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='mingCheng' id='edit_mingCheng_aqxjXjnr'  data-options=""  style='width: 150px'>
			    </td>
			     <td  class="form-td-label" style='width: 200px'>分类名称：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='typeName' id='edit_typeName_aqxjXjnr'  data-options=""  style='width: 150px'>
			    </td>
			   </tr>
			    <tr>
			    <td  class="form-td-label" >检查点所在位置：</td>
			    <td class="form-td-content" colspan='3'>
			      <input class="easyui-textbox"   name='address' id='edit_address_aqxjXjnr'  data-options=""  style='width: 100%;height:60px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >责任人名称：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='zeRenrmc' id='edit_zeRenrmc_aqxjXjnr'  data-options=""  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >监督人名称：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='jianDurmc' id='edit_jianDurmc_aqxjXjnr'  data-options=""  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >检查频次：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='jianChapc' id='edit_jianChapc_aqxjXjnr'  data-options=""  style='width: 150px'>
			    </td>
			  </tr>
			   <tr>
			    <td  class="form-td-label"  valign="top" align="right" >备注：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='beiZhu'  id='edit_beiZhu_aqxjXjnr'  data-options=""  style='width: 100%;height:100px'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>


<!-- 【安全巡检内容】结果列表 -->
	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
		<table id="dataGrid_aqxjXjnr"  data-options="fit:true,border:false"></table>
	</div>

<div id="toolbar2" >
    	<a onclick="reloadAqxjXjnr();" href="javascript:void(0);" id="clear_nrbutton_aqxjXjnr" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<shiro:hasPermission name="/aqxj/xjdgl/aqxjXjnr/add">
    	</shiro:hasPermission>
    	<a onclick="addAqxjXjnrFun();" href="javascript:void(0);" id="add_nrbutton_aqxjXjd" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	<shiro:hasPermission name="/aqxj/xjdgl/aqxjXjnr/edit">
    	</shiro:hasPermission>
    	<a onclick="editAqxjXjnrFun2();" href="javascript:void(0);" id="edit_nrbutton_aqxjXjd" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	<shiro:hasPermission name="/aqxj/xjdgl/aqxjXjnr/del">
    	</shiro:hasPermission>
    	<a onclick="deleteAqxjXjnrFun();" href="javascript:void(0);" id="del_nrbutton_aqxjXjd" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	<a onclick="clearAqxjXjnrSelections();" href="javascript:void(0);" id="clearselect_nrbutton_aqxjXjnr" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
</div>
</div>
<div id="show-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#dategridDialog_aqxjXjnr').dialog('close')">关闭</a>
	</div>
<!-- 【安全巡检内容】新增弹出框-->	<div id="addDialog_aqxjXjnr" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal:true"	style="width:490px;height:200px;padding:10px 10px;display:none;" >
        <form id="addForm_aqxjXjnr" method="post" data-options="novalidate:true">
			<input name="ItemId" type="hidden" id="add_itemsId_aqxjXjnr">
			<table  class="grid" style='width: 460px'>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" ><span style="color: red;">*</span>巡检内容：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='mingCheng'  id='add_mingCheng_aqxjXjnr'  data-options="prompt:'请输入巡检内容',multiline:true,validType:'length[1,40]',required:true"  style='width: 100%;height:60px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>顺序号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-numberbox" class="easyui-validatebox span2"  name='xuHao' id='add_xuHao_aqxjXjnr'  data-options="prompt:'请输入顺序号（仅限数字）',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="add-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="addAqxjXjnrAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#addDialog_aqxjXjnr').dialog('close')">关闭</a>
	</div>

<!-- 【安全巡检内容】编辑弹出框 -->
	<div id="editDialog_aqxjXjnr" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#edit-dialog-buttons',closed:true,cache: false,modal:true"
	style="width:490px;height:200px;padding:10px 10px;display:none;" >
        <form id="editForm_aqxjXjnr" method="post" data-options="novalidate:true">
        <input name="ItemId" type="hidden" id="edit_itemsId_aqxjXjnr">
           <input name="id" type="hidden" >
           <input name="updateTime" type="hidden" >
			<table  class="grid" style='width: 460px'>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" ><span style="color: red;">*</span>巡检内容：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='mingCheng'  id='edit_mingCheng_aqxjXjnr'  data-options="prompt:'请输入巡检内容',multiline:true,validType:'length[1,40]',required:true"  style='width: 100%;height:60px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>顺序号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-numberbox" class="easyui-validatebox span2"  name='xuHao' id='edit_xuHao_aqxjXjnr'  data-options="prompt:'请输入顺序号（仅限数字）',required:true"  style='width: 150px'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="edit-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="editAqxjXjnrAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#editDialog_aqxjXjnr').dialog('close')">关闭</a>
	</div>
