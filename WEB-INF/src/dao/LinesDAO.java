package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import dto.LinesDTO;


public class LinesDAO {
	private Connection connection;
	public LinesDAO(Connection connection){
		super();
		this.connection=connection;
	}


	public List<LinesDTO> selectAll(){
		try{
			PreparedStatement ps=connection.prepareStatement("select  * from lines order by line_id" );
			ResultSet rs=ps.executeQuery();
			List<LinesDTO> result=new ArrayList<LinesDTO>();
			while(rs.next()){
				LinesDTO lineDTO=new LinesDTO();
				lineDTO.setLine_id(rs.getString("line_id"));
				lineDTO.setArrival_stop_id(rs.getString("arrival_stop_id"));
				lineDTO.setDeparture_stop_id(rs.getString("departure_stop_id"));
				lineDTO.setFee(rs.getInt("fee"));
				lineDTO.setBus_id(rs.getString("bus_id"));
				lineDTO.setStart_date(rs.getDate("start_date"));
				lineDTO.setEnd_date(rs.getDate("end_date"));
				lineDTO.setStart_time(rs.getString("start_time"));
				lineDTO.setEnd_time(rs.getString("end_time"));
				result.add(lineDTO);

			}
			return result;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public List<LinesDTO> selectCertain_id(String line_id){
		try{
			PreparedStatement ps=connection.prepareStatement("select  * from lines where line_id=? order by line_id" );
			ps.setString(1, line_id);
			ResultSet rs=ps.executeQuery();
			List<LinesDTO> result=new ArrayList<LinesDTO>();
			while(rs.next()){
				LinesDTO lineDTO=new LinesDTO();
				lineDTO.setLine_id(rs.getString("line_id"));
				lineDTO.setArrival_stop_id(rs.getString("arrival_stop_id"));
				lineDTO.setDeparture_stop_id(rs.getString("departure_stop_id"));
				lineDTO.setFee(rs.getInt("fee"));
				lineDTO.setBus_id(rs.getString("bus_id"));
				lineDTO.setStart_date(rs.getDate("start_date"));
				lineDTO.setEnd_date(rs.getDate("end_date"));
				lineDTO.setStart_time(rs.getString("start_time"));
				lineDTO.setEnd_time(rs.getString("end_time"));
				result.add(lineDTO);

			}
			return result;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}



	public List<LinesDTO> selectCertain_day(Date day){
		try{
			PreparedStatement ps=connection.prepareStatement
					("select  * from lines where ? >= start_date and ? <= end_date" );
			ps.setDate(1, (java.sql.Date) day);
			ps.setDate(2, (java.sql.Date) day);
			ResultSet rs=ps.executeQuery();
			List<LinesDTO> result=new ArrayList<LinesDTO>();
			while(rs.next()){
				LinesDTO lineDTO=new LinesDTO();
				lineDTO.setLine_id(rs.getString("line_id"));
				lineDTO.setArrival_stop_id(rs.getString("arrival_stop_id"));
				lineDTO.setDeparture_stop_id(rs.getString("departure_stop_id"));
				lineDTO.setFee(rs.getInt("fee"));
				lineDTO.setBus_id(rs.getString("bus_id"));
				lineDTO.setStart_date(rs.getDate("start_date"));
				lineDTO.setEnd_date(rs.getDate("end_date"));
				lineDTO.setStart_time(rs.getString("start_time"));
				lineDTO.setEnd_time(rs.getString("end_time"));
				result.add(lineDTO);
			}
			return result;
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public int insert(LinesDTO dto){
		try{
			PreparedStatement ps=connection.prepareStatement
					("insert into lines (line_id,arrival_stop_id,departure_stop_id,fee,bus_id,start_date,end_date,start_time,end_time) " +
							"values('R'||to_char(nextval('line_id'),'FM0000'),?,?,?,?,?,?,?,?)");
			ps.setString(1, dto.getArrival_stop_id());
			ps.setString(2, dto.getDeparture_stop_id());
			ps.setInt(3, dto.getFee());
			ps.setString(4,dto.getBus_id());
			ps.setDate(5, dto.getStart_date());
			ps.setDate(6, dto.getEnd_date());
			ps.setString(7, dto.getStart_time());
			ps.setString(8, dto.getEnd_time());
			return ps.executeUpdate();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public int deleteCertain(String line_id){
		try{
			PreparedStatement ps=connection.prepareStatement("delete from lines where line_id = ?");
			ps.setString(1, line_id);
			return ps.executeUpdate();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

	public int update(LinesDTO lineDTO){
		try{
			PreparedStatement ps=connection.prepareStatement(
					"update lines set arrival_stop_id=? , departure_stop_id=? , fee=? , bus_id=? , start_date=? , end_date=?,start_time=? , end_time=? where line_id=?");
			ps.setString(1, lineDTO.getArrival_stop_id());
			ps.setString(2, lineDTO.getDeparture_stop_id());
			ps.setInt(3, lineDTO.getFee());
			ps.setString(4, lineDTO.getBus_id());
			ps.setDate(5, lineDTO.getStart_date());
			ps.setDate(6, lineDTO.getEnd_date());
			ps.setString(7,lineDTO.getStart_time());
			ps.setString(8, lineDTO.getEnd_time());
			ps.setString(9, lineDTO.getLine_id());
			return ps.executeUpdate();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}

}

