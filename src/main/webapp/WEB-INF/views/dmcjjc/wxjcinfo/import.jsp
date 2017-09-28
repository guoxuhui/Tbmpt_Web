<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
$(function() {

	//选择“项目名称” 下拉列表框  加载
	$('#gcbh').combobox({
        url : basePath + '/project/info/getProjects',
        valueField : "id",
        textField : "proName"
    });
	
	//当主页面点击确定按钮，#addForm列表发生点击事件，提交表单；
	$('#addForm').form({
		url : '${path }/dmcjjc/dmcjwxjcinfo/upload',
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
		        	$.messager.alert({
		    			title:'提示',
		    			msg:result.msg,
		    			height:'180px'
		    		}); 
	            parent.$.modalDialog.handler.dialog('close'); 
	            parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为list.jsp页面预定义好了
	        } else {
	            parent.$.messager.alert('错误', result.msg, 'error');
	        }
    	}
   });
    
});



</script>
<input type="hidden" id="filename" />
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div id="addDialog" data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;" >
        <form  id="addForm" method="post" enctype="multipart/form-data">
            <table class="grid">
                <tr>
                    <td class="form-td-label" style="width: 100px;"><span style="color: red;">*</span>工程名称：</td>
                    <td style="width: 200px;">
                       <select editable="false" id="gcbh" name="gcbh" class="easyui-combobox" data-options="required:true,prompt:'工程名称'" style="width: 100%;"></select>
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width: 100px;"><span style="color: red;">*</span>本次监测时间：</td>
                    <td style="width: 200px;">
                    <input editable="false" id="jcTime" name="jcTime" class="easyui-datetimebox" data-options="required:true,prompt:'请选择时间'" style="width:100%;">
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width: 100px; ">控制点高程：</td>
                    <td style="width: 200px;">
                    <input id="baseNumber" name="baseNumber" class="easyui-numberbox" precision="5" max="9999.99999" style="width: 100%;" 
                       data-options="prompt:'请输入控制点高程'" value="">
                    </td>
	         	</tr>
                <tr>
                   <td class="form-td-label"><span style="color: red;">*</span>天&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;气：</td>
					<td style="width: 200px;">
						<select editable="false" id="tianqi" name="tianqi"
							class="easyui-combobox" data-options="required:true"
							style="width:100%;">
								<option value="晴">晴</option>
								<option value="多云">多云</option>
								<option value="小雨">小雨</option>
								<option value="大雨">大雨</option>
						</select>
					</td>
                </tr>
                <tr>
                    <td class="form-td-label" ><span style="color: red;">*</span>导入文件路径：</td>
                    <td>
                        <input id="file" name="file" accept=".xls,.dat" class="easyui-filebox" data-options="buttonText:'选择文件',required:true,prompt:'选择文件'" style="width:100%">
                    </td>
                </tr>
                <tr style="height:50px">
                   <td class="form-td-label" style="width: 100px">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
					<td style="width:200px"><input id="remarks" name="remarks" class="easyui-textbox"
						data-options="validType:'length[1,200]',required:false,prompt:'请输入备注',multiline:true"
						style="width: 100%; height: 100%">
				    </td>
                </tr>
                <tr>
                	<td class="form-td-label" >Excel模板地址：</td>
                	<td>
                		<a href="${path }/dmcjjc/dmcjwxjcinfo/excelTemplate">点击下载模板</a>
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
