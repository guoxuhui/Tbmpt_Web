<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>集团角度项目信息管理</title>
<script type="text/javascript" src="${staticPath }/static/js/zsjk/jt/zsJkJtXmxx.js" charset="utf-8"></script>
</head>

<body class="easyui-layout" data-options="fit:true,border:false">
	<!-- 查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:100px;border: false;overflow: hidden;" data-options="collapsible:true">
		<form id="searchForm">
			<table class="grid">
				<tr>
					<td class="form-td-label" style="width: 80px">项目名称：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='xmMc'  id='search_xmMc' data-options="prompt:'请输入项目名称',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
					<td style="text-align: right;" colspan="2">
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a> 
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleanFun();">重置</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 结果列表 -->
	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100px;">
		<table id="dataGrid" title="列表" data-options="fit:true,border:false"></table>
	</div>
	
	<!--列表菜单 -->
	<div id="toolbar" style="display: none;">
    	<a onclick="reloadFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<a onclick="clearSelections();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    	<shiro:hasPermission name="zsjk/jt/zsJkJtXmxx/add">
    		<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="zsjk/jt/zsJkJtXmxx/edit">
    		<a onclick="editFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="zsjk/jt/zsJkJtXmxx/del">
    		<a onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="zsjk/jt/zsJkJtXmxx/sbInfo">
  	        <a onclick="showFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">查看</a>
    	</shiro:hasPermission>
    	 <shiro:hasPermission name="zsjk/jt/zsJkJtXmxx/ExpExcel">  
    	    <a onclick="expXls();" href="javascript:void(0);"   class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
    	</shiro:hasPermission> 
   	    <shiro:hasPermission name="zsjk/jt/zsJkJtXmxx/ExpPdf">  
    	    <a onclick="expPdf();" href="javascript:void(0);"   class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_white_acrobat'">导出Pdf</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="zsjk/jt/zsJkJtXmxx/imp">
			<a onclick="impFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">Excel导入</a>
    	</shiro:hasPermission>
   	</div>
    <!-- 弹出框－新增 -->
	<div id="addDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal: true"
		style="width:850px;height:390px;padding:5px 5px;" >
        <form id="addForm" method="post" data-options="novalidate:true">
			<table class="grid">
                <tr>
					<td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>项目Id：：</td>
                    <td class="form-td-content" colspan="5" >
                    	<input name='xmId'  id='add_xmId'  data-options="required:true,prompt:'请输入项目Id',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
                    </td>
                </tr>
	            <tr>
					<td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>项目名称：</td>
                    <td class="form-td-content" colspan="5" >
                    	<input name='xmMc'  id='add_xmMc'  data-options="required:true,prompt:'请输入项目名称',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width: 100px">负责人：</td>
                	<td class="form-td-content" style="width: 150px">
				        <input name='fzr'  id='add_fzr' data-options="prompt:'请输入项目负责人',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
	                </td>
                     <td class="form-td-label" style="width: 100px">中标价格：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="add_zbjg" name="zbjg"  data-options="editable:true, prompt:'请输入中标价格'" class="easyui-numberbox" min="0" max="1000000000"   class="easyui-validatebox span2"   style="width:100%">                    	
                    </td>
                	<td class="form-td-label" style="width: 100px">项目产值：</td>
                	<td class="form-td-content" style="width: 150px">
				        <input name='xmcz'  id='add_xmcz' data-options="prompt:'请输入项目产值',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
	                </td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width: 100px">当前项目完成产值：</td>
                	<td class="form-td-content" style="width: 150px">
				        <input name='dqxmwcz'  id='add_dqxmwcz' data-options="prompt:'请输入项目产值',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
	                </td>
                    <td class="form-td-label" style="width: 100px">城市地区：</td>
                	<td class="form-td-content" style="width: 150px"> 
				        <input name='csdq'  id='add_csdq' data-options="prompt:'请输入城市地区',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
	                </td>
					<td  class="form-td-label" style="width: 100px">坐标经度：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="add_zbjd" name="zbjd"  class="easyui-textbox" data-options="prompt:'请输入坐标经度'"   style="width:100%">
                    </td>
                </tr>
                <tr>
                	<td  class="form-td-label" style="width: 100px">坐标纬度：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="add_zbwd" name="zbwd"   class="easyui-textbox" data-options="prompt:'请输入坐标纬度'"  style="width:100%">
                    </td>
	                <td  class="form-td-label" style="width: 100px">总工期天数：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="add_zts" name="zts"  data-options=" prompt:'请输入总工期天数'" class="easyui-numberbox" min="0" max="1000000000"   class="easyui-validatebox span2"   style="width:100%">                    	
                    </td>
					<td  class="form-td-label" style="width: 100px">开工日期：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input name='kgrq'  id='add_kgrq' data-options="editable:false,prompt:'请选择开工日期'"  class="easyui-datebox" style='width: 100%;'/>
                    </td>
                </tr>
                <tr>
                    
                    <td  class="form-td-label" style="width: 100px">预计完成日期：</td>
                	<td class="form-td-content" style="width: 150px">
                		<input name='yjwcrq'  id='add_yjwcrq'  data-options="prompt:'请选择预计完成日期'" class="easyui-datebox" style="width:100%">
                	</td>
                    <td  class="form-td-label" style="width: 100px">地铁线路：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="add_dtxl" name="dtxl"  class="easyui-textbox" data-options="prompt:'请输入地铁线路'"  style="width:100%">
                    </td>
                     <td class="form-td-label" style="width: 100px">施工标段：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="add_sgbd" name="sgbd"  class="easyui-textbox" data-options="prompt:'请输入施工标段'"  style="width:100%">
                    </td>
                </tr>
                <tr>
                	<td  class="form-td-label" style="width: 100px" >总环数：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="add_zhs" name="zhs"  data-options=" prompt:'请输入总环数'" class="easyui-numberbox" min="0" max="1000000000"   class="easyui-validatebox span2"   style="width:100%">                    	
                    </td>
                    <td  class="form-td-label" style="width: 100px">当前掘进总环数：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="add_dqjzjhs" name="dqjzjhs"  data-options=" prompt:'请输入当前掘进总环数'" class="easyui-numberbox" min="0" max="1000000000"   class="easyui-validatebox span2"   style="width:100%">                    	
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
		 style="width:850px;height:390px;padding:5px 5px;" >
        <form id="editForm" method="post" data-options="novalidate:true">
        <input id="id" name="id" type="hidden"/>
			<table class="grid">
                <tr>
					<td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>项目Id：</td>
                    <td class="form-td-content" colspan="5" >
                    	<input name='xmId'  id='edit_xmId'  data-options="required:true,prompt:'请输入项目Id',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
                    </td>
                </tr>
	            <tr>
					<td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>项目名称：</td>
                    <td class="form-td-content" colspan="5" >
                    	<input name='xmMc'  id='edit_xmMc'  data-options="required:true,prompt:'请输入项目名称',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width: 100px">负责人：</td>
                	<td class="form-td-content" style="width: 150px">
				        <input name='fzr'  id='edit_fzr' data-options="prompt:'请输入项目负责人',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
	                </td>
                     <td class="form-td-label" style="width: 100px">中标价格：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="edit_zbjg" name="zbjg"  data-options="editable:true, prompt:'请输入中标价格'" class="easyui-numberbox" min="0" max="1000000000"   class="easyui-validatebox span2"   style="width:100%">                    	
                    </td>
                	<td class="form-td-label" style="width: 100px">项目产值：</td>
                	<td class="form-td-content" style="width: 150px">
				        <input name='xmcz'  id='edit_xmcz' data-options="prompt:'请输入项目产值',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
	                </td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width: 100px">当前项目完成产值：</td>
                	<td class="form-td-content" style="width: 150px">
				        <input name='dqxmwcz'  id='edit_dqxmwcz' data-options="prompt:'请输入项目产值',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
	                </td>
                    <td class="form-td-label" style="width: 100px">城市地区：</td>
                	<td class="form-td-content" style="width: 150px"> 
				        <input name='csdq'  id='edit_csdq' data-options="prompt:'请输入城市地区',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
	                </td>
					<td  class="form-td-label" style="width: 100px">坐标经度：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="edit_zbjd" name="zbjd"  class="easyui-textbox" data-options="prompt:'请输入坐标经度'"   style="width:100%">
                    </td>
                </tr>
                <tr>
                	<td  class="form-td-label" style="width: 100px">坐标纬度：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="edit_zbwd" name="zbwd"   class="easyui-textbox" data-options="prompt:'请输入坐标纬度'"  style="width:100%">
                    </td>
	                <td  class="form-td-label" style="width: 100px">总工期天数：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="edit_zts" name="zts"  data-options=" prompt:'请输入总工期天数'" class="easyui-numberbox" min="0" max="1000000000"   class="easyui-validatebox span2"   style="width:100%">                    	
                    </td>
					<td  class="form-td-label" style="width: 100px">开工日期：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input name='kgrq'  id='edit_kgrq' data-options="editable:false,prompt:'请选择开工日期'"  class="easyui-datebox" style='width: 100%;'/>
                    </td>
                </tr>
                <tr>
                    
                    <td  class="form-td-label" style="width: 100px">预计完成日期：</td>
                	<td class="form-td-content" style="width: 150px">
                		<input name='yjwcrq'  id='edit_yjwcrq'  data-options="prompt:'请选择预计完成日期'" class="easyui-datebox" style="width:100%">
                	</td>
                    <td  class="form-td-label" style="width: 100px">地铁线路：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="edit_dtxl" name="dtxl"  class="easyui-textbox" data-options="prompt:'请输入地铁线路'"  style="width:100%">
                    </td>
                     <td class="form-td-label" style="width: 100px">施工标段：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="edit_sgbd" name="sgbd"  class="easyui-textbox" data-options="prompt:'请输入施工标段'"  style="width:100%">
                    </td>
                </tr>
                <tr>
                	<td  class="form-td-label" style="width: 100px" >总环数：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="edit_zhs" name="zhs"  data-options=" prompt:'请输入总环数'" class="easyui-numberbox" min="0" max="1000000000"   class="easyui-validatebox span2"   style="width:100%">                    	
                    </td>
                    <td  class="form-td-label" style="width: 100px">当前掘进总环数：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="edit_dqjzjhs" name="dqjzjhs"  data-options=" prompt:'请输入当前掘进总环数'" class="easyui-numberbox" min="0" max="1000000000"   class="easyui-validatebox span2"   style="width:100%">                    	
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
		 style="width:850px;height:390px;padding:5px 5px;" >
        <form id="showForm" method="post" data-options="novalidate:true">
        <input id="id" name="id" type="hidden"/>
            <table class="grid">
                <tr>
					<td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>项目Id：：</td>
                    <td class="form-td-content" colspan="5" >
                    	<input name='xmId'  id='edit_xmId'  data-options="editable:false,required:true,prompt:'',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
                    </td>
                </tr>
	            <tr>
					<td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>项目名称：</td>
                    <td class="form-td-content" colspan="5" >
                    	<input name='xmMc'  id='edit_xmMc'  data-options="editable:false,required:true,prompt:'',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width: 100px">负责人：</td>
                	<td class="form-td-content" style="width: 150px">
				        <input name='fzr'  id='edit_fzr' data-options="editable:false,prompt:'',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
	                </td>
                     <td class="form-td-label" style="width: 100px">中标价格：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="edit_zbjg" name="zbjg"  data-options="editable:false, prompt:''" class="easyui-numberbox" min="0" max="1000000000"   class="easyui-validatebox span2"   style="width:100%">                    	
                    </td>
                	<td class="form-td-label" style="width: 100px">项目产值：</td>
                	<td class="form-td-content" style="width: 150px">
				        <input name='xmcz'  id='edit_xmcz' data-options="editable:false,prompt:''"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
	                </td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width: 100px">当前项目完成产值：</td>
                	<td class="form-td-content" style="width: 150px">
				        <input name='dqxmwcz'  id='edit_dqxmwcz' data-options="editable:false,prompt:''"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
	                </td>
                    <td class="form-td-label" style="width: 100px">城市地区：</td>
                	<td class="form-td-content" style="width: 150px"> 
				        <input name='csdq'  id='edit_csdq' data-options="editable:false,prompt:''"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
	                </td>
					<td  class="form-td-label" style="width: 100px">坐标经度：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="edit_zbjd" name="zbjd"  class="easyui-textbox" data-options="editable:false,prompt:''"   style="width:100%">
                    </td>
                </tr>
                <tr>
                	<td  class="form-td-label" style="width: 100px">坐标纬度：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="edit_zbwd" name="zbwd"   class="easyui-textbox" data-options="editable:false,prompt:''"  style="width:100%">
                    </td>
	                <td  class="form-td-label" style="width: 100px">总工期天数：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="edit_zts" name="zts"  data-options=" editable:false,prompt:''" class="easyui-numberbox" min="0" max="1000000000"   class="easyui-validatebox span2"   style="width:100%">                    	
                    </td>
					<td  class="form-td-label" style="width: 100px">开工日期：</td>
                    <td class="form-td-content" style="width: 150px">
                        <input name="kgrq" id="edit_kgrq" class="easyui-textbox" data-options="editable:false,prompt:''"  style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td  class="form-td-label" style="width: 100px">预计完成日期：</td>
                	<td class="form-td-content" style="width: 150px">
                	    <input name="yjwcrq"  id="edit_yjwcrq" class="easyui-textbox" data-options="editable:false,prompt:''"  style="width:100%">
                	</td>
                    <td  class="form-td-label" style="width: 100px">地铁线路：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="edit_dtxl" name="dtxl"  class="easyui-textbox" data-options="editable:false,prompt:''"  style="width:100%">
                    </td>
                     <td class="form-td-label" style="width: 100px">施工标段：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="edit_sgbd" name="sgbd"  class="easyui-textbox" data-options="editable:false,prompt:''"  style="width:100%">
                    </td>
                </tr>
                <tr>
                	<td  class="form-td-label" style="width: 100px" >总环数：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="edit_zhs" name="zhs"  data-options="editable:false, prompt:''" class="easyui-numberbox" min="0" max="1000000000"   class="easyui-validatebox span2"   style="width:100%">                    	
                    </td>
                    <td  class="form-td-label" style="width: 100px">当前掘进总环数：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="edit_dqjzjhs" name="dqjzjhs"  data-options="editable:false, prompt:''" class="easyui-numberbox" min="0" max="1000000000"   class="easyui-validatebox span2"   style="width:100%">                    	
                    </td>
                </tr>
        	</table>
        </form>
	</div>
	<div id="show-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#showDialog').dialog('close')">关闭</a>
	</div>
	
	<!-- 弹出框－导入窗口 -->
	<div id="impDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#imp-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:560px;height:200px;padding:5px 5px;" >
        <form  id="impForm" method="post" enctype="multipart/form-data">
        <input type="hidden" id="filename" />
            <table class="grid">
                <tr>
                    <td class="form-td-label" ><span style="color: red;">*</span>导入文件路径：</td>
                    <td style="width:400px;">
                        <input id="file" name="file" colspan="3" accept=".xls" class="easyui-filebox" data-options="buttonText:'选择文件',required:true,prompt:'选择文件'" style="width:100%">
                    </td>
                </tr>
                <tr>
                	<td class="form-td-label" >Excel模板地址：</td>
                	<td>
                		<a href="${path }/zsjk/jt/zsJkJtXmxx/excelTemplate">点击下载模板</a>
                	</td>
                </tr>
                <tr>
                	<td  colspan="2">
                	    <span >
		                	<font color='red'>
		                		备注：请严格按照模板格式录入数据，每行数据之间不得存在空行，模板外部不得编辑
		                		<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                		或增添任何文字或图片，模板中有“ * ”标注的列为必填项；
		                		
		                	</font>
	                	</span>
                	</td>
                </tr>
            </table>    
        </form>
    </div>
    <div id="imp-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="impAjax()">确定</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#impDialog').dialog('close')">关闭</a>
	</div>
</body>
</html>