<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache" />
<title>Insert title here</title>
<style type="text/css">
.l2 {
	 /*CSS class for menu headers in general (expanding or not!)*/ 
	 font: bold 18px Arial; 
	 color: white; 
	 background: #6CAAD9  repeat-x center left; 
	 /*margin-bottom: 10px; 
	 /*bottom spacing between header and rest of content*/ 
	 text-transform: uppercase;
	  padding: 4px 0 4px 10px; 
	  /*header text is indented 10px*/ 
	  cursor: hand; 
	  cursor: pointer; 
}
.l3 {
	display: "none";
}
a{ text-decoration:none} 
.l2 a:link{color:#FFFFFF} 
</style>
</head>
<body>
	<div id="bodycenter">
		<div class="menu">
			<div class="slist">
				<!-- 一个用户具有的三维向量<职务岗位, 实验室岗位, 质量管理岗位> 将决定以下各单元的显示-->
				<!-- 信息查看单元，<*,*,*>所有人均可见 -->
				<div id="basicinfo">
					<h2 class="l2">
						<a href="../jsp/UserManager.jsp">用户管理</a>
					</h2>
					<h2 class="l2">
						<a href="../jsp/ProjectManager.jsp">项目管理</a>
					</h2>
					<h2 class="l2">
						<a href="../jsp/MsgManager.jsp">消息管理</a>
					</h2>
				</div>
			</div>
		</div>
	</div>
</body>
</html>