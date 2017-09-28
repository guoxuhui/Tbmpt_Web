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
                <table class="grid" style="width:700px">
				<tr>
                	<td class="form-td-label" style="width:100px">项目名称:</td>
                	<td class="form-td-content" style="width:220px">
                    	<span id="show_proName"></span>
                	</td>
                	<td class="form-td-label" style="width:100px">安全风险级别:</td>
                	<td class="form-td-content" style="width:220px">
                    	<div style="width:160px;height:80%"><span id="show_riskLevel" ></span></div>
                	</td>
            	</tr>

                <tr>
                	<td class="form-td-label" >安全质量风险:</td>
                    <td class="form-td-content" colspan="3" >
                    	<span id="show_riskDesc" style="display:inline-block;width:500px;word-wrap:break-word;white-space:normal;"></span>
                    </td>
                </tr>
                  <tr>
                	<td class="form-td-label" >上报人:</td>
                    <td class="form-td-content"  >
                    	<span id="upUser"></span>
                    </td>
                    <td class="form-td-label" >上报人联系方式:</td>
                    <td class="form-td-content" >
                    <span id="upUserPhone"></span>
                    </td>
                </tr>
                <tr>
                	<td class="form-td-label" >监控时段:</td>
                    <td class="form-td-content"  >
                    	<span id="show_riskTime"></span>
                    </td>
                    <td class="form-td-label" >是否排除:</td>
                    <td class="form-td-content" >
                    	<span id="show_isOut"></span>
                    </td>
                </tr>
                 <tr>
                	<td class="form-td-label" >负责部门:</td>
                    <td class="form-td-content" colspan="3" >
                    	<span id="show_dpts"></span>
                    </td>
                </tr>
                 <tr>
                	<td class="form-td-label" >负责人:</td>
                    <td class="form-td-content" colspan="3" >
                    	<span id="show_persoon"></span>
                    </td>
                </tr>
                 <tr>
                	<td class="form-td-label" >主要管控措施:</td>
                    <td class="form-td-content" colspan="3" style="height:50px">
                    	<span id="show_mainControlMethod" style="display:inline-block;width:550px;word-wrap:break-word;white-space:normal;"></span>
                    </td>
                </tr>

                <tr>
                	<td class="form-td-label" style="width:100px">备注:</td>
                    <td class="form-td-content" colspan="3" style="height:20px">
                    	<span id="show_remark" style="display:inline-block;width:550px;word-wrap:break-word;white-space:normal;"></span>
                    </td>
                </tr>

            </table>
           <div id="img_div_project" style="display:inline-block;width:550px;"></div>
        </form>
	</div>
</div>
<!-- 先加载上传图片的jsp,js最后加载才能调用查看图片的方法 -->
<script type="text/javascript">
$(function() {
	var info=${info};
	$("#show_proName").html(info.proName);
	var level=info.rikeLevel;
	if(level!=""){
		$("#show_riskLevel").parent().css("background-color",level.split(",")[1]);
		$("#show_riskLevel").css("color",'white');
		$("#show_riskLevel").css("text-align",'center');
		$("#show_riskLevel").css("width",'200px');
		$("#show_riskLevel").css("height",'50px');
		$("#show_riskLevel").html(level.split(",")[0]);
	}

	$("#show_riskDesc").html(info.rikeDesc);
    $("#show_riskTime").html(info.rikeTimeStart+(info.rikeTimeEnd?"    至    "+info.rikeTimeEnd:""));
	if(info.isOut==0){
		$("#show_isOut").html('未处理');
	}else{
		$("#show_isOut").html('已处理');
	}


	$("#upUserPhone").html(info.upUserPhone);
	$("#upUser").html(info.upUser);
	$("#show_dpts").html(info.dpts);
	$("#show_persoon").html(info.persoon);
	$("#show_mainControlMethod").html(info.mainControlMethod);
	$("#show_remark").html(info.remark);
	_showAllFileInDiv(info.id,'img_div_project','相关附件');
});
</script>