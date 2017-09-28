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
 * “添加日掘进”窗体页面
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
		//打开添加页面  选择 上级下拉列表框 更新加载
		//xlBh:线路Id
		$('#addRjj_BId').combobox({   
	        url:path+'/addBzgl?xlBh='+xlBh,   
	        editable:false, //不可编辑状态  
	        cache: false,  
	        panelHeight: '150',//自动高度适合  
	        valueField:'id',     
	        textField:'bzname'  
	       });  
		
	}); 

 /***
  *  wpg:“ 查看【日掘进明细 】“添加单条日掘进信息”窗体   保存按钮事件；
  */
  function saveFun(){
	  
	  var sghh = $('#addRjj_sghh').val();
	  var bzId = $('#addRjj_BId').val();
	 /***
	  * 列表表单为“编辑表单”，发生保存按钮事件；
	  */
	 $('#addOneRjjInfoForm').form('submit', {
			url : path+'/addSave',
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
<!-- wpg:“ 查看【日掘进明细 】“添加单条日掘进信息”窗体页面   -->
<div id="addDgjjRjjInfoDialog" class="easyui-dialog" 
   data-options="iconCls:'icon-save',fit:true,border:falseclosed:true,cache: false"
	style="padding:10px 10px;overflow: hidden;">
		<form id="addOneRjjInfoForm" method="post">
			<table>
				 <tr>
	                <td  class="form-td-label" ><span style="color: red;">*</span>班组名称：</td>
				    <td>
				        <input name='BId' id="addRjj_BId" required="required" class="easyui-combobox"  style="width:100%;" >
	                </td>
	                <!-- 整型 -->
	            	<td  class="form-td-label" ><span style="color: red;">*</span>施工环号：</td>
				    <td>
				        <input name='sghh' id='addRjj_sghh' required="required"  data-options="prompt:'请输入施工环号',validType:'length[1,40]'" class="easyui-numberbox" min="0" max="100000000"   class="easyui-validatebox span2"    style='width: 100%;'>
	                </td>
			        <!-- 双精度型 -->
			        <td  class="form-td-label" >管片变换关键里程：</td>
			        <td class="form-td-content" >
	                    <input name='gpbhgjlc' id='addRjj_gpbhgjlc'  data-options="prompt:'请输入管片变换关键里程',validType:'length[1,40]'"  class="easyui-numberbox" precision="6" min="0.000001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>	
			        </td>
			    </tr>
	            <tr>
			        <!-- 文本类型  --> 
		            <td  class="form-td-label" >设计类型：</td>
				    <td class="form-td-content" >
				        <input name='sjlx'  id='addRjj_sjlx' data-options="prompt:'请输入设计类型',validType:'length[1,40]'"  class="easyui-textbox"   class="easyui-validatebox span2"   style='width: 100%;'>
                    </td>
	            
	                <!-- 文本类型  -->
                    <td  class="form-td-label" >设计轴线：</td>
				    <td>
				        <input name='sjzx'  id='addRjj_sjzx' data-options="prompt:'请输入设计轴线',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
				    </td>
	                <!-- 文本类型  -->
                    <td  class="form-td-label" >施工类型：</td>
				    <td>
				        <input name='sglx'  id='addRjj_sglx' data-options="prompt:'请输入施工类型',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
				    </td>
				</tr>
	            <tr>
			        <!-- 整型 -->
			        <td  class="form-td-label" >封顶块位置：</td>
			        <td class="form-td-content" >
	                    <input name='fdkwz' id='addRjj_fdkwz'  data-options="prompt:'请输入封顶块位置',validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>
			        </td>
			     
	            	<td  class="form-td-label" >注浆压力Mpa：</td>
				    <td class="form-td-content" >
				        <!-- 双精度型  6-->
					    <input name='zjyl' id='addRjj_zjyl'  data-options="prompt:'请输入注浆压力Mpa',validType:'length[1,40]'"  class="easyui-numberbox" precision="6" min="0.000001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>	
					</td>
					
					<td  class="form-td-label"   align="right" >同步注浆量：</td>
					<td class="form-td-content"  >
					    <!-- 整型 -->
					    <input name='tbzjl' id='addRjj_tbzjl'  data-options="prompt:'请输入同步注浆量',validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>
	                </td>
	             </tr>
	             <tr>  
	            <!-- 第 8  列  -->
		            <td  class="form-td-label"  align="right" >同步注浆位置：</td>
					<td class="form-td-content"  >
					    <!-- 文本类型  -->
						<input name='tbzjwz'  id='addRjj_tbzjwz' data-options="prompt:'请输入同步注浆位置',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
	            	<td  class="form-td-label"  align="right" >管片注浆位置：</td>
					<td class="form-td-content"  >
					    <!-- 文本类型  -->
						<input name='gpzjwz'  id='addRjj_gpzjwz' data-options="prompt:'请输入管片注浆位置',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
					<td  class="form-td-label"  align="right" >后续注浆时间：</td>
					<td class="form-td-content"  >
					    <!-- 文本类型  -->
						<input name='hxzjsj'  id='addRjj_hxzjsj' data-options="prompt:'请输入后续注浆时间',validType:'length[1,40]'"  class="easyui-textbox"   class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
				</tr>
	            <tr>
					<td  class="form-td-label"  align="right" >土压（bar)：</td>
					<td class="form-td-content"  >
						<!-- 双精度型  3-->
						<input name='ty' id='addRjj_ty'  data-options="prompt:'请输入土压（bar)',validType:'length[1,40]'"  class="easyui-numberbox" precision="3" min="0.001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>
	                </td>
		            <td  class="form-td-label"  align="right" >出土量(方)：</td>
					<td class="form-td-content"  >
						<!-- 文本类型  -->        
						<input name='ctl'  id='addRjj_ctl' data-options="prompt:'请输入出土量(方)',validType:'length[1,40]'"  class="easyui-textbox"   class="easyui-validatebox span2"   style='width: 100%;'>
		            </td>
		            
		            <td  class="form-td-label"  align="right" >平均总推力(KN)：</td>
					<td class="form-td-content"  >
					    <!-- 双精度型  3-->
						<input name='pjztl' id='addRjj_pjztl'  data-options="prompt:'请输入平均总推力(KN)',validType:'length[1,40]'"  class="easyui-numberbox" precision="3" min="0.001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
			    </tr>
	            <tr>
					<td  class="form-td-label"  align="right" >平均扭矩（KN.m）：</td>
					<td class="form-td-content"  >
					    <!-- 双精度型  3-->
						<input name='pjnj' id='addRjj_pjnj'  data-options="prompt:'请输入平均扭矩（KN.m）',validType:'length[1,40]'"  class="easyui-numberbox" precision="3" min="0.001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
	                <td  class="form-td-label"  align="right" >隧道轴线-高程：</td>
					<td class="form-td-content"  >
						<!-- 双精度型  6-->
						<input name='sdzxgc' id='addRjj_sdzxgc'  data-options="prompt:'请输入隧道轴线-高程',validType:'length[1,40]'"  class="easyui-numberbox" precision="6" min="0.000001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>	
	         		</td>
	                <!-- 第 16 列  -->
	            	<td  class="form-td-label"  align="right" >隧道轴线-平面：</td>
					<td class="form-td-content"  >
						<!-- 双精度型  6-->
						<input name='sdzxpm' id='addRjj_sdzxpm'  data-options="prompt:'请输入隧道轴线-平面',validType:'length[1,40]'"  class="easyui-numberbox" precision="6" min="0.000001" max="100000000" class="easyui-validatebox span2"   style='width: 100%;'>	
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
						            <input name='gpazqA' id='addRjj_gpazqA'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>
							    </td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpazqB' id='addRjj_gpazqB'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpazqC' id='addRjj_gpazqC'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpazqD' id='addRjj_gpazqD'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
				                
				            </tr>
				            <tr>
				                <td  class="form-td-label"  align="right" >推进千斤顶-管片安装后：</td>
								<td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpazhA' id='addRjj_gpazhA'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>
							    </td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpazhB' id='addRjj_gpazhB'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpazhC' id='addRjj_gpazhC'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpazhD' id='addRjj_gpazhD'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
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
						            <input name='dgjzxspqk' id='addRjj_dgjzxspqk'  data-options="prompt:'请输入水平-切口',validType:'length[1,40]'" class="easyui-numberbox"  max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>
							    </td>
							    <td  class="form-td-label"  align="right" >水平-盾尾：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dgjzxspdw' id='addRjj_dgjzxspdw'  data-options="prompt:'请输入水平-盾尾',validType:'length[1,40]'" class="easyui-numberbox"  max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
							   
				            </tr>
				            <tr>
				               <td  class="form-td-label"  align="right" >垂直-切口：</td>
								<td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dgjzxczqk' id='addRjj_dgjzxczqk'  data-options="prompt:'请输入垂直-切口',validType:'length[1,40]'" class="easyui-numberbox"  max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>
							    </td>
							    <td  class="form-td-label"  align="right" >垂直-盾尾：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dgjzxczdw' id='addRjj_dgjzxczdw'  data-options="prompt:'请输入垂直-盾尾',validType:'length[1,40]'" class="easyui-numberbox" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
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
						            <input name='dwjxpzqs' id='addRjj_dwjxpzqs'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>
							    </td>
							    <td  class="form-td-label"  align="right" >下：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dwjxpzqx' id='addRjj_dwjxpzqx'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
							    <td  class="form-td-label"  align="right" >左：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dwjxpzqz' id='addRjj_dwjxpzqz'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
							    <td  class="form-td-label"  align="right" >右：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dwjxpzqy' id='addRjj_dwjxpzqy'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
				                
				            </tr>
				            <tr title="盾尾间隙-拼装后">
				                <td  class="form-td-label"  align="right" >拼装后：</td>
				                <td  class="form-td-label"  align="right" >上：</td>
								<td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dwjxpzhs' id='addRjj_dwjxpzhs'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>
							    </td>
							    <td  class="form-td-label"  align="right" >下：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dwjxpzhx' id='addRjj_dwjxpzhx'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
							    <td  class="form-td-label"  align="right" >左：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dwjxpzhz' id='addRjj_dwjxpzhz'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
							    <td  class="form-td-label"  align="right" >右：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dwjxpzhy' id='addRjj_dwjxpzhy'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
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
						            <input name='gpztpzqgc' id='addRjj_gpztpzqgc'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>
							    </td>
							    <td  class="form-td-label"  align="right" >拼装前-平面：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpztpzqpm' id='addRjj_gpztpzqpm'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
							   
				                
				            </tr>
				            <tr title="管片姿态-拼装后">
				                 <td  class="form-td-label"  align="right" >拼装后-高程：</td>
								<td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpztpzhgc' id='addRjj_gpztpzhgc'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>
							    </td>
							    <td  class="form-td-label"  align="right" >拼装后-平面：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpztpzhpm' id='addRjj_gpztpzhpm'  data-options="validType:'length[1,40]'" class="easyui-numberbox" min="1" max="100000000" class="easyui-validatebox span2"    style='width: 100%;'>	
							    </td>
							   
				            </tr>
			            </table>
	                </td>
	            </tr>
	                
	            
	            <tr>
	                <td  class="form-td-label"  align="right" >盾构掘进完成起止时间：</td>
					<td class="form-td-content"  >
					    <!-- 文本类型  -->        
						<input name='dgjjwcqzsj'  id='addRjj_dgjjwcqzsj' data-options="prompt:'请输入盾构掘进完成起止时间',validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
					<td  class="form-td-label"  align="right" >施工日期：</td>
					<td class="form-td-content"  >
					    <!-- 文本类型  --> 
					     <input   name='sgrq'  id='addRjj_sgrq' class="easyui-datebox" data-options="prompt:'请输入施工日期'"  class="easyui-validatebox span2"  style="width:100%;">  
					</td>
	            </tr>
	            <tr>
		            <td  class="form-td-label"  valign="top" align="right" >备注：</td>
					<td class="form-td-content"  colspan='7'>
					     <!-- 文本类型  -->        
						<input name='remarks'  id='addRjj_remarks' data-options="prompt:'请输入备注',multiline:true,validType:'length[1,40]'"  class="easyui-textbox"  class="easyui-validatebox span2"   style='width: 100%;'>
					</td>
	            </tr>
			</table>
			<!-- wpg:“ 查看【日掘进明细 】“添加单条日掘进信息”窗体 隐形 按钮，用于发生 按钮事件  -->
			<div id="toolbar"> 
			<a id="addSaveBtn" style="display:none" onclick="saveFun()" href="javascript:void(0);"
			class="easyui-linkbutton" 
			data-options="plain:true,iconCls:'icon-save'">保存</a>
		</div>
		</form>
</div>

 