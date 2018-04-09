<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="../includes/header.jsp" %>
<%@ include file="../includes/menu.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
<link rel="stylesheet" type="text/css" href="../css/jquery.dataTables.css" media="all">
<link rel="stylesheet" type="text/css" href="../css/jquery.tooltip.css"	media="all" />
<link rel="stylesheet" type="text/css" href="../css/demo_table.css"	media="all" />
<script type="text/javascript" language="javascript"	 src="../js/jquery.js"></script>
<script type="text/javascript" language="javascript"	 src="../js/jquery.dataTables.js"></script>
<script type="text/javascript" language="javascript"	 src="../js/jquery.tooltip.js"></script>
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
			"sAjaxSource" : "${pageContext.request.contextPath}/UserManager?module=userlist&action=listall",
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
	function newUser()
	{
		window.showModalDialog('/Jinbao/jsp/UserManager/useradd.jsp',window,
		'dialogHeight:auto;dialogWidth:600px;center:yes;help:yes;resizeable:yes;status:yes');
		oTable.fnFilter('');
	//	
		selected = null;
	}
	
	function editUser()
	{
		if (selected == null)
			alert("请先选择一条人员记录。");
		else
		{
			//注意：ajax是异步执行，一个function的后面的语句可能优先于ajax的执行结果，造成错误
			$.ajax(
					{
						url: '${pageContext.request.contextPath}/login?',
						type: 'GET',
						dataType: 'json',
						timeout: 5000,
						async: false,
						error: function(){alert('通信失败5。');},
						success: function(data)
						{
							$("#auth").val(data);
						}
					});
			var data=$("#auth").val();
			if(data=="2"){
				window.showModalDialog('userupdate.jsp?userid=' + selected[0],window,
				'dialogHeight:auto;dialogWidth:600px;center:yes;help:yes;resizeable:yes;status:yes');
			}
			else if (data=="4"){
				alert('该人员也是系统管理员，您没有权限修改其密码');
				/* document.getElementById("detail").disabled=true;
				document.getElementById("delete").disabled=true; */
			}
			else if(data =="3"){
				alert('你不是系统管理员，没有修改其他人密码的权限~');
				document.getElementById("detail").disabled=true;
				document.getElementById("delete").disabled=true;
				document.getElementById("new").disabled=true;
			}
			else{
				//alert(selected[0]);
				window.open('./userupdate.jsp?userid=' + selected[0],window,'用户详情','dialogHeight:auto;dialogWidth:300px;center:yes;help:yes;resizeable:yes;status:yes');  	
			}
			
			oTable.fnFilter('');
			selected = null;	
		}
	}
	
	//密码修改
	function changepwd() {
		alert($("#userid").val())
		window.open('changepwd.jsp?target=user&userid='+ selected[0],window,'修改密碼','dialogHeight:auto;dialogWidth:300px;center:yes;help:yes;resizeable:yes;status:yes');
	}
</script>
</head>
<body>
<h1>用户管理</h1>
<hr/>
	<div><input type="hidden" id="auth" name="auth"  value="2"/></div>
	<table cellpadding="0" cellspacing="0" border="0" class="display" id="userlist">
		<thead>
			<tr>
				<th>用户ID</th>
				<th>登录名</th>
				<th>用户状态</th>
				<th>用户等级</th>
				<th>用户头像</th>
			</tr>
		</thead>
		<tbody>
		</tbody>
	</table>
	<br/><hr />
	<button type="button" id="detail" onclick="editUser()">修改用户信息</button>
</body>
</html>
