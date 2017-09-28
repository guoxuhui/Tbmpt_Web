<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>集团角度 主要设备投入情况信息</title>
<script type="text/javascript" src="${staticPath }/static/js/zsjk/jt/zsJkJtZySbTrXx.js" charset="utf-8"></script>
</head>

<body class="easyui-layout" data-options="fit:true,border:false">
	<!-- 查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:100px;border: false;overflow: hidden;" data-options="collapsible:true">
		<form id="searchForm">
			<table class="grid">
				<tr>
					<td class="form-td-label" style="width: 80px">设备类别：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='sbLb'  id='search_sbLb' data-options="prompt:'请输入设备类别',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
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
    	<shiro:hasPermission name="zsjk/jt/zsJkJtZySbTrXx/add">
    		<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="zsjk/jt/zsJkJtZySbTrXx/edit">
    		<a onclick="editFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="zsjk/jt/zsJkJtZySbTrXx/del">
    		<a onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="zsjk/jt/zsJkJtZySbTrXx/sbInfo">
  	        <a onclick="showFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">查看</a>
    	</shiro:hasPermission>
    	 <shiro:hasPermission name="zsjk/jt/zsJkJtZySbTrXx/ExpExcel">  
    	    <a onclick="expXls();" href="javascript:void(0);"   class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
    	</shiro:hasPermission> 
   	    <shiro:hasPermission name="zsjk/jt/zsJkJtZySbTrXx/ExpPdf">  
    	    <a onclick="expPdf();" href="javascript:void(0);"   class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_white_acrobat'">导出Pdf</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="zsjk/jt/zsJkJtZySbTrXx/imp">
			<a onclick="impFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">Excel导入</a>
    	</shiro:hasPermission>
    	
   	</div>
    	 
    	 
	<!-- 弹出框－新增 -->
	<div id="addDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:600px;height:250px;padding:5px 5px;" >
        <form id="addForm" method="post" data-options="novalidate:true">
			<table class="grid">
                
                <tr>
                	<td class="form-td-label"><span style="color: red;">*</span>设备类别：</td>
                	<td class="form-td-content">
                    	<input name='sbLb'  id='add_sbLb'required="required" data-options="prompt:'请输入设备类别',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
                	</td>
                	<td class="form-td-label">租入数量：</td>
                	<td>
				        <input name='zrSl' id='add_zrSl' data-options="prompt:'请输入租入数量',validType:'length[1,40]'" class="easyui-numberbox" min="0" max="100000000"   class="easyui-validatebox span2"    style='width: 100%;'>
	                </td>
                </tr>
                <tr>
                	
                	<td class="form-td-label" style="width: 80px">租出数量：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="add_zcSl" name="zcSl" class="easyui-numberbox" min="0" max="100000000"   class="easyui-validatebox span2"    data-options="editable:true, prompt:'请输入租出数量'" style="width:100%">                    	
                    </td>
                    <td class="form-td-label" style="width: 80px">检修数量：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="add_jxSl" name="jxSl" class="easyui-numberbox" min="0" max="100000000"   class="easyui-validatebox span2"    data-options="editable:true, prompt:'请输入检修数量'" style="width:100%">                    	
                    </td>
                </tr>
                <tr>
                	
                	<td class="form-td-label" style="width: 80px">运行数量：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="add_yxSl" name="yxSl" class="easyui-numberbox" min="0" max="100000000"   class="easyui-validatebox span2"    data-options="editable:true, prompt:'请输入运行数量'" style="width:100%">                    	
                    </td>
                    <td class="form-td-label" style="width: 80px">运输数量：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="add_ysSl" name="ysSl" class="easyui-numberbox" min="0" max="100000000"   class="easyui-validatebox span2"    data-options="editable:true, prompt:'请输入运输数量'" style="width:100%">                    	
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
		 style="width:600px;height:250px;padding:5px 5px;" >
        <form id="editForm" method="post" data-options="novalidate:true">
        <input id="id" name="id" type="hidden"/>
			<table class="grid">
                <tr>
                	<td class="form-td-label"><span style="color: red;">*</span>设备类别：</td>
                	<td class="form-td-content">
                    	<input name='sbLb'  id='edit_sbLb'required="required" data-options="prompt:'请输入设备类别',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
                	</td>
                	<td class="form-td-label">租入数量：</td>
                	<td>
				        <input name='zrSl' id='edit_zrSl' data-options="prompt:'请输入租入数量',validType:'length[1,40]'" class="easyui-numberbox" min="0" max="100000000"   class="easyui-validatebox span2"    style='width: 100%;'>
	                </td>
                </tr>
                <tr>
                	
                	<td class="form-td-label" style="width: 80px">租出数量：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="edit_zcSl" name="zcSl" class="easyui-numberbox" min="0" max="100000000"   class="easyui-validatebox span2"    data-options="editable:true, prompt:'请输入租出数量'" style="width:100%">                    	
                    </td>
                    <td class="form-td-label" style="width: 80px">检修数量：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="edit_jxSl" name="jxSl" class="easyui-numberbox" min="0" max="100000000"   class="easyui-validatebox span2"    data-options="editable:true, prompt:'请输入检修数量'" style="width:100%">                    	
                    </td>
                </tr>
                <tr>
                	
                	<td class="form-td-label" style="width: 80px">运行数量：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="edit_yxSl" name="yxSl" class="easyui-numberbox" min="0" max="100000000"   class="easyui-validatebox span2"    data-options="editable:true, prompt:'请输入运行数量'" style="width:100%">                    	
                    </td>
                    <td class="form-td-label" style="width: 80px">运输数量：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="edit_ysSl" name="ysSl" class="easyui-numberbox" min="0" max="100000000"   class="easyui-validatebox span2"    data-options="editable:true, prompt:'请输入运输数量'" style="width:100%">                    	
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
		 style="width:600px;height:250px;padding:5px 5px;" >
        <form id="showForm" method="post" data-options="novalidate:true">
        <input id="id" name="id" type="hidden"/>
        
			<table class="grid">
                <tr>
                	<td class="form-td-label"><span style="color: red;">*</span>设备类别：</td>
                	<td class="form-td-content">
                    	<input name='sbLb'  id='show_sbLb'required="required" readonly="readonly" data-options="prompt:'请输入设备类别',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
                	</td>
                	<td class="form-td-label">租入数量：</td>
                	<td>
				        <input name='zrSl' id='show_zrSl' data-options="prompt:'',validType:'length[1,40]'" readonly="readonly" class="easyui-numberbox" min="0" max="100000000"   class="easyui-validatebox span2"    style='width: 100%;'>
	                </td>
                </tr>
               <tr>
                	
                	<td class="form-td-label" style="width: 80px">租出数量：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="show_zcSl" name="zcSl" class="easyui-numberbox" min="0" max="100000000"   class="easyui-validatebox span2"    readonly="readonly"  data-options="editable:true,required:true" style="width:100%">                    	
                    </td>
                    <td class="form-td-label" style="width: 80px">检修数量：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="show_jxSl" name="jxSl" class="easyui-numberbox" min="0" max="100000000"   class="easyui-validatebox span2"    readonly="readonly"  data-options="editable:true,required:true" style="width:100%">                    	
                    </td>
                </tr>
                <tr>
                	
                	<td class="form-td-label" style="width: 80px">运行数量：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="show_yxSl" name="yxSl" class="easyui-numberbox" min="0" max="100000000"   class="easyui-validatebox span2"     readonly="readonly" data-options="editable:true,required:true" style="width:100%">                    	
                    </td>
                    <td class="form-td-label" style="width: 80px">运输数量：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input id="show_ysSl" name="ysSl" class="easyui-numberbox" min="0" max="100000000"   class="easyui-validatebox span2"    readonly="readonly"  data-options="editable:true,required:true" style="width:100%">                    	
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
                        <input id="file" name="file" colspan="3" accept=".xls" class="easyui-filebox" data-options="buttonText:'选择文件',required:true,prompt:'选择文件'" style="width:100%">
                    </td>
                </tr>
                <tr>
                	<td class="form-td-label" >Excel模板地址：</td>
                	<td>
                		<a href="${path }/zsjk/jt/zsJkJtZySbTrXx/excelTemplate">点击下载模板</a>
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