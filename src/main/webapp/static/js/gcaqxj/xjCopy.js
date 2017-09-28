/**
 *
 */
/** 系统模块同路径 */
var syspath = "gcaqxj";
/** 业务数据访问全路径 */
var path = basePath +"/"+ syspath +"/info/copy";
/** 菜单名称 */
var entityCnName = "巡检点引用";
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
var xjdyy_dataGrid;
function createProjectSelect(showId,required){
    $("#"+showId).combobox({
        required : required,
        valueField:'id',
        textField:'proName',
        url:basePath +"/project/info/getProlist",
        onChange: function (newVal,oldVal) {
            var prjName = $("#projectid_query_id").combobox('getText');
            var prjId =   $("#projectid_query_id").combobox('getValue');
            $("#sourceProjectId").val(prjId);
            $("#sourceProjectName").val(prjName);
            /**
             * 巡检分类树
             */
            orgzTree = $('#xunjianFlTree').tree({
                url : basePath+'/gcaqxj/xjdfl/getFl?proid='+ newVal,
                parentField : 'PARENTID',
                lines : true,
                onClick : function(node) {
                    //dataGrid.datagrid('load', {
                    //    projectid:newVal,
                    //    typeName:node.text,
                    //});
                        dataGrid.datagrid({
                            url: path + '/dataGrid',
                            queryParams: {
                                projectid:newVal,
                                typeName:node.text,
                            }});
                },
                onLoadSuccess:function(node,data){
                    //先 关闭所有节点
                    $("#xunjianFlTree").tree("collapseAll");
                    //默认展开前三级
                    //展开 第一级 节点
                    $("#orgzTree li:eq(0)").find("div").addClass("tree-node-selected");   //通过添加样式class，设置第一个节点高亮（选中节点）
                    var n = $("#xunjianFlTree").tree("getSelected");   //获取（选中节点）的值
                    if(n!=null){
                        $('#xunjianFlTree').tree('expand',n.target);   //展开指定的节点
                        $("#orgzTree li:eq(0)").find("div").removeClass("tree-node-selected"); //通过删除样式class，删除该节点高亮（选中）样式，准备展开下一个节点
                        //展开 第二级 节点
                        $("#orgzTree li:eq(1)").find("div").addClass("tree-node-selected");
                        var m = $("#xunjianFlTree").tree("getSelected");
                        if(m!=null){
                            $('#xunjianFlTree').tree('expand',m.target);
                            $("#orgzTree li:eq(1)").find("div").removeClass("tree-node-selected");
                            //展开 第三级 节点
                            $("#orgzTree li:eq(2)").find("div").addClass("tree-node-selected");
                            var b = $("#xunjianFlTree").tree("getSelected");
                            if(b!=null){
                                $('#xunjianFlTree').tree('expand',b.target);
                                $("#orgzTree li:eq(2)").find("div").removeClass("tree-node-selected");
                            }
                        }
                    }
                }
            });
        }
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
$(function() {
    /** 创建数据表格	url: path + '/dataGrid', */
    dataGrid = $('#dataGrid').datagrid({
        striped: true,
        rownumbers: true,
        singleSelect: false,
        checkOnSelect: true,
        selectOnCheck: true,
        pagination: true,
        idField: 'id',
        sortName: 'id',
        sortOrder: 'desc',
        pageSize: 15,
        pageList: [10, 15, 20, 50],
        frozenColumns: [[
            {width: '100',title: 'id',field: 'id',align:'center', checkbox:true},
            {width: '240',title: '工程名称',field: 'projectname',
                formatter: function(value,row,index){
                    return DataGridFormatter.tipsFormatter(value,50);
                }
            },
            {width: '80',title: '检查点名称',field:'mingcheng' 	},
            
            {
                width: '100', title: '所在位置', field:  'xujiandianweizhi'
            }            
            ]],
    	    columns : [ [
            {
                width: '80', title: '需检查频次', field:  'jianchapc'
            },
            {
                width: '60',title: '责任人',field: 'zerenrmc'
            },
            {
                width: '60',title: '监督人',field: 'jiandurmc'
            },
            {
                width: '60',title: '责任人id',field: 'zerenrid',hidden:'true',
            },
            {
                width: '60',title: '监督人id',field: 'jiandurid',hidden:'true',
            },
            {
                width: '30',title: '顺序号',field: 'xuhao'
            },
            {
                width: '80',title: '检查点分类',field: 'typeName'
            },
            {
                width: '80',title: '检查点分类id',field: 'typeId',hidden:'true',
            },

            {width: '180',title: '备注',field: 'beizhu',hidden:'true',
                formatter: function(value,row,index){
                    return DataGridFormatter.tipsFormatter(value,30);
                }
            },

            {
                width: '100',title: '工程id',field: 'proiectId',hidden:'true'
            },
        ]],
        toolbar : '#toolbar',
        onLoadSuccess : function(data) {
            hiddenButtonBase("dataGrid",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onCheck : function(){
            hiddenButtonBase("dataGrid",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onUncheck : function(){
            hiddenButtonBase("dataGrid",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onCheckAll : function(){
            hiddenButtonBase("dataGrid",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onUncheckAll : function(){
            hiddenButtonBase("dataGrid",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        },
        onClickRow:function(){
            hiddenButtonBase("dataGrid",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
        }
    });
    $('#searchForm').show();
    ////初始化查询条件
    createProjectSelect("projectid_query_id",true);

});

/** 表单查询 */
function searchFun() {

    var prjName = $("#projectid_query_id").combobox('getText');
    var prjId = $("#projectid_query_id").combobox('getValue');
    var mingcheng = $("#search_name").val();
    $('#proiectname_query').val(prjName);
    dataGrid.datagrid({
        url: path + '/dataGrid',
        queryParams:{
            projectid:prjId,
            mingcheng:mingcheng,
        },
        onLoadSuccess : function(data) {
           if(data.success==false){
               alert(data.msg);
           }
        },
        onLoadError: function(data) {
            if(data.success==false){
                alert(data.msg);
            }
        }
    });


    //dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
}

/*
 * 重置
 * */
function cleanFun() {
    $('#searchForm input').val('');
    dataGrid.datagrid('load', {});
}

/**
 * 打开复制当前工程对话框
 */
function copyProFun(){
    $("#srcProName").text($("#sourceProjectName").val());
    $("#srcProName2").text($("#sourceProjectName").val());
   if($("#sourceProjectName").val()==null||$("#sourceProjectName").val()==''){
       $.messager.alert('提示', '请选择数据后再复制！', 'info');
       return;
   }else{
       $("#copyPro_Dialog").dialog('open');
   }


}
/**
 * 执行复制当前工程操作
 * @constructor
 */
function CopyProAjax(){
    var srcProName = $("#sourceProjectName").val();
    params={proName:srcProName};
    $.ajax({
        type: "post",
        url: basePath+"/gcaqxj/info/copy/copyPro",
        dataType: "json",
        data: params,
        success: function(data){
            alert(data.msg);
        }
    });
    $('#copyPro_Dialog').dialog('close')

}


function CopyXunJianDianFun(){
    createTypeSelect("typeName_query_id",true);
    var ids = new Array();
    var rows = dataGrid.datagrid('getSelections');
    for(var i=0; i<rows.length; i++){
        ids.push(rows[i].id);
    }
    if(ids.length==0){
        $.messager.alert('提示', '请选择数据后再复制！', 'info');
        return;
    }else{
        $('#copyXunJianDian_Dialog').dialog('open');
    }


}
function CopyXunJianDianAjax(){
    var typeName = $("#typeName_query_id").combobox('getText');
    var  typeId  = $("#typeName_query_id").combobox('getValue');
    $('#copyXunJianDian_Dialog').dialog('close')
    var ids = new Array();
    var rows = dataGrid.datagrid('getSelections');
    for(var i=0; i<rows.length; i++){
        ids.push(rows[i].id);
    }
    $.post(basePath+"/gcaqxj/info/copy/copyXunjiandian",
        {
            ids : ids.join(","),
            typeName:typeName,
            typeId:typeId
    }, function(result) {
        if (result.success) {
            $.messager.show({title:'提示',msg:'复制成功!',showType:'show' });
            dataGrid.datagrid('clearSelections');
        }else{
            $.messager.show({title:'提示',msg:'复制出现错误!',showType:'show' });
            dataGrid.datagrid('clearSelections');
        }
        progressClose();
    }, 'JSON');
}
function closeDialogFun(){
    dataGrid.datagrid('clearSelections');
    $('#copyXunJianDian_Dialog').dialog('close')
}




