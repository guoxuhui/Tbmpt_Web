/**
 * 上传附件处理
 */
var _uploadUrl;//各业务上传图片controller 调用url
var _uploadDataGrid;
/**
 * 上传图片
 * @param keyId 业务记录主键
 * @param syspath 上传附件在文件服务器上的对应文件夹 一般可与业务模块的syspath相同即可
 * @param beizhu 备用字段 区分同条业务记录下的不同业务附件 可为空
 * @param dataGrid 业务模块grid列表对象
 * @param  menueName 业务菜单名称
 * @returns
 */
function _uploadPicture(keyId,syspath,beizhu,dataGrid,menueName){
	_uploadUrl = basePath+'/sys/base/sysFujian/uploadPic.action?id='+keyId+'&syspath='+syspath+'&beizhu='+encodeURI(beizhu)+"&menueName="+encodeURI(menueName);
	clearFileTable();
	addShowFile(keyId,beizhu,'');
	FormUtil.clearForm($("#upLoadForm_gczlYdxjSGZLXJInfo"));
	$('#keyId').val(keyId);
    $("#upLoadDialog_gczlYdxjSGZLXJInfo").dialog('open').dialog('setTitle', '上传附件');
    _uploadDataGrid = dataGrid;
}
/**
 * 上传附件
 * @param keyId 业务记录主键
 * @param syspath 上传附件在文件服务器上的对应文件夹 一般可与业务模块的syspath相同即可
 * @param beizhu 备用字段 区分同条业务记录下的不同业务附件 可为空
 * @param dataGrid 业务模块grid列表对象
 * @param  menueName 业务菜单名称
 * @returns
 */
function _uploadFile(keyId,syspath,beizhu,dataGrid,menueName){
	_uploadUrl = basePath+'/sys/base/sysFujian/uploadFile.action?id='+keyId+'&syspath='+syspath+'&beizhu='+encodeURI(beizhu)+"&menueName="+encodeURI(menueName);
	clearFileTable();
	addShowFile(keyId,beizhu,'');
	FormUtil.clearForm($("#upLoadForm_gczlYdxjSGZLXJInfo"));
	$('#keyId').val(keyId);
    $("#upLoadDialog_gczlYdxjSGZLXJInfo").dialog('open').dialog('setTitle', '上传附件');
    _uploadDataGrid = dataGrid;
}
/**
 * 方法说明：上传附件页面初始化时动态清空除第一行外的其他行数据
 * @returns
 * @author:YangYj
 * @Time: 2017年2月17日 下午5:14:58
 */
function clearFileTable(){
	  var trList = $("#xjZpTable").find("tr");
	  for (var i=1;i<trList.length;i++) {
			  trList.eq(i).remove();
	  }
	  var trList = $("#showTable").find("tr");
	  for (var i=1;i<trList.length;i++) {
			  trList.eq(i).remove();
	  }
}
/**提交上传现场图片操作 */
function upLoadPictureActionAjax() {
	    var msg = validateFileInput();//提交前校验
	    if(null !=msg && '' != msg && undefined != msg){
	    	$.messager.alert('错误', msg, 'error');
	    	return;
	    }
		progressLoad();
		$('#upLoadForm_gczlYdxjSGZLXJInfo').form('submit', {
			url : _uploadUrl,
//			type: 'post',
			onSubmit : function() {
			},
			success : function(result) {
				progressClose();
				result = $.parseJSON(result);
				if (result.success) {
					$.messager.show({title:'提示',msg:'上传图片操作：成功!',showType:'show' });
					$("#upLoadDialog_gczlYdxjSGZLXJInfo").dialog('close');
					_uploadDataGrid.datagrid('reload');
				}
				else {
					$.messager.alert('错误', result.msg, 'error');
				}
			}
		});
}

/**
 * 方法说明：添加上传图片按钮框
 * @returns
 * @author:YangYj
 * @Time: 2017年2月17日 下午4:46:35
 */
function addUploadFileInput(){
	$('#xjZpTable tr:last').after("<tr><td class='form-td-content'><input  name='xjZp' accept='.jpg,.png,.gif,.bmp,.wbmp,.jpeg,.BMP,.JPG,.PNG,.JPEG,.WBMP,.GIF' type='file' class='easyui-filebox'  style='width:230px'></td><td><img alt='删除' style='cursor:pointer;width:15px;height:15px;' src='"+basePath+"/static/style/images/close1.png' onclick='delTr(this);'/></td></tr>");
}

/**
 * 方法说明：动态显示已上传图片
 * @returns
 * @author:YangYj
 * @Time: 2017年2月18日 上午11:09:01
 */
function addShowFile(foreignId,backupOne,backupTwo){
	var url = basePath+'/sys/base/sysFujian/findDataByKeyidAndBeiZhu?foreignId='+foreignId+'&backupOne='+encodeURI(backupOne)+'&backupTwo='+encodeURI(backupTwo);
	$.ajax({
		url: url,
		type: 'post',
		data: null,
		dataType : 'JSON',
		async:true,
		success : function(data) {
			for(var i =0;i<data.length;i++){
				$('#showTable').append("<tr><td class='form-td-content'><a href='"+imageBrowsePath+data[i].minImgPath+"' target='_blank'>"+data[i].fileName+"</a></td><td><img style='width:15px;height:15px;' alt='删除'  style='cursor:pointer' src='"+basePath+"/static/style/images/close1.png' onclick='delTrAndFile(this,\""+data[i].id+"\");'/></td></tr>");
			}
		}
	});
//		
}

/**
 * 方法说明：附件上传页面中动态删除当前行数据操作
 * @param obj
 * @returns
 * @author:YangYj
 * @Time: 2017年2月17日 下午5:12:04
 */
function delTr(obj) {
	$(obj).parent().parent().remove();
//	window.parent.height($(document.body).outerHeight(true));
}

/**
 * 方法说明：附件上传页面中动态删除当前行数据操作同时删除数据库记录及文件记录
 * @param obj
 * @returns
 * @author:YangYj
 * @Time: 2017年2月17日 下午5:12:04
 */
function delTrAndFile(obj,id) {
	var url = basePath+'/sys/base/sysFujian/deleteFile?id='+id;
	$.ajax({
		url: url,
		type: 'get',
		data: null,
		dataType : 'JSON',
		async:true,
		success : function(data) {
			$(obj).parent().parent().remove();
//			window.parent.height($(document.body).outerHeight(true));
			$.messager.alert('信息', data.msg, 'info');
		}
	});
}
var _dataArray = new Array();
/**
 * 方法说明：上传附件前校验，不可为空，文件不可重复上传
 * @returns 若验证通过返回空字符串，否则返回错误信息
 * @author:YangYj
 * @Time: 2017年2月17日 下午4:50:47
 */
function validateFileInput(){
	var msg = "";
	 _dataArray = new Array();
	//将待上传的附件放入校验数组中
	$("#showTable").find("tr").each(function(){
			var tdArr = $(this).children();
			var inputVal = tdArr.eq(0).find("a").text();
			if(null != inputVal && inputVal != '' && undefined != inputVal  ){
				_dataArray.push(inputVal);
			}
	  });
	//将待上传的附件放入校验数组中
	$("#xjZpTable").find("tr").each(function(){
			var tdArr = $(this).children();
			var inputVal = tdArr.eq(0).find("input").val();
			if(null == inputVal || inputVal == ''  ){
				msg = "上传文件不可为空,请确认。";
				return msg;
			}
			if(inputVal.indexOf("\\")>0){
				inputVal = inputVal.substr(inputVal.lastIndexOf("\\")+1)
			}
			if(_dataArray.length>0){
				for(var i = 0;i<_dataArray.length;i++){
					if(_dataArray[i]==inputVal){
						msg ="不可重复上传文件【"+inputVal+"】,请确认。";
						return msg;
					}
				}
			}
			_dataArray.push(inputVal);
	  });
	return msg;
}
/**
 * 方法说明：查看对应的业务记录所上传的所有图片
 * @param foreignId 业务主键id
 * @param showFileTrId 显示图片位置div的id
 * @returns
 * @author:YangYj
 * @Time: 2017年2月21日 下午5:24:36
 */
function _showAllFileInDiv(foreignId,showFileDivId){
	//先清空之前所有的元素
	  var imgList = $("#"+showFileDivId).children();
	  for (var i=0;i<imgList.length;i++) {
		  imgList.eq(i).remove();
	  }
	var url = basePath+'/sys/base/sysFujian/findDataByKeyidAndBeiZhu?foreignId='+foreignId;
	$.ajax({
		url: url,
		type: 'get',
		data: null,
		dataType : 'JSON',
		async:true,
		success : function(data) {
			if(data.length>0){
				var html="<span><b>相关附件</span></b></br>";
				for(var i =0;i<data.length;i++){
					html = html+"<a class='tooltip1'  title='"+data[i].fileName+"' href='"+imageBrowsePath+data[i].minImgPath+"' target='_blank'> <img   style='cursor:pointer;width:50px;height:50px;' src='"+imageBrowsePath+data[i].minImgPath+"' onclick='openOriginalImg(this,\""+imageBrowsePath+data[i].filePath+"\");'/></a>";
				}
				$('#'+showFileDivId).append(html);
				appendTooltip();
			}
		}
	});
}

/**
 * 方法说明：查看对应的业务记录所上传的z所有图片纵向显示
 * @param foreignId 业务主键id
 * @param showFileTrId 显示图片位置div的id
 * @returns
 * @author:YangYj
 * @Time: 2017年2月21日 下午5:24:36
 */
function _showAllFileInDiv2(foreignId,showFileDivId){
	//先清空之前所有的元素
	var imgList = $("#"+showFileDivId).children();
	for (var i=0;i<imgList.length;i++) {
		imgList.eq(i).remove();
	}
	var url = basePath+'/sys/base/sysFujian/findDataByKeyidAndBeiZhu?foreignId='+foreignId;
	$.ajax({
		url: url,
		type: 'get',
		data: null,
		dataType : 'JSON',
		async:true,
		success : function(data) {
			if(data.length>0){
				var html="<span><b>相关附件</span></b></br>";
				for(var i =0;i<data.length;i++){
					html = html+"<a class='tooltip1'  title='"+data[i].fileName+"' href='"+imageBrowsePath+data[i].minImgPath+"' target='_blank'> <img   style='cursor:pointer;width:50px;height:50px;' src='"+imageBrowsePath+data[i].minImgPath+"' onclick='openOriginalImg(this,\""+imageBrowsePath+data[i].filePath+"\");'/></a></br>";
				}
				$('#'+showFileDivId).append(html);
				appendTooltip();
			}
		}
	});
}

var x = 10;  
var y = 20;  
var screenWidth = $(document.body).width();//浏览器时下窗口文档body的宽度  
var imgWigth=0;//图片宽度  
var imgHeight=0;//图片高度  
/**
 * 方法说明：鼠标移动到图片上后动态显示放大图片
 * @returns
 * @author:YangYj
 * @Time: 2017年2月22日 下午7:44:27
 */
function appendTooltip(){
    $("a.tooltip1").mouseover(function(e){  
        var tooltip = "<div id='tooltip1'><img src='"+ this.href +"' style='max-width:500px; max-height:500px;'/><\/div>"; //创建 div 元素  
        $("body").append(tooltip);  
         imgWidth = $("#tooltip1").width();  
         imgHeight = $("#tooltip1").height();  
        //把它追加到文档中                         
        $("#tooltip1")  
            .css({  
                "top": (e.pageY-imgHeight-y) + "px",  
                "left": ((screenWidth-imgWidth)/2) + "px"  
            }).show("fast");      //设置x坐标和y坐标，并且显示  
    }).mouseout(function(){  
        $("#tooltip1").remove();  //移除   
    }).mousemove(function(e){  
        $("#tooltip1")  
            .css({  
                "top": (e.pageY-imgHeight-y) + "px",  
                "left": (e.pageX)+"px"
            });  
    });  
}


/**
 * 方法说明：打开原始图片
 * @param url  图片路径
 * @returns
 * @author:YangYj
 * @Time: 2017年2月18日 下午5:40:11
 */
function openOriginalImg(obj,url){
	window.open(url);   
}


/**
 * 查看图片 此方法为工程质量巡检模块中附件有整改前和整改后记录调用，其他模块调用无效
 * @param foreignId 业务记录主键
 * @returns
 */
function _showPic(foreignId){
	$("#showPicDialog_gczlYdxjSGZLXJInfo").dialog('open').dialog('setTitle', '查看图片');
	//先清空之前所有的元素
	  var trList = $("#showFileTable").find("tr");
	  for (var i=0;i<trList.length;i++) {
			  trList.eq(i).remove();
	  }
	  var trList = $("#showZghFileTable").find("tr");
	  for (var i=0;i<trList.length;i++) {
			  trList.eq(i).remove();
	  }
	//整改前图片
	var url = basePath+'/sys/base/sysFujian/findDataByKeyidAndBeiZhu?foreignId='+foreignId+'&backupOne='+encodeURI("整改前");
	$.ajax({
		url: url,
		type: 'get',
		data: null,
		dataType : 'JSON',
		async:true,
		success : function(data) {
			if(data.length>0){
				var html = "<tr style='background-color:#F08080'><td class='form-td-content' colspan='3'> <span ><b>整改前照片</b></span></tr><tr>";
				for(var i =0;i<data.length;i++){
					if(i >0 && i%3==0){
						html = html+"</tr><tr>";
					}
					html = html+"<td style='width:30%' class='form-td-content'> <img alt='删除'  style='cursor:pointer;width:100%;height:150px' src='"+imageBrowsePath+data[i].minImgPath+"' onclick='openOriginalImg(this,\""+imageBrowsePath+data[i].filePath+"\");'/></td>";
				}
				if(data.length==1){
					html +="<td></td><td></td>";
				}else if(data.length==2){
					html +="<td></td>";
				}
				html +="</tr>";
				$('#showFileTable').append(html);
			}
		}
	});
	//整改后图片
	var url = basePath+'/sys/base/sysFujian/findDataByKeyidAndBeiZhu?foreignId='+foreignId+'&backupOne='+encodeURI("整改后");
	$.ajax({
		url: url,
		type: 'get',
		data: null,
		dataType : 'JSON',
		async:true,
		success : function(data) {
			if(data.length>0){
				var html = "<tr style='background-color:#C1FFC1'><td class='form-td-content' colspan='3'> <span><b>整改后照片</b></span></tr><tr>";
				for(var i =0;i<data.length;i++){
					if(i >0 && i%3==0){
						html = html+"</tr><tr>";
					}
					html = html+"<td style='width:30%' class='form-td-content'> <img alt='删除'  style='cursor:pointer;width:100%;height:150px' src='"+imageBrowsePath+data[i].minImgPath+"' onclick='openOriginalImg(this,\""+imageBrowsePath+data[i].filePath+"\");'/></td>";
				}
				if(data.length==1){
					html +="<td></td><td></td>";
				}else if(data.length==2){
					html +="<td></td>";
				}
				
				html +="</tr>";
				$('#showZghFileTable').append(html);
			}
		}
	});
}


/**
 * 方法说明：查看对应的业务记录所上传的所有图片
 * @param foreignId
 * @returns
 * @author:YangYj
 * @Time: 2017年2月21日 下午5:24:36
 */
function _showAllPic(foreignId){
	$("#showPicDialog_gczlYdxjSGZLXJInfo").dialog('open').dialog('setTitle', '查看图片');
	//先清空之前所有的元素
	  var trList = $("#showFileTable").find("tr");
	  for (var i=0;i<trList.length;i++) {
			  trList.eq(i).remove();
	  }
	var url = basePath+'/sys/base/sysFujian/findDataByKeyidAndBeiZhu?foreignId='+foreignId;
	$.ajax({
		url: url,
		type: 'get',
		data: null,
		dataType : 'JSON',
		async:true,
		success : function(data) {
			if(data.length>0){
				var html = "<tr>";
				var nameTr = "<tr>";
				for(var i =0;i<data.length;i++){
					if(i >0 && i%3==0){
						html = html+"</tr><tr>";
					}
//					nameTr += "<td><span>"+data[i].fileName+"</span></td>";
					html = html+"<td style='width:30%' class='form-td-content'> <img alt='删除'    style='cursor:pointer;width:100%;height:150px' src='"+imageBrowsePath+data[i].minImgPath+"' onclick='openOriginalImg(this,\""+imageBrowsePath+data[i].filePath+"\");'/></td>";
				}
				if(data.length==1){
					html +="<td></td><td></td>";
				}else if(data.length==2){
					html +="<td></td>";
				}
				html +="</tr>";
				$('#showFileTable').append(html);
			}
		}
	});
	
}

