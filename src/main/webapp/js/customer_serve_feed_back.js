function openFeedBackDlg(){
	var rows = $("#dg").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("来自crm","请选择进行分配的记录","info");
		return;
	}
	if(rows.length>1){
		$.messager.alert("来自crm","只能选择一条记录进行分配","info");
		return;
	}
	$('#fm').form("load",rows[0]);
	$('#dlg').dialog("open").dialog('反馈已处理的记录');
}

function addCustomerServeServiceFeedBack(){
	$('#fm').form('submit',{
		type:'json',
		url:ctx+'/customer_serve/update?id='+$('#id').val()+'&state=5',
		onSubmit:function(params){
			return $("#fm").form("validate")
		},
		success: function(data){
			data=JSON.parse(data);
			if(data.code==200){
				$("#dlg").dialog("close")
				$('#dg').datagrid('load');
			}else{
				$.messager.alert('来自crm',data.msg,'info');
			}
		}
	})
}
function closeCustomerServeDialog(){
	$('#dlg').dialog("close");
}