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
    $('#searchForm_XunJianByDay').show();
    ////初始化查询条件
    createProjectSelect("projectid_query_id",true);
});
/** 表单查询 */
function searchForm_XunJianByDayFun() {
    var prjName = $("#projectid_query_id").combobox('getText');
    $('#proiectname_query').val(prjName);
    var startDate= $("#startDate").val();
    var endDate= $("#endDate").val();
    $.post(path+'/ReportByDay', {
        projectname : prjName,
        startDate:startDate,
        endDate:endDate
    }, function(result) {
        if (result.success) {
            var xAxisData = new Array();
            var seriesData = new Array();

            for(j = 0,len=result.obj.length; j < len; j++) {
                xAxisData[j]=result.obj[j].DAYTIME;
                seriesData[j]=result.obj[j].COUNTBYXUNJIAN;
            }
            var myChart = echarts.init(document.getElementById('main'));
            var option = {
                title:{
                    text:prjName+'项目部日巡检次数统计',
                    x:'center',
                    y:'top',
                    textAlign:'left'
                },
                tooltip: {
                    show: true
                },
                legend: {
                    data:['次数']
                },
                xAxis : [
                    {
                        type : 'category',
                        data : xAxisData
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        "name":"巡检次数",
                        "type":"bar",
                        "data": seriesData,
                        itemStyle:{
                            normal: {
                                //设置柱状图颜色
                                color: '#7EC0EE',
                                },
                        }
                    }
                ]
            };

            // 为echarts对象加载数据
            myChart.setOption(option);
            var title = {
                text:"我是新标题",
                style:{
                    color:"#ff0000"
                }
            };
            myChart.ser

        }
        progressClose();
    }, 'JSON');
}

/*
 * 重置
 * */
function cleandXunJianByDayFun() {
    $('#searchForm_XunJianByDay input').val('');
}






