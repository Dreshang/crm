$(function(){
	onLoadQuery();
})
function onLoadQuery(){
	$.ajax({
		type:'post',
		url:ctx+'/customer/queryLevelCount',
		dataType:'json',
		success:function(data){
			if(data.code==200){
				var level=data.level;
				var count=data.count;
					// 基于准备好的dom，初始化echarts实例
				   var myChart = echarts.init(document.getElementById('main'));
				   // 指定图表的配置项和数据
					  var  option = {
				       title: {
				           text: '客户组成图'
				       },
				       tooltip: {},
				       legend: {
				           data:['客户等级']
				       },
				       xAxis: {
				           data: level
				       },
				       yAxis: {},
				       series: [{
				           name: '客户数量',
				           type: 'bar',
				           data: count
				       }]
				   };

				   // 使用刚指定的配置项和数据显示图表。
				   myChart.setOption(option);
			}else{
				$.messager.alert('来自crm','暂无数据','info');
			}
		}
	})
	
	
}