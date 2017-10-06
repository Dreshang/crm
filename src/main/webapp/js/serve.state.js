function formatterServeState(val){
	if(val==1){
		return "未分配";
	}if(val==2){
		return "未处理";
	}if(val==3){
		return "等待反馈";
	}if(val==4){
		return "已反馈";
	}if(val==5){
		return "已归档";
	}
}