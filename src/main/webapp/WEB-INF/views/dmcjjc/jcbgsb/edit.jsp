<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
var dataGrid;
var editRow = undefined;
    $(function() {
    	
        $('#addForm').form({
        		url : '${path}/dmcjjc/bgsb/updateAll',
            	onSubmit : function() {
            		progressLoad();
	           		var detailsInfo=new Array();
	               	var rows = $("#dataGrid").datagrid("getRows"); 
	               	if(rows.length <=0 ){
	               		parent.$.messager.alert('提示', '无监测报告上报明细，更新失败。', 'info');
	               		return;
	               	}
	               	if (editRow != undefined) {
	                    $("#dataGrid").datagrid('endEdit', editRow);
	                 }
	               	for(var i=0;i<rows.length;i++)	{
	            		var obj = new Object();
	            		obj.qujian=rows[i].qujian;
	            		obj.xianlu=rows[i].xianlu;
	            		obj.jcdNo=rows[i].jcdNo;
	            		obj.weizhi=rows[i].weizhi;
	            		obj.licheng=rows[i].licheng;
	            		obj.bcgc = rows[i].bcgc;
	            		obj.remarks = rows[i].remarks;
	            		detailsInfo[i] = obj;
	            	}
	               	var json = JSON.stringify(detailsInfo);
	               	$('#details').val(json);
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
    	
    	 $('#gcbh').combobox({
    	        url : basePath + '/project/info/getProjects',
    	        valueField : "id",
    	        textField : "proName",
    	        onLoadSuccess:function(){
    	            $('#gcbh').combobox('setValue','${jc.gcbh}');
    	            $('#projname').text($('#gcbh').combobox('getText'));
    	        }
    	    });
    	    
		var tian = '${jc.tianqi}';
      	$("#tianqi").val(tian);     	    

    	 dataGrid = $('#dataGrid').datagrid({
    	        url : '${path}/dmcjjc/bgsb/detailList?fid=${jc.id}&jcTime=${jc.jcTime}',
    	        striped : true,
    	        rownumbers : true,
    	        pagination : false,
    	        singleSelect : false,
    	        idField : 'id',
    	        pageSize : 10,
    	        pageList : [ 10, 15, 20, 25, 50],
    	        frozenColumns : [ [ { 
                	field:'ck',checkbox:true 
                },{
    	            width : '120',
    	            title : '区间',
    	            field : 'qujianName',
    	            sortable : false
    	        } , {
    	            width : '50',
    	            title : '线路',
    	            field : 'xianluName'
    	        } , {
    	            width : '80',
    	            title : '点位编号',
    	            field : 'jcdNo'
    	        } , {
    	            width : '100',
    	            field : 'licheng',
    	            title : '里程'
    	        }]],
    	        columns : [[{
    	            width : '200',
    	            field : 'weizhi',
    	            title : '环号/位置'
    	        } , {
    	            width : '80',
    	            field : 'csgc',
    	            align:'right',halign:'center',
    	            title : '初始高程(米)'
    	        } , {
    	            width : '80',
    	            field : 'bcgc',
    	            align:'right',halign:'center',
    	            title : '本次高程(米)',
    	            sortable : false,
    	            editor:{
   	                   type:'numberbox',
   	                   options:{precision:5}
   	                 }
    	        } , {
    	            width : '80',
    	            field : 'scgc',
    	            align:'right',halign:'center',
    	            title : '上次高程(米)'
    	        }, {
    	            width : '100',
    	            field : 'bcbhl',
    	            align:'right',halign:'center',
    	            title : '本次变化量(毫米)'
    	        }, {
    	            width : '100',
    	            field : 'ljbhl',
    	            align:'right',halign:'center',
    	            title : '累计变化量(毫米)'
    	        }, {
    	            width : '70',
    	            field : 'bhsl',
    	            align:'right',halign:'center',
    	            title : '变化速率'
    	        }, {
    	            width : '80',
    	            field : 'remarks',
    	            title : '备注'
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
		               var bcgc = rowData.bcgc;//本次高程
		               var scgc = rowData.scgc;//上次高程
		               var csgc = rowData.csgc;//初始高程
		               var bcbhl = calculateBcbhl(bcgc,scgc);//本次变化量
		               var ljbhl = caculateljBhl(bcgc,csgc,scgc);//累计变化量
		              $('#dataGrid').datagrid('updateRow',{
		            		index: rowIndex,
		            		row: {
		            			bcbhl: bcbhl,
		            			ljbhl:ljbhl,
		            		    bhsl:bcbhl
		            		}
		            	});
		               $('#dataSta').val('modified');
		               $('#dataGrid').datagrid('refreshRow', rowIndex);
		           },
    	        toolbar : '#toolbar'
    	    });
    	 
   });
    
    var _rowIndex;
    function openWin(flag,rowIndex){
    	resetDetailWin();
    	_rowIndex = rowIndex;
        
        $('#win').window('open');
        $('#flag').val(flag);
         var pid = $('#gcbh').combobox('getValue');
         $('#qjbh').combobox({
             url : '${path}/project/line/getProSectionDic?proId='+pid,
             valueField: "id",
             textField: "sectionName",
             onChange:function(){
                var qjid = $('#qjbh').combobox('getValue');
                 $('#xlbh').combobox({
                     url : '${path}/project/line/getProLineDic?sectionId='+qjid,
                     valueField: "id",
                     textField: "lineName"
                 });
            }
         });
         detailRemarks();
    }    
    
    function btnConfirm(){
    	//从后台取点位高程数据并计算变化量，如果该点没有上次高程，则不能保存该点
    	var isValid = $('#newForm').form('validate');
        if (!isValid) {
            progressClose();
            return;
        }
        var detailRremarks = $('#detailRremarks').combobox('getValue');
        $.ajax({
            url: "${path}/dmcjjc/dmcjjcpoint/jcdInfo",
            data: {
                jcdno: $('#jcdbh').val(),
                jcTime:$('#jcTime').val(),
                bcgc:$('#bcgc').val(),
                flag:'info'
            },
            success: function(data) {
            data = $.parseJSON(data);
	   			  if ( data.success) {
	            	//计算后的内容在明细表格中新增一行
	            	if($('#flag').val() == "insert"){
	            		addRow(data,detailRremarks);
	            	}else if($('#flag').val() == "update"){
	            		modiRow(data,detailRremarks);
	            	}
	   			 }else{
	   				 //没有项目信息，不能保存该点
	   				 parent.$.messager.alert('提示', data.msg, 'info');
	   			 }    
            },
            error: function(data) {
                
            }
        });
    }
    
    //新增的时候要检查点位是不是该线路下以及是否在监测信息中重复存在
    function addRow(obj,detailRremarks){
    	$.ajax({
     		url: "${path}/dmcjjc/dmcjjcinfo/checkPointInLine",
     		data: {
     			jcdno: $('#jcdbh').val(),
     			xlbh: $('#xlbh').combobox('getValue')
     		},
     		success: function(data) {
     			data = $.parseJSON(data);
     			 if (data.success) {
     				 //再检查是否重复
     				 	var rows = $("#dataGrid").datagrid("getRows");
							for(var i=0;i<rows.length;i++)	{
								if(rows[i].jcdNo == obj.obj.jcdNo){
									parent.$.messager.alert('提示', '保存失败，监测点点号已存在', 'info');
									return;     				 
								}
							}
							$('#dataGrid').datagrid('appendRow',{
				    			qujianName:obj.obj.qujianName,
				    			xianluName:obj.obj.xianluName,
				    			qujian:obj.obj.qujian,
				    			xianlu:obj.obj.xianlu,
				    			jcdNo:obj.obj.jcdNo,
				    			licheng:obj.obj.licheng,
				    			weizhi:obj.obj.weizhi,
				    			csgc:obj.obj.csgc,
				    			bcgc:obj.obj.bcgc,
				    			scgc:obj.obj.scgc,
				    			bcbhl:obj.obj.bcbhl,
				    			ljbhl:obj.obj.ljbhl,
				    			bhsl:obj.obj.bhsl,
				    			remarks:detailRremarks
				    	});
					  $('#dataSta').val('modified');
     		    	  $('#win').window('close');
     			 }else{
                parent.$.messager.alert('提示', data.msg, 'info');
     			 }    
     			//dataGrid.datagrid('reload');
     		},
     		error: function(data) {
     		}
     	});
    }
    
    //修改的时候要检查点位是不是该线路下以及是否在监测信息中重复存在
    function modiRow(obj,detailRremarks){
    	 $.ajax({
     		url: "${path}/dmcjjc/dmcjjcinfo/checkPointInLine",
     		data: {
     			jcdno: $('#jcdbh').val(),
     			xlbh: $('#xlbh').combobox('getValue')
     		},
     		success: function(data) {
     			data = $.parseJSON(data);
     			 if (data.success) {
     				 //再检查是否重复
     				 	var rows = $("#dataGrid").datagrid("getRows");
							for(var i=0;i<rows.length;i++)	{
								if(rows[i].jcdNo == obj.obj.jcdNo && i != _rowIndex){
									parent.$.messager.alert('提示', '保存失败，监测点点号已存在', 'info');
									return;     				 
								}
							}
     		    	$('#dataGrid').datagrid('updateRow',{
     		    		index:_rowIndex,
     		    		row:{
     		    			qujianName:obj.obj.qujianName,
     		    			xianluName:obj.obj.xianluName,
     		    			jcdNo:obj.obj.jcdNo,
     		    			licheng:obj.obj.licheng,
     		    			weizhi:obj.obj.weizhi,
     		    			csgc:obj.obj.csgc,
     		    			bcgc:obj.obj.bcgc,
     		    			scgc:obj.obj.scgc,
     		    			bcbhl:obj.obj.bcbhl,
     		    			ljbhl:obj.obj.ljbhl,
     		    			bhsl:obj.obj.bhsl,
     		    			remarks:detailRremarks
     		    		}
     		    	});
     		    	  $('#dataSta').val('modified');
     		    	  $('#win').window('close');
     			 }else{
                parent.$.messager.alert('提示', data.msg, 'info');
     			 }    
     			//dataGrid.datagrid('reload');
     		},
     		error: function(data) {
     		}
     	});
}
    function deleteFun(){
   	 var ids = checkSelect();
   	    if( 0 == ids.length){
   			return;
   		}
   	    parent.$.messager.confirm('询问', '您是否要删除当前所选数据？', function(b) {
   	        if (b) {
   	        	for(var i=0;i<ids.length;i++)	{
	    	        	var rowIndex = $('#dataGrid').datagrid('getRowIndex', ids[i]);//id是关键字值
 	  	        	$('#dataGrid').datagrid('deleteRow', rowIndex); 
 	  	            $('#dataSta').val('modified');
   	        	}
   	          //dataGrid.datagrid('reload');
   	        }
   	    });
   }

</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
	<div class="easyui-panel" title="监测报告上报信息" data-options="region:'north',border:false,collapsible:true"  
		style="width:100%;height:160px;border: false;overflow: hidden;">
	<form id="addForm" method="post">
	     <input id="dataSta"  type="hidden">
	     <input type='hidden' id='details' name='details' />
		<input id="id" name="id" type="hidden"  value="${jc.id}">
<%-- 		<input id="ifsb" name="ifsb" type="hidden"  value="${jc.ifsb}"> --%>
		<input id="ifqr" name="ifqr" type="hidden"  value="${jc.ifqr}">
            <table class="grid">
               <tr>
                    <td class="form-td-label" style="width:100px">工程名称：</td>
                    <td colspan="3" style="width:200px">
                    <div style="display:none;" ><select editable="false" id="gcbh" name="gcbh" class="easyui-combobox" data-options="required:true"  style="width: 140px;" readonly="true"></select></div>
                    <span id='projname'></span>
                    </td>
                    <td class="form-td-label" style="width:100px">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
                    <td style="width:230px" rowspan="3">
                    <input id="remarks" name="remarks" class="easyui-textbox" data-options="validType:'length[1,200]',required:false,prompt:'请输入备注',multiline:true" 
                    	style="width:100%;height:100%" value="${jc.remarks}">
                    </input>
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>本次监测时间：</td>
                    <td>
                    <input editable="false" id="jcTime" name="jcTime" class="easyui-datetimebox" 
                    	data-options="required:true,prompt:'本次监测时间'" value="${jc.jcTime}" style="width:100%;"/>    
                    </td>
                    <td class="form-td-label"><span style="color: red;">*</span>天&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;气：</td>
                    <td>
                        <select editable="false" id="tianqi" name="tianqi" class="easyui-combobox" data-options="required:true" style="width: 140px;">
                            <option value="晴">晴</option>
                            <option value="多云">多云</option>
                            <option value="小雨">小雨</option>
                            <option value="大雨">大雨</option>   
                        </select>
                    </td>
                    <td></td>
                </tr>
                <tr>
                	<td class="form-td-label">确认状态：</td>
					<td>${jc.ifqr}</td>
					<td></td>
					<td></td>
					<td></td>
                </tr>
            </table>
		</form>   
		</div>
		         
        <!-- 明细信息表格 -->
        <div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100px;">
    	<table id="dataGrid" title="列表<font color='red'>(*鼠标双击本次高程可直接编辑*)</font>" data-options="fit:true,border:false"></table>
        <div id="toolbar">
        	<a onclick="openWin('insert',0);" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'">新增明细</a>
        	<a onclick="editDetailFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'">编辑明细</a>
        	<a onclick="deleteFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-del'">删除明细</a>
        </div>
        </div>
</div>

 <!-- 修改明细时弹出的窗口 -->
<div id="win" class="easyui-window" title="监测报告数据" style="width:400px;height:300px"
data-options="modal:true,closed:true,inline:false,resizable:false,
maximizable:false,minimizable:false,collapsible:false">
<form id="newForm">
    <table class="grid">
    <tr>
		<td class="form-td-label" style="width: 100px;"><span style="color: red;">*</span>区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间：</td>
		<td style="width: 260px;">
		<select editable="false" id="qjbh" name="qjbh" class="easyui-combobox" 
			data-options="required:true,prompt:'区间'" style="width: 100%;"></select>
		</td>
	</tr>
    <tr>
		<td class="form-td-label"><span style="color: red;">*</span>线&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;路：</td>
		<td><select editable="false" id="xlbh" name="xlbh" class="easyui-combobox"
			data-options="required:true,prompt:'线路'" style="width: 100%;"></select></td>
	</tr>
	<tr>
	    <td class="form-td-label"><span style="color: red;">*</span>点位编号：</td>
		<td><input id="jcdbh" name="jcdbh" type="text"
			class="easyui-textbox" data-options="required:true,prompt:'请输入点位编号'"
			style="width: 100%;"></td>
    </tr>
    <tr>
    	<td class="form-td-label"><span style="color: red;">*</span>本次高程：</td>
		<td><input id="bcgc" name="bcgc" type="text"
			class="easyui-numberbox" precision="5"
			max="9999.99999" size="20" maxlength="10"
			data-options="required:true,prompt:'请输入本次高程'" style="width: 100%;"></td>
	</tr>
	<tr>
		<td class="form-td-label">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
		<td><select editable="false" id="detailRremarks" name="detailRremarks"
			class="easyui-combobox" data-options="validType:'length[1,20]',required:false,prompt:'请输入备注'"
			style="width: 100%;"></select></td>
	</tr>
	<tr>
	    <td colspan="2" style="text-align: right;">
	    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="btnConfirm()" style="width:15%">保存</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeDetailWin()" style="width:15%">关闭</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="resetDetailWin()" style="width:15%">重置</a>
	    <input type="hidden" id="flag"/>
	    <input type="hidden" id="detailId"/>
	    </td>
	</tr>
</table>
</form>
</div>