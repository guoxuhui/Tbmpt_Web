/**
 * 
 * @requires jQuery
 * 
 * 当页面加载完毕关闭加载进度
 * **/
$(window).load(function(){
    $("#loading").fadeOut();
});

/**
 * 使panel和datagrid在加载时提示
 * 
 * @requires jQuery,EasyUI
 * 
 */
$.fn.panel.defaults.loadingMessage = '加载中....';
$.fn.datagrid.defaults.loadMsg = '加载中....';

/**
 * @requires jQuery,EasyUI
 * 
 * panel关闭时回收内存，主要用于layout使用iframe嵌入网页时的内存泄漏问题
 */
$.fn.panel.defaults.onBeforeDestroy = function() {
    var frame = $('iframe', this);
    try {
        if (frame.length > 0) {
            for ( var i = 0; i < frame.length; i++) {
                frame[i].src = '';
                frame[i].contentWindow.document.write('');
                frame[i].contentWindow.close();
            }
            frame.remove();
            if (navigator.userAgent.indexOf("MSIE") > 0) {// IE特有回收内存方法
                try {
                    CollectGarbage();
                } catch (e) {
                }
            }
        }
    } catch (e) {
    }
};

/**
 *  
 * 
 * @requires jQuery,EasyUI
 * 
 * 防止panel/window/dialog组件超出浏览器边界
 * @param left
 * @param top
 */
var easyuiPanelOnMove = function(left, top) {
    var l = left;
    var t = top;
    if (l < 1) {
        l = 1;
    }
    if (t < 1) {
        t = 1;
    }
    var width = parseInt($(this).parent().css('width')) + 14;
    var height = parseInt($(this).parent().css('height')) + 14;
    var right = l + width;
    var buttom = t + height;
    var browserWidth = $(window).width();
    var browserHeight = $(window).height();
    if (right > browserWidth) {
        l = browserWidth - width;
    }
    if (buttom > browserHeight) {
        t = browserHeight - height;
    }
    $(this).parent().css({/* 修正面板位置 */
        left : l,
        top : t
    });
};
$.fn.dialog.defaults.onMove = easyuiPanelOnMove;
$.fn.window.defaults.onMove = easyuiPanelOnMove;
$.fn.panel.defaults.onMove = easyuiPanelOnMove;

/**
 *  
 * 
 * @requires jQuery,EasyUI
 * 
 * 通用错误提示
 * 
 * 用于datagrid/treegrid/tree/combogrid/combobox/form加载数据出错时的操作
 */
var easyuiErrorFunction = function(XMLHttpRequest) {
    parent.$.messager.alert('错误', XMLHttpRequest.responseText);
};
$.fn.datagrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.treegrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.tree.defaults.onLoadError = easyuiErrorFunction;
$.fn.combogrid.defaults.onLoadError = easyuiErrorFunction;
$.fn.combobox.defaults.onLoadError = easyuiErrorFunction;
$.fn.form.defaults.onLoadError = easyuiErrorFunction;

/**
 *  
 * 
 * @requires jQuery,EasyUI
 * 
 * 为datagrid、treegrid增加表头菜单，用于显示或隐藏列，注意：冻结列不在此菜单中
 */
var createGridHeaderContextMenu = function(e, field) {
    e.preventDefault();
    var grid = $(this);/* grid本身 */
    var headerContextMenu = this.headerContextMenu;/* grid上的列头菜单对象 */
    if (!headerContextMenu) {
        var tmenu = $('<div style="width:100px;"></div>').appendTo('body');
        var fields = grid.datagrid('getColumnFields');
        for ( var i = 0; i < fields.length; i++) {
            var fildOption = grid.datagrid('getColumnOption', fields[i]);
            if (!fildOption.hidden) {
                $('<div iconCls="tick" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu);
            } else {
                $('<div iconCls="bullet_blue" field="' + fields[i] + '"/>').html(fildOption.title).appendTo(tmenu);
            }
        }
        headerContextMenu = this.headerContextMenu = tmenu.menu({
            onClick : function(item) {
                var field = $(item.target).attr('field');
                if (item.iconCls == 'tick') {
                    grid.datagrid('hideColumn', field);
                    $(this).menu('setIcon', {
                        target : item.target,
                        iconCls : 'bullet_blue'
                    });
                } else {
                    grid.datagrid('showColumn', field);
                    $(this).menu('setIcon', {
                        target : item.target,
                        iconCls : 'tick'
                    });
                }
            }
        });
    }
    headerContextMenu.menu('show', {
        left : e.pageX,
        top : e.pageY
    });
};
$.fn.datagrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;
$.fn.treegrid.defaults.onHeaderContextMenu = createGridHeaderContextMenu;

/**
 * grid tooltip参数
 * 
 *  
 */
var gridTooltipOptions = {
    tooltip : function(jq, fields) {
        return jq.each(function() {
            var panel = $(this).datagrid('getPanel');
            if (fields && typeof fields == 'object' && fields.sort) {
                $.each(fields, function() {
                    var field = this;
                    bindEvent($('.datagrid-body td[field=' + field + '] .datagrid-cell', panel));
                });
            } else {
                bindEvent($(".datagrid-body .datagrid-cell", panel));
            }
        });

        function bindEvent(jqs) {
            jqs.mouseover(function() {
                var content = $(this).text();
                if (content.replace(/(^\s*)|(\s*$)/g, '').length > 5) {
                    $(this).tooltip({
                        content : content,
                        trackMouse : true,
                        position : 'bottom',
                        onHide : function() {
                            $(this).tooltip('destroy');
                        },
                        onUpdate : function(p) {
                            var tip = $(this).tooltip('tip');
                            if (parseInt(tip.css('width')) > 500) {
                                tip.css('width', 500);
                            }
                        }
                    }).tooltip('show');
                }
            });
        }
    }
};
/**
 * Datagrid扩展方法tooltip 基于Easyui 1.3.3，可用于Easyui1.3.3+
 * 
 * 简单实现，如需高级功能，可以自由修改
 * 
 * 使用说明:
 * 
 * 在easyui.min.js之后导入本js
 * 
 * 代码案例:
 * 
 * $("#dg").datagrid('tooltip'); 所有列
 * 
 * $("#dg").datagrid('tooltip',['productid','listprice']); 指定列
 * 
 *  
 */
$.extend($.fn.datagrid.methods, gridTooltipOptions);

/**
 * Treegrid扩展方法tooltip 基于Easyui 1.3.3，可用于Easyui1.3.3+
 * 
 * 简单实现，如需高级功能，可以自由修改
 * 
 * 使用说明:
 * 
 * 在easyui.min.js之后导入本js
 * 
 * 代码案例:
 * 
 * $("#dg").treegrid('tooltip'); 所有列
 * 
 * $("#dg").treegrid('tooltip',['productid','listprice']); 指定列
 * 
 *  
 */
$.extend($.fn.treegrid.methods, gridTooltipOptions);

/**
 *  
 * 
 * @requires jQuery,EasyUI
 * 
 * 扩展validatebox，添加验证两次密码功能
 */
$.extend($.fn.validatebox.defaults.rules, {
    eqPwd : {
        validator : function(value, param) {
            return value == $(param[0]).val();
        },
        message : '密码不一致！'
    }
});

//扩展tree，使其可以获取实心节点
$.extend($.fn.tree.methods, {
    getCheckedExt : function(jq) {// 获取checked节点(包括实心)
        var checked = $(jq).tree("getChecked");
        var checkbox2 = $(jq).find("span.tree-checkbox2").parent();
        $.each(checkbox2, function() {
            var node = $.extend({}, $.data(this, "tree-node"), {
                target : this
            });
            checked.push(node);
        });
        return checked;
    },
    getSolidExt : function(jq) {// 获取实心节点
        var checked = [];
        var checkbox2 = $(jq).find("span.tree-checkbox2").parent();
        $.each(checkbox2, function() {
            var node = $.extend({}, $.data(this, "tree-node"), {
                target : this
            });
            checked.push(node);
        });
        return checked;
    }
});

//扩展tree，使其支持平滑数据格式
$.fn.tree.defaults.loadFilter = function(data, parent) {
    var opt = $(this).data().tree.options;
    var idFiled, textFiled, parentField;
    if (opt.parentField) {
        idFiled = opt.idFiled || 'id';
        textFiled = opt.textFiled || 'text';
        parentField = opt.parentField;
        var i, l, treeData = [], tmpMap = [];
        for (i = 0, l = data.length; i < l; i++) {
            tmpMap[data[i][idFiled]] = data[i];
        }
        for (i = 0, l = data.length; i < l; i++) {
            if (tmpMap[data[i][parentField]] && data[i][idFiled] != data[i][parentField]) {
                if (!tmpMap[data[i][parentField]]['children'])
                    tmpMap[data[i][parentField]]['children'] = [];
                data[i]['text'] = data[i][textFiled];
                tmpMap[data[i][parentField]]['children'].push(data[i]);
            } else {
                data[i]['text'] = data[i][textFiled];
                treeData.push(data[i]);
            }
        }
        return treeData;
    }
    return data;
};

// 扩展treegrid，使其支持平滑数据格式
$.fn.treegrid.defaults.loadFilter = function(data, parentId) {
    var opt = $(this).data().treegrid.options;
    var idFiled, textFiled, parentField;
    if (opt.parentField) {
        idFiled = opt.idFiled || 'id';
        textFiled = opt.textFiled || 'text';
        parentField = opt.parentField;
        var i, l, treeData = [], tmpMap = [];
        for (i = 0, l = data.length; i < l; i++) {
            tmpMap[data[i][idFiled]] = data[i];
        }
        for (i = 0, l = data.length; i < l; i++) {
            if (tmpMap[data[i][parentField]] && data[i][idFiled] != data[i][parentField]) {
                if (!tmpMap[data[i][parentField]]['children'])
                    tmpMap[data[i][parentField]]['children'] = [];
                data[i]['text'] = data[i][textFiled];
                tmpMap[data[i][parentField]]['children'].push(data[i]);
            } else {
                data[i]['text'] = data[i][textFiled];
                treeData.push(data[i]);
            }
        }
        return treeData;
    }
    return data;
};

// 扩展combotree，使其支持平滑数据格式
$.fn.combotree.defaults.loadFilter = $.fn.tree.defaults.loadFilter;

/**
 * 
 * @requires jQuery,EasyUI
 * 
 * 创建一个模式化的dialog
 * 
 * @returns $.modalDialog.handler 这个handler代表弹出的dialog句柄
 * 
 * @returns $.modalDialog.xxx 这个xxx是可以自己定义名称，主要用在弹窗关闭时，刷新某些对象的操作，可以将xxx这个对象预定义好
 */
$.modalDialog = function(options) {
    if ($.modalDialog.handler == undefined) {// 避免重复弹出
        var opts = $.extend({
            title : '',
            width : 840,
            height : 680,
            modal : true,
            loadingMessage : "数据在加载中...",
            onClose : function() {
                $.modalDialog.handler = undefined;
                $(this).dialog('destroy');
            },
            onOpen : function() {
            }
        }, options);
        opts.modal = true;// 强制此dialog为模式化，无视传递过来的modal参数
        return $.modalDialog.handler = $('<div/>').dialog(opts);
    }
};


$.cookie = function(key, value, options) {
    if (arguments.length > 1 && (value === null || typeof value !== "object")) {
        options = $.extend({}, options);
        if (value === null) {
            options.expires = -1;
        }
        if (typeof options.expires === 'number') {
            var days = options.expires, t = options.expires = new Date();
            t.setDate(t.getDate() + days);
        }
        return (document.cookie = [ encodeURIComponent(key), '=', options.raw ? String(value) : encodeURIComponent(String(value)), options.expires ? '; expires=' + options.expires.toUTCString() : '', options.path ? '; path=' + options.path : '', options.domain ? '; domain=' + options.domain : '', options.secure ? '; secure' : '' ].join(''));
    }
    options = value || {};
    var result, decode = options.raw ? function(s) {
        return s;
    } : decodeURIComponent;
    return (result = new RegExp('(?:^|; )' + encodeURIComponent(key) + '=([^;]*)').exec(document.cookie)) ? decode(result[1]) : null;
};

/**
 * 
 * @requires jQuery
 * 
 * 将form表单元素的值序列化成对象
 * 
 * @returns object
 */
$.serializeObject = function(form) {
    var o = {};
    $.each(form.serializeArray(), function(index) {
        if (o[this['name']]) {
            o[this['name']] = o[this['name']] + "," + this['value'];
        } else {
            o[this['name']] = this['value'];
        }
    });
    return o;
};

/**
 * 
 * 增加formatString功能
 * 
 * 使用方法：$.formatString('字符串{0}字符串{1}字符串','第一个变量','第二个变量');
 * 
 * @returns 格式化后的字符串
 */
$.formatString = function(str) {
    for ( var i = 0; i < arguments.length - 1; i++) {
        str = str.replace("{" + i + "}", arguments[i + 1]);
    }
    return str;
};

/**
 * 
 * 接收一个以逗号分割的字符串，返回List，list里每一项都是一个字符串
 * 
 * @returns list
 */
$.stringToList = function(value) {
    if (value != undefined && value != '') {
        var values = [];
        var t = value.split(',');
        for ( var i = 0; i < t.length; i++) {
            values.push('' + t[i]);/* 避免他将ID当成数字 */
        }
        return values;
    } else {
        return [];
    }
};

/**
 * 
 * @requires jQuery
 * 
 * 改变jQuery的AJAX默认属性和方法
 */
$.ajaxSetup({
    type : 'POST',
    error : function(XMLHttpRequest, textStatus, errorThrown) {
        try {
            parent.$.messager.progress('close');
            parent.$.messager.alert('错误', XMLHttpRequest.responseText);
        } catch (e) {
            alert(XMLHttpRequest.responseText);
        }
    }
});


/**
 * 
 * @requires jQuery
 * 
 * 去掉空格
 * **/
String.prototype.trim = function() {
    return this.replace(/(^\s*)|(\s*$)/g, '');
};
String.prototype.ltrim = function() {
    return this.replace(/(^\s*)/g, '');
};
String.prototype.rtrim = function() {
    return this.replace(/(\s*$)/g, '');
};

/**
 * 
 * @requires jQuery
 * 
 * 页面加载加载进度条启用
 * **/
function progressLoad(){  
    $("<div class=\"datagrid-mask\" style=\"position:absolute;z-index: 9999;\"></div>").css({display:"block",width:"100%",height:$(window).height()}).appendTo("body");  
    $("<div class=\"datagrid-mask-msg\" style=\"position:absolute;z-index: 9999;\"></div>").html("正在处理，请稍候。。。").appendTo("body").css({display:"block",left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2});  
}

/**
 * 
 * @requires jQuery
 * 
 * 页面加载加载进度条关闭
 * **/
function progressClose(){
    $(".datagrid-mask").remove();  
    $(".datagrid-mask-msg").remove();
}

/**
 * 
 * @requires jQuery
 * 
 * 防止退格键导致页面回退
 */
$(document).keydown(function (e) { 
    var doPrevent; 
    if (e.keyCode == 8) { 
        var d = e.srcElement || e.target; 
        if (d.tagName.toUpperCase() == 'INPUT' || d.tagName.toUpperCase() == 'TEXTAREA') { 
            doPrevent = d.readOnly || d.disabled; 
        }else{
            doPrevent = true; 
        }
    }else{ 
        doPrevent = false; 
    }
    if (doPrevent) 
    e.preventDefault(); 
});


/**
 * 判断一个元素val是否包含于一个数组arr中
 */
function isInArray(val, arr){
	if(val && arr){
		if(jQuery.inArray(val, arr, 0)> -1){
			return true;
		}
	}
	return false;
}

/**
 * DateGrid导出方法
 */
var ExportUtil = {
	
	TYPE_XLS_2003 : "xls",
	TYPE_XLS_2007 : "xlsx",
	TYPE_PDF : "pdf",
	EXPXLS_MAX_ROWS : 65534,
	EXPXLS_MAX_COLUMNS : 100,
	
	SPLIT_STR : ",",
	
	appendElement : function(fm, type, name, value, otherAttr){
		var e = '<input type="'+ type +'" name="' + name + '" value="' + value +'"';
		
		if(otherAttr){
			$.each(otherAttr, function(i, n){
				e += ' ' + n;
			});
		}
		e += '>';
		
		fm.append(e);
		return fm;
	},
	
	appendHiddenElement : function(fm, name, value){
		return ExportUtil.appendElement(fm, 'hidden', name, value);
	},

	/**
	 * 导出方法
	 * @param expUrl 导出action路径
	 * @param fileType 导出文件类型
	 * @param fileName 导出文件名称
	 * @param datagridId 导出表格的id
	 * @param isExpId 是否导出ID
	 */
	doExport1 : function(expUrl, fileType, fileName, datagrid, isExpId,arr){
		if(fileType == ExportUtil.TYPE_XLS_2003 
				|| fileType == ExportUtil.TYPE_XLS_2007 
				|| fileType == ExportUtil.TYPE_PDF){
			
			var expForm =$("<form />");

			expForm.attr("action", expUrl);
			expForm.attr("method", "post");

			expForm = ExportUtil.appendHiddenElement(expForm, 'fileName', fileName);
			expForm = ExportUtil.appendHiddenElement(expForm, 'fileType', fileType);
			expForm = ExportUtil.appendElement(expForm, 'radio', 'all', '1', ['checked="checked"']);
			expForm.append("所有数据");
			expForm.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
			expForm = ExportUtil.appendElement(expForm, 'radio', 'all', '0');
			expForm.append("当前页数据");
			
			if(datagrid){
				var dg = datagrid;
				
				if(dg && (dg.length > 0)){
					var ufclos = dg.datagrid('getColumnFields');
					var fclos = dg.datagrid('getColumnFields', true);
					var cols = fclos.concat(ufclos);
					var opts = dg.datagrid("options");
					var idField = opts.idField;
					var fields = [];
					if(isExpId){
						fields = cols;
					}else{
						fields = $.map(cols, function(n){
							return (!isInArray(n,arr))? n : null;
						});
					}

					var titles = [];
					if(fields && (fields.length >0)){
						$.each(fields, function(i, n){
							titles.push(dg.datagrid('getColumnOption', n).title);
						});
					}
					
					var ids = [];
					var srows = dg.datagrid('getSelections');
					if (srows.length > 0) {
						$.each(srows, function(i, n){
							ids.push(srows[i].id);
						});
						expForm = ExportUtil.appendHiddenElement(expForm, 'ids', ids.join(ExportUtil.SPLIT_STR));
					}
					expForm = ExportUtil.appendHiddenElement(expForm, 'fields', fields.join(ExportUtil.SPLIT_STR));
					expForm = ExportUtil.appendHiddenElement(expForm, 'titles', titles.join(ExportUtil.SPLIT_STR));
					
					var treeField = opts.treeField;
					if(!treeField){	
						try{
							var pg = dg.datagrid('getPager');
							if(!(jQuery.isEmptyObject(pg))){
								var pgOpts = pg.pagination('options');
								expForm = ExportUtil.appendHiddenElement(expForm, 'pageNumber', pgOpts.pageNumber);
								expForm = ExportUtil.appendHiddenElement(expForm, 'pageSize', pgOpts.pageSize);
								expForm = ExportUtil.appendHiddenElement(expForm, 'total', pgOpts.total);
							}
						}catch(e){
						}
					}
					var queryParams = opts.queryParams;
					if(opts.sortName){
						queryParams.sort = opts.sortName;
						queryParams.order = opts.sortOrder;
					}
					for(var pro in queryParams){
						if(pro && (queryParams[pro] != "")){							
							expForm = ExportUtil.appendHiddenElement(expForm, pro, queryParams[pro]);
						}
					}
					expForm = ExportUtil.appendHiddenElement(expForm, 'expXlsMaxRows', ExportUtil.EXPXLS_MAX_ROWS);
					expForm = ExportUtil.appendHiddenElement(expForm, 'expXlsMaxColumns', ExportUtil.EXPXLS_MAX_COLUMNS);
					
					var	expDiv = $("<div id='pexpDiv' style='display:none; overflow:hidden' align='center'/>");
					expDiv.append(expForm);
					$("body").append(expDiv);

					expForm.submit();
					expDiv.remove();
				}else{
					alertMessage('', '未找到目标datagrid对象 ！', '');
				}
			}
		}else{
			alertMessage('', '目前只支持导出Excel和PDF文件！', '');
		}
	},

    doExport : function(expUrl, fileType, fileName, datagrid, isExpId){
        if(fileType == ExportUtil.TYPE_XLS_2003
            || fileType == ExportUtil.TYPE_XLS_2007
            || fileType == ExportUtil.TYPE_PDF){

            var expForm =$("<form />");

            expForm.attr("action", expUrl);
            expForm.attr("method", "post");

            expForm = ExportUtil.appendHiddenElement(expForm, 'fileName', fileName);
            expForm = ExportUtil.appendHiddenElement(expForm, 'fileType', fileType);
            expForm = ExportUtil.appendElement(expForm, 'radio', 'all', '1', ['checked="checked"']);
            expForm.append("所有数据");
            expForm.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
            expForm = ExportUtil.appendElement(expForm, 'radio', 'all', '0');
            expForm.append("当前页数据");

            if(datagrid){
                var dg = datagrid;

                if(dg && (dg.length > 0)){
                    var ufclos = dg.datagrid('getColumnFields');
                    var fclos = dg.datagrid('getColumnFields', true);
                    var cols = fclos.concat(ufclos);
                    var opts = dg.datagrid("options");
                    var idField = opts.idField;
                    var fields = [];
                    if(isExpId){
                        fields = cols;
                    }else{
                        fields = $.map(cols, function(n){
                            return (n != idField)? n : null;
                        });
                    }

                    var titles = [];
                    if(fields && (fields.length >0)){
                        $.each(fields, function(i, n){
                            titles.push(dg.datagrid('getColumnOption', n).title);
                        });
                    }

                    var ids = [];
                    var srows = dg.datagrid('getSelections');
                    if (srows.length > 0) {
                        $.each(srows, function(i, n){
                            ids.push(srows[i].id);
                        });
                        expForm = ExportUtil.appendHiddenElement(expForm, 'ids', ids.join(ExportUtil.SPLIT_STR));
                    }
                    expForm = ExportUtil.appendHiddenElement(expForm, 'fields', fields.join(ExportUtil.SPLIT_STR));
                    expForm = ExportUtil.appendHiddenElement(expForm, 'titles', titles.join(ExportUtil.SPLIT_STR));

                    var treeField = opts.treeField;
                    if(!treeField){
                        try{
                            var pg = dg.datagrid('getPager');
                            if(!(jQuery.isEmptyObject(pg))){
                                var pgOpts = pg.pagination('options');
                                expForm = ExportUtil.appendHiddenElement(expForm, 'pageNumber', pgOpts.pageNumber);
                                expForm = ExportUtil.appendHiddenElement(expForm, 'pageSize', pgOpts.pageSize);
                                expForm = ExportUtil.appendHiddenElement(expForm, 'total', pgOpts.total);
                            }
                        }catch(e){
                        }
                    }
                    var queryParams = opts.queryParams;
                    if(opts.sortName){
                        queryParams.sort = opts.sortName;
                        queryParams.order = opts.sortOrder;
                    }
                    for(var pro in queryParams){
                        if(pro && (queryParams[pro] != "")){
                            expForm = ExportUtil.appendHiddenElement(expForm, pro, queryParams[pro]);
                        }
                    }
                    expForm = ExportUtil.appendHiddenElement(expForm, 'expXlsMaxRows', ExportUtil.EXPXLS_MAX_ROWS);
                    expForm = ExportUtil.appendHiddenElement(expForm, 'expXlsMaxColumns', ExportUtil.EXPXLS_MAX_COLUMNS);

                    var	expDiv = $("<div id='pexpDiv' style='display:none; overflow:hidden' align='center'/>");
                    expDiv.append(expForm);
                    $("body").append(expDiv);

                    expForm.submit();
                    expDiv.remove();
                }else{
                    alertMessage('', '未找到目标datagrid对象 ！', '');
                }
            }
        }else{
            alertMessage('', '目前只支持导出Excel和PDF文件！', '');
        }
    }
};

/**
 * 定义combo、combobox、combotree清空按钮
 */
$.fn.combo.defaults.icons=[{

    iconCls:'icon-clear',
    handler:function(e){
        //alert($(e.handleObj.data.target).combobox('getValue'));
        $(e.handleObj.data.target).combo('clear');
    }
}];
$.fn.combobox.defaults.icons=[{

    iconCls:'icon-clear',
    handler:function(e){
        //alert($(e.handleObj.data.target).combobox('getValue'));
        $(e.handleObj.data.target).combobox('clear');
    }
}];
$.fn.combotree.defaults.icons=[{

    iconCls:'icon-clear',
    handler:function(e){
        //alert($(e.handleObj.data.target).combobox('getValue'));
        $(e.handleObj.data.target).combotree('clear');
    }
}];
$.fn.combogrid.defaults.icons=[{

    iconCls:'icon-clear',
    handler:function(e){
        //alert($(e.handleObj.data.target).combobox('getValue'));
        $(e.handleObj.data.target).combogrid('clear');
    }
}];
$.fn.combotreegrid.defaults.icons=[{

    iconCls:'icon-clear',
    handler:function(e){
        //alert($(e.handleObj.data.target).combobox('getValue'));
        $(e.handleObj.data.target).combotreegrid('clear');
    }
}];

/**
 * 定义日期输入框清空按钮
 */
$.fn.datebox.defaults.cleanText = '清空';

var datebox_buttons = $.extend([], $.fn.datebox.defaults.buttons);
datebox_buttons.splice(1, 0, {
    text: function (target) {
        return $(target).datebox("options").cleanText
    },
    handler: function (target) {
        $(target).datebox("setValue", "");
        $(target).datebox("hidePanel");
    }
});
$.extend($.fn.datebox.defaults, {
    buttons: datebox_buttons,
    editable: false
});

$.fn.datetimebox.defaults.cleanText = '清空';

var datetimebox_buttons = $.extend([], $.fn.datetimebox.defaults.buttons);
datetimebox_buttons.splice(1, 0, {
    text: function (target) {
        return $(target).datetimebox("options").cleanText
    },
    handler: function (target) {
        $(target).datetimebox("setValue", "");
        $(target).datetimebox("hidePanel");
    }
});
$.extend($.fn.datetimebox.defaults, {
    buttons: datetimebox_buttons,
    editable: false
});

/**
 * 全局设置下拉框不可编辑
 */
$.extend($.fn.combo.defaults, {
    editable: false
});
$.extend($.fn.combobox.defaults, {
    editable: false
});
$.extend($.fn.combotree.defaults, {
    editable: false
});
$.extend($.fn.combogrid.defaults, {
    editable: false
});
$.extend($.fn.combotreegrid.defaults, {
    editable: false
});
/**
 * 禁用form表单中所有的input[文本框、复选框、单选框],select[下拉选],多行文本框[textarea]  
 * @param formId
 * @param isDisabled
 * @returns
 */
function disableForm(formId,isDisabled) {  
    var attr="disable";  
    if(!isDisabled){  
       attr="enable";  
    }  
    
    
    $("form[id='"+formId+"'] :text").attr("disabled",isDisabled);  
    $("form[id='"+formId+"'] :input").attr("disabled",isDisabled);  
    $("form[id='"+formId+"'] select").attr("disabled",isDisabled);  
    $("form[id='"+formId+"'] :radio").attr("disabled",isDisabled);  
    $("form[id='"+formId+"'] :checkbox").attr("disabled",isDisabled);  
    $("form[id='"+formId+"'] input").attr("disabled",isDisabled);
    
    $("form[id='"+formId+"'] input").attr("readonly",true);
    $("form[id='"+formId+"'] select").attr("readonly",true);
    //禁用jquery easyui中的下拉选（使用input生成的combox）  
    $("#" + formId + " input[class='textbox-text']").each(function () {  
        if (this) {
            $(this).combobox(attr);  
        }  
    });  
    
    $("#" + formId + " input[class='combobox-f combo-f']").each(function () {  
        if (this) {
            $(this).combobox(attr);  
        }  
    });  
      
    //禁用jquery easyui中的下拉选（使用select生成的combox）  
    $("#" + formId + " select[class='combobox-f combo-f']").each(function () {  
        if (this) {
            $(this).combobox(attr);  
        }  
    });  
      
    //禁用jquery easyui中的日期组件dataBox  
    $("#" + formId + " input[class='datebox-f combo-f']").each(function () {  
        if (this) {
            $(this).datebox(attr);  
        }  
    });
    
    
    $("#" + formId + " .textbox-icon").each(function () {  
        if (this) {
            $(this).remove();
        }  
    });  
    
    $("#" + formId + " .textbox-button").each(function () {  
        if (this) {
            $(this).remove();
        }  
    }); 
}  

/**
 * 将标签下的输入框变为只读
 * @param tagId 要设置的标签ID
 * @param isDisabled true：只读  false：可编辑
 * @returns
 */
function disableTag(tagId,isDisabled) {
	$("#"+tagId+" input").attr("disabled",isDisabled);
	$("#"+tagId+" textarea").attr("disabled",isDisabled);
	$("#"+tagId+" .easyui-textbox").combo('readonly',isDisabled);
	$("#"+tagId+" .easyui-datetimebox").combo('readonly',isDisabled);
	$("#"+tagId+" .easyui-combobox").combo('readonly',isDisabled);
	$("#"+tagId+" .easyui-combotree").combo('readonly',isDisabled);
	
}

/**
 * 表格字段格式化公共方法
 */
var DataGridFormatter = {
	
	/**
	 * 参数：str:原始字符串 n:需要返回的长度，汉字=2 返回值：处理后的字符串
	 * 默认40个字符 20个汉字
	 */  
	tipsFormatter:function(str,n){
		if((str != null) && (str != undefined)){
			var num = 40;
			if(n != null && n != undefined ){
				num = n;
			}
			var ilen = str.length;  
			if(ilen*2 <= num) return str;  
			num -= 3;  
			var i = 0;  
			while(i < ilen && num > 0){  
				if(escape(str.charAt(i)).length>4) num--;  
				num--;
				i++;  
			}  
			if( num > 0) return str;  
			var result = str.substring(0,i)+"...";
			return '<span class="easyui-tooltip" title="' + str + '">' + result +'</span>';//class="easyui-tooltip"
		}else{
			return "";
		}
	}  
	
}


/**
 * 表单工具类
 * 
 * 
 */
var FormUtil = {
		
	clearForm:function(form){
		form.find("input").val("");
		form.find("textarea").val("");
		form.form('clear');
	}
}


var is_fullscreen = false;
var fullscreen=function(){  
    elem=document.body;  
    if(elem.webkitRequestFullScreen){  
        elem.webkitRequestFullScreen();     
    }else if(elem.mozRequestFullScreen){  
        elem.mozRequestFullScreen();  
    }else if(elem.requestFullScreen){  
        elem.requestFullscreen();  
    }else{  
        //浏览器不支持全屏API或已被禁用  
    	if(typeof window.ActiveXObject != "undefined") {
    	    //for IE，这里其实就是模拟了按下键盘的F11，使浏览器全屏
    	    var wscript = new ActiveXObject("WScript.Shell");
    	    if(wscript != null) {
    	        wscript.SendKeys("{F11}");
    	    }
    	}
    }  
}  

var exitFullscreen=function(){  
    var elem=document;  
    if(elem.webkitCancelFullScreen){  
        elem.webkitCancelFullScreen();      
    }else if(elem.mozCancelFullScreen){  
        elem.mozCancelFullScreen();  
    }else if(elem.cancelFullScreen){  
        elem.cancelFullScreen();  
    }else if(elem.exitFullscreen){  
        elem.exitFullscreen();  
    }else{  
        //浏览器不支持全屏API或已被禁用  
        //浏览器不支持全屏API或已被禁用  
    	if(typeof window.ActiveXObject != "undefined") {
    	    //for IE，这里其实就是模拟了按下键盘的F11，使浏览器全屏
    	    var wscript = new ActiveXObject("WScript.Shell");
    	    if(wscript != null) {
    	        wscript.SendKeys("{F11}");
    	    }
    	}
    	
    }  
} 

var is_topopen = true;
