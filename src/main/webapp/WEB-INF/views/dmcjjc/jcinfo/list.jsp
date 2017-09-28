<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>日常监测管理</title>
    <script type="text/javascript">
    var dataGrid;
    $(function() {
    	
        $('#gcbh').combobox({
            url : basePath + '/project/info/getProjects',
            valueField : "id",
            textField : "proName"
        });
    	
        dataGrid = $('#dataGrid').datagrid({
            url : '${path }/dmcjjc/dmcjjcinfo/list',
            striped : true,
            rownumbers : true,
            pagination : true,
            singleSelect : false,
            idField : 'id',
            pageSize : 20,
            pageList : [ 10, 15, 20, 25, 50],
            frozenColumns : [ [ { 
            	field:'ck',checkbox:true 
            }, {
                width : '320',
                title : '工程名称',
                field : 'projName',
                sortable : false
            } , {
                width : '150',
                title : '本次监测时间',
                field : 'jcTime',
                sortable : false,
                formatter : function(value, row, index) {
                    var str = $.formatString('<a href="javascript:void(0)" onclick="showFun(\'{0}\',\'{1}\',\'{2}\');" >{3}</a>', row.id,'${path}/dmcjjc/dmcjjcinfo/showPage','日常监测详细信息',value);
                    return str;
                }
            }]], 
            columns : [ [{
                width : '50',
                title : '天气',
                field : 'tianqi'
            } , {
                width : '80',
                title : '确认状态',
                field : 'ifqr'
            } , {
                field : 'action',
                title : '导入文件下载',
                width : 100,
                formatter : function(value, row, index) {
                	if(row.impFilePath == null || row.impFilePath == ""){
						return "";
					}
                    //var str = $.formatString('<a href="../upload/'+row.impFilePath+'">点击下载</a>', row.id);
                    var str = $.formatString('<a href="${path }/dmcjjc/dmcjjcinfo/download?filepath='+row.impFilePath+'">点击下载</a>', row.id);
                    return str;
                }
            }, {
                width : '320',
                field : 'remarks',
                title : '备注',
                align:'left',halign:'center',
                sortable : false,
            } ] ],
            onLoadSuccess:function(data){
                $('.role-easyui-linkbutton-edit').linkbutton({text:'编辑',plain:true,iconCls:'icon-edit'});
                $('.role-easyui-linkbutton-del').linkbutton({text:'删除',plain:true,iconCls:'icon-del'});
                $('.role-easyui-linkbutton-qy').linkbutton({text:'确认',plain:true,iconCls:'icon-ok'});
                $('.role-easyui-linkbutton-jy').linkbutton({text:'撤销确认',plain:true,iconCls:'icon-no'});
                $('.role-easyui-linkbutton-download').linkbutton({text:'下载',plain:true,iconCls:'icon-download'});
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
		            {text: '取消已选',iconCls: 'icon-undo',
		    			handler: function(){
		    				dataGrid.datagrid('clearSelections');
		    			}
		    		},
		            '-',
		            {text : "删除",iconCls : 'icon-del',handler : deleteFunNew},
		            '-', 
		         	{text : "确认",iconCls : 'icon-ok',handler : qyFun},
	            	'-', 
	            	{text : "撤销确认",iconCls : 'icon-no',handler : jyFun},
		            '-', 
		            //{text : "导出Excel",iconCls : 'icon-page_excel',handler : expDDInfo}
		            {text : "导出Excel",iconCls : 'icon-page_excel',
		    			handler: function(){
							var url = "${path }/dmcjjc/dmcjjcinfo/expXls";
		    				ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, "地面沉降监测点日报", dataGrid, false);
		    			}
		            }
           ]
        });
    });

    function qyFun(){
    	jcqy('${path }/dmcjjc/dmcjjcinfo/qy');
    }
    
    function jyFun(){
    	jcjy('${path }/dmcjjc/dmcjjcinfo/jy');
    }
    
    function editFunNew(id) {
    	editDetailInfoFun('${path }/dmcjjc/dmcjjcinfo/editPage','日常监测信息修改');
    }

    function deleteFunNew(id) {
    	deleteJcFun('${path }/dmcjjc/dmcjjcinfo/del');
    }
    
    function addFun() {
        parent.$.modalDialog({
            title : '请选择导入文件',
            width : 400,
            height : 250,
            href : '${path }/dmcjjc/dmcjjcinfo/importPage',//跳转到上传文件导入页面
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
        $('#gcbh').combobox('setValue','');
        $('#ifqr').combobox('setValue','');
        $('#beginTime').datetimebox('setValue','');
        $('#endTime').datetimebox('setValue','');
        $('#searchForm')[0].reset();
        dataGrid.datagrid('load', {});
    }
    
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
   <!-- 查询表单 -->
    <div class="easyui-layout" data-options="region:'center',border:false" style="width:100%;height:100%;">
   <!-- 查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:100px;border: false;overflow: hidden;" data-options="collapsible:true">
		<form id="searchForm">
			<table class="grid" style="border: 0px">
				<tr>
					<td class="form-td-label" style="width: 80px">工程名称:</td>
					<td class="form-td-content" colspan="3" style="width: 480px">
						<select editable="false" id="gcbh" name="gcbh" class="easyui-combobox" data-options="prompt:'工程名称'" style="width: 100%;"></select>
					</td>
					<td class="form-td-label" style="width: 80px">确认状态:</td>
					<td class="form-td-content" style="width: 200px">
					<select editable="false" id="ifqr" name="ifqr" class="easyui-combobox" data-options="prompt:'确认状态'" style="width: 100%;">
						<option value="">--请选择--</option>
						<option value="已确认">已确认</option>
						<option value="未确认">未确认</option>
					</select>
					</td>
				</tr>
				<tr>
				<td class="form-td-label" >起始监测时间:</td>
				<td>
				<input editable="false" id="beginTime" name="beginTime" class="easyui-datetimebox" data-options="prompt:'起始监测时间'" style="width:100%;">
        		</td>
				<td class="form-td-label" >截止监测时间:</td>
				<td>
				<input editable="false" id="endTime" name="endTime" class="easyui-datetimebox" data-options="prompt:'截止监测时间'" style="width:100%;">
        		</td>
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
	</div>
    <!-- 放一个隐藏表单用提交于下载文件 -->
    <form id="download" name="download" style="display:" method="post" action="${path}/dmcjjc/dmcjjcinfo/expJcInfo">
    	<!-- 存放选中的记录id -->
    	<input type="hidden" id="ids" name="ids"/>
    </form>
    
	<div id="openDiv" class="easyui-window" data-options="modal:true,closed:true,inline:false,resizable:false,
	maximizable:false,minimizable:false,collapsible:false" title="添加" style="width:900px;height:500px;">
	    <iframe id='openXXXIframe' src="" style="width:100%;height:98%;"></iframe>
	</div>
</body>
</html>