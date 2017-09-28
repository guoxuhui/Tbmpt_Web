<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>线路中心线信息管理</title>
<script type="text/javascript" src="${staticPath}/static/js/gpztcl/base/gpztclSjzxInfo.js?v=20170424181047 " charset="utf-8"></script>
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
<!-- 【线路中心线信息管理】查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:71px;border: false;overflow: auto;" data-options="collapsible:true">
		<form id="searchForm_gpztclSjzxInfoParent">
			<table class="grid" width='650px' border='0' cellspacing='0' style='padding:5px'>
			  <tr>
			  	<td class="form-td-label" style="width: 80px">项目名称：</td>
			    <td align="left" width="160px"colspan="3">
			      <input   class="easyui-combobox"  name='proId'  data-options="prompt:'请选择项目名称'" id="query_gcBh_gcztclSjzxInfo"  style='width: 410px'>
			    </td>
			    <td align="left" width="170px" >
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchGpztclSjzxInfoParentFun();">查询</a>
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleanGpztclSjzxInfoParentFun();">重置</a>
			    </td>
			  </tr>
			</table>
		</form>
	</div>

	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
		<table id="dataGrid_gpztclSjzxInfoParent" title="列表" data-options="fit:true,border:false"></table>
	</div>

<div id="toolbarParent" style="display: none;">
    	<a onclick="reloadGpztclSjzxInfoParent();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<a onclick="clearGpztclSjzxInfoParentSelections();" id="dataGrid_gpztclSjzxInfoParent_button_seleClean" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    	<shiro:hasPermission name="/gpztcl/base/gpztclSjzxInfo/imp">
    		<a onclick="addGpztclSjzxInfoFun();" id="dataGrid_gpztclSjzxInfoParent_button_Imp"  href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导入线路中心线信息</a>
    	</shiro:hasPermission>
</div>

<!------------------------------------------------- 以上为主表、以下为子表 ----------------------------------------------->

<div id="toolbar" style="display: none;">
    	<a onclick="reloadGpztclSjzxInfo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
	    <a onclick="deleteGpztclSjzxInfoFun();" id="datagrid_gpztclSjzxInfo_del" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	<a onclick="openGpztclSjzxInfoImp();" id="dataGrid_gpztclSjzxInfo_button_ImpExcel"  href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导入Excle</a>
    	<a onclick="clearGpztclSjzxInfoSelections();" id="dataGrid_gpztclSjzxInfo_button_seleClean" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
</div>
	<!-- 【线路中心线信息管理】新增弹出框-->
   <form id="addForm_gpztclSjzxInfo" method="post" data-options="novalidate:true">
   		<input type="hidden" id="addForm_gpztclSjzxInfoClumOne" name="clumOne">
   		<input type="hidden" id="addForm_gcBh" name="gcBh">
   		<input type="hidden" id="addForm_qlBh" name="qlBh">
   		<input type="hidden" id="addForm_xlBh" name="xlBh">
		<div id="addDialog_gpztclSjzxInfo" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal:true"	style="width:1000px;height:450px;padding:10px 10px;" >
				<table class="grid" width='650px' border='0' cellspacing='0' style='padding:5px'>
					<tr>
				    <td  class="form-td-label" >项目名称：</td>
				    <td class="form-td-content" colspan="3">
				      	<span id="add_projectName"></span>
				    </td>
				  </tr>
				  <tr>
				    <td  class="form-td-label" >区间名称：</td>
				    <td class="form-td-content" >
				      	<span id="add_qujianName"></span>
				    </td>
				    <td  class="form-td-label" >线路名称：</td>
				    <td class="form-td-content" >
				      	<span id="add_lineName"></span>
				    </td>
				  </tr>
				</table>
			<!-- 【线路中心线信息管理】结果列表 -->
				<div class="easyui-panel" data-options="border:false" style="width:100%;height:280px">
					<table id="dataGrid_gpztclSjzxInfo" title="线路中心线信息管理" data-options="fit:true,border:false"></table>
				</div>
		</div>
   </form>
   <div id="add-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="addGpztclSjzxInfoAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#addDialog_gpztclSjzxInfo').dialog('close')">关闭</a>
   </div>
	
	
	<div id="openImpexcel_gpztclSjzxInfo" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#upload-dialog-buttons',closed:true,cache: false,modal:true"	
	style="width:470px;height:210px;padding:10px 10px;" >
			<form id="uploadFile_gpztclsjzxInfo_Form"  method="post" data-options="novalidate:true" enctype="multipart/form-data">
				<table >
					<tr>
					    <td align="left" width="160px" >
				        	<input class="easyui-filebox"  name='clumOne' label="请选择要导入的文件：" labelPosition="top"
				        	 accept=".xls,.xlsx"
				        	data-options="prompt:'选择文件',buttonText:'&nbsp;选&nbsp;择&nbsp;',required:true"   id="query_gcBh_gcztclSjzxInfo"  style='width: 410px'>
				    	</td>
				  </tr>
				  <tr>
				  	<td><a href="${staticPath}/gpztcl/base/gpztclSjzxInfo/downLoadModel" >点击下载导入模板</a></td>
				  </tr>
				  <tr>
                	<td  colspan="2">
                	    <span >
		                	<font color='red'>
		                		备注：请严格按照模板格式录入数据，每行数据之间不得存在空行，
		                		<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                		           模板外部不得编辑或增添任何文字或图片，
		                		<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                		           模板中有“ * ”标注的列为必填项；
		                	</font>
	                	</span>
                	</td>
                </tr>
				</table>
			</form>
	</div>
			<div id="upload-dialog-buttons">
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="uploadFile_gpztclsjzx()">保存</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#openImpexcel_gpztclSjzxInfo').dialog('close')">关闭</a>
			</div>
			
			
		<!-- 查看页面 -->
		<div id="showToolbar" style="display: none;">
	    	<a onclick="reloadGpztclSjzxInfoShow();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
	    	<shiro:hasPermission name="/gpztcl/base/gpztclSjzxInfo/exp">
		    	<a onclick="expGpztclSjzxInfoXls();" href="javascript:void(0);"  id="dataGrid_gpztclSjzxInfo_button_ExpExcel" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
    		</shiro:hasPermission>
		</div>
		<div id="showDialog_gpztclSjzxInfo" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#show-dialog-buttons',closed:true,cache: false,modal:true"	style="width:960px;height:450px;padding:10px 10px;" >
				<table class="grid" width='650px' border='0' cellspacing='0' style='padding:5px'>
					<tr>
				    <td  class="form-td-label" >项目名称：</td>
				    <td class="form-td-content" colspan="3">
				      	<span id="show_projectName"></span>
				    </td>
				  </tr>
				  <tr>
				    <td  class="form-td-label" >区间名称：</td>
				    <td class="form-td-content" >
				      	<span id="show_qujianName"></span>
				    </td>
				    <td  class="form-td-label" >线路名称：</td>
				    <td class="form-td-content" >
				      	<span id="show_lineName"></span>
				      	<input type="hidden" id="show_lineNameId">
				    </td>
				  </tr>
				</table>
			<!-- 【线路中心线信息管理】结果列表 -->
				<div class="easyui-panel" data-options="border:false" style="width:100%;height:280px">
					<table id="showDataGrid_gpztclSjzxInfo" title="线路中心线信息管理" data-options="fit:true,border:false"></table>
				</div>
		</div>	
		<div id="show-dialog-buttons">
			<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#showDialog_gpztclSjzxInfo').dialog('close')">关闭</a>
	   </div>
</body>
</html>
