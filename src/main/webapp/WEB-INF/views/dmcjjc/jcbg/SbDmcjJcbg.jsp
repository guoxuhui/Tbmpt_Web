<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
     $(function() {
        $('#gcbh').combobox({
            url : basePath + '/project/info/getProjects',
            valueField : "id",
            textField : "proName",
            onLoadSuccess:function(){
                $('#gcbh').combobox('setValue','${jc.gcbh}');
            }
        });
        var tian = '${jc.tianqi}';
        $("#tianqi").val(tian); 

        $('#addForm').form({
            url : '${path}/dmcjjc/bg/sbDmcjJcbg',
            onSubmit : function() {
                progressLoad();
                var isValid = $(this).form('validate');
                if (!isValid) {
                    progressClose();
                }
                saveFun();
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
    });

function saveFun(){
    var detailsInfo=new Array();
    $('#tbody').children().each(function(index){
        var obj = new Object();;
        var detailId;
        $(this).children().each(function(index){
            if(0 == index){
                detailId = $(this).text();
            }else if(1 == index){
                obj.qujian=$(this).text();
            }else if(2 == index){
                obj.xianlu=$(this).text();
            }else if(3 == index){
                obj.jcdNo=$(this).text();
            }else if(4 == index){
                obj.weizhi=$(this).text();
            }else if(5 == index){
                obj.licheng=$(this).text();
            }else if(7 == index){
                obj.bcgc = $(this).text();
            }
        });
        detailsInfo[index] = obj;
    });
    var json = JSON.stringify(detailsInfo);
    $('#details').val(json);
}
</script>
<title>监测报告上报</title>
<script type="text/javascript">
</script>
</head>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;" >
        <form id="addForm" method="post">
            <input type='hidden' id='details' name='details' />
            <input id="id" name="id" type="hidden"  value="${jc.id}">
            <input id="remarks" name="remarks" type="hidden"  value="${jc.remarks}">
            <table class="grid">
                <tr>
                    <td>工程名称：</td>
                    <td><select editable="false" id="gcbh" name="gcbh" class="easyui-combobox" data-options="required:true"  style="width: 140px;" readonly="true"></select></td>
                </tr>
                <tr>
                    <td>本次监测时间：</td>
                    <td>
                        <input id="jcTime" name="jcTime" placeholder="点击选择时间" data-options="required:true" readonly="readonly" value="${jc.jcTime}" 
                        onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="easyui-validatebox"/>
                    </td>
                </tr>
                <tr>
                    <td>天气：</td>
                    <td>
                        <select editable="false" id="tianqi" name="tianqi" class="easyui-combobox" data-options="required:true" style="width: 140px;">
                            <option value="晴">晴</option>
                            <option value="多云">多云</option>
                            <option value="小雨">小雨</option>
                            <option value="大雨">大雨</option>   
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>备注：</td>
                    <td colspan="3">
                    ${jc.remarks}
                    </td>
                </tr>
            </table>
            <!-- 明细信息表格 -->
            <table class="grid">
            	<tr>
            		<td>区间</td><td>线路</td><td>点位编号</td><td>里程</td><td>环号/位置</td><td>初始高程</td><td>本次高程</td>
            		<td>上次高程</td><td>本次变化量</td><td>累计变化量</td><td>变化速率</td>
            	</tr>
            	<tbody id="tbody">
            	<c:forEach var="user" items="${jcdetails}" varStatus="s">
				<tr>
                    <td style='display:none;'>${user.id}</td>
					<td><c:out value="${user.qujian}"></c:out></td>
					<td><c:out value="${user.xianlu}"/></td>
					<td><c:out value="${user.jcdNo}"></c:out></td>
					<td><c:out value="${user.licheng}"/></td>
					<td><c:out value="${user.weizhi}"></c:out></td>
					<td><c:out value="${user.csgc}"></c:out></td>
					<td><c:out value="${user.bcgc}"/></td>
					<td><c:out value="${user.scgc}"/></td>
					<td><c:out value="${user.bcbhl}"></c:out></td>
					<td><c:out value="${user.ljbhl}"/></td>
					<td><c:out value="${user.bhsl}"></c:out></td>
				</tr>
				</c:forEach>
				</tbody>
            </table>
            
        </form>
    </div>
</div>