package servlet;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dao.BusesDAO;
import dto.BusesDTO;

public class BusInsertServlet extends AbstractServlet{

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(BusInsertServlet.class);

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
			return "/LoginServlet";

		}
		System.out.println("Insert");


		BusesDTO busDTO=new BusesDTO();
		busDTO.setSeat_colum(Integer.parseInt(request.getParameter("seat_colum")));
		busDTO.setSeat_row(Integer.parseInt(request.getParameter("seat_row")));
		busDTO.setNp_name(request.getParameter("np_name"));
		busDTO.setNp_class_no(Integer.parseInt(request.getParameter("np_class_no")));
		busDTO.setNp_hiragana(request.getParameter("np_hiragana"));
		busDTO.setNp_no1(Integer.parseInt(request.getParameter("np_no1")));
		busDTO.setNp_no2(Integer.parseInt(request.getParameter("np_no2")));

		BusesDAO busDAO=new BusesDAO(con);
		int a=busDAO.insert(busDTO);
		System.out.println(a+"件追加しました。");


		return "BusListServlet";
	}

}