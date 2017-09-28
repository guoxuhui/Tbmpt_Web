<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
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
<script type="text/javascript" src="${staticPath}/static/js/sys/base/uploadPicture.js?v=20170228" charset="utf-8"></script>
</head>
<!--上传图片弹出框 -->
	<div id="upLoadDialog_gczlYdxjSGZLXJInfo" class="easyui-dialog"  style="display:none" data-options="iconCls:'icon-save',buttons:'#upload-dialog-buttons',closed:true,cache: false,modal: true"
	style="width:400px;height:350px;padding:10px 10px;" >
        <form id="upLoadForm_gczlYdxjSGZLXJInfo" method="post" data-options="novalidate:true" enctype="multipart/form-data" style="width:600px;height:250px;">
           <input name="id" id="keyId" type="hidden" >
           
            <input name="updateUser" type="hidden" >
           <input name="updateUsername" type="hidden" >
           <input name="updateOrgz" type="hidden" >
           <input name="updateOrgzname" type="hidden" >
           <input name="updateTime" type="hidden" >
	            		   <table  class="grid" style="width:100%;">
	            <tbody id='showTable' >
	              <tr>
	                <td><b><span>已上传附件：</span></b></td>
	              </tr>
				</tbody>
				<tbody>
			       <tr>
	                <td><b><span>待上传附件：</span></b></td>
	              </tr>
				</tbody>
			<tbody  id='xjZpTable' >
			  <tr id='xjZpTr'>
			    <td class="form-td-content" >
			        <input  name="xjZp" accept=".jpg,.png,.gif,.bmp,.wbmp,.jpeg,.BMP,.JPG,.PNG,.JPEG,.WBMP,.GIF" type="file"  style="width:230px">
			    </td>
			    <td>
			     <img alt="增加新附件" style="cursor:pointer;width:15px;height:15px;" src="${basePath}/static/style/images/t01.png" onclick="addUploadFileInput();"/>
			    </td>
			  </tr>
			</tbody>
			</table>
        </form>
	</div>
	<div id="upload-dialog-buttons">
<!-- 	    <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addUploadFileInput()">增加附件</a> -->
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" onclick="upLoadPictureActionAjax()">开始上传</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#upLoadDialog_gczlYdxjSGZLXJInfo').dialog('close')">关闭</a>
	</div>
	
	
				
<!-- 查看现场图片弹出框-->	
<div id="showPicDialog_gczlYdxjSGZLXJInfo" class="easyui-dialog" 
data-options="iconCls:'icon-save',buttons:'#showPic-dialog-buttons',closed:true,cache: false,modal: true"	style="width:850px;height:500px;" >
           <input name="id" type="hidden" >
           <table id="showFileTable">
           </table>
            <table id="showZghFileTable">
           </table>
	</div>
	<div id="showPic-dialog-buttons">
<!-- 		<a href="" id="showOraginalPicButton" target="_blank" class="easyui-linkbutton" data-options="iconCls:'icon-search'" >查看原图</a> -->
		<a href="javascript:void(0)"  class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="javascript:$('#showPicDialog_gczlYdxjSGZLXJInfo').dialog('close')">关闭</a>
	</div>
