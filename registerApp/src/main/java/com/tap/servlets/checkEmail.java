package com.tap.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class checkEmail extends HttpServlet
{
	 @Override
		public void init() throws ServletException {
			System.out.println("init() method in checkEmail");
		}
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("service() method in checkemail");
		String email=req.getParameter("email");
		String name=req.getParameter("name");
		String password=req.getParameter("password");
		String cpassword=req.getParameter("cpassword");
		String phoneNumber=req.getParameter("phoneNumber");
		String Address=req.getParameter("Address");
		if(email.length()>=10) {
			resp.getWriter().println("Email checked");
			HttpSession session=req.getSession();
			session.setAttribute("name", name);
			session.setAttribute("email", email);
			session.setAttribute("password", password);
			session.setAttribute("cpassword", cpassword);
			session.setAttribute("phoneNumber", phoneNumber);
			session.setAttribute("Address",Address);
			
			resp.sendRedirect("register");
			
		}
		else {
			resp.getWriter().println("Email error");
		}
	}
	@Override
	public void destroy() {
		System.out.println("destroy()method on checkEmail");
	}

}
//giving control to register sevrlet so it can access parameters
//RequestDispatcher rd=req.getRequestDispatcher("/register");
//rd.forward(req, resp);
//rd.include(req, resp);
