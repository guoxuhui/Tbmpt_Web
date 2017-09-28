/**
 *
 */
/** 系统模块同路径 */
var syspath = "gcaqxj";
/** 业务数据访问全路径 */
var path = basePath +"/"+ syspath +"/tjcx";
/** 菜单名称 */
var entityCnName = "统计查询";
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
$(function() {

    /** 创建数据表格	 */
    xjdgl_dataGrid = $('#dataGrid_report_xunjiandian').datagrid({
        url : path+'/ReportByXunjiandian',
        striped : true,
        rownumbers : true,

        frozenColumns:[[
            {width: '100',title: '巡检点',field: 'MINGCHENG',align:'center'},
            {width: '200',title: '不合格记录(次)',field: 'MINGCHENG_COUNT',
            },

        ]],
    });
    $('#searchForm_XunJianDian').show();
    //初始化查询条件
    createProjectSelect("projectid_query_id",true);

});

/** 表单查询 */
function searchXunJianDianFun() {
    var prjName = $("#projectid_query_id").combobox('getText');
    $('#proiectname_query').val(prjName);
    xjdgl_dataGrid.datagrid('load', $.serializeObject($('#searchForm_XunJianDian')));
}

/*
 * 重置
 * */
function cleandXunJianDianFun() {
    $('#searchForm_XunJianDian input').val('');
    xjdgl_dataGrid.datagrid('load', {});
}







