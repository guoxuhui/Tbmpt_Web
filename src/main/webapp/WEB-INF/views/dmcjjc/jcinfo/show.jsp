<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
var dataGrid;
    $(function() {
         $('#gcbh').combobox({
                url : basePath + '/project/info/getProjects',
                valueField : "id",
                textField : "proName",
                onLoadSuccess:function(){
                    $('#gcbh').combobox('setValue','${jc.gcbh}');
                    $('#projname').text($('#gcbh').combobox('getText'));
                }
            });

         dataGrid = $('#dataGrid').datagrid({
                url : '${path}/dmcjjc/dmcjjcinfo/detailList?fid=${jc.id}&jcTime=${jc.jcTime}',
                striped : true,
                rownumbers : true,
                pagination : false,
                singleSelect : false,
                idField : 'id',
                pageSize : 200,
                pageList : [ 200, 500, 1000, 2000, 5000],
                frozenColumns : [ [ { 
                	field:'id',checkbox:true 
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
                }]],
                columns : [ [ {
                    width : '100',
                    field : 'licheng',
                    title : '里程'
                } , {
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
                    sortable : false
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
                    width : '80',
                    field : 'bhsl',
                    align:'right',halign:'center',
                    title : '变化速率'
                }, {
                    width : '80',
                    field : 'remarks',
                    title : '备注'
                }]],
                onLoadSuccess:function(data){
                },
                toolbar : [ 
		    		{
		    			text: '导出Excel',
		    			iconCls: 'icon-page_excel',
		    			handler: function(){
		    				var url = "${path}/dmcjjc/dmcjjcinfo/detailExpXls.action?fid=${jc.id}&jcTime=${jc.jcTime}&projectId=${jc.gcbh}";
		    				ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, "导出excel信息", dataGrid, false);
		    			}
		    		},
		    		'-', 
		    		{text: '取消已选',iconCls: 'icon-undo',
		    			handler: function(){
		    				dataGrid.datagrid('clearSelections');
		    			}
		    		}
		           ]
            });
         
   });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div class="easyui-panel" title="监测信息" data-options="region:'north',border:false,collapsible:true"  
		style="width:100%;height:140px;border: false;overflow: hidden;">
		<form id="addForm" method="post">
			<input id="impFilePath" name="impFilePath" type="hidden"
				value="${fileUrl}"> <input id="ifqr" name="ifqr"
				type="hidden" value="未确认"> <input type='hidden' id='details'
				name='details' />
			<table class="grid">
				<tr>
					<td class="form-td-label" style="width:100px">工程名称：</td>
					<td colspan="3" style="width:200px">
						<div style="display: none;"><select id="gcbh" name="gcbh" class="easyui-combobox"
						data-options="required:true" style="width: 140px;"></select>
						</div>
						<span id='projname' ></span>
					</td>
					<td class="form-td-label" style="width:100px">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
					<td style="width:230px" rowspan="3">
					${jc.remarks}
					</td>
				</tr>
				<tr>
					<td class="form-td-label" style="width:100px">本次监测时间：</td>
					<td>${jc.jcTime}</td>
					<td class="form-td-label" style="width:100px">天&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;气：</td>
					<td style="width:100px">
					${jc.tianqi}
					</td>
					<td></td>
				</tr>
				<tr>
					<td class="form-td-label">导入文件路径：</td>
					<td>
					<a href='${path }/dmcjjc/dmcjjcinfo/download?filepath=${jc.impFilePath}'>${jc.impFileName}</a>
					</td>
					<td class="form-td-label">确认状态：</td>
					<td>${jc.ifqr}</td>
					<td></td>
				</tr>	
			</table>
		</form>
	</div>
	<!-- 明细信息表格 -->
	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:850px;">
		<table id="dataGrid" title="列表" data-options="fit:true,border:false"></table>
	</div>
	
</div>
