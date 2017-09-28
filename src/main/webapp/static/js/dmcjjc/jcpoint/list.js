var dataGrid;
    $(function() {
    	
	   $('#gcbh').combobox({
            url : basePath + '/project/info/getProjects',
            valueField : "id",
            textField : "proName",
            onChange:function(){
            	 $('#qjbh').combobox({
                     url : basePath+'/project/line/getProSectionDic?proId='+$('#gcbh').combobox('getValue'),
                     valueField: "id",
                     textField: "sectionName",
                     onChange:function(){
                        var qjid = $('#qjbh').combobox('getValue');
                         $('#xlbh').combobox({
                             url : basePath+'/project/line/getProLineDic?sectionId='+qjid,
                             valueField: "id",
                             textField: "lineName"
                         });
                    }
                 });
            }
        });
	   
	   $('#sjType').combobox({
           url : basePath+'/dmcjjc/dmcjddinfo/getJcType',
           valueField: "ddName",
           textField: "ddName"
       });
	   
	   $('#sjTimeType').combobox({
           url : basePath+'/dmcjjc/dmcjddinfo/getSjTime',
           valueField: "ddName",
           textField: "ddName"
       });
	   
        dataGrid = $('#dataGrid').datagrid({
            url : basePath+'/dmcjjc/dmcjjcpoint/list',
            striped : true,
            rownumbers : true,
            pagination : true,
            singleSelect : false,
            idField : 'id',
    		sortName : 'id',
            pageSize : 20,
            pageList : [ 10, 15, 20, 25, 50],
            frozenColumns : [ [ { 
            	field:'ck',checkbox:true 
            }, {
                width : '100',
                title : '点位编号',
                field : 'jcdbh',
                sortable : true,
                formatter : function(value, row, index) {
                    var str = $.formatString('<a href="javascript:void(0)" onclick="showFun(\'{0}\',\'{1}\',\'{2}\',600,400);" >{3}</a>', row.id,basePath+'/dmcjjc/dmcjjcpoint/showPage','监测点详细信息',value);
                    return str;
                }
            } , {
                width : '120',
                title : '区间',
                field : 'qujianName',
                sortable : true
            }, {
                width : '120',
                title : '线路',
                field : 'xianluName',
                sortable : true
            } , {
                width : '220',
                field : 'sjType',
                title : '点位类型',
                sortable : true
            }]], 
            columns : [ [{
                width : '250',
                field : 'weizhi',
                title : '环号/位置',
                sortable : true,
            } , {
                width : '250',
                field : 'licheng',
                title : '里程',
                sortable : false,
            } , {
                width : '80',
                field : 'csgc',
                align:'right',halign:'center',
                title : '初始高程(米)',
                sortable : false,
            } , {
                width : '80',
                field : 'sjTimeType',
                title : '设计时间',
                sortable : true,
            } , {
                width : '100',
                field : 'maxControl',
                title : '控制值上限',
                sortable : false,
            } , {
                width : '100',
                field : 'minControl',
                title : '控制值下限',
                sortable : false,
            } , {
                width : '100',
                field : 'maxAlarm',
                title : '报警值上限',
                sortable : false,
            } , {
                width : '100',
                field : 'minAlarm',
                title : '报警值下限',
                sortable : false,
            }  ] ],
            onLoadSuccess:function(data){
            },
            toolbar : [ 
					{text : '刷新',iconCls: 'icon-reload',
					    handler: function(){
					    	dataGrid.datagrid('reload');
					    }
					},                       
		            {text : "添加",iconCls : 'icon-add',handler : addFun}, 
		            '-',
		            {text : "编辑",iconCls : 'icon-edit',handler : editFunNew}, 
		            '-', 
		            {text : "删除",iconCls : 'icon-del',handler : deleteFunNew},
		            '-', 
		            {text: '取消已选',iconCls: 'icon-undo',
		    			handler: function(){
		    				dataGrid.datagrid('clearSelections');
		    			}
		    		},
		            '-',
		            //{text : "导出Excel",iconCls : 'icon-page_excel',handler : expDDInfo}
		            {text : "导出Excel",iconCls : 'icon-page_excel',
		    			handler: function(){
							var url = basePath+"/dmcjjc/dmcjjcpoint/expXls";
		    				ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, "地面沉降监测点信息", dataGrid, false);
		    			}
		            }, 
		            '-',
		    		{text : "导入Excel",iconCls : 'icon-add',handler : excelAddFun}
		           ]
        });
    });
    
    
    //wpg:点击导入Excel按钮  打开 “选择导入文件” 页面
   function excelAddFun() {
       parent.$.modalDialog({
           title : '请选择导入文件+[请用下载的“Excel模版”]',
           width : 420,
           height : 250,           
           href : basePath+'/dmcjjc/dmcjjcpoint/importPage',//跳转到上传文件导入页面
           buttons : [ {
               text : '确定',
               handler : function() {
                   parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                   
                   var f = parent.$.modalDialog.handler.find('#addForm');
                   f.submit();
               }
           } ]
       });
   }
    
      
       
    function addFun() {
        parent.$.modalDialog({
            title : '添加',
            width : 600,
            height : 450,
            href : basePath+'/dmcjjc/dmcjjcpoint/addPage',
            buttons : [ {
                text : '保存',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#addForm');
                    f.submit();
                }
            } ]
        });
    }

    function editFunNew(id) {
    	editFun(basePath+'/dmcjjc/dmcjjcpoint/editPage',620,450);
    }

    function deleteFunNew(id) {
    	deleteFun(basePath+'/dmcjjc/dmcjjcpoint/del');
    }

    function searchFun() {
        dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
        dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
    }
    function cleanFun() {
        $('#gcbh').combobox('setValue','');
        $('#xlbh').combobox('setValue','');
        $('#qjbh').combobox('setValue','');
        $('#sjTimeType').combobox('setValue','');
        $('#sjType').combobox('setValue','');
        $("#jcdbh").textbox('setValue','')
        
        $('#searchForm')[0].reset();
        dataGrid.datagrid('load', {});
    }