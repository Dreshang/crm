function userLogin(){
	console.log(111);
	//判断用户名密码非空
	var userName=$("#userName").val();
	var userPwd=$("#userPwd").val();
	if(isEmpty(userName)){
		alert("用户名不能为空");
		return;
	}
	if(isEmpty(userPwd)){
		alert("密码不能为空");
		return;
	}
	var params={};
	params.userName=userName;
	params.userPwd=userPwd;
	console.log(ctx);
	$.ajax({
		type:"post",
		url:ctx+"/user/userLogin",
		data:params,
		dataType:"json",
		success:function(data){
			if(data.code==200){
				var userInfo=data.result;
				$.cookie("userName",userInfo.userName);
				$.cookie("trueName",userInfo.trueName);
				$.cookie("userIdStr",userInfo.userIdStr);
				window.location.href="main";
			}else{
				alert(data.msg);
			}
		}
		
	})
	
}