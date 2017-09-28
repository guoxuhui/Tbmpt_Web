<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>培训人员管理</title>
<script type="text/javascript" src="${staticPath}/static/js/aqsc/base/peiXunRenYuan.js?v=20170526102217" charset="utf-8"></script>
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
<!-- 【培训人员管理】查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:137px;border: false;overflow: auto;" data-options="collapsible:true">
		<form id="searchForm_peiXunRenYuan">
			<table class="grid" width='650px' border='0' cellspacing='0' style='padding:5px'>
			  <tr>
			    <td class="form-td-label" style="width: 80px">姓名：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='name'   style='width: 149px'>
			    </td>
			    <td class="form-td-label" style="width: 80px">性别：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='sex' id='search_sex'   style='width: 149px' panelHeight="50">
			    </td>
			    <td class="form-td-label" style="width: 80px">身份证号：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='cardNo'   style='width: 149px'>
			    </td>
			  </tr>
			  <tr>
			    <td class="form-td-label" style="width: 80px">家庭住址：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='adress'   style='width: 149px'>
			    </td>
			    <td class="form-td-label" style="width: 80px">联系电话：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='phone'   style='width: 149px'>
			    </td>
			    <td class="form-td-label" style="width: 80px">状态：</td><td align="left" width="160px">
			     <input   class="easyui-textbox"  name='state' id='search_state'  style='width: 149px'>
			    </td>
			  </tr>
			  <tr>
			    <td class="form-td-label" style="width: 80px">单位名称：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='demptName'   style='width: 149px'>
			    </td>
			    <td class="form-td-label" style="width: 80px">备注：</td><td align="left" width="160px">
			      <input   class="easyui-textbox"  name='remark'   style='width: 149px'>
			    </td>
			    <td class="form-td-label" style="width: 80px">入职日期：</td><td align="left" width="160px">
			      <input   class="easyui-datebox"  name='queryStarDate'   style='width: 90px'>至
			      <input   class="easyui-datebox"  name='queryEndDate'   style='width: 90px'>
			    </td>
			    <td align="center" width="170px">
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchPeiXunRenYuanFun();">查询</a>
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleanPeiXunRenYuanFun();">重置</a>
			    </td>
			  </tr>
			</table>
		</form>
	</div>

<!-- 【培训人员管理】结果列表 -->
	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
		<!-- 行 颜色  提示：浮动 -->
		<div class="tishiDiv" style="margin-left:auto;margin-right:10px;text-align:center; width:370px;">
		   <img alt="" style="width:15px;height:15px;" src="${staticPath}/static/style/images/hongse.png"/>
		   <span style="">红色：代表证件已过期；</span>
		   <img alt="" style="width:15px;height:15px;" src="${staticPath}/static/style/images/huangse.png"/>
		   <span >黄色：代表证件3个月内将过期。</span>
		</div>
		<table id="dataGrid_peiXunRenYuan" title="列表" data-options="fit:true,border:false"></table>
	</div>

<div id="toolbar" style="display: none;">
    	<a onclick="reloadPeiXunRenYuan();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<shiro:hasPermission name="/aqsc/base/peiXunRenYuan/add">
    	</shiro:hasPermission>
    	<a onclick="addPeiXunRenYuanFun();" id="add_button_peiXunRenYuan" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	<shiro:hasPermission name="/aqsc/base/peiXunRenYuan/edit">
    	</shiro:hasPermission>
    	<a onclick="editPeiXunRenYuanFun();" id="edit_button_peiXunRenYuan" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	<shiro:hasPermission name="/aqsc/base/peiXunRenYuan/del">
    	</shiro:hasPermission>
    	<a onclick="deletePeiXunRenYuanFun();" id="del_button_peiXunRenYuan" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	<a onclick="clearPeiXunRenYuanSelections();" id="cancelSelect_button_peiXunRenYuan" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    	<a onclick="expPeiXunRenYuanXls();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
    	<shiro:hasPermission name="/aqsc/base/peiXunRenYuan/imp"> 
			<a onclick="impFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">Excel导入</a>
    	</shiro:hasPermission>
</div>
<!-- 【培训人员管理】新增弹出框-->
	<div id="addDialog_peiXunRenYuan" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal:true"	style="width:520px;height:378px;padding:10px 10px;display:none;" >
        <form id="addForm_peiXunRenYuan" method="post" data-options="novalidate:true">
			<table  class="grid">
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>姓名：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='name' id='add_name_peiXunRenYuan'  data-options="prompt:'请输入姓名',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" ><span style="color: red;">*</span>身份证号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='cardNo' id='add_cardNo_peiXunRenYuan'  data-options="prompt:'请输入身份证号',validType:'idcard ',required:true"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >联系电话：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='phone' id='add_phone_peiXunRenYuan'  data-options="prompt:'请输入联系电话',validType:'mobile'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >工种：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='gongzhong' id='add_gongzhong_peiXunRenYuan'  data-options="prompt:'请输入工种',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >家庭住址：</td>
			    <td class="form-td-content" colspan="3">
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='adress' id='add_adress_peiXunRenYuan'  data-options="prompt:'请输入家庭住址',validType:'length[1,400]'"  style='width: 390px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >入职日期：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datebox" class="easyui-validatebox span2" name='inDate'  id='add_inDate_peiXunRenYuan' data-options="prompt:'请输入入职日期'" style='width: 150px'>			    </td>
			    <td  class="form-td-label" >离职日期：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datebox" class="easyui-validatebox span2" name='outDate'  id='add_outDate_peiXunRenYuan' data-options="prompt:'请输入离职日期'" style='width: 150px'>			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >培训时间：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datebox" class="easyui-validatebox span2" name='peixunTime'  id='add_peixunTime_peiXunRenYuan' data-options="prompt:'请输入培训时间'" style='width: 150px'>			    </td>
			    <td  class="form-td-label" >状态：</td>
			    <td class="form-td-content" >
			       <input   class="easyui-textbox"  name='state' id='add_state'   style='width: 149px' panelHeight="50">
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >单位名称：</td>
			    <td class="form-td-content" colspan="3">
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='demptName' id='add_demptName_peiXunRenYuan'  data-options="prompt:'请输入单位名称',validType:'length[1,400]'"  style='width: 390px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >备注：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='remark'  id='add_remark_peiXunRenYuan'  data-options="prompt:'请输入备注',multiline:true,validType:'length[1,40]'"  style='width: 100%;height:60px'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="add-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="addPeiXunRenYuanAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#addDialog_peiXunRenYuan').dialog('close')">关闭</a>
	</div>

<!-- 【培训人员管理】编辑弹出框 -->
	<div id="editDialog_peiXunRenYuan" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#edit-dialog-buttons',closed:true,cache: false,modal:true"
	style="width:520px;height:378px;padding:10px 10px;display:none;" >
        <form id="editForm_peiXunRenYuan" method="post" data-options="novalidate:true">
           <input name="id" type="hidden" >
           <input name="updateTime" type="hidden" >
			<table  class="grid">
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>姓名：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='name' id='edit_name_peiXunRenYuan'  data-options="prompt:'请输入姓名',validType:'length[1,40]',required:true"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" ><span style="color: red;">*</span>身份证号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='cardNo' id='edit_cardNo_peiXunRenYuan'  data-options="prompt:'请输入身份证号',validType:'idcard',required:true"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >联系电话：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='phone' id='edit_phone_peiXunRenYuan'  data-options="prompt:'请输入联系电话',validType:'mobile'"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >工种：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='gongzhong' id='edit_gongzhong_peiXunRenYuan'  data-options="prompt:'请输入工种',validType:'length[1,40]'"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >家庭住址：</td>
			    <td class="form-td-content" colspan="3">
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='adress' id='edit_adress_peiXunRenYuan'  data-options="prompt:'请输入家庭住址',validType:'length[1,400]'"  style='width: 390px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >入职日期：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datebox" class="easyui-validatebox span2" name='inDate'  id='edit_inDate_peiXunRenYuan' data-options="prompt:'请输入入职日期'" style='width: 150px'>			    </td>
			    <td  class="form-td-label" >离职日期：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datebox" class="easyui-validatebox span2" name='outDate'  id='edit_outDate_peiXunRenYuan' data-options="prompt:'请输入离职日期'" style='width: 150px'>			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >培训时间：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datebox" class="easyui-validatebox span2" name='peixunTime'  id='edit_peixunTime_peiXunRenYuan' data-options="prompt:'请输入培训时间'" style='width: 150px'>			    </td>
			    <td  class="form-td-label" >状态：</td>
			    <td class="form-td-content" >
			      <input   class="easyui-textbox"  name='state' id='edit_state'   style='width: 149px' panelHeight="50">
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >单位名称：</td>
			    <td class="form-td-content" colspan="3">
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='demptName' id='edit_demptName_peiXunRenYuan'  data-options="prompt:'请输入单位名称',validType:'length[1,400]'"  style='width: 390px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >备注：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='remark'  id='edit_remark_peiXunRenYuan'  data-options="prompt:'请输入备注',multiline:true,validType:'length[1,40]'"  style='width: 100%;height:60px'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="edit-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="editPeiXunRenYuanAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#editDialog_peiXunRenYuan').dialog('close')">关闭</a>
	</div>
	
	
	
	<!-- 【培训人员管理】查看弹出框 -->
	<div id="showDialog_peiXunRenYuan" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#show-dialog-buttons',closed:true,cache: false,modal:true"
	style="width:520px;height:378px;padding:10px 10px;display:none;" >
        <form id="showForm_peiXunRenYuan" method="post" data-options="novalidate:true">
           <input name="id" type="hidden" >
           <input name="updateTime" type="hidden" >
			<table  class="grid">
			  <tr>
			    <td  class="form-td-label" >姓名：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='name' id='show_name_peiXunRenYuan'  readonly="readonly"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >身份证号：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='cardNo' id='show_cardNo_peiXunRenYuan'  readonly="readonly"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >联系电话：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='phone' id='show_phone_peiXunRenYuan'  readonly="readonly"  style='width: 150px'>
			    </td>
			    <td  class="form-td-label" >工种：</td>
			    <td class="form-td-content" >
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='gongzhong' id='show_gongzhong_peiXunRenYuan'  readonly="readonly"  style='width: 150px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >家庭住址：</td>
			    <td class="form-td-content" colspan="3">
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='adress' id='show_adress_peiXunRenYuan'  readonly="readonly"  style='width: 390px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >入职日期：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datebox" class="easyui-validatebox span2" name='inDate'  id='show_inDate_peiXunRenYuan' readonly="readonly" style='width: 150px'>			    </td>
			    <td  class="form-td-label" >离职日期：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datebox" class="easyui-validatebox span2" name='outDate'  id='show_outDate_peiXunRenYuan' readonly="readonly" style='width: 150px'>			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >培训时间：</td>
			    <td class="form-td-content" >
			      <input class="easyui-datebox" class="easyui-validatebox span2" name='peixunTime'  id='show_peixunTime_peiXunRenYuan' readonly="readonly" style='width: 150px'>			    </td>
			    <td  class="form-td-label" >状态：</td>
			    <td class="form-td-content" >
			      <input   class="easyui-textbox"  name='state' id='show_state'   style='width: 149px' panelHeight="50">
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" >单位名称：</td>
			    <td class="form-td-content" colspan="3">
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='demptName' id='show_demptName_peiXunRenYuan'  readonly="readonly"  style='width: 390px'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >备注：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input class="easyui-textbox" class="easyui-validatebox span2"  name='remark'  id='show_remark_peiXunRenYuan'  readonly="readonly" style='width: 100%;height:60px'>
			    </td>
			  </tr>
			</table>
        </form>
	</div>
	<div id="show-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#showDialog_peiXunRenYuan').dialog('close')">关闭</a>
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
                	   <%--  <a href="${path }/aqsc/base/peiXunRenYuan/excelTemplate">点击生成模板</a> --%>
                		<a href="${path }/aqsc/base/peiXunRenYuan/downLoadModel">点击下载模板</a>
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
