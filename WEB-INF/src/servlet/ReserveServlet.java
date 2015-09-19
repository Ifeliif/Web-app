package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Member_Reserve;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dao.LinesDAO;
import dao.Member_ReserveDAO;
import dao.MembersDAO;
import dao.StopsDAO;
import dto.LinesDTO;
import dto.MembersDTO;
import dto.StopsDTO;


public class ReserveServlet extends AbstractServlet{

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(exampleServlet.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp,
			Connection connection) throws ServletException, IOException {
		log.debug("servlet start");

		String member_id=req.getParameter("member_id");
		//idによる会員情報の特定およびデータ送信を行う
		MembersDAO memberDAO =new MembersDAO(connection);
		List<MembersDTO>memberList=memberDAO.selectCertain(member_id);
		String member_name=memberList.get(0).getMember_name();
		String telephone=memberList.get(0).getTelephone();
		String mail=memberList.get(0).getMail();

		req.setAttribute("member_id", member_id);
		req.setAttribute("member_name", member_name);
		req.setAttribute("telephone", telephone);
		req.setAttribute("mail", mail);

		//idによる路線情報・予約情報の特定及びデータ送信を行う
		Member_ReserveDAO mrDAO =new Member_ReserveDAO(connection);
		List<Member_Reserve>mrList=mrDAO.selectCertain(member_id);
		//(続き)以下データの加工を行う
		//stop id→stop_name
		StopsDAO stopDAO=new StopsDAO(connection);
		List<StopsDTO> stopList=stopDAO.selectAll();
		for(int i=0;i<mrList.size();i++){
			for(int j=0;j<stopList.size();j++){
				//stop id→stop_name
				if(stopList.get(j).getStop_id().equals(mrList.get(i).getArrival_stop_id())){
					mrList.get(i).setArrival_stop_id(stopList.get(j).getStop_name());
				}
				if(stopList.get(j).getStop_id().equals(mrList.get(i).getDeparture_stop_id())){
					mrList.get(i).setDeparture_stop_id(stopList.get(j).getStop_name());
				}
			}
			//hhmm→hh:mm
			String start_time_before=mrList.get(i).getStart_time();
			String end_time_before=mrList.get(i).getEnd_time();

			String start_time=start_time_before.substring(0,2)+":";
			start_time+=start_time_before.substring(2,4);
			String end_time=end_time_before.substring(0,2)+":";
			end_time+=end_time_before.substring(2,4);
			mrList.get(i).setStart_time(start_time);
			mrList.get(i).setEnd_time(end_time);

		}
		req.setAttribute("member_reserve", mrList);

		return "Reserve.jsp";

	}

}