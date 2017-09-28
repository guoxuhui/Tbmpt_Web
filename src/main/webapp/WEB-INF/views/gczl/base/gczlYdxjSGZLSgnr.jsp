<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>施工内容管理</title>
<script type="text/javascript" src="${staticPath}/static/js/gczl/base/gczlYdxjSGZLSgnr.js" charset="utf-8"></script>
<style type="text/css">	td{		 white-space: nowrap;	}	td input{		white-space: normal;	}</style></head>
<body class="easyui-layout" data-options="fit:true,border:false">
<!-- 【施工内容管理】查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:61px;border: false;overflow: hidden;" data-options="collapsible:true">
		<form id="searchForm_gczlYdxjSGZLSgnr">
			<table class="grid" width='650px' border='0' cellspacing='0' style='padding:5px'>
			  <tr>
			    <td class="form-td-label" style="width: 80px">施工内容：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='sgNr'   style='width: 149px' data-options="prompt:'请输入施工内容'" >
			    </td>
			    <td align="left" width="170px" >
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchGczlYdxjSGZLSgnrFun();">查询</a>
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleanGczlYdxjSGZLSgnrFun();">重置</a>
			    </td>
			  </tr>
			</table>
		</form>
	</div>

<!-- 【施工内容管理】结果列表 -->
	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
		<table id="dataGrid_gczlYdxjSGZLSgnr" title="列表" data-options="fit:true,border:false"></table>
	</div>
<div id="toolbar" style="display: none;">
    	        <a onclick="reloadGczlYdxjSGZLSgnr();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	    	<shiro:hasPermission name="/gczl/base/gczlYdxjSGZLSgnr/add">
    	    	<a onclick="addGczlYdxjSGZLSgnrFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	    	</shiro:hasPermission>
				<shiro:hasPermission name="/gczl/base/gczlYdxjSGZLSgnr/edit">
    	    	<a onclick="editGczlYdxjSGZLSgnrFun();" id="edit_button_gczlYdxjSGZLSgnr" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	    	</shiro:hasPermission>    	
    	    	<shiro:hasPermission name="/gczl/base/gczlYdxjSGZLSgnr/delete">
    	    	<a onclick="deleteGczlYdxjSGZLSgnrFun();" href="javascript:void(0);" id="del_button_gczlYdxjSGZLSgnr" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	    	</shiro:hasPermission>
		    	<a onclick="clearGczlYdxjSGZLSgnrSelections();" href="javascript:void(0);" id="cancelSelect_button_gczlYdxjSGZLSgnr" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a> 
		    	<a onclick="expGczlYdxjSGZLSgnrXls();" href="javascript:void(0);" id="expExcel_gczlYdxjSGZLSgnr" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
		    	<a onclick="expGczlYdxjSGZLSgnrPdf();" href="javascript:void(0);" id="expPdf_gczlYdxjSGZLSgnr" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_white_acrobat'">导出Pdf</a>
		    	<a onclick="editJtwzFun();" href="javascript:void(0);" id="jtwz_button_gczlYdxjSGZLSgnr" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">设置具体位置</a>
		    	<a onclick="editZlqxFun();" href="javascript:void(0);" id="zlqx_button_gczlYdxjSGZLSgnr" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">设置质量缺陷</a>
		    	<a onclick="ifQyQ();" href="javascript:void(0);" id="qy_button_gczlYdxjSGZLSgnr" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">启用</a>
		    	<a onclick="ifQyJ();" href="javascript:void(0);" id="jy_button_gczlYdxjSGZLSgnr" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">禁用</a>
  </div>
<!-- 【施工内容管理】新增弹出框-->	
<div id="addDialog_gczlYdxjSGZLSgnr" class="easyui-dialog" 
   data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal: true"	
      style="width:540px;height:338px;padding:10px 10px;display:none; overflow: hidden;" >
        <form id="addForm_gczlYdxjSGZLSgnr" method="post" data-options="novalidate:true">
			<table  class="grid">
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" ><span style="color: red;">*</span>施工内容：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sgNr'  id='add_sgNr_gczlYdxjSGZLSgnr'  data-options="prompt:'请输入施工内容',multiline:true,validType:'length[1,40]',required:true"  style='width: 100%;height:60px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >排序号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-numberspinner" class="easyui-validatebox span2"  name='sortNum' id='add_sortNum_gczlYdxjSGZLSgnr'  data-options="min:0,prompt:'请输入排序号',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >使用状态：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='ifQy' panelHeight="50px" id='add_ifQy_gczlYdxjSGZLSgnr'  data-options="prompt:'请输入使用状态',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >备注：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='remarks'  id='add_remarks_gczlYdxjSGZLSgnr'  data-options="prompt:'请输入备注',multiline:true,validType:'length[1,400]'"  style='width: 100%;height:60px'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="add-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="addGczlYdxjSGZLSgnrAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#addDialog_gczlYdxjSGZLSgnr').dialog('close')">关闭</a>
	</div>

<!-- 【施工内容管理】编辑弹出框 -->
	<div id="editDialog_gczlYdxjSGZLSgnr" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#edit-dialog-buttons',closed:true,cache: false,modal: true"
	style="width:540px;height:288px;padding:10px 10px;display:none; overflow: hidden;" >
        <form id="editForm_gczlYdxjSGZLSgnr" method="post" data-options="novalidate:true">
           <input name="id" type="hidden" >
           
           <input name="updateUser" type="hidden" >
           <input name="updateUsername" type="hidden" >
           <input name="updateOrgz" type="hidden" >
           <input name="updateOrgzname" type="hidden" >
           <input name="updateTime" type="hidden" >
           
			<table  class="grid">
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" ><span style="color: red;">*</span>施工内容：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sgNr'  id='edit_sgNr_gczlYdxjSGZLSgnr'  data-options="prompt:'请输入施工内容',multiline:true,validType:'length[1,40]',required:true"  style='width: 100%;height:60px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >排序号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-numberspinner" class="easyui-validatebox span2"  name='sortNum' id='edit_sortNum_gczlYdxjSGZLSgnr'  data-options="min:0,prompt:'请输入排序号',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >使用状态：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='ifQy' panelHeight="50px"  id='edit_ifQy_gczlYdxjSGZLSgnr'  data-options="prompt:'请输入使用状态',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >备注：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='remarks'  id='edit_remarks_gczlYdxjSGZLSgnr'  data-options="prompt:'请输入备注',multiline:true,validType:'length[1,400]'"  style='width: 100%;height:60px'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="edit-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="editGczlYdxjSGZLSgnrAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#editDialog_gczlYdxjSGZLSgnr').dialog('close')">关闭</a>
	</div>
	<!-- 【施工内容管理】查看弹出框 -->
<div id="showSgNrDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#showSgNr-dialog-buttons',closed:true,cache: false,modal: true"	style="width:650px;height:500px;padding:0px 0px;display:none;" >
    	  <form id="showSgNrForm"  >
           <input name="id" type="hidden" >
			<table  class="grid">
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >施工内容：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  disabled="disabled" name='sgNr'  id='show_sgNr_gczlYdxjSGZLSgnr'   style='width: 100%'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >排序号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-numberspinner" class="easyui-validatebox span2"  disabled="disabled" name='sortNum' id='show_sortNum_gczlYdxjSGZLSgnr' data-options="min:0"    style='width: 220px'>
			    </td>
			    <td  class="form-td-label" >使用状态：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='ifQy'  disabled="disabled" panelHeight="50px"  id='show_ifQy_gczlYdxjSGZLSgnr'    style='width: 220px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >备注：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='remarks'  disabled="disabled"  id='show_remarks_gczlYdxjSGZLSgnr'  data-options="multiline:true" style='width: 100%;height:60px'>
			    </td>
			  </tr>
			</table>
        </form>
        <table id="show_dataGrid_gczlYdxjSGZLJtwz" title="结构施工质量基础数据-具体位置" data-options="border:false" style='width: 97%;height:210px'></table>
        <table id="show_dataGrid_gczlYdxjSGZLZlqx" title="结构施工质量基础数据-质量缺陷" data-options="border:false" style='width: 97%;height:210px'></table>
	</div>
	<div id="showSgNr-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#showSgNrDialog').dialog('close')">关闭</a>
	</div>
	
	
	
<!--==========================================【结构施工质量基础信息--具体位置信息】操作方法========================================================= -->	
	<!-- 【结构施工质量基础数据-具体位置】结果列表 -->
    <div id="gczlYdxjSGZLJtwz_list" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#jtwz-dialog-buttons',closed:true,cache: false,modal: true"	style="width:680px;height:500px;padding:10px 10px;display:none; overflow: hidden;" >
    	<form id="jtwzForm_sgnr">
			<table  class="grid">
			 <input name="id"  id="jtwz_sgnr_id" type="hidden">
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >施工内容：</td>
			    <td class="form-td-content" colspan='3' >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  disabled="disabled" name='sgNr'  id='jtwz_sgNr_gczlYdxjSGZLSgnr'  data-options="multiline:false"  style='width: 100%;'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >排序号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-numberspinner" class="easyui-validatebox span2" disabled="disabled" name='sortNum' id='jtwz_sortNum_gczlYdxjSGZLSgnr'  data-options="min:0"   style='width: 230px'>
			    </td>
			    <td  class="form-td-label" >使用状态：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='ifQy'  disabled="disabled" panelHeight="50px"  id='jtwz_ifQy_gczlYdxjSGZLSgnr'    style='width: 230px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >备注：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='remarks'  disabled="disabled"  id='jtwz_remarks_gczlYdxjSGZLSgnr'  data-options="multiline:true" style='width: 100%;height:60px'>
			    </td>
			  </tr>
			</table>
		</form>
		<table id="dataGrid_gczlYdxjSGZLJtwz" title="结构施工质量基础数据-具体位置" data-options="border:false" style='height:270px'></table>
	</div>
	<div id="jtwz-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#gczlYdxjSGZLJtwz_list').dialog('close')">关闭</a>
	</div>
	

<!-- 【结构施工质量基础数据-具体位置】新增弹出框-->	
<div id="addDialog_gczlYdxjSGZLJtwz" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal: true"	style="width:550px;height:300px;padding:10px 10px;display:none; overflow: hidden;" >
        <form id="addForm_gczlYdxjSGZLJtwz" method="post" data-options="novalidate:true">
             <input name="sgNr" id='add_sgNr_gczlYdxjSGZLJtwz' type="hidden" >
			<table  class="grid">
			  <tr >
			     <td  class="form-td-label" >施工内容：</td>
			    <td  colspan='3'>
			     <span id="add_sgNrName_gczlYdxjSGZLJtwz"></span>
			    </td>
			  </tr>
			  <tr>
			      <td  class="form-td-label" ><span style="color: red;">*</span>具体位置名称：</td>
			    <td class="form-td-content" colspan="3" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='jtWz' id='add_jtWz_gczlYdxjSGZLJtwz'  data-options="prompt:'请输入具体位置名称',validType:'length[1,40]',required:true"  style='width: 100%;'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >排序号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-numberspinner" class="easyui-validatebox span2"  name='sortNum' id='add_sortNum_gczlYdxjSGZLJtwz'  data-options="min:0,prompt:'请输入排序号',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >使用状态：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"   panelHeight="50px" name='ifQy' id='add_ifQy_gczlYdxjSGZLJtwz'  data-options="prompt:'请输入使用状态',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			   
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >具体位置说明：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='remarks'  id='add_remarks_gczlYdxjSGZLJtwz'  data-options="prompt:'请输入备注',multiline:true,validType:'length[1,40]'"  style='width: 100%;height:60px'>
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
	<div id="editDialog_gczlYdxjSGZLJtwz" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#edit-dialog-buttons',closed:true,cache: false,modal: true"
	style="width:550px;height:320px;padding:10px 10px;display:none; overflow: hidden;" >
        <form id="editForm_gczlYdxjSGZLJtwz" method="post" data-options="novalidate:true">
           <input name="id" type="hidden" >
           <input name="sgNr" type="hidden" >
           
           <input name="updateUser" type="hidden" >
           <input name="updateUsername" type="hidden" >
           <input name="updateOrgz" type="hidden" >
           <input name="updateOrgzname" type="hidden" >
           <input name="updateTime" type="hidden" >
           
			<table  class="grid">
			    <tr >
			     <td  class="form-td-label" >施工内容：</td>
			    <td  colspan='3'>
			     <span id="edit_sgNrName_gczlYdxjSGZLJtwz"></span>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>具体位置名称：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='jtWz' id='edit_jtWz_gczlYdxjSGZLJtwz'  data-options="prompt:'请输入具体位置名称',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >排序号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-numberspinner" class="easyui-validatebox span2"  name='sortNum' id='edit_sortNum_gczlYdxjSGZLJtwz'  data-options="min:0,prompt:'请输入排序号',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >使用状态：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  panelHeight="50px"  name='ifQy' id='edit_ifQy_gczlYdxjSGZLJtwz'  data-options="prompt:'请输入使用状态',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >具体位置说明：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='remarks'  id='edit_remarks_gczlYdxjSGZLJtwz'  data-options="prompt:'请输入备注',multiline:true,validType:'length[1,40]'"  style='width: 100%;height:60px'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="edit-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="editGczlYdxjSGZLJtwzAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#editDialog_gczlYdxjSGZLJtwz').dialog('close')">关闭</a>
	</div>
	
	
	<!-- 【结构施工质量基础数据-具体位置】查看弹出框 -->
	<div id="showDialog_gczlYdxjSGZLJtwz" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#jtwz_show-dialog-buttons',closed:true,cache: false,modal: true"
	style="width:550px;height:320px;padding:10px 10px;display:none; overflow: hidden;" >
        <form id="showForm_gczlYdxjSGZLJtwz" method="post" data-options="novalidate:true">
           <input name="id" type="hidden" >
           <input name="sgNr" type="hidden" >
			<table  class="grid">
			    <tr >
			     <td  class="form-td-label" >施工内容：</td>
			    <td  colspan='3'>
			     <span id="show_sgNrName_gczlYdxjSGZLJtwz"></span>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >具体位置名称：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='jtWz' id='show_jtWz_gczlYdxjSGZLJtwz' disabled="disabled"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >排序号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-numberspinner" class="easyui-validatebox span2"  name='sortNum' id='show_sortNum_gczlYdxjSGZLJtwz' data-options="min:0"   disabled="disabled" style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >使用状态：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  panelHeight="50px"  name='ifQy' id='show_ifQy_gczlYdxjSGZLJtwz'  disabled="disabled" style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >具体位置说明：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='remarks'  id='show_remarks_gczlYdxjSGZLJtwz'   data-options="multiline:true" disabled="disabled"  style='width: 100%;height:60px'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="jtwz_show-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#showDialog_gczlYdxjSGZLJtwz').dialog('close')">关闭</a>
	</div>
<!--==========================================【结构施工质量基础信息--质量缺陷信息】操作方法========================================================= -->	

<!-- 【结构施工质量基础数据-质量缺陷】结果列表 -->
    <div id="gczlYdxjSGZLZlqx_list" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#zlqx-dialog-buttons',closed:true,cache: false,modal: true"	style="width:680px;height:500px;padding:10px 10px;display:none; overflow: hidden;" >
    	<form id="zlqxForm_sgnr">
			<table  class="grid">
			 <input name="id"  id="zlqx_sgnr_id" type="hidden">
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >施工内容：</td>
			    <td class="form-td-content" colspan='3' >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  disabled="disabled" name='sgNr'  id='zlqx_sgNr_gczlYdxjSGZLSgnr'  data-options="multiline:false"  style='width: 100%;'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >排序号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-numberspinner" class="easyui-validatebox span2"  disabled="disabled" name='sortNum' id='zlqx_sortNum_gczlYdxjSGZLSgnr' data-options="min:0"   style='width: 230px'>
			    </td>
			    <td  class="form-td-label" >使用状态：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='ifQy'  disabled="disabled" panelHeight="50px"  id='zlqx_ifQy_gczlYdxjSGZLSgnr'    style='width: 230px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >备注：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='remarks'  disabled="disabled"  id='zlqx_remarks_gczlYdxjSGZLSgnr'  data-options="multiline:true" style='width: 100%;height:60px'>
			    </td>
			  </tr>
			</table>
		</form>
		<table id="dataGrid_gczlYdxjSGZLZlqx" title="结构施工质量基础数据-质量缺陷" data-options="border:false" style='height:270px'></table>
	</div>
	<div id="zlqx-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#gczlYdxjSGZLZlqx_list').dialog('close')">关闭</a>
	</div>
	
	
<!-- 【结构施工质量基础数据-质量缺陷】新增弹出框-->	
<div id="addDialog_gczlYdxjSGZLZlqx" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal: true"	style="width:600px;height:328px;padding:10px 10px;display:none; overflow: hidden;" >
        <form id="addForm_gczlYdxjSGZLZlqx" method="post" data-options="novalidate:true">
           <input name="sgNr" id='add_sgNr_gczlYdxjSGZLZlqx' type="hidden" >
			<table  class="grid">
			  <tr >
			     <td  class="form-td-label" >施工内容：</td>
			    <td  colspan='3'>
			     <span id="add_sgNrName_gczlYdxjSGZLZlqx"></span>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" ><span style="color: red;">*</span>质量缺陷名称：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='zlQx'  id='add_zlQx_gczlYdxjSGZLZlqx'  data-options="prompt:'请输入存在质量问题、缺陷说明的名称',validType:'length[1,40]',required:true"  style='width: 100%'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >排序号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-numberspinner" class="easyui-validatebox span2"  name='sortNum' id='add_sortNum_gczlYdxjSGZLZlqx'  data-options="min:0,prompt:'请输入排序号',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >使用状态：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='ifQy' panelHeight="50px" id='add_ifQy_gczlYdxjSGZLZlqx'  data-options="prompt:'请输入使用状态',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >质量缺陷说明：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='remarks'  id='add_remarks_gczlYdxjSGZLZlqx'  data-options="prompt:'请输入质量缺陷说明',multiline:true,validType:'length[1,400]'"  style='width: 100%;height:60px'>
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
	<div id="editDialog_gczlYdxjSGZLZlqx" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#edit-dialog-buttons',closed:true,cache: false,modal: true"
	style="width:600px;height:328px;padding:10px 10px;display:none; overflow: hidden;" >
        <form id="editForm_gczlYdxjSGZLZlqx" method="post" data-options="novalidate:true">
           <input name="id" type="hidden" >
           <input name="sgNr" type="hidden" >
           
           <input name="updateUser" type="hidden" >
           <input name="updateUsername" type="hidden" >
           <input name="updateOrgz" type="hidden" >
           <input name="updateOrgzname" type="hidden" >
           <input name="updateTime" type="hidden" >
           
			<table  class="grid">
			 <tr >
			     <td  class="form-td-label" >施工内容：</td>
			    <td  colspan='3'>
			     <span id="edit_sgNrName_gczlYdxjSGZLZlqx"></span>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" ><span style="color: red;">*</span>质量缺陷名称：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='zlQx'  id='edit_zlQx_gczlYdxjSGZLZlqx'  data-options="prompt:'请输入存在质量问题、缺陷说明的名称',validType:'length[1,40]',required:true"  style='width: 100%'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >排序号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-numberspinner" class="easyui-validatebox span2"  name='sortNum' id='edit_sortNum_gczlYdxjSGZLZlqx'  data-options="min:0,prompt:'请输入排序号',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >使用状态：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='ifQy' panelHeight="50px" id='edit_ifQy_gczlYdxjSGZLZlqx'  data-options="prompt:'请输入使用状态',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >质量缺陷说明：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='remarks'  id='edit_remarks_gczlYdxjSGZLZlqx'  data-options="prompt:'请输入质量缺陷说明',multiline:true,validType:'length[1,400]'"  style='width: 100%;height:60px'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="edit-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="editGczlYdxjSGZLZlqxAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#editDialog_gczlYdxjSGZLZlqx').dialog('close')">关闭</a>
	</div>
	
	<!-- 【结构施工质量基础数据-具体位置】查看弹出框 -->
	<div id="showDialog_gczlYdxjSGZLZlqx" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#zlqx_show-dialog-buttons',closed:true,cache: false,modal: true"
	style="width:550px;height:320px;padding:10px 10px;display:none; overflow: hidden;" >
        <form id="showForm_gczlYdxjSGZLZlqx" method="post" data-options="novalidate:true">
           <input name="id" type="hidden" >
           <input name="sgNr" type="hidden" >
			<table  class="grid">
			 <tr >
			     <td  class="form-td-label" >施工内容：</td>
			    <td  colspan='3'>
			     <span id="show_sgNrName_gczlYdxjSGZLZlqx"></span>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >质量缺陷名称：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='zlQx'  id='show_zlQx_gczlYdxjSGZLZlqx' disabled="disabled" data-options="validType:'length[1,40]',required:true"  style='width: 100%'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >排序号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-numberspinner" class="easyui-validatebox span2"  name='sortNum' id='show_sortNum_gczlYdxjSGZLZlqx'  disabled="disabled"   data-options="min:0,validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >使用状态：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='ifQy' panelHeight="50px" id='show_ifQy_gczlYdxjSGZLZlqx' disabled="disabled"  data-options="validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >质量缺陷说明：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='remarks'  id='show_remarks_gczlYdxjSGZLZlqx' disabled="disabled"  data-options="multiline:true,validType:'length[1,400]'"  style='width: 100%;height:60px'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="zlqx_show-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#showDialog_gczlYdxjSGZLZlqx').dialog('close')">关闭</a>
	</div>
</body>
</html>
