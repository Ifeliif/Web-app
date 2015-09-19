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
import dao.LinesDAO;
import dao.StopsDAO;
import dto.BusesDTO;
import dto.LinesDTO;
import dto.StopsDTO;


public class LineDeleteConfirmServlet extends AbstractServlet{

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(LineDeleteConfirmServlet.class);

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
		System.out.println(line_id);

		LinesDAO lineDAO=new LinesDAO(connection);
		List<LinesDTO> lineList=lineDAO.selectCertain_id(line_id);
		StopsDAO stopDAO=new StopsDAO(connection);
		List<StopsDTO> stopList=stopDAO.selectAll();
		for(int i=0;i<lineList.size();i++){
			String str1=lineList.get(i).getStart_time().substring(0,2)+":"+lineList.get(i).getStart_time().substring(2,4);
			lineList.get(i).setStart_time(str1);
			String str2=lineList.get(i).getEnd_time().substring(0,2)+":"+lineList.get(i).getEnd_time().substring(2,4);
			lineList.get(i).setEnd_time(str2);

			for(int j=0;j<stopList.size();j++){
				if(stopList.get(j).getStop_id().equals(lineList.get(i).getArrival_stop_id())){
					lineList.get(i).setArrival_stop_id(stopList.get(j).getStop_name());
				}
				if(stopList.get(j).getStop_id().equals(lineList.get(i).getDeparture_stop_id())){
					lineList.get(i).setDeparture_stop_id(stopList.get(j).getStop_name());
				}
			}
		}


		BusesDAO busDAO=new BusesDAO(connection);
		List<BusesDTO> busList=busDAO.selectAll();

		for(int i=0;i<busList.size();i++){
			if(lineList.get(0).getBus_id().equals(busList.get(i).getBus_id())){
				int seat_colum=busList.get(i).getSeat_colum();
				int seat_row=busList.get(i).getSeat_row();
				req.setAttribute("seat_colum",seat_colum);
				req.setAttribute("seat_row",seat_row);
			}
		}


		req.setAttribute("line_id", lineList.get(0).getLine_id());
		req.setAttribute("arrival_stop_id", lineList.get(0).getArrival_stop_id());
		req.setAttribute("departure_stop_id", lineList.get(0).getDeparture_stop_id());
		req.setAttribute("fee", lineList.get(0).getFee());
		req.setAttribute("bus_id", lineList.get(0).getBus_id());
		req.setAttribute("start_date", lineList.get(0).getStart_date());
		req.setAttribute("end_date", lineList.get(0).getEnd_date());
		req.setAttribute("start_time", lineList.get(0).getStart_time());
		req.setAttribute("end_time", lineList.get(0).getEnd_time());

		return "WEB-INF/pages/LineDeleteConfirm.jsp";

	}

}