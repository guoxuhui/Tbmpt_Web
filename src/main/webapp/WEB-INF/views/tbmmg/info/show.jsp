<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<meta http-equiv="pragma" content="no-cache"> 
<meta http-equiv="cache-control" content="no-cache"> 
<meta http-equiv="expires" content="0">   
<script type="text/javascript">
var tbmCode;
$(function() {
	tbm=${tbm};	
 	section=${section};
	pro=${pro};
	line=${line};
	$("#tbmCode").html(tbm.tbmCode);
	$("#tbmName").html(tbm.tbmName);
	$("#manageno").html(tbm.manageno);
	$("#functionaryName").html(tbm.functionaryName);
	$("#contactNumber").html(tbm.contactNumber);
	$("#manufacturer").html(tbm.manufacturer);
	$("#factorynumber").html(tbm.factorynumber);
	$("#specification").html(tbm.specification);
	$("#model").html(tbm.model);
	if(tbm.type=='1'){
		$("#type").html("土压平衡盾构机");		
	}
	if(tbm.type=='2'){
		$("#type").html("土压平衡盾构机");
	}
	
	$("#tbmVest").html(tbm.tbmVest);
	$("#factorydate").html(tbm.factorydate);
	$("#buydate").html(tbm.buydate);
	$("#sydc").html(tbm.sydc);
	$("#cslc").html(tbm.cslc);
	if(tbm.tbmState=='0'){
		$("#tbmState").html("未分配");	
	}else{
		$("#tbmState").html("已分配");
	}
	$("#remark").html(tbm.remark);
});
function openpro(){
	if(pro!=""){		
		$('#pro_win').dialog('open');
		$("#proName").html('');
		$("#tunnellength").html('');
		$("#ringwidth").html('');
		$("#proName").html(pro.proName);
		$("#tunnellength").html(pro.tunnellength);
		$("#ringwidth").html(pro.ringwidth);
	}else{
		$.messager.alert('提示', '无项目信息！');
	}
}
function opensection(){
	if(section!=""){
		$('#section_win').dialog('open');
		$("#sectionName").html('');
		$("#remark").html('');
		$("#sectionName").html(section.sectionName);
		$("#remark").html(section.remark);
	}else{
		$.messager.alert('提示', '无区间信息！');
	}
}
function openline(){
	if(line!=""){
		$('#line_win').dialog('open');
		$("#lineName").html('');
		$("#tunnellength").html('');
		$("#tunneltime").html('');
		$("#lineName").html(line.lineName);
		$("#tunnellength").html(line.tunnellength);
		$("#tunneltime").html(line.tunneltime);
	}else{
		$.messager.alert('提示', '无线路信息！');
	}
}

</script>
<div class="easyui-layout" data-options="fit:true,border:false">
	<form id="tbmForm" method="post" data-options="novalidate:false">
		<table class="grid" style="width:100%">
					<tr>
						<td class="form-td-label" style="width: 100px;"><span style="color: red;">*</span>盾构机编号：</td>
						<td class="form-td-content" colspan="3" style="width: 380px;">
							<span id="tbmCode"></span>							
						</td>
					</tr>
 					<tr>
						<td class="form-td-label" style="width: 100px;"><span style="color: red;">*</span>盾构机名称：</td>
						<td class="form-td-content" style="width: 140px;">
							<span id="tbmName"></span>
							<%-- ${tbm.tbmName} --%>
						</td>
						<td class="form-td-label" style="width: 100px;"><span style="color: red;">*</span>盾构机管理号：</td>
						<td class="form-td-content" style="width: 140px;">
							<span id="manageno"></span>
							<%-- ${tbm.manageno} --%>
						</td>
					</tr>
					<tr>
						<td class="form-td-label">负责人名称：</td>
						<td class="form-td-content">
							<span id="functionaryName"></span>
							<%-- ${tbm.functionaryName} --%>
						</td>
						<td class="form-td-label">负责人联系方式：</td>
						<td class="form-td-content">
							<span id="contactNumber"></span>
							<%-- ${tbm.contactNumber} --%>
						</td>
					</tr>
					<tr>
						<td class="form-td-label">生产厂商：</td>
						<td class="form-td-content">
							<span id="manufacturer"></span>
							<%-- ${tbm.manufacturer} --%>
						</td>
						<td class="form-td-label">出厂编号：</td>
						<td class="form-td-content">
							<span id="factorynumber"></span>
							<%-- ${tbm.factorynumber} --%>
						</td>
					</tr>
					<tr>
						<td class="form-td-label">规格：</td>
						<td class="form-td-content">
							<span id="specification"></span>
							<%-- ${tbm.specification} --%>
						<td class="form-td-label">型号：</td>
						<td class="form-td-content">
							<span id="model"></span>
							<%-- ${tbm.model} --%>
						</td>
					</tr>
					<tr>
						<td class="form-td-label"><span style="color: red;">*</span>盾构机类型：</td>
	                    <td class="form-td-content" style="width: 160px">
	                    	<span id="type"></span>
	                        <%-- <c:choose>
						       <c:when test="${tbm.type == '1'}">  
							         土压平衡盾构机
							   </c:when>
							   <c:when test="${tbm.type == '2'}">  
							         土压平衡盾构机
							   </c:when>
							   <c:when test="${tbm.type == '0'}">  
							        
							   </c:when>
							</c:choose> --%>
		                </td>
						
						<td class="form-td-label"><span style="color: red;">*</span>资产归属：</td>
						<td class="form-td-content">
							<span id="tbmVest"></span>
							<%-- ${tbm.tbmVest} --%>
						</td>
					</tr>
					<tr>
					    <td class="form-td-label">出厂日期：</td>
						<td class="form-td-content">
							<span id="factorydate"></span>
							<%-- ${tbm.factorydate}" --%>
						</td>
						<td class="form-td-label">购置日期：</td>
						<td class="form-td-content">
							<span id="buydate"></span>
							<%-- ${tbm.buydate} --%>
						</td>
						
					</tr>
					<tr>
					    <td class="form-td-label">适应地层：</td>
						<td class="form-td-content">
							<span id="sydc"></span>
							<%-- ${tbm.factorydate}" --%>
						</td>
						<td class="form-td-label">初始里程：</td>
						<td class="form-td-content">
							<span id="cslc"></span>
							<%-- ${tbm.buydate} --%>
						</td>
						
					</tr>
					<tr>
					    <td class="form-td-label">状态：</td>
						<td class="form-td-content">
							<span id="tbmState"></span>
							<%-- <c:choose>
							   <c:when test="${tbm.tbmState == '0'}">  
							         未分配
							   </c:when>
							   <c:otherwise> 
							         已分配
							   </c:otherwise>
							</c:choose> --%>

						</td>
					</tr>
					<tr>
						<td class="form-td-label">备注：</td>
						<td class=".form-td-content-3" colspan="3">
							<span id="remark"></span>
							<%-- ${tbm.remark} --%>
						</td>
					</tr>
				<tr>
                	<td class="form-td-label" >在建工程信息：</td>
                	<td>
                		<a href="javascript:void(0);" onclick="openpro()">点击打开在建工程详细信息</a>
                	</td>
                	<td class="form-td-label" >在建区间信息：</td>
                	<td>
                		<a href="javascript:void(0);" onclick="opensection()">点击打开在建区间详细信息</a>
                	</td>
                </tr>
                <tr>
                	<td class="form-td-label" >在建线路信息：</td>
                	<td>
                		<a href="javascript:void(0);" onclick="openline()">点击打开在建线路信息详细信息</a>
                	</td>
                </tr> 
			</table>
	</form>
</div>
<div id="pro_win" class="easyui-dialog" title="在建工程信息"
	style="width: 550px; height: 150px"
	data-options="modal:true,closed:true,inline:false,resizable:false,
maximizable:false,minimizable:false,collapsible:false">
	<form id="proForm" method="post" data-options="novalidate:true">
		<table class="grid" style="width:100%">
					<%-- <tr>
						<td class="form-td-label" style="width: 100px;"><span style="color: red;">*</span>工程编号：</td>
						<td class="form-td-content" colspan="3" style="width: 380px;">
							${pro.id}
						</td>
					</tr> --%>
					<tr>
						<td class="form-td-label" ><span style="color: red;">*</span>工程名称：</td>
						<td class="form-td-content" colspan="3">
							<span id="proName"></span>
							<%-- ${pro.proName} --%>
						</td>
					</tr>
					<tr>
						<td class="form-td-label" style="width: 100px;"><span style="color: red;">*</span>掘进总长度(米)：</td>
						<td class="form-td-content" style="width: 140px;">
							<span id="tunnellength"></span>
							<%-- ${pro.tunnellength} --%>
						</td>
						<td class="form-td-label" style="width: 100px;"><span style="color: red;">*</span>工程环宽(米)：</td>
						<td class="form-td-content" style="width: 140px;">
							<span id="ringwidth"></span>
							<%-- ${pro.ringwidth} --%>
						</td>
					</tr>
				</table>
	</form>
</div>
<div id="section_win" class="easyui-dialog" title="在建区间信息"
	style="width: 550px; height: 150px"
	data-options="modal:true,closed:true,inline:false,resizable:false,
maximizable:false,minimizable:false,collapsible:false">
	<form id="sectionForm" method="post" data-options="novalidate:true">
		<table class="grid" style="width:100%">
					
					<%-- <tr>
						<td class="form-td-label" style="width: 100px;"><span style="color: red;">*</span>区间编号：</td>
						<td class="form-td-content" colspan="3" style="width: 380px;">
							${section.sectionCode}
						</td>
					</tr> --%>
					<tr>
						<td class="form-td-label" style="width:100px"><span style="color: red;">*</span>区间名称：</td>
						<td class="form-td-content" colspan="3">
							<span id="sectionName"></span>
							<%-- ${section.sectionName} --%>
						</td>
					</tr>
					<tr>
						<td class="form-td-label" style="width:100px">区间介绍：</td>
						<td class="form-td-content" colspan="3">
							<span id="remark"></span>
							<%-- ${section.remark} --%>
						</td>
					</tr>
				</table>
	</form>
</div>
<div id="line_win" class="easyui-dialog" title="在建线路信息"
	style="width: 550px; height: 150px"
	data-options="modal:true,closed:true,inline:false,resizable:false,
maximizable:false,minimizable:false,collapsible:false">
	<form id="lineForm" method="post" data-options="novalidate:true">
		<table class="grid" style="width:100%">
					<%-- <tr>
						<td class="form-td-label" style="width: 100px;"><span style="color: red;">*</span>线路编号：</td>
						<td class="form-td-content" colspan="3" style="width: 380px;">
							${line.lineCode}
						</td>
					</tr> --%>
					<tr>
						<td class="form-td-label" style=""><span style="color: red;">*</span>线路名称：</td>
						<td class="form-td-content" colspan="3">
							<span id="lineName"></span>
							<%-- ${line.lineName} --%>
						</td>
					</tr>
					<tr>
						<td class="form-td-label" style="width: 100px;"><span style="color: red;">*</span>隧道长度(米)：</td>
						<td class="form-td-content" style="width: 140px;">
							<span id="tunnellength"></span>
							<%-- ${line.tunnellength} --%>
						</td>
						<td class="form-td-label" style="width: 100px;"><span style="color: red;">*</span>推进总工期(天)：</td>
						<td class="form-td-content" style="width: 140px;">
							<span id="tunneltime"></span>
							<%-- ${line.tunneltime} --%>
						</td>
					</tr>
				</table>
	</form>
</div>
	
