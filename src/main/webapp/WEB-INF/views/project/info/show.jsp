<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!-- 引用上传图片的部分jsp和js -->
<style type="text/css">
	#tooltip1{
	position:absolute;
	border:1px solid #eeeeee;
	background:#eeeeee;
	padding:1px 1px 1px 1px;
	display:none;
	z-index:99999;
}
</style>

<script type="text/javascript" src="${staticPath}/static/js/sys/base/uploadPicture.js?v=20170405121212" charset="utf-8"></script>

    <!-- 弹出框－查看 -->
<div class="easyui-layout" data-options="fit:true,border:false" >
	<div class="easyui-panel" data-options="region:'center',border:false,collapsible:false"  
		style="width:100%;height:100%;border: false;style="overflow-y:auto;">
        <form id="showForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden">
			<table class="grid">
	            <tr>
					<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>工程名称：</td>
                    <td class="form-td-content" colspan="5" >
                    	<input id="show_proName"  data-options="editable:false"class="easyui-textbox" style="width:100%">
                    </td>
                </tr>
                <tr>
                   <td class="form-td-label">工程简称：</td>
                    <td class="form-td-content">
                    	<input id="show_abbreviation" name="abbreviation"  data-options="editable:false"class="easyui-textbox" style="width:100%">
                    </td>
                    
                    <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>负责人：</td>
                    <td class="form-td-content" style="width:150px">
                    	<input id="show_empName" name="empName" data-options="editable:false"class="easyui-textbox"  style="width:100%">
                    	<input id="show_empId" name="empId" type="hidden"/>
                    </td>
                    <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>施工单位：</td>
                    <td class="form-td-content" style="width:150px">
                    	<input id="show_parentName" name="parentName" data-options="editable:false"class="easyui-textbox"  style="width:100%">
                    </td>
					
                </tr>
                <tr>
                    <td class="form-td-label">承建单位：</td>
                    <td class="form-td-content">
                    	<input id="show_cjdw" name="cjdw" data-options="editable:false"class="easyui-textbox" style="width:100%" >
                    </td>
                    <td class="form-td-label">监理单位：</td>
                    <td class="form-td-content">
                    	<input id="show_jldw" name="jldw" data-options="editable:false"class="easyui-textbox" style="width:100%">
                    </td>
                    <td class="form-td-label">建设单位：</td>
                    <td class="form-td-content">
                    	<input id="show_jsdw" name="jsdw" data-options="editable:false"class="easyui-textbox" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width:100px"><span style="color: red;">*</span>地理位置：</td>
                    <td class="form-td-content" style="width:150px">
                    	<input id="show_position" name="position"  data-options="editable:false"class="easyui-textbox" style="width:100%">
                    </td>	
					<td class="form-td-label"><span style="color: red;">*</span>所在国家：</td>
                    <td class="form-td-content">
                    	<input id="show_country" name="country"  data-options="editable:false"class="easyui-textbox" style="width:100%">
                    </td>
					<td class="form-td-label"><span style="color: red;">*</span>所在片区：</td>
                    <td class="form-td-content">
                    	<input id="show_area" name="area"  data-options="editable:false"class="easyui-textbox" style="width:100%">
                    </td>
                    
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>所在省份：</td>
                    <td class="form-td-content">
                    	<input id="show_province" name="province" data-options="editable:false"class="easyui-textbox" style="width:100%">
                    </td>
                	<td class="form-td-label"><span style="color: red;">*</span>所在城市：</td>
                    <td class="form-td-content">
                    	<input id="show_city" name="city"  data-options="editable:false" class="easyui-textbox" style="width:100%">
                    </td>
                    <td class="form-td-label">总长度(米)：</td>
                	<td class="form-td-content">
                		<input id="show_tunnellength" name="tunnellength" data-options="editable:false" class="easyui-textbox" style="width:100%">
                	</td>
                	
                </tr>
                <tr>
                    <td class="form-td-label"><span style="color: red;">*</span>管片长度(m)：</td>
                	<td class="form-td-content">
                		<input id="show_ringwidth" name="ringwidth"  data-options="editable:false" class="easyui-textbox" style="width:100%">
                	</td>
                	<td class="form-td-label">合同工期起始：</td>
                	<td class="form-td-content">
                		<input id="show_startdate" name="startdate" data-options="editable:false" class="easyui-textbox" style="width:100%">
                	</td>
                    <td class="form-td-label">合同工期截止：</td>
                	<td class="form-td-content">
                		<input id="show_estimateenddate" name="estimateenddate" data-options="editable:false" class="easyui-textbox" style="width:100%">
                	</td>
                </tr>
                <tr>
                	<td class="form-td-label">地铁线路：</td>
                	<td class="form-td-content">
                		<input id="show_line" name="line"  data-options="editable:false" class="easyui-textbox" style="width:100%">
                	</td>
                	<td class="form-td-label">合同金额：</td>
                	<td class="form-td-content">
                		<input id="show_htje" name="htje"  data-options="editable:false" class="easyui-textbox" style="width:100%">
                	</td>
                	<td class="form-td-label">开累完成：</td>
                	<td class="form-td-content">
                		<input id="show_klwc" name="klwc"  data-options="editable:false" class="easyui-textbox" style="width:100%">
                	</td>
                </tr>
                <tr>
                	<td class="form-td-label">管片外径(m)：</td>
                	<td class="form-td-content">
                		<input id="show_gpwj" name="gpwj"  data-options="editable:false" class="easyui-textbox" style="width:100%">
                	</td>
                	<td class="form-td-label"><span style="color: red;">*</span>施工状态：</td>
                	<td class="form-td-content">
                		<input id="show_proState" name="proState"  data-options="editable:false" class="easyui-textbox" style="width:100%">
                	</td>
                </tr>
                <tr>
                	<td class="form-td-label">施工地址：</td>
                    <td class="form-td-content" colspan="5">
                    	<input id="show_projectaddress" name="projectaddress" data-options="editable:false" class="easyui-textbox" style="width:100%">
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label" rowspan="5">工程简介：</td>
                    <td colspan="5" rowspan="5">
                    	<input id="show_description"  data-options="editable:false,multiline:true" class="easyui-textbox" style="width:100%;height:100%">
                    </td>
                </tr>
                <tr>
                	<td ></td>
                </tr>
                <tr>
                	<td ></td>
                </tr>
                <tr>
                	<td ></td>
                </tr>
                <tr>
                	<td ></td>
                </tr>
            </table>
        <div id="img_div_project"></div>
        </form>
</div>
</div>
<!-- 先加载上传图片的jsp,js最后加载才能调用查看图片的方法 -->
<script type="text/javascript">
$(function() {
	var pro=${pro};
	var orgz=${orgz};
/* 	var orgzJsdw=${orgzJsdw}; */
	var emp=${emp};
	$("#show_proName").val(pro.proName);
	$("#show_abbreviation").val(pro.abbreviation);
	if(emp!=""&&emp!=null){
	$("#show_empName").val(emp.name);
	}
	if(orgz!=""&&orgz!=null){
	$("#show_parentName").val(orgz.name);
	}
	$("#show_cjdw").val(pro.cjdw);
	$("#show_jldw").val(pro.jldw);
	$("#show_jsdw").val(pro.jsdw);
	$("#show_position").val(pro.position);
	$("#show_country").val(pro.country);
	$("#show_area").val(pro.area);
	$("#show_province").val(pro.province);
	$("#show_city").val(pro.city);
	$("#show_tunnellength").val(pro.tunnellength);
	$("#show_ringwidth").val(pro.ringwidth);
	$("#show_startdate").val(pro.startdate);
	$("#show_estimateenddate").val(pro.estimateenddate);
	$("#show_line").val(pro.line);
	$("#show_htje").val(pro.htje);
	$("#show_klwc").val(pro.klwc);
	$("#show_gpwj").val(pro.gpwj);
	if(pro.proState==0){
		$("#show_proState").val('未开工');
	}else if(pro.proState==1){
		$("#show_proState").val('在建');	
	}else if(pro.proState==2){
		$("#show_proState").val('完工');
	}
	$("#show_projectaddress").val(pro.projectaddress);
	$("#show_description").val(pro.description);
	_showAllFileInDiv(pro.id,'img_div_project');
	/* disableForm('showForm',true);  */
});
</script>	