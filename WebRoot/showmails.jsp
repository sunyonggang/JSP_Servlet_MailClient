<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 	 <frameset rows="25%,*"> 
        <frame src="/test/ShowHeaderServlet?msgnum=<%=request.getParameter("msgnum")%>" scrolling="yes"> 
        <frame src="/test/ShowContentServlet?msgnum=<%=request.getParameter("msgnum")%>" scrolling="yes"> 
    </frameset> 
</html>
