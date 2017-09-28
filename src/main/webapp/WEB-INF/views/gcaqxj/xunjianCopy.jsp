<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <%@ include file="/commons/basejs.jsp" %>
  <meta http-equiv="X-UA-Compatible" content="edge" />
  <title>巡检信息引用</title>
  <script type="text/javascript" src="${staticPath }/static/js/gcaqxj/xjCopy.js" charset="utf-8"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
<div data-options="region:'west',split:true"  style="width:230px;">
  <div id="projectList" style="margin-left: 20px;margin-top: 20px;">
      <input  class="easyui-combobox" data-options="prompt:'==请选择工程名'" name='proiectid' id = "projectid_query_id" style="width: 100%;"  />

  </div>
  <ul id="xunjianFlTree"  style="margin: 10px 10px 10px 10px;width: 95%;"></ul>
</div>
<!-- 查询表单 -->
<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
  <div class="easyui-layout" fit="true">
    <div title="查询" data-options="region:'north',border:false"  style="width:100%;height:100px;border: false;overflow: hidden;" data-options="collapsible:true">
      <form id="searchForm">
        <table class="grid" style="border: 0px">
          <tr>
            <td class="form-td-label" style="width: 100px">检查点名称：</td>
            <td class="form-td-content" style="width: 180px">
              <input id="search_name" name="mingcheng" class="easyui-textbox" data-options="prompt:'检查点名称'" style="width: 100%;" />
              <input type="hidden" name="projectname" id="proiectname_query">
            </td>
          </tr>
          <tr>
            <td style="text-align: right;" colspan="2" >
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
      <a onclick="reloadFun();" href="javascript:void(0);" id="reload_button_sys_role" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
      <shiro:hasPermission name="/gcaqxj/info/copy/copyProject"> 
      	<a onclick="copyProFun();" href="javascript:void(0);" id="copy_button_project" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_copy'">复制当前工程</a>
      </shiro:hasPermission>
     <!--
      <a onclick="" href="javascript:void(0);" id="copy_button_fl" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_white_acrobat'">复制当前工程分类</a>
      -->
      <shiro:hasPermission name="/gcaqxj/info/copy/copyPoint"> 
      	<a onclick="CopyXunJianDianFun()" href="javascript:void(0);" id="copy_button_xunjiandian" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_copy'">复制巡检点</a>
      </shiro:hasPermission>
    </div>
  </div>
</div>

<div id="copyPro_Dialog" class="easyui-dialog" title="复制工程" style="width:400px;height:200px;padding:25px;display:none;"
     data-options="iconCls: 'icon-save',buttons: '#copyPro-dialog-buttons',closed:true,cache:false,modal:true">
  复制内容：<span id="srcProName"></span><br/>
  所有巡检内容（包含巡检点分类、巡检点、巡检内容）<br/>
 <font color="#dc143c"> 复制<span id="srcProName2"></span>所有巡检内容至当前${pro.proName}</font>
</div>
<div id="copyPro-dialog-buttons"  style="display:none">
  <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="CopyProAjax();">保存</a>
  <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#copyPro_Dialog').dialog('close')">关闭</a>
</div>


<div id="copyXunJianDian_Dialog" class="easyui-dialog" title="复制分类" style="width:400px;height:200px;padding:25px;display:none;"
     data-options="iconCls: 'icon-save',buttons: '#copyXunJianDian-dialog-buttons',closed:true,cache:false,modal:true">
  复制到本项目分类下：<input  class="easyui-combobox" data-options="prompt:'请选择分类'"  id = "typeName_query_id" style="width: 100%;"  />
</div>
<div id="copyXunJianDian-dialog-buttons"  style="display:none">
  <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="CopyXunJianDianAjax();">保存</a>
  <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="closeDialogFun();">关闭</a>
</div>
<input type="hidden" id="sourceProjectId" name="sourceProjectId"/>
<input type="hidden" id="sourceProjectName" name="sourceProjectName"/>
<input type="hidden" id="targetProjectId" name="targetProjectId" value="${pro.id}"/>
<input type="hidden" id="targetProjectName" name="targetProjectName" value="${pro.proName}"/>
<input type="hidden" id="sourceTypeId" name="sourceTypeId"/>
<input type="hidden" id="sourceTypeName" name="sourceTypeName"/>
<input type="hidden" id="targetTypeId" name="targetTypeId"/>
<input type="hidden" id="targetTypeName" name="targetTypeName"/>
</body>
</html>