function searchRoles(){
	$("#dg").datagrid('load',{
		roleName:$("#roleName").val()
	})
} 


function closeDialog(){
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

function openAddRoleDialog(){
	openDlg('dlg','添加角色记录')
}

function openModifyRoleDialog(){
	openModifyDlg('dg','dlg','fm','修改角色记录');
}

function saveOrUpdateRole(){
	var url = ctx+'/role/insert';
	var id=$("#id").val();
	if(!isEmpty(id)){
		url=ctx+'/role/update';
	}
	saveOrUpdate('dg','fm',url);
	
}

function deleteRole(){
	var url=ctx+'/role/delete';
	delData('dg',url)
	
}


/**
 * 打开关联权限对话框
 */
function openRelatePermissionDlg(){
	
	var rows=$("#dg").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("来自crm","请选择角色进行授权!","info");
		return;
	}
	
	if(rows.length>1){
		$.messager.alert("来自crm","只能选择一条角色进行授权!","info");
		return;
	}
	
	$("#rid").val(rows[0].id);
	
	loadModuleData();
	
	openDlg("dlg02", "关联权限");
}


var ztreeObj;
function loadModuleData(){
	$.ajax({
		type:"post",
		url:ctx+'/module/queryModule',
		dataType:"json",
		data:'rid='+$('#rid').val(),
		success:function(data){
			// zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
			var setting = {
					check: {
						enable: true
					},
					data: {
						simpleData: {
							enable: true
						}
					},
					callback: {
						onCheck: zTreeOnCheck
					}
			};
			// zTree 的数据属性，深入使用请参考 API 文档（zTreeNode 节点数据详解）
			var zNodes =data;
			ztreeObj= $.fn.zTree.init($("#treeDemo"), setting, zNodes);
		}
	});
}

/**
 * 添加权限
 */
function addPermission(){
	/**
	 * 1.角色id
	 * 2.资源id
	 */
	$.ajax({
		type:"post",
		url:ctx+'/permission/savaPermission',
		data:"rid="+$("#rid").val()+"&"+$("#moduleIds").val(),
		dataType:"json",
		success:function(data){
			console.log(data);
			if(data.code==200){
				$.messager.alert("来自crm",data.msg,"info");
				$("#moduleIds").val("");
				$("#rid").val("");
				closeDialog02();
			}else{
				$.messager.alert("来自crm",data.msg,"info");
			}
			
			
		}
	});
}

function closeDialog02(){
	closeDlg("dlg02");
}



function zTreeOnCheck(event, treeId, treeNode){
	var znodes=ztreeObj.getCheckedNodes(true);
	var moduleIds="moduleIds=";
	if(znodes.length>0){
		for(var i=0;i<znodes.length;i++){
			if(i<=znodes.length-2){
				moduleIds=moduleIds+znodes[i].id+"&moduleIds=";
			}else{
				moduleIds=moduleIds+znodes[i].id;
			}
		}
	}
	console.log(moduleIds);
	$("#moduleIds").val(moduleIds);
}
/*
var zTreeObj;
function openRelatePermissionDlg(){
	var rows = $("#dg").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("来自crm","请选择需要授权的角色","info");
		return;
	}
	if(rows.length>1){
		$.messager.alert("来自crm","只能选择一条角色进行授权","info");
		return;
	}
	$('#rid').val(rows[0].id);
	$.ajax({
		type:"post",
		url:ctx+'/module/queryModule',
		dateType:'json',
		success:function(data){
			   // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
			   var setting = {
					   check: {
							enable: true
						},
						data: {
							simpleData: {
								enable: true
							}
						},
						callback: {
							onCheck: zTreeOnCheck
						}
			   };
			   
			   var zNodes = data;
			   zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
		}
	})
	openDlg('dlg02','权限关联')
}*/

/*
function addPermission(){
	var rid=$('#rid').val;
	var moduleIds=$('#moduleIds').val;
	$.ajax({
		type:"post",
		url:ctx+'/permission/savaPermission',
		data:"rid="+rid+"&"+moduleIds,
		dateType:'json',
		success:function(data){
			if(date.code==200){
				$("#tb").datagrid('load');
				$("#moduleIds").val("");
				$("#rid").val("");
				$.messager.alert('来自crm',data.msg,'info');
				$('#dlg02').diagrid('close');
			}else{
				$.messager.alert('来自crm',data.msg,'info');
			}
		}
	})
}	

function zTreeOnCheck(event, treeId, treeNode){
	var znodes=zTreeObj.getCheckedNodes(true);
	var moduleIds="moduleIds=";
	if(znodes.length>0){
		for(var i=0;i<znodes.length;i++){
			if(i<=znodes.length-2){
				moduleIds=moduleIds+znodes[i].id+"&moduleIds=";
			}else{
				moduleIds=moduleIds+znodes[i].id;
			}
		}
	}
	console.log(moduleIds);
	$("#moduleIds").val(moduleIds);
}

function closeDialog02(){
	closeDlg('dlg02');
}
$(function(){
	$("#dlg02").dialog({
		onClose:function(){
			$("#dlg02").form("clear");
		}
	});
})
*/