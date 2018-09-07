package study.day01;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * User: kimkm
 * Date: 2018-08-04
 * Time: 오전 10:07
 */
@WebServlet(
		urlPatterns = "/hi",
		initParams = {
				@WebInitParam(name="url",value = "http://www.naver.com"),
				@WebInitParam(name = "url2", value = "http://www.naver.co.kr")
		}
)
public class HelloWorld extends GenericServlet
{

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException
	{
		int a = Integer.parseInt(request.getParameter("a"));
		int b = Integer.parseInt(request.getParameter("b"));

		response.setContentType("text/plain");
		PrintWriter writer = response.getWriter();
		writer.println("더하기 결과 = " + (a + b));
		writer.println("param = " + getInitParameter("url"));
		writer.println("param = " + getInitParameter("url2"));







	}
}
