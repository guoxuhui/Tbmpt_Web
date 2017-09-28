//"use strict"
//srcDs:{fields:[{name:'f1',type:1}...],resource:{rqAnalyse:null,type:1父rqAnalyse/2dataSource及ql/3dfxFile及dfxParams/4dfxScript及dfxParams/5inputFiles|currTable|tableNames/6（dql查询方式，无dataId，dataSource）,dataId:'数据来源ID，后台根据这个ID提供数据',dataSource:'',ql:'',dqlConf:'',dfxFile:'',dfxParams:'',dfxScript:'',inputFiles:'',currTable:'',tableNames:''}}
//items:[{id:1,type:1原始字段/2计算字段/3聚合字段/4条件/5切片条件/6复杂条件,dataType:'',datas:[示例数据，用于切片],parentId:0表示根,name:'',edit:'editStyleName',content:1原始字段/2计算字段表达式/3{field:原始或计算字段,aggr:sum/count/countd...}/4{whereConf}/5{field:原始或计算字段,disp:'中国、美国',code:'1,2'}/6复杂条件表达式}...]
//confs:[{_hasAggr:'0/1',_status:'为空表示正确，不为空是失效的具体信息',type:1自定义分析报表/2模板报表,name:'报表名称',dialog:{open:0/1,top:100,left:100,width:500,height:400},reportId:'',structType:1:单条记录，全是统计字段/2:明细报表/3:分组及交叉报表,template:'',autoCalc:0/1,isRowData:1,
//	lefts:[{name:'',macroName:'',srcItems:[itemId0,itemId1],exp:'itemId0/itemId1',aggrExp:'sum(itemId0)/sum(itemId1)',use:1,order:0无序/1升序/2降序,groups:[lefts,tops里的分组，空分组表示整体聚合]/null表示随分组自动聚合,analyse:{analyseName:'占比/排名/比上期/比同期',field:'被分析的测度字段',scopeGroups:[空则表示针对全部]},format:'',dataType:'',_parentType:'top/left/field',_fieldType:'group/detail/aggr/analyse',_status:'为空表示正确，不为空是失效的具体信息'}]
//	,tops:[],fields:[],wheres:[{item:itemId}]}...] 调序、排序、改名
//editStyles:[{name:'ed1',type:1inputbox/2passwordbox/3checkbox/4calendar/5radio/6whereedit/7select/8tree,content:{}}]
//var rqAnalyse = {srcDs:null,items:[],confs:[],editStyles:[],maxId:0,currRpx:"",currAggr:''};
var dialogCount = 1;
var oldConfig = "";


/*

rqAnalyse = {
	currRpx:''
	,dataSets:[
		{
			name:'',
			,type:1父rqAnalyse/2（dataSource及ql）/3（dfxFile及dfxParams）/4（dfxScript及dfxParams）/5（inputFiles|currTable|tableNames）/6（dql类型dataSource、tableName）
			,parent:null
			,fields:[// type!=6时，可用的字段列表
				{
					name:''
					,dataType:2
					,edit:''
					,exp:'' // exp undefined/等于''是原始字段
				}
			]
			,dataSource:''
			,ql:''
			,dfxFile:''
			,dfxScript:''
			,dfxParams:''
			,inputFiles:''
			,currTable:''
			,tableNames:''
			,tableName:''
			,dqlConf:{}
			,dataId:''//缓存的数据ID
			,rowCount:0
			,_status:''
		}
	],rpxs:[
		{
			name:'报表名称'
			,colWidths:'3:50;4:80'//第三列宽度50，第四列宽度80
			,rowHeights:'3:50;4:80'//第三行高度50，第四行高度80
			,dataSet:''
			//,dataSetLevel:'none/calc/where/group/having/order'
			,_hasAggr:'0/1'
			,_status:'为空表示正确，不为空是失效的具体信息'
			,type:1自定义分析报表/2模板报表
			,dialog:{
				open:0/1
				,top:100
				,left:100
				,width:500
				,height:400
			}
			,reportId:''
			,structType:1:单条记录，全是统计字段/2:明细报表/3:分组及交叉报表
			,template:''
			,autoCalc:0/1
			,isRowData:0/1
			,lefts:[
				name:''
				,src:'字段信息'
				,dataType:''//原始字段数据类型/或聚合后计算字段的表达式
				,srcName:''//原始字段名称
				,srcEdit:''//编辑风格
				,aggr:''
				,use:1
				,order:0无序/1升序/2降序
				,groups:[lefts,tops里的分组，空分组表示整体聚合]/null表示随分组自动聚合
				,analyse:{//指标字段
					analyseName:'占比/排名/比上期/比同期'
					,field:'被分析的测度字段'
					,scopeGroups:[空则表示针对全部]
				}
				,newAnalyse : {
					leftLevel : ''//root/维字段
					,topLevel : ''//root/维字段
					,exp : ''// ?1/?2，?1代表下面第一个子项
					,items : [
						{
							field:'数据来源字段，可以是维或测度或其它分析字段，但分析字段不能互相引用'
							,value:'curr/find' //当前值、查找值
							//以下属性只针对find
							,aggr:'count/avg/sum/max/min/first'
							,exp:'fieldCurrValue/fieldFindValue/fieldCurrSeq/fieldFindSeq' //'上层维度字段或本测度字段'
						}
					]
				}
				,exp:''//聚合后的计算字段，要求聚合指标在同一个层次上，否则计算出来的数据没意义。
				,where:{conf:[]} //去掉,exp:"",disp:""
				,having:{conf:[]}
				,format:''
				,macroName:''
				,_finalType:''//加上聚合后的最终数据类型
  				,_parentType:'top/left/field'
				,_fieldType:'group/detail/aggr/analyse'
				,_status:'为空表示正确，不为空是失效的具体信息'
			]
			,tops:[]
			,fields:[]
			,where:{conf:[]}
			,calcs:[]
		}
	]
}

*/

var rqAnalyse = {
	currRpx:'报表名称'
	,dataSets:[
		{
			name:'dqlTable'
			,type:6//2（dataSource及ql）/3（dfxFile及dfxParams）/4（dfxScript及dfxParams）/5（inputFiles|currTable|tableNames）/6（dql类型dataSource、tableName）
			,dataSource:'DataLogic'
			,parent:null
			,fields:[]
			,ql:''
			,dfxFile:''
			,dfxScript:''
			,dfxParams:''
			,inputFiles:''
			,currTable:''
			,tableNames:''
			,tableName:'雇员'
			,dqlConf:{}
			,dataId:''//缓存的数据ID
			,rowCount:0
		}
		,{
			name:'订单明细'
			,type:6//2（dataSource及ql）/3（dfxFile及dfxParams）/4（dfxScript及dfxParams）/5（inputFiles|currTable|tableNames）/6（dql类型dataSource、tableName）
			,dataSource:'DataLogic'
			,parent:null
			,fields:[]
			,ql:''
			,dfxFile:''
			,dfxScript:''
			,dfxParams:''
			,inputFiles:''
			,currTable:''
			,tableNames:''
			,tableName:'订单明细'
			,dqlConf:{}
			,dataId:''//缓存的数据ID
			,rowCount:0
		}
		,{
			name:'订单'
			,type:6//2（dataSource及ql）/3（dfxFile及dfxParams）/4（dfxScript及dfxParams）/5（inputFiles|currTable|tableNames）/6（dql类型dataSource、tableName）
			,dataSource:'DataLogic'
			,parent:null
			,fields:[]
			,ql:''
			,dfxFile:''
			,dfxScript:''
			,dfxParams:''
			,inputFiles:''
			,currTable:''
			,tableNames:''
			,tableName:'订单'
			,dqlConf:{}
			,dataId:''//缓存的数据ID
			,rowCount:0
		}

	],rpxs:[
		{
			name:'报表名称'
			,dataSet:'dqlTable'
			//,dataSetLevel:'none/calc/where/group/having/order'
			,_hasAggr:0//'0/1'
			,_status:''//'为空表示正确，不为空是失效的具体信息'
			,type:1//1自定义分析报表/2模板报表
			,dialog:{
				open:1//0/1
				,top:100
				,left:100
				,width:500
				,height:400
			}
			,reportId:new Date().getTime()
			,structType:1//:单条记录，全是统计字段/2:明细报表/3:分组及交叉报表
			,template:''
			,autoCalc:1//0/1
			,isRowData:1//0/1
			,lefts:[
				/*
				name:''
				,src:'字段信息'
				,dataType:''//原始字段数据类型
				,srcName:''//原始字段名称
				,srcEdit:''//编辑风格
				,aggr:''
				,use:1
				,order:0无序/1升序/2降序
				,groups:[lefts,tops里的分组，空分组表示整体聚合]/null表示随分组自动聚合
				,analyse:{//指标字段
					analyseName:'占比/排名/比上期/比同期'
					,field:'被分析的测度字段'
					,scopeGroups:[空则表示针对全部]
				}
				,where:{conf:[]}
				,having:{conf:[]}
				,format:''
				,_finalType:''
  				,_parentType:'top/left/field'
				,_fieldType:'group/detail/aggr/analyse'
				,_status:'为空表示正确，不为空是失效的具体信息'
				*/
			]
			,tops:[]
			,fields:[]
			,where:{conf:[]}
			,calcs:[]
		}	
	]};

	
function refreshStatus(dataSet,finishFunc,container) {
	if (!container) container = $('#queryStatus');
	$.ajax({
		type: 'POST',
		async : false,
		url: contextPath + "/DLServletAjax?d="+new Date().getTime(),
		data: {action:2,oper:'getLoadedStatus',dataId:dataSet.dataId},
		success: function(data){
			if (data.indexOf('error:')==0) {
				alert(data.substring(6));
				return;
			}
			data = eval("("+data+")");
			if (data.error) {
				alert(data.error);
				return;
			}
			dataSet.rowCount = data.loadedRow;
			//viewPage.over = data.over;
			//viewPage.loadedRow = data.loadedRow;
			if (data.over == 1) {
				if (data.loadedRow == 0) {
					container.html("数据集["+dataSet.name+"]未查询到任何数据");
					return;
				}
				if (finishFunc) finishFunc();
				container.html("数据集["+dataSet.name+"]已加载完毕，共&nbsp;" + data.loadedRow + "&nbsp;行数据");
			} else {
				if (data.loadedRow == 0) {
					container.html("数据集["+dataSet.name+"]正在执行查询");
				} else {
					container.html("数据集["+dataSet.name+"]已加载&nbsp;" + data.loadedRow + "&nbsp;行数据");
				}
				setTimeout(function(){refreshStatus(dataSet,finishFunc,container);},1000);
			}
		}
	});
}

var aly = {
	initFields : function(dataSetFields) {
		var items = [];
		for (var i=0; i<dataSetFields.length; i++) {
			items.push(aly.initDataSetField(dataSetFields[i]));
		}
		return items;
	}
	,initDataSetField : function(dataSetField){
		return {
			name : dataSetField.name
			,src:dataSetField.name
			,dataType:dataSetField.dataType
			,srcName:dataSetField.name
			,srcEdit:dataSetField.edit//编辑风格
			,aggr:dataSetField.dataType==1?'sum':'count'
			,use:1
			,order:0
			,groups:null//[lefts,tops里的分组，空分组表示整体聚合]/null表示随分组自动聚合
			,analyse:{//指标字段
				analyseName:''//'占比/排名/比上期/比同期'
				,field:''//'被分析的测度字段'
				,scopeGroups:[]//[空则表示针对全部]
			}
			,macroName:''
			,where:{conf:[]}
			,having:{conf:[]}
			,format:''
			,_finalType:''
			,_parentType:''//'top/left/field'
			,_fieldType:''//'group/detail/aggr/analyse'
			,_status:''//'为空表示正确，不为空是失效的具体信息'
		};
	}
	,cache : {
		reports : []
	}
		/*
		rqAnalyse = {
			currRpx:''
			,dataSets:[
				{
					name:'',
					,type:1父rqAnalyse/2（dataSource及ql）/3（dfxFile及dfxParams）/4（dfxScript及dfxParams）/5（inputFiles|currTable|tableNames）/6（dql类型dataSource、tableName）
					,parent:null
					,fields:[// type!=6时，可用的字段列表
						{
							name:''
							,dataType:2
							,edit:''
							,exp:'' // exp undefined/等于''是原始字段
						}
					]
					,dataSource:''
					,ql:''
					,dfxFile:''
					,dfxScript:''
					,dfxParams:''
					,inputFiles:''
					,currTable:''
					,tableNames:''
					,tableName:''
					,dqlConf:{}
					,dataId:''//缓存的数据ID
					,rowCount:0
					,_status:''
				}
			],rpxs:[
				{
					name:'报表名称'
					,dataSet:''
					//,dataSetLevel:'none/calc/where/group/having/order'
					,_hasAggr:'0/1'
					,_status:'为空表示正确，不为空是失效的具体信息'
					,type:1自定义分析报表/2模板报表
					,dialog:{
						open:0/1
						,top:100
						,left:100
						,width:500
						,height:400
					}
					,reportId:''
					,structType:1:单条记录，全是统计字段/2:明细报表/3:分组及交叉报表
					,template:''
					,autoCalc:0/1
					,isRowData:0/1
					,lefts:[
						name:''
						,src:'字段信息'
						,dataType:''//原始字段数据类型/或聚合后计算字段的表达式
						,srcName:''//原始字段名称
						,srcEdit:''//编辑风格
						,aggr:''
						,use:1
						,order:0无序/1升序/2降序
						,groups:[lefts,tops里的分组，空分组表示整体聚合]/null表示随分组自动聚合
						,analyse:{//指标字段
							analyseName:'占比/排名/比上期/比同期'
							,field:'被分析的测度字段'
							,scopeGroups:[空则表示针对全部]
						}
						,exp:''//聚合后的计算字段，要求聚合指标在同一个层次上，否则计算出来的数据没意义。
						,where:{conf:[]} //去掉,exp:"",disp:""
						,having:{conf:[]}
						,format:''
						,macroName:''
						,_finalType:''//加上聚合后的最终数据类型
						,_parentType:'top/left/field'
						,_fieldType:'group/detail/aggr/analyse'
						,_status:'为空表示正确，不为空是失效的具体信息'
					]
					,tops:[]
					,fields:[]
					,where:{conf:[]}
					,calcs:[]
				}
			]
		}
		*/
	,set : function(str) {
		var a = JSON.parse(str.replaceAll("<d_q>",'"'));
		aly.setAfter(a);
		for (var i=0; i<a.rpxs.length; i++){
			a.rpxs[i]._firstOpen = true;
		}
		return a;
	}
	,setAfter : function(a) {
		for (var i=0; i<a.dataSets.length; i++) {
			var dsi = a.dataSets[i];
			dsi.ql = dsi.ql.replaceAll("<d__q>",'"');
			dsi.dfxParams = dsi.dfxParams.replaceAll("<d__q>",'"');
			dsi.dfxScript = dsi.dfxScript.replaceAll("<d__q>",'"').replaceAll('<_n_>',"\\n").replaceAll('<_t_>',"\\t");
			domUtils.setAfter(dsi.dqlConf);
			if (dsi.fields) {
				for (var j=0; j<dsi.fields.length; j++) {
					var fj = dsi.fields[j];
					if (fj.exp) fj.exp = fj.exp.replaceAll("<d__q>",'"');
				}
			}
		}

		for (var i=0; i<a.rpxs.length; i++) {
			//var rpxi = a.rpxs[i];
		}
	}
	,toString : function(rqa) {
		if (!rqa) rqa = rqAnalyse;
		var cp = JSON.parse(JSON.stringify(rqa));
		aly.toStringBefore(cp);
		return JSON.stringify(cp).replaceAll('"','<d_q>');
	}
	,toStringBefore : function(a) {
		for (var i=0; i<a.dataSets.length; i++) {
			var dsi = a.dataSets[i];
			dsi.ql = dsi.ql.replaceAll('"',"<d__q>");
			dsi.dfxParams = dsi.dfxParams.replaceAll('"',"<d__q>");
			dsi.dfxScript = dsi.dfxScript.replaceAll('"',"<d__q>").replaceAll("\\n",'<_n_>').replaceAll("\\t",'<_t_>');
			domUtils.toStringBefore(dsi.dqlConf);
			if (dsi.fields) {
				for (var j=0; j<dsi.fields.length; j++) {
					var fj = dsi.fields[j];
					if (fj.exp) fj.exp = fj.exp.replaceAll('"',"<d__q>");
				}
			}
		}

		for (var i=0; i<a.rpxs.length; i++) {
			//var rpxi = a.rpxs[i];
		}
	}
	//,rpxBak : null
	,before : function(obj) {
		aly.rpxBak = rqAnalyse;
		rqAnalyse = obj;
	}
	,after : function() {
		rqAnalyse = aly.rpxBak;
	}
	,checkDataSetConf : function(dataSet) {
		var ds = aly.getDataSet(dataSet);
		ds._status = '';
		if (ds.type == 2) {
			if (ds.dataSource == '') ds._status = '数据集信息不全，缺少数据源';
			if (ds.ql == '') ds._status = '数据集信息不全，缺少查询语句';
		} else if (ds.type == 3) {
			if (ds.dfxFile == '') ds._status = '数据集信息不全，缺少dfx文件';
		} else if (ds.type == 4) {
			if (ds.dfxScript == '') ds._status = '数据集信息不全，缺少dfx脚本';
		} else if (ds.type == 5) {
			if (ds.inputFiles == '') ds._status = '数据集信息不全，缺少填报数据文件';
		} else if (ds.type == 6) {
			if (ds.dataSource == '') ds._status = '数据集信息不全，缺少数据源';
			if (ds.fixedTable == '') ds._status = '数据集信息不全，缺少要分析的表';
		} else {
			ds._status = '不认识数据集类型';
		}
	}
	,checkDataSetFields : function (dataSet) {
		var ds = aly.getDataSet(dataSet);
		if (ds.type!=6 && ds.fields == null) {
			$.ajax({
				type: 'POST',
				async : false,
				url: contextPath + "/DLServletAjax?d="+new Date().getTime(),
				data: {action:2,oper:'getTableInfo',dataId:ds.dataId,dataFileType:guideConf.dataFileType},
				success: function(data){
					if (data.indexOf('error:')==0) {
						ds._status = data.substring(6);
						return;
					}
					data = JSON.parse(data.replaceAll('<d_q>','"'));
					ds.fields = data.fields;
				}
			});
		}
		return true;
	}
	,queryDataSet : function(reQuery,dataSet,callback) {
		var ds = aly.getDataSet(dataSet);
		//if ()
		//{
		//}
		$.ajax({
			type: 'POST',
			async : false,
			url: contextPath + "/DLServletAjax?d="+new Date().getTime(),
			data: {action:2,oper:'query',reQuery:reQuery,dataId:ds.dataId,type:ds.type,dataSource:ds.dataSource,ql:ds.ql,dfxFile:ds.dfxFile,dfxScript:ds.dfxScript,dfxParams:ds.dfxParams,inputFiles:ds.inputFiles,filter:'',currTable:ds.currTable,dataFileType:guideConf.dataFileType},
			success: function(data){
				if (data.indexOf('error:')==0) {
					ds._status = data.substring(6);
					return;
				}
				callback(data);
			}
		});
	}
	,showDataSetRpxs : function(dataSet,lastRpxFunc) {
		var ds = aly.getDataSet(dataSet);
		if (guideConf.showHistoryRpx != 'yes'){
			aly.checkDataSetConf(dataSet);
			aly.checkDataSetFields(dataSet);
			if (ds._status != '') {
				alert(ds._status);
				return;
			}
		}
		/*
		if (!aly.getDataSetField(ds,"静态的")) {
			ds.fields.push({
				name:'静态的'
				,dataType:2
				,edit:''
				,exp:'"aa"'
			});
		}
		*/
		for (var i=0; i<rqAnalyse.rpxs.length; i++) {
			var rpx = rqAnalyse.rpxs[i];
			if (rpx.dataSet != dataSet) continue;
			var cnt = rpx.tops.length+rpx.lefts.length+rpx.fields.length;
			//alert(rpx.fields.length);
			//if (ds.fields != null && cnt == 0 && guideConf.emptyReport != 'yes') rpx.fields = aly.initFields(ds.fields);
			if (!rpx.dialog){
				rpx[i].dialog = {open:1,top:100,left:100+100*i,width:500,height:300};
			}
			if (!rpx.autoCalc) rpx.autoCalc = 1;
			//console.log(rqAnalyse.currRpx + "--------" + dataSet);
			if (rqAnalyse.currRpx == rpx.name) {
				//aly.refresh();
			} else if (rpx.dialog.open == 1) aly.refreshReport(rpx.name, false, false);
			initRpxCount++;
			lastRpxFunc();
		}
	}
	/*
	,load : function(str) {
		if (str) {
			rqAnalyse = JSON.parse(str.replaceAll('<d_q>','"'));
			for (var i=0; i<rqAnalyse.rpxs.length; i++) {
				if (rqAnalyse.rpxs[i].dialog.open == 1) aly.refreshReport(rqAnalyse.rpxs[i].name, false, false);
			}
		} else rqAnalyse = {srcDs:null,items:[],confs:[],editStyles:[],maxId:0};
		aly.refresh();
	}
	,init : function(dataId,str) {
		var src = JSON.parse(str.replaceAll('<d_q>','"'));
		rqAnalyse.srcDs = {fields:[],resource:{rqAnalyse:src,type:1,id:dataId}};
		aly.generateReportConf();
		aly.refresh();
	}
	var guideConf = {};
	var lmdStr = "({tables:[{name:'客户',dispName:'客户',type:0,desc:'',fields:[{name:'客户',type:2,desc:'',dispName:'客户',aliases:'',aggrs:[],edit:'',format:'',pk:1,dim:'客户'},{name:'名称',type:2,desc:'',dispName:'名称',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''},{name:'联系人姓名',type:2,desc:'',dispName:'联系人姓名',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''},{name:'联系人职务',type:2,desc:'',dispName:'联系人职务',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''},{name:'市',type:1,desc:'',dispName:'市',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:'市',destTable:'市',destLevels:[{name:'省',dest:'省.省'},{name:'大区',dest:'大区.大区'}]}],fks:[{name:'fk_城市',hide:0,destTable:'市',desc:'',dispName:'fk_城市',aliases:'',aggrs:[],format:'',fields:['市']}]},{name:'VIP客户',dispName:'VIP客户',type:0,desc:'',fields:[{name:'VIP客户',type:2,desc:'',dispName:'VIP客户',aliases:'',aggrs:[],edit:'',format:'',pk:1,dim:'客户'},{name:'VIP级别',type:2,desc:'',dispName:'VIP级别',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''},{name:'VIP折扣率',type:1,desc:'',dispName:'VIP折扣率',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''},{name:'VIP起始时间',type:5,desc:'',dispName:'VIP起始时间',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''},{name:'VIP结束时间',type:5,desc:'',dispName:'VIP结束时间',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''}],fks:[]},{name:'雇员',dispName:'雇员',type:0,desc:'',fields:[{name:'雇员',type:1,desc:'',dispName:'雇员',aliases:'',aggrs:[],edit:'',format:'',pk:1,dim:'雇员'},{name:'姓名',type:2,desc:'',dispName:'姓名',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''},{name:'职务',type:2,desc:'',dispName:'职务',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''},{name:'出生日期',type:3,desc:'',dispName:'出生日期',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:'日期',destLevels:[{name:'年月',dest:'年月.年月'},{name:'年',dest:'年.年'}]},{name:'雇佣日期',type:3,desc:'',dispName:'雇佣日期',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:'日期',destLevels:[{name:'年月',dest:'年月.年月'},{name:'年',dest:'年.年'}]},{name:'市',type:1,desc:'',dispName:'市',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:'市',destTable:'市',destLevels:[{name:'省',dest:'省.省'},{name:'大区',dest:'大区.大区'}]},{name:'上级',type:1,desc:'',dispName:'上级',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:'雇员',destTable:'雇员'}],fks:[{name:'fk2',hide:0,destTable:'雇员',desc:'',dispName:'fk2',aliases:'',aggrs:[],format:'',fields:['上级']},{name:'fk3',hide:1,destTable:'日期',desc:'',dispName:'fk3',aliases:'',aggrs:[],format:'',fields:['雇佣日期']},{name:'fk4',hide:1,destTable:'日期',desc:'',dispName:'fk4',aliases:'',aggrs:[],format:'',fields:['出生日期']},{name:'fk_城市',hide:0,destTable:'市',desc:'',dispName:'fk_城市',aliases:'',aggrs:[],format:'',fields:['市']}]},{name:'客户经理',dispName:'客户经理',type:0,desc:'',fields:[{name:'客户经理',type:1,desc:'',dispName:'客户经理',aliases:'',aggrs:[],edit:'',format:'',pk:1,dim:''},{name:'责任大区',type:1,desc:'',dispName:'责任大区',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:'大区',destTable:'大区'}],fks:[{name:'fk_地区',hide:0,destTable:'大区',desc:'',dispName:'fk_地区',aliases:'',aggrs:[],format:'',fields:['责任大区']}]},{name:'供应商',dispName:'供应商',type:0,desc:'',fields:[{name:'供应商',type:1,desc:'',dispName:'供应商',aliases:'',aggrs:[],edit:'',format:'',pk:1,dim:'供应商'},{name:'名称',type:2,desc:'',dispName:'名称',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''},{name:'联系人姓名',type:2,desc:'',dispName:'联系人姓名',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''},{name:'联系人职务',type:2,desc:'',dispName:'联系人职务',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''},{name:'市',type:2,desc:'',dispName:'市',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:'市',destTable:'市',destLevels:[{name:'省',dest:'省.省'},{name:'大区',dest:'大区.大区'}]}],fks:[{name:'fk_城市',hide:0,destTable:'市',desc:'',dispName:'fk_城市',aliases:'',aggrs:[],format:'',fields:['市']}]},{name:'产品',dispName:'产品',type:0,desc:'',fields:[{name:'产品',type:1,desc:'',dispName:'产品',aliases:'',aggrs:[],edit:'',format:'',pk:1,dim:'产品'},{name:'名称',type:2,desc:'',dispName:'名称',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''},{name:'供应商',type:1,desc:'',dispName:'供应商',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:'供应商',destTable:'供应商'},{name:'产品类别',type:1,desc:'',dispName:'产品类别',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:'产品类别',destTable:'产品类别'},{name:'单价',type:1,desc:'',dispName:'单价',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''},{name:'库存量',type:1,desc:'',dispName:'库存量',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''}],fks:[{name:'fk1',hide:0,destTable:'供应商',desc:'',dispName:'fk1',aliases:'',aggrs:[],format:'',fields:['供应商']},{name:'fk2',hide:0,destTable:'产品类别',desc:'',dispName:'fk2',aliases:'',aggrs:[],format:'',fields:['产品类别']}]},{name:'产品类别',dispName:'产品类别',type:0,desc:'',fields:[{name:'产品类别',type:1,desc:'',dispName:'产品类别',aliases:'',aggrs:[],edit:'',format:'',pk:1,dim:'产品类别'},{name:'名称',type:2,desc:'',dispName:'名称',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''},{name:'说明',type:2,desc:'',dispName:'说明',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''}],fks:[]},{name:'订单明细',dispName:'订单明细',type:0,desc:'',fields:[{name:'编号',type:1,desc:'',dispName:'编号',aliases:'',aggrs:[],edit:'',format:'',pk:1,dim:'订单',destTable:'订单'},{name:'产品',type:1,desc:'',dispName:'产品',aliases:'',aggrs:[],edit:'',format:'',pk:1,dim:'产品',destTable:'产品'},{name:'数量',type:1,desc:'',dispName:'数量',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''},{name:'折扣',type:1,desc:'',dispName:'折扣',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''},{name:'金额',type:1,desc:'',dispName:'金额',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''}],fks:[{name:'fk1',hide:0,destTable:'订单',desc:'',dispName:'fk1',aliases:'',aggrs:[],format:'',fields:['编号']},{name:'fk2',hide:0,destTable:'产品',desc:'',dispName:'fk2',aliases:'',aggrs:[],format:'',fields:['产品']}]},{name:'订单',dispName:'订单',type:0,desc:'',fields:[{name:'编号',type:1,desc:'',dispName:'编号',aliases:'',aggrs:[],edit:'',format:'',pk:1,dim:'订单'},{name:'客户',type:2,desc:'',dispName:'客户',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:'客户',destTable:'客户'},{name:'雇员',type:1,desc:'',dispName:'雇员',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''},{name:'签单日期',type:3,desc:'',dispName:'签单日期',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:'日期',destLevels:[{name:'年月',dest:'年月.年月'},{name:'年',dest:'年.年'}]},{name:'发货日期',type:3,desc:'',dispName:'发货日期',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:'日期',destLevels:[{name:'年月',dest:'年月.年月'},{name:'年',dest:'年.年'}]}],fks:[{name:'fk3',hide:1,destTable:'日期',desc:'',dispName:'fk3',aliases:'',aggrs:[],format:'',fields:['签单日期']},{name:'fk4',hide:1,destTable:'日期',desc:'',dispName:'fk4',aliases:'',aggrs:[],format:'',fields:['发货日期']},{name:'fk1',hide:0,destTable:'客户',desc:'',dispName:'fk1',aliases:'',aggrs:[],format:'',fields:['客户']}]},{name:'入库单',dispName:'入库单',type:0,desc:'',fields:[{name:'编号',type:1,desc:'',dispName:'编号',aliases:'',aggrs:[],edit:'',format:'',pk:1,dim:''},{name:'入库日期',type:3,desc:'',dispName:'入库日期',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:'日期',destLevels:[{name:'年月',dest:'年月.年月'},{name:'年',dest:'年.年'}]},{name:'产品',type:1,desc:'',dispName:'产品',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:'产品',destTable:'产品'},{name:'入库数量',type:1,desc:'',dispName:'入库数量',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''},{name:'经办人',type:1,desc:'',dispName:'经办人',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:'雇员',destTable:'雇员'},{name:'入库金额',type:1,desc:'',dispName:'入库金额',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''}],fks:[{name:'fk1',hide:1,destTable:'日期',desc:'',dispName:'fk1',aliases:'',aggrs:[],format:'',fields:['入库日期']},{name:'fk2',hide:0,destTable:'产品',desc:'',dispName:'fk2',aliases:'',aggrs:[],format:'',fields:['产品']},{name:'fk3',hide:0,destTable:'雇员',desc:'',dispName:'fk3',aliases:'',aggrs:[],format:'',fields:['经办人']}]},{name:'回款单',dispName:'回款单',type:0,desc:'',fields:[{name:'编号',type:1,desc:'',dispName:'编号',aliases:'',aggrs:[],edit:'',format:'',pk:1,dim:''},{name:'回款日期',type:3,desc:'',dispName:'回款日期',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:'日期',destLevels:[{name:'年月',dest:'年月.年月'},{name:'年',dest:'年.年'}]},{name:'客户',type:2,desc:'',dispName:'客户',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:'客户',destTable:'客户'},{name:'金额',type:1,desc:'',dispName:'金额',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''},{name:'销售',type:1,desc:'',dispName:'销售',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''},{name:'客户VIP结束年月',desc:'',dispName:'客户VIP结束年月',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''}],fks:[{name:'fk1',hide:0,destTable:'客户',desc:'',dispName:'fk1',aliases:'',aggrs:[],format:'',fields:['客户']},{name:'fk3',hide:1,destTable:'日期',desc:'',dispName:'fk3',aliases:'',aggrs:[],format:'',fields:['回款日期']}]},{name:'支付单',dispName:'支付单',type:0,desc:'',fields:[{name:'编号',type:1,desc:'',dispName:'编号',aliases:'',aggrs:[],edit:'',format:'',pk:1,dim:''},{name:'支付日期',type:3,desc:'',dispName:'支付日期',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:'日期',destLevels:[{name:'年月',dest:'年月.年月'},{name:'年',dest:'年.年'}]},{name:'支付金额',type:1,desc:'',dispName:'支付金额',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''},{name:'供应商',type:1,desc:'',dispName:'供应商',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:'供应商',destTable:'供应商'},{name:'经办人',type:1,desc:'',dispName:'经办人',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:'雇员',destTable:'雇员'},{name:'审核人',type:1,desc:'',dispName:'审核人',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:'雇员',destTable:'雇员'},{name:'手续费',type:1,desc:'',dispName:'手续费',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''}],fks:[{name:'fk1',hide:1,destTable:'日期',desc:'',dispName:'fk1',aliases:'',aggrs:[],format:'',fields:['支付日期']},{name:'fk2',hide:0,destTable:'供应商',desc:'',dispName:'fk2',aliases:'',aggrs:[],format:'',fields:['供应商']},{name:'fk3',hide:0,destTable:'雇员',desc:'',dispName:'fk3',aliases:'',aggrs:[],format:'',fields:['审核人']},{name:'fk4',hide:0,destTable:'雇员',desc:'',dispName:'fk4',aliases:'',aggrs:[],format:'',fields:['经办人']}]},{name:'支付月汇总',dispName:'支付月汇总',type:0,desc:'',hide:1,fields:[{name:'年月',type:1,desc:'',dispName:'年月',aliases:'',aggrs:[],edit:'',format:'',pk:1,dim:'年月',destLevels:[{name:'年',dest:'年.年'}]},{name:'支付总额',type:1,desc:'',dispName:'支付总额',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''}],fks:[]},{name:'支付供应商汇总',dispName:'支付供应商汇总',type:0,desc:'',hide:1,fields:[{name:'供应商',type:1,desc:'',dispName:'供应商',aliases:'',aggrs:[],edit:'',format:'',pk:1,dim:'供应商'},{name:'支付总额',type:1,desc:'',dispName:'支付总额',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''}],fks:[]},{name:'支付供应商月汇总',dispName:'支付供应商月汇总',type:0,desc:'',hide:1,fields:[{name:'年月',type:1,desc:'',dispName:'年月',aliases:'',aggrs:[],edit:'',format:'',pk:1,dim:'年月',destTable:'支付月汇总',destLevels:[{name:'年',dest:'年.年'}]},{name:'供应商',type:1,desc:'',dispName:'供应商',aliases:'',aggrs:[],edit:'',format:'',pk:1,dim:'供应商',destTable:'供应商'},{name:'支付总额',type:1,desc:'',dispName:'支付总额',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''}],fks:[{name:'fk1',hide:0,destTable:'支付月汇总',desc:'',dispName:'fk1',aliases:'',aggrs:[],format:'',fields:['年月']},{name:'fk2',hide:0,destTable:'供应商',desc:'',dispName:'fk2',aliases:'',aggrs:[],format:'',fields:['供应商']}]},{name:'入库单汇总',dispName:'入库单汇总',type:0,desc:'',hide:1,fields:[{name:'年月',type:1,desc:'',dispName:'年月',aliases:'',aggrs:[],edit:'',format:'',pk:1,dim:'年月',destTable:'支付月汇总',destLevels:[{name:'年',dest:'年.年'}]},{name:'供应商',type:1,desc:'',dispName:'供应商',aliases:'',aggrs:[],edit:'',format:'',pk:1,dim:''},{name:'类别',type:1,desc:'',dispName:'类别',aliases:'',aggrs:[],edit:'',format:'',pk:1,dim:''},{name:'产品',type:1,desc:'',dispName:'产品',aliases:'',aggrs:[],edit:'',format:'',pk:1,dim:''},{name:'总数量',type:1,desc:'',dispName:'总数量',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''},{name:'总金额',type:1,desc:'',dispName:'总金额',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''}],fks:[{name:'fk1',hide:0,destTable:'支付月汇总',desc:'',dispName:'fk1',aliases:'',aggrs:[],format:'',fields:['年月']},{name:'fk2',hide:0,destTable:'供应商',desc:'',dispName:'fk2',aliases:'',aggrs:[],format:'',fields:['供应商']},{name:'fk4',hide:0,destTable:'产品',desc:'',dispName:'fk4',aliases:'',aggrs:[],format:'',fields:['产品']}]},{name:'日期',dispName:'日期',type:2,desc:'',fields:[{name:'日期',type:3,desc:'',dispName:'日期',aliases:'',aggrs:[],edit:'',format:'',pk:1,dim:'日期',destLevels:[{name:'年月',dest:'年月.年月'},{name:'年',dest:'年.年'}]}],fks:[]},{name:'年月',dispName:'年月',type:2,desc:'',fields:[{name:'年月',type:1,desc:'',dispName:'年月',aliases:'',aggrs:[],edit:'',format:'',pk:1,dim:'年月',destLevels:[{name:'年',dest:'年.年'}]}],fks:[]},{name:'年',dispName:'年',type:2,desc:'',fields:[{name:'年',type:1,desc:'',dispName:'年',aliases:'',aggrs:[],edit:'',format:'',pk:1,dim:'年'}],fks:[]},{name:'大区',dispName:'大区',type:0,desc:'',fields:[{name:'大区',type:1,desc:'',dispName:'大区',aliases:'',aggrs:[],edit:'',format:'',pk:1,dim:'大区'},{name:'名称',type:2,desc:'',dispName:'名称',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''}],fks:[]},{name:'省',dispName:'省',type:0,desc:'',fields:[{name:'省',type:1,desc:'',dispName:'省',aliases:'',aggrs:[],edit:'',format:'',pk:1,dim:'省',destLevels:[{name:'大区',dest:'大区.大区'}]},{name:'名称',type:2,desc:'',dispName:'名称',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''}],fks:[]},{name:'市',dispName:'市',type:0,desc:'',fields:[{name:'市',type:1,desc:'',dispName:'市',aliases:'',aggrs:[],edit:'',format:'',pk:1,dim:'市',destLevels:[{name:'省',dest:'省.省'},{name:'大区',dest:'大区.大区'}]},{name:'名称',type:2,desc:'',dispName:'名称',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''},{name:'人口',type:1,desc:'',dispName:'人口',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''}],fks:[]},{name:'订单总表',dispName:'订单总表',type:3,desc:'',fields:[{name:'订单编号',type:1,desc:'',dispName:'订单编号',aliases:'',aggrs:[],edit:'',format:'',pk:1,dim:''}],fks:[]},{name:'员工',dispName:'员工',type:0,desc:'',fields:[{name:'编号',type:1,desc:'',dispName:'编号',aliases:'',aggrs:[],edit:'',format:'',pk:1,dim:'员工'},{name:'姓名',type:2,desc:'',dispName:'姓名',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''},{name:'部门',type:1,desc:'',dispName:'部门',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:'部门',destTable:'部门'},{name:'国籍',type:2,desc:'',dispName:'国籍',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''}],fks:[{name:'fk1',hide:0,destTable:'部门',desc:'',dispName:'fk1',aliases:'',aggrs:[],format:'',fields:['部门']}]},{name:'部门',dispName:'部门',type:0,desc:'',fields:[{name:'编号',type:1,desc:'',dispName:'编号',aliases:'',aggrs:[],edit:'',format:'',pk:1,dim:'部门'},{name:'名称',type:2,desc:'',dispName:'名称',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:''},{name:'经理',type:1,desc:'',dispName:'经理',aliases:'',aggrs:[],edit:'',format:'',pk:0,dim:'员工',destTable:'员工'}],fks:[{name:'fk1',hide:0,destTable:'员工',desc:'',dispName:'fk1',aliases:'',aggrs:[],format:'',fields:['经理']}]}],dims:[{name:'客户',dispName:'客户',sql:'SELECT t1.客户 AS 客户, t1.名称 AS 名称 FROM 客户 t1 ORDER BY t1.客户 ASC',code:'客户',table:'客户',field:'客户'},{name:'雇员',dispName:'雇员',sql:'SELECT t1.雇员 AS 雇员, t1.姓名 AS 姓名 FROM 雇员 t1 ORDER BY t1.雇员 ASC',code:'雇员',table:'雇员',field:'雇员'},{name:'客户经理',dispName:'客户经理',sql:'SELECT t1.客户经理 AS 客户经理, t1.姓名 AS 姓名 FROM 客户经理 t1 ORDER BY t1.客户经理 ASC',code:'客户经理',table:'客户经理',field:'客户经理'},{name:'供应商',dispName:'供应商',sql:'SELECT t1.供应商 AS 供应商, t1.名称 AS 名称 FROM 供应商 t1 ORDER BY t1.供应商 ASC',code:'供应商',table:'供应商',field:'供应商'},{name:'产品',dispName:'产品',sql:'SELECT t1.产品 AS 产品, t1.名称 AS 名称 FROM 产品 t1 ORDER BY t1.产品 ASC',code:'产品',table:'产品',field:'产品'},{name:'产品类别',dispName:'产品类别',sql:'SELECT t1.产品类别 AS 产品类别, t1.名称 AS 名称 FROM 产品类别 t1 ORDER BY t1.产品类别 ASC',code:'产品类别',table:'产品类别',field:'产品类别'},{name:'订单',dispName:'订单',table:'订单',field:'编号'},{name:'入库单',dispName:'入库单',table:'入库单',field:'编号'},{name:'回款单',dispName:'回款单',table:'回款单',field:'编号'},{name:'支付单',dispName:'支付单',table:'支付单',field:'编号'},{name:'日期',dt:5,exp:'date(\\'?1-?2-?3\\')',dispName:'日期',table:'日期',field:'日期',destLevels:[{name:'年月',dest:'年月.年月',formula:'year(?)*100+month(?)'},{name:'年',dest:'年.年',formula:'year(?)'}]},{name:'年月',dt:4,exp:'?1*100+?2',dispName:'年月',table:'年月',field:'年月',destLevels:[{name:'年',dest:'年.年',formula:'int(?/100)'}]},{name:'年',dispName:'年',table:'年',field:'年'},{name:'订单2011',dispName:'订单2011',table:'订单2011',field:'订单编号'},{name:'订单2012',dispName:'订单2012',table:'订单2012',field:'订单编号'},{name:'大区',dispName:'大区',sql:'SELECT t1.大区 AS 大区, t1.名称 AS 名称 FROM 大区 t1 ORDER BY t1.大区 ASC',code:'大区',table:'大区',field:'大区'},{name:'省',dispName:'省',sql:'SELECT t1.省 AS 省, t1.名称 AS 名称 FROM 省 t1 ORDER BY t1.省 ASC',code:'省',table:'省',field:'省',destLevels:[{name:'大区',dest:'大区.大区',formula:'int(?/100)'}]},{name:'市',dispName:'市',sql:'SELECT t1.市 AS 市, t1.名称 AS 名称 FROM 市 t1 ORDER BY t1.市 ASC',code:'市',table:'市',field:'市',destLevels:[{name:'省',dest:'省.省',formula:'int(?/100)'},{name:'大区',dest:'大区.大区',formula:'int(?/10000)'}]},{name:'订单总表',dispName:'订单总表',table:'订单总表',field:'订单编号'},{name:'员工',dispName:'员工',table:'员工',field:'编号'},{name:'部门',dispName:'部门',table:'部门',field:'编号'}],levels:[],annexTables:[[{name:'客户',pks:['客户']},{name:'VIP客户',pks:['VIP客户']}],[{name:'供应商',pks:['供应商']},{name:'支付供应商汇总',pks:['供应商']}],[{name:'支付月汇总',pks:['年月']},{name:'年月',pks:['年月']}]],classTables:[{name:'国内公司',tables:[{name:'客户',fields:[],locators:[]},{name:'VIP客户',fields:['VIP级别','折扣率','起始时间','结束时间','fk1','fk2','fk3','客户'],locators:[]},{name:'雇员',fields:['姓名','职务','出生日期','雇佣日期','上级','fk2','fk3','fk4','fk_城市','雇员','市'],locators:[]},{name:'供应商',fields:['联系人姓名','联系人职务','fk_城市','名称','供应商','市'],locators:[]},{name:'产品',fields:['单价','库存量','fk1','fk2','名称','产品','供应商','产品类别'],locators:[]},{name:'订单明细',fields:['数量','折扣','fk1','fk2','产品','编号'],locators:[]},{name:'订单',fields:['签单日期','发货日期','fk1','fk2','fk3','fk4','普通签单金额','VIP签单金额','编号','客户','雇员'],locators:[]},{name:'入库单',fields:['入库日期','入库数量','经办人','fk1','fk2','fk3','入库金额','入库单','产品'],locators:[]},{name:'回款单',fields:['ID','回款日期','客户','金额','fk1','fk2','fk3','客户VIP结束年月','销售'],locators:[]},{name:'支付单',fields:['ID','支付日期','支付金额','供应商','经办人','审核人','fk1','fk2','fk3','fk4','手续费'],locators:[]},{name:'日期',fields:['日期'],locators:[]},{name:'年月',fields:['年月'],locators:[]},{name:'年',fields:['年'],locators:[]},{name:'大区',fields:['名称','大区'],locators:[]},{name:'省',fields:['名称','省'],locators:[]},{name:'市',fields:['名称','人口','市'],locators:[]},{name:'产品类别',fields:['产品类别','说明','名称'],locators:[]},{name:'客户经理',fields:['客户经理','责任大区','fk1','fk_地区'],locators:[]}],dims:[]},{name:'海外公司',tables:[{name:'订单总表',fields:['订单'],locators:[]},{name:'员工',fields:['部门','国籍','fk1','编号','姓名'],locators:[]},{name:'部门',fields:['fk1','编号','名称','经理'],locators:[]}],dims:[]}],editStyles:[]})"
	var grpxStr = '';
	guideConf.view = 'source';
	out.println("guideConf.dataSource = '"+dataSource+"';");
	out.println("guideConf.dataSources = '"+DataSphereServlet.DATASOURCES+"';");
	out.println("guideConf.dqlDataSources = '"+DataSphereServlet.DQLDATASOURCES+"';");
	out.println("guideConf.dataId = '"+dataId+"';");
	out.println("guideConf.ql = \""+guideEncode(ql)+"\";");
	out.println("guideConf.dfxFile = \""+guideEncode(dfxFile)+"\";");
	out.println("guideConf.dfxScript = \""+guideEncode(dfxScript)+"\";");
	out.println("guideConf.dfxParams = \""+guideEncode(dfxParams)+"\";");
	out.println("guideConf.inputFiles = \""+guideEncode(inputFiles)+"\";");
	out.println("guideConf.currTable = '"+currTable+"';");
	out.println("guideConf.tableNames = ["+tableNames+"];");
	out.println("guideConf.reportPage = '"+reportPage+"';");
	
	var existGrpx = '';
	var existDfx = ['dataSource.dfx'];
	var existInputFiles = ['/artDialog/_doc/ajaxContent/content.json','/bak/escalc_demo/artDialog/_doc/ajaxContent/content.json','/dl/js/artDialog/_doc/ajaxContent/content.json','/dl/js/chosen_v1.5.1/bower.json','/dl/js/jquery-dropdown/component.json','/dl/js/jquery-ui-1.10.3/package.json','/dl/js/jquery-ui-1.10.3/ui.accordion.jquery.json','/dl/js/jquery-ui-1.10.3/ui.autocomplete.jquery.json','/dl/js/jquery-ui-1.10.3/ui.button.jquery.json','/dl/js/jquery-ui-1.10.3/ui.core.jquery.json','/dl/js/jquery-ui-1.10.3/ui.datepicker.jquery.json','/dl/js/jquery-ui-1.10.3/ui.dialog.jquery.json','/dl/js/jquery-ui-1.10.3/ui.draggable.jquery.json','/dl/js/jquery-ui-1.10.3/ui.droppable.jquery.json','/dl/js/jquery-ui-1.10.3/ui.effect-blind.jquery.json','/dl/js/jquery-ui-1.10.3/ui.effect-bounce.jquery.json','/dl/js/jquery-ui-1.10.3/ui.effect-clip.jquery.json','/dl/js/jquery-ui-1.10.3/ui.effect-drop.jquery.json','/dl/js/jquery-ui-1.10.3/ui.effect-explode.jquery.json','/dl/js/jquery-ui-1.10.3/ui.effect-fade.jquery.json','/dl/js/jquery-ui-1.10.3/ui.effect-fold.jquery.json','/dl/js/jquery-ui-1.10.3/ui.effect-highlight.jquery.json','/dl/js/jquery-ui-1.10.3/ui.effect-pulsate.jquery.json','/dl/js/jquery-ui-1.10.3/ui.effect-scale.jquery.json','/dl/js/jquery-ui-1.10.3/ui.effect-shake.jquery.json','/dl/js/jquery-ui-1.10.3/ui.effect-slide.jquery.json','/dl/js/jquery-ui-1.10.3/ui.effect-transfer.jquery.json','/dl/js/jquery-ui-1.10.3/ui.effect.jquery.json','/dl/js/jquery-ui-1.10.3/ui.menu.jquery.json','/dl/js/jquery-ui-1.10.3/ui.mouse.jquery.json','/dl/js/jquery-ui-1.10.3/ui.position.jquery.json','/dl/js/jquery-ui-1.10.3/ui.progressbar.jquery.json','/dl/js/jquery-ui-1.10.3/ui.resizable.jquery.json','/dl/js/jquery-ui-1.10.3/ui.selectable.jquery.json','/dl/js/jquery-ui-1.10.3/ui.slider.jquery.json','/dl/js/jquery-ui-1.10.3/ui.sortable.jquery.json','/dl/js/jquery-ui-1.10.3/ui.spinner.jquery.json','/dl/js/jquery-ui-1.10.3/ui.tabs.jquery.json','/dl/js/jquery-ui-1.10.3/ui.tooltip.jquery.json','/dl/js/jquery-ui-1.10.3/ui.widget.jquery.json','/dl/js/selectBoxIt/bower.json','/dl/js/selectBoxIt/package.json','/dl/js/selectric/package.json','/dl/js/selectric/selectric.jquery.json','/dl/js/treetable/treetable.jquery.json','/dl/js/ztree/zTree.v3.jquery.json','/guide20160815/artDialog/_doc/ajaxContent/content.json','/guide20160815/dl/js/artDialog/_doc/ajaxContent/content.json','/guide20160815/dl/js/chosen_v1.5.1/bower.json','/guide20160815/dl/js/jquery-dropdown/component.json','/guide20160815/dl/js/jquery-ui-1.10.3/package.json','/guide20160815/dl/js/jquery-ui-1.10.3/ui.accordion.jquery.json','/guide20160815/dl/js/jquery-ui-1.10.3/ui.autocomplete.jquery.json','/guide20160815/dl/js/jquery-ui-1.10.3/ui.button.jquery.json','/guide20160815/dl/js/jquery-ui-1.10.3/ui.core.jquery.json','/guide20160815/dl/js/jquery-ui-1.10.3/ui.datepicker.jquery.json','/guide20160815/dl/js/jquery-ui-1.10.3/ui.dialog.jquery.json','/guide20160815/dl/js/jquery-ui-1.10.3/ui.draggable.jquery.json','/guide20160815/dl/js/jquery-ui-1.10.3/ui.droppable.jquery.json','/guide20160815/dl/js/jquery-ui-1.10.3/ui.effect-blind.jquery.json','/guide20160815/dl/js/jquery-ui-1.10.3/ui.effect-bounce.jquery.json','/guide20160815/dl/js/jquery-ui-1.10.3/ui.effect-clip.jquery.json','/guide20160815/dl/js/jquery-ui-1.10.3/ui.effect-drop.jquery.json','/guide20160815/dl/js/jquery-ui-1.10.3/ui.effect-explode.jquery.json','/guide20160815/dl/js/jquery-ui-1.10.3/ui.effect-fade.jquery.json','/guide20160815/dl/js/jquery-ui-1.10.3/ui.effect-fold.jquery.json','/guide20160815/dl/js/jquery-ui-1.10.3/ui.effect-highlight.jquery.json','/guide20160815/dl/js/jquery-ui-1.10.3/ui.effect-pulsate.jquery.json','/guide20160815/dl/js/jquery-ui-1.10.3/ui.effect-scale.jquery.json','/guide20160815/dl/js/jquery-ui-1.10.3/ui.effect-shake.jquery.json','/guide20160815/dl/js/jquery-ui-1.10.3/ui.effect-slide.jquery.json','/guide20160815/dl/js/jquery-ui-1.10.3/ui.effect-transfer.jquery.json','/guide20160815/dl/js/jquery-ui-1.10.3/ui.effect.jquery.json','/guide20160815/dl/js/jquery-ui-1.10.3/ui.menu.jquery.json','/guide20160815/dl/js/jquery-ui-1.10.3/ui.mouse.jquery.json','/guide20160815/dl/js/jquery-ui-1.10.3/ui.position.jquery.json','/guide20160815/dl/js/jquery-ui-1.10.3/ui.progressbar.jquery.json','/guide20160815/dl/js/jquery-ui-1.10.3/ui.resizable.jquery.json','/guide20160815/dl/js/jquery-ui-1.10.3/ui.selectable.jquery.json','/guide20160815/dl/js/jquery-ui-1.10.3/ui.slider.jquery.json','/guide20160815/dl/js/jquery-ui-1.10.3/ui.sortable.jquery.json','/guide20160815/dl/js/jquery-ui-1.10.3/ui.spinner.jquery.json','/guide20160815/dl/js/jquery-ui-1.10.3/ui.tabs.jquery.json','/guide20160815/dl/js/jquery-ui-1.10.3/ui.tooltip.jquery.json','/guide20160815/dl/js/jquery-ui-1.10.3/ui.widget.jquery.json','/guide20160815/dl/js/selectBoxIt/bower.json','/guide20160815/dl/js/selectBoxIt/package.json','/guide20160815/dl/js/selectric/package.json','/guide20160815/dl/js/selectric/selectric.jquery.json','/guide20160815/dl/js/treetable/treetable.jquery.json','/guide20160815/dl/js/ztree/zTree.v3.jquery.json','/guideWeb160423/dl/js/chosen_v1.5.1/bower.json','/WEB-INF/inputDatas/7.1.3.b','/WEB-INF/inputDatas/7.1.4.b'];

		{
			name:'报表名称'
			,dataSet:''
			//,dataSetLevel:'none/calc/where/group/having/order'
			,_hasAggr:'0/1'
			,_status:'为空表示正确，不为空是失效的具体信息'
			,type:1自定义分析报表/2模板报表
			,dialog:{
				open:0/1
				,top:100
				,left:100
				,width:500
				,height:400
			}
			,reportId:''
			,structType:1:单条记录，全是统计字段/2:明细报表/3:分组及交叉报表
			,template:''
			,autoCalc:0/1
			,isRowData:0/1
			,lefts:[
				name:''
				,src:'字段信息'
				,dataType:''//原始字段数据类型
				,srcName:''//原始字段名称
				,aggr:''
				,use:1
				,order:0无序/1升序/2降序
				,groups:[lefts,tops里的分组，空分组表示整体聚合]/null表示随分组自动聚合
				,analyse:{//指标字段
					analyseName:'占比/排名/比上期/比同期'
					,field:'被分析的测度字段'
					,scopeGroups:[空则表示针对全部]
				}
				,where:{conf:[]}
				,having:{conf:[]}
				,format:''
				,_finalType:''//加上聚合后的最终数据类型
  				,_parentType:'top/left/field'
				,_fieldType:'group/detail/aggr/analyse'
				,_status:'为空表示正确，不为空是失效的具体信息'
			]
			,tops:[]
			,fields:[]
			,where:{conf:[]}
			,calcs:[]
*/
	,getDql : function(conf, rd) {
		if (!rd) rd = rqAnalyse;
		if (!conf) conf = aly.getCurrRpx();
		var dims = [];
		var dimNames = [];
		var bys = [];
		var dimWhere = '';
		var tableWhere = '';
		var fields = [];
		var fieldNames = [];
		for (var i=0; i<conf.tops.length; i++)
		{
			var topi = conf.tops[i];
			if (topi._status != '') {
				alert('请先删除无效字段');
				return null;
			}
			var iObj = getInfosObj(topi.src);
			dims.push(iObj.dim);
			dimNames.push(topi.name);
			var subTable = iObj.subTable;
			if (subTable != '') subTable = "@"+subTable;
			bys.push("T1"+subTable+"."+iObj.expNoTableNoAggr);
			if (topi.where.conf != null && topi.where.conf.length>0){
				var where = whereUtils.getExp(topi.where.conf, "T1.", 1);
				if (tableWhere != '') tableWhere += " AND "
				tableWhere += "("+where+")"
			}
		}
		for (var i=0; i<conf.lefts.length; i++)
		{
			var lefti = conf.lefts[i];
			if (lefti._status != '') {
				alert('请先删除无效字段');
				return null;
			}
			var iObj = getInfosObj(lefti.src);
			var subTable = iObj.subTable;
			if (subTable != '') subTable = "@"+subTable;
			dims.push(iObj.dim);
			dimNames.push(lefti.name);
			bys.push("T1"+subTable+"."+iObj.expNoTableNoAggr);
			if (lefti.where.conf != null && lefti.where.conf.length>0){
				var where = whereUtils.getExp(lefti.where.conf, "T1.", 1);
				if (tableWhere != '') tableWhere += " AND "
				tableWhere += "("+where+")"
			}
		}
		for (var i=0; i<conf.fields.length; i++)
		{
			var fieldi = conf.fields[i];
			if (fieldi._fieldType == 'analyse' || fieldi._fieldType == 'newAnalyse') continue;
			if (fieldi._status != '') {
				alert('请先删除无效字段');
				return null;
			}
			var iObj = getInfosObj(fieldi.src);
			var subTable = iObj.subTable;
			if (subTable != '') subTable = "@"+subTable;
			if (fieldi._fieldType == 'group') {
				dims.push(iObj.dim);
				dimNames.push(fieldi.name);
				bys.push("T1"+subTable+"."+iObj.expNoTableNoAggr);
			} else {
				if (fieldi._fieldType == 'aggr') fields.push("T1"+subTable+"."+fieldi.aggr+"("+iObj.expNoTableNoAggr+")");
				else fields.push("T1"+subTable+"."+iObj.expNoTableNoAggr);
				fieldNames.push(fieldi.name);
			}
			if (fieldi.where.conf != null && fieldi.where.conf.length>0){
				var where = whereUtils.getExp(fieldi.where.conf, "T1.", 1);
				if (tableWhere != '') tableWhere += " AND "
				tableWhere += "("+where+")"
			}
		}
		if (conf.where.conf != null && conf.where.conf.length>0){
			var where = whereUtils.getExp(conf.where.conf, "T1.", 1);
			if (tableWhere != '') tableWhere += " AND "
			tableWhere += "("+where+")"
		}
		
		var tName = aly.getDataSet(conf.dataSet).tableName;

		if (guideConf.outerCondition && guideConf.outerCondition.length>0) {
			var outerUsed = [];
			for (var j=0; j<guideConf.outerCondition.length; j++) {
				var oj = guideConf.outerCondition[j];
				if (oj.table == tName) {
					if (outerUsed.indexOf(oj.table) >= 0) break;
					outerUsed.push(oj.table);
					if (tableWhere!='') tableWhere += " AND ";
					tableWhere += "("+oj.exp.replaceAll("${T}","T1")+")";
					break;
				}
			}
			for (var j=0; j<guideConf.outerCondition.length; j++) {
				var oj = guideConf.outerCondition[j];
				if (outerUsed.indexOf(oj.table) >= 0) continue;
				var dimObj = mdUtils.getDimByTable(oj.table);
				if (!dimObj) continue;
				var infoss = new Array();
				byInfosUtil._getDims(tName, dimObj.name, infoss, 0, false);
				if (infoss.length == 0) continue;
				outerUsed.push(oj.table);
				if (tableWhere!='') tableWhere += " AND ";
				tableWhere += "("+oj.exp.replaceAll("${T}","T1."+getInfosObj(infoss[0]).expNoTable)+")";
			}
		}
				
		
		var dql = "SELECT ";
		for (var i=0; i<fields.length; i++)
		{
			if (i>0) dql += ",";
			dql += fields[i]+" " + fieldNames[i];
		}
		if (dims.length>0)
		{
			dql += " ON ";
			for (var i=0; i<dims.length; i++)
			{
				if (i>0) dql += ",";
				{
					dql += dims[i]+" "+dimNames[i];
				}
			}
		}
		dql += " FROM " + tName + " T1";
		if (tableWhere != '') dql += " WHERE " + tableWhere;
		if (dims.length>0)
		{
			dql += " BY ";
			for (var i=0; i<dims.length; i++)
			{
				if (i>0) dql += ",";
				{
					dql += bys[i];
				}
			}
		}
		return dql;
	}
	
	,getDfxExps : function(exceptWhereItem, confObj, rd) {
		if (!rd) rd = rqAnalyse;
		//var dataId = "data"+new Date().getTime();
		//var calcs = "\"aa\"+说明:a1<;>\"bb\"+a1:a2";
		//var filters = "!like(a1,\"*软*\")<;>!like(a2,\"*软*\")";
		//var fields = "说明,a1<;>说明,a2";
		//var resultExp = "groups(说明:A;count(a2):B;1)";
		//var resultExp = "id("+item.name+";100)";
		
		var resultExp = "";
		var calcs = null;
		var filters = null;
		var fields = null;
		if (!confObj) confObj = aly.getCurrRpx();
		var types = '';

		var conf = confObj;
		var dataSet = aly.getDataSet(conf.dataSet);
		var dataId = dataSet.dataId;
		if (exceptWhereItem) {
			if (guideConf.dataFileType == 'text') resultExp = "id("+exceptWhereItem.name+")";
			else resultExp = "id("+exceptWhereItem.name+";100)";
		}
		var calc = "";
		var filter = "";
		for (var j=0; j<dataSet.fields.length; j++) {
			var itemj = dataSet.fields[j];
			if (itemj.exp && itemj.exp != '') {
				if (calc != "") calc += ",";
				calc += itemj.exp + ":" + itemj.name;
			}
		}

		if (conf.where.conf != null && conf.where.conf.length>0) {
			if (filter != "") filter += " && ";
			filter += "("+whereUtils.getExp(conf.where.conf, "", 1, 2)+")";
		}
		var fs = aly.getRpxFields(conf);
		for (var j=0; j<fs.length; j++) {
			if (exceptWhereItem && exceptWhereItem.name == fs[j].srcName) continue;
			if (fs[j].where.conf == null || fs[j].where.conf.length==0) continue;
			if (filter != "") filter += " && ";
			filter += "("+whereUtils.getExp(fs[j].where.conf, "", 1, 2)+")";
		}
		if (calc == '') calc = 'no';
		if (filter == '') filter = 'no';
		if (calcs == null) {
			calcs = calc;
			filters = filter;
			fields = "no";
		} else {
			calcs += "<;>" + calc;
			filters += "<;>" + filter;
			fields += "<;>" + "no";
		}
		
		if (!exceptWhereItem) {
			//var resultExp = "groups(说明:A;count(a2):B;1)";
			if (conf.tops.length==0 && conf.lefts.length==0 && conf.fields.length==0) return null;
			if (conf.type == 1 || conf.type == 2) {
				var groups = '';
				var aggrs = '';
				var existItems = [];
				var hasGroup = false;
				for (var j=0; j<fs.length; j++) {
					if (fs[j]._fieldType != 'group') continue;
					hasGroup = true;
					var n = fs[j].name;
					//if (existItems.indexOf(e)>=0) continue;
					var fj = aly.getDataSetField(dataSet,fs[j].srcName);
					if (!fj) {
						alert("数据集结构已变动，["+n+"]字段失效");
						return null;
					}
					var e = fj.exp;
					if (e == '' || !e) e = fj.name;
					//existItems.push(n);
					if (groups != '') groups += ',';
					groups += e+":"+n;
					types += "," + n + ":" + fs[j].dataType;
				}
				for (var j=0; j<fs.length; j++) {
					if (fs[j]._fieldType != 'detail' && fs[j]._fieldType != 'aggr') continue;
					var n = fs[j].name;
					//if (existItems.indexOf(e)>=0) continue;
					var fj = aly.getDataSetField(dataSet,fs[j].srcName);
					if (!fj) {
						alert("数据集结构已变动，["+n+"]字段失效");
						return null;
					}
					var e = fj.exp;
					if (e == '' || !e) e = fj.name;
					//existItems.push(n);
					if (hasGroup ){
						if (aggrs != '') aggrs += ',';
						var aggr = fs[j].aggr;
						if (aggr == 'countd') aggr = 'icount';
						aggrs += aggr + "(" + e + "):" + n;
						types += "," + n + ":" + fs[j]._finalType;
					} else {
						if (groups != '') groups += ',';
						groups += e+":"+n;
						types += "," + n + ":" + fs[j].dataType;
					}
				}
				if (!hasGroup) {
					if (guideConf.dataFileType.toLowerCase()=='binary')
						resultExp = "new("+groups+").fetch("+guideConf.maxDataSize+")";
					else
						resultExp = "new("+groups+")";
				} else {
					var scope = groups.split(',').length;
					if (guideConf.dataFileType.toLowerCase()=='binary')
						resultExp = "groups("+groups+";"+aggrs+";"+guideConf.maxDataSize+")";
					else
						resultExp = "groups("+groups+";"+aggrs+")";
				}
			} else {
				//TODO 自定义rpx
				//return null;
			}
		}

		if (types.length>0) types = types.substring(1);
		return {calcs:calcs,filters:filters,fields:fields,resultExp:resultExp,dataId:dataId,types:types};
	}
	,getSrc : function(name, rd) {
		if (!rd) rd = rqAnalyse;
		for (var i=0; i<rd.srcDs.fields.length; i++) {
			var f = rd.srcDs.fields[i];
			if (f.name == name) return f;
		}
	}
	,getItem : function(id, rd) {
		if (!rd) rd = rqAnalyse;
		for (var i=0; i<rd.items.length; i++) {
			if (rd.items[i].id == id) return rd.items[i];
		}
	}
	,getItemByName : function(name, rd) {
		if (!rd) rd = rqAnalyse;
		for (var i=0; i<rd.items.length; i++) {
			if (rd.items[i].name == name) return rd.items[i];
		}
	}
	,getItemByParentId : function(parentId, type, rd) {
		if (!rd) rd = rqAnalyse;
		for (var i=0; i<rd.items.length; i++) {
			if (rd.items[i].parentId == parentId && rd.items[i].type == type) return rd.items[i];
		}
		return null;
	}
	,getAggrItem : function(aggrigate,parentId,rd) {
		if (!rd) rd = rqAnalyse;
		var aggrs = [];
		if (aggrigate == 'avg') {
			aggrs = ["sum","count"];
		//} else if (aggr == 'count' || aggr == 'countd') {
		//	aggrs = ['sum'];
		} else aggrs = [aggrigate];

		var p = aly.getItem(parentId, rd);
		var items = [];
		for (var j=0; j<aggrs.length; j++) {
			var exist = false;
			for (var i=0; i<rd.items.length; i++) {
				var ii = rd.items[i];
				if (ii.type == 3 && ii.parentId == parentId && ii.content.aggr == aggrs[j]) {
					exist = true;
					items[j] = ii.id; 
					break;
				}
			}
			if (exist) continue;
			
			var id = rd.maxId++;
			var dataType = p.dataType;
			var aggr = aggrs[j];
			if (aggr == 'count' || aggr == 'countd' || aggr == 'sum') dataType = 1;
			var name = raqDt.getAggrName(aggrs[j]);
			if (["sum","count","countd"].indexOf(aggr)>=0) name = p.name+name;
			else name = name + p.name;
			var ii = {id:id,type:3,parentId:parentId,name:name,content:{field:p.name,aggr:aggr},dataType:dataType,datas:[]};
			rd.items[rd.items.length] = ii;
			items[j] = ii.id; 
		}
		return items;
	}
	,removeItem : function(id, rd){
		if (!rd) rd = rqAnalyse;
		for (var i=0; i<rd.rpxs.length; i++) {
			var conf = rd.rpxs[i];
			for (var j=conf.tops.length-1; j>=0; j--) {
				if (!conf.tops[j].srcItems) continue;
				for (var z=0; z<conf.tops[j].srcItems.length; z++) {
					if (conf.tops[j].srcItems[z] == id) conf.tops.remove(conf.tops[j]);
					break;
				}
			}
			for (var j=conf.lefts.length-1; j>=0; j--) {
				if (!conf.lefts[j].srcItems) continue;
				for (var z=0; z<conf.lefts[j].srcItems.length; z++) {
					if (conf.lefts[j].srcItems[z] == id) conf.lefts.remove(conf.lefts[j]);
					break;
				}
			}
			for (var j=conf.fields.length-1; j>=0; j--) {
				if (!conf.fields[j].srcItems) continue;
				for (var z=0; z<conf.fields[j].srcItems.length; z++) {
					if (conf.fields[j].srcItems[z] == id) conf.fields.remove(conf.fields[j]);
					break;
				}
			}
			for (var j=conf.wheres.length-1; j>=0; j--) {
				if (conf.wheres[j].item == id) conf.wheres.remove(conf.wheres[j]);
			}
		}
		rd.items.remove(aly.getItem(id));
	}
	,editItem : function(id, rd){
		if (!rd) rd = rqAnalyse;
		//
		for (var i=0; i<rd.rpxs.length; i++) {
			var conf = rd.rpxs[i];
			for (var j=conf.tops.length-1; j>=0; j--) {
				if (!conf.tops[j].srcItems) continue;
				for (var z=0; z<conf.tops[j].srcItems.length; z++) {
					if (conf.tops[j].srcItems[z] == id) conf.tops.remove(conf.tops[j]);
					break;
				}
			}
			for (var j=conf.lefts.length-1; j>=0; j--) {
				if (!conf.lefts[j].srcItems) continue;
				for (var z=0; z<conf.lefts[j].srcItems.length; z++) {
					if (conf.lefts[j].srcItems[z] == id) conf.lefts.remove(conf.lefts[j]);
					break;
				}
			}
			for (var j=conf.fields.length-1; j>=0; j--) {
				if (!conf.fields[j].srcItems) continue;
				for (var z=0; z<conf.fields[j].srcItems.length; z++) {
					if (conf.fields[j].srcItems[z] == id) conf.fields.remove(conf.fields[j]);
					break;
				}
			}
			for (var j=conf.wheres.length-1; j>=0; j--) {
				if (conf.wheres[j].item == id) conf.wheres.remove(conf.wheres[j]);
			}
		}
		rd.items.remove(aly.getItem(id));
	}
	,getRpx : function(name, rd) {
		if (!rd) rd = rqAnalyse;
		for (var i=0; i<rd.rpxs.length; i++) {
			if (rd.rpxs[i].name == name) return rd.rpxs[i];
		}
	}
	,getDataSet : function(name, rd) {
		if (!rd) rd = rqAnalyse;
		for (var i=0; i<rd.dataSets.length; i++) {
			if (rd.dataSets[i].name == name) return rd.dataSets[i];
		}
	}
	,getDataSetField : function(dataSet, field) {		
		for (var i=0; i<dataSet.fields.length; i++) {
			if (dataSet.fields[i].name == field) return dataSet.fields[i];
		}
	}
	
	,isGroupConf : function(conf,rd) {
		if (conf.tops.length>0||conf.lefts.length>0) return true;
		else return false;
		/*
		if (!rd) rd = rqAnalyse;
		if (conf.tops.length>0||conf.lefts.length>0) return true;
		for (var i=0;i<conf.fields.length; i++) {
			if (aly.getItem(conf.fields[i].srcItems[0], rd).type==3) return true;
		}
		return false;
		*/
	}
	,getCurrRpx : function(rd) {
		if (!rd) rd = rqAnalyse;
		if (rd.rpxs.length == 0) return null;
		if (rd.currRpx === undefined || rd.currRpx == "") {
			rd.currRpx = rd.rpxs[0].name;
			return rd.rpxs[0];
		}
		return aly.getRpx(rd.currRpx, rd);
	}
	,getRpxField : function(conf, itemId) {
		for (var i=0; i<conf.fields.length; i++) {
			for (var z=0; z<conf.fields[i].srcItems.length; z++) {
				if (conf.fields[i].srcItems[z] == itemId) return conf.fields[i];
			}
		}
	}
/*
	,getRpxItemName : function(confItem) {
		if (confItem.name === undefined || confItem.name == "") {
			var item = aly.getItem(confItem.item);
			confItem.name = item.name;
		}
		return confItem.name;
	}
*/
	,getNewConfFieldName : function(conf, name) {
		var names = [];
		for (var i=0; i<conf.fields.length; i++) {
			if (conf.fields[i] == null) continue;
			names[names.length] = conf.fields[i].name;
		}
		for (var i=0; i<conf.tops.length; i++) {
			names[names.length] = conf.tops[i].name;
		}
		for (var i=0; i<conf.lefts.length; i++) {
			names[names.length] = conf.lefts[i].name;
		}
		var count = 1;
		if (names.indexOf(name) == -1) return name;
		while (names.indexOf(name+count)>=0) {
			count++;
		}
		return name+count;
	}
	,getRpxFieldByName : function(conf, name) {
		for (var i=0; i<conf.fields.length; i++) {
			if (conf.fields[i] == null) continue;
			if (conf.fields[i].name == name) return conf.fields[i];
		}
		for (var i=0; i<conf.tops.length; i++) {
			if (conf.tops[i].name == name) return conf.tops[i];
		}
		for (var i=0; i<conf.lefts.length; i++) {
			if (conf.lefts[i].name == name) return conf.lefts[i];
		}
	}
	,modifyConfFieldName : function(conf, field, name) {
		var old = field.name;
		field.name = name;
		for (var i=0; i<conf.fields.length; i++) {
			var fi = conf.fields[i];
			if (fi == null) continue;
			if (fi._fieldType == 'aggr' && fi.groups != null) {
				for (var j=0; j<fi.groups.length; j++) {
					if (fi.groups[j] == old) fi.groups[j] = name;
				}
			} else if (fi._fieldType == 'analyse') {
				for (var j=0; j<fi.analyse.scopeGroups.length; j++) {
					if (fi.analyse.scopeGroups[j] == old) fi.analyse.scopeGroups[j] = name;
				}
				if (fi.analyse.field == old) fi.analyse.field = name;
			}
		}
	}
	,isItemUsed : function(itemId,rd) {
		if (!rd) rd = rqAnalyse;
		var used = 0;
		for (var i=0; i<rd.rpxs.length; i++) {
			for (var j=0; j<rd.rpxs[i].lefts.length; j++) {
				for (var z=0; z<rd.rpxs[i].lefts[j].srcItems.length; z++) {
					if (rd.rpxs[i].lefts[j].srcItems[z] == itemId) used++;
				}
			}
			for (var j=0; j<rd.rpxs[i].tops.length; j++) {
				for (var z=0; z<rd.rpxs[i].tops[j].srcItems.length; z++) {
					if (rd.rpxs[i].tops[j].srcItems[z] == itemId) used++;
				}
			}
			for (var j=0; j<rd.rpxs[i].fields.length; j++) {
				for (var z=0; z<rd.rpxs[i].fields[j].srcItems.length; z++) {
					if (rd.rpxs[i].fields[j].srcItems[z] == itemId) used++;
				}
			}
			for (var j=0; j<rd.rpxs[i].wheres.length; j++) {
				if (rd.rpxs[i].wheres[j].item == itemId) used++;
			}
		}
		return used;
	}
	,getSameSlice : function(parentId, code, rd) {
		if (!rd) rd = rqAnalyse;
		for (var i=0; i<rd.items.length; i++) {
			var ii = rd.items[i];
			var code2 = ii.content.code;
			if (ii.type != 5 || code.length !=code2.length) continue;
			var same = true;
			for (var j=0; j<code.length; j++) {
				if (code2.indexOf(code[j])==-1) {
					same = false;
					break;
				}
			}
			if (same) return ii;
		}
		return null;
	}
	,setSlice : function(parentId, selectDatas,sliceItem, conf, refresh, rd){
		if (!rd) rd = rqAnalyse;
		if (sliceItem == null) {
			if (selectDatas.length == 0) return;
			if (aly.getSameSlice(parentId, selectDatas, rd) != null) return;
		} else {
			var used = aly.isItemUsed(sliceItem.id ,rd);
			if (used<=1) {
				aly.removeItem(sliceItem.id, rd);
				for (var z=0; z<conf.wheres.length; z++) {
					if (conf.wheres[z].item == sliceItem.id) conf.wheres.remove(conf.wheres[z]);
				} 
			}
			if (selectDatas.length == 0) {
				setTimeout(function(){aly.refresh()},100);
				return;
			}
		}
		var id = rd.maxId++;
		var si = {id:id,type:5,parentId:parentId,name:"",content:{field:"",disp:[],code:[]}};
		rd.items[rd.items.length] = si;
		si.content.code = selectDatas;
		si.content.disp = selectDatas; //TODO
		conf.wheres.push({item:id});
		if (refresh) setTimeout(function(){aly.refresh()},100);
	}
	//TODO 完善删除left、top、where等
	,getCalcFields : function(conf, rd) {
		if (!rd) rd = rqAnalyse;
		var exp = "";
		for(var i=0; i<conf.fields.length; i++) {
			var confi = conf.fields[i];
			var item = aly.getItem(confi.srcItems[0], rd);
			if (item.type == 2) {
				if (exp!="") exp += ",";
				exp += item.content + ":" + item.name;
			}
		}
		return exp;
	}
	,getConfFieldByName : function(conf, name) {
		for (var i=0; i<conf.fields.length; i++) {
			if (conf.fields[i] == null) continue;
			if (conf.fields[i].name == name) return conf.fields[i];
		}
		for (var i=0; i<conf.tops.length; i++) {
			if (conf.tops[i].name == name) return conf.tops[i];
		}
		for (var i=0; i<conf.lefts.length; i++) {
			if (conf.lefts[i].name == name) return conf.lefts[i];
		}
	}
	,getRpxFields : function(conf) {
		var fs = [];
		for (var i=0; i<conf.fields.length; i++) {
			if (conf.fields[i] == null) continue;
			fs.push(conf.fields[i]);
		}
		for (var i=0; i<conf.tops.length; i++) {
			fs.push(conf.tops[i]);
		}
		for (var i=0; i<conf.lefts.length; i++) {
			fs.push(conf.lefts[i]);
		}
		return fs;
	}
	,checkFieldName : function(conf, f, rd){
		if (!rd) rd = rqAnalyse;
		var name = f.name;
		f._status = '';
		if (f._fieldType == 'analyse' || f._fieldType == 'newAnalyse')
		{
			return;
		}
		var dataSet = aly.getDataSet(conf.dataSet);
		if (dataSet.type == 6){
			try {
				var alertBack = alert;
				alert = function(){};
				var iObj = getInfosObj(f.src);
				if (f.where.conf != null && f.where.conf.length>0){
					var where = whereUtils.getExp(f.where.conf, "T1.", 1);
				}
				if (iObj.firstTable != dataSet.tableName){
					f._status = "数据集结构已变动，["+f.name+"]字段失效";
				}
				alert = alertBack;
			} catch (e) {
				f._status = "数据集结构已变动，["+f.name+"]字段失效";
			}
		} else {
			var src = aly.getDataSetField(dataSet,f.srcName);
			if (!src) f._status = "数据集结构已变动，["+f.name+"]字段失效";
		}
		if (name == '')
		{
			if (f._fieldType == 'aggr')
			{
				name = f.srcName + getAggrName(f.aggr);
			} else {
				name = f.srcName;
			}
		} else {
			name = name.replaceAll(f.srcName,'_src_');
			
			for (var i=aggrNames.length-1; i>=0; i--)
			{
				//if (!(f._fieldType == 'aggr' && aggrs[i] == f.aggr)){
					if (name == '_src_'+aggrNames[i]) name = name.replaceAll(aggrNames[i],'');
				//}
			}
			if (name == '_src_' && f._fieldType == 'aggr') name += getAggrName(f.aggr);
			name = name.replaceAll('_src_',f.srcName);
		}
		var names = [];
		for (var i=0; i<conf.fields.length; i++) {
			if (conf.fields[i] == null || f == conf.fields[i]) continue;
			names[names.length] = conf.fields[i].name;
		}
		for (var i=0; i<conf.tops.length; i++) {
			if (f == conf.tops[i]) continue;
			names[names.length] = conf.tops[i].name;
		}
		for (var i=0; i<conf.lefts.length; i++) {
			if (f == conf.lefts[i]) continue;
			names[names.length] = conf.lefts[i].name;
		}
		if (names.indexOf(name) == -1) {
			f.name = name;
		} else {
			var count = 1;
			while (names.indexOf(name+count)>=0) {
				count++;
			}
			f.name = name+count;
		}
	}
	,checkConf : function(conf) {
		//_parentType:'top/left/field',_fieldType:'group/detail/aggr/analyse/newAnalyse',_status:'为空表示正确，不为空是失效的具体信息'		
//		lefts:[{name:'',macroName:'',srcItems:[itemId0,itemId1],exp:'itemId0/itemId1',aggrExp:'sum(itemId0)/sum(itemId1)',use:1,order:0无序/1升序/2降序,groups:[lefts,tops里的分组，空分组表示整体聚合]/null表示随分组自动聚合,analyse:{analyseName:'占比/排名/比上期/比同期',field:'被分析的测度字段',scopeGroups:[空则表示针对全部]},format:'',dataType:'',_parentType:'top/left/field',_fieldType:'group/detail/aggr/analyse',_status:'为空表示正确，不为空是失效的具体信息'}]
		//var conf = aly.getCurrRpx();
		//if (conf.type == 2) return;
		conf._status = '';
		for (var i=0; i<conf.tops.length; i++) {
			conf.tops[i]._parentType = 'top';
			conf.tops[i]._fieldType = 'group';
			conf.tops[i]._finalType = conf.tops[i].dataType;
			aly.checkFieldName(conf,conf.tops[i]);
		}
		for (var i=0; i<conf.lefts.length; i++) {
			conf.lefts[i]._parentType = 'left';
			conf.lefts[i]._fieldType = 'group';
			conf.lefts[i]._finalType = conf.lefts[i].dataType;
			aly.checkFieldName(conf,conf.lefts[i]);
		}
		var hasGroup = conf.tops.length+conf.lefts.length>0;
		var hasAggr = false;
		for (var i=0; i<conf.fields.length; i++) {
			var fi = conf.fields[i];
			if (conf.type == 1) {
				fi._parentType = 'field';
				if (fi._fieldType == 'analyse' || fi._fieldType == 'newAnalyse') {
					fi._finalType = 1;
				} else {
					if (hasGroup) {
						fi._fieldType = 'aggr';
						if (fi.aggr =='' || fi.aggr===undefined) fi.aggr = 'count';
						if (fi.aggr == 'sum' || fi.aggr == 'count' || fi.aggr == 'countd' || fi.aggr == 'avg') fi._finalType = 1;
						else fi._finalType = fi.dataType;
						hasAggr = true;
					} else {
						fi.aggr = '';
						fi._fieldType = 'detail';
						fi._finalType = fi.dataType;
					}
				}
				aly.checkFieldName(conf,fi);
			} else {
				if (fi) {
					fi._parentType = 'field';
					fi._fieldType = (fi.aggr =='' || fi.aggr===undefined)?'group_detail':'aggr';
					if (fi._fieldType == 'aggr') hasAggr = true;
					if (fi.aggr == 'sum' || fi.aggr == 'count' || fi.aggr == 'countd' || fi.aggr == 'avg') fi._finalType = 1;
					else fi._finalType = fi.dataType;
					aly.checkFieldName(conf,fi);
				}
			}
		}
		if (conf.type == 2) {
			for (var i=0; i<conf.fields.length; i++) {
				var fi = conf.fields[i];
				if (fi && fi._fieldType == 'group_detail') {
					if (hasAggr) {
						fi._fieldType = 'group';
						hasGroup = true;
					} else {
						fi._fieldType = 'detail';
					}
				}
			}
		}
		conf._hasAggr = hasAggr;
		conf.structType = hasGroup?3:2;
		for (var i=0; i<conf.fields.length; i++) {
			var fi = conf.fields[i];
			if (!fi) continue;
			var status = "";
			if (fi._fieldType == 'aggr') {
				if (fi.groups != null && fi.groups.length>0) {
					var lefts=[],tops=[];
					for (var j=0; j<fi.groups.length; j++) {
						var fj = aly.getRpxFieldByName(conf,fi.groups[j]);
						if (fj == null) {
							status = "本聚合指标指定的维度【"+fi.groups[j]+"】已被删除，导致本聚合指标失效，请重新“编辑”维度";
						} else if (fj._parentType == 'field') {
							status = "本聚合指标指定的维度【"+fi.groups[j]+"】不在表头，导致本聚合指标无法展示";
						} else if (fj._parentType == 'top') {
							tops[tops.length] = [conf.tops.indexOf(fj),j];
						} else if (fj._parentType == 'left') {
							lefts[lefts.length] = [conf.lefts.indexOf(fj),j];
						}
						if (status != '') break;
					}
					if (status == '') {
						for (var j=0; j<lefts.length; j++) {
							if (lefts[j][0]>=lefts.length) {
								status = "本聚合指标指定的维度【"+fi.groups[lefts[j][1]]+"】在左表头太靠后，导致本聚合指标无法展示，请前移";
								break;
							}
						}						
					}
					if (status == '') {
						for (var j=0; j<tops.length; j++) {
							if (tops[j][0]>=tops.length) {
								status = "本聚合指标指定的维度【"+fi.groups[tops[j][1]]+"】在上表头太靠下，导致本聚合指标无法展示，请上移";
								break;
							}
						}
					}
					if (status == '') {
						if (lefts.length==0&&tops.length==0) {
							status = "没有表头分组，无法显示指定分组的聚合指标";
						}
					}
				}
			} else if (fi._fieldType == 'newAnalyse') {
			} else if (fi._fieldType == 'analyse') {
				//analyse:{analyseName:'占比/排名/比上期/比同期',field:'被分析的测度字段',scopeGroups:[空则表示针对全部]},format:'',dataType:'',_parentType:'top/left/field',_fieldType:'group/detail/aggr/analyse',_status:'为空表示正确，不为空是失效的具体信息'
				var fa = aly.getRpxFieldByName(conf,fi.analyse.field);
				if (!fa) {
					status = "本分析指标依赖的聚合指标已删除，导致分析指标失效";	
				} else {
					if (fa.groups != null && fa.groups.length == 0) {
						status = "本分析指标依赖的聚合指标是最终汇总的一个值，无法再分析";
					} else {
						var groups = fa.groups;
						if (fa.groups == null) {
							groups = [];
							for (var z=0; z<conf.lefts.length; z++) {
								groups[groups.length] = conf.lefts[z].name;
							}
							for (var z=0; z<conf.tops.length; z++) {
								groups[groups.length] = conf.tops[z].name;
							}
						}
						if (fi.analyse.scopeGroups == null) fi.analyse.scopeGroups = []; 
						var topGroups = fi.analyse.scopeGroups;
						if (topGroups.length>=groups.length) {
							status = "本“分析指标”的分析范围未覆盖依赖的“聚合指标”的范围";
						} else {
							for (var z=0; z<topGroups.length; z++) {
								if (groups.indexOf(topGroups[z]) == -1) {
									status = "本“分析指标”的分析范围未覆盖依赖的“聚合指标”的范围！";
									break;
								} 
							}
							if (status == '') {
								var lefts=[],tops=[];
								for (var j=0; j<topGroups.length; j++) {
									var fj = aly.getRpxFieldByName(conf,topGroups[j]);
									if (fj == null) {
										status = "本分析指标指定的维度【"+topGroups[j]+"】已被删除，导致本分析指标失效，请重新“编辑”维度";
									} else if (fj._parentType == 'field') {
										status = "本分析指标指定的维度【"+topGroups[j]+"】不在表头，导致本分析指标无法展示";
									} else if (fj._parentType == 'top') {
										tops[tops.length] = [conf.tops.indexOf(fj),j];
									} else if (fj._parentType == 'left') {
										lefts[lefts.length] = [conf.lefts.indexOf(fj),j];
									}
									if (status != '') break;
								}
								if (status == '') {
									for (var j=0; j<lefts.length; j++) {
										if (lefts[j][0]>=lefts.length) {
											status = "本分析指标指定的维度【"+topGroups[lefts[j][1]]+"】在左表头太靠后，导致本分析指标无法展示，请前移";
											break;
										}
									}						
								}
								if (status == '') {
									for (var j=0; j<tops.length; j++) {
										if (tops[j][0]>=tops.length) {
											status = "本聚合指标指定的维度【"+topGroups[tops[j][1]]+"】在上表头太靠下，导致本聚合指标无法展示，请上移";
											break;
										}
									}
								}
							}
						}
					}
				}
			}
			if (fi._status == '') fi._status = status;
		}
	}
	,refresh : function(noCalc, noRefreshDialog) {
		//alert(rqAnalyse.rpxs[0].fields.length);
		console.debug("aly refresh in ：" + rqAnalyse.currRpx);
		var reportConf = $("#analyseConf");
		//reportConf.css("visibility","hidden").css("opacity",0);
		var t2 = $('<table border=0 style="border:0;border-collapse:collapse;border:0px;margin:0px 0 10px 10px;" cellspacing=0 cellpadding=0></table>');
		var t3 = $('<table id="reportConfTable" border=0 style="border:0;border-collapse:collapse;border:0px;" cellspacing=0 cellpadding=0></table>');
		reportConf.html("").append(t3);
		t3.append('<tr style="border:1px solid lightgray;"><td style="width:239px;"></td><td style=""></td><td style="width:250px;"></td></tr>');
		var t3tds = t3.find('td');
		t3tds.css({"vertical-align":"top",'background-color':'#FFFFFF'});
		var confsDiv = $("<div style='margin-left: 10px;'></div>");
		var confsTitle = $("<div style='border-right:1px solid #E4E4E4;padding:5px;'><div style='font-weight:bold;color:#333333;float:left;padding:5px 20px 5px 0;'>报表列表</div></div>");
		confsDiv.append(confsTitle);
		for (var i=rqAnalyse.rpxs.length-1; i>=0; i--) {
			var confi = rqAnalyse.rpxs[i];
			if (confi.autoCalc != 0) confi.autoCalc = 1;
			var sty = "border-right:1px solid #E4E4E4;";
			if (rqAnalyse.currRpx == confi.name) sty = "border-top:1px solid #E4E4E4;border-bottom:1px solid #E4E4E4;border-left:1px solid #E4E4E4;";
			confsDiv.append("<div style='padding:5px;cursor:pointer;"+sty+"' confName='"+confi.name+"'><img style='vertical-align:-2px;cursor: pointer;' lock=1 src='"+contextPath+(guideConf.guideDir+(confi.autoCalc==1?"/img/guide/17.png":"/img/guide/18.png"))+"'><span style='padding:0 20px 0 5px;vertical-align:1px;'>"+confi.name+"</span><img style='float:right;vertical-align:-2px;cursor:pointer;margin:0 5px;' del=1 src='"+contextPath+guideConf.guideDir+"/img/guide/13.png'><img modify=1 style='vertical-align:-2px;cursor:pointer;margin:0 5px;float:right;' src='"+contextPath+guideConf.guideDir+"/img/guide/31.png'></div>")
		}
		if (rqAnalyse.rpxs.length == 0) {
			confsDiv.append("<div style='font-size:14px;padding:5px;border-right:1px solid #E4E4E4;'>请添加报表</div>");
		}
		confsDiv.find('img[lock=1]').click(function(){
			var cn = $(this).parent().attr('confName');
			var thisConf = aly.getRpx(cn);
			thisConf.autoCalc = thisConf.autoCalc==1?0:1;
			//$(this).attr('src',contextPath+(thisConf.autoCalc==1?"/dl/img/guide/17.png":"/dl/img/guide/18.png"));
			//if (thisConf.autoCalc == 1) {
		    	rqAnalyse.currRpx = cn;
				aly.refresh();
			//}
		});
		confsDiv.find('span').click(function(){
			var cn = $(this).parent().attr('confName');
			//var conf = aly.getRpx(cn);
	    	rqAnalyse.currRpx = cn;
			aly.refresh(true);
		});
		confsDiv.find('img[del=1]').click(function(){
			var cn = $(this).parent().attr('confName');
			rqAnalyse.rpxs.remove(aly.getRpx(cn));
			if (rqAnalyse.currRpx == cn) {
				rqAnalyse.currRpx = '';
			}		
			var reports = aly.cache.reports;
			for (var i=0; i<reports.length; i++) {
				if (reports[i].name == cn) {
					reports[i].dlg.close();
					reports[i].dlg.DOM.wrap.remove();
					reports.remove(reports[i]);
					break;
				}
			}
			aly.refresh();
		});
		confsDiv.find('img[modify=1]').click(function(){
			var cn = $(this).parent().attr('confName');
			var rpx = aly.getRpx(cn);
			if (!rpx.colWidths) rpx.colWidths = '';
			if (!rpx.rowHeights) rpx.rowHeights = '';
			zIndexBak = artDialog.defaults.zIndex;
			var dlg = art.dialog({
				id : dialogCount++,
				title : '修改',
		    content: '<div style="margin:'+(rpx.type==1?'20px':'40px 20px')+';"><input type="text" style="width:350px;" id="modifyNameTxt" value="'+cn+'"></div>'
		    	+'<div style="margin:20px;margin-top:0;"><input style="width:350px;'+(rpx.type==1?'':'display:none;')+'" type="text" id="modifyColWidthTxt" placeholder="输入“3:40;4:50”代表第3列宽40，第4列宽50；默认列宽'+guideConf.defaultWidth+'" value="'+(rpx.colWidths?rpx.colWidths:"")+'"></div>'
		    	+'<div style="margin:20px;margin-top:0;"><input style="width:350px;'+(rpx.type==1?'':'display:none;')+'" type="text" id="modifyRowHeightTxt" placeholder="输入“1:15;2:10”代表第1行高15，第2行高10；默认列宽'+guideConf.defaultWidth+'" value="'+(rpx.rowHeights?rpx.rowHeights:"")+'"></div>'
		    	+'<div style="margin:20px;margin-top:0;'+(rpx.type==1?'':'display:none;')+'">注:指的是报表模板中的行列，不是报表展示的行列</div>'
		    ,ok : function() {
					var n = $.trim($('#modifyNameTxt').val());
		    	if (n == '') {
		    		alert("名称不能为空");
		    		return false;
		    	}
		    	//$(t2tds[0]).find('div[confName="'+cn+'"]').html(n);
		    	if (aly.getRpx(n) && aly.getRpx(n) != rpx) {
		    		alert("报表名称已存在");
		    		return false;
		    	}
					var cw = $.trim($('#modifyColWidthTxt').val()).replaceAll("；",";").replaceAll("：",":");
					var rh = $.trim($('#modifyRowHeightTxt').val()).replaceAll("；",";").replaceAll("：",":");
		    	if (cn == n && cw == rpx.colWidths && rh == rpx.rowHeights) return true;
		    	rpx.name = n;
		    	rpx.colWidths = cw;
		    	rpx.rowHeights = rh;
					var reports = aly.cache.reports;
					for (var i=0; i<reports.length; i++) {
						if (reports[i].name == cn) {
							//console.debug();
							//reports[i].dlg.title(n);
							//reports[i].name = n;
							reports[i].dlg.close();
							reports[i].dlg.DOM.wrap.remove();
							reports.remove(reports[i]);
							break;
						}
					}
					artDialog.defaults.zIndex = zIndexBak;
		    	setTimeout(function(){
			    	rqAnalyse.currRpx = n;
						aly.refreshReport(n, false, false);
						aly.refresh();
		    	},1);
		    	return true;
		    }
 		    ,cancel : function() {
		    	artDialog.defaults.zIndex = zIndexBak;
		    	return true;
		    }
		    ,okVal : '确定'
		    ,cancelVal : '取消'
		    ,lock : true
		    ,duration : 0
		    ,width : '400px'
				,height : (rpx.type==1?'170px':'80px')
				,opacity : 0.1
				,padding : '2px 2px'
				,zIndex : 41000
			});
		});
		if (guideConf.showOlapList == "no") $(t3tds[0]).css('display','none');
		$(t3tds[0]).append(confsDiv);
		var addReport = $('<div style="margin-right:10px;color:#FFFFFF;background-color:#64CE67;padding:5px;float:right;cursor:pointer;">添加报表</div>');
		addReport.click(function(){
			var cn = "报表名称";
			var count = 1;
			while (aly.getRpx(cn) != null) {
				cn = "报表名称"+count;
				count++;
			}
			cn = '';
			zIndexBak = artDialog.defaults.zIndex;
			var dlg = art.dialog({
				id : dialogCount++,
				title : '添加报表',
			    content: '<div style="margin:0 0 0 30px;"><span id="addReportDs"></span></div>'
					+'<div style="margin:10px;"><input type="text" id="addConfName" placeholder="报表名称" style="width:260px;margin:10px 0 0 20px;height:30px;" value="'+cn+'"></div>'
			    	+'<div style="margin:0 10px;"><input type="checkbox" id="addReportChk" style="vertical-align:-2px;">使用报表模板</div>'
			    	+'<div style="margin:0 0 0 30px;"><span id="addReportSpan"></span></div>'
			    	
			    ,ok : function() {
					var dataSet = selDom2.val();
					if (dataSet == '') {
			    		alert("必须要创建一个数据集才能做报表分析");
			    		return false;
					}
					var ds = aly.getDataSet(dataSet);
					aly.checkDataSetConf(dataSet);
					if (ds._status != '')
					{
						alert(ds._status);
						return false;
					}
					if (ds.fields == null && ds.type != 6)
					{
						alert("该数据集需要先查询生成数据文件才能报表分析");
						return false;
					}


					var n = $.trim($('#addConfName').val());
			    	if (n == '') {
			    		alert("名称不能为空");
			    		return false;
			    	}
			    	
			    	if (aly.getRpx(n)) {
			    		alert("报表名称已存在");
			    		return false;
			    	}
			    	
			    	var type = 1;
			    	
			    	if ($('#addReportChk')[0].checked && selDom1.val() != '') {
			    		type = 2;
			    	} 
			    	//var conff = {type:type,name:n,reportId:'r'+new Date().getTime(),show:1,template:selDom1.val(),lefts:[],tops:[],fields:[],wheres:[],isRowData:1};
					var conff = 		{
						name:n
						,dataSet:dataSet
						//,dataSetLevel:'none/calc/where/group/having/order'
						,_hasAggr:0//'0/1'
						,_status:''//'为空表示正确，不为空是失效的具体信息'
						,type:type//1自定义分析报表/2模板报表
						,dialog:{
							open:1//0/1
							,top:100+Math.random()*100
							,left:100+Math.random()*200
							,width:500
							,height:400
						}
						,reportId:"rid"+new Date().getTime()
						,structType:1//:单条记录，全是统计字段/2:明细报表/3:分组及交叉报表
						,template:selDom1.val()
						,autoCalc:1//0/1
						,isRowData:1//0/1
						,lefts:[
							/*
							name:''
							,src:'字段信息'
							,srcName:''//原始字段名称
							,dataType:''
							,aggr:''
							,use:1
							,order:0无序/1升序/2降序
							,groups:[lefts,tops里的分组，空分组表示整体聚合]/null表示随分组自动聚合
							,analyse:{//指标字段
								analyseName:'占比/排名/比上期/比同期'
								,field:'被分析的测度字段'
								,scopeGroups:[空则表示针对全部]
							}
							,where:{conf:{}}
							,having:{conf:{}}
							,format:''
							,_finalType:''
							,_parentType:'top/left/field'
							,_fieldType:'group/detail/aggr/analyse'
							,_status:'为空表示正确，不为空是失效的具体信息'
							*/
						]
						,tops:[]
						,fields:[]
						,where:{conf:[]}
						,calcs:[]
					};

					if (type == 2) {
						var desc = existRpxDisc[existRpx.indexOf(selDom1.val())];
						for (var z=0; z<desc.split(";").length; z++) conff.fields[z] = null;
					}
					rqAnalyse.rpxs.push(conff);
					rqAnalyse.currRpx = n;
			    	artDialog.defaults.zIndex = zIndexBak;
			    	aly.refresh();
			    }
			    ,cancel : function() {
			    	artDialog.defaults.zIndex = zIndexBak;
			    	return true;
			    }
			    ,okVal : '确定'
			    ,cancelVal : '取消'
			    ,lock : true
			    ,duration : 0
			    ,width : '330px'
				,height : '150px'
				,opacity : 0.1
				,padding : '2px 2px'
				,zIndex : 41000
			});
			
			var selDom1 = getSelectDom(existRpx.length==0?[""]:existRpx, existRpx.length==0?["服务器没有报表模板"]:existRpx,"" );
			selDom1.attr('disabled',true).css({'background-color':'#FFFFFF','border':'1px solid lightgray','color':'#333333','padding':'4px','margin-top':'4px','width':'260px','height':'28px'}).attr('title','').change(function(){
			});
			$('#addReportSpan').append(selDom1);
			$('#addReportChk').change(function(){
				selDom1.attr('disabled',(this.checked?false:true));
			});

			var ds = [];
			for (var i=0; i<rqAnalyse.dataSets.length; i++)
			{
				ds[i] = rqAnalyse.dataSets[i].name;
			}
			var selDom2 = getSelectDom(ds.length==0?[""]:ds, ds.length==0?["没有可用数据集，请先添加数据集"]:ds,"");
			selDom2.css({'background-color':'#FFFFFF','border':'1px solid lightgray','color':'#333333','padding':'4px','margin-top':'4px','width':'260px','height':'28px'}).attr('title','').change(function(){
			});
			$('#addReportDs').append(selDom2);
			
		});
			
		$(confsTitle).append(addReport).append('<div style="clear:both;"></div>');
		$(t3tds[2]).css('border-right','1px solid #E4E4E4').append(t2);
		
		var conf = aly.getCurrRpx();
		if (conf == null) return; 
		analyseApi.event.changeReport();
		aly.checkConf(conf);
		var dataSet = aly.getDataSet(conf.dataSet);
		if (dataSet.type == 6) {
			for (var i=0; i<lmds.length; i++)
			{
				if (lmds[i].dsName == dataSet.dataSource)
				{
					lmd = lmds[i].lmd;
					break;
				}
			}
		}
		
		t2.append('<tr><td style="padding:5px 10px 0;"></td></tr><tr><td style="padding-top:5px;"></td></tr><tr><td></td></tr>');
		var t2tds = t2.find('td');
		t2tds.css({"vertical-align":"top"});
		
		var aggrs = $("<div id='aggrs' style='padding: 2px 0 2px 5px;background-color:#F8F8F8;border:1px solid #E4E4E4;'></div>");
		var items = $("<div id='items' style='width:220px;overflow: auto;max-height: 200px;'></div>");
		var contentDiv = "<div id='contentDiv' style='border:0;padding:0px;width:220px;height:200px;overflow:auto;' class='ztree'></div>";
		//$(t2tds[0]).append(aggrs);
		if (dataSet.type == 6) $(t2tds[0]).append(contentDiv);
		var aggrsDef = [{name:'',title:'原始值'},{name:'sum',title:'求和'},{name:'count',title:'计数'},{name:'avg',title:'平均'},{name:'max',title:'最大'},{name:'min',title:'最小'},{name:'countd',title:'值计数'}];
		if (!rqAnalyse.currAggr) rqAnalyse.currAggr = '';
		for (var i=0; i<aggrsDef.length; i++) {
			var uis = rqAnalyse.currAggr==aggrsDef[i].name?" class='ui-selected'":"";
			aggrs.append("<div"+uis+" style='float:left;cursor:pointer;' aggr='"+aggrsDef[i].name+"'>"+aggrsDef[i].title+"</div>");
		}
		aggrs.find('div').css({"margin":"2px","padding":"3px"}).click(function(){
			aggrs.find('div').each(function(){
				$(this).removeClass('ui-selected');
			});
			
			var ts = $(this);
			ts.addClass('ui-selected');
			rqAnalyse.currAggr = $(this).attr('aggr');
		}).hover(function(){
			$(this).addClass('ui-selected');
		},function(){
			if (rqAnalyse.currAggr != $(this).attr('aggr'))
			{
				$(this).removeClass('ui-selected');
			}
		});
		
		aggrs.append('<div style="clear:both;"></div>');
		//aggrs.selectable();
		
		$(t2tds[0]).append(items);
		items.css('height','');
		if (items.height()>200) items.css('height','200px');

		var whereDisp = whereUtils.getDisp(conf.where.conf);
		if (whereDisp == '') whereDisp = "复杂条件";
		if (whereDisp.length>20) whereDisp = whereDisp.substring(0,20)+"...";
		var whereBut = $("<div style='cursor:pointer;padding:5px;border:1px solid #E4E4E4;margin:0 10px 0 0px;border-bottom:0;'>"+whereDisp+"</div>");
		$(t2tds[1]).append(whereBut)
		whereBut.click(function(){
			var filter1 = "";
			if (dataSet.type == 6) filter1 = whereUtils.getExp(conf.where.conf, "T1.", 1);
			else filter1 = whereUtils.getExp(conf.where.conf, "", 1, 2);
			var saveFunc = function () {
				var disp = whereUtils.getDisp(cache.where.wheres);
				if (disp == '') return false;
				conf.where.conf = cache.where.wheres;
				var exp = '';
				if (dataSet.type == 6) exp = whereUtils.getExp(conf.where.conf, "T1.", 1);
				else exp = whereUtils.getExp(conf.where.conf, "", 1, 2);
				if (exp != filter1) {
					setTimeout("aly.refresh();",1);
				}
				artDialog.defaults.zIndex = zIndexBak;
				return true;
			 };
			 var clearFunc = function () {
				conf.where.conf = [];
				var exp = '';
				if (dataSet.type == 6) exp = whereUtils.getExp(conf.where.conf, "T1.", 1);
				else exp = whereUtils.getExp(conf.where.conf, "", 1, 2);
				if (exp != filter1) {
					setTimeout("aly.refresh();",1);
				}
				artDialog.defaults.zIndex = zIndexBak;
				return true;
			}

			var fields = [];
			if (dataSet.type == 6){
				byInfosUtil._getFields(aly.getDataSet(conf.dataSet).tableName, fields, 0, null, null,false);
				for (var i=0; i<fields.length; i++) fields[i] = transWhereInfo(fields[i],null,false);
			} else {
				for (var n=0; n<dataSet.fields.length; n++) {
					var itemn = dataSet.fields[n];
					if (itemn.exp && itemn.exp != '') continue;
					fields[fields.length] = {disp:itemn.name,dataType:itemn.dataType,edit:itemn.edit,exp:itemn.name,valueType:1,values:""};
				}
			}
			var initField = fields[0];

			whereUtils.openWhereDialog(saveFunc,clearFunc);
			whereUtils.refresh(fields, initField, JSON.parse(JSON.stringify(conf.where.conf)));
		});

		
		var div0 = null;
		var div1 = null;
		var div2 = null;
		var div3 = null;
		var divs = [];
		var table = null;
		if (conf.type == 1) { //自定义报表
			table = $('<table border=0 style="border:0;border-collapse:collapse;border:0px;width:100%;" cellspacing=0 cellpadding=0></table>');
			var tbody = $('<tbody>'
							+'<tr>'
								+'<td style="border:1px solid #E4E4E4;width:40%;" colspan=2 rowspan=2><div cType=1>&nbsp;</div></td>'
								+'<td style="border:1px solid #E4E4E4;border-bottom:0;"><div cType=2 style="width:100%;"></div></td>'
							+'</tr>'
							+'<tr>'
								+'<td style="border:1px solid #E4E4E4;border-top:0;height:20px;text-align: center;"></td>'
							+'</tr>'
							+'<tr>'
								+'<td style="border:1px solid #E4E4E4;border-right:0;"><div cType=3 style="width:100%;height:100%;"></div></td>'
								+'<td style="border:1px solid #E4E4E4;width:12px;border-left:0;text-align: center;vertical-align: middle;"></td>'
								+'<td style="border:1px solid #E4E4E4;"><div cType=4></div></td>'
							+'</tr>'
						+'</tbody>');
			table.append(tbody);
			$(t2tds[2]).css('padding','0px 10px 10px 0').append(table);
			
			var tds = tbody.find('td');
			div0 = $(tds[0]).find('div');
			div1 = $(tds[1]).find('div[cType]');
			$(tds[3]).css('height','100%');
			div2 = $(tds[3]).find('div[cType]');
			div3 = $(tds[5]).find('div[cType]');
			var changeRowData = function() {
				//$(t2tds[1]).find('div[isRowData]').html(conf.isRowData==0?"纵向显示数据":"横向显示数据");
			}
			if (conf.fields.length>1){
				var rowDataBut = conf.isRowData==0?$(tds[4]):$(tds[2]);
				rowDataBut.css('background-color','#E0E0E0').css('cursor','pointer').html("指标标题").click(function(){
					conf.isRowData = conf.isRowData==0?1:0;
					aly.refresh();
				});
			}
			
			
			tbody.find("div").css({width:'100%',height:'100%'});
			for (var i=0; i<conf.tops.length; i++) {
				var topi = conf.tops[i];
				var color = topi._status == ''?'':'gray';
				var divi = $("<div title='"+topi._status+"' style='color:"+color+";' cfName='"+topi.name+"' iType=5 idx='"+i+"'>"+topi.name+"</div>");
				div1.append(divi);
				var orderImg = $('<img confItemName="'+topi.name+'" style="vertical-align:-3px;cursor:pointer;margin:0px;" src="'+contextPath+guideConf.guideDir+'/img/guide/21.png">');
				divi.append(orderImg);
			}
			//if (conf.tops.length == 0) 
				div1.append("<div id='confHints' style='height:18px;width:100%;margin:3px;padding:3px;color:lightgray'>上表头</div>");
				div1.append("<div id='placeHolders' style='height:18px;width:40px;margin:3px;padding:3px;border:1px solid lightgray;display:none;'></div>");
			div1.css({'min-height':'20px', 'min-width':'40px'}).find('#confHints').css('display',conf.tops.length == 0?'block':'none');
			for (var i=0; i<conf.lefts.length; i++) {
				var lefti = conf.lefts[i];
				var color = lefti._status == ''?'':'gray';
				var divi = $("<div title='"+lefti._status+"' style='color:"+color+";' cfName='"+lefti.name+"' iType=6 idx='"+i+"'>"+lefti.name+"</div>");
				div2.append(divi);
				var orderImg = $('<img confItemName="'+lefti.name+'" style="color:'+color+';vertical-align:-3px;cursor:pointer;margin:0px;" src="'+contextPath+guideConf.guideDir+'/img/guide/21.png">');
				divi.append(orderImg);
			}
			//if (conf.lefts.length == 0) 
				div2.append("<div id='confHints' style='height:100%;text-align: center; margin:3px;padding:3px;color:lightgray;'>左表头</div>");
				div2.append("<div id='placeHolders' style='height:18px;width:40px;margin:3px;padding:3px;border:1px solid lightgray;display:none;'></div>");
			div2.css({'min-height':'20px', 'min-width':'40px'}).find('#confHints').css('display',conf.lefts.length == 0?'block':'none');
			for (var i=0; i<conf.fields.length; i++) {
				var fieldi = conf.fields[i];
				if (!fieldi._status) fieldi._status = '';
				var bc = fieldi._fieldType=='aggr'?'#DDEBF8':(fieldi._fieldType=='analyse'||fieldi._fieldType=='newAnalyse'?'#FFECB8':'');
				var color = fieldi._status == ''?'':'gray';
				var divi = $("<div title='"+fieldi._status+"' style='color:"+color+";background-color:"+bc+";' cfName='"+fieldi.name+"' iType=7 idx='"+i+"'>"+fieldi.name+"</div>");
				div3.append(divi);
				var orderImg = $('<img confItemName="'+fieldi.name+'" style="vertical-align:-3px;cursor:pointer;margin:0px;" src="'+contextPath+guideConf.guideDir+'/img/guide/21.png">');
				divi.append(orderImg);
			}
			table.find('img[confItemName]').click(function(){
				aly.confField.init($(this).attr('confItemName'));
			}).powerFloat({
				target : $("#confFieldFloat")
				,eventType:'click'
				,zIndex:50000
			});
			//if (conf.fields.length == 0) 
				div3.append("<div id='confHints' style='margin:3px;padding:3px;color:lightgray'>指标数据区</div>");
				div3.append("<div id='placeHolders' style='height:18px;width:40px;margin:3px;padding:3px;border:1px solid lightgray;display:none;'></div>");
			div3.find('#confHints').css('display',conf.fields.length == 0?'block':'none');
			if (conf.isRowData === undefined) conf.isRowData = 1;
			var div567s = table.find('div[iType=5],div[iType=6],div[iType=7]');
			div567s.css({margin:"3px",padding:"3px",cursor:"move"}).click(function(){
				//var h = $(this).attr("cfName");
				//aly.editConfItem(h);
			});
			var div5s = table.find('div[iType=5]');
			var div6s = table.find('div[iType=6]');
			//div6s.css({float:'left'});
			//div2.css("clear","both");
			div2.append('<div style="clear:left;"></div>');
			var div7s = table.find('div[iType=7]');
			var addCalcBut = $('<img src="'+contextPath+guideConf.guideDir+'/img/guide/9.png" style="cursor:pointer;margin:4px 2px;" title="添加分析指标">');
			div3.append(addCalcBut);
			if (conf.isRowData == 1) {
				//div7s.css({float:'left'});
				//addCalcBut.css({float:'left'});
				//div3.find("#confHints").css({float:'left'});
				//div3.find("#placeHolders").css({float:'left'});
				//div3.append('<div style="clear:left;"></div>');
			}
			div3.css('width','100%').css('height','150px').css('overflow','auto');
			//if (div3.height()>80) div3.css('height','80px');
			if (div3.width()>400) div3.css('width','400px');

			addCalcBut.click(function(){
				aly.editConfItem();
			});
		} else { // conf type is 2
			$(t2tds[1])
				//.append("<div style='float:left;font-weight:bold;padding:5px 20px 5px 0;'>关联数据</div>")
				.append("<div style='margin:0 9px 0 0;cursor:pointer;' id='allReportStyles'></div>")
				//.append('<div style="clear:both;"></div>');
			var existRpxNames = [];
			for (var z=0; z<existRpx.length; z++) existRpxNames[z] = existRpx[z].substring(guideConf.rpxFolderOnServer.length);
			var styles = getSelectDom(existRpx.length==0?[""]:existRpx, existRpx.length==0?["服务器没有模板"]:existRpxNames,"" );
			styles.css({'width':'100%','background-color':'#FFFFFF','border':'1px solid lightgray','color':'#333333','padding':'1px','margin-top':'0','height':'22px'}).attr('title','').change(function(){
				var ni = $(this).val();
				var desc1 = existRpxDisc[existRpx.indexOf(ni)];;
				conf.template = ni;
				conf.fields = [];
				for (var i=0; i<desc1.split(";").length; i++) conf.fields[i] = null;
				aly.refresh();
			});
			styles.val(conf.template);
			$('#allReportStyles').append(styles);
			var desc = existRpxDisc[existRpx.indexOf(conf.template)];
			currDesc = desc.split(";");	

			table = $('<table border=0 style="width:100%;border:0;border-collapse:collapse;border:0px;" cellspacing=0 cellpadding=0></table>');
			var tbody = $('<tbody></tbody>');
			table.append(tbody);
			$(t2tds[2]).css('padding','5px 10px 0 0').append(table);
			for (var i=0; i<currDesc.length; i++) {
				var fieldi = conf.fields[i];
				var macroName = currDesc[i];
				if (macroName.indexOf("(")>=0) macroName = macroName.substring(0,macroName.indexOf("("));
				else if (macroName.indexOf(":")>=0) macroName = macroName.substring(0,macroName.indexOf(":"));

				var tri = $('<tr><td style="width:100px;">'+macroName+'</td><td><div style="width:100%;height:100%;" cType=6 macroName="'+macroName+'" idx='+i+'></div></td></tr>');
				if (fieldi) {
					var bc = '';//fieldi._fieldType=='aggr'?'#DDEBF9':(fieldi._fieldType=='analyse'?'#42B06A':'');
					var color = fieldi._status == ''?'':'gray';
					var divi = $("<div style='color:"+color+";background-color:"+bc+";' cfName='"+fieldi.name+"' idx='"+i+"'>"+fieldi.name+"</div>");
					var orderImg = $('<img confItemName="'+fieldi.name+'" style="vertical-align:-3px;cursor:pointer;margin:0px;" src="'+contextPath+guideConf.guideDir+'/img/guide/21.png">');
					divi.append(orderImg);
					tri.find('div[macroName]').append(divi);
				} else {
					tri.find('div[macroName]').append('&nbsp;');
				}

				tbody.append(tri);
				divs[i] = tri.find('div');
			}
			tbody.find("td").css('padding','3px').css('border','1px solid #E4E4E4');
			table.find('img[confItemName]').click(function(){
				aly.confField.init($(this).attr('confItemName'));
			}).powerFloat({
				target : $("#confFieldFloat")
				,eventType:'click'
				,zIndex:50000
			});
		}

		var changeFunc = function( event, ui ) {
			//alert(1);
			var container = ui.placeholder.parent();
			var cType = container.attr('cType');
			div1.find('#confHints').css("display",div1.find("div[iType=5]").length-div1.find('.ui-sortable-helper').length>0?"none":"block");
			div2.find('#confHints').css("display",div2.find("div[iType=6]").length-div2.find('.ui-sortable-helper').length>0?"none":"block");
			div3.find('#confHints').css("display",div3.find("div[iType=7]").length-div3.find('.ui-sortable-helper').length>0?"none":"block");

			if (ui.item.attr("iType") == 7) {
				var fieldi = conf.fields[ui.item.attr("idx")];
				if (fieldi._fieldType == 'analyse' && cType != 4) {
					ui.placeholder.css('display',"none");
					return;
				}
			}
			if (conf.type == 1) {
				ui.helper.css('width','').css('height','17px');
				div1.parent().attr('sel',0).css("background-color","");
				div2.parent().attr('sel',0).css("background-color","");
				div3.parent().attr('sel',0).css("background-color","");
				//console.log("----1");
				
				ui.placeholder.html('&nbsp;').css("visibility","visible").css({'display':"block",height:'',padding:"2px",margin:"3px","background-color":"",border:'1px solid gray'});
				//alert (ui.helper.css('width'));
				//ui.placeholder.css('width',ui.helper.width()+"px");
				//if ((cType == 4 && conf.isRowData == 1) || cType == 3) ui.placeholder.css("float","left");
				ui.placeholder.css("float","");
				container.find('#confHints').css('display','none');//.css("visibility","hidden");
				container.parent().attr('sel',1).css("background-color","#FFFF88");
			} else {
				for (var i=0; i<divs.length; i++) {
					divs[i].parent().attr('sel',0).css("background-color","");
				}
				
				ui.placeholder.html('&nbsp;').css("visibility","visible").css({'display':"block",height:'',padding:"2px",float:"",margin:"3px","background-color":"",border:'1px solid gray'});
				//alert (ui.helper.css('width'));
				//ui.placeholder.css('width',ui.helper.width()+"px");
				//if ((cType == 4 && conf.isRowData == 1) || cType == 3) ui.placeholder.css("float","left");
				//ui.placeholder.css("float","");
				//div1.find('#confHints').css("visibility","visible");
				//div2.find('#confHints').css("visibility","visible");
				//div3.find('#confHints').css("visibility","visible");
				//container.find('#confHints').css("visibility","hidden");
				container.parent().attr('sel',1).css("background-color","#FFFF88");
			}
		}
		
		var confOverFunc = function(event, ui) {
			//alert(1);
			if (ui.placeholder.css('display') == 'none') {
				//alert(1);
				aly.refresh(true,true);
				return;
			}
			if (table == null) return;
			var confItems = table.find('div[iType]');
			var newTops = [];
			var newLefts = [];
			var newFields = [];
			//console.log("confOverFunc");
			for (var i=0; i<confItems.length; i++) {
				var ii = $(confItems[i]);
				var iType = ii.attr("iType");
				var idx = ii.attr("idx");
				var parentId = ii.attr("parentId");
				var aggr = ii.attr("aggr");
				var obj = null;
				if (iType == 5) obj = conf.tops[idx];
				else if (iType == 6) obj = conf.lefts[idx];
				else if (iType == 7) obj = conf.fields[idx];
				else if (iType == 1) {
					obj = aly.initDataSetField(aly.getDataSetField(dataSet,ii.attr("itemId")));
				}
				
				var p = null;
				if (ii.parent()[0] == div1[0]) {
					if (iType==1 || iType==5 || iType==6 || iType==7) p = newTops;
					else p = newFields;
				}
				if (ii.parent()[0] == div2[0]) {
					if (iType==1 || iType==5 || iType==6 || iType==7) p = newLefts;
					else p = newFields;
				}
				if (ii.parent()[0] == div3[0]) {
					p = newFields;
				}
				if (p != null) p.push(obj);
			}
			conf.tops = newTops;
			conf.lefts = newLefts;
			conf.fields = newFields;
			setTimeout("aly.refresh()",1);
		}

		if (dataSet.type == 6) {
			generateFields([dataSet.tableName],[]);
		} else {
			for (var i=0; i<dataSet.fields.length; i++) {
				var item = dataSet.fields[i];
				var itemDiv = $("<div style='padding:3px 5px;border:0px solid #E4E4E4;border-top:0;' name='"+item.name+"'></div>");
				items.append(itemDiv);
				var item0 = $("<div iType=1 itemId='"+item.name+"' style='margin:0 5px;width:80px;'>"+item.name+"</div>");
				itemDiv.append('<img src="'+contextPath+guideConf.guideDir+'/img/guide/27.png" style="float:left;">');
				itemDiv.append(item0);
				if (item.exp && item.exp != '') {
					item0.css('width','64px');
					var orderImg = $('<img confItemId="'+item.name+'" style="float:left;vertical-align:-3px;cursor:pointer;margin:0px;" src="'+contextPath+guideConf.guideDir+'/img/guide/21.png">');
					itemDiv.append(orderImg);
					orderImg.click(function(){
						aly.calcField.init($(this).attr('confItemId'));
					}).powerFloat({
						target : $("#calcFieldFloat")
						,eventType:'click'
						,zIndex:50000
					});
				}
				item0.css({"float":"left"});
				/*
				var item5 = null;
				for (var j=0; j<conf.wheres.length; j++) {
					var itemj = aly.getItem(conf.wheres[j].item);
					if (itemj.type == 5 && itemj.parentId == item.id) {
						item5 = itemj;
						break;
					}
				}
				if (item5 == null) {
					var str = "筛选数据";//JSON.stringify(item.datas);
					if (str) {
						if (str.length>20) str = str.substring(0,20)+"...";
						var itemiDiv = $("<div iType=4 parentId='"+item.id+"' style='margin:0px 5px;float:left;color:#666666;'>&nbsp;<span>"+str+"</span></div>");
						itemDiv.append(itemiDiv);
					}
				} else {
					if (itemj.parentId != item.id) continue;
					var str = JSON.stringify(itemj.content.disp);
					if (str.length>20) str = str.substring(0,20)+"...";
					var itemjDiv = $("<div iType=3 itemId='"+itemj.id+"' parentId='"+item.id+"' style='margin:2px 10px;float:left;'><span>数据范围&nbsp;:&nbsp;"+str+"</span></div>");
					itemDiv.append(itemjDiv);
				}
				*/
				itemDiv.append("<div style='clear:both;'></div>");
			}

			if (dataSet.fields.length>0){
				var addBut = $('<img src="'+contextPath+guideConf.guideDir+'/img/guide/9.png" style="cursor:pointer;margin:4px 2px;" title="添加计算字段">');
				items.append(addBut);
				addBut.click(function(){
					aly.editCalcField(null,function(){
						aly.refresh(true,true);
					});
				});
			}

			items.find('div[iType=1]').css({"cursor":"move"}).draggable({
				//revert:true
				//items: '> tr',
				//forcePlaceholderSize: true,
				connectToSortable: "div[cType]",
				appendTo:'body',
				helper: function(e) {
					var div = $(this);
					//var originals = tr.children();
					//alert(div.attr("iType"));
					/*
					var iType = div.attr("iType");
					var item = null;
					var str = "";
					if (iType == 1) {
						var item = aly.getItem(div.attr('itemId'));
						var aggr = '';
						$('#aggrs').find('div').each(function(){
							if($(this).hasClass('ui-selected')) aggr = $(this).attr('aggr');
						});
						var ts = $('#aggrs').find('.ui-selected');
						//alert(ts.length);
						ts.css({'background-color':'#64CE67'}).animate({'background-color':'#50A171'},300).animate({'background-color':'#64CE67'},300).animate({'background-color':'#50A171'},300).animate({'background-color':'#64CE67'},300,'',function(){
							ts.css({'background-color':''})
						});
						if (aggr != '') {
							str = raqDt.getAggrName(aggr);
							if (["sum","count","countd"].indexOf(aggr)>=0) str = item.name+str;
							else str = str + item.name;
						} else str = item.name;
					}
					var helper = $("<div style='margin:3px;padding:3px;background-color:#F8F8F8;'>"+str+"</div>");
					*/
					var helper = $(this).clone();
					helper.css("z-index",55555).css("opacity","0.8");
					
					//$('body').append(helper);
					//helper.css("width",helper.width());
					return helper;
				}
				//,axis:"y"  
				,drag:function(e, ui){
					var iType = $(this).attr("iType");
					ui.helper.css("z-index",55555);
					if (iType == 1 || iType == 2) {
						ui.helper.css("width",ui.helper.width()+"px");
						if (conf.type == 1) {
							if ((div1 != null && div1.parent().attr("sel")==1) || (div2 != null && div2.parent().attr("sel")==1)) return;
							div3.attr("sel",1).parent().css("background-color","#FFFF88");
						} else {
							
						}
					} else if (iType == 3) {
						//divWhere.attr("sel",1).parent().css("background-color","#FFFF88");
					}
				}
				,stop : function(event, ui) {
					var iType = $(this).attr("iType");
					if (div1 != null) div1.attr("sel",0).parent().css("background-color","");
					if (div2 != null) div2.attr("sel",0).parent().css("background-color","");
					if (div3 != null) div3.attr("sel",0).parent().css("background-color","");
					for (var i=0; i<divs.length; i++) {
						divs[i].attr("sel",0).parent().css("background-color","");
					}
					//confOverFunc(event, ui);
				}
				//,stop:confOverFunc
			});
			
		}

		$( "div[cType=2],div[cType=3],div[cType=4]" ).sortable({
			connectWith : "div[cType=2],div[cType=3],div[cType=4]"
			,tolerance : "pointer"
			,items : "ul,div[iType]"
		    ,change:changeFunc
		    ,start:changeFunc
		    ,stop:confOverFunc
		});
		
		
	    $( "div[cType=5]" ).droppable({
	        accept: "div[iType=3]",
	        //activeClass: "ui-state-hover",
	        //hoverClass: "ui-state-active",
	        drop: function( event, ui ) {
	    		divWhere.attr("sel",0).parent().css("background-color","");
	    		var iType = ui.draggable.attr('iType');
	    		if (iType == 3) {
	    			var item = aly.getItem(ui.draggable.attr('itemId'));
	    			var p = aly.getItem(ui.draggable.attr('parentId'));
	    			for (var i=0; i<conf.wheres.length; i++) {
	    				var item2 = aly.getItem(conf.wheres[i].item);
	    				if (item2.type == 5) {
			    			var p2 = aly.getItem(item2.parentId);
			    			if (p2.id == p.id) conf.wheres.remove(conf.wheres[i]);
	    				}
	    			}
	    			conf.wheres[conf.wheres.length] = {item:item.id};
	    			setTimeout("aly.refresh()",1);
	    		}
	        }
	    });

	    if (conf.type == 2) {
		    table.find('div[cType=6]').droppable({
		        accept: "div[iType=1]",
				//accept: ".zTreeDragUL",
		        //activeClass: "ui-state-hover",
		        //hoverClass: "ui-state-active",
		        drop: function(event,ui) {
		    		var itemId = ui.draggable.attr("itemId");
		    		var idx = $(this).attr("idx");
		    		var macroName = $(this).attr("macroName");
		    		//console.log(111);
		    		//console.log(idx);
		    		//console.log(333);
					//var aggr = ui.draggable.attr("aggr");
					var obj = aly.initDataSetField(aly.getDataSetField(dataSet,itemId));
					obj.aggr = '';
		    		obj.macroName = macroName;
		    		conf.fields[idx] = obj;
		    		setTimeout("aly.refresh();",1);
		    	}
		    	,over: function(event, ui) {
					//console.log("111111111111111111111111");
		    		$( this ).css("background-color","#FFFF88");
		    		//console.log("1" + ui.droppable);
					//console.log("2" + ui.draggable);
		    		
		    	}	
		    	,out: function(event, ui) {
		    		$( this ).css("background-color","");
		    		//console.log("3" + ui.droppable);
					//console.log("4" + ui.draggable);
		        }
		    });

	    }
		
		aly.cache.doms = {div1:div1,div2:div2,div3:div3,divs:divs};
		//if (div1 != null) div1.droppable(bigDrop);

		confsDiv.append('<div id="olapsBottomDiv" style="border-right:1px solid #E4E4E4;height:'+(confsDiv.parent().height()-confsDiv.height())+'px;"></div>');
	    
	    //var ht = $(window).height()-reportConf.height();
	    //if (ht<80) ht = 80;
	    //reportConf.css("top",ht+"px");

		if ($.cookie('reportConfShow') == '0') {
			//reportConf.css({left:0-reportConf.width()+"px"});
		}

		aly.refreshReport(aly.getCurrRpx().name, noCalc, noRefreshDialog);
	    
	}
	,calcField : {
		currItem : null
		,init : function(f) {
			aly.calcField.currItem = f;
		}
		,edit : function() {//
			$.powerFloat.hide();
			aly.editCalcField(aly.calcField.currItem,function(itemId){
				/*
				for (var i=0; i<rqAnalyse.rpxs.length; i++) {
					if (!rqAnalyse.rpxs[i].dialog){
						rqAnalyse.rpxs[i].dialog = {open:1,top:100,left:100+100*i,width:500,height:300};
					}
					if (rqAnalyse.rpxs[i].dialog.open == 1) aly.refreshReport(rqAnalyse.rpxs[i].name, false, false);
				}
				*/
				aly.refresh(true,true);
			});
		}
		,del : function() {//
			$.powerFloat.hide();
			var conf = aly.getCurrRpx();
			var dataSet = aly.getDataSet(conf.dataSet);
			var field = aly.getDataSetField(dataSet,aly.calcField.currItem);
			dataSet.fields.remove(field);
			aly.refresh(true,true);
		}
	}
	,confField : {
		currField : null
		,init : function(f) {
			//alert($('#confFieldFloat').find('[seq='+4+']').length);
			aly.confField.currField = f;
			var conf = aly.getCurrRpx();
			var dataSet = aly.getDataSet(conf.dataSet);
			var field = aly.getRpxFieldByName(conf,f);
			var buts = $('#confFieldFloat');
			var sets = [[1,2,3,4,5,16,6,7,8,18]
				,[9,10,11,12,13,14,15,5,16,6,7,8,18]
				,[5,6,7,8]
				,[9,10,11,12,13,14,15,17,5,16,6,7,8,18]
				,[9,10,12,13,14,5,16,6,7,8,18]
				,[9,10,12,13,14,17,5,16,6,7,8,18]
			];
			var set = null;
			if (conf.type == 1) {
				set = field._fieldType == 'aggr'?(field.dataType==1?sets[1]:sets[4]):(field._fieldType == 'analyse'?sets[2]:sets[0]);
			} else {
				set = field.dataType==1?sets[3]:sets[5];
			}
			var eds = mdUtils.getDimEditStyles();
			if (eds.length==0) set.remove(18); 
			for (var i=1; i<=18; i++)
			{
				buts.find('[seq='+i+']').css('display',set.indexOf(i)==-1?'none':'block');
			}
			buts.find('[aggr]').css('visibility','hidden');
			buts.find('[aggr="'+field.aggr+'"]').css('visibility','visible');
			buts.find('[order]').css('visibility','hidden');
			buts.find('[order="'+field.order+'"]').css('visibility','visible');
		}
		,order : function(o){
			var conf = aly.getCurrRpx();
			var field = aly.getRpxFieldByName(conf,aly.confField.currField)
			field.order = o;
			$.powerFloat.hide();
			aly.refresh();
		}
		,edit : function() {//
			$.powerFloat.hide();
			aly.editConfItem(aly.confField.currField);
		}
		,editStyle : function() {//
			$.powerFloat.hide();
			var conf = aly.getCurrRpx();
			var field = aly.getRpxFieldByName(conf,aly.confField.currField)
			zIndexBak = artDialog.defaults.zIndex;
			var dlg = art.dialog({
				id : dialogCount++,
				title : '设置显示值',
				content: 
					'<div id="editStyleDiv" style="margin:20px 10px;"></div>'
				,ok : function() {
					artDialog.defaults.zIndex = zIndexBak;
					console.log(selDom4.val());
					field.srcEdit = selDom4.val();
					aly.refresh();
					return true;
				}
				,cancel : function() {
					artDialog.defaults.zIndex = zIndexBak;
					return true;
				}
				,okVal : '确定'
				,cancelVal : '取消'
				,lock : true
				,duration : 0
				,width : '250px'
				,height : '80px'
				,opacity : 0.1
				,padding : '2px 2px'
				,zIndex : 41000
			});

			var eds1 = mdUtils.getDimEditStyles();
			eds1.splice(0,0,"");
			var eds2 = mdUtils.getDimEditStyles();
			eds2.splice(0,0,"无显示值");
			console.log(field.srcEdit);
			var selDom4 = getSelectDom(eds1, eds2, field.srcEdit);
			selDom4.css({'color':'#333333','background-color': '#F8F8F8','border': '1px solid #E4E4E4','padding':'2px','height':'27px','margin-top':'9px'}).attr('title','').change(function(){
			});
			$('#editStyleDiv').append(selDom4);
			
		}
		,format : function() {
			$.powerFloat.hide();
			var conf = aly.getCurrRpx();
			var field = aly.getRpxFieldByName(conf,aly.confField.currField)
			setFormat(field.format,field.dataType,function(fmt){
				field.format = fmt;
				aly.refresh();
			})
		}
		,del : function() {//
			var confItemName = aly.confField.currField;
			var conf = aly.getCurrRpx();
			for (var i=0; i<conf.tops.length; i++) {
				if (conf.tops[i].name == confItemName) {
					if (conf.type == 1) conf.tops.remove(conf.tops[i]);
					else conf.tops[i] = null;
					$.powerFloat.hide();
					aly.refresh();
					return;
				}
			}
			for (var i=0; i<conf.lefts.length; i++) {
				if (conf.lefts[i].name == confItemName) {
					if (conf.type == 1) conf.lefts.remove(conf.lefts[i]);
					else conf.lefts[i] = null;
					$.powerFloat.hide();
					aly.refresh();
					return;
				}
			}
			for (var i=0; i<conf.fields.length; i++) {
				if (conf.fields[i] != null && conf.fields[i].name == confItemName) {
					if (conf.type == 1) conf.fields.remove(conf.fields[i]);
					else conf.fields[i] = null;
					$.powerFloat.hide();
					aly.refresh();
					return;
				}
			}
		}
		,where : function(){
			$.powerFloat.hide();

			var conf = aly.getCurrRpx();
			//alert(aly.confField.currField);
			var field = aly.getRpxFieldByName(conf,aly.confField.currField);
			var dataSet = aly.getDataSet(conf.dataSet);

			if (dataSet.type != 6)
			{
				var dataScope = null;
				var func = function(data) {
					if (data == 'empty') {
						return;
					}				
					if (data.indexOf('info:')==0) {
						alert(data.substring(5));
						return;
					}
					dataScope = JSON.parse(data);
				}
				calcDfxData(aly.getDataSetField(dataSet, field.srcName), conf, func);
				if (dataScope == null) return;
				//alert(dataScope+"111");
				mdUtils.setEditStyleDataScope(field.srcEdit,dataScope);
			}
			//alert(dataScope+"222");
			var filter1 = '';
			if (dataSet.type == 6) filter1 = whereUtils.getExp(field.where.conf, "T1.", 1);
			else filter1 = whereUtils.getExp(field.where.conf, "", 1, 2);
			var saveFunc = function () {
				var disp = whereUtils.getDisp(cache.where.wheres);
				if (disp == '') return false;
				field.where.conf = cache.where.wheres;
				if (dataSet.type == 6) exp = whereUtils.getExp(field.where.conf, "T1.", 1);
				else exp = whereUtils.getExp(field.where.conf, "", 1, 2);
				if (exp != filter1) {
					setTimeout("aly.refresh();",1);
				}
				artDialog.defaults.zIndex = zIndexBak;
				return true;
			 };
			 var clearFunc = function () {
				field.where.conf = [];
				var exp = '';
				if (dataSet.type == 6) exp = whereUtils.getExp(field.where.conf, "T1.", 1);
				else exp = whereUtils.getExp(field.where.conf, "", 1, 2);
				if (exp != filter1) {
					setTimeout("aly.refresh();",1);
				}
				artDialog.defaults.zIndex = zIndexBak;
				var edit = mdUtils.getEditStyle(field.srcEdit);
				if (edit) edit.dataScope = null;
				return true;
			}
			var fields = [];
			if (dataSet.type == 6){
				fields = [transWhereInfo(field.src,null,false)];
			} else {
				fields[0] = {disp:field.name,dataType:field.dataType,edit:field.srcEdit,exp:field.src,valueType:1,values:""};
			}
			var initField = fields[0];

			whereUtils.openWhereDialog(saveFunc,clearFunc);
			whereUtils.refresh(fields, initField, JSON.parse(JSON.stringify(field.where.conf)));

		}
		,aggr : function(aggr) {
			$.powerFloat.hide();
			var conf = aly.getCurrRpx();
			var field = aly.getRpxFieldByName(conf,aly.confField.currField);
			field.aggr = aggr;
			aly.refresh();
		}
	}
	,editConfItem : function(name){
		"use strict";
		var conf = aly.getCurrRpx();
		if (!name) name = '';
		var confField = null;
		var isNew = true;
		var isTest = true;
		//var 
		if (name != '') {
			confField = aly.getRpxFieldByName(conf,name);
			isNew = false;
		} else {
			//aly.getNewConfFieldName(conf,item.name,rd)
			//analyse:{analyseName:'占比/排名/比上期/比同期',field:'被分析的测度字段',scopeGroups:[空则表示针对全部]},format:'',dataType:'',_parentType:'top/left/field',_fieldType:'group/detail/aggr/analyse',_status:'为空表示正确，不为空是失效的具体信息'}
			confField = {name:''
				,src:''
				,dataType:''//原始字段数据类型/或聚合后计算字段的表达式
				,srcName:''//原始字段名称
				,srcEdit:''//编辑风格
				,aggr:''
				,use:1
				,order:0//无序/1升序/2降序
				,groups:null//null表示随分组自动聚合
				,analyse:{//指标字段
					analyseName:''
					,field:''
					,scopeGroups:[]
				}
				,newAnalyse:null
				,exp:''//聚合后的计算字段，要求聚合指标在同一个层次上，否则计算出来的数据没意义。
				,where:{conf:[]} //去掉,exp:"",disp:""
				,having:{conf:[]}
				,format:''
				,macroName:''
				,_finalType:''//加上聚合后的最终数据类型
  			,_parentType:'field'
				,_fieldType:isTest?'newAnalyse':'analyse'
				,_status:''
			}
			//{name:'',srcItems:[],item:'',use:1,order:0,exp:"",aggrExp:"",macroName:'',groups:null,analyse:{analyseName:'排名',field:'',scopeGroups:[]},format:'',dataType:2,_fieldType:'analyse',_parentType:'field'};
		}

		var aggrs = [];
		for (var i=0; i<conf.fields.length; i++) {
			if (conf.fields[i]._fieldType == 'aggr') {
				aggrs[aggrs.length] = conf.fields[i].name;
			}
		}

		zIndexBak = artDialog.defaults.zIndex;
		var dlg = art.dialog({
			id : dialogCount++,
			title : isNew?'添加分析指标':'编辑',
	    content: '<div style="margin:10px;"><input type="text" id="confItemName" placeholder="指标名称" style="width:210px;height:30px;" value="'+name+'"></div>'
	    	+(confField._fieldType=='aggr'?'<div id="confItemAggrGroup" style="margin:10px;"></div>':'')
	    	+(confField._fieldType.indexOf('nalyse')>=0?'<div id="newAnalyse" style="margin:10px;display:none;"></div><div id="confItemAggrs" style="margin:10px;display:none;"></div><div id="confItemAnalyse" style="margin:10px;display:none;"></div><div id="confItemAnalyseGroups" style="margin:10px;display:none;"></div>':'')
	    ,ok : function() {
	    	if (isTest && confField._fieldType=='newAnalyse') {
	    		return analyseSave();
	    	}
				var groups = null;
				var analyseName = '';
				var aggr = '';
				var scopeGroups = [];					
				if (confField._fieldType=='aggr') {
					if ($('#confItemAggrGroup1').val() == 2) {
						var divs = $('#confItemAggrGroup').find('div .ui-selected');
						groups = [];
						for (var i=0; i<divs.length; i++) groups[groups.length] = $(divs[i]).html();
					}
				} else if (confField._fieldType=='analyse') {
					if (aggrs.length == 0) {
						return true;
					}
					analyseName = $('#confItemAggrs2').val();
					aggr = $('#confItemAggrs1').val();
					var divs = $('#confItemAnalyseGroups').find('div .ui-selected');
					for (var i=0; i<divs.length; i++) scopeGroups[scopeGroups.length] = $(divs[i]).html();
				}
				var n = $.trim($('#confItemName').val());
		    	if (n == '') {
		    		alert("名称不能为空");
		    		return false;
		    	}
		    	if (aly.getRpxFieldByName(conf,n)!=null && n!=confField.name) {
		    		alert("名称已存在");
		    		return false;
		    	}
		    	
				if (confField._fieldType=='aggr') {
					if (groups != null && groups.length>0) {
						var oldGroups = confField.groups;
						confField.groups = groups;
						aly.checkConf(conf);
						if (confField._status != '') {
							alert(confField._status);
							confField.groups = oldGroups;
							confField._status = '';
							return false;
						}
					} else confField.groups = groups;
				} else if (confField._fieldType=='analyse') {
					if (isNew) {
						confField.name = 'tempName';
						conf.fields[conf.fields.length] = confField;
			    	}
					var oldAggr = confField.analyse.field;
					var oldAnalyseName = confField.analyse.analyseName;
					var oldScopeGroups = confField.analyse.scopeGroups;
					confField.analyse.analyseName = analyseName;
					confField.analyse.field = aggr;
					confField.analyse.scopeGroups = scopeGroups;
					aly.checkConf(conf);
					if (confField._status != '') {
						alert(confField._status);
						if (isNew) {
							conf.fields.remove(confField);
						} else {
							confField.analyse.analyseName = oldAnalyseName;
							confField.analyse.field = oldAggr;
							confField.analyse.scopeGroups = oldScopeGroups;
						}
						confField._status = '';
						return false;
					}
				}
				aly.modifyConfFieldName(conf,confField,n);		    	
	    	
	    	artDialog.defaults.zIndex = zIndexBak;
	    	setTimeout(function(){aly.refresh();},1);
	    	return true;
	    }
	    ,cancel : function() {
	    	artDialog.defaults.zIndex = zIndexBak;
	    	return true;
	    }
	    ,okVal : '确定'
	    ,cancelVal : '取消'
	    ,lock : true
	    ,duration : 0
		  ,width : isTest?"900px":(confField._fieldType=='aggr'?'550px':(confField._fieldType=='analyse'?'550px':'240px'))
			,height : isTest?"500px":(confField._fieldType=='aggr'?'230px':(confField._fieldType=='analyse'?'300px':'63px'))
			,opacity : 0.1
			,padding : '2px 2px'
			,zIndex : 41000
		});
		$('.aui_content').css('overflow','auto');
		let currAnalyse = null;
		if (confField._fieldType=='aggr') {
			if (confField.aggr != 'avg') { // 2017/05/25 : confField.aggr != 'countd' && 
				var d1 = $('#confItemAggrGroup');
				//TODO 把聚合改为具体的求和、计数等
				var selDom4 = getSelectDom([1,2], ['跟随表头分组聚合','指定分组聚合'],confField.groups==null?1:2);
				selDom4.attr('id','confItemAggrGroup1').css('width','210px').css({'margin':'1px 0','color':'#333333','background-color': '#F8F8F8','border': '1px solid #E4E4E4','padding':'4px'}).attr('title','').change(function(){
					d2.css('display',$(this).val()==1?'none':'block');
				});
				var d2 = $('<div style="display:'+(confField.groups==null?'none':'block')+';margin:0 10px;"></div>');
				d2.append("<div style='color:gray;margin:10px 7px;'>不选择任何分组则全部聚合起来；要选择靠前的分组，这样表格展示的数据才整齐易看</div>");
				var lefts = [];
				//if (conf.lefts.length>0) d2.append("<div>左表头分组</div>"); 
				for (var i=0; i<conf.lefts.length; i++) {
					var ni = conf.lefts[i].name;
					if (confField.groups!=null&&confField.groups.indexOf(ni)>=0) d2.append("<div sel=1>"+ni+"</div>");
					else d2.append("<div seq='l"+i+"' sel=0>"+ni+"</div>");
				}
				var tops = [];
				if (conf.tops.length>0) {
					if (conf.lefts.length>0) d2.append("<div style='font-size:3px;clear:both;'>&nbsp;</div>");
					//d2.append("<div>上表头分组</div>");
				}
				for (var i=0; i<conf.tops.length; i++) {
					var ni = conf.tops[i].name;
					if (confField.groups!=null&&confField.groups.indexOf(ni)>=0) d2.append("<div sel=1>"+ni+"</div>");
					else d2.append("<div seq='t"+i+"' sel=0>"+ni+"</div>");
				}
				
				d2.find('div[sel=1]').addClass('ui-selected');
				d2.find('div[sel]').css('float','left').css('margin','2px').css('padding','5px').css('cursor','pointer');
				//p.append('<div'+((selectDatas.length>0&&selectDatas.indexOf(ss1[i])>=0)?' class="ui-selected"':"")+' style="padding:5px;margin:2px;float:left;" disp="'+(ss2==null?"":ss2[i])+'" value="' + ss1[i] + '">'+disp+'</div>');
				d2.bind("mousedown", function(e) {
					  e.metaKey = true;
				}).selectable({
					filter: "div[sel]"
					,stop: function() {
					}
				});
				
				d1.append(selDom4).append(d2);//.css('display','none');//TODO 16/12/29隐藏掉这个汇总显示的功能
			}
		} else if (confField._fieldType=='analyse') {
			$('#confItemName').css('width','270px');
			var d5 = $('#confItemAnalyse');
			var selDom3 = getSelectDom(['占比','排名','比上期','累积'], ['占比','排名','比上期','累积'], confField.analyse.analyseName);
			selDom3.attr('id','confItemAggrs2').css('width','198px').css({'margin':'1px 0','color':'#333333','background-color': '#F8F8F8','border': '1px solid #E4E4E4','padding':'4px'}).attr('title','').change(function(){
				//changeClassTable();
			});
			d5.css('display','block').append("<span style='font-weight:bold;'>分析方法　：</span>").append(selDom3);

			var d1 = $('#confItemAggrs');
			if (aggrs.length == 0) {
				d1.css('display','block').html("<span style=font-weight:bold;'>被分析数据：</span>没有可被分析的聚合指标");
				return;
			}
			var selDom4 = getSelectDom(aggrs, aggrs, confField.analyse.field);
			selDom4.attr('id','confItemAggrs1').css('width','198px').css({'margin':'1px 0','color':'#333333','background-color': '#F8F8F8','border': '1px solid #E4E4E4','padding':'4px'}).attr('title','').change(function(){
				//changeClassTable();
			});
			d1.css('display','block').append("<span style='font-weight:bold;'>被分析数据：</span>").append(selDom4);
			
			var d3 = $('#confItemAnalyseGroups');
			d3.append('<span style="font-weight:bold;">分析范围　：</span>');
			d3.append("<span style='color:gray;margin:10px 0px;'>选择下面分组作为分析范围，不选择任何分组则针对整体范围的数据进行分析</span>");
			d3.append('<div style="font-size:1px;">&nbsp;</div>');
			var d2 = $('<div style="margin-left:65px;"></div>');
			d3.append(d2);
			var lefts = [];
			//if (conf.lefts.length>0) d2.append("<div>左表头分组</div>"); 
			for (var i=0; i<conf.lefts.length; i++) {
				var ni = conf.lefts[i].name;
				if (confField.analyse.scopeGroups!=null&&confField.analyse.scopeGroups.indexOf(ni)>=0) d2.append("<div sel=1>"+ni+"</div>");
				else d2.append("<div seq='l"+i+"' sel=0>"+ni+"</div>");
			}
			var tops = [];
			if (conf.tops.length>0) {
				if (conf.lefts.length>0) d2.append("<div style='clear:both;'></div>");
				//d2.append("<div>上表头分组</div>");
			}
			for (var i=0; i<conf.tops.length; i++) {
				var ni = conf.tops[i].name;
				if (confField.analyse.scopeGroups!=null&&confField.analyse.scopeGroups.indexOf(ni)>=0) d2.append("<div sel=1>"+ni+"</div>");
				else d2.append("<div seq='t"+i+"' sel=0>"+ni+"</div>");
			}
			
			d2.find('div[sel=1]').addClass('ui-selected');
			d2.find('div[sel]').css('float','left').css('margin','2px').css('padding','5px').css('cursor','pointer');
			//p.append('<div'+((selectDatas.length>0&&selectDatas.indexOf(ss1[i])>=0)?' class="ui-selected"':"")+' style="padding:5px;margin:2px;float:left;" disp="'+(ss2==null?"":ss2[i])+'" value="' + ss1[i] + '">'+disp+'</div>');
			d2.bind("mousedown", function(e) {
				  e.metaKey = true;
			}).selectable({
				filter: "div[sel]"
				,stop: function() {
			    }
			});
			
			d3.css('display','block');
		} else if (confField._fieldType=='newAnalyse') {
			/*
				,newAnalyse : {
					leftLevel : ''//root/维字段
					,topLevel : ''//root/维字段
					,exp : ''// ?1/?2，?1代表下面第一个子项
					,items : [
						{
							field:'数据来源字段，可以是维或测度或其它分析字段，但分析字段不能互相引用'
							,value:'curr/find' //当前值、查找值
							//以下属性只针对find
							,aggr:'count/avg/sum/max/min/first'
							,exp:'fieldCurrValue/fieldFindValue/fieldCurrSeq/fieldFindSeq' //'上层维度字段或本测度字段'
						}
					]
				}
			*/
			
			if (!confField.newAnalyse) {
				confField.newAnalyse = {
					leftLevel : ''//root/维字段
					,topLevel : ''//root/维字段
					,exp : ''// ?1/?2，?1代表下面第一个子项
					,items : [
					]
				};
			}
					
			var na = $('#newAnalyse');
			na.css('display','block');
			
			let d1 = $('<div id="analyseD1"></div>');
			let d2 = $('<div id="analyseD2"></div>');
			let d3 = $('<div id="analyseD3"></div>');
			let d4 = $('<div id="analyseD4"></div>');
			let d11 = $('<div></div>');
			let d22 = $('<div style="border-left:3px solid lightgray;margin:10px 2px 0;padding-left:5px;"></div>');
			na.append(d11);
			na.append(d22);
			d22.append(d3);
			d22.append(d4);

			var lefts = [];
			if (conf.lefts.length>0) {
				d11.append(d1);
				var pos = conf.lefts.length;
				for (var i=0; i<conf.lefts.length; i++) {
					var ni = conf.lefts[i].name;
					if (confField.newAnalyse.leftLevel==ni) {
						pos = i;
						break;
					}
				}
				d1.append("<div level='root' class='ui-selected'>左表头根层</div>");
				for (var i=0; i<conf.lefts.length; i++) {
					lefts[i] = conf.lefts[i].name;
					d1.append("<div level='"+conf.lefts[i].name+"' class="+(pos>=i?'ui-selected':'')+">"+conf.lefts[i].name+"</div>");
				}
				d1.find('div').css('float','left').css('margin','2px').css('padding','5px').css('cursor','pointer').click(function(){
					d12func($('#analyseD1'),this);
				});
				d1.append('<div style="clear:both;"></div>');
			}

			var tops = [];
			if (conf.tops.length>0) {
				d11.append(d2);
				var pos = conf.tops.length;
				for (var i=0; i<conf.tops.length; i++) {
					var ni = conf.tops[i].name;
					if (confField.newAnalyse.topLevel==ni) {
						pos = i;
						break;
					}
				}
				d2.append("<div level='root' class='ui-selected'>上表头根层</div>");
				for (var i=0; i<conf.tops.length; i++) {
					tops = conf.tops[i].name;
					d2.append("<div level='"+conf.tops[i].name+"' class="+(pos>=i?'ui-selected':'')+">"+conf.tops[i].name+"</div>");
				}
				d2.find('div').css('float','left').css('margin','2px').css('padding','5px').css('cursor','pointer').click(function(){
					d12func($('#analyseD2'),this);
				});
				d2.append('<div style="clear:both;"></div>');
			}
			
			let lastDim = "";
			if (lefts.length>0) lastDim = lefts[lefts.length-1];
			else if (tops.length>0) lastDim = tops[tops.length-1];
			let lastDim2 = ""; // 倒数第二个维
			if (lefts.length>1) {
				lastDim = lefts[lefts.length-1];
				lastDim2 = lefts[lefts.length-2];
			} else if (tops.length>1) {
				lastDim = tops[tops.length-1];
				lastDim2 = tops[tops.length-2];
			}
			

			var nas = [
				{//排名
					exp : '?1+1'// ?1/?2，?1代表下面第一个子项
					,items : [
						{
							field:aggrs[0]
							,value:'find' //当前值、查找值
							//以下属性只针对find
							,aggr:'count'
							,exp:aggrs[0]+'当前值<'+aggrs[0]+'查找值' //'上层维度字段或本测度字段'
						}
					]
				}
				,{//占比
					exp : '?1/?2'// ?1/?2，?1代表下面第一个子项
					,items : [
						{
							field:aggrs[0]
							,value:'curr' //当前值、查找值
							,aggr:'count'
							,exp:''
						}
						,{
							field:aggrs[0]
							,value:'find' //当前值、查找值
							//以下属性只针对find
							,aggr:'sum'
							,exp:''
						}
					]
				}
				,{//环比
					exp : '?1/?2'// ?1/?2，?1代表下面第一个子项
					,items : [
						{
							field:aggrs[0]
							,value:'curr' //当前值、查找值
							,aggr:'count'
							,exp:''
						}
						,{
							field:aggrs[0]
							,value:'find' //当前值、查找值
							//以下属性只针对find
							,aggr:'first'
							,exp:lastDim+'当前序号=='+lastDim+'查找序号+'+1
						}
					]
				}
				,{//累积
					exp : '?1'// ?1/?2，?1代表下面第一个子项
					,items : [
						{
							field:aggrs[0]
							,value:'find' //当前值、查找值
							//以下属性只针对find
							,aggr:'sum'
							,exp:lastDim+'当前序号>='+lastDim+'查找序号'
						}
					]
				}
				,{//同比
					exp : '?1/?2'// ?1/?2，?1代表下面第一个子项
					,items : [
						{
							field:aggrs[0]
							,value:'curr' //当前值、查找值
							,aggr:'count'
							,exp:''
						}
						,{
							field:aggrs[0]
							,value:'find' //当前值、查找值
							//以下属性只针对find
							,aggr:'first'
							,exp:lastDim+'当前序号==('+lastDim+'查找序号+4)'
						}
					]
				}

			];
			
			if (!isNew) {
				d3.append("<div class=''>当前分析设置</div>");
				nas.splice(0,0,JSON.parse(JSON.stringify(confField.newAnalyse)));
			}
			d3.append("<div class=''>排名</div>");
			d3.append("<div class=''>占比</div>");
			d3.append("<div class=''>环比</div>");
			d3.append("<div class=''>累积</div>");
			d3.append("<div class=''>同比</div>");
			currAnalyse = nas[0];
			d3.find('div').css('float','left').css('margin','2px').css('padding','5px').css('cursor','pointer').click(function(){
				var d2ds = d3.find('div');
				for (var i=0; i<d2ds.length; i++){
					//$(d2ds[i]).removeClass('ui-selected');
					if (d2ds[i] == this) {
						saveAn();
						currAnalyse = nas[i];
						showAn();
						//$(d2ds[i]).addClass('ui-selected');
					}
				}
			});
			d3.append('<div style="clear:both;"></div>');
		
			var allf = lefts.concat(tops).concat(aggrs);	
			
			function showAn() {
				//d4.html("");
				d4.html('<input type="text" id="analyseExp" placeholder="分析表达式" style="width:280px;height:30px;margin-left:4px;" value="'+currAnalyse.exp+'">');
				for (let i=0; i<currAnalyse.items.length; i++) {
					let itemi = currAnalyse.items[i];
					let delButi = $('<img style="vertical-align:-2px;cursor:pointer;margin:0 5px;'+(i==0?"visibility:hidden;":"")+'" src="'+contextPath+guideConf.guideDir+'/img/guide/13.png">');
					let divi = $('<div idx='+i+' style="margin:3px 7px 3px 3px;padding:3px;border-left:3px solid lightgray;"></div>');
					divi.append(delButi).append('<span style="font-weight:bold;font-size:13px;margin-right:5px;">?'+(i+1)+'</span>');
					d4.append(divi);
					delButi.click(function(){
						saveAn();
						currAnalyse.items.remove(currAnalyse.items[i]);
						showAn();
					});

					
					let selDom2 = getSelectDom(allf, allf, itemi.field);
					selDom2.attr('id','itemField').css('width','150px').css({'margin':'3px 3px 3px 0','color':'#333333','background-color': '#F8F8F8','border': '1px solid #E4E4E4','padding':'2px'}).attr('title','').change(function(){
						itemi.field = selDom2.val();
						showCond();
					});
					divi.append(selDom2);

					let selDom1 = getSelectDom(["curr","find"], ["当前值","查找值"], itemi.value);
					selDom1.attr('id','itemValue').css('width','75px').css({'margin':'3px','color':'#333333','background-color': '#F8F8F8','border': '1px solid #E4E4E4','padding':'2px'}).attr('title','').change(function(){
						itemi.value = selDom1.val();
						showCond();
					});
					divi.append(selDom1);
					
					let selDom3 = getSelectDom(["sum","count","avg","max","min","first"], ["求和","计数","平均","最大","最小","第一个"], itemi.aggr);
					selDom3.attr('id','itemAggr').css('width','75px').css({'visibility':'hidden','margin':'3px','color':'#333333','background-color': '#F8F8F8','border': '1px solid #E4E4E4','padding':'2px'}).attr('title','').change(function(){
						//changeClassTable();
					});
					divi.append(selDom3);
					
					let divii = $('<div style="display:none;margin:3px 0 0 45px;"></div>');
					divi.append(divii);
					let showCond = function () {
						if (itemi.value == 'find') {
							selDom3.css('visibility','visible');
							divii.css('display','block');
						} else {
							selDom3.css('visibility','hidden');
							divii.css('display','none');
						}
						divii.html("");
						divii.append('<input type="text" id="analyseItemExp" placeholder="条件表达式" style="width:760px;height:26px;" value="'+itemi.exp+'">');
						let fs = [];
						if (aggrs.indexOf(itemi.field)>=0) {
							fs = lefts.concat(tops).concat(itemi.field);
						} else if (lefts.indexOf(itemi.field)>=0) {
							for (let m=0; m<=lefts.indexOf(itemi.field); m++) fs[m] = lefts[m]; 
						} else {
							for (let m=0; m<=tops.indexOf(itemi.field); m++) fs[m] = tops[m]; 
						}
						
						let divm = $('<div></div>');
						divii.append(divm);
						for (let m=0; m<fs.length; m++) {
							//divm.append(fs[m]+" : ");
							divm.append('<a style="color:gray;padding:5px;" href="javascript:void(0)" v="'+fs[m]+'当前值">'+fs[m]+'当前值</a>');
							divm.append('<a style="color:gray;padding:5px;" href="javascript:void(0)" v="'+fs[m]+'查找值">'+fs[m]+'查找值</a>');
							if (aggrs.indexOf(fs[m])==-1){
								divm.append('<a style="color:gray;padding:5px;" href="javascript:void(0)" v="'+fs[m]+'当前序号">'+fs[m]+'当前序号</a>');
								divm.append('<a style="color:gray;padding:5px;" href="javascript:void(0)" v="'+fs[m]+'查找序号">'+fs[m]+'查找序号</a>');
							}
						}
						divm.append('&nbsp;&nbsp;|&nbsp;&nbsp;');
						divm.append('<a style="color:#333;padding:5px;" href="javascript:void(0)" v="==">等于</a>');
						divm.append('<a style="color:#333;padding:5px;" href="javascript:void(0)" v=">">大于</a>');
						divm.append('<a style="color:#333;padding:5px;" href="javascript:void(0)" v="<">小于</a>');
						divm.append('<a style="color:#333;padding:5px;" href="javascript:void(0)" v="&&">并且</a>');
						divm.append('<a style="color:#333;padding:5px;" href="javascript:void(0)" v="||">或者</a>');
						divii.find('a[v]').css('text-decoration','none').click(function(){
							divii.find('#analyseItemExp').val(divii.find('#analyseItemExp').val()+" "+$(this).attr("v")+" ");
						});
					}
					showCond();
				}
				let addBut = $('<img style="vertical-align:-2px;cursor:pointer;margin:0 5px;" src="'+contextPath+guideConf.guideDir+'/img/guide/9.png">');
				d4.append(addBut);
				addBut.click(function(){
					saveAn();
					currAnalyse.items.push(
						{
							field:aggrs[0]
							,value:'curr' //当前值、查找值
							,aggr:'count'
							,exp:''
						}
					);
					showAn();
				});

			}
			showAn();			
		}
		let analyseSave = function(){
			let n = $.trim($('#confItemName').val());
    	if (n == '') {
    		alert("名称不能为空");
    		return false;
    	}
    	if (aly.getRpxFieldByName(conf,n)!=null && n!=confField.name) {
    		alert("名称已存在");
    		return false;
    	}
			saveAn();

			let json = null;
			if (isNew) {
				confField.name = 'tempName';
				conf.fields[conf.fields.length] = confField;
			} else json = JSON.stringify(confField);

			currAnalyse.leftLevel = d12func2($("#analyseD1"));
			currAnalyse.topLevel = d12func2($("#analyseD2"));
			confField.newAnalyse = currAnalyse;

			aly.checkConf(conf);
			if (confField._status != '') {
				alert(confField._status);
				if (isNew) {
					conf.fields.remove(confField);
				} else {
					confField = JSON.parse(json);
				}
				confField._status = '';
				return false;
			}
				
			aly.modifyConfFieldName(conf,confField,n);		    	
			
    	artDialog.defaults.zIndex = zIndexBak;
    	setTimeout(function(){aly.refresh();},1);
    	return true;
		}
		let saveAn = function(){
			let d4 = $('#analyseD4');
			currAnalyse.exp = d4.find('#analyseExp').val();
			let items = [];
			let is = d4.find('div[idx]');
			for (let i=0; i<is.length; i++) {
				items.push(
					{
						field:$(is[i]).find('#itemField').val()
						,value:$(is[i]).find('#itemValue').val() //当前值、查找值
						,aggr:$(is[i]).find('#itemAggr').val()
						,exp:$(is[i]).find('#analyseItemExp').val()
					}
				);
			}
			currAnalyse.items = items;
		}
		let d12func = function(d1,thisObj){
			var level = '';
			var d1ds = d1.find('div');
			for (var i=0; i<d1ds.length; i++){
				$(d1ds[i]).removeClass('ui-selected');
				if (level=='') $(d1ds[i]).addClass('ui-selected');
				if (d1ds[i] == thisObj) {
					level = $(thisObj).attr('level');
				}
			}
			return level;
		}
		let d12func2 = function(d1){
			var level = '';
			var d1ds = d1.find('div');
			for (var i=0; i<d1ds.length; i++){
				if ($(d1ds[i]).hasClass('ui-selected')) level = $($(d1ds[i])).attr('level');
				else break;
			}
			return level;
		}
		
	}
	,rpxFocusChanged : function() {
		//console.log(initRpxsOver);
		if (!initRpxsOver) return;
		var reports = aly.cache.reports;
		var topRpx = null;
		for (var i=0; i<reports.length; i++){
			if (reports[i].dlg.DOM.wrap.css('display').toLowerCase() == 'none') continue;
			if (topRpx == null) topRpx = reports[i];
			else {
				var z1 = reports[i].dlg.DOM.wrap.css('z-index');
				var z2 = topRpx.dlg.DOM.wrap.css('z-index');
				if (z2<z1) topRpx = reports[i];
			}
		}
		if (!topRpx) return;
		if (rqAnalyse.currRpx == topRpx.name) return;
		rqAnalyse.currRpx = topRpx.name;
		aly.refresh(true,true);
	}
	,focusReport : function(name) {
		var reports = aly.cache.reports;
		for (var i=0; i<reports.length; i++){
			if (reports[i].name == name) {
				reports[i].dlg.focus();
				break;
			}
		}
	}
	,refreshReport : function(confName, noCalc, noRefreshDialog) {
		if (noRefreshDialog) return;
		var reports = aly.cache.reports;
		for (var i=0; i<rqAnalyse.rpxs.length; i++) {
			var confi = rqAnalyse.rpxs[i];
			if (confName && confi.name != confName) continue;
			var dlg = null;
			for (var j=0; j<reports.length; j++) {
				if (reports[j].name == confi.name) {
					dlg = reports[j].dlg;
				}
			}
			if (!confi.dialog) confi.dialog = {open:1,top:random(200,50),left:random(500,40),width:550,height:300};
			if (dlg == null) {
				initDialogOver = false;
				dlg = art.dialog({
					id : dialogCount++,
					title : confi.name+"<img confNameLoading='"+confi.name+"' style='vertical-align:-3px;margin-left:4px;visibility:hidden;' src='"+contextPath+guideConf.guideDir+"/js/ztree/css/zTreeStyle/img/loading.gif'/>",
				    content: '<iframe name="'+confi.name+'" confName="'+confi.name+'" style="border:0;width:100%;height:100%;"></iframe>'
				    ,ok : null
				 	,close: function () {
				        this.hide();
				        for (var z=0; z<reports.length; z++) {
				        	if (reports[z].dlg == this) {
				        		var confz = aly.getRpx(reports[z].name);
				        		if (confz == null) break;
				        		confz.dialog.open = 0;
				        		break;
				        	} 
				        }
				        dialogs.remove(this);
						setTimeout(function(){aly.rpxFocusChanged();},1);
				        return false;
				    }
					,button : []
				    ,okVal : ''
				    ,cancelVal : ''
				    ,lock : false
				    //,follow:'#reportDiv'
				    ,duration : 0
				    ,width : confi.dialog.width+'px'
					,height : confi.dialog.height+'px'
					,opacity : 0.1
					,padding : '0'
					,fixed : false
					,top:confi.dialog.top+'px'
					,left:confi.dialog.left+'px'
					//,drag: false
				});
				dlg.DOM.wrap.find('.aui_main').css('padding-top','0');
				dlg.DOM.wrap.find('.aui_content').css('width','100%').css('height','100%');
				
				//dlg.follow('#reportConfTable');
				reports[reports.length] = {dlg:dlg,name:confi.name};
				initDialogOver = true;

				$('iframe[confName="'+confi.name+'"]').attr('src', contextPath + guideConf.guideDir+"jsp/empty.jsp?guideDir="+guideConf.guideDir+"&confName="+encodeURIComponent(confi.name));
			} else {
				artDialog.defaults.zIndex++;
				dlg.show();
				dlg.DOM.wrap.css('z-index',artDialog.defaults.zIndex);
        		confi.dialog.open = 1;
				
				//alert(wrap.css('z-index'));
			}
			var openHisory = false;
			if (dialogs.indexOf(dlg)==-1) {
				dialogs[dialogs.length] = dlg;
				if (guideConf.showHistoryRpx == 'yes' && confi._firstOpen) {
					var n = guideConf.resultRpxPrefixOnServer+confi.name+".rpx";
					$.ajax({
						type: 'POST',
						async : false,
						url: contextPath + "/DLServletAjax?d="+new Date().getTime(),
						data: {action:2,oper:'fileExist',file:n},
						success: function(data){
							if (data.indexOf('error:')==0) {
								alert(data.substring(6));
								return;
							}
							if (data == 1) {
								openHisory = true;
								//$('img[confNameLoading="'+conf.name+'"]').css('visibility','hidden');
								//$('iframe[confName="'+conf.name+'"]').attr('src', "");
								$('iframe[confName="'+confi.name+'"]').attr('src', guideConf.reportPage+"?reportId="+confi.reportId+"&guideDir="+guideConf.guideDir+"&isOlap=no"+"&confName="+encodeURIComponent(confi.name)+"&finish=1&rpxFile="+encodeURIComponent(n));
								return;
							}
						}
					});
				}
			}
			confi._firstOpen = false;

			if ((!openHisory) && confi.autoCalc==1 && !noCalc) aly.queryData(confi);
		}
	}
	,initPage : function(srcDs) {
		rqAnalyse.srcDs = srcDs;
	}
	,editCalcField : function(fieldName, callback) {
		var field = null;
		var conf = aly.getCurrRpx();
		var dataSet = aly.getDataSet(conf.dataSet);
		if (fieldName) {
			field = aly.getDataSetField(dataSet,fieldName);
		}
		zIndexBak = artDialog.defaults.zIndex;
		var fs = "";
		for (var i=0; i<dataSet.fields.length; i++) {
			var fieldi = dataSet.fields[i];
			//if (!fieldi.exp) fieldi.exp = fieldi.name;
			if (fieldi.exp != '' && fieldi.exp !== undefined) continue;
			fs += "<div style='float:left;cursor:pointer;padding:3px 10px 3px 0;'>"+fieldi.name+"</div>";
		}
		fs += "<div style='clear:both;'></div>";
		var dlg = art.dialog({
			id : dialogCount++,
			title : '编辑计算字段',
		    content: '<div style="margin:10px;">名　　称：<input type="text" id="calcFieldName" value="'+(field==null?"":field.name)+'" style="width:120px;"></div>'
		    	+'<div style="margin:10px;">数据类型：<select id="calcFieldType" style="width:120px;">'+raqDt.getDataTypeOptions(2)+'</select></div>'
		    	+'<div style="margin:10px;">表达式　：<input placeholder="集算器表达式，例：金额*(1-折扣)、year(now())-year(生日)" type="text" id="calcFieldExp" value="" style="width:370px;"></div>'
		    	+'<div style="margin:10px;" id="calcFieldSrcs">'+fs+'</div>'
		    ,ok : function() {
				var n = $.trim($('#calcFieldName').val());
		    	if (n == '') {
		    		alert("名称不能为空");
		    		return false;
		    	}
				var e = $.trim($('#calcFieldExp').val());
		    	if (e == '') {
		    		alert("表达式不能为空");
		    		return false;
		    	}
				var t = $.trim($('#calcFieldType').val());
				if (aly.getDataSetField(dataSet,n) && (field != null && field.name != n)) {
					alert("名称已存在");
					return false;
				}
		    	if (field == null) {
					dataSet.fields.push({name:n,exp:e,dataType:t,edit:''});
		    	} else {
		    		field.name = n;
		    		field.dataType = t;
		    		field.exp = e;
				}
		    	artDialog.defaults.zIndex = zIndexBak;
				if (callback) {
					callback(fieldName);
					return;
				}
		    }
		    ,cancel : function() {
		    	artDialog.defaults.zIndex = zIndexBak;
		    	return true;
		    }
		    ,okVal : '确定'
		    ,cancelVal : '取消'
		    ,lock : true
		    ,duration : 0
		    ,width : '530px'
			,height : '200px'
			,opacity : 0.1
			,padding : '2px 2px'
			,zIndex : 41000
		});
		
		if (field != null) {
			$('#calcFieldType').val(field.dataType);
			$('#calcFieldExp').val(field.exp);
		}
		$('#calcFieldSrcs').find('div').click(function(){
			$('#calcFieldExp').val($('#calcFieldExp').val()+" "+$(this).html());
		});
	}
	,generateReport : function(conf) {
		if (conf._status != '' && !conf._status){
			aly.checkConf(conf);
		}
		var success = function(data){
			if (data.indexOf('error:')==0) {
				if (data.indexOf('null')>=0) {
					alert('报表已超时失效，请访问查询页面重新查询！');
				} else {
					alert(data.substring(6));
				}
				$('img[confNameLoading="'+conf.name+'"]').css('visibility','hidden');
				$('iframe[confName="'+conf.name+'"]').attr('src', contextPath + guideConf.guideDir+"jsp/empty.jsp?guideDir="+guideConf.guideDir+"&confName="+encodeURIComponent(conf.name));
				return;
			}
			
			$('iframe[confName="'+conf.name+'"]').attr('src', guideConf.reportPage+"?reportId="+conf.reportId+"&guideDir="+guideConf.guideDir+"&isOlap=no"+"&confName="+encodeURIComponent(conf.name)+"&finish="+finish);
		}
		var finish = conf._dataOver;
		
		//conf, field, type:1src/2group/3select/4aggr/5field/6detail, outerOrder 
		aly.exps = function(conf, field, type, outerOrder, aggrCell, leftMain, topMain, currCell) {
			
			if (type == 1 || type == 5) {
				return "ds1."+field.name;
			}
			if (type == 4) {
				var aggr = field.aggr;
				if (aggr == 'count' || aggr == 'countd') aggr = 'sum';
				return "ds1." + aggr + "(" + field.name + ")";
			}
			if (type == 3) {
				if (outerOrder != '') outerOrder = ";" + outerOrder;
				return "ds1.select("+field.name+outerOrder+")";
			}
			if (type == 2) {
				var exp = field.name;
				var order = "";
				if (field.order != 0) order = (field.order==1?(";"+exp+":1"):(";"+exp+":-1"));
				else order = ";"+exp+":0";
				exp = "ds1.group(" + exp + order + ")";
				return exp;
			}
			if (type == 6) {
				//if(len(string(ds1.select(联系人姓名)))>20,left(string(ds1.select(联系人姓名)),20)+"...",string(ds1.select(联系人姓名)))
				var exp = field.name;
				return "if(len(string(ds1.select("+exp+")))>20,left(string(ds1.select("+exp+")),20)+\"...\",string(ds1.select("+exp+")))";
			} else if (type == 7) {
				//E4/sum(E4[`0;`0]{})
				if (field.analyse.analyseName == '占比') {
					return aggrCell+"/sum("+aggrCell+"["+leftMain+";"+topMain+"]{})";
				} else if (field.analyse.analyseName == '排名') {
					return "count("+aggrCell+"["+leftMain+";"+topMain+"]{"+aggrCell+">$"+aggrCell+"})+1";
				} else if (field.analyse.analyseName == '比上期') {
					return ""+aggrCell+"/"+aggrCell+"[-1]";
				} else if (field.analyse.analyseName == '累积') {
					return ""+aggrCell+"+"+currCell+"[-1]";
				} else if (field.analyse.analyseName == '同期比') {
					return "";
				} else if (field.analyse.analyseName == '排名') {
					return "";
				} else if (field.analyse.analyseName == '排名') {
					return "";
				}
				
			}
			return exp;
		}
		
		var cellCols = ["","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","AA","AB","AC","AD","AE","AF","AG","AH","AI","AJ","AK","AL"];
		var backColors = ['255,255,255','242,248,238','255,255,255','242,248,238','255,255,255','242,248,238','255,255,255','242,248,238','255,255,255','242,248,238','255,255,255','242,248,238','255,255,255','242,248,238','255,255,255','242,248,238','255,255,255','242,248,238'];
		var colWidths1 = [];
		var colWidths2 = [];
		if (conf.colWidths && conf.colWidths != "") {
			var cw = conf.colWidths.split(";");
			for (var i=0; i<cw.length; i++) {
				var cwi = cw[i].split(":");
				if (cwi.length == 2) {
					try {
						var cw1 = parseInt(cwi[0]);
						var cw2 = parseInt(cwi[1]);
						colWidths1.push(cw1);
						colWidths2.push(cw2);
					} catch(e) {}
				}
			}
		}
		var defaultWidth = guideConf.defaultWidth;

		var rowHeights1 = [];
		var rowHeights2 = [];
		if (conf.rowHeights && conf.rowHeights != "") {
			var rh = conf.rowHeights.split(";");
			for (var i=0; i<rh.length; i++) {
				var rhi = rh[i].split(":");
				if (rhi.length == 2) {
					try {
						var rh1 = parseInt(rhi[0]);
						var rh2 = parseInt(rhi[1]);
						rowHeights1.push(rh1);
						rowHeights2.push(rh2);
					} catch(e) {}
				}
			}
		}
		var defaultHeight = guideConf.defaultHeight;
		var formatExp = guideConf.formatExp?guideConf.formatExp:'';

		var func3 = function() {
			if (conf._dataCount == 0) {
				$('iframe[confName="'+conf.name+'"]').attr('src', contextPath + guideConf.guideDir+"jsp/empty.jsp?guideDir="+guideConf.guideDir+"&confName="+encodeURIComponent(conf.name));
				$('img[confNameLoading="'+conf.name+'"]').css('visibility','hidden');
				return;
			}
			finish = conf._dataOver;
			
			//{rowCount:1,colCount:1,rows:[{row:1,type:'IRowCell定义'}],cols:[{col:1,type:'IColCell定义'}],cells:[{row:1,col:1,row2:1,col2:1,format:'',valueExp:'',value:'',extend:'',leftMain:'左主格',topMain:'上主格'}]}
			var rpt = {rowCount:1,colCount:1,rows:[],cols:[],cells:[]};
			//1：单条记录，全是统计字段； 2：明细报表；3：分组及交叉报表
			var fields = [];
			for (var i=0; i<conf.fields.length; i++) {
				if (conf.fields[i]._status == '') fields[fields.length] = conf.fields[i];
			}

			var biaoti = {"name":"标题","backColor":-1641217,"color":-16777216,"hAlign":-47};
			var fenzu = {"name":"分组","backColor":-1641217,"color":-16777216,"hAlign":-47};
			var zhibiao = [];
			
			for (var i=0; i<guideConf.style.length; i++)
			{
				var si = guideConf.style[i];
				if (si.name == '标题') biaoti = si;
				else if (si.name == '分组') fenzu = si;
				else if (si.name.indexOf('指标')>=0) zhibiao.push(si);
			}
			if (zhibiao.length == 0) zhibiao = [{"name":"指标1","backColor":-1,"color":-16777216,"hAlign":-48},{"name":"指标2","backColor":-853778,"color":-16777216,"hAlign":-48}];

			var ts = "";
			var ls = "";
			if (conf.tops.length==0&&conf.lefts.length==0) {
				if (fields.length==0) {
					//alert("没有可展示的维度和指标");
					$('iframe[confName="'+conf.name+'"]').attr('src', contextPath + guideConf.guideDir+"jsp/empty.jsp?guideDir="+guideConf.guideDir+"&confName="+encodeURIComponent(conf.name));
					$('img[confNameLoading="'+conf.name+'"]').css('visibility','hidden');
					return;
				}
				
				var orders = '';
				for (var j=0; j<conf.fields.length; j++) {
					var fieldj = conf.fields[j];
					var exp = aly.exps(conf,fieldj,1);
					if (fieldj.order != 0) {
						if (orders != '') orders += ",";
						orders += exp+":"+(fieldj.order==1?1:-1);
					}
				}

				//,adjustSizeMode:48,textWrap:1,hAlign:208/209/210,color:'',backColor:''
				//map(list(0,2),list("男","女"))
				if (conf.isRowData) {
					var rhIdx = rowHeights1.indexOf(1);
					rpt.rows[0] = {row:1,type:161,height:rhIdx==-1?defaultHeight:rowHeights2[rhIdx]};
					for (var i=0; i<fields.length; i++) {
						rpt.cells.push({row:1,col:i+1,row2:1,col2:i+1,format:'',value:fields[i].name,valueExp:'',extend:16,leftMain:'',topMain:'',tip:5,adjustSizeMode:48,textWrap:1,hAlign:biaoti.hAlign,color:biaoti.color,backColor:biaoti.backColor,dispExp:'',formatExp:formatExp});//'230,244,255'
						rpt.cells.push({row:2,col:i+1,row2:2,col2:i+1,format:fields[i].format,value:'',valueExp:aly.exps(conf,fields[i],i==0?3:5,i==0?orders:''),extend:16,leftMain:'',topMain:'',tip:4,adjustSizeMode:48,textWrap:1,hAlign:zhibiao[0].hAlign,color:zhibiao[0].color,backColor:zhibiao[0].backColor,dispExp:mdUtils.getEditExp4Rpx(fields[i].srcEdit),formatExp:formatExp});
						var cwIdx = colWidths1.indexOf(i+1);
						rpt.cols[rpt.cols.length] = {col:i+1,type:177,width:cwIdx==-1?defaultWidth:colWidths2[cwIdx]};
					}
					rpt.rowCount = 2;
					rpt.colCount = fields.length;
				} else {
					var cwIdx = colWidths1.indexOf(1);
					rpt.cols[0] = {col:1,type:176,width:cwIdx==-1?defaultWidth:colWidths2[cwIdx]};
					for (var i=0; i<fields.length; i++) {
						rpt.cells.push({row:i+1,col:1,row2:i+1,col2:1,format:'',value:fields[i].name,valueExp:'',extend:16,leftMain:'',topMain:'',tip:5,adjustSizeMode:48,textWrap:1,hAlign:biaoti.hAlign,color:biaoti.color,backColor:biaoti.backColor,dispExp:'',formatExp:formatExp});
						rpt.cells.push({row:i+1,col:2,row2:i+1,col2:2,format:fields[i].format,value:'',valueExp:aly.exps(conf,fields[i],i==0?3:5,i==0?orders:''),extend:19,leftMain:'',topMain:'',tip:4,adjustSizeMode:48,textWrap:1,hAlign:zhibiao[0].hAlign,color:zhibiao[0].color,backColor:zhibiao[0].backColor,dispExp:mdUtils.getEditExp4Rpx(fields[i].srcEdit),formatExp:formatExp});
						var rhIdx = rowHeights1.indexOf(i+1);
						rpt.rows[i] = {row:i+1,type:161,height:rhIdx==-1?defaultHeight:rowHeights2[rhIdx]};
					}
					rpt.rowCount = fields.length;
					rpt.colCount = 2;
				}
			} else {
				var tops = [];
				var lefts = [];
				for (var i=0; i<conf.tops.length; i++) {
					tops.push(conf.tops[i].name);
				}
				for (var i=0; i<conf.lefts.length; i++) {
					lefts.push(conf.lefts[i].name);
				}
				
				//横向显示数据
				if (conf.isRowData) {
					//[top,left,scopeTop,scopeLeft]
					var levels = [];
					var maxTop = 0;
					var showAggrTitle = fields.length>1;
					if (fields.length == 0) maxTop = conf.tops.length;
					for (var i=0; i<fields.length; i++) {
						var level = [conf.tops.length,conf.lefts.length,conf.tops.length,conf.lefts.length];
						levels.push(level);
						if (fields[i]._fieldType == 'aggr' && fields[i].groups != null) {
							var max = 0;
							for (var j=0; j<fields[i].groups.length; j++) {
								var maxj = tops.indexOf(fields[i].groups[j]);
								if (maxj==-1) continue;
								if (maxj+1>max) max = maxj+1;
							}
							level[0] = max;
							if (max>maxTop) maxTop = max;
							max = 0;
							for (var j=0; j<fields[i].groups.length; j++) {
								var maxj = lefts.indexOf(fields[i].groups[j]);
								if (maxj==-1) continue;
								if (maxj+1>max) max = maxj+1;
							}
							level[1] = max;
						} else if (fields[i]._fieldType == 'newAnalyse') {
							var max = tops.indexOf(fields[i].newAnalyse.topLevel)+1;
							level[0] = max;
							if (max>maxTop) maxTop = max;
							max = lefts.indexOf(fields[i].newAnalyse.leftLevel)+1;
							level[1] = max;
						} else if (fields[i]._fieldType == 'analyse') {
							var fa = aly.getConfFieldByName(conf,fields[i].analyse.field);
							
							var max = 0;
							if (fa.groups != null) {
								for (var j=0; j<fa.groups.length; j++) {
									var maxj = tops.indexOf(fa.groups[j]);
									if (maxj==-1) continue;
									if (maxj+1>max) max = maxj+1;
								}
								level[0] = max;
								if (max>maxTop) maxTop = max;
								
								max = 0;
								for (var j=0; j<fa.groups.length; j++) {
									var maxj = lefts.indexOf(fa.groups[j]);
									if (maxj==-1) continue;
									if (maxj+1>max) max = maxj+1;
								}
								level[1] = max;
							}
							
							max = 0;
							for (var j=0; j<fields[i].analyse.scopeGroups.length; j++) {
								var maxj = tops.indexOf(fields[i].analyse.scopeGroups[j]);
								if (maxj==-1) continue;
								if (maxj+1>max) max = maxj+1;
							}
							level[2] = max;
							
							max = 0;
							for (var j=0; j<fields[i].analyse.scopeGroups.length; j++) {
								var maxj = lefts.indexOf(fields[i].analyse.scopeGroups[j]);
								if (maxj==-1) continue;
								if (maxj+1>max) max = maxj+1;
							}
							level[3] = max;
						} else maxTop = conf.tops.length;
					}
					
					rpt.rowCount = maxTop+(showAggrTitle?1:0)+(conf.lefts.length>0||fields.length>0?1:0);
					rpt.colCount = conf.lefts.length+(fields.length>0?fields.length:(conf.tops.length>0?1:0));
					var cellNames = [];
					var cellRefs = [];

					for (var i=0; i<maxTop; i++) {
						if (ts != '') ts += ",";
						ts += tops[i];
						var rhIdx = rowHeights1.indexOf(i+1);
						rpt.rows[rpt.rows.length] = {row:i+1,type:161,height:rhIdx==-1?defaultHeight:rowHeights2[rhIdx]};
					}
					for (var i=0; i<conf.lefts.length; i++) {
						if (ls != '') ls += ",";
						ls += lefts[i];
						var cwIdx = colWidths1.indexOf(i+1);
						rpt.cols[rpt.cols.length] = {col:i+1,type:177,width:cwIdx==-1?defaultWidth:colWidths2[cwIdx]};
					}
					if (rpt.rowCount>1 && conf.lefts.length>0) {
						var topNames = ',';
						for (var i=0; i<maxTop; i++) {
							if (i>0) topNames += ";";
							topNames += conf.tops[i].name;
						}
						var leftNames = '';
						for (var i=0; i<conf.lefts.length; i++) {
							if (i>0) leftNames += ";";
							leftNames += conf.lefts[i].name;
						}
						var aggrNames = '';
						if (fields.length == 1) aggrNames = ","+fields[0].name;
						var value11 = leftNames + "" + topNames + "" + aggrNames;
						rpt.cells.push({row:1,col:1,row2:rpt.rowCount-1,col2:conf.lefts.length,format:'',value:value11,diagonal:36,valueExp:'',extend:16,leftMain:'',topMain:'',tip:6,adjustSizeMode:48,textWrap:1,hAlign:biaoti.hAlign,color:biaoti.color,backColor:biaoti.backColor,dispExp:'',formatExp:formatExp});
					}
					for (var i=0; i<lefts.length; i++) {
						rpt.cells.push({row:rpt.rowCount,col:i+1,row2:rpt.rowCount,col2:i+1,format:conf.lefts[i].format,value:'',valueExp:aly.exps(conf,conf.lefts[i],2),extend:18,leftMain:'',topMain:'',tip:1,adjustSizeMode:48,textWrap:1,hAlign:fenzu.hAlign,color:fenzu.color,backColor:fenzu.backColor,dispExp:mdUtils.getEditExp4Rpx(conf.lefts[i].srcEdit),formatExp:formatExp});
						cellNames.push(lefts[i]);
						cellRefs.push(cellCols[i+1]+rpt.rowCount);
					}
					
					
					var currCol = conf.lefts.length;
					var finalFields = [];
					var finalCells = [];
					for (var i=maxTop; i>=0; i--) {
						var counti = 0;
						for (var j=0; j<fields.length; j++) {
							var level = levels[j];
							if (level[0]!=i) continue;
							currCol++;
							counti++;
							if (showAggrTitle) {
								rpt.cells.push({row:i+1,col:currCol,row2:rpt.rowCount-1,col2:currCol,format:'',value:fields[j].name,valueExp:'',extend:16,leftMain:'',topMain:'',tip:7,adjustSizeMode:48,textWrap:1,hAlign:biaoti.hAlign,color:biaoti.color,backColor:biaoti.backColor,dispExp:'',formatExp:formatExp});
								var rhIdx = rowHeights1.indexOf(i+1);
								rpt.rows[rpt.rows.length] = {row:i+1,type:161,height:rhIdx==-1?defaultHeight:rowHeights2[rhIdx]};
							}
							finalFields.push(fields[j]);
							var exp = "";
							var leftMain = level[1]>0?cellCols[level[1]]+rpt.rowCount:"`0";
							var topMain = level[0]>0?cellCols[conf.lefts.length+1]+level[0]:"`0";
							if (fields[j]._fieldType == 'detail') exp = aly.exps(conf,fields[j],6);
							else if (fields[j]._fieldType == 'aggr') exp = aly.exps(conf,fields[j],4);
							else if (fields[j]._fieldType == 'analyse') {
								//exp = aly.exps(conf,fields[i],7,);
							}
							var zhibiaoj = zhibiao[j%zhibiao.length];
							var cell = {row:rpt.rowCount,col:currCol,row2:rpt.rowCount,col2:currCol,format:fields[j].format,value:'',valueExp:exp,extend:16,leftMain:leftMain,topMain:topMain,tip:3,adjustSizeMode:48,textWrap:1,hAlign:zhibiaoj.hAlign,color:zhibiaoj.color,backColor:zhibiaoj.backColor,dispExp:'',formatExp:formatExp};
							finalCells.push(cell);
							rpt.cells.push(cell);
							cellNames.push(fields[j].name);
							cellRefs.push(cellCols[currCol]+rpt.rowCount);
						}
						if (i>0) {
							rpt.cells.push({row:i,col:lefts.length+1,row2:i,col2:fields.length==0?currCol+1:currCol,format:conf.tops[i-1].format,value:'',valueExp:aly.exps(conf,conf.tops[i-1],2),extend:19,leftMain:'',topMain:'',tip:2,adjustSizeMode:48,textWrap:1,hAlign:fenzu.hAlign,color:fenzu.color,backColor:fenzu.backColor,dispExp:mdUtils.getEditExp4Rpx(conf.tops[i-1].srcEdit),formatExp:formatExp});
							cellNames.push(tops[i-1]);
							cellRefs.push(cellCols[lefts.length+1]+i);
						}
					}

					//console.log(cellNames);
					//console.log(cellRefs);
					var rhIdx = rowHeights1.indexOf(rpt.rowCount);
					rpt.rows[rpt.rows.length] = {row:rpt.rowCount,type:161,height:rhIdx==-1?defaultHeight:rowHeights2[rhIdx]};

					for (var i=0; i<finalFields.length; i++) {
						if (finalFields[i]._fieldType != 'analyse') continue;
						var aggrCell = "";
						for (var j=0; j<finalFields.length; j++) {
							if (finalFields[j].name == finalFields[i].analyse.field) {
								aggrCell = cellCols[conf.lefts.length+j+1]+rpt.rowCount;
								break;
							}
						}
						var level = levels[i];
						var currCell = cellCols[conf.lefts.length+i+1]+rpt.rowCount;
						var leftMain = level[3]>0?cellCols[level[3]]+rpt.rowCount:"`0";
						var topMain = level[2]>0?cellCols[conf.lefts.length+1]+level[2]:"`0";
						finalCells[i].valueExp = aly.exps(conf,fields[i],7,null,aggrCell,leftMain,topMain,currCell);
					}

					for (var i=0; i<finalFields.length; i++) {
						if (finalFields[i]._fieldType != 'newAnalyse') continue;
						var valueExp = finalFields[i].newAnalyse.exp;
						for (var j=0; j<finalFields[i].newAnalyse.items.length; j++) {
							var itemj = finalFields[i].newAnalyse.items[j];
							var exp = "";
							if (itemj.value=='curr') exp = cellRefs[cellNames.indexOf(itemj.field)];
							else {
								exp = cellRefs[cellNames.indexOf(itemj.field)]+"[`0,`0]{"+itemj.exp+"}";
								for (var z=0; z<cellNames.length; z++) {
									exp = exp.replaceAll(cellNames[z]+"当前值","$"+cellRefs[z]);
									exp = exp.replaceAll(cellNames[z]+"查找值",cellRefs[z]);
									exp = exp.replaceAll(cellNames[z]+"当前序号","seq($"+cellRefs[z]+")");
									exp = exp.replaceAll(cellNames[z]+"查找序号","seq("+cellRefs[z]+")");
								}
								if (itemj.aggr=='first') exp = "valueat("+exp+",0)";
								else exp = itemj.aggr+"("+exp+")";
							}
							valueExp = valueExp.replaceAll("?"+(j+1),exp);
						}
						finalCells[i].valueExp = valueExp;
					}
				//纵向显示数据
				} else {
					//[left,top,scopeLeft,scopeTop]
					var levels = [];
					var maxLeft = 0;
					var showAggrTitle = fields.length>1;
					if (fields.length == 0) maxLeft = conf.lefts.length;
					for (var i=0; i<fields.length; i++) {
						var level = [conf.lefts.length,conf.tops.length,conf.lefts.length,conf.tops.length];
						levels.push(level);
						if (fields[i]._fieldType == 'aggr' && fields[i].groups != null) {
							var max = 0;
							for (var j=0; j<fields[i].groups.length; j++) {
								var maxj = lefts.indexOf(fields[i].groups[j]);
								if (maxj==-1) continue;
								if (maxj+1>max) max = maxj+1;
							}
							level[0] = max;
							if (max>maxLeft) maxLeft = max;
							max = 0;
							for (var j=0; j<fields[i].groups.length; j++) {
								var maxj = tops.indexOf(fields[i].groups[j]);
								if (maxj==-1) continue;
								if (maxj+1>max) max = maxj+1;
							}
							level[1] = max;
						} else if (fields[i]._fieldType == 'newAnalyse') {
							var max = lefts.indexOf(fields[i].newAnalyse.leftLevel)+1;
							level[0] = max;
							if (max>maxLeft) maxLeft = max;
							max = tops.indexOf(fields[i].newAnalyse.topLevel)+1;
							level[1] = max;
						} else if (fields[i]._fieldType == 'analyse') {
							var fa = aly.getConfFieldByName(conf,fields[i].analyse.field);
							
							var max = 0;
							if (fa.groups != null) {
								for (var j=0; j<fa.groups.length; j++) {
									var maxj = lefts.indexOf(fa.groups[j]);
									if (maxj==-1) continue;
									if (maxj+1>max) max = maxj+1;
								}
								level[0] = max;
								if (max>maxLeft) maxLeft = max;
								
								max = 0;
								for (var j=0; j<fa.groups.length; j++) {
									var maxj = tops.indexOf(fa.groups[j]);
									if (maxj==-1) continue;
									if (maxj+1>max) max = maxj+1;
								}
								level[1] = max;
							}
							
							max = 0;
							for (var j=0; j<fields[i].analyse.scopeGroups.length; j++) {
								var maxj = lefts.indexOf(fields[i].analyse.scopeGroups[j]);
								if (maxj==-1) continue;
								if (maxj+1>max) max = maxj+1;
							}
							level[2] = max;
							
							max = 0;
							for (var j=0; j<fields[i].analyse.scopeGroups.length; j++) {
								var maxj = tops.indexOf(fields[i].analyse.scopeGroups[j]);
								if (maxj==-1) continue;
								if (maxj+1>max) max = maxj+1;
							}
							level[3] = max;
						} else maxLeft = conf.lefts.length;
					}
					
					rpt.rowCount = conf.tops.length+(fields.length>0?fields.length:(conf.lefts.length>0?1:0));
					rpt.colCount = maxLeft+(showAggrTitle?1:0)+(conf.tops.length>0||fields.length>0?1:0);

					for (var i=0; i<maxLeft; i++) {
						if (ls != '') ls += ",";
						ls += lefts[i];
						var cwIdx = colWidths1.indexOf(i+1);
						rpt.cols[rpt.cols.length] = {col:i+1,type:177,width:cwIdx==-1?defaultWidth:colWidths2[cwIdx]};
					}
					for (var i=0; i<conf.tops.length; i++) {
						if (ts != '') ts += ",";
						ts += tops[i];
						var rhIdx = rowHeights1.indexOf(i+1);
						rpt.rows[rpt.rows.length] = {row:i+1,type:161,height:rhIdx==-1?defaultHeight:rowHeights2[rhIdx]};
					}
					if (rpt.colCount>1 && conf.tops.length>0) {
						var topNames = ',';
						for (var i=0; i<conf.tops.length; i++) {
							if (i>0) topNames += ";";
							topNames += conf.tops[i].name;
						}
						var leftNames = '';
						for (var i=0; i<maxLeft; i++) {
							if (i>0) leftNames += ";";
							leftNames += conf.lefts[i].name;
						}
						var aggrNames = '';
						if (fields.length == 1) aggrNames = ","+fields[0].name;
						var value11 = leftNames + "" + topNames + "" + aggrNames;
						rpt.cells.push({row:1,col:1,row2:conf.tops.length,col2:rpt.colCount-1,format:'',value:value11,diagonal:36,valueExp:'',extend:16,leftMain:'',topMain:'',tip:6,adjustSizeMode:48,textWrap:1,hAlign:biaoti.hAlign,color:biaoti.color,backColor:biaoti.backColor,dispExp:'',formatExp:formatExp});
					}
					for (var i=0; i<conf.tops.length; i++) {
						rpt.cells.push({row:i+1,col:rpt.colCount,row2:i+1,col2:rpt.colCount,format:conf.tops[i].format,value:'',valueExp:aly.exps(conf,conf.tops[i],2),extend:19,leftMain:'',topMain:'',tip:2,adjustSizeMode:48,textWrap:1,hAlign:fenzu.hAlign,color:fenzu.color,backColor:fenzu.backColor,dispExp:mdUtils.getEditExp4Rpx(conf.tops[i].srcEdit),formatExp:formatExp});
					}

					var currRow = conf.tops.length;
					var finalFields = [];
					var finalCells = [];
					for (var i=maxLeft; i>=0; i--) {
						var counti = 0;
						for (var j=0; j<fields.length; j++) {
							var level = levels[j];
							if (level[0]!=i) continue;
							currRow++;
							counti++;
							if (showAggrTitle) {
								rpt.cells.push({row:currRow,col:i+1,row2:currRow,col2:rpt.colCount-1,format:'',value:fields[j].name,valueExp:'',extend:16,leftMain:'',topMain:'',tip:7,adjustSizeMode:48,textWrap:1,hAlign:biaoti.hAlign,color:biaoti.color,backColor:biaoti.backColor,dispExp:'',formatExp:formatExp});
							}
							finalFields.push(fields[j]);
							var exp = "";
							var topMain = level[1]>0?cellCols[rpt.colCount]+level[1]:"`0";
							var leftMain = level[0]>0?cellCols[level[0]]+(conf.tops.length+1):"`0";
							if (fields[j]._fieldType == 'detail') exp = aly.exps(conf,fields[j],6);
							else if (fields[j]._fieldType == 'aggr') exp = aly.exps(conf,fields[j],4);
							else if (fields[j]._fieldType == 'analyse') {
								//exp = aly.exps(conf,fields[i],7,);
							}
							var zhibiaoj = zhibiao[j%zhibiao.length];
							var cell = {row:currRow,col:rpt.colCount,row2:currRow,col2:rpt.colCount,format:fields[j].format,value:'',valueExp:exp,extend:16,leftMain:leftMain,topMain:topMain,tip:3,adjustSizeMode:48,textWrap:1,hAlign:zhibiaoj.hAlign,color:zhibiaoj.color,backColor:zhibiaoj.backColor,dispExp:'',formatExp:formatExp};
							finalCells.push(cell);
							rpt.cells.push(cell);
						}
						if (i>0) rpt.cells.push({row:conf.tops.length+1,col:i,row2:fields.length==0?currRow+1:currRow,col2:i,format:conf.lefts[i-1].format,value:'',valueExp:aly.exps(conf,conf.lefts[i-1],2),extend:18,leftMain:'',topMain:'',tip:1,adjustSizeMode:48,textWrap:1,hAlign:fenzu.hAlign,color:fenzu.color,backColor:fenzu.backColor,dispExp:mdUtils.getEditExp4Rpx(conf.lefts[i-1].srcEdit),formatExp:formatExp});
					}

					var cwIdx = colWidths1.indexOf(rpt.colCount);
					rpt.cols[rpt.cols.length] = {col:rpt.colCount,type:177,width:cwIdx==-1?defaultWidth:colWidths2[cwIdx]};

					for (var i=0; i<finalFields.length; i++) {
						if (finalFields[i]._fieldType != 'analyse') continue;
						var aggrCell = "";
						for (var j=0; j<finalFields.length; j++) {
							if (finalFields[j].name == finalFields[i].analyse.field) {
								aggrCell = cellCols[rpt.colCount]+(conf.tops.length+j+1);
								break;
							}
						}
						var level = levels[i];
						var currCell = cellCols[rpt.colCount]+(conf.tops.length+i+1);
						var topMain = level[3]>0?cellCols[rpt.colCount]+level[3]:"`0";
						var leftMain = level[2]>0?cellCols[level[2]]+(conf.tops.length+1):"`0";
						finalCells[i].valueExp = aly.exps(conf,fields[i],7,null,aggrCell,leftMain,topMain,currCell);
					}
				}			
			}

			for (var i=1; i<=rpt.rowCount; i++) {
				var rhIdx = rowHeights1.indexOf(i);
				rpt.rows.push({row:i,height:rhIdx==-1?defaultHeight:rowHeights2[rhIdx]});
			}
			for (var i=1; i<=rpt.colCount; i++) {
				var cwIdx = colWidths1.indexOf(i);
				rpt.cols.push({col:i,width:cwIdx==-1?defaultWidth:colWidths2[cwIdx]});
			}

			$.ajax({
				type : 'POST',
				async : true,
				url: contextPath + "/DLServletAjax?d="+new Date().getTime(),
				data: {action:2,oper:'genReport',reportId:conf.reportId,title:conf.name,rpt:JSON.stringify(rpt).replaceAll('"','<d_q>'),lefts:ls,tops:ts,maxSize:guideConf.maxReportSize,resultRpxPrefixOnServer:guideConf.resultRpxPrefixOnServer},
				//confs:[{type:1自定义分析报表/2模板报表,name:'报表名称',reportId:'',template:'',show:0/1,isRowData:1,lefts:[{name:'',item:itemId,use:1,order:0无序/1升序/2降序}],tops:[],fields:[],wheres:[{item:itemId}]}...] 调序、排序、改名
				success: success
			});
		}

		var func2 = function() {
			if (conf._dataCount == 0) {
				$('iframe[confName="'+conf.name+'"]').attr('src', contextPath + guideConf.guideDir+"jsp/empty.jsp?guideDir="+guideConf.guideDir+"&confName="+encodeURIComponent(conf.name));
				$('img[confNameLoading="'+conf.name+'"]').css('visibility','hidden');
				return;
			}
			finish = conf._dataOver;
			var fields = "";
			for (var j=0; j<conf.fields.length; j++) {
				var fj = conf.fields[j];
				if (fj == null) {
					$('iframe[confName="'+conf.name+'"]').attr('src', contextPath + guideConf.guideDir+"jsp/empty.jsp?guideDir="+guideConf.guideDir+"&confName="+encodeURIComponent(conf.name));
					return;
				}
				if (j>0) fields += "<;>";
				var disps = '';
				if (fj.aggr == '') disps = mdUtils.getEditExp4Rpx(fj.srcEdit);
				fields += fj.macroName+"<,>"+fj.name+"<,>"+disps;
			}
			
			$.ajax({
				type : 'POST',
				async : true,
				url: contextPath + "/DLServletAjax?d="+new Date().getTime(),
				data: {action:2,oper:'calcReport',reportId:conf.reportId,title:conf.name,reportType:conf.type,fields:fields,template:conf.template,resultRpxPrefixOnServer:guideConf.resultRpxPrefixOnServer},
				//confs:[{type:1自定义分析报表/2模板报表,name:'报表名称',reportId:'',template:'',show:0/1,isRowData:1,lefts:[{name:'',item:itemId,use:1,order:0无序/1升序/2降序}],tops:[],fields:[],wheres:[{item:itemId}]}...] 调序、排序、改名
				success:success
			});
		}
		if (conf.type == 2) {
			if (conf.fields.length == 0) {
				$('iframe[confName="'+conf.name+'"]').attr('src', contextPath + guideConf.guideDir+"jsp/empty.jsp?guideDir="+guideConf.guideDir+"&confName="+encodeURIComponent(conf.name));
				$('img[confNameLoading="'+conf.name+'"]').css('visibility','hidden');
				return;
			}
			for (var j=0; j<conf.fields.length; j++) {
				var fj = conf.fields[j];
				if (fj == null) {
					$('iframe[confName="'+conf.name+'"]').attr('src', contextPath + guideConf.guideDir+"jsp/empty.jsp?guideDir="+guideConf.guideDir+"&confName="+encodeURIComponent(conf.name));
					$('img[confNameLoading="'+conf.name+'"]').css('visibility','hidden');
					return;
				}
			}
		}
		conf.type==1?func3():func2();
	}
	,queryData : function(conf) {
		if (conf.tops.length+conf.lefts.length+conf.fields.length==0) {
			aly.generateReport(conf);
			return;
		}

		if (conf.type == 2){
			for (var i=0; i<conf.fields.length; i++){
				if(conf.fields[i]==null) return;
			}
		}
		var dataSet = aly.getDataSet(conf.dataSet);
		if (dataSet.type == 6){
			var dql = aly.getDql(conf);
			if (dql == null) {
				return;
			}
			$('img[confNameLoading="'+conf.name+'"]').css('visibility','visible');
			var reportId = conf.reportId;
			$.ajax({
				type: 'POST',
				async : false,
				url: contextPath + "/DLServletAjax?d="+new Date().getTime(),
				data: {action:2,oper:'queryDqlData',reportId:conf.reportId,dql:dql,dataSource:dataSet.dataSource,maxDataSize:guideConf.maxDataSize},
				success: function(data){
					if (data.indexOf('error:')==0) {
						alert(data.substring(6));
						return;
					}
					data.split(":");
					conf._dataCount = data[0];
					if (data[0] == 0)
					{
						alert("未查询到数据");
						return;
					}
					conf._dataOver = data[1];
					aly.generateReport(conf);
					//alert(data);
				}
			});
		} else {
			calcDfxData(null, conf, function(d1){
				if (d1 == 'empty') {
					$('iframe[confName="'+conf.name+'"]').attr('src', contextPath + guideConf.guideDir+"jsp/empty.jsp?guideDir="+guideConf.guideDir+"&confName="+encodeURIComponent(conf.name));
					$('img[confNameLoading="'+conf.name+'"]').css('visibility','hidden');
					return;
				}
				if (d1.indexOf('error:')==0) {
					alert(d1.substring(6));
					$('iframe[confName="'+conf.name+'"]').attr('src', contextPath + guideConf.guideDir+"jsp/empty.jsp?guideDir="+guideConf.guideDir+"&confName="+encodeURIComponent(conf.name));
					$('img[confNameLoading="'+conf.name+'"]').css('visibility','hidden');
					return;
				}
				d1 = eval('('+d1+')');
				//conf._dataCount = data[0];
				conf._dataOver = d1.finish;
				aly.generateReport(conf);
			});
		}
	}
}


function calcDfxData(dataSetField, conf, callback) {
	var r = aly.getDfxExps(dataSetField, conf);
	var ds = aly.getDataSet(conf.dataSet);
	if (r == null) {
		callback("empty");
		return;
	}
	//1结果返回给前台,2结果转成DataSet保存起来
	var cacheType = 1;
	var reportId = "where";
	if (dataSetField == null) {
		reportId = conf.reportId;
		cacheType = 2;
	}
	var calcs = r.calcs;//"\"aa\"+说明:a1<;>\"bb\"+a1:a2";
	var filters = r.filters;//"!like(a1,\"*软*\")<;>!like(a2,\"*软*\")";
	var fields = r.fields;//"说明,a1<;>说明,a2";
	var resultExp = r.resultExp;
	var dataId = r.dataId;

	//var resultExp = "groups(说明:A;count(a2):B;1)";
	//console.log(r);
	//getIds("d:/temp/a1",,,,);

	function f() {
		$.ajax({
			type: 'POST',
			async : false,
			url: contextPath + "/DLServletAjax?d="+new Date().getTime(),
			data: {action:2,oper:'calc',reportId:reportId,dataId:dataId,cacheType:cacheType,calcs:calcs,filters:filters,fields:fields,resultExp:resultExp,types:r.types,dataFileType:guideConf.dataFileType},
			success: function(data){
				if (data.indexOf('error:')==0) {
					alert(data.substring(6));
					return;
				} else if (data.indexOf('error:')>0) {
					data = eval("("+data+")");
					if (data.action == 'reQuery') {
						if (confirm(data.error)){
							aly.queryDataSet(1,ds.name,function(data){
								refreshStatus(ds,function(){
									f();
								});
							});
						}
						return;
					}
				}
				callback(data);
			}
		});
	}
	f();
}



function canDrill(reportConfName) {
	var conf = aly.getRpx(reportConfName);
	if (!aly.isGroupConf(conf)) return false;
	return true;
}

var drillCounter = 1;
function reportCellDrill(reportConfName, topValues, leftValues, fieldValues, detail) {
	var conf = aly.getRpx(reportConfName);
	if (!aly.isGroupConf(conf)) return;
	var conf2 = JSON.parse(JSON.stringify(conf));
	var conf2Name = '';
	conf2.dialog.top = conf2.dialog.top+(0.5-Math.random())*100;
	conf2.dialog.left = conf2.dialog.left+(0.5-Math.random())*200;
	//[{"level":1,"fieldInfo":{"disp":"雇员.市","dataType":1,"useTreeDisp":false,"edit":"市","exp":"市","valueType":2,"values":"市"},"oper":"等于","values":"30101","disp":""}]
	//
	var dataSet = aly.getDataSet(conf.dataSet);
	for (var i=0; i<topValues.length; i++)
	{
		if (conf2Name != '') conf2Name += '_'
		conf2Name += topValues[i];
		if (dataSet.type == 6) {
			var whereConf = [{"level":1,"fieldInfo":transWhereInfo(conf2.tops[i].src,null,false),"oper":"等于","values":""+mdUtils.getEditStyleValue(conf2.tops[i].srcEdit,topValues[i]),"disp":""}];
			conf2.tops[i].where = {conf:whereConf,exp:whereUtils.getExp(whereConf, "T1.", 1),disp:whereUtils.getDisp(whereConf)};	
		} else {
			var whereConf = [{"level":1,"fieldInfo":{disp:conf2.tops[i].name,dataType:conf2.tops[i].dataType,edit:conf2.tops[i].srcEdit,exp:conf2.tops[i].src,valueType:1,values:""},"oper":"等于","values":""+mdUtils.getEditStyleValue(conf2.tops[i].srcEdit,topValues[i]),"disp":""}];
			conf2.tops[i].where = {conf:whereConf,exp:whereUtils.getExp(whereConf, "", 1, 2),disp:whereUtils.getDisp(whereConf)};	
		}
	}
	for (var i=0; i<leftValues.length; i++)
	{
		if (conf2Name != '') conf2Name += '_'
		conf2Name += leftValues[i];
		if (dataSet.type == 6) {
			var whereConf = [{"level":1,"fieldInfo":transWhereInfo(conf2.lefts[i].src,null,false),"oper":"等于","values":""+mdUtils.getEditStyleValue(conf2.lefts[i].srcEdit,leftValues[i]),"disp":""}]
			conf2.lefts[i].where = {conf:whereConf,exp:whereUtils.getExp(whereConf, "T1.", 1),disp:whereUtils.getDisp(whereConf)};	
		} else {
			var whereConf = [{"level":1,"fieldInfo":{disp:conf2.lefts[i].name,dataType:conf2.lefts[i].dataType,edit:conf2.lefts[i].srcEdit,exp:conf2.lefts[i].src,valueType:1,values:""},"oper":"等于","values":""+mdUtils.getEditStyleValue(conf2.lefts[i].srcEdit,leftValues[i]),"disp":""}];
			conf2.lefts[i].where = {conf:whereConf,exp:whereUtils.getExp(whereConf, "", 1, 2),disp:whereUtils.getDisp(whereConf)};	
		}
	}
	for (var i=0; i<conf2.tops.length ; i++ ) conf2.fields.push(conf2.tops[i]);
	for (var i=0; i<conf2.lefts.length ; i++ ) conf2.fields.push(conf2.lefts[i]);
	conf2.tops = [];
	conf2.lefts = [];
	conf2.name = "["+conf2Name+"]钻取";//"钻取结果" + (drillCounter++);
	if (aly.getRpx(conf2.name) == null){
		rqAnalyse.rpxs.push(conf2);;
	}
	rqAnalyse.currRpx = conf2.name;
	aly.refresh();
}

function downloadRpx(n) {
	n = guideConf.resultRpxPrefixOnServer+n+".rpx";
	$.ajax({
		type: 'POST',
		async : false,
		url: contextPath + "/DLServletAjax?d="+new Date().getTime(),
		data: {action:2,oper:'fileExist',file:n},
		success: function(data){
			if (data.indexOf('error:')==0) {
				alert(data.substring(6));
				return;
			}
			if (data != 1) {
				alert('报表已不存在，无法下载');
				return;
			}
			$('#downloadForm #path').val(n);
			$('#downloadForm #content').val("");
			$('#downloadForm #mode').val("");
			$('#downloadForm').submit();
		}
	});
}

function saveFile(content,type,needDataPath,oldDataPath) {
	var cb = function() {
		var name = $.trim($('#saveGrpxName').val());
		if (name.indexOf('.'+type) == -1) name = name + "."+type;
		var onServer = type=='qyx'?guideConf.qyxFolderOnServer:(type=='olap'?guideConf.olapFolderOnServer:"");
		$('#downloadForm #path').val(onServer+"/"+$.trim($('#saveGrpxPath1').val())+"/"+name);
		$('#downloadForm #content').val(content);
		var mode = $('#saveGrpxChk1')[0].checked?"client":"";
		mode += $('#saveGrpxChk2')[0].checked?"server":"";
		$('#downloadForm #mode').val(mode);
		$('#downloadForm').submit();
	}

	zIndexBak = artDialog.defaults.zIndex;
	var dlg = art.dialog({
		id : dialogCount++,
		title : '保存'+type,
	    content: '<input id="saveGrpxName" type="text" placeholder="文件名" style="width:150px;margin:5px 22px;">'
	    	+'<div><input id="saveGrpxChk1" type="checkbox" checked style="cursor:pointer;vertical-align:-2px;">下载到本地</div>'
	    	+'<div><input id="saveGrpxChk2" type="checkbox" style="cursor:pointer;"><input id="saveGrpxPath1" type="text" placeholder="保存到服务器的目录，如：/sales/" style="width:350px;margin:10px 2px;"></div>'
	    	+'<div style="'+(needDataPath?'':'display:none;')+'"><input style="cursor:pointer;" id="saveGrpxChk3" type="checkbox"><input id="saveGrpxPath2" type="text" placeholder="在服务器上缓存数据文件的目录" style="width:350px;margin:0px 2px;"></div>'
    	,button: [
	         {
	             name: '保存',
	             callback: function() {
					var name = $.trim($('#saveGrpxName').val());
	 				if (name.length == 0) {
	 					alert("文件名不能为空");
	 					return false;
	 				}
	 				if (!($('#saveGrpxChk1')[0].checked || $('#saveGrpxChk2')[0].checked)) {
	 					alert("下载到本地、保存到服务器至少要勾选一个");
	 					return false;
	 				}
					var fileExist = false;
					if ($('#saveGrpxChk2')[0].checked) {
						var onServer = type=='qyx'?guideConf.qyxFolderOnServer:(type=='olap'?guideConf.olapFolderOnServer:"");
						onServer += "/"+$.trim($('#saveGrpxPath1').val())+"/"+name;
						$.ajax({
							type: 'POST',
							async : false,
							url: contextPath + "/DLServletAjax?d="+new Date().getTime(),
							data: {action:2,oper:'fileExist',file:onServer},
							success: function(data){
								if (data.indexOf('error:')==0) {
									alert(data.substring(6));
									return;
								}
								fileExist = data==1;
								
							}
						});
						if (fileExist) {
							if (!confirm("文件已存在，是否覆盖？")) {
								return;
							}
						}
					}

			    	if ($('#saveGrpxChk3')[0].checked) {
		 				var path = guideConf.dataFolderOnServer+"/"+$.trim($('#saveGrpxPath2').val())+"/"+name+(guideConf.dataFileType.toLowerCase()=="binary"?".bin":".txt");
				    	$.ajax({
				    		type: 'POST',
				    		async : false,
				    		url: contextPath + "/DLServletAjax?d="+new Date().getTime(),
				    		data: {action:2,oper:'saveCacheData',path:path,dataId:topResource.dataId},
				    		success: function(data){
				    			content = content.replaceAll(oldDataPath,path);
				    			cb();
				    		}
				    	});
			    	} else cb(); 
			    	artDialog.defaults.zIndex = zIndexBak;
				},
	            focus: true
	         },
	         {
	             name: '取消'
	         }
	     ]
	    ,cancel : function() {
	    	artDialog.defaults.zIndex = zIndexBak;
	    	return true;
	    }
	    ,okVal : '保存'
	    ,cancelVal : '取消'
	    ,lock : true
	    ,duration : 0
	    ,width : '400px'
		,height : '150px'
		,opacity : 0.1
		,padding : '2px 2px'
		,zIndex : 41000
	});
}

function openFile(type,callback) {
	var data1 = "";
	$.ajax({
		type: 'POST',
		async : false,
		url: contextPath + "/DLServletAjax?d="+new Date().getTime(),
		data: {action:2,oper:'getFiles',dfxFolderOnServer:guideConf.dfxFolderOnServer,qyxFolderOnServer:guideConf.qyxFolderOnServer,rpxFolderOnServer:guideConf.rpxFolderOnServer,inputFileFolderOnServer:guideConf.inputFileFolderOnServer,olapFolderOnServer:guideConf.olapFolderOnServer},
		success: function(data){
			if (data.indexOf('error:')==0) {
				alert(data.substring(6));
				return;
			}
			data1 = data;
			//data1 = "var existGrpx = ['WEB-INF/files/grpx/5666.grpx'];";
		}
	});
	eval(data1);

	zIndexBak = artDialog.defaults.zIndex;
	var dlg = art.dialog({
		id : dialogCount++,
		title : '打开'+type,
	    content: ''
	    	+'<a href="javascript:void(0);" title="打开'+type+'文件" style="display:none;overflow:hidden;display:-moz-inline-box;display:inline-block;width:140px;height:30px;vertical-align:top;background-image:url('+contextPath+guideConf.guideDir+'/img/guide/43.png);margin:10px;">'
	    	+'<form id="openForm" METHOD=POST ACTION="'+contextPath+'/servlet/dataSphereServlet?action=38" ENCTYPE="multipart/form-data" target=hiddenFrame>'
	    		+'<input id="openGrpxFile" name="openGrpxFile" type="file" style="height: 30px; margin-left:-80px;filter:alpha(opacity=0);opacity:0;cursor:pointer;" accept=".'+type+'" />'
	    		+'<input type=hidden name=path id=upPath value="tmp">'
	    	+'</form>'
	    	+'</a>'
	    	+'<div style="margin-left:10px;"><input id="openGrpxBut" type="button" value="打开服务器文件" style="margin:0 5px 0 0;height:30px;width:140px;"><span id="openGrpxSel"></span></div>'
    	,button: [
	     ]
	    ,close : function() {
			artDialog.defaults.zIndex = zIndexBak;
	    	return true;
	    }
	    //,okVal : '保存'
	    //,cancelVal : '取消'
	    ,lock : true
	    ,duration : 0
	    ,width : '500px'
		,height : '130px'
		,opacity : 0.1
		,padding : '2px 2px'
		,zIndex : 41000
	});

	openFileCb = callback;
	$('#openGrpxFile').change(function(){
		openFileName = $('#openGrpxFile').val();
		var idx = openFileName.lastIndexOf('/');
		if (idx == -1) idx = openFileName.lastIndexOf('\\');
		if (idx >= 0) openFileName = openFileName.substring(idx + 1);
		var f = openFileName.toLowerCase();
		if (f.indexOf('.'+type) == -1) {
			alert("请选择[."+type+"]类型文件！");
			return;
		}
		//alert(f);
		$('#openForm').submit();
	});

	var exists = type=='qyx'?existQyx:(type=='olap'?existOlap:[]);
	var selDom1 = getSelectDom(exists.length==0?[""]:exists, exists.length==0?["服务器没有"+type+"文件"]:exists,"" );
	selDom1.css({'color':'#333333','background-color': '#F8F8F8','border': '1px solid #E4E4E4','padding':'4px','margin-top':'9px','width':'300px','height':'28px'}).attr('title','').change(function(){
	});
	$('#openGrpxSel').append(selDom1);
	
	$('#openGrpxBut').click(function(){
		if (selDom1.val() == '') return;
		openFileCb(null,selDom1.val());
	});
}
var openFileName;
var openFileCb;
function openFileCallback(content) {
	openFileCb(content)
}

function openQyx(){
	openFile("qyx",function(content, file){
		var form = $('<form method="post" accept-charset="UTF-8"></form>');
		var url = window.location.href;
		if (url.indexOf('?')>=0) url = url.substring(0,url.indexOf('?'));
		form.attr('action',url);
		form.attr('target', "_self");
		form.append('<input type="hidden" name="qyx" value="'+(content==null?file:content)+'">');
		$('body').append(form);
		form[0].submit();
	});
}
function saveQyx(){
	saveFile(domUtils.toString(),"qyx",false,"");
}
function openOlap(){
	openFile("olap",function(content, file){
		var form = $('<form method="post" accept-charset="UTF-8"></form>');
		var url = window.location.href;
		if (url.indexOf('?')>=0) url = url.substring(0,url.indexOf('?'));
		form.attr('action',url);
		form.attr('target', "_self");
		form.append('<input type="hidden" name="olap" value="'+(content==null?file:content)+'">');
		$('body').append(form);
		form[0].submit();
	});
}
function saveOlap(){
	saveFile(aly.toString(),"olap",false,"");
}


var dialogs = [];
//1平铺；2重叠
function relocalReports(type){
	if (dialogs.length == 0) return; 
	var w = $(window).width();
	var h = $(window).height() - 50;
	if (type == 1) {
		var w1 = w/dialogs.length;
		var h1 = h;
		for (var i=0; i<dialogs.length; i++) {
			relocalReports2(dialogs[i],w1-40,h1-50,i*w1+10,50,i*1000);
			relocalReports2(dialogs[i],w1-40,h1-50,i*w1+10,50,i*1000);
		}
	} else if (type == 2) {
		var w1 = w;
		var h1 = h/dialogs.length;
		for (var i=0; i<dialogs.length; i++) {
			relocalReports2(dialogs[i],w1-40,h1-50,0+10,50+i*h1,i*1000);
			relocalReports2(dialogs[i],w1-40,h1-50,0+10,50+i*h1,i*1000);
		}
	} else if (type == 3) {
		var w1 = w - dialogs.length*60;
		var h1 = h - dialogs.length*30-20;
		for (var i=0; i<dialogs.length; i++) {
			relocalReports2(dialogs[i],w1,h1,10+i*60,50+i*30,i*1000);
			relocalReports2(dialogs[i],w1,h1,10+i*60,50+i*30,i*1000);
		}
	}

	var reports = aly.cache.reports;
	for (var i=0; i<reports.length; i++) {
		var wrap = reports[i].dlg.DOM.wrap;
		var confz = aly.getRpx(reports[i].name);
		confz.dialog.width = wrap.width();
		confz.dialog.height = wrap.height();
		confz.dialog.top = wrap.position().top;
		confz.dialog.left = wrap.position().left;
	}
}
function relocalReports2(dlg,w,h,l,t,time) {
	dlg.size(w,h);
	dlg.position(l,t);
}
	
var zIndexBak;

