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
import dao.StopsDAO;
import dto.BusesDTO;
import dto.LinesDTO;
import dto.StopsDTO;

public class LineInsertInputServlet extends AbstractServlet {
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(BusInputServlet.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response,
			Connection con) throws ServletException, IOException {
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
		System.out.println("LineInsertInputServlet");

		String bus_id=request.getParameter("bus_id");
		request.setAttribute("bus_id", bus_id);
		StopsDAO stopsDAO = new StopsDAO(con);
		List<StopsDTO> stopsList = stopsDAO.selectAll();

		BusesDAO busesDAO = new BusesDAO(con);
		List<BusesDTO> busesList = busesDAO.selectAll();


		String startDate = request.getParameter("raw_start_date");
		String endDate = request.getParameter("raw_end_date");

		request.setAttribute("raw_start_date", startDate);
		request.setAttribute("raw_end_date", endDate);

		request.setAttribute("stopsDTOList", stopsList);
		request.setAttribute("busesDTOList", busesList);

		return "/WEB-INF/pages/common/LineInsertInput.jsp";
	}


}
