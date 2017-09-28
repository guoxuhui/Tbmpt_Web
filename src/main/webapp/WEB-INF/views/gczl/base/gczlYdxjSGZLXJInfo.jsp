<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>结构施工质量巡检管理</title>
<script type="text/javascript" src="${staticPath}/static/js/gczl/base/gczlYdxjSGZLXJInfo.js?v=20170616154729" charset="utf-8"></script>

<style type="text/css">	td{		 white-space: nowrap;	}	td input{		white-space: normal;	}</style></head>
<body class="easyui-layout" data-options="fit:true,border:false">
<jsp:include  page="../../sys/base/uploadPicture.jsp" />
<!-- 【结构施工质量巡检管理】查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:0px;border: false;overflow: auto;" data-options="collapsible:true">
		<form id="searchForm_gczlYdxjSGZLXJInfo">
			<table class="grid" width='650px' border='0' cellspacing='0' style='padding:5px'>
			  <tr>
			    <td class="form-td-label" style="width: 80px">所属工程：</td><td align="left" width="160px" colspan="3">
			      <input   class="easyui-textbox"  name='gcBh' id="search_gcBh_gczlYdxjSGZLXJInfo" data-options="prompt:'请选择所属工程'" style='width: 100%'>
			    </td>
			    <td class="form-td-label" style="width: 80px">单位工程：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='dwgcBh' id="search_dwgcBh_gczlYdxjSGZLXJInfo"  data-options="prompt:'请选择单位工程'" style='width: 149px'>
			    </td>
			     <td class="form-td-label" style="width: 80px">分部工程：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='fbGcbh' id="search_fbGcbh_gczlYdxjSGZLXJInfo" data-options="prompt:'请选择分部工程'"  style='width: 149px'>
			    </td>
			  </tr>
			  <tr>
			    <td class="form-td-label" style="width: 80px">施工内容：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='sgNr'  id="search_sgNr_gczlYdxjSGZLXJInfo"  data-options="prompt:'请选择施工内容'" style='width: 149px'>
			    </td>
			    <td class="form-td-label" style="width: 80px">具体位置：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='jtWz'  id="search_jtWz_gczlYdxjSGZLXJInfo"  data-options="prompt:'请选择具体位置'" style='width: 149px'>
			    </td>
			     <td class="form-td-label" style="width: 80px">质量缺陷：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='zlQx' id="search_zlQx_gczlYdxjSGZLXJInfo"   data-options="prompt:'请选择质量缺陷'" style='width: 149px'>
			    </td>
			  <td class="form-td-label" style="width: 80px">起始巡检时间：</td><td align="left"  width="160px">
			      <input   class="easyui-datetimebox"  name='startXjTime'   data-options="prompt:'请选择起始巡检时间'" style='width: 149px'>
			    </td>
			    
			  </tr>
			  <tr>
			    <td class="form-td-label" style="width: 80px">截止巡检时间：</td><td align="left" width="160px">
			      <input   class="easyui-datetimebox"  name='endXjTime'   data-options="prompt:'请选择截止巡检时间'" style='width: 149px'>
			    </td>
			     <td class="form-td-label" style="width: 80px">上报状态：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='sbZt' id="search_sbZt"   data-options="prompt:'请选择上报状态'" style='width: 149px'>
			    </td>
			    <td class="form-td-label" style="width: 80px">审核状态：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='shZt'  id="search_shZt" data-options="prompt:'请选择审核状态'" style='width: 149px'>
			    </td>
			    <td class="form-td-label" style="width: 80px">整改状态：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='zgZt'  id="search_zgZt"  data-options="prompt:'请选择整改状态'" style='width: 149px'>
			    </td>
			     <td align="center" width="170px">
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchGczlYdxjSGZLXJInfoFun();">查询</a>
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleanGczlYdxjSGZLXJInfoFun();">重置</a>
			    </td>
			  </tr>
			</table>
		</form>
	</div>

<!-- 【结构施工质量巡检管理】结果列表 -->
	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
		<table id="dataGrid_gczlYdxjSGZLXJInfo" title="列表" data-options="fit:true,border:false"></table>
	</div>
	
	<div id="toolbar" style="display: none;">
    	        <a onclick="reloadGczlYdxjSGZLXJInfo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	    	<shiro:hasPermission name="/gczl/base/gczlYdxjSGZLXJInfo/add">
    	    	<a onclick="addGczlYdxjSGZLXJInfoFun();" href="javascript:void(0);" id="add_button_gczlYdxjSGZLXJInfo" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	    	</shiro:hasPermission>
				<shiro:hasPermission name="/gczl/base/gczlYdxjSGZLXJInfo/edit">
    	    	<a onclick="editGczlYdxjSGZLXJInfoFun();" id="edit_button_gczlYdxjSGZLXJInfo" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	    	</shiro:hasPermission>    	
    	    	<shiro:hasPermission name="/gczl/base/gczlYdxjSGZLXJInfo/delete">
    	    	<a onclick="deleteGczlYdxjSGZLXJInfoFun();" href="javascript:void(0);" id="del_button_gczlYdxjSGZLXJInfo" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	    	</shiro:hasPermission>
		    	<a onclick="clearGczlYdxjSGZLXJInfoSelections();" href="javascript:void(0);" id="cancelSelect_button_gczlYdxjSGZLXJInfo" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a> 
		    	<a onclick="expGczlYdxjSGZLXJInfoXls();" href="javascript:void(0);" id="expExcel_gczlYdxjSGZLXJInfo" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
		    	<a onclick="expGczlYdxjSGZLXJInfoPdf();" href="javascript:void(0);" id="expPdf_gczlYdxjSGZLXJInfo" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_white_acrobat'">导出Pdf</a>
		    	<shiro:hasPermission name="/gczl/base/gczlYdxjSGZLXJInfo/sbInfo">
		    	<a onclick="sbInfo();" href="javascript:void(0);" id="sb_button_gczlYdxjSGZLXJInfo" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">上报</a>
		    	</shiro:hasPermission>
		    	<shiro:hasPermission name="/gczl/base/gczlYdxjSGZLXJInfo/shInfo">
		    	<a onclick="shInfo();" href="javascript:void(0);" id="sh_button_gczlYdxjSGZLXJInfo" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">审核</a>
		    	</shiro:hasPermission>
		    	<shiro:hasPermission name="/gczl/base/gczlYdxjSGZLXJInfo/zgInfo">
		    	<a onclick="zgfkInfo();" href="javascript:void(0);" id="zgfk_button_gczlYdxjSGZLXJInfo" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">整改反馈</a>
		    	</shiro:hasPermission>
		    	<shiro:hasPermission name="/gczl/base/gczlYdxjSGZLXJInfo/upload">
		    	<a onclick="myUploadPicture('0');" href="javascript:void(0);" id="scxctp_button_gczlYdxjSGZLXJInfo" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-20130406125647919_easyicon_net_16'">上传整改前图片</a>
		    	</shiro:hasPermission>
		    	<shiro:hasPermission name="/gczl/base/gczlYdxjSGZLXJInfo/zghupload">
		    	<a onclick="myUploadPicture('1');" href="javascript:void(0);" id="sczghtp_button_gczlYdxjSGZLXJInfo" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-20130406125647919_easyicon_net_16'">上传整改后图片</a>
		    	</shiro:hasPermission>
  </div>

<!-- 【结构施工质量巡检管理】新增弹出框-->	
<div id="addDialog_gczlYdxjSGZLXJInfo" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal: true"	style="width:580px;height:388px;padding:10px 10px;display:none;" >
        <form id="addForm_gczlYdxjSGZLXJInfo" method="post" data-options="novalidate:true">
           <input type="hidden" name="gcBh"  id="add_gcBh_gczlYdxjSGZLXJInfo">
			<table  class="grid">
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>所属工程：</td>
			    <td class="form-td-content"  colspan="3">
			      <span id="add_gcName_gczlYdxjSGZLXJInfo"></span>
			    </td>
			  </tr>
			   <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>单位工程：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='dwgcBh' id='add_dwgcBh_gczlYdxjSGZLXJInfo'  data-options="prompt:'请选择',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" ><span style="color: red;">*</span>分部工程：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='fbGcbh' id='add_fbGcbh_gczlYdxjSGZLXJInfo'  data-options="prompt:'请选择',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>施工段：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sgd' id='add_sgd_gczlYdxjSGZLXJInfo'  data-options="prompt:'请输入施工段',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			     <td  class="form-td-label"  valign="top" align="right" ><span style="color: red;">*</span>施工内容：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sgNr'  id='add_sgNr_gczlYdxjSGZLXJInfo'  data-options="prompt:'请选择',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>具体位置：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='jtWz' id='add_jtWz_gczlYdxjSGZLXJInfo'  data-options="prompt:'请选择',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label"  valign="top" align="right" ><span style="color: red;">*</span>质量缺陷：</td>
			    <td class="form-td-content"  >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='zlQx'  id='add_zlQx_gczlYdxjSGZLXJInfo'  data-options="prompt:'请选择',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			     <td  class="form-td-label" ><span style="color: red;">*</span>巡检时间：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datetimebox" class="easyui-validatebox span2" name='xjTime'  id='add_xjTime_gczlYdxjSGZLXJInfo' data-options="prompt:'请输入巡检时间'" style='width: 150px'>		
			    </td>
			    <td></td>
			     <td></td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >其他需说明情况：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='remarks'  id='add_remarks_gczlYdxjSGZLXJInfo'  data-options="prompt:'请输入其他需说明情况',multiline:true,validType:'length[1,40]'"  style='width: 100%;height:60px'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="add-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="addGczlYdxjSGZLXJInfoAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#addDialog_gczlYdxjSGZLXJInfo').dialog('close')">关闭</a>
	</div>

<!-- 【结构施工质量巡检管理】编辑弹出框 -->
	<div id="editDialog_gczlYdxjSGZLXJInfo" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#edit-dialog-buttons',closed:true,cache: false,modal: true"
	style="width:800px;height:500px;padding:10px 10px;display:none;" >
        <form id="editForm_gczlYdxjSGZLXJInfo" method="post" data-options="novalidate:true">
           <input name="id" type="hidden" >
		   <input name="gcBh" type="hidden" >
		   <input name="zgFkry" type="hidden" >
		   <input name="zgBm" type="hidden" >
		   <input name="zgRy" type="hidden" >
		   <input name="zgTime" type="hidden" >
		   
		    <input name="updateUser" type="hidden" >
           <input name="updateUsername" type="hidden" >
           <input name="updateOrgz" type="hidden" >
           <input name="updateOrgzname" type="hidden" >
           <input name="updateTime" type="hidden" >
           
			<table  class="grid">
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>所属工程：</td>
			    <td class="form-td-content"  colspan="5">
			      <span id="edit_gcName_gczlYdxjSGZLXJInfo"></span>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>单位工程：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='dwgcBh' id='edit_dwgcBh_gczlYdxjSGZLXJInfo'  data-options="prompt:'请选择',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			      <td></td>  <td></td>
			    <td  class="form-td-label" ><span style="color: red;">*</span>分部工程：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='fbGcbh' id='edit_fbGcbh_gczlYdxjSGZLXJInfo'  data-options="prompt:'请选择',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			    
			  
			  </tr>
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>施工段：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sgd' id='edit_sgd_gczlYdxjSGZLXJInfo'  data-options="prompt:'请输入施工段',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			       <td></td>  <td></td>
			    <td  class="form-td-label"  valign="top" align="right" ><span style="color: red;">*</span>施工内容：</td>
			    <td class="form-td-content"  >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sgNr'  id='edit_sgNr_gczlYdxjSGZLXJInfo'  data-options="prompt:'请选择',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>具体位置：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='jtWz' id='edit_jtWz_gczlYdxjSGZLXJInfo'  data-options="prompt:'请选择',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			     <td></td>  <td></td>
			    <td  class="form-td-label"  valign="top" align="right" ><span style="color: red;">*</span>质量缺陷：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='zlQx'  id='edit_zlQx_gczlYdxjSGZLXJInfo'  data-options="prompt:'请选择',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			     <td  class="form-td-label" ><span style="color: red;">*</span>巡检时间：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datetimebox" class="easyui-validatebox span2" name='xjTime'  id='edit_xjTime_gczlYdxjSGZLXJInfo' data-options="prompt:'请输入巡检时间'" style='width: 150px'>	
			    </td>
			      <td></td>  <td></td>
			      <td></td>  <td></td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >其他需说明情况：</td>
			    <td class="form-td-content"  colspan='5'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='remarks'  id='edit_remarks_gczlYdxjSGZLXJInfo'  data-options="prompt:'请输入其他需说明情况',multiline:true,validType:'length[1,40]'"  style='width: 100%;height:50px'>
			    </td>
			  </tr>
			   <tr>
			    <td  class="form-td-label"   align="right" >上报状态：</td>
			    <td class="form-td-content"  >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sbZt'  id='edit_sbZt_gczlYdxjSGZLXJInfo'  disabled="disabled"  style='width: 150px'>
			    </td>
			     <td  class="form-td-label"   align="right" >审核状态：</td>
			    <td class="form-td-content"  >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='shZt'  id='edit_shZt_gczlYdxjSGZLXJInfo'  disabled="disabled"  style='width: 150px'>
			    </td>
			     <td  class="form-td-label"   align="right" >整改状态：</td>
			    <td class="form-td-content"  >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='zgZt'  id='edit_zgZt_gczlYdxjSGZLXJInfo'  disabled="disabled"  style='width: 150px'>
			    </td>
			  </tr>
			   <tr>
			    <td  class="form-td-label"   align="right" >维护时间：</td>
			    <td class="form-td-content"  >
			      <input class="easyui-datetimebox" class="easyui-validatebox span2"  name='whTime'  id='edit_whTime_gczlYdxjSGZLXJInfo'  disabled="disabled"  style='width: 150px'>
			    </td>
			     <td  class="form-td-label" >巡检部门：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2" name='xjBmName'  id='edit_xjBmName_gczlYdxjSGZLXJInfo' disabled="disabled" style='width: 150px'>		
			    </td>
			    <td  class="form-td-label" >巡检人员：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2" name='xjRyName'  id='edit_xjRyName_gczlYdxjSGZLXJInfo' disabled="disabled" style='width: 150px'>		
			    </td>
			  </tr>
			   <tr>
			    <td  class="form-td-label"   align="right" >上报时间：</td>
			    <td class="form-td-content"  >
			      <input class="easyui-datetimebox" class="easyui-validatebox span2"  name='sbTime'  id='edit_sbTime_gczlYdxjSGZLXJInfo'  disabled="disabled"  style='width: 150px'>
			    </td>
			     <td  class="form-td-label" >上报部门：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2" name='sbBmName'  id='edit_sbBmName_gczlYdxjSGZLXJInfo' disabled="disabled" style='width: 150px'>		
			    </td>
			     <td  class="form-td-label" >上报人员：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2" name='sbRyName'  id='edit_sbRyName_gczlYdxjSGZLXJInfo' disabled="disabled" style='width: 150px'>		
			    </td>
			  </tr>
			   <tr>
			    <td  class="form-td-label"   align="right" >审核时间：</td>
			    <td class="form-td-content"  >
			      <input class="easyui-datetimebox" class="easyui-validatebox span2"  name='shTime'  id='edit_shTime_gczlYdxjSGZLXJInfo'  disabled="disabled"  style='width: 150px'>
			    </td>
			     <td  class="form-td-label" >审核部门：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2" name='shBmName'  id='edit_shBmName_gczlYdxjSGZLXJInfo' disabled="disabled" style='width: 150px'>		
			    </td>
			     <td  class="form-td-label" >审核人员：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2" name='shRyName'  id='edit_shRyName_gczlYdxjSGZLXJInfo' disabled="disabled" style='width: 150px'>		
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >审核意见：</td>
			    <td class="form-td-content"  colspan='6'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='shSm'  id='edit_shSm_gczlYdxjSGZLXJInfo'  disabled="disabled" data-options="multiline:true,validType:'length[1,400]'"  style='width: 100%;height:50px'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="edit-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="editGczlYdxjSGZLXJInfoAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#editDialog_gczlYdxjSGZLXJInfo').dialog('close')">关闭</a>
	</div>
	
	

	
<!-- 【结构施工质量巡检管理】审核弹出框-->	
<div id="shDialog_gczlYdxjSGZLXJInfo" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#sh-dialog-buttons',closed:true,cache: false,modal: true"	style="width:780px;height:500px;padding:10px 10px;display:none;" >
        <form id="shForm_gczlYdxjSGZLXJInfo" method="post" data-options="novalidate:true">
           <input name="id" type="hidden"  id="showsh_xjZpsltId">
           
            <input name="updateUser" type="hidden" >
           <input name="updateUsername" type="hidden" >
           <input name="updateOrgz" type="hidden" >
           <input name="updateOrgzname" type="hidden" >
           <input name="updateTime" type="hidden" >
           
			<table  class="">
			  <tr>
			    <td  class="form-td-label" >所属工程：</td>
			    <td class="form-td-content"  colspan="5">
			      <span id="sh_gcName_gczlYdxjSGZLXJInfo"></span>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='gcName'  disabled="disabled" id='sh_qjBh_gczlYdxjSGZLXJInfo'   style='width: 100%'>
			    </td>
			  </tr>
			   <tr>
			    <td  class="form-td-label" >单位工程：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='dwgcName' id='sh_dwgcBh_gczlYdxjSGZLXJInfo' disabled="disabled"   style='width: 150px'>
			    </td>
			    <td></td>
			    <td></td>
			    <td  class="form-td-label" >分部工程：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='fbGcName' id='sh_fbGcbh_gczlYdxjSGZLXJInfo'  disabled="disabled"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >施工段：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sgd' id='sh_sgd_gczlYdxjSGZLXJInfo'  disabled="disabled"   style='width: 150px'>
			    </td>
			     <td></td>
			    <td></td>
			     <td  class="form-td-label"   align="right" >施工内容：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sgNr'  id='sh_sgNr_gczlYdxjSGZLXJInfo'  disabled="disabled"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >具体位置：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='jtWz' id='sh_jtWz_gczlYdxjSGZLXJInfo'  disabled="disabled"  style='width: 150px'>
			    </td>
			     <td></td>
			    <td></td>
			    <td  class="form-td-label"   align="right" >质量缺陷：</td>
			    <td class="form-td-content"  >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='zlQx'  id='sh_zlQx_gczlYdxjSGZLXJInfo'  disabled="disabled"  style='width: 150px'>
			    </td>
			  </tr>
			 <tr>
			    <td  class="form-td-label" >巡检时间：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datetimebox" class="easyui-validatebox span2" name='xjTime'  id='sh_xjTime_gczlYdxjSGZLXJInfo' disabled="disabled" style='width: 150px'>		
			    </td>
			     <td></td>
			    <td></td>
			    <td  class="form-td-label" >现场图片：</td>
			    <td class="form-td-content" >
			       <a  id="shYouFuJian"  onclick="showMyPic('showsh_xjZpsltId');" style="cursor:pointer"><u>点击查看</u> </a>
			       <span id="shWuFuJian"  >无图片</span>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >其他需说明情况：</td>
			    <td class="form-td-content"  colspan='6'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='remarks'  id='sh_remarks_gczlYdxjSGZLXJInfo'  disabled="disabled" data-options="multiline:true,validType:'length[1,400]'"  style='width: 100%;height:50px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"   align="right" ><span style="color: red;">*</span>审核情况：</td>
			    <td class="form-td-content"  >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='shZt'  id='sh_shZt_gczlYdxjSGZLXJInfo'  data-options="prompt:'请选择',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label"   align="right" >指定整改人员：</td>
			    <td class="form-td-content"  colspan='6'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='zgRy'  id='sh_zgRy_gczlYdxjSGZLXJInfo'  data-options="prompt:'请输入指定整改人员',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >审核意见：</td>
			    <td class="form-td-content"  colspan='6'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='shSm'  id='sh_shSm_gczlYdxjSGZLXJInfo'  data-options="prompt:'请输入审核意见',multiline:true,validType:'length[1,40]'"  style='width: 100%;height:50px'>
			    </td>
			  </tr>
			   <tr>
			   
			    <td  class="form-td-label"   align="right" ><span style="color: red;">*</span>整改截止时间：</td>
			    <td class="form-td-content"  >
			      <input class="easyui-datetimebox" class="easyui-validatebox span2"  name='zgjzTime'  id='sh_zgjzTime_gczlYdxjSGZLXJInfo' style='width: 150px'>
			    </td>
			    <td  class="form-td-label"   align="right" >上报状态：</td>
			    <td class="form-td-content"  >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sbZt'  id='sh_sbZt_gczlYdxjSGZLXJInfo'  disabled="disabled"  style='width: 150px'>
			    </td>
			  </tr>
			   <tr>
			    <td  class="form-td-label"   align="right" >维护时间：</td>
			    <td class="form-td-content"  >
			      <input class="easyui-datetimebox" class="easyui-validatebox span2"  name='whTime'  id='sh_whTime_gczlYdxjSGZLXJInfo'  disabled="disabled"  style='width: 150px'>
			    </td>
			     <td  class="form-td-label" >巡检部门：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2" name='xjBmName'  id='sh_xjBmName_gczlYdxjSGZLXJInfo' disabled="disabled" style='width: 150px'>		
			    </td>
			    <td  class="form-td-label" >巡检人员：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2" name='xjRyName'  id='sh_xjRyName_gczlYdxjSGZLXJInfo' disabled="disabled" style='width: 150px'>		
			    </td>
			  </tr>
			   <tr>
			    <td  class="form-td-label"   align="right" >上报时间：</td>
			    <td class="form-td-content"  >
			      <input class="easyui-datetimebox" class="easyui-validatebox span2"  name='sbTime'  id='sh_sbTime_gczlYdxjSGZLXJInfo'  disabled="disabled"  style='width: 150px'>
			    </td>
			     <td  class="form-td-label" >上报部门：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2" name='sbBmName'  id='sh_sbBmName_gczlYdxjSGZLXJInfo' disabled="disabled" style='width: 150px'>		
			    </td>
			     <td  class="form-td-label" >上报人员：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2" name='sbRyName'  id='sh_sbRyName_gczlYdxjSGZLXJInfo' disabled="disabled" style='width: 150px'>		
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="sh-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="shGczlYdxjSGZLXJInfoAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#shDialog_gczlYdxjSGZLXJInfo').dialog('close')">关闭</a>
	</div>
	
	
	
	
		
<!-- 【结构施工质量巡检管理】整改弹出框-->	
<div id="zgDialog_gczlYdxjSGZLXJInfo" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#zg-dialog-buttons',closed:true,cache: false,modal: true"	style="width:780px;height:500px;padding:10px 10px;display:none;" >
        <form id="zgForm_gczlYdxjSGZLXJInfo" method="post" data-options="novalidate:true">
           <input name="id" type="hidden" id='showzg_xjZpId'>
            <input name="updateUser" type="hidden" >
           <input name="updateUsername" type="hidden" >
           <input name="updateOrgz" type="hidden" >
           <input name="updateOrgzname" type="hidden" >
           <input name="updateTime" type="hidden" >
           
			<table  class="">
			  <tr>
			    <td  class="form-td-label" >所属工程：</td>
			    <td class="form-td-content"  colspan="5">
			      <span id="zg_gcName_gczlYdxjSGZLXJInfo"></span>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='gcName'  disabled="disabled" id='zg_qjBh_gczlYdxjSGZLXJInfo'   style='width: 100%'>
			    </td>
			  </tr>
			   <tr>
			    <td  class="form-td-label" >单位工程：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='dwgcName' id='zg_qjBh_gczlYdxjSGZLXJInfo' disabled="disabled"   style='width: 150px'>
			    </td>
			    <td></td>
			    <td></td>
			    <td  class="form-td-label" >分部工程：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='fbGcName' id='zg_fbGcbh_gczlYdxjSGZLXJInfo'  disabled="disabled"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >施工段：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sgd' id='zg_sgd_gczlYdxjSGZLXJInfo'  disabled="disabled"   style='width: 150px'>
			    </td>
			     <td></td>
			    <td></td>
			     <td  class="form-td-label"  valign="top" align="right" >施工内容：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sgNr'  id='zg_sgNr_gczlYdxjSGZLXJInfo'  disabled="disabled"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >具体位置：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='jtWz' id='zg_jtWz_gczlYdxjSGZLXJInfo'  disabled="disabled"  style='width: 150px'>
			    </td>
			     <td></td>
			    <td></td>
			    <td  class="form-td-label"   align="right" >质量缺陷：</td>
			    <td class="form-td-content"  >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='zlQx'  id='zg_zlQx_gczlYdxjSGZLXJInfo'  disabled="disabled"  style='width: 150px'>
			    </td>
			   
			  </tr>
			  <tr>
			     <td  class="form-td-label" >巡检时间：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datetimebox" class="easyui-validatebox span2" name='xjTime'  id='zg_xjTime_gczlYdxjSGZLXJInfo' disabled="disabled" style='width: 150px'>		
			    </td>
			     <td></td>
			    <td></td>
			    <td  class="form-td-label" >现场图片：</td>
			    <td class="form-td-content" >
			       <a  id="zgYouFuJian" onclick="showMyPic('showzg_xjZpId');" style="cursor:pointer"><u>点击查看</u> </a>
			       <span id="zgWuFuJian"  >无图片</span>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >其他需说明情况：</td>
			    <td class="form-td-content"  colspan='6'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='remarks'  id='zg_remarks_gczlYdxjSGZLXJInfo'  disabled="disabled" data-options="multiline:true,validType:'length[1,400]'"  style='width: 100%;height:50px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" ><span style="color: red;">*</span>整改结果：</td>
			    <td class="form-td-content"  >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='zgZt'  id='zg_zgZt_gczlYdxjSGZLXJInfo'  data-options="prompt:'请选择',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label"   align="right" ><span style="color: red;">*</span>整改时间：</td>
			    <td class="form-td-content"  >
			      <input class="easyui-datetimebox" class="easyui-validatebox span2"  name='zgTime'  id='zg_zgTime_gczlYdxjSGZLXJInfo' data-options="prompt:'请选择整改时间',validType:'length[1,40]',required:true"   style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >整改说明：</td>
			    <td class="form-td-content"  colspan='6'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='zgJg'  id='zg_zgSm_gczlYdxjSGZLXJInfo'  data-options="prompt:'请输入整改说明',multiline:true,validType:'length[1,400]'"  style='width: 100%;height:50px'>
			    </td>
			  </tr>
			   <tr>
			    <td  class="form-td-label"   align="right" >上报状态：</td>
			    <td class="form-td-content"  >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sbZt'  id='zg_sbZt_gczlYdxjSGZLXJInfo'  disabled="disabled"  style='width: 150px'>
			    </td>
			     <td  class="form-td-label"   align="right" >审核状态：</td>
			    <td class="form-td-content"  >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='shZt'  id='zg_shZt_gczlYdxjSGZLXJInfo'  disabled="disabled"  style='width: 150px'>
			    </td>
			     <td  class="form-td-label"   align="right" >整改状态：</td>
			    <td class="form-td-content"  >
			      <span>未整改</span>
			    </td>
			  </tr>
			   <tr>
			    <td  class="form-td-label"   align="right" >维护时间：</td>
			    <td class="form-td-content"  >
			      <input class="easyui-datetimebox" class="easyui-validatebox span2"  name='whTime'  id='zg_whTime_gczlYdxjSGZLXJInfo'  disabled="disabled"  style='width: 150px'>
			    </td>
			     <td  class="form-td-label" >巡检部门：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2" name='xjBmName'  id='zg_xjBmName_gczlYdxjSGZLXJInfo' disabled="disabled" style='width: 150px'>		
			    </td>
			    <td  class="form-td-label" >巡检人员：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2" name='xjRyName'  id='zg_xjRyName_gczlYdxjSGZLXJInfo' disabled="disabled" style='width: 150px'>		
			    </td>
			  </tr>
			   <tr>
			    <td  class="form-td-label"   align="right" >上报时间：</td>
			    <td class="form-td-content"  >
			      <input class="easyui-datetimebox" class="easyui-validatebox span2"  name='sbTime'  id='zg_sbTime_gczlYdxjSGZLXJInfo'  disabled="disabled"  style='width: 150px'>
			    </td>
			     <td  class="form-td-label" >上报部门：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2" name='sbBmName'  id='zg_sbBmName_gczlYdxjSGZLXJInfo' disabled="disabled" style='width: 150px'>		
			    </td>
			     <td  class="form-td-label" >上报人员：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2" name='sbRyName'  id='zg_sbRyName_gczlYdxjSGZLXJInfo' disabled="disabled" style='width: 150px'>		
			    </td>
			  </tr>
			   <tr>
			    <td  class="form-td-label"   align="right" >审核时间：</td>
			    <td class="form-td-content"  >
			      <input class="easyui-datetimebox" class="easyui-validatebox span2"  name='shTime'  id='zg_shTime_gczlYdxjSGZLXJInfo'  disabled="disabled"  style='width: 150px'>
			    </td>
			     <td  class="form-td-label" >审核部门：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2" name='shBmName'  id='zg_shBmName_gczlYdxjSGZLXJInfo' disabled="disabled" style='width: 150px'>		
			    </td>
			     <td  class="form-td-label" >审核人员：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2" name='shRyName'  id='zg_shRyName_gczlYdxjSGZLXJInfo' disabled="disabled" style='width: 150px'>		
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >审核意见：</td>
			    <td class="form-td-content"  colspan='6'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='shSm'  id='zg_shSm_gczlYdxjSGZLXJInfo'  disabled="disabled" data-options="multiline:true,validType:'length[1,400]'"  style='width: 100%;height:50px'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="zg-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="zgGczlYdxjSGZLXJInfoAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#zgDialog_gczlYdxjSGZLXJInfo').dialog('close')">关闭</a>
	</div>
	
	
	
			
<!-- 【结构施工质量巡检管理】查看弹出框-->	
<div id="showDialog_gczlYdxjSGZLXJInfo" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#show-dialog-buttons',closed:true,cache: false,modal: true"	style="width:800px;height:500px;padding:10px 10px;display:none;" >
        <form id="showForm_gczlYdxjSGZLXJInfo" method="post" data-options="novalidate:true">
           <input name="id" type="hidden" id='show_xjZpsltId'>
			<table  class="">
			  <tr>
			    <td  class="form-td-label" >所属工程：</td>
			    <td class="form-td-content"  colspan="5">
			      <span id="zg_gcName_gczlYdxjSGZLXJInfo"></span>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='gcName'  disabled="disabled"   style='width: 100%'>
			    </td>
			  </tr>
			   <tr>
			    <td  class="form-td-label" >单位工程：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='dwgcName' disabled="disabled"   style='width: 150px'>
			    </td>
			    <td></td>
			    <td></td>
			    <td  class="form-td-label" >分部工程：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='fbGcName' disabled="disabled"  style='width: 150px'>
			    </td>
			    
			  </tr>
			  <tr>
			    <td  class="form-td-label" >施工段：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sgd'  disabled="disabled"   style='width: 150px'>
			    </td>
			     <td></td>
			    <td></td>
			     <td  class="form-td-label"  valign="top" align="right" >施工内容：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sgNr'    disabled="disabled"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >具体位置：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='jtWz'   disabled="disabled"  style='width: 150px'>
			    </td>
			     <td></td>
			    <td></td>
			    <td  class="form-td-label"   align="right" >质量缺陷：</td>
			    <td class="form-td-content"  >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='zlQx'  disabled="disabled"  style='width: 150px'>
			    </td>
			   
			  </tr>
			  <tr>
			     <td  class="form-td-label" >巡检时间：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datetimebox" class="easyui-validatebox span2" name='xjTime'  disabled="disabled" style='width: 150px'>		
			    </td>
			     <td></td>
			    <td></td>
			    <td  class="form-td-label" >现场图片：</td>
			    <td class="form-td-content" >
			       <a  id="showYouFuJian"  onclick="showMyPic('show_xjZpsltId');" style="cursor:pointer"><u>点击查看</u> </a>
			       <span id="showWuFuJian"  >无图片</span>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >其他需说明情况：</td>
			    <td class="form-td-content"  colspan='6'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='remarks'    disabled="disabled" data-options="multiline:true,validType:'length[1,400]'"  style='width: 100%;height:50px'>
			    </td>
			  </tr>
			 
			   <tr>
			    <td  class="form-td-label"   align="right" >上报状态：</td>
			    <td class="form-td-content"  >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sbZt'  id='zg_sbZt_gczlYdxjSGZLXJInfo'  disabled="disabled"  style='width: 150px'>
			    </td>
			     <td  class="form-td-label"   align="right" >审核状态：</td>
			    <td class="form-td-content"  >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='shZt'  id='zg_shZt_gczlYdxjSGZLXJInfo'  disabled="disabled"  style='width: 150px'>
			    </td>
			     <td  class="form-td-label"   align="right" >整改状态：</td>
			    <td class="form-td-content"  >
			       <input class="easyui-textbox" class="easyui-validatebox span2"  name='zgZt'    disabled="disabled" style='width: 150px'>
			    </td>
			  </tr>
			   <tr>
			    <td  class="form-td-label"   align="right" >维护时间：</td>
			    <td class="form-td-content"  >
			      <input class="easyui-datetimebox" class="easyui-validatebox span2"  name='whTime'  id='zg_whTime_gczlYdxjSGZLXJInfo'  disabled="disabled"  style='width: 150px'>
			    </td>
			     <td  class="form-td-label" >巡检部门：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2" name='xjBmName'  id='zg_xjBmName_gczlYdxjSGZLXJInfo' disabled="disabled" style='width: 150px'>		
			    </td>
			    <td  class="form-td-label" >巡检人员：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2" name='xjRyName'  id='zg_xjRyName_gczlYdxjSGZLXJInfo' disabled="disabled" style='width: 150px'>		
			    </td>
			  </tr>
			   <tr>
			    <td  class="form-td-label"   align="right" >上报时间：</td>
			    <td class="form-td-content"  >
			      <input class="easyui-datetimebox" class="easyui-validatebox span2"  name='sbTime'  id='zg_sbTime_gczlYdxjSGZLXJInfo'  disabled="disabled"  style='width: 150px'>
			    </td>
			     <td  class="form-td-label" >上报部门：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2" name='sbBmName'  id='zg_sbBmName_gczlYdxjSGZLXJInfo' disabled="disabled" style='width: 150px'>		
			    </td>
			     <td  class="form-td-label" >上报人员：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2" name='sbRyName'  id='zg_sbRyName_gczlYdxjSGZLXJInfo' disabled="disabled" style='width: 150px'>		
			    </td>
			  </tr>
			   <tr>
			    <td  class="form-td-label"   align="right" >审核时间：</td>
			    <td class="form-td-content"  >
			      <input class="easyui-datetimebox" class="easyui-validatebox span2"  name='shTime'  id='zg_shTime_gczlYdxjSGZLXJInfo'  disabled="disabled"  style='width: 150px'>
			    </td>
			     <td  class="form-td-label" >审核部门：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2" name='shBmName'  id='zg_shBmName_gczlYdxjSGZLXJInfo' disabled="disabled" style='width: 150px'>		
			    </td>
			     <td  class="form-td-label" >审核人员：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2" name='shRyName'  id='zg_shRyName_gczlYdxjSGZLXJInfo' disabled="disabled" style='width: 150px'>		
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >审核意见：</td>
			    <td class="form-td-content"  colspan='6'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='shSm'  id='zg_shSm_gczlYdxjSGZLXJInfo'  disabled="disabled" data-options="multiline:true,validType:'length[1,400]'"  style='width: 100%;height:50px'>
			    </td>
			  </tr>
			   <tr>
			    <td  class="form-td-label"  valign="top" align="right" >整改反馈时间：</td>
			    <td class="form-td-content"  >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='zgfkTime'    disabled="disabled" style='width: 150px'>
			    </td>
			    <td  class="form-td-label"   align="right" >整改反馈部门：</td>
			    <td class="form-td-content"  >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='zgBmName'   disabled="disabled"   style='width: 150px'>
			    </td>
			     <td  class="form-td-label"   align="right" >整改反馈人员：</td>
			    <td class="form-td-content"  >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='zgFkryName'   disabled="disabled"   style='width: 150px'>
			    </td>
			   </tr>
			   <tr>
			    <td  class="form-td-label"   align="right" >整改时间：</td>
			    <td class="form-td-content"  >
			      <input class="easyui-datetimebox" class="easyui-validatebox span2"  name='zgTime'   disabled="disabled"   style='width: 150px'>
			    </td>
			    <td></td>
			    <td></td>
			    <td  class="form-td-label"  valign="top" align="right" >指定整改人员：</td>
			    <td class="form-td-content"  >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='zgRy'    disabled="disabled" style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >整改说明：</td>
			    <td class="form-td-content"  colspan='6'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='zgJg'  disabled="disabled"  data-options="multiline:true,validType:'length[1,400]'"  style='width: 100%;height:50px'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="show-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#showDialog_gczlYdxjSGZLXJInfo').dialog('close')">关闭</a>
	</div>

</body>
</html>
