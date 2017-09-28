<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="shangBaoShow_div_gczlYdxjGPZLXJInfo" >
		<table class="grid"  style="width: 780px">
		  <tr id="shangBaoShow_tr_shqk">
		    <td class="form-td-label" ><span style="color: red;">*</span>审核情况：</td>
		    <td class="form-td-content" >
		      <select class="easyui-combobox" class="easyui-validatebox span2" name="shzt"  data-options="prompt:'-请选择-',required:true"  style='width: 150px'>
			      	<option value="已审核">已审核</option>
			      	<option value="已打回">已打回</option>
			  </select>
		    </td>
		    <td  class="form-td-label">整改人员：</td>
		    <td class="form-td-content" colspan="3">
		      <input class="easyui-textbox" class="easyui-validatebox span2" data-options="prompt:'请填写整改人员'" name='zgry' style='width: 150px'>
		    </td>
		  </tr>
		  <tr id="shangBaoShow_tr_shyj">
		    <td  class="form-td-label" valign="top" align="right" ><span style="color: red;">*</span>审核意见：</td>
		    <td class="form-td-content" colspan="5">
		      <input class="easyui-textbox" class="easyui-validatebox span2"  name='shsm'  data-options="prompt:'请输入审核意见',multiline:true,validType:'length[1,400]',required:true"  style='width: 100%;height:50px'>
		    </td>
		  </tr>
		  <tr>
		  	<td  class="form-td-label"   align="right" ><span style="color: red;">*</span>整改截止时间：</td>
		    <td class="form-td-content" id="sh_zgjzTime" >
		      <input class="easyui-datetimebox" class="easyui-validatebox span2"  name='zgjzTime'  id='sh_zgjzTime_gczlYdxjSGZLXJInfo' style='width: 150px'>
		    </td>
		    <td  class="form-td-label" >上报状态：</td>
		    <td class="form-td-content" colspan="5">
		      <input class="easyui-textbox" class="easyui-validatebox span2" name="sbzt" style='width: 150px' readonly="readonly"/>
		    </td>
		  </tr>
		  <tr>
		    <td  class="form-td-label" >维护时间：</td>
		    <td class="form-td-content" >
		      <input class="easyui-textbox" class="easyui-validatebox span2"  name='whtime'  style='width: 150px' readonly="readonly">
		    </td>
		    <td  class="form-td-label" >巡检部门：</td>
		    <td class="form-td-content">
		      <input class="easyui-textbox" class="easyui-validatebox span2"  name='xjbmName' style='width: 150px' readonly="readonly">
		    </td>
		    <td  class="form-td-label">巡检人员：</td>
		    <td class="form-td-content" >
		      <input class="easyui-textbox" class="easyui-validatebox span2"  name='xjryName' style='width: 150px' readonly="readonly">
		    </td>
		  </tr>
		  <tr>
		  </tr>
		  <tr>
		    <td  class="form-td-label" >上报时间：</td>
		    <td class="form-td-content" >
		      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sbtime'  style='width: 150px' readonly="readonly">
		    </td>
		    <td  class="form-td-label" >上报部门：</td>
		    <td class="form-td-content">
		      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sbbmName' style='width: 150px' readonly="readonly">
		    </td>
		    <td  class="form-td-label">上报人员：</td>
		    <td class="form-td-content">
		      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sbryName' style='width: 150px' readonly="readonly">
		    </td>
		  </tr>
		</table>
</div>

<div id="zhengGaiShow_div_gczlYdxjGPZLXJInfo" >
		<table class="grid" style="width: 780px">
		  <tr id="zhengGaiShow_tr_zgjg">
		    <td  class="form-td-label" ><span style="color: red;">*</span>整改结果：</td>
		    <td class="form-td-content">
		      <select class="easyui-combobox" class="easyui-validatebox span2" name="zgzt"  data-options="prompt:'-请选择-',required:true"  style='width: 150px'>
			      	<option value="已整改">已整改</option>
			  </select>
		    </td>
		    <td  class="form-td-label" ><span style="color: red;">*</span>整改时间：</td>
		    <td class="form-td-content">
		      <input class="easyui-datetimebox" class="easyui-validatebox span2" data-options="prompt:'-请选择-',required:true" name="zgtime" style='width: 150px'/>
		    </td>
		  </tr>
		  <tr id="zhengGaiShow_tr_zgsm">
		    <td  class="form-td-label" valign="top" align="right" >整改说明：</td>
		    <td class="form-td-content" colspan="5">
		      <input class="easyui-textbox" class="easyui-validatebox span2"  name='zgjg' data-options="prompt:'请填写整改说明',multiline:true" data-options="multiline:true,validType:'length[1,400]'"  style='width: 100%;height:50px'>
		    </td>
		  </tr>
		  <tr>
		    <td  class="form-td-label" >上报状态：</td>
		    <td class="form-td-content">
		      <input class="easyui-textbox" class="easyui-validatebox span2" name="sbzt" style='width: 150px' readonly="readonly"/>
		    </td>
		    <td  class="form-td-label" >审核状态：</td>
		    <td class="form-td-content">
		      <input class="easyui-textbox" class="easyui-validatebox span2" name="shzt" style='width: 150px' readonly="readonly"/>
		    </td>
		    <td  class="form-td-label" >整改状态：</td>
		    <td class="form-td-content">
		      <input class="easyui-textbox" class="easyui-validatebox span2" value="未整改" style='width: 150px' readonly="readonly"/>
		    </td>
		  </tr>
		  <tr>
		    <td  class="form-td-label" >维护时间：</td>
		    <td class="form-td-content" >
		      <input class="easyui-textbox" class="easyui-validatebox span2"  name='whtime'  style='width: 150px' readonly="readonly">
		    </td>
		    <td  class="form-td-label" >巡检部门：</td>
		    <td class="form-td-content">
		      <input class="easyui-textbox" class="easyui-validatebox span2"  name='xjbmName' style='width: 150px' readonly="readonly">
		    </td>
		    <td  class="form-td-label">巡检人员：</td>
		    <td class="form-td-content" >
		      <input class="easyui-textbox" class="easyui-validatebox span2"  name='xjryName' style='width: 150px' readonly="readonly">
		    </td>
		  </tr>
		  <tr>
		    <td  class="form-td-label" >上报时间：</td>
		    <td class="form-td-content" >
		      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sbtime'  style='width: 150px' readonly="readonly">
		    </td>
		    <td  class="form-td-label" >上报部门：</td>
		    <td class="form-td-content">
		      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sbbmName' style='width: 150px' readonly="readonly">
		    </td>
		    <td  class="form-td-label">上报人员：</td>
		    <td class="form-td-content">
		      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sbryName' style='width: 150px' readonly="readonly">
		    </td>
		  </tr>
		  <tr>
		    <td  class="form-td-label" >审核时间：</td>
		    <td class="form-td-content" >
		      <input class="easyui-textbox" class="easyui-validatebox span2"  name='shtime'  style='width: 150px' readonly="readonly">
		    </td>
		    <td  class="form-td-label" >审核部门：</td>
		    <td class="form-td-content">
		      <input class="easyui-textbox" class="easyui-validatebox span2"  name='shbmName' style='width: 150px' readonly="readonly">
		    </td>
		    <td  class="form-td-label">审核人员：</td>
		    <td class="form-td-content">
		      <input class="easyui-textbox" class="easyui-validatebox span2"  name='shryName' style='width: 150px' readonly="readonly">
		    </td>
		  </tr>
		  <tr>
		    <td  class="form-td-label" valign="top" align="right" >审核意见：</td>
		    <td class="form-td-content" colspan="5">
		      <input class="easyui-textbox" class="easyui-validatebox span2"  name='shsm'  data-options="multiline:true,validType:'length[1,40]'"  style='width: 100%;height:50px' readonly="readonly">
		    </td>
		  </tr>
		</table>
</div>
<div id="allShow_div_gczlYdxjGPZLXJInfo" >
		<table class="grid" style="width: 780px">
		  <tr>
		    <td  class="form-td-label" width="109px">上报状态：</td>
		    <td class="form-td-content">
		      <input class="easyui-textbox" class="easyui-validatebox span2" name="sbzt" style='width: 150px' readonly="readonly"/>
		    </td>
		    <td  class="form-td-label" >审核状态：</td>
		    <td class="form-td-content">
		      <input class="easyui-textbox" class="easyui-validatebox span2" name="shzt" style='width: 150px' readonly="readonly"/>
		    </td>
		    <td  class="form-td-label" >整改状态：</td>
		    <td class="form-td-content">
		      <input class="easyui-textbox" class="easyui-validatebox span2" name="zgzt" style='width: 150px' readonly="readonly"/>
		    </td>
		  </tr>
		  <tr>
		    <td  class="form-td-label" >维护时间：</td>
		    <td class="form-td-content" >
		      <input class="easyui-textbox" class="easyui-validatebox span2"  name='whtime'  style='width: 150px' readonly="readonly">
		    </td>
		    <td  class="form-td-label" >巡检部门：</td>
		    <td class="form-td-content">
		      <input class="easyui-textbox" class="easyui-validatebox span2"  name='xjbmName' style='width: 150px' readonly="readonly">
		    </td>
		    <td  class="form-td-label">巡检人员：</td>
		    <td class="form-td-content" >
		      <input class="easyui-textbox" class="easyui-validatebox span2"  name='xjryName' style='width: 150px' readonly="readonly">
		    </td>
		  </tr>
		  <tr>
		    <td  class="form-td-label" >上报时间：</td>
		    <td class="form-td-content" >
		      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sbtime'  style='width: 150px' readonly="readonly">
		    </td>
		    <td  class="form-td-label" >上报部门：</td>
		    <td class="form-td-content">
		      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sbbmName' style='width: 150px' readonly="readonly">
		    </td>
		    <td  class="form-td-label">上报人员：</td>
		    <td class="form-td-content">
		      <input class="easyui-textbox" class="easyui-validatebox span2"  name='sbryName' style='width: 150px' readonly="readonly">
		    </td>
		  </tr>
		  <tr>
		    <td  class="form-td-label" >审核时间：</td>
		    <td class="form-td-content" >
		      <input class="easyui-textbox" class="easyui-validatebox span2"  name='shtime'  style='width: 150px' readonly="readonly">
		    </td>
		    <td  class="form-td-label" >审核部门：</td>
		    <td class="form-td-content">
		      <input class="easyui-textbox" class="easyui-validatebox span2"  name='shbmName' style='width: 150px' readonly="readonly">
		    </td>
		    <td  class="form-td-label">审核人员：</td>
		    <td class="form-td-content">
		      <input class="easyui-textbox" class="easyui-validatebox span2"  name='shryName' style='width: 150px' readonly="readonly">
		    </td>
		  </tr>
		  <tr>
		    <td  class="form-td-label" valign="top" align="right" >审核意见：</td>
		    <td class="form-td-content" colspan="5">
		      <input class="easyui-textbox" class="easyui-validatebox span2"  name='shsm'  data-options="multiline:true,validType:'length[1,40]'"  style='width: 100%;height:50px' readonly="readonly">
		    </td>
		  </tr>
		  
		  <tr>
		    <td  class="form-td-label" >整改时间：</td>
		    <td class="form-td-content">
		      <input class="easyui-datetimebox" class="easyui-validatebox span2" name="zgtime" style='width: 150px' readonly="readonly"/>
		    </td>
		    <td  class="form-td-label" >整改反馈部门：</td>
		    <td class="form-td-content">
		      <input class="easyui-datetimebox" class="easyui-validatebox span2" name="zgbmName" style='width: 150px' readonly="readonly"/>
		    </td>
		    <td  class="form-td-label" >整改反馈人员：</td>
		    <td class="form-td-content">
		      <input class="easyui-datetimebox" class="easyui-validatebox span2" name="zgfkryName" style='width: 150px' readonly="readonly"/>
		    </td>
		   </tr>
		   <tr>
		    <td  class="form-td-label" >整改反馈时间：</td>
		    <td class="form-td-content">
		      <input class="easyui-datetimebox" class="easyui-validatebox span2" name="zgfktime" style='width: 150px' readonly="readonly"/>
		    </td>
		    <td  class="form-td-label" >指定整改人员：</td>
		    <td class="form-td-content">
		      <input class="easyui-datetimebox" class="easyui-validatebox span2" name="zgry" style='width: 150px' readonly="readonly"/>
		    </td>
		    
		  </tr>
		  <tr>
		    <td  class="form-td-label" valign="top" align="right" >整改说明：</td>
		    <td class="form-td-content" colspan="5">
		      <input class="easyui-textbox" class="easyui-validatebox span2"  name='zgjg'  data-options="multiline:true,validType:'length[1,40]'"  style='width: 100%;height:50px' readonly="readonly">
		    </td>
		  </tr>
		</table>
</div>