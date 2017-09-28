<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
//上传文件导入页面  监测点管理 
$(function() {
    
	//选择“项目名称” 下拉列表框  加载
	$('#gcbh').combobox({
        url : basePath + '/project/info/getProjects',
        valueField : "id",
        textField : "proName"
    });
	
	$('#addForm').form({
		url : '${path }/dmcjjc/dmcjjcpoint/upload?fileName=',//跳转到上传文件导入页面
    onSubmit : function() {
    	//打开   加载  提示
    	progressLoad();
    	//验证功能
        var isValid = $(this).form('validate');
        if (!isValid) {
        	//关闭  加载  提示
            progressClose();
        }
        return isValid;
    },
    success : function(result) {
            progressClose();
            result = $.parseJSON(result);
            if (result.success) {
            	parent.$.messager.show({title:'提示',msg:result.msg,showType:'show' });
                parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为list.jsp页面预定义好了
                parent.$.modalDialog.handler.dialog('close');
                parent.$.messager.alert('操作成功', result.msg, 'info');
            } else {
            	parent.$.modalDialog.handler.dialog('close');
                parent.$.messager.alert('错误', result.msg, 'error');
            }
    	}
		});
    
});


//wpg:监测点管理  当前是 上传文件导入页面
</script>
<input type="hidden" id="filename" />
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;" >
        <form action="${path }/dmcjjc/dmcjjcpoint/upload" id="addForm" method="post" enctype="multipart/form-data">
            <table class="grid">
                <tr>
                    <td class="form-td-label" style="width: 100px;"><span style="color: red;">*</span>工程名称：</td>
                    <td style="width: 200px;">
                    
                    <select editable="false" id="gcbh" name="gcbh" class="easyui-combobox" data-options="prompt:'工程名称'" style="width: 100%;"></select>
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label" ><span style="color: red;">*</span>导入文件路径：</td>
                    <td>
                    <input id="file" name="file" accept=".xls" class="easyui-filebox" data-options="buttonText:'选择文件',prompt:'选择文件'" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;">
                    <a href="${path }/dmcjjc/dmcjjcpoint/excelTemplate" class="easyui-linkbutton" data-options="iconCls:'icon-page_excel',plain:true" style="width:20ps" >下载Excel模版</a> 
                    </td>
                </tr>
                <tr>
                	<td  colspan="2">
                	    <span >
		                	<font color='red'>
		                		备注：请严格按照模板格式录入数据，每行数据之间不得存在空行，
		                		<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                		           模板外部 不得编辑或增添任何文字或图片，
		                		<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		                		           模板中有“ * ”标注的列为必填项；
		                	</font>
	                	</span>
                	</td>
                </tr>
            </table>    
        </form>
    </div>
</div>
