<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>盾构机管理</title>
<script type="text/javascript" src="${staticPath }/static/js/tbmmg/info/list.js?v=20170502172335" charset="utf-8"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<!-- 查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:100px;border: false;overflow: hidden;" data-options="collapsible:true">
		<form id="searchForm">
			<table class="grid" style="border: 0px">
				<tr>
					<td class="form-td-label" style="width: 80px">盾构机编号：</td>
					<td class="form-td-content" style="width: 180px">
						<input name="code" class="easyui-textbox"  data-options="prompt:'请输入盾构机编号'" style="width: 100%" />
					</td>
					<td class="form-td-label" style="width: 80px">盾构机名称：</td>
					<td class="form-td-content" style="width: 180px">
						<input name="name" class="easyui-textbox"  data-options="prompt:'请输入盾构机名称'" style="width: 100%"/>
					</td>
					<td style=""></td>
				</tr>
				<tr>
					<td class="form-td-label">开始时间：</td>
					<td class="form-td-content">
						<input id="startTime" name="startTime" class="easyui-datebox" data-options="prompt:'起始监测时间'" style="width:100%;"></td>
					<td class="form-td-label">结束时间：</td>
					<td class="form-td-content">
						<input id="endTime" name="endTime" class="easyui-datebox" data-options="prompt:'起始监测时间'" style="width:100%;"></td>
					<td style="text-align: left;">
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
    <div id="toolbar" style="display: none;">
    	<a onclick="reload();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<shiro:hasPermission name="/tbmmg/info/add">
    	<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/tbmmg/info/edit">
    	<a onclick="editFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/tbmmg/info/del">
    	<a onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	</shiro:hasPermission>
    	<a onclick="clearSelections();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    	<shiro:hasPermission name="/tbmmg/info/list">
    	<a onclick="showFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-book_previous'">查看</a>
    	</shiro:hasPermission>
    	<a onclick="expXls();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
    	<a onclick="expPdf();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Pdf</a>
    </div>
	<!-- 弹出框－新增 -->
	<div id="addDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:600px;height:500px;padding:5px 5px;" >
        <form id="addForm" method="post" data-options="novalidate:true">
            <table class="grid">
                <tr>
					<td class="form-td-label" style="width: 100px;"><span style="color: red;">*</span>盾构机编号：</td>
                    <td class="form-td-content" colspan="3" style="width: 400px;">
                    	<input name="tbmCode" data-options="required:true,prompt:'盾构机编号'"
                    	class="easyui-textbox" style="width:100%;" >
                    </td>
                </tr>
                <tr>
					<td class="form-td-label" style="width: 100px;"><span style="color: red;">*</span>盾构机名称：</td>
                    <td class="form-td-content" style="width: 150px;">
                    	<input name="tbmName" data-options="required:true,prompt:'请输入盾构机名称'"
                    	class="easyui-textbox" style="width:100%;" >
                    </td>
                    <td class="form-td-label" style="width: 100px;"><span style="color: red;">*</span>盾构机管理号：</td>
                    <td class="form-td-content" style="width: 150px;">
                    	<input name="manageno" data-options="required:true,prompt:'请输入盾构机管理号'"
                    	class="easyui-textbox" style="width:100%;">
                    </td>
                </tr>
                 <tr>
					<td class="form-td-label">负责人名称：</td>
                    <td class="form-td-content">
                    	<input name="functionaryName" data-options="prompt:'请输入负责人名称'"
                    	class="easyui-textbox" style="width:100%;" >
                    </td>
                	<td class="form-td-label">负责人联系方式：</td>
                    <td class="form-td-content">
                    	<input name="contactNumber" data-options="prompt:'请输入联系方式'"
                    	class="easyui-textbox" style="width:100%;">
                    </td>
                </tr>
                <tr>
					<td class="form-td-label">生产厂商：</td>
                    <td class="form-td-content">
                    	<input name="manufacturer" data-options="prompt:'请输入生产厂商'"
                    	class="easyui-textbox" style="width:100%;" >
                    </td>
                	<td class="form-td-label">出厂编号：</td>
                    <td class="form-td-content">
                    	<input name="factorynumber" data-options="prompt:'请输入出厂编号'"
                    	class="easyui-textbox" style="width:100%;">
                    </td>
                </tr>
                <tr>
					<td class="form-td-label">规格：</td>
                    <td class="form-td-content">
                    	<input name="specification" data-options="prompt:'请输入规格'"
                    	class="easyui-textbox" style="width:100%;" >
                    </td>
                	<td class="form-td-label">型号：</td>
                    <td class="form-td-content">
                    	<input name="model" data-options="prompt:'请输入型号'"
                    	class="easyui-textbox" style="width:100%;" >
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>盾构机类型：</td>
                    <td class="form-td-content" style="width: 160px">
	                    <select name="type" class="easyui-combobox" data-options="editable:false,required:true,prompt:'请输入盾构机类型'" style="width:100%;">
	                        <option value="1">土压平衡盾构机</option>
	                        <option value="2">混合式盾构机</option>
	                    </select>
	                </td>
                	<td class="form-td-label"><span style="color: red;">*</span>资产归属：</td>
                    <td class="form-td-content">
                    	<input name="tbmVest" data-options="required:true,prompt:'请输入资产归属'"
                    	class="easyui-textbox" style="width:100%;" >
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>出厂日期：</td>
                    <td class="form-td-content">
                    	<input name="factorydate" class="easyui-datebox" data-options="prompt:'出厂日期'" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${now}" />" style="width:100%;">
                    </td>
					<td class="form-td-label"><span style="color: red;">*</span>购置日期：</td>
                	<td class="form-td-content">
                		<input name="buydate" class="easyui-datebox" data-options="prompt:'购置日期'" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${now}" />" style="width:100%;">
                	</td>
                	
                </tr>
                <tr>
                    <td class="form-td-label">适应地层：</td>
                    <td class="form-td-content">
                    	<input id="add_sydc" name="sydc" class="easyui-textbox" data-options="prompt:'适应地层'" style="width:100%;"/>
                    </td>
					<td class="form-td-label">初始里程：</td>
                	<td class="form-td-content">
                		<input name="cslc" class="easyui-textbox" data-options="prompt:'初始里程'" style="width:100%;"/>
                	</td>
                	
                </tr>
                <tr>                    
					<td class="form-td-label">盾构机状态：</td>
                	<td class="form-td-content">
                		<input id="add_tbmState" name="tbmState" class="easyui-textbox" data-options="prompt:'盾构机状态'" style="width:100%;"/>
                	</td>                	
                </tr>
                <tr>
                    <td class="form-td-label">备注：</td>
                    <td class="form-td-content" colspan="3">
                    	<input name="remark" class="easyui-textbox" data-options="prompt:'请输入备注',multiline:true" style="width:100%;height:80px">
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
		style="width:600px;height:500px;padding:5px 5px;" >
        <form id="editForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden"/>
			<table class="grid">
                <tr>
					<td class="form-td-label" style="width: 100px;"><span style="color: red;">*</span>盾构机编号：</td>
                    <td class="form-td-content" colspan="3" style="width: 400px;">
                    	<input name="tbmCode" data-options="required:true,prompt:'请输入盾构机编号'"
                    	class="easyui-textbox" style="width:100%;" >
                    </td>
                </tr>
                <tr>
					<td class="form-td-label" style="width: 100px;"><span style="color: red;">*</span>盾构机名称：</td>
                    <td class="form-td-content" style="width: 150px;">
                    	<input name="tbmName" data-options="required:true,prompt:'请输入盾构机名称'"
                    	class="easyui-textbox" style="width:100%;" >
                    </td>
                    <td class="form-td-label" style="width: 100px;"><span style="color: red;">*</span>盾构机管理号：</td>
                    <td class="form-td-content" style="width: 150px;">
                    	<input name="manageno" data-options="required:true,prompt:'请输入盾构机管理号'"
                    	class="easyui-textbox" style="width:100%;">
                    </td>
                </tr>
                <tr>
					<td class="form-td-label">负责人名称：</td>
                    <td class="form-td-content">
                    	<input name="functionaryName" data-options="prompt:'请输入负责人名称'"
                    	class="easyui-textbox" style="width:100%;" >
                    </td>
                	<td class="form-td-label">负责人联系方式：</td>
                    <td class="form-td-content">
                    	<input name="contactNumber" data-options="prompt:'请输入联系方式'"
                    	class="easyui-textbox" style="width:100%;">
                    </td>
                </tr>
                <tr>
					<td class="form-td-label">生产厂商：</td>
                    <td class="form-td-content">
                    	<input name="manufacturer" data-options="prompt:'请输入生产厂商'"
                    	class="easyui-textbox" style="width:100%;" >
                    </td>
                	<td class="form-td-label">出厂编号：</td>
                    <td class="form-td-content">
                    	<input name="factorynumber" data-options="prompt:'请输入出厂编号'"
                    	class="easyui-textbox" style="width:100%;">
                    </td>
                </tr>
                <tr>
					<td class="form-td-label">规格：</td>
                    <td class="form-td-content">
                    	<input name="specification" data-options="prompt:'请输入规格'"
                    	class="easyui-textbox" style="width:100%;" >
                    </td>
                	<td class="form-td-label">型号：</td>
                    <td class="form-td-content">
                    	<input name="model" data-options="prompt:'请输入型号'"
                    	class="easyui-textbox" style="width:100%;" >
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>盾构机类型：</td>
                    <td class="form-td-content" style="width: 160px">
	                    <select name="type" class="easyui-combobox" data-options="editable:false,required:true,prompt:'请输入盾构机类型'" style="width:100%;">
	                        <option value="0"></option>
	                        <option value="1">土压平衡盾构机</option>
	                        <option value="2">混合式盾构机</option>
	                    </select>
	                </td>
					
                	<td class="form-td-label"><span style="color: red;">*</span>资产归属：</td>
                    <td class="form-td-content">
                    	<input name="tbmVest" data-options="required:true,prompt:'请输入资产归属'"
                    	class="easyui-textbox" style="width:100%;" >
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>出厂日期：</td>
                    <td class="form-td-content">
                    	<input name="factorydate" class="easyui-datebox" data-options="prompt:'出厂日期'" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${now}" />" style="width:100%;">
                    </td>
					<td class="form-td-label"><span style="color: red;">*</span>购置日期：</td>
                	<td class="form-td-content">
                		<input name="buydate" class="easyui-datebox" data-options="prompt:'购置日期'" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${now}" />" style="width:100%;">
                	</td>
                </tr>
                <tr>
                    <td class="form-td-label">适应地层：</td>
                    <td class="form-td-content">
                    	<input id="edit_sydc" name="sydc" class="easyui-textbox" data-options="prompt:'适应地层'" style="width:100%;"/>
                    </td>
					<td class="form-td-label">初始里程：</td>
                	<td class="form-td-content">
                		<input name="cslc" class="easyui-textbox" data-options="prompt:'初始里程'" style="width:100%;"/>
                	</td>
                	
                </tr>
                <tr>                    
					<td class="form-td-label">盾构机状态：</td>
                	<td class="form-td-content">
                		<input id="edit_tbmState" name="tbmState" class="easyui-textbox" data-options="prompt:'盾构机状态'" style="width:100%;"/>
                	</td>                	
                </tr>
                <tr>
                    <td class="form-td-label">备注：</td>
                    <td class="form-td-content" colspan="3">
                    	<input name="remark" class="easyui-textbox" data-options="prompt:'请输入备注',multiline:true" style="width:100%;height:80px">
                    </td>
                </tr>
            </table>
        </form>
	</div>
	<div id="edit-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="editAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#editDialog').dialog('close')">关闭</a>
	</div>
	
</body>
</html>