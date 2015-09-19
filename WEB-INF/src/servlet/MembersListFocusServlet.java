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

import dao.LinesDAO;
import dao.MembersDAO;
import dao.StopsDAO;
import dto.LinesDTO;
import dto.MembersDTO;
import dto.StopsDTO;

import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.LogFactory;

public class MembersListFocusServlet extends AbstractServlet {

	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(MembersListFocusServlet.class);
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp,
			Connection con) throws ServletException, IOException {
		log.debug("servlet start");
		System.out.println("MembersListFocus");
		String member_id = req.getParameter("member_id");
		String member_name = req.getParameter("member_name");
		System.out.println(member_name);
		List<MembersDTO> membersList = null;

		if(!(member_id.isEmpty()) && member_name.isEmpty()){
			System.out.println("id");
		MembersDAO membersDAO = new MembersDAO(con);
		membersList = membersDAO.selectReserch_id(member_id);
		}

		else if(member_id.isEmpty() && !(member_name.isEmpty())){
			System.out.println("name");
			MembersDAO membersDAO = new MembersDAO(con);
			membersList = membersDAO.selectReserch_name(member_name);
			System.out.println(member_name);
		}

		else if(!(member_id.isEmpty() && member_name.isEmpty())){
			System.out.println("idname");
			MembersDAO membersDAO = new MembersDAO(con);
			membersList = membersDAO.selectReserch(member_id,member_name);
		}

		else if(member_id.isEmpty() && member_name.isEmpty()){
			System.out.println("Empty");
			return "MembersListServlet";
		}
		req.setAttribute("membersList", membersList);
		return "/MembersList.jsp";

}
}

