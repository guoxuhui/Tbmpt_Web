<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp"%>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>盾构机历史使用信息纪录</title>
<script type="text/javascript" src="${staticPath }/static/js/tbmmg/useInfo/list.js?v=20170522112458" charset="utf-8"></script>
</head>

<body class="easyui-layout" data-options="fit:true,border:false">
	<!-- 查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:100px;border: false;overflow: hidden;" data-options="collapsible:true">
        <form id="searchForm">
        	<input id="search_tbmName" name="tbmName" type="hidden"/>
            <table class="grid">
				<tr>
					<td class="form-td-label" style="width: 100px">盾构机名称：</td>
					<td class="form-td-content" style="width: 160px">
						<select id="search_tbmbh" name="tbmbh" class="easyui-combobox" data-options="prompt:'请输入盾构机名称'" style="width: 100%;"></select>
					</td>
					<td class="form-td-label" style="width: 100px">所在单位：</td>
					<td class="form-td-content" style="width: 160px">
						<select id="search_szdw" name="szdw" class="easyui-combobox" data-options="prompt:'请输入所在单位'" style="width: 100%;"></select>
					</td>
				</tr>
				<tr>					
					<td class="form-td-label" style="width: 100px" >负责人：</td>
					<td class="form-td-content" style="width: 160px" >
						<select  name="person" class="easyui-textbox" data-options="prompt:'请输入负责人'" style="width: 100%;"></select>
					</td>					
					<td class="form-td-label" style="width: 100px" >盾构机状态：</td>
					<td class="form-td-content" style="width: 160px" >
						<input id="search_tbmState" name ="tbmState" class="easyui-textbox" data-options="prompt:'请输入盾构机状态'" style="width: 100%;"/>
					</td>										
														
					<td style="text-align: Right;" colspan="2" >
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
		<a onclick="reload();" href="javascript:void(0);" id="reload_button_tbmmg_useInfo" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<a onclick="clearSelections();" href="javascript:void(0);" id="clear_button_tbmmg_useInfo" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    	<shiro:hasPermission name="/tbmmg/useInfo/add">
    	   <a onclick="addFun();" href="javascript:void(0);" id="add_button_tbmmg_useInfo" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/tbmmg/useInfo/edit">
    	   <a onclick="editFun();" href="javascript:void(0);" id="edit_button_tbmmg_useInfo" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/tbmmg/useInfo/del">
    	   <a onclick="deleteFun();" href="javascript:void(0);" id="del_button_tbmmg_useInfo" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/tbmmg/useInfo/list">
    	   <a onclick="showFun();" href="javascript:void(0);" id="show_button_tbmmg_useInfo" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-book_previous'">查看</a>
    	</shiro:hasPermission>  	
    	<a onclick="expXls();" href="javascript:void(0);" id="expXls_button_tbmmg_useInfo" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
    	<a onclick="expPdf();" href="javascript:void(0);" id="expPdf_button_tbmmg_useInfo" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_white_acrobat'">导出Pdf</a>       
  	</div>
  	
  	<!-- 弹出框－新增 -->
	<div id="addDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal: true"
		style="width:850px;height:380px;padding:10px 10px;">
        <form id="addForm" method="post" data-options="novalidate:true">
        	<input id="add_tbmName" name="tbmName" type="hidden">
			<table class="grid" style="width:100%">
	            <tr>
                    <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>盾构机名称：</td>
                    <td class="form-td-content" style="width:300px">
                    	<select id="add_tbmbh" name="tbmbh" class="easyui-combogrid" data-options="editable:false,required:true,prompt:'请输入盾构机名称'" style="width:100%"></select>
                    </td>
					<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>所在单位：</td>
                    <td class="form-td-content" style="width:300px">
                    	<input id="add_szdw" name="szdw" class="easyui-textbox" data-options="prompt:'请输入所在单位'" style="width:100%">
                    </td>
                </tr>

                <tr>
                    <td class="form-td-label" style="width:100px">到达时间：</td>
                    <td class="form-td-content">
                    	<input id="add_daTime" name="daTime" class="easyui-datetimebox" data-options="prompt:'请输入到达时间'" style="width:100%">
                    </td>
                    <td class="form-td-label" style="width:100px">离开时间：</td>
                    <td class="form-td-content">
                    	<input id="add_lkTime" name="lkTime" class="easyui-datetimebox" data-options="prompt:'请输入离开时间'" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <!-- <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>地理位置：</td>
                    <td class="form-td-content" style="width:150px">
                    	<input name="position" type="text" class="easyui-textbox" data-options="buttonText:'',buttonAlign:'right',buttonIcon:'icon-search',editable:false,prompt:'请输入地理位置',onClickButton:selectAddress" style="width:100%">
                    </td> -->	
					<td class="form-td-label" style="width:100px">始发时间：</td>
                    <td class="form-td-content">
                    	<input id="add_sfTime" name="sfTime" type="text" class="easyui-datetimebox" data-options="prompt:'请输入始发时间'" value="" style="width:100%">
                    </td>
					<td class="form-td-label" style="width:100px">出洞时间：</td>
                    <td class="form-td-content">
                    	<input id="add_cdTime" name="cdTime" type="text" class="easyui-datetimebox" data-options="prompt:'请输入出洞时间'" value="" style="width:100%">
                    </td>
                    
                </tr>
                <tr>
                    <td class="form-td-label" style="width:100px">运输时间：</td>
                    <td class="form-td-content">
                    	<input name="ysTime" type="text" class="easyui-datetimebox" data-options="prompt:'请输入运输时间'" value="" style="width:100%">
                    </td>
                	<td class="form-td-label" style="width:100px">维修时间：</td>
                    <td class="form-td-content">
                    	<input name="wxTime" type="text" class="easyui-datetimebox" data-options="prompt:'请输入维修时间'" value="" style="width:100%">
                    </td>
                  </tr>
                  <tr>
                    <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>负责人：</td>
                	<td class="form-td-content">
                		<input name="person" type="text" class="easyui-textbox" data-options="required:true,prompt:'请输入负责人'" value="" style="width:100%">
                	</td>
                    <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>联系电话：</td>
                	<td class="form-td-content">
                		<input name="phone" type="text" class="easyui-textbox" data-options="required:true,prompt:'请输入联系电话'" value="" style="width:100%">
                	</td>
                	
                </tr>
                <tr>
                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>盾构机状态：</td>
                	<td class="form-td-content">
                		<input id="add_tbmState" name="tbmState" class="easyui-combogrid" data-options="required:true,prompt:'请输入盾构机状态'" style="width:100%">
                	</td>
                    <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>日期：</td>
                	<td class="form-td-content">
                		<input name="rq" data-options="required:true,prompt:'请输入日期'" class="easyui-datetimebox" style="width:100%">
                	</td>
                </tr>                
            </table>
            <div id="add_hidden_szdw">
            <div id="add_hidden_tr">
            	<table class="grid" style="width:100%">
                <tr>
                    <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>项目名称：</td>
                    <td class="form-td-content" style="width:300px">
                    	<input id="add_gcid" name="gcid" class="easyui-combobox" data-options="required:true,prompt:'请输入项目名称'" style="width:100%">
                    </td>
                    <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>区间名称：</td>
                    <td class="form-td-content" style="width:300px">
                    	<input id="add_qjid" name="qjid" class="easyui-combobox" data-options="required:true,prompt:'请输入区间名称'" style="width:100%">
                    </td>
                 </tr>
                 <tr>
                    <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>线路名称：</td>
                    <td class="form-td-content" style="width:300px">
                    	<input id="add_xlid" name="xlid" class="easyui-textbox" data-options="required:true,prompt:'请选择线路'" style="width:100%">
                    </td>
					
                </tr>
                </table>
             </div>
             </div>
        </form>
	</div>
	<div id="add-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="addAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#addDialog').dialog('close')">关闭</a>
	</div>
	
	<div id="editDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#edit-dialog-buttons',closed:true,cache: false,modal: true"
		style="width:850px;height:380px;padding:5px 5px;">
        <form id="editForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden"/>
        	<input id="edit_tbmName" name="tbmName" type="hidden">
			<table class="grid" style="width:100%">
	            <tr>
                    <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>盾构机名称：</td>
                    <td class="form-td-content" style="width:300px">
                    	<select id="edit_tbmbh" name="tbmbh" class="easyui-combogrid" data-options="editable:false,required:true,prompt:'请输入盾构机名称'" style="width:100%"></select>
                    </td>
					<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>所在单位：</td>
                    <td class="form-td-content" style="width:300px">
                    	<input id="edit_szdw" name="szdw" class="easyui-textbox" data-options="prompt:'请输入所在单位'" style="width:100%">
                    </td>
                </tr>

                <tr>
                    <td class="form-td-label" style="width:100px">到达时间：</td>
                    <td class="form-td-content">
                    	<input id="edit_daTime" name="daTime" class="easyui-datetimebox" data-options="prompt:'请输入到达时间'" style="width:100%">
                    </td>
                    <td class="form-td-label" style="width:100px">离开时间：</td>
                    <td class="form-td-content">
                    	<input id="edit_lkTime" name="lkTime" class="easyui-datetimebox" data-options="prompt:'请输入离开时间'" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <!-- <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>地理位置：</td>
                    <td class="form-td-content" style="width:150px">
                    	<input name="position" type="text" class="easyui-textbox" data-options="buttonText:'',buttonAlign:'right',buttonIcon:'icon-search',editable:false,prompt:'请输入地理位置',onClickButton:selectAddress" style="width:100%">
                    </td> -->	
					<td class="form-td-label" style="width:100px">始发时间：</td>
                    <td class="form-td-content">
                    	<input id="edit_sfTime" name="sfTime" type="text" class="easyui-datetimebox" data-options="prompt:'请输入始发时间'" value="" style="width:100%">
                    </td>
					<td class="form-td-label" style="width:100px">出洞时间：</td>
                    <td class="form-td-content">
                    	<input id="edit_cdTime" name="cdTime" type="text" class="easyui-datetimebox" data-options="prompt:'请输入出洞时间'" value="" style="width:100%">
                    </td>
                    
                </tr>
                <tr>
                    <td class="form-td-label" style="width:100px">运输时间：</td>
                    <td class="form-td-content">
                    	<input name="ysTime" type="text" class="easyui-datetimebox" data-options="prompt:'请输入运输时间'" value="" style="width:100%">
                    </td>
                	<td class="form-td-label" style="width:100px">维修时间：</td>
                    <td class="form-td-content">
                    	<input name="wxTime" type="text" class="easyui-datetimebox" data-options="prompt:'请输入维修时间'" value="" style="width:100%">
                    </td>
                  </tr>
                  <tr>
                    <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>负责人：</td>
                	<td class="form-td-content">
                		<input name="person" type="text" class="easyui-textbox" data-options="required:true,prompt:'请输入负责人'" value="" style="width:100%">
                	</td>
                    <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>联系电话：</td>
                	<td class="form-td-content">
                		<input name="phone" type="text" class="easyui-textbox" data-options="required:true,prompt:'请输入联系电话'" value="" style="width:100%">
                	</td>
                	
                </tr>
                <tr>
                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>盾构机状态：</td>
                	<td class="form-td-content">
                		<input id="edit_tbmState" name="tbmState" class="easyui-combogrid" data-options="required:true,prompt:'请输入盾构机状态'" style="width:100%">
                	</td>
                    <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>日期：</td>
                	<td class="form-td-content">
                		<input name="rq" data-options="required:true,prompt:'请输入日期'" class="easyui-datetimebox" style="width:100%">
                	</td>
                </tr>                
            </table>
            <div id="edit_hidden_szdw">
            <div id="edit_hidden_tr">
            	<table class="grid" style="width:100%">
                <tr>
                    <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>项目名称：</td>
                    <td class="form-td-content" style="width:300px">
                    	<input id="edit_gcid" name="gcid" class="easyui-combobox" data-options="required:true,prompt:'请输入项目名称'" style="width:100%">
                    </td>
                    <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>区间名称：</td>
                    <td class="form-td-content" style="width:300px">
                    	<input id="edit_qjid" name="qjid" class="easyui-combobox" data-options="required:true,prompt:'请输入区间名称'" style="width:100%">
                    </td>
                 </tr>
                 <tr>
                    <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>线路名称：</td>
                    <td class="form-td-content" style="width:300px">
                    	<input id="edit_xlid" name="xlid" class="easyui-textbox" data-options="required:true,prompt:'请选择线路'" style="width:100%">
                    </td>
					
                </tr>
                </table>
             </div>
             </div>
        </form>
	</div>
	<div id="edit-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="editAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#editDialog').dialog('close')">关闭</a>
	</div>
		<!-- 弹出框－查看 -->
	<div id="showDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#show-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:850px;height:380px;padding:5px 5px;" >
        <form id="showForm" method="post" data-options="novalidate:true">
        <input id="show_tbmName" name="tbmName" type="hidden">
			<table class="grid" style="width:100%">
	            <tr>
                    <td class="form-td-label" style="width:100px">盾构机名称：</td>
                    <td class="form-td-content" style="width:300px">
                    	<select id="show_tbmbh" name="tbmbh" class="easyui-combogrid" data-options="editable:false,required:true,prompt:'请输入盾构机名称'" style="width:100%" readonly="readonly"></select>
                    </td>
					<td class="form-td-label" style="width:100px">所在单位：</td>
                    <td class="form-td-content" style="width:300px">
                    	<input id="show_szdw" name="szdw" class="easyui-textbox" data-options="prompt:'请输入所在单位'" style="width:100%" readonly="readonly">
                    </td>
                </tr>

                <tr>
                    <td class="form-td-label" style="width:100px">到达时间：</td>
                    <td class="form-td-content">
                    	<input id="show_daTime" name="daTime" class="easyui-datetimebox" data-options="prompt:'请输入到达时间'" style="width:100%" readonly="readonly">
                    </td>
                    <td class="form-td-label" style="width:100px">离开时间：</td>
                    <td class="form-td-content">
                    	<input id="show_lkTime" name="lkTime" class="easyui-datetimebox" data-options="prompt:'请输入离开时间'" style="width:100%" readonly="readonly">
                    </td>
                </tr>
                <tr>
                    <!-- <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>地理位置：</td>
                    <td class="form-td-content" style="width:150px">
                    	<input name="position" type="text" class="easyui-textbox" data-options="buttonText:'',buttonAlign:'right',buttonIcon:'icon-search',editable:false,prompt:'请输入地理位置',onClickButton:selectAddress" style="width:100%">
                    </td> -->	
					<td class="form-td-label" style="width:100px">始发时间：</td>
                    <td class="form-td-content">
                    	<input id="show_sfTime" name="sfTime" type="text" class="easyui-datetimebox" data-options="prompt:'请输入始发时间'" value="" style="width:100%" readonly="readonly">
                    </td>
					<td class="form-td-label" style="width:100px">出洞时间：</td>
                    <td class="form-td-content">
                    	<input id="show_cdTime" name="cdTime" type="text" class="easyui-datetimebox" data-options="prompt:'请输入出洞时间'" value="" style="width:100%" readonly="readonly">
                    </td>
                    
                </tr>
                <tr>
                    <td class="form-td-label" style="width:100px">运输时间：</td>
                    <td class="form-td-content">
                    	<input name="ysTime" type="text" class="easyui-datetimebox" data-options="prompt:'请输入运输时间'" value="" style="width:100%" readonly="readonly">
                    </td>
                	<td class="form-td-label" style="width:100px">维修时间：</td>
                    <td class="form-td-content">
                    	<input name="wxTime" type="text" class="easyui-datetimebox" data-options="prompt:'请输入维修时间'" value="" style="width:100%" readonly="readonly">
                    </td>
                  </tr>
                  <tr>
                    <td class="form-td-label" style="width:100px">负责人：</td>
                	<td class="form-td-content">
                		<input name="person" type="text" class="easyui-textbox" data-options="required:true,prompt:'请输入负责人'" value="" style="width:100%" readonly="readonly">
                	</td>
                    <td class="form-td-label" style="width:100px">联系电话：</td>
                	<td class="form-td-content">
                		<input name="phone" type="text" class="easyui-textbox" data-options="required:true,prompt:'请输入联系电话'" value="" style="width:100%" readonly="readonly">
                	</td>
                	
                </tr>
                <tr>
                	<td class="form-td-label" style="width:100px">盾构机状态：</td>
                	<td class="form-td-content">
                		<input id="show_tbmState" name="tbmState" class="easyui-combogrid" data-options="required:true,prompt:'请输入盾构机状态'" style="width:100%" readonly="readonly">
                	</td>
                    <td class="form-td-label" style="width:100px">日期：</td>
                	<td class="form-td-content">
                		<input name="rq" data-options="required:true,prompt:'请输入日期'" class="easyui-datetimebox" style="width:100%" readonly="readonly">
                	</td>
                </tr>                
            </table>
            <div id="show_hidden_szdw">
            <div id="show_hidden_tr">
            	<table class="grid" style="width:100%">
                <tr>
                    <td class="form-td-label" style="width:100px">项目名称：</td>
                    <td class="form-td-content" style="width:300px">
                    	<input id="show_gcid" name="gcid" class="easyui-combobox" data-options="required:true,prompt:'请输入项目名称'" style="width:100%" readonly="readonly">
                    </td>
                    <td class="form-td-label" style="width:100px">区间名称：</td>
                    <td class="form-td-content" style="width:300px">
                    	<input id="show_qjid" name="qjid" class="easyui-combobox" data-options="required:true,prompt:'请输入区间名称'" style="width:100%" readonly="readonly">
                    </td>
                 </tr>
                 <tr>
                    <td class="form-td-label" style="width:100px">线路名称：</td>
                    <td class="form-td-content" style="width:300px">
                    	<input id="show_xlid" name="xlid" class="easyui-textbox" data-options="required:true,prompt:'请选择线路'" style="width:100%" readonly="readonly">
                    </td>
					
                </tr>
                </table>
             </div>
             </div>
        </form>
	</div>
	<div id="show-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#showDialog').dialog('close')">关闭</a>
	</div>
</body>

</html>