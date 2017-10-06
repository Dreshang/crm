$(function(){
	var devResult=$("#devResult").val();
	if(devResult==2||devResult==3){
		$("#toolbar").remove();
	}
	var saleChanceId=$("#saleChanceId").val();
	$("#dg").edatagrid({
		url:ctx+"/cus_dev_plan/queryCusDevPlansByParams?saleChanceId="+saleChanceId,
		saveUrl:ctx+"/cus_dev_plan/insetCusDevPlans?saleChanceId="+saleChanceId,
		updateUrl:ctx+"/cus_dev_plan/updateCusDevPlans?saleChanceId="+saleChanceId,
		destroyUrl:ctx+"/cus_dev_plan/deleteCusDevPlans?saleChanceId="+saleChanceId
	})
})

function saveCusDevPlan(){
	$("#dg").edatagrid("saveRow");
	$("#dg").edatagrid("load");
}
function updateCusDevPlan(){
	$("#dg").edatagrid("saveRow");
	$("#dg").edatagrid("load");
}

function delCusDevPlan(){
	$("#dg").edatagrid("destroyRow");
	$("#dg").edatagrid("load");
}


function updateSaleChanceDevResult(devResult){
	var saleChanceId=$("#saleChanceId").val();
	$.ajax({
		url:ctx+"/sale_chance/updateDevResult?devResult="+devResult+"&saleChanceId="+saleChanceId,
		type:"post",
		success:function(data){
			if(data.code==200){
				$.messager.alert('来自crm',data.msg,'info');
				$("#toolbar").remove();
			}else{
				$.messager.alert('来自crm',data.msg,'info');
			}
		}
	})
}







