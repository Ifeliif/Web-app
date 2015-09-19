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
import dao.StopsDAO;
import dto.LinesDTO;
import dto.StopsDTO;


public class LineListServlet extends AbstractServlet{

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(LineListServlet.class);

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

		LinesDAO lineDAO=new LinesDAO(con);
		List<LinesDTO> lineList=lineDAO.selectAll();
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

		return "/LineList.jsp";
	}

}
