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
var xjdrd_dataGrid;
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
    xjdrd_dataGrid = $('#dataGrid_report_redu').datagrid({
        url : path+'/ReportByproject',
        striped : true,
        rownumbers : true,
        frozenColumns:[[
            {width: '100',title: '巡检人',field: 'JIANCHAPERSON',align:'center'},
            {width: '200',title: '巡检次数',field: 'JIANCHAPERSON_COUNT',
            },
        ]],
    });
    $('#searchForm_XunJianReDu').show();
    ////初始化查询条件
    createProjectSelect("projectid_query_id",true);

});

/** 表单查询 */
function searchForm_XunJianReDuFun() {
    var prjName = $("#projectid_query_id").combobox('getText');
    $('#proiectname_query').val(prjName);
    xjdrd_dataGrid.datagrid('load', $.serializeObject($('#searchForm_XunJianReDu')));
}

/*
 * 重置
 * */
function cleandXunJianReDuLFun() {
    $('#searchForm_XunJianReDu input').val('');
    xjdrd_dataGrid.datagrid('load', {});
}






