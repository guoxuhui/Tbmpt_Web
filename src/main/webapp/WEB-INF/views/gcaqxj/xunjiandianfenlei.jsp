<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/commons/basejs.jsp" %>
	<meta http-equiv="X-UA-Compatible" content="edge" />
<title>巡检点分类管理</title>
<script type="text/javascript" src="${staticPath}/static/js/gcaqxj/xjdfl.js" charset="utf-8"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">

	<!-- 【巡检点分类管理】查询表单
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:105px;border: false;overflow: hidden;" data-options="collapsible:true" >
		<form id="searchForm_XunJianDianFenLei" style="display:none">
			<table class="grid" border='0' cellspacing='0' style='width:60%;padding:5px;height: auto;'>
			  <tr>
			  	<td class="form-td-label" style="width: 150px">工程名称：</td>
				<td class="form-td-content" style="width: 100px">
					<input  class="easyui-combobox" data-options="prompt:'工程名称'" name='proiectid' id = "projectid_query_id" style="width: 180px;"  />
					<input type="hidden" name="proiectName" id="proiectname_query"/>
				</td>
				  <td></td>
			  </tr>
			   <tr>
				<tr>
					<td class="form-td-label" style="width: 150px">分类名称：</td>
					<td class="form-td-content" style="width: 100px">
						<input  class="easyui-textbox" data-options="prompt:'分类名称'" name='typeName' id = "typeName_query_id" style="width: 180px;"  />
					</td>

			    <td align="right" width="230px" >
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchXunJianDianFenLeiFun();">查询</a>
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleandXunJianDianFenLeiFun();">重置</a>
			    </td>
			  </tr>
			</table>
		</form>
	</div>
	-->
	<!-- 【施工内容管理】结果列表 -->
	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
		<table id="dataGrid_xunjiandianfenlei" title="列表" data-options="fit:true,border:false"></table>
	</div>
<div id="toolbar" style="display: none;">
    	        <a onclick="reloadXunJianDianFenLeiFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	        <shiro:hasPermission name="/gcaqxj/xjdfl/add">    
    	    		<a onclick="addXunJianDianFenLeiFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	    	</shiro:hasPermission>
    	    	
    	    	<shiro:hasPermission name="/gcaqxj/xjdfl/edit"> 
    	    		<a onclick="editXunJianDianFenLeiFun();" id="edit_button_XunJianDianFenLei" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	    	</shiro:hasPermission>
    	    	
    	    	<shiro:hasPermission name="/gcaqxj/xjdfl/delete"> 
    	    		<a onclick="deleteYanShouYingXiangFun();" href="javascript:void(0);" id="delete__button_xunjiandianfenlei" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
				</shiro:hasPermission>
				<a onclick="clearXunJianDianFenLeiFunSelections();" href="javascript:void(0);" id="cancelSelect_button_xunjiandianfenlei" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
	<a onclick="expand();" href="javascript:void(0);"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-redo'">展开</a>
	<a onclick="collapse();" href="javascript:void(0);"  class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">折叠</a>
</div>

	<!--巡检分类编辑界面-->
	<div id="editDialog_XunJianFenLei" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#edit-dialog-buttons',closed:true,cache:false,modal:true"
		 style="width:580px;height:350px;padding:10px 10px;display:none;" >
		<form id="editForm_XunJianFenLei" method="post" data-options="novalidate:true">
			<table  class="grid" style="width:530px">
				<input type="hidden" name="id"/>
				<input type="hidden" name="updateTime"/>
				<tr>
					<td class="form-td-label" style="width: 150px"><span style="color: red;">*</span>工程名称：</td>
					<td class="form-td-content" style="width: 100px">
						<input  class="easyui-combobox" data-options="prompt:'工程名称'" name='projectid_commbox_edit' id = "projectid_commbox_edit" style="width: 180px;"  />
						<input type="hidden" name="proiectName" id="proiectname_edit"/>
						<input type="hidden" name="proiectId" id="proiectId_edit"/>
					</td>
				</tr>
				<tr>
					<td class="form-td-label" style="width: 150px">所属父分类：</td>
					<td class="form-td-content" style="width: 100px">
						<input  class="easyui-combobox" data-options="prompt:'所属父分类',novalidate:true"  name="typeParent_commbox_edit" id = "typeParent_commbox_edit" style="width: 180px;"   />
						<input type="hidden" name="parentId" id="parentId_edit"/>
						<input type="hidden" name="parentName" id="parentName_edit"/>
					</td>
				</tr>
				<tr>
					<td class="form-td-label" style="width: 150px"><span style="color: red;">*</span>分类名称：</td>
					<td class="form-td-content" style="width: 100px">
						<input  class="easyui-textbox" data-options="prompt:'分类名称',validType:'length[1,40]'" required="true" name='typeName' id = "typeName_edit" style="width: 180px;"  />
					</td>
				</tr>
				<tr>
					<td class="form-td-label" style="width: 150px">备注：</td>
					<td class="form-td-content" style="width: 100px">
						<input  class="easyui-textbox" data-options="prompt:'备注'" name='remark' style="width: 180px;"  />
					</td>
				</tr>
			</table>
		</form>
	</div>

	<div id="edit-dialog-buttons"  style="display:none">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="editXunJianFenLeiAjax();">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#editDialog_XunJianFenLei').dialog('close')">关闭</a>
	</div>

	<!--巡检分类新增界面-->
	<div id="addDialog_XunJianFenLei" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache:false,modal:true"
		 style="width:580px;height:350px;padding:10px 10px;display:none;" >
		<form id="addForm_XunJianFenLei" method="post">
			<table  class="grid" style="width:530px">

				<tr>
					<td class="form-td-label" style="width: 150px"><span style="color: red;">*</span>工程名称：</td>
					<td class="form-td-content" style="width: 100px">
						<input  class="easyui-combobox" data-options="prompt:'工程名称'" name='projectid_commbox_add' id = "projectid_commbox_add" style="width: 180px;"  />
						<input type="hidden" name="proiectName" id="proiectname_add"/>
						<input type="hidden" name="proiectId" id="proiectid_add"/>
					</td>
				</tr>
				<tr>
					<td class="form-td-label" style="width: 150px">所属父分类：</td>
					<td class="form-td-content" style="width: 100px">
						<input  class="easyui-combobox" data-options="prompt:'所属父分类',novalidate:true" name='typeParent_commbox_add' id = "typeParent_commbox_add" style="width: 180px;"   />
						<input type="hidden" name="parentId" id="parentId_add"/>
						<input type="hidden" name="parentName" id="parentName_add"/>
					</td>
				</tr>
				<tr>
					<td class="form-td-label" style="width: 150px"><span style="color: red;">*</span>分类名称：</td>
					<td class="form-td-content" style="width: 100px">
						<input  class="easyui-textbox" data-options="prompt:'分类名称',validType:'length[1,40]'" required="true" name='typeName' id = "typeName" style="width: 180px;"  />
					</td>
				</tr>
				<tr>
					<td class="form-td-label" style="width: 150px">备注：</td>
					<td class="form-td-content" style="width: 100px">
						<input  class="easyui-textbox" data-options="prompt:'备注'" name='remark' id = "remark" style="width: 180px;"  />
					</td>
				</tr>
			</table>
		</form>
	</div>
	<div id="add-dialog-buttons" style="display:none">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="addXunJianFenLeiAjax();">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#addDialog_XunJianFenLei').dialog('close')">关闭</a>
	</div>

</body>
</html>