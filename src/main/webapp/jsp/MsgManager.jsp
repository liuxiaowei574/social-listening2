<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>消息管理</title>
<link rel="stylesheet" type="text/css" href="/Social-Listening/css/jquery.dataTables.css" media="all">
<link rel="stylesheet" type="text/css" href="/Social-Listening/css/jquery.tooltip.css"	media="all" />
<link rel="stylesheet" type="text/css" href="/Social-Listening/css/demo_table.css"	media="all" />
<script type="text/javascript" language="javascript"	 src="/Social-Listening/js/jquery.js"></script>
<script type="text/javascript" language="javascript"	 src="/Social-Listening/js/jquery.dataTables.js"></script>
<script type="text/javascript" language="javascript"	 src="/Social-Listening/js/jquery.tooltip.js"></script>
<script type="text/javascript" charset="utf-8">
	var selected = null;
	var oTable= null;
	var tableid;
	var search = "";
	$(document).ready(function(){
		tableid = $("table").attr("id");
		//init Datatables
		search = getParam("target");
		if(search == null){
			search = "";
		}
		oTable = $('#userlist').dataTable({
			"bProcessing" : true,
			"bServerSide" : true,
			"sPaginationType" : "full_numbers",
			"oLanguage" : {
				"sProcessing" : "正在获取数据",
				"sLengthMenu" : "每页显示 _MENU_ 条记录",
				"sZeroRecords" : "抱歉， 没有找到",
				"sInfo" : "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
				"sInfoEmpty" : "没有数据",
				"sInfoFiltered" : "(从 _MAX_ 条数据中检索)",
				"sZeroRecords" : "没有检索到数据",
				"sSearch" : "查询:",
				"oPaginate" : {
					"sFirst" : "首页",
					"sPrevious" : "前一页",
					"sNext" : "后一页",
					"sLast" : "尾页"
				}
			},
			"sAjaxSource" : "/Social-Listening/MsgManager?module=list_all",
			"aoColumns" : [ {
				"bVisible" : false,
				"sClass" : "center",
			},{
				"bSortable" : false,
				"sClass" : "center",
			}, {
				"bSortable" : false,
				"sClass" : "center",
			}, {
				"bSortable" : false,
				"sClass" : "center",
			}, {
				"bSortable" : false,
				"sClass" : "center",
			},{
				"bSortable" : false,
				"sClass" : "center",
			}],
			"fnRowCallback" : function(nRow, aData, iDisplayIndex) {
				//var outofdate = oTable.fnGetData(nRow);
				return nRow;
			}
		});
		if(oTable){
			oTable.fnFilter(search);
		}
		$('#userlist tbody tr').live('click',function(){
			$('#userlist tbody tr').removeClass('row_selected');
			$(this).addClass('row_selected');
			selected = oTable.fnGetData(this);
		});		
	});
	/*从url中获取参数 */
	function getParam(val) {
		var uri = window.location.search;
		var re = new RegExp(val + "=([^&?]*)", "ig");
		return ((uri.match(re)) ? (uri.match(re)[0].substr(val.length + 1))
				: null);
	}
	function newMsg()
	{
		window.open('./newMsg.jsp', window,'新建消息','dialogHeight:auto;dialogWidth:300px;center:yes;help:yes;resizeable:yes;status:yes');  
		oTable.fnFilter('');
	//	window.location.reload();
		selected = null;
	}
	
	function deletMsg()
	{
		if (selected == null)
			alert("请先选择一条人员记录。");
		else
		{
			if(confirm("确定删除选中消息")){
				$.get("/Social-Listening/MsgManager?module=delete&target="+selected[0]
						,function(result) {
						});
			}
			
			oTable.fnFilter('');
			selected = null;
		}
	}
</script>
</head>
<body>
<h1>消息管理</h1>
<hr/>
	<div><input type="hidden" id="auth" name="auth"  value="2"/></div>
	<table cellpadding="0" cellspacing="0" border="0" class="display" id="userlist">
		<thead>
			<tr>
				<th>消息ID</th>
				<th>接收人</th>
				<th>消息等级</th>
				<th>消息标题</th>
				<th>消息内容</th>
				<th>消息状态</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	<br/><hr />
	<button type="button" id="detail" onclick="newMsg()">创建消息</button>
	<button type="button" id="detail" onclick="deletMsg()">删除消息</button>
</body>
</html>
