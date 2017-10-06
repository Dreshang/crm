function searchCustomer(){
	$("#dg").datagrid("load",{
	khno: $("#s_khno").val(),
	name:$("#s_name").val()
	})
}

function openCustomerAddDialog(){
	openDlg("dlg","添加客户信息")
}

function openCustomerModifyDialog(){
	openModifyDlg('dg','dlg','fm','修改客户信息')
}

function closeCustomerDialog(){
	closeDlg('dlg');
}

function initFormData(){
	$("#dlg").form("clear");
}

$(function(){
	$("#dlg").dialog({
		onClose:function(){
			initFormData();
		}
	});
})

function saveOrUpdateCustomer(){
		var id = $("#id").val();
		var url = ctx+"/customer/insert";
		if(!isEmpty(id)){
			var url = ctx+"/customer/update";
		}
		saveOrUpdate('dg','fm',url);
}



function deleteCustomer(){
	var url = ctx+'/customer/delete';
	delData('dg',url)
}

function openCustomerOtherInfo(title,type){
	var rows = $("#dg").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("来自crm","请选择需要操作的记录","info");
		return;
	}
	if(rows.length>1){
		$.messager.alert("来自crm","只能选择一条记录进行操作","info");
		return;
	}
	var id=rows[0].id;
	window.parent.openTab(title,ctx+'/customer/toOtherPage/'+type+'/'+id);
}

