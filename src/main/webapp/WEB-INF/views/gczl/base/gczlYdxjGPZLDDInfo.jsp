<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>盾构施工质量基础数据</title>
<script type="text/javascript" src="${staticPath}/static/js/gczl/base/gczlYdxjGPZLDDInfo.js" charset="utf-8"></script>
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
<!-- 查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:70px;border: false;overflow: hidden;" data-options="collapsible:true">
		<form id="searchForm">
			<table class="grid" width='650px' border='0' cellspacing='0' style='padding:5px'>
			  <tr>
					<td class="form-td-label" style="width: 80px">质量问题分类：</td>
					<td class="form-td-content" style="width: 200px">
						<input name="typeName" class="easyui-textbox" data-options="prompt:'请选择质量问题分类'" id = "typeName_query_id" style="width: 100%;" />
					</td>
					<td class="form-td-label" style="width: 80px">基础数据名称：</td>
					<td class="form-td-content" style="width: 200px">
						<input name="ddName" class="easyui-textbox" data-options="prompt:'请输入基础数据名称',validType:'length[1,40]'" style="width: 100%;" />
					</td>
				    <td align="left" width="170px" >
				      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a>
				      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleanFun();">重置</a>
				    </td>
			  </tr>
			</table>
		</form>
	</div>
	<!-- 结果列表 -->
	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
		<table id="dataGrid" title="列表" data-options="fit:true,border:false"></table>
	</div>
	
	 <div id="toolbar" style="display: none;">
    	<a onclick="reload();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<shiro:hasPermission name="/gczl/base/gczlYdxjGPZLDDInfo/add">
    	<a onclick="addFun();"  href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/gczl/base/gczlYdxjGPZLDDInfo/edit">
    	<a onclick="editFun();" id="query_button_edit" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/gczl/base/gczlYdxjGPZLDDInfo/del">
    	<a onclick="deleteFun();" id="query_button_del" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	</shiro:hasPermission>
    	<a onclick="clearSelections();" id="query_button_unselect" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    	<shiro:hasPermission name="/gczl/base/gczlYdxjGPZLDDInfo/ExpExcel">
    	<a onclick="expXls();" href="javascript:void(0);"  id="query_button_expExcel" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
        </shiro:hasPermission>
    	<shiro:hasPermission name="/gczl/base/gczlYdxjGPZLDDInfo/ifQyQ">
    	<a onclick="ifQyQ();" href="javascript:void(0);" id="query_button_qiYong" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">启用</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/gczl/base/gczlYdxjGPZLDDInfo/ifQyJ">
    	<a onclick="ifQyJ();" href="javascript:void(0);" id="query_button_jinYong" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">禁用</a>
        </shiro:hasPermission>
    </div>
	
	<!-- 弹出框－新增 -->
	<div id="addDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:518px;height:278px;padding:10px 10px;display:none;" >
        <form id="addForm" method="post" data-options="novalidate:true">
            <table class="grid">
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>基础数据名称：</td>
                    <td class="form-td-content">
                    	<input name="ddName" class="easyui-textbox"  class="easyui-validatebox span2" data-options="required:true,prompt:'请输入基础数据名称',validType:'length[1,40]'" value="" style="width:130px;">
                    </td>
                    <td class="form-td-label"><span style="color: red;">*</span>质量问题分类：</td>
                    <td class="form-td-content">
                    	<input name="typeName" id="typeName_add_id" class="easyui-textbox"  class="easyui-validatebox span2" data-options="required:true,prompt:'请选择质量问题分类'" value="" style="width:130px;">
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label">使用状态</td>
                    <td class="form-td-content">
                    	<select name="ifQy" class="easyui-combobox" data-options="editable:false,prompt:'请选择使用状态',panelHeight:'auto'" style="width:130px;">
                    		<option selected="selected">启用</option>
                    		<option>禁用</option>
                    	</select>
                    </td>
                    <td class="form-td-label"><span style="color: red;">*</span>排&nbsp;&nbsp;序&nbsp;&nbsp;号：</td>
                    <td class="form-td-content">
                    	<input name="sortNum"  class="easyui-numberbox" data-options="required:true,prompt:'请输入排序号'" value="" style="width:130px;">
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label"  valign="top" align="right">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
                    <td class="form-td-content" colspan="3">
                    	<input name="remarks" class="easyui-textbox" data-options="required:false,prompt:'请输入备注',multiline:true,validType:'length[1,400]'" style="width:100%;height:60px">
                    </td>
                </tr>
            </table>
        </form>
	</div>
	<div id="add-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="addAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#addDialog').dialog('close')">关闭</a>
	</div>
	
	<!-- 弹出框－编辑 -->
	<div id="editDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#edit-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:518px;height:278px;padding:10px 10px;display:none;" >
        <form id="editForm" method="post" data-options="novalidate:true">
           <input name="id" type="hidden" >
           <input name="updateTime" type="hidden" >
           <table class="grid">
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>基础数据名称：</td>
                    <td class="form-td-content">
                    	<input name="ddName" class="easyui-textbox"  class="easyui-validatebox span2" data-options="required:true,prompt:'请输入基础数据名称',validType:'length[1,40]'" value="" style="width:130px;">
                    </td>
                    <td class="form-td-label"><span style="color: red;">*</span>质量问题分类：</td>
                    <td class="form-td-content">
                    	<input name="typeName" id="typeName_edit_id" class="easyui-textbox"  class="easyui-validatebox span2" data-options="required:true,prompt:'请选择质量问题分类'" value="" style="width:130px;">
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label">使用状态</td>
                    <td class="form-td-content">
                    	<select name="ifQy" class="easyui-combobox" data-options="editable:false,prompt:'请选择使用状态',panelHeight:'auto'" style="width:130px;">
                    		<option selected="selected">启用</option>
                    		<option>禁用</option>
                    	</select>
                    </td>
                    <td class="form-td-label"><span style="color: red;">*</span>排&nbsp;&nbsp;序&nbsp;&nbsp;号：</td>
                    <td class="form-td-content">
                    	<input name="sortNum"  class="easyui-numberbox" data-options="required:true,prompt:'请输入排序号'" value="" style="width:130px;">
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label" valign="top" align="right">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
                    <td class="form-td-content" colspan="3">
                    	<input name="remarks" class="easyui-textbox" data-options="required:false,prompt:'请输入备注',multiline:true,validType:'length[1,400]'" style="width:100%;height:60px">
                    </td>
                </tr>
            </table>
        </form>
	</div>
	<div id="edit-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="editAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#editDialog').dialog('close')">关闭</a>
	</div>
	
	<!-- 弹出框－查看 -->
	<div id="showDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#show-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:518px;height:278px;padding:10px 10px;display:none;" >
        <form id="showForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden" >
            <table class="grid">
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>基础数据名称：</td>
                    <td class="form-td-content">
                    	<input name="ddName" class="easyui-textbox"  class="easyui-validatebox span2" data-options="required:true" value="" style="width:130px;" readonly="readonly">
                    </td>
                    <td class="form-td-label"><span style="color: red;">*</span>质量问题分类：</td>
                    <td class="form-td-content">
                    	<input name="typeName" id="typeName_edit_id" class="easyui-textbox"  class="easyui-validatebox span2" data-options="required:true" value="" style="width:130px;"  readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label">使用状态</td>
                    <td class="form-td-content">
                    	<select name="ifQy" class="easyui-combobox" data-options="editable:false,panelHeight:'auto'" style="width:130px;"  readonly="readonly">
                    		<option selected="selected">启用</option>
                    		<option>禁用</option>
                    	</select>
                    </td>
                    <td class="form-td-label"><span style="color: red;">*</span>排&nbsp;&nbsp;序&nbsp;&nbsp;号：</td>
                    <td class="form-td-content">
                    	<input name="sortNum"  class="easyui-numberbox" data-options="required:true" value="" style="width:130px;" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
                    <td class="form-td-content" colspan="3">
                    	<input name="remarks" class="easyui-textbox" data-options="required:false,multiline:true,validType:'length[1,400]'" style="width:100%;height:60px" readonly="readonly">
                    </td>
                </tr>
            </table>
        </form>
	</div>
	<div id="show-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#showDialog').dialog('close')">关闭</a>
	</div>
</body>
</html>
