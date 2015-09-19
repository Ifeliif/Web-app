package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.ValidationUtil;

import dao.LinesDAO;
import dao.StopsDAO;
import dto.LinesDTO;
import dto.StopsDTO;
import form.DateForm;

public class LineListFocusServlet extends AbstractServlet {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory
			.getLog(LineListFocusServlet.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response, Connection con)
			throws ServletException, IOException {
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

		String rawDay = request.getParameter("day");
		DateForm dateForm=new DateForm();
		dateForm.setDay(rawDay);
		List<String> errmsgs=ValidationUtil.validate(dateForm);
		if(errmsgs.size() > 0){
			System.out.println(errmsgs.get(0));
			request.setAttribute("errorMessages", errmsgs);
			return "LineListServlet";
		}

		if(rawDay.length()>8){
			request.setAttribute("oneTimeMessage", "年月日をyyyyMMdd方式で入力してください。");
			return "LineListServlet";
		}

		System.out.println(rawDay);
		String strA=rawDay.substring(0,4);
		String strB=rawDay.substring(4,6);
		String strC=rawDay.substring(6,8);
		String fmtDay=strA+"-"+strB+"-"+strC;

		//(MM>12 or dd>31)の場合遷移しない
		if(Integer.parseInt(strB)>12||Integer.parseInt(strC)>31){
			request.setAttribute("oneTimeMessage", "年月日をyyyyMMdd方式で入力してください。");
			return "LineListServlet";
				}


		LinesDAO lineDAO = new LinesDAO(con);
		List<LinesDTO> lineList = lineDAO.selectCertain_day(Date.valueOf(fmtDay));
		StopsDAO stopDAO=new StopsDAO(con);
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

		request.setAttribute("lineList", lineList);

		String Day ="";
		String str1 = new String(request.getParameter("day"));
	    String new_str1 = str1.substring(0, 4);
	    Day += new_str1 + "年";

	    String new_str2 = str1.substring(4, 6);
	    Day += new_str2 + "月";

	    String new_str3 = str1.substring(6, 8);
	    Day += new_str3 + "日";

	    System.out.println(Day);
		//HttpSession session = request.getSession();
			session.setAttribute("oneTimeMessage", Day + "に運行している路線の検索結果");

		if(lineList.size()==0){
			request.setAttribute("oneTimeMessage",Day + "と一致するデータがありません");

		}else{
			request.setAttribute("oneTimeMessage",Day + "に運行している路線の検索結果");
		}

		return "/LineList.jsp";
	}

}
