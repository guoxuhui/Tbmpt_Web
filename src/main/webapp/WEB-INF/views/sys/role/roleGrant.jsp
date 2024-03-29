<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var resourceTree;
    $(function() {
        resourceTree = $('#resourceTree').tree({
            url : '${path }/sys/resource/allTrees',
            parentField : 'pid',
            lines : true,
            checkbox : true,
            onClick : function(node) {
            },
            onLoadSuccess : function(node, data) {
                progressLoad();
                $.post( '${path }/sys/role/findResourceIdListByRoleId', {
                    id : '${id}'
                }, function(result) {
                    var ids;
                    if (result.success == true && result.obj != undefined) {
                        ids = $.stringToList(result.obj + '');
                    }
                    if (ids.length > 0) {
                        for ( var i = 0; i < ids.length; i++) {
                            if (resourceTree.tree('find', ids[i])) {
                                resourceTree.tree('check', resourceTree.tree('find', ids[i]).target);
                            }
                        }
                    }
                    
                    var checkedNodes = resourceTree.tree('getChecked'); 
					var checkedId = "", cur_node;
					for(var i = 0; i < checkedNodes.length; i++) {
						checkedId = checkedNodes[i].id;
						if(!(isInArray(checkedId, ids))){
							cur_node = resourceTree.tree("find", checkedId);
							if(cur_node){
								resourceTree.tree("uncheck", cur_node.target);
							}
						}
					}
                }, 'json');
                progressClose();
            },
            cascadeCheck : true
        });

        $('#roleGrantForm').form({
            url : '${path }/sys/role/grant',
            onSubmit : function() {
                progressLoad();
                var isValid = $(this).form('validate');
                if (!isValid) {
                    progressClose();
                }
                var checknodes = resourceTree.tree('getChecked');
				var checknodes_ind = resourceTree.tree('getChecked', 'indeterminate');
				jQuery.merge(checknodes,checknodes_ind);
				
                var ids = [];
                if (checknodes && checknodes.length > 0) {
                    for ( var i = 0; i < checknodes.length; i++) {
                        ids.push(checknodes[i].id);
                    }
                }
                $('#resourceIds').val(ids);
                return isValid;
            },
            success : function(result) {
                progressClose();
                result = $.parseJSON(result);
                if (result.success) {
                    parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
                    parent.$.modalDialog.handler.dialog('close');
                } else {
                    parent.$.messager.alert('错误', result.msg, 'error');
                }
            }
        });
    });

    function checkAll() {
        var nodes = resourceTree.tree('getChecked', 'unchecked');
        if (nodes && nodes.length > 0) {
            for ( var i = 0; i < nodes.length; i++) {
                resourceTree.tree('check', nodes[i].target);
            }
        }
    }
    function uncheckAll() {
        var nodes = resourceTree.tree('getChecked');
        if (nodes && nodes.length > 0) {
            for ( var i = 0; i < nodes.length; i++) {
                resourceTree.tree('uncheck', nodes[i].target);
            }
        }
    }
    function checkInverse() {
        var unchecknodes = resourceTree.tree('getChecked', 'unchecked');
        var checknodes = resourceTree.tree('getChecked');
        if (unchecknodes && unchecknodes.length > 0) {
            for ( var i = 0; i < unchecknodes.length; i++) {
                resourceTree.tree('check', unchecknodes[i].target);
            }
        }
        if (checknodes && checknodes.length > 0) {
            for ( var i = 0; i < checknodes.length; i++) {
                resourceTree.tree('uncheck', checknodes[i].target);
            }
        }
    }
</script>
<div id="roleGrantLayout" class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center'" title="系统资源" style="width: 100%; padding: 1px;border:false">
        <div class="well well-small">
            <form id="roleGrantForm" method="post">
                <input name="id" type="hidden"  value="${id}" readonly="readonly">
                <ul id="resourceTree"></ul>
                <input id="resourceIds" name="resourceIds" type="hidden" />
            </form>
        </div>
    </div>
    <div data-options="region:'north'" title="" style="overflow: hidden; height:30px;padding: 1px;border:false">
        <div style="text-align: right;">
        	
        	<a href="javascript:void(0);" class="easyui-linkbutton" onclick="checkAll();">全选</a>
        	<a href="javascript:void(0);" class="easyui-linkbutton" onclick="checkInverse();">反选</a>
        	<a href="javascript:void(0);" class="easyui-linkbutton" onclick="uncheckAll();">清空</a>
        </div>
    </div>
</div>