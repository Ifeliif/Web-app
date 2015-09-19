package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
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


public class LineUpdateInputServlet extends AbstractServlet {
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

		String line_id=request.getParameter("line_id");

		StopsDAO stopsDAO = new StopsDAO(con);
		List<StopsDTO> stopsList = stopsDAO.selectAll();

		BusesDAO busesDAO = new BusesDAO(con);
		List<BusesDTO> busesList = busesDAO.selectAll();

		LinesDAO linesDAO = new LinesDAO(con);
		List<LinesDTO> lineList = linesDAO.selectCertain_id(line_id);

		SimpleDateFormat smf=new SimpleDateFormat("yyyyMMdd");

		String start_time=lineList.get(0).getStart_time();
		String end_time=lineList.get(0).getEnd_time();
		String start_date=smf.format(lineList.get(0).getStart_date());
		String end_date=smf.format(lineList.get(0).getEnd_date());
		int fee=lineList.get(0).getFee();
		String arrival_stop_id=lineList.get(0).getArrival_stop_id();
		String departure_stop_id=lineList.get(0).getDeparture_stop_id();
		String bus_id=lineList.get(0).getBus_id();

		request.setAttribute("start_time", start_time);
		request.setAttribute("end_time", end_time);
		request.setAttribute("start_date", start_date);
		request.setAttribute("end_date", end_date);
		request.setAttribute("fee", fee);
		request.setAttribute("arrival_stop_id_before", arrival_stop_id);
		request.setAttribute("departure_stop_id_before", departure_stop_id);
		request.setAttribute("bus_id", bus_id);

		//バスのシート列数およびシート数を取得して送信
		BusesDAO busDAO=new BusesDAO(con);
		List<BusesDTO> busList=busDAO.selectAll();
		for(int i=0;i<busList.size();i++){
			if(bus_id.equals(busList.get(i).getBus_id())){
				int seat_colum=busList.get(i).getSeat_colum();
				int seat_row=busList.get(i).getSeat_row();
				request.setAttribute("seat_colum",seat_colum);
				request.setAttribute("seat_row",seat_row);
			}
		}

		//stop_id→stop_name
				LinesDTO lineDTO=new LinesDTO();
				StopsDAO stopDAO=new StopsDAO(con);
				List<StopsDTO> stopList=stopDAO.selectAll();
				for(int j=0;j<stopList.size();j++){
					if(stopList.get(j).getStop_id().equals(arrival_stop_id)){
						arrival_stop_id=stopList.get(j).getStop_name();
					}
					if(stopList.get(j).getStop_id().equals(departure_stop_id)){
						departure_stop_id=stopList.get(j).getStop_name();
					}
				}
		request.setAttribute("arrival_stop_id", arrival_stop_id);
		request.setAttribute("departure_stop_id", departure_stop_id);
		request.setAttribute("stopsDTOList", stopsList);
		request.setAttribute("busesDTOList", busesList);
		request.setAttribute("line_id", line_id);



		return "/WEB-INF/pages/common/lineUpdateInput.jsp";
	}

}
