<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'test.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript" src="js/echarts.common.min.js"></script>
<link rel="stylesheet" href="zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
  <script type="text/javascript" src="zTree_v3/js/jquery-1.4.4.min.js" ></script>
  <script type="text/javascript" src="zTree_v3/js/jquery.ztree.core.js" ></script>
  <script type="text/javascript" src="zTree_v3/js/jquery.ztree.excheck.js" ></script>
<SCRIPT LANGUAGE="JavaScript">
   var zTreeObj;
   // zTree 的参数配置，深入使用请参考 API 文档（setting 配置详解）
   var setting = {
		   check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			}
   };
   
   var zNodes =[
    			{ id:1, pId:0, name:"随意勾选 1", open:true},
    			{ id:11, pId:1, name:"随意勾选 1-1", open:true},
    			{ id:111, pId:11, name:"随意勾选 1-1-1"},
    			{ id:112, pId:11, name:"随意勾选 1-1-2"},
    			{ id:12, pId:1, name:"随意勾选 1-2", open:true},
    			{ id:121, pId:12, name:"随意勾选 1-2-1"},
    			{ id:122, pId:12, name:"随意勾选 1-2-2"},
    			{ id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
    			{ id:21, pId:2, name:"随意勾选 2-1"},
    			{ id:22, pId:2, name:"随意勾选 2-2", open:true},
    			{ id:221, pId:22, name:"随意勾选 2-2-1", checked:true},
    			{ id:222, pId:22, name:"随意勾选 2-2-2"},
    			{ id:23, pId:2, name:"随意勾选 2-3"}
    		];
   $(document).ready(function(){
      zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
   });
  </SCRIPT>
</head>
<body>
<div>
   <ul id="treeDemo" class="ztree"></ul>
</div>
</body>
</html>