<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>监测点坐标管理</title>
 <script type="text/javascript" src="${staticPath}/static/js/sgkshgl/jcdzbgl/list.js?v=20170526180452" charset="utf-8"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
<div data-options="region:'west',split:true" title="项目列表" style="width:230px;">
		<ul id="orgzTree"  style="margin: 10px 10px 10px 10px;width: 95%;"></ul>
</div>
    <!-- 查询表单 -->
    <div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
	<div class="easyui-layout" fit="true">
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:100px;border: false;overflow: hidden;" data-options="collapsible:true">
		<form id="searchForm">
			<table class="grid" style="border: 0px">
				<tr>
					<td class="form-td-label" style="width: 80px">工程名称:</td>
					<td class="form-td-content" colspan="3" style="width: 480px">
						<select editable="false" id="proId" name="proId" class="easyui-combobox" data-options="prompt:'请输入工程名称'" style="width: 100%;"></select>
					</td>
					<td class="form-td-label" style="width: 80px">点位编号:</td>
					<td class="form-td-content" style="width: 200px">
						<input id="jcId" name="jcId" class="easyui-textbox" data-options="prompt:'请输入点位编号'" style="width: 100%;" /></td>
				</tr>
				<tr>
					<td class="form-td-label" style="width: 80px">区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间:</td>
					<td class="form-td-content" style="width: 200px">
						<select editable="false" id="secId" name="secId" class="easyui-combobox" data-options="prompt:'请输入区间'" style="width: 100%;"></select>
					</td>
					<td class="form-td-label" style="width: 80px">点位类型:</td>
					<td class="form-td-content" style="width: 200px">
						<select editable="false" id="jcType" name="jcType" class="easyui-combobox" data-options="prompt:'请输入点位类型'" style="width: 100%;"></select>										
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
	<div id="toolbar" style="display: none;">
    	<a onclick="reloadFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<a onclick="clearSelections();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    	    	<shiro:hasPermission name="/sgkshgl/jcdzbgl/add">
    	    		<a onclick="addFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	    	</shiro:hasPermission>
				<shiro:hasPermission name="/sgkshgl/jcdzbgl/edit">
    	    		<a onclick="editFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	    	</shiro:hasPermission>
    	    	<shiro:hasPermission name="/sgkshgl/jcdzbgl/del">
    	    		<a onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	    	</shiro:hasPermission>
		    	 		   
    	    	<shiro:hasPermission name="/sgkshgl/jcdzbgl/show">
			    	<a onclick="showFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">查看</a>
    	    	</shiro:hasPermission>

    	    	<shiro:hasPermission name="/sgkshgl/jcdzbgl/excelAdd">
			    	<a onclick="excelAddFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">导入</a>
    	    	</shiro:hasPermission>   	    	    	
    	    	
    	    	
    	    	<a onclick="expXls();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出excel</a>
    	    	
    	    	<a onclick="expPdf();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_white_acrobat'">导出pdf</a>	
    	        
    	 </div>
	
    <!-- 放一个隐藏表单用提交于下载文件 -->
    <form id="download" name="download" style="display:" method="post" action="${path}/dmcjjc/dmcjjcpoint/expJcPoint">
    	<!-- 存放选中的记录id -->
    	<input type="type" id="ids" name="ids"/>
    </form>
</div>
</div> 
</body>
</html>