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
<script type="text/javascript" language="javascript" src="../js/jquery.js"></script>
<script type="text/javascript" language="javascript" src="../js/jquery.form.js"></script>
<script type="text/javascript" language="javascript" src="../js/jquery.tooltip.js"></script>

<script type="text/javascript" charset="utf-8">
var userid =  '${param.userid}';
	function getuserinfo() {
		//获取 URL中传过来的参数 
		
		//alert(userid);
		$("#userid").val(userid);
		if ((userid != '') && (userid != null)){
			$.ajax({
				url : '${pageContext.request.contextPath}/UserManager?module=select&user_id=' + userid,
				type : 'GET',
				dataType : 'json',
				timeout : 5000,
				error : function() {
					alert('通信失败4。');
				},
				success : function(result) {
					console.log(result);
					$("#loginname").val(result.data.loginname);
					$("#user_level").val(result.data.user_level);
					$("#user_pic").val(result.data.user_pic);
					$("#userstate").val(result.data.user_status);
				}
			});
		}

	}
	
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
		var loginname = document.getElementById("loginname").value;
		var user_level = document.getElementById("user_level");
		var userstate = document.getElementById("userstate");
		var user_pic = document.getElementById("user_pic").value;
		//记录错误操作信息
		var errors="";
		
		if (loginname=="" || loginname==null ) {
			errors+="登录名不能为空";
		}
		else if(loginname.length>20 || loginname.length<6){
			errors+="登录名长度应在6~20之间";
		}
		else if(!isNumberOr_Letter(loginname)){
			errors+="登录名只能由数字、字母和下划线组成";
		}
		
		if (userstate.value.length < 1) {
			errors+="人员状态不能为空,请选择人员状态";
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
	function updateUserInfo() {
		if (check4new()) {
			$.ajax({
				url : '${pageContext.request.contextPath}/UserManager?module=update&login_name='+$("#loginname").val()+'&user_level='+$("#user_level").val()+'&user_id='+userid+'&user_status='+$("#userstate").val(),
				type : 'GET',
				dataType : 'json',
				timeout : 5000,
				error : function() {
					alert('The function doesn\'t work ')
				},
				success : function(result) {
					if ( result.status == "ok" ) {
						alert("人员信息修改成功");
						window.close();
					} 
					else {
						alert(result.status);
						alert("人员信息修改失败");
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

<body onload="getuserinfo()">
	<h1>编辑人员信息</h1>
	<hr />
	<form action="${pageContext.request.contextPath}/UserManager" id="userupdate" method="post">
		<input id="index" name="index" type="hidden" value="1">
		<table border="0">
			<tr>
				<td>登录名</td>
				<td><input type="text" name="loginname" id="loginname"><font color="#FF0000">*</font></td>
			</tr>
			<tr>
				<td>等级</td>
				<td><input type="text" name="user_level" id="user_level"><font
					color="#FF0000">*</font></td>
			</tr>
			<tr>
				<td>状态</td>
				<td><select name="userstate" id="userstate"
					onchange="checkstat()">
						<option value=1>正常</option>
						<option value=0>禁用</option>
				</select><font color="#FF0000">*</font></td>
			</tr>
				<tr>
				<td>头像</td>
				<td><input type="text" name="user_pic" id="user_pic">
				<input type="hidden" name="userid" id="userid" value="">
				</td>
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
				<td><input type="button" value="保存" onclick="updateUserInfo()"></td>
				<td><input type="button" value="修改密码 " onclick="changepwd()"></td>
			</tr>
			</table>
	</form>
</body>
</html>