package com.tap.servlets;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class registerSevlet extends HttpServlet
{
	String username="root";
	String Password="Naveen@135";
	String url="jdbc:mysql://localhost:3306/jee";
	String query="insert into jee.user(name,email,phoneNumber,password,Address) values(?,?,?,?,?);";
	private Connection con=null;
    private PreparedStatement pstmt=null;
    @Override
	public void init() throws ServletException {
		System.out.println("init() method in registerServlet");
	}
	@Override
//	dopost
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter pw=resp.getWriter();
		System.out.println("service() method in registerServlet");
		
		try {
			HttpSession session=req.getSession();
//			getattribute will return you an object u need to use with string child class so you need to use downcasting 
			String name=(String)session.getAttribute("name");
			String email=(String)session.getAttribute("email");
			String password=(String)session.getAttribute("password");
			String cpassword=(String)session.getAttribute("cpassword");
			String pn=(String)session.getAttribute("phoneNumber");
			String Address=(String)session.getAttribute("Address");
			long phoneNumber= Long.parseLong(pn);
			
//			Long pn=Long.parseLong("phoneNumber");
			
			if(password.equals(cpassword)) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con=DriverManager.getConnection(url,username,Password);
				pstmt=con.prepareStatement(query);
				pstmt.setString(1, name);
				pstmt.setString(2, email);
				pstmt.setLong(3, phoneNumber);
				pstmt.setString(4, password);
				pstmt.setString(5, Address);
				
				pw.println(name+" "+email+" "+password+" "+cpassword+" "+phoneNumber+" "+Address); 
				int x=pstmt.executeUpdate();
				pw.println(x+"row effected navin");
			}
			else {
				pw.print("password correct ga kottu mowa");
			}
		}
		catch(ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void destroy() {
		System.out.println("destroy() method in registerServlet");
	}

}
