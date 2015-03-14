<%@ page language="java" import="java.util.*, java.util.Properties, javax.mail.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'receivemail.jsp' starting page</title>
    
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
		
        String pop3Server = (String)session.getAttribute("pop3Server");  
        String username = (String)session.getAttribute("username");  
        String password = (String)session.getAttribute("password");  
    // 	System.out.println("pop3Server: " + pop3Server);
    // 	System.out.println("username: " + username);
    // 	System.out.println("password: " + password);
        // 创建一个有具体连接信息的Properties对象  
        Properties props = new Properties();  
        props.setProperty("mail.store.protocol", "pop3");  
        props.setProperty("mail.pop3.host", pop3Server);  
          
        // 使用Properties对象获得Session对象  
        Session mailSession = Session.getDefaultInstance(props, null);  
        //mailSession.setDebug(true);  
     
        Folder folder = null;  
        try{          
            // 利用Session对象获得Store对象，并连接pop3服务器  
            Store store = mailSession.getStore("pop3");  
            store.connect(pop3Server, username, password);  
            // System.out.println(store + " try: "); 
            // 获得邮箱内的邮件夹Folder对象，以"读-写"打开  
            folder = store.getFolder("inbox");  
            folder.open(Folder.READ_ONLY);
            
     
        }catch(Exception e){ 
        	
        	//request.getRequestDispatcher("loginwrong.jsp").forward(request, response);
        	e.printStackTrace();
        	//System.exit(0);
             
        }  
    //  Folder folder = POP3Connect.getFolder(pop3Server,user,pwd);  
        session.setAttribute("folder", folder);  
     
        String from = " ";  
        String subject = " ";  
       // System.out.println(folder.getMessages().toString() + "--------");
        Message[] messages = folder.getMessages(); 
        
        int messageCounts = messages.length;  
        for(int i = messageCounts-1; i >= 0; i--)  
        {  
            try{  
                from = messages[i].getFrom()[0].toString();  
                subject = messages[i].getSubject();  
                out.println("<B>邮件 " + (i+1) + "</B>");  
       %>
          </br>
                              发件人地址：<%=from %>&nbsp;&nbsp;
                              邮件主题：<%=subject %>&nbsp;&nbsp;
          <a href="showmails.jsp?msgnum=<%=i+1%>"> 查看邮件</a>
           </br>
            </br>
<%  
            }catch(Exception e){  
                e.printStackTrace();  
            }  
        }  
    %>
	
	    

  </body>
</html>
