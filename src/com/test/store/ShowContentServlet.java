package com.test.store;

import java.io.IOException;
import java.io.OutputStream;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowContentServlet extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		// 获取输出流、Session 会话对象、邮件夹 Folder 对象
		OutputStream out = response.getOutputStream();
		HttpSession session = request.getSession();
		Folder folder = (Folder) session.getAttribute("folder");
		// System.out.println("bbbb: " + request.getParameter("msgnum") +
		// ": aaaaaaa");
		int msgnum = Integer.parseInt(request.getParameter("msgnum"));

		try
		{
			Message message = folder.getMessage(msgnum);
			if (!message.isMimeType("multipart/mixed"))
			{
				// 若邮件类型不是"mixed"则表明不包含附件，
				// 并设置类型让浏览器直接输出正文
				response.setContentType("message/rfc822");
				message.writeTo(out);
			}
//			else
//			{
//				 如果是"mixed"型，则遍历所有BodyPart对象，
//				 把不包含附件的邮件正文打印出来。
//				 这是为了不让该程序既输出正文又传输附件大量的数据
//				Multipart multipart = (Multipart) message.getContent();
//				int bodyCounts = multipart.getCount();
//				for (int i = 0; i < bodyCounts; i++)
//				{
//					BodyPart bodypart = multipart.getBodyPart(i);
//					 不是"mixed"型且不包含附件
//					if (!bodypart.isMimeType("multipart/mixed")
//							&& bodypart.getDisposition() == null)
//					{
//						response.setContentType("message/rfc822");
//						bodypart.writeTo(out);
//					}
//				}
//			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
