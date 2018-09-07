package study.day01;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: kimkm
 * Date: 2018-08-04
 * Time: 오전 10:59
 */
@WebServlet("/member/list")
public class MemberListServlet extends GenericServlet
{
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException
	{
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;

		try
		{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection("jdbc:mysql://localhost/midas1", "kimkm", "kimkm");
			stat = conn.createStatement();
			rs = stat.executeQuery("select code, email, pwd, name from members ");

			response.setContentType("text/html");

			List<Map<String,String>> listMap = new ArrayList<>();
			while (rs.next())
			{
				Map<String,String> map = new HashMap<>();
				map.put("code", rs.getString("code"));
				map.put("email", rs.getString("email"));
				map.put("name", rs.getString("name"));
				listMap.add(map);
			}
			request.setAttribute("LIST", listMap);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/member/list.jsp");
			requestDispatcher.include(request, response);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
//			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/member/error.jsp");
//			requestDispatcher.forward(request,response );
		}
		finally
		{
			if(rs!= null)
			{
				try
				{
					rs.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
			if(stat !=null)
			{
				try
				{
					stat.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
			if(conn != null)
			{
				try
				{
					conn.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}
