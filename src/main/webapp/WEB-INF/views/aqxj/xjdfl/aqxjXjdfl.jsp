<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>施工内容管理</title>
<script type="text/javascript" src="${staticPath}/static/js/aqxj/aqxjXjdfl.js" charset="utf-8"></script>
<body class="easyui-layout" data-options="fit:true,border:false">

<!-- 【施工内容管理】查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:65px;border: false;overflow: hidden;" data-options="collapsible:true">
		<form id="searchForm_AqxjXjdfl">
			<table class="grid" width='650px' border='0' cellspacing='0' style='padding:5px'>
			  <tr>
			  <td class="form-td-label" style="width: 110px">工程名称：</td>
					<td class="form-td-content" style="width: 200px">
						<input name="gcMc" class="easyui-textbox" data-options="prompt:'请选择工程名称'" id = "gcName_query_id" style="width: 100%;"  />
					</td>
			    <td class="form-td-label" style="width: 110px">分类名称：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='fenleiMc'   style='width: 149px' data-options="prompt:'请输入分类名称'" >
			    </td>
			    <td align="center" width="200px" >
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchAqxjXjdflFun();">查询</a>
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleanAqxjXjdflFun();">重置</a>
			    </td>
			  </tr>
			</table>
		</form>
	</div>

     <!-- 【施工内容管理】结果列表 -->
	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
		<table id="dataGrid_aqxjXjdfl" title="列表" data-options="fit:true,border:false"></table>
	</div>
<div id="toolbar">
    	        <a onclick="reloadAqxjXjdfl();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	    	<a onclick="addAqxjXjdflFun();" href="javascript:void(0);" id="add_button_aqxjXjdfl" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	    	<shiro:hasPermission name="/aqxj/xjdfl/aqxjXjdfl/add">
    	    	</shiro:hasPermission>
    	    	<a onclick="editAqxjXjdflFun();" id="edit_button_aqxjXjdfl" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
				<shiro:hasPermission name="/aqxj/xjdfl/aqxjXjdfl/edit">
    	    	</shiro:hasPermission>    	
    	    	<a onclick="deleteAqxjXjdflFun();" href="javascript:void(0);" id="del_button_aqxjXjdfl" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	    	<shiro:hasPermission name="/aqxj/xjdfl/aqxjXjdfl/delete">
    	    	</shiro:hasPermission>
		    	<a onclick="clearAqxjXjdflSelections();" href="javascript:void(0);" id="cancelSelect_button_aqxjXjdfl" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a> 
		    	<a onclick="expand();" href="javascript:void(0);" id="expExcel_aqxjXjdfl" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">展开</a>
		    	<a onclick="collapse();" href="javascript:void(0);" id="expPdf_aqxjXjdfl" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_white_acrobat'">折叠</a>
		    	
  </div> 
<!-- 【巡检点分类管理】新增弹出框-->	
<div id="addDialog_AqxjXjdfl" class="easyui-dialog" 
   data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal: true"	
      style="width:540px;height:270px;padding:10px 10px;display:none; overflow: hidden;" >
        <form id="addForm_AqxjXjdfl" method="post" data-options="novalidate:true">
      <input type="hidden"  name='pid' id='add_pid_aqxjXjdfl'> 
        <input type="hidden" name="id"/>
			<table  class="grid">
			<tr>
				<td class="form-td-label" style="width: 80px">所属工程：</td>
					<td class="form-td-content" style="width: 180px">
					<!-- <input type="hidden"  name='pid' id='add_pid_mc_aqxjXjdfl'> -->
						<input id="search_gcMc" name="gcMc" class="easyui-combobox" data-options="prompt:'请输入工程名称'" style="width: 100%;">
					</td>
			</tr>
			 <tr>
			    <td  class="form-td-label" >父类名称：</td>
			    <td class="form-td-content" >
			      
			     <input  class="easyui-textbox"   name='pName' panelHeight="50px" id='add_pName_aqxjXjdfl'  data-options="prompt:'所属父分类',validType:'length[1,40]'"  style='width: 415px;' >
			    </td>
			  <!-- <tr>
			  		 <td class="form-td-label"><span style="color: red;">*</span>所属父分类：</td>
                    <td class="form-td-content">
                    	<input name="typeName" id="typeName_add_id" class="easyui-textbox"  class="easyui-validatebox span2" data-options="required:true,prompt:'请选择'" value="" style="width:130px;">
                    </td>
			  </tr> -->
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>分类名称：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='fenleiMc' panelHeight="50px" id='add_ifQy_aqxjXjdfl'  data-options="prompt:'请输入分类名称',validType:'length[1,40]',required:true"  style='width: 415px'>
			    </td>
			  </tr>			 
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >备注：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='beiZ'  id='add_beiZ_aqxjXjdfl'  data-options="prompt:'请输入备注',multiline:true,validType:'length[1,400]'"  style='width: 100%;height:60px'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="add-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="addAqxjXjdflAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#addDialog_AqxjXjdfl').dialog('close')">关闭</a>
	</div>


<!--【安全巡检管理】编辑弹出框 -->
	<div id="editDialog_AqxjXjdfl" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#edit-dialog-buttons',closed:true,cache: false,modal: true"
	style="width:540px;height:270px;padding:10px 10px;display:none; overflow: hidden;" >
        <form id="editForm_aqxjXjdfl" method="post" data-options="novalidate:true">
           <input name="id" type="hidden" >
           
           <input name="updateUser" type="hidden" >
           <input name="updateUsername" type="hidden" >
           <input name="updateOrgz" type="hidden" >
           <input name="updateOrgzname" type="hidden" >
           <input name="updateTime" type="hidden" >
           <input type="hidden"  name='pid' id='edit_pid_aqxjXjdfl'>
           
			<table  class="grid">
			<tr>
				<td class="form-td-label" style="width: 80px">所属工程：</td>
					<td class="form-td-content" style="width: 180px">
						<input id="edit_search_pro_Id" name="gcMc" class="easyui-textbox" data-options="prompt:'请输入工程名称'" style="width: 100%;" >
					</td>
			</tr>
			  <tr>
			     <td  class="form-td-label" >父类名称：</td>
			    <td class="form-td-content" >
			     
			     <input  class="easyui-textbox" class="easyui-validatebox span2"  name='pName' panelHeight="50px" id='edit_pName_aqxjXjdfl'  data-options="prompt:'父类名称',validType:'length[1,40]'"  style='width: 415px;'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>分类名称：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='fenleiMc' panelHeight="50px" id='add_ifQy_aqxjXjdfl'  data-options="prompt:'请输入分类名称',validType:'length[1,40]'"  style='width: 415px'>
			    </td>
			  </tr>	
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >备注：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='beiZ'  id='edit_remarks_gczlYdxjSGZLSgnr'  data-options="prompt:'请输入备注',multiline:true,validType:'length[1,400]'"  style='width: 100%;height:60px'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="edit-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="editAqxjXjdflAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#editDialog_AqxjXjdfl').dialog('close')">关闭</a>
	</div>

	
	
</body>
</html>