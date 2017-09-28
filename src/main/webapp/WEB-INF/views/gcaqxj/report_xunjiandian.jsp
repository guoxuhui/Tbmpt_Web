<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <%@ include file="/commons/basejs.jsp" %>
  <meta http-equiv="X-UA-Compatible" content="edge" />
  <title>巡检记录管理</title>
  <script type="text/javascript" src="${staticPath}/static/js/gcaqxj/report_xunjiandian.js" charset="utf-8"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">

<!-- 【巡检记录管理】查询表单 -->
<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:50%;height:105px;border: false;overflow: hidden;" data-options="collapsible:true" >
  <form id="searchForm_XunJianDian" style="display:none">
    <table class="grid" border='0' cellspacing='0' style='width:60%;padding:5px;height: auto;'>
      <tr>
        <td class="form-td-label" style="width: 100px">工程名称：</td>
        <td class="form-td-content" style="width: 350px"  colspan="2">
          <input  class="easyui-combobox" data-options="prompt:'工程名称'" name='proiectid' id = "projectid_query_id" style="width: 350px;"  />
          <input type="hidden" name="projectname" id="proiectname_query"/>
        </td>
      </tr>
      <tr>
        <td class="form-td-label" style="width: 100px">日期：</td>
        <td class="form-td-content" style="width: 250px">
          <input   class="easyui-datetimebox"  name='startDate'   style='width: 149px' data-options="prompt:'请输入开始时间'" > ---
          <input   class="easyui-datetimebox"  name='endDate'   style='width: 149px' data-options="prompt:'请输入结束时间'" >
        </td>
        <td align="right" width="120px" >
          <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchXunJianDianFun();">查询</a>
          <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleandXunJianDianFun();">重置</a>
        </td>
      </tr>
    </table>
  </form>
</div>
<!-- 结果列表 -->
<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
  <table id="dataGrid_report_xunjiandian" title="列表" data-options="fit:true,border:false"></table>
</div>
</body>
</html>