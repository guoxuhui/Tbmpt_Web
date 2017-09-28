<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
    	 $('#gcbh').combobox({
             url : '${path}/project/info/getProjects',
             valueField: "id",
             textField: "proName",
             onChange:function(){
                var pid = $('#gcbh').combobox('getValue');
                 $('#add_qjbh').combobox({
                     url : "${path}/project/line/getProSectionDic?proId="+pid,
                     valueField: "id",
                     textField: "sectionName",
                     onChange:function(){
                        var qjid = $('#add_qjbh').combobox('getValue');
                         $('#add_xlbh').combobox({
                             url : '${path}/project/line/getProLineDic?sectionId='+qjid,
                             valueField: "id",
                             textField: "lineName"
                         });
                    }
                 });
            }
         });
    	 
    	 $('#sjType').combobox({
             url : '${path}/dmcjjc/dmcjddinfo/getJcType',
             valueField: "ddName",
             textField: "ddName"
         });
    	 
    	 $('#sjTimeType').combobox({
             url : '${path}/dmcjjc/dmcjddinfo/getSjTime',
             valueField: "ddName",
             textField: "ddName"
         });
    	
        $('#addForm').form({
            url : '${path }/dmcjjc/dmcjjcpoint/save',
            onSubmit : function() {
                progressLoad();
                var isValid = $(this).form('validate');
                if (!isValid) {
                    progressClose();
                }
                var maxControl = $("#add_maxControl").val();
                var minControl = $("#add_minControl").val();
                var maxAlarm = $("#add_maxAlarm").val();
                var minAlarm = $("#add_minAlarm").val();
                var vaResult = validMaxAndMin(maxControl,minControl,maxAlarm,minAlarm);
                if(null != vaResult && '' !=vaResult && 'undefined' != vaResult){
                	parent.$.messager.alert('错误', vaResult, 'error');
                	 progressClose();
                	return false;
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
        /**
         * 方法说明：比较控制值、报警值各自上限值是否大于下限值
         * @param maxControl 控制最大值
         * @param minControl 控制最小值
         * @param maxAlarm   报警最大值
         * @param minAlarm   报警最小值
         * @returns  若不否和要求则返回错误信息，否则返回空字符串
         * @author:YangYj
         * @Time: 2016年12月12日 下午5:32:27
         */
        function validMaxAndMin(maxControl,minControl,maxAlarm,minAlarm){
        	if(null == maxControl || '' == maxControl || 'undefined' == maxControl){
        		maxControl = 0;
        	}
        	if(null == minControl || '' == minControl || 'undefined' == minControl){
        		minControl = 0;
        	}
        	if(null == maxAlarm || '' == maxAlarm || 'undefined' == maxAlarm){
        		maxAlarm = 0;
        	}
        	if(null == minAlarm || '' == minAlarm || 'undefined' == minAlarm){
        		minAlarm = 0;
        	}
        	if(maxControl < minControl) return "控制值上限不可小于控制值下限,请确认。";
        	if(maxAlarm < minAlarm) return "报警值上限不可小于报警值下限,请确认。";
        	return "";
        }
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;" >
        <form id="addForm" method="post">
            <table class="grid" style="width: 98%;">
                <tr>
                    <td class="form-td-label" style="width: 100px;"><span style="color: red;">*</span>工程名称：</td>
                    <td colspan='3' style="width: 400px;">
                    <select editable="false" id="gcbh" name="gcbh" class="easyui-combobox" data-options="required:true,prompt:'请输入工程名称'" style="width: 100%;"></select>
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width: 100px;"><span style="color: red;">*</span>点位编号：</td>
                    <td style="width: 160px;">
                         <input id="jcdbh" name="jcdbh" class="easyui-textbox" data-options="required:true,prompt:'请输入点位编号'" style="width: 100%;">
                    </td>
                    <td class="form-td-label" style="width: 100px;"><span style="color: red;">*</span>区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间：</td>
                    <td style="width: 150px;">
                         <select editable="false" id="add_qjbh" name="qjbh" class="easyui-combobox" data-options="required:true,prompt:'请输入区间名称'" style="width: 100%;"></select>
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label">线&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;路：</td>
                    <td>
                    <select editable="false" id="add_xlbh" name="xlbh" class="easyui-combobox" data-options="prompt:'请输入线路'" style="width: 100%;"></select>
                    </td>
                    <td class="form-td-label"><span style="color: red;">*</span>点位类型：</td>
                    <td>
                    <select editable="false" id="sjType" name="sjType" class="easyui-combobox" data-options="required:true,prompt:'请输入点位类型'" style="width: 100%;"></select>
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>环号/位置：</td>
                    <td>
                       <input id="weizhi" name="weizhi" class="easyui-textbox" style="width: 100%;" data-options="required:true,prompt:'请输入环号/位置'" value="">
                    </td>
                    <td class="form-td-label">初始高程：</td>
                    <td>
                       <input id="csgc" name="csgc" class="easyui-numberbox" precision="5" max="9999.99999" style="width: 100%;" data-options="prompt:'请输入初始高程'" value="">
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>里&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;程：</td>
                    <td>
                        <input id="licheng" name="licheng" class="easyui-textbox" data-options="required:true,prompt:'请输入里程'" style="width: 100%;">
                    </td>
                    <td class="form-td-label"><span style="color: red;">*</span>设计时间：</td>
                    <td>
                        <select editable="false" id="sjTimeType" name="sjTimeType" class="easyui-combobox" data-options="required:true,prompt:'请输入设计时间'" style="width: 100%;"></select>
                    </td>
                </tr> 
                
                 <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>控制值上限：</td>
                    <td>
                       <input id="add_maxControl"  name="maxControl" class="easyui-numberbox" data-options="min:-99,max:99,precision:1,required:true,prompt:'请输入控制值上限'" style="width: 125px">&nbsp;&nbsp;毫米</td>
                    <td class="form-td-label"><span style="color: red;">*</span>控制值下限：</td>
                    <td>
                        <input id="add_minControl" class="easyui-numberbox"  name='minControl'  data-options="min:-99,max:99,precision:1,prompt:'请输入控制值下限',validType:'length[1,40]'"  style='width: 125px'>&nbsp;&nbsp;毫米
                    </td>
                </tr> 
                 <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>报警值上限：</td>
                    <td>
                      <input id="add_maxAlarm" name="maxAlarm" class="easyui-numberbox" data-options="min:-99,max:99,precision:1,required:true,prompt:'请输入报警值上限'" style="width: 125px;">&nbsp;&nbsp;毫米</td>
                    <td class="form-td-label"><span style="color: red;">*</span>报警值下限：</td>
                    <td>
                         <input  id="add_minAlarm" class="easyui-numberbox"   name='minAlarm'   data-options="min:-99,max:99,precision:1,prompt:'请输入报警值下限',validType:'length[1,40]'"  style='width: 125px'>&nbsp;&nbsp;毫米
                    </td>
                </tr> 
                <tr>
                    <td class="form-td-label">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
                    <td colspan="3">
					<input id="remarks" name="remarks" class="easyui-textbox" data-options="validType:'length[1,200]',required:false,prompt:'请输入备注',multiline:true" style="width:100%;height:80px">
                    </td>
                </tr>
                
            </table>
            <!-- 默认新增数据禁用 -->
            <input id="ifqy" name="ifqy" type="hidden" value="启用">
        </form>
    </div>
</div>