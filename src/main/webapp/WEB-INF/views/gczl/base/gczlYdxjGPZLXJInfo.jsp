<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>盾构施工质量巡检信息</title>
<script type="text/javascript" src="${staticPath}/static/js/gczl/base/gczlYdxjGPZLXJInfo.js?v=20170522104519" charset="utf-8"></script>
<style type="text/css">	td{		 white-space: nowrap;	}	td input{		white-space: normal;	}</style></head>
<jsp:include  page="../../sys/base/uploadPicture.jsp" />
<body class="easyui-layout" data-options="fit:true,border:false">
<!-- 【盾构施工质量巡检信息】查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:0px;border: false;overflow: hidden;overflow :auto" data-options="collapsible:true">
		<form id="searchForm_gczlYdxjGPZLXJInfo">
			<table class="grid" width='650px' border='0' cellspacing='0' style='padding:5px'>
			  <tr>
			    <td class="form-td-label" style="width: 80px">工程名称：</td>
			    <td align="left" width="160px"colspan="3">
			      <input   class="easyui-combobox"  name='gcBh'  data-options="prompt:'请选择工程名称'" id="query_gcBh_gczlYdxjGPZLXJInfo"  style='width: 410px'>
			    </td>
			    <td class="form-td-label" style="width: 80px">质量问题分类：</td><td align="left" width="160px">
			      <input   class="easyui-combobox"  name='typeName' data-options="prompt:'请选择分类'" id="query_typeName_gczlYdxjGPZLXJInfo"  style='width: 149px'>
			    </td>
			    <td class="form-td-label" style="width: 80px">质量问题小类：</td><td align="left" width="160px">
			      <input   class="easyui-combobox"  name='qkms'  data-options="prompt:'请选择小类'" id="query_typeChild_gczlYdxjGPZLXJInfo"  style='width: 149px'>
			    </td>
			    <td class="form-td-label" style="width: 80px">上报状态：</td><td align="left" width="160px">
			      <select class="easyui-combobox" class="easyui-validatebox span2"  name='sbzt'  data-options="prompt:'请选择',required:true"  style='width: 150px'>
			      	<option value="">--请选择--</option>
			      	<option value="未上报">未上报</option>
			      	<option value="已上报">已上报</option>
			      </select>
			    </td>
			 </tr>
			 <tr>
			    <td class="form-td-label" style="width: 80px">起始巡检时间：</td><td align="left" width="160px">
			      <input   class="easyui-datetimebox"  name='xjtimeStr' data-options="prompt:'请输入起始时间'"   style='width: 149px'>
			    </td>
			    <td class="form-td-label" style="width: 80px">截止巡检时间：</td><td align="left" width="160px">
			      <input   class="easyui-datetimebox"  name='xjtimeEnd'  data-options="prompt:'请输入截止时间'"  style='width: 149px'>
			    </td>
			    <td class="form-td-label" style="width: 80px">整改状态：</td><td align="left" width="160px">
			       <select class="easyui-combobox" class="easyui-validatebox span2"  name='zgzt'  data-options="prompt:'请选择',required:true"  style='width: 150px'>
			      	<option value="">--请选择--</option>
			      	<option value="未整改">未整改</option>
			      	<option value="已整改">已整改</option>
			      </select>
			    </td>
			    <td class="form-td-label" style="width: 80px">点位：</td><td align="left" width="160px">
			      <select class="easyui-combobox" class="easyui-validatebox span2"  name='dw' data-options="prompt:'请选择点位',required:true"  style='width: 150px'>
			      	<option value="">--请选择--</option>
			      	<option value="1">1</option>
			      	<option value="2">2</option>
			      	<option value="3">3</option>
			      	<option value="4">4</option>
			      	<option value="5">5</option>
			      	<option value="6">6</option>
			      	<option value="7">7</option>
			      	<option value="8">8</option>
			      	<option value="9">9</option>
			      	<option value="10">10</option>
			      	<option value="11">11</option>
			      	<option value="12">12</option>
			      </select>
			    </td>
			    <td class="form-td-label" style="width: 80px">审核状态：</td><td align="left" width="160px">
			       <select class="easyui-combobox" class="easyui-validatebox span2"  name='shzt'  data-options="prompt:'请选择',required:true"  style='width: 150px'>
			      	<option value="">--请选择--</option>
			      	<option value="未审核">未审核</option>
			      	<option value="已审核">已审核</option>
			      </select>
			    </td>
			  </tr>
			  <tr>
			    <td class="form-td-label" style="width: 80px">巡检部门：</td>
			    <td align="left" width="160px" colspan="3">
			      <input   class="easyui-combobox"  name='xjbm' id="query_xjbm" data-options="prompt:'请输入巡检部门'"  style='width: 410px'>
			    </td>
			    <td class="form-td-label" style="width: 80px">巡检人员：</td><td align="left" width="160px">
			      <input id="add_empName" class="easyui-textbox" alt="add_emp" 
			      	data-options="
			      	required:true,
			      	labelPosition: 'top',
			      	prompt:'请选择',
			      	icons:[
				      	{
				      		 iconCls:'icon-bullet_cross',
	                         handler: function(e){
                            	$(e.data.target).textbox('clear');
                            	$('#add_empId').val('');
				      		}
				      	},
				      	{
				      		 iconCls:'icon-search',
	                         handler: function(e){
	                         	empSelection(e);
				      		}
				      	}
			      	]"
			      	 style="width:100%">
                  <input id="add_empId" name="xjry" type="hidden"/>
			    </td>
			    <td class="form-td-label" style="width: 80px">环号：</td><td align="left" width="160px">
			      <input   class="easyui-numberbox"  name='hh' data-options="prompt:'请输入环号'"  style='width: 149px'>
			    </td>
			    <td align="left" width="170px">
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchGczlYdxjGPZLXJInfoFun();">查询</a>
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleanGczlYdxjGPZLXJInfoFun();">重置</a>
			    </td>
			  </tr>
			</table>
		</form>
	</div>

<!-- 【盾构施工质量巡检信息】结果列表 -->
	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
		<table id="dataGrid_gczlYdxjGPZLXJInfo" title="列表" data-options="fit:true,border:false"></table>
	</div>

<div id="toolbar" style="display: none;">
    	<a onclick="reloadGczlYdxjGPZLXJInfo();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	    	<shiro:hasPermission name="/gczl/base/gczlYdxjGPZLXJInfo/add">
    	    		<a onclick="addGczlYdxjGPZLXJInfoFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	    	</shiro:hasPermission>
				<shiro:hasPermission name="/gczl/base/gczlYdxjGPZLXJInfo/edit">
    	    		<a onclick="editGczlYdxjGPZLXJInfoFun();" id="edit_GczlYdxjGPZLXJInfo" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	    	</shiro:hasPermission>
    	    	<shiro:hasPermission name="/gczl/base/gczlYdxjGPZLXJInfo/del">
    	    		<a onclick="deleteGczlYdxjGPZLXJInfoFun();" href="javascript:void(0);" id="del_button_gczlYdxjGPZLXJInfo" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	    	</shiro:hasPermission>
		    	<a onclick="clearGczlYdxjGPZLXJInfoSelections();" href="javascript:void(0);" id="unSelect_gczlYdxjGPZLXJInfo" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a> 
		    	<a onclick="expGczlYdxjGPZLXJInfoXls();" href="javascript:void(0);" id="expExcel_gczlYdxjGPZLXJInfo" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
    	    	<shiro:hasPermission name="/gczl/base/gczlYdxjGPZLXJInfo/sbInfo">
			    	<a onclick="sendUpGczlYdxjGPZLXJInfo();" href="javascript:void(0);" id="sendup_button" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">上报</a>
    	    	</shiro:hasPermission>
    	    	<shiro:hasPermission name="/gczl/base/gczlYdxjGPZLXJInfo/shInfo">
			    	<a onclick="shengHeGczlYdxjGPZLXJInfo();" href="javascript:void(0);" id="shengHe_button" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">审核</a>
    	    	</shiro:hasPermission>
    	    	<shiro:hasPermission name="/gczl/base/gczlYdxjGPZLXJInfo/zgInfo">
			    	<a onclick="zhengGaiFkGczlYdxjGPZLXJInfo();" href="javascript:void(0);" id="zhenggai_button" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">整改反馈</a>
    	    	</shiro:hasPermission>
    	    	<shiro:hasPermission name="/gczl/base/gczlYdxjGPZLXJInfo/upload">
			    	<a onclick="uploadGczlYdxjGPZLXJInfoImg('0');" href="javascript:void(0);" id="uploadImg_button" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-20130406125647919_easyicon_net_16'">上传整改前图片</a>
    	    	</shiro:hasPermission>
    	    	<shiro:hasPermission name="/gczl/base/gczlYdxjGPZLXJInfo/zghupload">
			    	<a onclick="uploadGczlYdxjGPZLXJInfoImg('1');" href="javascript:void(0);" id="uploadZghImg_button" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-20130406125647919_easyicon_net_16'">上传整改后图片</a>
    	    	</shiro:hasPermission>
    	 </div>
    	<!-- 【盾构施工质量巡检信息】新增弹出框-->	
    	<div id="addDialog_gczlYdxjGPZLXJInfo" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache:false,modal:true"	
    	style="width:823px;height:438px;padding:10px 10px;display:none;" >
        <form id="addForm_gczlYdxjGPZLXJInfo" method="post" data-options="novalidate:true">
			<table  class="grid" style="width: 780px">
			  <tr>
			    <td class="form-td-label" width="109px"><span style="color: red;">*</span>所属工程：</td>
			    <td class="form-td-content" colspan="3">
			    	${projectName}
			    	<input type="hidden" id="sushuGcId_gczlYdxjGPZLXJInfo" value="${projectId}" />
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>巡检时间：</td>
			    <td class="form-td-content" colspan="3">
			      <input class="easyui-datetimebox" class="easyui-validatebox span2" name='xjtime'  id='add_xjtime_gczlYdxjGPZLXJInfo' data-options="prompt:'请输入巡检时间',required:true" style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>区间：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='qlBh' id='add_qlBh_gczlYdxjGPZLXJInfo'  data-options="prompt:'请输入选择区间',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label"  width="109px"><span style="color: red;">*</span>线路：</td>
			    <td class="form-td-content" >
			      <input class="easyui-combobox" class="easyui-validatebox span2"  name='xlBh' id='add_xlBh_gczlYdxjGPZLXJInfo'  data-options="prompt:'请选择线路',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>环号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-numberbox" class="easyui-validatebox span2"  name='hh' id='add_hh_gczlYdxjGPZLXJInfo'  data-options="prompt:'请输入环号',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" ><span style="color: red;">*</span>点位：</td>
			    <td class="form-td-content" >
			       <select class="easyui-combobox" class="easyui-validatebox span2"  name='dw' id='add_dw_gczlYdxjGPZLXJInfo'  data-options="prompt:'请选择点位',required:true"  style='width: 150px'>
			      	<option value="1">1</option>
			      	<option value="2">2</option>
			      	<option value="3">3</option>
			      	<option value="4">4</option>
			      	<option value="5">5</option>
			      	<option value="6">6</option>
			      	<option value="7">7</option>
			      	<option value="8">8</option>
			      	<option value="9">9</option>
			      	<option value="10">10</option>
			      	<option value="11">11</option>
			      	<option value="12">12</option>
			      </select>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>质量问题分类：</td>
			    <td class="form-td-content" colspan="3">
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='typeName' id='add_typeName_gczlYdxjGPZLXJInfo'  data-options="prompt:'请输入质量问题分类',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			  </tr>
			  </table>
<!-- ****【管片破损】***************************************************************************************************-->	
<div id="selectChild_gczlYdxjGPZLXJInfo">
	<div id="gpps_div_gczlYdxjGPZLXJInfo"  style="width: 563px">
		<table class="grid" style="width: 780px">
		  <tr>
		    <td  class="form-td-label"  width="109px"><span style="color: red;">*</span>破损情况描述：</td>
		    <td class="form-td-content" colspan="3">
		      <input class="easyui-combotree" class="easyui-validatebox span2" name="gppsQkMiaoshu" id='add_psqkms_gczlYdxjGPZLXJInfo'  data-options="prompt:'请选择破损情况',required:true"  style='width: 150px'>
		    </td>
		  </tr>
		  <tr>
		    <td  class="form-td-label" ><span style="color: red;">*</span>破损长度(mm)：</td>
		    <td class="form-td-content" >
		      <input class="easyui-numberbox" class="easyui-validatebox span2" name="gppsLength" id='add_pscd_gczlYdxjGPZLXJInfo'  data-options="prompt:'请输入破损长度',validType:'length[1,40]',required:true"  style='width: 150px'>
		    </td>
		    <td  class="form-td-label" width="109px"><span style="color: red;">*</span>破损宽度(mm)：</td>
		    <td class="form-td-content" >
		      <input class="easyui-numberbox" class="easyui-validatebox span2" name="gppsWidth"  id='add_pskd_gczlYdxjGPZLXJInfo'  data-options="prompt:'请输入破损宽度',validType:'length[1,40]',required:true"  style='width: 150px'>
		    </td>
		  </tr>
		  <tr>
		    <td  class="form-td-label"  valign="top" align="right" >其他需说明情况：</td>
		    <td class="form-td-content"  colspan='3'>
		      <input class="easyui-textbox" class="easyui-validatebox span2"  name='remarks'  id='add_remarks_gczlYdxjGPZLXJInfo'  data-options="prompt:'请输入其他需说明情况',multiline:true,validType:'length[1,400]'"  style='width: 100%;height:60px'>
		    </td>
		  </tr>
		</table>
	</div>
				  
<!-- ****【管片错台】***************************************************************************************************-->		  
<div id="gpct_div_gczlYdxjGPZLXJInfo" >
		<table class="grid" style="width: 780px">
		  <tr>
		    <td  class="form-td-label"  width="109px"><span style="color: red;">*</span>径向(mm)：</td>
		    <td class="form-td-content" >
		      <input class="easyui-numberbox" class="easyui-validatebox span2" name="gpctJinXiang" id='add_pscd_gczlYdxjGPZLXJInfo'  data-options="prompt:'请输入径向',validType:'length[1,40]',required:true"  style='width: 150px'>
		    </td>
		    <td  class="form-td-label"  width="109px"><span style="color: red;">*</span>环向(mm)：</td>
		    <td class="form-td-content" >
		      <input class="easyui-numberbox" class="easyui-validatebox span2"  name="gpctHuanXiang" id='add_pskd_gczlYdxjGPZLXJInfo'  data-options="prompt:'请输入环向',validType:'length[1,40]',required:true"  style='width: 150px'>
		    </td>
		  </tr>
		  <tr>
		    <td  class="form-td-label"  valign="top" align="right" >其他需说明的情况：</td>
		    <td class="form-td-content"  colspan='3'>
		      <input class="easyui-textbox" class="easyui-validatebox span2"  name='remarks'  id='add_remarks_gczlYdxjGPZLXJInfo'  data-options="prompt:'请输入其他需说明的情况',multiline:true,validType:'length[1,400]'"  style='width: 100%;height:60px'>
		    </td>
		  </tr>
		</table>
	</div>
<!-- ****【螺栓复紧】***************************************************************************************************-->	
<div id="lsfj_div_gczlYdxjGPZLXJInfo" >
		<table class="grid" style="width: 780px">
		  <tr>
		    <td  class="form-td-label"  width="109px"><span style="color: red;">*</span>复紧不到位数量：</td>
		    <td class="form-td-content"  width="152px" colspan="3">
		      <input class="easyui-numberbox" class="easyui-validatebox span2" name="lsfjNums" id='add_lsfj_gczlYdxjGPZLXJInfo'  data-options="prompt:'请输入复紧不到位数量',validType:'length[1,40]',required:true"  style='width: 150px'>
		    </td>
		  </tr>
		  <tr>
		    <td  class="form-td-label"  valign="top" align="right" >其他需说明的情况：</td>
		    <td class="form-td-content"  colspan='3'>
		      <input class="easyui-textbox" class="easyui-validatebox span2"  name='remarks'  id='add_remarks_gczlYdxjGPZLXJInfo'  data-options="prompt:'请输入其他需说明的情况',multiline:true,validType:'length[1,40]'"  style='width: 100%;height:60px'>
		    </td>
		  </tr>
		</table>
	</div>	  
<!-- ****【渗漏水】***************************************************************************************************-->
<div id="sls_div_gczlYdxjGPZLXJInfo" >
		<table class="grid" style="width: 780px">
		  <tr>
		    <td  class="form-td-label"  width="109px"><span style="color: red;">*</span>渗水情况描述：</td>
		    <td class="form-td-content" colspan="3">
		    	<input class="easyui-combotree" class="easyui-validatebox span2" name="slsMiaoShu" id='add_psqksls_gczlYdxjGPZLXJInfo'  data-options="prompt:'请选择渗水情况',required:true"  style='width: 150px'>
		    </td>
		  </tr>
		  <tr>
		    <td  class="form-td-label"  valign="top" align="right" >其他需说明的情况：</td>
		    <td class="form-td-content"  colspan='3'>
		      <input class="easyui-textbox" class="easyui-validatebox span2"  name='remarks'  id='add_remarks_gczlYdxjGPZLXJInfo'  data-options="prompt:'请输入其他需说明的情况',multiline:true,validType:'length[1,400]'"  style='width: 100%;height:60px'>
		    </td>
		  </tr>
		</table>
	</div>
</div>	
        </form>
	</div>
	<div id="add-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="addGczlYdxjGPZLXJInfoAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#addDialog_gczlYdxjGPZLXJInfo').dialog('close')">关闭</a>
	</div>

<!-- 【盾构施工质量巡检信息】编辑弹出框 -->
	<div id="editDialog_gczlYdxjGPZLXJInfo" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#edit-dialog-buttons',closed:true,cache: false,modal: true"
	style="width:823px;height:438px;padding:10px 10px;display:none;" >
        <form id="editForm_gczlYdxjGPZLXJInfo" method="post" data-options="novalidate:true">
           <div id="editId">
           		<input name="id" type="hidden" id='showzg_xjZpId'/>
         		<input name="optionName" id="edit_optionName" type="hidden" >
         		<input name="updateTime" type="hidden" >
           </div>
			<table  class="grid" id="editTable_gczlYdxjGPZLXJInfo" style="width: 780px">
			  <tr>
			    <td  class="form-td-label"  width="109px"><span style="color: red;">*</span>所属工程：</td>
			    <td class="form-td-content" colspan="3">
			    	${projectName}
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>巡检时间：</td>
			    <td class="form-td-content"  colspan="3">
			      <input class="easyui-datetimebox" class="easyui-validatebox span2" name='xjtime'  id='upd_xjtime_gczlYdxjGPZLXJInfo' data-options="prompt:'请输入巡检时间',required:true" style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>区间：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='qlBh' id='upd_qlBh_gczlYdxjGPZLXJInfo'  data-options="prompt:'请输入选择区间',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label"  width="109px"><span style="color: red;">*</span>线路：</td>
			    <td class="form-td-content" >
			      <input class="easyui-combobox" class="easyui-validatebox span2"  name='xlBh' id='upd_xlBh_gczlYdxjGPZLXJInfo'  data-options="prompt:'请选择线路',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>环号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-numberbox" class="easyui-validatebox span2"  name='hh' id='upd_hh_gczlYdxjGPZLXJInfo'  data-options="prompt:'请输入环号',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" ><span style="color: red;">*</span>点位：</td>
			    <td class="form-td-content" >
			       <select class="easyui-combobox" class="easyui-validatebox span2"  name='dw' id='upd_dw_gczlYdxjGPZLXJInfo'  data-options="prompt:'请选择点位',required:true"  style='width: 150px'>
			      	<option value="1">1</option>
			      	<option value="2">2</option>
			      	<option value="3">3</option>
			      	<option value="4">4</option>
			      	<option value="5">5</option>
			      	<option value="6">6</option>
			      </select>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>质量问题分类：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='typeName' id='upd_typeName_gczlYdxjGPZLXJInfo'  data-options="prompt:'请输入质量问题分类',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >现场图片：</td>
			    <td class="form-td-content" >
			       <a   onclick="showMyPic('showzg_xjZpId');" style="cursor:pointer"><u>点击查看</u> </a>
			    </td>
			  </tr>
			  </table>
			  <div id="selectChild_gczlYdxjGPZLXJInfo_upd">
			  </div>
			<%@ include file="gczlYdxjGPZLXJInfo_shh.jsp" %>
        </form>
	</div>
	<div id="edit-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" id="edit_saveButton_gczlYdxjGPZLXJInfo" onclick="editGczlYdxjGPZLXJInfoAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#editDialog_gczlYdxjGPZLXJInfo').dialog('close')">关闭</a>
	</div>
	
</body>
</html>
