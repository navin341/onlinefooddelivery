package com.tap.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class loginServlet extends HttpServlet
{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet res=null;
        PrintWriter pw=resp.getWriter();		
		String username="root";
		String pass="Naveen@135";
		Statement stmt=null;
//		String query="select * from jee.user where `email`=? and `password`=?;";
		String query="select * from jee.user;";
		System.out.println("control reached");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jee",username,pass);
//			pstmt=con.prepareStatement(query);
//			pstmt.setString(1,email);
//			pstmt.setString(2, password);
			stmt=con.createStatement();
			res=stmt.executeQuery(query);
			if(res.next()) {
			String name=res.getString("name");
			String gmail=res.getString("email");
			String phoneNumber=res.getString("phoneNumber");
			Cookie c1=new Cookie("name",name);
			Cookie c2=new Cookie("gmail",gmail);
			Cookie c3=new Cookie("phoneNumber",phoneNumber);
			c1.setMaxAge(30);
			c2.setMaxAge(40);
			c3.setMaxAge(50);
			resp.addCookie(c1);
			resp.addCookie(c2);
			resp.addCookie(c3);
			resp.sendRedirect("loginSuccess.html");
			}
			else {
				resp.sendRedirect("loginFailure.html");
			}
		
		}
		catch(ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		}
		finally {
            try {
                if (res != null) {
                    res.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
		}
	}
}
		
		
//System.out.println("resultset executed");
//pw.println("<table>");
//pw.println("<h1>welcome to my jee app</h1><br>");
//while(res.next()==true) {
//	String id=res.getString("id");
//	String name=res.getString("name");
//	String gmail=res.getString("email");
//	String phoneNumber=res.getString("phoneNumber");
//	String Address=res.getString("Address");
//	pw.println("<tr>"
//	+"<td>"+id+"</td>"
//    +"<td>"+name+"</td>"
//	+"<td>"+gmail+"</td>"
//	+"<td>"+phoneNumber+"</td>"
//	+"<td>"+Address+"</td>"
//	+"</tr>");
//}
//pw.println("</table>");




		
////	resp.addCookie(c4);
//		private static final long serialVersionUID = 1L;
