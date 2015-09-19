package servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LogoutServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(exampleServlet.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response,
			Connection con) throws ServletException, IOException{
		log.debug("servlet start");

		HttpSession session=request.getSession();
		session.invalidate();

		System.out.println("ログアウト");
		return "LoginServlet";
	}

}
