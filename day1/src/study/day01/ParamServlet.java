package study.day01;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: kimkm
 * Date: 2018-08-04
 * Time: 오후 12:09
 */
public class ParamServlet extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String url = getInitParameter("url");
		System.out.println(url);
		super.doPost(req, resp);
	}
}
