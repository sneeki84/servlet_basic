package study.day01;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * User: kimkm
 * Date: 2018-08-04
 * Time: 오후 12:27
 */
@WebFilter(
		urlPatterns = "/member/*",
		initParams = {
				@WebInitParam(name="encoding",value = "UTF-8")
		}
)
public class CharFilter implements Filter
{
	String charSet;
	@Override
	public void init(FilterConfig config) throws ServletException
	{
		charSet = config.getInitParameter("encoding");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		response.setCharacterEncoding(charSet);

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		if(session.getAttribute("name") == null)
		{
			RequestDispatcher rd = req.getRequestDispatcher("/auth/login");
			rd.forward(req, response);
		}else
		{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy()
	{

	}
}
