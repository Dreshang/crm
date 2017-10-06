function openTab(text, url, iconCls){
    if($("#tabs").tabs("exists",text)){
        $("#tabs").tabs("select",text);
    }else{
        var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='" + url + "'></iframe>";
        $("#tabs").tabs("add",{
            title:text,
            iconCls:iconCls,
            closable:true,
            content:content
        });
    }
}

//获取session用户信息
$(function(){
	getUserInfo();
})
function getUserInfo(){
	var userName=$.cookie("userName");
	var trueName=$.cookie("trueName");
	$("#userInfo").html(userName+"|【"+trueName+"】");
}

//退出
function logout(){
	$.messager.confirm("来自crm","确定退出?",function(r){
		if(r){
			$.messager.alert("来自crm","系统将在2秒后退出!!!")
			setTimeout(function(){
				$.removeCookie("userName");
				$.removeCookie("trueName");
				$.removeCookie("userIdStr");
				window.location.href="index";
			},2000)
		}
	})
	}

//修改密码
//打开修改框
function openPasswordModifyDialog(){
	$("#dlg").dialog("open");
}
function modifyPassword(){
	$("#fm").form("submit",{
		url:ctx+"/user/userPwdModify",
		onSubmit:function (){return $("#fm").form("validate")
		},
		success:function(data){
			data=JSON.parse(data);
			if(data.code==200){
				$.messager.alert("来自crm","密码修改成功,系统将在2秒后退出!!!")
				setTimeout(function(){
				$.removeCookie("userIdStr");
				$.removeCookie("userName");
				$.removeCookie("trueName");
				window.location.href="index"
				},2000)
			}else{
				$.messager.alert("来自crm",data.msg,"info")
			}
		}
	}
	)
}
function closePasswordModifyDialog(){
	$("#dlg").dialog("close");
}


















