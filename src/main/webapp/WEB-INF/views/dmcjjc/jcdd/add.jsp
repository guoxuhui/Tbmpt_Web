<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
    	 $('#typeName').combobox({
             url : '${path}/dmcjjc/dmcjddtype/getAll',
             valueField: "typeName",
             textField: "typeName"
         });
    	
        $('#addForm').form({
            url : '${path }/dmcjjc/dmcjddinfo/save',
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
                } else {
                    parent.$.messager.alert('错误', result.msg, 'error');
                }
            }
        });
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;" >
        <form id="addForm" method="post">
            <table class="grid">
                <tr>
                    <td class="form-td-label" style="width: 100px;"><span style="color: red;">*</span>分类名称：</td>
                    <td><select editable="false" id="typeName" name="typeName" class="easyui-combobox" data-options="required:true,prompt:'请选择分类名称'" style="width: 300px;"></select></td>
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>数据名称：</td>
                    <td><input id="ddName" name="ddName" class="easyui-textbox" data-options="required:true,prompt:'请输入数据名称'" style="width: 300px;"></td>
                </tr>
                <tr>
                    <td class="form-td-label">排&nbsp;&nbsp;序&nbsp;&nbsp;号：</td>
                    <td><input id="sortNum" name="sortNum" class="easyui-numberbox" data-options="required:false,prompt:'请输入排序号'" style="width: 300px;"></td>
                </tr>
                <tr>
                    <td class="form-td-label">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
                    <td colspan="3">
                    <input id="remarks" name="remarks" class="easyui-textbox" data-options="validType:'length[1,200]',required:false,prompt:'请输入备注',multiline:true" style="width:300px;height:80px">
                    </td>
                </tr>
            </table>
            <!-- 默认新增数据启用 -->
            <input id="ifqy" name="ifqy" type="hidden" value="启用">
        </form>
    </div>
</div>