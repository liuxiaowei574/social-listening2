<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>项目详情</title>
<link rel="stylesheet" type="text/css" href="../css/jquery.tooltip.css" media="all" />
<link rel="stylesheet" type="text/css" href="../css/jquery.datetimepicker.css" media="all" />
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
	src="../js/jquery.datetimepicker.js"></script>
<script type="text/javascript" language="javascript"
	src="../js/jquery.tooltip.js"></script>
<script type="text/javascript" language="javascript" src="../js/jquery.validate.js"></script>

<script type="text/javascript" charset="utf-8">
	$(document).ready(function() {
		var options = {
			target : '#output2', // target element(s) to be updated with server response 
			success : showResponse, // post-submit callback 
			dataType : 'text', // 'xml', 'script', or 'json' (expected server response type) 
			timeout : 3000
		};
		$('#userupdate').ajaxForm(options);
		jQuery(function() {
			jQuery('#start_time').datetimepicker(
					{
						format : 'Y-m-d',
						lang : 'ch',
						onShow : function(ct) {
							this.setOptions({
								maxDate : jQuery('#start_time')
										.val() ? jQuery(
										'#start_time').val()
										: false
							})
						},
						timepicker : false
					});
			jQuery('#end_time')
					.datetimepicker(
							{
								format : 'Y-m-d',
								lang : 'ch',
								onShow : function(ct) {
									this
											.setOptions({
												minDate : jQuery(
														'#end_time')
														.val() ? jQuery(
														'#end_time')
														.val()
														: false
											})
								},
								timepicker : false
							});
			jQuery('#updata_time').datetimepicker({
				format : 'Y/m/d',
				lang : 'ch',
				onShow : function(ct) {
					this.setOptions({
					// minDate:jQuery('#accessdate1').val()?jQuery('#accessdate1').val():false
					})
				},
				timepicker : false
			});
		});
		
	});
	var project_id =  '<%=request.getParameter("project_id")%>';
	function getuserinfo() {
		//获取 URL中传过来的参数 
		
		//alert(project_id);
		
		if ((project_id != '') && (project_id != null)){
			$.ajax({
				url : '${pageContext.request.contextPath}/ProjectManager?module=backbone_select&project_id=' + project_id,
				type : 'GET',
				dataType : 'json',
				timeout : 5000,
				error : function() {
					alert('通信失败4。');
				},
				success : function(result) {
					$("#project_name").val(result.data.project_name);
					$("#project_type").val(result.data.project_type);
					$("#brand").val(result.data.project_brand);
					$("#product").val(result.data.project_product);
					$("#project_info").val(result.data.project_info);
					$("#source_list").val(result.data.project_source);
					$("#key_words").val(result.data.project_key_words);
					$("#start_time").val(result.data.start_time);
					$("#end_time").val(result.data.end_time);
					$("#project_priority").val(result.data.project_priority);
					$("#project_status").val(result.data.project_status);
					
					document.getElementById("project_name").readOnly = true;
					document.getElementById("project_type").readOnly = true;
					document.getElementById("brand").readOnly = true;
					document.getElementById("product").readOnly = true;
					document.getElementById("project_info").readOnly = true;
					document.getElementById("source_list").readOnly = true;
					document.getElementById("key_words").readOnly = true;
					document.getElementById("start_time").readOnly = true;
					document.getElementById("end_time").readOnly = true;
					
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
		var project_name =document.getElementById("project_name").value;

		//记录错误操作信息
		var errors="";
		if( project_name=="" || project_name==null){
			errors+="项目名称不能为空";
		}

		if ($("#start_time").val() != "") {
			if (!isDate($("#start_time").val())) {
				errors+="您输入的时间格式非法，请按yyyy-mm-dd格式重新填写";
				$("#start_time").val("");
			}
		}
		if ($("#end_time").val() != "") {
			if (!isDate($("#end_time").val())) {
				errors+="您输入的时间格式非法，请按yyyy-mm-dd格式重新填写";
				$("#end_time").val("");
			}
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
	function updateProjectInfo() {
		if (check4new()) {
			var reqData = {
					module : "check_update",
					project_id : project_id,
					project_priority : $("#project_priority" ).val(),
					project_status : $("#project_status" ).val()
			    };
			
			$.ajax({
				url : '${pageContext.request.contextPath}/ProjectManager',
				data: reqData,
				type : 'GET',
				contentType:"application/json;charset=utf-8",
				dataType : 'json',
				timeout : 5000,
				error : function() {
					alert('通信失败4。');
				},
				success : function(result) {
					if ( result.status == "ok" ) {
						alert("项目状态修改成功");
						window.close();
					} 
					else {
						alert(result.status);
						alert("项目状态修改失败");
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
	<h1>编辑项目信息</h1>
	<hr />
	<form action="${pageContext.request.contextPath}/UserManager" id="userupdate" method="post">
		<input id="index" name="index" type="hidden" value="1">
		<table border="0">
			<tr>
				<td>项目名称</td>
				<td><input type="text" name="project_name" id="project_name"><font color="#FF0000" >*</font></td>
			</tr>
			<tr>
				<td>项目类型</td>
				<td><input type="text" name="project_type" id="project_type"><font color="#FF0000">*</font></td>
			</tr>
			<tr>
				<td>品牌</td>
				<td><input type="text" name="brand" id="brand"><font
					color="#FF0000">*</font></td>
			</tr>
			<tr>
				<td>商品</td>
				<td><input type="text" name="product" id="product"><font
					color="#FF0000">*</font></td>
			</tr>
			<tr>
				<td>简介</td>
				<td><textarea rows="10" cols="80" name="project_info" id="project_info"></textarea><font
					color="#FF0000">*</font></td>
			</tr>
			<tr>
				<td>关键字</td>
				<td><input type="text" name="key_words" id="key_words"><font
					color="#FF0000">*</font></td>
			</tr>
			<tr>
				<td>信息源</td>
				<td><input type="text" name="source_list" id="source_list"><font
					color="#FF0000">*</font></td>
			</tr>
			<tr>
				<td>开始时间</td>
				<td><input type="text" name="start_time" id="start_time"><font
					color="#FF0000">*</font></td>
			</tr>
			<tr>
				<td>结束时间</td>
				<td><input type="text" name="end_time" id="end_time"><font
					color="#FF0000">*</font></td>
			</tr>
			<tr>
				<td>优先级</td>
				<td><input type="text" name="project_priority" id="project_priority"><font
					color="#FF0000">*</font></td>
			</tr>
			<tr>
				<td>状态</td>
				<td><select name="project_status" id="project_status">
						<option value=0>拒绝</option>
						<option value=1>提交</option>
						<option value=2>分析</option>
						<option value=3>完成</option>
				</select><font
					color="#FF0000">*</font></td>
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
				<td><input type="button" value="保存" onclick="updateProjectInfo()"></td>
			</tr>
			</table>
	</form>
</body>
</html>