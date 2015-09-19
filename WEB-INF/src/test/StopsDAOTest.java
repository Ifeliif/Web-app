package test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import util.DBConnectUtil;
import dao.StopsDAO;
import dto.StopsDTO;

public class StopsDAOTest {

	public static void main(String[] args) {
		//以下select文のテスト
		Connection con=DBConnectUtil.getConnection();
		try{
		StopsDAO stopsDAO=new StopsDAO(con);
		showAllStop(stopsDAO);
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
		System.out.println("+++++++++");

	}

	private static void showAllStop(StopsDAO stopsDAO) {
		// TODO 自動生成されたメソッド・スタブ
		List<StopsDTO> dtos=stopsDAO.selectAll();
		for (StopsDTO stopsDTO : dtos){
			String result ="";
			result+=stopsDTO.getStop_id();
			result+=":";
			result+=stopsDTO.getPrefecture();
			result+=":";
			result+=stopsDTO.getStop_name();
			System.out.println(result);
		}
	}
}
