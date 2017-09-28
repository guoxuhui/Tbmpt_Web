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
		url : '${path }/sgkshgl/jcdzbgl/uploadFile',//跳转到上传文件导入页面
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
            parent.$.modalDialog.handler.dialog('close');
            addEditFun(result);
        } else {
            parent.$.messager.alert('错误', result.msg, 'error');
        }                
    	}
		});
    
});



function addEditFun(result) {
    parent.$.modalDialog({
        title : '导入内容',
        width : 900,
        height : 500,
        maximizable:true,
        href : '${path }/sgkshgl/jcdzbgl/upload?fileName=' + encodeURIComponent(result.obj.fileName),//跳转到上传文件导入页面
        buttons : [{
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
<input type="hidden" id="filename" />
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;" >
        <form action="${path }/sgkshgl/jcdzbgl/uploadFile" id="addForm" method="post" enctype="multipart/form-data">
            <table class="grid" style="width:100%">                
                <tr>
                    <td class="form-td-label" ><span style="color: red;">*</span>导入文件路径：</td>
                    <td>
                    <input id="file" name="file" accept=".xls" class="easyui-filebox" data-options="buttonText:'选择文件',prompt:'选择文件'" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center;">
                    <a href="${path }/sgkshgl/jcdzbgl/excelTemplate" class="easyui-linkbutton" data-options="iconCls:'icon-page_excel',plain:true" style="width:20ps" >下载Excel模版</a> 
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
