
/**
 * 打开对话框
 * @param dlgId
 * @param title
 */
function openDlg(dlgId,title){
	$("#"+dlgId).dialog("open").dialog(title);
}
/**
 * 
 * 关闭对话框
 * @param dlgId
 */
function closeDlg(dlgId){
	$("#"+dlgId).dialog("close");
}

/**
 * 
 * 修改对话框
 * @param dlgId
 * @param fmId
 * @param title
 */
function openModifyDlg(dgId,dlgId,fmId,title){
	var rows = $("#"+dgId).datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("来自crm","请选择需要修改的记录","info");
		return;
	}
	if(rows.length>1){
		$.messager.alert("来自crm","只能选择一条记录进行修改","info");
		return;
	}
	$('#'+fmId).form("load",rows[0]);
	$("#"+dlgId).dialog("open").dialog(title);
}

/**
 * 
 * 保存或更新
 * @param dlgId
 * @param fmId
 * @param url
 */
function saveOrUpdate(dgId,fmId,url){
	$("#"+fmId).form("submit",{
		url:url,
		onSubmit:function(params){
			params.createMan=$.cookie("trueName");
			return $("#"+fmId).form("validate")
		},
		success: function(data){
			data=JSON.parse(data);
			if(data.code==200){
				closeDialog();
				$.messager.alert('来自crm',data.msg,'info');
				$('#'+dgId).datagrid('load');
			}else{
				$.messager.alert('来自crm',data.msg,'info');
			}
		}
	});
	
}

/**
 * 
 *删除选中行
 * @param dlgId
 * @param url
 */
function delData(dgId,url){
	var rows = $("#"+dgId).datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("来自crm","请选择需要删除的记录","info");
		return;
	}
	var ids="ids="
	for(var i=0;i<rows.length;i++){
		if(i<rows.length-1){
			ids=ids+rows[i].id+"&ids=";
			continue;
		}
		ids=ids+rows[i].id;
	}
	$.messager.confirm('来自crm','确定删除选中的'+rows.length+'条记录?',function(r){
		if(r){
			$.ajax({
				type:'post',
				url:url,
				data:ids,
				dataType:"json",
				success:function(data){
				if(data.code==200){
					$.messager.alert('来自crm','删除成功','info');
					$('#'+dgId).datagrid('load');
				}else{
					$.messager.alert(data.msg);
				}	
				}
			})
		}
	});
	
}











