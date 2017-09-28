<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>监测报告管理</title>
    <script type="text/javascript">
    var dataGrid;
    var jcinfoDG;
    $(function() {
    	
        $('#gcbh').combobox({
            url : basePath + '/project/info/getProjects',
            valueField : "id",
            textField : "proName"
        });

        dataGrid = $('#dataGrid').datagrid({
            url : '${path }/dmcjjc/bg/list',
            striped : true,
            fitColumn:true,
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
                	var str = $.formatString('<a href="javascript:void(0)" onclick="showFun(\'{0}\',\'{1}\',\'{2}\');" >{3}</a>', row.id,'${path}/dmcjjc/bg/showPage','监测报告详细信息',value);
                   	return str;
                }
            }]], 
            columns : [ [{
                width : '80',
                field : 'tianqi',
                title : '天气',
                sortable : false,
            }, {
                width : '80',
                field : 'ifqr',
                title : '确认状态',
                sortable : false,
            },{
                width : '80',
                field : 'ifsb',
                title : '上报状态',
                sortable : false,
            },{
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
     		         	{text : "确认",iconCls : 'icon-ok',handler : qyFun},
     	            	'-', 
     	            	{text : "撤销确认",iconCls : 'icon-no',handler : jyFun},
     		            '-', 
     		           	//{text : "导出Excel",iconCls : 'icon-page_excel',handler : expDDInfo},
	      		        {text : "导出Excel",iconCls : 'icon-page_excel',
			    			handler: function(){
								var url = "${path }/dmcjjc/bg/expXls";
			    				ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, "地面沉降监测点上报", dataGrid, false);
			    			}
		            	},
     		           	'-', 
    		            {text : "上报",iconCls : 'icon-exp',handler : shangbaoFun}
                ]
        });
    });
    
    function qyFun(){
    	jcqy('${path }/dmcjjc/bg/qy');
    }
    
    function jyFun(){
    	jcjy('${path }/dmcjjc/bg/jy');
    }
    
    function editFunNew(id) {
    	editDetailInfoFun('${path }/dmcjjc/bg/editPage','监测报告信息修改');
    }

    function deleteFunNew(id) {
    	deleteJcFun('${path }/dmcjjc/bg/del');
    }

    function searchFun() {
        dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
        dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
    }
    function cleanFun() {
        $('#gcbh').combobox('setValue','');
        $('#ifqr').combobox('setValue','');
        $('#ifsb').combobox('setValue','');
        $('#beginTime').datetimebox('setValue','');
        $('#endTime').datetimebox('setValue','');
        $('#searchForm')[0].reset();
        dataGrid.datagrid('load', {});
    }
    
    function shangbaoFun(){
    	parent.$.modalDialog({
            title : '请选择监测报告信息',
            width : 600,
            height : 400,
            href : '${path }/dmcjjc/bgsb/addPage',
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
    
    function addFun() {
        parent.$.modalDialog({
            title : '请选择日常监测信息',
            width : 600,
            height : 400,
            maximizable:true,
            href : '${path }/dmcjjc/bg/addPage',
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
    
    function newFun(id){
       $('#id').val(id);
       $('#newForm').submit();
       $('#win').window('close');
    }
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
<!-- 查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:140px;border: false;overflow: hidden;" data-options="collapsible:true">
		<form id="searchForm">
			<table class="grid" style="border: 0px">
				<tr>
					<td class="form-td-label" style="width: 80px">工程名称:</td>
					<td class="form-td-content" colspan="3" style="width: 480px">
						<select editable="false" id="gcbh" name="gcbh" data-options="prompt:'请输入工程名称'" class="easyui-combobox" style="width:100%;"></select>
					</td>
					<td></td>
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
        		<td></td>
        </tr>
        <tr>
	        <td class="form-td-label" >上报状态</td>
	        <td><select editable="false" id="ifsb" name="ifsb" class="easyui-combobox" style="width: 200px;">
	                        <option value="">--请选择--</option>
	                        <option value="已上报">已上报</option>
	                        <option value="未上报">未上报</option>
	                      </select>
	        </td>
        	<td class="form-td-label" style="width: 120px">确认状态:</td>
					<td class="form-td-content" style="width: 200px">
					<select editable="false" id="ifqr" name="ifqr" class="easyui-combobox"
						style="width: 200px;">
						<option value="">--请选择--</option>
						<option value="已确认">已确认</option>
						<option value="未确认">未确认</option>
					</select>
					</td>
				<td style="text-align: left;">
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a> 
						<a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-application_form_delete',plain:true" onclick="cleanFun();">重置</a>
					</td>
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
    <form id="download" name="download" style="display:" method="post" action="${path}/dmcjjc/bg/expBg">
    	<!-- 存放选中的记录id -->
    	<input type="type" id="ids" name="ids"/>
    </form>
    
    <!-- 点击添加时，选择日常监测信息的窗口 -->
    <div id="win" class="easyui-window" title="请选择日常监测数据" style="width:800px;height:500px"
    data-options="modal:true,closed:true,inline:false,resizable:false,
    maximizable:false,minimizable:false,collapsible:false">
        <form id='newForm' action='${path}/dmcjjc/bg/save' method="post">
        <input type="hidden" id='id' name='id'/>
        </form>
        <table id="jcinfoDG" data-options="fit:true,border:false"></table>
	</div>
	
	<div id="openDiv" class="easyui-window" data-options="modal:true,closed:true,inline:false,resizable:false,
maximizable:false,minimizable:false,collapsible:false" title="添加" style="width:1200px;height:600px;">
    <iframe id='openbgIframe' src="" frameborder="0" style="width:100%;height:98%;"></iframe>
	</div>
	
</body>
</html>