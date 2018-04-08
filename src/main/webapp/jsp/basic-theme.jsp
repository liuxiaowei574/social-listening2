<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<title><decorator:title default="INTRANET" /></title>
<link rel="stylesheet" type="text/css" href="../css/main_frame.css" media="all" />
<decorator:head />
</head>
<%-- <body bgcolor="#FFFFFF" background="<%=request.getContextPath()%>/images/bg.gif" > --%>
<body bgcolor="#FFFFFF">
<table height="auto" width="auto" border="0" cellspacing="0" cellpadding="0">
<tr>
<td>
<img id="logo" src="/Social-Listening/favicon.ico" alt="180CA管理系统" width=180 height=80/>
</td>
<td valign="up"  id="header" nowrap> 
<%@ include file="/includes/header.jsp" %>
</td>

</tr>
<tr>
<td  width="16%" valign="top"  id="menu" nowrap>
<script type="text/javascript">window.status = "Loading: Navigation...";</script>
<%@ include file="../includes/menu.jsp" %>
</td>
<td valign="top" id="main" nowrap>
<br>
<div class="docBody"><decorator:body /></div>
</td>
<td width="1%" nowrap> </td>
</tr>
</table>
<br>

 

</body>
</html>