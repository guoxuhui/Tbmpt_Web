<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {

        $('#editUserPwdForm').form({
            url : '${path }/user/editUserPwd',
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
                    parent.$.messager.alert('提示', result.msg, 'info');
                    parent.$.modalDialog.handler.dialog('close');
                } else {
                    parent.$.messager.alert('错误', result.msg, 'error');
                }
            }
        });
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;" >
            <form id="editUserPwdForm" method="post">
                <table class="grid">
                    <tr>
                        <td class="form-td-label" style="width: 30%;">登录名：</td>
                        <td class="form-td-content" style="width: 70%;"><input value="<shiro:principal></shiro:principal>" type="text" class="easyui-textbox" style="width: 90%;margin-right: 15px" readonly="readonly"></td>
                    </tr>
                    <tr>
                        <td class="form-td-label"><span style="color: red;">*</span>原密码：</td>
                        <td class="form-td-content"><input name="oldPwd" type="password" placeholder="请输入原密码" class="easyui-textbox" data-options="required:true" style="width: 90%;margin-right: 15px"></td>
                    </tr>
                    <tr>
                        <td class="form-td-label"><span style="color: red;">*</span>新密码：</td>
                        <td class="form-td-content"><input name="pwd" type="password" placeholder="请输入新密码" class="easyui-textbox" data-options="required:true" style="width: 90%;margin-right: 15px"></td>
                    </tr>
                    <tr>
                        <td class="form-td-label"><span style="color: red;">*</span>重复密码：</td>
                        <td class="form-td-content"><input name="rePwd" type="password" placeholder="请再次输入新密码" class="easyui-textbox" data-options="required:true,validType:'eqPwd[\'#editUserPwdForm input[name=pwd]\']'" style="width: 90%;margin-right: 15px"></td>
                    </tr>
                </table>
            </form>
    </div>
</div>