<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>工程管理</title>
<script type="text/javascript" src="${staticPath }/static/js/project/info/list.js?v=20170516152139" charset="utf-8"></script>
<script type="text/javascript">

</script>

</head>
<jsp:include  page="../../sys/base/uploadPicture.jsp" />
<body class="easyui-layout" data-options="fit:true,border:false">
	<!-- 查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:100px;border: false;overflow: hidden;" data-options="collapsible:true">
        <form id="searchForm">
			<table class="grid">
				<tr>
				    <td class="form-td-label" style="width: 80px">工程名称：</td>
					<td class="form-td-content" style="width: 180px">
						<input name="name" class="easyui-textbox"  data-options="prompt:'请输入工程名称'" style="width: 100%"/>
					</td>
					<td class="form-td-label" style="width: 80px">工程简称：</td>
					<td class="form-td-content" style="width: 180px">
						<input name="abbreviation" class="easyui-textbox"  data-options="prompt:'请输入工程工程简称'" style="width: 100%" />
					</td>
					<td class="form-td-label" style="width: 80px">施工状态：</td>
					<td class="form-td-content" style="width: 180px">
						<input id="search_proState" name="proState" class="easyui-textbox"  data-options="prompt:'请选择施工状态'" style="width: 100%" />
					</td>
				</tr>
				<tr>
					<td class="form-td-label">开始时间：</td>
					<td class="form-td-content">
						<input id="startTime" name="startTime" class="easyui-datebox" data-options="prompt:'起始监测时间'" style="width:100%;"></td>
					<td class="form-td-label">结束时间：</td>
					<td class="form-td-content">
						<input id="endTime" name="endTime" class="easyui-datebox" data-options="prompt:'起始监测时间'" style="width:100%;"></td>
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
		<a onclick="reloadFun();" href="javascript:void(0);" id="reload_button_project_info" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<shiro:hasPermission name="/project/info/add">
    	   <a onclick="addFun();" href="javascript:void(0);" id="add_button_project_info" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/project/info/edit">
    	   <a onclick="editFun();" href="javascript:void(0);" id="edit_button_project_info" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/project/info/del">
    	   <a onclick="deleteFun();" href="javascript:void(0);" id="del_button_project_info" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	</shiro:hasPermission>
    	<a onclick="clearSelections();" href="javascript:void(0);" id="clear_button_project_info" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    	<shiro:hasPermission name="/project/info/list">
    	   <a onclick="showFun();" href="javascript:void(0);" id="show_button_project_info" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-book_previous'">查看</a>
    	</shiro:hasPermission>   	
    	<shiro:hasPermission name="/project/info/picture">
    	<a onclick="UploadPicture();" href="javascript:void(0);" id="picture_button_project_info" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-20130406125647919_easyicon_net_16'">上传图片</a>
    	</shiro:hasPermission>
    	<a onclick="expXls();" href="javascript:void(0);" id="expXls_button_project_info" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
    	<a onclick="expPdf();" href="javascript:void(0);" id="expPdf_button_project_info" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_white_acrobat'">导出Pdf</a>       
  	</div>
    <!-- 弹出框－新增 -->
	<div id="addDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal: true"
		style="width:850px;height:620px;padding:10px 10px;">
        <form id="addForm" method="post" data-options="novalidate:true">
			<table class="grid">
	            <tr>
					<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>工程名称：</td>
                    <td class="form-td-content" colspan="5" >
                    	<input name="proName" type="text" class="easyui-textbox" data-options="required:true,prompt:'请输入工程名称'" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label">工程简称：</td>
                    <td class="form-td-content">
                    	<input name="abbreviation" type="text" class="easyui-textbox" data-options="prompt:'请输入工程简称'" style="width:100%">
                    </td>
                    <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>负责人：</td>
                    <td class="form-td-content" style="width:150px">
                    	<input id="add_empName" name="empName" class="easyui-textbox" alt="add_emp" data-options="required:true,buttonText:'',buttonAlign:'right',buttonIcon:'icon-search',editable:false,prompt:'请输入负责人',onClickButton:empSelection" style="width:100%">
                    	<input id="add_empId" name="empId" type="hidden"/>
                    </td>
                    <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>施工单位：</td>
                    <td class="form-td-content" style="width:150px">
                    	<input id="add_parentId" name="parentId" class="easyui-combotree" data-options="required:true,prompt:'请输入施工单位'" style="width:100%">
                    </td>
					
                </tr>
                <tr>
                    <td class="form-td-label">承建单位：</td>
                    <td class="form-td-content">
                    	<input id="add_cjdw" name="cjdw" class="easyui-textbox" data-options="prompt:'请输入承建单位'" style="width:100%" readonly="readonly">
                    </td>
                    <td class="form-td-label">监理单位：</td>
                    <td class="form-td-content">
                    	<input id="add_jldw" name="jldw" class="easyui-textbox" data-options="prompt:'请输入监理单位'" style="width:100%">
                    </td>
                    <td class="form-td-label">建设单位：</td>
                    <td class="form-td-content">
                    	<input id="add_jsdw" name="jsdw" class="easyui-textbox" data-options="prompt:'请输入建设单位'" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>地理位置：</td>
                    <td class="form-td-content" style="width:150px">
                    	<input name="position" type="text" class="easyui-textbox" data-options="required:true,buttonText:'',buttonAlign:'right',buttonIcon:'icon-search',editable:false,prompt:'请输入地理位置',onClickButton:selectAddress" style="width:100%">
                    </td>	
					<td class="form-td-label"><span style="color: red;">*</span>所在国家：</td>
                    <td class="form-td-content">
                    	<input id="add_country" name="country" type="text" class="easyui-textbox" data-options="required:true,prompt:'请输入所在国家'" value="" style="width:100%">
                    </td>
					<td class="form-td-label"><span style="color: red;">*</span>所在片区：</td>
                    <td class="form-td-content">
                    	<input id="add_area" name="area" type="text" class="easyui-textbox" data-options="required:true,prompt:'请输入所在片区'" value="" style="width:100%">
                    </td>
                    
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>所在省份：</td>
                    <td class="form-td-content">
                    	<input name="province" type="text" class="easyui-textbox" data-options="required:true,prompt:'请输入所在省份'" value="" style="width:100%">
                    </td>
                	<td class="form-td-label"><span style="color: red;">*</span>所在城市：</td>
                    <td class="form-td-content">
                    	<input name="city" type="text" class="easyui-textbox" data-options="required:true,prompt:'请输入所在城市'" value="" style="width:100%">
                    </td>
                    <td class="form-td-label">总长度(米)：</td>
                	<td class="form-td-content">
                		<input name="tunnellength" type="text" class="easyui-numberbox" data-options="prompt:'请输入掘进总长度(米)'" value="" style="width:100%">
                	</td>
                	
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>管片长度(m)：</td>
                	<td class="form-td-content">
                		<input name="ringwidth" type="text" class="easyui-numberbox" data-options="required:true,prompt:'请输入工程环宽(米)',precision:1" value="" style="width:100%">
                	</td>
                	<td class="form-td-label">合同工期起始：</td>
                	<td class="form-td-content">
                		<input name="startdate" class="easyui-datebox" data-options="prompt:'请输入进场日期'" style="width:100%">
                	</td>
                    <td class="form-td-label">合同工期截止：</td>
                	<td class="form-td-content">
                		<input name="estimateenddate" data-options="prompt:'请输入预计完成日期'" class="easyui-datebox" style="width:100%">
                	</td>
                </tr>
                <tr>
                	<td class="form-td-label">地铁线路：</td>
                	<td class="form-td-content">
                		<input name="line" type="text" class="easyui-textbox" data-options="required:false,prompt:'请输入地铁线路',precision:1" value="" style="width:100%">
                	</td>
                	<td class="form-td-label">合同金额：</td>
                	<td class="form-td-content">
                		<input name="htje" type="text" class="easyui-textbox" data-options="required:false,prompt:'请输入合同金额',precision:1" value="" style="width:100%">
                	</td>
                	<td class="form-td-label">开累完成：</td>
                	<td class="form-td-content">
                		<input name="klwc" type="text" class="easyui-textbox" data-options="required:false,prompt:'请输入开累完成',precision:1" value="" style="width:100%">
                	</td>
                </tr>
                <tr>
                	<td class="form-td-label">管片外径(m)：</td>
                	<td class="form-td-content">
                		<input name="gpwj" type="text" class="easyui-textbox" data-options="required:false,prompt:'请输入管片外径(m)',precision:1" value="" style="width:100%">
                	</td>
                	<td class="form-td-label"><span style="color: red;">*</span>施工状态：</td>
                	<td class="form-td-content">
                		<input id="add_proState" name="proState" type="text" class="easyui-combobox" data-options="required:true,prompt:'请选择施工状态',precision:1" value="" style="width:100%">
                	</td>
                </tr>
                <tr>
                	<td class="form-td-label">施工地址：</td>
                    <td class="form-td-content" colspan="5">
                    	<input name="projectaddress" data-options="prompt:'请输入施工地址'" class="easyui-textbox" value="" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label" rowspan="5">工程简介：</td>
                    <td class="form-td-content" colspan="5" rowspan="5">
                    	<input name="description" class="easyui-textbox" data-options="required:false,prompt:'请输入备注',multiline:true" style="width:100%;height:100%">
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
        </form>
	</div>
	<div id="add-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="addAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#addDialog').dialog('close')">关闭</a>
	</div>
    <!-- 弹出框－编辑 -->
	<div id="editDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#edit-dialog-buttons',closed:true,cache: false,modal: true"
		style="width:850px;height:480px;padding:5px 5px;">
        <form id="editForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden">
			<table class="grid">
                
	            <tr>
					<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>工程名称：</td>
                    <td class="form-td-content" colspan="5" >
                    	<input name="proName" type="text" class="easyui-textbox" data-options="required:true,prompt:'请输入工程名称'" style="width:100%">
                    </td>
                    
                </tr>
                <tr>
                    <td class="form-td-label"style="width:100px">工程简称：</td>
                    <td class="form-td-content">
                    	<input name="abbreviation" type="text" class="easyui-textbox" data-options="prompt:'请输入工程简称'" style="width:100%" >
                    </td>
                    <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>负责人：</td>
                    <td class="form-td-content" style="width:150px">
                    	<input id="edit_empName" name="empName" class="easyui-textbox" alt="edit_emp" data-options="required:true,buttonText:'',buttonAlign:'right',buttonIcon:'icon-search',editable:false,prompt:'请输入负责人',onClickButton:empSelection" style="width:100%">
                    	<input id="edit_empId" name="empId" type="hidden"/>
                    </td>
                    <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>施工单位：</td>
                    <td class="form-td-content" style="width:150px">
                    	<input id="edit_parentId" name="parentId" class="easyui-combotree" data-options="required:true,prompt:'请输入施工单位'" style="width:100%">
                    </td>
					
                </tr>
                 
                <tr>
                    <td class="form-td-label">承建单位：</td>
                    <td class="form-td-content">
                    	<input id="edit_cjdw" name="cjdw" class="easyui-textbox" data-options="prompt:'请输入承建单位'" style="width:100%">
                    </td>
                    <td class="form-td-label">监理单位：</td>
                    <td class="form-td-content">
                    	<input id="edit_jldw" name="jldw" class="easyui-textbox" data-options="prompt:'请输入监理单位'" style="width:100%">
                    </td>
                    <td class="form-td-label">建设单位：</td>
                    <td class="form-td-content">
                    	<input id="edit_jsdw" name="jsdw" class="easyui-textbox" data-options="prompt:'请输入建设单位'" style="width:100%">
                    </td>
                </tr>
                <tr>
                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>地理位置：</td>
                    <td class="form-td-content" style="width:150px">
                    	<input name="position" type="text" class="easyui-textbox" data-options="required:true,buttonText:'',buttonAlign:'right',buttonIcon:'icon-search',editable:false,prompt:'请输入地理位置',onClickButton:selectAddress" style="width:100%">
                    </td>
					<td class="form-td-label"><span style="color: red;">*</span>所在国家：</td>
                    <td class="form-td-content">
                    	<input id="edit_country" name="country" type="text" class="easyui-textbox" data-options="required:true,prompt:'请输入所在国家'" value="" style="width:100%">
                    </td>
					<td class="form-td-label"><span style="color: red;">*</span>所在片区：</td>
                    <td class="form-td-content">
                    	<input id="edit_area" name="area" type="text" class="easyui-textbox" data-options="required:true,prompt:'请输入所在片区'" value="" style="width:100%">
                    </td>
                
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>所在省份：</td>
                    <td class="form-td-content">
                    	<input name="province" type="text" class="easyui-textbox" data-options="required:true,prompt:'请输入所在省份'" value="" style="width:100%">
                    </td>
                	<td class="form-td-label"><span style="color: red;">*</span>所在城市：</td>
                    <td class="form-td-content">
                    	<input name="city" type="text" class="easyui-textbox" data-options="required:true,prompt:'请输入所在城市'" value="" style="width:100%">
                    </td>
					<td class="form-td-label">总长度(米)：</td>
                	<td class="form-td-content">
                		<input name="tunnellength" type="text" class="easyui-numberbox" data-options="prompt:'请输入掘进总长度(米)'" value="" style="width:100%">
                	</td>
				
                </tr>
                <tr>
                	
                	
                	<td class="form-td-label"><span style="color: red;">*</span>管片长度(m)：</td>
                	<td class="form-td-content">
                		<input name="ringwidth" type="text" class="easyui-numberbox" data-options="required:true,prompt:'请输入工程环宽(米)',precision:1" value="" style="width:100%">
                	</td>
                	
                    <td class="form-td-label">合同工期起始：</td>
                	<td class="form-td-content">
                		<input name="startdate" class="easyui-datebox" data-options="prompt:'请输入进场日期'" style="width:100%">
                	</td>
                	<td class="form-td-label">合同工期截止：</td>
                	<td class="form-td-content">
                		<input name="estimateenddate" data-options="prompt:'请输入预计完成日期'" class="easyui-datebox" style="width:100%">
                	</td>
                </tr>
                <tr>
                	<td class="form-td-label">地铁线路：</td>
                	<td class="form-td-content">
                		<input name="line" type="text" class="easyui-textbox" data-options="required:false,prompt:'请输入地铁线路',precision:1" value="" style="width:100%">
                	</td>
                	<td class="form-td-label">合同金额：</td>
                	<td class="form-td-content">
                		<input name="htje" type="text" class="easyui-textbox" data-options="required:false,prompt:'请输入合同金额',precision:1" value="" style="width:100%">
                	</td>
                	<td class="form-td-label">开累完成：</td>
                	<td class="form-td-content">
                		<input name="klwc" type="text" class="easyui-textbox" data-options="required:false,prompt:'请输入开累完成',precision:1" value="" style="width:100%">
                	</td>
                </tr>
                <tr>
                	<td class="form-td-label">管片外径(m)：</td>
                	<td class="form-td-content">
                		<input name="gpwj" type="text" class="easyui-textbox" data-options="required:false,prompt:'请输入管片外径(m)',precision:1" value="" style="width:100%">
                	</td>
                	<td class="form-td-label"><span style="color: red;">*</span>施工状态：</td>
                	<td class="form-td-content">
                		<input id="edit_proState" name="proState" type="text" class="easyui-combobox" data-options="required:true,prompt:'请选择施工状态',precision:1" value="" style="width:100%">
                	</td>
                </tr>
                <tr>
                <tr>
                	<td class="form-td-label">施工地址：</td>
                    <td class="form-td-content" colspan="5">
                    	<input name="projectaddress" data-options="prompt:'请输入施工地址'" class="easyui-textbox" value="" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label">工程简介：</td>
                    <td class="form-td-content" colspan="5" rowspan="2">
                    	<input name="description" class="easyui-textbox" data-options="required:false,prompt:'请输入备注',multiline:true" style="width:100%;height:100%">
                    </td>
                </tr>
                <tr>
                	<td ></td>
                </tr>
            </table>
        </form>
	</div>
	<div id="edit-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="editAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#editDialog').dialog('close')">关闭</a>
	</div>
    <!-- 弹出框－查看 -->
	<div id="showDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#show-dialog-buttons',closed:true,cache: false,modal: true,maximizable:true"
		style="width:850px;height:580px;padding:5px 5px;" >
        <form id="showForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden">
			<table class="grid">
	            <tr>
					<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>工程名称：</td>
                    <td class="form-td-content" colspan="5" >
                    	<input name="proName" type="text" class="easyui-textbox" data-options="" style="width:100%"  >
                    </td>
                    
                </tr>
                <tr>
                    <td class="form-td-label" >工程简称：</td>
                    <td class="form-td-content" >
                    	<input name="abbreviation" type="text" class="easyui-textbox" data-options="" style="width:100%"  >
                    </td>
                    <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>负责人：</td>
                    <td class="form-td-content" style="width:150px">
                    	<input name="empName"  class="easyui-textbox" data-options="" style="width:100%"  >
                    </td>
                    <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>施工单位：</td>
                    <td class="form-td-content" style="width:150px">
                    	<input name="parentName"  class="easyui-textbox" data-options="" style="width:100%"  >
                    </td>
						
                </tr>
                 
                <tr>
                    <td class="form-td-label">承建单位：</td>
                    <td class="form-td-content">
                    	<input name="cjdw"  class="easyui-textbox" data-options="" style="width:100%"  >
                    </td>
                    <td class="form-td-label">监理单位：</td>
                    <td class="form-td-content">
                    	<input id="show_jldw" name="jldw" class="easyui-textbox" data-options="prompt:''" style="width:100%"  >
                    </td>
                    <td class="form-td-label">建设单位：</td>
                    <td class="form-td-content">
                    	<input name="jsdw"  class="easyui-textbox" value="中铁一局城轨公司" data-options="" style="width:100%"  >
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>地理位置：</td>
                    <td class="form-td-content" style="width:150px">
                    	<input name="position"  class="easyui-textbox" data-options="" style="width:100%"  >
                    </td>
					<td class="form-td-label"><span style="color: red;">*</span>所在国家：</td>
                    <td class="form-td-content">
                    	<input name="country"  class="easyui-textbox" data-options="" style="width:100%"  >
                    </td>
					<td class="form-td-label"><span style="color: red;">*</span>所在片区：</td>
                    <td class="form-td-content">
                    	<input name="area"  class="easyui-textbox" data-options="" style="width:100%"  >
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>所在省份：</td>
                    <td class="form-td-content">
                    	<input name="province"  class="easyui-textbox" data-options="" style="width:100%"  >
                    </td>
                	<td class="form-td-label"><span style="color: red;">*</span>所在城市：</td>
                    <td class="form-td-content">
                    	<input name="city" type="text" class="easyui-textbox" data-options="required:true" value="" style="width:100%"  >
                    </td>
					<td class="form-td-label">总长度(米)：</td>
                	<td class="form-td-content">
                		<input name="tunnellength" type="text" class="easyui-numberbox" data-options="" value="" style="width:100%"  >
                	</td>
                </tr>
                <tr>
                	
                	<td class="form-td-label"><span style="color: red;">*</span>管片长度(m)：</td>
                	<td class="form-td-content">
                		<input name="ringwidth" type="text" class="easyui-numberbox" data-options="required:true,precision:1" value="" style="width:100%"  >
                	</td>
                	<td class="form-td-label">合同工期起始：</td>
                	<td class="form-td-content">
                		<input name="startdate"  class="easyui-textbox" data-options="" style="width:100%"  >
                    </td>
                	<td class="form-td-label">合同工期截止：</td>
                	<td class="form-td-content">
                		<input name="estimateenddate"  class="easyui-textbox" data-options="" style="width:100%"  >
                    </td>
                </tr>
                <tr>
                	<td class="form-td-label">地铁线路：</td>
                	<td class="form-td-content">
                		<input name="line" type="text" class="easyui-textbox" data-options="required:false,prompt:'',precision:1" value="" style="width:100%"  >
                	</td>
                	<td class="form-td-label">合同金额：</td>
                	<td class="form-td-content">
                		<input name="htje" type="text" class="easyui-textbox" data-options="required:false,prompt:'',precision:1" value="" style="width:100%"  >
                	</td>
                	<td class="form-td-label">开累完成：</td>
                	<td class="form-td-content">
                		<input name="klwc" type="text" class="easyui-textbox" data-options="required:false,prompt:'',precision:1" value="" style="width:100%"  >
                	</td>
                </tr>
                <tr>
                	<td class="form-td-label">管片外径(m)：</td>
                	<td class="form-td-content">
                		<input name="gpwj" type="text" class="easyui-textbox" data-options="required:false,prompt:'',precision:1" value="" style="width:100%"  >
                	</td>
                	<td class="form-td-label"><span style="color: red;">*</span>施工状态：</td>
                	<td class="form-td-content">
                		<input name="proState"  class="easyui-textbox" data-options="" style="width:100%"  >
                    </td>
                </tr>
                <tr>
                <tr>
                	<td class="form-td-label">施工地址：</td>
                    <td class="form-td-content" colspan="5">
                    	<input name="projectaddress" data-options="" class="easyui-textbox" value="" style="width:100%"  >
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label" rowspan="5">工程简介：</td>
                    <td class="form-td-content" colspan="5" rowspan="5">
                    	<input name="description" class="easyui-textbox" data-options="required:false,multiline:true" style="width:100%;height:100%"  >
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
        </form>
	</div>
	<div id="show-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#showDialog').dialog('close')">关闭</a>
	</div>
	<!-- 地图选择器 -->
	<div id="openDiv" class="easyui-window" closed="true" modal="true" title="位置选取" style="width:800px;height:500px;">
   		<iframe id='openMapIframe' src="" frameborder="0" style="width:100%;height:98%;"></iframe>
	</div>
</body>
</html>