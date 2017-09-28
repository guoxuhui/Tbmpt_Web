<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>盾构掘进点位对比信息</title>
<script type="text/javascript" src="${staticPath }/static/js/dgjjdw/dwsjzh/dwsjzhSearchList.js?v=20170801" charset="utf-8"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
	<!-- 查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:80px;border: false;overflow: hidden;" data-options="collapsible:true">
		<form id="searchDwsjzhForm">
			<table class="grid" style="border: 0px">
				<tr>
				    <td class="form-td-label" style="width: 80px">盾构机名称：</td>
					<td class="form-td-content" style="width: 180px">
						<input name="dgjname" class="easyui-textbox"  data-options="prompt:'请输入盾构机名称'" style="width: 100%"/>
					</td>
					<td class="form-td-label" style="width: 90px">标准点位名称：</td>
					<td class="form-td-content" style="width: 180px">
						<input name="dwname" class="easyui-textbox"  data-options="prompt:'请输入标准点位名称'" style="width: 100%" />
					</td>

				    <td align="right" width="250px" >
			      	   <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchDwsjzhFun();">查询</a>
			     	   <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleandDwsjzh();">重置</a>
			   	  </td>
				</tr> 
			</table>
		</form>
	</div>
	<!-- 结果列表 -->
	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100px;">
		<table id="dataGrid_DwsjzhSearch" title="列表" data-options="fit:true,border:false"></table>
	</div>
	
	<div id="toolbar" style="display: none;">
    	<a onclick="reloadFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<a onclick="addDwsjzhFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a> 
        <a onclick="editDwsjzhFun();" id="edit_button_Dwsjzh" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
        <a onclick="deleteDwsjzhFun();" href="javascript:void(0);" id="del_button_Dwsjzh" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
        <a onclick="showDwsjzhFun();" href="javascript:void(0);" id="show_button_Dwsjzh" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-book_previous'">查看</a>
	    <a onclick="clearDwsjzhSelections();" href="javascript:void(0);" id="cancelSelect_button_Dwsjzh" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>  	    	    	   	
   	</div>
    <!-- 弹出框-新增 -->
    <div id="add_dwsjzh_Dialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#tbm-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:600px;height:400px;padding:5px 5px;">
		 <form id="dwsjzhForm" method="post" data-options="novalidate:true">
		 	<input type="hidden" id="id" name="id"/>
		 	<table class="grid" style="width:100%">
		 		<tr>
		 	        <td class="form-td-label" style="width:140px"><span style="color: red;">*</span>盾构机名称：</td>
                	<td class="form-td-content">
                    	<input id="dgjname" name="dgjname" class="easyui-textbox" data-options="editable:true,required:true,prompt:'请输入盾构机名称',validType:'length[1,40]'" style="width:100%">
                	</td>
		 			<td class="form-td-label" style="width:140px"><span style="color: red;">*</span>计算方式：</td>
                	<td class="form-td-content">
                    	<input id="calType" name="calType" class="easyui-textbox" data-options="editable:true,required:true,prompt:'请输计算方式信息',validType:'length[1,40]'"  style="width:100%">
                	</td> 
		 		</tr>
		 		<tr>
		 			<td class="form-td-label" style="width:140px"><span style="color: red;">*</span>标准点位名称：</td>
                	<td class="form-td-content" colspan="3">
                    	<input id="dwname" name="dwname" class="easyui-textbox" data-options="editable:true,required:true,prompt:'请输入标准点位名称',validType:'length[1,40]'" style="width:100%">
                	</td> 
		 			
		 		</tr>
		 		<tr>
		 			<td class="form-td-label" style="width:140px"><span style="color: red;">*</span>跨度开始：</td>
                	<td class="form-td-content">
                    	<input id="kdStart" name="kdStart" class="easyui-textbox" data-options="editable:true,required:true,prompt:'请输入跨度开始信息',validType:'length[1,40]'"  style="width:100%">
                	</td>
                    <td class="form-td-label" style="width:140px"><span style="color: red;">*</span>跨度结束：</td>
                	<td class="form-td-content">
                    	<input id="kdEnd" name="kdEnd" class="easyui-textbox"  data-options="editable:true,required:true,prompt:'请输入跨度结束信息',validType:'length[1,40]'" style="width:100%">
                	</td>
		 		</tr>
		 	    <tr>
		 			<td class="form-td-label" style="width:140px"><span style="color: red;">*</span>数据值开始：</td>
                	<td class="form-td-content">
                    	<input id="dataStart" name="dataStart" class="easyui-textbox" data-options="editable:true,required:true,prompt:'请输入数据值开始信息',validType:'length[1,40]'"  style="width:100%">
                	</td>
                    <td class="form-td-label" style="width:140px"><span style="color: red;">*</span>数据值结束：</td>
                	<td class="form-td-content">
                    	<input id="dataEnd" name="dataEnd" class="easyui-textbox" data-options="editable:true,required:true,prompt:'请输入数据值结束信息',validType:'length[1,40]'"   style="width:100%">
                	</td>
		 		</tr>
		 	    <tr>
		 			<td class="form-td-label" style="width:140px"><span style="color: red;">*</span>单位：</td>
                	<td class="form-td-content">
                    	<input id="dw" name="dw" class="easyui-textbox" data-options="editable:true,required:true,prompt:'请输入单位信息',validType:'length[1,40]'"    style="width:100%">
                	</td>
                    <td class="form-td-label" style="width:140px"><span style="color: red;">*</span>正负数：</td>
                	<td class="form-td-content">
                    	<select  name="dataZf" class="easyui-combobox" data-options="prompt:'请选择'" style="width: 100%;">
						   <option value="1" selected="selected">正数</option>
						   <option value="-1">负数</option>
						</select>
                	</td>
		 		</tr>
		 		
		 	    <tr> 
                    <td class="form-td-label" style="width:140px">备注：</td>
                	<td class="form-td-content" colspan="3">
                    	<input class="easyui-textbox"
                    	 name='remarks' id='remarks'  data-options="multiline:true,validType:'length[1,400]'"  style='width:100%;height:100px'/>
                	</td>
		 		</tr>
		 		 
		 	</table>
		 </form>
	</div>	 
    <div id="tbm-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="addDwsjzh()">确定</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#add_dwsjzh_Dialog').dialog('close')">关闭</a>
	</div>
	
	    <!-- 弹出框-更新 -->
    <div id="edit_dwsjzh_Dialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#tbm-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:600px;height:400px;padding:5px 5px;">
		 <form id="editDwsjzhForm" method="post" data-options="novalidate:true">
		 	<input type="hidden" id="id" name="id"/>
		 	<table class="grid" style="width:100%">
		 		<tr>
		 	        <td class="form-td-label" style="width:140px"><span style="color: red;">*</span>盾构机名称：</td>
                	<td class="form-td-content">
                    	<input id="dgjname" name="dgjname" class="easyui-textbox" data-options="editable:true,required:true,prompt:'请输入盾构机名称',validType:'length[1,40]'" style="width:100%">
                	</td>
		 			<td class="form-td-label" style="width:140px"><span style="color: red;">*</span>计算方式：</td>
                	<td class="form-td-content">
                    	<input id="calType" name="calType" class="easyui-textbox" data-options="editable:true,required:true,prompt:'请输计算方式信息',validType:'length[1,40]'"  style="width:100%">
                	</td> 
		 			
		 		</tr>
		 		<tr>
		 			<td class="form-td-label" style="width:140px"><span style="color: red;">*</span>标准点位名称：</td>
                	<td class="form-td-content" colspan="3">
                    	<input id="dwname" name="dwname" class="easyui-textbox" data-options="editable:true,required:true,prompt:'请输入标准点位名称',validType:'length[1,40]'" style="width:100%">
                	</td> 
		 		</tr>
		 		<tr>
		 			<td class="form-td-label" style="width:140px"><span style="color: red;">*</span>跨度开始：</td>
                	<td class="form-td-content">
                    	<input id="kdStart" name="kdStart" class="easyui-textbox" data-options="editable:true,required:true,prompt:'请输入跨度开始信息',validType:'length[1,40]'"  style="width:100%">
                	</td>
                    <td class="form-td-label" style="width:140px"><span style="color: red;">*</span>跨度结束：</td>
                	<td class="form-td-content">
                    	<input id="kdEnd" name="kdEnd" class="easyui-textbox"  data-options="editable:true,required:true,prompt:'请输入跨度结束信息',validType:'length[1,40]'" style="width:100%">
                	</td>
		 		</tr>
		 	    <tr>
		 			<td class="form-td-label" style="width:140px"><span style="color: red;">*</span>数据值开始：</td>
                	<td class="form-td-content">
                    	<input id="dataStart" name="dataStart" class="easyui-textbox" data-options="editable:true,required:true,prompt:'请输入数据值开始信息',validType:'length[1,40]'"  style="width:100%">
                	</td>
                    <td class="form-td-label" style="width:140px"><span style="color: red;">*</span>数据值结束：</td>
                	<td class="form-td-content">
                    	<input id="dataEnd" name="dataEnd" class="easyui-textbox" data-options="editable:true,required:true,prompt:'请输入数据值结束信息',validType:'length[1,40]'"   style="width:100%">
                	</td>
		 		</tr>
		 	    <tr>
		 			<td class="form-td-label" style="width:140px"><span style="color: red;">*</span>单位：</td>
                	<td class="form-td-content">
                    	<input id="dw" name="dw" class="easyui-textbox" data-options="editable:true,required:true,prompt:'请输入单位信息',validType:'length[1,40]'"    style="width:100%">
                	</td>
                    <td class="form-td-label" style="width:140px"><span style="color: red;">*</span>正负数：</td>
                	<td class="form-td-content">
                		<select  name="dataZf" class="easyui-combobox" data-options="prompt:'请选择',required:true" style="width: 100%;">
						   <option value="1">正数</option>
						   <option value="-1">负数</option>
						</select>
                	</td>
		 		</tr>
		 		
		 	    <tr> 
                    <td class="form-td-label" style="width:140px">备注：</td>
                	<td class="form-td-content" colspan="3">
                	<input class="easyui-textbox"
                    	 name='remarks' id='remarks'  data-options="multiline:true,validType:'length[1,400]'"  style='width:100%;height:100px'/>
                	</td>
		 		</tr>
		 	</table>
		 </form>
	</div>	 
    <div id="tbm-dialog-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="editDwsjzh()">确定</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#edit_dwsjzh_Dialog').dialog('close')">关闭</a>
	</div>
	
	
	   <!-- 弹出框-查看 -->
    <div id="show_dwsjzh_Dialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#tbm-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:600px;height:400px;padding:5px 5px;">
		 <form id="dwsjzhShowForm" method="post" data-options="novalidate:true">
		 	<input type="hidden" id="id" name="id"/>
		 	<table class="grid" style="width:100%">
		 		<tr>
		 		    <td class="form-td-label" style="width:140px"><span style="color: red;">*</span>盾构机名称：</td>
                	<td class="form-td-content">
                    	<input id="dgjname" name="dgjname" class="easyui-textbox" data-options="readonly:true" style="width:100%">
                	</td>
		 			<td class="form-td-label" style="width:140px"><span style="color: red;">*</span>计算方式：</td>
                	<td class="form-td-content">
                    	<input id="calType" name="calType" class="easyui-textbox" data-options="readonly:true"  style="width:100%">
                	</td>
                	
          
		 		</tr>
		 		<tr>
		 			<td class="form-td-label" style="width:140px"><span style="color: red;">*</span>标准点位名称：</td>
                	<td class="form-td-content" colspan="3">
                    	<input id="dwname" name="dwname" class="easyui-textbox" data-options="readonly:true" style="width:100%">
                    </td>
                  
		 		</tr>
		 		<tr>
		 			<td class="form-td-label" style="width:140px"><span style="color: red;">*</span>跨度开始：</td>
                	<td class="form-td-content">
                    	<input id="kdStart" name="kdStart" class="easyui-textbox" data-options="readonly:true"  style="width:100%">
                	</td>
                    <td class="form-td-label" style="width:140px"><span style="color: red;">*</span>跨度结束：</td>
                	<td class="form-td-content">
                    	<input id="kdEnd" name="kdEnd" class="easyui-textbox"  data-options="readonly:true" style="width:100%">
                	</td>
		 		</tr>
		 	    <tr>
		 			<td class="form-td-label" style="width:140px"><span style="color: red;">*</span>数据值开始：</td>
                	<td class="form-td-content">
                    	<input id="dataStart" name="dataStart" class="easyui-textbox" data-options="readonly:true"  style="width:100%">
                	</td>
                    <td class="form-td-label" style="width:140px"><span style="color: red;">*</span>数据值结束：</td>
                	<td class="form-td-content">
                    	<input id="dataEnd" name="dataEnd" class="easyui-textbox" data-options="readonly:true"   style="width:100%">
                	</td>
		 		</tr>
		 	    <tr>
		 			<td class="form-td-label" style="width:140px"><span style="color: red;">*</span>单位：</td>
                	<td class="form-td-content">
                    	<input id="dw" name="dw" class="easyui-textbox" data-options="readonly:true"    style="width:100%">
                	</td>
                    <td class="form-td-label" style="width:140px"><span style="color: red;">*</span>正负数：</td>
                	<td class="form-td-content">
                	<input id="dataZf" name="dataZf" class="easyui-textbox" data-options="readonly:true"    style="width:100%">
                	</td>
		 		</tr>
		 		
		 		<tr> 
                    <td class="form-td-label" style="width:140px">备注：</td>
                	<td class="form-td-content" colspan="3">
                	<input class="easyui-textbox"
                    	 name='remarks' id='remarks'  data-options="multiline:true,readonly:true"  style='width:100%;height:100px'/>
                	</td>
		 		</tr>
		 	</table>
		 </form>
	</div>	 
    <div id="tbm-dialog-buttons"> 
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#show_dwsjzh_Dialog').dialog('close')">关闭</a>
	</div>
	 
</body>
</html>