<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>基础数据管理</title>
    <script type="text/javascript">
    var dataGrid;
    $(function() {
    	
    	 $('#typeName').combobox({
             url : '${path}/dmcjjc/dmcjddtype/getAll',
             valueField: "typeName",
             textField: "typeName"
         });
    	
        dataGrid = $('#dataGrid').datagrid({
            url : '${path }/dmcjjc/dmcjddinfo/list',
            striped : true,
            rownumbers : true,
            pagination : true,
            singleSelect : false,
            idField : 'id',
            pageSize : 20,
            pageList : [ 10, 15, 20, 25, 50],
            frozenColumns : [ [ { 
            	field:'ck',checkbox:true 
            },{
                width : '150',
                title : '分类名称',
                field : 'typeName',
                sortable : false
            } , {
                width : '150',
                title : '数据名称',
                field : 'ddName',
                sortable : false,
                formatter : function(value, row, index) {
                    var str = $.formatString('<a href="javascript:void(0)" onclick="showFun(\'{0}\',\'{1}\',\'{2}\',450,300);" >{3}</a>', row.id,'${path}/dmcjjc/dmcjddinfo/showPage','基础数据详细信息',value);
                    return str;
                }
            }]], 
            columns : [ [{
                width : '60',
                field : 'ifqy',
                title : '使用状态',
                sortable : false,
            }, {
                width : '60',
                title : '排序号',
                field : 'sortNum'
            } ,{
                width : '320',
                title : '备注',
                field : 'remarks',
                align:'left',halign:'center',
                sortable : false,
            }] ],
            onLoadSuccess:function(data){
            },
            toolbar : [ 
                   	{text : '刷新',iconCls: 'icon-reload',
					    handler: function(){
					    	dataGrid.datagrid('reload');
					    }
					},
   		            {text : "添加",iconCls : 'icon-add',handler : addFun}, 
   		            '-',
   		            {text : "编辑",iconCls : 'icon-edit',handler : editFunNew}, 
   		            '-', 
   		            {text : "删除",iconCls : 'icon-del',handler : deleteFunNew},
   		            '-', 
   		         	{text: '取消已选',iconCls: 'icon-undo',
		    			handler: function(){
		    				dataGrid.datagrid('clearSelections');
		    			}
		    		},
		            '-',
         			{text : "启用",iconCls : 'icon-ok',handler : qyFun},
	            	'-', 
	            	{text : "禁用",iconCls : 'icon-no',handler : jyFun},
   		            '-', 
   		            {text : "导出Excel",iconCls : 'icon-page_excel',handler : expDDInfo}
	           ]
        });
    });

    function qyFun(){
    	qy('${path }/dmcjjc/dmcjddinfo/qy');
    }
    
    function jyFun(){
    	jy('${path }/dmcjjc/dmcjddinfo/jy');
    }
    
    function editFunNew(id) {
    	editFun('${path }/dmcjjc/dmcjddinfo/editPage',500,300);
    }

    function deleteFunNew(id) {
    	deleteFun('${path }/dmcjjc/dmcjddinfo/del');
    }
    
    function addFun() {
        parent.$.modalDialog({
            title : '添加',
            width : 500,
            height : 300,
            href : '${path }/dmcjjc/dmcjddinfo/addPage',
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#addForm');
                    f.submit();
                }
            } ]
        });
    }

    function searchFun() {
        dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
        dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
    }
    function cleanFun() {
        $('#typeName').combobox('setValue','');
        $('#searchForm')[0].reset();
        dataGrid.datagrid('load', {});
    }
    
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
  <!-- 查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:70px;border: false;overflow: hidden;" data-options="collapsible:true">
		<form id="searchForm">
			<table class="grid" style="border: 0px">
				<tr>
					<td class="form-td-label" style="width: 80px">分类名称:</td>
					<td class="form-td-content" style="width: 200px">
						<select editable="false" id="typeName" name="typeName" class="easyui-combobox" editable="false"  data-options="prompt:'请输入分类名称'" style="width: 140px;"></select>
					</td>
					<td style="text-align: left;">
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
    
    <!-- 放一个隐藏表单用提交于下载文件 -->
    <form id="download" name="download" style="display:" method="post" action="${path}/dmcjjc/dmcjddinfo/expDDInfo">
    	<!-- 存放选中的记录id -->
    	<input type="type" id="ids" name="ids"/>
    </form>
</body>
</html>