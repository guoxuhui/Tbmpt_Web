<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>培训记录</title>
<script type="text/javascript" src="${staticPath}/static/js/aqsc/base/huiyiJilu.js?v=20170522115925" charset="utf-8"></script>
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
<jsp:include  page="../../sys/base/uploadPicture.jsp" />
<!-- 【培训记录】查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:95px;border: false;overflow: auto;" data-options="collapsible:true">
		<form id="searchForm_huiyiJilu">
			<table class="grid" width='650px' border='0' cellspacing='0' style='padding:5px'>
			  <tr>
			    <td class="form-td-label" style="width: 80px">培训日期：</td>
			    <td align="left" width="160px" colspan="1">
			      <input   class="easyui-datebox"  name='starDay'   style='width: 149px'>&nbsp;&nbsp;至&nbsp;&nbsp;<input   class="easyui-datebox"  name='endDay'   style='width: 149px'>
			    </td>
			    <td class="form-td-label" style="width: 80px">培训名称：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='name'   style='width: 149px'>
			    </td>
			  </tr>
			  <tr>
			    <td class="form-td-label" style="width: 80px">培训地点：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='adress'   style='width: 320px'>
			    </td>
			    <td class="form-td-label" style="width: 80px">培训摘要：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='content'   style='width: 149px'>
			    </td>
			    <td align="left" width="170px" >
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchHuiyiJiluFun();">查询</a>
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleanHuiyiJiluFun();">重置</a>
			    </td>
			  </tr>
			</table>
		</form>
	</div>

<!-- 【培训记录】结果列表 -->
	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
		<table id="dataGrid_huiyiJilu" title="列表" data-options="fit:true,border:false"></table>
	</div>

<div id="toolbar" style="display: none;">
    	<a onclick="reloadHuiyiJilu();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<shiro:hasPermission name="/aqsc/base/huiyiJilu/add">
    	</shiro:hasPermission>
    	<a onclick="addHuiyiJiluFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	<shiro:hasPermission name="/aqsc/base/huiyiJilu/edit">
    	</shiro:hasPermission>
    	<a onclick="editHuiyiJiluFun();" id="button_edit_huiyiJilu" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	<shiro:hasPermission name="/aqsc/base/huiyiJilu/del">
    	</shiro:hasPermission>
    	<a onclick="deleteHuiyiJiluFun();" id="button_del_huiyiJilu" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	<a onclick="clearHuiyiJiluSelections();" id="button_quxiao_huiyiJilu" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    	<a onclick="expHuiyiJiluXls();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
    	<a onclick="myUploadPicture();" href="javascript:void(0);" id="uploadPic_button_huiyiJilu" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-20130406125647919_easyicon_net_16'">上传培训图片</a>
</div>
<!-- 【培训记录】新增弹出框-->	<div id="addDialog_huiyiJilu" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal:true"	style="width:660px;height:378px;padding:10px 10px;display:none;" >
        <form id="addForm_huiyiJilu" method="post" data-options="novalidate:true">
			<table  class="grid">
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>培训日期：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datebox" class="easyui-validatebox span2" name='hydate'  id='add_hydate_huiyiJilu' data-options="prompt:'请输入培训日期'" style='width: 150px'>			    </td>
			    <td  class="form-td-label" >培训主持人：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='zhuchiren' id='add_zhuchiren_huiyiJilu'  data-options="prompt:'请输入培训主持人',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>培训名称：</td>
			    <td class="form-td-content" colspan="3">
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='name' id='add_name_huiyiJilu'  data-options="prompt:'请输入培训名称',validType:'length[1,400]',required:true"  style='width: 390px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >培训参加人：</td>
			    <td class="form-td-content" colspan="3">
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='chuxiren' id='add_chuxiren_huiyiJilu'  data-options="prompt:'请输入培训参加人',validType:'length[1,400]'"  style='width: 390px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>培训地点：</td>
			    <td class="form-td-content" colspan="3">
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='adress' id='add_adress_huiyiJilu'  data-options="prompt:'请输入培训地点',validType:'length[1,400]',required:true"  style='width: 390px'>
			    </td>
			  </tr>
			  <!-- 
			  <tr>
			    <td  class="form-td-label" >培训记录人：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='jiluren' id='add_jiluren_huiyiJilu'  data-options="prompt:'请输入培训记录人',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			   -->
			  <tr>
			    <td  class="form-td-label" valign="top">培训摘要：</td>
			    <td class="form-td-content" colspan="3">
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='content' id='add_content_huiyiJilu'  data-options="prompt:'请输入培训摘要',validType:'length[1,600]',multiline:true"  style='width: 390px;height:100px'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="add-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="addHuiyiJiluAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#addDialog_huiyiJilu').dialog('close')">关闭</a>
	</div>

<!-- 【培训记录】编辑弹出框 -->
	<div id="editDialog_huiyiJilu" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#edit-dialog-buttons',closed:true,cache: false,modal:true"
	style="width:660px;height:378px;padding:10px 10px;display:none;" >
        <form id="editForm_huiyiJilu" method="post" data-options="novalidate:true">
           <input name="id" type="hidden" >
           <input name="updateTime" type="hidden" >
			<table  class="grid">
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>培训日期：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datebox" class="easyui-validatebox span2" name='hydate'  id='edit_hydate_huiyiJilu' data-options="prompt:'请输入培训日期'" style='width: 150px'>			    </td>
			    <td  class="form-td-label" >培训主持人：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='zhuchiren' id='edit_zhuchiren_huiyiJilu'  data-options="prompt:'请输入培训主持人',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>培训名称：</td>
			    <td class="form-td-content" colspan="3">
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='name' id='edit_name_huiyiJilu'  data-options="prompt:'请输入培训名称',validType:'length[1,400]',required:true"  style='width: 390px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >培训参加人：</td>
			    <td class="form-td-content"  colspan="3">
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='chuxiren' id='edit_chuxiren_huiyiJilu'  data-options="prompt:'请输入培训参加人',validType:'length[1,40]'"  style='width: 390px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>培训地点：</td>
			    <td class="form-td-content" colspan="3">
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='adress' id='edit_adress_huiyiJilu'  data-options="prompt:'请输入培训地点',validType:'length[1,40]',required:true"  style='width: 390px'>
			    </td>
			  </tr>
			  <!-- 
			  <tr>
			    <td  class="form-td-label" >培训记录人：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='jiluren' id='edit_jiluren_huiyiJilu'  data-options="prompt:'请输入培训记录人',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			   -->
			  <tr>
			    <td  class="form-td-label" valign='top'>培训摘要：</td>
			    <td class="form-td-content" colspan="3">
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='content' id='edit_content_huiyiJilu'  data-options="prompt:'请输入培训摘要',validType:'length[1,40]',multiline:true"  style='width: 390px;height:100px;'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>
<!-- 【培训记录】查看弹出框 -->
	<div id="showDialog_huiyiJilu" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#show-dialog-buttons',closed:true,cache: false,modal:true"
	style="width:660px;height:378px;padding:10px 10px;display:none;" >
           <input name="id" type="hidden"  id="show_id">
           <input name="updateTime" type="hidden" >
			<table  class="grid">
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>培训日期：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2" name='hydate'   style='width: 150px' readonly="readonly">			    </td>
			    <td  class="form-td-label" >培训主持人：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='zhuchiren'   style='width: 150px' readonly="readonly">
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>培训名称：</td>
			    <td class="form-td-content" colspan="3">
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='name'   style='width: 390px' readonly="readonly">
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >培训参加人：</td>
			    <td class="form-td-content"  colspan="3">
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='chuxiren'   style='width: 390px' readonly="readonly">
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>培训地点：</td>
			    <td class="form-td-content" colspan="3">
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='adress'   style='width: 390px' readonly="readonly">
			    </td>
			  </tr>
			  <!-- 
			  <tr>
			    <td  class="form-td-label" >培训记录人：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='jiluren' id='edit_jiluren_huiyiJilu'  data-options="prompt:'请输入培训记录人',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			   -->
			  <tr>
			    <td  class="form-td-label" valign='top'>培训摘要：</td>
			    <td class="form-td-content" colspan="3">
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='content' id='edit_content_huiyiJilu'  data-options="prompt:'请输入培训摘要',validType:'length[1,40]',multiline:true"  style='width: 390px;height:100px;'>
			    </td>
			  </tr>
			</table>
			<div id="img_div_huiyiJilu">
		    </div>
	</div>
	<div id="edit-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="editHuiyiJiluAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#editDialog_huiyiJilu').dialog('close')">关闭</a>
	</div>
	<div id="show-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#showDialog_huiyiJilu').dialog('close')">关闭</a>
	</div>
</body>
</html>
