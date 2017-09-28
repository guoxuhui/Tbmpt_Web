<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>监测报告上报管理</title>
    <script type="text/javascript">
    var dataGrid;
    $(function() {
    	
        $('#gcbh').combobox({
            url : basePath + '/project/info/getProjects',
            valueField : "id",
            textField : "proName"
        });

        dataGrid = $('#dataGrid').datagrid({
            url : '${path }/dmcjjc/bgsb/list',
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
                    //var str = $.formatString('<a href="${path}/dmcjjc/bgsb/showPage?id='+row.id+'&jcTime={0}">'+value+'</a>', value);
                    //return str;
                    var str = $.formatString('<a href="javascript:void(0)" onclick="showFun(\'{0}\',\'{1}\',\'{2}\',\'{3}\',\'{4}\');" >{5}</a>', row.id,'${path}/dmcjjc/bgsb/showPage','监测报告上报详细信息',900,500,value);
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
                width : '320',
                title : '备注',
                field : 'remarks',
                align:'left',halign:'center',
                sortable : false,
            }] ],
            toolbar : [ 
						{text : '刷新',iconCls: 'icon-reload',
						    handler: function(){
						    	dataGrid.datagrid('reload');
						    }
						},
						 '-',
	     		        {text : "编辑",iconCls : 'icon-edit',handler : editFunNew}, 
						'-',
						{text: '取消已选',iconCls: 'icon-undo',
			    			handler: function(){
			    				dataGrid.datagrid('clearSelections');
			    			}
			    		},
			            '-',
      		            //{text : "导出Excel",iconCls : 'icon-page_excel',handler : expDDInfo},
	      		        {text : "导出Excel",iconCls : 'icon-page_excel',
			    			handler: function(){
								var url = "${path }/dmcjjc/bgsb/expXls";
			    				ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, "地面沉降监测点报告", dataGrid, false);
			    			}
		            	},
      		            '-',
	  		            {text : "删除",iconCls : 'icon-del',handler : delFun},
	  		            '-', 
	  		         	{text : "确认",iconCls : 'icon-ok',handler : qyFun},
	  	            	'-', 
	  	            	{text : "撤销确认",iconCls : 'icon-no',handler : jyFun}
                 ]
        });
    });
    function editFunNew(id) {
    	editDetailInfoFun('${path }/dmcjjc/bgsb/editPage','监测报告上报信息修改');
    }
    //报告确认
    function qyFun(){
    	jcqy('${path }/dmcjjc/bgsb/qy');
    }
    //报告撤销确认
    function jyFun(){
    	jcjy('${path }/dmcjjc/bgsb/jy');
    }
    //删除上报报告
    function delFun() {
    	deleteJcFun('${path }/dmcjjc/bgsb/del');
    }
    function searchFun() {
        dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
        dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
    }
    function cleanFun() {
        $('#gcbh').combobox('setValue','');
        $('#beginTime').datetimebox('setValue','');
        $('#endTime').datetimebox('setValue','');
        $('#searchForm')[0].reset();
        dataGrid.datagrid('load', {});
    }
    
</script>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
 <!-- 查询表单 -->
	<div class="easyui-panel" title="查询" data-options="region:'north',border:false"  style="width:100%;height:100px;border: false;overflow: hidden;" data-options="collapsible:true">
		<form id="searchForm">
			<table class="grid" style="border: 0px">
				<tr>
					<td class="form-td-label" style="width: 80px">工程名称:</td>
					<td class="form-td-content" colspan="3" style="width: 200px">
						<select editable="false" id="gcbh" name="gcbh" class="easyui-combobox" data-options="prompt:'请输入工程名称'" style="width: 100%;"></select>
					</td>
					<td></td>
				</tr>
				<tr>
					<td class="form-td-label" >起始监测时间:</td>
					<td><input editable="false" id="beginTime" name="beginTime" class="easyui-datetimebox" data-options="prompt:'起始监测时间'" style="width:100%;">
	        		</td>
					<td class="form-td-label" >截止监测时间:</td>
					<td><input editable="false" id="endTime" name="endTime" class="easyui-datetimebox" data-options="prompt:'截止监测时间'" style="width:100%;">
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
    <form id="download" name="download" style="display:" method="post" action="${path}/dmcjjc/bgsb/expBgsb">
    	<!-- 存放选中的记录id -->
    	<input type="type" id="ids" name="ids"/>
    </form>
</body>
</html>