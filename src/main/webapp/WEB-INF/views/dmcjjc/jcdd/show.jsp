<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
    });
    
    $('#typeName').combobox({
        url : '${path}/dmcjjc/dmcjddtype/getAll',
        valueField: "typeName",
        textField: "typeName",
        onLoadSuccess:function(){
			$('#typeName').combobox('setValue',$('#typeNameVal').val());
        }
   	});
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;" >
        <form id="addForm" method="post">
            <table class="grid">
                <tr>
                    <td class="form-td-label" style="width:100px">分类名称：</td>
                    <td style="width:300px">${vo.typeName}
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label" >数据名称：</td>
                    <td>${vo.ddName}</td>
                </tr>
                <tr>
                    <td class="form-td-label" >排&nbsp;&nbsp;序&nbsp;&nbsp;号：</td>
                    <td>${vo.sortNum}</td>
                </tr>
                <tr>
                    <td class="form-td-label">使用状态：</td>
                    <td>${vo.ifqy}</td>
                </tr>
                <tr>
                    <td class="form-td-label">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
                    <td colspan="3">${vo.remarks}</td>
                </tr>
            </table>
        </form>
    </div>
</div>