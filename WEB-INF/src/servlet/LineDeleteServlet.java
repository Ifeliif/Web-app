package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dao.LinesDAO;
import dto.LinesDTO;


public class LineDeleteServlet extends AbstractServlet{

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(LineDeleteServlet.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp,
			Connection connection) throws ServletException, IOException {
		log.debug("servlet start");
		//ログイン状態の確認(セッションスコープ確認)
				//HttpSessionクラスからインスタンスを作成
				HttpSession session = req.getSession();

				//sessionクラスに入っている管理者IDを取ってきて、変数に入れる
				String manager_id = (String)session.getAttribute("manager_id");

				//管理者IDがnull(= ログインしていない状態)ならば、ログイン画面に遷移
				if(manager_id == null){
					System.out.println("BusInputNull");
					return "/LoginServlet";

						}
		String line_id=req.getParameter("line_id");
		LinesDAO lineDAO=new LinesDAO(connection);
		int deleteCount=lineDAO.deleteCertain(line_id);
		System.out.println(deleteCount+"件削除しました。");

		return "LineListServlet";

	}

}