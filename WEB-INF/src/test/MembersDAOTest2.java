package test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import util.DBConnectUtil;
import dao.MembersDAO;
import dto.MembersDTO;

public class MembersDAOTest2 {

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

	}


	private static void showAllMembers2(MembersDAO memberDAO, String member_id) {
	// TODO 自動生成されたメソッド・スタブ
	List<MembersDTO> dtos=memberDAO.selectCertain(member_id);
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
