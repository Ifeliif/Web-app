package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory.getLog(AbstractServlet.class);


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doExecute(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doExecute(req, resp);
	}

	private void doExecute(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		String oneTimeMessage = (String)session.getAttribute("oneTimeMessage");
		if(oneTimeMessage != null){
			req.setAttribute("oneTimeMessage", oneTimeMessage);
			session.removeAttribute("oneTimeMessage");
		}

		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://ZK047E45/bus_manage", "postgres", "postgres");
			con.setAutoCommit(false);
			String next = execute(req, resp, con);
			con.commit();

			if(next != null){
				if(next.startsWith("redirect:")){
					String nextPath = next.replaceFirst("redirect:", "");
					resp.sendRedirect(nextPath);
				}
				else{
					RequestDispatcher dispatcher = req.getRequestDispatcher(next);
					dispatcher.forward(req, resp);
				}
			}
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
					log.error(e1);
			}
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} finally{
			if(con != null){
				try {
					con.close();
				} catch (SQLException e) {
					log.error(e);
				}
			}
		}
	}

	public abstract String execute(HttpServletRequest req, HttpServletResponse resp, Connection connection)
			throws ServletException, IOException;
}
