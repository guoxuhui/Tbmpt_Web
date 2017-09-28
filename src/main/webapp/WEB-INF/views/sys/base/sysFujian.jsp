<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>公共统一附件</title>
<script type="text/javascript" src="${staticPath}/static/js/sys/base/sysFujian.js" charset="utf-8"></script>
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
<!-- 【公共统一附件】查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:135px;border: false;overflow: auto;" data-options="collapsible:true">
		<form id="searchForm_sysFujian">
			<table class="grid" width='650px' border='0' cellspacing='0' style='padding:5px'>
			  <tr>
			    <td class="form-td-label" style="width: 80px">文件名称：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='fileName'   style='width: 149px'>
			    </td>
			    <td class="form-td-label" style="width: 80px">文件类型：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='fileType'   style='width: 149px'>
			    </td>
			  </tr>
			  <tr>
			    <td class="form-td-label" style="width: 80px">模块ID：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='resId'   style='width: 149px'>
			    </td>
			    <td class="form-td-label" style="width: 80px">上传人员：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='createUsername'   style='width: 149px'>
			    </td>
			  </tr>
			  <tr>
			    <td class="form-td-label" style="width: 80px">上传单位名称：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='createOrgzname'   style='width: 149px'>
			    </td>
			    <td colspan="2"></td><td align="center" width="170px">
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchSysFujianFun();">查询</a>
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleanSysFujianFun();">重置</a>
			    </td>
			  </tr>
			</table>
		</form>
	</div>

<!-- 【公共统一附件】结果列表 -->
	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
		<table id="dataGrid_sysFujian" title="公共统一附件" data-options="fit:true,border:false"></table>
	</div>

<div id="toolbar" style="display: none;">
    	<a onclick="reloadSysFujian();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<shiro:hasPermission name="/sys/base/sysFujian/add">
    	</shiro:hasPermission>
    	<a onclick="addSysFujianFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	<shiro:hasPermission name="/sys/base/sysFujian/edit">
    	</shiro:hasPermission>
    	<a onclick="editSysFujianFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	<shiro:hasPermission name="/sys/base/sysFujian/del">
    	</shiro:hasPermission>
    	<a onclick="deleteSysFujianFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	<a onclick="clearSysFujianSelections();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    	<a onclick="expSysFujianXls();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
</div>
<!-- 【公共统一附件】新增弹出框-->	<div id="addDialog_sysFujian" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal:true"	style="width:660px;height:378px;padding:10px 10px;display:none;" >
        <form id="addForm_sysFujian" method="post" data-options="novalidate:true">
			<table  class="grid">
			  <tr>
			    <td  class="form-td-label" >文件名称：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='fileName' id='add_fileName_sysFujian'  data-options="prompt:'请输入文件名称',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >文件路径：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='filePath' id='add_filePath_sysFujian'  data-options="prompt:'请输入文件路径',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >缩略图路径：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='minImgPath' id='add_minImgPath_sysFujian'  data-options="prompt:'请输入缩略图路径',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >文件类型：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='fileType' id='add_fileType_sysFujian'  data-options="prompt:'请输入文件类型',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >文件大小：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='fileSize' id='add_fileSize_sysFujian'  data-options="prompt:'请输入文件大小',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >模块ID：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='resId' id='add_resId_sysFujian'  data-options="prompt:'请输入模块ID',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >上传时间：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datetimebox" class="easyui-validatebox span2" name='uploadTime'  id='add_uploadTime_sysFujian' data-options="prompt:'请输入上传时间'" style='width: 150px'>			    </td>
			    <td  class="form-td-label" >上传人员ID：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='operatorId' id='add_operatorId_sysFujian'  data-options="prompt:'请输入上传人员ID',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >上传人员：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='operatorName' id='add_operatorName_sysFujian'  data-options="prompt:'请输入上传人员',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >上传单位ID：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='depId' id='add_depId_sysFujian'  data-options="prompt:'请输入上传单位ID',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >上传单位名称：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='depName' id='add_depName_sysFujian'  data-options="prompt:'请输入上传单位名称',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >引用表ID：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='foreignId' id='add_foreignId_sysFujian'  data-options="prompt:'请输入引用表ID',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >顺序号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='orderno' id='add_orderno_sysFujian'  data-options="prompt:'请输入顺序号',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >备用字段1：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='backupOne' id='add_backupOne_sysFujian'  data-options="prompt:'请输入备用字段1',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >备用字段2：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='backupTwo' id='add_backupTwo_sysFujian'  data-options="prompt:'请输入备用字段2',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="add-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="addSysFujianAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#addDialog_sysFujian').dialog('close')">关闭</a>
	</div>

<!-- 【公共统一附件】编辑弹出框 -->
	<div id="editDialog_sysFujian" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#edit-dialog-buttons',closed:true,cache: false,modal:true"
	style="width:660px;height:378px;padding:10px 10px;display:none;" >
        <form id="editForm_sysFujian" method="post" data-options="novalidate:true">
           <input name="id" type="hidden" >
           <input name="updateTime" type="hidden" >
			<table  class="grid">
			  <tr>
			    <td  class="form-td-label" >文件名称：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='fileName' id='edit_fileName_sysFujian'  data-options="prompt:'请输入文件名称',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >文件路径：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='filePath' id='edit_filePath_sysFujian'  data-options="prompt:'请输入文件路径',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >缩略图路径：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='minImgPath' id='edit_minImgPath_sysFujian'  data-options="prompt:'请输入缩略图路径',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >文件类型：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='fileType' id='edit_fileType_sysFujian'  data-options="prompt:'请输入文件类型',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >文件大小：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='fileSize' id='edit_fileSize_sysFujian'  data-options="prompt:'请输入文件大小',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >模块ID：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='resId' id='edit_resId_sysFujian'  data-options="prompt:'请输入模块ID',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >上传时间：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datetimebox" class="easyui-validatebox span2" name='uploadTime'  id='edit_uploadTime_sysFujian' data-options="prompt:'请输入上传时间'" style='width: 150px'>			    </td>
			    <td  class="form-td-label" >上传人员ID：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='operatorId' id='edit_operatorId_sysFujian'  data-options="prompt:'请输入上传人员ID',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >上传人员：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='operatorName' id='edit_operatorName_sysFujian'  data-options="prompt:'请输入上传人员',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >上传单位ID：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='depId' id='edit_depId_sysFujian'  data-options="prompt:'请输入上传单位ID',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >上传单位名称：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='depName' id='edit_depName_sysFujian'  data-options="prompt:'请输入上传单位名称',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >引用表ID：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='foreignId' id='edit_foreignId_sysFujian'  data-options="prompt:'请输入引用表ID',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >顺序号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='orderno' id='edit_orderno_sysFujian'  data-options="prompt:'请输入顺序号',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >备用字段1：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='backupOne' id='edit_backupOne_sysFujian'  data-options="prompt:'请输入备用字段1',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >备用字段2：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='backupTwo' id='edit_backupTwo_sysFujian'  data-options="prompt:'请输入备用字段2',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="edit-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="editSysFujianAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#editDialog_sysFujian').dialog('close')">关闭</a>
	</div>
</body>
</html>
