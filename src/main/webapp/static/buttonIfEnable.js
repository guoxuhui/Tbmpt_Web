/************************************/
/*****当前JS已放入includejs.jsp*******/
/*****创建时间20140923_王彬彬**********/

/**
 * 根据勾选的数据数，来屏蔽按钮【此方法优先使用】
 * @param datagrId 加载的数据集合ID
 * @param hiddenClumArrayZero 若无一条勾选数据，需屏蔽按钮的数组集合
 * @param hiddenClumArrayOnlyOneItem 若只勾选一条数据，需屏蔽按钮的数组集合
 * @param hiddenClumArrayMoreOneItem 若勾选大于一条数据，需屏蔽按钮的数组集合
 * @returns
 */
function hiddenButtonBase(datagrId,hiddenClumArrayZero,hiddenClumArrayOnlyOneItem,hiddenClumArrayMoreOneItem){
	var allButton = [];//所有要屏蔽字段
	var allButtontmp = [];//临时容器
	allButtontmp = hiddenClumArrayZero.concat(hiddenClumArrayOnlyOneItem);
	allButtontmp = allButtontmp.concat(hiddenClumArrayMoreOneItem);
	for (var i = 0; i < allButtontmp.length; i++) {
		  if(allButton.indexOf(allButtontmp[i])<0){
			  allButton.push(allButtontmp[i]);
		  }
	}
	//显示所有字段
	for (var i = 0; i < allButton.length; i++) {
			  $('#'+allButton[i]).linkbutton('enable');
	}
	
	//根据所选行数隐藏需隐藏字段
	var selrows = $('#'+datagrId).datagrid("getSelections");
	if(selrows && selrows.length == 0){
		for (var i = 0; i < hiddenClumArrayZero.length; i++) {
			  $('#'+hiddenClumArrayZero[i]).linkbutton('disable');
		}
	}else if(selrows && selrows.length == 1){
		for (var i = 0; i < hiddenClumArrayOnlyOneItem.length; i++) {
			$('#'+hiddenClumArrayOnlyOneItem[i]).linkbutton('disable');
		}
	}else if(selrows && selrows.length > 1){
		for (var i = 0; i < hiddenClumArrayMoreOneItem.length; i++) {
			$('#'+hiddenClumArrayMoreOneItem[i]).linkbutton('disable');
		}
	}
}

/**
 * 基本列表的选择取消，修改按钮状态
 * @param datagrId 列表datagr的ID
 * @param updateButtonId 修改按钮ID
 */
function onselectRow(datagrId,updateButtonId){
	var selrows = $('#'+datagrId).datagrid("getSelections");
	if(selrows && selrows.length > 1){
		$('#'+updateButtonId).linkbutton('disable');
	}else{
		$('#'+updateButtonId).linkbutton('enable');
	}
}
/**
 *【启禁用】 选择取消数据，启用、禁用、修改按钮状诚控制
 * @param datagrId 当前DIV的ID
 * @param Statefield 当前状态字段名称
 * @param updateButtonId 修改按钮ID
 * @param qiyongButtonId 启用按钮ID
 * @param qiyongValue 启用时的值
 * @param jinYongButtonId 禁用按钮ID
 * @param jinYongValue 禁用时的值
 */
function onselectRow_qyOrJy(datagrId,Statefield,updateButtonId,qiyongButtonId,qiyongValue,jinYongButtonId,jinYongValue){
	var selrows = $('#'+datagrId).datagrid("getSelections");
	var qiyongButton = true;
	var jinYongButton = true;
	for (i in selrows) {
		if(selrows[i][Statefield]==qiyongValue)qiyongButton=false;
		if(selrows[i][Statefield]==jinYongValue)jinYongButton=false;
	}
	if(qiyongButton&&!jinYongButton){
		$('#'+qiyongButtonId).linkbutton('enable');
		$('#'+jinYongButtonId).linkbutton('disable');
	}else if(!qiyongButton&&jinYongButton){
		$('#'+qiyongButtonId).linkbutton('disable');
		$('#'+jinYongButtonId).linkbutton('enable');
	}else if(!qiyongButton&&!jinYongButton){
		$('#'+qiyongButtonId).linkbutton('disable');
		$('#'+jinYongButtonId).linkbutton('disable');
	}else if(qiyongButton&&jinYongButton){
		$('#'+qiyongButtonId).linkbutton('enable');
		$('#'+jinYongButtonId).linkbutton('enable');
	}
    if(selrows && selrows.length > 1){
         $('#'+updateButtonId).linkbutton('disable');
    }else{
    	$('#'+updateButtonId).linkbutton('enable');
    }
}

/**
 * 【审核】选择和取消数据行，修改、删除、送审、审核按钮状坊
 * @param datagrId 当前DIV的ID
 * @param auditfield 当前审批字段名称ID
 * @param updateButtonId 修改按钮ID
 * @param deletButtonId 删除按钮ID
 * @param sendButtonId 送审按钮ID
 * @param sendValue 送审状态的值
 * @param passButtonId 审核通过按钮ID
 * @param passValue 审核通过状态值
 */
function onselectRow_audit(datagrId,auditfield,updateButtonId,deletButtonId,sendButtonId,sendValue,passButtonId,passValue){
	var selrows = $('#'+datagrId).datagrid("getSelections");
	var sendButton = true;
	var passButton = true;
	var sendButtonIfExists = true;
	if(sendButtonId==null||sendButtonId=="")sendButtonIfExists=false;
	for (i in selrows) {
		if(sendButtonIfExists&&selrows[i][auditfield]==sendValue)sendButton=false;
		if(selrows[i][auditfield]==passValue)passButton=false;
	}
	var selectRows = 0;
	if(selrows)selectRows =selrows.length;
	if(selectRows > 1){
		$('#'+updateButtonId).linkbutton('disable');
		if(sendButtonIfExists)$('#'+sendButtonId).linkbutton('disable');
		$('#'+passButtonId).linkbutton('disable');
		$('#'+deletButtonId).linkbutton('disable');
	}else{
		$('#'+updateButtonId).linkbutton('enable');
		if(sendButtonIfExists)$('#'+sendButtonId).linkbutton('enable');
		$('#'+passButtonId).linkbutton('enable');
		$('#'+deletButtonId).linkbutton('enable');
	}
	if(sendButton&&passButton){
		if(sendButtonIfExists)$('#'+sendButtonId).linkbutton('enable');
		$('#'+passButtonId).linkbutton('enable');
		if(selectRows<=1)$('#'+updateButtonId).linkbutton('enable');
		$('#'+deletButtonId).linkbutton('enable');
	}else if(sendButton&&!passButton){
		if(sendButtonIfExists)$('#'+sendButtonId).linkbutton('disable');
		$('#'+passButtonId).linkbutton('disable');
		$('#'+updateButtonId).linkbutton('disable');
		$('#'+deletButtonId).linkbutton('disable');
	}else if(!sendButton&&passButton){
		if(sendButtonIfExists)$('#'+sendButtonId).linkbutton('disable');
		if(selectRows<=1)$('#'+passButtonId).linkbutton('enable');
		$('#'+updateButtonId).linkbutton('disable');
		$('#'+deletButtonId).linkbutton('disable');
	}else if(!sendButton&&!passButton){
		if(sendButtonIfExists)$('#'+sendButtonId).linkbutton('disable');
		$('#'+passButtonId).linkbutton('disable');
		$('#'+updateButtonId).linkbutton('disable');
		$('#'+deletButtonId).linkbutton('disable');
	}
}


