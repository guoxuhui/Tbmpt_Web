var dataGrid;
    $(function() {
    	
    	/**
    	 * 组织机构树
    	 */
    	orgzTree = $('#orgzTree').tree({
    	url : basePath+'/sgkshgl/jcdzbgl/tree',
    	parentField : 'pid',
    	lines : true,
    	onClick : function(node) {
    		dataGrid.datagrid('load', {
    	        proId: node.id
    	    });
    	}
    	});
    	
	   $('#proId').combobox({
            url : basePath + '/project/info/getProjects',
            valueField : "id",
            textField : "proName",
            onChange:function(){
            	 $('#secId').combobox({
                     url : basePath+'/project/line/getProSectionDic?proId='+$('#proId').combobox('getValue'),
                     valueField: "id",
                     textField: "sectionName",
                     onChange:function(){
                        var qjid = $('#secId').combobox('getValue');
                         $('#xlbh').combobox({
                             url : basePath+'/project/line/getProLineDic?sectionId='+qjid,
                             valueField: "id",
                             textField: "lineName"
                         });
                    }
                 });
            }
        });
	   
	   $('#jcType').combobox({
           url : basePath+'/dmcjjc/dmcjddinfo/getJcType',
           valueField: "ddName",
           textField: "ddName"
       });
	   
        dataGrid = $('#dataGrid').datagrid({
            url : basePath+'/sgkshgl/jcdzbgl/list',
            striped : true,
            rownumbers : true,
            pagination : true,
            singleSelect : false,
            idField : 'id',
            pageSize : 20,
            pageList : [ 10, 15, 20, 25, 50],
            frozenColumns : [ [ { 
            	field:'ck',checkbox:true 
            }, 
//            {
//                width : '100',
//                title : 'OBJECTID',
//                field : 'objectid',
//                sortable : false,
//                formatter : function(value, row, index) {
//                    var str = $.formatString('<a href="javascript:void(0)" onclick="showFun(\'{0}\',\'{1}\',\'{2}\',600,400);" >{3}</a>', row.id,basePath+'/dmcjjc/dmcjjcpoint/showPage','监测点详细信息',value);
//                    return str;
//                }
//            }, 
            {
                width : '220',
                title : '项目',
                field : 'proName',
                sortable : false
            },{
                width : '220',
                title : '区间',
                field : 'secName',
                sortable : false
            }]], 
            columns : [ [{
                width : '80',
                field : 'jcId',
                title : '点位编号',
                sortable : false,
            },{
                width : '220',
                field : 'jcType',
                title : '点位类型',
                sortable : false,
            },{
                width : '80',
                field : 'lc',
                title : '里程',
                sortable : false,
            } , {
                width : '80',
                field : 'hh',
                title : '环号',
                sortable : false,
            } , {
                width : '80',
                field : 'xx',
                align:'right',halign:'center',
                title : '报警下限',
                sortable : false,
            } , {
                width : '80',
                field : 'sx',
                title : '报警上限',
                sortable : false,
            }, {
                width : '100',
                field : 'x',
                title : 'X',
                sortable : false,
            } , {
                width : '100',
                field : 'y',
                title : 'Y',
                sortable : false,
            } , {
                width : '100',
                field : 'z',
                title : 'Z',
                sortable : false,
            } ,{
                width : '100',
                field : 'bz',
                title : '备注',
                sortable : false,
            } ] ],
            onLoadSuccess:function(data){
            },
//            toolbar : [ 
//					{text : '刷新',iconCls: 'icon-reload',
//					    handler: function(){
//					    	dataGrid.datagrid('reload');
//					    }
//					},                       
//		            {text : "添加",iconCls : 'icon-add',handler : addFun}, 
//		            '-',
//		            {text : "编辑",iconCls : 'icon-edit',handler : editFun}, 
//		            '-', 
//		            {text : "删除",iconCls : 'icon-del',handler : deleteFun},
//		            '-', 
//		            {text: '取消已选',iconCls: 'icon-undo',
//		    			handler: function(){
//		    				dataGrid.datagrid('clearSelections');
//		    			}
//		    		},
//		            '-',		            
//		    		{text : "导入Excel",iconCls : 'icon-add',handler : excelAddFun}
//		           ]
            toolbar:'#toolbar'
        });
    });
    

       function excelAddFun() {
           parent.$.modalDialog({
               title : '请选择导入文件+[请用下载的“Excel模版”]',
               width : 400,
               height : 230,           
               href : basePath+'/sgkshgl/jcdzbgl/importPage',//跳转到上传文件导入页面
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
            height : 400,
            href : basePath+'/sgkshgl/jcdzbgl/addPage',
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

    function editFun() {
    	var id;
    	var rows = dataGrid.datagrid('getSelections');
    	if (rows.length > 1) {
    		$.messager.alert('提示', '请选择单条数据进行编辑！', 'info');
    		return;
    	}
    	else if (rows.length == 1) {
    		if (rows[0]) {
    			id = rows[0].objectid;
    		}
    		else {
    			$.messager.alert('提示', '请选择数据后再编辑！', 'info');
    			return;
    		}
    	}
    	else {
    		$.messager.alert('提示', '请选择数据后再编辑！', 'info');
    		return;
    	}
    	if (rows[0]) {
    	parent.$.modalDialog({
            title : '编辑',
            width : 600,
            height : 400,
            href : basePath+'/sgkshgl/jcdzbgl/editPage?objectid='+id,
            buttons : [{
                text : '保存',
                handler : function() {
                	
                	parent.$.modalDialog.openner_dataGrid = dataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好 
                    var f = parent.$.modalDialog.handler.find('#editForm');
                    f.submit();
                }
            },{
                text : '关闭',
                handler : function() {
                	parent.$.modalDialog.handler.dialog('close');
                }
            } ]
        });
    }
    }
    
    
    function showFun() {
    	var id;
    	var rows = dataGrid.datagrid('getSelections');
    	if (rows.length > 1) {
    		$.messager.alert('提示', '请选择单条数据进行编辑！', 'info');
    		return;
    	}
    	else if (rows.length == 1) {
    		if (rows[0]) {
    			id = rows[0].objectid;
    		}
    		else {
    			$.messager.alert('提示', '请选择数据后再编辑！', 'info');
    			return;
    		}
    	}
    	else {
    		$.messager.alert('提示', '请选择数据后再编辑！', 'info');
    		return;
    	}
    	if (rows[0]) {
    	parent.$.modalDialog({
            title : '查看',
            width : 600,
            height : 400,
            href : basePath+'/sgkshgl/jcdzbgl/showPage?objectid='+id,
            buttons : [{
                text : '关闭',
                handler : function() {
                	parent.$.modalDialog.handler.dialog('close');
                }
            } ]
        });
    }
    }

    function deleteFun() {
    	var ids = new Array();
    	var rows = dataGrid.datagrid('getSelections');
    	if (rows.length == 0) {
    		$.messager.alert('提示', '请选择数据后再删除！', 'info');
    		return;
    	}
    	for(var i=0; i<rows.length; i++){
    		ids.push(rows[i].objectid);
    	}
        parent.$.messager.confirm('询问', '您是否要删除当前所选数据？', function(b) {
            if (b) {
                progressLoad();
                $.post(basePath+'/sgkshgl/jcdzbgl/del', {
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

    function searchFun() {
        dataGrid.datagrid('load', $.serializeObject($('#searchForm')));
        dataGrid.datagrid('unselectAll').datagrid('uncheckAll');
    }
    function cleanFun() {
        $('#proId').combobox('setValue','');
        $('#secId').combobox('setValue','');
        $('#jcType').combobox('setValue','');
        $("#jcId").textbox('setValue','')
        
        $('#searchForm')[0].reset();
        dataGrid.datagrid('load', {});
    }
    
   
    
    /**
     * 刷新
     */
    function reloadFun(){
    	dataGrid.datagrid('reload');
    }

    /**
     * 取消已选
     * @returns
     */
    function clearSelections(){
    	dataGrid.datagrid('clearSelections');
    }
    
    /**
     * 数据导出
     * @returns
     */
    function expXls(){
    	var url = basePath+"/sgkshgl/jcdzbgl/expXls";
    	
    	ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, "监测点坐标管理", dataGrid, false);
    }

    function expPdf(){
    	var url = basePath+"/sgkshgl/jcdzbgl/expPdf";
    	
    	ExportUtil.doExport(url, ExportUtil.TYPE_PDF, "监测点坐标管理", dataGrid, false);
    }