<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
    	 $('#sjType').combobox({
             url : '${path}/dmcjjc/dmcjddinfo/getJcType',
             valueField: "ddName",
             textField: "ddName",
             onLoadSuccess:function(){
                 $('#sjType').combobox('setValue','${vo.sjType}');
             }
         });
    	 
    	 $('#sjTimeType').combobox({
             url : '${path}/dmcjjc/dmcjddinfo/getSjTime',
             valueField: "ddName",
             textField: "ddName",
             onLoadSuccess:function(){
                 $('#sjTimeType').combobox('setValue','${vo.sjTimeType}');
             }
         });
    	
        var i=0;
         $('#gcbh').combobox({
             url : '${path}/project/info/getProjects',
             valueField: "id",
             textField: "proName",
             onChange:function(){

            },
            onLoadSuccess:function(){
                $('#gcbh').combobox('setValue','${vo.gcbh}');
                var pid = $('#gcbh').combobox('getValue');
                $('#qjbh').combobox({
                    url : '${path}/project/line/getProSectionDic?proId='+pid,
                    valueField: "id",
                    textField: "sectionName",
                    onChange:function(){

                   },
                   onLoadSuccess:function(){
                       if(i ==0){
                           $('#qjbh').combobox('setValue','${vo.qjbh}');
                       }
                       var qjid = $('#qjbh').combobox('getValue');
                       $('#xlbh').combobox({
                          url : '${path}/project/line/getProLineDic?sectionId='+qjid,
                          valueField: "id",
                          textField: "lineName",
                          onLoadSuccess:function(){
                              if(i == 0 ){
                                  $('#xlbh').combobox('setValue','${vo.xlbh}');
                              }
                              i = 1;
                          }
                       });
                   }
                });
            }
         });
        
        $("#sjType").val("${vo.sjType}"); 
        $("#sjTimeType").val("${vo.sjTimeType}");  

        $('#addForm').form({
            url : '${path }/dmcjjc/dmcjjcpoint/update',
            onSubmit : function() {
                progressLoad();
                var isValid = $(this).form('validate');
                if (!isValid) {
                    progressClose();
                }
                var maxControl = $("#edit_maxControl").val();
                var minControl = $("#edit_minControl").val();
                var maxAlarm = $("#edit_maxAlarm").val();
                var minAlarm = $("#edit_minAlarm").val();
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
        <form id="addForm"  method="post">
        	<c:choose> <c:when test="${ifsy}">
        	<div class="light-info" style="overflow: hidden;padding: 3px;">
                <div>监测点已经存在检测报告信息，您将无法修改监测点的主要信息（工程、区间、编号、线路以及类型）。</div>
            </div>
			</c:when><c:otherwise></c:otherwise></c:choose>
            <table class="grid" style="width: 98%;">
                <tr>
                    <td class="form-td-label" style="width: 100px;"><span style="color: red;">*</span>工程名称：</td>
                    <td style="width: 400px;" colspan="3">
                      <select id="gcbh" name="gcbh" style="width: 100%;" class="easyui-combobox"data-options="required:true<c:choose> <c:when test="${ifsy}">,editable:false,readonly:true</c:when><c:otherwise></c:otherwise></c:choose>" ></select>
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width: 100px;"><span style="color: red;">*</span>点位编号：</td>
                    <td >
                        <input id="jcdbh" name="jcdbh" style="width: 100%;" class="easyui-textbox"  data-options="required:true,prompt:'请输入点位编号'<c:choose> <c:when test="${ifsy}">,editable:false,readonly:true</c:when><c:otherwise></c:otherwise></c:choose>" value="${vo.jcdbh}">
                    </td>
                    <td class="form-td-label" style="width: 100px;"><span style="color: red;">*</span>区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间：</td>
                    <td >
                        <select editable="false" id="qjbh" name="qjbh" class="easyui-combobox" data-options="required:true<c:choose> <c:when test="${ifsy}">,editable:false,readonly:true</c:when><c:otherwise></c:otherwise></c:choose>" style="width: 100%;"></select>
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label">线&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;路：</td>
                    <td style="width: 160px;">
                        <select editable="false" id="xlbh" name="xlbh" class="easyui-combobox" data-options="required:false<c:choose> <c:when test="${ifsy}">,editable:false,readonly:true</c:when><c:otherwise></c:otherwise></c:choose>" style="width: 100%;"></select>
                    </td>
                    <td class="form-td-label"><span style="color: red;">*</span>点位类型：</td>
                    <td style="width: 160px;">
                       <select editable="false" id="sjType" name="sjType" class="easyui-combobox" data-options="required:true<c:choose> <c:when test="${ifsy}">,editable:false,readonly:true</c:when><c:otherwise></c:otherwise></c:choose>" style="width: 100%;"></select>
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label">初始高程：</td>
                    <td>
                       <input id="csgc" name="csgc" style="width: 100%;"class="easyui-numberbox" precision="5" max="9999.99999"data-options="required:false" value="${vo.csgc}">
                    </td>
                    
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>里&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;程：</td>
                    <td>
                       <input id="licheng" name="licheng" type="text" class="easyui-textbox" data-options="required:true" value="${vo.licheng}" style="width: 100%;">
                    </td>
                    <td class="form-td-label"><span style="color: red;">*</span>设计时间：</td>
                    <td>
                    	<select editable="false" id="sjTimeType" name="sjTimeType" class="easyui-combobox" data-options="required:true" style="width: 100%;"></select>
                    </td>
                </tr> 
                 <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>控制值上限：</td>
                    <td>
                       <input  id="edit_maxControl" name="maxControl" class="easyui-numberbox"  value="${vo.maxControl}" data-options="min:-99,max:99,precision:1,required:true,prompt:'请输入控制值上限'" style="width: 125px">&nbsp;&nbsp;毫米</td>
                    <td class="form-td-label"><span style="color: red;">*</span>控制值下限：</td>
                    <td>
                        <input id="edit_minControl" class="easyui-numberbox"  name='minControl' value="${vo.minControl}"  data-options="min:-99,max:99,precision:1,prompt:'请输入控制值下限',validType:'length[1,40]'"  style='width: 125px'>&nbsp;&nbsp;毫米
                    </td>
                </tr> 
                 <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>报警值上限：</td>
                    <td>
                      <input id="edit_maxAlarm" name="maxAlarm" class="easyui-numberbox"  value="${vo.maxAlarm}" data-options="min:-99,max:99,precision:1,required:true,prompt:'请输入报警值上限'" style="width: 125px;">&nbsp;&nbsp;毫米</td>
                    <td class="form-td-label"><span style="color: red;">*</span>报警值下限：</td>
                    <td>
                         <input id="edit_minAlarm" class="easyui-numberbox"   name='minAlarm'  value="${vo.minAlarm}"   data-options="min:-99,max:99,precision:1,prompt:'请输入报警值下限',validType:'length[1,40]'"  style='width: 125px'>&nbsp;&nbsp;毫米
                    </td>
                </tr> 
                <tr>
                    <td class="form-td-label">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
                    <td colspan="3">
                    <input id="remarks" name="remarks" class="easyui-textbox" data-options="validType:'length[1,200]',required:false,prompt:'请输入备注',multiline:true" value="${vo.remarks}" style="width:100%;height:80px">
                    </td>
                </tr>
            </table>
            <!-- 修改保持原样 -->
            <input id="ifqy" name="ifqy" type="hidden" value="${vo.ifqy}">
            <input id="id" name="id" type="hidden" value="${vo.id}">
        </form>
    </div>
</div>
