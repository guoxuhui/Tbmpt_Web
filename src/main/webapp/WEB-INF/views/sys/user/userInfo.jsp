<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        var roleIds = ${roleIds };
        $('#orgzId').combotree({
            url : '${path }/sys/orgz/tree',
            parentField : 'pid',
            lines : true,
            panelHeight : 'auto',
            value : '${user.orgzId}'
        });

        $('#roleIds').combotree({
            url : '${path }/sys/role/tree',
            parentField : 'pid',
            lines : true,
            panelHeight : 'auto',
            multiple : true,
            required : true,
            cascadeCheck : false,
            value : roleIds
        });

        $('#userEditForm').form({
            url : '${path }/sys/user/edit',
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
                    parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
                    parent.$.modalDialog.handler.dialog('close');
                } else {
                    parent.$.messager.alert('错误', result.msg, 'error');
                }
            }
        });
        $("#userType").val('${user.userType}');
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="userEditForm" method="post">
        	<input type="hidden" name="createTime" value="${user.createTime}">
            <table class="grid">
                <tr>
                    <td class="form-td-label">登录名：</td>
                    <td class="form-td-content"><input name="id" type="hidden"  value="${user.id}">
                    <input name="loginName" class="easyui-textbox" style="width: 100%;" value="${user.loginName}" readonly="readonly"></td>
                    <td class="form-td-label">姓名：</td>
                    <td class="form-td-content"><input name="name" class="easyui-textbox" style="width: 100%;" value="${user.name}" readonly="readonly"></td>
                </tr>
                <tr>
                    <td class="form-td-label">密码：</td>
                    <td class="form-td-content"><input name="password" class="easyui-textbox" value="******" readonly="readonly"/></td>
                	<td class="form-td-label">用户类型：</td>
                    <td class="form-td-content"><select id="userType" name="userType"  class="easyui-combobox" style="width: 100%;" value="${user.userType}" readonly="readonly">
                            <option value="0">管理员</option>
                            <option value="1">用户</option>
                    </select></td>
                </tr>
                <tr>
                    <td class="form-td-label">部门：</td>
                    <td class="form-td-content"><select id="orgzId" name="orgzId" style="width: 100%;" value="${user.orgzId}" readonly="readonly"></select></td>
                    <td class="form-td-label">角色：</td>
                    <td class="form-td-content"><input  id="roleIds" name="roleIds" style="width: 100%;" value="${roleIds}" readonly="readonly"/></td>
                </tr>
            </table>
        </form>
    </div>
</div>