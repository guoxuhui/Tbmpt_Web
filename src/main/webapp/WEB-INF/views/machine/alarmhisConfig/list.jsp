<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>机器数据报警类别配置</title>
<script type="text/javascript" src="${staticPath }/static/js/machine/alarmhisConfig/list.js?v=20170606160119" charset="utf-8"></script>
</head>

<body class="easyui-layout" data-options="fit:true,border:false">
	<!-- 查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:80px;border: false;overflow: hidden;" data-options="collapsible:true">
		<form id="searchForm">
		<table class="grid" style="border: 0px">
				<tr>
					<td class="form-td-label">报警分类名称：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='name'   data-options="prompt:'请输入报警分类名称',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
					<td class="form-td-label">所属电器系统：</td>
					<td class="form-td-content" style="width: 160px">
						<select id="search_msgtype" name="msgtype" class="easyui-combobox" data-options="prompt:'请选择所属电器系统'" style="width: 100%;">
						    <option value="">--请选择--</option>
		  					<option value="推进系统">推进系统</option>
						    <option value="导向系统">导向系统</option>
						</select>
					</td>
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
	
	<!--列表菜单 -->
	<div id="toolbar" style="display: none;">
    	<a onclick="reloadFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<a onclick="clearSelections();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    	
    	
    	<shiro:hasPermission name="/machine/alarmhisConfig/add">
    		<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/machine/alarmhisConfig/edit">
    		<a onclick="editFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/machine/alarmhisConfig/sbInfo">
  	        <a onclick="showFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-book_previous'">查看</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/machine/alarmhisConfig/del">
    		<a onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/machine/alarmhisConfig/ExpExcel">  
    	    <a onclick="expXls();" href="javascript:void(0);"   class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
    	</shiro:hasPermission> 
   	    <shiro:hasPermission name="/machine/alarmhisConfig/ExpPdf">  
    	    <a onclick="expPdf();" href="javascript:void(0);"   class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_white_acrobat'">导出Pdf</a>
    	</shiro:hasPermission>
    	<!-- <a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	<a onclick="editFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	<a onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	<a onclick="showFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">查看</a>
    	<a onclick="expXls();" href="javascript:void(0);"   class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
    	<a onclick="expPdf();" href="javascript:void(0);"   class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_white_acrobat'">导出Pdf</a>
    	  -->
   	</div>
    	 
    	 
	<!-- 弹出框－新增 -->
	<div id="addDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:600px;height:310px;padding:5px 5px;" >
        <form id="addForm" method="post" data-options="novalidate:true">
			<table class="grid">
				<tr>
					<td class="form-td-label"><span style="color: red;">*</span>报警分类名称：</td>
					<td class="form-td-content" style="width: 160px">
						<input id="add_name" name='name'   data-options="required:true,prompt:'请输入报警分类名称',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
					<td class="form-td-label"><span style="color: red;">*</span>所属电器系统：</td>
					<td class="form-td-content" style="width: 160px">
						<select id="add_electricalsystem" name="electricalsystem" class="easyui-combobox" data-options="required:true,prompt:'请选择所属电器系统'" style="width: 100%;">
						    <option value="推进系统">推进系统</option>
						    <option value="导向系统">导向系统</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="form-td-label" ><span style="color: red;">*</span>报警周期：</td>
					<td class="form-td-content" style="width: 160px">
						<input id="add_alarmcycle" name='alarmcycle'  data-options="required:true,prompt:'请输入报警周期',validType:'length[1,40]'" class="easyui-numberbox" min="0" max="100000000"   class="easyui-validatebox span2"    style='width: 100%;'>
					</td>
					<td class="form-td-label" ><span style="color: red;">*</span>是否开启：</td>
					<td class="form-td-content" style="width: 160px">
						<select id="add_isopen" name="isopen" class="easyui-combobox" data-options="required:true,prompt:'请选择是否开启'" style="width: 100%;">
						    <option value="0">开启</option>
		  					<option value="1">关闭</option>
						</select>
					</td>
				</tr>
				<tr>
				    <td class="form-td-label" >上次报警时间：</td>
					<td class="form-td-content" style="width: 160px" >
						<input id="add_lastalarmtime" name="lastalarmtime" class="easyui-datetimebox" data-options="prompt:'上次报警时间'" style="width:100%;">
					</td>
					<td class="form-td-label" >下次报警时间：</td>
					<td class="form-td-content" style="width: 160px" >
						<input id="add_nextalarmtime" name="nextalarmtime" class="easyui-datetimebox" data-options="prompt:'下次报警时间'" style="width:100%;">
					</td>
					
				</tr>
				<tr>
					<td class="form-td-label" rowspan="3"><span style="color: red;">*</span>报警条件：</td>
					<td class="form-td-content" width="240px"rowspan="3" colspan="3">
						<input id="add_alarmcondition" name='alarmcondition'  data-options="required:true,prompt:'请输入报警条件',multiline:true" style="width:100%;height:100%;"  class="easyui-textbox"  class="easyui-validatebox span2"  >
					</td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr >
                    <td class="form-td-label">备注：</td>
					<td class="form-td-content"  width="240px" colspan="3">
						<input id="add_remark" name='remark'   data-options="prompt:'请输入备注',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
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
		 style="width:650px;height:350px;padding:5px 5px;" >
        <form id="editForm" method="post" data-options="novalidate:true">
            <input id="id" name="id" type="hidden"/>
			<table class="grid">
				<tr>
					<td class="form-td-label"><span style="color: red;">*</span>报警分类名称：</td>
					<td class="form-td-content" style="width: 160px">
						<input   name='name'   data-options="required:true,prompt:'请输入报警分类名称',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
					<td class="form-td-label"><span style="color: red;">*</span>所属电器系统：</td>
					<td class="form-td-content" style="width: 160px">
						<select name="electricalsystem" class="easyui-combobox" data-options="required:true,prompt:'请选择所属电器系统'" style="width: 100%;">
						    <option value="推进系统">推进系统</option>
						    <option value="导向系统">导向系统</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="form-td-label" ><span style="color: red;">*</span>报警周期：</td>
					<td class="form-td-content" style="width: 160px">
						<input   name='alarmcycle'  data-options="required:true,prompt:'请输入报警周期',validType:'length[1,40]'" class="easyui-numberbox" min="0" max="100000000"   class="easyui-validatebox span2"    style='width: 100%;'>
					</td>
					<td class="form-td-label" ><span style="color: red;">*</span>是否开启：</td>
					<td class="form-td-content" style="width: 160px">
						<select  name="isopen" class="easyui-combobox" data-options="required:true,prompt:'请选择是否开启'" style="width: 100%;">
						    <option value="0">开启</option>
		  					<option value="1">关闭</option>
						</select>
					</td>
				</tr>
				<tr>
				    <td class="form-td-label" >上次报警时间：</td>
					<td class="form-td-content" style="width: 160px" >
						<input   name="lastalarmtime" class="easyui-datetimebox" data-options="prompt:'上次报警时间'" style="width:100%;">
					</td>
					<td class="form-td-label" >下次报警时间：</td>
					<td class="form-td-content" style="width: 160px" >
						<input   name="nextalarmtime" class="easyui-datetimebox" data-options="prompt:'下次报警时间'" style="width:100%;">
					</td>
					
				</tr>
				<tr>
					<td class="form-td-label" rowspan="3"><span style="color: red;">*</span>报警条件：</td>
					<td class="form-td-content" width="240px"rowspan="3" colspan="3">
						<input   name='alarmcondition'  data-options="required:true,prompt:'请输入报警条件',multiline:true" style="width:100%;height:100%;"  class="easyui-textbox"  class="easyui-validatebox span2"  >
					</td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr >
                    <td class="form-td-label">备注：</td>
					<td class="form-td-content"  width="240px" colspan="3">
						<input   name='remark'   data-options="prompt:'请输入备注',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
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
		 style="width:600px;height:300px;padding:5px 5px;" >
        <form id="showForm" method="post" data-options="novalidate:true">
        <input id="id" name="id" type="hidden"/>
        	<table class="grid">
				<tr>
					<td class="form-td-label"><span style="color: red;">*</span>报警分类名称：</td>
					<td class="form-td-content" style="width: 160px">
						<input   name='name'   data-options="required:true,prompt:'请输入报警分类名称',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
					<td class="form-td-label"><span style="color: red;">*</span>所属电器系统：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='electricalsystem'   data-options="required:true,prompt:'',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
				</tr>
				<tr>
					<td class="form-td-label" ><span style="color: red;">*</span>报警周期：</td>
					<td class="form-td-content" style="width: 160px">
						<input   name='alarmcycle'  data-options="required:true,prompt:'请输入报警周期',validType:'length[1,40]'" class="easyui-numberbox" min="0" max="100000000"   class="easyui-validatebox span2"    style='width: 100%;'>
					</td>
					<td class="form-td-label" ><span style="color: red;">*</span>是否开启：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='isopen'   data-options="required:true,prompt:'',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
				</tr>
				<tr>
				    <td class="form-td-label" >上次报警时间：</td>
					<td class="form-td-content" style="width: 160px" >
					    <input name='lastalarmtime'   data-options="required:true,prompt:'',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
					<td class="form-td-label" >下次报警时间：</td>
					<td class="form-td-content" style="width: 160px" >
						<input name='nextalarmtime'   data-options="required:true,prompt:'',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
					
				</tr>
				<tr>
					<td class="form-td-label" rowspan="3"><span style="color: red;">*</span>报警条件：</td>
					<td class="form-td-content" width="240px"rowspan="3" colspan="3">
						<input   name='alarmcondition'  data-options="required:true,prompt:'请输入报警条件',multiline:true" style="width:100%;height:100%;"  class="easyui-textbox"  class="easyui-validatebox span2"  >
					</td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr>
					<td></td>
				</tr>
				<tr >
                    <td class="form-td-label">备注：</td>
					<td class="form-td-content"  width="240px" colspan="3">
						<input   name='remark'   data-options="prompt:'请输入备注',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
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