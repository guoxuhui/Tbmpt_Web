/**
 *
 */
/** 系统模块同路径 */
var syspath = "gcaqxj";
/** 业务数据访问全路径 */
var path = basePath +"/"+ syspath +"/push";
/** 菜单名称 */
var entityCnName = "推送给我的";
/**
 * 模块名称
 */
var moduleName = "工程项目安全巡检系统";
/**若无一条勾选数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayZero = [];
/**若勾选大于一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayMoreOneItem =[];
var hiddenClumArrayOnlyOneItem = [];
/** 列表对象 */
/** 名称字段 */
var nameField = "typeName";
/** 名称字段 */
var yanshour = "yanshour";
/** 列表对象 */
var xjdgl_dataGrid;
function createProjectSelect(showId,required){
    $("#"+showId).combobox({
        required : required,
        valueField:'id',
        textField:'proName',
        url:basePath +"/project/info/getProjects"
    });
    /*点击文本框内，就显示下拉面板*/
    $("#"+showId).combobox('textbox').click(function(){
        $("#"+showId).combobox('showPanel');
    });
}
/**
 * 创建巡检分类下拉列表
 * @param showId
 * @param required
 */
function createTypeSelect(showId,required){
    $("#"+showId).combobox({
        required : required,
        valueField:'id',
        textField:'typeName',
        url:basePath +"/gcaqxj/xjdfl/getType"
    });
    /*点击文本框内，就显示下拉面板*/
    $("#"+showId).combobox('textbox').click(function(){
        $("#"+showId).combobox('showPanel');
    });
}

/**
 * 创建巡检分类下拉列表
 * @param showId
 * @param required
 */
function createUserSelect(showId,required,orgzId){
    $("#"+showId).combobox({
        required : required,
        valueField:'id',
        textField:'name',
        url:basePath +"/sys/user/userList" ,
        onBeforeLoad: function(param){
            param.orgzId = orgzId;
        }
    });
    /*点击文本框内，就显示下拉面板*/
    $("#"+showId).combobox('textbox').click(function(){
        $("#"+showId).combobox('showPanel');
    });
}

$(function() {

    /** 创建数据表格	 */
    xjdgl_dataGrid = $('#dataGrid_XJTS').datagrid({
        url : path+'/dataGrid',
        striped : true,
        rownumbers : true,
        singleSelect : false,
        checkOnSelect : true,
        selectOnCheck : true,
        pagination : true,
        idField : 'id',
        pageSize : 15,
        pageList : [ 10, 15, 20, 50 ],
        frozenColumns:[[
            {width: '100',title: 'id',field: 'id',align:'center', checkbox:true},
            {width: '100',title: '查看状态',field: 'isView',
                formatter: function(value,row,index){
                    if(value==1){
                        return '已看';
                    }else{
                        return '未看';
                    }
                }
            },
            {width: '240',title: '工程名称',field: 'projectname',
                formatter: function(value,row,index){
                    return DataGridFormatter.tipsFormatter(value,50);
                }
            },
            {width: '80',title: '巡检点',field:'mingcheng' 	}
            
            ]],
    	    columns : [ [
            {
                width: '100', title: '巡检点所在位置', field:  'itemadress'
            },
            {
                width: '80', title: '巡检人', field:  'jianchaperson'
            },
            {
                width: '100',title: '巡检时间',field: 'jianchatime'
            },
            {
                width: '60',title: '巡检结果',field: 'jianchajg',
                formatter: function(value,row,index){
                    if(value==1){
                        return '合格';
                    }else{
                        return '不合格';
                    }
                },
            },
            {
                width: '60',title: '需巡检频次',field: 'jianchapc',
            },
            {
                width: '80',title: '责任人',field: 'zerenrmc',
            },
            {
                width: '80',title: '监督人',field: 'jiandurmc'
            },
            {
                width: '80',title: '巡检点分类',field: 'itemtype'
            },
            {
                width: '80',title: '检查点分类id',field: 'typeId',hidden:'true',
            },
            {
                width: '100',title: '备注',field: 'jieguoms',hidden:'true',
            },
        ]],

        toolbar : '#toolbar',
        onLoadSuccess : function(data) {
            hiddenButtonBase("dataGrid_XJTS",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onCheck : function(){
            hiddenButtonBase("dataGrid_XJTS",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onUncheck : function(){
            hiddenButtonBase("dataGrid_XJTS",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onCheckAll : function(){
            hiddenButtonBase("dataGrid_XJTS",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onUncheckAll : function(){
            hiddenButtonBase("dataGrid_XJTS",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onClickRow:function(){
            hiddenButtonBase("dataGrid_XJTS",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        }
    });
    $('#searchForm_XJTS').show();
    //初始化查询条件
    createProjectSelect("projectid_query_id",true);

});

/** 表单查询 */
function searchForm_XJTSFun() {
    var prjName = $("#projectid_query_id").combobox('getText');
    $('#proiectname_query').val(prjName);
    xjdgl_dataGrid.datagrid('load', $.serializeObject($('#searchForm_XJTS')));
}

/*
 * 重置
 * */
function cleandXJTSFun() {
    $('#searchForm_XJTS input').val('');
    xjdgl_dataGrid.datagrid('load', {});
}
/*
 * 刷新
 * */
function reloadXJTSFun(){
    xjdgl_dataGrid.datagrid('reload');
}

/**
 * 查看
 */
function viewXunJianDianJLFun(){
    var id;
    var rows = xjdgl_dataGrid.datagrid('getSelections');
    if (rows.length > 1) {
        $.messager.alert('提示', '请选择单条数据进行查看！', 'info');
        return;
    } else if (rows.length == 1) {
        if (rows[0]) {
            id = rows[0].id;
        } else {
            $.messager.alert('提示', '请选择数据后再查看！', 'info');
            return;
        }
    } else {
        $.messager.alert('提示', '请选择数据后再查看！', 'info');
        return;
    }
    if (rows[0]) {
        var  id = rows[0].id;
        if(rows[0]['isView']==0){
            //更新状态
            var url = path + '/update';
            $.post(url, {id:id}, function(data){
                if(data.success){
                    xjdgl_dataGrid.datagrid('reload');
                }else if(data.success == false){
                    alert(data.msg);
                    return ;
                }
            });
        }
        //viewDialog_XunJianNeiRongJL
        $("#viewDialog_XunJianNeiRongJL").dialog('open').dialog('setTitle', '查看巡检内容');
        //工程名称
        $("#view_JLprojectName").text( rows[0]['projectname']);
        //检查点名称
        $("#view_JLMingCheng").text( rows[0]['mingcheng']);
        //检查点分类
        $("#view_JLTypeName").text( rows[0]['itemtype']);
        //检查点所在位置
        $("#view_JLXuJianDianWeiZhi").text( rows[0]['itemadress'])
        //责任人
        $("#view_JLZeRenMC").text( rows[0]['zerenrmc']);
        //监督人
        $("#view_JLJianDuRenMC").text( rows[0]['jiandurmc']);
        //检查频次view_JLJianChaPC
        $("#view_JLJianChaPC").text(rows[0]['jianchapc']);
        //备注view_JLBeiZhu
        $("#view_JLBeiZhu").text(rows[0]['jieguoms']);
        // $("#dg_XJNR").
        $('#dg_XJNR').datagrid({
            url:'/gcaqxj/xjjl/detail/list?id='+ id,
            rownumbers : true,
            frozenColumns:[[
                {field:'contentxunjian',title:'巡检内容',width:400},
                {field:'jianchajg',title:'是否合格',width:100,
                    formatter: function(value,row,index){
                        if(value==1){
                            return '合格';
                        }else{
                            return '不合格';
                        }
                    }},
            ]],
        });



    }
}
/**
 * 关闭查看对话框
 */
function closeDialog(){
    $('#viewDialog_XunJianNeiRongJL').dialog('close');
    xjdgl_dataGrid.datagrid('reload');
}




