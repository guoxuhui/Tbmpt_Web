<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>实测中线信息管理</title>
<script type="text/javascript" src="${staticPath}/static/js/gpztcl/base/gpztclSczxpc.js?v=7" charset="utf-8"></script>
<style type="text/css">
	td{
		 white-space: nowrap;
	}
	td input{
		white-space: normal;
	}
	//更改表格标题文字大小
.datagrid-header span {     font-size: 5px !important; }  
.datagrid-header-row {     /* background-color: #e3e3e3; */     /* color: #111 */     Height: 12px; }

//更改表格中单元格行间距
#table .datagrid-btable tr{height: 1px;}

//更改表格标题的行间距
.panel-title {
    margin-top: 2px;
    margin-bottom: 2px;
    font-size: 5px;
    color: inherit;
}
	
</style>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
<!-- 【实测中线信息管理】查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:105px;border: false;overflow: auto;" data-options="collapsible:true">
		<form id="searchForm_gpztclSczxpc">
			<table class="grid" width='650px' border='0' cellspacing='0' style='padding:5px'>
			 <tr>
			    <td class="form-td-label" style="width: 80px" >所属项目：</td><td align="left" width="100%" colspan="3">
			      <input   class="easyui-textbox"  name='gcBh'  id="gcBh_search"  style='width: 100%' data-options="prompt:'请选择所属项目'">
			    </td>
			    <td class="form-td-label" style="width: 80px">区间名称：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='qlBh'  id="qlBh_search" data-options="prompt:'请选择区间'" style='width: 250px'>
			    </td>
			  </tr>
			  <tr>
			    <td class="form-td-label" style="width: 80px">线路名称：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='xlBh'  id="xlBh_search"  data-options="prompt:'请选择线路'"  style='width: 149px'>
			    </td>
			    <td class="form-td-label" style="width: 80px">测量类型：</td><td align="left" width="160px">
			      <input   class="easyui-textbox" id="clType_search"  name='clType' data-options="prompt:'请选择测量类型'"  panelHeight="80"  style='width: 149px'>
			    </td>
			    <td ></td><td align="center" width="170px">
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchGpztclSczxpcFun();">查询</a>
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleanGpztclSczxpcFun();">重置</a>
			    </td>
			  </tr>
			</table>
		</form>
	</div>

<!-- 【实测中线信息管理】结果列表 -->
	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
		<table id="dataGrid_gpztclSczxpc" title="列表" data-options="fit:true,border:false"></table>
	</div>

<div id="toolbar" style="display: none;">
    	<a onclick="reloadGpztclSczxpc();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<shiro:hasPermission name="/gpztcl/base/gpztclSczxpc/add">
    	<a id="add_button_gpztclSczxpc" onclick="addGpztclSczxpcFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/gpztcl/base/gpztclSczxpc/edit">
    	<a id="edit_button_gpztclSczxpc" onclick="editGpztclSczxpcFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/gpztcl/base/gpztclSczxpc/del">
    	<a id="del_button_gpztclSczxpc" onclick="deleteGpztclSczxpcFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/gpztcl/base/gpztclSczxpc/sb">
    	<a id="sb_button_gpztclSczxpc" onclick="ifSb('1');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-accept'">上报</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/gpztcl/base/gpztclSczxpc/cancelSb">
    	<a id="cancelSb_button_gpztclSczxpc" onclick="ifSb('0');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">撤销上报</a>
    	</shiro:hasPermission>
    	<a onclick="expGpztclSczxpcXls();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
    	<a id="cancelSelect_button_gpztclSczxpc" onclick="clearGpztclSczxpcSelections();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
</div>
<!-- 【实测中线信息管理】新增弹出框-->	
<div id="addDialog_gpztclSczxpc" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,maximizable:true"	style="width:100%;height:100%;padding:10px 10px;overflow: hidden;" >
        <form id="addForm_gpztclSczxpc" method="post" data-options="novalidate:true">
          <input type="hidden" id='dataSta' value='original'/>
          <input type="hidden" name="dataList" id="add_details"></input>
			<table  class="grid">
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>线路名称：</td>
			    <td class="form-td-content"  style="width:250px">
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='xlBh' id='add_xlBh_gpztclSczxpc'  
			      data-options="prompt:'请选择线路',validType:'length[1,40]',required:true"  style='width: 100px'>
			    </td>
			    <td  class="form-td-label" >工程名称：</td>
			    <td class="form-td-content" colspan="3" style="width:400px">
			      <label id="add_gcName_gpztclSczxpc"></label>
			      <input type="hidden"    name='gcBh' id='add_gcBh_gpztclSczxpc' "  >
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >区间名称：</td>
			    <td class="form-td-content" style="width:250px">
			      <label id="add_qlName_gpztclSczxpc"></label>
			      <input type="hidden"   name='qlBh' id='add_qlBh_gpztclSczxpc' >
			    </td>
			    <td  class="form-td-label" ><span style="color: red;">*</span>测量类型：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  panelHeight="80"  name='clType' id='add_clType_gpztclSczxpc'  data-options="prompt:'请输入测量类型',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" ><span style="color: red;">*</span>测量日期：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datebox" class="easyui-validatebox span2" name='clTime'  id='add_clTime_gpztclSczxpc' data-options="prompt:'请输入测量时间',required:true" style='width: 150px'>			
			    </td>
			   
			  </tr>
			</table>
        </form>
        <!-- 【实测中线信息明细管理】结果列表 -->
          <div class="easyui-panel"  style="width:100%;height:87%;">
              <table id="dataGrid_gpztclSczxInfo" title="列表<font color='red'>(*鼠标双击导向系统数据行,可直接编辑数据*)</font>" data-options="fit:true,border:false" style="height:200px"></table>
          </div>

     <div id="toolbar2" style="display: none;">
      <a onclick="reloadGpztclSczxInfo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<a onclick="deleteGpztclSczxInfoFun('dataGrid_gpztclSczxInfo');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	<a onclick="impQzyFile('add');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-table_go'">导入全站仪数据</a>
    	<a onclick="impDaoXiangSystemFile('add');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_go'">导入导向系统数据</a>
    	<a onclick="countZb();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-calculator'">计算设计中线坐标</a>
    	<a onclick="clearGpztclSczxInfoSelections('dataGrid_gpztclSczxInfo');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    </div>
	</div>
	<div id="add-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="addGpztclSczxpcAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#addDialog_gpztclSczxpc').dialog('close')">关闭</a>
	</div>

<!-- 【实测中线信息管理】编辑弹出框 -->
	<div id="editDialog_gpztclSczxpc" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#edit-dialog-buttons',closed:true,cache: false,maximizable:true"	style="width:100%;height:100%;padding:10px 10px;overflow: hidden;">
        <form id="editForm_gpztclSczxpc" method="post" data-options="novalidate:true">
        <input type="hidden" id='dataStaEdit' value='original'/>
           <input name="id" type="hidden" >
           <input name="updateTime" type="hidden" >
           <input type="hidden"  name='impMan' id='edit_impMan_gpztclSczxpc' >
           <input type="hidden" name='impTime'  id='edit_impTime_gpztclSczxpc'>
		   <input type="hidden"  name='sendState' id='edit_sendState_gpztclSczxpc'  >
           <input type="hidden"   name='starNo' id='edit_starNo_gpztclSczxpc'  >
		   <input type="hidden"  name='endNo' id='edit_endNo_gpztclSczxpc' >
		   <input type="hidden" name="dataList" id="edit_details"></input>
			<table  class="grid">
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>线路名称：</td>
			    <td class="form-td-content"  style="width:250px">
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='xlBh' 
			      id='edit_xlBh_gpztclSczxpc'  data-options="prompt:'请选择线路',validType:'length[1,40]',required:true"  
			      style='width: 100px'>
			    </td>
			    <td  class="form-td-label" >工程名称：</td>
			    <td class="form-td-content"  colspan="3" style="width:400px">
			      <label id="edit_gcName_gpztclSczxpc"></label>
			      <input type="hidden"  name='gcBh' id='edit_gcBh_gpztclSczxpc'  >
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >区间名称：</td>
			    <td class="form-td-content"  style="width:250px">
			      <label id="edit_qlName_gpztclSczxpc"></label>
			      <input type="hidden"  name='qlBh' id='edit_qlBh_gpztclSczxpc' >
			    </td>
			    <td  class="form-td-label" ><span style="color: red;">*</span>测量类型：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2" panelHeight="80"  name='clType' id='edit_clType_gpztclSczxpc'  data-options="prompt:'请输入测量类型',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" ><span style="color: red;">*</span>测量日期：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datebox" class="easyui-validatebox span2" name='clTime'  id='edit_clTime_gpztclSczxpc' data-options="prompt:'请输入测量时间'" style='width: 150px'>	
			    </td>
			  </tr>
			</table>
        </form>
         <!-- 【实测中线信息明细管理】结果列表 -->
          <div class="easyui-panel"  style="width:100%;height:87%;">
              <table id="dataGrid_gpztclSczxInfo_edit" title="列表<font color='red'>(*鼠标双击导向系统数据行,可直接编辑数据*)</font>" data-options="fit:true,border:false" style="height:200px"></table>
          </div>
           <div id="toolbar3" style="display: none;">
           <a onclick="reloadGpztclSczxInfo_edit();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
	    	<a onclick="deleteGpztclSczxInfoFun('dataGrid_gpztclSczxInfo_edit');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
	    	<a onclick="impQzyFile('edit');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-table_go'">导入全站仪数据</a>
	    	<a onclick="impDaoXiangSystemFile('edit');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_go'">导入导向系统数据</a>
	    	<a onclick="expGpztclSczxInfoXls();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-calculator'">计算设计中线坐标</a>
	    	<a onclick="clearGpztclSczxInfoSelections('dataGrid_gpztclSczxInfo_edit');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
	    </div>
	</div>
	<div id="edit-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="editGpztclSczxpcAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#editDialog_gpztclSczxpc').dialog('close')">关闭</a>
	</div>
	
	<!--导入全站仪数据弹出框 -->
	<div id="impQzyDialog_gpztclSczxpc" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#impQzyFile-dialog-buttons',closed:true,cache: false,modal: true"
	style="width:400px;height:150px;padding:10px 10px;" >
        <form id="impQzyForm_gpztclSczxpc" method="post" data-options="novalidate:true" enctype="multipart/form-data">
			<table  class="grid">
			  <tr>
			    <td  class="form-td-label" >导入数据：</td>
			    <td class="form-td-content" >
			        <input id="qzyFile" name="qzyFile" accept=".txt,.dat" class="easyui-filebox" data-options="buttonText:'选择文件',prompt:'选择文件'" style="width:230px">
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="impQzyFile-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="impQzyFileActionAjax()">导入</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#impQzyDialog_gpztclSczxpc').dialog('close')">关闭</a>
	</div>
	
	<!-- 【实测中线明细信息管理】解析文件弹出框-->	
     <div id="parseFileDialog_gpztclSczxpc" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#parseFile-dialog-buttons',closed:true,cache: false,maximizable:true,modal: true"
     	style="width:600px;height:400px;padding:10px 10px 10px 10px;overflow: hidden;" >
        <form id="parseFileForm_gpztclSczxpc" method="post" data-options="novalidate:true">
            <input type="hidden" name="dataList" id="p_dataList"></input>
			<table  class="grid">
			  <tr>
			    <td align="center" style=" height:15px">环号</td>
			    <td align="center" style=" height:15px">横向(X)</td>
			    <td align="center" style=" height:15px">纵向(Y)</td>
			    <td align="center" style=" height:15px">高程</td>
			  </tr>
			   <tr>
			     <td>
			       <input id="p_hh" name="hh" style="width:130px;" panelHeight="100px"></input>
			      </td>
			     <td >
			        <input id="p_sczbX" name="sczbX" style="width:130px;" panelHeight="100px"></input>
                  </td>
			     <td>
			         <input id="p_sczbY" name="sczbY" style="width:130px;" panelHeight="100px"></input>
			     </td>
			     <td>
			       <input id="p_sczbH" name="sczbH" style="width:130px;" panelHeight="100px"></input>
			     </td>
			  </tr>
			</table>
        </form>
     <div class="easyui-panel"  style="width:100%;height:85%;">
	    <!-- 【实测中线信息明细管理】解析结果列表 -->
	   <table id="dataGrid_parseFileGpztclSczxInfo" title="列表" data-options="fit:true,border:false" style="height:100px"></table>
	 </div>
	<div id="parseFile-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="configParseFileGpztclSczxpcAjax()">确认</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#parseFileDialog_gpztclSczxpc').dialog('close')">关闭</a>
	</div>
	</div>
	
		<!--导入导向系统数据弹出框 -->
	<div id="impDaoXiangSystemDialog_gpztclSczxpc" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#impDaoXiangSystemFile-dialog-buttons',closed:true,cache: false,modal: true"
	style="width:400px;height:150px;padding:10px 10px;" >
        <form id="impDaoXiangSystemForm_gpztclSczxpc" method="post" data-options="novalidate:true" enctype="multipart/form-data">
           <input type="hidden" id="dx_dataList" name="dataList">
           <input type="hidden" id="dx_startNo" name="starNo">
           <input type="hidden" id="dx_endNo" name="endNo">
			<table  class="grid">
			  <tr>
			    <td  class="form-td-label" >导入数据：</td>
			    <td class="form-td-content" >
			        <input id="dxxtFile" name="dxxtFile" accept=".db" class="easyui-filebox" data-options="buttonText:'选择文件',prompt:'选择文件'" style="width:230px">
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="impDaoXiangSystemFile-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="parseDaoXiangSystemFileActionAjax()">导入</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#impDaoXiangSystemDialog_gpztclSczxpc').dialog('close')">关闭</a>
	</div>
</body>
</html>
