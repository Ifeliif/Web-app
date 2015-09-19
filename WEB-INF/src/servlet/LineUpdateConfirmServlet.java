package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.ValidationUtil;

import dao.BusesDAO;
import dao.LinesDAO;
import dao.StopsDAO;
import dto.BusesDTO;
import dto.LinesDTO;
import dto.StopsDTO;
import form.LineForm;


public class LineUpdateConfirmServlet extends AbstractServlet{

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(LineUpdateConfirmServlet.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp,
			Connection connection) throws ServletException, IOException {
		log.debug("servlet start");
		String line_id=req.getParameter("line_id");
		String arrival_stop_id=req.getParameter("arrival_stop_id");
		String departure_stop_id=req.getParameter("departure_stop_id");
		String fee_str=req.getParameter("fee");
		String bus_id=req.getParameter("bus_id");
		String start_date_str=req.getParameter("start_date");
		String end_date_str=req.getParameter("end_date");
		String start_time=req.getParameter("start_time");
		String end_time=req.getParameter("end_time");

		//料金に数値以外が入力された場合遷移しない。
		for(int i=0;i<fee_str.length();i++){
			char c=fee_str.charAt(i);
			if(c>'9' || c<'0'){
				req.setAttribute("oneTimeMessage", "料金は6桁以内の半角数字の方式で入力してください。");
				return "LineUpdateInputServlet";
			}
		}


//バリデーションを使うよ！
		LineForm lineForm=new LineForm();
		lineForm.setStart_date(start_date_str);
		lineForm.setEnd_date(end_date_str);
		lineForm.setFee(fee_str);
		lineForm.setStart_time(start_time);
		lineForm.setEnd_time(end_time);
		List<String> errmsgs=ValidationUtil.validate(lineForm);
		if(errmsgs.size() > 0){
			System.out.println(errmsgs.get(0));
			req.setAttribute("errorMessages", errmsgs);
			return "LineUpdateInputServlet";
		}

//小細工する前に必要なデータを送信する。次のサーブレットで使用する。
		req.setAttribute("arrival_stop_id_before", arrival_stop_id);
		req.setAttribute("departure_stop_id_before", departure_stop_id);
		req.setAttribute("start_time_before", start_time);
		req.setAttribute("end_time_before", end_time);

//yyyyMMdd→yyyy-MM-dd
		String start_date_str1=start_date_str.substring(0,4);
		String start_date_str2=start_date_str.substring(4,6);
		String start_date_str3=start_date_str.substring(6,8);
		start_date_str=start_date_str1+"-"+start_date_str2+"-"+start_date_str3;

		String end_date_str1=end_date_str.substring(0,4);
		String end_date_str2=end_date_str.substring(4,6);
		String end_date_str3=end_date_str.substring(6,8);
		end_date_str=end_date_str1+"-"+end_date_str2+"-"+end_date_str3;

		//出発時刻が到着時刻より遅い場合遷移しない
		if(Integer.parseInt(start_time)>Integer.parseInt(end_time)){
			req.setAttribute("oneTimeMessage", "出発時刻と到着時刻が不正です。");
			return "LineUpdateInputServlet";
		}

		//hhmm→hh:mm
		start_time=start_time.substring(0,2)+":"+start_time.substring(2,4);
		end_time=end_time.substring(0,2)+":"+end_time.substring(2,4);

//(hh>24 or mm>60 or MM>12 or dd>31)の場合遷移しない
if(Integer.parseInt(start_date_str2)>12||Integer.parseInt(start_date_str3)>31){
	req.setAttribute("oneTimeMessage", "運行開始日はyyyyMMdd方式で入力してください。");
	return "LineUpdateInputServlet";
		}
if(Integer.parseInt(end_date_str2)>12||Integer.parseInt(end_date_str3)>31){
	req.setAttribute("oneTimeMessage", "運行終了日はyyyyMMdd方式で入力してください。");
	return "LineUpdateInputServlet";
}


if(Integer.parseInt(start_time.substring(0,2))>24||Integer.parseInt(end_time.substring(0,2))>24||Integer.parseInt(start_time.substring(3,5))>59||Integer.parseInt(end_time.substring(3,5))>59){
req.setAttribute("oneTimeMessage", "出発時刻と到着時刻が不正です。");
return "LineUpdateInputServlet";
}

//開始日が終了日よりも前の場合遷移しない。
if(Date.valueOf(end_date_str).before(Date.valueOf(start_date_str))){
	req.setAttribute("oneTimeMessage", "運行開始日・終了日を正しく入力してください。");
	return "LineUpdateInputServlet";
}



//バスのシート列数およびシート数を取得して送信
		BusesDAO busDAO=new BusesDAO(connection);
		List<BusesDTO> busList=busDAO.selectAll();
		for(int i=0;i<busList.size();i++){
			if(bus_id.equals(busList.get(i).getBus_id())){
				int seat_colum=busList.get(i).getSeat_colum();
				int seat_row=busList.get(i).getSeat_row();
				req.setAttribute("seat_colum",seat_colum);
				req.setAttribute("seat_row",seat_row);
			}
		}



//stop_id→stop_name
		LinesDTO lineDTO=new LinesDTO();
		StopsDAO stopDAO=new StopsDAO(connection);
		List<StopsDTO> stopList=stopDAO.selectAll();
		for(int j=0;j<stopList.size();j++){
			if(stopList.get(j).getStop_id().equals(arrival_stop_id)){
				arrival_stop_id=stopList.get(j).getStop_name();
			}
			if(stopList.get(j).getStop_id().equals(departure_stop_id)){
				departure_stop_id=stopList.get(j).getStop_name();
			}
		}



		lineDTO.setArrival_stop_id(arrival_stop_id);
		lineDTO.setDeparture_stop_id(departure_stop_id);
		lineDTO.setFee(Integer.parseInt(fee_str));
		lineDTO.setBus_id(bus_id);
		lineDTO.setStart_date(Date.valueOf(start_date_str));
		lineDTO.setEnd_date(Date.valueOf(end_date_str));
		lineDTO.setStart_time(start_time);
		lineDTO.setEnd_time(end_time);
		lineDTO.setLine_id(line_id);

		req.setAttribute("lineDTO", lineDTO);

		return "/WEB-INF/pages/common/LineUpdateConfirm.jsp";

	}

}
