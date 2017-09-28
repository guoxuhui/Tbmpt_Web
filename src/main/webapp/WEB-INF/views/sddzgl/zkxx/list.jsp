<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>钻孔信息管理</title>
<script type="text/javascript" src="${staticPath }/static/js/sddzgl/zkxx/list.js?v=20170407083513" charset="utf-8"></script>
</head>

<body class="easyui-layout" data-options="fit:true,border:false">
	<!-- 查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:140px;border: false;overflow: hidden;" data-options="collapsible:true">
		<form id="searchForm">
		<table class="grid" style="border: 0px">
				<tr>
					<td class="form-td-label" style="width: 80px">工程名称:</td>
					<td class="form-td-content" colspan="3" style="width: 480px">
						<select editable="false" id="gcbh" name="xmId" class="easyui-combobox" data-options="prompt:'请输入工程名称'" style="width: 100%;"></select>
					</td>
				</tr>
				<tr>
					<td class="form-td-label" style="width: 80px">区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间:</td>
					<td class="form-td-content" style="width: 200px">
						<select editable="false" id="qjbh" name="qjId" class="easyui-combobox" data-options="prompt:'请输入区间'" style="width: 100%;"></select>
					</td>
					<td class="form-td-label" style="width: 80px">线&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;路:</td>
					<td class="form-td-content" style="width: 200px">
						<select editable="false" id="xlbh" name="xlId" class="easyui-combobox" data-options="prompt:'请输入线路'" style="width: 100%;"></select>
					</td>
				</tr>
				<tr>
					<td class="form-td-label" style="width: 80px">钻孔编号：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='zkbh'  id='search_zkbh' data-options="prompt:'请输入地层名称',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
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
    	
    	<!-- 
    	<shiro:hasPermission name="sddzgl/zkxx/add">
    		<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="sddzgl/zkxx/edit">
    		<a onclick="editFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="sddzgl/zkxx/del">
    		<a onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="sddzgl/zkxx/sbInfo">
  	        <a onclick="showFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-book_previous'">查看</a>
    	</shiro:hasPermission>
    	 <shiro:hasPermission name="sddzgl/zkxx/ExpExcel">  
    	    <a onclick="expXls();" href="javascript:void(0);"   class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
    	</shiro:hasPermission> 
   	    <shiro:hasPermission name="sddzgl/zkxx/ExpPdf">  
    	    <a onclick="expPdf();" href="javascript:void(0);"   class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_white_acrobat'">导出Pdf</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="sddzgl/zkxx/imp">
			<a onclick="impFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">Excel导入</a>
    	</shiro:hasPermission> 
    	  -->
    	  
    	<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	<a onclick="editFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	<a onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	<a onclick="showFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-book_previous'">查看</a>
    	<a onclick="expXls();" href="javascript:void(0);"   class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
    	<a onclick="expPdf();" href="javascript:void(0);"   class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_white_acrobat'">导出Pdf</a>
    	<a onclick="impFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">Excel导入</a>
    	 
   	</div>
    	 
    	 
	<!-- 弹出框－新增 -->
	<div id="addDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:650px;height:350px;padding:5px 5px;" >
        <form id="addForm" method="post" data-options="novalidate:true">
			<table class="grid">
                <tr>
					<td class="form-td-label" style="width: 80px"><span style="color: red;">*</span>工程名称:</td>
					<td class="form-td-content" colspan="3" style="width: 480px">
						<select editable="false" id="add_xmId" name="xmId" required="required" class="easyui-combobox" data-options="prompt:'请输入工程名称'" style="width: 100%;"></select>
					</td>
				</tr>
				<tr>
					<td class="form-td-label" style="width: 80px"><span style="color: red;">*</span>区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间:</td>
					<td class="form-td-content" style="width: 200px">
						<select editable="false" id="add_qjId" name="qjId"required="required" class="easyui-combobox" data-options="prompt:'请输入区间'" style="width: 100%;"></select>
					</td>
					<td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>线&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;路:</td>
					<td class="form-td-content" style="width: 200px">
						<select editable="false" id="add_xlId" name="xlId"required="required" class="easyui-combobox" data-options="prompt:'请输入线路'" style="width: 100%;"></select>
					</td>
				</tr>
				<tr>
					<td class="form-td-label"><span style="color: red;">*</span>钻孔编号：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='zkbh'  id='add_zkbh'required="required" data-options="prompt:'请输入钻孔编号',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
					<td class="form-td-label">钻孔施工单位：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='zksgdw'  id='add_zksgdw' data-options="prompt:'请输入钻孔施工单位',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
				</tr>
				<tr>
					<td class="form-td-label" >钻孔类型：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='zklx'  id='add_zklx' data-options="prompt:'请输入钻孔类型',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
					<td class="form-td-label" >钻孔位置(X)：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='zkwzX'  id='add_zkwzX' data-options="prompt:'请输入钻孔位置(X)',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
				</tr>
				<tr>
					<td class="form-td-label" >钻孔位置(Y)：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='zkwzY'  id='add_zkwzY' data-options="prompt:'请输入钻孔位置(Y)',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
					<td class="form-td-label" >对应环号：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='dyhh'  id='add_dyhh' data-options="prompt:'请输入对应环号',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
				</tr>
				<tr>
					<td class="form-td-label" >钻孔里程：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='zklc'  id='add_zklc' data-options="prompt:'请输入钻孔里程',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
				</tr>
				<tr >
                    <td class="form-td-label">备注：</td>
					<td class="form-td-content"  width="240px" colspan="3">
						<input name='bz'  id='add_bz' data-options="prompt:'请输入地层描述',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
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
					<td class="form-td-label" style="width: 80px"><span style="color: red;">*</span>工程名称:</td>
					<td class="form-td-content" colspan="3" style="width: 480px">
						<select editable="false" id="edit_xmId" name="xmId" required="required" class="easyui-combobox" data-options="prompt:'请输入工程名称'" style="width: 100%;"></select>
					</td>
				</tr>
				<tr>
					<td class="form-td-label" style="width: 80px"><span style="color: red;">*</span>区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间:</td>
					<td class="form-td-content" style="width: 200px">
						<select editable="false" id="edit_qjId" name="qjId" required="required" class="easyui-combobox" data-options="prompt:'请输入区间'" style="width: 100%;"></select>
					</td>
					<td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>线&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;路:</td>
					<td class="form-td-content" style="width: 200px">
						<select editable="false" id="edit_xlId" name="xlId" required="required" class="easyui-combobox" data-options="prompt:'请输入线路'" style="width: 100%;"></select>
					</td>
				</tr>
				<tr>
					<td class="form-td-label"><span style="color: red;">*</span>钻孔编号：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='zkbh'  id='edit_zkbh'required="required" data-options="prompt:'请输入钻孔编号',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
					<td class="form-td-label">钻孔施工单位：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='zksgdw'  id='edit_zksgdw' data-options="prompt:'请输入钻孔施工单位',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
				</tr>
				<tr>
					<td class="form-td-label" >钻孔类型：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='zklx'  id='edit_zklx' data-options="prompt:'请输入钻孔类型',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
					<td class="form-td-label" >钻孔位置(X)：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='zkwzX'  id='edit_zkwzX' data-options="prompt:'请输入钻孔位置(X)',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
				</tr>
				<tr>
					<td class="form-td-label" >钻孔位置(Y)：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='zkwzY'  id='edit_zkwzY' data-options="prompt:'请输入钻孔位置(Y)',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
					<td class="form-td-label" >对应环号：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='dyhh'  id='edit_dyhh' data-options="prompt:'请输入对应环号',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
				</tr>
				<tr>
					<td class="form-td-label" >钻孔里程：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='zklc'  id='edit_zklc' data-options="prompt:'请输入钻孔里程',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
				</tr>
				<tr >
                    <td class="form-td-label">备注：</td>
					<td class="form-td-content"  width="240px" colspan="3">
						<input name='bz'  id='edit_bz' data-options="prompt:'请输入地层描述',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
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
		 style="width:650px;height:350px;padding:5px 5px;" >
        <form id="showForm" method="post" data-options="novalidate:true">
        <input id="id" name="id" type="hidden"/>
        <table class="grid">
                <tr>
					<td class="form-td-label" style="width: 80px">工程名称:</td>
					<td class="form-td-content" colspan="3" style="width: 480px">
						<input name='xmMc'  id='show_xmMc'required="required" readonly="readonly" data-options="prompt:'',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
				</tr>
				<tr>
					<td class="form-td-label" style="width: 80px">区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间:</td>
					<td class="form-td-content" style="width: 200px">
						<input name='qjMc'  id='show_qjMc'required="required" readonly="readonly" data-options="prompt:'',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
					<td class="form-td-label" style="width: 100px">线&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;路:</td>
					<td class="form-td-content" style="width: 200px">
						<input name='xlMc'  id='show_xlMc'required="required" readonly="readonly" data-options="prompt:'',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
				</tr>
				<tr>
					<td class="form-td-label"><span style="color: red;">*</span>钻孔编号：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='zkbh'  id='show_zkbh'required="required" readonly="readonly" data-options="prompt:'请输入钻孔编号',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
					<td class="form-td-label">钻孔施工单位：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='zksgdw'  id='show_zksgdw' readonly="readonly" data-options="prompt:'请输入钻孔施工单位',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
				</tr>
				<tr>
					<td class="form-td-label" >钻孔类型：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='zklx'  id='show_zklx' readonly="readonly" data-options="prompt:'请输入钻孔类型',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
					<td class="form-td-label" >钻孔位置(X)：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='zkwzX'  id='show_zkwzX' readonly="readonly" data-options="prompt:'请输入钻孔位置(X)',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
				</tr>
				<tr>
					<td class="form-td-label" >钻孔位置(Y)：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='zkwzY'  id='show_zkwzY' readonly="readonly" data-options="prompt:'请输入钻孔位置(Y)',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
					<td class="form-td-label" >对应环号：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='dyhh'  id='show_dyhh' readonly="readonly" data-options="prompt:'请输入对应环号',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
				</tr>
				<tr>
					<td class="form-td-label" >钻孔里程：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='zklc'  id='show_zklc' readonly="readonly" data-options="prompt:'请输入钻孔里程',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
				</tr>
				<tr >
                    <td class="form-td-label">备注：</td>
					<td class="form-td-content"  width="240px" colspan="3">
						<input name='bz'  id='show_bz' readonly="readonly" data-options="prompt:'请输入地层描述',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
                </tr>
        	</table>
        </form>
	</div>
	<div id="show-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#showDialog').dialog('close')">关闭</a>
	</div>
	
	<!-- 弹出框－导入窗口 -->
	<div id="impDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#imp-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:560px;height:200px;padding:5px 5px;" >
        <form  id="impForm" method="post" enctype="multipart/form-data">
        <input type="hidden" id="filename" />
            <table class="grid">
                <tr>
                    <td class="form-td-label" ><span style="color: red;">*</span>导入文件路径：</td>
                    <td style="width:400px;">
                        <input id="file" name="file" colspan="3" accept=".xls,.xlsx" class="easyui-filebox" data-options="buttonText:'选择文件',required:true,prompt:'选择文件'" style="width:100%">
                    </td>
                </tr>
                <tr>
                	<td class="form-td-label" >Excel模板地址：</td>
                	<td>
                	    <%-- <a href="${path }/sddzgl/zkxx/excelTemplate">点击下载模板</a> --%>
                		<a href="${path }/sddzgl/zkxx/downLoadModel">点击下载模板</a>
                	</td>
                </tr>
                <tr>
                	<td  colspan="2">
                	    <span >
		                	<font color='red'>
		                		备注：请严格按照模板格式录入数据，每行数据之间不得存在空行，模板外部不得编辑
		                		<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                		或增添任何文字或图片，模板中有“ * ”标注的列为必填项；
		                		
		                	</font>
	                	</span>
                	</td>
                </tr>
            </table>    
        </form>
    </div>
    <div id="imp-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="impAjax()">确定</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#impDialog').dialog('close')">关闭</a>
	</div>

   
</body>
</html>