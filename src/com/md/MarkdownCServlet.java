package com.md;

import java.io.IOException;
import java.io.PrintWriter;

import javax.mail.internet.MimeUtility;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.markdown4j.Markdown4jProcessor;

public class MarkdownCServlet extends HttpServlet
{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		process(req, resp);
	}

	public void process(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		resp.setContentType("text/html;charset=utf-8");
//		resp.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("utf-8");

//		PrintWriter out = resp.getWriter();
//		String toAddress = req.getParameter("toAddress");
//		String subject = req.getParameter("subject");
		String contentNew = req.getParameter("content");

		contentNew = new Markdown4jProcessor().process(contentNew);
//		System.out.println("you get it: " + contentNew);
		req.setAttribute("contentNew", contentNew);

		req.getRequestDispatcher("TransportMarkdownMailServlet").forward(req, resp);

//		System.out.println("fail......." + contentolder);
		
	}
}
