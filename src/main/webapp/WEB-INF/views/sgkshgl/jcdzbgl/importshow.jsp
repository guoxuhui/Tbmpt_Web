<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<div class="easyui-layout" data-options="fit:true,border:false" >
<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100px;">
	<form id="addForm" method="POST">
	<input type='hidden' id='details' name='details' />
	</form>
		<table id="dataGrid" title="列表" data-options="fit:true,border:false"></table>
		<div id="toolbar">
		<a onclick="reloadFun();" href="javascript:void(0);" id="reload_button" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
		<a onclick="clearSelections();" href="javascript:void(0);" id="clear_button" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-undo'">取消已选</a>
		<a
			id="saveBtn" style="display:none" onclick="saveFun()" href="javascript:void(0);"
			class="easyui-linkbutton" 
			data-options="plain:true,iconCls:'icon-save'">保存</a>
		</div>
</div>
</div>
<script type="text/javascript">
var dataGrid;
var editRow = undefined;
var list
$(function(){
list=${list};
$('#addForm').form({
	url : '${path}/sgkshgl/jcdzbgl/saveImport',
	onSubmit : function() {
    progressLoad();
    var isValid = $(this).form('validate');
    if (!isValid) {
        progressClose();
    }
    return isValid;
},
success : function(result) {
    progressClose();
    result = $.parseJSON(result);
    if (result.success) {
    	 $('#dataSta').val('original');
    	parent.$.messager.show({title:'提示',msg:result.msg,showType:'show' });
        parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
        parent.$.modalDialog.handler.dialog('close');
    } else {
        parent.$.messager.alert('错误', result.msg, 'error');
    }
}
});

dataGrid = $('#dataGrid').datagrid({
    striped : true,
    rownumbers : true,
    pagination : false,
    singleSelect : false,
    idField : 'id',
    pageSize : 20,
    pageList : [ 10, 15, 20, 25, 50],
    frozenColumns : [ [ { 
    	field:'ck',checkbox:true 
    },
/*     {
        width : '100',
        title : 'OBJECTID',
        field : 'objectid',
        sortable : false,
        formatter : function(value, row, index) {
            var str = $.formatString('<a href="javascript:void(0)" onclick="showFun(\'{0}\',\'{1}\',\'{2}\',600,400);" >{3}</a>', row.id,basePath+'/dmcjjc/dmcjjcpoint/showPage','监测点详细信息',value);
            return str;
        }
    }, */
    {
        width : '220',
        title : '项目',
        field : 'proName',
        sortable : false
    },{
        width : '220',
        title : '区间',
        field : 'secName',
        sortable : false
    }]], 
    columns : [ [{
        width : '80',
        field : 'jcId',
        title : '点位编号',
        sortable : false,
    },{
        width : '220',
        field : 'jcType',
        title : '点位类型',
        sortable : false,
    },{
        width : '80',
        field : 'lc',
        title : '里程',
        sortable : false,
    } , {
        width : '80',
        field : 'hh',
        title : '环号',
        sortable : false,
    } , {
        width : '80',
        field : 'xx',
        align:'right',halign:'center',
        title : '报警下限',
        sortable : false,
    } , {
        width : '80',
        field : 'sx',
        title : '报警上限',
        sortable : false,
    }, {
        width : '100',
        field : 'x',
        title : 'X',
        sortable : false,
        editor:{
               type:'numberbox',
               options:{precision:6}
             }
    } , {
        width : '100',
        field : 'y',
        title : 'Y',
        sortable : false,
        editor:{
               type:'numberbox',
               options:{precision:6}
             }
    } , {
        width : '100',
        field : 'z',
        title : 'Z',
        sortable : false,
        editor:{
               type:'numberbox',
               options:{precision:6}
             }
    } ,{
        width : '100',
        field : 'bz',
        title : '备注',
        sortable : false,
        editor:{
        	type:'textbox'        	
        }
    } ] ],
    onLoadSuccess:function(data){
    },
    onDblClickRow: function (rowIndex, rowData) {
        if (editRow != undefined) {
            $("#dataGrid").datagrid('endEdit', editRow);
        }
        if (editRow == undefined) {
            $("#dataGrid").datagrid('beginEdit', rowIndex);
            editRow = rowIndex;
        }
    },
    onClickRow: function (rowIndex, rowData) {
        if (editRow != undefined) {
            $("#dataGrid").datagrid('endEdit', editRow);
            editRow = undefined;
        }
    },
    onAfterEdit: function (rowIndex, rowData, changes) {
        editRow = undefined;		               		        
        $('#dataGrid').datagrid('refreshRow', rowIndex);
    },
    toolbar:'#toolbar'
});
	$('#dataGrid').datagrid('loadData', list);
});

/**
 * 刷新
 */
function reloadFun(){
	$('#dataGrid').datagrid('loadData', list);
}
/**
 * 取消已选
 */
function clearSelections(){
	dataGrid.datagrid('clearSelections');
}

function saveFun(){
	var detailsInfo=new Array();
	var rows = $("#dataGrid").datagrid("getRows");
	if (editRow != undefined) {
        $("#dataGrid").datagrid('endEdit', editRow);
    }
	for(var i=0;i<rows.length;i++)	{
		var obj = new Object();
		obj.objectid=rows[i].objectid;
		obj.proId=rows[i].proId;
		obj.secId=rows[i].secId;
		obj.jcId=rows[i].jcId;
		obj.jcType=rows[i].jcType;
		obj.lc = rows[i].lc;
		obj.hh = rows[i].hh;
		obj.xx = rows[i].xx;
		obj.sx = rows[i].sx;
		obj.x = rows[i].x;
		obj.y = rows[i].y;
		obj.z = rows[i].z;
		obj.bz = rows[i].bz;
		detailsInfo[i] = obj;
	}
	
	var json = JSON.stringify(detailsInfo);
	$('#details').val(json);
	var isValid = $('#addForm').form('validate')
    $('#addForm').submit();

}
</script>