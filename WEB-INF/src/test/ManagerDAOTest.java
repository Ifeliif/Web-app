package test;
import java.sql.*;


import dao.ManagersDAO;
import dto.ManagersDTO;


import util.DBConnectUtil;
public class ManagerDAOTest {

	public static void main(String[] args) {
		Connection con=DBConnectUtil.getConnection();
		try{
		ManagersDAO managersDAO=new ManagersDAO(con);
		showManager(managersDAO.selectByLogin("B001"));
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
	private static void showManager(ManagersDTO managersDTO) {
		String result="";
		result+=managersDTO.getManager_id();
		result+=":";
		result+=managersDTO.getPassword();
		System.out.println(result);

	}
}
