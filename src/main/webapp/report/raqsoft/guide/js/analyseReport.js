function setDataScope() {
	zIndexBak = artDialog.defaults.zIndex;
	var s = guideConf.dataScope;
	var dlg = art.dialog({
		id : dialogCount++,
		title : '设置数据范围',
	    content: 
	    	'<div style="margin:10px;">性能说明：用来分析的基础数据可以无限大，但展现的最终分析结果报表不能过大，否则有可能会内存溢出，我们也认为很大的分析结果也难以被观察；设置以下范围来防止产生巨大结果的分析</div>'
			+'<div><div style="margin:10px;"><input type="text" id="scope0" value="'+(s.length>0?s[0]:30000)+'" style="width:50px;margin-right:5px;"><span style="color:gray">明细列表查询时返回的最大行数</span></div>'
			+'<div style="margin:10px;"><input type="text" id="scope1" value="'+(s.length>1?s[1]:5000)+'" style="width:50px;margin-right:5px;"><span style="color:gray">只有1个分组时，分组内数据最大数量</span></div>'
			+'<div style="margin:10px;"><input type="text" id="scope2" value="'+(s.length>2?s[2]:200)+'" style="width:50px;margin-right:5px;"><span style="color:gray">只有2个分组时，每个分组内数据最大数量</span></div>'
			+'<div style="margin:10px;"><input type="text" id="scope3" value="'+(s.length>3?s[3]:50)+'" style="width:50px;margin-right:5px;"><span style="color:gray">只有3个分组时，每个分组内数据最大数量</span></div>'
			+'<div style="margin:10px;"><input type="text" id="scope4" value="'+(s.length>4?s[4]:40)+'" style="width:50px;margin-right:5px;"><span style="color:gray">只有4个分组时，每个分组内数据最大数量</span></div>'
			+'<div style="margin:10px;"><input type="text" id="scope5" value="'+(s.length>5?s[5]:40)+'" style="width:50px;margin-right:5px;"><span style="color:gray">只有5个分组时，每个分组内数据最大数量</span></div>'
			+'<div style="margin:10px;"><input type="text" id="scope6" value="'+(s.length>6?s[6]:40)+'" style="width:50px;margin-right:5px;"><span style="color:gray">只有6个分组时，每个分组内数据最大数量</span></div>'
			+'</div>'
	    ,ok : function() {
			var s0 = $('#scope0').val();
			s0 = parseInt(s0);
			if (isNaN(s0) || s0<1) s0 = 1;
			var s1 = $('#scope1').val();
			s1 = parseInt(s1);
			if (isNaN(s1) || s1<1) s1 = 1;
			var s2 = $('#scope2').val();
			s2 = parseInt(s2);
			if (isNaN(s2) || s2<1) s2 = 1;
			var s3 = $('#scope3').val();
			s3 = parseInt(s3);
			if (isNaN(s3) || s3<1) s3 = 1;
			var s4 = $('#scope4').val();
			s4 = parseInt(s4);
			if (isNaN(s4) || s4<1) s4 = 1;
			var s5 = $('#scope5').val();
			s5 = parseInt(s5);
			if (isNaN(s5) || s5<1) s5 = 1;
			var s6 = $('#scope6').val();
			s6 = parseInt(s6);
			if (isNaN(s6) || s6<1) s6 = 1;
			s[0] = s0;
			s[1] = s1;
			s[2] = s2;
			s[3] = s3;
			s[4] = s4;
			s[5] = s5;
			s[6] = s6;
			artDialog.defaults.zIndex = zIndexBak;
	    	setTimeout(function(){
				for (var i=0; i<rpxData.confs.length; i++) {
					if (!rpxData.confs[i].dialog){
						rpxData.confs[i].dialog = {open:1,top:100,left:100+100*i,width:500,height:300};
					}
					if (rpxData.confs[i].dialog.open == 1) rpx.refreshReport(rpxData.confs[i].name, false, false);
				}
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
	    ,width : '570px'
		,height : '280px'
		,opacity : 0.1
		,padding : '2px 2px'
		,zIndex : 41000
	});
}

function reportCellMouseover() {
	var td = $(this);
	//alert();
	var tt = td.attr('title');
	if (tt && tt != '') {
		td.attr('tt',tt);
		td.attr('title','');
	} else tt = td.attr('tt');
	if (!(tt && tt != '')) {
		td.css('cursor','');
		return;
	}
	if (!parent.canDrill(reportConfName)){
		td.css('cursor','');
		return;
	}
	td.css('cursor','pointer');
}

function reportCellClick(event) {
	var td = $(this);
	var tds = td.parent().parent().find('td');
	//var tds2 = td.parent().parent().parent().find('td');
	//alert(tds.length);
	//alert(tds2.length);
	var x1 = td.position().left;
	var y1 = td.position().top;
	var x2 = x1 + td.width();
	var y2 = y1 + td.height();
	//alert(left + "--" + top);
	var topValues = [];
	var leftValues = [];
	var fieldValues = [];
	var tt = td.attr('title');
	if (tt && tt != '') {
		td.attr('tt',tt);
		td.attr('title','');
	} else tt = td.attr('tt');
	var detail = tt == "3";
	for (var i=0; i<tds.length; i++) {
		var tdi = $(tds[i]);
		var x11 = tdi.position().left;
		var y11 = tdi.position().top;
		var x22 = x11 + tdi.outerWidth(true);
		var y22 = y11 + tdi.outerHeight(true);
		var tti = tdi.attr('title');
		if (tti && tti != '') {
			tdi.attr('tt',tti);
			tdi.attr('title','');
		} else tti = tdi.attr('tt');
		var val = tdi.html().replaceAll("<br>","").replaceAll("\n\r","").replaceAll("\n","");
		
		if ((tti == 1) && (y1>=y11&&y1<=y22) && (y2>=y11&&y2<=y22) && x11<=x1) {
			leftValues[leftValues.length] = val;
			//alert(tdi.html());
		}
		if ((tti == 2) && (x1>=x11&&x1<=x22) && (x2>=x11&&x2<=x22)) {
			topValues[topValues.length] = val;
		}
		if (tti == 4 && y1 == y11 && y2 == y22) {
			fieldValues[fieldValues.length] = val;
		}
		
		if (y11>y2) break;
	}
	//alert(topValues);
	//alert(leftValues);
	if (topValues.length != 0 || leftValues.length != 0) event.stopPropagation();

	parent.reportCellDrill(reportConfName, topValues, leftValues, fieldValues, detail);
}
