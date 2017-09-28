<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/commons/basejs.jsp" %>
<meta http-equiv="X-UA-Compatible" content="edge" />
<title>报警详细信息</title>
</head>
<body class="easyui-layout" data-options="fit:true,border:false">
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="padding: 3px;">
        <form id="showForm" method="post" data-options="novalidate:true">
        	<input name="id" type="hidden">

			<table class="grid" style="height:100%">
			
				<tr>
                    <td class="form-td-label" style="width:100px">报警内容：</td>
                    <td class="form-td-content" colspan="5" >
                    	${alarm.alarmcontent}
                    </td>
                </tr>
                
                <tr>
                    <td class="form-td-label" style="width:100px">项目名称：</td>
                    <td class="form-td-content" colspan="5">
                    	${alarm.proname}--${alarm.sectionname}--${alarm.linename}--${alarm.tbmname}
                    </td>
                </tr>
			
                <tr>
                   <td class="form-td-label" style="width:100px">报警名称：</td>
                    <td class="form-td-content">
                    	${alarm.alarmname}
                    </td>
                    
                    <td class="form-td-label" style="width:100px">报警类别：</td>
                    <td class="form-td-content">
                    	${alarm.alarmtype}
                    </td>
                    <td class="form-td-label" style="width:100px">报警时间：</td>
                    <td class="form-td-content">
                    	${alarm.alarmtime}
                    </td>
                </tr>
                <c:forEach items="${requestScope.param}" var="obj">  
					<tr>
	                	<td class="form-td-label">点位名称：</td>
	                	<td class="form-td-content">
	                		${obj.dwname}
	                	</td>
	                	<td class="form-td-label">点位数值：</td>
	                	<td class="form-td-content">
	                		${obj.tagvalue}
	                	</td>
	                	<td class="form-td-label">点位时间：</td>
	                	<td class="form-td-content">
	                		${obj.tagtime}
	                	</td>
                	</tr>
                </c:forEach> 
                
            </table>
        </form>
    </div>
</div>

</body>
</html>