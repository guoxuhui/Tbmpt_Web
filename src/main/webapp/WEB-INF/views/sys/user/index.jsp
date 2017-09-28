<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
<script type="text/javascript" src="${staticPath }/static/js/sys/user/list.js?v=20170602160803" charset="utf-8"></script>
<script type="text/javascript">
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
<div data-options="region:'west',split:true" title="组织机构" style="width:260px;">
	<ul id="orgzTree"  style="margin: 10px 10px 10px 10px;width: 95%;"></ul>
</div>
		<!-- 查询表单 -->
<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
<div class="easyui-layout" fit="true">
		<div title="用户查询" data-options="region:'north',border:false,collapsible:true"  
			style="width:100%;height:100px;overflow: hidden;" >
			<form id="searchForm">
				<table class="grid" style="border: 0px">
					<tr>
						<td class="form-td-label" style="width: 100px">登录名：</td>
						<td class="form-td-content" style="width: 160px">
							<input id="search_loginName" name="loginName" class="easyui-textbox" data-options="prompt:'请输入登录名'" style="width: 100%;" />
						</td>
						<td class="form-td-label" style="width: 100px">用户名称：</td>
						<td class="form-td-content" style="width: 160px">
							<input id="search_name" name="name" class="easyui-textbox" data-options="prompt:'请输入用户名称'" style="width: 100%;" />
						</td>
						<td style=""></td>
					</tr>
					<tr>
						<td class="form-td-label">创建起始时间：</td>
						<td class="form-td-content">
							<input id="startTime" name="startTime" class="easyui-datetimebox" data-options="prompt:'创建起始时间'" style="width:100%;"></td>
						<td class="form-td-label">创建截止时间：</td>
						<td class="form-td-content">
							<input id="endTime" name="endTime" class="easyui-datetimebox" data-options="prompt:'创建截止时间'" style="width:100%;"></td>
						<td style="text-align: right;" colspan="2">
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a> 
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleanFun();">重置</a>
						</td>
					</tr>
				</table>
			</form>
	</div>
	<div data-options="region:'center',border:false" style="width:100%;height:100%;">
			<table id="dataGrid" title="结果列表" data-options="fit:true,border:false"></table>
	</div>
	<div id="toolbar" style="display: none;">
		<a onclick="reloadFun();" href="javascript:void(0);" id="reload_button_sys_user" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<shiro:hasPermission name="/sys/user/add">
    	   <a onclick="addFun();" href="javascript:void(0);" id="add_button_sys_user" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/sys/user/edit">
    	   <a onclick="editFun();" href="javascript:void(0);" id="edit_button_sys_user" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/sys/user/del">
    	   <a onclick="deleteFun();" href="javascript:void(0);" id="del_button_sys_user" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	</shiro:hasPermission>
    	<a onclick="clearSelections();" href="javascript:void(0);" id="clear_button_sys_user" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    	<shiro:hasPermission name="/sys/user/list">
    	   <a onclick="showFun();" href="javascript:void(0);" id="show_button_sys_user" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-book_previous'">查看</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/sys/user/pdreset">
    	<a onclick="pdresetFun();" href="javascript:void(0);" id="pdreset_button_sys_user" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">密码重置</a>
    	</shiro:hasPermission>    	
    	<a onclick="expXls();" href="javascript:void(0);" id="expXls_button_sys_user" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
    	<a onclick="expPdf();" href="javascript:void(0);" id="expPdf_button_sys_user" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_white_acrobat'">导出Pdf</a>       
  	</div>
</div>
</div>
		<!-- 组织结构树 -->
<%-- <div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
<div class="easyui-layout" data-options="fit:true,border:false">
<div data-options="region:'west',split:true" title="组织机构" style="width:260px;">
<ul id="orgzTree"  style="margin: 10px 10px 10px 10px;width: 95%;"></ul>
</div>
	<div data-options="region:'center',title:'结果列表',iconCls:'icon-ok'">
	    <table id="dataGrid" data-options="border:false,singleSelect:true,fit:true"></table>
	</div>
	<div id="toolbar" style="display: none;">
		<a onclick="reloadFun();" href="javascript:void(0);" id="reload_button_sys_user" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<shiro:hasPermission name="/sys/user/add">
    	   <a onclick="addFun();" href="javascript:void(0);" id="add_button_sys_user" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/sys/user/edit">
    	   <a onclick="editFun();" href="javascript:void(0);" id="edit_button_sys_user" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/sys/user/del">
    	   <a onclick="deleteFun();" href="javascript:void(0);" id="del_button_sys_user" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	</shiro:hasPermission>
    	<a onclick="clearSelections();" href="javascript:void(0);" id="clear_button_sys_user" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    	<shiro:hasPermission name="/sys/user/list">
    	   <a onclick="showFun();" href="javascript:void(0);" id="show_button_sys_user" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-book_previous'">查看</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/sys/user/pdreset">
    	<a onclick="pdresetFun();" href="javascript:void(0);" id="pdreset_button_sys_user" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-ok'">密码重置</a>
    	</shiro:hasPermission>    	
    	<a onclick="expXls();" href="javascript:void(0);" id="expXls_button_sys_user" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
    	<a onclick="expPdf();" href="javascript:void(0);" id="expPdf_button_sys_user" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_white_acrobat'">导出Pdf</a>       
  	</div>
</div>
</div> --%>
		<!-- 结果列表 -->
<!-- 		<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
			<table id="dataGrid" title="结果列表" data-options="fit:true,border:false"></table>
		</div> -->

	<!-- 弹出框－新增 -->
	<div id="addDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:550px;height:220px;padding:5px 5px;" >
        <form id="addForm" method="post" data-options="novalidate:true">
        	<input name="password" type="hidden"  value="123456">
        	<div class="light-info" style="overflow: hidden;padding: 3px;">
                <div>默认密码为123456，请用户登陆平台后自行修改。</div>
            </div>
			<table class="grid">
                <tr>
                    <td class="form-td-label" style="width: 80px"><span style="color: red;">*</span>登录名：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input name="loginName" class="easyui-textbox" data-options="required:true,prompt:'请输入登录名'" style="width:100%">
                    </td>
                    <td class="form-td-label" style="width: 80px"><span style="color: red;">*</span>员工名称：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="add_empId" name="empId" type="hidden">
                    	<input id="add_empName" name="name" class="easyui-textbox" alt="add_emp" data-options="required:true,buttonText:'',buttonAlign:'right',buttonIcon:'icon-search',editable:false,onClickButton:empSelection" style="width: 100%;">
                    </td>
                    
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>角色：</td>
                    <td class="form-td-content" >
                    	<select id="add_roleIds" name="roleIds" class="easyui-combobox" data-options="required:true,editable:false"  style="width: 100%;"></select>
                    </td>
                    <td class="form-td-label"><span style="color: red;">*</span>用户类型：</td>
                    <td class="form-td-content" >
                        <select name="userType" class="easyui-combobox" data-options="required:true,editable:false" style="width: 100%;">
                            <option value="0">管理员</option>
                            <option value="1" selected="selected">用户</option>
                        </select>
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
		 style="width:550px;height:220px;padding:5px 5px;" >
        <form id="editForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden"  >
        	<input name="orgzId" type="hidden"  >
        	<input name=status type="hidden"  >
        	<input name=password type="hidden"  >
        	<input name="createTime" type="hidden" >
			<table class="grid">
                <tr>
                    <td class="form-td-label" style="width: 80px"><span style="color: red;">*</span>登录名：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input name="loginName" class="easyui-textbox" data-options="required:true,prompt:'请输入>登录名'" style="width:100%">
                    </td>
                    <td class="form-td-label" style="width: 80px"><span style="color: red;">*</span>员工名称：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="edit_empId" name="empId" type="hidden">
                    	<input id="edit_empName" name="name" class="easyui-textbox" alt="edit_emp" data-options="required:true,buttonText:'',buttonAlign:'right',buttonIcon:'icon-search',editable:false,onClickButton:empSelection" style="width: 100%;">
                    </td>
                    
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>角色：</td>
                    <td class="form-td-content" >
                    	<select id="edit_roleIds" class="easyui-combobox" name="roleIds" data-options="required:true,editable:false" style="width: 100%;"></select>
                    </td>
                    <td class="form-td-label"><span style="color: red;">*</span>用户类型：</td>
                    <td class="form-td-content" >
                        <select name="userType" class="easyui-combobox" data-options="required:true,editable:false" style="width: 100%;">
                            <option value="0">管理员</option>
                            <option value="1" selected="selected">用户</option>
                        </select>
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
		style="width:550px;height:220px;padding:5px 5px;" >
        <form id="showForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden"  >
        	<input name="orgzId" type="hidden"  >
        	<input name=status type="hidden"  >
        	<input name=password type="hidden"  >
        	<input name="createTime" type="hidden" >
			<table class="grid">
                <tr>
                    <td class="form-td-label" style="width: 80px"><span style="color: red;">*</span>登录名：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input name="loginName" class="easyui-textbox" data-options="required:true" style="width:100%">
                    </td>
                    <td class="form-td-label" style="width: 80px"><span style="color: red;">*</span>员工名称：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="show_empId" name="empId" type="hidden">
                    	<input id="show_empName" name="name" class="easyui-textbox" alt="show_emp" data-options="required:true,buttonText:'',buttonAlign:'right',buttonIcon:'icon-search',editable:false,onClickButton:empSelection" style="width: 100%;">
                    </td>
                    
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>角色：</td>
                    <td class="form-td-content" >
                    	<select id="show_roleIds" class="easyui-combobox" name="roleIds" data-options="required:true,editable:false" style="width: 100%;"></select>
                    </td>
                    <td class="form-td-label"><span style="color: red;">*</span>用户类型：</td>
                    <td class="form-td-content" >
                        <select name="userType" class="easyui-combobox" data-options="required:true,editable:false" style="width: 100%;">
                            <option value="0">管理员</option>
                            <option value="1" selected="selected">用户</option>
                        </select>
                    </td>
                </tr>
            </table>
        </form>
	</div>
	<div id="show-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#showDialog').dialog('close')">关闭</a>
	</div>
</body>
</html>