package com.test.util;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ValidateServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		List<String> list = new ArrayList<String>();
		
		if(null == username || "".equals(username))
		{
			list.add("username can't be blank!");
		}
		if(password == null || password.length() < 6 || password.length() > 20)
		{
			list.add("length of password should be between 6 and 15");
		}
		
		if(list.isEmpty())
		{
//			req.setAttribute("username", username);
//			req.setAttribute("password", password);
			req.getRequestDispatcher("main.jsp").forward(req, resp);
		}
		else
		{
			req.setAttribute("error", list);
			
			req.getRequestDispatcher("error.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException
	{
		this.doGet(req, resp);
	}
}
