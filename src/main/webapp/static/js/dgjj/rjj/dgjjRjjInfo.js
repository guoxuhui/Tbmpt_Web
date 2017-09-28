/** /** 【日掘进信息管理】盾构掘进参数管理系统 基础模块
 * 创建时间：2017-01-09
   */
/** 系统模块同路径 */
var syspath = "dgjj";
/** 子模块路径 */
var module = "rjj";
/** 菜单名称 */
var entityCnName = "日掘进信息管理";
/** 名称字段 */
var nameField = "";
/** 业务数据访问全路径 */
var path = basePath +"/"+ syspath +"/"+ module+"/dgjjRjjInfo";

//废码
var xyfysPath=basePath +"";

/** 创建数据表格  1 用于主页 	 */
/**若无一条勾选数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayZero = ["dataGrid_dgjjRjjInfoParent_button_seleClean","dataGrid_dgjjRjjInfo_button_ImpExcel"];
/**若只勾选一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayOnlyOneItem = [];
/**若勾选大于一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayMoreOneItem =["dataGrid_dgjjRjjInfo_button_ImpExcel"];

/** 创建数据表格  2  用于选择 班组	 */
/**若无一条勾选数据，需屏蔽按钮ID的数组集合*/                                                                                                                                                                                                  
var hiddenClumArrayZeroSelectBanZu = ["dataGrid_SelectBanZu_button_seleClean","uploadFile_dgjjRjjInfo"];
/**若只勾选一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayOnlyOneItemSelectBanZu = [];
/**若勾选大于一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayMoreOneItemSelectBanZu =["uploadFile_dgjjRjjInfo"];

/** 创建数据表格 3  用于Excle解析日掘进信息 子页面	 */
/**若无一条勾选数据，需屏蔽按钮ID的数组集合*/                                                                                                                                                                                                 
var hiddenClumArrayZeroExcleWay = ["datagrid_dgjjRjjInfo_delExcleWay","dataGrid_dgjjRjjInfo_button_ExcleWayseleClean"];
/**若只勾选一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayOnlyOneItemExcleWay = [];
/**若勾选大于一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayMoreOneItemExcleWay =[];

/** 创建数据表格 4  用于查看“日掘进明细”页面	 */
/**若无一条勾选数据，需屏蔽按钮ID的数组集合*/                                                                                                                                                                                 
var hiddenClumArrayZeroShow= ["datagrid_dgjjRjjInfo_edit","datagrid_dgjjRjjInfo_book_previous","datagrid_dgjjRjjInfo_del","dataGrid_dgjjRjjInfo_button_seleClean"];
/**若只勾选一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayOnlyOneItemShow = [];
/**若勾选大于一条数据，需屏蔽按钮ID的数组集合*/
var hiddenClumArrayMoreOneItemShow=["datagrid_dgjjRjjInfo_edit","datagrid_dgjjRjjInfo_book_previous"];

/** 项目列表对象 */
var dataGrid_gpztclSjzxInfoParent;
/** 班组列表对象 */
var dataGrid_SelectBanZu;
/** Excle 方式 日掘进参数明细列表对象 */
var dataGrid_excleWay_dgjjRjjInfo;
/** 日掘进参数明细列表对象 */
var dataGrid_dgjjRjjInfo;
 


/** 用于判断 列表行 双击 后的状态，  */ 
var editRow = undefined; 

var editSghh ="非空";
//声明集合，用于表单行被编辑后，保存编辑后的数据；
var editRowList = new Array();


//工程名称 、区间名称、路线名称、线路Id、班组名称、班组Id；
var proName;
var sectionName;
var lineName;
var xlBh;
var bzname;
var BId;
var currentRowIndex;
$(function() {
	 
	//var editRow = undefined;
	/**  【日掘进信息管理】创建 线路信息数据表格 1	 */
	dataGrid_dgjjRjjInfoParent = $('#dataGrid_dgjjRjjInfoParent').datagrid({
		url : path+'/dataGridParent',
		striped : true,
		rownumbers : true,
		singleSelect : false,
		checkOnSelect : true,
		selectOnCheck : true,
		fitColumn:true, //显示滚动条；
		pagination : true,
		idField : 'id',
		pageSize : 15,
		pageList : [ 10, 15, 20, 50 ],
		columns :[[
			{field : 'ck',checkbox : true},
			{width : '420',title : '所属项目',field : 'proName',sortable : true},
			{width : '150',title : '区间名称',field : 'sectionName',sortable : true}, 
			{width : '150',title : '线路名称',field : 'lineName',sortable : true},
			{width : '150',title : '日掘进参数',field : 'dgjjRjjInfo',sortable : true, 
				formatter: function(value,row,index){
					                    
					return '<a href="#" onclick="showDgjjRjjInfoFun(\''+ row.id +'\', \'' + index + '\')">日掘进明细</a>';
				}
			}
		     ]],
			onLoadSuccess : function(data) {
			 
				hiddenButtonBase("dataGrid_dgjjRjjInfoParent",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
			},
	        onCheck : function(){
	        	//alert("进入了onCheck事2件！！！！！！//勾选一行数据时发生该事件！");
	        	hiddenButtonBase("dataGrid_dgjjRjjInfoParent",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
	        },
	        onUncheck : function(){
	        	//alert("进入了onUncheck 事3件！！！！！！//去掉一行数据勾选时发生该事件！");
	        	hiddenButtonBase("dataGrid_dgjjRjjInfoParent",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
	        },
	        onCheckAll : function(){
	        	//alert("进入了onCheckAll 事4件！！！！！！//勾选所有行时发生该事件！");
	        	hiddenButtonBase("dataGrid_dgjjRjjInfoParent",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
	        },
	        onUncheckAll : function(){
	        	//alert("进入了onUncheckAll 事5件！！！！！！//去掉勾选所有行时发生该事件！");
	        	hiddenButtonBase("dataGrid_dgjjRjjInfoParent",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
	        },
	        onClickRow:function(){
	        	//alert("进入了onClickRow 事6件！！！！！！//点击表单行时发生该事件！");
	        	hiddenButtonBase("dataGrid_dgjjRjjInfoParent",hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem);
	        },
		toolbar : '#toolbarParent' 
	});
	

 
	/** excleWay批量导入【日掘进参数信息】 选择班组 创建班组信息数据表格 2	 */
	dataGrid_SelectBanZu = $('#dataGrid_SelectBanZu').datagrid({
		url : path+'/selectBanZuBy',
		striped : true,
		rownumbers : true,
		singleSelect : false,
		checkOnSelect : true,
		selectOnCheck : true,
		pagination : false,
		idField : 'id',
		pageSize : 15,
		pageList : [ 500, 1000 ],
		columns:[[
			{field:'id',align:'center',title:'ID',width:40, checkbox:true},
			{width: '100',title: '班组名称',field: 'bzname',align:'center'},
			{width: '140',title: '上班时间',field: 'startTime',align:'center'},
			{width: '140',title: '下班时间',field: 'endTime',align:'center'}
			]],
			onLoadSuccess : function(data) {
				 
				hiddenButtonBase("dataGrid_SelectBanZu",hiddenClumArrayZeroSelectBanZu,hiddenClumArrayOnlyOneItemSelectBanZu,hiddenClumArrayMoreOneItemSelectBanZu);
			},
	        onCheck : function(){
	        	hiddenButtonBase("dataGrid_SelectBanZu",hiddenClumArrayZeroSelectBanZu,hiddenClumArrayOnlyOneItemSelectBanZu,hiddenClumArrayMoreOneItemSelectBanZu);
			},
	        onUncheck : function(){
	        	hiddenButtonBase("dataGrid_SelectBanZu",hiddenClumArrayZeroSelectBanZu,hiddenClumArrayOnlyOneItemSelectBanZu,hiddenClumArrayMoreOneItemSelectBanZu);
			},
	        onCheckAll : function(){
	        	hiddenButtonBase("dataGrid_SelectBanZu",hiddenClumArrayZeroSelectBanZu,hiddenClumArrayOnlyOneItemSelectBanZu,hiddenClumArrayMoreOneItemSelectBanZu);
			},
	        onUncheckAll : function(){
	        	hiddenButtonBase("dataGrid_SelectBanZu",hiddenClumArrayZeroSelectBanZu,hiddenClumArrayOnlyOneItemSelectBanZu,hiddenClumArrayMoreOneItemSelectBanZu);
			},
	        onClickRow:function(){
	        	hiddenButtonBase("dataGrid_SelectBanZu",hiddenClumArrayZeroSelectBanZu,hiddenClumArrayOnlyOneItemSelectBanZu,hiddenClumArrayMoreOneItemSelectBanZu);
			},
			toolbar : '#SelectBanZushowToolbar' 
	});
	

	
	
	/** excleWay批量导入【日掘进参数信息】创建数据表格 	 */

	dataGrid_excleWay_dgjjRjjInfo = $('#dataGrid_excleWay_dgjjRjjInfo').datagrid({
		//url : path+'/dataGrid',
		striped : true,
		rownumbers : true,
		singleSelect : false,
		pagination : false,
        idField : 'id',
		pageSize : 15,
		pageList : [ 10, 15, 20, 50 ],
		//该事件中的列 不生成滚动条；
		frozenColumns: [ [
			{field:'ck',checkbox:true },
			{width: '100',title: '施工环号',field: 'sghh',align:'center',editor:{type:'numberbox'}},
			{width: '120',title: '管片变换关键里程',field: 'gpbhgjlc',align:'center',editor:{type:'numberbox',options:{precision:6}}},
			{width: '100',title: '设计类型',field: 'sjlx',align:'center',editor:{type: 'textbox'}}
			]],
       //该事件中的列 生成滚动条；
        columns :[[
        	{width: '100',title: '设计轴线',field: 'sjzx',rowspan:3,align:'center',editor:{type: 'textbox'}},
            {width: '100',title: '施工类型',field: 'sglx',rowspan:3,align:'center',editor:{type:'textbox'}},
            {width: '100',title: '封顶块位置',field: 'fdkwz',rowspan:3,align:'center',editor:{type:'numberbox'}},
            {width: '100',title: '注浆压力Mpa',field: 'zjyl',rowspan:3,align:'center',editor:{type:'numberbox',options:{precision:6}}},
            
            //   ===== 注浆  =======
            {title: '注浆',colspan:4,field: 'zj', align:'center'},
            
            {width: '100',title: '土压（bar)',field: 'ty',align:'center',rowspan:3, editor:{type:'numberbox',options:{precision:3}}},
            {width: '100',title: '出土量(方)',field: 'ctl',align:'center',rowspan:3,editor:{type:'textbox'}},
            {width: '100',title: '平均总推力(KN)',field: 'pjztl',align:'center',rowspan:3,editor:{type:'numberbox',options:{precision:3}}},
            {width: '100',title: '平均扭矩（KN.m）',field: 'pjnj',align:'center',rowspan:3,editor:{type:'numberbox',options:{precision:3}}},
            
            
            //=== 隧道轴线 ===
            {title: '隧道轴线',field: 'sdzx',colspan:2,align:'center'},
            //=== 推进千斤顶（mm） ===
            {title: '推进千斤顶（mm）',field: 'tjqjd',colspan:8,align:'center'},
            //=== 盾构机轴线姿态 ===
            {title: '盾构机轴线姿态',field: 'dgjzzt',colspan:4,align:'center'},
            
            //=== 盾尾间隙（mm）===
            {title: '盾尾间隙（mm）',field: 'dwjx',colspan:8,align:'center'},
            
            //=== 管片姿态（mm）===
            {title: '管片姿态（mm）',field: 'gpzt',colspan:4,align:'center'},
            
            //盾构掘进完成起止时间    左：left
            {width:'180',title: '盾构掘进完成起止时间',field: 'dgjjwcqzsj',rowspan:3,align:'center',editor:{type: 'textbox'}},
            {width:'160',title: '施工日期',field: 'sgrq',rowspan:3,align:'center',editor:{type: 'textbox'}},
            {width:'300',title: '备注',field: 'remarks',rowspan:3,align:'left',editor:{type: 'textbox'}}  
           
            ],
            [
         	//第二层 标题
         	//   ===== 注浆  =======
             {width: '100',title: '同步注浆量',field: 'tbzjl',rowspan:2,align:'center',editor:{type:'numberbox'}},
             {width: '100',title: '同步注浆位置',field: 'tbzjwz',rowspan:2,align:'center',editor:{type:'textbox'}},
             {width: '100',title: '管片注浆位置',field: 'gpzjwz',rowspan:2,align:'center',editor:{type:'textbox'}},
             {width: '100',title: '后续注浆时间',field: 'hxzjsj',rowspan:2,align:'center',editor:{type:'textbox'}},
         	
             //=== 隧道轴线 ===
             {width: '100',title: '高程',field: 'sdzxgc',rowspan:2,align:'center',editor:{type:'numberbox',options:{precision:6}}},
             {width: '100',title: '平面',field: 'sdzxpm',rowspan:2,align:'center',editor:{type:'numberbox',options:{precision:6}}},
           
             //=== 推进千斤顶（mm） ===
             {title: '管片安装前',field: 'qjdgpazq',colspan:4,rowspan:1,align:'center'},
             {title: '管片安装后',field: 'qjdgpazh',colspan:4,rowspan:1,align:'center'},
             
             //=== 盾构机轴线姿态 ===
             {title: '水平',field: 'dgjzxztsp',colspan:2,align:'center'},
             {title: '垂直',field: 'dgjzxztcz',colspan:2,align:'center'},
           
             //=== 盾尾间隙（mm）===
             {title: '拼装前',field: 'dwjxpzq',colspan:4,align:'center'},
             {title: '拼装后',field: 'dwjxpzh',colspan:4,align:'center'},
           
             //=== 管片姿态（mm）===
             {title: '拼装前',field: 'gpztpzq',colspan:2,align:'center'},
             {title: '拼装后',field: 'gpztpzh',colspan:2,align:'center'}
             
            ],
            [
         	 //第三层 标题
         	
            //=== 推进千斤顶-管片安装前（mm） ===
            {width: '70',field: 'gpazqA',align:'center',editor:{type:'numberbox'}},
            {width: '70',field: 'gpazqB',align:'center',editor:{type:'numberbox'}},
            {width: '70',field: 'gpazqC',align:'center',editor:{type:'numberbox'}},
            {width: '70',field: 'gpazqD',align:'center',editor:{type:'numberbox'}},
            //=== 推进千斤顶-管片安装后（mm） ===
            {width: '70',field: 'gpazhA',align:'center',editor:{type:'numberbox'}},
            {width: '70',field: 'gpazhB',align:'center',editor:{type:'numberbox'}},
            {width: '70',field: 'gpazhC',align:'center',editor:{type:'numberbox'}},
            {width: '70',field: 'gpazhD',align:'center',editor:{type:'numberbox'}},
            
            //=== 盾构机轴线姿态-水平 ===
            {width: '70',title: '切口',field: 'dgjzxspqk',align:'center',editor:{type:'numberbox'}},
            {width: '70',title: '盾尾',field: 'dgjzxspdw',align:'center',editor:{type:'numberbox'}},
            //盾构机轴线姿态-垂直
            {width: '70',title: '切口',field: 'dgjzxczqk',align:'center',editor:{type:'numberbox'}},
            {width: '70',title: '盾尾',field: 'dgjzxczdw',align:'center',editor:{type:'numberbox'}},
            
            //盾尾间隙（mm）-拼装前
            {width: '70',title: '上',field: 'dwjxpzqs',align:'center',editor:{type:'numberbox'}},
            {width: '70',title: '下',field: 'dwjxpzqx',align:'center',editor:{type:'numberbox'}},
            {width: '70',title: '左',field: 'dwjxpzqz',align:'center',editor:{type:'numberbox'}},
            {width: '70',title: '右',field: 'dwjxpzqy',align:'center',editor:{type:'numberbox'}},
            
            //盾尾间隙（mm）-拼装后
            {width: '70',title: '上',field: 'dwjxpzhs',align:'center',editor:{type:'numberbox'}},
            {width: '70',title: '下',field: 'dwjxpzhx',align:'center',editor:{type:'numberbox'}},
            {width: '70',title: '左',field: 'dwjxpzhz',align:'center',editor:{type:'numberbox'}},
            {width: '70',title: '右',field: 'dwjxpzhy',align:'center',editor:{type:'numberbox'}},
            
            
            //=== 管片姿态（mm）-拼装前===
            {width: '100',title: '高程',field: 'gpztpzqgc',align:'center',editor:{type:'numberbox'}},
            {width: '100',title: '平面',field: 'gpztpzqpm',align:'center',editor:{type:'numberbox'}},
            //管片姿态（mm）-拼装后
            {width: '100',title: '高程',field: 'gpztpzhgc',align:'center',editor:{type:'numberbox'}},
            {width: '100',title: '平面',field: 'gpztpzhpm',align:'center',editor:{type:'numberbox'}}
            
            ]],
           onLoadSuccess : function(data) {
        	  
 		      hiddenButtonBase("dataGrid_excleWay_dgjjRjjInfo",hiddenClumArrayZeroExcleWay,hiddenClumArrayOnlyOneItemExcleWay,hiddenClumArrayMoreOneItemExcleWay);
 	      },
 	      onCheck : function(){
 	        	hiddenButtonBase("dataGrid_excleWay_dgjjRjjInfo",hiddenClumArrayZeroExcleWay,hiddenClumArrayOnlyOneItemExcleWay,hiddenClumArrayMoreOneItemExcleWay);
 	      },
 	      onUncheck : function(){
 	        	hiddenButtonBase("dataGrid_excleWay_dgjjRjjInfo",hiddenClumArrayZeroExcleWay,hiddenClumArrayOnlyOneItemExcleWay,hiddenClumArrayMoreOneItemExcleWay);
 	      },
 	      onCheckAll : function(){
 	        	hiddenButtonBase("dataGrid_excleWay_dgjjRjjInfo",hiddenClumArrayZeroExcleWay,hiddenClumArrayOnlyOneItemExcleWay,hiddenClumArrayMoreOneItemExcleWay);
 	      },
 	      onUncheckAll : function(){
 	        	hiddenButtonBase("dataGrid_excleWay_dgjjRjjInfo",hiddenClumArrayZeroExcleWay,hiddenClumArrayOnlyOneItemExcleWay,hiddenClumArrayMoreOneItemExcleWay);
 	      },
           onDblClickRow: function (rowIndex, rowData) {
        	   hiddenButtonBase("dataGrid_excleWay_dgjjRjjInfo",hiddenClumArrayZeroExcleWay,hiddenClumArrayOnlyOneItemExcleWay,hiddenClumArrayMoreOneItemExcleWay);
        	   
        	   if (editRow != undefined  && editSghh !="") {   
            	   //结束编辑
            	   $("#dataGrid_excleWay_dgjjRjjInfo").datagrid('endEdit', editRow); 
                }
                if (editRow == undefined ) {
             	  //开始编辑
                   $("#dataGrid_excleWay_dgjjRjjInfo").datagrid('beginEdit', rowIndex);
                   editRow = rowIndex;
                }
          },
          onClickRow: function (rowIndex, rowData) {
        	  hiddenButtonBase("dataGrid_excleWay_dgjjRjjInfo",hiddenClumArrayZeroExcleWay,hiddenClumArrayOnlyOneItemExcleWay,hiddenClumArrayMoreOneItemExcleWay);
       	      if (editRow != undefined ) { 
            		//结束编辑
            		$("#dataGrid_excleWay_dgjjRjjInfo").datagrid('endEdit', editRow); 
              }
          },
          onAfterEdit: function (rowIndex, rowData, changes) {
        	  hiddenButtonBase("dataGrid_excleWay_dgjjRjjInfo",hiddenClumArrayZeroExcleWay,hiddenClumArrayOnlyOneItemExcleWay,hiddenClumArrayMoreOneItemExcleWay);
        	  /*** sghh:施工环号，不能为空！ */
          	if (rowData.sghh =="") { 
          		   $.messager.alert({title:'提示',msg:'“施工环号”不能为空!',showType:'show' });
          		   $("#dataGrid_excleWay_dgjjRjjInfo").datagrid('beginEdit', rowIndex);
          		   editRow = rowIndex;
          		   editSghh ="";
          	 }else{  
                  $('#dataGrid_excleWay_dgjjRjjInfo').datagrid('refreshRow', rowIndex);
                  editSghh ="非空";
                  editRow = undefined; 
                  
        		 } 
          },
		  
	      toolbar : '#excleWayToolbar' 
	});
	

	
	
	/**  查看【日掘进参数信息】列表 创建数据表格 	 */

	dataGrid_dgjjRjjInfo = $('#dataGrid_dgjjRjjInfo').datagrid({
		url : path+'/dataGrid',
		striped : true,
		rownumbers : true,
		singleSelect : false, 
		pagination : true,
		idField : 'id',
		pageSize : 15,
		pageList : [ 10, 15, 20, 50 ],
		//该事件中的列 不生成滚动条；
		frozenColumns: [ [
			{field:'ck',checkbox:true },
			//双击行时，班次不可以修改
			{title: '班次',width: '100',field: 'bzname',align:'center'},
			{width: '100',title: '施工环号',field: 'sghh',align:'center',editor:{type:'numberbox',required:true}},
			{width: '120',title: '管片变换关键里程',field: 'gpbhgjlc',align:'center',editor:{type:'numberbox',options:{precision:6}}}
			]],
       //该事件中的列 生成滚动条；
	    columns :[[
           {width: '100',title: '设计类型',field: 'sjlx',rowspan:3,align:'center',editor:{type: 'textbox'}},
           {width: '100',title: '设计轴线',field: 'sjzx',rowspan:3,align:'center',editor:{type: 'textbox'}},
           {width: '100',title: '施工类型',field: 'sglx',rowspan:3,align:'center',editor:{type:'textbox'}},
           {width: '100',title: '封顶块位置',field: 'fdkwz',rowspan:3,align:'center',editor:{type:'numberbox'}},
           {width: '100',title: '注浆压力Mpa',field: 'zjyl',rowspan:3,align:'center',editor:{type:'numberbox',options:{precision:6}}},
           
           //   ===== 注浆  =======
           {title: '注浆',colspan:4,field: 'zj', align:'center'},
           
           {width: '100',title: '土压（bar)',field: 'ty',align:'center',rowspan:3, editor:{type:'numberbox',options:{precision:3}}},
           {width: '100',title: '出土量(方)',field: 'ctl',align:'center',rowspan:3,editor:{type:'textbox'}},
           {width: '100',title: '平均总推力(KN)',field: 'pjztl',align:'center',rowspan:3,editor:{type:'numberbox',options:{precision:3}}},
           {width: '100',title: '平均扭矩（KN.m）',field: 'pjnj',align:'center',rowspan:3,editor:{type:'numberbox',options:{precision:3}}},
           
           
           //=== 隧道轴线 ===
           {title: '隧道轴线',field: 'sdzx',colspan:2,align:'center'},
           //=== 推进千斤顶（mm） ===
           {title: '推进千斤顶（mm）',field: 'tjqjd',colspan:8,align:'center'},
           //=== 盾构机轴线姿态 ===
           {title: '盾构机轴线姿态',field: 'dgjzzt',colspan:4,align:'center'},
           
           //=== 盾尾间隙（mm）===
           {title: '盾尾间隙（mm）',field: 'dwjx',colspan:8,align:'center'},
           
           //=== 管片姿态（mm）===
           {title: '管片姿态（mm）',field: 'gpzt',colspan:4,align:'center'},
           
           //盾构掘进完成起止时间    左：left
           {width:'180',title: '盾构掘进完成起止时间',field: 'dgjjwcqzsj',rowspan:3,align:'center',editor:{type: 'textbox'}},
           {width:'160',title: '施工日期',field: 'sgrq',rowspan:3,align:'center',editor:{type: 'textbox'}},
           {width:'300',title: '备注',field: 'remarks',rowspan:3,align:'left',editor:{type: 'textbox'}}  
          
           ],
           [
        	//第二层 标题
        	//   ===== 注浆  =======
            {width: '100',title: '同步注浆量',field: 'tbzjl',rowspan:2,align:'center',editor:{type:'numberbox'}},
            {width: '100',title: '同步注浆位置',field: 'tbzjwz',rowspan:2,align:'center',editor:{type:'textbox'}},
            {width: '100',title: '管片注浆位置',field: 'gpzjwz',rowspan:2,align:'center',editor:{type:'textbox'}},
            {width: '100',title: '后续注浆时间',field: 'hxzjsj',rowspan:2,align:'center',editor:{type:'textbox'}},
        	
            //=== 隧道轴线 ===
            {width: '100',title: '高程',field: 'sdzxgc',rowspan:2,align:'center',editor:{type:'numberbox',options:{precision:6}}},
            {width: '100',title: '平面',field: 'sdzxpm',rowspan:2,align:'center',editor:{type:'numberbox',options:{precision:6}}},
          
            //=== 推进千斤顶（mm） ===
            {title: '管片安装前',field: 'qjdgpazq',colspan:4,rowspan:1,align:'center'},
            {title: '管片安装后',field: 'qjdgpazh',colspan:4,rowspan:1,align:'center'},
            
            //=== 盾构机轴线姿态 ===
            {title: '水平',field: 'dgjzxztsp',colspan:2,align:'center'},
            {title: '垂直',field: 'dgjzxztcz',colspan:2,align:'center'},
          
            //=== 盾尾间隙（mm）===
            {title: '拼装前',field: 'dwjxpzq',colspan:4,align:'center'},
            {title: '拼装后',field: 'dwjxpzh',colspan:4,align:'center'},
          
            //=== 管片姿态（mm）===
            {title: '拼装前',field: 'gpztpzq',colspan:2,align:'center'},
            {title: '拼装后',field: 'gpztpzh',colspan:2,align:'center'}
            
           ],
           [
        	 //第三层 标题
        	
           //=== 推进千斤顶-管片安装前（mm） ===
           {width: '70',field: 'gpazqA',align:'center',editor:{type:'numberbox'}},
           {width: '70',field: 'gpazqB',align:'center',editor:{type:'numberbox'}},
           {width: '70',field: 'gpazqC',align:'center',editor:{type:'numberbox'}},
           {width: '70',field: 'gpazqD',align:'center',editor:{type:'numberbox'}},
           //=== 推进千斤顶-管片安装后（mm） ===
           {width: '70',field: 'gpazhA',align:'center',editor:{type:'numberbox'}},
           {width: '70',field: 'gpazhB',align:'center',editor:{type:'numberbox'}},
           {width: '70',field: 'gpazhC',align:'center',editor:{type:'numberbox'}},
           {width: '70',field: 'gpazhD',align:'center',editor:{type:'numberbox'}},
           
           //=== 盾构机轴线姿态-水平 ===
           {width: '70',title: '切口',field: 'dgjzxspqk',align:'center',editor:{type:'numberbox'}},
           {width: '70',title: '盾尾',field: 'dgjzxspdw',align:'center',editor:{type:'numberbox'}},
           //盾构机轴线姿态-垂直
           {width: '70',title: '切口',field: 'dgjzxczqk',align:'center',editor:{type:'numberbox'}},
           {width: '70',title: '盾尾',field: 'dgjzxczdw',align:'center',editor:{type:'numberbox'}},
           
           //盾尾间隙（mm）-拼装前
           {width: '70',title: '上',field: 'dwjxpzqs',align:'center',editor:{type:'numberbox'}},
           {width: '70',title: '下',field: 'dwjxpzqx',align:'center',editor:{type:'numberbox'}},
           {width: '70',title: '左',field: 'dwjxpzqz',align:'center',editor:{type:'numberbox'}},
           {width: '70',title: '右',field: 'dwjxpzqy',align:'center',editor:{type:'numberbox'}},
           
           //盾尾间隙（mm）-拼装后
           {width: '70',title: '上',field: 'dwjxpzhs',align:'center',editor:{type:'numberbox'}},
           {width: '70',title: '下',field: 'dwjxpzhx',align:'center',editor:{type:'numberbox'}},
           {width: '70',title: '左',field: 'dwjxpzhz',align:'center',editor:{type:'numberbox'}},
           {width: '70',title: '右',field: 'dwjxpzhy',align:'center',editor:{type:'numberbox'}},
           
           
           //=== 管片姿态（mm）-拼装前===
           {width: '100',title: '高程',field: 'gpztpzqgc',align:'center',editor:{type:'numberbox'}},
           {width: '100',title: '平面',field: 'gpztpzqpm',align:'center',editor:{type:'numberbox'}},
           //管片姿态（mm）-拼装后
           {width: '100',title: '高程',field: 'gpztpzhgc',align:'center',editor:{type:'numberbox'}},
           {width: '100',title: '平面',field: 'gpztpzhpm',align:'center',editor:{type:'numberbox'}}
           
           ]],
		onLoadSuccess : function(data) { 
			hiddenButtonBase("dataGrid_dgjjRjjInfo",hiddenClumArrayZeroShow,hiddenClumArrayOnlyOneItemShow,hiddenClumArrayMoreOneItemShow);
		},
        onCheck : function(){
        	hiddenButtonBase("dataGrid_dgjjRjjInfo",hiddenClumArrayZeroShow,hiddenClumArrayOnlyOneItemShow,hiddenClumArrayMoreOneItemShow);
        }, 
        onCheckAll : function(){
        	hiddenButtonBase("dataGrid_dgjjRjjInfo",hiddenClumArrayZeroShow,hiddenClumArrayOnlyOneItemShow,hiddenClumArrayMoreOneItemShow);
        },
        onUncheck : function(){
        	hiddenButtonBase("dataGrid_dgjjRjjInfo",hiddenClumArrayZeroShow,hiddenClumArrayOnlyOneItemShow,hiddenClumArrayMoreOneItemShow);
        },
        onUncheckAll : function(){
        	hiddenButtonBase("dataGrid_dgjjRjjInfo",hiddenClumArrayZeroShow,hiddenClumArrayOnlyOneItemShow,hiddenClumArrayMoreOneItemShow);
        }, 
        onDblClickRow: function (rowIndex, rowData) { 
     	    hiddenButtonBase("dataGrid_dgjjRjjInfo",hiddenClumArrayZeroShow,hiddenClumArrayOnlyOneItemShow,hiddenClumArrayMoreOneItemShow);
       	    //列表表单 双击事件  
 	       if (editRow != undefined  && editSghh !="") { 
       		  //结束编辑
       		  $("#dataGrid_dgjjRjjInfo").datagrid('endEdit', editRow); 
           }
           if (editRow == undefined ) {
        	  //开始编辑
              $("#dataGrid_dgjjRjjInfo").datagrid('beginEdit', rowIndex);
              editRow = rowIndex;
           }
      },
      onClickRow: function (rowIndex, rowData) {
    	
    	  hiddenButtonBase("dataGrid_dgjjRjjInfo",hiddenClumArrayZeroShow,hiddenClumArrayOnlyOneItemShow,hiddenClumArrayMoreOneItemShow);
          if (editRow != undefined ) { 
        		//结束编辑
        		$("#dataGrid_dgjjRjjInfo").datagrid('endEdit', editRow); 
          }
      },
      onAfterEdit: function (rowIndex, rowData, changes) {
    	  /*** sghh:施工环号，不能为空！ */
    	if (rowData.sghh =="") { 
    		   $.messager.alert({title:'提示',msg:'“施工环号”不能为空!',showType:'show' });
    		   $("#dataGrid_dgjjRjjInfo").datagrid('beginEdit', rowIndex);
    		   editRow = rowIndex;
    		   editSghh ="";
    	 }else{  
            $('#dataGrid_dgjjRjjInfo').datagrid('refreshRow', rowIndex);
            editSghh ="非空";
            editRow = undefined; 
            /*** 保存 编辑过的数据，editRowList:全局 new  array */ 
            editRowList.push(rowData);
  		 }
    	  hiddenButtonBase("dataGrid_dgjjRjjInfo",hiddenClumArrayZeroShow,hiddenClumArrayOnlyOneItemShow,hiddenClumArrayMoreOneItemShow);
          
                  
          /*** 做 ‘编辑过’ 提示 */
          /***  更新行数据后  获取 更新后 的行数据，保存到 全局集合，点击保存时，提交 全局集合 */
      },
		toolbar : '#show_toolbar' 
	});
	
	/***
	 * 构造查询项目，区间，线路下拉框
	 */
	_getProDic("query_gcBh_dgjjRjjInfo",null,null);
});


 /**
 * 【日掘进信息管理】主页面刷新
 * @returns
 */
function reloadDgjjRjjInfoParent(){
	dataGrid_dgjjRjjInfoParent.datagrid('reload');
}

/**
 * 【日掘进信息管理】主页面取消已选
 * @returns
 */
function clearDgjjRjjInfoParentSelections(){
	dataGrid_dgjjRjjInfoParent.datagrid('clearSelections');
}




/** 【日掘进信息管理】主页面表单查询 */
function searchDgjjRjjInfoParentFun() {
	dataGrid_dgjjRjjInfoParent.datagrid('load', $.serializeObject($('#searchForm_dgjjRjjInfoParent')));
}

/**【日掘进信息管理】主页面表单重置 */
function cleanDgjjRjjInfoParentFun() {
	$('#searchForm_dgjjRjjInfoParent input').val('');
	dataGrid_dgjjRjjInfoParent.datagrid('load', {});
}


/** 【日掘进信息管理】打开明细页面 */
/***
 * 执行说明：先 获取项目、区间、线路:ID和name;
 * 
 */
function showDgjjRjjInfoFun(id,index) {
	
    var rows = dataGrid_dgjjRjjInfoParent.datagrid('getRows');
	//显示  项目  名称
	$("#show_projectName").html(rows[index].proName);
	//区间名称
	$("#show_qujianName").html(rows[index].sectionName);
	//路线名称
	$("#show_lineName").html(rows[index].lineName);
	xlBh = rows[index].id;
	//打开   页面
	$("#showDialog_dgjjRjjInfo").dialog('open').dialog('setTitle', '［'+entityCnName+"］");
	//打开 或 关闭 页面时   重置  或 清空  datagrid（列表）数据

	$("#dataGrid_dgjjRjjInfo").datagrid('loadData', []);
	//取消已选
	dataGrid_dgjjRjjInfo.datagrid('clearSelections');
	dataGrid_dgjjRjjInfo.datagrid("load",{xlBh:rows[index].id});
	
}





/*** 查看日掘进明细 页面 按钮   开始   */


/**
 * 【日掘进信息管理】明细刷新
 * @returns
 */
function reloadDgjjRjjInfo(){
	dataGrid_dgjjRjjInfo.datagrid('reload');
}


/**
 * 【日掘进信息管理】明细 取消已选
 * @returns
 */
function clearDgjjRjjInfoSelections(){
	dataGrid_dgjjRjjInfo.datagrid('clearSelections');
}

/**
 * 【线路中心线信息管理】导出excel
 * @returns
 */
function expDgjjRjjInfoXls(){
	var url = path + "/expXls";
	ExportUtil.doExport(url, ExportUtil.TYPE_XLS_2003, "日掘进信息",dataGrid_dgjjRjjInfo, false);
	
}

/**
 * 【线路中心线信息管理】导出excel 
 * @returns
 */
function expDgjjRjjInfoPdf(){ 
	var url = path + "/expPdf";
	ExportUtil.doExport(url, ExportUtil.TYPE_PDF, "日掘进信息",dataGrid_dgjjRjjInfo, false);
	 
}

/**【日掘进信息管理】查看“日掘进明细”页面，添加单条 日掘进信息*/
function addDgjjRjjInfoFun(){ 
	parent.$.modalDialog({
        title : '新增日掘进信息',
        width : 1000,
        height : 450,
        href : path+'/addUnfold?xlBh='+xlBh,
        buttons : [ {
            text : '保存',
            handler : function() {
            	parent.$.modalDialog.openner_dataGrid = dataGrid_dgjjRjjInfo;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
            	var f = parent.$.modalDialog.handler.find('#addSaveBtn');
            	$(f).trigger("onclick");          	
            }
        },{
            text : '关闭',
            handler : function() {
                parent.$.modalDialog.handler.dialog('close');
            }
        } ],
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


/**【日掘进信息管理】查看“日掘进明细”页面，编辑单条 日掘进信息*/
function editDgjjRjjInfoFun(){
	var rows = dataGrid_dgjjRjjInfo.datagrid('getSelections');
	if (rows.length == 0 || rows.length > 1) {
		$.messager.alert('提示', '请选择一条数据再编辑！', 'info');
		return;
	}
	
	var rId = rows[0].id;
	parent.$.modalDialog({
        title : '编辑日掘进信息',
        width : 1000,
        height : 450,
        href : path+'/editUnfold?xlBh='+xlBh+'&id='+rId,
        buttons : [ {
            text : '保存',
            handler : function() {
            	parent.$.modalDialog.openner_dataGrid = dataGrid_dgjjRjjInfo;//因为编辑成功之后，需要刷新这个dataGrid，所以先预定义好
            	var f = parent.$.modalDialog.handler.find('#editSaveBtn');
            	$(f).trigger("onclick");          	
            }
        },{
            text : '关闭',
            handler : function() {
                parent.$.modalDialog.handler.dialog('close');
            }
        } ],
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


/**【日掘进信息管理】查看“日掘进明细”页面，查看单条 日掘进信息*/
function lookDgjjRjjInfoFun(){
	var rows = dataGrid_dgjjRjjInfo.datagrid('getSelections');
	if (rows.length == 0 || rows.length > 1) {
		$.messager.alert('提示', '请选择一条数据再编辑！', 'info');
		return;
	}
	
	var rId = rows[0].id;
	parent.$.modalDialog({
        title : '查看日掘进信息',
        width : 1000,
        height : 450,
        href : path+'/lookUnfold?id='+rId,
        buttons : [ {
            text : '关闭',
            handler : function() {
                parent.$.modalDialog.handler.dialog('close');
            }
        } ]
    });
}

/*** 【日掘进明细】 删除 选中 日掘进信息  */
function deleteDgjjRjjInfoFun() {
	deleteFun(path+'/del');
	
}
//列表页面勾选复选框时检查列表是否选择了数据的通用方法
function checkSelect(){
	//【日掘进明细】 表单
    var checkedItems = $('#dataGrid_dgjjRjjInfo').datagrid('getChecked');
    var ids = [];
    $.each(checkedItems, function(index, item){
        ids.push(item.id);
    });
    if( 0 == ids.length){
        parent.$.messager.alert('提示', '请选择数据', 'info');
        return ids;
    }
    
    return ids;
}
//删除函数
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
                    dataGrid_dgjjRjjInfo.datagrid('clearSelections');
                    //【日掘进明细】 表单
                    dataGrid_dgjjRjjInfo.datagrid('reload');
                }
                progressClose();
            }, 'JSON');
        }
    });
}



/**  
 * 
 * 【日掘进明细页面】保存按钮 事件  
 */
function showDgjjRjjInfoSaveAjax(){
	if (editRow != undefined ) { 
		//结束编辑
		$("#dataGrid_dgjjRjjInfo").datagrid('endEdit', editRow);
		//再次提交
		showDgjjRjjInfoSaveAjax();
    }else{
    	if(editRowList.length > 0 && editRow ==undefined){
    		parent.$.messager.confirm('询问', '确定要保存修改吗？', function(b) {
      	        if (b) { 
      	        	var json = JSON.stringify(editRowList);
      	  		$('#show_details').val(json);
      	      	$('#showForm_dgjjRjjInfo').form('submit', {
      	      		url : path+'/showUpdate',
      	      		onSubmit : function() {
      	      			progressLoad();
      	      		},
      	      		success : function(result) {
      	      			progressClose();
      	      			result = $.parseJSON(result);
      	      			if (result.success) {
      	      				$.messager.show({title:'提示',msg:'保存成功!',showType:'show' });
      	      				//清空全局 数据
      	      				editRowList = [];
      	      				dataGrid_dgjjRjjInfo.datagrid('reload');
      	      				FormUtil.clearForm($("#showForm_dgjjRjjInfo"));
      	      			}
      	      			else {
      	      				$.messager.alert('错误', result.msg, 'error');
      	      			}
      	      		}
      	      	});
      	        }
    		 })
    	}else{ 
    		progressClose();
    		$.messager.show({title:'提示',msg:'无数据保存!',showType:'show' });
    	}
    }
	
}
  	

/*** 查看【日掘进明细】 页面 关闭按钮 */
function closeDgjjRjjInfoShowDialog(){
	
	if(editRowList.length > 0){
		 progressLoad();
		 parent.$.messager.confirm('询问', '您尚未保存数据，确定离开页面吗？', function(b) {
  	        if (b) { 
  	            //清空全局 数据
				editRowList = [];
  	        	$('#showDialog_dgjjRjjInfo').dialog('close');
  	        	progressClose();
  	        }
		 })
	}else{
		$('#showDialog_dgjjRjjInfo').dialog('close');
	}
}

 

 
/** wpg: 打开【导入日掘进信息】导入excel页面 */
function openDgjjRjjInfoImp() {
	FormUtil.clearForm($("#uploadFile_dgjjRjjInfo_Form"));
	$("#openImpexcel_dgjjRjjInfo").dialog('open').dialog('setTitle', '导入日掘进信息');
}



/** wpg:确定选中【日掘进信息】 文件 确定按钮事件, 做班组查询  */

function selectBanZuByXlbh(){
	//判断 是否 选择了文件；
	if($('input[name=clumOne]').val()!=""){
		//取消已选
		dataGrid_SelectBanZu.datagrid('clearSelections');
		//打开添加页面时   重置  或 清空 添加  页面 数据
		FormUtil.clearForm($("#SelectBanZuDialog_dgjjRjjInfo"));
		var rows = dataGrid_dgjjRjjInfoParent.datagrid('getSelections');
		$("#SelectBanZu_projectName").html(rows[0].proName);
		$("#SelectBanZu_qujianName").html(rows[0].sectionName);
		$("#SelectBanZu_lineName").html(rows[0].lineName);
		
		//用于 下个 《日掘进明细》页面
		proName = rows[0].proName;
		sectionName = rows[0].sectionName;
		lineName = rows[0].lineName;
		
		$("#SelectBanZuDialog_dgjjRjjInfo").dialog('open').dialog('setTitle', '添加［'+entityCnName+"］-选择班组信息");
		dataGrid_SelectBanZu.datagrid("load",{xlBh:rows[0].id});
		 
	}else{
		parent.$.messager.alert('提示', "请选择文件！", 'show');
	}   
}



/** 选好班组后， 上传【日掘进信息】excel文件，要有班组信息  */
function uploadFile_dgjjRjjInfo() {
	
	
	//获取 选中的 班组 信息
	var rows = dataGrid_SelectBanZu.datagrid('getSelections');
	//打开添加页面时   重置  或 清空 添加  页面 数据
	FormUtil.clearForm($("#editDgjjRjjInfoDialog"));
	
	//获取  班组 名称；
	bzname = rows[0].bzname;
    BId =rows[0].id;
    
    //wpg:当  '#uploadFile_dgjjRjjInfo_Form' 表单 发生 'submit' 事件，执行｛｝大括号里的代码；
	$('#uploadFile_dgjjRjjInfo_Form').form('submit', {
		url : path+'/upload',
		onSubmit : function() {
			progressLoad();
		},
		success : function(result) {
			progressClose();
			result = $.parseJSON(result);
			if (result.success) {
				
				/***
				 * 把返回来的数据给 下个页面；
				 */
				$("#dataGrid_excleWay_dgjjRjjInfo").datagrid('loadData', []); 
				dataGrid_excleWay_dgjjRjjInfo.datagrid('clearSelections');
				dataGrid_excleWay_dgjjRjjInfo.datagrid('loadData',result.obj);
				
				//给 日掘进明细 表单中   的班组 Id 、班组名称、项目名称、区间名称、线路名称   赋值
				$("#excleWayAdd_BId").html(rows[0].id);
				$("#excleWayAdd_bzname").html(rows[0].bzname);
				$("#excleWayAdd_projectName").html(proName);
				$("#excleWayAdd_qujianName").html(sectionName);
				$("#excleWayAdd_lineName").html(lineName);
			
				//打开  日掘进明细  页面 
				$("#excleWayAddDialog_dgjjRjjInfo").dialog('open').dialog('setTitle', 'Excle导入［'+entityCnName+"］");
				//关闭 选择 文件 页面 ，选择  班组 页面
				$("#openImpexcel_dgjjRjjInfo").dialog('close');
				$("#SelectBanZuDialog_dgjjRjjInfo").dialog('close');
				//清空 文件 表单 ， 班组 表单 
				FormUtil.clearForm($("#uploadFile_dgjjRjjInfo_Form"));
				FormUtil.clearForm($("#SelectBanZuForm_dgjjRjjInfo"));
			}
			else {
				$.messager.alert('错误', result.msg, 'error');
			}
		}
	});
}


 
/**
 * 【日掘进信息管理】选择班组刷新
 * @returns
 */
function reloadSelectBanZuShow(){
	dataGrid_SelectBanZu.datagrid('reload');
}


/**
 * 【日掘进信息管理】选择班组 取消已选
 * @returns
 */
function clearSelectBanZuSelections(){
	dataGrid_SelectBanZu.datagrid('clearSelections');
}





/**
 * 【日掘进信息管理】Excle解析日掘进明细页面 删除明细
 * @returns
 */
 
function deleteExcleWayDgjjRjjInfoFun() {
	 
	  //获取选中的数据行“集合”；
	  var rows ="";
	  rows = dataGrid_excleWay_dgjjRjjInfo.datagrid('getSelections');
      if (rows.length > 0) { 
		parent.$.messager.confirm('询问', '您是否要删除当前所选数据？', function(b) {
	        if (b) { 
	        	
	        	for(var i=rows.length-1; i>=0; i--){ 
		   			 var r = rows[0];  
		   			 if(r.sghh!=""){ 
		   				 var index=dataGrid_excleWay_dgjjRjjInfo.datagrid('getRowIndex',r);
		   				 //删除 第index行数据；
		   			     dataGrid_excleWay_dgjjRjjInfo.datagrid('deleteRow',index);
		   			  } 
	   		    } 
	        	dataGrid_excleWay_dgjjRjjInfo.datagrid('clearSelections');
	        } 	 
	    });
	} 
}	
/**
 * 【日掘进信息管理】Excle解析日掘进明细页面  取消已选
 * @returns
 */    
function clearExcleWayDgjjRjjInfoSelections(){
	dataGrid_excleWay_dgjjRjjInfo.datagrid('clearSelections');
}

/**
 * 【日掘进信息管理】Excle解析日掘进明细页面 添加明细
 * @returns
 */
function addExcleWayDgjjRjjInfoFun(){
	$("#excleWayAddOneRjj").dialog('open').dialog('setTitle', 'Excle导入［'+entityCnName+"］-添加明细");
	
}

/**
 * 【日掘进信息管理】Excle解析日掘进明细页面 添加明细，保存;
 * @returns
 */
function btnCon(){
	
	var sghh = $('#excleAddRjj_sghh').val();
	if(sghh==""){
		return true;
	}else{
		return false;
	}
		
}
/**
 * 新增保存事件
 */
function excleWayAddOneRjjSave(){
	
	var isValid = $('#excleWayAddOneRjjForm').form('validate');
	//判断 添加提交保存 是否为空
	if (!isValid || btnCon()) {
		progressClose();
	}
	else{
		
	  var data={
			  
		sghh: $('#excleAddRjj_sghh').val(),
	    gpbhgjlc: $('#excleAddRjj_gpbhgjlc').val(),
		sjlx: $('#excleAddRjj_sjlx').val(),
		sjzx: $('#excleAddRjj_sjzx').val(),
		sglx: $('#excleAddRjj_sglx').val(),
		fdkwz: $('#excleAddRjj_fdkwz').val(),
		zjyl: $('#excleAddRjj_zjyl').val(),
		tbzjl: $('#excleAddRjj_tbzjl').val(),
		tbzjwz: $('#excleAddRjj_tbzjwz').val(),
		gpzjwz: $('#excleAddRjj_gpzjwz').val(),
		hxzjsj: $('#excleAddRjj_hxzjsj').val(),
		ty: $('#excleAddRjj_ty').val(),
		ctl: $('#excleAddRjj_ctl').val(),
		pjztl: $('#excleAddRjj_pjztl').val(),
		pjnj: $('#excleAddRjj_pjnj').val(),
		sdzxgc: $('#excleAddRjj_sdzxgc').val(),
		sdzxpm: $('#excleAddRjj_sdzxpm').val(),
		gpazqA: $('#excleAddRjj_gpazqA').val(),
		gpazqB: $('#excleAddRjj_gpazqB').val(),
		gpazqC: $('#excleAddRjj_gpazqC').val(),
		gpazqD: $('#excleAddRjj_gpazqD').val(),
		gpazhA: $('#excleAddRjj_gpazhA').val(),
		gpazhB: $('#excleAddRjj_gpazhB').val(),
		gpazhC: $('#excleAddRjj_gpazhC').val(),
		gpazhD: $('#excleAddRjj_gpazhD').val(),
		dgjzxspqk: $('#excleAddRjj_dgjzxspqk').val(),
		dgjzxspdw:$('#excleAddRjj_dgjzxspdw').val(),
		dgjzxczqk: $('#excleAddRjj_dgjzxczqk').val(),
	    dgjzxczdw: $('#excleAddRjj_dgjzxczdw').val(),
	    dwjxpzqs: $('#excleAddRjj_dwjxpzqs').val(),
		dwjxpzqx: $('#excleAddRjj_dwjxpzqx').val(),
		dwjxpzqz: $('#excleAddRjj_dwjxpzqx').val(),
		dwjxpzqy: $('#excleAddRjj_dwjxpzqy').val(),
		dwjxpzhs: $('#excleAddRjj_dwjxpzhs').val(),
		dwjxpzhx: $('#excleAddRjj_dwjxpzhx').val(),
		dwjxpzhz: $('#excleAddRjj_dwjxpzhz').val(),
		dwjxpzhy: $('#excleAddRjj_dwjxpzhy').val(),
		gpztpzqgc: $('#excleAddRjj_gpztpzqgc').val(),
		gpztpzqpm: $('#excleAddRjj_gpztpzqpm').val(),
		gpztpzhgc: $('#excleAddRjj_gpztpzhgc').val(),
		gpztpzhpm: $('#excleAddRjj_gpztpzhpm').val(),
		dgjjwcqzsj: $('#excleAddRjj_dgjjwcqzsj').val(),
		sgrq: $('#excleAddRjj_sgrq').val(),
		remarks: $('#excleAddRjj_remarks').val()
    }
	 
    //把添加的信息，显示到 列表表单
	addRow(data);
	}
	
}

/**  
 * 
 *
 * 新增保存更新数据列表
 */
function addRow(obj){
	
	//把添加的信息，显示到 列表表单
	$('#dataGrid_excleWay_dgjjRjjInfo').datagrid('appendRow',{
		
		sghh:obj.sghh,
	    gpbhgjlc:obj.gpbhgjlc,
		sjlx:obj.sjlx,
		sjzx:obj.sjzx,
		sglx:obj.sglx,
		fdkwz:obj.fdkwz,
		zjyl:obj.zjyl,
		tbzjl:obj.tbzjl,
		tbzjwz:obj.tbzjwz,
		gpzjwz:obj.gpzjwz,
		hxzjsj:obj.hxzjsj,
		ty:obj.ty,
		ctl:obj.ctl,
		pjztl:obj.pjztl,
		pjnj:obj.pjnj,
		sdzxgc:obj.sdzxgc,
		sdzxpm:obj.sdzxpm,
		gpazqA:obj.gpazqA,
		gpazqB:obj.gpazqB,
		gpazqC:obj.gpazqC,
		gpazqD:obj.gpazqD,
		gpazhA:obj.gpazhA,
		gpazhB:obj.gpazhB,
		gpazhC:obj.gpazhC,
		gpazhD:obj.gpazhD,
		dgjzxspqk:obj.dgjzxspqk,
		dgjzxspdw:obj.dgjzxspdw,
		dgjzxczqk:obj.dgjzxczqk,
		dgjzxczdw:obj.dgjzxczdw,
	    dwjxpzqs:obj.dwjxpzqs,
		dwjxpzqx:obj.dwjxpzqx,
		dwjxpzqz:obj.dwjxpzqz,
		dwjxpzqy:obj.dwjxpzqy,
		dwjxpzhs:obj.dwjxpzhs,
		dwjxpzhx:obj.dwjxpzhx,
		dwjxpzhz:obj.dwjxpzhz,
		dwjxpzhy:obj.dwjxpzhy,
		gpztpzqgc:obj.gpztpzqgc,
		gpztpzqpm:obj.gpztpzqpm,
		gpztpzhgc:obj.gpztpzhgc,
		gpztpzhpm:obj.gpztpzhpm,
		dgjjwcqzsj:obj.dgjjwcqzsj,
		sgrq:obj.sgrq,
		remarks:obj.remarks,
		
   });
	 //关闭新增明细的窗口
	$('#excleWayAddOneRjj').window('close');
	
}

/*** 关闭 Excle 解析出 的 日掘进信息窗口 */
function closeExcleWayDgjjRjjInfoDialog(){ 
	var rows = $("#dataGrid_excleWay_dgjjRjjInfo").datagrid("getRows");
    if(rows.length > 0){
		 progressLoad();
		 parent.$.messager.confirm('询问', '您尚未保存数据，确定离开页面吗？', function(b) {
 	        if (b) { 
 	            //清空全局 数据
 	        	$("#dataGrid_excleWay_dgjjRjjInfo").datagrid('loadData', []);
 	        	$('#excleWayAddDialog_dgjjRjjInfo').dialog('close');
 	        	progressClose();
 	        }else{
 	        	progressClose();
 	        }
		 })
	}else{
		$('#excleWayAddDialog_dgjjRjjInfo').dialog('close');
	}
}
/**  
 * 
 * 保存Excle 解析出 的 日掘进信息 
 */
function excleWayAddDgjjRjjInfoAjax(){
	//声明集合
	var detailsInfo=new Array();
	//获取列表数据
  	var rows = $("#dataGrid_excleWay_dgjjRjjInfo").datagrid("getRows");
  	if(rows.length > 0){
	   for(var i=0;i<rows.length;i++)	{
		   detailsInfo[i] = getRjjRow(i,rows);
			
		}
	    var json = JSON.stringify(detailsInfo);
	    //把列表的值 赋给 表单 id="excleWayAddForm_dgjjRjjInfoDetails";的标准
		$('#excleWayAddForm_dgjjRjjInfoDetails').val(json);
		var isValid = $('#excleWayAddForm_dgjjRjjInfo').form('validate')
		
		if(isValid){ 
			//列表表单为“编辑表单”，发生按钮事件；  
	    	$('#excleWayAddForm_dgjjRjjInfo').form('submit', {
	    		url : path+'/excleWayAdd',
	    		onSubmit : function() {
	    			progressLoad();
	    		},
	    		success : function(result) {
	    			progressClose();
	    			result = $.parseJSON(result);
	    			if (result.success) {
	    				$.messager.show({title:'提示',msg:'新增操作：成功!',showType:'show' });
	    				FormUtil.clearForm($("#excleWayAddForm_dgjjRjjInfo"));
	    				$("#excleWayAddDialog_dgjjRjjInfo").dialog('close'); 
	    			}
	    			else {
	    				$.messager.alert('错误', result.msg, 'error');
	    			}
	    		}
	    	});
		}else{
			progressClose();
		}
  	}
  	
}



/***
 * 通用 获取 编辑列表行 数据
 * @param i
 * @returns
 */
function  getRjjRow(i,rows){
	
	var obj = new Object();
	
	obj.BId = BId;//班组表Id，BId:全局变量；
	obj.sghh = rows[i].sghh;
	obj.gpbhgjlc = rows[i].gpbhgjlc;
	obj.sjlx = rows[i].sjlx;
	obj.sjzx = rows[i].sjzx;
	obj.sglx = rows[i].sglx;
	obj.fdkwz = rows[i].fdkwz;
	obj.zjyl = rows[i].zjyl;
	obj.tbzjl = rows[i].tbzjl;
	obj.tbzjwz = rows[i].tbzjwz;
	obj.gpzjwz = rows[i].gpzjwz;
	obj.hxzjsj = rows[i].hxzjsj;
	obj.ty = rows[i].ty;  
	obj.ctl = rows[i].ctl;
	obj.pjztl = rows[i].pjztl;
	obj.pjnj = rows[i].pjnj;
	obj.sdzxgc = rows[i].sdzxgc;
	obj.sdzxpm = rows[i].sdzxpm;
	obj.gpazqA = rows[i].gpazqA;
	obj.gpazqB = rows[i].gpazqB;
	obj.gpazqC = rows[i].gpazqC;
	obj.gpazqD = rows[i].gpazqD;
	obj.gpazhA = rows[i].gpazhA;
	obj.gpazhB = rows[i].gpazhB;
	obj.gpazhC = rows[i].gpazhC;
	obj.gpazhD = rows[i].gpazhD;
	obj.dgjzxspqk = rows[i].dgjzxspqk;
	obj.dgjzxspdw = rows[i].dgjzxspdw;
	obj.dgjzxczqk = rows[i].dgjzxczqk;
	obj.dgjzxczdw = rows[i].dgjzxczdw;
	obj.dwjxpzqs = rows[i].dwjxpzqs;
	obj.dwjxpzqx = rows[i].dwjxpzqx;
	obj.dwjxpzqz = rows[i].dwjxpzqz;
	obj.dwjxpzqy = rows[i].dwjxpzqy;
	obj.dwjxpzhs = rows[i].dwjxpzhs;
	obj.dwjxpzhx = rows[i].dwjxpzhx;
	obj.dwjxpzhz = rows[i].dwjxpzhz;
	obj.dwjxpzhy = rows[i].dwjxpzhy;
	obj.gpztpzqgc = rows[i].gpztpzqgc;
	obj.gpztpzqpm = rows[i].gpztpzqpm;
	obj.gpztpzhgc = rows[i].gpztpzhgc;
	obj.gpztpzhpm = rows[i].gpztpzhpm;
	obj.dgjjwcqzsj = rows[i].dgjjwcqzsj;
	obj.sgrq = rows[i].sgrq;
	obj.remarks = rows[i].remarks;
	
	return obj;
	
}

 



