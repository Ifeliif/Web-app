package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import dto.ManagersDTO;

	public class ManagersDAO {
		private Connection connection;
		public ManagersDAO(Connection connection){
			super();
			this.connection=connection;
		}

	public ManagersDTO selectByLogin(String Manager_id){
		ManagersDTO result=null;
		try{
			PreparedStatement ps=connection.prepareStatement(
					" SELECT MANAGER_ID, PASSWORD FROM MANAGERS WHERE MANAGER_ID=? ");
			ps.setString(1, Manager_id);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				result=new ManagersDTO();
				result.setManager_id(rs.getString("MANAGER_ID"));
				result.setPassword(rs.getString("PASSWORD"));
			}
			return result;
		}catch (SQLException e){
			throw new RuntimeException(e);
		}
	}
}
