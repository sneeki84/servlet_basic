package study.day02;

import org.omg.CORBA.TCKind;
import study.IOUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * User: kimkm
 * Date: 2018-08-18
 * Time: 오전 10:55
 */
@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		RequestDispatcher requestDispatcher = req.getRequestDispatcher("/auth/loginform.jsp");
		requestDispatcher.forward(req,resp );
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		try{
			ServletContext sc = this.getServletContext();
			conn = (Connection) sc.getAttribute("conn");
			pstmt = conn.prepareStatement("select code, email, pwd, name from members WHERE email = ? AND pwd = ?");
			pstmt.setString(1, req.getParameter("email") );
			pstmt.setString(2, req.getParameter("password") );
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				HttpSession session = req.getSession();
				session.setAttribute("code", rs.getString("code"));
				session.setAttribute("name", rs.getString("name"));
				resp.sendRedirect("../member/list");
			}else{
				RequestDispatcher requestDispatcher = req.getRequestDispatcher("/auth/loginfail.jsp");
				requestDispatcher.forward(req,resp );
			}
		}catch (Exception e)
		{
			throw new ServletException(e);
		}finally
		{
			IOUtil.closeAll(rs, pstmt);
		}
	}
}
