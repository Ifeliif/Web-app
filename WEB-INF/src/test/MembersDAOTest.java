package test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import util.DBConnectUtil;
import dao.BusesDAO;
import dao.LinesDAO;
import dao.MembersDAO;
import dto.BusesDTO;
import dto.LinesDTO;
import dto.MembersDTO;

public class MembersDAOTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		System.out.println("a");
		Connection con=DBConnectUtil.getConnection();



////	//selectCertainのテスト
	try{
		MembersDAO memberDAO=new MembersDAO(con);
		String member_id=("tanakachan");
		showAllMembers2(memberDAO,member_id);
		con.commit();
		} catch (Throwable t) {
			t.printStackTrace();
			if(con!=null){
				try{
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
		}
		}
	System.out.println("+++++++++");

//	//selectReserchのテスト
	try{
		MembersDAO memberDAO=new MembersDAO(con);
		String member_id=("tan");
		String member_name=("田中");
		showSelectMembers3(memberDAO,member_id,member_name);
		con.commit();
		} catch (Throwable t) {
			t.printStackTrace();
			if(con!=null){
				try{
					con.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
		}
		}
	System.out.println("+++++++++");
	}
	//List<LinesDTO> dtos=lineDAO.selectCertain_day(day);
	private static void showSelectMembers3(MembersDAO memberDAO, String member_id,
			String member_name) {
		// TODO 自動生成されたメソッド・スタブ
		List<MembersDTO> dtos=memberDAO.selectAll();
		for (MembersDTO memberDTO : dtos){
			String result ="";
			result+=memberDTO.getMember_id();
			result+=":";
			result+=memberDTO.getMember_name();
			result+=":";
			result+=memberDTO.getTelephone();
			result+=":";
			result+=memberDTO.getMail();
			result+=":";

			System.out.println(result);
	}
	}

		private static void showAllMembers2(MembersDAO memberDAO, String member_id) {
		// TODO 自動生成されたメソッド・スタブ
		List<MembersDTO> dtos=memberDAO.selectReserch_id(member_id);
		for (MembersDTO memberDTO : dtos){
			String result ="";
			result+=memberDTO.getMember_id();
			result+=":";
			result+=memberDTO.getMember_name();
			result+=":";
			result+=memberDTO.getTelephone();
			result+=":";
			result+=memberDTO.getMail();
			result+=":";

			System.out.println(result);
	}
	}

	private static void showAllMembers(MembersDAO memberDAO) {
		// TODO 自動生成されたメソッド・スタブ
		List<MembersDTO> dtos=memberDAO.selectAll();
		for (MembersDTO memberDTO : dtos){
			String result ="";
			result+=memberDTO.getMember_id();
			result+=":";
			result+=memberDTO.getMember_name();
			result+=":";
			result+=memberDTO.getTelephone();
			result+=":";
			result+=memberDTO.getMail();
			result+=":";

			System.out.println(result);
	}
}

}

