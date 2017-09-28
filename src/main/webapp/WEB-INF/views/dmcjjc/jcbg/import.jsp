<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var jcinfoDG;
    $(function() {

        jcinfoDG = $('#jcinfoDG').datagrid({
            url : '${path }/dmcjjc/dmcjjcinfo/list',
            striped : true,
            fitColumns:true,
            rownumbers : true,
            pagination : true,
            singleSelect : true,
            idField : 'id',
            pageSize : 10,
            pageList : [ 10, 15,20, 25, 50],
            columns : [ [{ 
                field:'ck',checkbox:true , fixed:true
            }, {
                width : '120',
                title : '工程名称',
                field : 'projName',
                sortable : false
            } , {
                width : '150',
                title : '本次监测时间',
                field : 'jcTime',
                sortable : false
                /**
                formatter : function(value, row, index) {
                    var str = $.formatString('<a href="${path}/dmcjjc/dmcjjcinfo/showPage?id='+row.id+'">'+value+'</a>', row.id);
                    return str;
                }**/
            }, {
                width : '50',
                title : '天气',
                field : 'tianqi'
            } , {
                width : '80',
                title : '确认状态',
                field : 'ifqr'
            }, {
                field : 'action',
                title : '导入文件下载',
                width : 100,
                formatter : function(value, row, index) {
                    var str = $.formatString('<a href="upload/'+row.impFilePath+'">点击下载</a>', row.id);
                    return str;
                }
            }  ] ],
            onLoadSuccess:function(data){
            }
        });

        $('#addForm').form({
            url : '${path}/dmcjjc/bg/temp',
            onSubmit : function() {
                var checkedItems = $('#jcinfoDG').datagrid('getChecked');
                var ids = [];
                var ifqr;
                $.each(checkedItems, function(index, item){
                    ids.push(item.id);
                    $('#id').val(item.id);
                    $('#jcTime').val(item.jcTime);
                    ifqr = item.ifqr;
                });
                if( 0 == ids.length){
                    parent.$.messager.alert('提示', '请选择一条数据', 'info');
                    return false;
                }
                if("未确认" == ifqr){
                	parent.$.messager.alert('提示', '请先确认再添加', 'info');
                  return false;
                }

                progressLoad();
                var isValid = $(this).form('validate');
                if (!isValid) {
                    progressClose();
                }
                return isValid;
            },
            success : function(result) {
            	var idParam = $('#id').val();
            	progressClose();
                result = $.parseJSON(result);
                if (result.success) {
                	parent.$.modalDialog.handler.dialog('close');
                    addEditFun(idParam);
                } else {
                    parent.$.messager.alert('错误', result.msg, 'error');
                }
            }
        });
    });
    
    function addEditFun(id) {
        parent.$.modalDialog({
            title : '导入内容',
            width : 900,
            height : 500,
            maximizable:true,
            href : '${path}/dmcjjc/bg/tempsave?id=' + id,
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
            }],
            onBeforeClose:function(){
            	var item = parent.$.modalDialog.handler.find('#dataSta');
            	dataSta = item.val();//数据状态 original:原始状态；modified:修改状态;
            	if(dataSta !=null && dataSta =='modified'){
            		 parent.$.messager.confirm('询问', '您尚未保存数据，确定离开页面吗？', function(b) {
            	  	        if (b) {
            	  	        	item.val('original');//关闭页面后，数据状态恢复为原始状态
            	  	        	parent.$.modalDialog.handler.dialog('close');
            	  	        }
            		 })
            	}else{
            		return true;
            	}
            	return false;
            }
        });
    }    
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;" >
        <form id='addForm' method="post" action="${path}/dmcjjc/bg/temp">
        <input type="hidden" id='id' name='id'/>
        <input type="hidden" id='jcTime' name='jcTime'/>
        </form>
        <table id="jcinfoDG" data-options="fit:true,border:false"></table>
    </div>
</div>
