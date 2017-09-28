<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
var syspath = "gpztcl";
var module = "base";
var path = basePath +"/"+ syspath +"/"+ module;
/**
 * 配置平曲线要素路径
 */
var xyfysPath=basePath +"/"+ syspath +"/"+ module+"/gpztclXyfys";
var dataGrid;
var editRow = undefined;
/**
 * 项目id
 */
var proId;
/**
 * 区间id
 */
var sectionId;
/**
 * 线路id
 */
var id;
$(function() {
		proId=${proId};
		sectionId=${sectionId};
		id=${id};
		xy=${xy};
		$("#proId").val(proId);
		$("#sectionId").val(sectionId);
		$("#xlId").val(id);
		var proName=xy.proName;
		var sectionName=xy.sectionName;
		var lineName=xy.lineName;
		$("#proName").html(proName);
		$("#sectionName").html(sectionName);
		$("#lineName").html(lineName);
		var list=${list};
		
		$('#addForm').form({
    		url : '${path}/gpztcl/base/gpztclXyfys/save',
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
                parent.$.modalDialog.handler.dialog('close');
            } else {
                parent.$.messager.alert('错误', result.msg, 'error');
            }
        }
    });
    	dataGrid = $('#dataGrid').datagrid({
    	        
    	        striped : true,
    	        rownumbers : true,
    	        pagination : false,//这里的数据是从文件解析出来的，不能分页，所有不要分页，全查出
    	        singleSelect : false,
    	        idField : 'id',
    	        pageSize : 10,
    	        pageList : [ 10, 15, 20, 25, 100],
    	        frozenColumns : [ [ { 
                	field:'ck',checkbox:true 
                },{
    	            width : '110',
    	            title : '起点桩号',
    	            field : 'qdZh',
    	            align:'right',
    	            sortable : false,
    	            editor:{
   	                   type:'numberbox',
   	                   options:{precision:10}
   	                 }
    	        },{
    	        	width : '110',
    	            title : '止点桩号',
    	            field : 'zdZh',
    	            align:'right',
    	            sortable : false,
    	            editor:{
   	                   type:'numberbox',
   	                   options:{precision:10}
   	                 }
    	        } , {
    	        	width : '110',
    	            title : '起点坐标X',
    	            field : 'qdZbX',
    	            align:'right',
    	            sortable : false,
    	            editor:{
   	                   type:'numberbox',
   	                   options:{precision:10}
   	                 }
    	        }]],
    	        columns : [ [{
    	        	width : '110',
    	            title : '起点坐标Y',
    	            field : 'qdZbY',
    	            align:'right',
    	            sortable : false,
    	            editor:{
    	                   type:'numberbox',
    	                   options:{precision:10}
    	                 }
    	        } , {
    	        	width : '110',
    	            title : '起点方位角',
    	            field : 'qdFwj',
    	            align:'right',
    	            sortable : false,
    	            editor:{
    	                   type:'numberbox',
    	                   options:{precision:10}
    	                 }
    	        } , {
    	        	width : '60',
    	            title : '起点半径',
    	            field : 'qdBj',
    	            align:'center',
    	            sortable : false,
    	            editor:{
    	                   type:'numberbox',
    	                   options:{precision:5}
    	                 }
    	        } , {
    	        	width : '80',
    	            title : '止点半径',
    	            field : 'zdBj',
    	            align:'right',
    	            sortable : false,
    	            editor:{
  	                   type:'numberbox',
  	                   options:{precision:5}
  	                 }
    	        } , {
    	        	width : '100',
    	            title : '转向(右:1/左:-1)',
    	            field : 'zx',
    	            align:'right',
    	            sortable : false,
    	            editor:{
    	                   type:'numberbox',
    	                   options:{precision:5}
    	                 }
    	        }, {
    	        	width : '100',
    	            title : '起点偏移量',
    	            field : 'qdPyl',
    	            align:'right',
    	            sortable : false,
    	            editor:{
    	                   type:'numberbox',
    	                   options:{precision:6}
    	                 }
    	        }, {
    	        	width : '100',
    	            title : '止点偏移量',
    	            field : 'zdPyl',
    	            align:'right',
    	            sortable : false,
    	            editor:{
    	                   type:'numberbox',
    	                   options:{precision:6}
    	                 }
    	        }] ],
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
		    
		               }
		           },
		           onAfterEdit: function (rowIndex, rowData, changes) {
		               editRow = undefined;		               		        
		               $('#dataSta').val('modified');
		               $('#dataGrid').datagrid('refreshRow', rowIndex);
		           },
    	        toolbar : '#toolbar'
    	    });
    	 $('#dataGrid').datagrid('loadData', list);
    	 
   });
var _rowIndex;
function openWin(flag,rowIndex){
	_rowIndex = rowIndex;
	resetDetailWin();   
    $('#win').window('open');
}  

/**
 * 新增保存事件
 */
function btnConfirm(){
	
	var isValid = $('#newForm').form('validate');
	if (!isValid) {
		progressClose();
	}
	else{
		var data={
        qdZh: $('#qdZh').val(),
        zdZh:$('#zdZh').val(),
        bcgc:$('#bcgc').val(),
        qdZbX:$('#qdZbX').val(),
        qdZbY:$('#qdZbY').val(),
        qdFwj:$('#qdFwj').val(),
        qdBj:$('#qdBj').val(),
        zdBj:$('#zdBj').val(),
        zx:$('#zx').val(),
        qdPyl:$('#qdPyl').val(),
        zdPyl:$('#zdPyl').val()
    }
	addRow(data);
	}
	$('#dataSta').val('modified');
}

/**
 * 更新数据列表
 */
function addRow(obj){
	$('#dataGrid').datagrid('appendRow',{		
		qdZh:obj.qdZh,
		zdZh:obj.zdZh,
		qdZbX:obj.qdZbX,
		qdZbY:obj.qdZbY,
		qdFwj:obj.qdFwj,
		qdBj:obj.qdBj,
		zdBj:obj.zdBj,
		zx:obj.zx,
		qdPyl:obj.qdPyl,
		zdPyl:obj.zdPyl
});
	$('#win').window('close');
}

/**
 * 主表保存事件
 */
function saveFun(){
	var detailsInfo=new Array();
  	var rows = $("#dataGrid").datagrid("getRows");
  	if (editRow != undefined) {
        $("#dataGrid").datagrid('endEdit', editRow);
    }
  	for(var i=0;i<rows.length;i++)	{
		var obj = new Object();
		obj.xlId=id;
		obj.qdZh=rows[i].qdZh;
		obj.zdZh=rows[i].zdZh;
		obj.qdZbX=rows[i].qdZbX;
		obj.qdZbY=rows[i].qdZbY;
		obj.qdFwj=rows[i].qdFwj;
		obj.qdBj = rows[i].qdBj;
		obj.zdBj = rows[i].zdBj;
		obj.zx = rows[i].zx;
		obj.qdPyl = rows[i].qdPyl;
		obj.zdPyl = rows[i].zdPyl;
		detailsInfo[i] = obj;
	}
	
	var json = JSON.stringify(detailsInfo);
	$('#details').val(json);
	var isValid = $('#addForm').form('validate')
	if(isValid){
    	$('#addForm').submit();
	}else{
		progressClose();
	}
}

/**
 * 删除数据
 */
function deleteFun() {
	var ids = new Array();
	var rows = dataGrid.datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择数据后再删除！', 'info');
		return;
	}
	for(var i=rows.length-1; i>=0; i--){
		var index=dataGrid.datagrid('getRowIndex',rows[i]);
		dataGrid.datagrid('deleteRow',index);
	}
	$('#dataSta').val('modified');
	
}

/**
 * excel导出
 */
function expGpztclSjzxInfoXls(){
	var href = xyfysPath + "/expXls?id="+id;
	ExportUtil.doExport(href, ExportUtil.TYPE_XLS_2003, "平曲线要素表", dataGrid, false);
}
/**
 * 清空表单
 */
function resetDetailWin(){
	//把已有的值清掉
	$("#qdZh").numberbox('setValue','');
	$("#zdZh").numberbox('setValue','');
    $('#qdZbX').numberbox('setValue','');
    $('#qdZbY').numberbox('setValue','');
    $('#qdFwj').numberbox('setValue','');
    $('#qdBj').numberbox('setValue','');
    $('#zdBj').numberbox('setValue','');
    $('#qdPyl').numberbox('setValue','');
    $('#zdPyl').numberbox('setValue','');
    $('#zx').numberbox('setValue','');
}

</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div class="easyui-panel" title="线路信息" data-options="region:'north',border:false,collapsible:true"  
		style="width:100%;height:100px;border: false;overflow: hidden;">
		<form id="addForm" method="post">
		<input type="hidden" id="dataSta" value='' />
		<input type='hidden' id='details' name='details'/>
  		<input type="hidden" id="proId" name="proId"/>
  		<input type="hidden" id="sectionId" name="sectionId"/>
  		<input type="hidden" id="xlId" name="xlId"/>
			<table class="grid">
				<tr>
					<td class="form-td-label" style="width:100px">项目名称：</td>
                	<td class="form-td-content" colspan="3" >
                		<span id="proName" name="proName" style="width: 400px;margin-right: 15px"></span>
                		<!-- 
                    	<input id="proName" name="proName" class="easyui-textbox" style="width: 100%;margin-right: 15px">
                    	 -->
                	</td>				
				</tr>
				<tr>
					<td class="form-td-label" style="width:100px">区间名称：</td>
					<td style="width:200px" >
						<span id="sectionName" name="sectionName" style="width: 300px;margin-right: 15px"></span>
						<!-- 
						<input id="sectionName" name="sectionName" class="easyui-textbox" style="width: 100%;margin-right: 15px">
						 -->
					</td>
					<td class="form-td-label" style="width:100px">线路名称：</td>
					<td style="width:200px" >
						<span id="lineName" name="lineName" style="width: 200px;margin-right: 15px"></span>
						<!-- 
						<input id="lineName" name="lineName" class="easyui-textbox" style="width: 100%;margin-right: 15px">
						 -->
					</td>
				</tr>
				
			</table>
		</form>
	</div>
	<!-- 明细信息表格 -->
	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100px;">
		<table id="dataGrid" title="列表<font color='red'>(*鼠标双击可直接编辑*)</font>" data-options="fit:true,border:false"></table>
		<div id="toolbar">
		<a onclick="openWin('insert',0);" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="plain:true,iconCls:'icon-add'">新增明细</a>
			 <a
			onclick="deleteFun();" href="javascript:void(0);"
			class="easyui-linkbutton"
			data-options="plain:true,iconCls:'icon-del'">删除明细</a>
			<a onclick="expGpztclSjzxInfoXls();" href="javascript:void(0);"  id="dataGrid_gpztclSjzxInfo_button_ExpExcel" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a> 
			<a
			id="saveBtn" style="display:none" onclick="saveFun()" href="javascript:void(0);"
			class="easyui-linkbutton" 
			data-options="plain:true,iconCls:'icon-save'">保存</a>
		</div>
	</div>
	
</div>

<!-- 新增明细时弹出的窗口 -->
<div id="win" class="easyui-window" title="平曲线要素数据"
	style="width: 550px; height: 300px"
	data-options="modal:true,closed:true,inline:false,resizable:false,
maximizable:false,minimizable:false,collapsible:false">
	<form id="newForm" method="post" data-options="novalidate:true">
		<table class="grid">
			<tr>
				<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>起点桩号：</td>
                	<td class="form-td-content" style="width:100px">
                    	<input id="qdZh" name="qdZh" class="easyui-numberbox" data-options="required:true,precision:10"   style="width: 100%;margin-right: 15px">
                	</td>
                <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>止点桩号：</td>
                	<td class="form-td-content" style="width:100px">
                    	<input id="zdZh" name="zdZh" class="easyui-numberbox" data-options="required:true,precision:10" style="width: 100%;margin-right: 15px">
                	</td>
			</tr>
			<tr>
				<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>起点坐标(X)：</td>
                	<td class="form-td-content"  style="width:100px">
                    	<input id="qdZbX" name="qdZbX" class="easyui-numberbox" data-options="required:true,precision:10" style="width: 100%;margin-right: 15px">
                	</td>
                <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>起点坐标(Y)：</td>
                	<td class="form-td-content"  style="width:100px">
                    	<input id="qdZbY" name="qdZbY" class="easyui-numberbox" data-options="required:true,precision:10" style="width: 100%;margin-right: 15px">
                	</td>
			</tr>
			<tr>
				<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>起点方位角：</td>
                	<td class="form-td-content"  style="width:100px">
                    	<input id="qdFwj" name="qdFwj" class="easyui-numberbox" data-options="required:true,precision:10" style="width: 100%;margin-right: 15px">
                	</td>
                <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>起点半径：</td>
                	<td class="form-td-content"  style="width:100px">
                    	<input id="qdBj" name="qdBj" class="easyui-numberbox" data-options="required:true,precision:5" style="width: 100%;margin-right: 15px">
                	</td>
			</tr>
			<tr>
				<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>止点半径：</td>
                	<td class="form-td-content"  style="width:100px">
                    	<input id="zdBj" name="zdBj" class="easyui-numberbox" data-options="required:true,precision:5" style="width: 100%;margin-right: 15px">
                	</td>
                <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>转向(右:1/左:-1)：</td>
                	<td class="form-td-content"  style="width:100px">
                    	<input id="zx" name="zx" class="easyui-numberbox" data-options="required:true,precision:0" style="width: 100%;margin-right: 15px">
                	</td>
			</tr>
			<tr>
				<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>起点偏移量：</td>
                	<td class="form-td-content"  style="width:100px">
                    	<input id="qdPyl" name="qdPyl" class="easyui-numberbox" data-options="required:true,precision:6" style="width: 100%;margin-right: 15px">
                	</td>
                <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>止点偏移量：</td>
                	<td class="form-td-content"  style="width:100px">
                    	<input id="zdPyl" name="zdPyl" class="easyui-numberbox" data-options="required:true,precision:6" style="width: 100%;margin-right: 15px">
                	</td>
			</tr>
			<tr></tr>
			<tr style="margin-top:20px ">
				<td colspan="4" style="text-align: right;">
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="btnConfirm()" style="width:15%">确定</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeDetailWin()" style="width:15%">关闭</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="resetDetailWin()" style="width:15%">重置</a>
					<input type="hidden" id="flag" /> <input type="hidden" id="detailId" />
				</td>
			</tr>
		</table>
	</form>
</div>