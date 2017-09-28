<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/commons/basejs.jsp" %>
	<meta http-equiv="X-UA-Compatible" content="edge" />
	<title>验收影像资料管理</title>
	<script type="text/javascript" src="${staticPath}/static/js//gczlys/YanShouYingXiang.js" charset="utf-8"></script>
	<style>
		.div-a{ float:left;width:41%;}
		.div-b{ float:left;width:41%;}
		.div-c{ float:left;width:14%;height:250px;overflow-y:scroll;}
	</style>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
<jsp:include  page="../../sys/base/uploadPicture.jsp" />
<!-- 【验收影像资料管理】查询表单 -->
<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:105px;border: false;overflow: hidden;" data-options="collapsible:true" >
	<form id="searchForm_YanShouYingXiang" style="display:none">
		<table class="grid" border='0' cellspacing='0' style='width:90%;padding:5px'>
			<tr>
				<td class="form-td-label" style="width: 180px">检查内容及验收情况：</td>
				<td class="form-td-content" style="width: 120px">
					<input name="miaoshu" class="easyui-textbox" data-options="prompt:'请输入检查内容及验收情况'" id = "gcName_query_id" style="width: 180px;"  />
				</td>

			</tr>
			<tr>

				<td class="form-td-label" style="width: 120px">验收时间：</td>
				<td align="left" width="3200px" colspan="3">
					<input   class="easyui-datetimebox"  name='startYanshousj'   style='width: 149px' data-options="prompt:'请输入验收时间'" > ---
					<input   class="easyui-datetimebox"  name='endYanshousj'   style='width: 149px' data-options="prompt:'请输入验收时间'" >
				</td>
				<td align="right" width="250px" >
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchYanShouYingXiangFun();">查询</a>
					<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleandYanShouYingXiang();">重置</a>
				</td>
			</tr>
		</table>
	</form>
</div>

<!-- 【施工内容管理】结果列表 -->
<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
	<table id="dataGrid_YanShouYingXiang" title="列表" data-options="fit:true,border:false"></table>
</div>
<div id="toolbar" style="display: none;">
	<a onclick="reloadYanShouYingXiang();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>

	<shiro:hasPermission name="/gczlys/yanShouYingXiang/add">
		<a onclick="addYanShouYingXiangFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
	</shiro:hasPermission>

	<shiro:hasPermission name="/gczlys/yanShouYingXiang/edit">
		<a onclick="editYanShouYingXiangFun();" id="edit_button_YanShouYingXiang" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
	</shiro:hasPermission>

	<shiro:hasPermission name="/gczlys/yanShouYingXiang/delete">
		<a onclick="deleteYanShouYingXiangFun();" href="javascript:void(0);" id="del_button_YanShouYingXiang" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
	</shiro:hasPermission>

	<shiro:hasPermission name="/gczlys/yanShouYingXiang/info">
		<a onclick="showFun();" href="javascript:void(0);" id="show_button_YanShouYingXiang" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-book_previous'">查看</a>
	</shiro:hasPermission>

	<a onclick="clearYanShouYingXiangSelections();" href="javascript:void(0);" id="cancelSelect_button_YanShouYingXiang" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>

	<shiro:hasPermission name="/gczlys/yanShouYingXiang/copy">
		<a onclick="copyYanShouYingXiangFun();" href="javascript:void(0);" id="copy_button_YanShouYingXiang" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-book_previous'">复制</a>
	</shiro:hasPermission>

	<%--<shiro:hasPermission name="/gczlys/yanShouYingXiang/addImg">--%>
		<a onclick="myUploadPicture();" href="javascript:void(0);" id="uploadPic_button_yxzl" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-20130406125647919_easyicon_net_16'">上传图片</a>
	<%--</shiro:hasPermission>--%>
</div>

<!-- 【盾构施工质量巡检信息】新增弹出框-->
<div id="addDialog_YanShouYingXiang" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache:false,modal:true"
	 style="width:580px;height:480px;padding:10px 10px;display:none;" >
	<form id="addForm_YanShouYingXiang" method="post" data-options="novalidate:true">
		<table  class="grid" style="width:530px">
			<tr>
				<td  class="form-td-label" width="100px"><span style="color: red;">*</span>工程名称：</td>
				<td class="form-td-content" width="141px" colspan="3">
					<input class="easyui-combobox" class="easyui-validatebox span2" name='projectid' id='add_gcid_YanShouYingXiang'  data-options="prompt:'请输入工程部位',validType:'length[1,40]',required:true"  style='width: 350px'>
					<input type="hidden" name="projectname" id="add_gcname_YanShouYingXiang"/>
				</td>
			</tr>
			<tr>
				<td  class="form-td-label"><span style="color: red;">*</span>天气：</td>
				<td class="form-td-content" width="141px">
					<select class="easyui-combobox" class="easyui-validatebox span2"  name='tianqi' id='add_tq_YanShouYingXiang'  data-options="prompt:'请选择点位',required:true"  style='width: 150px'>
						<option value="1">晴</option>
						<option value="2">阴</option>
						<option value="3">雨</option>
						<option value="4">雪</option>
					</select>
				</td>
			</tr>
			<tr>
				<td  class="form-td-label" width="100px" ><span style="color: red;">*</span>工程部位：</td>
				<td class="form-td-content">
					<input class="easyui-textbox" class="easyui-validatebox span2"  name='gcbuwei' id='add_gcbw_YanShouYingXiang'  data-options="prompt:'请输入工程部位',validType:'length[1,40]',required:true"  style='width: 150px'>
				</td>
				<td  class="form-td-label" width="180px"><span style="color: red;">*</span>分布分项工序名称：</td>
				<td class="form-td-content" width="120px">
					<input class="easyui-textbox" class="easyui-validatebox span2"  name='gongxu' id='add_fbfx_YanShouYingXiang'  data-options="prompt:'请输入分布分项工序名称',validType:'length[1,40]',required:true"  style='width: 150px'>
				</td>
			</tr>
			<tr>
				<td  class="form-td-label" width="100px"><span style="color: red;">*</span>施工班组：</td>
				<td class="form-td-content" >
					<input class="easyui-textbox"   name='banzu' id='add_sgbz_YanShouYingXiang'  data-options="prompt:'请输入施工班组',validType:'length[1,40]',required:true"  style='width: 150px'>
				</td>
				<td  class="form-td-label" width="100px"><span style="color: red;">*</span>班组负责人：</td>
				<td class="form-td-content" width="141px">
					<input class="easyui-textbox"   name='bzfuzr' id='add_bzfzr_YanShouYingXiang'  data-options="prompt:'请输入施工班组',validType:'length[1,40]',required:true"  style='width: 150px'>
				</td>
			</tr>
			<tr>
				<td  class="form-td-label" colspan="4" style="text-align:left;"><span style="color: red;">*</span>现场检查实际情况(写出各检查主要参数)：</td>
			</tr>
			<tr>
				<td class="form-td-content" colspan="4">
					<input class="easyui-textbox"    name='miaoshu' id='add_xcjc_YanShouYingXiang'  data-options="prompt:'填写现场检查实际情况(写出各检查主要参数)',validType:'length[1,40]',required:true,multiline:true"  style='width: 100%;height:60px'>
				</td>
			</tr>
			<tr>
				<td  class="form-td-label" colspan="4" style="text-align:left;">检查验收意见和结论：</td>
			</tr>
			<tr>
				<td class="form-td-content" colspan="4">
					<input class="easyui-textbox"    name='jianchaqk' id='add_jcjl_YanShouYingXiang'  data-options="prompt:'填写现场检查实际情况(写出各检查主要参数)',validType:'length[1,40]',multiline:true"  style='width: 100%;height:60px'>
				</td>
			</tr>
			<tr>
				<td  class="form-td-label" width="100px" ><span style="color: red;">*</span>验收人：</td>
				<td class="form-td-content" >
					<input class="easyui-textbox" class="easyui-validatebox span2"  name='yanshour' id='add_ysr_YanShouYingXiang'  data-options="prompt:'请输入验收人',validType:'length[1,40]',required:true"  style='width: 150px'>
				</td>
				<td  class="form-td-label" ><span style="color: red;">*</span>验收时间：</td>
				<td class="form-td-content" colspan="3">
					<input class="easyui-datetimebox" class="easyui-validatebox span2" name='yanshousj'  id='add_yssj_gczlYdxjGPZLXJInfo' data-options="prompt:'请输入验收时间',required:true" style='width: 150px'>
				</td>
			</tr>

		</table>
	</form>
</div>
<div id="add-dialog-buttons" style="display:none">
	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="addYanShouYingXiangAjax()">保存</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#addDialog_YanShouYingXiang').dialog('close')">关闭</a>
</div>


<!-- 【验收影像资料信息】编辑弹出框-->
<div id="editDialog_YanShouYingXiang" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache:false,modal:true"
	 style="width:580px;height:480px;padding:10px 10px;display:none;" >
	<form id="editForm_YanShouYingXiang" method="post" data-options="novalidate:true">
		<input type="hidden" name="id"/>
		<input type="hidden" name="updateTime"/>
		<table  class="grid" style="width: 530px">
			<tr>
				<td  class="form-td-label" width="100px"><span style="color: red;">*</span>工程名称：</td>
				<td class="form-td-content" colspan="3">
					<input class="easyui-textbox" class="easyui-validatebox span2"  name='projectid' id='edit_gcid_YanShouYingXiang'  data-options="prompt:'请输入工程部位',validType:'length[1,40]',required:true"  style='width: 350px'>
					<input type="hidden"  name="projectname" id="edit_gcname_YanShouYingXiang"/>
				</td>
			</tr>
			<tr>
				<td  class="form-td-label"width="100px"><span style="color: red;">*</span>天气：</td>
				<td class="form-td-content" width="141px">
					<select class="easyui-combobox" class="easyui-validatebox span2"  name='tianqi' id='edit_tq_YanShouYingXiang'  data-options="prompt:'请选择点位',required:true"  style='width: 150px'>
						<option value="1">晴</option>
						<option value="2">阴</option>
						<option value="3">雨</option>
						<option value="4">雪</option>
					</select>
				</td>
			</tr>
			<tr>
				<td  class="form-td-label" width="100px"><span style="color: red;">*</span>工程部位：</td>
				<td class="form-td-content" >
					<input class="easyui-textbox" class="easyui-validatebox span2"  name='gcbuwei' id='edit_gcbw_YanShouYingXiang'  data-options="prompt:'请输入工程部位',validType:'length[1,40]',required:true"  style='width: 150px'>
				</td>
				<td  class="form-td-label" width="180px"><span style="color: red;">*</span>分布分项工序名称：</td>
				<td class="form-td-content" width="141px">
					<input class="easyui-textbox" class="easyui-validatebox span2"  name='gongxu' id='edit_fbfx_YanShouYingXiang'  data-options="prompt:'请输入分布分项工序名称',validType:'length[1,40]',required:true"  style='width: 150px'>
				</td>
			</tr>
			<tr>
				<td  class="form-td-label" width="100px"><span style="color: red;">*</span>施工班组：</td>
				<td class="form-td-content" >
					<input class="easyui-textbox" class="easyui-validatebox span2"  name='banzu' id='edit_sgbz_YanShouYingXiang'  data-options="prompt:'请输入施工班组',validType:'length[1,40]',required:true"  style='width: 150px'>
				</td>
				<td  class="form-td-label" width="100px"><span style="color: red;">*</span>班组负责人：</td>
				<td class="form-td-content" width="141px">
					<input class="easyui-textbox" class="easyui-validatebox span2"  name='bzfuzr' id='edit_bzfzr_YanShouYingXiang'  data-options="prompt:'请输入施工班组',validType:'length[1,40]',required:true"  style='width: 150px'>
				</td>
			</tr>
			<tr>
				<td  class="form-td-label" colspan="4" style="text-align:left;"><span style="color: red;">*</span>现场检查实际情况(写出各检查主要参数)：</td>
			</tr>
			<tr>
				<td class="form-td-content" colspan="4">
					<input class="easyui-textbox"    name='miaoshu' id='edit_xcjc_YanShouYingXiang'  data-options="prompt:'填写现场检查实际情况(写出各检查主要参数)',validType:'length[1,50]',required:true,multiline:true"  style='width: 100%;height:60px'>
				</td>
			</tr>
			<tr>
				<td  class="form-td-label" colspan="4" style="text-align:left;">检查验收意见和结论：</td>
			</tr>
			<tr>
				<td class="form-td-content" colspan="4">
					<input class="easyui-textbox"   name='jianchaqk' id='edit_jcjl_YanShouYingXiang'  data-options="prompt:'填写现场检查实际情况(写出各检查主要参数)',validType:'length[1,50]',multiline:true"  style='width: 100%;height:60px'>
				</td>
			</tr>
			<tr>
				<td  class="form-td-label" width="100px"><span style="color: red;">*</span>验收人：</td>
				<td class="form-td-content" >
					<input class="easyui-textbox" class="easyui-validatebox span2"  name='yanshour' id='edit_ysr_YanShouYingXiang'  data-options="prompt:'请输入验收人',validType:'length[1,40]',required:true"  style='width: 150px'>
				</td>
				<td  class="form-td-label" ><span style="color: red;">*</span>验收时间：</td>
				<td class="form-td-content" colspan="3">
					<input class="easyui-datetimebox" class="easyui-validatebox span2" name='yanshousj'  id='edit_yssj_gczlYdxjGPZLXJInfo' data-options="prompt:'请输入验收时间',required:true" style='width: 150px'>
				</td>
			</tr>

		</table>
	</form>
</div>
<div id="add-dialog-buttons" style="display:none">
	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="editYanShouYingXiangAjax()">保存</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#editDialog_YanShouYingXiang').dialog('close')">关闭</a>
</div>

<!-- 【盾构施工质量巡检信息】复制弹出框-->
<div id="copyDialog_YanShouYingXiang" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache:false,modal:true"
	 style="width:580px;height:480px;padding:10px 10px;display:none;" >
	<form id="copyForm_yanshouyingxiang" method="post" data-options="novalidate:true">
		<table  class="grid" style="width: 530px">
			<tr>
				<td  class="form-td-label" width="100px"><span style="color: red;">*</span>工程名称：</td>
				<td class="form-td-content"  colspan="3">
					<input class="easyui-textbox" class="easyui-validatebox span2"  name='projectid' id='copy_gcid_YanShouYingXiang'  data-options="prompt:'请输入工程部位',validType:'length[1,40]',required:true"  style='width: 350px'>
					<input type="hidden" name="projectname" id="copy_gcname_YanShouYingXiang"/>
				</td>
			</tr>
			<tr>
				<td  class="form-td-label" ><span style="color: red;">*</span>天气：</td>
				<td class="form-td-content" width="141px">
					<select class="easyui-combobox" class="easyui-validatebox span2"  name='tianqi' id='copy_tq_YanShouYingXiang'  data-options="prompt:'请选择点位',required:true"  style='width: 150px'>
						<option value="1">晴</option>
						<option value="2">阴</option>
						<option value="3">雨</option>
						<option value="4">雪</option>
					</select>
				</td>
			</tr>
			<tr>
				<td  class="form-td-label"  width="100px"><span style="color: red;">*</span>工程部位：</td>
				<td class="form-td-content" >
					<input class="easyui-textbox" class="easyui-validatebox span2"  name='gcbuwei' id='copy_gcbw_YanShouYingXiang'  data-options="prompt:'请输入工程部位',validType:'length[1,40]',required:true"  style='width: 150px'>
				</td>
				<td  class="form-td-label"  width="180px"><span style="color: red;">*</span>分布分项工序名称：</td>
				<td class="form-td-content" width="141px">
					<input class="easyui-textbox" class="easyui-validatebox span2"  name='gongxu' id='copy_fbfx_YanShouYingXiang'  data-options="prompt:'请输入分布分项工序名称',validType:'length[1,40]',required:true"  style='width: 150px'>
				</td>
			</tr>
			<tr>
				<td  class="form-td-label"  width="100px" ><span style="color: red;">*</span>施工班组：</td>
				<td class="form-td-content" >
					<input class="easyui-textbox" class="easyui-validatebox span2"  name='banzu' id='copy_sgbz_YanShouYingXiang'  data-options="prompt:'请输入施工班组',validType:'length[1,40]',required:true"  style='width: 150px'>
				</td>
				<td  class="form-td-label" ><span style="color: red;">*</span>班组负责人：</td>
				<td class="form-td-content" width="141px">
					<input class="easyui-textbox" class="easyui-validatebox span2"  name='bzfuzr' id='copy_bzfzr_YanShouYingXiang'  data-options="prompt:'请输入施工班组',validType:'length[1,40]',required:true"  style='width: 150px'>
				</td>
			</tr>
			<tr>
				<td  class="form-td-label" colspan="4" style="text-align:left;"><span style="color: red;">*</span>现场检查实际情况(写出各检查主要参数)：</td>
			</tr>
			<tr>
				<td class="form-td-content" colspan="4">
					<input class="easyui-textbox"    name='miaoshu' id='copy_xcjc_YanShouYingXiang'  data-options="prompt:'填写现场检查实际情况(写出各检查主要参数)',validType:'length[1,40]',required:true,multiline:true"  style='width: 100%;height:60px'>
				</td>
			</tr>
			<tr>
				<td  class="form-td-label" colspan="4" style="text-align:left;">检查验收意见和结论：</td>
			</tr>
			<tr>
				<td class="form-td-content" colspan="4">
					<input class="easyui-textbox"   name='jianchaqk' id='copy_jcjl_YanShouYingXiang'  data-options="prompt:'填写现场检查实际情况(写出各检查主要参数)',validType:'length[1,40]',multiline:true"  style='width: 100%;height:60px'>
				</td>
			</tr>
			<tr>
				<td  class="form-td-label"  width="100px"><span style="color: red;">*</span>验收人：</td>
				<td class="form-td-content" >
					<input class="easyui-textbox" class="easyui-validatebox span2"  name='yanshour' id='copy_ysr_YanShouYingXiang'  data-options="prompt:'请输入验收人',validType:'length[1,40]',required:true"  style='width: 150px'>
				</td>
				<td  class="form-td-label" ><span style="color: red;">*</span>验收时间：</td>
				<td class="form-td-content" colspan="3">
					<input class="easyui-datetimebox" class="easyui-validatebox span2" name='yanshousj'  id='copy_yssj_gczlYdxjGPZLXJInfo' data-options="prompt:'请输入验收时间',required:true" style='width: 150px'>
				</td>
			</tr>

		</table>
	</form>
</div>
<div id="add-dialog-buttons" style="display:none">
	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="copyYanShouYingXiangAjax()">保存</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#copyDialog_YanShouYingXiang').dialog('close')">关闭</a>
</div>


<!-- 【盾构施工质量巡检信息】查看-->
<div id="showDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#show-dialog-buttons',closed:true,cache:false,modal:true"
	 style="width:75%;height:380px;padding:10px 10px;display:none;" >

	<div class="div-a">
		<div>
			<span style="color: red;">*</span>工程名称：<input class="easyui-textbox" class="easyui-validatebox span2"  name='projectname'   style='width: 230px'>
		</div>
		<div>
			<span style="color: red;">*</span>工程部位：<input class="easyui-textbox" class="easyui-validatebox span2"  name='gcbuwei'   style='width: 230px'>
		</div>
		<div>
			<span style="color: red;">*</span>施工班组：<input class="easyui-textbox" class="easyui-validatebox span2"  name='banzu'   style='width: 230px'>
		</div>
		<div>
			<div><span style="color: red;">*</span>现场检查实际情况(写出各检查主要参数)：</div>
			<div>
				<input class="easyui-textbox" name='miaoshu'  data-options="multiline:true"  style="width:90%;height:150px">
			</div>
		</div>
	</div>
	<div class="div-b">
		<div>
			<div style="float: left;width:120px;text-align: right;">天气：</div>
			<div style="float: left;">
				<select class="easyui-combobox" class="easyui-validatebox span2"  name='tianqi' id='show_tq_YanShouYingXiang'   style='width: 150px'>
					<option value="1">晴</option>
					<option value="2">阴</option>
					<option value="3">雨</option>
					<option value="4">雪</option>
				</select>
			</div>
		</div>
		<div style="clear:both;">
			<div style="float: left;width:120px;text-align: right;"><span style="color: red;">*</span>分布分项工序名称：</div>
			<div style="float: left">
				<input class="easyui-textbox" class="easyui-validatebox span2"  name='gongxu'   style='width: 150px'>
			</div>
		</div>
		<div style="clear:both;">
			<div style="float: left;width:120px;text-align: right;"><span style="color: red;">*</span>班组负责人：</div>
			<div style="float: left">
				<input class="easyui-textbox" class="easyui-validatebox span2"  name='bzfuzr' style='width: 150px'>
			</div>
		</div>
		<div style="clear:both;">
			<div>检查验收意见和结论：</div>
			<div>
				<input class="easyui-textbox" name='jianchaqk'  data-options="multiline:true"  style="width:90%;height:150px">
			</div>
		</div>
	</div>
	<div id="img_div_yxYszlYs" class="div-c" ></div>
</div>

<div id="show-dialog-buttons" style="display:none">
	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#showDialog').dialog('close')">关闭</a>
</div>

</body>
</html>