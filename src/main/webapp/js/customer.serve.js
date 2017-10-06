$(function(){
	onLoadQuery();
})
function onLoadQuery(){
	$.ajax({
		type:'post',
		url:ctx+'/customer/queryServeCount',
		dataType:'json',
		success:function(data){
			if(data.code==200){
				var type=data.type;
				var cusServeDto=data.cusServeDto;
					// 基于准备好的dom，初始化echarts实例
				   var myChart = echarts.init(document.getElementById('main'));
				   // 指定图表的配置项和数据
				   var option = {
				  		    title : {
				  		        text: '顾客服务分析',
				  		        subtext: '纯属虚构',
				  		        x:'center'
				  		    },
				  		    tooltip : {
				  		        trigger: 'item',
				  		        formatter: "{a} <br/>{b} : {c} ({d}%)"
				  		    },
				  		    legend: {
				  		        orient: 'vertical',
				  		        left: 'left',
				  		        data: type,
				  		    },
				  		    series : [
				  		        {
				  		            name: '服务类型',
				  		            type: 'pie',
				  		            radius : '55%',
				  		            center: ['50%', '60%'],
				  		            data:cusServeDto,
				  		            itemStyle: {
				  		                emphasis: {
				  		                    shadowBlur: 10,
				  		                    shadowOffsetX: 0,
				  		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
				  		                }
				  		            }
				  		        }
				  		    ]
				  		};


				   // 使用刚指定的配置项和数据显示图表。
				   myChart.setOption(option);
			}else{
				$.messager.alert('来自crm','暂无数据','info');
			}
		}
	})
	
	
}