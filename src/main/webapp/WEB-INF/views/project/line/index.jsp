<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>区间线路管理</title>
<script type="text/javascript" src="${staticPath }/static/js/project/line/list.js?v=20170407083519" charset="utf-8"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<!-- 查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:100px;border: false;overflow: hidden;" data-options="collapsible:true">
		<form id="searchForm">
			<table class="grid">
				<tr>
					<td class="form-td-label" style="width: 80px">工程名称：</td>
					<td class="form-td-content" style="width: 160px">
						<select id="search_proId" name="proId" class="easyui-combobox" data-options="prompt:'请输入工程名称'" style="width: 100%;"></select>
					</td>
					<td class="form-td-label" style="width: 80px">区间名称：</td>
					<td class="form-td-content" style="width: 160px">
						<select id="search_sectionId" name="sectionId" class="easyui-combobox" data-options="prompt:'请输入区间名称'" style="width: 100%;"></select>
					</td>
					<td class="form-td-label" style="width: 80px" >线路名称：</td>
					<td class="form-td-content" style="width: 160px" >
						<input id="search_lineName" name="lineName" class="easyui-textbox" data-options="prompt:'请输入线路名称'" style="width: 100%;"/>
					</td>
				</tr>
				<tr>
					<td class="form-td-label">起始时间：</td>
					<td class="form-td-content">
						<input id="startTime" name="startTime" class="easyui-datetimebox" data-options="prompt:'起始创建时间'" style="width:100%;"></td>
					<td class="form-td-label">结束时间：</td>
					<td class="form-td-content">
						<input id="endTime" name="endTime" class="easyui-datetimebox" data-options="prompt:'截止创建时间'" style="width:100%;"></td>
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
	<div id="toolbar" style="display: none;">
		<a onclick="reloadFun();" href="javascript:void(0);" id="reload_button_line_info" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<shiro:hasPermission name="/project/line/add">
    	   <a onclick="addFun();" href="javascript:void(0);" id="add_button_line_info" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/project/line/edit">
    	   <a onclick="editFun();" href="javascript:void(0);" id="edit_button_line_info" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/project/line/del">
    	   <a onclick="deleteFun();" href="javascript:void(0);" id="del_button_line_info" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	</shiro:hasPermission>
    	<a onclick="clearSelections();" href="javascript:void(0);" id="clear_button_line_info" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    	<shiro:hasPermission name="/project/line/list">
    	   <a onclick="showFun();" href="javascript:void(0);" id="show_button_line_info" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-book_previous'">查看</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/project/line/start">
    	   <a onclick="startFun();" href="javascript:void(0);" id="start_button_line_info" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-package_start'">已开工</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/project/line/stop">
    	   <a onclick="endFun();" href="javascript:void(0);" id="stop_button_line_info" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-package_stop'">已完工</a>
    	</shiro:hasPermission>    	
    	<a onclick="expXls();" href="javascript:void(0);" id="expXls_button_line_info" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
    	<a onclick="expPdf();" href="javascript:void(0);" id="expPdf_button_line_info" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_white_acrobat'">导出Pdf</a>       
  	</div>
	<!-- 弹出框－新增 -->
	<div id="addDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:600px;height:420px;padding:5px 5px;" >
        <form id="addForm" method="post" data-options="novalidate:true">
			<table class="grid">
                <tr>
					<td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>所属工程：</td>
                    <td class="form-td-content" colspan="3" style="width: 400px">
                    	<select id="add_proId" name="proId" class="easyui-combobox" data-options="editable:false,required:true,prompt:'请输入所属工程'" style="width:100%"></select>
                   	</td>
                </tr>
                <tr>
                	<td class="form-td-label"><span style="color: red;">*</span>线路名称：</td>
                	<td class="form-td-content">
                    	<input name="lineName" class="easyui-textbox" data-options="required:true,prompt:'请输入线路名称'" style="width:100%">
                   	</td>
					<td class="form-td-label"><span style="color: red;">*</span>所属区间：</td>
                	<td class="form-td-content">
                    	<select id="add_sectionId" name="sectionId" class="easyui-combobox" data-options="editable:false,required:true,prompt:'请输入所属区间'" style="width:100%"></select>
                	</td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>起始里程：</td>
                    <td class="form-td-content" style="width: 150px">
                        <input name='startMileage'data-options="required:true,prompt:'请输入起始里程',validType:'length[1,40]'"  class="easyui-numberbox" precision="3" min="0.001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
					<td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>终止里程：</td>
                    <td class="form-td-content" style="width: 150px">
                        <input name='endMileage'data-options="required:true,prompt:'请输入终止里程',validType:'length[1,40]'"  class="easyui-numberbox" precision="3" min="0.001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>管环数量：</td>
                    <td class="form-td-content">
                    	<input name="ringCount" class="easyui-numberbox" data-options="required:true,prompt:'请输入管环数量'" style="width:100%">
                    </td>
					<td class="form-td-label">里程前辍标识：</td>
                    <td class="form-td-content">
                    	<input name="mileagePrefix" class="easyui-textbox" data-options="prompt:'请输入里程前辍标识'" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>隧道长度(米)：</td>
                    <td class="form-td-content">
                    	<input name="tunnellength" type="text" placeholder="隧道长度(米)" class="easyui-numberbox" data-options="required:true,prompt:'请输入隧道长度(米)'" style="width:100%">
                    </td>
					<td class="form-td-label">推进总工期(天)：</td>
                    <td class="form-td-content">
                    	<input name="tunneltime" class="easyui-numberbox" data-options="prompt:'请输入推进总工期(天)'" style="width:100%">
                    </td>
                </tr>
                <tr>
					<td class="form-td-label">预计始发日期：</td>
					<td class="form-td-content">
						<input id="yjsfrq" name="yjsfrq" class="easyui-datebox" data-options="prompt:'请输入预计始发日期'" style="width:100%;">
					</td>
					<td class="form-td-label">预计出洞日期：</td>
					<td class="form-td-content">
						<input id="yjcdrq" name="yjcdrq" class="easyui-datebox" data-options="prompt:'请输入预计出洞日期'" style="width:100%;">
					</td>
                </tr>
                <tr>
					<td class="form-td-label">实际始发日期：</td>
					<td class="form-td-content">
						<input id="sjsfrq" name="sjsfrq" class="easyui-datebox" data-options="prompt:'请输入实际始发日期'" style="width:100%;">
					</td>
					<td class="form-td-label">实际出洞日期：</td>
					<td class="form-td-content">
						<input id="sjcdrq" name="sjcdrq" class="easyui-datebox" data-options="prompt:'请输入实际出洞日期'" style="width:100%;">
					</td>
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>起始环号：</td>
                    <td class="form-td-content">
                    	<input name="startRingnum" class="easyui-numberbox" data-options="required:true,prompt:'请输入起始环号'" style="width:100%">
                    </td>
					<td class="form-td-label">起始位置：</td>
                    <td class="form-td-content">
                    	<input name="startPosition" class="easyui-textbox" data-options="prompt:'请输入起始位置'" style="width:100%">
                    </td>
                </tr>
                <tr>
                	<td class="form-td-label">施工盾构机：</td>
                	<td class="form-td-content" >
                    	<select id="add_tbmId" name="tbmId" class="easyui-combogrid" data-options="editable:false,prompt:'请输入施工盾构机'" style="width:100%"></select>
                	</td>
                	<td class="form-td-label">盾构机司机：</td>
                    <td class="form-td-content">
                    	<input name="dgChauffeur" class="easyui-textbox" data-options="prompt:'请输入盾构机司机名称'" style="width:100%">
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
		 style="width:600px;height:400px;padding:5px 5px;" >
        <form id="editForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden">
			<table class="grid">
				<tr>
					<td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>所属工程：</td>
                    <td class="form-td-content" colspan="3" style="width: 400px">
                    	<select id="edit_proId" name="proId" class="easyui-combobox" data-options="editable:false,required:true,prompt:'请输入所属工程'" style="width:100%"></select>
                	</td>
                </tr>
                 <tr>
                	<td class="form-td-label"><span style="color: red;">*</span>线路名称：</td>
                	<td class="form-td-content">
                    	<input name="lineName" class="easyui-textbox" data-options="required:true,prompt:'请输入线路名称'" style="width:100%">
                   	</td>
					<td class="form-td-label"><span style="color: red;">*</span>所属区间：</td>
                	<td class="form-td-content">
                    	<select id="edit_sectionId" name="sectionId" class="easyui-combobox" data-options="editable:false,required:true,prompt:'请输入所属区间'" style="width:100%"></select>
                	</td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>起始里程：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input name='startMileage'data-options="required:true,prompt:'请输入终止里程',validType:'length[1,40]'"  class="easyui-numberbox" precision="3" min="0.001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
					<td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>终止里程：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input name='endMileage'data-options="required:true,prompt:'请输入终止里程',validType:'length[1,40]'"  class="easyui-numberbox" precision="3" min="0.001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>管环数量：</td>
                    <td class="form-td-content">
                    	<input name="ringCount" class="easyui-numberbox" data-options="required:true,prompt:'请输入管环数量'" style="width:100%">
                    </td>
					<td class="form-td-label">里程前辍标识：</td>
                    <td class="form-td-content">
                    	<input name="mileagePrefix" class="easyui-textbox" data-options="prompt:'请输入里程前辍标识'" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>隧道长度(米)：</td>
                    <td class="form-td-content">
                    	<input name="tunnellength" type="text" placeholder="隧道长度(米)" class="easyui-numberbox" data-options="required:true,prompt:'请输入隧道长度(米)'" style="width:100%">
                    </td>
					<td class="form-td-label">推进总工期(天)：</td>
                    <td class="form-td-content">
                    	<input name="tunneltime" class="easyui-numberbox" data-options="prompt:'请输入推进总工期(天)'" style="width:100%">
                    </td>
                </tr>
               <tr>
					<td class="form-td-label">预计始发日期：</td>
					<td class="form-td-content">
						<input name="yjsfrq" class="easyui-datebox" data-options="prompt:'请输入预计始发日期'" style="width:100%;">
					</td>
					<td class="form-td-label">预计出洞日期：</td>
					<td class="form-td-content">
						<input name="yjcdrq" class="easyui-datebox" data-options="prompt:'请输入预计出洞日期'" style="width:100%;">
					</td>
                </tr>
                <tr>
					<td class="form-td-label">实际始发日期：</td>
					<td class="form-td-content">
						<input  name="sjsfrq" class="easyui-datebox" data-options="prompt:'请输入实际始发日期'" style="width:100%;">
					</td>
					<td class="form-td-label">实际出洞日期：</td>
					<td class="form-td-content">
						<input name="sjcdrq" class="easyui-datebox" data-options="prompt:'请输入实际出洞日期'" style="width:100%;">
					</td>
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>起始环号：</td>
                    <td class="form-td-content">
                    	<input name="startRingnum" class="easyui-numberbox" data-options="required:true,prompt:'请输入起始环号'" style="width:100%">
                    </td>
					<td class="form-td-label">起始位置：</td>
                    <td class="form-td-content">
                    	<input name="startPosition" class="easyui-textbox" data-options="prompt:'请输入起始位置'" style="width:100%">
                    </td>
                </tr>
               
                <tr>
                	<td class="form-td-label">施工盾构机：</td>
                	<td class="form-td-content" >
                    	<select id="edit_tbmId" name="tbmId" class="easyui-combogrid" data-options="editable:false,prompt:'请输入施工盾构机'" style="width:100%"></select>
                	</td>
                	<td class="form-td-label">盾构机司机：</td>
                    <td class="form-td-content">
                    	<input name="dgChauffeur" class="easyui-textbox" data-options="prompt:'请输入盾构机司机名称'" style="width:100%">
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
		 style="width:600px;height:450px;padding:5px 5px;" >
        <form id="showForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden">
			<table class="grid">
				
				<tr>
					<td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>所属工程：</td>
                    <td class="form-td-content" colspan="3" style="width: 400px">
                    	<input name="proName" class="easyui-textbox" data-options="prompt:''" style="width:100%">
                	</td>
                </tr>
                <tr>
                	<td class="form-td-label"><span style="color: red;">*</span>线路名称：</td>
                	<td class="form-td-content">
                    	<input name="lineName" class="easyui-textbox" data-options="required:true,prompt:''" style="width:100%">
                   	</td>
					<td class="form-td-label"><span style="color: red;">*</span>所属区间：</td>
                	<td class="form-td-content">
                    	<input name="sectionName" class="easyui-textbox" data-options="prompt:''" style="width:100%">
                	</td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>起始里程：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input name='startMileage'data-options="required:true,prompt:'',validType:'length[1,40]'"  class="easyui-numberbox" precision="3" min="0.001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
					<td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>终止里程：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input name='endMileage'data-options="required:true,prompt:'',validType:'length[1,40]'"  class="easyui-numberbox" precision="3" min="0.001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>管环数量：</td>
                    <td class="form-td-content">
                    	<input name="ringCount" class="easyui-numberbox" data-options="required:true,prompt:''" style="width:100%">
                    </td>
					<td class="form-td-label">里程前辍标识：</td>
                    <td class="form-td-content">
                    	<input name="mileagePrefix" class="easyui-textbox" data-options="prompt:''" style="width:100%">
                    </td>
                </tr>
                
                <tr>
					<td class="form-td-label">预计始发日期：</td>
					<td class="form-td-content">
						<input name="yjsfrq"  class="easyui-textbox" data-options="prompt:''" style="width:100%;">
					</td>
					<td class="form-td-label">预计出洞日期：</td>
					<td class="form-td-content">
						<input name="yjcdrq"  class="easyui-textbox" data-options="prompt:''" style="width:100%;">
					</td>
                </tr>
                <tr>
					<td class="form-td-label">实际始发日期：</td>
					<td class="form-td-content">
						<input  name="sjsfrq"  class="easyui-textbox" data-options="prompt:''" style="width:100%;">
					</td>
					<td class="form-td-label">实际出洞日期：</td>
					<td class="form-td-content">
						<input name="sjcdrq"  class="easyui-textbox" data-options="prompt:''" style="width:100%;">
					</td>
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>隧道长度(米)：</td>
                    <td class="form-td-content">
                    	<input name="tunnellength" type="text" placeholder="隧道长度(米)" class="easyui-numberbox" data-options="required:true,prompt:''" style="width:100%">
                    </td>
					<td class="form-td-label">推进总工期(天)：</td>
                    <td class="form-td-content">
                    	<input name="tunneltime" class="easyui-numberbox" data-options="prompt:''" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>起始环号：</td>
                    <td class="form-td-content">
                    	<input name="startRingnum" class="easyui-numberbox" data-options="required:true,prompt:''" style="width:100%">
                    </td>
					<td class="form-td-label">起始位置：</td>
                    <td class="form-td-content">
                    	<input name="startPosition" class="easyui-textbox" data-options="prompt:''" style="width:100%">
                    </td>
                </tr>
                <tr>
                	<td class="form-td-label">施工盾构机：</td>
                	<td class="form-td-content" >
                	     <input name="tbmName" class="easyui-textbox" data-options="prompt:''" style="width:100%">
                    </td>
                	<td class="form-td-label">盾构机司机：</td>
                    <td class="form-td-content">
                    	<input name="dgChauffeur" class="easyui-textbox" data-options="prompt:''" style="width:100%">
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