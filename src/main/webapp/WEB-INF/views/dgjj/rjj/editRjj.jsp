<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<style type="text/css">
	td{
		 white-space: nowrap;
	}
	td input{
		white-space: normal;
	}
</style>
<script type="text/javascript">

/*
 * “设置曲线要素”窗体页面
 */

 
 /** 系统模块同路径 */
 var syspath = "dgjj";
 /** 子模块路径 */
 var module = "rjj";
 /** 菜单名称 */
 var entityCnName = "日掘进信息管理";
 /** 名称字段 */
 var nameField = "";
 /** 业务数据访问全路径 */
 var path = basePath +"/"+ syspath +"/"+ module+"/dgjjRjjInfo";

 var xlBh;
 $(function() {

	//获取 主表 线路Id
		xlBh =${xlBh};
		
		//打开编辑页面  选择 上级下拉列表框 更新加载
		//xlBh:线路Id
		$('#editRjj_BId').combobox({   
	        url:path+'/addBzgl?xlBh='+xlBh,   
	        editable:false, //不可编辑状态  
	        cache: false,  
	        panelHeight: '150',//自动高度适合  
	        valueField:'id',     
	        textField:'bzname'  
	       });  
	
	}); 

 /***
  *  wpg:“ 查看【日掘进明细 】“编辑单条日掘进信息”窗体页面   保存事件；
  */
  function editSaveFun(){
	  
	  var sghh = $('#editRjj_sghh').val();
	  var bzId = $('#editRjj_BId').val();
	 /***
	  * 列表表单为“编辑表单”，发生保存按钮事件；
	  */
	 $('#editOneRjjInfoForm').form('submit', {
			url : path+'/editSave',
			onSubmit : function() {
			    progressLoad();
			    var isValid = $(this).form('validate');
			    if (!isValid || sghh=="" || bzId=="") {
			        progressClose();
			    }
			    return isValid;
			},
			success : function(result) {
				
			    progressClose();
			    result = $.parseJSON(result);
			    if (result.success) {
			    	
			    	parent.$.messager.show({title:'提示',msg:result.msg,showType:'show' });               
			    	parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为dgjjRjjInfo.js页面预定义好了
			    	parent.$.modalDialog.handler.dialog('close');
			    } else {
			        parent.$.messager.alert('错误', result.msg, 'error');
			    }
			}
		});
 }
</script>
    
<!-- wpg:“ 查看【日掘进明细 】“编辑单条日掘进信息”窗体页面    -->
<div id="editDgjjRjjInfoDialog" class="easyui-dialog" 
   data-options="iconCls:'icon-save',fit:true,border:falseclosed:true,cache: false"
	style="pediting:10px 10px;overflow: hidden;">
		<form id="editOneRjjInfoForm" method="post">
			<input id="id" name="id" type="hidden"  value="${Dto.id}">
			<table>
				 <tr>
	                <td  class="form-td-label" ><span style="color: red;">*</span>班组名称：</td>
				    <td> 

				    <select  name="BId" id="editRjj_BId"required="required" class="easyui-combobox" data-options="required:true,editable:false"  style="width: 100%;">
                       <option value = "${Dto.BId}" selected = "selected">${Dto.bzname}</option>
                    
                    </select>
				        <!-- <input name='BId' id="editRjj_BId" required="required" class="easyui-combobox"  style="width:100%;" > -->
	                </td>
	                <!-- 整型 -->
	            	<td  class="form-td-label" ><span style="color: red;">*</span>施工环号：</td>
				    <td>
				        <input name='sghh' id='editRjj_sghh' value="${Dto.sghh}"required="required"  data-options="prompt:'请输入施工环号',validType:'length[1,40]'" class="easyui-numberbox" min="0" max="100000000"   class="easyui-validatebox span2"    style='width: 100%;'>
	                </td>
			        <!-- 双精度型 -->
			        <td  class="form-td-label" >管片变换关键里程：</td>
			        <td class="form-td-content" >
	                    <input name='gpbhgjlc' id='editRjj_gpbhgjlc' value="${Dto.gpbhgjlc}" data-options="prompt:'请输入管片变换关键里程',validType:'length[1,40]'"  class="easyui-numberbox" precision="6" min="0.000001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>	
			        </td>
			    </tr>
	            <tr>
			        <!-- 文本类型  --> 
		            <td  class="form-td-label" >设计类型：</td>
				    <td class="form-td-content" >
				        <input name='sjlx'  id='editRjj_sjlx'value="${Dto.sjlx}" data-options="prompt:'请输入设计类型',validType:'length[1,40]'"  class="easyui-textbox" class="easyui-numberbox" class="easyui-validatebox span2"   style='width: 100%;'>
                    </td>
	            
	                <!-- 文本类型  -->
                    <td  class="form-td-label" >设计轴线：</td>
				    <td>
				        <input name='sjzx'  id='editRjj_sjzx'value="${Dto.sjzx}" data-options="prompt:'请输入设计轴线',validType:'length[1,40]'"  class="easyui-textbox" class="easyui-numberbox" class="easyui-validatebox span2"   style='width: 100%;'>
				    </td>
	                <!-- 文本类型  -->
                    <td  class="form-td-label" >施工类型：</td>
				    <td>
				        <input name='sglx'  id='editRjj_sglx'value="${Dto.sglx}" data-options="prompt:'请输入施工类型',validType:'length[1,40]'"  class="easyui-textbox" class="easyui-numberbox" class="easyui-validatebox span2"   style='width: 100%;'>
				    </td>
				</tr>
	            <tr>
			        <!-- 整型 -->
			        <td  class="form-td-label" >封顶块位置：</td>
			        <td class="form-td-content" >
	                    <input name='fdkwz' id='editRjj_fdkwz'value="${Dto.fdkwz}"  data-options="prompt:'请输入封顶块位置',validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>
			        </td>
			     
	            	<td  class="form-td-label" >注浆压力Mpa：</td>
				    <td class="form-td-content" >
				        <!-- 双精度型  6-->
					    <input name='zjyl' id='editRjj_zjyl'value="${Dto.zjyl}"  data-options="prompt:'请输入注浆压力Mpa',validType:'length[1,40]'"  class="easyui-numberbox" precision="6" min="0.000001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>	
					</td>
					
					<td  class="form-td-label"   align="right" >同步注浆量：</td>
					<td class="form-td-content"  >
					    <!-- 整型 -->
					    <input name='tbzjl' id='editRjj_tbzjl'value="${Dto.tbzjl}"  data-options="prompt:'请输入同步注浆量',validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>
	                </td>
	             </tr>
	             <tr>  
	            <!-- 第 8  列  -->
		            <td  class="form-td-label"  align="right" >同步注浆位置：</td>
					<td class="form-td-content"  >
					    <!-- 文本类型  -->
						<input name='tbzjwz'  id='editRjj_tbzjwz'value="${Dto.tbzjwz}" data-options="prompt:'请输入同步注浆位置',validType:'length[1,40]'"  class="easyui-textbox" class="easyui-numberbox" class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
	            	<td  class="form-td-label"  align="right" >管片注浆位置：</td>
					<td class="form-td-content"  >
					    <!-- 文本类型  -->
						<input name='gpzjwz'  id='editRjj_gpzjwz'value="${Dto.gpzjwz}" data-options="prompt:'请输入管片注浆位置',validType:'length[1,40]'"  class="easyui-textbox" class="easyui-numberbox" class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
					<td  class="form-td-label"  align="right" >后续注浆时间：</td>
					<td class="form-td-content"  >
					    <!-- 文本类型  -->
						<input name='hxzjsj'  id='editRjj_hxzjsj'value="${Dto.hxzjsj}" data-options="prompt:'请输入后续注浆时间',validType:'length[1,40]'"  class="easyui-textbox" class="easyui-numberbox" class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
				</tr>
	            <tr>
					<td  class="form-td-label"  align="right" >土压（bar）：</td>
					<td class="form-td-content"  >
						<!-- 双精度型  3-->
						<input name='ty' id='editRjj_ty' value="${Dto.ty}" data-options="prompt:'请输入土压（bar)',validType:'length[1,40]'"  class="easyui-numberbox" precision="3" min="0.001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>
	                </td>
		            <td  class="form-td-label"  align="right" >出土量(方)：</td>
					<td class="form-td-content"  >
						<!-- 文本类型  -->        
						<input name='ctl'  id='editRjj_ctl'value="${Dto.ctl}" data-options="prompt:'请输入出土量(方)',validType:'length[1,40]'"  class="easyui-textbox" class="easyui-numberbox" class="easyui-validatebox span2"   style='width: 100%;'>
		            </td>
		            
		            <td  class="form-td-label"  align="right" >平均总推力(KN)：</td>
					<td class="form-td-content"  >
					    <!-- 双精度型  3-->
						<input name='pjztl' id='editRjj_pjztl' value="${Dto.pjztl}" data-options="prompt:'请输入平均总推力(KN)',validType:'length[1,40]'"  class="easyui-numberbox" precision="3" min="0.001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
			    </tr>
	            <tr>
					<td  class="form-td-label"  align="right" >平均扭矩（KN.m）：</td>
					<td class="form-td-content"  >
					    <!-- 双精度型  3-->
						<input name='pjnj' id='editRjj_pjnj' value="${Dto.pjnj}" data-options="prompt:'请输入平均扭矩（KN.m）',validType:'length[1,40]'"  class="easyui-numberbox" precision="3" min="0.001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
	                <td  class="form-td-label"  align="right" >隧道轴线-高程：</td>
					<td class="form-td-content"  >
						<!-- 双精度型  6-->
						<input name='sdzxgc' id='editRjj_sdzxgc' value="${Dto.sdzxgc}" data-options="prompt:'请输入隧道轴线-高程',validType:'length[1,40]'"  class="easyui-numberbox" precision="6" min="0.000001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>	
	         		</td>
	                <!-- 第 16 列  -->
	            	<td  class="form-td-label"  align="right" >隧道轴线-平面：</td>
					<td class="form-td-content"  >
						<!-- 双精度型  6-->
						<input name='sdzxpm' id='editRjj_sdzxpm' value="${Dto.sdzxpm}" data-options="prompt:'请输入隧道轴线-平面',validType:'length[1,40]'"  class="easyui-numberbox" precision="6" min="0.000001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>	
	         		</td>
	            </tr>
	            <tr>
	                <td colspan='8'>
			            <table class="grid" title="推进千斤顶">
			            <tr><td >推进千斤顶:</td></tr>
				            <tr >
				                <td  class="form-td-label"  align="right" >推进千斤顶-管片安装前：</td>
								<td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpazqA' id='editRjj_gpazqA'value="${Dto.gpazqA}"  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>
							    </td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpazqB' id='editRjj_gpazqB'value="${Dto.gpazqB}"  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpazqC' id='editRjj_gpazqC'value="${Dto.gpazqC}"  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpazqD' id='editRjj_gpazqD' value="${Dto.gpazqD}" data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
				                
				            </tr>
				            <tr>
				                <td  class="form-td-label"  align="right" >推进千斤顶-管片安装后：</td>
								<td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpazhA' id='editRjj_gpazhA'value="${Dto.gpazhA}"  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>
							    </td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpazhB' id='editRjj_gpazhB' value="${Dto.gpazhB}" data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpazhC' id='editRjj_gpazhC' value="${Dto.gpazhC}" data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpazhD' id='editRjj_gpazhD' value="${Dto.gpazhD}" data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
				            </tr>
			            </table>
	                </td>
	            </tr>
	            
	            <tr>
	                <td colspan='8'>
			            <table class="grid" title="盾构机轴线姿态">
			                <tr><td >盾构机轴线姿态:</td></tr>
				            <tr>
				                <td  class="form-td-label"  align="right" >水平-切口：</td>
								<td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dgjzxspqk' id='editRjj_dgjzxspqk' value="${Dto.dgjzxspqk}" data-options="prompt:'请输入水平-切口',validType:'length[1,40]'" class="easyui-numberbox"  max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>
							    </td>
							    <td  class="form-td-label"  align="right" >水平-盾尾：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dgjzxspdw' id='editRjj_dgjzxspdw' value="${Dto.dgjzxspdw}" data-options="prompt:'请输入水平-盾尾',validType:'length[1,40]'" class="easyui-numberbox"  max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
							   
				            </tr>
				            <tr>
				               <td  class="form-td-label"  align="right" >垂直-切口：</td>
								<td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dgjzxczqk' id='editRjj_dgjzxczqk' value="${Dto.dgjzxczqk}" data-options="prompt:'请输入垂直-切口',validType:'length[1,40]'" class="easyui-numberbox"  max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>
							    </td>
							    <td  class="form-td-label"  align="right" >垂直-盾尾：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dgjzxczdw' id='editRjj_dgjzxczdw' value="${Dto.dgjzxczdw}" data-options="prompt:'请输入垂直-盾尾',validType:'length[1,40]'" class="easyui-numberbox" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
				            </tr>
			            </table>
	                </td>
	            </tr>
	            
	            <tr>
	                <td colspan='8'>
			            <table class="grid" title="盾尾间隙（mm）">
			            <tr><td >盾尾间隙（mm）:</td></tr>
				            <tr title="盾尾间隙-拼装前">
				                <td  class="form-td-label"  align="right" >拼装前：</td>
				                <td  class="form-td-label"  align="right" >上：</td>
								<td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dwjxpzqs' id='editRjj_dwjxpzqs' value="${Dto.dwjxpzqs}" data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>
							    </td>
							    <td  class="form-td-label"  align="right" >下：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dwjxpzqx' id='editRjj_dwjxpzqx' value="${Dto.dwjxpzqx}" data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
							    <td  class="form-td-label"  align="right" >左：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dwjxpzqz' id='editRjj_dwjxpzqz' value="${Dto.dwjxpzqz}" data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
							    <td  class="form-td-label"  align="right" >右：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dwjxpzqy' id='editRjj_dwjxpzqy' value="${Dto.dwjxpzqy}" data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
				                
				            </tr>
				            <tr title="盾尾间隙-拼装后">
				                <td  class="form-td-label"  align="right" >拼装后：</td>
				                <td  class="form-td-label"  align="right" >上：</td>
								<td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dwjxpzhs' id='editRjj_dwjxpzhs' value="${Dto.dwjxpzhs}" data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>
							    </td>
							    <td  class="form-td-label"  align="right" >下：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dwjxpzhx' id='editRjj_dwjxpzhx' value="${Dto.dwjxpzhx}" data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
							    <td  class="form-td-label"  align="right" >左：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dwjxpzhz' id='editRjj_dwjxpzhz' value="${Dto.dwjxpzhz}" data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
							    <td  class="form-td-label"  align="right" >右：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dwjxpzhy' id='editRjj_dwjxpzhy' value="${Dto.dwjxpzhy}" data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
				            </tr>
			            </table>
	                </td>
	            </tr>
	            
	            <tr>
	                <td colspan='8'>
			            <table class="grid" title="管片姿态（mm）">
			                <tr><td >管片姿态（mm）:</td></tr>
				            <tr title="管片姿态-拼装前">
				                <td  class="form-td-label"  align="right" >拼装前-高程：</td>
								<td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpztpzqgc' id='editRjj_gpztpzqgc' value="${Dto.gpztpzqgc}" data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>
							    </td>
							    <td  class="form-td-label"  align="right" >拼装前-平面：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpztpzqpm' id='editRjj_gpztpzqpm' value="${Dto.gpztpzqpm}" data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
							   
				                
				            </tr>
				            <tr title="管片姿态-拼装后">
				                 <td  class="form-td-label"  align="right" >拼装后-高程：</td>
								<td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpztpzhgc' id='editRjj_gpztpzhgc' value="${Dto.gpztpzhgc}" data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>
							    </td>
							    <td  class="form-td-label"  align="right" >拼装后-平面：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpztpzhpm' id='editRjj_gpztpzhpm'value="${Dto.gpztpzhpm}"  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
							   
				            </tr>
			            </table>
	                </td>
	            </tr>
	                
	            
	            <tr>
	                <td  class="form-td-label"  align="right" >盾构掘进完成起止时间：</td>
					<td class="form-td-content"  >
					    <!-- 文本类型  -->        
						<input name='dgjjwcqzsj'  id='editRjj_dgjjwcqzsj'value="${Dto.dgjjwcqzsj}" data-options="prompt:'请输入盾构掘进完成起止时间',validType:'length[1,40]'"  class="easyui-textbox" class="easyui-numberbox" class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
					<td  class="form-td-label"  align="right" >施工日期：</td>
					<td class="form-td-content"  >
					    <!-- 文本类型  --> 
					    <input   name='sgrq'  id='editRjj_sgrq' class="easyui-datebox" value="${Dto.sgrq}"data-options="prompt:'请输入施工日期'"  class="easyui-validatebox span2"  style="width:100%;">   
					</td>
	            </tr>
	            <tr>
		            <td  class="form-td-label"  valign="top" align="right" >备注：</td>
					<td class="form-td-content"  colspan='7'>
					     <!-- 文本类型  -->        
						<input name='remarks'  id='editRjj_remarks'value="${Dto.remarks}" data-options="prompt:'请输入备注',multiline:true,validType:'length[1,40]'"  class="easyui-textbox" class="easyui-numberbox" class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
	            </tr>
			</table>
			<!-- wpg:“ 查看【日掘进明细 】“编辑单条日掘进信息”窗体 隐形 按钮，用于发生 按钮事件  -->
			<div id="ediToolbar"> 
			<a id="editSaveBtn" style="display:none" onclick="editSaveFun()" href="javascript:void(0);"
			class="easyui-linkbutton" 
			data-options="plain:true,iconCls:'icon-save'">保存</a>
		</div>
		</form>
</div>

 