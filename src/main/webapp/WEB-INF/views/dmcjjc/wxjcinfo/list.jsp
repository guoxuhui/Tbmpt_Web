<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>无锡日常监测管理</title>
    <script type="text/javascript">
    var dataGrid;
    $(function() {
    	
        $('#gcbh').combobox({
            url : basePath + '/project/info/getProjects',
            valueField : "id",
            textField : "proName"
        });
    	
        dataGrid = $('#dataGrid').datagrid({
            url : '${path}/dmcjjc/dmcjwxjcinfo/list',
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
                    var str = $.formatString('<a href="javascript:void(0)" onclick="showFun(\'{0}\',\'{1}\',\'{2}\');" >{3}</a>', row.id,'${path}/dmcjjc/dmcjwxjcinfo/showPage','日常监测详细信息',value);
                    return str;
                }
            }]], 
            columns : [ [{
                width : '80',
                title : '天气',
                field : 'tianqi'
            } , {
                field : 'action',
                title : '导入文件下载',
                width : '150',
                formatter : function(value, row, index) {
                	if(row.impFilePath == null || row.impFilePath == ""){
						return "";
					}
                    var str = $.formatString('<a href="${path }/dmcjjc/dmcjwxjcinfo/download?filepath='+row.impFilePath+'">点击下载原始DAT</a>', row.id);
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
                $('.role-easyui-linkbutton-del').linkbutton({text:'删除',plain:true,iconCls:'icon-del'});
                },
            toolbar : [ 
					{text : '刷新',iconCls: 'icon-reload',
					    handler: function(){
					    	dataGrid.datagrid('reload');
					    }
					},
					'-',
		            {text : "导入",iconCls : 'icon-add',handler : addFun},
					'-',
		            {text : "删除",iconCls : 'icon-del',handler : deleteFunNew},
	              	'-', 
		            {text: '取消已选',iconCls: 'icon-undo',
		    			handler: function(){
		    				dataGrid.datagrid('clearSelections');
		    			}
		    		},
		    		'-',
		            {text : "生成报表",iconCls : 'icon-chart_curve',handler : reportGeneration}
               ]
          });
    });
    /*** 生成报表 */
    function reportGeneration(){
    	var checkedItems = $('#dataGrid').datagrid('getChecked');
    	var ids = [];
         
        $.each(checkedItems, function(index, item){ 
            ids.push(item.id);
        });
        if( 0 == ids.length ){
        	parent.$.messager.alert('提示', '请选择数据', 'info');
        	return;
        }
        if( ids.length > 1 ){
        	parent.$.messager.alert('提示', '只能选择一条数据', 'info');
        	return;
        }
        
        var gcbh = checkedItems[0].gcbh;
        var jcTime = checkedItems[0].jcTime;
        
        parent.addTab({
            title : "监测日报-"+jcTime,
            url : "dmcjjc/report/dmcjjcrbwxDo?gcBh="+gcbh+"&cxDate="+jcTime
        });
    }
 
    /*** 导入‘无锡日常监测信息’  */
    function addFun() {
    	FormUtil.clearForm($("#addDialog"));
        parent.$.modalDialog({
            title : '请选择导入文件[支持.xls,.DAT文件导入]',
            width : 400,
            height : 400,
            href : '${path }/dmcjjc/dmcjwxjcinfo/importPage',//跳转到上传文件导入页面
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
    
    function deleteFunNew(id) {
    	deleteWxJcFun('${path }/dmcjjc/dmcjwxjcinfo/del');
    	
    }
    // 删除无锡监测信息函数
    function deleteWxJcFun(url) {
    	var checkedItems = $('#dataGrid').datagrid('getChecked');
    	var ids = [];
         
        $.each(checkedItems, function(index, item){ 
            ids.push(item.id);
        });
        if( 0 == ids.length){
        	parent.$.messager.alert('提示', '请选择数据', 'info');
        	return;
        }
         
        parent.$.messager.confirm('询问', '您是否要删除当前所选数据？', function(b) {
            if (b) {
                progressLoad();
                $.post(url, {
                    ids : ids.toString()
                }, function(result) {
                    if (result.success) {
                        parent.$.messager.show({title:'提示',msg:result.msg,showType:'show' });
                        dataGrid.datagrid('reload');
                        dataGrid.datagrid('clearSelections');
                    } 
                    progressClose();
                }, 'JSON');
            }
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
					<td class="form-td-label" style="width: 80px" colspan="2"></td>
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
				<td style="text-align: left;" colspan="2">
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