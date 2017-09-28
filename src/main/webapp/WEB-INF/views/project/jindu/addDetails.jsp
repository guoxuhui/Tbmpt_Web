<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
/**
 * 项目管理系统 区间线路管理模块
 * 
 * 创建时间：2016年11月05日 
 * 
 */
/**
 * 系统模块同路径
 */
var syspath = "project";
/**
 * 子模块路径
 */
var module = "jindu";
/**
 * 模块名称
 */
var moduleName = "施工日进度";
/**
 * 名称字段
 */
var nameField = "jinduName";
/**
 * 业务数据访问全路径
 */
var path = basePath +"/"+ syspath +"/"+ module;
/**
 * 加载数据表格
 * 
 */
 var dataGrid;
 //日期
 var rq;
 //项目名称
 var proName;
 //项目id
 var proId;
 //编辑的行数
 var editRow = undefined;
 //明细
 var details=new Array();
 //区间名称
 var froName;
 //区间id
 var qjId;
 //线路名称
 var xlName;
 //线路id
 var xlId;
 
 $(function() {
	 
	 var detailData = ${list};
	 rq=detailData[0].rq;
	 proName=detailData[0].proName;
	 proId=detailData[0].proId;
	 
	 $('#rq').val(rq);
	 $('#proName').val(proName);
	 
	 
	 $('#addForm').form({
			url : path+'/edit',
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
					parent.$.messager.show({title:'提示',msg:result.msg,showType:'show' });
	             parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
	             parent.$.modalDialog.handler.dialog('close');
				}
				else {
					$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
	 
	dataGrid = $('#dataGrid').datagrid({
		striped : true,
        rownumbers : false,
        pagination : false,
        singleSelect : false,
        idField : 'id',
        pageSize : 10,
        pageList : [ 10, 15, 20, 25, 50],
	columns : [ [
		 {field : 'ck',checkbox : true},
         {width : '320',title : '工程名称',field : 'proName',align:'center'}, 
         {width : '100',title : '区间名称',field : 'sectionName',align:'center',
        	 formatter:formatterln,
        	 editor: {  
                 type: 'combobox',  
                 options: {  
                	 url : '${path}/project/line/getProSectionDic?proId='+proId,
                     valueField: "id",
                     textField: "sectionName"                                
                    },
                    
                     required: true  
                 }  
             
         }, 
         {width : '100',title : '线路名称',field : 'lineName',align:'center',
        	 formatter:formatterxl,
         
        	 editor: {  
                 type: 'combobox',  
                 options: {  
                	 valueField: "id",
                     textField: "lineName"                    
                    },
                     required: true  
                 } 
         }, 
         {width : '100',title : '日期',field : 'rq',sortable : true}, 
         {width : '100',title : '日掘进环数',field : 'rjjnum',align:'center',sortable : false,
        	 editor:{
                 type:'numberbox',
                 options:{precision:0}
               } 
         }, 
         {width : '100',title : '备注',field : 'remarks',align:'center',editor:'text'
        	 
         }      
        ]],
	onLoadSuccess : function(data) {},
	
	onBeforeEdit: function(index, row) {
		
		
	},
	//双击行触发
	onDblClickRow: function (rowIndex, rowData) {
        if (editRow != undefined) {
            $("#dataGrid").datagrid('endEdit', editRow);
        }
        if (editRow == undefined) {
            $("#dataGrid").datagrid('beginEdit', rowIndex);
            editRow = rowIndex;
        }
        
        //获取区间下拉框改变的文本和id
        var ed=new Array();
        ed = $('#dataGrid').datagrid('getEditor', {index:rowIndex,field:'sectionName'});
  		var name=$('#dataGrid').datagrid('getRows');
  		
  		$(ed.target).combobox({ 		
  			onChange:function(rowIndex){
  				froName=$(ed.target).combobox('getText');
  				qjId=$(ed.target).combobox('getValue');			
  				var url = '${path}/project/line/getProLineDic?sectionId='+qjId;
  				//联动加载线路
  				target.combobox('clear');
  				target.combobox('reload', url);
  			}
  		});
  		var target = $('#dataGrid').datagrid('getEditor', {'index':rowIndex,'field':'lineName'}).target;
  		
  		//获取线路下拉框改变的文本和id
  		
  		$(target).combobox({
  			onChange:function(rowIndex){
  				xlName=$(target).combobox('getText');
  				xlId=$(target).combobox('getValue');
  			}
  		});
  		$(ed.target).combobox('setValue',rowData.qjId);
  		$(target).combobox('setValue',rowData.xlId);
  		froName=detailData[rowIndex].sectionName;
  		qjId=$(ed.target).combobox('getValue');
		xlName=detailData[rowIndex].lineName;
		xlId=$(target).combobox('getValue');

    },
    //单击行触发
    onClickRow: function (rowIndex, rowData) {
    	
        if (editRow != undefined) {
            $("#dataGrid").datagrid('endEdit', editRow);

        }
        
    },
    //编辑后触发
    onAfterEdit: function (rowIndex, rowData, changes) {
    	rowData.qjId=rowData.sectionName;
    	rowData.xlId=rowData.lineName;
    	rowData.sectionName=froName;
    	rowData.lineName=xlName;
		editRow = undefined;
		var obj = new Object();
  		obj.id=rowData.id;
  		obj.proId=rowData.proId;
  		obj.qjId=rowData.qjId;
  		obj.proName=rowData.proName;
  		obj.sectionName=rowData.sectionName;
  		obj.lineName=rowData.lineName;
  		obj.xlId=rowData.xlId;
  		obj.rq=rowData.rq;
  		obj.rjjnum=rowData.rjjnum;
  		obj.remarks = rowData.remarks;
  		details[rowIndex] = obj;

    },
	toolbar : '#toolbar'
	
}); 
	$('#dataGrid').datagrid('loadData', detailData);
 });
 
//格式化数据表格显示
 function formatterln(value,row,index){
	
	if(row.sectionName==qjId){
		return froName;
			
		}
	else{
			return row.sectionName;
		}
	}
function formatterxl(value,row,index){
	if(row.lineName==xlId){
		return xlName;
	}
	else {
		return row.lineName;
	}
}
 
 
 /**
  * 数据添加
  */
  var _rowIndex;
  function addFun(flag,rowIndex) {
	  _rowIndex = rowIndex;
	  $('#flag').val(flag);
	 	var pid=proId;
		FormUtil.clearForm($("#addDialog"));
		
		$("#addDialog").dialog('open').dialog('setTitle', '添加［'+moduleName+"］");
		
		$('#pro_Name').textbox('setValue',proName);
			
		$('#pro_rq').textbox('setValue',rq);
		
		$('#pro_Name').textbox('textbox').attr('readonly',true);  //设置输入框为禁用
		
		$('#pro_rq').textbox('textbox').attr('readonly',true);  //设置输入框为禁用
		
		$('#proId').textbox('setValue',pid);
        $('#add_sectionId').combobox({
            url : '${path}/project/line/getProSectionDic?proId='+pid,
            valueField: "id",
            textField: "sectionName",
            onChange:function(){
               var qjid = $('#add_sectionId').combobox('getValue');
                $('#add_LineId').combobox({
                    url : '${path}/project/line/getProLineDic?sectionId='+qjid,
                    valueField: "id",
                    textField: "lineName"
                });
               
           }
        });
        detailRemarks();
	}
//新增保存
  function addAjax() {
		$('#newForm').form('submit', {
			url : path+'/add',
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
					var data={
							proName:$('#pro_Name').val(),
							rq:$('#pro_rq').val(),
							rjjnum:$('#rjjnum').val(),
							remarks:$('#remarks').val()
							
							
					};
					parent.$.modalDialog.handler.dialog('close');
					addEditFun(rq,proId);
					/*addRow(data,$('#add_sectionId').combobox('getText'),
							$('#add_LineId').combobox('getText'));*/
					$("#addDialog").dialog('close');
				}
				else {
					$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
	}
	

 /* function addRow(obj,add_sectionId,add_LineId){
	  $('#dataGrid').datagrid('appendRow',{
		  proName:obj.proName,
		  sectionName:add_sectionId,
		  lineName:add_LineId,
		  rq:obj.rq,
		  rjjnum:obj.rjjnum,
		  remarks:obj.remarks
				
  });
  }*/
  //主页面点保存时调用的方法
  function saveFun(){
  	var detailsInfo=new Array();
  	var rows = $("#dataGrid").datagrid("getRows"); 
  	
  	if(rows.length <=0 ){
  		parent.$.messager.alert('提示', '无明细，新增失败。', 'info');
  		return;
  	}
  	var j=-1;
  	for(var i=0;i<details.length;i++)	{	
  		if(details[i]!=null){
  			j++;
  		var obj = new Object();  		
  		obj.id=details[i].id;
  		obj.proId=details[i].proId;
  		obj.qjId=details[i].qjId;
  		
  		obj.proName=details[i].proName;
  		obj.sectionName=details[i].sectionName;
  		obj.lineName=details[i].lineName;
  		obj.xlId=details[i].xlId;
  		obj.rq=details[i].rq;
  		obj.rjjnum=details[i].rjjnum;
  		obj.remarks = details[i].remarks;
  		detailsInfo[j] = obj;
  		}		
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
 //删除数据表格记录
  function deleteFun(){
	  
 	 var ids = checkSelect();
 	    if( 0 == ids.length){
 			return;
 		}
 	    parent.$.messager.confirm('询问', '您是否要删除当前所选数据？', function(b) {
 	        if (b) {
 	        	deleteModel();
 	        	for(var i=0;i<ids.length;i++)	{
	    	        	var rowIndex = $('#dataGrid').datagrid('getRowIndex', ids[i]);//id是关键字值
	  	        	$('#dataGrid').datagrid('deleteRow', rowIndex); 
 	        	}
 	          //dataGrid.datagrid('reload');
 	        }
 	    });
 }
  //删除数据库数据
  function deleteModel() {
		var ids = new Array();
		var rows = dataGrid.datagrid('getSelections');
		if (rows.length == 0) {
			
			return;
		}
		for(var i=0; i<rows.length; i++){
			ids.push(rows[i].id);
		}
		$.messager.confirm('提示','删除成功', function(b) {
			if (b) {
				progressLoad();
				$.post(path+'/delete', {
					ids : ids.join(",")
				}, function(result) {
					if (result.success) {

						dataGrid.datagrid('reload');
						dataGrid.datagrid('clearSelections');
					}
					progressClose();
				}, 'JSON');
			}
		});
	}
 //刷新数据表格
  function addEditFun(rq,proId) {
	     parent.$.modalDialog({
	         title : '导入内容',
	         width : 900,
	         height : 500,
	         href : '${path}/project/jindu/addDetails?rq='+rq+'&proId='+proId,
	        
	         buttons : [ {
	             text : '保存',
	             handler : function() {
	             	var f = parent.$.modalDialog.handler.find('#saveBtn');
	             	$(f).trigger("onclick");
	             }
	         },{
	             text : '关闭',
	             handler : function() {
	                 parent.$.modalDialog.handler.dialog('close');
	             }
	         } ]
	     });
	 }
</script>
<!-- 结果列表 -->
<div class="easyui-layout" data-options="fit:true,border:false" >
<div class="easyui-panel" title="施工日进度明细" data-options="region:'north',border:false,collapsible:true"  
		style="width:100%;height:160px;border: false;overflow: hidden;">
	<form id="addForm" method="post">
		<input type='hidden' id='details' name='details' />
			<table class="grid">
               
                <tr>
                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>所属项目：</td>
                	<td class="form-td-content" colspan="3" style="width:260px">
                    	<input id="proName" name="proName" class="easyui-textbox" style="width: 100%;margin-right: 15px">
                	</td>
                	<td class="form-td-label">备注：</td>
                	<td class="form-td-content" colspan="3" style="width:260px">
                    	<textarea name="remarks"  class="easyui-textbox"  data-options="required:false,prompt:'请输入备注',multiline:true" style="width:100%;height:50px"></textarea>
                    </td>
                	
                </tr>
                <tr>
                	<td class="form-td-label">日期：</td>
                	<td class="form-td-content" colspan="3" style="width:260px">		
						<input id="rq" class="easyui-datebox" data-options="prompt:''" style="width:100%;" ></td>
					</td>
                </tr>
                
               
                
        	</table>
        </form>
        </div>
    <div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100px;">
        <table id="dataGrid" title="列表" data-options="fit:true,border:false"></table>
        <div id="toolbar">
	    	<a onclick="addFun('insert',0)" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">新增明细</a>
	    	<a onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除明细</a>
	      	<a id="saveBtn" style="display:none" onclick="saveFun()"  href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-save'">保存</a>
	    </div>
	</div>
</div>	
	<!-- 弹出框--新增 -->
	<div id="addDialog" class="easyui-dialog" data-options="iconCls:'icon-save',buttons:'#add-dialog-buttons',closed:true,cache: false,modal: true"
		 style="width:420px;height:350px;padding:5px 5px;" >
        <form id="newForm" method="post" data-options="novalidate:true">
        	<input id="proId" name="proId" class="easyui-textbox" type="hidden"/>
			<table class="grid">
               
                <tr>
                	<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>所属项目：</td>
                	<td class="form-td-content" colspan="3" style="width:260px">
                    	<input id="pro_Name" name="proName" class="easyui-textbox" style="width: 100%;margin-right: 15px" >
                	</td>
                	
                </tr>
                <tr>
					<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>所属区间：</td>
                	<td class="form-td-content" colspan="3" style="width:260px">
                    	<select id="add_sectionId" name="qjId" class="easyui-combobox" data-options="required:true" style="width:100% ;margin-right: 15px"></select>
                	</td>
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>线路编号：</td>
                    <td class="form-td-content" colspan="3" style="width:260px">
                    	<select id="add_LineId" name="xlId" class="easyui-combobox" data-options="required:true" style="width: 100%;margin-right: 15px"></select>
                    </td>
                 </tr>
                 <tr>
					<td class="form-td-label"><span style="color: red;">*</span>掘进环数：</td>
                    <td class="form-td-content" colspan="3" style="width:260px">
						<input id="rjjnum" name="rjjnum" type="text" placeholder="管环数量" class="easyui-numberbox" data-options="required:true" value="" style="width: 100%;margin-right: 15px">
					</td>
                </tr>
                <tr>
                	<td class="form-td-label"><span style="color: red;">*</span>日期：</td>
                	<td class="form-td-content" colspan="3" style="width:260px">		
						<input id="pro_rq" name="rq" class="easyui-textbox" data-options="required:true" style="width:100%;"></td>
					</td>
                </tr>
                <tr>
                	<td class="form-td-label">备注：</td>
                	<td class="form-td-content" colspan="3" style="width:260px">
                    	<textarea id="remarks" name="remarks"  class="easyui-textbox"  data-options="required:false,prompt:'请输入备注',multiline:true" style="width:100%;height:50px"></textarea>
                    </td>
                </tr>
                <tr style="margin-top:20px ">
				<td colspan="2" style="text-align: right;">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="addAjax()" style="width:15%">保存</a>
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeDetailWin()" style="width:15%">关闭</a>
				<input type="hidden" id="flag" /> <input type="hidden" id="detailId" />
				</td>
			</tr>
        	</table>
        	
        </form>
	
        
    </div>
	