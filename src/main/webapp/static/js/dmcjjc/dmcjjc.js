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
        }  ]
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

