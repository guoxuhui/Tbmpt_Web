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
                    $('#projname').text($('#gcbh').combobox('getText'));
                }
            });
         $('#qjbh').combobox({
        	 url : '${path}/project/line/getProSectionDic?proId='+'${jc.gcbh}',
             valueField : "id",
             textField : "sectionName",
             onLoadSuccess:function(){
                 $('#qjbh').combobox('setValue','${jc.qjbh}');
                 $('#qjname').text($('#qjbh').combobox('getText'));
             }
         });
         $('#xlbh').combobox({
        	 url : '${path}/project/line/getProLineDic?sectionId='+'${jc.qjbh}',
             valueField : "id",
             textField : "lineName",
             onLoadSuccess:function(){
                 $('#xlbh').combobox('setValue','${jc.xlbh}');
                 $('#xlname').text($('#xlbh').combobox('getText'));
             }
         });
         
   });
</script>
<div  class="easyui-layout" data-options="fit:true,border:false" >
            <table class="grid">
             <tr>
                    <td class="form-td-label" style="width:100px;">工程名称：</td>
                    <td colspan="3" style="width:500px">
                    <div style="display:none;" ><select id="gcbh" name="gcbh" class="easyui-combobox" data-options="required:true"  style="width: 140px;" readonly="true"></select></div>
                    <span id='projname'></span>
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width:100px">点位编号：</td>
                    <td style="width:200px">${jc.jcdbh}</td>
                    <td class="form-td-label" style="width:100px;">区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;间：</td>
                    <td style="width:200px"><span id='qjname'></span>
                    <div style="display:none;" ><select id="qjbh" name="qjbh" class="easyui-combobox" style="width: 95%;margin-right: 15px"></select></div>
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label">线&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;路：</td>
                    <td><span id='xlname'></span>
                    <div style="display:none;" ><select id="xlbh" name="xlbh" class="easyui-combobox" style="width: 95%;margin-right: 15px"></select></div></td>
                    <td class="form-td-label">点位类型：</td>
                    <td>${jc.sjType}</td>
                </tr>
                <tr>
                    <td class="form-td-label">环号/位置：</td>
                    <td>${jc.weizhi}</td>
                    <td class="form-td-label">初始高程(米)：</td>
                    <td>${jc.csgc}</td>
                </tr>
                <tr>
                    <td class="form-td-label">里&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;程：</td>
                    <td>${jc.licheng}</td>
                    <td class="form-td-label">设计时间：</td>
                    <td>${jc.sjTimeType}</td>
                </tr>
                 <tr>
                    <td class="form-td-label">控制值上限：</td>
                    <td>${jc.maxControl}</td>
                    <td class="form-td-label">控制值下限：</td>
                    <td>${jc.minControl}</td>
                </tr>
                 <tr>
                    <td class="form-td-label">报警值上限：</td>
                    <td>${jc.maxAlarm}</td>
                    <td class="form-td-label">报警值下限：</td>
                    <td>${jc.minAlarm}</td>
                </tr>
                <tr>
                    <td class="form-td-label">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
                    <td colspan="3">
                    ${jc.remarks}
                    </td>
                </tr>
            </table>
            <!-- 明细信息表格 -->
            <table id="dataGrid" data-options="fit:true,border:false"></table>
            <div id="toolbar" />
</div>