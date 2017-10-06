$(function(){
	var state=$("#state").val();
	if(state==1){
		$("#toolbar").remove();
	}
	var lossId=$("#lossId").val();
	$("#dg").edatagrid({
		url:ctx+"/cus_repri/queryByLossId?lossId="+lossId,
		saveUrl:ctx+"/cus_repri/save?lossId="+lossId,
		updateUrl:ctx+"/cus_repri/update?lossId="+lossId,
		destroyUrl:ctx+"/cus_repri/delete?lossId="+lossId,
	})
})
function saveCustomerRepri(){
	$("#dg").edatagrid("saveRow");
	$("#dg").edatagrid("load");
}

function delCustomerRepri(){
	$("#dg").edatagrid("destroyRow");
	$("#dg").edatagrid("load");
}

//确认流失
function updateCustomerLossState(){
	$.messager.confirm('来自crm','确认流失该记录吗?',function(r){
		if(r){
			$.messager.prompt('流失原因填写', '请输入流失原因：', function(val){
				if (val){
					$.ajax({
						type:'post',
						url:ctx+'/customer_loss/updateLossState',
						data:'id='+$('#lossId').val()+'&state=1'+'&lossReason='+val,
						dataType:'json',
						success:function(data){
							if(data.code==200){
								$.messager.alert('来自crm','该客户已确认流失','info');
								$("#toolbar").remove();
							}else{
								$.messager.alert('来自crm',data.msg,'info');
							}
						}
						
					})
				}
			});
		}else{
			$.messager.alert('来自crm','请填写流失原因','info');
		}
	})
}
