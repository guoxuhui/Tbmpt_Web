<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>管片质量上报管理</title>
<script type="text/javascript" src="${staticPath }/static/js/zl/up/listPro.js?time=20170824184233" charset="utf-8"></script>
<script type="text/javascript" src="${staticPath}/static/js/sys/base/uploadPicture.js?v=20170405121212" charset="utf-8"></script>
</head>
<jsp:include  page="../../sys/base/uploadPicture.jsp" />
<body class="easyui-layout" data-options="fit:true,border:false">
	<!-- 查询表单 -->
	<div class="easyui-panel" title="筛选" data-options="region:'north',border:false"  style="width:100%;height:0px;border: false;overflow: hidden;" data-options="collapsible:true">
        <form id="searchForm">
        		<input id="proName" name="proName" type="hidden" value="${proName}">
        		<input id="section" name="section" type="hidden" value="${section}">
        		<input id="line" name="line" type="hidden" value="${line}">
        		<input id="upDateStart" name="upDateStart" type="hidden" value="${startTime}">
        		<input id="upDateEnd" name="upDateEnd" type="hidden" value="${endTime}">
        		<input id="hasProblem" name="hasProblem" type="hidden" value="有">
        </form>
	</div>	
	<!-- 结果列表 -->
	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100px;">
		<table id="dataGrid" title="列表" data-options="fit:true,border:false"></table>
	</div>
	<div id="toolbar" style="display: none;">
		<a onclick="reloadFun();" href="javascript:void(0);" id="reload_button_zl_up" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<a onclick="clearSelections();" href="javascript:void(0);" id="clear_button_sys_emp" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    	<shiro:hasPermission name="/zl/up/add">
    	   <a onclick="addFun();" href="javascript:void(0);" id="add_button_zl_up" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/zl/up/edit">
    	   <a onclick="editFun();" href="javascript:void(0);" id="edit_button_zl_up" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/zl/up/del">
    	   <a onclick="deleteFun();" href="javascript:void(0);" id="del_button_zl_up" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/zl/up/picture">
    	<a onclick="UploadPicture();" href="javascript:void(0);" id="picture_button_zl_up" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-20130406125647919_easyicon_net_16'">上传图片</a>
    	</shiro:hasPermission>
    <shiro:hasPermission name="/zl/up/dataSynchronous">
    	<a onclick="dataSynchronous();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">数据同步</a>
    	</shiro:hasPermission>
  	</div>
	<!-- 弹出框－新增 -->
	<div id="addDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:680px;height:400px;padding:5px 5px;visibility: hidden;" >
         <form id="addForm" method="post" data-options="novalidate:true">
            <table class="grid" id="add_hasProblem_table">
            	<tbody id="add_hasProblem_table_after">
           		<tr>
					<td class="form-td-label" style="width: 120px"><span style="color: red;">*</span>所属工程:</td>
                    <td class="form-td-content"  style="width: 220px">
                    	<select id="add_proId" name="proName" class="easyui-combobox" data-options="editable:false,required:true,prompt:'--请选择--'" style="width:100%"></select>
                   	</td>
                   	<td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>所属区间:</td>
                		<td class="form-td-content" style="width: 220px">
                    	<select id="add_sectionId" name="section" class="easyui-combobox" data-options="editable:false,required:true,prompt:'--请选择--'" style="width:100%"></select>
                		</td>
                </tr>
                <tr>
                		<td class="form-td-label"  ><span style="color: red;">*</span>线路名称:</td>
                		<td class="form-td-content">
                    	<select id="add_lineId" name="line" class="easyui-combobox" data-options="editable:false,required:true,prompt:'--请选择--'" style="width:100%"></select>
                   	</td>
                   	<td class="form-td-label" ><span style="color: red;">*</span>环号:</td>
                    <td class="form-td-content">
                      <input id="add_cycleNo" name="cycleNo" class="easyui-numberbox" min="0" max="100000000"   data-options="required:false,prompt:'请输入环号'" style="width:100%;">
                    </td>
                </tr>
                <tr>
                	<td class="form-td-label" ><span style="color: red;">*</span>拼装日期:</td>
                    <td class="form-td-content" >
                   	<input name="fixDate" class="easyui-datetimebox" data-options="required:true,prompt:'拼装日期'" style="width: 100%;">

                    </td>
               		<td class="form-td-label" ><span style="color: red;">*</span>质量问题:</td>
                    <td class="form-td-content">
                   		<select id="add_hasProblem" onChange="add_hasProblemEvent()" name="hasProblem" class="easyui-combobox" data-options="required:true,prompt:'--请选择--'" style="width: 100%;">
						  
						</select>
                    </td>
                    
                </tr>
                </tbody>
                
	            		<tbody id="add_hasProblem_tr">
		                <tr>
		                    <td class="form-td-label"><span style="color: red;">*</span>问题分类:</td>
		                    <td class="form-td-content" >
		                    	<select  name="problemType" class="easyui-combobox" data-options="required:true,prompt:'--请选择--'" style="width: 100%;">
								   <option value="开裂">开裂</option>
								   <option value="错台">错台</option>
								   <option value="渗漏水">渗漏水</option>
								   <option value="姿态">姿态</option>
								   <option value="复紧">复紧</option>
								</select>
		                    </td>
		                    <td class="form-td-label" ><span style="color: red;">*</span>处理状态:</td>
		                    <td class="form-td-content">
		                       <select  name="status" id="status" class="easyui-combobox" data-options="required:true,prompt:'--请选择--'" style="width: 100%;">
								   <option value="未处理">未处理</option>
								   <option value="已处理">已处理</option>
								   <option value="暂不处理">暂不处理</option>
								</select>
		                    </td>
                		</tr>
                		<tr>
		                	<td class="form-td-label" ><span style="color: red;">*</span>问题描述:</td>
		                    <td class="form-td-content" colspan="3">
		                    	<input name="problemDesc" validType="length[0,200]" class="easyui-textbox" data-options="required:true,prompt:'请输入问题描述(200字以内)',multiline:true" style="width:100%;height:50px">
		                    </td>
		                </tr>
	                </tbody>
	           <tbody>
                 <tr>
                	<td class="form-td-label">备注:</td>
                    <td class="form-td-content" colspan="3" >
                    	<input name="remark" class="easyui-textbox" data-options="required:false,prompt:'请输入备注(200字以内)',multiline:true" validType="length[0,200]" style="width:100%;height:50px">
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
	</div>
	<div id="add-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="addAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#addDialog').dialog('close')">关闭</a>
	</div>

	<!-- 弹出框－编辑 -->
	<div id="editDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#edit-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:680px;height:400px;padding:5px 5px;visibility: hidden;" >
        <form id="editForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden">
           <table class="grid">
           		<tbody id="edit_hasProblem_table_after">
           		<tr>
					<td class="form-td-label" style="width: 120px"><span style="color: red;">*</span>所属工程:</td>
                    <td class="form-td-content"  style="width: 220px">
                    	<select id="edit_proId" name="proName" class="easyui-combobox" data-options="editable:false,required:true,prompt:'--请选择--'" style="width:100%"></select>
                   	</td>
                   	<td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>所属区间:</td>
                		<td class="form-td-content" style="width: 220px">
                    	<select id="edit_sectionId" name="section" class="easyui-combobox" data-options="editable:false,required:true,prompt:'--请选择--'" style="width:100%"></select>
                		</td>
                </tr>
                <tr>
                	<td class="form-td-label" ><span style="color: red;">*</span>线路名称:</td>
                	<td class="form-td-content">
                    	<select id="edit_lineId" name="line" class="easyui-combobox" data-options="editable:false,required:true,prompt:'--请选择--'" style="width:100%"></select>
                   	</td>
                   	<td class="form-td-label"><span style="color: red;">*</span>环号:</td>
                    <td class="form-td-content">
                      <input id="edit_cycleNo" name="cycleNo" class="easyui-numberbox" min="0" max="100000000"  data-options="required:true,prompt:'请输入环号'" style="width:100%;">
                    </td>
                </tr>
                <tr>
                	<td class="form-td-label" ><span style="color: red;">*</span>拼装日期:</td>
                    <td class="form-td-content" >
                   	<input name="fixDate" class="easyui-datetimebox" data-options="required:true,prompt:'拼装日期'" style="width: 100%;">

                    </td>
               		<td class="form-td-label" ><span style="color: red;">*</span>质量问题:</td>
                    <td class="form-td-content">
                   		<select id="edit_hasProblem" name="hasProblem" class="easyui-combobox" data-options="required:true,prompt:'--请选择--'" style="width: 100%;">
						  
						</select>
                    </td>
                    
                </tr>
                </tbody>
	            	<tbody id="edit_hasProblem_tr">
		                <tr>
		                    <td class="form-td-label" ><span style="color: red;">*</span>问题分类:</td>
		                    <td class="form-td-content" >
		                    	<select  name="problemType" class="easyui-combobox" data-options="required:true,prompt:'--请选择--'" style="width: 100%;">
								   <option value="开裂">开裂</option>
								   <option value="错台">错台</option>
								   <option value="渗漏水">渗漏水</option>
								   <option value="姿态">姿态</option>
								   <option value="复紧">复紧</option>
								</select>
		                    </td>
		                    <td class="form-td-label"><span style="color: red;">*</span>处理状态:</td>
		                    <td class="form-td-content" >
		                       <select  name="status" class="easyui-combobox" data-options="required:true,prompt:'--请选择--'" style="width: 100%;">
								   <option value="未处理">未处理</option>
								   <option value="已处理">已处理</option>
								   <option value="暂不处理">暂不处理</option>
								</select>
		                    </td>
                		</tr>
                		<tr>
		                	<td class="form-td-label"><span style="color: red;">*</span>问题描述:</td>
		                    <td class="form-td-content" colspan="3">
		                    	<input name="problemDesc" class="easyui-textbox" data-options="required:true,prompt:'请输入问题描述',multiline:true" style="width:100%;height:50px">
		                    </td>
		                </tr>
	                </tbody>
               <tbody>
                 <tr>
                	<td class="form-td-label">备注:</td>
                    <td class="form-td-content" colspan="3">
                    	<input name="remark" class="easyui-textbox" data-options="required:false,prompt:'请输入备注',multiline:true" validType="length[0,200]" style="width:100%;height:50px">
                    </td>
                </tr>
                </tbody>
            </table>
        </form>
	</div>
	<div id="edit-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="editAjax()">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#editDialog').dialog('close')">关闭</a>
	</div>

	<!-- 弹出框－查看 -->
	<div id="showDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#show-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:680px;height:400px;padding:5px 5px;visibility: hidden;" >
        <form id="showForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden">
           <table class="grid">
           	<tbody id="show_hasProblem_table_after">
             <tr>
					<td class="form-td-label" style="width: 120px">所属工程：</td>
                    <td class="form-td-content"  style="width: 220px">
                    	<select  readonly="readonly" id="show_proId" name="proName" class="easyui-combobox" data-options="editable:false,required:true,prompt:'请输入所属工程'" style="width:100%"></select>
                   	</td>
                   	<td class="form-td-label" style="width: 100px">所属区间：</td>
                	<td class="form-td-content" style="width: 220px">
                    	<select  readonly="readonly"  id="show_sectionId" name="section" class="easyui-combobox" data-options="editable:false,required:true,prompt:'请输入所属区间'" style="width:100%"></select>
                	</td>
                </tr>
                <tr>
                	<td class="form-td-label"  >线路名称：</td>
                	<td class="form-td-content">
                    	<select  readonly="readonly"  id="show_lineId" name="lineName" class="easyui-combobox" data-options="editable:false,required:true,prompt:'请输入所属线路'" style="width:100%"></select>
                   	</td>
                   	<td class="form-td-label" >环号:</td>
                    <td class="form-td-content" >
                      <input  readonly="readonly"  id="show_cycleNo" name="cycleNo" class="easyui-textbox" data-options="required:false,prompt:'请输入环号',editable:false" style="width:100%;">
                    </td>
                </tr>
                 <tr>
                	<td class="form-td-label" >拼装日期:</td>
                    <td class="form-td-content" >
                   	<input  readonly="readonly"  name="fixDate" class="easyui-datetimebox" data-options="required:true,prompt:'拼装日期'" style="width: 100%;">

                    </td>
               		<td class="form-td-label" >质量问题:</td>
                    <td class="form-td-content">
                   		<select  readonly="readonly"  id="show_hasProblem" name="hasProblem" class="easyui-combobox" data-options="required:true,prompt:'--请选择--'" style="width: 100%;">
						  
						</select>
                    </td>
                    
                </tr>
                </tbody>
	            	<tbody id="show_hasProblem_tr">
		                <tr>
		                    <td class="form-td-label" >问题分类:</td>
		                    <td class="form-td-content" >
		                    	<select  readonly="readonly"   name="problemType" class="easyui-combobox" data-options="required:true,prompt:'--请选择--'" style="width: 100%;">
								   <option value="开裂">开裂</option>
								   <option value="错台">错台</option>
								   <option value="渗漏水">渗漏水</option>
								   <option value="姿态">姿态</option>
								   <option value="复紧">复紧</option>
								</select>
		                    </td>
		                    <td class="form-td-label" >处理状态:</td>
		                    <td class="form-td-content" >
		                       <select  readonly="readonly"   name="status" class="easyui-combobox" data-options="required:true,prompt:'--请选择--'" style="width: 100%;">
								   <option value="未处理">未处理</option>
								   <option value="已处理">已处理</option>
								   <option value="暂不处理">暂不处理</option>
								</select>
		                    </td>
                		</tr>
                		<tr>
		                	<td class="form-td-label" >问题描述:</td>
		                    <td class="form-td-content" colspan="3" >
		                    	<input  readonly="readonly"  name="problemDesc" class="easyui-textbox" data-options="required:true,prompt:'请输入问题描述',multiline:true" style="width:100%;height:50px">
		                    </td>
		                </tr>
	                </tbody>
               <tbody>
                 <tr>
                	<td class="form-td-label" >备注:</td>
                    <td class="form-td-content" colspan="3" >
                    	<input  readonly="readonly"  name="remark" class="easyui-textbox" data-options="required:false,prompt:'请输入备注',multiline:true" style="width:100%;height:50px">
                    </td>
                </tr>
                </tbody>
            </table>

        </form>

	</div>
	<div id="show-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#showDialog').dialog('close')">关闭</a>
	</div>
</body>
</html>