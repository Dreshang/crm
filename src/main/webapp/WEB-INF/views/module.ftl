<html>
<head>
<#include "common.ftl">
<script type="text/javascript" src="${ctx}/js/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctx}/js/common.js"></script>
<script type="text/javascript" src="${ctx}/js/base.js"></script>
<script type="text/javascript" src="${ctx}/js/module.js"></script>
</head>
<body>
	<table id="dg" class="easyui-datagrid"  pagination=true 
	     rownumbers=true    toolbar="#tb" fit=true url="${ctx}/module/queryModulesByParams">   
    <thead>   
        <tr>
            <th field='cb' checkbox=true >id</th>    
            <th field='moduleName'>模块名称</th>   
            <th field='parentModuleName'>父模块名称</th>   
            <th field='moduleStyle'  >模块样式</th>
            <th field='optValue'  >操作权限值</th>
            <th field='url'>url</th>
            <th field='grade' formatter="formatterGrade">层级</th>
            <th field='createDate'>创建时间</th>
            <th field='updateDate'>更新时间</th>
        </tr>   
    </thead>   
</table>

<div id="tb">
   <a  href="javascript:openAddSaleChanceDialog()" class="easyui-linkbutton" iconCLs="icon-save"  plain=true>添加</a>
   <a   href="javascript:openModifySaleChanceDialog()"  class="easyui-linkbutton"    iconCLs="icon-edit" plain=true>修改</a>
   <a  href="javascript:deleteSaleChance()" class="easyui-linkbutton" iconCLs="icon-remove" plain=true>删除</a>
   <br/>
    模块名称:<input id="moduleName" type="text"/>
    操作码值:<input  id="optValue" type="text"/>
   父模块名称:<input id="parentModuleName" type="text" ></input> 
 <a href="javascript:searchModules()" class="easyui-linkbutton" iconCls="icon-search" plain=true>查询</a>
</div>




<!--
<div id='dlg' class="easyui-dialog"  style="width: 500px;height: 400px" title="添加营销机会记录" closed=true buttons="#bt">
     <form  id="fm" method="post">
	     客户名称:<input id="cc" class="easyui-combobox" name="customerName"   
                 valueField='name'  textField='name' url='${ctx}/customer/queryAllCustomers' panelHeight="auto" />  <br/><br/>
	    
	   机会来源:<input name="chanceSource" type="text"/><br/><br/>
	   成功几率:<input name="cgjl" type="text"/><br/><br/>
	   
	   概要:<input name="overview" type="text"/><br/>
	   联系人:<input name="linkMan" required=true  type="text"/><br/>
	   联系电话:<input name="linkPhone" required=true type="text"/><br/>
	  机会描述:<input name="description" required=true type="text"/><br/>
	 分配人:<input id="cc" class="easyui-combobox" name="assignMan"   
                 valueField='trueName'  textField='trueName' url='${ctx}/user/queryAllCustomerManager'panelHeight="auto"/><br/><br/>
     <input name="id" id="id" type="hidden"/>
     <input name="createMan" id="createMan" type="hidden"/>
     </form>

</div>


<div id="bt">
   <a href="javascript:saveSaleChance()" class="easyui-linkbutton" iconCls="icon-save">保存</a>
   <a href="javascript:closeDialog()" class="easyui-linkbutton" iconCls="icon-cancel">取消</a>
</div>
-->





  
</body>
</html>