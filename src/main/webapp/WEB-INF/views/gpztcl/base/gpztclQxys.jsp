<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<style type="text/css">
	td{
		 white-space: nowrap;
	}
	td input{
		white-space: normal;
	}
</style>
<script type="text/javascript">

/*
 * “设置曲线要素”窗体页面
 */

 

/** 系统模块同路径 */
var syspath = "gpztcl";
/** 子模块路径 */
var module = "base";
var path = basePath +"/"+ syspath +"/"+ module;
/** 设置曲线要素 控制台 路径  */
var qxysPath=basePath +"/"+ syspath +"/"+ module+"/qxys";
//声明数据表单对象
var dataGrid;
/** 用于判断 列表行 双击 后的状态，  */
var editRow = undefined;
//用于保存主表 线路Id
var FId;

$(function() {
	
	//获取 主表 线路Id
	FId =${xlBh};
	//获取 子表数据集合
	var list=${qxyslist};
	//赋值 编辑 表单 父Id
	$("#edit_XlBh").val(FId);
	
	//数据表单
	dataGrid = $('#dataGrid').datagrid({
        //url : detailData,
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
            width : '120',
            title : '变坡点桩号(K)',
            field : 'bpdzh',
            sortable : false,
            sortable : false,
            editor:{
                type:'numberbox',
                options:{precision:6}
              }
        },{
        	width : '120',
            title : '变坡点高程(m)',
            field : 'bpdgc',
            sortable : false,
            editor:{
                  type:'numberbox',
                  options:{precision:6}
                }
        } , {
        	width : '120',
            title : '设计曲线半径R(m)',
            field : 'sjqxbj',
            sortable : false,
            editor:{
                  type:'numberbox',
                  options:{precision:6}
                }
        },{
        	width : '200',
            title : '备注',
            field : 'remarks',
            sortable : false,
            editor: {
         	   type: 'textbox'
            }
        }]],
        onLoadSuccess:function(data){
        	
        },
        onDblClickRow: function (rowIndex, rowData) {
        	   //列表表单 双击事件 
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
	//打开 “设置曲线” 页面时，把后台传来的集合，赋值给，列表；
	$('#dataGrid').datagrid('loadData', list);
	
	/***
	 * 列表表单为“编辑表单”，发生保存按钮事件；
	 */
	$('#editForm').form({
		url : '${path}/gpztcl/base/qxys/save',
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
	
});
 
/***
 * 当前页面 增、删、改，都是通过 “主表保存按钮”进行统一保存，保存时，先：根据主表Id删掉子表信息，再：重新保存一遍；
 */
 
/**
 *打开新增明细的窗口
 */
var _rowIndex;
function openWin(flag,rowIndex){
	
	_rowIndex = rowIndex;
	
	//打开添加页面时   清空 添加  页面 数据
	//resetDetailWin();
	
	//打开添加页面时   重置  或 清空 添加  页面 数据
	FormUtil.clearForm($("#addQxys"));
	
	 //打开新增明细的窗口
    $('#addQxys').window('open');
  
     detailRemarks();
    
}  
/**
 * 清空表单
 */
function resetDetailWin(){
	//把已有的值清掉
	$('#addQxys_bpdzh').numberbox('setValue','');
	$('#addQxys_bpdgc').numberbox('setValue','');
	$('#addQxys_sjqxbj').numberbox('setValue','');
	$('#addQxys_remarks').numberbox('setValue','');
}
/**
 * 判断 添加提交保存 是否为空
 */
function btnCon(){
	var bpdzh = $('#addQxys_bpdzh').val();
	var bpdgc = $('#addQxys_bpdgc').val();
	var sjqxbj = $('#addQxys_sjqxbj').val();
	if(bpdzh==""&& bpdgc==""&&sjqxbj==""){
		return true;
	}else{
		return false;
	}
		
}

/**
 * 新增保存事件
 */
function btnConfirm(){
	var isValid = $('#addForm').form('validate');
	//判断 添加提交保存 是否为空
	if (!isValid || btnCon()) {
		progressClose();
	}
	else{
	  var data={
		bpdzh: $('#addQxys_bpdzh').val(),
		bpdgc:$('#addQxys_bpdgc').val(),
		sjqxbj:$('#addQxys_sjqxbj').val(),
		remarks:$('#addQxys_remarks').val(),
    }
    //把添加的信息，显示到 列表表单
	addRow(data);
	}
	//说明 当前 表格被修改过
	$('#dataSta').val('modified');
}
/**  
 * 
 *
 * 更新数据列表
 */
function addRow(obj){
	//把添加的信息，显示到 列表表单
	$('#dataGrid').datagrid('appendRow',{	
		bpdzh:obj.bpdzh,
		bpdgc:obj.bpdgc,
		sjqxbj:obj.sjqxbj,
		remarks:obj.remarks,
		
   });
	 //关闭新增明细的窗口
	$('#addQxys').window('close');
}



/**
 * 列表保存事件
 */
function saveFun(){
	//声明集合
	var detailsInfo=new Array();
	//获取列表数据
  	var rows = $("#dataGrid").datagrid("getRows");
  	if (editRow != undefined) {
        $("#dataGrid").datagrid('endEdit', editRow);
    }
  	for(var i=0;i<rows.length;i++)	{
		var obj = new Object();
		obj.FId = FId;//路线主表Id，FId:全局变量；
		obj.bpdzh = rows[i].bpdzh;
		obj.bpdgc = rows[i].bpdgc;
		obj.sjqxbj = rows[i].sjqxbj;
		obj.remarks = rows[i].remarks;
		detailsInfo[i] = obj;
	}
	
	var json = JSON.stringify(detailsInfo);
	$('#details').val(json);
	var isValid = $('#editForm').form('validate')
	if(isValid){
		//列表表单为“编辑表单”，发生按钮事件；
    	$('#editForm').submit();
	}else{
		progressClose();
	}
}



/**
 * 删除数据
 */
function deleteFun() {
	//声明集合
	var ids = new Array();
	//获取选中的数据行“集合”；
	var rows = dataGrid.datagrid('getSelections');
	if (rows.length == 0) {
		$.messager.alert('提示', '请选择数据后再删除！', 'info');
		return;
	}
	for(var i=rows.length-1; i>=0; i--){
		var index=dataGrid.datagrid('getRowIndex',rows[i]);
		//删除 第index行数据；
		dataGrid.datagrid('deleteRow',index);
	}
	
}

/**
 * excel导出
 */
function expGpztclSjzxInfoXls(){
	var href = qxysPath + "/expXls?FId="+FId;
	ExportUtil.doExport(href, ExportUtil.TYPE_XLS_2003, "曲线要素信息", dataGrid, false);
	var url = path + "/expXls";
	
}

</script>

    <!-- “设置曲线要素”窗体页面 -->

<div class="easyui-layout" data-options="fit:true,border:false">
	<div class="easyui-panel" title="线路信息" data-options="region:'north',border:false,collapsible:true"  
		style="width:100%;height:100px;border: false;overflow: hidden;">
		<form id="editForm" method="post">
		    <!-- 用于表单双击修改 ，传到后台判断是否被修改过；默认值：value='modified'，当前页面不用！-->
		    <!-- <input id="dataSta" name="modified" value='modified' type="hidden" > -->
		    <!-- 该 标签为隐藏标签， 作：保存“子表集合”数据；表单发生提交事件时赋值，并提交表单数据；在saveFun()提交事件方法赋值； -->
			<input id='details' name='details' type='hidden' /> 
  		    <input id='edit_XlBh' name='XlBh' type='hidden' />
			<table class="grid">
				  <tr>
				    <td  class="form-td-label"  style="width:100px">项目名称：</td>
				    <td class="form-td-content" colspan="3">
				      	<span id="Qxys_projectName" style="width: 400px;margin-right: 15px">${proName}</span>
				    </td>
				  </tr>
				  <tr>
				    <td  class="form-td-label"  style="width:100px">区间名称：</td>
				    <td class="form-td-content" >
				      	<span id="Qxys_qujianName" style="width: 300px;margin-right: 15px">${sectionName}</span>
				    </td>
				    <td  class="form-td-label"  style="width:100px">线路名称：</td>
				    <td class="form-td-content" >
				      	<span id="Qxys_lineName" style="width: 200px;margin-right: 15px">${lineName}</span>
				    </td>
				  </tr>
				</table>
		</form>
	</div>
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

 

  <!-- 新增 曲线要素 的窗口 -->
<div id="addQxys" class="easyui-window" title="新增曲线要素"
	style="width:600px;height:300px;"
	data-options="modal:true,closed:true,inline:false,resizable:false,
maximizable:false,minimizable:false,collapsible:false">
	<form id="addForm" method="post" data-options="novalidate:true">
		<table class="grid">
			<tr >
			     <td  class="form-td-label" ><span style="color: red;">*</span>变坡点 桩号(K)：</td>
			     <td>
			      <input name='bpdzh'  class="easyui-numberbox" precision="6" min="0.000001" max="100000000" class="easyui-validatebox span2" id='addQxys_bpdzh'  data-options="prompt:'请输入变坡点 桩号',validType:'length[1,40]'"  style='width: 100%;'>
			     </td>
			  
			     <td  class="form-td-label" ><span style="color: red;">*</span>变坡点 高程(m)：</td>
			     <td class="form-td-content" >
			       <input name='bpdgc' class="easyui-numberbox" precision="6" min="0.000001" max="100000000" class="easyui-validatebox span2" id='addQxys_bpdgc'  data-options="prompt:'请输入变坡点 高程(m)',validType:'length[1,40]'"  style='width: 100%;'>
			     </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label" ><span style="color: red;">*</span>设计曲线半径R(m)：</td>
			    <td class="form-td-content" >
			      <input  name='sjqxbj' id='addQxys_sjqxbj'  class="easyui-numberbox" precision="6" min="0.0000001" max="100000000" class="easyui-validatebox span2" data-options="prompt:'请输入设计曲线半径R(m)',validType:'length[1,40]'"  style='width: 100%;'>
			    </td>
			  </tr>
			  <tr>
			    <td  class="form-td-label"  valign="top" align="right" >备注：</td>
			    <td class="form-td-content"  colspan='3'>
			      <input name='remarks'  id='addQxys_remarks'  class="easyui-textbox" class="easyui-numberbox" class="easyui-validatebox span2"  data-options="prompt:'请输入备注',multiline:true,validType:'length[1,40]'"  style='width: 100%;height:100px'>
			    </td>
			  </tr>
			<tr></tr>
			<tr style="margin-top:20px ">
				<td colspan="4" style="text-align: right;">
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="btnConfirm()" style="width:15%">确定</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:FormUtil.clearForm($('#addQxys'))" style="width:15%">重置</a>
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#addQxys').dialog('close')" style="width:15%">关闭</a>
					<input type="hidden" id="flag" /> <input type="hidden" id="detailId" />
				</td>
			</tr>
		</table>
	</form>
	
</div>
   
