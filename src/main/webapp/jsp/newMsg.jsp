<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户详情</title>
<link rel="stylesheet" type="text/css" href="../css/jquery.tooltip.css" media="all" />
<style type="text/css">
.outofdate {
	color: red;
}
</style>
<script type="text/javascript" language="javascript"
	src="../js/jquery.js"></script>
<script type="text/javascript" language="javascript"
	src="../js/jquery.form.js"></script>
<script type="text/javascript" language="javascript" src="../js/md5.js"></script>

<script type="text/javascript" language="javascript"
	src="../js/jquery.tooltip.js"></script>

<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		var options = {
			target : '#output2', // target element(s) to be updated with server response 
			success : showResponse, // post-submit callback 
			dataType : 'text', // 'xml', 'script', or 'json' (expected server response type) 
			timeout : 3000
		};
		$('#userupdate').ajaxForm(options);
		
		$.get("/Social-Listening/UserManager?module=listall",
		function(result) {
			if ( result != "[]"){
				var data = eval(result);
				$.each(data, function(i, item) {
					$("<option value="+item[0]+">" + item[1] + "</option>").appendTo($("#reciever"));
				});
				$("#reciever").val(1);
			}
		});
	});
	
	function showResponse(responseText, statusText, xhr, $form) {
		if (responseText == "UPDATE OK") {
			alert("人员信息修改成功");
			window.close();
		} 
		else if (responseText == "UPDATE FAILED") {
			alert("人员信息修改失败");
		}
	}
	//验证字符串需由字母数字和下划线组成
	function isNumberOr_Letter(s){
		var regu="^[0-9a-zA-Z\_]+$";
		var re=new RegExp(regu);
		if(re.test(s)){
			return true;
		}
		else 
			return false;
	}
	//验证字符串需要由 数字组成
	function isNumber(s){
		var regu="^[0-9]+$";
		var re=new RegExp(regu);
		if(re.test(s)){
			return true;
		}
		else 
			return false;
	}
	function check4new() {
		var msg_title =document.getElementById("msg_title").value;
		var msg_info = document.getElementById("msg_info").value;
		//记录错误操作信息
		var errors="";
		if(msg_title=="" || msg_title==null){
			errors+="人员编号不能为空";
		}
		else if(msg_info=="" || msg_info==null){
			errors+="人员编号应小于20位";
		}
		
		if(errors==""){
			return true;
		}
		else{
			alert(errors);
			return false;
		}
	}
	/**
	 * 更新人员信息 
	 */
	function insertMsg() {
		if (check4new()) {
			$.ajax({
				url : '/Social-Listening/MsgManager?module=insert&msg_title='+$("#msg_title").val()+'&msg_info='+$("#msg_info").val()+'&user_id='+$("#reciever").val()+'&msg_level=1',
				type : 'GET',
				dataType : 'json',
				timeout : 5000,
				error : function() {
					alert('The function doesn\'t work ')
				},
				success : function(result) {
					if ( result.status == "ok" ) {
						alert("消息发送成功");
						window.close();
					} 
					else {
						alert(result.status);
						alert("消息发送失败");
					}
				}
			});
		}
	}
	//从cookie中得到名字为name的值，返回相应的字符串，如找不到返回-1
	function getcookie(name) {
		var cookie_start = document.cookie.indexOf(name);
		var cookie_end = document.cookie.indexOf(";", cookie_start);
		return cookie_start == -1 ? '' : unescape(document.cookie.substring(
				cookie_start + name.length + 1,
				(cookie_end > cookie_start ? cookie_end
						: document.cookie.length)));
	}



</script>

</head>

<body>
	<h1>发新消息</h1>
	<hr />
	<form action="/Social-Listening/UserManager" id="userupdate" method="post">
		<input id="index" name="index" type="hidden" value="1">
		<table border="0">
			<tr>
				<td>消息标题</td>
				<td><input type="text" name="msg_title" id="msg_title"><font color="#FF0000">*</font></td>
			</tr>
			<tr>
				<td>消息内容</td>
				<td><textarea rows="10" cols="80" name="msg_info" id="msg_info"></textarea><font color="#FF0000">*</font></td>
			</tr>
			<tr>
				<td>接收人</td>
				<td><select name="reciever" id="reciever">
				</select><font color="#FF0000">*</font></td>
			</tr>
		</table>
			<!-- <table cellpadding="0" cellspacing="0" border="0" class="display"
				id="credlist">
				<thead>
					<tr>
						<th>人员资质</th>
						<th>资质获得时间</th>
						<th>资质有效期至</th>
					</tr>
					<tr>
						<th><input type="text" name="cred_name1" id="cred_name1"></th>
						<th><input type="text" name="accessdate1" id="accessdate1"></th>
						<th><input type="text" name="period1" id="period1"></th>
					</tr>
					<tr>
						<th><input type="text" name="cred_name2" id="cred_name2"></th>
						<th><input type="text" name="accessdate2" id="accessdate2"></th>
						<th><input type="text" name="period2" id="period2"></th>
					</tr>
					<tr>
						<th><input type="text" name="cred_name3" id="cred_name3"></th>
						<th><input type="text" name="accessdate3" id="accessdate3"></th>
						<th><input type="text" name="period3" id="period3"></th>
					</tr>

				</thead>
				<tbody>
				</tbody>
			</table> -->

			<table>
				<tr>
				<td><input id="mode" name="mode" type="hidden" value="update"></td>
				<td><input type="button" value="保存" onclick="insertMsg()"></td>
			</tr>
			</table>
	</form>
</body>
</html>