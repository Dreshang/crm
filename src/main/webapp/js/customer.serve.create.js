function saveCustomerService(){
	$.messager.confirm('来自crm','确定创建客服服务吗?',function(r){
		if(r){
			$("#fm").form('submit',{
				url:ctx+"/customer_serve/insert?createPeople="+$.cookie("trueName"),
				onSubmit:function (){
					return $("#fm").form("validate")
				},
				success:function(data){
					data=JSON.parse(data);
					if(data.code==200){
						$.messager.alert("来自crm","服务创建成功!!!",'info')
					}else{
						$.messager.alert("来自crm",data.msg,"info")
					}
				}
			});
		}
})
}
function closeCustomerServeDialog(){
	$('#dlg').dialog("close");
}