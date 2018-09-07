package study.day02;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * User: kimkm
 * Date: 2018-08-18
 * Time: 오전 10:59
 */
public class AppInitServlet extends HttpServlet
{
	@Override
	public void init(ServletConfig config) throws ServletException
	{
		System.out.println("APPLICATION Init,...");
		super.init(config);

		try{
			ServletContext sc = this.getServletContext();
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/midas1", "kimkm", "kimkm");
			sc.setAttribute("conn", conn);
		}catch (Throwable e){
			throw new ServletException();
		}
	}

	@Override
	public void destroy()
	{
		System.out.println("APPLICATION Destroy,...");
		super.destroy();
		Connection conn = (Connection) this.getServletContext().getAttribute("conn");
		try{
			if(conn != null && !conn.isClosed())
			{
				conn.close();
			}
		}catch (Exception ignore)
		{

		}
	}
}

