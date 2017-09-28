<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>盾构掘进点位管理：盾构机机器数据实时信息管理</title>
<script type="text/javascript" src="${staticPath }/static/js/dgjjdw/real/dgjjdwPlcReal.js" charset="utf-8"></script>
</head>
<!-- dgjjdwPlcReal.jsp -->
<body class="easyui-layout" data-options="fit:true,border:false">
	<!-- 查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:100px;border: false;overflow: hidden;" data-options="collapsible:true">
		<form id="searchForm">
			<table class="grid">
				<tr>
					<td class="form-td-label" style="width: 100px">点位编号：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='id'  id='search_id' data-options="prompt:'请输入点位编号',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
					<td class="form-td-label" style="width: 100px">盾构机CODE：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='tbmcode'  id='search_tbmcode' data-options="prompt:'请输入盾构机CODE',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
			    </tr>
				<tr>
					<td class="form-td-label" style="width: 100px">工业库点位名称：</td>
					<td class="form-td-content" style="width: 160px">
						<input name='tagname'  id='search_tagname' data-options="prompt:'请输入工业库定位名称',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
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
    	
    	<shiro:hasPermission name="dgjjdw/real/dgjjdwPlcReal/sbInfo">
  	        <a onclick="showFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">查看</a>
    	</shiro:hasPermission>
    	<%-- <shiro:hasPermission name="dgjjdw/real/dgjjdwPlcReal/ExpExcel">  
    	    <a onclick="expXls();" href="javascript:void(0);"   class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
    	</shiro:hasPermission> 
   	     <shiro:hasPermission name="dgjjdw/real/dgjjdwPlcReal/ExpPdf">  
    	    <a onclick="expPdf();" href="javascript:void(0);"   class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_white_acrobat'">导出Pdf</a>
    	</shiro:hasPermission> --%>
    	
   	</div>
  
	<!-- 弹出框－查看 -->
	<div id="showDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#show-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:600px;height:310px;padding:5px 5px;" >
        <form id="showForm" method="post" data-options="novalidate:true">
            <table class="grid">
                <tr>
                    <td class="form-td-label" style="width: 100px">点位编号：</td>
                	<td class="form-td-content" colspan="3" >
				        <input name='id'  id='show_id' data-options="editable:false,prompt:'',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
	                </td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width: 100px">盾构机ID：</td>
                	<td class="form-td-content" style="width: 150px">
				        <input name='tbmid'  id='show_tbmid' data-options="editable:false,prompt:'',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
	                </td>
                    <td class="form-td-label" style="width: 100px">盾构机CODE：</td>
                    <td class="form-td-content" style="width: 150px">
                    	<input name='tbmcode'  id='show_tbmcode' data-options="editable:false,prompt:'',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width: 100px">工业库点位名称：</td>
                	<td class="form-td-content" style="width: 150px"> 
				        <input name='tagname'  id='show_tagname' data-options="editable:false,prompt:'',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
	                </td>
                    <td class="form-td-label" style="width: 100px" >工业库点位数据类型：</td>
                	<td class="form-td-content" style="width: 150px">
				        <input name='tagtype'  id='show_tagtype' data-options="editable:false,prompt:'',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
	                </td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width: 100px">工业库点位值：</td>
                	<td class="form-td-content" style="width: 150px"> 
				        <input name='tagvalue'  id='show_tagvalue' data-options="editable:false,prompt:'',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
	                </td>
                    <td class="form-td-label" style="width: 100px">时间：</td>
                	<td class="form-td-content" style="width: 150px">
				        <input name='tagtime'  id='show_tagtime' data-options="editable:false,prompt:'',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
	                </td>
                    
                </tr>
                <tr>
                    <td class="form-td-label" style="width: 100px">描述：</td>
                	<td class="form-td-content" colspan="5">
				        <input name='ms'  id='show_ms' data-options="editable:false,prompt:'',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
	                </td>
	            </tr>
                <tr>
                    <td class="form-td-label" style="width: 100px">备注：</td>
                	<td class="form-td-content"colspan="5"> 
				        <input name='bz'  id='show_bz' data-options="editable:false,prompt:'',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
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
                		<a href="${path }/dgjjdw/real/dgjjdwPlcReal/excelTemplate">点击下载模板</a>
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