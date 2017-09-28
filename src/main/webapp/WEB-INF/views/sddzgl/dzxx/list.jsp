<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>地质信息管理</title>
<script type="text/javascript" src="${staticPath }/static/js/sddzgl/dzxx/list.js?v=20170407083513" charset="utf-8"></script>
</head>

<body class="easyui-layout" data-options="fit:true,border:false">
	<!-- 查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:100px;border: false;overflow: hidden;" data-options="collapsible:true">
		<form id="searchForm">
			<table class="grid">
				<tr>
				    <td class="form-td-label" style="width: 80px">钻孔编号：</td>
					<td class="form-td-content"  width="240px"colspan="3">
						<input name='zkBh'  id='search_zkBh' data-options="prompt:'请输入钻孔编号',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
				</tr>
				<tr>
					<td class="form-td-label" style="width: 80px">岩土名称：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='ytmc'  id='search_ytmc' data-options="prompt:'请输入地层名称',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
					<td style="text-align: right;" width="170px" colspan="2">
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
    	<%-- <shiro:hasPermission name="/sddzgl/dzxx/add">
    		<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/sddzgl/dzxx/edit">
    		<a onclick="editFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/sddzgl/dzxx/del">
    		<a onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/sddzgl/dzxx/sbInfo">
  	        <a onclick="showFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-book_previous'">查看</a>
    	</shiro:hasPermission>
    	 <shiro:hasPermission name="/sddzgl/dzxx/ExpExcel">  
    	    <a onclick="expXls();" href="javascript:void(0);"   class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
    	</shiro:hasPermission> 
   	    <shiro:hasPermission name="/sddzgl/dzxx/ExpPdf">  
    	    <a onclick="expPdf();" href="javascript:void(0);"   class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_white_acrobat'">导出Pdf</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/sddzgl/dzxx/imp">
			<a onclick="impFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">Excel导入</a>
    	</shiro:hasPermission> --%>
    	
    	
    	
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
		 style="width:600px;height:320px;padding:5px 5px;" >
        <form id="addForm" method="post" data-options="novalidate:true">
			<table class="grid">
                <tr>
                    <td class="form-td-label" style="width: 90px"><span style="color: red;">*</span>所属钻孔：</td>
					<td class="form-td-content" >
						<select name="zkId"  id="add_zkId"required="required" class="easyui-combobox" data-options="prompt:'请选择所属钻孔'" style="width: 100%;"></select>
					</td>
                    <td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>岩土名称：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='ytmc'  id='add_ytmc'required="required" data-options="prompt:'请输入岩土名称',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width: 80px">岩土编号：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='ytbh'  id='add_ytbh' data-options="prompt:'请输入岩土编号',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
					<td class="form-td-label" style="width: 80px">承载力特征值：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='czltzz'  id='add_czltzz' data-options="prompt:'请输入承载力特征值',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width: 80px">压缩模量：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='ysml'  id='add_ysml' data-options="prompt:'请输入压缩模量',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
					<td class="form-td-label" style="width: 80px">岩土施工分级：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='ytsgfc'  id='add_ytsgfc' data-options="prompt:'请输入岩土施工分级',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width: 80px">地层埋深：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='tcms'  id='add_tcms' data-options="prompt:'请输入地层埋深',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
                </tr>
                <tr >
                    <td class="form-td-label" style="width: 80px">地层描述：</td>
					<td class="form-td-content"  width="240px" colspan="3">
						<input name='dcms'  id='add_dcms' data-options="prompt:'请输入地层描述',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
                </tr>
                <tr >
                    <td class="form-td-label" style="width: 80px">备注：</td>
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
		 style="width:600px;height:320px;padding:5px 5px;" >
        <form id="editForm" method="post" data-options="novalidate:true">
        <input id="id" name="id" type="hidden"/>
        <table class="grid">
                <tr>
                    <td class="form-td-label" style="width: 90px"><span style="color: red;">*</span>所属钻孔：</td>
					<td class="form-td-content" >
						<select name="zkId"  id="edit_zkId"required="required" class="easyui-combobox" data-options="prompt:'请选择所属钻孔'" style="width: 100%;"></select>
					</td>
                    <td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>岩土名称：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='ytmc'  id='edit_ytmc'required="required" data-options="prompt:'请输入岩土名称',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width: 80px">岩土编号：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='ytbh'  id='edit_ytbh' data-options="prompt:'请输入岩土编号',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
					<td class="form-td-label" style="width: 80px">承载力特征值：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='czltzz'  id='edit_czltzz' data-options="prompt:'请输入承载力特征值',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width: 80px">压缩模量：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='ysml'  id='edit_ysml' data-options="prompt:'请输入压缩模量',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
					<td class="form-td-label" style="width: 80px">岩土施工分级：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='ytsgfc'  id='edit_ytsgfc' data-options="prompt:'请输入岩土施工分级',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width: 80px">地层埋深：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='tcms'  id='edit_tcms' data-options="prompt:'请输入地层埋深',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
                </tr>
                <tr >
                    <td class="form-td-label" style="width: 80px">地层描述：</td>
					<td class="form-td-content"  width="240px" colspan="3">
						<input name='dcms'  id='edit_dcms' data-options="prompt:'请输入地层描述',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
                </tr>
                <tr >
                    <td class="form-td-label" style="width: 80px">备注：</td>
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
		 style="width:600px;height:320px;padding:5px 5px;" >
        <form id="showForm" method="post" data-options="novalidate:true">
        <input id="id" name="id" type="hidden"/>
        <table class="grid">
                <tr>
                    <td class="form-td-label" style="width: 90px"><span style="color: red;">*</span>所属钻孔：</td>
					<td class="form-td-content" >
						<input name='zkBh'  id='show_zkBh'required="required" readonly="readonly" data-options="prompt:'',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
                    <td class="form-td-label" style="width: 100px"><span style="color: red;">*</span>岩土名称：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='ytmc'  id='show_ytmc'required="required" readonly="readonly" data-options="prompt:'请输入岩土名称',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width: 80px">岩土编号：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='ytbh'  id='show_ytbh' readonly="readonly" data-options="prompt:'请输入岩土编号',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
					<td class="form-td-label" style="width: 80px">承载力特征值：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='czltzz'  id='show_czltzz' readonly="readonly" data-options="prompt:'请输入承载力特征值',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width: 80px">压缩模量：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='ysml'  id='show_ysml' readonly="readonly" data-options="prompt:'请输入压缩模量',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
					<td class="form-td-label" style="width: 80px">岩土施工分级：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='ytsgfc'  id='show_ytsgfc' readonly="readonly" data-options="prompt:'请输入岩土施工分级',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width: 80px">地层埋深：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='tcms'  id='show_tcms' readonly="readonly" data-options="prompt:'请输入地层埋深',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
                </tr>
                <tr >
                    <td class="form-td-label" style="width: 80px">地层描述：</td>
					<td class="form-td-content"  width="240px" colspan="3">
						<input name='dcms'  id='show_dcms' readonly="readonly" data-options="prompt:'请输入地层描述',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
                </tr>
                <tr >
                    <td class="form-td-label" style="width: 80px">备注：</td>
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
                	    <%-- <a href="${path }/sddzgl/dzxx/excelTemplate">点击下载模板</a> --%>
                		<a href="${path }/sddzgl/dzxx/downLoadModel">点击下载模板</a>
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