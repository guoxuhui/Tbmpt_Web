<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" src="${staticPath}/static/js/gpztcl/base/gpztclSczxpcShowPage.js" charset="utf-8"></script>

<div  class="easyui-layout" data-options="fit:true,border:false" style="width:1060px">
	<div class="easyui-panel" title="实测中线信息" data-options="region:'north',border:false,collapsible:true"  
		style="width:1060px;height:140px;border: false;overflow: hidden;">
		<form id="addForm" method="post">
		    <input type="hidden" id="fid" value="${dto.id}">
		    <input type="hidden" id="dataList" value="${dataList}">
            <table class="grid">
            
                <tr>
                    <td class="form-td-label" style="width:100px">工程名称：</td>
                    <td  colspan="5">
                      ${dto.gcName}
                    </td>
                </tr>
                <tr>
                    <td class="form-td-label" style="width:100px">区间名称：</td>
                    <td  style="width:200px">
                    <span id='projname'>${dto.qlName}</span>
                    </td>
                    <td class="form-td-label" style="width:100px">上报状态：</td>
                    <td style="width:80px">${dto.sendState}</td>
                    <td class="form-td-label" style="width:200px">测量日期：</td>
                    <td>${dto.clTime}</td>
                </tr>
                 <tr>
                 <td class="form-td-label" style="width:100px">线路名称：</td>
                    <td  style="width:200px">
                    <span id='projname'>${dto.xlName}</span>
                    </td>
                    
                    <td class="form-td-label" style="width:100px">操作人员：</td>
                    <td  style="width:80px">
                    <span id='projname'>${dto.impManName}</span>
                    </td>
                    <td class="form-td-label" style="width:200px">操作时间：</td>
                    <td>${dto.impTime}</td>
                </tr>
            </table>
        </form>
	</div>
        <!-- 明细信息表格 -->
	<div class="easyui-panel" data-options="region:'center',border:false" style="width:100%;height:100%;">
		<table id="dataGrid" title="列表" data-options="fit:true,border:false"></table>
	</div>
	<div id="showPagetoolbar" style="display: none;">
    	<a onclick="expGpztclSczxInfoXls();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-page_excel'">导出Excel</a>
</div>
</div>
