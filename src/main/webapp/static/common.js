//获取工程下拉列表的方法
function getGongCheng() {
    $('#gcbh').combobox({
        url : basePath + '/project/info/getProjects',
        valueField : "id",
        textField : "proName"
    });
}

//根据工程编号获取区间
function getGongCheng(pid) {
  $('#qujian').combobox({
         url : '${path}/project/section/getSectionByProId?id'+pid,
         valueField: "id",
         textField: "sectionName"
    });
}

//编辑监测明细信息时的备注下拉框
function detailRemarks(){
 $('#detailRremarks').combobox({
        url : basePath + '/dmcjjc/dmcjddinfo/getJcShuoMing',
        valueField : "ddName",
        textField : "ddName",
        onLoadSuccess:function(){
        }
  });
}

//打开编辑监测明细信息窗口时的通用方法
function editDetailInfoFun(url,title) {
	var ids = checkSelect();
    if( 1 < ids.length){
        parent.$.messager.alert('提示', '请选择一条数据进行编辑', 'info');
        return;
    }
    if( 0 == ids.length){
    	return;
    }
    var rowIndex = $('#dataGrid').datagrid('getRowIndex', ids[0]);//id是关键字值
    var data = $('#dataGrid').datagrid('getData').rows[rowIndex];
    if("已确认" == data.ifqr){
        parent.$.messager.alert('提示', '选择的数据确认状态为已确认，禁止修改。', 'info');
        return;
    }

    parent.$.modalDialog({
        title : title,
        width : 900,
        height : 500,
        maximizable:true,
        href : url+"?id="+ids[0],
        buttons : [ {
            text : '保存',
            handler : function() {
                parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                var f = parent.$.modalDialog.handler.find('#addForm');
                f.submit();
                dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
            }
        },{
            text : '返回',
            handler : function() {
            	parent.$.modalDialog.handler.dialog('close');
            }
        }],
        onBeforeClose:function(){
        	var item = parent.$.modalDialog.handler.find('#dataSta');
        	dataSta = item.val();//数据状态 original:原始状态；modified:修改状态;
        	if(dataSta !=null && dataSta =='modified'){
        		 parent.$.messager.confirm('询问', '您尚未保存数据，确定离开页面吗？', function(b) {
        	  	        if (b) {
        	  	        	item.val('original');//关闭页面后，数据状态恢复为原始状态
        	  	        	parent.$.modalDialog.handler.dialog('close');
        	  	        }
        		 })
        	}else{
        		return true;
        	}
        	return false;
        }
    });
}

//列表中点击检测日期，打开明细页面通用方法
function showFun(id,url,title,width,height){
    if(null == width)
        width = 900;
    if(null == height)
        height = 500;
    parent.$.modalDialog({
    	maximizable:true,
        title : title,
        width : width,
        height : height,
        href : url+"?id="+id,
        buttons : [ {
            text : '返回',
            handler : function() {
                parent.$.modalDialog.handler.dialog('close');
            }
        } ]
    });
    //dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
}

//通用查询函数
function searchFun() {
    dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
    dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
}

//列表页面勾选复选框时检查列表是否选择了数据的通用方法
function checkSelect(){
    var checkedItems = $('#dataGrid').datagrid('getChecked');
    var ids = [];
    $.each(checkedItems, function(index, item){
        ids.push(item.id);
    });
    if( 0 == ids.length){
        parent.$.messager.alert('提示', '请选择数据', 'info');
        return ids;
    }
    //dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
    return ids;
}

//通用删除监测信息函数
function deleteJcFun(url) {
	var checkedItems = $('#dataGrid').datagrid('getChecked');
	var ids = [];
    var flag = false;
    $.each(checkedItems, function(index, item){
        if("已确认" == item.ifqr){
        	flag = true;
        }
        ids.push(item.id);
    });
    if( 0 == ids.length){
    	parent.$.messager.alert('提示', '请选择数据', 'info');
    	return;
    }
    if( flag ){
    	parent.$.messager.alert('提示', '不能删除已确认的数据', 'info');
    	return;
    }
    parent.$.messager.confirm('询问', '您是否要删除当前所选数据？', function(b) {
        if (b) {
            progressLoad();
            $.post(url, {
                ids : ids.toString()
            }, function(result) {
                if (result.success) {
                    parent.$.messager.show({title:'提示',msg:result.msg,showType:'show' });
                    dataGrid.datagrid('reload');
                }
                //dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
                progressClose();
            }, 'JSON');
        }
    });
}

//通用监测信息确认函数
function jcqy(url) {
    var checkedItems = $('#dataGrid').datagrid('getChecked');
    var ids = [];
    var flag = false;
    $.each(checkedItems, function(index, item){
        if("已确认" == item.ifqr){
        	flag = true;
        }
        ids.push(item.id);
    });
    if( 0 == ids.length){
        parent.$.messager.alert('提示', '请选择数据', 'info');
        return;
    }
    if( flag ){
    	parent.$.messager.alert('提示', '选择的信息确认状态为已确认，操作失败', 'info');
    	return;
    }
    
    parent.$.messager.confirm('询问', '您是否要确认当前信息？', function(b) {
        if (b) {
            progressLoad();
            $.post(url, {
            	ids : ids.toString()
            }, function(result) {
                if (result.success) {
                    parent.$.messager.show({title:'提示',msg:result.msg,showType:'show' });
                    dataGrid.datagrid('reload');
                    dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
                } else {
                	//parent.$.messager.show({title:'提示',msg:result.msg,showType:'show' });
                	parent.$.messager.alert('提示', result.msg, 'info');
                }
                //dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
                progressClose();
            }, 'JSON');
        }
    });
} 

//通用监测信息取消确认函数
function jcjy(url) {
    var checkedItems = $('#dataGrid').datagrid('getChecked');
    var ids = [];
    var flag = false;
    $.each(checkedItems, function(index, item){
        if("未确认" == item.ifqr){
        	flag = true;
        }
        ids.push(item.id);
    });
    if( 0 == ids.length){
        parent.$.messager.alert('提示', '请选择数据', 'info');
        return;
    }
    if( flag ){
    	parent.$.messager.alert('提示', '选择的信息确认状态为未确认，操作失败', 'info');
        return;
    }
    
    parent.$.messager.confirm('询问', '您是否要撤销确认当前信息？', function(b) {
        if (b) {
            progressLoad();
            $.post(url, {
            	ids : ids.toString()
            }, function(result) {
                if (result.success) {
                    parent.$.messager.show({title:'提示',msg:result.msg,showType:'show' });
                    dataGrid.datagrid('reload');
                    dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
                } else {
                	//parent.$.messager.show({title:'提示',msg:result.msg,showType:'show' });
                	parent.$.messager.alert('提示', result.msg, 'info');
                }
                //dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
                progressClose();
            }, 'JSON');
        }
    });
} 

//通用删除函数
function deleteFun(url) {
    var ids = checkSelect();
    if( 0 == ids.length){
        return;
    }
    parent.$.messager.confirm('询问', '您是否要删除当前所选数据？', function(b) {
        if (b) {
            progressLoad();
            $.post(url, {
                ids : ids.toString()
            }, function(result) {
                if (result.success) {
                    parent.$.messager.show({title:'提示',msg:result.msg,showType:'show' });
                    dataGrid.datagrid('reload');
                    dataGrid.datagrid('clearSelections');
                }
                //dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
                progressClose();
            }, 'JSON');
        }
    });
}

//明细信息（日常检查，报告，上报）通用修改函数
function editDetailFun() {
    var ids = checkSelect();
    if( 1 < ids.length){
        parent.$.messager.alert('提示', '请选择一条数据进行编辑', 'info');
        return;
    }
    if( 0 == ids.length){
    	return;
    }
    parent.$.messager.confirm('询问', '您是否要编辑当前所选数据？', function(b) {
        if (b) {
            //打开窗口div，赋上所选择的明细数据值
            var rowIndex = $('#dataGrid').datagrid('getRowIndex', ids[0]);//id是关键字值
            openWin('update',rowIndex);
            var data = $('#dataGrid').datagrid('getData').rows[rowIndex];
            //区间
            $('#qjbh').combobox('setValue',data.qujian);
            //线路
            $('#xlbh').combobox('setValue',data.xianlu);
            //点位
            $('#jcdbh').textbox('setValue',data.jcdNo);
            //本次高程
            $("#bcgc").textbox('setValue',data.bcgc);
            //备注
            $('#detailRremarks').combobox('setValue',data.remarks);
            //隐藏明细信息id  
            $('#detailId').val(data.id);
        }
    });
}

//通用编辑函数
function editFun(url,width,height) {
	if(null == width)
        width = 800;
    if(null == height)
        height = 400;
    var ids = checkSelect();
    if( 1 < ids.length){
        parent.$.messager.alert('提示', '请选择一条数据进行编辑', 'info');
        return;
    }
    if( 0 == ids.length){
    	return;
    }
    parent.$.messager.confirm('询问', '您是否要编辑当前所选数据？', function(b) {
        if (b) {
            parent.$.modalDialog({
                title : '编辑',
                width : width,
                height : height,
                maximizable:true,
                href : url+'?id=' + ids[0],
                buttons : [ {
                    text : '保存',
                    handler : function() {
                        parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                        var f = parent.$.modalDialog.handler.find('#addForm');
                        f.submit();
                        dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
                    }
                } ]
            });
        }
    });
}

//通用启用函数
function qy(url) {
    var checkedItems = $('#dataGrid').datagrid('getChecked');
    var ids = [];
    var flag = false;
    $.each(checkedItems, function(index, item){
        if("启用" == item.ifqy){
        	flag = true;
        }
        ids.push(item.id);
    });
    if( 0 == ids.length){
        parent.$.messager.alert('提示', '请选择数据', 'info');
        return;
    }
    if( flag ){
    	parent.$.messager.alert('提示', '不能启用已启用的数据', 'info');
    	return;
    }
    
    parent.$.messager.confirm('询问', '您是否要启用当前数据？', function(b) {
        if (b) {
            progressLoad();
            $.post(url, {
            	ids : ids.toString()
            }, function(result) {
                if (result.success) {
                    parent.$.messager.show({title:'提示',msg:result.msg,showType:'show' });
                    dataGrid.datagrid('reload');
                }
                dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
                progressClose();
            }, 'JSON');
        }
    });
} 

//通用禁用函数
function jy(url) {
    var checkedItems = $('#dataGrid').datagrid('getChecked');
    var ids = [];
    var flag = false;
    $.each(checkedItems, function(index, item){
        if("禁用" == item.ifqy){
        	flag = true;
        }
        ids.push(item.id);
    });
    if( 0 == ids.length){
        parent.$.messager.alert('提示', '请选择数据', 'info');
        return;
    }
    if( flag ){
    	parent.$.messager.alert('提示', '不能禁用已禁用的数据', 'info');
        return;
    }
    
    parent.$.messager.confirm('询问', '您是否要禁用当前数据？', function(b) {
        if (b) {
            progressLoad();
            $.post(url, {
            	ids : ids.toString()
            }, function(result) {
                if (result.success) {
                    parent.$.messager.show({title:'提示',msg:result.msg,showType:'show' });
                    dataGrid.datagrid('reload');
                }
                dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
                progressClose();
            }, 'JSON');
        }
    });
} 

//列表页面导出excel的通用函数,不选时全部导出
function expDDInfo(){
	var checkedItems = $('#dataGrid').datagrid('getChecked');
	var ids = [];
	$.each(checkedItems, function(index, item){
		ids.push(item.id);
	});
	if( 0 == ids.length){
		parent.$.messager.confirm('询问', '是否要导出全部数据？', function(b) {
			if (b) {
				$('#download')[0].submit();
			}
		});
	}else{
		parent.$.messager.confirm('询问', '是否要导出所选数据？', function(b) {
			if (b) {
				$('#ids').val(ids.toString());
				$('#download')[0].submit();
				dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
			}
		});
	}
	$('#ids').val('');
	//dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
}

//日常监测，监测报告，监测报告上报明细页面添加和编辑时重置按钮的通用函数
function resetDetailWin(){
	//把已有的值清掉
	$("#jcdbh").textbox('setValue','');
	$("#bcgc").textbox('setValue','');
    $('#xlbh').combobox('setValue','');
    $('#qjbh').combobox('setValue','');
    $('#detailRremarks').combobox('setValue','');
}

//日常监测，监测报告，监测报告上报明细页面添加和编辑时关闭按钮的通用函数
function closeDetailWin(){
	//先把已有的值清掉
	resetDetailWin();
	$('#win').window('close');
}
//**此方法用于调试js对象，adress文字描述；data为js对象***/
function showJson(adress,data){
	alert(adress+"\r\n"+JSON.stringify(data));
}
/**
 *计算本次变化量
 *bcgc 本次高程
 *scgc 上次高程
 */
function calculateBcbhl(bcgc,scgc){
	var bcbhl =0;
	if(bcgc==0){
		bcbhl = 0;
	}else{
		bcbhl = (bcgc*1-scgc*1)*1000;
	}
	return bcbhl.toFixed(1);
}
/**
*计算累计变化量
* bcgc 本次高程
*csgc 初次高程
*scgc 上次高程
*/
function caculateljBhl(bcgc,csgc,scgc){
    var ljbhl =0;
    if(bcgc==0){
    	ljbhl = (scgc*1-csgc*1)*1000
    }else{
    	ljbhl = (bcgc*1-csgc*1)*1000;
    }
	return ljbhl.toFixed(1);
}


/**
 * ajax方法，前台通过ajax调用后台方法，返回对象
 * @param  url 返回后台url地址
 * @param jsonParam json数组格式参数 例如：{id:id,name:name}
 * @param type  post or get
 * @param async true为异步，false为同步
 * @returns 返回后台结果对象
 */
function AjaxAction(url,jsonParam,type,async){
	if(null == url || '' == url){
		return;
	}
	if(null == type || '' == type){
		type='post';
	}
	if(null == async || '' == async){
		async=false;
	}
	var res = null;
	$.ajax({
		url: url,
		type: type,
		data: jsonParam,
		dataType : 'JSON',
		async:async,
		success : function(data) {
			res=  data;
		}
	});
	return res;
}



/**
 * 获取用户所在机构类型
 * @returns _currOrgzType 用户所在机构类型
 * 
 * 0机关单位
 * 1项目单位
 * 2部室单位
 */
function _getCurrOrgzType(){
	return _currOrgzType;
}

/**
 * 工程获取公共方法 
 * @param pid  项目编号
 * @param sid  区间编号  为空不加载
 * @param lid  线路编号  为空不加载
 * @returns
 */
function _getProDic(pid,sid,lid){
	$('#'+pid).combobox({
		url : basePath+'/project/line/getProDic',
		valueField : "id",
		textField : "proName",
		onLoadSuccess : function() {
			
		},
		onSelect:function(newValue,oldValue){
			$('#'+sid).combobox("clear");
	    	$('#'+sid).combobox('reload', basePath+"/project/line/getProSectionDic?proId="+newValue.id);
		}
	});

	if(sid){
		$('#'+sid).combobox({
			url : basePath+'/project/line/getProSectionDic',
			valueField : "id",
			textField : "sectionName",
			onLoadSuccess : function() {

			},
			onSelect:function(newValue,oldValue){
				$('#'+lid).combobox("clear");
		    	$('#'+lid).combobox('reload', basePath+"/project/line/getProLineDic?sectionId="+newValue.id);
			}
		});
		if(lid){
			$('#'+lid).combobox({
				url : basePath+'/project/line/getProLineDic',
				valueField : "id",
				textField : "lineName",
				onLoadSuccess : function() {
				}
			});
		}
	}
	
}


/**
 * 方法说明：区间下拉列表构建
 * @param qjshowId  业务界面中区间显示位置id
 * @param userFun 调用此方法用户自定义方法，若需要，则将自定义方法名传递，回调方法需要在业务方法中自己定义，其在单击行返回行数据rowData
 * @returns
 * @author:YangYj
 * @Time: 2016年12月21日 下午2:43:57
 */
function _createLineGried(qjshowId,userFun){
	$("#"+qjshowId).combogrid({
		url : basePath+'/project/line/dataGrid',
		panelWidth:550,
		idField : "id",
		textField : "lineName",
		fitColumns: false,  
        striped: true,  
        editable:true,  
        rownumbers:true,
        collapsible:false,  
        fit: true,
		sortName : 'proName,sectionName,lineName',
		sortOrder : 'desc',
		pagination: true,
		pageSize : 100,
		pageList : [ 100, 150, 200, 500 ],
		columns:[[
		          {field:'lineName',title:'线路名称',width:100},
		          {field:'sectionName',title:'区间名称',width:100},
		          {field:'proName',title:'工程名称',width:300},
		      ]],
      onClickRow: function (rowIndex, rowData) {
    	  try{
    		  if(null != userFun && '' !=userFun && 'undefined'!=userFun){
    			  eval(userFun+"(rowData)");
    		  }
    	  }catch(e){
    	  }
       }
	});
}

/**
 * 方法说明：列表删除操作
 * @param gridShowId 列表页面显示id
 * @returns 
 * @author:YangYj
 * @Time: 2016年12月23日 下午2:29:43
 */
function deleteGridRow(gridShowId){
	    var checkedItems = $('#'+gridShowId).datagrid('getChecked');
	    var ids = [];
	    $.each(checkedItems, function(index, item){
	        ids.push(item.id);
	    });
	    if( 0 == ids.length){
	        parent.$.messager.alert('提示', '请选择数据', 'info');
	        return ids;
	    }
	    parent.$.messager.confirm('询问', '确定删除所选数据行吗？', function(b) {
  	        if (b) {
  	        	for(var i=0;i<ids.length;i++)	{
  	        		var rowIndex = $('#'+gridShowId).datagrid('getRowIndex', ids[i]);//id是关键字值
  	        		$('#'+gridShowId).datagrid('deleteRow', rowIndex); 
  	        	}
  	        }
	   })
}

/**
 * 方法说明：数据列表取消已选操作
 * @param gridShowId 列表页面显示id
 * @returns
 * @author:YangYj
 * @Time: 2016年12月23日 下午2:40:35
 */
function clearGridSelections(gridShowId){
	$("#"+gridShowId).datagrid('clearSelections');
}


/**
*
* @requires jQuery,EasyUI
*
* 扩展validatebox
*/
$.extend($.fn.validatebox.defaults.rules, {
   idcard : {// 验证身份证
       validator : function(value) {
           return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value);
       },
       message : '身份证号码格式不正确'
   },
   minLength: {
       validator: function(value, param){
           return value.length >= param[0];
       },
       message: '请输入至少（2）个字符.'
   },
   length:{validator:function(value,param){
       var len=$.trim(value).length;
           return len>=param[0]&&len<=param[1];
       },
       message:"输入内容长度必须介于{0}和{1}之间."
   },
   phone : {// 验证电话号码
       validator : function(value) {
           return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
       },
       message : '格式不正确,请使用下面格式:010-88888888'
   },
   mobile : {// 验证手机号码
       validator : function(value) {
           return /^(13|15|18)\d{9}$/i.test(value);
       },
       message : '手机号码格式不正确'
   },
   intOrFloat : {// 验证整数或小数
       validator : function(value) {
           return /^\d+(\.\d+)?$/i.test(value);
       },
       message : '请输入数字，并确保格式正确'
   },
   currency : {// 验证货币
       validator : function(value) {
           return /^\d+(\.\d+)?$/i.test(value);
       },
       message : '货币格式不正确'
   },
   qq : {// 验证QQ,从10000开始
       validator : function(value) {
           return /^[1-9]\d{4,9}$/i.test(value);
       },
       message : 'QQ号码格式不正确'
   },
   integer : {// 验证整数
       validator : function(value) {
           return /^[+]?[1-9]+\d*$/i.test(value);
       },
       message : '请输入整数'
   },
   age : {// 验证年龄
       validator : function(value) {
           return /^(?:[1-9][0-9]?|1[01][0-9]|120)$/i.test(value);
       },
       message : '年龄必须是0到120之间的整数'
   },
   chinese : {// 验证中文
       validator : function(value) {
           return /^[\Α-\￥]+$/i.test(value);
       },
       message : '请输入中文'
   },
   english : {// 验证英语
       validator : function(value) {
           return /^[A-Za-z]+$/i.test(value);
       },
       message : '请输入英文'
   },
   unnormal : {// 验证是否包含空格和非法字符
       validator : function(value) {
           return /.+/i.test(value);
       },
       message : '输入值不能为空和包含其他非法字符'
   },
   username : {// 验证用户名
       validator : function(value) {
           return /^[a-zA-Z][a-zA-Z0-9_]{5,15}$/i.test(value);
       },
       message : '用户名不合法（字母开头，允许6-16字节，允许字母数字下划线）'
   },
   faxno : {// 验证传真
       validator : function(value) {
           return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
       },
       message : '传真号码不正确'
   },
   zip : {// 验证邮政编码
       validator : function(value) {
           return /^[0-9]\d{5}$/i.test(value);
       },
       message : '邮政编码格式不正确'
   },
   ip : {// 验证IP地址
       validator : function(value) {
           return /d+.d+.d+.d+/i.test(value);
       },
       message : 'IP地址格式不正确'
   },
   name : {// 验证姓名，可以是中文或英文
           validator : function(value) {
               return /^[\Α-\￥]+$/i.test(value)|/^\w+[\w\s]+\w+$/i.test(value);
           },
           message : '请输入姓名'
   },
   msn:{
           validator : function(value){
               return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value);
           },
           message : '请输入有效的msn账号(例：abc@hotnail(msn/live).com)'
   }
});

/**
 * 方法说明：构建性别下拉框
 * @param id 页面显示id
 * @param required 是否必填
 * @returns 
 * @author:YangYj
 * @Time: 2017年2月20日 下午4:21:42
 */
function createSexSelect(id,required){
	if(null == required || '' == required || required == undefined){
		required = false;
	}
	
	$("#"+id).combobox({
		required : false,
		valueField:'id',
		textField:'text',
		data: 
		[{
			id: '男',
			text: '男'
		 },{
			 id: '女',
			text: '女'
		 }]
	});
	/*点击文本框内，就显示下拉面板*/
	$("#"+id).combobox('textbox').click(function(){		
		$("#"+id).combobox('showPanel');
	});
}

/**
 * 方法说明：时间比较
 * @param startDate 开始时间
 * @param endDate 结束时间
 * @returns 若开始时间小于等于结束时间则返回true,否则返回false
 * @author:YangYj
 * @Time: 2017年2月21日 上午9:55:48
 */
function compareDate(startDate,endDate){
	if(null != startDate && '' != startDate && undefined != startDate
			&& null != endDate && '' != endDate && undefined != endDate){
		var d1 = new Date(startDate.replace(/\-/g, "\/")); 
		var d2 = new Date(endDate.replace(/\-/g, "\/")); 
		if(d1 <= d2)
		{ 
		   return true; 
		}else{
			return false;
		}
	}else{
		return false;
	}
}

/**
 * 方法说明：在指定的日期上增加指定月数
 * @param dateStr 日期字符串
 * @param month 增加月数
 * @param format  返回日期格式，若为空，则默认格式为'yyyy-MM-dd'
 * @returns
 * @author:YangYj
 * @Time: 2017年2月21日 上午10:01:29
 */
function addMonth(dateStr,month,format){
	if(null != dateStr && '' != dateStr && undefined != dateStr){
		var d = new Date(dateStr);
		d.setMonth(d.getMonth()+month);
		if(null == format || '' == format || undefined == format){
			format = 'yyyy-MM-dd';
		}
	  return d.format(format);
	}else{
		return null;
	}
	
}
/**
 * 日期格式化处理
 */
Date.prototype.format = function(format){
	var o = {
		"M+" : this.getMonth()+1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth()+3)/3), // quarter
		"S" : this.getMilliseconds() // millisecond
	}
	if(/(y+)/.test(format)) {
	  format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
	}
	for(var k in o) {
		if(new RegExp("("+ k +")").test(format)) {
		  format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
		}
	}
	return format;
} 