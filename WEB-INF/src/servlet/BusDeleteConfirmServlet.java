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

import dao.BusesDAO;
import dto.BusesDTO;


public class BusDeleteConfirmServlet extends AbstractServlet{

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(BusDeleteConfirmServlet.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse respsponse,
			Connection connection) throws ServletException, IOException {
		log.debug("servlet start");
		//ログイン状態の確認(セッションスコープ確認)
				//HttpSessionクラスからインスタンスを作成
				HttpSession session = request.getSession();

				//sessionクラスに入っている管理者IDを取ってきて、変数に入れる
				String manager_id = (String)session.getAttribute("manager_id");

				//管理者IDがnull(= ログインしていない状態)ならば、ログイン画面に遷移
				if(manager_id == null){
					System.out.println("BusInputNull");
					return "/LoginServlet";

						}
		System.out.println("BusDeleteConfirmServlet");
		String bus_id=request.getParameter("bus_id");

		BusesDAO managerDAO=new BusesDAO(connection);
		List<BusesDTO> busList=managerDAO.selectCertain(bus_id);
		request.setAttribute("busList", busList);


		return "busDeleteConfirm.jsp";
	}

}