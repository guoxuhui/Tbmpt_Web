<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
var orgzTree;
var orgzId;
var dataGrid;
$(function() {
	
	/**
	 * 创建数据表格
	 *
	 */
	dataGrid = $('#dataGrid').datagrid({
		url : '${path }/sys/emp/dataGrid',
		queryParams:{
			orgzId: "-1"
		},
		striped : true,
		singleSelect : false,
		checkOnSelect : true,
		selectOnCheck : true,
		pagination : true,
		idField : 'id',
		sortName : 'id',
		sortOrder : 'desc',
		pageSize : 15,
		pageList : [ 10, 15, 20, 50 ],
		columns : [ [
			{width : '80',title : '姓名',field : 'name',sortable : true}, 
            {width : '80',title : '性别',field : 'sex',
                formatter : function(value, row, index) {
                    switch (value) {
                    case '1':
                        return '男';
                    case '0':
                        return '女';
                    }
                }
            },
            {width : '120',title : '电话',field : 'phone'}, 
            {width : '200',title : '部门',field : 'orgzName'}
            
			] ],
		onLoadSuccess : function(data) {}
		/*,
		toolbar: [
			{
			    text:'刷新',
			    iconCls:'icon-reload',
			    handler:function(){}
			},'-',{
			    text:'确定',
			    iconCls:'icon-ok',
			    handler:function(){}
			}]
		*/
	});
	
	
    orgzTree = $('#orgzTree').tree({
        url : '${path }/sys/orgz/tree',
        parentField : 'pid',
        lines : true,
        onClick : function(node) {
            dataGrid.datagrid('load', {
                orgzId: node.id
            });
            orgzId=node.id;
        },
    	onLoadSuccess:function(node,data){  
    		//先 关闭所有节点
    		$("#orgzTree").tree("collapseAll");
    		//默认展开前三级
    		
    		//展开 第一级 节点
            $("#orgzTree li:eq(0)").find("div").addClass("tree-node-selected");   //通过添加样式class，设置第一个节点高亮（选中节点）  
            var n = $("#orgzTree").tree("getSelected");   //获取（选中节点）的值
            if(n!=null){  
                 $('#orgzTree').tree('expand',n.target);   //展开指定的节点
                 $("#orgzTree li:eq(0)").find("div").removeClass("tree-node-selected"); //通过删除样式class，删除该节点高亮（选中）样式，准备展开下一个节点
                 //展开 第二级 节点
                 $("#orgzTree li:eq(1)").find("div").addClass("tree-node-selected");
                 var m = $("#orgzTree").tree("getSelected");
                 if(m!=null){
                      $('#orgzTree').tree('expand',m.target);
                      $("#orgzTree li:eq(1)").find("div").removeClass("tree-node-selected");
                      //展开 第三级 节点
                      $("#orgzTree li:eq(2)").find("div").addClass("tree-node-selected");
                      var b = $("#orgzTree").tree("getSelected");
                      if(b!=null){
                           $('#orgzTree').tree('expand',b.target);
                           $("#orgzTree li:eq(2)").find("div").removeClass("tree-node-selected");
                      }
                 }
            }  
            
         }
    
    });
});
/**
 * 表单查询
 */
function searchAddFun() {
	var name = $('#EmpName').val();
	dataGrid.datagrid('load',{name:name,orgzId:orgzId});
}
/**
 * 表单重置
 */
function cleanFun() {
	$('#searchForm input').val('');
	orgzId="";
	$('#searchForm').form('clear');
	dataGrid.datagrid('load',{});
}
/**
 * 表单查询
 */
var searchFun = function(orgzId) {
	dataGrid.datagrid('load',{orgzId:orgzId});
}


</script>
<style type="text/css">
<!--
.datagrid-toolbar table{
    float: right;
}
-->
</style>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'west',split:true" title="组织机构" style="width:260px;">
		<ul id="orgzTree"  style="margin: 10px 10px 10px 10px;width: 95%;"></ul>
	</div>
	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
		<div class="easyui-layout" fit="true">
			<div title="员工查询" data-options="region:'north',border:false,collapsible:true"  
					style="width:100%;height:70px;overflow: hidden;" >
					<form id="searchForm">
						<table class="grid" style="border: 0px">
							<tr>
								<td class="form-td-label" style="width: 80px">员工名称：</td>
								<td class="form-td-content" style="width: 160px">
									<input id="EmpName" name="name" class="easyui-textbox" data-options="prompt:'员工名称'" style="width: 100%;" />
								</td>
								<td style="text-align: right;" >
									<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchAddFun();">查询</a> 
									<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleanFun();">重置</a>
								</td>
							</tr>
						</table>
					</form>
			</div>
			<div data-options="region:'center',title:'员工列表',iconCls:'icon-ok'">
			    <table id="dataGrid" data-options="border:false,singleSelect:true,fit:true"></table>
			</div>
		</div>
	</div>
	<!-- <div title="员工查询" data-options="region:'north',border:false,collapsible:true"  
			style="width:100%;height:100px;overflow: hidden;" >
			<form id="searchForm">
				<table class="grid" style="border: 0px">
					<tr>
						<td class="form-td-label" style="width: 80px">员工名称：</td>
						<td class="form-td-content" style="width: 160px">
							<input id="search_empId" name="name" class="easyui-textbox" data-options="prompt:'员工名称'" style="width: 100%;" />
						</td>
						<td style="text-align: right;" >
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a> 
							<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleanFun();">重置</a>
						</td>
					</tr>
				</table>
			</form>
	</div>
	<div data-options="region:'center',title:'员工列表',iconCls:'icon-ok'">
	    <table id="dataGrid" data-options="border:false,singleSelect:true,fit:true"></table>
	</div> -->
</div>