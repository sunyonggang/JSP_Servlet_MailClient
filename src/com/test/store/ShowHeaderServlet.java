package com.test.store;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.MimeUtility;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowHeaderServlet extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("utf-8");
//		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		Folder folder = (Folder) session.getAttribute("folder");
		int msgnum = Integer.parseInt(request.getParameter("msgnum"));

		try
		{
			Message message = folder.getMessage(msgnum);
			String from = (message.getFrom()[0]).toString();
			String subject = message.getSubject();
			String sentDate = DateFormat.getInstance().format(
					message.getSentDate());

			out.println("邮件主题：" + subject + "<br/>");
			out.println("发件人地址：" + from + "<br/>");
			out.println("发送日期：" + sentDate + "<br/>");

			// 如果该邮件是组合型"multipart/*"则可能包含附件等
			if (message.isMimeType("multipart/*"))
			{
				Multipart multipart = (Multipart) message.getContent();
				int bodyCounts = multipart.getCount();
				for (int i = 0; i < bodyCounts; i++)
				{
					BodyPart bodypart = multipart.getBodyPart(i);
//					 如果该BodyPart对象包含附件，则应该解析出来
					if (bodypart.getDisposition() != null)
					{
						String filename = bodypart.getFileName();
						// if(filename.startsWith("=?"))
						{
							// 把文件名编码成符合RFC822规范
							filename = MimeUtility.decodeText(filename);
						}
						// 生成打开附件的超链接
						out.print("<B>附件 " + (i + 1) + "：</B>");
						out.println("<a href=HandleAttachments?msgnum="
								+ msgnum + "&&bodynum=" + i + "&&filename="
								+ filename + ">" + filename + "</a></br>");
					}
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
