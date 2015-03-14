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
		// ��ȡ�������Session �Ự�����ʼ��� Folder ����
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
				// ���ʼ����Ͳ���"mixed"�����������������
				// �����������������ֱ���������
				response.setContentType("message/rfc822");
				message.writeTo(out);
			}
//			else
//			{
//				 �����"mixed"�ͣ����������BodyPart����
//				 �Ѳ������������ʼ����Ĵ�ӡ������
//				 ����Ϊ�˲��øó������������ִ��丽������������
//				Multipart multipart = (Multipart) message.getContent();
//				int bodyCounts = multipart.getCount();
//				for (int i = 0; i < bodyCounts; i++)
//				{
//					BodyPart bodypart = multipart.getBodyPart(i);
//					 ����"mixed"���Ҳ���������
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
