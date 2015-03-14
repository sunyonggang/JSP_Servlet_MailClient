package com.test.transport;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.markdown4j.Markdown4jProcessor;

public class TransportMarkdownMailServlet extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		try
		{
			sendMail(request, response);
		} catch (AddressException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		try
		{
			sendMail(request, response);
		} catch (AddressException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendMail(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			AddressException, MessagingException
	{
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String user = (String) session.getAttribute("username");
		String pwd = (String) session.getAttribute("password");
		String from = (String) session.getAttribute("from");

		// 获取HTML页面请求中的收件人、主题、正文
		request.setCharacterEncoding("utf-8");
		String toAddress = request.getParameter("toAddress");
		String subject = request.getParameter("subject");
		String content = (String) request.getAttribute("contentNew");

		// 从会话对象session中获得javax.mail.Session对象，创建Message对象
		javax.mail.Session smtpSession = (javax.mail.Session) session
				.getAttribute("smtpSession");
		//显示邮件发送内容
		smtpSession.setDebug(true);
		Message mail = new MimeMessage(smtpSession);

		// 设置Message对象中的收件人、发件人、主题、正文
		mail.setRecipient(Message.RecipientType.TO, new InternetAddress(
				toAddress));
		mail.setFrom(new InternetAddress(from));
		mail.setSubject(subject);

		// 将纯文本的正文放到Multipart对象中
		MimeMultipart multipart = new MimeMultipart("alternative");
		MimeBodyPart htmlBodyPart = new MimeBodyPart();
		htmlBodyPart.setContent(content, "text/html; charset=gb2312");
		multipart.addBodyPart(htmlBodyPart);
		mail.setContent(multipart);
		mail.saveChanges();

		// 保存修改后的Message对象

		try
		{
			// 获得Transport对象并连接SMTP服务器，由于属性文件中
			// 已设置了SMTP服务器的主机名，此处可用null省略之
			Transport transport = smtpSession.getTransport();
			transport.connect(null, user, pwd);

			// 发送邮件
			transport.sendMessage(mail,
					mail.getRecipients(Message.RecipientType.TO));
			// System.out.println("123: " + mail);
			transport.close();
		} catch (Exception e)
		{
//			 request.getRequestDispatcher("loginwrong.jsp").forward(request,
//			 response);
			e.printStackTrace();
//			 System.exit(0);
		}
		
		toAddress = new Markdown4jProcessor().process("##" + toAddress + "##");
		subject = new Markdown4jProcessor().process("##" + subject + "##");
		out.println("收件人: " + toAddress);
		out.println("主题: " + subject);
		out.println("内容: ");
		out.println(content);
		out.flush();
		// System.out.println("邮件发送成功！！！");
	}
}
