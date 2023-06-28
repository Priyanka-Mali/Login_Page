

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user")
public class user extends HttpServlet {
	 
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		try
		{
		com.mysql.cj.jdbc.Driver d=new com.mysql.cj.jdbc.Driver();
		DriverManager.registerDriver(d);
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/priyanka","root","root");
		
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String n=req.getParameter("name");
		String p=req.getParameter("password");
		
		PreparedStatement pst=conn.prepareStatement("select *from login where name=? and password=?");
//		pst.setString(1,id);
	 	pst.setString(1, n);
		pst.setString(2, p);
		ResultSet rs=pst.executeQuery();
		if(rs.next())
		{
			RequestDispatcher rd=req.getRequestDispatcher("home.html");
			rd.forward(req, res);
			
		}
		else
		{
			out.println("<h2>Login failed</h2>");	
			out.println("<a href=login.jsp>Try Again</a>");  
		}
		}
		catch(Exception ex)
		{
			System.out.println("error "+ex);
		}

}
}
