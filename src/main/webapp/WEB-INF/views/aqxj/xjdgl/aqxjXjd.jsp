<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<%@ include file="aqxjXjnr.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>安全巡检点</title>
<script type="text/javascript" src="${staticPath}/static/js/aqxj/xjdgl/aqxjXjd.js" charset="utf-8"></script>
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
<!-- 【安全巡检点】查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:135px;border: false;overflow: auto;" data-options="collapsible:true">
		<form id="searchForm_aqxjXjd">
			<table class="grid" width='550px' border='0' cellspacing='0' style='padding:5px'>
			  <tr>
			    <td class="form-td-label" style="width: 80px">工程项目名称：</td>
			    <td align="left" width="160px">
			      <input   class="easyui-textbox"  name='projectName'  id="query_gcBh_aqxjXjd" style='width: 149px'>
			    </td>
			    <td class="form-td-label" style="width: 80px">巡检点名称：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='mingCheng'   style='width: 149px'>
			    </td>
			  </tr>
			  <tr>
			    <td class="form-td-label" style="width: 80px">责任人名称：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='zeRenrmc'   style='width: 149px'>
			    </td>
			    <td class="form-td-label" style="width: 80px">监督人名称：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='jianDurmc'   style='width: 149px'>
			    </td>
			  </tr>
			  <tr>
			    <td class="form-td-label" style="width: 80px">分类名称：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='typeName' id="query_flmc_aqxjXjd"  style='width: 149px'>
			    </td>
			    <td align="center" width="170px">
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchAqxjXjdFun();">查询</a>
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleanAqxjXjdFun();">重置</a>
			    </td>
			  </tr>
			</table>
		</form>
	</div>

<!-- 【安全巡检点】结果列表 -->
	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
		<table id="dataGrid_aqxjXjd" title="安全巡检点" data-options="fit:true,border:false"></table>
	</div>

<div id="toolbar" style="display: none;">
    	<a onclick="reloadAqxjXjd();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<shiro:hasPermission name="/aqxj/xjdgl/aqxjXjd/add">
    	</shiro:hasPermission>
    	<a onclick="addAqxjXjdFun();" href="javascript:void(0);" id="add_tj_aqxjXjd" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	<shiro:hasPermission name="/aqxj/xjdgl/aqxjXjd/edit">
    	</shiro:hasPermission>
    	<a onclick="editAqxjXjdFun();" href="javascript:void(0);" id="edit_bj_aqxjXjd" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	<shiro:hasPermission name="/aqxj/xjdgl/aqxjXjd/del">
    	</shiro:hasPermission>
    	<a onclick="deleteAqxjXjdFun();" href="javascript:void(0);" id="del_sc_aqxjXjd" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	<a onclick="clearAqxjXjdSelections();" href="javascript:void(0);" id="cancelSelect_bj_aqxjXjd" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    	<a onclick="editAqxjXjnrFun();" href="javascript:void(0);" id="edit_AqxjXjnr" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑巡检内容</a>
    	<a onclick="expXlsAqxjXjnrFun();" href="javascript:void(0);" id="expXls_button_project_AqxjXjnr" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
    	<a onclick="expPdfAqxjXjnrFun();" href="javascript:void(0);" id="expPdf_button_project_AqxjXjnr" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_white_acrobat'">导出Pdf</a>
    	<a onclick="printAqxjXjnrFun();" href="javascript:void(0);" id="print_AqxjXjnr" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">打印</a> 
</div>
<!-- 【安全巡检点】新增弹出框-->	<div id="addDialog_aqxjXjd" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal:true"	style="width:550px;height:420px;padding:10px 10px;display:none;" >
        <form id="addForm_aqxjXjd" method="post" data-options="novalidate:true">
        	<input type="hidden" name="id"/>
        	<!-- <input type="hidden" name="typeId"/> -->
			<table  class="grid">
			<tr>
				<td  class="form-td-label" >工程项目名称：</td>
			    <td class="form-td-content" colspan='3'><span id="add_projec_tName_aqxjXjd">${projectName}</span>
			      <input type="hidden" name="projectName" id='add_projectName_aqxjXjd' style='width: 150px'>
			    </td>
			</tr>
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>分类名称：</td>
			    <td class="form-td-content" >
			      <input class="easyui-combobox"   name='typeId' id='add_typeId_aqxjXjd'  data-options="prompt:'请输入分类名称',validType:'length[1,40]',required:true"  style='width: 150px'>
			      <input name='typeName' id='add_typeName_aqxjXjd' type="hidden">
			    </td>
			    <td  class="form-td-label" ><span style="color: red;">*</span>巡检点名称：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='mingCheng' id='add_mingCheng_aqxjXjd'  data-options="prompt:'请输入巡检点名称',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >检查点所在位置：</td>
			    <td class="form-td-content" colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='address' id='add_address_aqxjXjd'  data-options="prompt:'请输入检查点所在位置',multiline:true,validType:'length[1,40]'"  style='width: 100%;height:60px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>责任人名称：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='zeRenrmc' id='add_zeRenrmc_aqxjXjd'  data-options="prompt:'请输入责任人名称',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" ><span style="color: red;">*</span>监督人名称：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='jianDurmc' id='add_jianDurmc_aqxjXjd'  data-options="prompt:'请输入监督人名称',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >检查频次：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='jianChapc' id='add_jianChapc_aqxjXjd'  data-options="prompt:'请输入检查频次',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			     <td  class="form-td-label" >顺序号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-numberbox" class="easyui-validatebox span2"  name='xuHao' id='add_xuHao_aqxjXjd'  data-options="prompt:'请输入顺序号（仅限数字）',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			   <tr>
			    <td  class="form-td-label"  valign="top" align="right" >备注：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='beiZhu'  id='add_beiZhu_aqxjXjd'  data-options="prompt:'请输入备注',multiline:true,validType:'length[1,40]'"  style='width: 100%;height:100px'>
			    </td>
			  </tr>
			  <tr>
			    
			  </tr>
			</table>
        </form>
	</div>
	<div id="add-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="addAqxjXjdAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#addDialog_aqxjXjd').dialog('close')">关闭</a>
	</div>

<!-- 【安全巡检点】编辑弹出框 -->
	<div id="editDialog_aqxjXjd" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#edit-dialog-buttons',closed:true,cache: false,modal:true"
	style="width:550px;height:420px;padding:10px 10px;display:none;" >
        <form id="editForm_aqxjXjd" method="post" data-options="novalidate:true">
           <input name="id" type="hidden" >
           <input name="updateTime" type="hidden" >
			<table  class="grid">
			  <tr>
				<td  class="form-td-label" >工程项目名称：</td>
			    <td class="form-td-content" colspan='3'><span id="edit_projec_tName_aqxjXjd">${projectName}</span>
			      <input type="hidden" name="projectName" id='edit_projectName_aqxjXjd' style='width: 150px'>
			    </td>
			</tr>
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>巡检点名称：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='mingCheng' id='edit_mingCheng_aqxjXjd'  data-options="prompt:'请输入巡检点名称',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			     <td  class="form-td-label" ><span style="color: red;">*</span>分类名称：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='typeId' id='edit_fl_typeId_aqxjXjd'  data-options="prompt:'请输入分类名称',validType:'length[1,40]',required:true"  style='width: 150px'>
			    <input name='typeName' id='edit_typeName_aqxjXjd' type="hidden">
			    </td>
			   </tr>
			    <tr>
			    <td  class="form-td-label" >检查点所在位置：</td>
			    <td class="form-td-content" colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='address' id='edit_address_aqxjXjd'  data-options="prompt:'请输入检查点所在位置',multiline:true,validType:'length[1,40]'"  style='width: 100%;height:60px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>责任人名称：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='zeRenrmc' id='edit_zeRenrmc_aqxjXjd'  data-options="prompt:'请输入责任人名称',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" ><span style="color: red;">*</span>监督人名称：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='jianDurmc' id='edit_jianDurmc_aqxjXjd'  data-options="prompt:'请输入监督人名称',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >检查频次：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='jianChapc' id='edit_jianChapc_aqxjXjd'  data-options="prompt:'请输入检查频次',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >顺序号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-numberbox" class="easyui-validatebox span2"  name='xuHao' id='edit_xuHao_aqxjXjd'  data-options="prompt:'请输入顺序号（仅限数字）',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			   <tr>
			    <td  class="form-td-label"  valign="top" align="right" >备注：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='beiZhu'  id='edit_beiZhu_aqxjXjd'  data-options="prompt:'请输入备注',multiline:true,validType:'length[1,40]'"  style='width: 100%;height:100px'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="edit-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="editAqxjXjdAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#editDialog_aqxjXjd').dialog('close')">关闭</a>
	</div>
</body>
</html>
