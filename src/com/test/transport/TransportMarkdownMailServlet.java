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

		// ��ȡHTMLҳ�������е��ռ��ˡ����⡢����
		request.setCharacterEncoding("utf-8");
		String toAddress = request.getParameter("toAddress");
		String subject = request.getParameter("subject");
		String content = (String) request.getAttribute("contentNew");

		// �ӻỰ����session�л��javax.mail.Session���󣬴���Message����
		javax.mail.Session smtpSession = (javax.mail.Session) session
				.getAttribute("smtpSession");
		//��ʾ�ʼ���������
		smtpSession.setDebug(true);
		Message mail = new MimeMessage(smtpSession);

		// ����Message�����е��ռ��ˡ������ˡ����⡢����
		mail.setRecipient(Message.RecipientType.TO, new InternetAddress(
				toAddress));
		mail.setFrom(new InternetAddress(from));
		mail.setSubject(subject);

		// �����ı������ķŵ�Multipart������
		MimeMultipart multipart = new MimeMultipart("alternative");
		MimeBodyPart htmlBodyPart = new MimeBodyPart();
		htmlBodyPart.setContent(content, "text/html; charset=gb2312");
		multipart.addBodyPart(htmlBodyPart);
		mail.setContent(multipart);
		mail.saveChanges();

		// �����޸ĺ��Message����

		try
		{
			// ���Transport��������SMTP�����������������ļ���
			// ��������SMTP�����������������˴�����nullʡ��֮
			Transport transport = smtpSession.getTransport();
			transport.connect(null, user, pwd);

			// �����ʼ�
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
		out.println("�ռ���: " + toAddress);
		out.println("����: " + subject);
		out.println("����: ");
		out.println(content);
		out.flush();
		// System.out.println("�ʼ����ͳɹ�������");
	}
}
