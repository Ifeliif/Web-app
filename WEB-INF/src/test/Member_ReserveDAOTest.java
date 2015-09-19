package test;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;

import model.Member_Reserve;

import dao.Member_ReserveDAO;



import util.DBConnectUtil;
public class Member_ReserveDAOTest {

	public static void main(String[] args) {
		Connection con=DBConnectUtil.getConnection();
		try{
			Member_ReserveDAO mrDAO=new Member_ReserveDAO(con);
			List<Member_Reserve>mrList=mrDAO.selectCertain("tanakachan");
			showMember_Reserve(mrList);

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
	private static void showMember_Reserve(List<Member_Reserve> mrList) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");

		for (Member_Reserve mr : mrList){
		String result="";
		result+=sdf.format(mr.getReserve_date());
		result+=":";
		result+=mr.getLine_id();
		result+=":";
		result+=mr.getDeparture_stop_id();
		result+=":";
		result+=mr.getArrival_stop_id();
		result+=":";
		result+=mr.getStart_time();
		result+=":";
		result+=mr.getEnd_time();
		result+=":";
		result+=mr.getFee();
		result+=":";
		result+=mr.getBus_id();
		System.out.println(result);
		}
	}
}
