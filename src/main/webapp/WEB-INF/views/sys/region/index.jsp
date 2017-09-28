<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>地区区划管理</title>

<script type="text/javascript" src="${staticPath }/static/js/sys/region/list.js" charset="utf-8"></script>
 
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
    
    <!-- 结果列表 -->
	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
		<table id="treeGrid" title="资源列表树" data-options="fit:true,border:false"></table>
	</div>
	<%--/** 地区名 *//** 详细地址 *//** 编号 *//** 图标 *//** 父级主键 *//** 排序 */ --%>
	
	<!-- 弹出框－新增 -->
	<div id="addDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false"
		 style="width:530px;height:225px;padding:10px 10px;" >
        <form id="addForm" method="post" data-options="novalidate:true">
	        <table class="grid">
	            <tr>
	                <td class="form-td-label"><span style="color: red;">*</span>地区名称：</td>
	                <td class="form-td-content">
	                	<input name="name" type="text"  class="easyui-textbox"  data-options="required:true,prompt:'请输入地区名称'" style="width: 98%;margin-right: 15px">
	                </td>
	                
	                <td class="form-td-label">编码：</td>
	                <td class="form-td-content">
	                	<input  name="code" class="easyui-textbox"  data-options="prompt:'请输入地区编码'"style="width:98%;margin-right: 15px"/>
	                </td>
	            </tr>
	             <tr>
	             <td class="form-td-label"><span style="color: red;">*</span>地区类型：</td>
	                <!-- 地区类型：1、国家， 2、地区 ，3、省级，4、省辖市级，5、直辖市，6、自治区， -->
	                <td class="form-td-content">
	                    <select id="select_regionType" name="type" required="required" class="easyui-combobox" 
	                        data-options="editable:false,panelHeight:'auto',prompt:'请选择地区类型'" style="width:98%;">
	                        <option value="城市">城市</option>
	                        <option value="省份">省份</option>
	                        <option value="地区">地区</option>
	                        <option value="国家">国家</option>
	                    </select>
	                </td>
	                
	                <td class="form-td-label"><span style="color: red;">*</span>排序：</td>
	                <td class="form-td-content">
	                	<input name="seq"  value="0" class="easyui-numberspinner" required="required" 
	                	data-options="editable:false,prompt:'请选择排序'" style="width: 98%;margin-right: 15px">
	                </td>
	                
	            </tr>
	            <tr>
	                <td class="form-td-label">所属地区：</td>
	                <td class="form-td-content" colspan="3">
	                	<select id="add_pid" name="pid" data-options="prompt:'请选择所属地区'" style="width:376px;"></select>
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
	<div id="editDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#edit-dialog-buttons',closed:true,cache: false"
		 style="width:530px;height:225px;padding:10px 10px;" >
        <form id="editForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden" >
        	
	        <table class="grid">
	            <tr>
	                <td class="form-td-label"><span style="color: red;">*</span>地区名称：</td>
	                <td class="form-td-content">
	                	<input name="name" type="text" class="easyui-textbox"  data-options="required:true,prompt:'请输入地区名称'" style="width: 98%;margin-right: 15px"  >
	                </td>
	                
	                
	                
	                <td class="form-td-label">编码：</td>
	                <td class="form-td-content">
	                	<input  name="code" class="easyui-textbox"  data-options="prompt:'请输入地区编码'"style="width: 98%;margin-right: 15px"/>
	                </td>
	            </tr>
	            <tr>    
	                <td class="form-td-label"><span style="color: red;">*</span>地区类型：</td>
	                <!-- 地区类型：1、国家， 2、地区 ，3、省级，4、省辖市级，5、直辖市，6、自治区， -->
	                <td class="form-td-content">
	                    <select name="type" class="easyui-combobox" 
	                        data-options="editable:false,panelHeight:'auto',prompt:'请选择地区类型'" style="width:98%;">
	                        <option value="城市">城市</option>
	                        <option value="省份">省份</option>
	                        <option value="地区">地区</option>
	                        <option value="国家">国家</option>
	                    </select>
	                </td>
	                <td class="form-td-label"><span style="color: red;">*</span>排序：</td>
	                <td class="form-td-content">
	                	<input name="seq"  class="easyui-numberspinner" required="required" 
	                data-options="editable:false,prompt:'请选择排序'" value="0" style="width: 98%;margin-right: 15px">
	                </td>
	                
	            </tr>
	            <tr>
	                <td class="form-td-label">上级地区：</td>
	                <td class="form-td-content" colspan="3">
	                	<select id="edit_pid" data-options="prompt:'请选择所属地区'" name="pid" style="width:376px;"></select>
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
	<div id="showDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#show-dialog-buttons',closed:true,cache: false"
		 style="width:530px;height:225px;padding:10px 10px;" >
        <form id="showForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden" >
        	<input  name="code" type="hidden"/>
        	<input  name="seq" type="hidden"/>
	        <table class="grid">
	            <tr>
	                <td class="form-td-label"><span style="color: red;">*</span>地区名称：</td>
	                <td class="form-td-content">
	                	<input name="name" type="text" data-options="editable:false" style="width: 98%;margin-right: 15px" class="easyui-textbox"  >
	                </td>
	                
	                <td class="form-td-label">编码：</td>
	                <td class="form-td-content">
	                	<input name="code" type="text" data-options="editable:false" class="easyui-textbox" style="width: 98%;margin-right: 15px">
	                </td>
	            </tr>
	            <tr>
	                <td class="form-td-label"><span style="color: red;">*</span>地区类型：</td>
	                <!-- 地区类型：1、国家， 2、地区 ，3、省级，4、省辖市级，5、直辖市，6、自治区， -->
	                <td class="form-td-content">
	                    <select name="type" class="easyui-combobox"  
	                        data-options="editable:false,panelHeight:'auto'" style="width:98%;">
	                        <option value="城市">城市</option>
	                        <option value="省份">省份</option>
	                        <option value="地区">地区</option>
	                        <option value="国家">国家</option>
	                    </select>
	                </td>
	                <td class="form-td-label"><span style="color: red;">*</span>排序：</td>
	                <td class="form-td-content">
	                	<input name="seq" value="0" class="easyui-textbox"  data-options="editable:false" style="width: 98%;margin-right: 15px">
	                </td>
	            </tr>
	            <tr>
	                <td class="form-td-label"><span style="color: red;">*</span>上级地区：</td>
	                <td class="form-td-content" colspan="3">
	                	<select id="show_pid"  name="pid" data-options="editable:false" style="width:376px;"></select>
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