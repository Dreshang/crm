function searchCustomerLosses(){
	$("#dg").datagrid("load",{
		cusName:$("#s_cusName").val(),
		cusNo:$("#s_cusNo").val(),
		createDate:$("#s_time").datebox('getValue'),
		cusManager:$("#s_cusManager").combobox('getValue')
	});
}

$(function(){
	$('#dg').datagrid({
		rowStyler: function(index,rowData){
			if (rowData.state==0){
				return 'background-color:yellow';
			}
			if (rowData.state==1){
				return 'background-color:red';
			}
		}
	});


})

function formatterState(val){
	if(val==0){
		
		return "暂缓流失";
	}
	if(val==1){
		return "确定流失";
	}
}

function formatterOp(val ,rowData){
	if(rowData.state==1){
		var href="javascript:openCusDevPlanDetailTab('查看流失详细信息_"+rowData.id+"',"+rowData.id+")";
		return "<a href="+href+">查看流失详情</a>";
	}
	if(rowData.state==0){
		var href="javascript:openCusDevPlanDetailTab('流失情况处理_"+rowData.id+"',"+rowData.id+")";
		return "<a href="+href+">添加暂缓处理</a>";
	}
}

function openCusDevPlanDetailTab(title,id){
	window.parent.openTab(title,ctx+"/customer_loss/queryCustomerRepid?id="+id);
	
	
}



