<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/commons/basejs.jsp" %>
    <meta http-equiv="X-UA-Compatible" content="edge"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>日志管理</title>
    <script type="text/javascript" src="${staticPath }/static/js/log/syslog/list.js" charset="utf-8"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<!-- 查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:100px;border: false;overflow: hidden;" data-options="collapsible:true">
		<form id="searchForm">
			<table class="grid" style="border: 0px">
				<tr>
					<td class="form-td-label" style="width: 80px">操作用户：</td>
					<td class="form-td-content" style="width: 160px">
						<input name="loginName" class="easyui-textbox" placeholder="请输入操作用户" style="width: 100%;" />
					</td>
					<td class="form-td-label" style="width: 80px">所属系统：</td>
					<td class="form-td-content" style="width: 160px">
						<input name="sysName" class="easyui-textbox" placeholder="请输入所属系统" style="width: 100%;" />
					</td>
					<td style=""></td>
				</tr>
				<tr>
					<td class="form-td-label">开始时间：</td>
					<td class="form-td-content">
						<input id="startTime" name="startTime" class="easyui-datetimebox" data-options="prompt:'起始监测时间'" style="width:100%;"></td>
					<td class="form-td-label">结束时间：</td>
					<td class="form-td-content">
						<input id="endTime" name="endTime" class="easyui-datetimebox" data-options="prompt:'起始监测时间'" style="width:100%;"></td>
					<td style="text-align: left;">
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a> 
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleanFun();">重置</a>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 结果列表 -->
	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
		<table id="dataGrid" title="角色列表" data-options="fit:true,border:false"></table>
	</div>
	<!-- 弹出框－编辑 -->
	<div id="showDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#show-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:560px;height:320px;padding:5px 5px;" >
        <form id="showForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden" >
            <table class="grid">
                <tr>
                    <td class="form-td-label" style="width:100px">操作用户：</td>
                    <td class="form-td-content" style="width:150px">
                    	<input name="loginName" class="easyui-textbox" style="width:100%;">
                    </td>
                    <td class="form-td-label" style="width:100px">操作类型：</td>
                    <td class="form-td-content" style="width:150px">
                    	<input name="optName" class="easyui-textbox" style="width:100%;">
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label">操作系统：</td>
                    <td class="form-td-content">
                    	<input name="sysName" class="easyui-textbox" style="width:100%;">
                    </td>
                    <td class="form-td-label">操作模块：</td>
                    <td class="form-td-content">
                    	<input name="moduleName" class="easyui-textbox" style="width:100%;">
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label">操作IP：</td>
                    <td class="form-td-content">
                    	<input name="clientIp" class="easyui-textbox" style="width:100%;">
                    </td>
                    <td class="form-td-label">操作时间：</td>
                    <td class="form-td-content">
                    	<input name="createTime" class="easyui-textbox" style="width:100%;">
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label">操作内容：</td>
                    <td class="form-td-content" colspan="3">
                    	<input name="optContent" class="easyui-textbox" data-options="required:false,multiline:true" style="width:100%;height:50px">
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