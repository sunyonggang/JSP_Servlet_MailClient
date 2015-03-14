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

			out.println("�ʼ����⣺" + subject + "<br/>");
			out.println("�����˵�ַ��" + from + "<br/>");
			out.println("�������ڣ�" + sentDate + "<br/>");

			// ������ʼ��������"multipart/*"����ܰ���������
			if (message.isMimeType("multipart/*"))
			{
				Multipart multipart = (Multipart) message.getContent();
				int bodyCounts = multipart.getCount();
				for (int i = 0; i < bodyCounts; i++)
				{
					BodyPart bodypart = multipart.getBodyPart(i);
//					 �����BodyPart���������������Ӧ�ý�������
					if (bodypart.getDisposition() != null)
					{
						String filename = bodypart.getFileName();
						// if(filename.startsWith("=?"))
						{
							// ���ļ�������ɷ���RFC822�淶
							filename = MimeUtility.decodeText(filename);
						}
						// ���ɴ򿪸����ĳ�����
						out.print("<B>���� " + (i + 1) + "��</B>");
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
