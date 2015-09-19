package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.StopsDTO;

public class StopsDAO {

	private Connection connection;
	public StopsDAO(Connection connection){
		super();
		this.connection=connection;
	}

	public List<StopsDTO> selectAll(){
		try{
			PreparedStatement ps=connection.prepareStatement("select * from stops order by stop_id" );
			ResultSet rs=ps.executeQuery();
			List<StopsDTO> result=new ArrayList<StopsDTO>();
			while(rs.next()){
				StopsDTO stopsDTO=new StopsDTO();
				stopsDTO.setStop_id(rs.getString("stop_id"));
				stopsDTO.setPrefecture(rs.getString("prefecture"));
				stopsDTO.setStop_name(rs.getString("stop_name"));
				result.add(stopsDTO);
			}
			return result;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

}
