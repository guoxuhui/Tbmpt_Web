<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
    	 $('#proId').combobox({
             url : '${path}/project/info/getProjects',
             valueField: "id",
             textField: "proName",
             onChange:function(){
                var pid = $('#proId').combobox('getValue');
                 $('#secId').combobox({
                     url : "${path}/project/line/getProSectionDic?proId="+pid,
                     valueField: "id",
                     textField: "sectionName"                     
                 });
            }
         });
    	 
    	 $('#jcType').combobox({
             url : '${path}/dmcjjc/dmcjddinfo/getJcType',
             valueField: "ddName",
             textField: "ddName"
         });
    	
        $('#addForm').form({
            url : '${path }/sgkshgl/jcdzbgl/save',
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
                    parent.$.modalDialog.openner_dataGrid.datagrid('clearSelections');
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
            <table class="grid" style="width:100%">
                <tr>
                    <td class="form-td-label" style="width: 100px;">工程名称：</td>
                    <td colspan='3' style="width: 400px;">
                    <select editable="false" id="proId" name="proId" class="easyui-combobox" data-options="prompt:'请输入工程名称'" style="width: 100%;"></select>
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width: 100px;">点位编号：</td>
                    <td style="width: 150px;"><input id="jcId" name="jcId" class="easyui-textbox" data-options="prompt:'请输入点位编号'" style="width: 100%;"></td>
                    <td class="form-td-label" style="width: 100px;">区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间：</td>
                    <td style="width: 150px;"><select editable="false" id="secId" name="secId" class="easyui-combobox" data-options="prompt:'请输入区间名称'" style="width: 100%;"></select></td>
                </tr>
                <tr>
                    
                    <td class="form-td-label">点位类型：</td>
                    <td><select editable="false" id="jcType" name="jcType" class="easyui-combobox" data-options="prompt:'请输入点位类型'" style="width: 100%;">
                    </select></td>
                    <td class="form-td-label">环号/位置：</td>
                    <td><input id="hh" name="hh" class="easyui-textbox" style="width: 100%;" data-options="prompt:'请输入环号/位置'" value=""></td>
                </tr>  
                <tr>
                    <td class="form-td-label">里&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;程：</td>
                    <td><input id="lc" name="lc" class="easyui-textbox" data-options="prompt:'请输入里程'" style="width: 100%;"></td>
                    <td class="form-td-label">X坐标</td>
                    <td><input id="x" name="x" class="easyui-textbox" data-options="prompt:'请输入X坐标'" style="width: 100%;"></td>
                    
                </tr>
                <tr>
                	<td class="form-td-label">Y坐标</td>
                    <td><input id="y" name="y" class="easyui-textbox" data-options="prompt:'请输入Y坐标'" style="width: 100%;"></td>
                    <td class="form-td-label">Z坐标</td>
                    <td><input id="z" name="z" class="easyui-textbox" data-options="prompt:'请输入Z坐标'" style="width: 100%;"></td>
                </tr>
                <tr>
                    <td class="form-td-label">报警值上限：</td>
                    <td>
                      <input id="sx" name="sx" class="easyui-numberbox" data-options="prompt:'请输入报警值上限'" style="width: 125px;">&nbsp;&nbsp;毫米</td>
                    <td class="form-td-label">报警值下限：</td>
                    <td>
                         <input  id="xx" class="easyui-numberbox"   name='xx'   data-options="prompt:'请输入报警值下限'"  style='width: 125px'>&nbsp;&nbsp;毫米
                    </td>
                </tr> 
                <tr>
                    <td class="form-td-label">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
                    <td colspan="3">
					<input id="bz" name="bz" class="easyui-textbox" data-options="prompt:'请输入备注'" style="width:100%;height:80px">
                    </td>
                </tr>
                
            </table>           
            
        </form>
    </div>
</div>