<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
  <%@ include file="/commons/basejs.jsp" %>
  <meta http-equiv="X-UA-Compatible" content="edge" />
  <title>巡检点管理</title>
  <script type="text/javascript" src="${staticPath}/static/js/gcaqxj/xjdgl.js" charset="utf-8"></script>
  <script type="text/javascript" src="${staticPath}/static/easyui/plugins/jquery.edatagrid.js"></script>
  <script type="text/javascript" src="${staticPath}/static/PrintArea/jquery.PrintArea.js"></script>
  <script type="text/javascript" src="${staticPath}/static/qrcode/qrcode.min.js"></script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">

<!-- 【巡检点管理】查询表单 -->
<div class="easyui-panel" title="查询" data-options="region:'north',border:false,collapsible:true"  style="width:80%;height:105px;border: false;overflow: hidden;" >
  <form id="searchForm_XunJianDianGL" style="display:none">
    <table class="grid" border='0' cellspacing='0' style='width:80%;padding:5px;height: auto;'>
      <tr>
        <td class="form-td-label" style="width: 100px">工程名称：</td>
        <td class="form-td-content" style="width: 350px"  colspan="4">
          <input  class="easyui-combobox" data-options="prompt:'工程名称'" name='proiectid' id = "projectid_query_id" style="width:350px;"  />
          <input type="hidden" name="proiectname" id="proiectname_query"/>
        </td>
        <td class="form-td-label" style="width: 100px">检查点分类：</td>
        <td class="form-td-content" style="width: 100px">
          <input class="easyui-textbox"  data-options="prompt:'检查点分类名称'" name="typeName"  style="width: 100px;"/>
        </td>
      </tr>
      <tr>
        <td class="form-td-label" style="width: 80px">检查点名称：</td>
        <td class="form-td-content" style="width: 100px">
          <input  class="easyui-textbox" data-options="prompt:'检查点名称'" name='mingcheng' id = "mingcheng_query_id" style="width: 100px;"  />
        </td>
        <td class="form-td-label" style="width: 60px">责任人：</td>
        <td class="form-td-content" style="width: 100px">
          <input  class="easyui-textbox" data-options="prompt:'责任人'" name='zerenrmc' id = "zerenrmc_query_id" style="width: 100px;"  />
        </td>
        <td class="form-td-label" style="width: 60px">监督人：</td>
        <td class="form-td-content" style="width: 100px">
          <input  class="easyui-textbox" data-options="prompt:'监督人'" name='jiandurmc' id = "jiandurmc_query_id" style="width: 100px;"  />
        </td>
        <td align="center" width="230px" colspan="1">
        <shiro:hasPermission name="/gcaqxj/xjdgl/search">        
          <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchXunJianDianGLFun();">查询</a>
        </shiro:hasPermission>  
          <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleandXunJianDianGLFun();">重置</a>
        </td>
      </tr>
    </table>

  </form>
</div>

<!-- 结果列表 -->
<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
  <table id="dataGrid_xunjiandianGL" title="列表" data-options="fit:true,border:false"></table>
</div>
<div id="toolbar" style="display: none;">
  <a onclick="reloadXunJianDianGLFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
  
  <shiro:hasPermission name="/gcaqxj/xjdgl/add">    
  	<a onclick="addXunJianDianGLFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">添加</a>
  </shiro:hasPermission>
  
  <shiro:hasPermission name="/gcaqxj/xjdgl/edit">   
  	<a onclick="editXunJianDianGLFun();" id="edit_button_XunJianDianGL" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
  </shiro:hasPermission>
  
  <shiro:hasPermission name="/gcaqxj/xjdgl/delete">   
 	 <a onclick="delXunJianDianGLFun();" href="javascript:void(0);" id="delete__button_xunjiandianGL" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除</a>
  </shiro:hasPermission>
  
  <shiro:hasPermission name="/gcaqxj/xjdgl/info"> 
  	 <a onclick="viewXunJianDianNeiRongFun();" href="javascript:void(0);" id="view__button_xunjiandianGL" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-application_view_icons'">查看</a>
  </shiro:hasPermission>
   
  <a onclick="clearXunJianDianGLFunSelections();" href="javascript:void(0);" id="cancelSelect_button_xunjiandianGL" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
  
  <shiro:hasPermission name="/gcaqxj/xjdgl/editDef"> 
  <a onclick="editXunJianDianNeiRongFun();" id="edit_button_XunJianDianNeiRong" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑巡检内容</a>
  </shiro:hasPermission>
  
  <shiro:hasPermission name="/gcaqxj/xjdgl/outExl"> 
  	<a onclick="expXlsXunJianDianGLFun();" href="javascript:void(0);" id="expXls_button_xunjiandianGL" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
  </shiro:hasPermission>
  
  <shiro:hasPermission name="/gcaqxj/xjdgl/outPdf"> 
  	<a onclick="expPdfXunJianDianGLFun();" href="javascript:void(0);" id="expPdf_button_xunjiandianGL" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_white_acrobat'">导出Pdf</a>
  </shiro:hasPermission>
  
  <shiro:hasPermission name="/gcaqxj/xjdgl/print"> 
  	<a onclick="printXunJianDianGLFun();" href="javascript:void(0);" id="printer_button_xunjiandianGL" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-printer_go'">打印</a>
  </shiro:hasPermission>
</div>

<!--新增界面-->
<div id="addDialog_XunJianGL" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#xinzeng-dialog-buttons',closed:true,cache:false,modal:true"
     style="width:580px;height:350px;padding:10px 10px;display:none;" >
  <form id="addForm_XunJianGL" method="post" data-options="novalidate:true">
    <table  class="grid" style="width:530px">
      <tr>
        <td class="form-td-label" style="width: 150px">工程名称：</td>
        <td class="form-td-content" style="width: 200px" colspan="3">
          <span id="add_projectName">  </span>
          <input type="hidden" name="projectname" id="projectname_add"/>
          <input type="hidden" name="projectid" id="projectid_add"/>
        </td>
      </tr>
      <tr>
        <td class="form-td-label" style="width: 200px"><span style="color: red;">*</span>检查点名称：</td>
        <td class="form-td-content" style="width: 150px">
          <input  class="easyui-textbox" data-options="prompt:'检查点名称',validType:'length[1,40]',required:true" name='mingcheng' id = "mingcheng_add" style="width: 150px;"   />
        </td>
        <td class="form-td-label" style="width: 200px"><span style="color: red;">*</span>检查点分类：</td>
        <td class="form-td-content" style="width: 200px">
          <input  class="easyui-combobox" data-options="prompt:'检查点分类'" required="true"  id = "typeName_commbox_add" style="width: 200px;"  />
          <input type="hidden" name="typeId" id="typeId_add"/>
          <input type="hidden" name="typeName" id="typeName_add"/>
        </td>
      </tr>
      <tr>
        <td class="form-td-label" style="width: 210px"><span style="color: red;">*</span>检查点所在位置：</td>
        <td class="form-td-content" style="width: 300px" colspan="3">
          <input  class="easyui-textbox" data-options="prompt:'检查点所在位置',validType:'length[1,40]',required:true" name='xujiandianweizhi' id = "xujiandianweizhi_add" style="width: 300px;"  />
        </td>
      </tr>
      <tr>
        <td class="form-td-label" style="width: 170px"><span style="color: red;">*</span>责任人：</td>
        <td class="form-td-content" style="width: 100px">
          <input  class="easyui-textbox" data-options="prompt:'责任人',validType:'length[1,40]',required:true"  id = "zerenrmc_commbox_add" style="width: 150px;"  />
          <input type="hidden" name="zerenrid" id="zerenrid_add"/>
          <input type="hidden" name="zerenrmc" id="zerenrmc_add"/>
        </td>
        <td class="form-td-label" style="width: 150px"><span style="color: red;">*</span>监督人：</td>
        <td class="form-td-content" style="width: 100px">
          <input  class="easyui-combobox" data-options="prompt:'监督人',validType:'length[1,40]',required:true"  id = "jiandurmc_commbox_add" style="width: 150px;"  />
          <input type="hidden" name="jiandurid" id="jiandurid_add"/>
          <input type="hidden" name="jiandurmc" id="jiandurmc_add"/>
        </td>
      </tr>
      <tr>
        <td class="form-td-label" style="width: 210px"><span style="color: red;">*</span>需要检查频次：</td>
        <td class="form-td-content" style="width: 100px">
          <input  class="easyui-textbox" data-options="prompt:'需要检查频次',required:true" name="jianchapc"   style="width: 150px;"  />
        </td>
        <td class="form-td-label" style="width: 150px">顺序号：</td>
        <td class="form-td-content" style="width: 100px">
          <input  class="easyui-textbox" data-options="prompt:'顺序号(仅限数字)'" name="xuhao" id = "xuhao_add" style="width: 150px;"  />
        </td>
      </tr>
      <tr>
        <td class="form-td-label" style="width: 150px">备注：</td>
        <td class="form-td-content" style="width: 300px;height: 40px;" colspan="3">
          <input  class="easyui-textbox" data-options="prompt:'备注',multiline:true" name='beizhu' id = "beizhu" style="width: 300px;height: 40px;"  />
        </td>
      </tr>
    </table>
  </form>
</div>
<div id="xinzeng-dialog-buttons" style="display:none">
  <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="addXunJianGLAjax();">保存</a>
  <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#addDialog_XunJianGL').dialog('close')">关闭</a>
</div>


<!--编辑界面-->
<div id="editDialog_XunJianGL" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#edit-dialog-buttons',closed:true,cache:false,modal:true"
     style="width:580px;height:350px;padding:10px 10px;display:none;" >
  <form id="editForm_XunJianGL" method="post" data-options="novalidate:true">
    <table  class="grid" style="width:530px">
      <input type="hidden" name="id"/>
      <input type="hidden" name="updateTime"/>
      <tr>
        <td class="form-td-label" style="width: 150px"><span style="color: red;">*</span>工程名称：</td>
        <td class="form-td-content" style="width: 200px" colspan="3">
          <span id="edit_projectName">  </span>
          <input type="hidden" name="projectname" id="projectname_edit"/>
          <input type="hidden" name="projectid" id="projectid_edit"/>
        </td>
      </tr>
      <tr>
        <td class="form-td-label" style="width: 150px"><span style="color: red;">*</span>检查点名称：</td>
        <td class="form-td-content" style="width: 100px">
          <input  class="easyui-textbox" data-options="prompt:'检查点名称',validType:'length[1,40]',required:true" name='mingcheng' id = "mingcheng_edit" style="width: 100px;"   />
        </td>
        <td class="form-td-label" style="width: 150px"><span style="color: red;">*</span>检查点分类：</td>
        <td class="form-td-content" style="width: 100px">
          <input  class="easyui-combobox" data-options="prompt:'检查点分类',validType:'length[1,40]',required:true"   id = "typeName_commbox_edit" style="width: 100px;"  />
          <input type="hidden" name="typeId" id="typeId_edit"/>
          <input type="hidden" name="typeName" id="typeName_edit"/>
        </td>
      </tr>
      <tr>
        <td class="form-td-label" style="width: 210px"><span style="color: red;">*</span>检查点所在位置：</td>
        <td class="form-td-content" style="width: 300px" colspan="3">
          <input  class="easyui-textbox" data-options="prompt:'检查点所在位置',validType:'length[1,40]',required:true" name='xujiandianweizhi' id = "xujiandianweizhi_edit" style="width: 300px;"  />
        </td>
      </tr>
      <tr>
        <td class="form-td-label" style="width: 150px"><span style="color: red;">*</span>责任人：</td>
        <td class="form-td-content" style="width: 100px">
          <input  class="easyui-textbox" data-options="prompt:'责任人',validType:'length[1,40]',required:true"  id = "zerenrmc_commbox_edit" style="width: 150px;"  />
          <input type="hidden" name="zerenrid" id="zerenrid_edit"/>
          <input type="hidden" name="zerenrmc" id="zerenrmc_edit"/>
        </td>
        <td class="form-td-label" style="width: 150px"><span style="color: red;">*</span>监督人：</td>
        <td class="form-td-content" style="width: 100px">
          <input  class="easyui-combobox" data-options="prompt:'监督人',validType:'length[1,40]',required:true"  id = "jiandurmc_commbox_edit" style="width: 150px;"  />
          <input type="hidden" name="jiandurid" id="jiandurid_edit"/>
          <input type="hidden" name="jiandurmc" id="jiandurmc_edit"/>
        </td>
      </tr>
      <tr>
        <td class="form-td-label" style="width: 150px"><span style="color: red;">*</span>需要检查频次：</td>
        <td class="form-td-content" style="width: 100px">
          <input  class="easyui-textbox" data-options="prompt:'需要检查频次',validType:'length[1,40]',required:true" name="jianchapc"   style="width: 150px;"  />
        </td>
        <td class="form-td-label" style="width: 150px">顺序号：</td>
        <td class="form-td-content" style="width: 100px">
          <input  class="easyui-textbox" data-options="prompt:'顺序号'" name="xuhao" id = "xuhao_edit" style="width: 150px;"  />
        </td>
      </tr>
      <tr>
        <td class="form-td-label" style="width: 150px">备注：</td>
        <td class="form-td-content" style="width: 300px" colspan="3">
          <input  class="easyui-textbox" data-options="prompt:'备注',multiline:true" name='beizhu'  style="width: 300px;"  />
        </td>
      </tr>
    </table>
  </form>
</div>
<div id="edit-dialog-buttons" style="display:none">
  <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="editXunJianGLAjax();">保存</a>
  <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#editDialog_XunJianGL').dialog('close')">关闭</a>
</div>
<!--编辑巡检内容-->
<div id="editDialog_XunJianNeiRongGL" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#neirongedit-dialog-buttons',closed:true,cache:false,modal:true"
     style="width:580px;height:350px;padding:10px 10px;display:none;" >
  <table  class="grid" style="width:530px;border: none;">
    <tr>
      <td class="form-td-label" style="width: 150px">工程名称：</td>
      <td class="form-td-content" style="width: 200px" colspan="3">
        <span id="edit_NeiRongprojectName" class="width:100px;display:block;">  </span>
      </td>
    </tr>
    <tr>
      <td class="form-td-label" style="width: 150px">检查点名称：</td>
      <td class="form-td-content" style="width: 100px">
        <span id="edit_NeiRongMingCheng" class="width:100px;display:block;">  </span>
      </td>
      <td class="form-td-label" style="width: 150px">检查点分类：</td>
      <td class="form-td-content" style="width: 100px">
        <span id="edit_NeiRongTypeName" class="width:100px;display:block;">  </span>
      </td>
    </tr>
    <tr>
      <td class="form-td-label" style="width: 150px">检查点所在位置：</td>
      <td class="form-td-content" style="width: 300px" colspan="3">
        <span id="edit_NeiRongXuJianDianWeiZhi" class="width:300px;display:block;">  </span>
      </td>
    </tr>
    <tr>
      <td class="form-td-label" style="width: 150px">责任人：</td>
      <td class="form-td-content" style="width: 100px">
        <span id="edit_NeiRongZeRenMC" class="width:100px;display:block;">  </span>
      </td>
      <td class="form-td-label" style="width: 150px">监督人：</td>
      <td class="form-td-content" style="width: 100px">

        <span id="edit_NeiRongJianDuRenMC" class="width:100px;display:block;">  </span>
      </td>
    </tr>
    <tr>
      <td class="form-td-label" style="width: 150px">需要检查频次：</td>
      <td class="form-td-content" style="width: 100px">
        <span id="edit_NeiRongJianChaPC" class="width:100px;display:block;">  </span>
      </td>
    </tr>
    <tr>
      <td class="form-td-label" style="width: 150px">备注：</td>
      <td class="form-td-content" style="width: 300px" colspan="3">
        <span id="edit_NeiRongBeiZhu" class="width:300px;display:block;">  </span>
      </td>
    </tr>
  </table>
  <table id="dg" title="巡检内容管理" style="width:90%;height:200px"
         toolbar="#toolbar_NeiRong"  idField="id"
        singleSelect="true" data-options="onSave:function(){
		  $('#dg').edatagrid('reload');
		},
		destroyMsg:{
			norecord:{// 在没有记录选择的时候执行
				title:'警告',
				msg:'没有选择要删除的行!!!'
		},
			confirm:{// 在选择一行的时候执行
			  title:'确定',
				msg:'你真的要删除吗?'
			}
		},
		fitColumns:true,rownumbers:true">
    <thead>
    <tr>
      <th data-options="field:'id',hidden:true" class="display:none"/>
      <%--<th field="xuhao" width="50px" editor="{type:'validatebox',options:{required:true}}">顺序号</th>--%>
      <th field="contentXunjian" width="300px" editor="{type:'validatebox',options:{required:true}}">巡检内容</th>
    </tr>
    </thead>
  </table>
  <div id="toolbar_NeiRong">
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:$('#dg').edatagrid('addRow')">新增</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="javascript:$('#dg').edatagrid('destroyRow')">删除</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:$('#dg').edatagrid('saveRow')">保存</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-undo" plain="true" onclick="javascript:$('#dg').edatagrid('cancelRow')">取消</a>
  </div>
</div>
<div id="neirongedit-dialog-buttons" style="display:none">
  <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#editDialog_XunJianNeiRongGL').dialog('close')">关闭</a>
</div>
<!--查看巡检内容-->
<div id="viewDialog_XunJianNeiRongGL" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#view-dialog-buttons',closed:true,cache:false,modal:true"
     style="width:580px;height:350px;padding:10px 10px;display:none;" >
  <table  class="grid" style="width:530px;border: none;">
    <tr>
      <td class="form-td-label" style="width: 150px">工程名称：</td>
      <td class="form-td-content" style="width: 200px" colspan="3">
        <span id="view_NeiRongprojectName" class="width:100px;display:block;">  </span>
      </td>
    </tr>
    <tr>
      <td class="form-td-label" style="width: 150px">检查点名称：</td>
      <td class="form-td-content" style="width: 100px">
        <span id="view_NeiRongMingCheng" class="width:100px;display:block;">  </span>
      </td>
      <td class="form-td-label" style="width: 150px">检查点分类：</td>
      <td class="form-td-content" style="width: 100px">
        <span id="view_NeiRongTypeName" class="width:100px;display:block;">  </span>
      </td>
    </tr>
    <tr>
      <td class="form-td-label" style="width: 150px">检查点所在位置：</td>
      <td class="form-td-content" style="width: 300px" colspan="3">
        <span id="view_NeiRongXuJianDianWeiZhi" class="width:300px;display:block;">  </span>
      </td>
    </tr>
    <tr>
      <td class="form-td-label" style="width: 150px">责任人：</td>
      <td class="form-td-content" style="width: 100px">
        <span id="view_NeiRongZeRenMC" class="width:100px;display:block;">  </span>
      </td>
      <td class="form-td-label" style="width: 150px">监督人：</td>
      <td class="form-td-content" style="width: 100px">

        <span id="view_NeiRongJianDuRenMC" class="width:100px;display:block;">  </span>
      </td>
    </tr>
    <tr>
      <td class="form-td-label" style="width: 150px">需要检查频次：</td>
      <td class="form-td-content" style="width: 100px">
        <span id="view_NeiRongJianChaPC" class="width:100px;display:block;">  </span>

      </td>

    </tr>
    <tr>
      <td class="form-td-label" style="width: 150px">备注：</td>
      <td class="form-td-content" style="width: 300px" colspan="3">
        <span id="view_NeiRongBeiZhu" class="width:300px;display:block;"></span>
      </td>
    </tr>
  </table>
  <table id="dg_view"  style="width:100%;height:200px"
         pagination="false" idField="id"
         rownumbers="true" fitColumns="true" singleSelect="true" >
    <thead>
    <tr>
      <th data-options="field:'id',hidden:true" class="display:none"/>
      <%--<th field="xuhao" width="50px" editor="{type:'validatebox',options:{required:true}}">顺序号</th>--%>
      <th field="contentXunjian" width="480px" editor="{type:'validatebox',options:{required:true}}">巡检内容</th>
    </tr>
    </thead>
  </table>
</div>
<div id="view-dialog-buttons" style="display:none">
  <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#viewDialog_XunJianNeiRongGL').dialog('close')">关闭</a>
</div>


<!--打印巡检内容-->

<div  id="printDialog_XunJianNeiRongGL"   class="easyui-dialog"
      data-options="iconCls:'icon-save',buttons:'#print-dialog-buttons',closed:true,cache:false,modal:true"
      style="width:680px;height:450px;padding:10px 10px;display:none;">
  <div class=Section1  id="printTable"  style='width:600px;height:890px; vertical-align: middle;'>
  <table class=MsoNormalTable border=0 cellspacing=0 cellpadding=0
         style='margin-left:5.4pt;border-collapse:collapse'>
    <tr style='height:39.75pt'>
      <td width=206 valign=top style='width:154.5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:39.75pt'>
        <p class=MsoListParagraph align=center style='margin-left:21.0pt;text-align:
  center;text-indent:0cm;line-height:150%'><span style='font-size:22.0pt;
  line-height:150%;font-family:宋体' id="print_NeiRongTypeName"></span></p>
      </td>
      <td width=64 valign=top style='width:48.0pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:39.75pt'>
        <p class=MsoListParagraph align=center style='text-align:center;text-indent:
  0cm;line-height:150%'><span lang=EN-US style='font-size:22.0pt;line-height:
  150%'>&nbsp;</span></p>
      </td>
      <td width=278 rowspan=4 valign=top style='width:208.85pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:39.75pt'>

        <p class=MsoNormal align=left style='text-align:left'>
        <div  id = "qrcode" style="width:262px; height:228px;" ></div>
        </p>
      </td>
    </tr>
    <tr style='height:36.9pt'>
      <td width=270 colspan=2 valign=top style='width:202.5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:36.9pt'>
        <p class=MsoListParagraph align=left style='text-align:left;text-indent:0cm;
  line-height:150%'><span style='font-size:14.0pt;line-height:150%;font-family:
  宋体'>责 任 人<span lang=EN-US>:</span></span><span style='font-size:14.0pt;line-height:150%;font-family:
  宋体' id="print_NeiRongZeRenMC"></span></p>
      </td>
    </tr>
    <tr style='height:34.5pt'>
      <td width=270 colspan=2 valign=top style='width:202.5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:34.5pt'>
        <p class=MsoListParagraph align=left style='text-align:left;text-indent:0cm;
  line-height:150%'><span style='font-size:14.0pt;line-height:150%;font-family:
  宋体'>监 督 人：</span><span style='font-size:14.0pt;line-height:150%;font-family:
  宋体' id="print_NeiRongJianDuRenMC"></span></p>
      </td>
    </tr>
    <tr style='height:35.25pt'>
      <td width=270 colspan=2 valign=top style='width:202.5pt;padding:0cm 5.4pt 0cm 5.4pt;
  height:35.25pt'>
        <p class=MsoListParagraph align=left style='text-align:left;text-indent:0cm;
  line-height:150%'><span style='font-size:14.0pt;line-height:150%;font-family:
  宋体'>检查频次</span><span lang=EN-US style='font-size:14.0pt;line-height:150%'>:</span><span style='font-size:14.0pt;line-height:150%;font-family:
  宋体' id="print_NeiRongJianChaPC"></span></p>
      </td>
    </tr>
  </table>

 

  <table  id="dg_XJNR" class="easyui-datagrid"
          style='border-collapse:collapse;border:1; cellspacing:0 ;cellpadding:0; width:570px;'  data-options="fitColumns:true,singleSelect:true">
    <tr style='height:19.85pt'>
 
      <td width=550px valign=top style='width:450.25pt;border:solid windowtext 1.0pt;
  border-left:none;padding:0cm 5.4pt 0cm 5.4pt;height:19.85pt'>
        <p class=MsoNormal align=center style='text-align:center'><span
                style='font-size:12.0pt;font-family:宋体'>检查内容</span></p>
      </td>
    </tr> 
  </table>
    </div>
</div>

<div id="print-dialog-buttons" style="display:none">
  <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-printer_go'" onclick="printView();">打印</a>
  <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#printDialog_XunJianNeiRongGL').dialog('close')">关闭</a>
</div>

<input type="hidden" id="projectId-golbal" value="${pro.id}">
<input type="hidden" id="projectName-golbal" value="${pro.proName}">
<input type="hidden" id="orgId-global" value="${orgId}">
</body>
</html>