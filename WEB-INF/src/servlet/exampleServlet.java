package servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class exampleServlet extends AbstractServlet{

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(exampleServlet.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp,
			Connection connection) throws ServletException, IOException {
		log.debug("servlet start");


		return "WEB-INF/pages/common/lineUpdate.jsp";

	}

}