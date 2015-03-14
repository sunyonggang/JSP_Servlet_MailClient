<%@ page language="java" import="java.util.*, javax.mail.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'main.jsp' starting page</title>

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
	<%
		// 初始化SMTP、POP3服务器的主机名  
	    String servername = request.getParameter("servername");  
	    String smtpServer = "smtp." + servername;  
	    String pop3Server = "pop." + servername;  
	 
	    // 邮箱用户名、密码  
	    String username = request.getParameter("username");  
	    String password = request.getParameter("password");  
	 
	    // 用当前会话对象session保存发件人、用户名、密码  
	    session.setAttribute("from", username+"@"+servername);  
	    session.setAttribute("username", username);  
	    session.setAttribute("password", password); 
	    session.setAttribute("pop3Server", pop3Server);
	    session.setAttribute("smtpServer", smtpServer);
	 
	    // 创建SMTP服务器的属性文件并初始化  
	    Properties smtpProps = new Properties();  
	    smtpProps.setProperty("mail.transport.protocol", "smtp");  
	    smtpProps.setProperty("mail.smtp.host", smtpServer);  
	    smtpProps.setProperty("mail.smtp.auth", "true");  
	      
	    // 根据属性文件获得javax.mail.Session对象，并保存之  
	    Session smtpSession = Session.getInstance(smtpProps);  
	  //  smtpSession.setDebug(true);   
	    session.setAttribute("smtpSession", smtpSession);  
	 
	    // 创建POP3服务器的属性文件并初始化  
	    Properties pop3Props = new Properties();  
	    pop3Props.setProperty("mail.store.protocol", "pop3");  
	    pop3Props.setProperty("mail.pop3.host", pop3Server);  
	 
	    // 根据属性文件获得javax.mail.Session对象，并保存之  
	    Session pop3Session = Session.getInstance(pop3Props);  
	    //pop3Session.setDebug(true);//这里如果为true的话控制台输出太多了  
	    session.setAttribute("pop3Session", pop3Session); 
	
	
	 %>
	<div align="center">
		<img src="image/geekinformation.jpg" alt="selfintroduction.pic"
			height="25%" width="220">
		<p>
			<em>人面不知何处去，桃花依旧笑春风</em>
		</p>

	</div>
	<div style="height:60%">
		<div style="float:left;width:49%;">
		<a href="receivemail.jsp"><img width="300" height="300" alt="got a mail" src="image/got_a_mail.jpg"><br></a>

	</div>
	<div style="float:right;width:49%;">
			<a href="sendmail2.jsp"><img  width="300" height="300" alt="post a mail" src="image/post_a_mail.jpg"><br></a>
		
	</div>
	
	</div>
	
	<div style="float:left;height:8%">
		<p>
			你可以在这里找到我
			<a href="http://weibo.com/1949975397/profile?topnav=1wvr=5">@yonggang_sun</a>
		&nbsp;&nbsp;
			有问题可以联系我<a href="mailto:syonggang1126@gmail.com">yonggang_sun@gmail.com</a>
		</p>
	</div>
</body>
</html>
