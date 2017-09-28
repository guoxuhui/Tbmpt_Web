<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>特种人员管理</title>
<script type="text/javascript" src="${staticPath}/static/js/aqsc/base/specialMan.js?v=20170526102217" charset="utf-8"></script>
<style type="text/css">
	td{
		 white-space: nowrap;
	}
	td input{
		white-space: normal;
	}
   /* 行 颜色  提示：浮动 css*/
   .tishiDiv{
        position:relative;/* 浮动*/
        top:22px;
        left:0px; 
        z-index:1;   /* 层重叠顺序:z-index的数字越高越靠前 */
   }

</style>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
<jsp:include  page="../../sys/base/uploadPicture.jsp" />
<!-- 【特种人员管理】查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:135px;border: false;overflow: auto;" data-options="collapsible:true">
		<form id="searchForm_specialMan">
			<table class="grid" width='650px' border='0' cellspacing='0' style='padding:5px'>
			  <tr>
			    <td class="form-td-label" style="width: 80px">姓名：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='name'   style='width: 149px'>
			    </td>
			    <td class="form-td-label" style="width: 80px">性别：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='sex' id="search_sex"  style='width: 149px' panelHeight="50">
			    </td>
			    <td class="form-td-label" style="width: 80px">工种：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='workType'   style='width: 149px'>
			    </td>
			  </tr>
			  <tr>
			    <td class="form-td-label" style="width: 80px">人员类别：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='renyuanType'  id="query_rqType" data-options="panelHeight:'auto'" style='width: 149px'>
			    </td>
			    <td class="form-td-label" style="width: 80px">证件号码：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='zhengjianhaoma'   style='width: 149px'>
			    </td>
			     <td class="form-td-label" style="width: 80px">备注：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='remark'   style='width: 149px'>
			    </td>
			    
			   </tr>
			   <tr>
			     <td class="form-td-label" style="width: 80px">进场日期：</td><td align="left" width="160px">
			      <input   class="easyui-datebox"  name='queryStarDate'   style='width: 90px'>至
			      <input   class="easyui-datebox"  name='queryEndDate'   style='width: 90px'>
			    </td>
			    <td colspan="4" align="center" width="170px">
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchSpecialManFun();">查询</a>
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleanSpecialManFun();">重置</a>
			    </td>
			  </tr>
			  <tr>
			  </tr>
			</table>
		</form>
	</div>
    
<!-- 【特种人员管理】结果列表   style="margin-left:auto;margin-right:2px;width:300px;background-color:Aqua;"-->
	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
		<!-- 行 颜色  提示：浮动 -->
		<div class="tishiDiv" style="margin-left:auto;margin-right:10px;text-align:center; width:370px;">
		   <img alt="" style="width:15px;height:15px;" src="${staticPath}/static/style/images/hongse.png"/>
		   <span style="">红色：代表证件已过期；</span>
		   <img alt="" style="width:15px;height:15px;" src="${staticPath}/static/style/images/huangse.png"/>
		   <span >黄色：代表证件3个月内将过期。</span>
		</div>
		<table id="dataGrid_specialMan" title="列表 <font color='red'region='center'>(*鼠标双击可直接编辑*)</font>" data-options="fit:true,border:false"></table>
	</div>

<div id="toolbar" style="display: none;">
    	<a onclick="reloadSpecialMan();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<shiro:hasPermission name="/aqsc/base/specialMan/add">
    	</shiro:hasPermission>
    	<a onclick="addSpecialManFun();" id="add_button_specialMan" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	<shiro:hasPermission name="/aqsc/base/specialMan/edit">
    	</shiro:hasPermission>
    	<a onclick="editSpecialManFun();" id="edit_button_specialMan" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	<shiro:hasPermission name="/aqsc/base/specialMan/del">
    	</shiro:hasPermission>
    	<a onclick="deleteSpecialManFun();" id="del_button_specialMan" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	<a onclick="clearSpecialManSelections();" id="cancelSelect_button_specialMan"  href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    	<a onclick="expSpecialManXls();" id="expXls_button_specialMan"  href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
    	<shiro:hasPermission name="/aqsc/base/specialMan/imp">
			<a onclick="impFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">Excel导入</a>
    	</shiro:hasPermission>
    	<a onclick="myUploadPicture();" href="javascript:void(0);" id="uploadPic_button_huiyiJilu" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-20130406125647919_easyicon_net_16'">上传图片</a>
        
</div>
<!-- 【特种人员管理】新增弹出框-->	
  <div id="addDialog_specialMan" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal:true"	style="width:520px;height:378px;padding:10px 10px;display:none;" >
        <form id="addForm_specialMan" method="post" data-options="novalidate:true">
			<table  class="grid">
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>姓名：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='name' id='add_name_specialMan'  data-options="prompt:'请输入姓名',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >性别：</td>
			    <td class="form-td-content" >
			       <input   class="easyui-textbox" id="add_sex"  name='sex'   style='width: 149px' panelHeight="50">
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>工种：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='workType' id='add_workType_specialMan'  data-options="prompt:'请输入工种',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >人员类别：</td>
			    <td class="form-td-content" >
			    	<select class="easyui-combobox" name='renyuanType' data-options="prompt:'请选择人员类别',panelHeight:'auto'" style='width: 150px'>
			       		<option value="自有人员">自有人员</option>
			       		<option value="外协人员">外协人员</option>
			       </select>
			    </td>
			  </tr>
			  <tr>
			  <td  class="form-td-label" >身份证号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='cardNo' id='add_cardNo_specialMan'  data-options="prompt:'请输入身份证号',validType:'idcard '"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >发证机关：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='fazhengjiguan' id='add_fazhengjiguan_specialMan'  data-options="prompt:'请输入发证机关',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >证件号码：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='zhengjianhaoma' id='add_zhengjianhaoma_specialMan'  data-options="prompt:'请输入证件号码',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >取证日期：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datebox" class="easyui-validatebox span2" name='quzhengriqi'  id='add_quzhengriqi_specialMan' data-options="prompt:'请输入取证日期'" style='width: 150px'>			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >复审日期：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datebox" class="easyui-validatebox span2" name='fushengriqi'  id='add_fushengriqi_specialMan' data-options="prompt:'请输入复审日期'" style='width: 150px'>			    </td>
			    <td  class="form-td-label" >有效日期：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datebox" class="easyui-validatebox span2" name='youxiaoqi'  id='add_youxiaoqi_specialMan' data-options="prompt:'请输入有效日期'" style='width: 150px'>			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >进场日期：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datebox" class="easyui-validatebox span2" name='jinchangriqi'  id='add_jinchangriqi_specialMan' data-options="prompt:'请输入进场日期'" style='width: 150px'>			    </td>
			    <td  class="form-td-label" >离场日期：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datebox" class="easyui-validatebox span2" name='lichangriqi'  id='add_lichangriqi_specialMan' data-options="prompt:'请输入离场日期'" style='width: 150px'>			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >备注：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='remark'  id='add_remark_specialMan'  data-options="prompt:'请输入备注',multiline:true,validType:'length[1,40]'"  style='width: 100%;height:60px'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="add-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="addSpecialManAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#addDialog_specialMan').dialog('close')">关闭</a>
	</div>

<!-- 【特种人员管理】编辑弹出框 -->
	<div id="editDialog_specialMan" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#edit-dialog-buttons',closed:true,cache: false,modal:true"
	style="width:520px;height:378px;padding:10px 10px;display:none;" >
        <form id="editForm_specialMan" method="post" data-options="novalidate:true">
           <input name="id" type="hidden" >
           <input name="updateTime" type="hidden" >
			<table  class="grid">
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>姓名：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='name' id='edit_name_specialMan'  data-options="prompt:'请输入姓名',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >性别：</td>
			    <td class="form-td-content" >
			      <input   class="easyui-textbox" id="edit_sex"  name='sex'   style='width: 149px' panelHeight="50">
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>工种：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='workType' id='edit_workType_specialMan'  data-options="prompt:'请输入工种',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >人员类别：</td>
			    <td class="form-td-content" >
			   	 	<select class="easyui-combobox" name='renyuanType' data-options="panelHeight:'auto'" style='width: 150px'>
			       		<option value="自有人员">自有人员</option>
			       		<option value="外协人员">外协人员</option>
			       </select>
			    </td>
			  </tr>
			  <tr>
			   <td  class="form-td-label" >身份证号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='cardNo' id='edit_cardNo_specialMan'  data-options="prompt:'请输入身份证号',validType:'idcard '"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >发证机关：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='fazhengjiguan' id='edit_fazhengjiguan_specialMan'  data-options="prompt:'请输入发证机关',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >证件号码：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='zhengjianhaoma' id='edit_zhengjianhaoma_specialMan'  data-options="prompt:'请输入证件号码',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >取证日期：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datebox" class="easyui-validatebox span2" name='quzhengriqi'  id='edit_quzhengriqi_specialMan' data-options="prompt:'请输入取证日期'" style='width: 150px'>			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >复审日期：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datebox" class="easyui-validatebox span2" name='fushengriqi'  id='edit_fushengriqi_specialMan' data-options="prompt:'请输入复审日期'" style='width: 150px'>			    </td>
			    <td  class="form-td-label" >有效日期：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datebox" class="easyui-validatebox span2" name='youxiaoqi'  id='edit_youxiaoqi_specialMan' data-options="prompt:'请输入有效日期'" style='width: 150px'>			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >进场日期：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datebox" class="easyui-validatebox span2" name='jinchangriqi'  id='edit_jinchangriqi_specialMan' data-options="prompt:'请输入进场日期'" style='width: 150px'>			    </td>
			    <td  class="form-td-label" >离场日期：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datebox" class="easyui-validatebox span2" name='lichangriqi'  id='edit_lichangriqi_specialMan' data-options="prompt:'请输入离场日期'" style='width: 150px'>			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >备注：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='remark'  id='edit_remark_specialMan'  data-options="prompt:'请输入备注',multiline:true,validType:'length[1,40]'"  style='width: 100%;height:60px'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="edit-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="editSpecialManAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#editDialog_specialMan').dialog('close')">关闭</a>
	</div>
	
<!-- 【特种人员管理】查看弹出框 -->
	<div id="showDialog_specialMan" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#show-dialog-buttons',closed:true,cache: false,modal:true"
	style="width:520px;height:470px;padding:10px 10px;display:none;" >
        <form id="showForm_specialMan" method="post" data-options="novalidate:true">
           <input name="id" type="hidden"  id="show_id">
           <input name="updateTime" type="hidden" >
			<table  class="grid">
			  <tr>
			    <td  class="form-td-label" >姓名：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='name' id='show_name_specialMan'   style='width: 150px;' readonly="readonly">
			    </td>
			    <td  class="form-td-label" >性别：</td>
			    <td class="form-td-content" >
			      <input   class="easyui-textbox" id="show_sex"  name='sex'   style='width: 149px' panelHeight="50" readonly="readonly">
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >工种：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='workType' id='show_workType_specialMan'  readonly="readonly"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >人员类别：</td>
			    <td class="form-td-content" >
			   	 	<select class="easyui-combobox" name='renyuanType' readonly="readonly" style='width: 150px'>
			       		<option value="自有人员">自有人员</option>
			       		<option value="外协人员">外协人员</option>
			       </select>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >发证机关：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='fazhengjiguan' id='show_fazhengjiguan_specialMan'  readonly="readonly"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >证件号码：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='zhengjianhaoma' id='show_zhengjianhaoma_specialMan'  readonly="readonly"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >取证日期：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datebox" class="easyui-validatebox span2" name='quzhengriqi'  id='show_quzhengriqi_specialMan' readonly="readonly" style='width: 150px'>			    </td>
			    <td  class="form-td-label" >复审日期：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datebox" class="easyui-validatebox span2" name='fushengriqi'  id='show_fushengriqi_specialMan' readonly="readonly"style='width: 150px'>			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >有效日期：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datebox" class="easyui-validatebox span2" name='youxiaoqi'  id='show_youxiaoqi_specialMan' readonly="readonly" style='width: 150px'>			    </td>
			    <td  class="form-td-label" >进场日期：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datebox" class="easyui-validatebox span2" name='jinchangriqi'  id='show_jinchangriqi_specialMan' readonly="readonly" style='width: 150px'>			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >离场日期：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datebox" class="easyui-validatebox span2" name='lichangriqi'  id='show_lichangriqi_specialMan' readonly="readonly" style='width: 150px'>			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >备注：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='remark'  id='show_remark_specialMan'  readonly="readonly" style='width: 100%;height:60px'>
			    </td>
			  </tr>
<!-- 			   <tr> -->
<!-- 			  		<td colspan="4" align="left"> <a   onclick="showMyPic('show_id');" style="cursor:pointer"><u>点击查看附件</u> </a></td> -->
<!-- 			  </tr> -->
			</table>
        </form>
		<div id="img_div_specialMal">
		</div>
	</div>
	<div id="show-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#showDialog_specialMan').dialog('close')">关闭</a>
	</div>
	
	<!-- 弹出框－导入窗口 -->
	<div id="impDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#imp-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:600px;height:200px;padding:5px 5px;" >
        <form  id="impForm" method="post" enctype="multipart/form-data">
        <input type="hidden" id="filename" />
            <table class="grid">
                <tr>
                    <td class="form-td-label" ><span style="color: red;">*</span>导入文件路径：</td>
                    <td style="width:400px;">
                        <input id="file" name="file" colspan="3" accept=".xls,.xlsx" class="easyui-filebox" data-options="buttonText:'选择文件',required:true,prompt:'选择文件'" style="width:100%">
                    </td>
                </tr>
                <tr>
                	<td class="form-td-label" >Excel模板地址：</td>
                	<td>
                	    <%-- <a href="${path }/aqsc/base/specialMan/excelTemplate">点击生成模板</a> --%>
                		<a href="${path }/aqsc/base/specialMan/downLoadModel">点击下载模板</a>
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
