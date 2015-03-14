package com.test.store;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HandleAttachments extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		int msgnum = Integer.parseInt(request.getParameter("msgnum"));
		int bodynum = Integer.parseInt(request.getParameter("bodynum"));
		Folder folder = (Folder) session.getAttribute("folder");
		String filename = request.getParameter("filename");

		try
		{
			Message message = folder.getMessage(msgnum);
			// 将消息头类型设置为附件类型
			response.setHeader("Content-Disposition", "attachment;filename="
					+ filename);

			Multipart multipart = (Multipart) message.getContent();
			BodyPart bodypart = multipart.getBodyPart(bodynum);

			InputStream input = bodypart.getInputStream();
			int temp = 0;
			while ((temp = input.read()) != -1)
			{
				out.write(temp);
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
