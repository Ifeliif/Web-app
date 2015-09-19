package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Seat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dao.BusesDAO;
import dao.LinesDAO;
import dao.ReserveDAO;
import dao.StopsDAO;
import dto.BusesDTO;
import dto.LinesDTO;
import dto.ReserveDTO;
import dto.StopsDTO;


public class Line_ReserveServlet extends AbstractServlet{

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(exampleServlet.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response,
			Connection con) throws ServletException, IOException {
		log.debug("servlet start");

		String line_id=request.getParameter("line_id");
		LinesDAO lineDAO=new LinesDAO(con);
		List<LinesDTO> line=lineDAO.selectCertain_id(line_id);
		String bus_id=line.get(0).getBus_id();

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = sdf.format(cal.getTime());
		Date today=Date.valueOf(strDate);



		LinesDAO linesDAO=new LinesDAO(con);
		List<LinesDTO>lineList=linesDAO.selectCertain_id(line_id);

		//データの加工を行う。
		//stop_id→stop_name
		StopsDAO stopDAO=new StopsDAO(con);
		List<StopsDTO> stopList=stopDAO.selectAll();
		for(int j=0;j<stopList.size();j++){
			if(stopList.get(j).getStop_id().equals(lineList.get(0).getArrival_stop_id())){
				lineList.get(0).setArrival_stop_id(stopList.get(j).getStop_name());
			}
			if(stopList.get(j).getStop_id().equals(lineList.get(0).getDeparture_stop_id())){
				lineList.get(0).setDeparture_stop_id(stopList.get(j).getStop_name());
			}
		}

		request.setAttribute("reserve_date", today);
		request.setAttribute("arrival_stop_id", lineList.get(0).getArrival_stop_id());
		request.setAttribute("departure_stop_id", lineList.get(0).getDeparture_stop_id());
		request.setAttribute("start_time", lineList.get(0).getStart_time().substring(0,2)+":"+lineList.get(0).getStart_time().substring(2,4));
		request.setAttribute("end_time", lineList.get(0).getEnd_time().substring(0,2)+":"+lineList.get(0).getEnd_time().substring(2,4));
		request.setAttribute("fee", lineList.get(0).getFee());

		ReserveDAO reserveDAO=new ReserveDAO(con);
		List<ReserveDTO>reserveList=reserveDAO.selectCertainDateLine(today, line_id);
		request.setAttribute("reserveList", reserveList);
//bus_idからseat_columおよびseat_rowを取得する
		BusesDAO busDAO=new BusesDAO(con);
		List<BusesDTO> busDTO=busDAO.selectCertain(bus_id);

		//Seatモデルを作る。Seat型のリストに各データを保管する。
		List<Seat> seatList=new ArrayList<Seat>();
		for(int i=0;i<reserveList.size();i++){
			Seat seat=new Seat();
			if(reserveList.get(i).getSeat_number().length()==2){
				seat.setSeat_colum(Integer.parseInt(reserveList.get(i).getSeat_number().substring(0,1)));
				seat.setSeat_row(reserveList.get(i).getSeat_number().substring(1,2).charAt(0));
			}else{
				seat.setSeat_colum(Integer.parseInt(reserveList.get(i).getSeat_number().substring(0,2)));
				seat.setSeat_row(reserveList.get(i).getSeat_number().substring(2,3).charAt(0));
			}
			seatList.add(seat);
		}
//二次元配列seatArray[seat_colum][seat_row]を定義する。
		int seat_colum=busDTO.get(0).getSeat_row()+1;
		int seat_row=busDTO.get(0).getSeat_colum();
		System.out.println("seat_row"+seat_row);
		int[][] seatArray = new int[seat_colum][seat_row];


//seatListからデータを取り出してある場合はseatArrayに1を代入していく。
		for (int i=0;i<seatList.size();i++){
			int seat_row_no=seatList.get(i).getSeat_row()-65;
			int seat_colum_no=seatList.get(i).getSeat_colum();
			seatArray[seat_colum_no][seat_row_no]=1;
		}
		//新たにseatList_emptyを定義する。
		List<Seat> seatList_occupy=new ArrayList<Seat>();
		//seatArrayを調べる。0である場合はリストに加える。
		for(int i=1;i<seat_colum;i++){
			for(int j=0;j<seat_row;j++){
				Seat seat=new Seat();
				if(seatArray[i][j]==0){
					seat.setSeat_colum(i);
					seat.setSeat_row((char)(j+65));
					seatList_occupy.add(seat);
				}
			}
		}
		request.setAttribute("seatList",seatList_occupy);

		System.out.println("DailyLineReserveOK");
		return "/DailyLineReserve.jsp";
	}

}