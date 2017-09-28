<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <%@ include file="/commons/basejs.jsp" %>
  <meta http-equiv="X-UA-Compatible" content="edge" />
  <title>巡检记录管理</title>
  <script type="text/javascript" src="${staticPath}/static/js/gcaqxj/xjts.js" charset="utf-8"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">

<!-- 【巡检记录管理】查询表单 -->
<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:80%;height:105px;border: false;overflow: hidden;" data-options="collapsible:true" >
  <form id="searchForm_XJTS" style="display:none">
    <table class="grid" border='0' cellspacing='0' style='width:50%;padding:5px;height: auto;'>
      <tr>
        <td class="form-td-label" style="width: 100px">工程名称：</td>
        <td class="form-td-content" style="width: 400px"  colspan="2">
          <input  class="easyui-combobox" data-options="prompt:'工程名称'" name='proiectid' id = "projectid_query_id" style="width: 400px;"  />
          <input type="hidden" name="projectname" id="proiectname_query"/>
        </td>
      </tr>
      <tr>
        <td class="form-td-label" style="width: 80px">查看状态：</td>
          <td class="form-td-content" width="141px">
          <select class="easyui-combobox" class="easyui-validatebox span2"  name='isView' id='search_isView'  data-options="prompt:'请选择',required:true"  style='width: 150px'>
            <option value="">请选择==</option>
            <option value="1">查看</option>
            <option value="0">未查看</option>
          </select>
        </td>
        <td align="center" width="120px" >
          <shiro:hasPermission name="/gcaqxj/push/search">
          	<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchForm_XJTSFun();">查询</a>
          </shiro:hasPermission>
          <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleandXJTSFun();">重置</a>
        </td>
      </tr>
    </table>

  </form>
</div>

<!-- 结果列表 -->
<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
  <table id="dataGrid_XJTS" title="列表" data-options="fit:true,border:false"></table>
</div>
<div id="toolbar" style="display: none;">
  <a onclick="reloadXJTSFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
  <shiro:hasPermission name="/gcaqxj/push/info">
  	<a onclick="viewXunJianDianJLFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-application_view_columns'">查看</a>
  </shiro:hasPermission>
</div>


<!--查看巡检内容-->
<div id="viewDialog_XunJianNeiRongJL" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#view-dialog-buttons',closed:true,cache:false,modal:true"
     style="width:580px;height:350px;padding:10px 10px;display:none;" >
  <table  class="grid" style="width:530px;border: none;">
    <tr>
      <td class="form-td-label" style="width: 150px">工程名称：</td>
      <td class="form-td-content" style="width: 200px" colspan="3">
        <span id="view_JLprojectName" class="width:100px;display:block;">  </span>
      </td>
    </tr>
    <tr>
      <td class="form-td-label" style="width: 150px">检查点名称：</td>
      <td class="form-td-content" style="width: 100px">
        <span id="view_JLMingCheng" class="width:100px;display:block;">  </span>
      </td>
      <td class="form-td-label" style="width: 150px">检查点分类：</td>
      <td class="form-td-content" style="width: 100px">
        <span id="view_JLTypeName" class="width:100px;display:block;">  </span>
      </td>
    </tr>
    <tr>
      <td class="form-td-label" style="width: 150px">检查点所在位置：</td>
      <td class="form-td-content" style="width: 300px" colspan="3">
        <span id="view_JLXuJianDianWeiZhi" class="width:300px;display:block;">  </span>
      </td>
    </tr>
    <tr>
      <td class="form-td-label" style="width: 150px">责任人：</td>
      <td class="form-td-content" style="width: 100px">
        <span id="view_JLZeRenMC" class="width:100px;display:block;">  </span>
      </td>
      <td class="form-td-label" style="width: 150px">监督人：</td>
      <td class="form-td-content" style="width: 100px">

        <span id="view_JLJianDuRenMC" class="width:100px;display:block;">  </span>
      </td>
    </tr>
    <tr>
      <td class="form-td-label" style="width: 150px">需要检查频次：</td>
      <td class="form-td-content" style="width: 100px">
        <span id="view_JLJianChaPC" class="width:100px;display:block;">  </span>
      </td>
    </tr>
    <tr>
      <td class="form-td-label" style="width: 150px">备注：</td>
      <td class="form-td-content" style="width: 300px" colspan="3">
        <span id="view_JLBeiZhu" class="width:300px;display:block;">  </span>
      </td>
    </tr>
  </table>
  <table  id="dg_XJNR" class="easyui-datagrid"  style="width:530px;height:250px"
          data-options="fitColumns:true,singleSelect:true">
  </table>
</div>

<div id="view-dialog-buttons" style="display:none">
  <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="closeDialog();">关闭</a>
</div>
</body>
</html>