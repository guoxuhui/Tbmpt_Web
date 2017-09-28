/*-----------查询模块api-------------------------------------------*/
var queryApi = {
	//界面上发生一些事件后，用户还想自己做点事情，event里的这些方法是需要用户自定义实现的
	event : {
		//初始化一些界面参数
		init : function(){
			/*这些参数可以控制页面的初始状态
			guideConf.dqlCategory = ['国内公司','海外公司'];
			guideConf.fixedTable = '';
			guideConf.outerCondition = "";
			guideConf.showSubTable = 'yes';
			guideConf.maxDimSize = '5000';
			guideConf.showNullGroup = 'user';
			guideConf.detectLevel = '0';
			guideConf.showToolBar = 'yes';
			guideConf.showDataSources = 'yes';
			guideConf.dimAreaWidth = 100;
			guideConf.fieldAreaWidth = 350;
			guideConf.emptyReport = "yes";
			*/
		}
	}
	//查询
	,submitQuery : function() {
		submitQuery();
	}
	//撤销界面操作
	,undo : function() {
		operations.undo();
	}
	//重做界面操作
	,redo : function() {
		operations.redo();
	}
	//保存qyx
	,saveQyx : function() {
		saveQyx();
	}
	//打开qyx
	,openQyx : function() {
		openQyx();
	}
	//设置字段的编辑风格，tableName，fieldName是lmd里的名称，不是dct里的；editStyle是dimData.json里下拉树数据的名称
	,setEditStyle : function(tabelName,fieldName,editStyle) {
		var f = mdUtils.getField(tabelName,fieldName,true);
		if (f != null) {
			f.edit = editStyle;
		} else {
			alert("设置编辑风格失败，未找到字段“"+tableName+"."+fieldName+"”");	
		}
	}
	//设置编辑风格，相同name的编辑风格会被覆盖，def格式如下：{name:'名称',type:1文本编辑框/3日期编辑空间,dateFormat:'yy-mm-dd',timeFormat:'hh:mm:ss'}
	,setEditStyleDef : function(def) {
		var es = mdUtils.getEditStyle(def.name);
		if (es) editStyles.remove(es);
		editStyles.push(def);
	}
}

/*-----------分析模块api-------------------------------------------*/
var analyseApi = {
	//界面上发生一些事件后，用户还想自己做点事情，event里的这些方法是需要用户自定义实现的
	event : {
		//初始化一些界面参数
		init : function(){
			/*这些参数可以控制页面的初始状态
			guideConf.maxDataSize = '10000';
			guideConf.maxReportSize = '50000';
			guideConf.outerCondition = "";
			guideConf.dataFileType = 'text';
			
			//不同报表单元格的显示属性设置
			guideConf.style = [{"name":"标题","backColor":-1641217,"color":-16777216,"hAlign":-47},{"name":"分组","backColor":-1641217,"color":-16777216,"hAlign":-47},{"name":"指标1","backColor":-1,"color":-16777216,"hAlign":-48},{"name":"指标2","backColor":-853778,"color":-16777216,"hAlign":-48}];
			
			guideConf.canEditDataSet = 'yes';
			guideConf.canEditReport = 'yes';
			guideConf.showHistoryRpx = 'no';
			guideConf.showToolBar = 'no';
			
			//报表单元格的默认宽度
			guideConf.defaultWidth = 50;
			
			//报表单元格的默认宽度
			guideConf.defaultHeight = 20;
			
			//根据数据类型自动的显示风格
			guideConf.formatExp = 'if(ifnumber(if(value()==null,"",value())),"#.##",if(ifdate(if(value()==null,"",value())),"yyyy/MM/dd",""))';
			*/
		}
		//切换报表
		,changeReport : function(){}
	}
	//刷新报表，包括配置区域和报表本身
	,refreshRpx : function() {
		aly.refresh();
	}
	/*
	return	
		null:当前没有报表
		report：报表对象
			report.name ：报表名称
			report.where:{
				conf:[] ：	条件的详细配置
			}
	其中条件的详细配置json如下：
	[
		{//字段条件
			"level":1	//层级，高级数要加括号
			,"fieldInfo":{
				"disp":"客户"
				,"dataType":2
				,"edit":"客户"
				,"exp":"客户"
			}
			,"oper":"包含"
			,"values":"VI"
			,"disp":""
		}
		,{//连接
			join:'AND/OR'
			,level:1
		}
	]
	*/
	,getReport : function(){
		var rpx = aly.getCurrRpx();
		if (rpx == null) return null;
		return rpx;
	}
	//是否是明细列表报表	
	,isDetailListRpx : function(name){
		var rpx = aly.getRpx(name);
		if (rpx == null || rpx.type == 2) return false;
		return rpx.fields.length>0&&rpx.lefts.length==0&&rpx.tops.length==0;
	}
	/*
	return 
		null	没有数据集
		dataSet	当前数据集
			dataSet.fields : [
				{
					name:''		字段名称
					,dataType:2	字段类型，1数值、2字符、3日期、4时间、5日期时间
					,edit:''	编辑风格，通过编辑风格可以获得显示值列表
				}
			]
	*/
	,getDataSet : function(){
		var rpx = aly.getCurrRpx();
		if (rpx == null) return null;
		return aly.getDataSet(rpx.dataSet);
	}
	/*
		编辑风格的json如下，假如有data属性，则有显示值，显示值数据还可能是树状结构的
		{name:'编辑风格名称',type:6,data:[
				{ id:1, pId:0, name:"雇员", halfCheck:false, open:true}
				,{ id:2, pId:1, name:"张颖",real:"1",dim:'雇员'}
				,{ id:3, pId:1, name:"王伟",real:"2",dim:'雇员'}
				,{ id:4, pId:1, name:"李芳",real:"3",dim:'雇员'}
			]
		}
	*/
	,getEdit : function(editName) {
		return mdUtils.getEditStyle(editName);
	}
	/*
	判断服务器上是否存在某个文件，根路径是tag属性里的fileHome（fileHome为空时默认为应用根路径）
	*/
	,fileExist : function (serverFile) {
		var ex = false;
		$.ajax({
			type: 'POST',
			async : false,
			url: contextPath + "/DLServletAjax?d="+new Date().getTime(),
			data: {action:2,oper:'fileExist',file:serverFile},
			success: function(data){
				if (data.indexOf('error:')==0) {
					alert(data.substring(6));
					return;
				}
				ex = data==1;
				
			}
		});
		return ex;
	}
	//保存olap
	,saveOlap : function() {
		saveOlap();
	}
	//打开olap
	,openOlap : function() {
		openOlap();
	}
	//type ：１横向平铺；2纵向平铺；3重叠显示
	,resetReportWindow : function(type) {
		relocalReports(type);
	}
	//管理数据集
	,manageDataSet : function() {
		manageDataSet();
	}

}
