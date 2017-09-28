<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>CAD中心线维护</title>
<script type="text/javascript" src="${staticPath }/static/js/sgkshgl/zxxwh/list.js?v=20170509122815" charset="utf-8"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
<div data-options="region:'west',split:true" title="项目列表" style="width:230px;">
		<ul id="proTree"  style="margin: 10px 10px 10px 10px;width: 95%;"></ul>
</div>
<!-- 列表信息 -->
<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
<div class="easyui-layout" fit="true">
	<!-- 查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:140px;border: false;overflow: hidden;" data-options="collapsible:true">
		<form id="searchForm">
			<table class="grid" style="border: 0px">
			 <tr>
			    <td class="form-td-label" style="width: 80px" >所属项目：</td>
			    <td align="left" style="width: 200px" >
			      <input   id="search_proId"  name='proId' class="easyui-combobox"   style='width: 100%' data-options="prompt:'请选择所属项目'">
			    </td>
			    <td class="form-td-label" style="width: 80px">区间：</td>
			    <td align="left" style="width: 200px">
			      <input  id="search_sectionId"  name='sectionId' class="easyui-combobox"   data-options="prompt:'请选择区间'" style='width: 100%'>
			    </td>
			  </tr>
			  <tr>
			    <td class="form-td-label" style="width: 80px">线路：</td>
			    <td align="left" style="width: 100px">
			      <input id="search_lineId"  name='lineId'    class="easyui-combobox"  data-options="prompt:'请选择线路'"  style='width: 100%'>
			    </td>
			    <td class="form-td-label" style="width: 80px">类型：</td>
                <td class="form-td-content" style="width: 100px">
                    <input id="search_type" name="type" class="easyui-combobox" data-options="prompt:'请选择类型'" style="width:100%">
                </td>
			  </tr>
			   <tr>
			     <td class="form-td-label" style="width: 80px">环号：</td>
			     <td class="form-td-content" width="160px">
			       <input name='hh' id='search_hh'  data-options="prompt:'请输入环号',validType:'length[1,40]'" class="easyui-numberbox" min="0" max="100000000"   class="easyui-validatebox span2"    style='width: 100%;'>
			     </td>
			     <td ></td>
			     <td align="right" width="170px">
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a>
			      <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleanFun();">重置</a>
			     </td>
			   </tr>
			</table>
		</form>
	</div>
	<!-- 结果列表 -->
	<div data-options="region:'center',border:false" style="width:100%;height:100%;">
		<table id="dataGrid" title="列表" data-options="fit:true,border:false"></table>
	</div>
    <div id="toolbar" style="display: none;">
    	<a onclick="reloadFun();" href="javascript:void(0);" id="reload_button_sys_emp" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    	<a onclick="clearSelections();" href="javascript:void(0);" id="clear_button_sys_emp" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
    	<shiro:hasPermission name="/sgkshgl/zxxwh/add">
    	   <a onclick="addFun();" href="javascript:void(0);" id="add_button_sys_emp" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/sgkshgl/zxxwh/edit">
    	   <a onclick="editFun();" href="javascript:void(0);" id="edit_button_sys_emp" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/sgkshgl/zxxwh/del">
    	   <a onclick="deleteFun();" href="javascript:void(0);" id="del_button_sys_emp" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/sgkshgl/zxxwh/list">
    	   <a onclick="showFun();" href="javascript:void(0);" id="show_button_sys_emp" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-book_previous'">查看</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/sgkshgl/zxxwh/imp">
			<a onclick="impFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">Excel导入</a>
    	</shiro:hasPermission>
   	    
   	    <shiro:hasPermission name="/sgkshgl/zxxwh/ExpExcel">
    	   <a onclick="expXls();" href="javascript:void(0);" id="expXls_button_sys_emp" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
    	</shiro:hasPermission>
    	<shiro:hasPermission name="/sgkshgl/zxxwh/ExpPdf">
    	  <a onclick="expPdf();" href="javascript:void(0);" id="expPdf_button_sys_emp" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_white_acrobat'">导出Pdf</a>       
  	    </shiro:hasPermission> 
  	   
    	 
   </div>
</div>
</div>
	<!-- 弹出框－新增 -->
	<div id="addDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:510px;height:300px;padding:5px 5px;" >
        <form id="addForm" method="post" data-options="novalidate:true">
            <table class="grid">
                <tr>
                    <td class="form-td-label" style="width: 100px;"><span style="color: red;">*</span>工程名称：</td>
                    <td colspan='3' style="width: 400px;">
                       <select  id="add_proId" name="proId" class="easyui-combobox" data-options="required:true,prompt:'请选择工程名称'" style="width: 100%;"></select>
                    </td>
                </tr>
                <tr>
                   
                    <td class="form-td-label" style="width: 100px;"><span style="color: red;">*</span>区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间：</td>
                    <td style="width: 150px;">
                        <select editable="false" id="add_sectionId" name="sectionId" class="easyui-combobox" data-options="required:true,prompt:'请选择区间'" style="width: 100%;"></select>
                    </td>
                    <td class="form-td-label"><span style="color: red;">*</span>线&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;路：</td>
                    <td>
                        <select id="add_lineId" name="lineId" class="easyui-combobox" data-options="required:true,prompt:'请选择线路'" style="width: 100%;"></select>
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型：</td>
                    <td>
                        <select  id="add_type" name="type" class="easyui-combobox" data-options="required:true,prompt:'请选择类型'" style="width: 100%;"></select>
                    </td>
                    <td  class="form-td-label" ><span style="color: red;">*</span>环&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
				    <td>
				        <input name='hh' id='add_hh'  data-options="required:true,prompt:'请输入环号',validType:'length[1,40]'" class="easyui-numberbox" min="0" max="100000000"   class="easyui-validatebox span2"    style='width: 100%;'>
	                </td>
                </tr>
                <tr>
                    <td  class="form-td-label" >里&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;程：</td>
				    <td>
				        <input name='lc' id='add_lc'  data-options="prompt:'请输入里程',validType:'length[1,40]'" class="easyui-numberbox" min="0" max="100000000"   class="easyui-validatebox span2"    style='width: 100%;'>
					</td>
                   <td  class="form-td-label" >X：</td>
				    <td>
				        <!-- 双精度型  6-->
					    <input name='x' id='add_x'  data-options="prompt:'请输入X',validType:'length[1,40]'"  class="easyui-numberbox" precision="6" min="0.000001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>	
	                </td>
                </tr>
                <tr>
                    <td  class="form-td-label" >Y：</td>
				    <td>
				        <!-- 双精度型  6-->
					    <input name='y' id='add_y'  data-options="prompt:'请输入Y',validType:'length[1,40]'"  class="easyui-numberbox" precision="6" min="0.000001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>	
	                </td>
	                <td  class="form-td-label" >Z：</td>
				    <td>
				        <!-- 双精度型  6-->
					    <input name='z' id='add_z'  data-options="prompt:'请输入Z',validType:'length[1,40]'"  class="easyui-numberbox" precision="6" min="0.000001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>	
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
		 style="width:510px;height:300px;padding:5px 5px;" >
        <form id="editForm" method="post" data-options="novalidate:true">
        	<input name="objectid" type="hidden" >
        	<input name="proName" type="hidden" >
        	<input name="sectionName" type="hidden" >
        	<input name="lineName" type="hidden" >
        	<table class="grid">
                <tr>
                    <td class="form-td-label" style="width: 100px;"><span style="color: red;">*</span>工程名称：</td>
                    <td colspan='3' style="width: 400px;">
                       <select  id="edit_proId" name="proId" class="easyui-combobox" data-options="required:true,prompt:'请选择工程名称'" style="width: 100%;"></select>
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width: 100px;"><span style="color: red;">*</span>区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间：</td>
                    <td style="width: 150px;">
                        <select editable="false" id="edit_sectionId" name="sectionId" class="easyui-combobox" data-options="required:true,prompt:'请选择区间'" style="width: 100%;"></select>
                    </td>
                    <td class="form-td-label"><span style="color: red;">*</span>线&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;路：</td>
                    <td>
                        <select id="edit_lineId" name="lineId" class="easyui-combobox" data-options="required:true,prompt:'请选择线路'" style="width: 100%;"></select>
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型：</td>
                    <td>
                        <select  id="edit_type" name="type" class="easyui-combobox" data-options="required:true,prompt:'请选择类型'" style="width: 100%;"></select>
                    </td>
                    <td  class="form-td-label" ><span style="color: red;">*</span>环&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
				    <td>
				        <input name='hh' id='edit_hh'  data-options="required:true,prompt:'请输入环号',validType:'length[1,40]'" class="easyui-numberbox" min="0" max="100000000"   class="easyui-validatebox span2"    style='width: 100%;'>
	                </td>
                </tr>
                <tr>
                    <td  class="form-td-label" >里&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;程：</td>
				    <td>
				        <input name='lc' id='edit_lc'  data-options="prompt:'请输入里程',validType:'length[1,40]'" class="easyui-numberbox" min="0" max="100000000"   class="easyui-validatebox span2"    style='width: 100%;'>
					</td>
                   <td  class="form-td-label" >X：</td>
				    <td>
				        <!-- 双精度型  6-->
					    <input name='x' id='edit_x'  data-options="prompt:'请输入X',validType:'length[1,40]'"  class="easyui-numberbox" precision="6" min="0.000001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>	
	                </td>
                </tr>
                <tr>
                    <td  class="form-td-label" >Y：</td>
				    <td>
				        <!-- 双精度型  6-->
					    <input name='y' id='edit_y'  data-options="prompt:'请输入Y',validType:'length[1,40]'"  class="easyui-numberbox" precision="6" min="0.000001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>	
	                </td>
	                <td  class="form-td-label" >Z：</td>
				    <td>
				        <!-- 双精度型  6-->
					    <input name='z' id='edit_z'  data-options="prompt:'请输入Z',validType:'length[1,40]'"  class="easyui-numberbox" precision="6" min="0.000001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>	
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
		 style="width:510px;height:300px;padding:5px 5px;" >
        <form id="showForm" method="post" data-options="novalidate:true">
        	<input name="objectid" type="hidden" >
        	<input name="proName" type="hidden" >
        	<input name="sectionName" type="hidden" >
        	<input name="lineName" type="hidden" >
            <table class="grid">
                <tr>
                    <td class="form-td-label" style="width: 100px;"><span style="color: red;">*</span>工程名称：</td>
                    <td colspan='3' style="width: 400px;">
                       <select  id="show_proId" name="proId" readonly="readonly"class="easyui-combobox" data-options="required:true,prompt:''" style="width: 100%;"></select>
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width: 100px;"><span style="color: red;">*</span>区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间：</td>
                    <td style="width: 150px;">
                        <select id="show_sectionId" name="sectionId" readonly="readonly"class="easyui-combobox" data-options="required:true,prompt:''" style="width: 100%;"></select>
                    </td>
                    <td class="form-td-label"><span style="color: red;">*</span>线&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;路：</td>
                    <td>
                        <select id="show_lineId" name="lineId" readonly="readonly"class="easyui-combobox" data-options="required:true,prompt:''" style="width: 100%;"></select>
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型：</td>
                    <td>
                        <select  id="show_type" name="type" readonly="readonly"class="easyui-combobox" data-options="required:true,prompt:''" style="width: 100%;"></select>
                    </td>
                    <td  class="form-td-label" ><span style="color: red;">*</span>环&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
				    <td>
				        <input name='hh' id='show_hh'  readonly="readonly"data-options="prompt:'',validType:'length[1,40]'" class="easyui-numberbox" min="0" max="100000000"   class="easyui-validatebox span2"    style='width: 100%;'>
	                </td>
                </tr>
                <tr>
                    <td  class="form-td-label" >里&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;程：</td>
				    <td>
				        <input name='lc' id='show_lc'  readonly="readonly"data-options="prompt:'',validType:'length[1,40]'" class="easyui-numberbox" min="0" max="100000000"   class="easyui-validatebox span2"    style='width: 100%;'>
					</td>
                   <td  class="form-td-label" >X：</td>
				    <td>
				        <!-- 双精度型  6-->
					    <input name='x' id='show_x' readonly="readonly" data-options="prompt:'',validType:'length[1,40]'"  class="easyui-numberbox" precision="6" min="0.000001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>	
	                </td>
                </tr>
                <tr>
                    <td  class="form-td-label" >Y：</td>
				    <td>
				        <!-- 双精度型  6-->
					    <input name='y' id='show_y' readonly="readonly" data-options="prompt:'',validType:'length[1,40]'"  class="easyui-numberbox" precision="6" min="0.000001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>	
	                </td>
	                <td  class="form-td-label" >Z：</td>
				    <td>
				        <!-- 双精度型  6-->
					    <input name='z' id='show_z' readonly="readonly" data-options="prompt:'',validType:'length[1,40]'"  class="easyui-numberbox" precision="6" min="0.000001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>	
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
		 style="width:410px;height:330px;padding:5px 5px;" >
        <form  id="impForm" method="post" enctype="multipart/form-data">
        <input type="hidden" id="filename" />
            <table class="grid">
                
                 <tr>
                	<td class="form-td-label" style="width:120px"><span style="color: red;">*</span>所属工程:</td>
                	<td class="form-td-content" colspan="3" style="width:280px">
                    	<select id="imp_proId" name="proId" class="easyui-combobox"  data-options="required:true,prompt:'请选择所属工程'" style="width:100%;"></select>
                	</td>
            	</tr>
                <tr>
                	<td class="form-td-label" style="width:80px"><span style="color: red;">*</span>项目区间:</td>
                    <td class="form-td-content" colspan="3" style="width:280px">
                    	<select id="imp_sectionId"  name="sectionId" class="easyui-combobox" data-options="required:true,prompt:'请选择项目区间'" style="width: 100%;"></select>
                    </td>
                </tr>
                <tr>
	               	   <td class="form-td-label" style="width:80px"><span style="color: red;">*</span>区间线路:</td>
	                   <td class="form-td-content" colspan="3" style="width:280px">
	                   	   <select id="imp_lineId" name="lineId"  class="easyui-combobox" data-options="required:true,prompt:'请选择区间线路'" style="width: 100%;"></select>
	                   </td>
	            </tr>
	            <tr>
                    <td class="form-td-label" ><span style="color: red;">*</span>导入文件路径:</td>
                    <td colspan="3" style="width:280px;">
                        <input id="file" name="file"  accept=".xls,.xlsx" class="easyui-filebox" data-options="buttonText:'选择文件',required:true,prompt:'选择文件'" style="width:100%">
                    </td>
                </tr>
                <tr>
                	<td class="form-td-label" >Excel模板地址:</td>
                	<td>
                	     <%--<a href="${path }/sgkshgl/zxxwh/excelTemplate">点击生成模板</a> --%>
                		<a href="${path }/sgkshgl/zxxwh/downLoadModel">点击下载模板</a>
                	</td>
                </tr>
                <tr>
                	<td  colspan="2">
                	    <span >
		                	<font color='red'>
		                		备注：请严格按照模板格式录入数据，每行数据之间不得存在空行，
		                		<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                		           模板外部不得编辑或增添任何文字或图片，
		                		<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                		           模板中有“ * ”标注的列为必填项；
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