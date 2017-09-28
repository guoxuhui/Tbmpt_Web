<%@page import="com.crfeb.tbmpt.commons.utils.WebUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp"%>
<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <title>管片质量巡检</title>
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <link rel="stylesheet" href="${staticPath}/static/open/css/weui.min.css"/>
    <link rel="stylesheet" href="${staticPath}/static/open/css/main.css"/>
    <script type="text/javascript">
        function onChange(id) {
        	$('#x管片破损').hide();
        	$('#x管片错台').hide();
        	$('#x螺栓复紧').hide();
        	$('#x渗漏水').hide();
            $('#x'+id).show();
        }
    </script>
    
    <%
    	String type = WebUtil.checkDeviceType(request);
	%>
</head>
<body ontouchstart>
    <div class="weui-toptips weui-toptips_warn js_tooltips">错误提示</div>

    <div class="container" id="container">
        <div class="page input js_show">

            <div class="page__bd">

                <div class="weui-gallery" id="gallery">
                    <span class="weui-gallery__img" id="galleryImg"></span>
                    <div class="weui-gallery__opr">
                        <a href="javascript:" class="weui-gallery__del">
                            <i class="weui-icon-delete weui-icon_gallery-delete"></i>
                        </a>
                    </div>
                </div>

                <!-- 提交表单 -->
                <form id="form" method="post" action="${staticPath}/open/gczl/gczlGPZLXJAddDo" enctype="multipart/form-data">
                
                    <div class="weui-cells__title">所属工程</div>
                    <div class="weui-cells weui-cells_form">
                        <div class="weui-cell">
                            <div class="weui-cell__hd">
                            	<label class="weui-label" style="width: 8em;">所属工程：</label>
                            </div>
                            <div class="weui-cell__bd">
                            	${pro.name}
                            	<input name="gcBh" type="hidden" value="${pro.id}"/>
                            </div>
                        </div>
                        <div class="weui-cell weui-cell_select weui-cell_select-after">
                            <div class="weui-cell__hd">
                                <label for="" class="weui-label" style="width: 8em;">区间名称：</label>
                            </div>
                            <div class="weui-cell__bd">
                                <select id="section" name="qlBh" class="weui-select">
									<c:forEach items="${pro.proSecs}" var="sec">
										<option value="${sec.id}" >${sec.name}</option>
									</c:forEach>
									<!-- 
									<option value="1">曾钟</option>
                                    <option value="2">象钟</option>
                                     -->
                                </select>
                            </div>
                        </div>
                        <div class="weui-cell weui-cell_select weui-cell_select-after">
                            <div class="weui-cell__hd">
                                <label for="" class="weui-label" style="width: 8em;">线路名称：</label>
                            </div>
                            <div class="weui-cell__bd">
                                <select id="line" name="xlBh" class="weui-select" >
                                	
									<c:forEach items="${pro.proSecs}" var="sec">
										<c:forEach items="${sec.secLines}" var="line">
											<option value="${line.id}" class="${sec.id}">${line.name}</option>
										</c:forEach>
									</c:forEach>
									<!-- 
                                    <option value="1" class="1">曾钟左线</option>
                                    <option value="2" class="1">曾钟右线</option>
                                    <option value="3" class="2">象钟左线</option>
                                    <option value="4" class="2">象钟右线</option>
                                     -->
                                </select>
                            </div>
                        </div>
                        
                        
                        <div class="weui-cell">
                            <div class="weui-cell__hd"><label class="weui-label" style="width: 8em;">环号：</label></div>
                            <div class="weui-cell__bd">
                                <input name="hh" class="weui-input" type="text" placeholder="请输入环号" required/>
                            </div>
                        </div>
                        <div class="weui-cell weui-cell_select weui-cell_select-after">
                            <div class="weui-cell__hd"><label class="weui-label" style="width: 8em;">点位：</label></div>
                            <div class="weui-cell__bd">
                            	<select name="dw" class="weui-select" placeholder="请输入点位" required>
                                    <option value="1" >1点钟</option>
                                    <option value="2" >2点钟</option>
                                    <option value="3" >3点钟</option>
                                    <option value="4" >4点钟</option>
                                    <option value="5" >5点钟</option>
                                    <option value="6" >6点钟</option>
                                    <option value="7" >7点钟</option>
                                    <option value="8" >8点钟</option>
                                    <option value="9" >9点钟</option>
                                    <option value="10" >10点钟</option>
                                    <option value="11" >11点钟</option>
                                    <option value="12" >12点钟</option>
                                    <option value="13" >13点钟</option>
                                    <option value="14" >14点钟</option>
                                    <option value="15" >15点钟</option>
                                    <option value="16" >16点钟</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="weui-cells__title">质量情况</div>
                    <div class="weui-cells weui-cells_form">
                        <div class="weui-cell">
                            <div class="weui-cell__hd"><label for="" class="weui-label" style="width: 8em;">巡检时间：</label></div>
                            <div class="weui-cell__bd">
                                <input name="xjtime" class="weui-input" type="datetime-local" placeholder="请输入巡检时间" required/>
                            </div>
                        </div>
                        <div class="weui-cell weui-cell_select weui-cell_select-after">
                            <div class="weui-cell__hd">
                                <label for="" class="weui-label" style="width: 8em;">问题质量分类：</label>
                            </div>
                            <div class="weui-cell__bd">
                                <select name="typeName" class="weui-select" onChange="javascript:onChange(this.value)">
                                    <option value="管片破损" >管片破损</option>
                                    <option value="管片错台" >管片错台</option>
                                    <option value="螺栓复紧" >螺栓复紧</option>
                                    <option value="渗漏水" >渗漏水</option>
                                </select>
                            </div>
                        </div>

                    </div>

                    <div class="weui-cells__title">质量描述</div>
                    <div class="weui-cells weui-cells_form" id="x管片破损">
                        <div class="weui-cell weui-cell_select weui-cell_select-after">
                            <div class="weui-cell__hd">
                                <label for="" class="weui-label" style="width: 8em;">破损情况描述：</label>
                            </div>
                            <div class="weui-cell__bd">
                                <select name="gppsQkMiaoshu" class="weui-select">
                                	<c:forEach items="${dic}" var="d">
                                		<c:if test="${d.name == '管片破损'}">
                                			<c:forEach items="${d.gpzlDDinfos}" var="dd">
                                				<option value="${dd.name}" >${dd.name}</option>
                                			</c:forEach>
                                		</c:if>  
									</c:forEach>
									<!-- 
                                    <option value="1">湿渗</option>
                                     -->
                                </select>
                            </div>
                        </div>
                        <div class="weui-cell">
                            <div class="weui-cell__hd"><label class="weui-label" style="width: 8em;">破损长度(mm)：</label></div>
                            <div class="weui-cell__bd">
                                <input name="gppsLength" class="weui-input" type="number" placeholder="请输入破损长度" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"/>
                            </div>
                        </div>
                        <div class="weui-cell">
                            <div class="weui-cell__hd"><label class="weui-label" style="width: 8em;">破损宽度(mm)：</label></div>
                            <div class="weui-cell__bd">
                                <input name="gppsWidth" class="weui-input" type="number" placeholder="请输入破损宽度" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"/>
                            </div>
                        </div>
                    </div>

                    <div class="weui-cells weui-cells_form" style="display:none;" id="x管片错台">
                        <div class="weui-cell">
                            <div class="weui-cell__hd"><label class="weui-label" style="width: 8em;">径向(mm)：</label></div>
                            <div class="weui-cell__bd">
                                <input name="gpctJinXiang" class="weui-input" type="number" placeholder="请输入径向" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"/>
                            </div>
                        </div>
                        <div class="weui-cell">
                            <div class="weui-cell__hd"><label class="weui-label" style="width: 8em;">环向(mm)：</label></div>
                            <div class="weui-cell__bd">
                                <input name="gpctHuanXiang" class="weui-input" type="number" placeholder="请输入环向" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"/>
                            </div>
                        </div>
                    </div>

                    <div class="weui-cells weui-cells_form" style="display:none;" id="x螺栓复紧">
                        <div class="weui-cell">
                            <div class="weui-cell__hd"><label class="weui-label" style="width: 8em;">复紧不到位数量：</label></div>
                            <div class="weui-cell__bd">
                                <input name="lsfjNums" class="weui-input" type="number" placeholder="请输入复紧不到位数量" onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"/>
                            </div>
                        </div>
                    </div>

                    <div class="weui-cells weui-cells_form" style="display:none;" id="x渗漏水">
                        <div class="weui-cell weui-cell_select weui-cell_select-after">
                            <div class="weui-cell__hd">
                                <label for="" class="weui-label" style="width: 8em;">渗水情况描述：</label>
                            </div>
                            <div class="weui-cell__bd">
                                <select name="slsMiaoShu" class="weui-select">
                                	<c:forEach items="${dic}" var="d">
                                		<c:if test="${d.name == '渗漏水'}">
                                			<c:forEach items="${d.gpzlDDinfos}" var="dd">
                                				<option value="${dd.name}" >${dd.name}</option>
                                			</c:forEach>
                                		</c:if>  
									</c:forEach>
									<!-- 
                                    <option value="1">湿渗</option>
                                     -->
                                </select>
                            </div>
                        </div>
                    </div>
                    
                    <div class="weui-cells__title">其他内容</div>
                    <div class="weui-cells weui-cells_form">
                    	<div class="weui-cell">
							<div class="weui-cell__bd">
								<textarea name="remarks" class="weui-textarea" placeholder="请输入其他内容"
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
                                        <p class="weui-uploader__title">图片上传</p>
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

                </form>
                
              
                <!-- 上报提交管片质量巡检权限判断 -->
                <shiro:hasPermission name="/gczl/base/gczlYdxjGPZLXJInfo/add">
                <div class="weui-btn-area">
                    <a class="weui-btn weui-btn_primary" href="javascript:" id="formSubmitBtnToAndriod">确定</a>
                </div>
                </shiro:hasPermission>
                
                  <!-- 上报提交管片质量巡检权限判断 -->
                <shiro:hasPermission name="/gczl/base/gczlYdxjGPZLXJInfo/add">
                <div class="weui-btn-area">
                    <a class="weui-btn weui-btn_primary" href="javascript:" id="formSubmitBtn">确定并提交</a>
                </div>
                </shiro:hasPermission>
                
                
            </div>

            <div class="page__ft">
            </div>
        
        </div>
    </div>
	<div id="dialogs">
	        <!--BEGIN dialogTip-->
	        <div class="js_dialog" id="dialogTip" style="opacity: 0; display: none;">
	            <div class="weui-mask"></div>
	            <div class="weui-dialog">
	                <div id="dialogTip_content" class="weui-dialog__bd"></div>
	                <div class="weui-dialog__ft">
	                    <a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_primary">确定</a>
	                </div>
	            </div>
	        </div>
	        <!--END dialogTip-->
	        <!--BEGIN dialogSuccess-->
	        <div class="js_dialog" id="dialogSuccess" style="opacity: 0; display: none;">
	            <div class="weui-mask"></div>
	            <div class="weui-dialog weui-skin_android">
	                <div class="weui-dialog__hd"><strong class="weui-dialog__title">操作成功</strong></div>
	                <div id="dialogSuccess_content" class="weui-dialog__bd"></div>
	                <div class="weui-dialog__ft">
	                    <a href="javascript:closeCurrentWindow();" class="weui-dialog__btn weui-dialog__btn_default">返回</a>
	                    <a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_primary">取消</a>
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
        $(function(){
            var tmpl = '<li class="weui-uploader__file" style="background-image:url(#url#)"></li>',
                $gallery = $("#gallery"), $galleryImg = $("#galleryImg"),
                $uploaderInput = $("#uploaderInput"),
                $uploaderFiles = $("#uploaderFiles")
                ;

            $uploaderInput.on("change", function(e){
                var src, url = window.URL || window.webkitURL || window.mozURL, files = e.target.files;
                for (var i = 0, len = files.length; i < len; ++i) {
                    var file = files[i];

                    if (url) {
                        src = url.createObjectURL(file);
                    } else {
                        src = e.target.result;
                    }

                    $uploaderFiles.append($(tmpl.replace('#url#', src)));
                }
            });
            $uploaderFiles.on("click", "li", function(){
                $galleryImg.attr("style", this.getAttribute("style"));
                $gallery.fadeIn(100);
            });
            $gallery.on("click", function(){
                $gallery.fadeOut(100);
            });
            
            var $tooltips = $('.js_tooltips');

            $('#showTooltips').on('click', function(){
                if ($tooltips.css('display') != 'none') return;

                // toptips的fixed, 如果有`animation`, `position: fixed`不生效
                $('.page.cell').removeClass('slideIn');

                $tooltips.css('display', 'block');
                setTimeout(function () {
                    $tooltips.css('display', 'none');
                }, 2000);
            });
            
            var $dialogTip = $('#dialogTip');
            var $dialogSuccess = $('#dialogSuccess');

	        $('#dialogs').on('click', '.weui-dialog__btn', function(){
	            $(this).parents('.js_dialog').fadeOut(200);
	        });
	        
			<c:choose>
			   <c:when test="${isDo}">
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
			
			//上报提交管片质量巡检权限判断,没有权限提示
    		<shiro:lacksPermission name="/gczl/base/gczlYdxjGPZLXJInfo/add">
	    		$('#dialogTip_content').empty();
		    	$('#dialogTip_content').append('当前用户没有上报权限');
		   		$dialogTip.fadeIn(200);
    		</shiro:lacksPermission>
			
			var success = '${data.success}';
			
	        var $form = $("#form");
	      	
	        // 表单校验
	        $("#formSubmitBtn").on("click", function(){
	            $form.validate(function(error){
	                if(error){
	                	$('#dialogTip_content').empty();
				    	$('#dialogTip_content').append("请完善输入框");
				   		$dialogTip.fadeIn(200);
	                }else{
	                	$form.submit();
	                }
	            });

	        });
	        
	        
	     // 表单校验
	        $("#formSubmitBtnToAndriod").on("click", function(){
	            $form.validate(function(error){
	                if(error){
	                	$('#dialogTip_content').empty();
				    	$('#dialogTip_content').append("请完善输入框");
				   		$dialogTip.fadeIn(200);
	                }else{
	                	<%=type%>.savaForm($(form).serialize());
	                }
	            });

	        });
	        
        });
        
        var closeCurrentWindow = function(){
        	<%=type%>.closeCurrentWindow();
        }
        
        $("#line").chained("#section");
    </script>
</body>
</html>

