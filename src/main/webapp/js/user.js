function searchUsers(){
	$("#dg").datagrid("load",{
		userName:$("#userName").val(),
		trueName:$("#trueName").val(),
		email:$("#email").val(),
		phone:$("#phone").val()
	})
}

function openAddUserDialog(){
	
	openDlg('dlg','添加用户');
}

function closeDialog(){
	closeDlg('dlg');
}

function initFormData(){
	$("#password").attr("type","show");
	$("#pw").show();
	$("#dlg").form("clear");
}

$(function(){
	$("#dlg").dialog({
		onClose:function(){
			initFormData();
		}
	});
})


function saveOrUpdateUser(){
	var url = ctx+'/user/insert';
	var id=$("#id").val();
	if(!isEmpty(id)){
		url=ctx+'/user/update';
	}
	saveOrUpdate('dg','fm',url,'searchUsers');
	
}
function openModifyUserDialog(){
	$("#password").attr("type","hidden");
	$("#pw").hide();
	var rows = $("#dg").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("来自crm","请选择需要修改的记录","info");
		return;
	}
	if(rows.length>1){
		$.messager.alert("来自crm","只能选择一条记录进行修改","info");
		return;
	}
	$('#fm').form("load",rows[0]);
	$("#dlg").dialog("open").dialog("修改用户信息");
}


function deleteUser(){
	var url=ctx+'/user/delete';
	delData('dg',url,searchUsers)
}