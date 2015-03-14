<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'loginwrong.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <center><h1>There are some wrong with you</h1></center>
    <%
  //  String pop3Server = (String)session.getAttribute("pop3Server");  
    String username = (String)session.getAttribute("username");  
    String password = (String)session.getAttribute("password");  
 	
    %>
   <table border="0">
  <tr>
    <th>用户名</th>
    <th>密码</th>
   
  </tr>
  <tr>
    <td><%=username %></td>
    <td><%=password %></td>
   
  </tr>
  
</table>
<a href="http://localhost:8080/test/">回到首页</a>
  </body>
</html>
