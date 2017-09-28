<%@page import="com.crfeb.tbmpt.commons.utils.WebUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <title>管片质量巡检详情</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="stylesheet" href="${staticPath}/static/open/css/weui.min.css"/>
    <link rel="stylesheet" href="${staticPath}/static/open/css/main.css"/>
    <%
    	String type = WebUtil.checkDeviceType(request);
	%>
</head>
<body ontouchstart>
	<div class="weui-toptips weui-toptips_warn js_tooltips">错误提示</div>
	<div class="weui-gallery" id="gallery"
		style="opacity: 0; display: none;">
		<span class="weui-gallery__img" id="galleryImg"
			style="background-image: url(./images/pic_160.png)"></span>
		<div class="weui-gallery__opr">
			<a href="javascript:" class="weui-gallery__del"> <i
				class="weui-icon-delete weui-icon_gallery-delete"></i>
			</a>
		</div>
	</div>
	<div class="container" id="container">
		<div class="page preview js_show">

			<div class="page__bd">
				<div class="weui-form-preview">
					<div class="weui-form-preview__hd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">管片巡检纪录</label>
						</div>
					</div>
					<div class="weui-form-preview__bd">
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">巡检时间</label> <span
								class="weui-form-preview__value">${info.xjtime}</span>
						</div>
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">上报状态</label> <span
								class="weui-form-preview__value">${info.sbzt}</span>
						</div>
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">审核状态</label> <span
								class="weui-form-preview__value">${info.shzt}</span>
						</div>
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">整改状态</label> <span
								class="weui-form-preview__value">${info.zgzt}</span>
						</div>
						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">巡检人</label> <span
								class="weui-form-preview__value">${info.xjryName}</span>
						</div>

						<div class="weui-form-preview__item">
							<label class="weui-form-preview__label">巡检内容</label> <span
								class="weui-form-preview__value">${info.qkms}</span>
						</div>
						
						<c:choose>
							<c:when test="${info.sbzt == '已上报'  && info.shzt == '已审核'}">
							
								<div class="weui-form-preview__item">
									<label class="weui-form-preview__label">整改截止时间</label> <span
										class="weui-form-preview__value">${info.zgjzTime}</span>
								</div>
							
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>
						

						<c:if test="${!empty zgq_fjs}">
							<div class="weui-form-preview__item">
								<label class="weui-form-preview__label">整改前图片</label> 
							</div>
							
							<div class="weui-form-preview__item">
								<span class="weui-form-preview__value" id="imgzgq"> 
									
									<c:forEach items="${zgq_fjs}" var="fj">
										<img 
										name="img"
										src="${imageBrowsePath}/${fj.minImgPath}"
										style="width: 80px; height: 80px;" alt="${imageBrowsePath}/${fj.filePath}">
									</c:forEach>
									
								</span>
							</div>
						</c:if>
						<c:if test="${!empty zgh_fjs}">
							<div class="weui-form-preview__item">
								<label class="weui-form-preview__label">整改后图片</label> 
							</div>
							<div class="weui-form-preview__item">
								<span class="weui-form-preview__value" id="imgzgh"> 
									
									<c:forEach items="${zgh_fjs}" var="fj">
										<img 
										name="img"
										src="${imageBrowsePath}/${fj.minImgPath}"
										style="width: 80px; height: 80px;" alt="${imageBrowsePath}/${fj.filePath}">
									</c:forEach>
									
								</span>
							</div>
						</c:if>
					</div>
				</div>

					<c:choose>
						<c:when test="${info.sbzt == '未上报' }">
						
							<shiro:hasPermission name="/gczl/base/gczlYdxjGPZLXJInfo/sbInfo">
							<!-- 提交表单 -->
                			<form id="form_sb" method="post" 
                			action="${staticPath}/open/gczl/gczlGPZLXJSB">
                
                			<input name="id" type="hidden" value="${info.id}"/>
                			<input name="createUser" type="hidden" value="${info.createUser}"/>
                			<input name="createUsername" type="hidden" value="${info.createUsername}"/>
                			<input name="createOrgz" type="hidden" value="${info.createOrgz}"/>
                			<input name="createOrgzname" type="hidden" value="${info.createOrgzname}"/>
                			<input name="createTime" type="hidden" value="${info.createTime}"/>
                			<input name="updateUser" type="hidden" value="${info.updateUser}"/>
                			<input name="updateUsername" type="hidden" value="${info.updateUsername}"/>
                			<input name="updateOrgz" type="hidden" value="${info.updateOrgz}"/>
                			<input name="updateOrgzname" type="hidden" value="${info.updateOrgzname}"/>
                			<input name="updateTime" type="hidden" value="${info.updateTime}"/>
							<div class="weui-cells__title">上报操作</div>
							
							<div class="weui-cells weui-cells_form">
							
								<div class="weui-cell">
		                            <div class="weui-cell__hd"><label for="" class="weui-label" style="width: 8em;">整改人员：</label></div>
		                            <div class="weui-cell__bd">
		                                <input name="zgry" class="weui-input" type="text" placeholder="请输入整改人员" required/>
		                            </div>
		                        </div>
		                        
		                        <div class="weui-cell">
		                            <div class="weui-cell__hd"><label for="" class="weui-label" style="width: 8em;">整改截止时间：</label></div>
		                            <div class="weui-cell__bd">
		                                <input name="zgjzTime" class="weui-input" type="datetime-local" placeholder="请输入整改截止时间" required/>
		                            </div>
		                        </div>
								
							</div>
							
							<div class="weui-btn-area">
								<a class="weui-btn weui-btn_primary" href="javascript:"
									id="formSubmitBtn_sb">上报</a>
							</div>
							
							</form>
							</shiro:hasPermission>
						</c:when>
						<c:when test="${info.shzt == '未审核' }">
						
							<shiro:hasPermission name="/gczl/base/gczlYdxjGPZLXJInfo/shInfo">
							<!-- 提交表单 -->
                			<form id="form_sh" method="post" 
                			action="${staticPath}/open/gczl/gczlGPZLXJSH">
                
                			<input name="id" type="hidden" value="${info.id}"/>
                			<input name="createUser" type="hidden" value="${info.createUser}"/>
                			<input name="createUsername" type="hidden" value="${info.createUsername}"/>
                			<input name="createOrgz" type="hidden" value="${info.createOrgz}"/>
                			<input name="createOrgzname" type="hidden" value="${info.createOrgzname}"/>
                			<input name="createTime" type="hidden" value="${info.createTime}"/>
                			<input name="updateUser" type="hidden" value="${info.updateUser}"/>
                			<input name="updateUsername" type="hidden" value="${info.updateUsername}"/>
                			<input name="updateOrgz" type="hidden" value="${info.updateOrgz}"/>
                			<input name="updateOrgzname" type="hidden" value="${info.updateOrgzname}"/>
                			<input name="updateTime" type="hidden" value="${info.updateTime}"/>
                			
							<div class="weui-cells__title">审核操作</div>
							<div class="weui-cells weui-cells_form">
								<div class="weui-cell weui-cell_select weui-cell_select-after">
									<div class="weui-cell__hd">
										<label for="" class="weui-label" style="width: 8em;">审核状态：</label>
									</div>
									<div class="weui-cell__bd">
										<select class="weui-select" name="shzt"
											onChange="javascript:onChange('mune_x'+this.value)">
											<option value="已审核">已审核</option>
											<option value="已打回">已打回</option>
										</select>
									</div>
								</div>
								<div class="weui-cell">
		                            <div class="weui-cell__hd"><label for="" class="weui-label" style="width: 8em;">整改人员：</label></div>
		                            <div class="weui-cell__bd">
		                                <input name="zgry" class="weui-input" type="text" value="${info.zgry}" placeholder="请输入整改人员" required/>
		                            </div>
		                        </div>
		                         <div class="weui-cell">
		                            <div class="weui-cell__hd"><label for="" class="weui-label" style="width: 8em;">整改截止时间：</label></div>
		                            <div class="weui-cell__bd">
		                                <input name="zgjzTime" class="weui-input" type="datetime-local" value="${info.zgjzTime}" placeholder="请输入整改截止时间" required/>
		                            </div>
		                        </div>
		                        
								<div class="weui-cell">
									<div class="weui-cell__bd">
										<textarea name="shsm" class="weui-textarea" placeholder="请输入审核意见" rows="3"></textarea>
										<div class="weui-textarea-counter">
											<span>0</span>/200
										</div>
									</div>
								</div>
							</div>
							<div class="weui-btn-area">
								<a class="weui-btn weui-btn_primary" href="javascript:"
									id="formSubmitBtn_sh">确定</a>
							</div>
							</form>
							</shiro:hasPermission>
						</c:when>
						<c:when test="${info.zgzt == '未整改' }">
						
							<shiro:hasPermission name="/gczl/base/gczlYdxjGPZLXJInfo/zgInfo">
							<!-- 提交表单 -->
                			<form id="form_zg" method="post" 
                			action="${staticPath}/open/gczl/gczlGPZLXJZG"
                			enctype="multipart/form-data">
                
                			<input name="id" type="hidden" value="${info.id}"/>
                			<input name="createUser" type="hidden" value="${info.createUser}"/>
                			<input name="createUsername" type="hidden" value="${info.createUsername}"/>
                			<input name="createOrgz" type="hidden" value="${info.createOrgz}"/>
                			<input name="createOrgzname" type="hidden" value="${info.createOrgzname}"/>
                			<input name="createTime" type="hidden" value="${info.createTime}"/>
                			<input name="updateUser" type="hidden" value="${info.updateUser}"/>
                			<input name="updateUsername" type="hidden" value="${info.updateUsername}"/>
                			<input name="updateOrgz" type="hidden" value="${info.updateOrgz}"/>
                			<input name="updateOrgzname" type="hidden" value="${info.updateOrgzname}"/>
                			<input name="updateTime" type="hidden" value="${info.updateTime}"/>
                			
							<div class="weui-cells__title">整改反馈</div>
							<div class="weui-cells weui-cells_form">
								<div class="weui-cell weui-cell_select weui-cell_select-after">
									<div class="weui-cell__hd">
										<label for="" class="weui-label" style="width: 8em;">整改状态：</label>
									</div>
									<div class="weui-cell__bd">
										<select class="weui-select" name="zgzt"
											onChange="javascript:onChange('mune_x'+this.value)">
											<option value="已整改">已整改</option>
											<option value="未整改">未整改</option>
										</select>
									</div>
								</div>
		                        <div class="weui-cell">
		                            <div class="weui-cell__hd"><label for="" class="weui-label" style="width: 8em;">整改时间：</label></div>
		                            <div class="weui-cell__bd">
		                                <input name="zgtime" class="weui-input" type="datetime-local" placeholder="请输入整改时间" required/>
		                            </div>
		                        </div>
								<div class="weui-cell">
									<div class="weui-cell__bd">
										<textarea name="zgjg" class="weui-textarea" placeholder="请输入整改反馈内容"
											rows="3"></textarea>
										<div class="weui-textarea-counter">
											<span>0</span>/200
										</div>
									</div>
								</div>
							</div>
							<div class="weui-cells__title">相关图片</div>
		                    <div class="weui-cells weui-cells_form">
		                        <div class="weui-cell">
		                            <div class="weui-cell__bd">
		                                <div class="weui-uploader">
		                                    <div class="weui-uploader__hd">
		                                        <p class="weui-uploader__title">整改后图片</p>
		                                        <div class="weui-uploader__info">0/1</div>
		                                    </div>
		                                    <div class="weui-uploader__bd">
		                                        <ul class="weui-uploader__files" id="uploaderFiles">
		                                            
		                                        </ul>
		                                        <div class="weui-uploader__input-box">
		                                            <input id="uploaderInput" class="weui-uploader__input" name="file" type="file" accept="image/*" multiple />
		                                        </div>
		                                    </div>
		                                </div>
		                            </div>
		                        </div>
		
		                    </div>
							<div class="weui-btn-area">
								<a class="weui-btn weui-btn_primary" href="javascript:"
									id="formSubmitBtn_zg">确定</a>
							</div>
							</form>
							</shiro:hasPermission>
						</c:when>
						<c:otherwise>
						</c:otherwise>
					</c:choose>
				
			</div>

			<div class="page__ft">
				<a href="javascript:;"></a>
			</div>
		</div>
	</div>

	<div id="dialogs">
		<!--BEGIN dialogTip-->
		<div class="js_dialog" id="dialogTip"
			style="opacity: 0; display: none;">
			<div class="weui-mask"></div>
			<div class="weui-dialog">
				<div id="dialogTip_content" class="weui-dialog__bd"></div>
				<div class="weui-dialog__ft">
					<a href="javascript:;"
						class="weui-dialog__btn weui-dialog__btn_primary">确定</a>
				</div>
			</div>
		</div>
		<!--END dialogTip-->
		<!--BEGIN dialogSuccess-->
		<div class="js_dialog" id="dialogSuccess"
			style="opacity: 0; display: none;">
			<div class="weui-mask"></div>
			<div class="weui-dialog weui-skin_android">
				<div class="weui-dialog__hd">
					<strong class="weui-dialog__title">操作成功</strong>
				</div>
				<div id="dialogSuccess_content" class="weui-dialog__bd"></div>
				<div class="weui-dialog__ft">
					<a href="javascript:closeCurrentWindow();"
						class="weui-dialog__btn weui-dialog__btn_default">返回</a> <a
						href="javascript:;"
						class="weui-dialog__btn weui-dialog__btn_primary">取消</a>
				</div>
			</div>
		</div>
		<!--END dialogSuccess-->
	</div>

	<script src="${staticPath}/static/open/js/zepto.min.js"></script>
    <script src="${staticPath}/static/open/js/selector.js"></script>
    <script src="${staticPath}/static/open/js/jquery.chained.js"></script>
    <script src="${staticPath}/static/open/js/weui.min.js"></script>
    <script src="${staticPath}/static/open/js/ext.js"></script>
	<script type="text/javascript">
		$(function() {
			var tmpl = '<li class="weui-uploader__file" style="background-image:url(#url#)"></li>', 
			$gallery = $("#gallery"), 
			$galleryImg = $("#galleryImg"), 
			$uploaderInput = $("#uploaderInput"), 
			$uploaderFiles = $("#uploaderFiles"),
			$imgzgq = $("#imgzgq");
			$imgzgh = $("#imgzgh");

			$uploaderInput.on("change",
					function(e) {
						var src, url = window.URL || window.webkitURL
								|| window.mozURL, files = e.target.files;
						for (var i = 0, len = files.length; i < len; ++i) {
							var file = files[i];

							if (url) {
								src = url.createObjectURL(file);
							} else {
								src = e.target.result;
							}

							$uploaderFiles
									.append($(tmpl.replace('#url#', src)));
						}
					});
			$uploaderFiles.on("click", "li", function() {
				$galleryImg.attr("style", this.getAttribute("style"));
				$gallery.fadeIn(100);
			});
			$imgzgq.on("click","img",function() {
				$galleryImg.attr("style", "background-image: url('"+this.alt+"')");
				$gallery.fadeIn(100);
			});
			$imgzgh.on("click","img",function() {
				$galleryImg.attr("style", "background-image: url('"+this.alt+"')");
				$gallery.fadeIn(100);
			});
			$gallery.on("click", function() {
				$gallery.fadeOut(100);
			});

			var $tooltips = $('.js_tooltips');

			$('#showTooltips').on('click', function() {
				if ($tooltips.css('display') != 'none')
					return;

				// toptips的fixed, 如果有`animation`, `position: fixed`不生效
				$('.page.cell').removeClass('slideIn');

				$tooltips.css('display', 'block');
				setTimeout(function() {
					$tooltips.css('display', 'none');
				}, 2000);
			});

			var $dialogTip = $('#dialogTip');
			var $dialogSuccess = $('#dialogSuccess');

			$('#dialogs').on('click', '.weui-dialog__btn', function() {
				$(this).parents('.js_dialog').fadeOut(200);
			});

			<c:choose>
			<c:when test="${!empty data}">
			<c:choose>
			<c:when test="${data.success}">
			$('#dialogSuccess_content').empty();
			$('#dialogSuccess_content').append('${data.msg}');
			$dialogSuccess.fadeIn(200);
			</c:when>
			<c:otherwise>
			$('#dialogTip_content').empty();
			$('#dialogTip_content').append('${data.msg}');
			$dialogTip.fadeIn(200);
			</c:otherwise>
			</c:choose>
			</c:when>
			<c:otherwise>
			</c:otherwise>
			</c:choose>

			var success = '${data.success}';

			var $form_sb = $("#form_sb");
			var $form_sh = $("#form_sh");
			var $form_zg = $("#form_zg");

			// 表单校验
			$("#formSubmitBtn_sb").on("click", function() {
				$form_sb.validate(function(error) {
					if (error) {
						$('#dialogTip_content').empty();
						$('#dialogTip_content').append("请完善输入框");
						$dialogTip.fadeIn(200);
					} else {
						$form_sb.submit();
					}
				});

			});
			$("#formSubmitBtn_sh").on("click", function() {
				$form_sh.validate(function(error) {
					if (error) {
						$('#dialogTip_content').empty();
						$('#dialogTip_content').append("请完善输入框");
						$dialogTip.fadeIn(200);
					} else {
						$form_sh.submit();
					}
				});

			});
			$("#formSubmitBtn_zg").on("click", function() {
				$form_zg.validate(function(error) {
					if (error) {
						$('#dialogTip_content').empty();
						$('#dialogTip_content').append("请完善输入框");
						$dialogTip.fadeIn(200);
					} else {
						$form_zg.submit();
					}
				});

			});

		});

		var closeCurrentWindow = function() {
			<%=type%>.closeCurrentWindow();
		}

		$("#line").chained("#section");
	</script>
</body>
</html>

