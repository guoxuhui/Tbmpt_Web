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

<!-- wpg:“ 查看【日掘进明细 】“查看单条日掘进信息”窗体页面  -->
<div id="lookDgjjRjjInfoDialog" class="easyui-dialog" 
   data-options="iconCls:'icon-save',fit:true,border:falseclosed:true,cache: false"
	style="plooking:10px 10px;overflow: hidden;">
		<form id="lookOneRjjInfoForm" method="post">
			<input id="id" name="id" type="hidden"  value="${Dto.id}">
			<table>
				 <tr>
	                <td  class="form-td-label" ><span style="color: red;">*</span>班组名称：</td>
				    <td> 

				    <select  name="BId" id="lookRjj_BId" class="easyui-combobox" data-options="editable:false" style="width: 100%;">
                       <option value = "${Dto.BId}" selected = "selected">${Dto.bzname}</option>
                    
                    </select>
				       
	                </td>
	                <!-- 整型 -->
	            	<td  class="form-td-label" ><span style="color: red;">*</span>施工环号：</td>
				    <td>
				        <input name='sghh' id='lookRjj_sghh' value="${Dto.sghh}"  data-options="editable:false"  class="easyui-numberbox"   style='width: 100%;'>
	                </td>
			        <!-- 双精度型 -->
			        <td  class="form-td-label" >管片变换关键里程：</td>
			        <td class="form-td-content" >
	                    <input name='gpbhgjlc' id='lookRjj_gpbhgjlc' value="${Dto.gpbhgjlc}"  data-options="editable:false" class="easyui-numberbox"    style='width: 100%;'>	
			        </td>
			    </tr>
	            <tr>
			        <!-- 文本类型  --> 
		            <td  class="form-td-label" >设计类型：</td>
				    <td class="form-td-content" >
				        <input name='sjlx'  id='lookRjj_sjlx'value="${Dto.sjlx}"  data-options="editable:false"class="easyui-textbox"    style='width: 100%;'>
                    </td>
	            
	                <!-- 文本类型  -->
                    <td  class="form-td-label" >设计轴线：</td>
				    <td>
				        <input name='sjzx'  id='lookRjj_sjzx'value="${Dto.sjzx}"  data-options="editable:false" class="easyui-textbox"  style='width: 100%;'>
				    </td>
	                <!-- 文本类型  -->
                    <td  class="form-td-label" >施工类型：</td>
				    <td>
				        <input name='sglx'  id='lookRjj_sglx'value="${Dto.sglx}" data-options="editable:false" class="easyui-textbox"   style='width: 100%;'>
				    </td>
				</tr>
	            <tr>
			        <!-- 整型 -->
			        <td  class="form-td-label" >封顶块位置：</td>
			        <td class="form-td-content" >
	                    <input name='fdkwz' id='lookRjj_fdkwz'value="${Dto.fdkwz}" data-options="editable:false" class="easyui-numberbox"    style='width: 100%;'>
			        </td>
			     
	            	<td  class="form-td-label" >注浆压力Mpa：</td>
				    <td class="form-td-content" >
				        <!-- 双精度型  6-->
					    <input name='zjyl' id='lookRjj_zjyl'value="${Dto.zjyl}" data-options="editable:false"  class="easyui-numberbox"     style='width: 100%;'>	
					</td>
					
					<td  class="form-td-label"   align="right" >同步注浆量：</td>
					<td class="form-td-content"  >
					    <!-- 整型 -->
					    <input name='tbzjl' id='lookRjj_tbzjl'value="${Dto.tbzjl}" data-options="editable:false" class="easyui-numberbox"    style='width: 100%;'>
	                </td>
	             </tr>
	             <tr>  
	            <!-- 第 8  列  -->
		            <td  class="form-td-label"  align="right" >同步注浆位置：</td>
					<td class="form-td-content"  >
					    <!-- 文本类型  -->
						<input name='tbzjwz'  id='lookRjj_tbzjwz'value="${Dto.tbzjwz}" data-options="editable:false" class="easyui-textbox"    style='width: 100%;'>
					</td>
	            	<td  class="form-td-label"  align="right" >管片注浆位置：</td>
					<td class="form-td-content"  >
					    <!-- 文本类型  -->
						<input name='gpzjwz'  id='lookRjj_gpzjwz'value="${Dto.gpzjwz}"   class="easyui-textbox"     style='width: 100%;'>
					</td>
					<td  class="form-td-label"  align="right" >后续注浆时间：</td>
					<td class="form-td-content"  >
					    <!-- 文本类型  -->
						<input name='hxzjsj'  id='lookRjj_hxzjsj'value="${Dto.hxzjsj}" data-options="editable:false"  class="easyui-textbox"   style='width: 100%;'>
					</td>
				</tr>
	            <tr>
					<td  class="form-td-label"  align="right" >土压（bar）：</td>
					<td class="form-td-content"  >
						<!-- 双精度型  3-->
						<input name='ty' id='lookRjj_ty' value="${Dto.ty}" data-options="editable:false"  class="easyui-numberbox"   style='width: 100%;'>
	                </td>
		            <td  class="form-td-label"  align="right" >出土量(方)：</td>
					<td class="form-td-content"  >
						<!-- 文本类型  -->        
						<input name='ctl'  id='lookRjj_ctl'value="${Dto.ctl}"  data-options="editable:false" class="easyui-textbox"    style='width: 100%;'>
		            </td>
		            
		            <td  class="form-td-label"  align="right" >平均总推力(KN)：</td>
					<td class="form-td-content"  >
					    <!-- 双精度型  3-->
						<input name='pjztl' id='lookRjj_pjztl' value="${Dto.pjztl}" data-options="editable:false" class="easyui-numberbox"    style='width: 100%;'>
					</td>
			    </tr>
	            <tr>
					<td  class="form-td-label"  align="right" >平均扭矩（KN.m）：</td>
					<td class="form-td-content"  >
					    <!-- 双精度型  3-->
						<input name='pjnj' id='lookRjj_pjnj' value="${Dto.pjnj}" data-options="editable:false" class="easyui-numberbox"   style='width: 100%;'>
					</td>
	                <td  class="form-td-label"  align="right" >隧道轴线-高程：</td>
					<td class="form-td-content"  >
						<!-- 双精度型  6-->
						<input name='sdzxgc' id='lookRjj_sdzxgc' value="${Dto.sdzxgc}" data-options="editable:false" class="easyui-numberbox"   style='width: 100%;'>	
	         		</td>
	                <!-- 第 16 列  -->
	            	<td  class="form-td-label"  align="right" >隧道轴线-平面：</td>
					<td class="form-td-content"  >
						<!-- 双精度型  6-->
						<input name='sdzxpm' id='lookRjj_sdzxpm' value="${Dto.sdzxpm}"  data-options="editable:false" class="easyui-numberbox"  style='width: 100%;'>	
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
						            <input name='gpazqA' id='lookRjj_gpazqA'value="${Dto.gpazqA}" data-options="editable:false" class="easyui-numberbox"    style='width: 100%;'>
							    </td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpazqB' id='lookRjj_gpazqB'value="${Dto.gpazqB}" data-options="editable:false" class="easyui-numberbox" style='width: 100%;'>	
							    </td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpazqC' id='lookRjj_gpazqC'value="${Dto.gpazqC}"  data-options="editable:false"  class="easyui-numberbox"   style='width: 100%;'>	
							    </td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpazqD' id='lookRjj_gpazqD' value="${Dto.gpazqD}" data-options="editable:false" class="easyui-numberbox"   style='width: 100%;'>	
							    </td>
				                
				            </tr>
				            <tr>
				                <td  class="form-td-label"  align="right" >推进千斤顶-管片安装后：</td>
								<td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpazhA' id='lookRjj_gpazhA'value="${Dto.gpazhA}"  data-options="editable:false" class="easyui-numberbox"  style='width: 100%;'>
							    </td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpazhB' id='lookRjj_gpazhB' value="${Dto.gpazhB}" data-options="editable:false" class="easyui-numberbox"    style='width: 100%;'>	
							    </td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpazhC' id='lookRjj_gpazhC' value="${Dto.gpazhC}" data-options="editable:false" class="easyui-numberbox"    style='width: 100%;'>	
							    </td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpazhD' id='lookRjj_gpazhD' value="${Dto.gpazhD}" data-options="editable:false" class="easyui-numberbox"    style='width: 100%;'>	
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
						            <input name='dgjzxspqk' id='lookRjj_dgjzxspqk' value="${Dto.dgjzxspqk}"data-options="editable:false"  class="easyui-numberbox"    style='width: 100%;'>
							    </td>
							    <td  class="form-td-label"  align="right" >水平-盾尾：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dgjzxspdw' id='lookRjj_dgjzxspdw' value="${Dto.dgjzxspdw}"  data-options="editable:false" class="easyui-numberbox"    style='width: 100%;'>	
							    </td>
							   
				            </tr>
				            <tr>
				               <td  class="form-td-label"  align="right" >垂直-切口：</td>
								<td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dgjzxczqk' id='lookRjj_dgjzxczqk' value="${Dto.dgjzxczqk}" data-options="editable:false" class="easyui-numberbox"   style='width: 100%;'>
							    </td>
							    <td  class="form-td-label"  align="right" >垂直-盾尾：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dgjzxczdw' id='lookRjj_dgjzxczdw' value="${Dto.dgjzxczdw}"data-options="editable:false" class="easyui-numberbox"   style='width: 100%;'>	
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
						            <input name='dwjxpzqs' id='lookRjj_dwjxpzqs' value="${Dto.dwjxpzqs}"  data-options="editable:false" class="easyui-numberbox"    style='width: 100%;'>
							    </td>
							    <td  class="form-td-label"  align="right" >下：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dwjxpzqx' id='lookRjj_dwjxpzqx' value="${Dto.dwjxpzqx}" data-options="editable:false" class="easyui-numberbox"    style='width: 100%;'>	
							    </td>
							    <td  class="form-td-label"  align="right" >左：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dwjxpzqz' id='lookRjj_dwjxpzqz' value="${Dto.dwjxpzqz}" data-options="editable:false" class="easyui-numberbox"   style='width: 100%;'>	
							    </td>
							    <td  class="form-td-label"  align="right" >右：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dwjxpzqy' id='lookRjj_dwjxpzqy' value="${Dto.dwjxpzqy}" data-options="editable:false" class="easyui-numberbox"   style='width: 100%;'>	
							    </td>
				                
				            </tr>
				            <tr title="盾尾间隙-拼装后">
				                <td  class="form-td-label"  align="right" >拼装后：</td>
				                <td  class="form-td-label"  align="right" >上：</td>
								<td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dwjxpzhs' id='lookRjj_dwjxpzhs' value="${Dto.dwjxpzhs}" data-options="editable:false" class="easyui-numberbox"    style='width: 100%;'>
							    </td>
							    <td  class="form-td-label"  align="right" >下：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dwjxpzhx' id='lookRjj_dwjxpzhx' value="${Dto.dwjxpzhx}" data-options="editable:false" class="easyui-numberbox"    style='width: 100%;'>	
							    </td>
							    <td  class="form-td-label"  align="right" >左：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dwjxpzhz' id='lookRjj_dwjxpzhz' value="${Dto.dwjxpzhz}" data-options="editable:false" class="easyui-numberbox"     style='width: 100%;'>	
							    </td>
							    <td  class="form-td-label"  align="right" >右：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='dwjxpzhy' id='lookRjj_dwjxpzhy' value="${Dto.dwjxpzhy}" data-options="editable:false" class="easyui-numberbox"    style='width: 100%;'>	
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
						            <input name='gpztpzqgc' id='lookRjj_gpztpzqgc' value="${Dto.gpztpzqgc}"  data-options="editable:false" class="easyui-numberbox"  style='width: 100%;'>
							    </td>
							    <td  class="form-td-label"  align="right" >拼装前-平面：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpztpzqpm' id='lookRjj_gpztpzqpm' value="${Dto.gpztpzqpm}" data-options="editable:false" class="easyui-numberbox"    style='width: 100%;'>	
							    </td>
							   
				                
				            </tr>
				            <tr title="管片姿态-拼装后">
				                 <td  class="form-td-label"  align="right" >拼装后-高程：</td>
								<td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpztpzhgc' id='lookRjj_gpztpzhgc' value="${Dto.gpztpzhgc}" data-options="editable:false" class="easyui-numberbox"    style='width: 100%;'>
							    </td>
							    <td  class="form-td-label"  align="right" >拼装后-平面：</td>
							    <td class="form-td-content"  >
									<!-- 整型 -->
						            <input name='gpztpzhpm' id='lookRjj_gpztpzhpm'value="${Dto.gpztpzhpm}"  data-options="editable:false" class="easyui-numberbox"   style='width: 100%;'>	
							    </td>
							   
				            </tr>
			            </table>
	                </td>
	            </tr>
	                
	            
	            <tr>
	                <td  class="form-td-label"  align="right" >盾构掘进完成起止时间：</td>
					<td class="form-td-content"  >
					    <!-- 文本类型  -->        
						<input name='dgjjwcqzsj'  id='lookRjj_dgjjwcqzsj'value="${Dto.dgjjwcqzsj}" data-options="editable:false"  class="easyui-textbox"    style='width: 100%;'>
					</td>
					<td  class="form-td-label"  align="right" >施工日期：</td>
					<td class="form-td-content"  >
					    <!-- 文本类型  -->         
					    <input name='sgrq'  id='lookRjj_sgrq' value="${Dto.sgrq}" data-options="editable:false" class="easyui-datebox"  style="width:100%;">  
					</td>
	            </tr>
	            <tr>
		            <td  class="form-td-label"  valign="top" align="right" >备注：</td>
					<td class="form-td-content"  colspan='7'>
					     <!-- 文本类型  -->        
						<input name='remarks'  id='lookRjj_remarks'value="${Dto.remarks}" data-options="editable:false" class="easyui-textbox"    style='width: 100%;'>
					</td>
	            </tr>
			</table>
			<!-- wpg:“ 查看【日掘进明细 】“查看单条日掘进信息”窗体 隐形 按钮，用于发生 按钮事件  -->
			<div id="lookoolbar"> 
			<a id="lookSaveBtn" style="display:none" onclick="lookSaveFun()" href="javascript:void(0);"
			class="easyui-linkbutton" 
			data-options="plain:true,iconCls:'icon-save'">保存</a>
		</div>
		</form>
</div>

 