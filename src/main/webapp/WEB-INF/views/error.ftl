<#include "common.ftl" >
<script>
	$(function(){
	alert("${errorMsg}");
	if("${uri}"=="/main"){
	window.location.href=ctx+"/index";
	}else{
	window.parent.location.href=ctx+"/index";
	}
	})
</script>