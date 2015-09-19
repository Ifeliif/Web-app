package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dao.ManagersDAO;
import dto.ManagersDTO;


public class LoginServlet extends AbstractServlet{

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(exampleServlet.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response,
			Connection con) throws ServletException, IOException{
		log.debug("servlet start");
		String manager_id=request.getParameter("manager_id");
		String password=request.getParameter("password");


		HttpSession session = request.getSession();
		try{
			ManagersDAO managerDAO=new ManagersDAO(con);
			if(judgement(managerDAO,manager_id,password)){
				//ログイン状態の開始（セッションスコープのセット）

				//ログインフォームで入力されたIDをセッションIDとする
				session.setAttribute("manager_id",manager_id);

				System.out.println(manager_id);
				return "/WEB-INF/pages/common/topHeader.jsp";
			}

		}catch(Exception e){
			session.invalidate();

			System.out.println("bbb");
			return "/login.html";
		}
		session.invalidate();
		return "/login.html";
	}

	public static boolean judgement(ManagersDAO managerDAO,String manager_id,String password){
		ManagersDTO manager= managerDAO.selectByLogin(manager_id);

		if(manager.getPassword().equals(password)){
			return true;
		}else{
			return false;
		}
	}

}
