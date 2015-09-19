package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dao.LinesDAO;
import dto.LinesDTO;


public class LineUpdateServlet extends AbstractServlet{

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(LineUpdateServlet.class);

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
		String arrival_stop_id=req.getParameter("arrival_stop_id");
		String departure_stop_id=req.getParameter("departure_stop_id");
		String fee_str=req.getParameter("fee");
		String bus_id=req.getParameter("bus_id");
		String start_date_str=req.getParameter("start_date");
		String end_date_str=req.getParameter("end_date");
		String start_time=req.getParameter("start_time");
		String end_time=req.getParameter("end_time");



		LinesDTO lineDTO=new LinesDTO();
		LinesDAO lineDAO=new LinesDAO(connection);
		lineDTO.setArrival_stop_id(arrival_stop_id);
		lineDTO.setDeparture_stop_id(departure_stop_id);
		lineDTO.setFee(Integer.parseInt(fee_str));
		lineDTO.setBus_id(bus_id);
		lineDTO.setStart_date(Date.valueOf(start_date_str));
		lineDTO.setEnd_date(Date.valueOf(end_date_str));
		lineDTO.setStart_time(start_time);
		lineDTO.setEnd_time(end_time);
		lineDTO.setLine_id(line_id);

		int deleteCount=lineDAO.update(lineDTO);
		System.out.println(deleteCount+"件更新しました。");


		return "LineListServlet";

	}

}