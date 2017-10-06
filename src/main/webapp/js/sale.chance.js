function formatterState(val){
	if(val==0){
		return "未分配";
	}if(val==1){
		return "已分配";
	}else{
		return "未定义";
	}
}

function searchSaleChances(){
	$("#dg").datagrid("load",{
	createMan:$("#createMan").val(),
	customerName:$("#customerName").val(),
	createDate:$("#createDate").datebox('getValue'),
	state:$("#state").combobox('getValue')
	});
}

function openAddAccountDialog(){
	openDlg("dlg","添加营销机会记录");
	
}

function closeDialog(){
	closeDlg("dlg")
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


function openModifyAccountDialog(){
	openModifyDlg('dg','dlg','fm',"修改营销机会记录");
	
}

function saveSaleChance(){
	var id = $("#id").val();
	var url = ctx+"/sale_chance/insert";
	if(!isEmpty(id)){
		var url = ctx+"/sale_chance/update";
	}
	saveOrUpdate('dg','fm',url,'searchSaleChances');
	
}

function deleteAccount(){
	var url = ctx+'/sale_chance/delete';
	delData('dg',url,'searchSaleChances')
}


