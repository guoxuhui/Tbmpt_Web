<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />  
<title>风险上报管理</title>   
<script type="text/javascript" src="${staticPath }/static/js/risk/up/listPro.js?v=20170804203342" charset="utf-8"></script>
</head> 
<jsp:include  page="../../sys/base/uploadPicture.jsp" />
<body class="easyui-layout" data-options="fit:true,border:false">
	<!-- 筛选表单 -->
	<div class="easyui-panel" title="筛选" data-options="region:'north',border:false"  style="width:100%;height:0px;border: false;overflow: hidden;" data-options="collapsible:true">
        <form id="searchForm">
        		<input id="proName" name="proName" type="hidden" value="${proName}">
        		<input id="startUpTime" name="startUpTime" type="hidden" value="${startUpTime}">
        		<input id="endUpTime" name="endUpTime" type="hidden" value="${endUpTime}">
        </form>
	</div>
	<!-- 结果列表 -->
	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100px;">
		<table id="dataGrid" title="列表" data-options="fit:true,border:false"></table>
	</div>
	<div id="toolbar" style="display: none;">
		<a onclick="reloadFun();" href="javascript:void(0);" id="reload_button_risk_up" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<shiro:hasPermission name="/risk/up/add">
    	   <a onclick="addFun();" href="javascript:void(0);" id="add_button_risk_up" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">上报</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/risk/up/edit">
    	   <a onclick="editFun();" href="javascript:void(0);" id="edit_button_risk_up" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">修改</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/risk/up/list">
    	   <a onclick="showFun();" href="javascript:void(0);" id="show_button_risk_up" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-book_previous'">查看</a>
    	</shiro:hasPermission>  
    	<shiro:hasPermission name="/risk/up/delete">
    	   <a onclick="deleteFun();" href="javascript:void(0);" id="del_button_level_info" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/risk/up/export">
    	<a onclick="expXls();" href="javascript:void(0);" id="expXls_button_risk_up" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
    	</shiro:hasPermission>  
    	<shiro:hasPermission name="/risk/up/picture">
    	<a onclick="UploadPicture();" href="javascript:void(0);" id="picture_button_project_info" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-20130406125647919_easyicon_net_16'">上传图片</a>
    	</shiro:hasPermission>   	
    	<shiro:hasPermission name="/risk/up/tx">
    	   <a onclick="pushMessage();" href="javascript:void(0);" id="show_button_risk_up" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-book_previous'">提醒上报推送</a>
    	</shiro:hasPermission>  
  	</div>
  	 
  	
 	<!-- 弹出框－新增 -->
	<div id="addDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:680px;height:430px;padding:5px 5px;" >
        <form id="addForm" method="post" data-options="novalidate:true">
        		<input id="add_section" name="section" type="hidden" value="" >
            <table class="grid">
            		<tr>
	                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>项目名称:</td>
	
	                	<td class="form-td-content" style="width:220px">
	                    	<input id="add_proName" name="proName" class="easyui-textbox" data-options="required:true,prompt:'请输入项目名称'" style="width: 100%;"/>
	                	</td>
	                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>安全风险级别:</td>
	
	                	<td class="form-td-content" style="width:180px">
	                		<input id="add_riskLevel" name="rikeLevel" class="easyui-textbox" data-options="required:true,prompt:'请选择级别'" style="width: 100%;"/>
	                	</td>
            		</tr>
                <tr>
                		<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>安全质量风险:</td>
                    <td class="form-td-content" colspan="3" >
                    		<input name="rikeDesc" class="easyui-textbox" data-options="required:true,prompt:'请输入安全质量风险'" style="width:100%;">
                    </td>
                </tr>
                <tr>
                		<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>监控时段:</td>
                    <td class="form-td-content" >
                   	<input id="rikeTimeStart" name="rikeTimeStart" class="easyui-datebox" data-options="required:true,prompt:'起始日期'" style="width:100px;"> 至 <input id="rikeTimeEnd" name="rikeTimeEnd" class="easyui-datebox" data-options="required:true,prompt:'结束日期'" style="width:100px;">
                    </td>
                    <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>是否排除:</td>
                    <td class="form-td-content" >

                    	<select name="isOut" class="easyui-combobox" data-options="required:true,prompt:'请选择状态'" style="width: 100%;">
					     <option selected="selected" value="0" style="width:120px;height:30px; color: green;">未排除</option>
					     <option value="1" style="width:120px;height:30px;color: yellow;">已排除</option>
					</select>
                    </td>
                </tr>
                <tr>
                		<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>负责部门:</td>
                    <td class="form-td-content" >
                    	<select id="add_dpts" name="dpts" class="easyui-combobox" data-options="required:true,editable:false,prompt:'最多选择5个部门'"  style="width: 100%;"></select>
                    </td>
                    <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>负责人:</td>
                    <td class="form-td-content" >
                    	<select id="add_persoon" name="persoon" class="easyui-combobox" data-options="required:true,prompt:'最多选择5个负责人'"  style="width: 100%;"></select>
                    </td>
                </tr>
                 <tr>
                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>主要管控措施:</td>
                    <td class="form-td-content"  colspan="3">
                    	<input name="mainControlMethod" class="easyui-textbox" data-options="required:true,prompt:'请输入主要管控措施',multiline:true" style="width:100%;height:80px">
                    </td>
                </tr>
                <tr >
                		<td class="form-td-label" style="width:100px">备注:</td>
                    <td class="form-td-content"  colspan="3">
                    	<input name="remark" class="easyui-textbox" data-options="required:false,prompt:'请输入备注',multiline:true" style="width:100%;height:80px">
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
		  style="width:680px;height:430px;padding:5px 5px;" >
        <form id="editForm" method="post" data-options="novalidate:true">
        <input id="edit_section" name="section" type="hidden" value="">
        	<input name="id" type="hidden">
            <table class="grid">
	            	<tr>
	                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>项目名称:</td>
	                	<td class="form-td-content" style="width:200px">
	                    	<input id="edit_proName" name="proName" class="easyui-textbox" data-options="required:true,prompt:'请输入项目名称'" style="width: 100%;"/>
	                	</td>
	                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>安全风险级别:</td>
	                	<td class="form-td-content" style="width:180px">
	                		<input id="edit_riskLevel" name="rikeLevel" class="easyui-textbox" data-options="required:true,prompt:'请选择级别'" style="width: 100%;"/>
	                	</td>
	            	</tr>
                <tr>
                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>安全质量风险:</td>
                    <td class="form-td-content" colspan="3">
                    	<input name="rikeDesc" class="easyui-textbox" data-options="required:true,prompt:'请输入安全质量风险'" style="width:100%;">
                    </td>
                </tr>
                <tr>
                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>监控时段:</td>
                    <td class="form-td-content" >
                    	<input id="rikeTimeStart" name="rikeTimeStart" class="easyui-datebox" data-options="required:true,prompt:'起始日期'" style="width:90px;"> 至 <input id="rikeTimeEnd" name="rikeTimeEnd" class="easyui-datebox" data-options="required:true,prompt:'结束日期'" style="width:90px;">
                    </td>
                    <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>是否排除:</td>
                    <td class="form-td-content">
                    	<select name="isOut" class="easyui-combobox" data-options="required:true,prompt:'请选择状态'" style="width: 100%;">
					     	<option selected="selected" value="0" style="width:120px;height:30px; color: green;">未排除</option>
					     	<option value="1" style="width:120px;height:30px;color: yellow;">已排除</option>
						</select>
                    </td>
                </tr>
                <tr>
                		<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>负责部门:</td>
                    <td class="form-td-content" >
                    	<select id="edit_dpts" name="dpts" class="easyui-combobox" data-options="required:true,editable:false,prompt:'最多选择5个部门'"  style="width: 100%;"></select>
                    </td>
                    <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>负责人:</td>
                    <td class="form-td-content" >
                    	<select id="edit_persoon" name="persoon" class="easyui-combobox" data-options="required:true,prompt:'最多选择5个负责人'"  style="width: 100%;"></select>
                    </td>
                </tr>
                 <tr>
                		<td class="form-td-label" style="width:100px;float: right;"><span style="color: red;">*</span>主要管控措施:</td>
                    <td class="form-td-content" colspan="3" >
                    	<input name="mainControlMethod" class="easyui-textbox" data-options="required:true,prompt:'请输入主要管控措施',multiline:true" style="width:100%;height:80px">
                    </td>
                </tr>
                <tr>
                	<td class="form-td-label" style="width:100px;float: right;">备注:</td>
                    <td class="form-td-content" colspan="3">
                    	<input name="remark" class="easyui-textbox" data-options="required:false,prompt:'请输入备注',multiline:true" style="width:100%;height:80px">
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
		 style="width:920px;height:480px;padding:5px 5px;" > 
        <form id="showForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden">
        		<table class="grid">
            	<tr>
                	<td class="form-td-label" style="width:100px">项目名称:</td>
                	<td class="form-td-content" style="width:200px">
                    	<input id="show_proName" name="proName" class="easyui-textbox" data-options="required:true,prompt:'请输入项目名称'" style="width: 100%;"/>
                	</td>
                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>安全风险级别:</td>
                	<td class="form-td-content" colspan="3" style="width:50px">
                		<input id="show_riskLevel" name="rikeLevel" class="easyui-textbox" data-options="required:true,prompt:'请选择级别'" style="width: 100%;"/>
                	</td>
                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>所属片区:</td>
                	<td class="form-td-content" colspan="3" style="width:80px">
                		<input id="show_section" name="section" type="text" class="easyui-textbox" data-options="required:true,prompt:'请输入所在片区'" value="" style="width:100%">
                	</td>
            	</tr>
                <tr>
                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>安全质量风险:</td>
                    <td class="form-td-content" colspan="5" style="width:280px">
                    	<input name="rikeDesc" class="easyui-textbox" data-options="required:true,prompt:'请输入安全质量风险'" style="width:100%;">
                    </td>
                </tr>
                <tr>
                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>监控时段:</td>
                    <td class="form-td-content"  style="width:280px">
                    	<input name="rikeTimeStart" class="easyui-datebox" data-options="required:true,prompt:'监控开始时间'">至
						<input name="rikeTimeEnd" class="easyui-datebox" data-options="required:true,prompt:'监控结束时间'">
                    </td>
                    <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>是否排除:</td>
                    <td class="form-td-content" colspan="4" style="width:280px">
                    	<select name="isOut" class="easyui-combobox" data-options="prompt:'请选择状态'" style="width: 100%;">
					     	<option selected="selected" value="0" style="width:120px;height:30px; color: green;">未排除</option>
					     	<option value="1" style="width:120px;height:30px;color: yellow;">已排除</option>
						</select>
                    </td>
                </tr>
                <tr>
                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>上报人:</td>
                    <td class="form-td-content"  style="width:280px">
                    	<input name="upUser" class="easyui-datebox" data-options="required:true,prompt:'上报人'">
                    </td>
                    <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>上报人联系方式:</td>
                    <td class="form-td-content" colspan="4" style="width:280px">
                    	<input name="upUserPhone" class="easyui-datebox" data-options="required:true,prompt:'上报人联系方式'">
                    </td>
                </tr>
                 <tr>
                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>负责部门:</td>
                    <td class="form-td-content" colspan="5" style="width:280px">
                    	<select id="show_dpts" name="dpts" class="easyui-combobox" data-options="required:true,editable:false,prompt:'最多选择5个部门'"  style="width: 60%;"></select>
                    </td>
                </tr>
                 <tr>
                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>负责人:</td>
                    <td class="form-td-content" colspan="5" style="width:280px">
                    	<select id="show_persoon" name="persoon" class="easyui-combobox" data-options="required:true,prompt:'最多选择5个负责人'"  style="width: 60%;"></select>
                    </td>
                </tr>
                 <tr>
                	<td class="form-td-label" style="width:100px">主要管控措施:</td>
                    <td class="form-td-content" colspan="5" style="width:280px">
                    	<input name="mainControlMethod" class="easyui-textbox" data-options="required:true,prompt:'请输入主要管控措施',multiline:true" style="width:100%;height:50px">
                    </td>
                </tr>
                <tr>
                	<td class="form-td-label" style="width:100px;float: right;">备注:</td>
                    <td class="form-td-content" colspan="5" style="width:280px">
                    	<input name="remark" class="easyui-textbox" data-options="required:false,prompt:'请输入备注',multiline:true" style="width:100%;height:50px">
                    </td>
                </tr>
                  <tr>
                	<td ></td>
                </tr>
                <tr>
                	<td ></td>
                </tr>
                <tr>
                	<td ></td>
                </tr>
                <tr>
                	<td ></td>
                </tr>
            </table>
        <div id="img_div_project"></div>
            </table>
        </form>
	</div>
	<div id="show-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#showDialog').dialog('close')">关闭</a>
	</div>
</body>

</html>